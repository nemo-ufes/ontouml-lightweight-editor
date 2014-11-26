package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationVariabilityScenario extends AssociationScenario {

	enum Change {DIF, EQUAL, DISJ}
	private Change change;
	
	public AssociationVariabilityScenario (OntoUMLParser parser, Association a, boolean isReversed){
		super(parser, a, isReversed);
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
		default:
			return "";
		}
	}

	public void setIsChangeable(){
		change=Change.DIF;
	}
	
	public void setIsConstant(){
		change=Change.EQUAL;
	}
	
	public void setIsDisjoint(){
		change=Change.DISJ;
	}
	
	public boolean isChangeable(){
		return change==Change.DIF;
	}
	
	public boolean isConstant(){
		return change==Change.EQUAL;
	}
	
	public boolean isDisjoint(){
		return change==Change.DISJ;
	}
	
	@Override
	public String getAlloy() {
		String domain = getDomain();
		String end = getEnd();
		String op = getOperator();
		String prefix = getPrefix();
		String suffix = getSuffix();
		
		return "all w1,w2: World | w1!=w2 implies (all x:w1."+domain+" | x in w2."+domain+" implies "+prefix+"x."+end+"[w1]"+op+"x."+end+"[w2]"+suffix+")";
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getScenarioName() {
		return "AssociationVariability";
	}
	
	
}
