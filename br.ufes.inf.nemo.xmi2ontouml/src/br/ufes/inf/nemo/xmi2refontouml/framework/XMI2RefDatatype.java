package br.ufes.inf.nemo.xmi2refontouml.framework;

import RefOntoUML.DataType;
import RefOntoUML.Property;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;

public class XMI2RefDatatype extends XMI2RefClassifier
{
	public XMI2RefDatatype (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createDataType();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	public XMI2RefDatatype()
	{
	}
	
	@Override
	protected void deal()
	{
		if (((DataType)RefOntoUMLElement).getName() != null &&
				!((DataType)RefOntoUMLElement).getName().equals("Integer") &&
				!((DataType)RefOntoUMLElement).getName().equals("Boolean") && 
				!((DataType)RefOntoUMLElement).getName().equals("String") &&
				!((DataType)RefOntoUMLElement).getName().equals("Unlimited Natural"))
			super.deal();
			
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
//			listProperties.add(xmi2refprop);
			((DataType)RefOntoUMLElement).getOwnedAttribute().add((Property)xmi2refprop.getRefOntoUMLElement());
		}
		
		super.createSubElements();
	}
}
