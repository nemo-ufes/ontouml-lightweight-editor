package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.parser.OntoUMLParser;

public abstract class ContentScenario extends Scenario{
	OntoUMLParser parser;
	
	public ContentScenario (OntoUMLParser parser){
		this.parser = parser;
	}
	
	
}
