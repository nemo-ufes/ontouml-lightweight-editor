package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class NonImplemented extends Ocl2Owl_SwrlException {

	/**
	 * Constructor.
	 * 
	 * @param method - contains the name of the unimplemented method
	 * @param rule - contains the rule
	 */
	public NonImplemented(String method, String rule) 
    {		
		super("The rule was not translated because the " + method + " method is not implemented to solve it.");
		//super("\nThe rule below was not translated because the " + method + " method is not implemented to solve it:\n" + rule);
    }
	
	private static final long serialVersionUID = 1L;

}

