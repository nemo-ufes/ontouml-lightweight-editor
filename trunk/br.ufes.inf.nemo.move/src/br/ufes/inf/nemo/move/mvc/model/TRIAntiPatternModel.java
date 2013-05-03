package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.TRIAntiPattern;

/**
 * 
 * This class represents a TRI AntiPattern Model.
 * 
 * @author John Guerson
 */

public class TRIAntiPatternModel {

	/** TRI AntiPattern. */	
	private TRIAntiPattern tri;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param tri
	 */
	public TRIAntiPatternModel (TRIAntiPattern tri)
	{
		this.tri = tri;
	}

	/**
	 * Get TRI AntiPattern.
	 * 
	 * @return
	 */
	public TRIAntiPattern getTRIAntiPattern()
	{
		return tri;
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
