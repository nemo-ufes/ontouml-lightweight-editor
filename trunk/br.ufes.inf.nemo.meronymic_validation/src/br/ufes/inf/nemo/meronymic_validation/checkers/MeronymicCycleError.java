package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MeronymicCycleError extends MeronymicError<ArrayList<Classifier>> {

	public MeronymicCycleError(OntoUMLParser parser, ArrayList<Classifier> cycle) {
		super(parser,new ArrayList<Classifier>(cycle));
	}
	
	@Override
	public String getDescription(){
		
		String result = "";
		for (Classifier c : element) {
			result += c.getName()+", ";
		}
		
		return result;
	}

	@Override
	public String getType() {
		return "Part-Whole Cycle";
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
