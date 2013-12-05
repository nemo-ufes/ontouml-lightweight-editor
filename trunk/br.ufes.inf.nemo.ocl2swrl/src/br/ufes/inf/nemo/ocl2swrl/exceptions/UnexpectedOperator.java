package br.ufes.inf.nemo.ocl2swrl.exceptions;

public class UnexpectedOperator extends Exception {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param details
	 */
	public UnexpectedOperator(String operator, String tag, String rule) 
    {		
		super("\nThe rule above was not translated because the use of operator " + operator + " combined with the tag " + tag + " was unexpected:\n" + rule);
    }
	
	private static final long serialVersionUID = 1L;

}
