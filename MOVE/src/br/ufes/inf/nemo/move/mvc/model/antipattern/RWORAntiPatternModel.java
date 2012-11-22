package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.RWORAntiPattern;

/**
 * @author John Guerson
 */

public class RWORAntiPatternModel {

	private RWORAntiPattern rwor;
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param rwor
	 */
	public RWORAntiPatternModel (RWORAntiPattern rwor)
	{
		this.rwor = rwor;
	}

	public RWORAntiPattern getRWORAntiPattern()
	{
		return rwor;
	}
	
	public void IncrementId()
	{
		uniqueId++;
	}
	
	public Integer getId()
	{
		return uniqueId;
	}
	
	public void setId(Integer uniqueId)
	{
		this.uniqueId = uniqueId;
	}
}
