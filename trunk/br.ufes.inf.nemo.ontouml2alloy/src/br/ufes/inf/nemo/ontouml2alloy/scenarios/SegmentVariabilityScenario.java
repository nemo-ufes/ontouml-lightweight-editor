package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import RefOntoUML.parser.OntoUMLNameHelper;
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
	public String getPhrase() {
		return "a story in which "+getComplement();		
	}

	private String getComplement() {
		String firstPart = "the "+getSegmentComplement();
		
		if(SegmentBehavior.CONST==behavior)
			return firstPart + " are always the same in all worlds";
		if(SegmentBehavior.VAR==behavior){
			switch (variability) {
			case DEC:
				return firstPart + " decrease or remain the same from a world to the next";
			case INC:
				return firstPart + " increase or remain the same from a world to the next";
			case RAND:
				return firstPart + " are always different from a world to another";
			default:
				break;
			}
		}
		
		return "";
	}
	
	//TODO: MERGE ALL SEGMENTS IF POSSIBLE
	private String getSegmentComplement() {
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
