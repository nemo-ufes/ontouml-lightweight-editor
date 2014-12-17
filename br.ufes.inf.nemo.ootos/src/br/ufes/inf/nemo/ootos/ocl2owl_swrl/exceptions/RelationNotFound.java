package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class RelationNotFound extends Ocl2Owl_SwrlException  {
	/**
	 * Constructor.
	 * 
	 * @param operator -  contains the name of the unexpected operator
	 * @param tag - contains the name of the tag that doesn't uses the operator 
	 * @param rule - contains the rule
	 */
	public RelationNotFound(String rule) 
    {		
		//super("\nAn expected relation was not found. Then, the rule below was not transled:\n" + rule);
		super("An expected relation was not found. Then, the rule was not transled.");
    }
	
	private static final long serialVersionUID = 1L;

}
