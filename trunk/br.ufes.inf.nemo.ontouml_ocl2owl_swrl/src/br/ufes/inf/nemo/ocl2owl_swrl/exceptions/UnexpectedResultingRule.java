package br.ufes.inf.nemo.ocl2owl_swrl.exceptions;

public class UnexpectedResultingRule extends Ocl2Owl_SwrlException {
	/**
	 * Constructor.
	 * 
	 * @param operator -  contains the name of the unexpected operator
	 * @param tag - contains the name of the tag that doesn't uses the operator 
	 * @param rule - contains the rule
	 */
	public UnexpectedResultingRule(String atoms, String rule) 
    {		
		super("\nThe resulting rule above is not in the expected format, because the resulting atom(s) '" + atoms + "' is/are null:\n" + rule);
    }
	
	private static final long serialVersionUID = 1L;
}
