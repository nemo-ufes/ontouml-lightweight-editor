package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PatternCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.BinaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PartOfPattern;

public class ComponentOfPattern extends BinaryPattern implements PartOfPattern {

	public ComponentOfPattern(DescriptionCategory describedCategory,
			PatternCategory targetCategory) {
		super(describedCategory, targetCategory);
	}

}
