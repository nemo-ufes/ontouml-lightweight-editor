package br.ufes.inf.nemo.oled.finder;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.NamedElement;

public class ElementFound {

	protected EObject eobject;
	protected String name;
	protected String path;
	protected ArrayList<EObject> pathHierarchyList = new ArrayList<EObject>();
	protected String stereotype;
	
	public ElementFound(EObject eobject)
	{
		this.eobject = eobject;
		name = getName(eobject);
		path = getPath(eobject);
		stereotype = getStereotype(eobject);
	}
	
	public String getName() { return name; } 
	public String getType() { return stereotype; }
	public String getPath() { return path; }
	public ArrayList<EObject> getPathHierarchy() { return pathHierarchyList; }
	public EObject getElement() { return eobject; }
	
	private String getPath (EObject e)
	{
		String path = "";				
		if (e.eContainer()!=null) {
			path += getPath((e.eContainer()))+"::";				
		}
		path += getName(((NamedElement)e))+"";
		pathHierarchyList.add(e);
		return path;
	}	
	
	private String getStereotype(EObject e)
	{
		String stereotype = new String();
		stereotype = e.getClass().toString().replaceAll("class RefOntoUML.impl.","");
		stereotype = stereotype.replaceAll("Impl","");
		stereotype = Normalizer.normalize(stereotype, Normalizer.Form.NFD);	
	    if (!stereotype.equalsIgnoreCase("association")) stereotype = stereotype.replace("Association","");
	    return stereotype;
	}
	
	private String getName(EObject eobj)
	{
		if(eobj instanceof NamedElement){
			NamedElement c = (NamedElement)eobj;
			if (c.getName()==null) return "null";
			else if (c.getName().trim().isEmpty()) return"<empty>"; 
			else return c.getName();
		}else{
			return "<unnamed>";
		}
	}
}
