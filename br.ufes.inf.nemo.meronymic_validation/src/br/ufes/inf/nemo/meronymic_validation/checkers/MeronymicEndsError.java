package br.ufes.inf.nemo.meronymic_validation.checkers;

import javax.swing.JDialog;
import javax.swing.JFrame;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import RefOntoUML.Meronymic;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

public class MeronymicEndsError extends MeronymicError<Meronymic> {

	public MeronymicEndsError(OntoUMLParser parser, Meronymic m) {
		super(parser,m);
	}
	
	@Override
	public String getDescription() {
		return OntoUMLNameHelper.getCompleteName(element);
	}

	@Override
	public String getType() {
		if(element instanceof componentOf)
			return "Invalid ends' stereotypes for ComponentOf";
		if(element instanceof memberOf)
			return "Invalid ends' stereotypes for MemberOf";
		if(element instanceof subCollectionOf)
			return "Invalid ends' stereotypes for SubCollectionOf";
		if(element instanceof subQuantityOf)
			return "Invalid ends' stereotypes for SubQuantityOf";
		return "Invalid Meronymic";
	}

	@Override
	public JDialog createDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fix fix() {
		// TODO Auto-generated method stub
		return null;
	}

}
