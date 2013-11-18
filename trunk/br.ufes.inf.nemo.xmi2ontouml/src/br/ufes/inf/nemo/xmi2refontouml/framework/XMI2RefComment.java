package br.ufes.inf.nemo.xmi2refontouml.framework;

import java.util.List;

import RefOntoUML.Comment;
import RefOntoUML.Element;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;

public class XMI2RefComment extends XMI2RefElement
{
	public XMI2RefComment (Object XMIElement, Mapper mapper) throws Exception
	{
		this.XMIElement = XMIElement;
		this.Mapper = mapper;
		this.RefOntoUMLElement = factory.createComment();
		
		this.hashProp = Mapper.getProperties(XMIElement);
		
		commonTasks();
	}

	@Override
	protected void deal()
	{
		if (hashProp.get("body") != null)
			((Comment)RefOntoUMLElement).setBody((String)hashProp.get("body"));
		
		else
			((Comment)RefOntoUMLElement).setBody("");
	}

	@Override
	public void dealReferences()
	{
		for (Object annotatedElement : (List<?>)hashProp.get("annotatedelement"))
		{
			((Comment)RefOntoUMLElement).getAnnotatedElement().add((Element)elemMap.get((String)annotatedElement));
		}
	}

}
