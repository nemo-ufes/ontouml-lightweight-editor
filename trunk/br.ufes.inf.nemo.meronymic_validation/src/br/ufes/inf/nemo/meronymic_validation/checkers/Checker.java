package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class Checker <T>{

	protected OntoUMLParser parser;
	protected ArrayList<T> errors;
	
	public Checker(OntoUMLParser parser){
		this.parser = parser;
		errors = null;
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
	public abstract String getErrorDescription(int i);
	public abstract String getErrorType(int i);
}
