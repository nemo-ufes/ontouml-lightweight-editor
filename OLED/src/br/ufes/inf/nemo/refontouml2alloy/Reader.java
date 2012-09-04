package br.ufes.inf.nemo.refontouml2alloy;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.util.HashMap;


import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;

public class Reader {
		
	/* ============================================================================*/
	
	/** This class performs the transformation. */
	public static Transformer transformer;	
	
	/** HashMap < PackageableElement modelElement, String modifiedName >. */
	public static HashMap<PackageableElement,String> modelElementsMap;
	
	/** HashMap < Property AssocEnd, String modifiedName > */
	public static HashMap<Property,String> modelAssocEndMap;
	
	/** Model Name. */
	public static String modelName;
	
	/** Performs modifications on names */
	public static StringCheck ch ;
			
	/* ============================================================================*/
	
	/** Initial Method. The transformation begins here. */
	public static void init(Model m)
	{	
		transformer = new Transformer();
		
		modelAssocEndMap = new HashMap<Property,String>();
		modelElementsMap = new HashMap<PackageableElement,String>();
		
		ch = new StringCheck();		
		
		if(m != null) 
		{
			modelName = ch.removeSpecialNames(m.getName(),m.getClass().toString());		
			
			initHashMaps(m);
				
			setDefaultSigsTransformer(m);	
			
			// Here the transformation begins...
			transformer.init();
		}		
	}	
	
	/* ============================================================================*/
		
	private static void initHashMaps(PackageableElement pack) 
	{		
		for(PackageableElement p : ((Package) pack).getPackagedElement())
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
					modelAssocEndMap.put(property0, ch.removeSpecialNames(property0.getName(),property0.getClass().toString()));
				}
				if( (property1.getName() != null) && !(property1.getName().equals("")) )
				{
					modelAssocEndMap.put(property1, ch.removeSpecialNames(property1.getName(),property1.getClass().toString()));
				}
			}
			
			modelElementsMap.put(pe,ch.removeSpecialNames(pe.getName(),pe.getClass().toString()));			
		}				
	}
	
	/* ============================================================================*/
	
	private static void setDefaultSigsTransformer(Package p) 
	{					
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof ObjectClass) 
			{
				transformer.defaultSignatures.add("Object");
				break;
			}
		}
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof MomentClass) 
			{
				transformer.defaultSignatures.add("Property");
				break;
			}
		}
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) 
			{
				transformer.defaultSignatures.add("DataType");
				break;
			}
		}
	}
	
	/* ============================================================================*/
	
	public static void callTransformationModel() 
	{		
		callTransformationClassifiers();
		callTransformationGeneralizations();
		callTransformationGeneralizationSets();
		callTransformationAssociations();		
		
		if(transformer.objectNamesList.size() > 0) transformer.createExists("Object");		
		if(transformer.momentNamesList.size() > 0) transformer.createExists("Property");
		if(transformer.datatypeNamesList.size() > 0) transformer.createExists("Datatype");
		
		transformer.createKindDatatypePropertyDisjoint();
		transformer.finalAdditions();
	}
	
	/* ============================================================================*/
	
	private static void callTransformationClassifiers() 
	{
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof Classifier) 
			{
				transformer.transformClassifier( (Classifier)pe );								
			}
		}
	}	
	
	/* ============================================================================*/
	
	private static void callTransformationGeneralizations() 
	{		
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof Class)
			{
				for(Generalization gen : ((Class)pe).getGeneralization())
				{
					transformer.transformGeneralizations(gen);
				}
			}
		}
	}
	
	/* ============================================================================*/
	
	private static void callTransformationGeneralizationSets() 
	{		
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof GeneralizationSet)
			{
				transformer.transformGeneralizationSets((GeneralizationSet) pe);
			}
		}
	}
	
	/* ============================================================================*/
	
	private static void callTransformationAssociations() 
	{		
		for (PackageableElement pe : modelElementsMap.keySet())
		{
			if (pe instanceof Association && !(pe instanceof Derivation))
			{
				transformer.transformAssociations((Association) pe);
			}
			else if (pe instanceof Derivation)
			{
				transformer.transformDerivations((Derivation) pe);
			}
		}		
	}
	
	/* ============================================================================*/
	
	/** Print HashMap. This class is used for test. */
	public static void printModelElementsMap ()
	{		
		System.out.println("modelElementsMap < PackageableElement elem, String modifiedName >");
		for(PackageableElement p: modelElementsMap.keySet())
		{
			System.out.println("\""+p.getName()+"\"->\""+modelElementsMap.get(p)+"\"");			
		}
	}
	
	/* ============================================================================*/
	
	/** Print HashMap. This class is used for test. */
	public static void printModelAssocEndMap ()
	{		
		System.out.println("modelAssocEndMap <Property assocEnd,String modifiedName>");
		for(Property p: modelAssocEndMap.keySet())
		{
			System.out.println("\""+p.getName()+"\"->\""+modelAssocEndMap.get(p)+"\"");			
		}
	}
}
