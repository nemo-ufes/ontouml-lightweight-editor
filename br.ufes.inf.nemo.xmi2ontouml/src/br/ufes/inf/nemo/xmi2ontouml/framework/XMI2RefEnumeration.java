package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefEnumeration extends XMI2RefDatatype
{
	public XMI2RefEnumeration(Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createEnumeration());
	}
	
	@Override
	protected void createSubElements() throws Exception
	{
		for (Object enumlit : this.Mapper.getElements(XMIElement, ElementType.ENUMLITERAL))
		{
			XMI2RefEnumerationLiteral xmi2refenumlit = new XMI2RefEnumerationLiteral(enumlit, Mapper);
			if (xmi2refenumlit.RefOntoUMLElement != null)
			{
				((Enumeration)RefOntoUMLElement).getOwnedLiteral().add((EnumerationLiteral)xmi2refenumlit.getRefOntoUMLElement());
				xmi2refenumlit.createSubElements();
			}
		}
		
		super.createSubElements();
	}
}
