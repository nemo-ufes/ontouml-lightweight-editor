package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.HashMap;

import RefOntoUML.Element;

public class ElementMap extends HashMap<String, XMI2RefElement>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7506870759977111416L;
	
	public Element get(String key)
	{
		XMI2RefElement xmi2refelement = super.get(key);
		
		return xmi2refelement.getRefOntoUMLElement();
	}

}
