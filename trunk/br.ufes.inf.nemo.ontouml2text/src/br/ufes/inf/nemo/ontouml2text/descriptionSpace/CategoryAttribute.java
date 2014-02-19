package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

public class CategoryAttribute {
	private String label;
	private String kind;
	
	public CategoryAttribute(String label, String kind) {	
		this.label = label;
		this.kind = kind;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}
