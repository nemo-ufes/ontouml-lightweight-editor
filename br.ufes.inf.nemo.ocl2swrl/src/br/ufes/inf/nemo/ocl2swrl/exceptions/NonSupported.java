package br.ufes.inf.nemo.ocl2swrl.exceptions;

public class NonSupported extends Ocl2SwrlException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param operator - contains the name of the unsupported operator
	 * @param rule - contains the rule
	 */
	public NonSupported(String operator, String rule) 
    {		
		super("\nThe rule above was not translated because this transformation does not support the operator or tag '" + operator + "' used in the rule:\n" + rule);
    }
	
	
}

