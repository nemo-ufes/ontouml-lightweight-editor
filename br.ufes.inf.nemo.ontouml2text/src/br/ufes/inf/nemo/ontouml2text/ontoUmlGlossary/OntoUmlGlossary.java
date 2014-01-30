package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.Set;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator.DescriptionSpaceGenerator;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.*;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui.GlossaryGeneratorUI;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.*;

public class OntoUmlGlossary {

	public void xmiToText(OntoUMLParser parser) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlossaryGeneratorUI settings = new GlossaryGeneratorUI();
					settings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		//if(settings.doGlossaryGeneration()){
			DescriptionSpace space = new DescriptionSpace();		
			
			// Hash containing the labels of the categories already covered
			Set<String> hashCategories = new HashSet<String>();
			
			DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(space);
			generator.populateDescriptionSpace(parser, hashCategories);
			
			// Processing description space
			StringGenerator glossaryGenerator = new StringGenerator(space, 
					new HtmlGlossaryExporter("Glossary","C:/Users/Dio.Dio/Desktop","Glossário ANTT"), 
					new PortugueseLanguageAdaptor(new PortugueseDictionary()));
			
			glossaryGenerator.generateGlossary();
		//}
	}

}
