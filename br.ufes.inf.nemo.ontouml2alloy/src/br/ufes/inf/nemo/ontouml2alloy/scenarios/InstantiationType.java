package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum InstantiationType {
	MULT, //MULT = multiple instantiation
	EXCL; //EXCL = exclusive instantiation

	@Override
	public String toString(){
		
		switch (this) {
		case MULT:
			return "Multiple Instantiation";
		case EXCL:
			return "Exclusive Instantiation";
		}
		
		return "";
	}
}