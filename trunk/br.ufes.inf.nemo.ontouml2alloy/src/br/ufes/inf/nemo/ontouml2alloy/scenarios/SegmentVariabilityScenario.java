package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class SegmentVariabilityScenario extends ContentScenario {
		
	enum Behavior {VAR, CONST} 	//VAR = segment varies the individuals, CONST = only the same individuals
	enum Variability {RAND, INC, DEC} 	//RAND = segment varies without a predefined behavior, INC = segment grows, DEC = segment shrinks
	
	Segment seg;
	
	Behavior beh;
	Variability var;
	
	public SegmentVariabilityScenario(OntoUMLParser parser, Segment seg) {
		super(parser);
		this.seg = seg;
	}
		
	public void setAsConstant(){
		beh = Behavior.CONST;
		var = null;
	}

	public void setAsIncremental(){
		beh = Behavior.VAR;
		var = Variability.INC;
	}
	
	public void setAsDecremental(){
		beh = Behavior.VAR;
		var = Variability.DEC;
	}
	
	public void setAsRandom(){
		beh = Behavior.VAR;
		var = Variability.RAND;
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlloy() {
		switch (beh){
		case VAR:
			switch (var){
				case INC: 
					return "all w1, w2:World | w2 in w1.next implies w1."+seg.getName()+" in w2."+seg.getName();
				case DEC:
					return "all w1, w2:World | w2 in w1.next implies w2."+seg.getName()+" in w1."+seg.getName();
				default:
					return "all w1,w2:World | w2!=w1 implies w1."+seg.getName()+"!=w2."+seg.getName();
			}
		case CONST:
			return "all w1,w2:World | w1."+seg.getName()+"=w2."+seg.getName();
			
		default:
			return "";
	}
	}

	@Override
	public String getScenarioName() {
		return "VariabilityScenario";
	}


	
	

	
	
	
	
}
