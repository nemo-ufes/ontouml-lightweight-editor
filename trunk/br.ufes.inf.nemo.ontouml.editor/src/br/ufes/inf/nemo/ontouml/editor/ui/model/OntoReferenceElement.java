package br.ufes.inf.nemo.ontouml.editor.ui.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;

/**
 * This class encapsulates a model element of Carraretto's version of the OntoUML language.
 * 
 * @author John
 *
 */
public class OntoReferenceElement extends ElementAdapter {

	String name;
	String customInfo;
	
	public OntoReferenceElement(EObject eo) 
	{
		super(eo);		
		name = getName();
		customInfo = getCustomInfo();
	}
	
	@Override
	public String getName() 
	{
		if (element instanceof NamedElement) return ((NamedElement)element).getName();
		else return "#Unnamed"; 
	}	
	
	public String getCustomInfo()
	{
		if (element instanceof Generalization)
		{
			RefOntoUML.Classifier c = ((Generalization)element).getGeneral();			
			if (c!=null) return c.getName();
		}
		else if (element instanceof GeneralizationSet)
		{	
			String result = new String();
		    RefOntoUML.GeneralizationSet genset = (RefOntoUML.GeneralizationSet)element;
		    if (genset.parent()!=null)
		    {
		    	result += (genset.parent()).getName()+"{ ";
			    EList<Generalization> genlist = ((RefOntoUML.GeneralizationSet)element).getGeneralization();		    
			    int i=1;
			    for(Generalization gen: genlist)
			    {
			    	if (gen.getSpecific()!=null)
			    	{
			    		if(i < genlist.size()) result += gen.getSpecific().getName()+", ";
			    		else result += gen.getSpecific().getName() ;
			    	}
			    	i++;
			    }
			    result += " } ";
		    }
		    return result;		    
		}
		else if (element instanceof RefOntoUML.Property)
		{						
			String TypeName = ((RefOntoUML.Property)element).getType().getName();
			String name = ((RefOntoUML.Property)element).getName();
			Integer lower = ((RefOntoUML.Property)element).getLower();
			Integer upper = ((RefOntoUML.Property)element).getUpper();
			String lowerString = lower.toString();
			String upperString = upper.toString();
			if (lower == -1) lowerString = "*";
			if (upper == -1) upperString = "*";						 
			return TypeName+" ("+name+")"+" ["+lowerString+","+upperString+"]";
			
		}
		return "";		
	}
	
	@Override
	public String toString()
	{
		if (element instanceof RefOntoUML.Property || element instanceof Generalization)
		{						 
			return type+" "+customInfo;			
		} else {
			return type+" "+name + " " +customInfo;			    
		}		
	}	
}
