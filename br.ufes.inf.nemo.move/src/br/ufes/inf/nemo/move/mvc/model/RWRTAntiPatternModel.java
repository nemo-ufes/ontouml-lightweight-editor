package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.RWRTAntiPattern;

/**
 * 
 * This class represents a AC AntiPattern Model.
 * 
 * @author John Guerson
 */

public class RWRTAntiPatternModel {
	
	/** RWRT AntiPattern. */	
	private RWRTAntiPattern rwrt;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param ac
	 */
	public RWRTAntiPatternModel (RWRTAntiPattern rwrt)
	{
		this.rwrt = rwrt;
	}

	/**
	 * Get AC AntiPattern.
	 * 
	 * @return
	 */
	public RWRTAntiPattern getRWRTAntiPattern()
	{
		return rwrt;
	}
	
	/**
	 * Increment Unique ID.
	 */
	public void IncrementId()
	{
		uniqueId++;
	}
	
	/**
	 * Get Unique ID.
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

