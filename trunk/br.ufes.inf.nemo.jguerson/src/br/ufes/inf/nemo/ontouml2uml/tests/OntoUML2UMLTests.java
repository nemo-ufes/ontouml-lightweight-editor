package br.ufes.inf.nemo.ontouml2uml.tests;

import java.io.IOException;

import br.ufes.inf.nemo.common.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

public class OntoUML2UMLTests {

	public static void main(String[] args)
	{
		try {
			String path = "model/Imóvel.refontouml";
			OntoUML2UML.convertToUML(new OntoUMLParser(path),path.replace(".refontouml", ".uml" ),true,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
