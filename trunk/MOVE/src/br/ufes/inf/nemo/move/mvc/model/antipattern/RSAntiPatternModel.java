package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;

/**
 * @author John Guerson
 */

public class RSAntiPatternModel {
	
	private RSAntiPattern rs;
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

	public RSAntiPattern getRSAntiPattern()
	{
		return rs;
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
