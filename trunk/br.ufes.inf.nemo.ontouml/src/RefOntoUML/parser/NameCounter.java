package RefOntoUML.parser;

public class NameCounter {
	
	private String name;	
	private int counter;
	
	public NameCounter(String name, int counter) 
	{
		super();
		this.name = name;
		this.counter = counter;
	}
	
	public String getWord() { return name; }	
	public void setWord(String name) { this.name = name; }	
	public int getCounter(){ return counter; }	
	public void setCounter(int counter) { this.counter = counter; }		
}
