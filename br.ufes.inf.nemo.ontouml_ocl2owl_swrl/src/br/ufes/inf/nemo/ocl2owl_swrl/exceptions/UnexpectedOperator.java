package br.ufes.inf.nemo.ocl2owl_swrl.exceptions;

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
		super("\nThe rule above was not translated because the use of operator '" + operator + "' combined with the tag '" + tag + "' was unexpected:\n" + rule);
    }
	
	private static final long serialVersionUID = 1L;

}
