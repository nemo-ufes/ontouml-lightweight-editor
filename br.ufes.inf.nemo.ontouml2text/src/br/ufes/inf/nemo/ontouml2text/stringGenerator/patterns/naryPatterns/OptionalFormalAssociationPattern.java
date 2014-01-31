package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.NaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.FormalPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.OptionalMultiplicityPattern;

public class OptionalFormalAssociationPattern extends NaryPattern implements
		FormalPattern, OptionalMultiplicityPattern {

	public OptionalFormalAssociationPattern(
			DescriptionCategory describedCategory) {
		super(describedCategory);
	}

}
