package br.ufes.inf.nemo.ocl.ocl2alloy.exception;

/**
 * @author John Guerson
 */

public class StereotypeException extends java.lang.RuntimeException {

	/**
	 * Constructor.
	 * 
	 * @param stereo
	 */
	public StereotypeException(String stereo) 
	{		
		super("The stereotype \""+stereo+"\" is not supported. We only support invariants and derivations.");
	}
	
	private static final long serialVersionUID = 1L;

}
