package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;

import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;


public abstract class Antipattern<T extends AntipatternOccurrence> {
	
	protected OntoUMLParser parser;
	protected ArrayList<T> occurrence;
	
	/*basic constructors*/
	public Antipattern (OntoUMLParser parser) throws NullPointerException{
		if (parser == null)
			throw new NullPointerException("Antipattern.java: Null OntoUML parser!");
		
		this.parser = parser;
		occurrence = new ArrayList<T>();
	}
	
	public Antipattern (Package pack) throws NullPointerException{
		this(new OntoUMLParser(pack));
	}
	
	public static AntipatternInfo getAntipatternInfo(){
		throw new IllegalStateException("Antipattern info hasn't been set up in the subclass");
	}
	
	public abstract AntipatternInfo info();
	
	public abstract ArrayList<T> identify();
	
	public ArrayList<T> getOccurrences(){
		return occurrence;
	}
	
	public OntoUMLParser getParser(){
		return parser;
	}

	
}
