package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;
import br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator.DescriptionSpaceGenerator;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.HtmlGlossaryExporter;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseDictionary;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseLanguageAdaptor;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.StringGenerator;

public class OntoUmlGlossary {
	
	// List containing concepts without user description
	static ArrayList <String> conceptsWithoutDesc = new ArrayList<String>();
	
	public static void xmiToText(final OntoUMLParser parser, final String outputName) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {														
					DescriptionSpace space = new DescriptionSpace();		
					
					// Hash containing the labels of the categories already covered
					Set<String> hashCategories = new HashSet<String>();
					
					DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(space);
					generator.populateDescriptionSpace(parser, hashCategories , conceptsWithoutDesc);
					
					// Processing description space
					StringGenerator glossaryGenerator = new StringGenerator(space, 
							new HtmlGlossaryExporter(outputName,"C:/Users/jose anholetti/Desktop","Glossário ANTT"), 
							new PortugueseLanguageAdaptor(new PortugueseDictionary()));
					
					glossaryGenerator.generateGlossary();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static ArrayList<String> getConceptsWithoutDesc() {
		return conceptsWithoutDesc; 
	}
}
