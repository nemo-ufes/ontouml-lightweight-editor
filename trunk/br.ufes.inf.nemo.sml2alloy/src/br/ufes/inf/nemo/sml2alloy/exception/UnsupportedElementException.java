package br.ufes.inf.nemo.sml2alloy.exception;

public class UnsupportedElementException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5168225078124504354L;
	
	public UnsupportedElementException (String elemType)
	{
		super("The element type \""+elemType+"\" is not a SML element, thus not supported.");
	}
}
