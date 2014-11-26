package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomQuantification {
	
	enum Mode {ALL, NO, SOME, ONE, COMPARISON};
	
	private Mode mode;
	private ArrayList<QuantificationData> qd;
	
	private Comparator comp;
	private int n;
	private boolean isDisj;
	
	public CustomQuantification() {
		qd = new ArrayList<QuantificationData>();
		isDisj = false;
		n = 1;
	}
	
	public boolean isDisj() {
		return isDisj;
	}
	
	public void setDisj(boolean isDisj) {
		this.isDisj = isDisj;
	}

	public void setAsAll() {
		mode=Mode.ALL;
	}
	
	public void setAsNo() {
		mode=Mode.NO;
	}
	
	public void setAsSome() {
		mode=Mode.SOME;
	}
	
	public void setAsComparison(Comparator comp, int n){
		mode=Mode.COMPARISON;
		this.n = n;
		this.comp = comp;
	}
	
	public void setAsOne(){
		mode=Mode.ONE;
	}
	
	public boolean isAll() {
		return mode==Mode.ALL;
	}
	
	public boolean isNo() {
		return mode==Mode.NO;
	}
	
	public boolean isSome() {
		return mode==Mode.SOME;
	}
	
	public boolean isComparison(){
		return mode==Mode.COMPARISON;
	}
	
	public boolean isOne(){
		return mode==Mode.ONE;
	}

	public String addQuantification(String expression){
		String pre = "";
		
		if(isDisj)
			pre="disj ";
		
		if (mode==Mode.ALL)
			return "all "+pre+getAllDeclarations()+" | "+expression;
		if (mode==Mode.NO)
			return "no "+pre+getAllDeclarations()+" | "+expression;
		if (mode==Mode.ONE)
			return "one "+pre+getAllDeclarations()+" | "+expression;
		if (mode==Mode.SOME)
			return "some "+pre+getAllDeclarations()+" | "+expression;
		
		if (mode==Mode.COMPARISON){
			String s = "#{ "+pre+getAllDeclarations()+" | "+expression+"}";
			
			return comp.getExpression(s, Integer.toString(n));
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
	
}
