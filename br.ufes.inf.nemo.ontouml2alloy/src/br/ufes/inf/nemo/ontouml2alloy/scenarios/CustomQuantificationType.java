package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum CustomQuantificationType {
	ALL, 
	NO, 
	SOME, 
	ONE, 
	COMPARISON;
	
	@Override
	public String toString() {
		switch(this) {
		case ALL: 
			return "For All";
		case NO: 
			return "No";
		case SOME: 
			return "Some";
		case ONE:
			return "One";
		case COMPARISON:
			return "Comparison";
		default: 
			throw new IllegalArgumentException();
		}
	}
}