package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;

import RefOntoUML.parser.OntoUMLParser;

public abstract class Checker<T extends MeronymicError<?>>{

	protected OntoUMLParser parser;
	protected ArrayList<T> errors;
	
	public Checker(OntoUMLParser parser){
		this.parser = parser;
		this.errors = new ArrayList<T>();
	}
	
	public ArrayList<T> getErrors(){
		if(errors == null)
			check();
		return errors;
	}
	public T getError(int i){
		if(errors == null)
			check();
		
		return errors.get(i);
	}
	
	public abstract boolean check();
	public abstract String checkerName();
}
