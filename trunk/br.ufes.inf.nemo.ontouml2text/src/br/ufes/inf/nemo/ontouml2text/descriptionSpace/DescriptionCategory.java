package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.List;

public abstract class DescriptionCategory {
	private String label;
	private List<DescriptionFunction> functions;
	
	public DescriptionCategory(String label){
		this.label = label;
		this.functions = new ArrayList<DescriptionFunction>();
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<DescriptionFunction> getFunctions() {
		return functions;
	}
}
