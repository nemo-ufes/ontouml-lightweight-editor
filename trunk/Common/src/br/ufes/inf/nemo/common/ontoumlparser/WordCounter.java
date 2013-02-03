package br.ufes.inf.nemo.common.ontoumlparser;

/**
 *	This class is used as a Word Counter. 
 */

public class WordCounter {
	
	private String name;
	
	private int counter;
	
	public WordCounter(String name, int counter) 
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
