package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.List;

public class DescriptionSpace {
	private List<DescriptionCategory> categories;
	private List<DescriptionFunction> functions;
	
	public DescriptionSpace(){
		this.categories = new ArrayList<DescriptionCategory>();
		this.functions = new ArrayList<DescriptionFunction>();
	}
	
	public List<DescriptionCategory> getCategories() {
		return categories;
	}
	
	public List<DescriptionFunction> getFunctions() {
		return functions;
	}
}
