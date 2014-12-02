package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class ComparisonScenario extends QuantifiedScenario {

	Segment leftSeg, rightSeg;
	
	ComparisonType comparisonType;
	BinaryOperator sizeOperator;
	ExtensionOperator extensionOperator;
	
	public ComparisonScenario (OntoUMLParser parser, Segment leftSeg, Segment rightSeg, WorldQuantification q){
		super(parser,q);
		this.leftSeg = leftSeg;
		this.rightSeg = rightSeg;
	}
	
	public ComparisonScenario(OntoUMLParser parser) {
		super(parser,new WorldQuantification());
		this.leftSeg = new Segment(parser);
		this.rightSeg = new Segment(parser);
	}

	//TODO: DRAW VENN DIAGRAMS TO INCLUDE IN THE HELP SECTION
	private String getExtensionExpression(){
		String leftExpr = q.getWorldVariable()+"."+leftSeg.getName();
		String rightExpr = q.getWorldVariable()+"."+rightSeg.getName();
		String expr = extensionOperator.getExpression(leftExpr, rightExpr);
		return q.addQuantification(expr);
	}
	
	private String getSizeExpression(){
		String leftExpr = "#("+q.getWorldVariable()+"."+leftSeg.getName()+")";
		String rightExpr =  "#("+q.getWorldVariable()+"."+rightSeg.getName()+")";
		String expr = sizeOperator.getExpression(leftExpr, rightExpr);
		return q.addQuantification(expr);
	}

	
	public void setSizeComparison(BinaryOperator operator){
		comparisonType = ComparisonType.SIZE;
		sizeOperator = operator;
	}
	
	public void setExtesionComparison(ExtensionOperator operator){
		comparisonType = ComparisonType.EXT;
		extensionOperator = operator;
	}
	
	@Override
	public String getPhrase() {
		return "a story in which the "+getComparisonTypePhrase()+" "+getSegmentPhrase(leftSeg)+" is "+getOperatorPhrase()+" the "+getComparisonTypePhrase()+" "+getSegmentPhrase(rightSeg)+" "+getQuantificationPhrase();

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
	
	private String getOperatorPhrase() {
		if(isSizeComparison()){
			switch (sizeOperator) {
			case EQUAL:
				return "equal to";
			case DIF:
				return "different from";
			case GREATER:
				return "greater than";
			case GREATER_EQ:
				return "greater than or equal to";
			case LESSER:
				return "lower than";
			case LESSER_EQ:
				return "lower than or equal to";
			default:
				return "";
			}
		}
		else{
			switch (extensionOperator) {
			case DIF:
				return "different from";
			case DISJ:
				return "is disjoint from";
			case EQUAL:
				return "equal to";
			case INC:
				return "includes";
			case INTER:
				return "has an intersection with";
			case NOT_INC:
				return "does not include";
			default:
				return "";
			}
		}
	}

	private String getSegmentPhrase(Segment segment){
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
	
	private String getComparisonTypePhrase() {
		if(isSizeComparison())
			return "number of";
		return "set of";
	}

	@Override
	public String getAlloy() {
		if(comparisonType==ComparisonType.EXT)
			return getExtensionExpression();
		if(comparisonType==ComparisonType.SIZE)
			return getSizeExpression();
		
		return "UNSETTED VALUES";
	}

	@Override
	public String getScenarioName() {
		return "PopulationComparison";
	}

	public Segment getLeftSegment() {
		return leftSeg;
	}

	public Segment getRightSegment() {
		return rightSeg;
	}

	public ComparisonType getComparisonType() {
		return comparisonType;
	}

	public boolean isSizeComparison() {
		return comparisonType==ComparisonType.SIZE;
	}

	public boolean isExtensionComparison() {
		return comparisonType==ComparisonType.EXT;
	}
	
	

	
	
	
	
}
