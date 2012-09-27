package br.ufes.inf.nemo.ontouml2alloy.mapper;

/**
 *	This class is used as a name counter for names in the model. 
 *
 * 	@author John Guerson
 */

public class NamesCounter {
	
	private String name;
	
	private int counter;
	
	public NamesCounter(String name, int counter) 
	{
		super();
		this.name = name;
		this.counter = counter;
	}
	
	public String getWord() 
	{
		return name;
	}
	
	public void setWord(String name) 
	{
		this.name = name;
	}
	
	public int getCounter() 
	{
		return counter;
	}
	
	public void setCounter(int counter) 
	{
		this.counter = counter;
	}		
}
