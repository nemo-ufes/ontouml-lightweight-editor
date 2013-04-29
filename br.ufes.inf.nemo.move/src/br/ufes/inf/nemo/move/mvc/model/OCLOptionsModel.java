package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ocl2alloy.OCLOptions;

/**
 * 
 * This class represents a OCL COnstraints Options.
 * 
 * @author John Guerson
 */

public class OCLOptionsModel {

	/** OCL COnstraint Options. */
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
	
	/**
	 * Get OCL Constraints Options.
	 * 
	 * @return
	 */
	public OCLOptions getOCLOptions()
	{
		return option;
	}
	
	/**
	 * Set OCL Constraints Options.
	 * 
	 * @param opt
	 */
	public void setOCLOptions(OCLOptions opt)
	{
		this.option = opt;
	}	
	
}
