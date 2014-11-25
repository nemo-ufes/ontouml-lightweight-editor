package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;

public class InstantiationScenario extends QuantifiedScenario {
		
	enum Type {MULT, EXCL} 	//MULT = multiple instantiation, EXCL = exclusive instantiation
	Type type;
	
	Comparator comp;
	
	ArrayList<Classifier> classifiers;
	Classifier common;
	int m;
	
	public InstantiationScenario (OntoUMLParser parser, SimpleQuantification q){
		super(parser,q);
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
			String leftExpr = "#("+buildIntersection()+")";
			String rightExpr = ""+m;
			expr = comp.getExpression(leftExpr, rightExpr);
			return q.addQuantification(expr);
		}
		
		if(type==Type.EXCL){
			return "all x:"+q.getWorldVariable()+"."+parser.getAlias(common)+" | ("+buildListExpression(true)+") and " +
					"\n\t not ("+buildListExpression(false)+")";
		}
		
		return expr;
	}

	private String buildIntersection() {
		String s = "";
		
		Iterator<Classifier> i = classifiers.iterator();
		while(i.hasNext()){
			s += q.getWorldVariable()+"."+parser.getAlias(i.next());
			if(i.hasNext())
				s += " & ";
		}
		
		return s;
	}
	
	private String buildListExpression(boolean isDisjunction) {
		String s = "";
		
		Iterator<Classifier> i = classifiers.iterator();
		while(i.hasNext()){
			s += "x in "+q.getWorldVariable()+"."+parser.getAlias(i.next());
			if(i.hasNext()){
				if(isDisjunction)
					s += " or ";
				else
					s += " and ";
			}
		}
		
		return s;
	}
	

	@Override
	public String getScenarioName() {
		return "MultipleExclusiveInstantiation";
	}


	
	

	
	
	
	
}
