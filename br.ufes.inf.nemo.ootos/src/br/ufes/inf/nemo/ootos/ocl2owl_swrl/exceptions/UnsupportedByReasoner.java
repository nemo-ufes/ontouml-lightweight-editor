package br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions;

public class UnsupportedByReasoner extends Ocl2Owl_SwrlException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedByReasoner(String reasoner, String operator) 
    {		
		//super("\nError: Unable to perform the OCL transformation because of insufficient arguments. Verify the '" + argument + "'.\n");
		super("Warning: Unable to perform the OCL transformation because this OCL rule has an the operator '"+ operator +"' and will generate a SWRL rule using built-ins unsupported by the reasoner '" + reasoner + "'.");
    }
}
