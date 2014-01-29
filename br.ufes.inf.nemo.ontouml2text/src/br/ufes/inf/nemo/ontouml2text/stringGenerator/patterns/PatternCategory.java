package br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns;

import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;

public class PatternCategory extends DescriptionCategory{

	private int minMultiplicity;
	private int maxMultiplicity;

	public PatternCategory(String label, int minMultiplicity, int maxMultiplicity) {
		super(label);
		
		this.minMultiplicity = minMultiplicity;
		this.maxMultiplicity = maxMultiplicity;
	}	
	
	public int getMinMultiplicity() {
		return minMultiplicity;
	}

	public void setMinMultiplicity(int minMultiplicity) {
		this.minMultiplicity = minMultiplicity;
	}

	public int getMaxMultiplicity() {
		return maxMultiplicity;
	}

	public void setMaxMultiplicity(int maxMultiplicity) {
		this.maxMultiplicity = maxMultiplicity;
	}

}
