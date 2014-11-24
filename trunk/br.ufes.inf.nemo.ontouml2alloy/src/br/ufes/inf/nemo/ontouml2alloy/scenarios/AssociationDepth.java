package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.CustomQuantification.Mode;
import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationDepth extends QuantifiedScenario {

	enum Limit {UPPER, LOWER}
	private Limit limit;
	private Association a;
	
	private int depth;
	private CustomQuantification worldQ, classQ;
	
	public AssociationDepth (OntoUMLParser parser, Association a, int depth, Limit limit){
		super(parser);
		
		this.a=a;
		this.depth = depth;
		this.limit = limit;
		
		worldQ = new CustomQuantification(Mode.ALL);
		worldQ.addQuantificationData("w", "World", 1);
		
		classQ = new CustomQuantification(Mode.ALL, true);
		worldQ.addQuantificationData("x", getDomain(), getLimit());
	}
	
	public int getLimit(){
		if(limit==Limit.UPPER)
			return depth+1;
		return depth;
	}
	

	public Association getA() {
		return a;
	}

	public void setA(Association a) {
		this.a = a;
		updateQuantificationData();
	}

	private void updateQuantificationData() {
		classQ.getQuantificationData(0).setDomain(getDomain());
	}

	public void setWorldQuantification(Mode mode){
		worldQ.mode = mode;
	}
	
	public void setClassQuantification(Mode mode){
		classQ.mode = mode;
	}
	
	private String getEnd() {
		return parser.getAlias(a.getMemberEnd().get(1));
	}

	private String getDomain() {
		return parser.getAlias(a.getMemberEnd().get(0).getType());
	}
	
	@Override
	public String getAlloy() {
		String expr = getRestriction();
		expr = classQ.addQuantification(expr);
		expr = worldQ.addQuantification(expr);
		
		return expr;
	}

	private String getRestriction() {
		String expr="";
		String worldVariable = worldQ.getVariable(0,1);
		
		for (int i = 1; i < getLimit(); i++) {
			String var1 = classQ.getVariable(0,i);
			String var2 = classQ.getVariable(i,i+1);
			
			expr += var2+" in "+getEnd()+"["+var1+","+worldVariable+"]";
			
			if(i<getLimit()-1)
				expr+=" and ";
		}
		
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
