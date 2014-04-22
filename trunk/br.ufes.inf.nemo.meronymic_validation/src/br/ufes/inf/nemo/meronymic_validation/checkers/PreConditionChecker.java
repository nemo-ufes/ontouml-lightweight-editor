package br.ufes.inf.nemo.meronymic_validation.checkers;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class PreConditionChecker {
	OntoUMLParser parser;
	
	HierarchyCycleChecker hierachyCycleChecker;
	GeneralizationChecker generalizationChecker;
	IdentityChecker identityChecker;
	AggregationKindChecker aggregationChecker;
	MeronymicEndsChecker meronymicEndsChecker;
	MeronymicCycleChecker meronymicCycleChecker;
	
	public PreConditionChecker(OntoUMLParser parser) {
		this.parser = parser;
		
		hierachyCycleChecker = new HierarchyCycleChecker(parser);
		generalizationChecker = new GeneralizationChecker(parser);
		identityChecker = new IdentityChecker(parser);
		aggregationChecker = new AggregationKindChecker(parser);
		meronymicEndsChecker = new MeronymicEndsChecker(parser);
		meronymicCycleChecker = new MeronymicCycleChecker(parser);
	}
	
	/**
	 * 
	 * @return returns true if the model meets the following conditions:
	 *	-> there are no cycles in the hierarchy
	 */
	public boolean checkPreConditions(){
		
		if(!hierachyCycleChecker.check())
			return false;
		
		if(!generalizationChecker.check())
			return false;
		
		if(!identityChecker.check())
			return false;
		
		if(!aggregationChecker.check())
			return false;

		if(!meronymicEndsChecker.check())
			return false;
		
		if(!meronymicCycleChecker.check())
			return false;
		
		return true;
	}
	
	public HierarchyCycleChecker getHierachyCycleChecker() {
		return hierachyCycleChecker;
	}

	public GeneralizationChecker getGeneralizationChecker() {
		return generalizationChecker;
	}

	public IdentityChecker getIdentityChecker() {
		return identityChecker;
	}

	public AggregationKindChecker getAggregationChecker() {
		return aggregationChecker;
	}

	public MeronymicEndsChecker getMeronymicEndsChecker() {
		return meronymicEndsChecker;
	}

	public MeronymicCycleChecker getMeronymicCycleChecker() {
		return meronymicCycleChecker;
	}

	
	
}
