package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class SegmentSizeScenario extends QuantifiedScenario {
	
	private Segment segment;
	
	//minimum or maximal number of individuals in the selected segment
	private int minimum, maximum;
	
	public SegmentSizeScenario(OntoUMLParser parser){
		this(parser, new Segment(parser), new WorldQuantification(), 0, 1);
	}
	
	public SegmentSizeScenario(OntoUMLParser parser, Segment seg, WorldQuantification q, int minimum, int maximum) {
		super(parser, q);
		this.segment = seg;
		
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	
	
	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment seg) {
		this.segment = seg;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlloy() {
		if(minimum<=0 && maximum<0)
			return "";
		
		String cardinality = "#"+q.getWorldVariable()+"."+segment.getName();
		String expr = "";
		
		if(minimum>0){
			expr+=cardinality+" >= "+minimum;
			if(maximum>=0)
				expr+=" and ";
		}
		
		if(maximum>=0)
			expr+=cardinality+" <= "+maximum;
		
		return q.addQuantification(expr);
	}

	@Override
	public String getScenarioName() {
		return "SegmentSize";
	}

}
