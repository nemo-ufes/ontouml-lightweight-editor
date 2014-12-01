package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum SegmentBehavior {
	VAR, //VAR = segment varies the individuals
	CONST; //CONST = only the same individuals
	
	@Override
	public String toString() {
		switch(this) {
		case VAR: 
			return "Variable";
		case CONST: 
			return "Constant";
		default: 
			throw new IllegalArgumentException();
		}
	}
} 