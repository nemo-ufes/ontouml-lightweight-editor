package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class SegmentVariabilityScenario extends ContentScenario {
		
	private Segment segment;
	private SegmentBehavior behavior;
	private SegmentVariability variability;
	
	public SegmentVariabilityScenario(OntoUMLParser parser, Segment seg) {
		super(parser);
		this.segment = seg;
	}
		
	public void setAsConstant(){
		behavior = SegmentBehavior.CONST;
		variability = null;
	}

	public void setAsIncremental(){
		behavior = SegmentBehavior.VAR;
		variability = SegmentVariability.INC;
	}
	
	public void setAsDecremental(){
		behavior = SegmentBehavior.VAR;
		variability = SegmentVariability.DEC;
	}
	
	public void setAsRandom(){
		behavior = SegmentBehavior.VAR;
		variability = SegmentVariability.RAND;
	}
	
	@Override
	public String getString() {
		return "Variability: "+segment.toString()+", Behavior: "+getBehavior()+", Variability: "+getVariability();
	}

	@Override
	public String getAlloy() {
		switch (behavior){
		case VAR:
			switch (variability){
				case INC: 
					return "all w1, w2:World | w2 in w1.next implies w1."+segment.getName()+" in w2."+segment.getName();
				case DEC:
					return "all w1, w2:World | w2 in w1.next implies w2."+segment.getName()+" in w1."+segment.getName();
				default:
					return "all w1,w2:World | w2!=w1 implies w1."+segment.getName()+"!=w2."+segment.getName();
			}
		case CONST:
			return "all w1,w2:World | w1."+segment.getName()+"=w2."+segment.getName();
			
		default:
			return "";
	}
	}

	@Override
	public String getScenarioName() {
		return "VariabilityScenario";
	}

	public Segment getSegment() {
		return segment;
	}
	
	public SegmentBehavior getBehavior() {
		return behavior;
	}
	
	public SegmentVariability getVariability() {
		return variability;
	}


	
	

	
	
	
	
}
