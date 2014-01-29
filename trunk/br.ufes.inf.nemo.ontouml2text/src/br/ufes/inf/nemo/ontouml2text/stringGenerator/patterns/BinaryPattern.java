package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public abstract class BinaryPattern extends DescriptionPattern {
	private PatternCategory targetCategory;
	
	public BinaryPattern(DescriptionCategory describedCategory, 
			PatternCategory targetCategory) {
		super(describedCategory);
		
		this.targetCategory = targetCategory;
	}
	
	public PatternCategory getTargetCategory() {
		return targetCategory;
	}
}
