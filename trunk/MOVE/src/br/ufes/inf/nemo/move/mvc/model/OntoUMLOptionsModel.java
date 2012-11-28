package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;

/**
 * @author John Guerson
 */

public class OntoUMLOptionsModel {
	
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
			
	public OntoUMLOptions getOptions()
	{
		return option;
	}
	
	public void setOptions(OntoUMLOptions opt)
	{
		this.option = opt;
	}	
	
}
