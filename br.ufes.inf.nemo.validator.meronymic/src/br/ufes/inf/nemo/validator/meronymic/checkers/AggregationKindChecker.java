package br.ufes.inf.nemo.validator.meronymic.checkers;

import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLParser;

public class AggregationKindChecker extends Checker<AggregationKindError>{
	
	public AggregationKindChecker(OntoUMLParser parser) {
		super(parser);
	}
	
	@Override
	public boolean check(){
		
		errors.clear();
		
		for (Meronymic meronymic : parser.getAllInstances(Meronymic.class)) {
			if(!AggregationKindError.isAggregationKindSet(meronymic))
				errors.add(new AggregationKindError(parser, meronymic));
		}
		
		if(getErrors().size()>0)
			return false;
		return true;
	}

	@Override
	public String checkerName() {
		return "Aggregation Kind";
	}
		
	

	
	
	
}
