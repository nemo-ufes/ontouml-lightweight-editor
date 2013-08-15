package br.ufes.inf.nemo.ocl2swrl.exception;

/**
 * @author John Guerson
 */

public class IteratorException extends java.lang.RuntimeException {

	/** 
	 * Constructor.
	 * 
	 * @param iter
	 * @param details
	 */
	public IteratorException(String iter, String details) 
    {		
		super("Invalid iterator: \""+iter+"\". "+details);
    }
	
	private static final long serialVersionUID = 1L;

}

