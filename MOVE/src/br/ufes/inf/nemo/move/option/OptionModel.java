package br.ufes.inf.nemo.move.option;

import br.ufes.inf.nemo.ontouml2alloy.util.Options;

/**
 * @author John Guerson
 */

public class OptionModel {
	
	private Options option;
	
	/**
	 * Constructor.
	 * 
	 * @param opt
	 */
	public OptionModel (Options opt)
	{
		this.option = opt;
	}
	
	/**
	 * Constructor.
	 */
	public OptionModel ()
	{
		this.option = new Options();
	}

	public Options getOptions()
	{
		return option;
	}
	
	public void setOptions(Options opt)
	{
		this.option.antiRigidity = opt.antiRigidity;
		this.option.weakSupplementationConstraint = opt.weakSupplementationConstraint;
		this.option.identityPrinciple = opt.identityPrinciple;
		this.option.antiRigidity = opt.antiRigidity;
	}	
	
}
