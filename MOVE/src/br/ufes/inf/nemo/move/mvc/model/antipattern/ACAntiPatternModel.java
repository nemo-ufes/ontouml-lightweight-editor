package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.ACAntiPattern;

/**
 * @author John Guerson
 */

public class ACAntiPatternModel {
	
	private ACAntiPattern ac;
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

	public ACAntiPattern getACAntiPattern()
	{
		return ac;
	}
	
	public void IncrementId()
	{
		uniqueId++;
	}
	
	public Integer getId()
	{
		return uniqueId;
	}
	
	public void setId(Integer uniqueId)
	{
		this.uniqueId = uniqueId;
	}
}

