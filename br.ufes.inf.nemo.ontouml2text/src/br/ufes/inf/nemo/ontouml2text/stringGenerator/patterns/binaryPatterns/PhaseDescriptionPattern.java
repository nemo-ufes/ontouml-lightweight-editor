package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.BinaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PatternCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PhasePattern;

public class PhaseDescriptionPattern extends BinaryPattern implements PhasePattern {

	public PhaseDescriptionPattern(DescriptionCategory describedCategory,
			PatternCategory targetCategory) {
		super(describedCategory, targetCategory);
	}

}
