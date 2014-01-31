package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.MediationPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.NaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.OptionalMultiplicityPattern;

public class OrdinaryOptionalMediationPattern extends NaryPattern implements MediationPattern, OptionalMultiplicityPattern {

	public OrdinaryOptionalMediationPattern(
			DescriptionCategory describedCategory) {
		super(describedCategory);
	}

}
