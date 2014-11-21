package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class SizeVariabilityScenario extends QuantifiedScenario {
		
	enum BEHAVIOR {UNDEF, VAR, CONST} 	//VAR = segment varies the individuals, CONST = only the same individuals
	enum VARIABILITY {RAND, INC, DEC} 	//RAND = segment varies without a predefined behavior, INC = segment grows, DEC = segment shrinks
	
	Segment seg;
	Quantification q;
	
	BEHAVIOR beh;
	VARIABILITY var;
	
	//minimum or maximal number of individuals in the selected segment
	int minimum, maximum;
	
	public SizeVariabilityScenario (OntoUMLParser parser){
		super(parser);
	}
	
	private String getBehaviorExpression(){
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
	
	private String getSizeExpression(){
		
		String cardinality = "#"+q.getWorldVariable()+"."+seg.getName();
		String expr = cardinality+" >= "+minimum+" and "+cardinality+" <= "+maximum;
		
		return q.addQuantification(expr);
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlloy() {
		return getSizeExpression()+"\n\t"+getBehaviorExpression();
	}

	@Override
	public String getScenarioName() {
		return "PopulationSizeVariability";
	}


	
	

	
	
	
	
}
