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
import sml2.Link;
import sml2.Literal;
import sml2.Parameter;
import sml2.Participant;
import sml2.SituationParameterReference;
import sml2.SituationParticipant;
import sml2.SituationType;
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
	
	public void createCommonRules()
	{
		rules += "\nfact situationCommon {";
		
		for (SituationType sit : smlparser.getSituationTypes())
		{
			String partNamesList = new String();
			Iterator<Participant> partList = smlparser.getInstances(sit, Participant.class).iterator();
			try
			{
				while (partList.hasNext())
				{
					Participant part = partList.next();
					partNamesList += smlparser.getParticipationAlias(sit, part);
					if (partList.hasNext())
						partNamesList += "+";
				}
				rules += "\n\tsituationCont["+smlparser.getAlias(sit)+", "+partNamesList+"]\n";
				rules += "\tsituationUniq["+smlparser.getAlias(sit)+", "+partNamesList+"]\n";
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
		
		rules_aux += "\tall ";
		
		int i,j;
		for (i=1; i <= smlparser.getTotalWorlds(sit); i++)
		{
			if (i!=1) rules_aux += ",";
			rules_aux += "w"+i;
		}
		
		rules_aux += ": World | ";
		
		i=2;
		j=1;
		for (Participant p : smlparser.getInstances(sit, Participant.class))
		{
			String type = smlparser.getAlias(smlparser.getOntoUMLCounterpart(p));
			String world;
			
			//TODO w1 é sempre o atual. Mudar se isPast virar uma propriedade de qualquer elemento
			if (p instanceof SituationParticipant && ((SituationParticipant)p).isIsPast())
				world = "w"+i++;
			
			else
				world = "w1";
			
			rules_aux += "all part"+j+": "+world+"."+type+" | ";
			elementToWorld.put(p, world);
			elementToVar.put(p, "part"+j++);
		}
		
		rules_aux += handleMainSituationRule(sit);
		
		rules_aux += " => one s: w1."+smlparser.getAlias(sit)+" | ";
		elementToWorld.put(sit, "w1");
		elementToVar.put(sit, "s");
		
		Iterator<Participant> parts = smlparser.getInstances(sit, Participant.class).iterator();
		while (parts.hasNext())
		{
			Participant p = parts.next();
			rules_aux += handleNodeExpression(sit, p.getNodeParameter())+" = "+handleNodeExpression(p);
			if (parts.hasNext())
				rules_aux += " and ";
		}
		
		return rules_aux;
	}
	
	public String createNecessaryRule(SituationType sit) throws UnsupportedElementException
	{
		String rules_aux = new String();
		
		rules_aux += "\tall w1: World | ";
		rules_aux += "all s: w1."+smlparser.getAlias(sit)+" | ";
		
		for (Participant p : smlparser.getInstances(sit, Participant.class))
		{
			rules_aux += "all "+elementToVar.get(p)+": "+handleNodeExpression(sit, p.getNodeParameter())+" | ";
		}
		
		if (smlparser.getTotalWorlds(sit) > 1)
		{
			rules_aux += "some ";
			int i;
			for (i=2; i <= smlparser.getTotalWorlds(sit); i++)
			{
				if (i!=2) rules_aux += ",";
				rules_aux += "w"+i;
			}
			
			rules_aux += ": World | ";
		}
		
		rules_aux += handleMainSituationRule(sit);
		
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
		}
		
		//Propulates with comparative relations
		for (ComparativeRelation c_rel : smlparser.getInstances(sit, ComparativeRelation.class))
		{
			try
			{
				if (!mainrule.isEmpty()) mainrule += " and ";
				mainrule += handleComparativeRelation(c_rel);
			}
			catch (UnsupportedRelationException exc)
			{
				System.err.println(exc.getMessage());
			}
		}
		
		//Links relator's mediations to situation's participations
		for (Link l1 : smlparser.getInstances(sit, Link.class))
		{
			if (!mainrule.isEmpty()) mainrule += " and ";
			for (RefOntoUML.Property p : l1.getIsOfType().getMemberEnd())
			{
				if (smlparser.isSameType(p.getType(), l1.getEntity().getIsOfType()))
				{
					mainrule += handleNodeExpression(l1.getRelator(), p)+" = "+
							handleNodeExpression(l1.getEntity());
					break;
				}
			}
		}
		
		if (mainrule.isEmpty()) mainrule += "no none";
		
		return mainrule;
	}
	
	//TODO Incluir parâmetros
	//TODO e para relações que não são padrões? Tipo near, within the past...
	protected String handleComparativeRelation(ComparativeRelation c_rel) throws UnsupportedRelationException, UnsupportedElementException
	{
		String source = handleNodeExpression(c_rel.getSource());
		String target = handleNodeExpression(c_rel.getTarget());
		String sourceWorld = elementToWorld.get(c_rel.getSource());
		String targetWorld = elementToWorld.get(c_rel.getTarget());
		
		if (smlparser.getElementName(c_rel).equalsIgnoreCase("equals"))
			return source+" = "+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("less than"))
			return source+" < "+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("greater than"))
			return source+" > "+target;
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("before"))
			return "before["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("after"))
			return "after["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("mets"))
			return "mets["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("met by"))
			return "metby["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("overlaps"))
			return "overlaps["+sourceWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("overlapped by"))
			return "overlappedby["+sourceWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("finishes"))
			return "finishes["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("finished by"))
			return "finishedby["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("includes"))
			return "includes["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("during"))
			return "during["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("starts"))
			return "starts["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("started by"))
			return "startedby["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else if (smlparser.getElementName(c_rel).equalsIgnoreCase("coincides"))
			return "coincides["+sourceWorld+","+targetWorld+","+source+","+target+",exists]";
		
		else
			throw new UnsupportedRelationException(smlparser.getElementName(c_rel));
	}
	
	protected String handleNodeExpression(EObject node) throws UnsupportedElementException
	{
		return handleNodeExpression(null, node);
	}
	//TODO RelaçÕes transtemporais não possuem parâmetro mundo
	//TODO Na hora de quantificar um participante passado, ele deveria aparecer depois como some
	protected String handleNodeExpression(EObject source, EObject node) throws UnsupportedElementException
	{
		if (node instanceof Literal)
			return smlparser.getAlias(node);
		
		else if (node instanceof Function)
		{
			String paramNamesList = new String();
			Iterator<Parameter> paramList = ((Function) node).getParameter().iterator();
			while (paramList.hasNext())
			{
				Parameter param = paramList.next();
				paramNamesList += handleNodeExpression(source, param.getValue());
				if (paramList.hasNext()) paramNamesList += ",";
			}
			//TODO nome tem que ser mesmo da função, pode ser definido no modelo ontouml ou sml
			return smlparser.getAlias(node)+"["+paramNamesList+"]";
		}
		
		if (source == null)
		{
			if (node instanceof AttributeReference)
				return handleNodeExpression(((AttributeReference) node).getEntity(), ((AttributeReference) node).getAttribute());
				
			else if (node instanceof SituationParameterReference)
				return handleNodeExpression(((SituationParameterReference)node).getSituation(), ((SituationParameterReference) node).getParameter().getNodeReference());
		
			return elementToVar.get(node);
		}
		else
		{
			if (node instanceof AttributeReference)
			{
				for (SituationParameterReference sit_param : ((SituationParticipant)source).getParameter())
				{
					if (sit_param.getParameter().getNodeReference().equals(((AttributeReference)node).getEntity()))
						return elementToVar.get(source)+"."+handleNodeExpression(sit_param, ((AttributeReference)node).getAttribute());
				}
				System.err.println("Reference to element "+smlparser.getElementName(((AttributeReference)node).getEntity())+"not found");
			}
			else if (node instanceof Participant)
				return handleNodeExpression(source, ((Participant) node).getNodeParameter());
			
			else if (source instanceof SituationParameterReference)
			{
				SituationParticipant sit_part = ((SituationParameterReference) source).getSituation();
				return smlparser.getAlias(source)+"["+elementToWorld.get(sit_part)+"]"+
					smlparser.getAlias(node)+"["+elementToWorld.get(sit_part)+"]";
			}
			
			return elementToVar.get(source)+"."+smlparser.getAlias(node)+"["+elementToWorld.get(source)+"]";
		}
		
//		else if (source == null)
//		{
//			if (node instanceof Function)
//			{
//				String paramNamesList = new String();
//				Iterator<Parameter> paramList = ((Function) node).getParameter().iterator();
//				while (paramList.hasNext())
//				{
//					Parameter param = paramList.next();
//					paramNamesList += handleNodeExpression(param.getValue());
//					if (paramList.hasNext()) paramNamesList += ",";
//				}
//				return smlparser.getAlias(((Function) node).getFunction())+"["+paramNamesList+"]";
//			}
//			
//			else if (node instanceof SituationParameterReference)
//			{
//				ExportableNode node_ref = ((SituationParameterReference)node).getParameter().getNodeReference();
//				source = ((SituationParameterReference)node).getSituation();
//				if (node_ref instanceof AttributeReference)
//					return elementToVar.get(source)+"."+
//						smlparser.getAlias(((AttributeReference)node_ref).getEntity().getNodeParameter())+"["+elementToWorld.get(source)+"]"+"."+
//						smlparser.getAlias(((AttributeReference)node_ref).getAttribute())+"["+elementToWorld.get(source)+"]";
//				
//				else if (node_ref instanceof Function)
//				{
//					String paramNamesList = new String();
//					Iterator<Parameter> paramList = ((Function) node_ref).getParameter().iterator();
//					while (paramList.hasNext())
//					{
//						Parameter param = paramList.next();
//						paramNamesList += handleNodeExpression(source, param.getValue());
//						if (paramList.hasNext()) paramNamesList += ",";
//					}
//					return smlparser.getAlias(((Function) node_ref).getFunction())+"["+paramNamesList+"]";
//				}
//			}
//			
//			else
//				return elementToVar.get(node);
//		}
//		else if (node instanceof ExportableNode)
//			node = ((ExportableNode)node).getNodeParameter();
//		
//		return elementToVar.get(source)+"."+smlparser.getAlias(node)+"["+elementToWorld.get(source)+"]";
	}
}
