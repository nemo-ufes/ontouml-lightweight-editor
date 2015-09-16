package br.ufes.inf.nemo.sml2alloy.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import sml2.EqualsLink;
import sml2.FunctionParameter;
import sml2.InstantiationLink;
import sml2.Participant;
import sml2.QualityLiteral;
import sml2.ReferenceNode;
import sml2.SMLModel;
import sml2.SituationParticipant;
import sml2.SituationType;
import sml2.TemporalKind;
import sml2.TypeLiteral;
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
		if (elem instanceof SMLModel)
			return ((SMLModel) elem).getContextModel();
		
		else
		{
			EStructuralFeature feat = elem.eClass().getEStructuralFeature("type");
			if (feat != null)
				return (EObject) elem.eGet(feat);
			
			else
				throw new UnsupportedElementException(elem.toString());
		}
	}
	
	public String getElementName(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof SMLModel)
			return ((SMLModel) elem).getContextModel().getName();
		
		else if (elem instanceof FunctionParameter)
			return ((FunctionParameter) elem).getLabel();
		
		else if (elem instanceof InstantiationLink)
			return "instanceOf";
		
		else if (elem instanceof QualityLiteral)
			return ((QualityLiteral) elem).getValue();
		
		else if (elem instanceof ReferenceNode)
			return ((ReferenceNode) elem).getLabel();
		
		else if (elem instanceof TypeLiteral)
			return ontoumlparser.getAlias(((TypeLiteral) elem).getType());
		
		else
		{
			EStructuralFeature feat = elem.eClass().getEStructuralFeature("name");
			if (feat != null)
				return (String) elem.eGet(feat);
			
			else
			{
				feat = elem.eClass().getEStructuralFeature("type");
				if (feat != null)
					return getElementName((EObject) elem.eGet(feat));
				
				else
					throw new UnsupportedElementException(elem.toString());
			}
		}
	}
	
	public String getAlias(EObject elem) throws UnsupportedElementException
	{
		if (elem instanceof RefOntoUML.Element)
			return ontoumlparser.getAlias((RefOntoUML.Element) elem);
		
		String alias = getElementName(elem).replace(" ", "");
		
		if (elem instanceof QualityLiteral)
		{
			if (((QualityLiteral) elem).getType().getName().equalsIgnoreCase("string") ||
				((QualityLiteral) elem).getType().getName().equalsIgnoreCase("str"))
				return "\""+alias+"\"";
			
			else
				return alias;
		}
		
		else if (elem instanceof TypeLiteral)
			return ontoumlparser.getAlias(((TypeLiteral) elem).getType());
			
		else if (elem instanceof Participant)
		{
			if (((Participant) elem).getIsImageOf() != null)
				return handleAlias(aliases, alias.toLowerCase(), ((Participant) elem).getIsImageOf());
			
			else
				return handleAlias(aliases, alias.toLowerCase(), elem);
		}
		
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
			EObject p1_type = (EObject) p1.eGet(p1.eClass().getEStructuralFeature("type"));
			Participant p2 = (Participant) eobj2;
			EObject p2_type = (EObject) p2.eGet(p2.eClass().getEStructuralFeature("type"));
			
			for (EqualsLink eqlink : getInstances(p1.getSituation(), EqualsLink.class))
			{
				if (((eqlink.getSource().equals(p1) && eqlink.getTarget().equals(p2)) || 
						(eqlink.getSource().equals(p2) && eqlink.getTarget().equals(p1))))
					return false;
			}
			
			if (p1 instanceof SituationParticipant || p2 instanceof SituationParticipant)
				return p1_type.equals(p2_type);
			
			else
				return isSameType(p1_type, p2_type);
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
