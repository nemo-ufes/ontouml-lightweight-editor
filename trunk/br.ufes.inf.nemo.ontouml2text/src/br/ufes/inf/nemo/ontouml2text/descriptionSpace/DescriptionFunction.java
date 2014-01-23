package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

public abstract class DescriptionFunction {
	
	private boolean described;
	private int targetMinMultiplicity;
	private int targetMaxMultiplicity;
	private DescriptionCategory target;
	
	public DescriptionFunction(DescriptionCategory target,
			int targetMinMultiplicity, int targetMaxMultiplicity){
		this.described = false;
		this.targetMinMultiplicity = targetMinMultiplicity;
		this.targetMaxMultiplicity = targetMaxMultiplicity;
		this.target = target;
	}
	
	public boolean isDescribed() {
		return described;
	}
	
	public void setDescribed(boolean described) {
		this.described = described;
	}
	
	public int getTargetMinMultiplicity() {
		return targetMinMultiplicity;
	}
	
	public void setTargetMinMultiplicity(int targetMinMultiplicity) {
		this.targetMinMultiplicity = targetMinMultiplicity;
	}
	
	public int getTargetMaxMultiplicity() {
		return targetMaxMultiplicity;
	}
	
	public void setTargetMaxMultiplicity(int targetMaxMultiplicity) {
		this.targetMaxMultiplicity = targetMaxMultiplicity;
	}
	
	public DescriptionCategory getTarget() {
		return target;
	}
}
