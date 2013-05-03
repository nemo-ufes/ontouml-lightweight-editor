package br.ufes.inf.nemo.move.mvc.model;

import br.ufes.inf.nemo.antipattern.SSRAntiPattern;

/**
 * 
 * This class represents a SSR AntiPattern Model.
 * 
 * @author John Guerson
 */

public class SSRAntiPatternModel {

	/** SSR AntiPattern. */	
	private SSRAntiPattern ssr;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param ssr
	 */
	public SSRAntiPatternModel (SSRAntiPattern ssr)
	{
		this.ssr = ssr;
	}

	/**
	 * Get SSR AntiPattern.
	 * 
	 * @return
	 */
	public SSRAntiPattern getSSRAntiPattern()
	{
		return ssr;
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
