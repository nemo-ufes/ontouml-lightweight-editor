package br.ufes.inf.nemo.ontouml2ecore.tests;

import java.io.IOException;

import br.ufes.inf.nemo.common.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;

public class OntoUML2EcoreTests {
	
	public static void main(String[] args)
	{
		try {
			String path = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\Imóvel.refontouml";
//			String path = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";

			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path),path.replace(".refontouml", ".ecore" ),true,true);
			
			System.out.println(OntoUML2Ecore.getLog());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
