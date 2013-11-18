package br.ufes.inf.nemo.xmi2refontouml.framework;

import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;

public class XMI2RefEnumeration extends XMI2RefDatatype
{
	public XMI2RefEnumeration(Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createEnumeration();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		for (Object enumlit : this.Mapper.getElements(XMIElement, ElementType.ENUMLITERAL))
		{
			XMI2RefEnumerationLiteral xmi2refenumlit = new XMI2RefEnumerationLiteral(enumlit, Mapper);
//			listProperties.add(xmi2refenumlit.getRefOntoUMLElement());
			((Enumeration)RefOntoUMLElement).getOwnedLiteral().add((EnumerationLiteral)xmi2refenumlit.getRefOntoUMLElement());
		}
		
		super.createSubElements();
	}
}
