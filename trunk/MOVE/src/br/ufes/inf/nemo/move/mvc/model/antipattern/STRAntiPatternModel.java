package br.ufes.inf.nemo.move.mvc.model.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.STRAntiPattern;

/**
 * @author John Guerson
 */

public class STRAntiPatternModel {

	private STRAntiPattern str;
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

	public STRAntiPattern getSTRAntiPattern()
	{
		return str;
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
