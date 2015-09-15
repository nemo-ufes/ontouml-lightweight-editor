package br.ufes.inf.nemo.sml2alloy.exception;

public class UnsupportedRelationException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5168225078124504354L;
	
	private String relationFunctionCall = new String();
	
	public UnsupportedRelationException (String relation, String relCall)
	{
		super("ATTENTION: Relation/Function "+relation+" is not supported and must be defined manually.");
		
		this.relationFunctionCall = relCall;
	}
	
	public String getGeneratedCall()
	{
		return this.relationFunctionCall;
	}
}
