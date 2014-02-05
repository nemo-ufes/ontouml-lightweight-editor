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
		
		conceptsWithoutDesc.addElement("Object2");
		conceptsWithoutDesc.addElement("Object3");
		conceptsWithoutDesc.addElement("Object4");
		conceptsWithoutDesc.addElement("Object5");
		conceptsWithoutDesc.addElement("Object6");
		conceptsWithoutDesc.addElement("Object7");
		conceptsWithoutDesc.addElement("Object8");
		conceptsWithoutDesc.addElement("Object9");
		conceptsWithoutDesc.addElement("Object10");
		conceptsWithoutDesc.addElement("Object11");
		conceptsWithoutDesc.addElement("Object12");
		conceptsWithoutDesc.addElement("Object13");
		conceptsWithoutDesc.addElement("Object14");
		conceptsWithoutDesc.addElement("Object15");
		conceptsWithoutDesc.addElement("Object16");
		conceptsWithoutDesc.addElement("Object44");
		conceptsWithoutDesc.addElement("Object1");
		
		DescriptionSpace space = new DescriptionSpace();		
		
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
