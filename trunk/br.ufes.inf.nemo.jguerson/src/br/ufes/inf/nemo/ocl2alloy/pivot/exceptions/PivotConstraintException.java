package br.ufes.inf.nemo.ocl2alloy.pivot.exceptions;

public class PivotConstraintException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotConstraintException (String stereotype, String justification) 
	{
		super(stereotype+" - Unsupported Constraint : "+justification);
	}
}
