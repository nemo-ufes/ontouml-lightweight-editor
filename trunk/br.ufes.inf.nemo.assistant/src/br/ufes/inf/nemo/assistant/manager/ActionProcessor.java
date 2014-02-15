package br.ufes.inf.nemo.assistant.manager;

public class ActionProcessor {
	private PatternOperator patternOperator;
	
	public ActionProcessor(PatternOperator operator) {
		patternOperator = operator;
	}
	
	/**
	 * Substance Sortals are classes that can provide identity principal
	 * They are: Kind, Quantity and Collective;
	 * */
	public boolean existSomeSubstanceSortal() {
		if(!patternOperator.getSetKind().isEmpty() || !patternOperator.getSetQuantity().isEmpty() || !patternOperator.getSetCollective().isEmpty()){
			return true;
		}
		return true;
	}
}
