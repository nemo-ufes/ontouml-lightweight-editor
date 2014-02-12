package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Association;
import RefOntoUML.Derivation;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public class XMI2RefAssociation extends XMI2RefClassifier
{
	protected static boolean autoGenerateNames = false;
	
	public XMI2RefAssociation (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = solveStereotype(Mapper.getStereotype(this.XMIElement));
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	private Association solveStereotype(String stereotype) throws Exception
	{
		if (stereotype.equalsIgnoreCase("characterization"))
			return factory.createCharacterization();
			
		else if (stereotype.equalsIgnoreCase("componentof"))
			return factory.createcomponentOf();
			
		else if (stereotype.equalsIgnoreCase("formal") || stereotype.equalsIgnoreCase("formalassociation"))
			return factory.createFormalAssociation();
			
		else if (stereotype.equalsIgnoreCase("derivation"))
			return factory.createDerivation();
			
		else if (stereotype.equalsIgnoreCase("material") || stereotype.equalsIgnoreCase("materialassociation"))
			return factory.createMaterialAssociation();
			
		else if (stereotype.equalsIgnoreCase("mediation"))
			return factory.createMediation();
			
		else if (stereotype.equalsIgnoreCase("memberof"))
			return factory.creatememberOf();
			
		else if (stereotype.equalsIgnoreCase("subcollectionof"))
			return factory.createsubCollectionOf();
			
		else if (stereotype.equalsIgnoreCase("subquantityof"))
			return factory.createsubQuantityOf();
			
		else if (unknownStereotypeOpt == 0)
    		return factory.createAssociation();
		
    	else if (unknownStereotypeOpt == 1)
    		return null;
		
    	else
    	{//TODO colocar isso no ontoUML error e também gerar warning
    		String error;
    		
    		if (stereotype == null || stereotype == "")
    			error = "Stereotype undefined for class "+Mapper.getName(XMIElement);
    		
    		else
    			error = "Unknown Stereotype '"+stereotype+"' found in class "+Mapper.getName(XMIElement);
    		
    		throw new Exception(error);
    	}
	}
	
	private void createDerivation() throws Exception
	{
		String relatorID = Mapper.getRelatorfromMaterial(XMIElement);
		
    	if (relatorID != null && relatorID != "")
    	{
    		Derivation der = (Derivation) solveStereotype("derivation");
    		Relator relator = (Relator) elemMap.get(relatorID);
        	
        	Property prop1 = factory.createProperty();
        	prop1.setType((MaterialAssociation) RefOntoUMLElement);
        	LiteralUnlimitedNatural upper1 = factory.createLiteralUnlimitedNatural();
			upper1.setValue(1);
			prop1.setUpperValue(upper1);
			
			LiteralInteger lower1 = factory.createLiteralInteger();
			lower1.setValue(1);
			prop1.setLowerValue(lower1);
        	
        	Property prop2 = factory.createProperty();
        	prop2.setType((Relator) relator);
        	LiteralUnlimitedNatural upper2 = factory.createLiteralUnlimitedNatural();
			upper2.setValue(1);
			prop2.setUpperValue(upper2);
			
			LiteralInteger lower2 = factory.createLiteralInteger();
			lower2.setValue(1);
			prop2.setLowerValue(lower2);
        	
        	der.getOwnedEnd().add(prop1);
        	der.getMemberEnd().add(prop1);
        	der.getOwnedEnd().add(prop2);
        	der.getMemberEnd().add(prop2);
        	
        	if (autoGenerateNames)
        		der.setName("A_"+prop1.getType().getName().replace(" ", "")+"_"+prop2.getType().getName().replace(" ", ""));
        	
        	((Package)((MaterialAssociation)RefOntoUMLElement).eContainer()).getPackagedElement().add(der);
    	}
	}
	
	@Override
	protected void deal()
	{
		super.deal();
		
		Association association = (Association) RefOntoUMLElement;
		try
		{
			// Specific properties of Meronymic associations.
			if(association instanceof subQuantityOf)
				((subQuantityOf) association).setIsEssential(true);
			
			else
				((Meronymic)association).setIsEssential(Boolean.parseBoolean((String)hashProp.get("isessential")));
			
    		((Meronymic)association).setIsImmutablePart(Boolean.parseBoolean((String)hashProp.get("isimmutablepart")));
    		((Meronymic)association).setIsImmutableWhole(Boolean.parseBoolean((String)hashProp.get("isimmutablewhole")));
    		((Meronymic)association).setIsInseparable(Boolean.parseBoolean((String)hashProp.get("isinseparable")));
    		
    		if (hashProp.containsKey("isshareable"))
    			((Meronymic)association).setIsShareable(Boolean.parseBoolean((String)hashProp.get("isshareable")));
    		
    	} catch (ClassCastException e) {
    		// Association is not a Meronymic. Do nothing.
    	}
		
		//Material Association is always derived
		if(association instanceof MaterialAssociation)
			association.setIsDerived(true);
		
		else
			association.setIsDerived(Boolean.parseBoolean((String)hashProp.get("isderived")));
	}
	
	@Override
	public void dealReferences() throws Exception
	{
		try
		{
	    	for (Object memberEnd : (List<?>)hashProp.get("memberend"))
	    	{
	    		((Association)RefOntoUMLElement).getMemberEnd().add((Property)elemMap.get((String)memberEnd));
			}
	    	
	    	Association assoc = (Association) RefOntoUMLElement;
	    	if (autoGenerateNames && (assoc.getName() == null || assoc.getName().equals("")) &&
	    			assoc.getMemberEnd().get(0) != null && assoc.getMemberEnd().get(1) != null)
	    	{
	    		generateAutoName();
	    	}
		}
		catch (NullPointerException | IllegalArgumentException e)
		{
			if (!ignoreErrorElements)
				throw e;
		}
		finally
		{
			if (((Association)RefOntoUMLElement).getMemberEnd().size() < 2 && ignoreErrorElements)
	    	{
				System.err.println("Debug: removing association with error ("+((Association)RefOntoUMLElement).getName()+")");
	    		EcoreUtil.remove(RefOntoUMLElement);
//	    		elemMap.remove(Mapper.getID(XMIElement));
	    	}
		}
    	
    	if (RefOntoUMLElement instanceof MaterialAssociation)
    		createDerivation();
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		for (Object prop : this.Mapper.getElements(XMIElement, ElementType.PROPERTY))
		{
			XMI2RefProperty xmi2refprop = new XMI2RefProperty(prop, Mapper);
			if (xmi2refprop.RefOntoUMLElement != null)
			{
	//			listProperties.add(xmi2refprop);		
				((Association)RefOntoUMLElement).getOwnedEnd().add((Property)xmi2refprop.getRefOntoUMLElement());
				((Property)xmi2refprop.getRefOntoUMLElement()).setAssociation((Association)RefOntoUMLElement);
			}
		}
		
		super.createSubElements();
	}
	
	private void generateAutoName() throws Exception
	{
		Association assoc = (Association) RefOntoUMLElement;
		
		//Treats the cases where the type of the property(ies) is(are) still not set
		if (assoc.getMemberEnd().get(0).getType() == null)
		{
			for (XMI2RefElement xmi2refelement : elemMap.values())
			{
				if (xmi2refelement.RefOntoUMLElement.equals(assoc.getMemberEnd().get(0)))
					xmi2refelement.dealReferences();
			}
		}
		if (assoc.getMemberEnd().get(1).getType() == null)
		{
			for (XMI2RefElement xmi2refelement : elemMap.values())
			{
				if (xmi2refelement.RefOntoUMLElement.equals(assoc.getMemberEnd().get(1)))
					xmi2refelement.dealReferences();
			}
		}
		
		String source = (assoc.getMemberEnd().get(0).getType().getName() != null ? assoc.getMemberEnd().get(0).getType().getName() : "");
    	String target = (assoc.getMemberEnd().get(1).getType().getName() != null ? assoc.getMemberEnd().get(1).getType().getName() : "");
		
    	assoc.setName("A_"+source.replace(" ", "")+"_"+target.replace(" ", ""));
	}

	public static boolean isAutoGenerateNames() {
		return autoGenerateNames;
	}

	public static void setAutoGenerateNames(boolean autoGenerateNames) {
		XMI2RefAssociation.autoGenerateNames = autoGenerateNames;
	}
}
