package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;

/**
 * 
 * This class represents an OntoUML Model Options.
 * 
 * @author John Guerson
 */

public class OntoUMLOptionsModel {
	
	/** OntoUML model options */
	private OntoUMLOptions option;
	
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OntoUMLOptionsModel (OntoUMLOptions opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OntoUMLOptionsModel ()
	{
		this.option = new OntoUMLOptions();
	}
		
	/**
	 * Get OntoUML Model Options.
	 * @return
	 */
	public OntoUMLOptions getOptions()
	{
		return option;
	}
	
	/**
	 * Set OntoUML Model Options.
	 * 
	 * @param opt
	 */
	public void setOptions(OntoUMLOptions opt)
	{
		this.option = opt;
	}	
	
}
