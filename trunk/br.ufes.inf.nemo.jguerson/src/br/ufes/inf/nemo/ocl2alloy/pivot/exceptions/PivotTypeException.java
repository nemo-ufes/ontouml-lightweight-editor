package br.ufes.inf.nemo.ocl2alloy.pivot.exceptions;

public class PivotTypeException extends PivotOCLToAlloyException {

	private static final long serialVersionUID = 1L;

	public PivotTypeException (String type, String justification) 
	{
		super(type+" - Unsupported Type : "+justification);		
	}

}
