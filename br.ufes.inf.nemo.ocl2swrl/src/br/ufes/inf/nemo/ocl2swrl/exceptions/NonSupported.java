package br.ufes.inf.nemo.ocl2swrl.exceptions;

public class NonSupported extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonSupported(String operator, String rule) 
    {		
		super("This transformation does not support the operator or tag '" + operator + "' used in the rule:\n" + rule);
    }
	
	
}

