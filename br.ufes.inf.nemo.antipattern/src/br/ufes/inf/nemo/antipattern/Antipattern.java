package br.ufes.inf.nemo.antipattern;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import RefOntoUML.Package;
import java.util.ArrayList;

public abstract class Antipattern {
	
	protected OntoUMLParser parser;
		
	/*basic constructors*/
	public Antipattern (OntoUMLParser parser) throws NullPointerException{
		if (parser == null)
			throw new NullPointerException("Antipattern.java: Null OntoUML parser!");
		
		this.parser = parser;
	}
	
	public Antipattern (Package pack) throws NullPointerException{
		this(new OntoUMLParser(pack));
	}
	
	/*basic methods*/
			
	public static AntipatternInfo getAntipatternInfo(){
		throw new IllegalStateException("Antipattern info hasn't been set up in the subclass");
	}
		
	public abstract <T extends Antipattern> ArrayList<T> identify();
	public abstract <T extends Antipattern> ArrayList<T> getOccurrences();
}
