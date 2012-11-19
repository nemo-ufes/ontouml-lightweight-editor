package br.ufes.inf.nemo.move.ocl;

import org.eclipse.uml2.uml.Constraint;

public class OCLConstraintModel {
	
	private Constraint umlConstraint;
	
	public OCLConstraintModel (Constraint umlConstraint)
	{
		this.umlConstraint = umlConstraint;
	}	

	public Constraint getConstraint()
	{
		return umlConstraint;
	}
}
