package br.ufes.inf.nemo.common.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.parser.NameHandler;

import br.ufes.inf.nemo.common.resource.ResourceUtil;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;

/**
 *  This class is used to read the model instance and to associate the Elements of the model 
 *  with their modified names into Hash Maps.
 */

public class OntoUMLParser {

	/** Name of the RefOntoUML Model root. */
	private String refmodelname;
	
	/** Associate the Packageable Elements of the model with their modified names. */
	private HashMap<PackageableElement,String> elementsMap;
	
	/** Associate the Propeties of the model (AssociationEnds) with their modified names. */
	private HashMap<Property,String> assocEndMap;
	
	/** Performs modification on model name. */
	private NameHandler h1;
	
	/** Performs modifications on names. */
	private NameHandler h2;

	/**
	 * Constructor.
	 *  
	 * @param refmodel: The root of .refontouml model (RefOntoUML.Model).
	 */	 
	public OntoUMLParser(RefOntoUML.Package refmodel)
	{
		assocEndMap = new HashMap<Property,String>();
		elementsMap = new HashMap<PackageableElement,String>();
		
		h1 = new NameHandler();
		this.refmodelname = h1.treatName(refmodel.getName(),refmodel.getClass().toString());
		
		h2 = new NameHandler();
		initializeHashMaps(refmodel);		
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
		RefOntoUML.Model refmodel = (RefOntoUML.Model)resource.getContents().get(0);
		
		assocEndMap = new HashMap<Property,String>();
		elementsMap = new HashMap<PackageableElement,String>();
		
		h1 = new NameHandler();
		this.refmodelname = h1.treatName(refmodel.getName(),refmodel.getClass().toString());
		
		h2 = new NameHandler();
		initializeHashMaps(refmodel);
	}	

	/** 
	 * Initialize the Hash Maps.
	 * 
	 * @param rootpack: Usually the root of .refontouml model (RefOntoUML.Model).
	 */
	private void initializeHashMaps (PackageableElement rootpack) 
	{
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			if(p instanceof Package) 
				initializeHashMaps(p);
			else 
				addToHashMaps(p);			
		}
	}		
	
	/**
	 * Add packageable Element to the respective HashMap.
	 * 
	 * @param pe: A Packageable Element of the ontouml model.
	 */
	private void addToHashMaps(PackageableElement pe)
	{
		if (pe instanceof Class || pe instanceof Association || ((pe instanceof DataType) && !(pe instanceof PrimitiveType)) || (pe instanceof GeneralizationSet)) 
		{
			if(pe instanceof Association)
			{				
				Property property0 = ((Association)pe).getMemberEnd().get(0);				
				Property property1 = ((Association)pe).getMemberEnd().get(1);

				if( (property0.getName() != null) && !(property0.getName().equals("")) )
				{
					assocEndMap.put(property0, h2.treatName(property0.getName(),property0.getClass().toString()));
				}
				if( (property1.getName() != null) && !(property1.getName().equals("")) )
				{
					assocEndMap.put(property1, h2.treatName(property1.getName(),property1.getClass().toString()));
				}
			}
			
			elementsMap.put(pe,h2.treatName(pe.getName(),pe.getClass().toString()));			
		}				
	}	
	
	
	public Set<Property> getProperties()
	{
		return assocEndMap.keySet();
	}
	
	public Set<PackageableElement> getPackageableElements()
	{
		return elementsMap.keySet();
	}
	
	public String getName(PackageableElement elem)
	{
		return elementsMap.get(elem);
	}
	
	public String getName(Property prop)
	{
		return assocEndMap.get(prop);
	}
	
	public String getModelName()
	{
		return refmodelname;
	}	
	
	
}
