package br.ufes.inf.nemo.ontouml2ecore.tests;

import java.io.IOException;

import br.ufes.inf.nemo.common.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;

public class OntoUML2EcoreTests {
	
	public static void main(String[] args)
	{
		try {
			
			// we have to use absolute paths. I don't know why.
			
			String path1 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ontouml2ecore\\tests\\models\\Imóvel.refontouml";
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path1), path1.replace(".refontouml", ".ecore" ), true, true);
			System.out.println(OntoUML2Ecore.getLog());
			
			String path2 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ontouml2ecore\\tests\\models\\project.refontouml";
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path2),path2.replace(".refontouml", ".ecore" ), true, true);
			System.out.println(OntoUML2Ecore.getLog());
			
			String path3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ontouml2ecore\\tests\\models\\RoadTrafficAccident.refontouml";
			OntoUML2Ecore.convertToEcore(new OntoUMLParser(path3),path3.replace(".refontouml", ".ecore" ), true, true);
			System.out.println(OntoUML2Ecore.getLog());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
