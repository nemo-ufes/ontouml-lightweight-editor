package br.ufes.inf.nemo.common.ontoumlparser;

import java.text.Normalizer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.Model;
import RefOntoUML.NamedElement;

/**
 * This class represents a Parsing Element. 
 * OntoUMLParser uses this to keep useful information about an OntoUML Element. 
 * 
 * @author John
 *
 */
public class ParsingElement {
	
	/** OntoUML Element. */
	private EObject element;
	
	/** Is Selected (for transformation purposes). */
	private Boolean selected;
	
	/** Alias Name. */
	private String alias = new String();
	
	/**
	 * Constructor.
	 * 
	 * @param element
	 * @param selected
	 * @param alias
	 */
	public ParsingElement (EObject element, Boolean selected, String alias)
	{
		this.element = element;
		this.selected = selected;
		this.alias = alias;
	}

	/** Get OntoUML Element. */
	public EObject getElement() { return element; }

	/** Set OntoUML Element. */
	public void setElement(EObject element) { this.element = element; }

	/** Verifies if the OntoUML Element Is Selected. */
	public Boolean getSelected() { return selected; }

	/** Set if the OntoUML Element is Selected or Not. */
	public void setSelected(Boolean selected) { this.selected = selected; }

	/** Get Alias Name. */
	public String getAlias() { return alias; }

	/** Set Alias Name. */
	public void setAlias(String alias) { this.alias = alias; }
	
	@Override
	public String toString() 
	{		
		String name = new String();
		String type = new String();
		
		if (element instanceof NamedElement) 
		{
			name = ((NamedElement)element).getName();
		}
		
		type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);	
	    
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
			String PropertyName = ((RefOntoUML.Property)element).getName();
			Integer lower = ((RefOntoUML.Property)element).getLower();
			Integer upper = ((RefOntoUML.Property)element).getUpper();
			String lowerString = lower.toString();
			String upperString = upper.toString();
			if (lower == -1) lowerString = "*";
			if (upper == -1) upperString = "*";
						 
			return type+" "+TypeName+" ("+PropertyName+")"+" ["+lowerString+","+upperString+"]";			
		}
		
		if (name == null || name.equals("")) 
		{	    
		    return type + " "+name;			
		}
		
		return "<"+name+">";
	}
}
