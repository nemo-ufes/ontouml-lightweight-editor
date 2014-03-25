package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Class;
import RefOntoUML.Collective;
import RefOntoUML.Property;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefClass extends XMI2RefClassifier
{
	public XMI2RefClass (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper);
		
		this.RefOntoUMLElement = solveStereotype(mapper.getStereotype(XMIElement));
		deal();
	}
	
	private Class solveStereotype(String stereotype) throws Exception
	{		
		if (stereotype.equalsIgnoreCase("kind"))
    		return factory.createKind();
		
		else if (stereotype.equalsIgnoreCase("subkind"))
    		return factory.createSubKind();
		
    	else if (stereotype.equalsIgnoreCase("role"))
    		return factory.createRole();
		
    	else if (stereotype.equalsIgnoreCase("phase"))
    		return factory.createPhase();
		
    	else if (stereotype.equalsIgnoreCase("category"))
    		return factory.createCategory();
		
    	else if (stereotype.equalsIgnoreCase("collective"))
    		return factory.createCollective();
		
    	else if (stereotype.equalsIgnoreCase("mixin"))
    		return factory.createMixin();
		
    	else if (stereotype.equalsIgnoreCase("mode"))
    		return factory.createMode();
		
    	else if (stereotype.equalsIgnoreCase("quantity"))
    		return factory.createQuantity();
		
    	else if (stereotype.equalsIgnoreCase("relator"))
    		return factory.createRelator();
		
    	else if (stereotype.equalsIgnoreCase("rolemixin"))
    		return factory.createRoleMixin();
		
    	else if (unknownStereotypeOpt == 0)
    		return factory.createClass();
		
    	else if (unknownStereotypeOpt == 1)
    		return null;
		
    	else
    	{//TODO colocar isso no ontoUML error e também gerar warning
    		String error;
    		
    		if (stereotype == null || stereotype == "")
    			error = "Stereotype undefined for class "+hashProp.get("name");
    		
    		else
    			error = "Unknown Stereotype '"+stereotype+"' found in class "+hashProp.get("name");
    		
    		throw new Exception(error);
    	}
	}

	@Override
	protected void deal()
	{
		super.deal();
		
		if (RefOntoUMLElement instanceof Collective)
		{
			((Collective)RefOntoUMLElement).setIsExtensional(Boolean.parseBoolean((String)hashProp.get("isextensional")));
		}
		
		((Class)RefOntoUMLElement).setIsActive(Boolean.parseBoolean((String)hashProp.get("isactive")));
	}
	
	@Override
	public void dealReferences()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		for (Object prop : this.Mapper.getElements(XMIElement, ElementType.PROPERTY))
		{
			XMI2RefProperty xmi2refprop = new XMI2RefProperty(prop, Mapper);
			if (xmi2refprop.RefOntoUMLElement != null)
			{
//				listProperties.add(xmi2refprop);
				((Class)RefOntoUMLElement).getOwnedAttribute().add((Property)xmi2refprop.getRefOntoUMLElement());
				xmi2refprop.createSubElements();
			}
		}
		
		super.createSubElements();
	}
}
