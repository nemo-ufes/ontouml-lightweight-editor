package br.ufes.inf.nemo.ocl2swrl.exceptions;

/**
 * @author John Guerson
 */

public class NonInitialized extends java.lang.RuntimeException {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public NonInitialized(String argument) 
    {		
		super("The necessaries arguments was not passed to the constructor. Verify the " + argument + " argument");
    }
	
	private static final long serialVersionUID = 1L;

}

