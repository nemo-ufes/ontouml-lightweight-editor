package br.ufes.inf.nemo.tocl.editor.completion;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.editor.completion.ModelTemplateCompletion;

public class TModelCompletionProvider {
	
	public DefaultCompletionProvider provider;
	public ArrayList<ModelTemplateCompletion> tAttributes = new  ArrayList<ModelTemplateCompletion>();
	public ArrayList<ModelTemplateCompletion> tEndPoints = new  ArrayList<ModelTemplateCompletion>();
	
	public TModelCompletionProvider(DefaultCompletionProvider provider)
	{
		this.provider = provider;
	}
	
	public void addCompletions(OntoUMLParser refparser)
	{
		for(RefOntoUML.Association p: refparser.getAllInstances(RefOntoUML.Association.class))
		{
		   addCompletion(p);
		}	
	}
	
	public void addCompletion(RefOntoUML.Association p)
	{
	   for(Property pp: p.getMemberEnd()){
		   addCompletion(pp);
	   }
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
		   
		   description = "<b>Property "+p.getName()+"[w]: "+p.getType().getName()+" ("+multiplicity+") </b><br><br>" +
		   	"Owner: "+owner+"<br>Returns a set of "+p.getType().getName()+" existent in the Wolrd w";
		   
		   ModelTemplateCompletion c = new ModelTemplateCompletion(provider, 
				p.getName()+"[w]",p.toString().substring(0,p.toString().indexOf(" "))+"[w]",
				"_'"+p.getName()+"'"+"[w]",
				p.getType().getName()+" ("+multiplicity+")"
				,description);		
		   
		    provider.addCompletion(c);
		    if(p.getAssociation()!=null) tEndPoints.add(c);
		    else tAttributes.add(c);		   
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
	
	public void removeCompletion(EObject elem)
    {
	   // Class and DataTypes
	   if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) 
	   {		 
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
	
	public void removeAllCompletions()
	{
		for(ModelTemplateCompletion c: tAttributes) provider.removeCompletion(c);
		for(ModelTemplateCompletion c: tEndPoints) provider.removeCompletion(c);		
		tAttributes.clear();
		tEndPoints.clear();
	}
	
	@SuppressWarnings("rawtypes")
    public void removeEndPoint(RefOntoUML.Property elem)
    {
	   Iterator it = tEndPoints.iterator();
	   while(it.hasNext())
	   {
		   ModelTemplateCompletion tc = (ModelTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString().substring(0,elem.toString().indexOf(" "))+"[w]"))
		   {
			   it.remove();
			   provider.removeCompletion(tc);			   
		   }
	   }	   
    }
	   
    @SuppressWarnings("rawtypes")
    public void removeAttribute(RefOntoUML.Property elem)
    {
	   Iterator it = tAttributes.iterator();
	   while(it.hasNext())
	   {
		   ModelTemplateCompletion tc = (ModelTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString().substring(0,elem.toString().indexOf(" "))+"[w]"))
		   {
			   it.remove();
			   provider.removeCompletion(tc);			  
		   }
	   }	   
    }
    
    public void updateCompletion(EObject elem)
    {
	   removeCompletion(elem);	   	   
	   if (elem instanceof RefOntoUML.Association) addCompletion((RefOntoUML.Association)elem);
	   else if (elem instanceof RefOntoUML.Property) addCompletion((RefOntoUML.Property)elem);
    }
}
