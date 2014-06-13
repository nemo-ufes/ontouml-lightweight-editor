package br.ufes.inf.nemo.meronymic_validation.checkers;

import javax.swing.JDialog;

import RefOntoUML.Generalization;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class GeneralizationError extends MeronymicError<Generalization>{

	public GeneralizationError(OntoUMLParser parser, Generalization g) {
		super(parser,g);
	}
	
	@Override
	public String getDescription(){
		return "Child: "+OntoUMLNameHelper.getTypeAndName(element.getSpecific(), true, true)+
				", Parent: "+OntoUMLNameHelper.getTypeAndName(element.getGeneral(), true, true);
	}

	@Override
	public String getType() {
		return "Forbidden Generalization";
	}

	@Override
	public JDialog createDialog() {
		return null;
	}

	@Override
	public Fix fix() {
		// TODO Auto-generated method stub
		return null;
	}

}
