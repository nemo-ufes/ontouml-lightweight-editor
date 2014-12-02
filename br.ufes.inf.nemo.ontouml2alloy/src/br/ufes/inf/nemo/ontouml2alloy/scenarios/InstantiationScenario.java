package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class InstantiationScenario extends ContentScenario {
		
	private InstantiationType type;
	
	private CustomQuantification worldQ, classifierQ;
	
	private ArrayList<EObject> classifiers;
	
	public InstantiationScenario (OntoUMLParser parser){
		super(parser);
		
		classifiers = new ArrayList<EObject>();
		worldQ = CustomQuantification.createWorldQuantification(1);
		classifierQ = new CustomQuantification();
		classifierQ.addQuantificationData("x", "w.exists", 1);
	}
	
	public void setAsMultiple(ArrayList<EObject> classifiers){
		type = InstantiationType.MULT;
		updateClassifierList(classifiers);
	}
		
	public void setAsExclusive(ArrayList<EObject> classifiers){
		type = InstantiationType.EXCL;
		updateClassifierList(classifiers);
	}

	private void updateClassifierList(ArrayList<EObject> classifiers) {
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
	public String getPhrase() {
		if(type==InstantiationType.MULT)
			return "a story in which "+getMultClassQuantificationPhrase()+" "+getListPhrase()+" in "+getWorldQuantificationPhrase();
		if(type==InstantiationType.EXCL)
			return "a story in which "+getExclClassQuantificationPhrase()+" "+getListPhrase()+" in "+getWorldQuantificationPhrase();
	
		return "";
	}
		
	private String getExclClassQuantificationPhrase() {
		switch (classifierQ.getType()) {
		case COMPARISON:
			return getOperatorPhrase(worldQ.getOperator())+" "+worldQ.getValue()+" individual(s) instantiate(s) exactly one of the types:";
		case ALL:
			return "individuals can only instantiate one of the following types in a given moment:";
		case NO:
			return "no individual instantiates exactly one of the types:";
		case SOME:
			return "some individual instantiates exactly one of the types:";
		case ONE:
			return "one individual instantiates exactly one of the types:";
		default:
			break;
		}
		
		return "";
	}

	private String getMultClassQuantificationPhrase() {
		switch (classifierQ.getType()) {
		case COMPARISON:
			return getOperatorPhrase(classifierQ.getOperator())+" "+classifierQ.getValue()+" individual(s) simultaneously instantiate(s)";
		case ALL:
			return "individuals must instantiate the following types simultaneously:";
		case NO:
			return "no individual simultaneously instantiates";
		case SOME:
			return "some individual simultaneously instantiates";
		case ONE:
			return "one individual simultaneously instantiates";
		default:
			break;
		}
		
		return "";
	}

	
	private String getWorldQuantificationPhrase(){
		switch (worldQ.getType()) {
		case COMPARISON:
			return "in "+getOperatorPhrase(worldQ.getOperator())+" "+worldQ.getValue()+" world(s) of the story";
		case ALL:
			return "in every world of the story";
		case NO:
			return "in no world of the story";
		case SOME:
			return "in some world of the story";
		case ONE:
			return "in a single world of the story";
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

	private String getListPhrase() {
		String s = "";
		int size = classifiers.size();
		for (int i = 0; i < size; i++) {
			s += OntoUMLNameHelper.getTypeAndName(classifiers.get(i), true, true);
			
			if(i<size-2)
				s+=", ";
			else if (i==size-2)
				s+=" and ";
		}
		
		return s;
	}

	@Override
	public String getAlloy() {
		String expr = "";
		
		
		if(type==InstantiationType.MULT){ 
			if(classifierQ.isAll())
				expr = "("+buildListExpression(true)+") implies ("+buildListExpression(false)+")";
			else
				expr = buildListExpression(false);
		}
		
		else if(type==InstantiationType.EXCL){
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
		
		Iterator<EObject> i = classifiers.iterator();
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
	
	public ArrayList<EObject> getClassifierList(){
		return classifiers;
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

	public InstantiationType getType() {
		return type;
	}
	
	
}
