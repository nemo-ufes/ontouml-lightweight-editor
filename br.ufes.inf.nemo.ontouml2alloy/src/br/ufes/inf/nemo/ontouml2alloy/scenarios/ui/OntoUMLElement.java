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
		if(element instanceof RefOntoUML.Class)
			return OntoUMLNameHelper.getNameAndType(element, true, false);
		
		if(element instanceof Association){
			Association a = (Association) element;
			return OntoUMLNameHelper.getName(a, true, false)+" "+OntoUMLNameHelper.getTypeName(a, true)+" ("+OntoUMLNameHelper.getName(a.getMemberEnd().get(0).getType())+" - "+OntoUMLNameHelper.getName(a.getMemberEnd().get(1).getType())+")";
		}
		
		return OntoUMLNameHelper.getCommonName(element);
	}

	public EObject getElement() {
		return element;
	}
}
