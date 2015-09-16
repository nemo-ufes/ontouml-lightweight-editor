package br.ufes.inf.nemo.sml2alloy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import sml2.AttributeReference;
import sml2.ComparativeRelation;
import sml2.Function;
import sml2.FunctionParameter;
import sml2.Literal;
import sml2.MediationLink;
import sml2.ModeReference;
import sml2.Node;
import sml2.Participant;
import sml2.ReferableElement;
import sml2.ReferenceNode;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypeAssociation;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.sml2alloy.exception.UnsupportedElementException;
import br.ufes.inf.nemo.sml2alloy.exception.UnsupportedRelationException;
import br.ufes.inf.nemo.sml2alloy.parser.SMLParser;

public class SMLRuleCreator
{
	String rules;
	SMLParser smlparser;
	
	Map<EObject, String> elementToWorld = new HashMap<EObject, String>();
	Map<EObject, String> elementToVar = new HashMap<EObject, String>();
	
	public SMLRuleCreator(SMLParser smlparser)
	{
		rules = new String();
		this.smlparser = smlparser;
	}
	
	public void createCommonRules(SignatureDeclaration sigSituation, Variable exists)
	{
		rules += "\nfact situationCommon {";
		
		for (SituationType sit : smlparser.getSituationTypes())
		{
			String partNamesList = new String();
			String tempPartNamesList = new String();
			String mutabPartNamesList = new String();
			try
			{
				String add = new String();
				for (Participant part : smlparser.getInstances(sit, Participant.class))
				{
					if (!part.isImmutable())
					{
						if (!mutabPartNamesList.isEmpty()) mutabPartNamesList += "+";
						mutabPartNamesList += smlparser.getParticipationAlias(sit, part);
					}
					else if (smlparser.isTemporal(part))
					{
						if (!tempPartNamesList.isEmpty()) tempPartNamesList += "+";
						tempPartNamesList += smlparser.getParticipationAlias(sit, part);
					}
					else
					{
						if (!partNamesList.isEmpty()) partNamesList += "+";
						partNamesList += add+smlparser.getParticipationAlias(sit, part);
					}
				}
				
				//TODO talvez criar um pred só, ao invés de um para cada número diferente de parâmetros
				if (mutabPartNamesList.isEmpty())
					rules += "\n\tsituationCont["+smlparser.getAlias(sit)+", "+partNamesList+
							(!tempPartNamesList.isEmpty() ? ", "+tempPartNamesList : "")+"]\n";
				else
					rules += "\n\tsituationContMutable["+smlparser.getAlias(sit)+", "+
							(!partNamesList.isEmpty() ? partNamesList+", " : "")+
							mutabPartNamesList+"]\n";
				
				rules += "\tsituationUniq["+smlparser.getAlias(sit)+", "+partNamesList+
						(!tempPartNamesList.isEmpty() ? ", "+tempPartNamesList : "")+"]\n";
				rules += "\trigidity["+smlparser.getAlias(sit)+", "+sigSituation.getName()+", "+exists.getName()+"]\n";
			}
			catch (UnsupportedElementException e)
			{
				System.err.println(e.getMessage());
			}
		}
		
		rules += "}";
	}
	
	public void createSituationRule(SituationType sit)
	{
		String rules_aux = new String();
		try
		{
			rules_aux += "\n\nfact "+smlparser.getAlias(sit)+"Rule {\n";
			
			rules_aux += createSufficientRule(sit);
			rules_aux += "\n";
			rules_aux += createNecessaryRule(sit);
			
			rules_aux += "\n}";
		}
		catch (UnsupportedElementException e)
		{
			System.err.println(e.getMessage());
		}
		
		rules += rules_aux;
	}
	
	public String createSufficientRule(SituationType sit) throws UnsupportedElementException
	{
		String rules_aux = new String();
		
		rules_aux += "\tall w1";
		
		//Quantify worlds and participants
		//"all w1,w2(...): World | all part1: w1.Participant1, part2:w2.Participant2 (...) | "
		String partQuant = new String();
		int i=2,j=1;
		for (Participant part : smlparser.getInstances(sit, Participant.class))
		{
			String type = smlparser.getAlias(smlparser.getElementType(part));
			String world;
			
			if (smlparser.isTemporal(part))
			{
				world = "w"+i++;
				rules_aux += ","+world;
			}
			else
				world = "w1";
			
			if (!partQuant.isEmpty()) partQuant += ", ";
//			if (part.getIsImageOf() != null)
//				partQuant += "disj part"+j+",part"+(j+1)+": "+world+"."+type;
//			else
			partQuant += "part"+j+": "+world+"."+type;
			
			elementToWorld.put(part, world);
			elementToVar.put(part, "part"+j++);
		}
		
		rules_aux += ": World | all "+partQuant+" | ";
		
		rules_aux += handleMainSituationRule(sit);
		
		//Writes the implies Situation existence part
		//" => one s: w1.Situation | <situationConstraints>"
		//TODO se mesmo tipo e duas ou mais relações, tem que fazer o esquema do OR
		rules_aux += " => one s: w1."+smlparser.getAlias(sit)+" | ";
		elementToWorld.put(sit, "w1");
		elementToVar.put(sit, "s");
		
		Iterator<Participant> parts = smlparser.getInstances(sit, Participant.class).iterator();
		while (parts.hasNext())
		{
			Participant part = parts.next();
			rules_aux += handleNodeExpression(part)+" in "+handleNodeExpression(sit, part);
//			if (part.getReflection() != null)
//				rules_aux += " and "+handleNodeExpression(part.getReflection())+" in "+handleNodeExpression(sit, part);
			if (parts.hasNext())
				rules_aux += " and ";
		}
		
		return rules_aux;
	}
	
	public String createNecessaryRule(SituationType sit) throws UnsupportedElementException
	{
		String rules_aux = new String();
		String worldQuant = new String();
		int i=2;
		
		rules_aux += "\tall w1: World | ";
		rules_aux += "all s: w1."+smlparser.getAlias(sit)+" | ";
		
		for (Participant part : smlparser.getInstances(sit, Participant.class))
		{
//			if (part.getReflection() != null)
//				rules_aux += "all disj "+handleNodeExpression(part)+","+handleNodeExpression(part.getReflection());
//			else
			rules_aux += "all "+handleNodeExpression(part);
			rules_aux += ": "+handleNodeExpression(sit, part)+" | ";
			
			if (smlparser.isTemporal(part))
			{
				if (!worldQuant.isEmpty()) worldQuant += ",";
				worldQuant += "w"+i++;
			}
		}
		
		worldQuant = "some "+worldQuant+": World | ";
		rules_aux += (i != 2 ? worldQuant : "")+handleMainSituationRule(sit);
		
		return rules_aux;
	}
	
	/**
	 * Creates the situation's main constraints in the following order:
	 * 1) Disambiguates same type instances
	 * 2) Populates with the comparative relations
	 * 3) Relators have special treatment
	 * 4) If there is no rule, populates with "no none"
	 * @param sit the situation type
	 * @return the string that represents the constraint in alloy
	 * @throws UnsupportedElementException 
	 * @throws UnsupportedRelationException 
	 */
	protected String handleMainSituationRule(SituationType sit) throws UnsupportedElementException
	{
		String mainrule = new String();
		
		//Disambiguates same type instances
		Set<Participant> parts = smlparser.getInstances(sit, Participant.class);
		Set<Participant> parts_aux = new HashSet<Participant>();
		parts_aux.addAll(parts);
		for (Participant p1 : parts)
		{
			parts_aux.remove(p1);
			for (Participant p2 : parts_aux)
			{
				if (smlparser.isSameType(p1, p2))
				{
					if (!mainrule.isEmpty()) mainrule += " and ";
					mainrule += handleNodeExpression(p1)+" != "+handleNodeExpression(p2);
				}
			}
//			if (p1 instanceof SituationParticipant && p1.getTime().equals(TemporalKind.IS_PAST))
//			{
				//Se não houver nenhuma relação de Allen, então ela mets com w1
				//Se houver somente relação de Allen com situações passada então ela mets w1 se:
				//	for before e ela for target
				//	for after e ela for source
				//	for mets e ela for target
				//	for metby e ela for source
				//	for overlaps e ela for target
				//	for overlappedby e ela for source
				//	for finishes ou finishedby e ela for source/target (escolher somente 1 para não criar duas regras equivalentes)
				//	for includes e ela for source
				//	for during e ela for target
				//	for starts e ela for target
				//	for startedby e ela for source
				//	for coincides e ela for source/target (escolher somente 1 para não criar duas regras equivalentes)
				//Se houver uma relação de Allen com ums situação presente então ok
//			}
		}
		
		//Populates with comparative relations
		for (ComparativeRelation c_rel : smlparser.getInstances(sit, ComparativeRelation.class))
		{
			String c_relRule = new String();
			try
			{
				if (!mainrule.isEmpty()) c_relRule = " and ";
				c_relRule += handleComparativeRelation(c_rel);
			}
			catch (UnsupportedRelationException e)
			{
				c_relRule = " /* -Define relation manually- "+(!mainrule.isEmpty()?"and ":"")+e.getGeneratedCall()+"*/";
			}
			mainrule += c_relRule;
		}
		
		//Links relator's mediations to situation's participations
		for (MediationLink medlink : smlparser.getInstances(sit, MediationLink.class))
		{
			if (!mainrule.isEmpty()) mainrule += " and ";
			for (RefOntoUML.Property p : medlink.getType().getMemberEnd())
			{
				if (smlparser.isSameType(p.getType(), medlink.getEntity().getType()))
				{
					mainrule += handleNodeExpression(medlink.getRelator(), p)+" = "+
							handleNodeExpression(medlink.getEntity());
					break;
				}
			}
		}
		
		if (mainrule.isEmpty()) mainrule += "no none";
		
		return mainrule;
	}
	
	protected String handleComparativeRelation(ComparativeRelation c_rel) throws UnsupportedRelationException, UnsupportedElementException
	{
		//TODO tratar parametros
		String source = handleNodeExpression(c_rel.getSource());
		String target = handleNodeExpression(c_rel.getTarget());
		String sourceWorld = elementToWorld.get(c_rel.getSource());
		String targetWorld = elementToWorld.get(c_rel.getTarget());
		
		String negated = "";
		if (c_rel.isNegated()) negated = "not ";
		
		if (smlparser.getElementName(c_rel).equalsIgnoreCase("equals"))
			return negated+source+" = "+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("lessthan"))
			return negated+source+" < "+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("greaterthan"))
			return negated+source+" > "+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("instanceof"))
			return negated+source+" in "+sourceWorld+"."+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("before"))
			return negated+"before["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("after"))
			return negated+"after["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("meets"))
			return negated+"meets["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("metby"))
			return negated+"metby["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("overlaps"))
			return negated+"overlaps["+sourceWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("overlappedby"))
			return negated+"overlappedby["+sourceWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("finishes"))
			return negated+"finishes["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("finishedby"))
			return negated+"finishedby["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("includes"))
			return negated+"includes["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("during"))
			return negated+"during["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("starts"))
			return negated+"starts["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("startedby"))
			return negated+"startedby["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("coincides"))
			return negated+"coincides["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else
			throw new UnsupportedRelationException(smlparser.getElementName(c_rel), 
					negated+smlparser.getElementName(c_rel).replace(" ", "")+"["+source+","+target+"]");
	}
	
	protected String handleNodeExpression(EObject node) throws UnsupportedElementException
	{
		return handleNodeExpression(null, node);
	}

	protected String handleNodeExpression(EObject source, EObject node) throws UnsupportedElementException
	{
		if (node instanceof Literal)
			return smlparser.getAlias(node);
		
		else if (node instanceof Function)
		{
			String paramNamesList = new String();
			for (SituationTypeAssociation sitTypeAssoc : ((Function) node).sourceRelation())
			{
				if (sitTypeAssoc instanceof FunctionParameter)
				{
					if (!paramNamesList.isEmpty()) paramNamesList += ",";
					paramNamesList += handleNodeExpression(source, sitTypeAssoc.getTarget());
				}
			}
			System.err.println("ATTENTION: Relation/Function "+smlparser.getElementName(node)+
					" is not supported and must be defined manually.");
			return smlparser.getAlias(node)+"["+paramNamesList+"]";
		}
		
		if (source == null)
		{
			if (node instanceof AttributeReference)
			{
				AttributeReference att = (AttributeReference) node;
				Node attOwner = att.getEntity();
				if (attOwner instanceof ReferenceNode)
					return elementToVar.get(((ReferenceNode) attOwner).getSituation())+"."+handleNodeExpression(attOwner, att);
				
				else
					return handleNodeExpression(attOwner, att);
			}
			
			else if (node instanceof ModeReference)
			{
				ModeReference mode = (ModeReference) node;
				Node modeOwner = mode.getEntity();
				if (modeOwner instanceof ReferenceNode)
					return elementToVar.get(((ReferenceNode) modeOwner).getSituation())+"."+handleNodeExpression(modeOwner, mode);
				
				else
					return handleNodeExpression(modeOwner, mode);
			}
			
			else if (node instanceof ReferenceNode)
			{
				ReferenceNode refNode = (ReferenceNode) node;
				if (refNode.getReference() instanceof AttributeReference || refNode.getReference() instanceof ModeReference)
					return elementToVar.get(refNode.getSituation())+"."+
						handleNodeExpression(refNode, refNode.getReference());
				
				else
					return handleNodeExpression(refNode.getSituation(), refNode.getReference());
			}
		
			return elementToVar.get(node);
		}
		else
		{
			String leftOp;
			String rightOp;
			if (source instanceof ReferenceNode)
			{
				SituationParticipant sit_part = ((ReferenceNode) source).getSituation();
				ReferableElement ref_elem = ((ReferenceNode) source).getReference();
				leftOp = smlparser.getAlias(ref_elem)+(smlparser.isTemporal(ref_elem) ? "" : "["+elementToWorld.get(sit_part)+"]");
				if (node instanceof AttributeReference &&
						((RefOntoUML.Property)((AttributeReference)node).getType()).getAssociation() == null)
					rightOp = "("+elementToWorld.get(sit_part)+"."+smlparser.getAlias(((AttributeReference)node).getType())+")";
				else
					rightOp = smlparser.getAlias(node)+"["+elementToWorld.get(sit_part)+"]";
			}
			else
			{
				leftOp = elementToVar.get(source);
				if (node instanceof AttributeReference &&
						((RefOntoUML.Property)((AttributeReference)node).getType()).getAssociation() == null)
					rightOp = "("+elementToWorld.get(source)+"."+smlparser.getAlias(((AttributeReference)node).getType())+")";
				
				else if (smlparser.isTemporal((EObject) node))
					rightOp = smlparser.getAlias(node);
				
				else
					rightOp = smlparser.getAlias(node)+"["+elementToWorld.get(source)+"]";
			}
			
			return leftOp+"."+rightOp;
		}
	}
}
