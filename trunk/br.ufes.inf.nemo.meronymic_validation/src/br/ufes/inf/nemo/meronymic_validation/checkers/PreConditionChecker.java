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
		
		hierachyCycleChecker.check();
		if(hierachyCycleChecker.getCycles().size()>0)
			return false;
		
		generalizationChecker.check();
		if(generalizationChecker.getClassesWithError().size()>0)
			return false;
		
		identityChecker.check();
		if(identityChecker.getClassesWithError().size()>0)
			return false;
		
		aggregationChecker.check();
		if(aggregationChecker.getMeronymicWithError().size()>0)
			return false;

		meronymicEndsChecker.check();
		if(meronymicEndsChecker.getAllMeronymicWithError().size()>0)
			return false;
		
		meronymicCycleChecker.check();
		if(meronymicCycleChecker.getCycles().size()>0)
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
