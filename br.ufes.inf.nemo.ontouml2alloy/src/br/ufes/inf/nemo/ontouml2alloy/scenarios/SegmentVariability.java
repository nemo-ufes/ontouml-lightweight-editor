package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum SegmentVariability {
	RAND, //RAND = segment varies without a predefined behavior
	INC, //INC = segment grows
	DEC; //DEC = segment shrinks
	
	@Override
	public String toString() {
		switch(this) {
		case RAND: 
			return "Random";
		case INC: 
			return "Incremental";
		case DEC: 
			return "Decremental";
		default: 
			throw new IllegalArgumentException();
		}
	}
}