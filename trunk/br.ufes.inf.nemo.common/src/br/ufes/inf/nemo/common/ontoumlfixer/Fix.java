package br.ufes.inf.nemo.common.ontoumlfixer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.parser.ParsingElement;

public class Fix{

	private ArrayList<Object> deletedElements = new ArrayList<Object>();
	private ArrayList<Object> modifiedElements = new ArrayList<Object>();
	private ArrayList<String> addedRules = new ArrayList<String>();
	private HashMap<Object,Point2D.Double> addedElements = new HashMap<Object,Point2D.Double>();
	
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
		for(Object obj: addedElements.keySet()){
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
		if (!modifiedElements.contains(modified) && !deletedElements.contains(modified) && !addedElements.keySet().contains(modified)) 
			modifiedElements.add(modified);
	}
	public void includeRule(String rule){
		if(!addedRules.contains(rule))
			addedRules.add(rule);
	}	
	public void includeAdded(Object added){
		if (!deletedElements.contains(added) && !addedElements.keySet().contains(added)) 
			addedElements.put(added,new Point2D.Double(-1,-1));
		
		modifiedElements.remove(added);
	}
	public void includeDeleted(Object deleted){
		if (!deletedElements.contains(deleted)) 
			deletedElements.add(deleted);
		
		addedElements.remove(deleted);
		modifiedElements.remove(deleted);
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
		ArrayList<Object> added = new ArrayList<Object>();
		added.addAll(addedElements.keySet());
		return added;
	}
	
	public void setAdded(ArrayList<Object> addedElements) 
	{
		for (Object object : addedElements) {
			includeAdded(object);
		}
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
	
	public <T> ArrayList<T> getAddedByType(java.lang.Class<T> type)
	{
		ArrayList<T> added = new ArrayList<T>();
		for (Object elem : this.addedElements.keySet())
		{
			if(type.isInstance(elem)) added.add(type.cast(elem));
		}
		return added;
	}
	
	public void includeAdded(Object added, double x, double y)
	{
		if (!addedElements.keySet().contains(added)) addedElements.put(added,new Point2D.Double(x,y));
	}
	
	public Point2D.Double getAddedPosition(Object element)
	{
		return addedElements.get(element);
	}
	
	public void setAddedPosition(Object added, double x, double y)
	{
		if (addedElements.keySet().contains(added)) { addedElements.get(added).x=x; addedElements.get(added).y=y; }
	}
	
	@Override
	public String toString() {
		String result = new String();
		result += "Added:";
		for(Object elem: getAdded()){
			result += "\n"+(new ParsingElement((RefOntoUML.Element)elem,true,"")).toString();
		}
		result += "\nModified:";
		for(Object elem: getModified()){
			result += "\n"+(new ParsingElement((RefOntoUML.Element)elem,true,"")).toString();
		}
		result += "\nDeleted:";
		for(Object elem: getDeleted()){
			result += "\n"+(new ParsingElement((RefOntoUML.Element)elem,true,"")).toString();
		}
		return result;
	}
	
}
