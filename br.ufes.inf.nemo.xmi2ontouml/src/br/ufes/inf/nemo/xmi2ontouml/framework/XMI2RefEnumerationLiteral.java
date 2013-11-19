package br.ufes.inf.nemo.xmi2ontouml.framework;

import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;

public class XMI2RefEnumerationLiteral extends XMI2RefNamedElement
{
	public XMI2RefEnumerationLiteral(Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createEnumerationLiteral();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}

	@Override
	public void dealReferences()
	{
		// TODO Auto-generated method stub
		
	}
}
