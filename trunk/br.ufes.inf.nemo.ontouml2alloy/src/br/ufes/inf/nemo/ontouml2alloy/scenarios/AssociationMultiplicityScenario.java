package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationMultiplicityScenario extends AssociationScenario {
	
	private CustomQuantification worldQ, classQ;
	
	private int value;
	private Comparator comp;
	
	public AssociationMultiplicityScenario (OntoUMLParser parser, Association a, boolean isReversed){
		super(parser, a, isReversed);
		
		worldQ = CustomQuantification.createWorldQuantification(1);
		
		classQ = new CustomQuantification();
		classQ.addQuantificationData("x", "w."+getDomain(), 1);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		
	}

	public Comparator getComp() {
		return comp;
	}

	public void setData(Comparator comp, int value) {
		this.comp = comp;
		this.value = value;
	}
	
	@Override
	public void setA(Association a) {
		super.setA(a);
		updateQuantificationData();
	}

	@Override
	public void reverse(boolean reverse) {
		super.reverse(reverse);
		updateQuantificationData();
	}
	
	public void updateQuantificationData() {
		classQ.getQuantificationData(0).setDomain(getDomain());
	}
	
	public CustomQuantification getWorldQuantification(){
		return worldQ;
	}
	
	public CustomQuantification getClassQuantification(){
		return classQ;
	}
	
	@Override
	public String getAlloy() {
		String expr;
		String worldVariable = worldQ.getVariable(0,1);
		String classVariable = classQ.getVariable(0,1);
		String leftExpr = "#"+classVariable+"."+getEnd()+"["+worldVariable+"]";
		String rightExpr = Integer.toString(value);
		
		expr = comp.getExpression(leftExpr, rightExpr);
		expr = classQ.addQuantification(expr);
		expr = worldQ.addQuantification(expr);
		
		return expr;
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getScenarioName() {
		return "AssociationMultiplicity";
	}


	
	

	
	
	
	
}
