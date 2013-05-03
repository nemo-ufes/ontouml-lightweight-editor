package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.MRBSAntiPattern;

public class MRBSAntiPatternModel {

	/** MRBS AntiPattern. */	
	private MRBSAntiPattern mrbs;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param mrbs
	 */
	public MRBSAntiPatternModel (MRBSAntiPattern mrbs)
	{
		this.mrbs = mrbs;
	}

	/**
	 * Get MRBS AntiPattern.
	 * 
	 * @return
	 */
	public MRBSAntiPattern getMRBSAntiPattern()
	{
		return mrbs;
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
