package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class ConsequentVariableNonDeclaredOnAntecedent extends Ocl2Owl_SwrlException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param operator - contains the name of the unsupported operator
	 * @param rule - contains the rule
	 */
	public ConsequentVariableNonDeclaredOnAntecedent(String variable, String rule) 
    {		
		
		//super("\nWarning: the transformation result for the following OCL rule has a variable (" + variable + ") on consequent that wasn't declared on antecedent:\n" + rule);
		super("Warning: the transformation result for the following OCL rule has a variable (" + variable + ") on consequent that wasn't declared on antecedent.");
    }
	
	
}

