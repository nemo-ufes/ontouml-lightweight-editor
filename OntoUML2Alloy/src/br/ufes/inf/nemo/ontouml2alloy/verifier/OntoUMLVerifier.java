package br.ufes.inf.nemo.ontouml2alloy.verifier;

import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.PackageableElement;
import RefOntoUML.Quantity;
import br.ufes.inf.nemo.ontouml2alloy.parser.OntoUMLParser;

public class OntoUMLVerifier {

	public OntoUMLParser ontoparser;
	
	public boolean haveSubstanceSortal;
	
	public OntoUMLVerifier (RefOntoUML.Package model)
	{
		ontoparser = new OntoUMLParser(model);
	}
		
	public void initialize()
	{
		for (PackageableElement c : ontoparser.getPackageableElements())
		{
			if ((c instanceof Kind)||(c instanceof Collective)||(c instanceof Quantity))
			{
				haveSubstanceSortal = true;
				return;
			}
		}
		haveSubstanceSortal = false;
		return;
	}
}
