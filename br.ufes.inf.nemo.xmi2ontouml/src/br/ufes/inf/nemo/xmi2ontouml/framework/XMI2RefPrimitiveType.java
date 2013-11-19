package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.PrimitiveType;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;

public class XMI2RefPrimitiveType extends XMI2RefDatatype
{	
	public XMI2RefPrimitiveType (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.hashProp = Mapper.getProperties(XMIElement);
		
		String name = (String) hashProp.get("name");
		if (name.equalsIgnoreCase("int") || name.equalsIgnoreCase("integer"))
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
    		
    	} else
    	{
    		this.RefOntoUMLElement = factory.createPrimitiveType();
    		commonTasks();
    	}
		
		elemMap.put(Mapper.getID(XMIElement), this);
	}
	
	public XMI2RefPrimitiveType (PrimitiveType primType)
	{
		this.RefOntoUMLElement = primType;
	}
}
