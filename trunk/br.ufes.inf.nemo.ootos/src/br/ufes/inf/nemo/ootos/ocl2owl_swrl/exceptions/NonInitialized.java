package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class NonInitialized extends Ocl2Owl_SwrlException {

	/**
	 * Constructor.
	 * 
	 * @param argument - contains the uninitialized argument 
	 */
	public NonInitialized(String argument) 
    {		
		//super("\nError: Unable to perform the OCL transformation because of insufficient arguments. Verify the '" + argument + "'.\n");
		super("Error: Unable to perform the OCL transformation because of insufficient arguments. Verify the '" + argument + "'.");
    }
	
	private static final long serialVersionUID = 1L;

}

