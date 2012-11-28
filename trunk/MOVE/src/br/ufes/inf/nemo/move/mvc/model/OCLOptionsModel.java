package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ocl2alloy.options.OCLOptions;

/**
 * @author John Guerson
 */

public class OCLOptionsModel {

	private OCLOptions option;
	
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OCLOptionsModel (OCLOptions opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OCLOptionsModel ()
	{
		this.option = new OCLOptions();
	}
			
	public OCLOptions getOCLOptions()
	{
		return option;
	}
	
	public void setOCLOptions(OCLOptions opt)
	{
		this.option = opt;
	}	
	
}
