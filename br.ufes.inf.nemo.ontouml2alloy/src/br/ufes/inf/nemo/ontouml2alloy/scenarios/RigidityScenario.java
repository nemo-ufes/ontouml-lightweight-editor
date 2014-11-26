package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;

public class RigidityScenario extends ContentScenario {
		
	private enum Type {MANDATORY, PSEUDO}
	private Type type;
	
	private Class antiRigid;
	
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
	
	public void setMandatoryAntiRigidity(Class antiRigid){
		this.antiRigid = antiRigid;
		type = Type.MANDATORY;
	}
	
	public void setPseudoRigidity(Class antiRigid){
		this.antiRigid = antiRigid;
		type = Type.PSEUDO;
	}
	
	

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getScenarioName() {
		return "AntiRigidity";
	}


	
	

	
	
	
	
}
