package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.antipattern.RWORAntiPattern;

/**
 * 
 * This class represents a RWOR AntiPattern Model.
 * 
 * @author John Guerson
 */

public class RWORAntiPatternModel {

	/** RWOR AntiPattern. */
	private RWORAntiPattern rwor;
	
	/** Unique ID. */
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

	/**
	 * Get RWOR AntiPattern. 
	 * 
	 * @return
	 */
	public RWORAntiPattern getRWORAntiPattern()
	{
		return rwor;
	}
	
	/**
	 * Increment AntiPattern ID.
	 */
	public void IncrementId()
	{
		uniqueId++;
	}
	
	/**
	 * Get ID.
	 * 
	 * @return
	 */
	public Integer getId()
	{
		return uniqueId;
	}
	
	/**
	 * Set ID.
	 * 
	 * @param uniqueId
	 */
	public void setId(Integer uniqueId)
	{
		this.uniqueId = uniqueId;
	}
}
