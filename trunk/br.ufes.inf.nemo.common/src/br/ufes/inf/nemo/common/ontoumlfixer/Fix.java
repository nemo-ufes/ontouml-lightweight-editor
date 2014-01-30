package br.ufes.inf.nemo.common.ontoumlfixer;

import java.util.ArrayList;

public class Fix {

	private ArrayList<Object> deletedElements = new ArrayList<Object>();
	private ArrayList<Object> addedElements = new ArrayList<Object>();
	private ArrayList<Object> modifiedElements = new ArrayList<Object>();
	private ArrayList<String> addedRules = new ArrayList<String>();
	
	public void addAll(Fix fix)
	{
		this.includeAllAdded(fix.getAdded());
		this.includeAllDeleted(fix.getDeleted());
		this.includeAllModified(fix.getModified());
		this.includeAllRule(fix.getAddedRules());
	}
	
	public boolean isEmpty()
	{
		return modifiedElements.size()==0 &&  addedElements.size()==0 && deletedElements.size()==0
				&& addedRules.size()==0;
	}
	
	//get String methods...
	public String getDeletedString()
	{
		String result = new String();
		for(Object obj: deletedElements){
			result += obj.toString()+"\n";
		}
		return result;
	}	
	public String getAddedString()
	{
		String result = new String();
		for(Object obj: addedElements){
			result += obj.toString()+"\n";
		}
		return result;
	}
	public String getModifiedString()
	{
		String result = new String();
		for(Object obj: modifiedElements){
			result += obj.toString()+"\n";
		}
		return result;
	}
	public String getRulesString()
	{
		String result = new String();
		for(String obj: addedRules){
			result += obj+"\n";
		}
		return result;
	}
	
	// Include All and Exclude All methods...	
	public void includeAllModified(ArrayList<Object> modified){
		for(Object obj: modified) includeModified(obj);
	}
	public void includeAllRule(ArrayList<String> rules){
		for(String obj: rules) includeRule(obj);
	}	
	public void includeAllAdded(ArrayList<Object> added){
		for(Object obj: added) includeAdded(obj);
	}
	public void includeAllDeleted(ArrayList<Object> deleted){
		for(Object obj: deleted) includeDeleted(obj);
	}
	public void excludeAllRule(ArrayList<String> rules){
		for(String obj: rules) excludeRule(obj);
	}
	public void excludeAllModified(ArrayList<Object> modified){
		for(Object obj: modified) excludeModified(obj);
	}
	public void excludeAllAdded(ArrayList<Object> added){
		for(Object obj: added) excludeAdded(obj);
	}
	public void excludeAllDeleted(ArrayList<Object> deleted){
		for(Object obj: deleted) excludeDeleted(obj);
	}
	
	// Include and exclude methods...	
	public void includeModified(Object modified){
		if (!modifiedElements.contains(modified)) modifiedElements.add(modified);
	}
	public void includeRule(String rule){
		addedRules.add(rule);
	}	
	public void includeAdded(Object added){
		if (!addedElements.contains(added)) addedElements.add(added);
	}
	public void includeDeleted(Object deleted){
		if (!deletedElements.contains(deleted)) deletedElements.add(deleted);
	}
	public void excludeRule(String rule){
		addedRules.remove(rule);
	}
	public void excludeModified(Object modified){
		modifiedElements.remove(modified);
	}
	public void excludeAdded(Object added){
		addedElements.remove(added);
	}
	public void excludeDeleted(Object deleted){
		deletedElements.remove(deleted);	
	}
	
	// Getters and Setters...	
	public ArrayList<Object> getDeleted() 
	{
		return deletedElements;
	}
	public void setDeleted(ArrayList<Object> deletedElements) 
	{
		this.deletedElements = deletedElements;
	}
	public ArrayList<Object> getAdded() 
	{
		return addedElements;
	}
	public void setAdded(ArrayList<Object> addedElements) 
	{
		this.addedElements = addedElements;
	}
	public ArrayList<Object> getModified() 
	{
		return modifiedElements;
	}
	public void setModified(ArrayList<Object> modifiedElements) 
	{
		this.modifiedElements = modifiedElements;
	}
	public ArrayList<String> getAddedRules() 
	{
		return addedRules;
	}
	public void setAddedRules(ArrayList<String> addedRules) 
	{
		this.addedRules = addedRules;
	}
	
	public <T> ArrayList<T> getAddedByType(java.lang.Class<T> type){
		ArrayList<T> added = new ArrayList<T>();
		for (Object elem : this.addedElements) {
			if(type.isInstance(elem))
				added.add(type.cast(elem));
		}
		return added;
	}
}
