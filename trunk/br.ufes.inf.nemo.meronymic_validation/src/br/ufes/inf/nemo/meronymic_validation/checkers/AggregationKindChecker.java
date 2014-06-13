package br.ufes.inf.nemo.meronymic_validation.checkers;

import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

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
