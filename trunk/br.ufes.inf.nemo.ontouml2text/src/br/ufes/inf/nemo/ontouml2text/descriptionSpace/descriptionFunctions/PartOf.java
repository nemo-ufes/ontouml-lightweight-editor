package br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.BinaryDescriptionFunction;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;

public abstract class PartOf extends BinaryDescriptionFunction {
	
	private boolean essencial = false;
	private boolean inseparable = false;
	private boolean shareable = false;

	public PartOf(String label, 
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
				targetMinMultiplicity, targetMaxMultiplicity);
		this.essencial = essencial;
		this.inseparable = inseparable;
		this.shareable = shareable;
	}	
	
	public boolean isEssencial() {
		return essencial;
	}

	public void setEssencial(boolean essencial) {
		this.essencial = essencial;
	}

	public boolean isInseparable() {
		return inseparable;
	}

	public void setInseparable(boolean inseparable) {
		this.inseparable = inseparable;
	}

	public boolean isShareable() {
		return shareable;
	}

	public void setShareable(boolean shareable) {
		this.shareable = shareable;
	}

}
