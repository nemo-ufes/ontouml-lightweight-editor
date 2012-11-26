package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ontouml2alloy.options.Options;

/**
 * @author John Guerson
 */

public class OptionsModel {
	
	private Options option;
	
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OptionsModel (Options opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OptionsModel ()
	{
		this.option = new Options();
	}

	public Options getOptions()
	{
		return option;
	}
	
	public void setOptions(Options opt)
	{
		this.option = opt;
	}	
	
}
