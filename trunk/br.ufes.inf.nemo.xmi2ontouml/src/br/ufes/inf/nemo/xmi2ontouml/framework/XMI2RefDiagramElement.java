package br.ufes.inf.nemo.xmi2ontouml.framework;

import RefOntoUML.Element;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefDiagramElement extends XMI2RefElement
{
	public XMI2RefDiagramElement(Object XMIElement, XMIParser mapper)
	{
		super(XMIElement, mapper, null);
	}
	
	@Override
	protected void deal() {}

	@Override
	public void dealReferences() throws Exception {}
	
	@Override
	protected void createSubElements() {}
	
	public XMI2RefElement getSubject()
	{
		return elemMap.get(hashProp.get("subject"));
	}
	
	public Element getSubjectElement()
	{
		return elemMap.getElement(hashProp.get("subject"));
	}
}
