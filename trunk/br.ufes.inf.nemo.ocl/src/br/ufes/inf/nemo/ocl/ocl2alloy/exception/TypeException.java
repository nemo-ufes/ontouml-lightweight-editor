package br.ufes.inf.nemo.ocl.ocl2alloy.exception;

/**
 * @author John Guerson
 */

public class TypeException extends java.lang.RuntimeException {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public TypeException(String type, String details) 
    {		
		super("The type \""+type+"\" is not supported. "+details);
    }
	
	private static final long serialVersionUID = 1L;

}

