package br.ufes.inf.nemo.ontouml.editor.ui.model;
import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

/**
 * This class encapsulates a model element of any version of the OntoUML language.
 * 
 * @author John
 *
 */
public abstract class ElementAdapter {
	
	EObject element;	
	String type;
	
    public ElementAdapter (EObject eo)
    {
    	element = eo;
    	type = getType();
    }
    
    public EObject getElement() 
    { 
    	return element; 
    }
    
    public String getType ()
    {
    	String type = new String();
    	type = element.getClass().getSimpleName().replaceAll("Impl","");	    
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    return type;
    }
    
    public String toString()
    {    	
    	return ""+type+" "+getName();
    }
    
    public abstract String getName ();
}
