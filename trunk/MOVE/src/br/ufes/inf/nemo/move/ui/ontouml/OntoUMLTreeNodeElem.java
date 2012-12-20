package br.ufes.inf.nemo.move.ui.ontouml;

import java.text.Normalizer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.Model;
import RefOntoUML.NamedElement;

/**
 * @author John Guerson
 */

public class OntoUMLTreeNodeElem {
	
	private EObject element;
	private String name;
	private String type;
	private String uniqueName="";
	
	/** 
	 * Create a CheckBox Tree Node Element from Element.
	 * 
	 * @param refElement
	 */
	public OntoUMLTreeNodeElem (EObject refElement, String uniqueName) 
	{
		this.element = refElement;
		
		if (refElement instanceof NamedElement) 
		{
			this.name = ((NamedElement)refElement).getName();
		}
		
		type = refElement.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);	
	    
	    this.uniqueName= uniqueName; 
	}
	
	/**
	 * Create a CheckBox Tree Node Element from Name.
	 * 
	 * @param name
	 */
	public OntoUMLTreeNodeElem (String name) 
	{
		this.name = name;
	}	
	
	public String getUniqueName()
	{
		return uniqueName;
	}
	
	/**
	 * To String.
	 */
	@Override
	public String toString() 
	{
		if (element instanceof Model)
		{
			if (name ==null) name = ""; 
			return type + " " + name;
		}
		
		if (element instanceof Generalization)
		{			
			return type +" " + ((Generalization)element).getGeneral().getName();
		}
		
		if (element instanceof RefOntoUML.Classifier)
		{
			return type +" "+ name;
		}
		
		if (element instanceof RefOntoUML.GeneralizationSet)
		{		    
		    RefOntoUML.GeneralizationSet genset = (RefOntoUML.GeneralizationSet)element;
		    
		    String result = new String();
		    result += type + " " + name + " / "+(genset.getGeneralization().get(0).getGeneral()).getName()+" { ";
		    
		    EList<Generalization> genlist = ((RefOntoUML.GeneralizationSet)element).getGeneralization();		    
		    int i=1;
		    for(Generalization gen: genlist)
		    {
		    	if(i < genlist.size()) result += gen.getSpecific().getName()+", ";
		    	else result += gen.getSpecific().getName() + " } ";
		    	i++;
		    }
		    return result;		    
		}
		
		if (element instanceof RefOntoUML.Property)
		{
			String TypeName = ((RefOntoUML.Property)element).getType().getName();
			String name = ((RefOntoUML.Property)element).getName();
			Integer lower = ((RefOntoUML.Property)element).getLower();
			Integer upper = ((RefOntoUML.Property)element).getUpper();
			String lowerString = lower.toString();
			String upperString = upper.toString();
			if (lower == -1) lowerString = "*";
			if (upper == -1) upperString = "*";
						 
			return type+" "+TypeName+" ("+name+")"+" ["+lowerString+","+upperString+"]";			
		}
		
		if (name == null || name.equals("")) 
		{	    
		    return type + " "+name;			
		}
		
		return "<"+name+">";
	}
	
	/**
	 * Get Element.
	 */
	public EObject getElement() 
	{
		return element;
	}	
}
