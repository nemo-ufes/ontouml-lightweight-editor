package br.ufes.inf.nemo.ontouml2uml.tests;

import java.io.IOException;

import br.ufes.inf.nemo.common.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

public class OntoUML2UMLTests {

	public static void main(String[] args)
	{
		try {
			
			String path1 = "src/br/ufes/inf/nemo/ontouml2uml/tests/models/Imóvel.refontouml";
			OntoUML2UML.convertToUML(new OntoUMLParser(path1), path1.replace(".refontouml", ".uml" ), true, true);
			System.out.println(OntoUML2UML.getLog());
			
			String path2 = "src/br/ufes/inf/nemo/ontouml2uml/tests/models/Project.refontouml";
			OntoUML2UML.convertToUML(new OntoUMLParser(path2),path2.replace(".refontouml", ".uml" ), true, true);
			System.out.println(OntoUML2UML.getLog());
			
			String path3 = "src/br/ufes/inf/nemo/ontouml2uml/tests/models/RoadTrafficAccident.refontouml";
			OntoUML2UML.convertToUML(new OntoUMLParser(path3),path3.replace(".refontouml", ".uml" ), true, true);
			System.out.println(OntoUML2UML.getLog());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
