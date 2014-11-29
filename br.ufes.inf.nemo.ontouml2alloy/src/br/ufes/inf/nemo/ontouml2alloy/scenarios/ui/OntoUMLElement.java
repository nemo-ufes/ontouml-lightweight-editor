package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;

/**
 * @author John Guerson
 */

public class OntoUMLElement {
	
	protected EObject element;
	
	public OntoUMLElement (EObject element) 
	{
		this.element = element;
	}
	
	/**
	 * To String.
	 */
	@Override
	public String toString() 
	{
		String s = OntoUMLNameHelper.getNameAndType(element, true, false);
		
		if(element instanceof Association){
			Association a = (Association) element;
			s+="("+OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(0))+" -> "+OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(0))+")";
		}
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		return element.equals(obj);
	}

	public EObject getElement() {
		return element;
	}
}
