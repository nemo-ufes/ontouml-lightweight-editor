package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

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
		//super("\nWarning: the transformation result for the following OCL rule has null '" + atoms + "':\n" + rule);
		super("Warning: the transformation result for the following OCL rule has null '" + atoms + "'.");
    }
	
	private static final long serialVersionUID = 1L;
}
