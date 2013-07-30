package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ocl2alloy.OCL2AlloyOptions;

/**
 * 
 * This class represents a OCL COnstraints Options.
 * 
 * @author John Guerson
 */

public class OCLOptionsModel {

	/** OCL COnstraint Options. */
	private OCL2AlloyOptions option;
	
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OCLOptionsModel (OCL2AlloyOptions opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OCLOptionsModel ()
	{
		this.option = new OCL2AlloyOptions();
	}
	
	/**
	 * Get OCL Constraints Options.
	 * 
	 * @return
	 */
	public OCL2AlloyOptions getOCLOptions()
	{
		return option;
	}
	
	/**
	 * Set OCL Constraints Options.
	 * 
	 * @param opt
	 */
	public void setOCLOptions(OCL2AlloyOptions opt)
	{
		this.option = opt;
	}	
	
}
