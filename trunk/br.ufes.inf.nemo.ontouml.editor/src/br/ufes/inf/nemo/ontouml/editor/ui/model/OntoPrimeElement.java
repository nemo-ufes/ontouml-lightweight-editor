package br.ufes.inf.nemo.ontouml.editor.ui.model;

import org.eclipse.emf.ecore.EObject;

/**
 * This class encapsulates a model element of the Joao Paulo's version of the OntoUML language.
 * 
 * @author John
 *
 */

public class OntoPrimeElement extends ElementAdapter {

	public OntoPrimeElement(EObject eo) 
	{
		super(eo);
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public String getName() 
	{
		return "#Unimplemented";
	}

}
