package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public abstract class QuantifiedScenario extends ContentScenario{
	
	SimpleQuantification q;
	
	public QuantifiedScenario (OntoUMLParser parser, SimpleQuantification q){
		super(parser);
		this.q = q;
	}
	
	
	
	
}
