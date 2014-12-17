package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class NonSupported extends Ocl2Owl_SwrlException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param operator - contains the name of the unsupported operator
	 * @param rule - contains the rule
	 */
	public NonSupported(String operator, String rule) 
    {		
		//super("\nWarning: Unable to process the following OCL rule because it uses the unsupported operator '" + operator + "':\n" + rule);
		super("Warning: Unable to process the following OCL rule because it uses the unsupported operator '" + operator + "'.");
    }
	
	
}

