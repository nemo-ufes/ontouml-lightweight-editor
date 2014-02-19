package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.List;

public class DescriptionCategory {
	private String label;
	private String userDescription = "";
	private boolean isDerived = false;
	private List<DescriptionFunction> functions;
	private List<CategoryAttribute> attributes;
	
	public DescriptionCategory(String label){
		this.label = processLabel(label);
		this.functions = new ArrayList<DescriptionFunction>();
		this.attributes = new ArrayList<CategoryAttribute>();
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = processLabel(label);
	}
	
	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	public boolean isDerived(){
		return isDerived;
	}
	
	public List<DescriptionFunction> getFunctions() {
		return functions;
	}
	
	public List<CategoryAttribute> getAttributes() {
		return attributes;
	}
	
	public String processLabel(String label) {
		if(label.startsWith("/")){
			isDerived = true;
			return label.substring(1,label.length());
		}else
			return label;
	}
}
