package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public abstract class QuantifiedScenario extends ContentScenario{
	
	WorldQuantification q;
	
	public QuantifiedScenario (OntoUMLParser parser, WorldQuantification q){
		super(parser);
		this.q = q;
	}
	
	public WorldQuantification getWorldQuantification() {
		return q;
	}
	
	
	
	
}
