package br.ufes.inf.nemo.sml2alloy.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import sml2.AttributeReference;
import sml2.ComparativeRelation;
import sml2.EntityParticipant;
import sml2.ExportableNode;
import sml2.Function;
import sml2.Link;
import sml2.Literal;
import sml2.Parameter;
import sml2.Participant;
import sml2.RelatorParticipant;
import sml2.SMLModel;
import sml2.SituationParameterReference;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypeElement;
import sml2.SituationTypeParameter;
import sml2.Sml2Factory;
import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.sml2alloy.exception.UnsupportedElementException;

public class SMLParser
{
	SMLModel smlmodel;
	OntoUMLParser ontoumlparser;
	
	Map<EObject, Map<String, EObject>> participationsAlias = new HashMap<EObject, Map<String, EObject>>();
	
	Map<String, EObject> aliases = new HashMap<String, EObject>();
	
	public SMLParser(SMLModel smlmodel, OntoUMLParser ontoumlparser)
	{
		this.smlmodel = smlmodel;
		addMissingTypeParameters();
		this.ontoumlparser = ontoumlparser;
	}
	
	protected void addMissingTypeParameters()
	{
		Sml2Factory factory = Sml2Factory.eINSTANCE;
		
		for (SituationType sit : getSituationTypes())
		{
			for (ExportableNode expnode : getInstances(sit, ExportableNode.class))
			{
				if (expnode.getNodeParameter() == null)
				{
					try
					{
						SituationTypeParameter param = factory.createSituationTypeParameter();
						param.setNodeReference(expnode);
						expnode.setNodeParameter(param);
						param.setName(getElementName(expnode));
						sit.getParameter().add(param);
					}
					catch (UnsupportedElementException e)
					{
						System.err.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public List<SituationType> getSituationTypes()
	{
		return smlmodel.getElements();
	}
	
	@SuppressWarnings("unchecked")
	public <T> Set<T> getInstances(SituationType sit, java.lang.Class<T> type)
	{
		Set<T> result = new HashSet<T>();		
		for (EObject o : sit.getElements()) 
		{
			if(type.isInstance(o)) result.add((T) o);
		}
		return result;
	}
	
	public int getTotalWorlds(SituationType sit)
	{
		int total = 1;
		
		for (SituationTypeElement sitelem : sit.getElements())
		{
			if (sitelem instanceof SituationParticipant && ((SituationParticipant)sitelem).isIsPast()) total++;
		}
		
		return total;
	}
	
	public EObject getOntoUMLCounterpart(Participant part)
	{
		if (part instanceof EntityParticipant)
			return ((EntityParticipant) part).getIsOfType();
		
		else if (part instanceof RelatorParticipant)
			return ((RelatorParticipant) part).getIsOfType();
		
		else
			return part;
	}
	
	public String getElementName(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof AttributeReference)
			return ((AttributeReference) elem).getAttribute().getName();
		
		else if (elem instanceof ComparativeRelation)
			return ((ComparativeRelation) elem).getRelation().getName();
		
		else if (elem instanceof EntityParticipant)
			return ((EntityParticipant) elem).getIsOfType().getName();
		
		else if (elem instanceof Function)
			return ((Function) elem).getFunction().getName();
		
		else if (elem instanceof Link)
			return ((Link) elem).getIsOfType().getName();
		
		else if (elem instanceof Literal)
			return ((Literal) elem).getValue();
		
		else if (elem instanceof Parameter)
			return getElementName(((Parameter) elem).getValue());
		
		else if (elem instanceof RelatorParticipant)
			return ((RelatorParticipant) elem).getIsOfType().getName();
		
		else if (elem instanceof SituationParameterReference)
			return ((SituationParameterReference) elem).getParameter().getName();
		
		else if (elem instanceof SituationParticipant)
			return ((SituationParticipant) elem).getSituationType().getName();
		
		else if (elem instanceof SituationType)
			return ((SituationType) elem).getName();
		
		else if (elem instanceof SituationTypeParameter)
			return ((SituationTypeParameter) elem).getName();
		
		else if (elem instanceof SMLModel)
			return ((SMLModel) elem).getContextModel().getName();
		
		else
			throw new UnsupportedElementException(elem.toString());
	}
	
	public String getAlias(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof RefOntoUML.Element)
			return ontoumlparser.getAlias(((RefOntoUML.Element) elem));
		
		String alias = getElementName(elem).replace(" ", "");
		
		if (elem instanceof Literal)
			if (((Literal) elem).getDataType().getName().equalsIgnoreCase("string") ||
				((Literal) elem).getDataType().getName().equalsIgnoreCase("str"))
				return "\""+alias+"\"";
			else
				return alias;
		
		else if (elem instanceof SituationParticipant)
			return handleAlias(aliases, alias, ((SituationParticipant) elem).getSituationType());
			
		else if (elem instanceof SituationParameterReference)
			return handleAlias(aliases, alias.toLowerCase(), ((SituationParameterReference) elem).getParameter());
		
		else if (elem instanceof SituationTypeParameter)
			return handleAlias(aliases, alias.toLowerCase(), elem);
		
		return handleAlias(aliases, alias, elem);
	}
	
	public String getParticipationAlias(SituationType sit, Participant part) throws UnsupportedElementException
	{
		String alias = "P_"+getAlias(sit)+"_"+getAlias(getOntoUMLCounterpart(part));
		
		Map<String, EObject> map = participationsAlias.get(sit);
		if (map == null)
		{
			map = new HashMap<String, EObject>();
			participationsAlias.put(sit, map);
		}
		
		return handleAlias(map, alias, part);
	}
	
	protected String handleAlias(Map<String, EObject> map, String alias, EObject elem)
	{
		int cont = 1;
		String alias_aux = alias;
		while (map.get(alias_aux) != null)
		{
			if (map.get(alias_aux).equals(elem))
				return alias_aux;
			else
				alias_aux = alias+cont++;
		}
		alias = alias_aux;
		
		map.put(alias, elem);
		return alias;
	}
	
	public boolean isSameType(EObject t1, EObject t2)
	{
		ArrayList<RefOntoUML.Classifier> typesList = new ArrayList<RefOntoUML.Classifier>();
		if (t1 instanceof EntityParticipant && t2 instanceof EntityParticipant)
		{
			typesList.add(((EntityParticipant)t1).getIsOfType());
			typesList.add(((EntityParticipant)t2).getIsOfType());
		}
		else if (t1 instanceof RelatorParticipant && t2 instanceof RelatorParticipant)
		{
			typesList.add(((RelatorParticipant)t1).getIsOfType());
			typesList.add(((RelatorParticipant)t2).getIsOfType());
		}
		else if (t1 instanceof SituationParticipant && t2 instanceof SituationParticipant)
		{
			return ((SituationParticipant)t1).getSituationType().equals(((SituationParticipant)t2).getSituationType());
		}
		else if (t1 instanceof RefOntoUML.Classifier && t2 instanceof RefOntoUML.Classifier)
		{
			typesList.add((Classifier) t1);
			typesList.add((Classifier) t2);
		}
		
		ArrayList<RefOntoUML.GeneralizationSet> gensets = new ArrayList<RefOntoUML.GeneralizationSet>();
		try
		{
			if (ontoumlparser.getCommonSupertypes(typesList).size()==0)
				return false;
						
			else return ontoumlparser.allTypesOverlap(typesList, gensets);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}
}
