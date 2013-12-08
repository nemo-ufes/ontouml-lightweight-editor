package br.ufes.inf.nemo.ocl.ocl2alloy.exception;

/**
 * @author John Guerson
 */

public class OperationException extends java.lang.RuntimeException {

	/** 
	 * Constructor.
	 * 
	 * @param op
	 * @param details
	 */
	public OperationException(String op, String details) 
    {		
		super("Invalid operation: \""+op+"\". "+details);
    }
	
	private static final long serialVersionUID = 1L;

}
