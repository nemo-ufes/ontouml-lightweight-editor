package br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.NaryDescriptionFunction;

public abstract class GeneralizationSet extends NaryDescriptionFunction {
	
	private boolean disjoint = false;
	private boolean complete = false;
	private List<Generalization> generalizationElements;
	
	public GeneralizationSet(DescriptionCategory target,
			int targetMinMultiplicity, 
			int targetMaxMultiplicity,
			boolean disjoint,
			boolean complete) {
		super(target, targetMinMultiplicity, targetMaxMultiplicity);
		this.disjoint = disjoint;
		this.complete = complete;
		this.generalizationElements = new ArrayList<Generalization>();
	}

	public boolean isDisjoint() {
		return disjoint;
	}

	public boolean isComplete() {
		return complete;
	}
	
	public List<Generalization> getGeneralizationElements() {
		return generalizationElements;
	}
	
}
