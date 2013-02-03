package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.antipattern.IAAntiPattern;

/**
 * 
 * This class represents a IA AntiPattern Model.
 * 
 * @author John Guerson
 */

public class IAAntiPatternModel {

	/** IA AntiPattern. */
	private IAAntiPattern ia;
	
	/** Unique ID. */
	private Integer uniqueId = new Integer(0);
	
	/**
	 * Constructor.
	 * 
	 * @param ia
	 */
	public IAAntiPatternModel (IAAntiPattern ia) 
	{
		this.ia = ia;
	}

	/**
	 * Get IA AntiPattern.
	 * 
	 * @return
	 */
	public IAAntiPattern getIAAntiPattern()
	{
		return ia;
	}
	
	/**
	 * Increment ID.
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
