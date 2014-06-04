package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import RefOntoUML.AggregationKind;
import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AggregationKindChecker extends Checker<Meronymic>{
	
	public AggregationKindChecker(OntoUMLParser parser) {
		super(parser);
	}
	
	@Override
	public boolean check(){
		
		if(errors==null)
			errors = new ArrayList<Meronymic>();
		else
			errors.clear();
		
		for (Meronymic meronymic : parser.getAllInstances(Meronymic.class)) {
			if(!isAggregationKindSet(meronymic))
				errors.add(meronymic);
		}
		
		if(getErrors().size()>0)
			return false;
		return true;
	}
		
	public boolean isAggregationKindSet(Meronymic m){
		//both ends are NONE
		if(bothEndsAreNone(m))
			return false;
		
		//both ends are SET
		if(bothEndsAreSet(m))
			return false;
		
		return true;
	}

	private boolean bothEndsAreSet(Meronymic m) {
		return m.getMemberEnd().get(0).getAggregation()!=AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()!=AggregationKind.NONE;
	}

	private boolean bothEndsAreNone(Meronymic m) {
		return m.getMemberEnd().get(0).getAggregation()==AggregationKind.NONE && m.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE;
	}

	@Override
	public String getErrorDescription(int i) {
		return OntoUMLNameHelper.getTypeAndName(getError(i), true, true);
	}

	@Override
	public String getErrorType(int i) {
		String description = "";
		
		if(bothEndsAreNone(errors.get(i)))
			description += "Aggregation NONE in both ends";
		else
			description += "Aggregation setted in both ends";
		
		return description;
	}

	
	
	
}
