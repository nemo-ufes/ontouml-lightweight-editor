package br.ufes.inf.nemo.xmi2ontouml.framework;

import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;
import RefOntoUML.NamedElement;
import RefOntoUML.VisibilityKind;

public abstract class XMI2RefNamedElement extends XMI2RefElement
{
	public XMI2RefNamedElement() {}
	
	public XMI2RefNamedElement(Object XMIElement, XMIParser mapper)
	{
		super(XMIElement, mapper);
	}
	
	public XMI2RefNamedElement(Object XMIElement, XMIParser mapper, NamedElement namedElem)
	{
		super(XMIElement, mapper, namedElem);
	}
	
	@Override
	protected void deal()
	{
		((NamedElement)RefOntoUMLElement).setName((String)hashProp.get("name"));
		((NamedElement)RefOntoUMLElement).setVisibility(VisibilityKind.get((String)hashProp.get("visibility")));
	}
	
	public String getName()
	{
		return (String) hashProp.get("name");
	}
}
