package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;

public class RigidityScenario extends QuantifiedScenario {
		
	enum Type {MANDATORY, PSEUDO}
	Type type;
	
	Class antiRigid;
	
	public RigidityScenario (OntoUMLParser parser){
		super(parser);
	}
		
	@Override
	public String getAlloy() {
		String expr = "";
		
		if(type==Type.MANDATORY){ 
			return "antirigidity["+parser.getAlias(antiRigid)+"]";
		}
		
		if(type==Type.PSEUDO){
			return "rigidity["+parser.getAlias(antiRigid)+"]";
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
		return "Rigidity";
	}


	
	

	
	
	
	
}
