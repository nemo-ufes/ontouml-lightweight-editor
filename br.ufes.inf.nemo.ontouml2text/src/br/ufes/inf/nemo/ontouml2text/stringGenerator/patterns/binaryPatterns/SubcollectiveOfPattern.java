package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.BinaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PartOfPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PatternCategory;

public class SubcollectiveOfPattern extends BinaryPattern implements PartOfPattern {

	public SubcollectiveOfPattern(DescriptionCategory describedCategory,
			PatternCategory targetCategory) {
		super(describedCategory, targetCategory);
	}

}
