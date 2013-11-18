package br.ufes.inf.nemo.xmi2refontouml.framework;

import java.util.List;

import RefOntoUML.Constraintx;
import RefOntoUML.Element;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;

public class XMI2RefContraint extends XMI2RefNamedElement
{
	public XMI2RefContraint (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createComment();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}
	
	@Override
	public void deal()
	{
		super.deal();
	}

	@Override
	public void dealReferences()
	{
		for (Object constrainedElement : (List<?>)hashProp.get("constrainedelement"))
		{
			((Constraintx)RefOntoUMLElement).getConstrainedElement().add((Element)elemMap.get((String)constrainedElement));
		}
	}

}
