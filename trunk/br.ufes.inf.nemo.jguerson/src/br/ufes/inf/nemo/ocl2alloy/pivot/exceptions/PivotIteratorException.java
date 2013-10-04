package br.ufes.inf.nemo.ocl2alloy.pivot.exceptions;


public class PivotIteratorException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotIteratorException (String iterator, String justification) 
	{
		super(iterator+" - Unsupported Iterator : "+justification);
	}
}
