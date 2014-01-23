package br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.BinaryDescriptionFunction;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class Mediation extends BinaryDescriptionFunction {

	public Mediation(String label, 
			DescriptionCategory source,
			DescriptionCategory target, 
			int sourceMinMultiplicity,
			int sourceMaxMultiplicity, 
			int targetMinMultiplicity,
			int targetMaxMultiplicity) {
		super(label, source, target, sourceMinMultiplicity, sourceMaxMultiplicity,
				targetMinMultiplicity, targetMaxMultiplicity);
	}	

}
