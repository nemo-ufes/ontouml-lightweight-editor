package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.MediationPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.NaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.OptionalMultiplicityPattern;

public class OrdinaryMediationPattern extends NaryPattern implements MediationPattern, OptionalMultiplicityPattern {

	public OrdinaryMediationPattern(DescriptionCategory describedCategory) {
		super(describedCategory);
	}

}
