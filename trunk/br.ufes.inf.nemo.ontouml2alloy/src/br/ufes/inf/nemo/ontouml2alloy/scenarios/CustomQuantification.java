package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomQuantification {
	
	private CustomQuantificationType type;
	private ArrayList<QuantificationData> qd;
	
	private BinaryOperator op;
	private int value;
	private boolean isDisj;
	
	public CustomQuantification() {
		qd = new ArrayList<QuantificationData>();
		isDisj = false;
		value = 1;
	}
	
	public CustomQuantificationType getType() {
		return type;
	}
	
	public boolean isDisj() {
		return isDisj;
	}
	
	public void setDisj(boolean isDisj) {
		this.isDisj = isDisj;
	}

	public void setAsAll() {
		type=CustomQuantificationType.ALL;
	}
	
	public void setAsNo() {
		type=CustomQuantificationType.NO;
	}
	
	public void setAsSome() {
		type=CustomQuantificationType.SOME;
	}
	
	public void setAsComparison(BinaryOperator op, int n){
		type=CustomQuantificationType.COMPARISON;
		this.value = n;
		this.op = op;
	}
	
	public BinaryOperator getOperator(){
		return op;
	}
	
	public void setAsOne(){
		type=CustomQuantificationType.ONE;
	}
	
	public boolean isAll() {
		return type==CustomQuantificationType.ALL;
	}
	
	public boolean isNo() {
		return type==CustomQuantificationType.NO;
	}
	
	public boolean isSome() {
		return type==CustomQuantificationType.SOME;
	}
	
	public boolean isComparison(){
		return type==CustomQuantificationType.COMPARISON;
	}
	
	public boolean isOne(){
		return type==CustomQuantificationType.ONE;
	}

	public String addQuantification(String expression){
		String pre = "";
		
		if(isDisj)
			pre="disj ";
		
		if (type==CustomQuantificationType.ALL)
			return "all "+pre+getAllDeclarations()+" | "+expression;
		if (type==CustomQuantificationType.NO)
			return "no "+pre+getAllDeclarations()+" | "+expression;
		if (type==CustomQuantificationType.ONE)
			return "one "+pre+getAllDeclarations()+" | "+expression;
		if (type==CustomQuantificationType.SOME)
			return "some "+pre+getAllDeclarations()+" | "+expression;
		
		if (type==CustomQuantificationType.COMPARISON){
			String s = "#{ "+pre+getAllDeclarations()+" | "+expression+"}";
			
			return op.getExpression(s, Integer.toString(value));
		}

		return "";
	}
	
	private String getAllDeclarations(){
		String declaration = "";
		
		Iterator<QuantificationData> iterator = qd.iterator();
		
		while(iterator.hasNext()){
			declaration += iterator.next().getDeclaration();
			if(iterator.hasNext())
				declaration += ", ";
		}
		
		return declaration;
	}
	
	public void addQuantificationData(String variablePrefix, String domain, int numberOfVariables){
		qd.add(new QuantificationData(variablePrefix, domain, numberOfVariables));
	}

	public String getVariable(int i, int j) {
		return qd.get(i).getVariable(j);
	}

	public QuantificationData getQuantificationData(int i) {
		return qd.get(i);
	}
	
	public static CustomQuantification createWorldQuantification(int numberOfVariables){
		CustomQuantification cq = new CustomQuantification();
		cq.addQuantificationData("w", "World", numberOfVariables);
		return cq;
	}
	
	public int getValue(){
		return value;
	}

	
	
}
