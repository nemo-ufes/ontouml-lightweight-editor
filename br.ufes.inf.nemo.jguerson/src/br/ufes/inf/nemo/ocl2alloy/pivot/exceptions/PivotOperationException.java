package br.ufes.inf.nemo.ocl2alloy.pivot.exceptions;


public class PivotOperationException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotOperationException (String operation, String justification) 
	{
		super(operation+" - Unsupported Operation : "+justification);
	}
}
