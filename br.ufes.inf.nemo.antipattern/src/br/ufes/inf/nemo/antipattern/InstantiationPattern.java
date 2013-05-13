package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class InstantiationPattern {
	protected int worldNumber;
	protected Antipattern antipattern;
	protected Boolean isPossible;
	
	/*Generates the natural language description of the instantiation pattern*/ 
	//public abstract String description();
	
	/*Generates an Alloy predicate for the instantiation pattern to exemplify its occurences*/ 
	public abstract String predicate(ArrayList<InstantiationPatternParameter> parameter, OntoUMLParser parser) throws Exception ;
	
	
	/*Generates an OCL constraint which forbids the instantion pattern from happening*/
	//public abstract String forbiddanceConstraint();
	
	/*Generates an OCL constraint which forbids the instantion pattern from happening*/
	//spublic abstract String enforcementConstraint();
	
	
}
