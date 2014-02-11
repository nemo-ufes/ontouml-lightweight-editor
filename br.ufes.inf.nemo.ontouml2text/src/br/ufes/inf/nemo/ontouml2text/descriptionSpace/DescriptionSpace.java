package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	public boolean addCategory(DescriptionCategory category){
		this.categories.add(category);
		
		Collections.sort(this.categories, new Comparator<DescriptionCategory>(){		
			public int compare(DescriptionCategory d1, DescriptionCategory d2){
					int res = String.CASE_INSENSITIVE_ORDER.compare(d1.getLabel(), d2.getLabel());
			        if (res == 0) {
			            res = d1.getLabel().compareTo(d2.getLabel());
			        }
			        return res;
			}
		});
		
		return true;
	}
	
	public DescriptionCategory findCategory(String label){
		
		if(label.contains("/"))
			label = label.replace("/","");
		
		for(DescriptionCategory c: categories){
			
			if (c.getLabel().equals(label))
				return c;
		}
		return null;
	}
	
	/*
	public DescriptionCategory findCategory(String label){
	
		if(label.contains("/"))
			label = label.replace("/","");
			
		Collections.binarySearch(this.categories, new DescriptionCategory(label), new Comparator<DescriptionCategory>(){		
			
			public int compare(DescriptionCategory d1, DescriptionCategory d2){
				int res = String.CASE_INSENSITIVE_ORDER.compare(d1.getLabel(), d2.getLabel());
		        if (res == 0) {
		            res = d1.getLabel().compareTo(d2.getLabel());
		        }
		        return res;
			}
		});
		
		return null;
	}*/
}
