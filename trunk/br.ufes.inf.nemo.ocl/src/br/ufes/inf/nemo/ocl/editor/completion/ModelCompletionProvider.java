package br.ufes.inf.nemo.ocl.editor.completion;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.Enumeration;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class ModelCompletionProvider {
	
	public DefaultCompletionProvider provider;
	public ArrayList<ModelTemplateCompletion> classes = new  ArrayList<ModelTemplateCompletion>();
	public ArrayList<ModelTemplateCompletion> attributes = new  ArrayList<ModelTemplateCompletion>();
	public ArrayList<ModelTemplateCompletion> endpoints = new  ArrayList<ModelTemplateCompletion>();
	
	public ModelCompletionProvider(DefaultCompletionProvider provider)
	{
	  this.provider = provider;	    
	}
	   
	public void addCompletions(OntoUMLParser refparser)
	{
		for(EObject obj: refparser.getElements())
		{
		   	if(obj instanceof RefOntoUML.Class) addCompletion((RefOntoUML.Class)obj);
		   	if((obj instanceof DataType) && (!(obj instanceof PrimitiveType)) && (!(obj instanceof Enumeration))) addCompletion((RefOntoUML.DataType)obj);			   
		   	if((obj instanceof Association)) addCompletion((RefOntoUML.Association)obj);
	   }	   	
	}
	
	public void addCompletion(RefOntoUML.Association p)
	{
	   for(Property pp: p.getMemberEnd()){
		   addCompletion(pp);
	   }
	}  
   
	public void removeAllCompletions()
	{
		for(ModelTemplateCompletion c: classes) provider.removeCompletion(c);
		for(ModelTemplateCompletion c: attributes) provider.removeCompletion(c);
		for(ModelTemplateCompletion c: endpoints) provider.removeCompletion(c);
		classes.clear();
		attributes.clear();
		endpoints.clear();
	}
	
    public void addCompletion(RefOntoUML.Property p)
    {
	   if ((p.getName()!=null)&&!(p.getName().isEmpty())&&(p.getType()!=null))
	   {		   		   
		   String description = new String();
		   String owner = new String();
		   String multiplicity = new String();
		   
		   if (p.getAssociation()!=null) 
			   owner=getStereotype(p.getAssociation())+" "+p.getAssociation().getName();
		   else if (p.getAssociation()==null && p.getOwner()!=null)
			   owner = getStereotype(p.getOwner())+" "+((RefOntoUML.NamedElement)p.getOwner()).getName();
		   else
			   owner = getStereotype(p.eContainer())+" "+((RefOntoUML.NamedElement)p.eContainer()).getName();
		   
		   if (p.getLower()==p.getUpper() && p.getUpper()!=-1) multiplicity = Integer.toString(p.getLower());
			else if (p.getLower()==p.getUpper() && p.getUpper()==-1) multiplicity = "*";
			else if (p.getUpper()==-1) multiplicity = p.getLower()+".."+"*";
			else multiplicity = p.getLower()+".."+p.getUpper();
		   
		   description = "<b>Property "+p.getName()+": "+p.getType().getName()+" ("+multiplicity+") </b><br><br>" +
		   	"Owner: "+owner+"<br>Returns a set of "+p.getType().getName();
		   
		   ModelTemplateCompletion c = new ModelTemplateCompletion(provider, 
				p.getName(),p.toString().substring(0,p.toString().indexOf(" ")),
				"_'"+p.getName()+"'",
				p.getType().getName()+" ("+multiplicity+")"
				,description);		
		   
		    provider.addCompletion(c);
		    if(p.getAssociation()!=null) endpoints.add(c);
		    else attributes.add(c);		   
		}	   
    }	   

    public String getStereotype(EObject element)
    {
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
    }
	   
    public void addCompletion(RefOntoUML.Type oc)
    {
	   String description = new String();
	   description = ""+getStereotype(oc)+" <b>"+oc.getName()+"</b>";
			   
	   ModelTemplateCompletion c = new ModelTemplateCompletion(provider, 
			oc.getName(),oc.toString().substring(0,oc.toString().indexOf(" ")),
			"_'"+oc.getName()+"'",
			null,
			description);		
	    provider.addCompletion(c);
	    classes.add(c);	    
	    
	    if (oc instanceof RefOntoUML.Class){
	    	for(Property p: ((RefOntoUML.Class)oc).getOwnedAttribute()) addCompletion(p);	
	    }
	    if (oc instanceof RefOntoUML.DataType){
	    	for(Property p: ((RefOntoUML.DataType)oc).getOwnedAttribute()) addCompletion(p);	
	    }	    
    }
	   
    public void removeCompletion(EObject elem)
    {
	   // Class and DataTypes
	   if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) 
	   {
		    removeType((RefOntoUML.Type)elem);
		   	//Attributes
			if (elem instanceof Class){ for(Property p: ((Class)elem).getOwnedAttribute()) removeAttribute(p); }
			if (elem instanceof DataType){ for(Property p: ((DataType)elem).getOwnedAttribute()) removeAttribute(p); }
	   }
	   // Associations
	   else if (elem instanceof RefOntoUML.Association)
	   {
			//Properties			
			for(Property p: ((Association)elem).getMemberEnd()) { removeEndPoint((RefOntoUML.Property)p); }
	   }
	   // Properties
	   else if (elem instanceof RefOntoUML.Property) 
	   {
		   removeEndPoint((RefOntoUML.Property)elem);
	   }	   
    }
	   
    @SuppressWarnings("rawtypes")
    public void removeType(RefOntoUML.Type elem)
    {
	   Iterator it = classes.iterator();
	   while(it.hasNext())
	   {
		   ModelTemplateCompletion tc = (ModelTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString().substring(0,elem.toString().indexOf(" "))))
		   {
			   it.remove();
			   provider.removeCompletion(tc);			   
		   }
	   }	   	   
    }
    
    @SuppressWarnings("rawtypes")
    public void removeEndPoint(RefOntoUML.Property elem)
    {
	   Iterator it = endpoints.iterator();
	   while(it.hasNext())
	   {
		   ModelTemplateCompletion tc = (ModelTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString().substring(0,elem.toString().indexOf(" "))))
		   {
			   it.remove();
			   provider.removeCompletion(tc);			   
		   }
	   }	   
    }
	   
    @SuppressWarnings("rawtypes")
    public void removeAttribute(RefOntoUML.Property elem)
    {
	   Iterator it = attributes.iterator();
	   while(it.hasNext())
	   {
		   ModelTemplateCompletion tc = (ModelTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString().substring(0,elem.toString().indexOf(" "))))
		   {
			   it.remove();
			   provider.removeCompletion(tc);			  
		   }
	   }	   
    }
    
    public void updateCompletion(EObject elem)
    {
	   removeCompletion(elem);
	   
	   if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) addCompletion((RefOntoUML.Type)elem);
	   else if (elem instanceof RefOntoUML.Association) addCompletion((RefOntoUML.Association)elem);
	   else if (elem instanceof RefOntoUML.Property) addCompletion((RefOntoUML.Property)elem);
    }
}
