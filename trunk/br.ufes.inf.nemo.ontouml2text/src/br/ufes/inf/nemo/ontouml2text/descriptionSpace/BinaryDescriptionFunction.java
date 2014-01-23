package br.ufes.inf.nemo.ontouml2text.descriptionSpace;


public abstract class BinaryDescriptionFunction extends DescriptionFunction {

	private String label;
	private boolean reverseReadDirection ;
	private int sourceMinMultiplicity;
	private int sourceMaxMultiplicity;
	private DescriptionCategory source;
	
	public BinaryDescriptionFunction(String label, 
			DescriptionCategory source, 
			DescriptionCategory target,
			int sourceMinMultiplicity, 
			int sourceMaxMultiplicity,
			int targetMinMultiplicity, 
			int targetMaxMultiplicity) {
		super(target, targetMinMultiplicity, targetMaxMultiplicity);
		this.label = label;
		this.reverseReadDirection = false;
		this.sourceMinMultiplicity = sourceMinMultiplicity;
		this.sourceMaxMultiplicity = sourceMaxMultiplicity;
		this.source = source;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean getReverseReadDirection() {
		return reverseReadDirection;
	}
	
	public void setReverseReadDirection(boolean reverseReadDirection) {
		this.reverseReadDirection = reverseReadDirection;
	}
	
	public int getSourceMinMultiplicity() {
		return sourceMinMultiplicity;
	}
	
	public void setSourceMinMultiplicity(int sourceMinMultiplicity) {
		this.sourceMinMultiplicity = sourceMinMultiplicity;
	}
	
	public int getSourceMaxMultiplicity() {
		return sourceMaxMultiplicity;
	}
	
	public void setSourceMaxMultiplicity(int sourceMaxMultiplicity) {
		this.sourceMaxMultiplicity = sourceMaxMultiplicity;
	}
	
	public DescriptionCategory getSource() {
		return source;
	}
}
