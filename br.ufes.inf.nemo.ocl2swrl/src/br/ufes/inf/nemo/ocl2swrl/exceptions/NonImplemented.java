package br.ufes.inf.nemo.ocl2swrl.exceptions;

/**
 * @author John Guerson
 */

public class NonImplemented extends java.lang.RuntimeException {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public NonImplemented(String argument) 
    {		
		super("The " + argument + " method is not implemented for .");
    }
	
	private static final long serialVersionUID = 1L;

}

