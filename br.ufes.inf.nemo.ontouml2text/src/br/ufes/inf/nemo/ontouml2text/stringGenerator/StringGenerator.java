package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.GlossaryExporter;

public class StringGenerator {
	private DescriptionSpace descriptionSpace;
	private GlossaryExporter exporter;
	private LanguageAdaptor languageAdaptor;
	
	public StringGenerator(DescriptionSpace descriptionSpace, 
			GlossaryExporter exporter, LanguageAdaptor languageAdaptor){
		this.descriptionSpace = descriptionSpace;
		this.exporter = exporter;
		this.languageAdaptor = languageAdaptor;
	}
	
	public void generateGlossary(){
		int i;
		List<DescriptionPattern> patterns = new ArrayList<DescriptionPattern>();
		
		for(i = 0; i < descriptionSpace.getCategories().size(); i++){
			// Gerar patterns
			
			languageAdaptor.generateCategoryDescription(patterns);
		}
	}
	
	public DescriptionSpace getGeneralizationSpace() {
		return descriptionSpace;
	}
	
	public GlossaryExporter getExporter() {
		return exporter;
	}
	
	public LanguageAdaptor getLanguageAdaptor() {
		return languageAdaptor;
	}
}
