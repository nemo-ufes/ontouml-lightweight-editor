package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.List;

public class DescriptionCategory {
	private String label;
	private String userDescription;
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
	
	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	public List<DescriptionFunction> getFunctions() {
		return functions;
	}
}
