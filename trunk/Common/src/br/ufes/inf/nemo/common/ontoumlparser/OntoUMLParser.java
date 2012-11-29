package br.ufes.inf.nemo.common.ontoumlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 *  This class is used to read the model instance and to associate the Elements of the model 
 *  with their modified names into Hash Maps.
 */

public class OntoUMLParser {

	/** Name of the RefOntoUML Model root. */
	private String refmodelname;
	
	private HashMap<EObject,ParsingElement> elementHash; 
	
	/**
	 * Constructor.
	 *  
	 * @param refmodel: The root of .refontouml model (RefOntoUML.Model).
	 */	 
	public OntoUMLParser(RefOntoUML.Package refmodel)
	{
			
		elementHash = new HashMap<EObject,ParsingElement>();
		
		NameHandler h1 = new NameHandler();
		this.refmodelname = h1.treatName(refmodel.getName(),refmodel.getClass().toString());
		
		NameHandler h2 = new NameHandler();
		initializeElementList(refmodel, h2);
	}
	
	
	/**
	 * Constructor.
	 * 
	 * @param refontoumlPath: Absolute Path of the OntoUML Model.
	 * @throws IOException
	 */
	public OntoUMLParser(String refontoumlPath) throws IOException
	{
		Resource resource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
		Package refmodel = (Package)resource.getContents().get(0);
		
		elementHash = new HashMap<EObject,ParsingElement>();
		
		NameHandler h1 = new NameHandler();
		this.refmodelname = h1.treatName(refmodel.getName(),refmodel.getClass().toString());
		
		NameHandler h2 = new NameHandler();
		initializeElementList(refmodel, h2);
	}	

	/** 
	 * Initialize the Hash Maps.
	 * 
	 * @param rootpack: Usually the root of .refontouml model (RefOntoUML.Model).
	 */
	private void initializeElementList (PackageableElement rootpack, NameHandler h2) 
	{
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			add2ElementHash(p, h2);
			if(p instanceof Package) 
				initializeElementList(p, h2);
		}
	}		
	
	/**
	 * Add packageable Element to the respective HashMap.
	 * 
	 * @param pe: A Packageable Element of the ontouml model.
	 */
	private void add2ElementHash(PackageableElement pe, NameHandler h2)
	{
		ParsingElement e;
		
		if(pe instanceof Class){
			e = new ParsingElement(pe, true, h2.treatName(pe.getName(),pe.getClass().toString()));
			this.elementHash.put(pe,e);
			
			for (Generalization g : ((Class)pe).getGeneralization()) {
				e = new ParsingElement(g, true, h2.treatName("",g.getClass().toString()));
				this.elementHash.put(g,e);
			}
		}
		
		else if(pe instanceof Association){
			e = new ParsingElement(pe, true, h2.treatName(pe.getName(),pe.getClass().toString()));
			this.elementHash.put(pe,e);
			
			Property property0 = ((Association)pe).getMemberEnd().get(0);				
			Property property1 = ((Association)pe).getMemberEnd().get(1);

			e = new ParsingElement(property0, true, h2.treatName(property0.getName(),property0.getClass().toString()));
			this.elementHash.put(property0,e);
			
			e = new ParsingElement(property1, true, h2.treatName(property1.getName(),property1.getClass().toString()));
			this.elementHash.put(property1,e);
		}
		
		else{
			e = new ParsingElement(pe, true, h2.treatName(pe.getName(),pe.getClass().toString()));
			this.elementHash.put(pe,e);
		}
		
	}
	
	public void selectElements(ArrayList<EObject> list){
		for (ParsingElement pe : elementHash.values()) {
			if(list.contains(pe.getElement())){
				pe.setSelected(true);
			}
			else
				pe.setSelected(false);
		}
	}
	
	public Set<Property> getProperties()
	{
		Set<Property> list = new HashSet<Property>(); 
		
		for (EObject o : elementHash.keySet()) {
			if(o instanceof Property)
				list.add((Property) o);
		}
		
		return list;
	}
	
	public Set<PackageableElement> getPackageableElements()
	{
		Set<PackageableElement> list = new HashSet<PackageableElement>(); 
		
		for (EObject o : elementHash.keySet()) {
			if(o instanceof PackageableElement)
				list.add((PackageableElement) o);
		}
		
		return list;
	}
	
	public Set<Generalization> getGeneralizations()
	{
		Set<Generalization> list = new HashSet<Generalization>(); 
		
		for (EObject pe : elementHash.keySet()) {
			if(pe instanceof Generalization)
				list.add((Generalization) pe);
		}
		
		return list;
	}
	
	public String getAlias(EObject elem)
	{
		return elementHash.get(elem).getAlias();
	}
	
	public Boolean isSelected (EObject elem) {
		return elementHash.get(elem).getSelected();
	}
	
	public String getModelName()
	{
		return refmodelname;
	}	
	
	
}
