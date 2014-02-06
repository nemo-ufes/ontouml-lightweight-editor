package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;
import br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator.DescriptionSpaceGenerator;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.HtmlGlossaryExporter;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseDictionary;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseLanguageAdaptor;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.StringGenerator;

public class OntoUmlGlossary {
		
	public static DefaultListModel<String> xmiToText(final OntoUMLParser parser, final String outputName, final String outputdirectory) {		
		
		// List containing concepts without user description
		DefaultListModel<String> conceptsWithoutDesc = new DefaultListModel<String>();
		
		DescriptionSpace space = new DescriptionSpace();		
		
		/*int i;
		for(i=0;i<10;i++){
			DescriptionCategory obj = new Category("Cat"+i);
			conceptsWithoutDesc.add(i,obj);
		}*/

		// Hash containing the labels of the categories already covered
		Set<String> hashCategories = new HashSet<String>();
		
		DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(space);
		generator.populateDescriptionSpace(parser, hashCategories , conceptsWithoutDesc);
		
		// Processing description space
		StringGenerator glossaryGenerator = new StringGenerator(space, 
				new HtmlGlossaryExporter(outputName,outputdirectory,"Glossário ANTT"), 
				new PortugueseLanguageAdaptor(new PortugueseDictionary()));
		
		glossaryGenerator.generateGlossary();
		
		return conceptsWithoutDesc;
	}


}
