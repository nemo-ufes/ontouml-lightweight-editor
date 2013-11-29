package br.ufes.inf.nemo.ocl2swrl.exceptions;

public class NonSupported  extends java.lang.RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonSupported(String operator) 
    {		
		super("This transformation does not support '" + operator + "'.");
    }
	
	
}

