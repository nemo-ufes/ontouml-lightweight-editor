package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationMultiplicityScenario extends AssociationScenario {
	
	private CustomQuantification worldQ, classQ;
	
	private int value;
	private BinaryOperator op;
	
	public AssociationMultiplicityScenario (OntoUMLParser parser, Association a, boolean isReversed){
		super(parser, a, isReversed);
		
		worldQ = CustomQuantification.createWorldQuantification(1);
		
		classQ = new CustomQuantification();
		classQ.addQuantificationData("x", "w."+getDomain(), 1);
	}
	
	public AssociationMultiplicityScenario(OntoUMLParser parser) {
		this(parser, null, false);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public BinaryOperator getOperator() {
		return op;
	}
	
	public void setOperator(BinaryOperator op) {
		this.op = op;
	}

	public void setData(BinaryOperator op, int value) {
		this.op = op;
		this.value = value;
	}
	
	@Override
	public void setAssociation(Association a) {
		super.setAssociation(a);
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
		
		expr = op.getExpression(leftExpr, rightExpr);
		expr = classQ.addQuantification(expr);
		expr = worldQ.addQuantification(expr);
		
		return expr;
	}
	
	@Override
	public String getPhrase() {
		return "a story in which "+classQuantificationPhrase()+" of "+OntoUMLNameHelper.getTypeAndName(getSource(), true, true)+
				" is/are connected to "+getOperatorPhrase(op)+" "+value+" instances of "+OntoUMLNameHelper.getTypeAndName(getTarget(), true, true)+
				", through association "+OntoUMLNameHelper.getTypeAndName(getAssociation(), true, true)+", in "+worldQuantificationPhrase();
	}
	
	private String worldQuantificationPhrase(){
		switch (worldQ.getType()) {
		case COMPARISON:
			return getOperatorPhrase(worldQ.getOperator())+" "+worldQ.getValue()+" world(s) of the story";
		case ALL:
			return "every world of the story";
		case NO:
			return "no world of the story";
		case SOME:
			return "some world of the story";
		case ONE:
			return "a single world of the story";
		default:
			break;
		}
		
		return "";
	}
	
	private String classQuantificationPhrase() {
		switch (classQ.getType()) {
		case COMPARISON:
			return getOperatorPhrase(classQ.getOperator())+" "+classQ.getValue()+" instances";
		case ALL:
			return "all instances";
		case NO:
			return "no instance";
		case SOME:
			return "at least one instance";
		case ONE:
			return "exactly one instance";
		default:
			break;
		}
		
		return "";
	}

	private String getOperatorPhrase(BinaryOperator op) {
		switch (op) {
		case EQUAL:
			return "exactly";
		case DIF:
			return "a number different from";
		case GREATER:
			return "more than";
		case GREATER_EQ:
			return "at least";
		case LESSER:
			return "less than";
		case LESSER_EQ:
			return "at most";
		default:
			return "";
		}
			
	}
	
	@Override
	public String getScenarioName() {
		return "AssociationMultiplicity";
	}


	
	

	
	
	
	
}
