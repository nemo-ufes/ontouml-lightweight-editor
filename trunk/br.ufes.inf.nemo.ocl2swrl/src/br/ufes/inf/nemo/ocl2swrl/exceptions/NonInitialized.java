package br.ufes.inf.nemo.ocl2swrl.exceptions;

/**
 * @author John Guerson
 */

public class NonInitialized extends Exception {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public NonInitialized(String argument) 
    {		
		super("The necessary arguments was not passed to the OCL2SWRL constructor. Verify the '" + argument + "' argument used in the rule:\n");
    }
	
	private static final long serialVersionUID = 1L;

}

