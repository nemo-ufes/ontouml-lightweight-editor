package br.ufes.inf.nemo.meronymic_validation.checkers;

import javax.swing.JDialog;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class MeronymicError<T> {
	
	T element;
	Fix fix;
	OutcomeFixer fixer;
	OntoUMLParser parser;
	
	public MeronymicError(OntoUMLParser parser, T element){
		this.element = element;
		this.parser = parser;
		
		fix = new Fix();
		fixer = new OutcomeFixer(parser.getModel());
	}
	
	public T getElement(){
		return element;
	}
	
	public abstract String getType();
	public abstract String getDescription();
	public abstract JDialog createDialog(JDialog parent);
	public abstract Fix fix();
}
