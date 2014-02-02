package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.awt.EventQueue;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui.GlossaryGeneratorUI;

public class OntoUmlGlossary {
	

	public void xmiToText(final OntoUMLParser parser) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlossaryGeneratorUI settings = new GlossaryGeneratorUI();
					settings.getParser(parser);
					settings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		//if(settings.doGlossaryGeneration()){
		/*	DescriptionSpace space = new DescriptionSpace();		
			
			// Hash containing the labels of the categories already covered
			Set<String> hashCategories = new HashSet<String>();
			
			DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(space);
			generator.populateDescriptionSpace(parser, hashCategories);
			
			// Processing description space
			StringGenerator glossaryGenerator = new StringGenerator(space, 
					new HtmlGlossaryExporter("Glossary","C:/Users/jose anholetti/Desktop","Glossário ANTT"), 
					new PortugueseLanguageAdaptor(new PortugueseDictionary()));
			
			glossaryGenerator.generateGlossary();*/
		//}
	}

}
