package br.ufes.inf.nemo.meronymic_validation.checkers;

import javax.swing.JDialog;
import javax.swing.JFrame;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import RefOntoUML.AggregationKind;
import RefOntoUML.Meronymic;

public class AggregationKindError extends MeronymicError<Meronymic> {

	public AggregationKindError(OntoUMLParser parser, Meronymic meronymic) {
		super(parser,meronymic);
	}

	public static boolean isAggregationKindSet(Meronymic m){
		//both ends are NONE
		if(bothEndsAreNone(m))
			return false;
		
		//both ends are SET
		if(bothEndsAreSet(m))
			return false;
		
		return true;
	}

	static boolean bothEndsAreSet(Meronymic m) {
		return m.getMemberEnd().get(0).getAggregation()!=AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()!=AggregationKind.NONE;
	}

	static boolean bothEndsAreNone(Meronymic m) {
		return m.getMemberEnd().get(0).getAggregation()==AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE;
	}

	@Override
	public String getDescription() {
		return OntoUMLNameHelper.getCompleteName(element);
	}

	@Override
	public String getType() {
		String description = "";
		
		if(bothEndsAreNone(element))
			description += "Aggregation NONE in both ends";
		else
			description += "Aggregation SETTED in both ends";
		
		return description;
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
