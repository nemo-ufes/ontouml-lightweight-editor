package br.ufes.inf.nemo.meronymic_validation;

import javax.swing.JDialog;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.userinterface.FixDialog;

public abstract class Item {

	protected Fix fix;
	protected OutcomeFixer fixer;
	protected OntoUMLParser parser;
	
	public Item(OntoUMLParser parser) {
		this.parser = parser;
		
		fix = new Fix();
		fixer = new OutcomeFixer(parser.getModel());
	}
	
	public Fix getFix() {
		return fix;
	}
	
	public abstract FixDialog createDialog(JDialog parent);
	public abstract boolean hasAction();
	public abstract Fix fix();
}
