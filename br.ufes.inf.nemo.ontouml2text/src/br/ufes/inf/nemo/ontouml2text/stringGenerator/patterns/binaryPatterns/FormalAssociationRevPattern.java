package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.BinaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.FormalPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PatternCategory;

public class FormalAssociationRevPattern extends BinaryPattern implements FormalPattern {

	public FormalAssociationRevPattern(DescriptionCategory describedCategory,
			PatternCategory targetCategory) {
		super(describedCategory, targetCategory);
	}

}
