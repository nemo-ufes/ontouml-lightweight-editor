package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.ocl2alloy.options.OCLOptions;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;

/**
 * @author John Guerson
 */

public class OptionsModel {
	
	private OntoUMLOptions option;
	private OCLOptions oclOptions;
	
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OptionsModel (OntoUMLOptions opt, OCLOptions oclOptions)
	{
		this.option = opt;
		this.oclOptions = oclOptions;
	}
	
	/**
	 * Constructor.
	 */
	public OptionsModel ()
	{
		this.option = new OntoUMLOptions();
		this.oclOptions = new OCLOptions();
	}
		
	public OCLOptions getOCLOptions()
	{
		return oclOptions;
	}
	
	public void setOCLOptions(OCLOptions oclOptions)
	{
		this.oclOptions = oclOptions;
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
