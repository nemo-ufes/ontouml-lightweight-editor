package br.ufes.inf.nemo.ontouml2uml.tests;

import java.io.IOException;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLOption;

public class OntoUML2UMProfileTests {
	
	public static void main(String[] args)
	{
		try {
			
			String path1 = "src/br/ufes/inf/nemo/ontouml2uml/tests/models/Imóvel.refontouml";
			OntoUML2UML.convertToUMLProfile(new OntoUMLParser(path1), path1.replace(".refontouml", ".uml" ), new OntoUML2UMLOption(true,true));			
			System.out.println(OntoUML2UML.getLog());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
