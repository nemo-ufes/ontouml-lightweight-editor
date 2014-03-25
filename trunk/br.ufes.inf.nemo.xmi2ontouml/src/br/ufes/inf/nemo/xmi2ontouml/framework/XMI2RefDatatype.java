package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.DataType;
import RefOntoUML.Property;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefDatatype extends XMI2RefClassifier
{
	public XMI2RefDatatype() {}
	
	public XMI2RefDatatype (Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createDataType());
	}
	
	public XMI2RefDatatype (Object XMIElement, XMIParser mapper, DataType datatype) throws Exception
	{
		super(XMIElement, mapper, datatype);
	}
	
	@Override
	protected void deal()
	{
		if (((DataType)RefOntoUMLElement).getName() == null || (
				!((DataType)RefOntoUMLElement).getName().equals("Integer") &&
				!((DataType)RefOntoUMLElement).getName().equals("Boolean") && 
				!((DataType)RefOntoUMLElement).getName().equals("String") &&
				!((DataType)RefOntoUMLElement).getName().equals("Unlimited Natural")))
			super.deal();
			
	}

	@Override
	public void dealReferences() {}
	
	@Override
	protected void createSubElements() throws Exception
	{
		for (Object prop : this.Mapper.getElements(XMIElement, ElementType.PROPERTY))
		{
			XMI2RefProperty xmi2refprop = new XMI2RefProperty(prop, Mapper);
			if (xmi2refprop.RefOntoUMLElement != null)
			{
//				listProperties.add(xmi2refprop);
				((DataType)RefOntoUMLElement).getOwnedAttribute().add((Property)xmi2refprop.getRefOntoUMLElement());
			}
		}
		
		super.createSubElements();
	}
}
