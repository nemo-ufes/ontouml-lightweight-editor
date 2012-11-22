package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.IAAntiPattern;

/**
 * @author John Guerson
 */

public class IAAntiPatternModel {

	private IAAntiPattern ia;
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

	public IAAntiPattern getIAAntiPattern()
	{
		return ia;
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
