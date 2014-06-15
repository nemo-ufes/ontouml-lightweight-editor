package br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions;


public class PivotOperationException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotOperationException (String operation, String justification) 
	{
		super(operation+" - Unsupported Operation : "+justification);
	}
}
