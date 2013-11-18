package br.ufes.inf.nemo.xmi2refontouml.framework;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;

public class XMI2RefGeneralization extends XMI2RefElement
{
	public XMI2RefGeneralization (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createGeneralization();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}

	@Override
	protected void deal()
	{
		if (hashProp.containsKey("issubstitutable"))
			((Generalization)RefOntoUMLElement).setIsSubstitutable(Boolean.parseBoolean((String)hashProp.get("issubstitutable")));
		
	}

	@Override
	public void dealReferences()
	{
		// General and Specific are EOposites.
		// Given that, it`s only necessary to define one of those properties.
		((Generalization)RefOntoUMLElement).setGeneral((Classifier)elemMap.get((String)hashProp.get("general")));
		//((Generalization)RefOntoUMLElement).setSpecific((Classifier)hashProp.get("specific"));
	}
}
