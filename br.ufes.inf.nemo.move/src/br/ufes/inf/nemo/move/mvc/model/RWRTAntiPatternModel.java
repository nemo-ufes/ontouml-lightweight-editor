package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.rwrt.RWRTAntiPattern;

/**
 * 
 * This class represents a RWRT AntiPattern Model.
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
	 * @param rwrt
	 */
	public RWRTAntiPatternModel (RWRTAntiPattern rwrt)
	{
		this.rwrt = rwrt;
	}

	/**
	 * Get RWRT AntiPattern.
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

