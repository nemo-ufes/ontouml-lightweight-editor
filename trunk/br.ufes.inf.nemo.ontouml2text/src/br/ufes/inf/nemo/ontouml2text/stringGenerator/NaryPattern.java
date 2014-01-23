package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.DescriptionPattern;

public abstract class NaryPattern extends DescriptionPattern {
	private List<PatternCategory> targetCategories;
	
	public NaryPattern(DescriptionCategory describedCategory) {
		super(describedCategory);
		
		this.targetCategories = new ArrayList<PatternCategory>();
	}
	
	public List<PatternCategory> getTargetCategories() {
		return targetCategories;
	}

}
