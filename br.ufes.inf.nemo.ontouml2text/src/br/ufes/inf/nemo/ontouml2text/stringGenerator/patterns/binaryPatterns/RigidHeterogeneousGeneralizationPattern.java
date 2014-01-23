package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PatternCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.BinaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.GeneralizationPattern;

public class RigidHeterogeneousGeneralizationPattern extends BinaryPattern 
	implements GeneralizationPattern {

	public RigidHeterogeneousGeneralizationPattern(
			DescriptionCategory describedCategory,
			PatternCategory targetCategory) {
		super(describedCategory, targetCategory);
	}

}
