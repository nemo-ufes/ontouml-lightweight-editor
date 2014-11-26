package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationDepth extends AssociationScenario {

	enum Limit {UPPER, LOWER}
	private Limit limit;
	
	private int depth;
	private CustomQuantification worldQ, classQ;
	
	public AssociationDepth (OntoUMLParser parser, Association a){
		super(parser,a,false);
		
		worldQ = CustomQuantification.createWorldQuantification(1);
		
		classQ = new CustomQuantification();
		classQ.setDisj(true);
		classQ.addQuantificationData("x", "w."+getDomain(), getLimit());
	
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int depth){
		this.depth = depth;
		updateQuantificationData();
	}
	
	public boolean isLowerBound(){
		return limit==Limit.LOWER;
	}
	
	public boolean isUpperBound(){
		return limit==Limit.UPPER;
	}
	
	public void setAsLowerBound(int depth){
		limit = Limit.LOWER;
		this.depth = depth;
		updateQuantificationData();
	}
	
	public void setAsUpperBound(int depth){
		limit = Limit.UPPER;
		this.depth = depth;
		updateQuantificationData();
	}
	
	public int getLimit(){
		if(limit==Limit.UPPER)
			return depth+1;
		return depth;
	}

	public void setA(Association a) {
		super.setA(a);
		updateQuantificationData();
	}

	private void updateQuantificationData() {
		classQ.getQuantificationData(0).setDomain(getDomain());
		classQ.getQuantificationData(0).setNumberOfVariables(getLimit());
	}

	public CustomQuantification getWorldQuantification(){
		return worldQ;
	}
	
	public CustomQuantification getClassQuantification(){
		return classQ;
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
			String var2 = classQ.getVariable(0,i+1);
			
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
		return "AssociationDepth";
	}


	
	

	
	
	
	
}
