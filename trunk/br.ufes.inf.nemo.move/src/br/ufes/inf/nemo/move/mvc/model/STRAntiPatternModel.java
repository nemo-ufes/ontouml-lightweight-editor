package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.STRAntiPattern;

/**
 * 
 * This class represents a STR AntiPattern Model.
 * 
 * @author John Guerson
 */

public class STRAntiPatternModel {

	/** STR ANtiPattern. */
	private STRAntiPattern str;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param str
	 */
	public STRAntiPatternModel (STRAntiPattern str)
	{
		this.str = str;
	}

	/**
	 * Get STR AntiPattern.
	 * @return
	 */
	public STRAntiPattern getSTRAntiPattern()
	{
		return str;
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
