package br.ufes.inf.nemo.ocl2swrl.exceptions;

/**
 * @author John Guerson
 */

public class NonSupported extends java.lang.RuntimeException {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public NonSupported(String argument) 
    {		
		super("This transformation does not support '" + argument + ".");
    }
	
	private static final long serialVersionUID = 1L;

}

