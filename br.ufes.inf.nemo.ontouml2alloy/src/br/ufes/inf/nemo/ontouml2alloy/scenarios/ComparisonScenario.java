package br.ufes.inf.nemo.ontouml2alloy.scenarios;

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
	public String getString() {
		return leftSeg.getName();
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
