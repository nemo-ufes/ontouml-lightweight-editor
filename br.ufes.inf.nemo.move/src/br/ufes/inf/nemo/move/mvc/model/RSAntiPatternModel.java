package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.RSAntiPattern;

/**
 * This class represents a RS AntiPattern Model.
 * 
 * @author John Guerson
 */

public class RSAntiPatternModel {
	
	/** RS AntiPattern. */
	private RSAntiPattern rs;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param rs
	 */
	public RSAntiPatternModel (RSAntiPattern rs)
	{
		this.rs = rs;
	}

	/** Get RS AntiPattern */
	public RSAntiPattern getRSAntiPattern()
	{
		return rs;
	}
	
	/** Increment AntiPattern ID.*/
	public void IncrementId()
	{
		uniqueId++;
	}
	
	/** Get Anti Pattern ID. */
	public Integer getId()
	{
		return uniqueId;
	}
	
	/** Set AntiPattern ID. */
	public void setId(Integer uniqueId)
	{
		this.uniqueId = uniqueId;
	}

}
