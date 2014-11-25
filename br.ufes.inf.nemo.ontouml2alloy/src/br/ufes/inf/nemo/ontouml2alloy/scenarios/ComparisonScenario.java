package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class ComparisonScenario extends QuantifiedScenario {
	
	Segment leftSeg, rightSeg;
	Comparator comparator;
	
	enum ComparisonType {SIZE, EXT}
	ComparisonType comparisonType;
	
	enum ExtensionOperator {INC, NOT_INC, DISJ, INTER, EQUAL, DIF}
	ExtensionOperator extensionOperator;
	
	public ComparisonScenario (OntoUMLParser parser, Segment leftSeg, Segment rightSeg, SimpleQuantification q){
		super(parser,q);
		this.leftSeg = leftSeg;
		this.rightSeg = rightSeg;
	}
	
	//TODO: DRAW VENN DIAGRAMS TO INCLUDE IN THE HELP SECTION
	private String getExtensionExpression(){
		String leftExpr = q.getWorldVariable()+"."+leftSeg.getName();
		String rightExpr = q.getWorldVariable()+"."+rightSeg.getName();
		String expr = "";
		
		switch (extensionOperator) {
			case EQUAL:
				expr = leftExpr+" = "+rightExpr;
				break;
			case DIF:
				expr = leftExpr+" != "+rightExpr;
				break;
			case INC:
				expr = leftExpr+" in "+rightExpr;
				break; 
			case NOT_INC:
				expr = leftExpr+" not in "+rightExpr;
				break;
			case DISJ:
				expr = "disj ["+leftExpr+", "+rightExpr+"]";
				break;
			case INTER:
				expr = "some ("+leftExpr+" & "+rightExpr+")";
				break;
		}
		return q.addQuantification(expr);
	}
	
	private String getSizeExpression(){
		String leftExpr = "#("+q.getWorldVariable()+"."+leftSeg.getName()+")";
		String rightExpr =  "#("+q.getWorldVariable()+"."+rightSeg.getName()+")";
		String expr = comparator.getExpression(leftExpr, rightExpr);
		return q.addQuantification(expr);
	}

	
	public void setCompareSize(Comparator comparator){
		comparisonType = ComparisonType.SIZE;
		this.comparator = comparator;
	}
	
	public void setCompareExtension(ExtensionOperator operator){
		comparisonType = ComparisonType.EXT;
		extensionOperator = operator;
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
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


	
	

	
	
	
	
}
