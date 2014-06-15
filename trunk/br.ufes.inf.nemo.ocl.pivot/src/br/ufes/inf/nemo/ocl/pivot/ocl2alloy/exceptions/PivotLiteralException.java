package br.ufes.inf.nemo.ocl.pivot.ocl2alloy.exceptions;

public class PivotLiteralException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotLiteralException(String literal, String justification) 
	{
		super(literal+" - Unsupported Literal : "+justification);		
	}

}
