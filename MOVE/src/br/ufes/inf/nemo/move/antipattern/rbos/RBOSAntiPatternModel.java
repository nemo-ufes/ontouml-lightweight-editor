package br.ufes.inf.nemo.move.antipattern.rbos;

import br.ufes.inf.nemo.ontouml.antipattern.RBOSAntiPattern;

/**
 * @author John Guerson
 */

public class RBOSAntiPatternModel {

	private RBOSAntiPattern rbos;
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

	public RBOSAntiPattern getRBOSAntiPattern()
	{
		return rbos;
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
