package br.ufes.inf.nemo.ocl2owl_swrl.exceptions;

public class UnsupportedByReasoner extends Ocl2Owl_SwrlException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedByReasoner(String reasoner) 
    {		
		//super("\nError: Unable to perform the OCL transformation because of insufficient arguments. Verify the '" + argument + "'.\n");
		super("Warning: Unable to perform the OCL transformation because this rule will generate SWRL built-ins unsupported by the reasoner '" + reasoner + "'.");
    }
}
