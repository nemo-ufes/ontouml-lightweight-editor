package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.CustomQuantification.Mode;
import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationScenario extends QuantifiedScenario {

	enum Change {DIF, EQUAL, DISJ, UNDEF}
	private Change change;
	private Association a;
	
	private boolean isReversed;
	
	private int n;
	private Comparator comp;
	private CustomQuantification worldQ, classQ;
	
	public AssociationScenario (OntoUMLParser parser, Association a, boolean isReversed){
		super(parser);
		this.a=a;
		this.isReversed = isReversed;
		
		worldQ = new CustomQuantification(Mode.ALL);
		worldQ.addQuantificationData("w", "World", 1);
		
		classQ = new CustomQuantification(Mode.ALL);
		worldQ.addQuantificationData("x", getDomain(), 1);
	}
	
	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public Association getA() {
		return a;
	}

	public void setA(Association a) {
		this.a = a;
		updateQuantificationData();
	}

	public boolean isReversed() {
		return isReversed;
	}

	public void setReversed(boolean isReverserd) {
		this.isReversed = isReverserd;
		updateQuantificationData();
		
	}

	private void updateQuantificationData() {
		classQ.getQuantificationData(0).setDomain(getDomain());
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Comparator getComp() {
		return comp;
	}

	public void setComp(Comparator comp) {
		this.comp = comp;
	}

	public void setWorldQuantification(Mode mode){
		worldQ.mode = mode;
	}
	
	public void setClassQuantification(Mode mode){
		classQ.mode = mode;
	}
	
	private String getSuffix() {
		if(change==Change.DISJ)
			return "]";
		return "";
	}

	private String getPrefix() {
		if(change==Change.DISJ)
			return "disj[";
		return "";
	}

	private String getOperator() {
		switch (change) {
		case DIF:
			return "!=";
		case DISJ:
			return ",";
		case EQUAL:
			return "=";
		case UNDEF:
		default:
			return "";
		}
	}

	private String getEnd() {
		if(isReversed)
			return parser.getAlias(a.getMemberEnd().get(0));
		return parser.getAlias(a.getMemberEnd().get(1));
	}

	private String getDomain() {
		if(isReversed)
			return parser.getAlias(a.getMemberEnd().get(1).getType());
		return parser.getAlias(a.getMemberEnd().get(0).getType());
	}

	public void setIsChangeable(){
		change=Change.DIF;
	}
	
	public void setIsConstant(){
		change=Change.EQUAL;
	}
	
	public void setIsUndef(){
		change=Change.UNDEF;
	}
	
	@Override
	public String getAlloy() {
		String expr = "";
		
		if(change==Change.DIF || change==Change.EQUAL){
			expr+=getChangeabilityExpression();
			if(n>=0)
				expr+="\n\t";
		}
		
		if(n>=0)
			expr+=getMultiplicityExpression();
		
		return expr;
	}

	private String getChangeabilityExpression() {
		
		if(change==Change.UNDEF)
			return "";
		
		String domain = getDomain();
		String end = getEnd();
		String op = getOperator();
		String prefix = getPrefix();
		String suffix = getSuffix();
		
		return "all w1,w2: World | w1!=w2 implies (all x:w1."+domain+" | x in w2."+domain+" implies "+prefix+"x."+end+"[w1]"+op+"x."+end+"[w2]"+suffix+")";
	}
	
	private String getMultiplicityExpression() {
		String expr;
		String worldVariable = worldQ.getVariable(0,1);
		String classVariable = classQ.getVariable(0,1);
		String leftExpr = "#"+classVariable+"."+getEnd()+"["+worldVariable+"]";
		String rightExpr = Integer.toString(n);
		
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
		return "AssociationScenario";
	}


	
	

	
	
	
	
}
