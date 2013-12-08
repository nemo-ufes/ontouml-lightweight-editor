package br.ufes.inf.nemo.ocl.ocl2alloy.exception;

/**
 * @author John Guerson
 */

public class LiteralException extends java.lang.RuntimeException {

	/** 
	 * Constructor.
	 * 
	 * @param lit
	 */
	public LiteralException(String lit) 
    {		
		super("The literal \""+lit+"\" is not supported. We only support Integer literals. ");
    }
	
	private static final long serialVersionUID = 1L;

}
