package br.ufes.inf.nemo.xmi2ontouml.framework;

import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefEnumerationLiteral extends XMI2RefNamedElement
{
	public XMI2RefEnumerationLiteral(Object XMIElement, XMIParser mapper) throws Exception
	{
		super(XMIElement, mapper, factory.createEnumerationLiteral());
	}

	@Override
	public void dealReferences() {}
}
