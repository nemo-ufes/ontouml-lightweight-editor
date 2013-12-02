package br.ufes.inf.nemo.ocl2swrl.exceptions;

/**
 * @author John Guerson
 */

public class NonImplemented extends Exception {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public NonImplemented(String argument, String rule) 
    {		
		super("The " + argument + " method is not implemented to solve the rule:\n" + rule);
    }
	
	private static final long serialVersionUID = 1L;

}

