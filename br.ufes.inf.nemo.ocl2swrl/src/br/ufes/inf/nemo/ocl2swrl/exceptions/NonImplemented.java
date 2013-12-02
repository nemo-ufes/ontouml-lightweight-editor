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
		super("\nThe rule above was not translated because the " + argument + " method is not implemented to solve it:\n" + rule);
    }
	
	private static final long serialVersionUID = 1L;

}

