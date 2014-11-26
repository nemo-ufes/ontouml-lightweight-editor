package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;

public class InstantiationScenario extends ContentScenario {
		
	enum Type {MULT, EXCL} 	//MULT = multiple instantiation, EXCL = exclusive instantiation
	Type type;
	
	CustomQuantification worldQ, classifierQ;
	
	ArrayList<Classifier> classifiers;
	
	public InstantiationScenario (OntoUMLParser parser){
		super(parser);
		
		classifiers = new ArrayList<Classifier>();
		worldQ = CustomQuantification.createWorldQuantification(1);
		classifierQ = new CustomQuantification();
		classifierQ.addQuantificationData("x", "w.exists", 1);
	}
	
	public void setAsMultiple(ArrayList<Classifier> classifiers){
		type = Type.MULT;
		updateClassifierList(classifiers);
	}
	
	public void setAsExclusive(ArrayList<Classifier> classifiers){
		type = Type.EXCL;
		updateClassifierList(classifiers);
	}

	private void updateClassifierList(ArrayList<Classifier> classifiers) {
		this.classifiers.clear();
		this.classifiers.addAll(classifiers);
	}

	public CustomQuantification getWorldQuantification(){
		return worldQ;
	}
	
	public CustomQuantification getClassifierQuantification(){
		return classifierQ;
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlloy() {
		String expr = "";
		
		
		if(type==Type.MULT){ 
			if(classifierQ.isAll())
				expr = "("+buildListExpression(true)+") implies ("+buildListExpression(false)+")";
			else
				expr = buildListExpression(false);
		}
		
		else if(type==Type.EXCL){
			String connector = "";
			
			if(classifierQ.isAll()) 
				connector = "implies";
			else 
				connector = "and";
			
			expr = "("+buildListExpression(true)+") "+connector+" not ("+buildListExpression(false)+")";
		}
		
		expr = classifierQ.addQuantification(expr);
		expr = worldQ.addQuantification(expr);
		return expr;
	}
	
	

	private String buildListExpression(boolean isDisjunction) {
		String s = "";
		
		Iterator<Classifier> i = classifiers.iterator();
		while(i.hasNext()){
			s += getClassifierVariable()+" in "+getWorldVariable()+"."+parser.getAlias(i.next());
			if(i.hasNext()){
				if(isDisjunction)
					s += " or ";
				else
					s += " and ";
			}
		}
		
		return s;
	}
	

	private String getClassifierVariable() {
		return classifierQ.getQuantificationData(0).getVariable(1);
	}
	
	private String getWorldVariable() {
		return worldQ.getQuantificationData(0).getVariable(1);
	}

	@Override
	public String getScenarioName() {
		return "MultipleExclusiveInstantiation";
	}


	
	

	
	
	
	
}
