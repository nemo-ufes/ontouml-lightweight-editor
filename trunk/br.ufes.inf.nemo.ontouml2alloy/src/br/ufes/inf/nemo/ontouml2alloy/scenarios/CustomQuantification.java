package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomQuantification {
	
	enum Mode {ALL, NO, SOME, ONE, COMPARISON};
	
	Mode mode;
	ArrayList<QuantificationData> qd;
	
	Comparator comp;
	int n;
	boolean isDisj;
	
	public CustomQuantification(Mode mode) {
		super();
		
		qd = new ArrayList<QuantificationData>();
		this.mode = mode;
		isDisj = false;
	}
	
	public CustomQuantification(Mode mode, boolean isDisj) {
		super();
		
		this.qd = new ArrayList<QuantificationData>();
		this.mode = mode;
		this.isDisj = isDisj;
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
	
	
}
