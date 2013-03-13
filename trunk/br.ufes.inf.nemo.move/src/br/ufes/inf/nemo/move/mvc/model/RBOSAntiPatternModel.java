package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.RBOSAntiPattern;

/**
 * This class represents a RBOS AntiPattern Model.
 * 
 * @author John Guerson
 */

public class RBOSAntiPatternModel {

	/** RBOS Anti Pattern. */
	private RBOSAntiPattern rbos;
	
	/** Unique ID. */	
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param rbos
	 */
	public RBOSAntiPatternModel (RBOSAntiPattern rbos)
	{
		this.rbos = rbos;
	}

	/**
	 * Get RBOS AntiPattern.
	 * 
	 * @return
	 */
	public RBOSAntiPattern getRBOSAntiPattern()
	{
		return rbos;
	}
	
	/**
	 * Increment AntiPattern ID.
	 */
	public void IncrementId()
	{
		uniqueId++;
	}
	
	/**
	 * Get AntiPattern ID. 
	 * 
	 * @return
	 */
	public Integer getId()
	{
		return uniqueId;
	}
	
	/**
	 * Set AntiPattern ID.
	 * 
	 * @param uniqueId
	 */
	public void setId(Integer uniqueId)
	{
		this.uniqueId = uniqueId;
	}
}
