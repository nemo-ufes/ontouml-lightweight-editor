package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum InstantiationType {
	MULT, //MULT = multiple instantiation
	FORB_MULT,
	EXCL; //EXCL = exclusive instantiation

	@Override
	public String toString(){
		
		switch (this) {
		case MULT:
			return "Enforce Multiple Instantiation";
		case FORB_MULT:
			return "Forbid Multiple Instantiation";
		case EXCL:
			return "Enforce Exclusive Instantiation";
		}
		
		return "";
	}
}