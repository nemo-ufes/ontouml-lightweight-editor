package br.ufes.inf.nemo.sml2alloy.exception;

public class UnsupportedRelationException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5168225078124504354L;
	
	public UnsupportedRelationException (String relation)
	{
		super("The comparative relation \""+relation+"\" is not supported.");
	}
}
