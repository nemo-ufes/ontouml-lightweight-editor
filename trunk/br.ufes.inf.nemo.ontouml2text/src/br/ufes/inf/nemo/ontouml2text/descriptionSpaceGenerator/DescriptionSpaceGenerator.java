package br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;

public class DescriptionSpaceGenerator {
	private DescriptionSpace generalizationSpace;
	
	public DescriptionSpaceGenerator(DescriptionSpace generalizationSpace){
		this.generalizationSpace = generalizationSpace;
	}
	
	public DescriptionSpace getGeneralizationSpace() {
		return generalizationSpace;
	}	
}
