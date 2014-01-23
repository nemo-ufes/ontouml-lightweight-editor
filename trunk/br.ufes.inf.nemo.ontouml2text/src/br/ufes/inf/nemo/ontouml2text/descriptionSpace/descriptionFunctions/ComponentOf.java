package br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public class ComponentOf extends PartOf {

	public ComponentOf(String label, 
			DescriptionCategory source,
			DescriptionCategory target, 
			int sourceMinMultiplicity,
			int sourceMaxMultiplicity, 
			int targetMinMultiplicity,
			int targetMaxMultiplicity, 
			boolean essencial, 
			boolean inseparable,
			boolean shareable) {
		super(label, source, target, sourceMinMultiplicity, sourceMaxMultiplicity,
				targetMinMultiplicity, targetMaxMultiplicity, essencial, inseparable,
				shareable);
	}

}
