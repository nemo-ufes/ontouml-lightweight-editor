package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public class ComparisonScenario extends QuantifiedScenario {
	
	Segment leftSeg, rightSeg;
	Comparator comp;
	
	enum VAR {SIZE, EXT}
	VAR comparisonType;
	
	enum MODE_EXT {INC, NOT_INC, DISJ, INTER, EQUAL, DIF, }
	MODE_EXT modeExt;
	
	public ComparisonScenario (OntoUMLParser parser){
		super(parser);
	}
	
	//TODO: DRAW VENN DIAGRAMS TO INCLUDE IN THE HELP SECTION
	private String getExtensionExpression(){
		String leftExpr = q.getWorldVariable()+"."+leftSeg.getName();
		String rightExpr = q.getWorldVariable()+"."+rightSeg.getName();
		String expr = "";
		
		switch (modeExt) {
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
				expr = "["+leftExpr+", "+rightExpr+"]";
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
		String expr = comp.getExpression(leftExpr, rightExpr);
		return q.addQuantification(expr);
	}

	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getAlloy() {
		if(comparisonType==VAR.EXT)
			return getExtensionExpression();
		if(comparisonType==VAR.SIZE)
			return getSizeExpression();
		
		return "UNSETTED VALUES";
	}

	@Override
	public String getScenarioName() {
		return "PopulationComparison";
	}


	
	

	
	
	
	
}
