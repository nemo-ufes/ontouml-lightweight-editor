package br.ufes.inf.nemo.ontouml2alloy.verifier;

import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class OntoUMLVerifier {

	public OntoUMLParser ontoparser;
	
	public boolean haveSubstanceSortal;
	
	public OntoUMLVerifier (RefOntoUML.Package model)
	{
		ontoparser = new OntoUMLParser(model);
	}
		
	public void initialize()
	{
		if(ontoparser.getAllInstances(SubstanceSortal.class).size()>0)
			haveSubstanceSortal = true;
		else
			haveSubstanceSortal = false;
	}
}
