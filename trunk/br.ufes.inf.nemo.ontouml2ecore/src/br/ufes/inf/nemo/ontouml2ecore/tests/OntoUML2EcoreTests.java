package br.ufes.inf.nemo.ontouml2ecore.tests;

import java.io.File;
import java.io.IOException;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2EcoreOption;

/**
 * @author John Guerson
 */

public class OntoUML2EcoreTests {
	
	public static void main(String[] args)
	{
		try {
			
			// we have to use absolute paths. I don't know why.
						
			String path1 = new File("src/br/ufes/inf/nemo/ontouml2ecore/tests/models/Imóvel.refontouml").getAbsolutePath();
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path1), path1.replace(".refontouml", ".ecore" ), new OntoUML2EcoreOption(true, true));
			System.out.println(OntoUML2Ecore.getLog());
			
			String path2 = new File("src/br/ufes/inf/nemo/ontouml2ecore/tests/models/project.refontouml").getAbsolutePath();
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path2),path2.replace(".refontouml", ".ecore" ), new OntoUML2EcoreOption(true, true));
			System.out.println(OntoUML2Ecore.getLog());
			
			String path3 = new File("src/br/ufes/inf/nemo/ontouml2ecore/tests/models/RoadTrafficAccident.refontouml").getAbsolutePath();
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path3),path3.replace(".refontouml", ".ecore" ), new OntoUML2EcoreOption(true, true));
			System.out.println(OntoUML2Ecore.getLog());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
