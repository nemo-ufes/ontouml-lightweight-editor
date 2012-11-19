package br.ufes.inf.nemo.move.ocl.constraint;

import org.eclipse.uml2.uml.Constraint;

/**
 * @author John Guerson
 */

public class OCLConstraintModel {
	
	@SuppressWarnings("unused")
	private Constraint oclConstraint;
	private String oclString;
		
	/**
	 * Constraint.
	 * 
	 * @param oclTextualConstraint
	 */
	public OCLConstraintModel (String oclString)
	{
		this();
		
		this.oclString = oclString;		
	}	
	
	/**
	 * Constructor.
	 */
	public OCLConstraintModel()
	{
		
	}

	public String getOCLString()
	{
		return oclString;
	}
	
}
