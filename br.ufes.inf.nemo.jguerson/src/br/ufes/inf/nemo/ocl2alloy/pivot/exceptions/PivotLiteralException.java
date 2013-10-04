package br.ufes.inf.nemo.ocl2alloy.pivot.exceptions;

public class PivotLiteralException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotLiteralException(String literal, String justification) 
	{
		super(literal+" - Unsupported Literal : "+justification);		
	}

}
