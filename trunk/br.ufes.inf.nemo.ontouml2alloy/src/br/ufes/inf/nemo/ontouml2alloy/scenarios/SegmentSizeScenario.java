package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;

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
	public String getPhrase() {
		return "at least "+minimum+" and at most "+maximum+" "+getSegmentPhrase()+" "+getQuantificationPhrase();
	}
	
	private String getSegmentPhrase(){
		switch(segment.getType()){
		case POPULATION:
			return "individuals";
		case OBJECT:
			return "objects";
		case PROPERTY:
			return "tropes";
		case CLASS:
			return "instances of "+OntoUMLNameHelper.getTypeAndName(segment.getClassifier(), true, true);
		case ASSOCIATION:
			return "instances of the relation "+OntoUMLNameHelper.getTypeAndName(segment.getClassifier(), true, true);
		case STEREOTYPE:
			String x = "";
			if(segment.getStereotype() instanceof ClassStereotype)
				x = "classes";
			else
				x = "associations";
			return "individuals in the union of the extensions of all "+x+" stereotyped as "+segment.getStereotype();
		default:
			return "UNNAMED_SEGMENT";
		}
	}
	
	private String getQuantificationPhrase(){
		switch (q.getType()) {
		case ATLEAST:
			return "in at least "+q.getValue()+" world(s) of the story";
		case ATMOST:
			return "in at most "+q.getValue()+" world(s) of the story";
		case EVERY:
			return "in every world of the story";
		case EXACTLY:
			return "in exactly "+q.getValue()+" world(s) of the story";
		case NO:
			return "in no world of the story";
		case SOME:
			return "in some world of the story";
		case STORY:
			return "throughout the worlds of the story";
		default:
			break;
		}
		
		return "";
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
