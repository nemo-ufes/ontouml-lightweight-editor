package br.ufes.inf.nemo.ontouml2ecore.tests;

import java.io.IOException;

import br.ufes.inf.nemo.common.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;

public class OntoUML2EcoreTests {
	
	public static void main(String[] args)
	{
		try {
			
			String path1 = "src/br/ufes/inf/nemo/ontouml2ecore/tests/models/Imóvel.refontouml";
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path1), path1.replace(".refontouml", ".ecore" ), true, true);
			System.out.println(OntoUML2Ecore.getLog());
			
			String path2 = "src/br/ufes/inf/nemo/ontouml2ecore/tests/models/Project.refontouml";
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path2),path2.replace(".refontouml", ".ecore" ), true, true);
			System.out.println(OntoUML2Ecore.getLog());
			
			String path3 = "src/br/ufes/inf/nemo/ontouml2ecore/tests/models/RoadTrafficAccident.refontouml";
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path3),path3.replace(".refontouml", ".ecore" ), true, true);
			System.out.println(OntoUML2Ecore.getLog());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
