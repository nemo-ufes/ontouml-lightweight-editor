package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class SegmentSizeScenario extends QuantifiedScenario {
	
	Segment seg;
	
	//minimum or maximal number of individuals in the selected segment
	int minimum, maximum;
	
	public SegmentSizeScenario(OntoUMLParser parser, Segment seg, SimpleQuantification q, int minimum, int maximum) {
		super(parser, q);
		this.seg = seg;
		
		this.minimum = minimum;
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
		
		String cardinality = "#"+q.getWorldVariable()+"."+seg.getName();
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
