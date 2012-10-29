package br.ufes.inf.nemo.ontouml.antipattern.mapper;

import java.util.HashMap;
import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;

/**
 *  This class is used for associate the Elements of the refontouml model with their modified names.
 */

public class NamesMapper {

	/** Name of the root RefOntoUML Model. */
	public static String modelname;
	
	/** HashMap < PackageableElement modelElement, String modifiedName >. */
	public static HashMap<PackageableElement,String> elementsMap;
	
	/** HashMap < Property AssocEnd, String modifiedName > */
	public static HashMap<Property,String> assocEndMap;
		
	
	public NamesMapper(RefOntoUML.Model refmodel)
	{
		assocEndMap = new HashMap<Property,String>();
		elementsMap = new HashMap<PackageableElement,String>();
		
		c1 = new NamesCounter();	
		initModelName(refmodel);
		
		c2 = new NamesCounter();
		initHashMaps(refmodel);		
	}
	
	
	/** Performs modifications on names. */
	private static NamesCounter c1;
	
	/** Performs modifications on names. */
	private static NamesCounter c2;

	
	private static void initModelName(PackageableElement rootpack)
	{
		modelname = c1.treatName(rootpack.getName(),rootpack.getClass().toString());
	}
	
	
	private static void initHashMaps(PackageableElement rootpack) 
	{		
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			if(p instanceof Package) initHashMaps(p);
			else addToHashMaps(p);			
		}
	}		
	
	private static void addToHashMaps(PackageableElement pe)
	{
		if (pe instanceof Class || pe instanceof Association || ((pe instanceof DataType) && !(pe instanceof PrimitiveType)) || (pe instanceof GeneralizationSet)) 
		{
			if(pe instanceof Association)
			{
				Property property0 = ((Association)pe).getOwnedEnd().get(0);
				Property property1 = ((Association)pe).getOwnedEnd().get(1);
				
				if( (property0.getName() != null) && !(property0.getName().equals("")) )
				{
					assocEndMap.put(property0, c2.treatName(property0.getName(),property0.getClass().toString()));
				}
				if( (property1.getName() != null) && !(property1.getName().equals("")) )
				{
					assocEndMap.put(property1, c2.treatName(property1.getName(),property1.getClass().toString()));
				}
			}
			
			elementsMap.put(pe,c2.treatName(pe.getName(),pe.getClass().toString()));			
		}				
	}
	
}
