package br.ufes.inf.nemo.ocl2owl_swrl.exceptions;

public class NonInitialized extends Ocl2Owl_SwrlException {

	/**
	 * Constructor.
	 * 
	 * @param argument - contains the uninitialized argument 
	 */
	public NonInitialized(String argument) 
    {		
		super("\nThe necessary arguments was not passed to the OCL2SWRL constructor. Verify the '" + argument + "' argument used in the rule:\n");
    }
	
	private static final long serialVersionUID = 1L;

}

