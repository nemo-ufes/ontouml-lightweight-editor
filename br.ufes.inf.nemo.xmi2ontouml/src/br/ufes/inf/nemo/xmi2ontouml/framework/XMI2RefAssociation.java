package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.List;

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
	public XMI2RefAssociation (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = solveStereotype(Mapper.getStereotype(this.XMIElement));
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	private Association solveStereotype(String stereotype)
	{
		Association newassoc = null;
		
		if (stereotype.equalsIgnoreCase("characterization")) {
			newassoc = factory.createCharacterization();
    	}
		else if (stereotype.equalsIgnoreCase("componentof")) {
    		newassoc = factory.createcomponentOf();
    	}
		else if (stereotype.equalsIgnoreCase("formal") || stereotype.equalsIgnoreCase("formalassociation")) {
    		newassoc = factory.createFormalAssociation();
    	}
		else if (stereotype.equalsIgnoreCase("derivation")) {
    		newassoc = factory.createDerivation();
    	}
		else if (stereotype.equalsIgnoreCase("material") || stereotype.equalsIgnoreCase("materialassociation")) {
    		newassoc = factory.createMaterialAssociation();
    	}
		else if (stereotype.equalsIgnoreCase("mediation")) {
    		newassoc = factory.createMediation();
    	}
		else if (stereotype.equalsIgnoreCase("memberof")) {
    		newassoc = factory.creatememberOf();
    	}
		else if (stereotype.equalsIgnoreCase("subcollectionof")) {
    		newassoc = factory.createsubCollectionOf();
    	}
		else if (stereotype.equalsIgnoreCase("subquantityof")) {
    		newassoc = factory.createsubQuantityOf();
    	}
		else
		{
			newassoc = factory.createAssociation();
		}
		
		return newassoc;
	}
	
	private void createDerivation()
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
	public void dealReferences()
	{
    	for (Object memberEnd : (List<?>)hashProp.get("memberend"))
    	{
    		((Association)RefOntoUMLElement).getMemberEnd().add((Property)elemMap.get((String)memberEnd));
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
//			listProperties.add(xmi2refprop);		
			((Association)RefOntoUMLElement).getOwnedEnd().add((Property)xmi2refprop.getRefOntoUMLElement());
			((Property)xmi2refprop.getRefOntoUMLElement()).setAssociation((Association)RefOntoUMLElement);
		}
		
		super.createSubElements();
	}
}
