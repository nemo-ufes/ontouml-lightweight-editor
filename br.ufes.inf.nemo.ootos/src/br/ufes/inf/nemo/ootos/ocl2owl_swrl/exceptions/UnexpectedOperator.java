package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class UnexpectedOperator extends Ocl2Owl_SwrlException {

	/**
	 * Constructor.
	 * 
	 * @param operator -  contains the name of the unexpected operator
	 * @param tag - contains the name of the tag that doesn't uses the operator 
	 * @param rule - contains the rule
	 */
	public UnexpectedOperator(String operator, String tag, String rule) 
    {		
		//super("\nWarning: Unable to process the following OCL rule because of unexpected combination (operator: '" + operator + "' and tag: : '" + tag + "') :\n" +rule);
		super("Warning: Unable to process the following OCL rule because of unexpected combination (operator: '" + operator + "' and tag: : '" + tag + "').");
    }
	
	private static final long serialVersionUID = 1L;

}
