package br.ufes.inf.nemo.sml2alloy.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import sml2.AllenRelation;
import sml2.ComparativeRelation;
import sml2.Function;
import sml2.FunctionParameter;
import sml2.Instantiation;
import sml2.Literal;
import sml2.Participant;
import sml2.PrimitiveComparativeRelation;
import sml2.ReferenceNode;
import sml2.ReflectedParticipant;
import sml2.SMLModel;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.SituationTypedElement;
import sml2.TemporalKind;
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
		this.ontoumlparser = ontoumlparser;
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
	
	public EObject getElementType(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof SituationTypedElement)
			return ((SituationTypedElement) elem).getType();
		
		else if (elem instanceof Literal)
			return ((Literal) elem).getType();
		
		else if (elem instanceof SMLModel)
			return ((SMLModel) elem).getContextModel();
		
		//ReferenceNode, FunctionParameter, SituationType, ReflectedParticipant, Function
		else
			throw new UnsupportedElementException(elem.toString());
	}
	
	public String getElementName(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof Literal)
			return ((Literal) elem).getType() instanceof RefOntoUML.Class ? 
					ontoumlparser.getAlias(((Literal) elem).getType()) : ((Literal) elem).getValue();
					
		else if (elem instanceof SituationTypedElement)
		{
			EStructuralFeature feat = ((SituationTypedElement) elem).getType().eClass().getEStructuralFeature("name");
			return (String) ((SituationTypedElement) elem).getType().eGet(feat);
		}
		
		else if (elem instanceof Function)
			return ((Function) elem).getName();
		
		else if (elem instanceof FunctionParameter)
			return ((FunctionParameter) elem).getLabel();
		
		else if (elem instanceof PrimitiveComparativeRelation)
			return ((PrimitiveComparativeRelation) elem).getType().getName();
		
		else if (elem instanceof AllenRelation)
			return ((AllenRelation) elem).getType().getName();
		
		else if (elem instanceof Instantiation)
			return "instanceOf";
			
		else if (elem instanceof ReferenceNode)
			return ((ReferenceNode) elem).getLabel();
		
		else if (elem instanceof ReflectedParticipant)
			return getElementName(((ReflectedParticipant) elem).getParticipant());
		
		else if (elem instanceof SituationType)
			return ((SituationType) elem).getName();
		
		else if (elem instanceof SMLModel)
			return ((SMLModel) elem).getContextModel().getName();
		
		else
			throw new UnsupportedElementException(elem.toString());
	}
	
	public String getAlias(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof RefOntoUML.Element)
			return ontoumlparser.getAlias((RefOntoUML.Element) elem);
		
		String alias = getElementName(elem).replace(" ", "");
		
		if (elem instanceof Literal)
			if (((Literal) elem).getType().getName().equalsIgnoreCase("string") ||
				((Literal) elem).getType().getName().equalsIgnoreCase("str"))
				return "\""+alias+"\"";
			else if (((Literal) elem).getType() instanceof RefOntoUML.Class)
				return ontoumlparser.getAlias(((Literal) elem).getType());
			else
				return alias;
			
		else if (elem instanceof Participant)
			return handleAlias(aliases, alias.toLowerCase(), elem);
		
		else if (elem instanceof ReflectedParticipant)
			return handleAlias(aliases, alias.toLowerCase(), ((ReflectedParticipant) elem).getParticipant());
		
		return handleAlias(aliases, alias, elem);
	}
	
	public String getParticipationAlias(SituationType sit, Participant part) throws UnsupportedElementException
	{
		String alias = "P_"+getAlias(sit)+"_"+getAlias(getElementType(part));
		
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
	
	public boolean isSameType(EObject eobj1, EObject eobj2) throws UnsupportedElementException
	{
		if (eobj1 instanceof Participant && eobj2 instanceof Participant)
		{
			Participant p1 = (Participant) eobj1;
			Participant p2 = (Participant) eobj2;
			for (ComparativeRelation comprel : getInstances(p1.getSituation(), PrimitiveComparativeRelation.class))
			{
				if (((comprel.getSource().equals(p1) && comprel.getTarget().equals(p2)) ||
					(comprel.getSource().equals(p2) && comprel.getTarget().equals(p1)))
					&& getElementName(comprel).equals("equals"))
					return false;
			}
			
			if (p1 instanceof SituationParticipant || p2 instanceof SituationParticipant)
			{
				return p1.getType().equals(p2.getType());
			}
			else
			{
				eobj1 = p1.getType();
				eobj2 = p2.getType();
			}
		}
		
		ArrayList<RefOntoUML.Classifier> typesList = new ArrayList<RefOntoUML.Classifier>();
		typesList.add((RefOntoUML.Classifier) eobj1);
		typesList.add((RefOntoUML.Classifier) eobj2);
		try
		{
			if (eobj1.equals(eobj2))
				return true;
			else if (ontoumlparser.getCommonSupertypes(typesList).size()==0)
				return false;
						
			else return ontoumlparser.allTypesOverlap(typesList, new ArrayList<RefOntoUML.GeneralizationSet>());
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean isTemporal(EObject p)
	{
		EStructuralFeature feat = p.eClass().getEStructuralFeature("temporality");
		return feat != null && (p.eGet(feat).equals(TemporalKind.PAST) || p.eGet(feat).equals(TemporalKind.ANY));
	}
}
