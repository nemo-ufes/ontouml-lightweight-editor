package br.ufes.inf.nemo.oled.problems;

import org.eclipse.emf.ecore.EObject;

public class ErrorElement extends ProblemElement {

	public ErrorElement(EObject eobject, int identifier,String description, TypeProblem typeProblem) 
	{
		super(eobject, identifier, description, typeProblem);
	}	
}
