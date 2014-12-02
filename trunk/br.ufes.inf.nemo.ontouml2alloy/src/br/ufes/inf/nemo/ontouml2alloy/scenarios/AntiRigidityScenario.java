package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;

public class AntiRigidityScenario extends ContentScenario {
		
	public enum AntirigidityMode {
		MANDATORY {
			@Override
			public String toString(){
				return "Mandatory Anti-Rigidity";
			}
		}, 
		PSEUDO {
			@Override
			public String toString(){
				return "Pseudo Rigidity";
			}
		}
	}
	
	private AntirigidityMode arMode;
	private Class antiRigid;
	
	public AntiRigidityScenario (OntoUMLParser parser){
		super(parser);
	}
		
	@Override
	public String getAlloy() {
		String expr = "";
		
		if(arMode==AntirigidityMode.MANDATORY){ 
			return "antirigidity["+parser.getAlias(antiRigid)+"]";
		}
		
		if(arMode==AntirigidityMode.PSEUDO){
			return "rigidity["+parser.getAlias(antiRigid)+"]";
		}
		
		return expr;
	}
	
	public void setMandatoryAntiRigidity(Class antiRigid){
		this.antiRigid = antiRigid;
		arMode = AntirigidityMode.MANDATORY;
	}
	
	public void setPseudoRigidity(Class antiRigid){
		this.antiRigid = antiRigid;
		arMode = AntirigidityMode.PSEUDO;
	}
	
	

	@Override
	public String getPhrase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getScenarioName() {
		return "AntiRigidity";
	}

	public AntirigidityMode getAntirigidityMode() {
		return arMode;
	}

	public void setAntirigidityMode(AntirigidityMode type) {
		this.arMode = type;
	}

	public Class getAntiRigid() {
		return antiRigid;
	}

	public void setAntiRigid(Class antiRigid) {
		this.antiRigid = antiRigid;
	}


	
	

	
	
	
	
}
