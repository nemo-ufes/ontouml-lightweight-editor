package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.antipattern.ACAntiPattern;

/**
 * 
 * This class represents a AC AntiPattern Model.
 * 
 * @author John Guerson
 */

public class ACAntiPatternModel {
	
	/** AC AntiPattern. */	
	private ACAntiPattern ac;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param ac
	 */
	public ACAntiPatternModel (ACAntiPattern ac)
	{
		this.ac = ac;
	}

	/**
	 * Get AC AntiPattern.
	 * 
	 * @return
	 */
	public ACAntiPattern getACAntiPattern()
	{
		return ac;
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

