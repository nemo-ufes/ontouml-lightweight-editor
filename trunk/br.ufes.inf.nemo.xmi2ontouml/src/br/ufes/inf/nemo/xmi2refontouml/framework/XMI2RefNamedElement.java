package br.ufes.inf.nemo.xmi2refontouml.framework;

import RefOntoUML.NamedElement;
import RefOntoUML.VisibilityKind;

public abstract class XMI2RefNamedElement extends XMI2RefElement
{
	@Override
	protected void deal()
	{
		((NamedElement)RefOntoUMLElement).setName((String)hashProp.get("name"));
		((NamedElement)RefOntoUMLElement).setVisibility(VisibilityKind.get((String)hashProp.get("visibility")));
	}
}
