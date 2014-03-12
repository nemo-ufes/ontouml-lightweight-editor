package br.ufes.inf.nemo.assistant.manager;

import br.ufes.inf.nemo.assistant.util.ActionEnum;

public class ActionProcessor{
	private PatternOperator patternOperator;
	
	public ActionProcessor(PatternOperator operator) {
		patternOperator = operator;
	}

	public boolean process(ActionEnum action){
		boolean returnAction = false;
		
		switch (action) {
		case  EXIST_SOME_SUBSTANCE_SORTAL:
			returnAction = existSomeSubstanceSortal();
			break;
		case EXIST_SOME_CATEGORY_OR_MIXIN:
			returnAction = existSomeCategoryOrMixin();
			break;
		case EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND:
			returnAction = existSomeSortalOrSubkind();
			break;
		}
		
		return returnAction;
	}
	
	
	private boolean existSomeSortalOrSubkind() {
		System.out.println("Not treated: existSomeSortalOrSubkind");
		return false;
	}

	/**
	 * Substance Sortals are classes that can provide identity principal
	 * They are: Kind, Quantity and Collective;
	 * */
	public boolean existSomeSubstanceSortal() {
		if(!patternOperator.getSetKind().isEmpty() || !patternOperator.getSetQuantity().isEmpty() || !patternOperator.getSetCollective().isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Verify the existence of Categories or Mixins
	 * */
	public boolean existSomeCategoryOrMixin(){
		if(!patternOperator.getSetCategory().isEmpty() || !patternOperator.getSetMixin().isEmpty()){
			return true;
		}
		return false;
	}
}
