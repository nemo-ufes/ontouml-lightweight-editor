package br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;
import br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator.DescriptionSpaceGenerator;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.HtmlGlossaryExporter;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseDictionary;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseLanguageAdaptor;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.StringGenerator;

public class OntoUmlGlossary {
	
	public static final int PT_BR = 10;
	public static final int EN_US = 20;
	
	private DescriptionSpace descriptionSpace;
	private StringGenerator stringGenerator;
	
	public OntoUmlGlossary(final int idiom, final OntoUMLParser parser, final String outputName, 
			final String outputdirectory, final String title, final String subtitle, boolean inheritMediations){
		descriptionSpace = new DescriptionSpace();
		
		// Hash containing the labels of the categories already covered
		Set<String> hashCategories = new HashSet<String>();
				
		DescriptionSpaceGenerator generator = new DescriptionSpaceGenerator(descriptionSpace);
		generator.populateDescriptionSpace(parser, hashCategories, inheritMediations);
		
		// Preparing for text generation
		switch(idiom){
		case PT_BR:
			stringGenerator = new StringGenerator(descriptionSpace, 
					new HtmlGlossaryExporter(outputName,outputdirectory,title,subtitle), 
					new PortugueseLanguageAdaptor(new PortugueseDictionary()));
			break;
		case EN_US:
			System.out.println("Not implemented yet");
			System.exit(0);
		}
	}
	
	public List<String> verifiyMissingUserDescriptions(){
		return stringGenerator.verifyMissingUserDescription();
	}
	
	public List<String> verfifyIsolatedDescriptions(){
		return stringGenerator.verifyIsolatedConcepts();
	}
	
	public List<String> verfifyNonDeterminedRelationships(){
		return stringGenerator.verfifyNonDeterminedRelationships();
	}
		
	public void modelToText() {		
		long startTime = System.currentTimeMillis();
		
		stringGenerator.generateGlossary();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time to process: "+totalTime+" miliseconds");
	}


}
