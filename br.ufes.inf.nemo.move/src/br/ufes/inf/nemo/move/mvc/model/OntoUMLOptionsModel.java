package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ontouml2alloy.Onto2AlloyOptions;

/**
 * 
 * This class represents an OntoUML Model Options.
 * 
 * @author John Guerson
 */

public class OntoUMLOptionsModel {
	
	/** OntoUML model options */
	private Onto2AlloyOptions option;
		
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OntoUMLOptionsModel (Onto2AlloyOptions opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OntoUMLOptionsModel ()
	{	
		this.option = new Onto2AlloyOptions();
	}
		
	/**
	 * Get OntoUML Model Options.
	 * @return
	 */
	public Onto2AlloyOptions getOptions()
	{
		return option;
	}
	
	/**
	 * Set OntoUML Model Options.
	 * 
	 * @param opt
	 */
	public void setOptions(Onto2AlloyOptions opt)
	{
		this.option = opt;
	}	
	
}
