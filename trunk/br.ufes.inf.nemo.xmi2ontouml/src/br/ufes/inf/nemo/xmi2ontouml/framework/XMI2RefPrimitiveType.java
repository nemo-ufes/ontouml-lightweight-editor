package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.PrimitiveType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefPrimitiveType extends XMI2RefDatatype
{	
	public XMI2RefPrimitiveType (Object XMIElement, XMIParser mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		String name = (String) hashProp.get("name");
		if (name.equalsIgnoreCase("int") || name.equalsIgnoreCase("integer") ||
				name.equalsIgnoreCase("double"))
		{
			this.RefOntoUMLElement = XMI2RefModel.INTEGER_PRIMITIVE;
    	}
		else if (name.equalsIgnoreCase("bool") || name.equalsIgnoreCase("boolean"))
		{
			this.RefOntoUMLElement = XMI2RefModel.BOOLEAN_PRIMITIVE;
    	}
		else if (name.equalsIgnoreCase("str") || name.equalsIgnoreCase("string") ||
    			name.equalsIgnoreCase("char") || name.equalsIgnoreCase("character"))
		{
			this.RefOntoUMLElement = XMI2RefModel.STRING_PRIMITIVE;
    		
    	} else if (name.equalsIgnoreCase("unlimited") || name.equalsIgnoreCase("float"))
    	{
    		this.RefOntoUMLElement = XMI2RefModel.UNLIMITED_NATURAL_PRIMITIVE;
    	}
    	else
    	{
    		this.RefOntoUMLElement = factory.createPrimitiveType();
    		deal();
    	}
		elemMap.put(XMIElement, this);
	}
	
	public XMI2RefPrimitiveType (PrimitiveType primType)
	{
		this.RefOntoUMLElement = primType;
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		if (this.RefOntoUMLElement != XMI2RefModel.INTEGER_PRIMITIVE &&
			this.RefOntoUMLElement != XMI2RefModel.BOOLEAN_PRIMITIVE &&
			this.RefOntoUMLElement != XMI2RefModel.STRING_PRIMITIVE &&
			this.RefOntoUMLElement != XMI2RefModel.UNLIMITED_NATURAL_PRIMITIVE)
		{
			super.createSubElements();
		}
	}
}
