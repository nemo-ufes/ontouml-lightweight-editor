package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * 
 * This class represents an OntoUML Model Options.
 * 
 * @author John Guerson
 */

public class OntoUMLOptionsModel {
	
	/** OntoUML model options */
	private OntoUML2AlloyOptions option;
		
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OntoUMLOptionsModel (OntoUML2AlloyOptions opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OntoUMLOptionsModel ()
	{	
		this.option = new OntoUML2AlloyOptions();
	}
		
	/**
	 * Get OntoUML Model Options.
	 * @return
	 */
	public OntoUML2AlloyOptions getOptions()
	{
		return option;
	}
	
	/**
	 * Set OntoUML Model Options.
	 * 
	 * @param opt
	 */
	public void setOptions(OntoUML2AlloyOptions opt)
	{
		this.option = opt;
	}	
	
}
