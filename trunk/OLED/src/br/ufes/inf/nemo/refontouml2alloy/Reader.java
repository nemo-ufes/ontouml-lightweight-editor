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
import RefOntoUML.Category;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.SubKind;

public class Reader {
		
	public static Transformer transformer;	
	
	// HashMap < PackageableElement modelElement, String modifiedName >
	public static HashMap<PackageableElement,String> modelElementsMap;
	
	// HashMap < Property AssocEnd, String modifiedName >
	public static HashMap<Property,String> modelAssocEndMap;
	
	// default
	public static String modelName;
	
	// performs modifications on names
	public static StringCheck ch ;			
	
	/* ============================================================================*/
		
	public static void init(Model m)
	{	
		transformer = new Transformer();		
		modelAssocEndMap = new HashMap<Property,String>();
		modelElementsMap = new HashMap<PackageableElement,String>();
		ch = new StringCheck();
		modelName = "model";
		
		if(m != null) 
		{		
			initModelName(m);
			
			initModelElements(m);		
			
			setDefaultSigsTransformer(m);	
			
			transformer.init();
		}
	}
	
	/* ============================================================================*/
		
	private static void initModelName(Model m) 
	{				
		StringCheck ch = new StringCheck();		
		
		if((m.getName() != null)&&(m.getName() != "")) modelName = ch.removeSpecialNames(m.getName());				
	}
	
	/* ============================================================================*/
	
	private static void initModelElements(PackageableElement pack) 
	{		
		for(PackageableElement p : ((Package) pack).getPackagedElement())
		{
			if(p instanceof Package) initModelElements(p);
			else addModelElements(p);			
		}
	}		
	
	private static void addModelElements(PackageableElement pe)
	{
		if((pe instanceof Class || pe instanceof Association || pe instanceof DataType) && !(pe instanceof PrimitiveType))
		{
			if(pe instanceof Association)
			{
				Property property0 = ((Association)pe).getOwnedEnd().get(0);
				Property property1 = ((Association)pe).getOwnedEnd().get(1);
				
				if( (property0.getName() != null) && !(property0.getName().equals("")) )
				{
					modelAssocEndMap.put(property0, ch.removeSpecialNames(property0.getName()));
				}
				if( (property1.getName() != null) && !(property1.getName().equals("")) )
				{
					modelAssocEndMap.put(property1, ch.removeSpecialNames(property1.getName()));
				}
			}
			if( pe.getName() != null )
			{
				if(pe.getName().compareTo("") != 0)
					modelElementsMap.put(pe,ch.removeSpecialNames(pe.getName()));
				
				else if (pe.getName().compareTo("") == 0)
					modelElementsMap.put(pe,ch.removeSpecialNames(pe.getClass().toString()));
			}
		}else{
			modelElementsMap.put(pe,"#!");	
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
		
		if(transformer.ObjectsList.size() > 0) transformer.createExists("Object");		
		if(transformer.PropertiesList.size() > 0) transformer.createExists("Property");
		if(transformer.datatypesList.size() > 0) transformer.createExists("Datatype");	
		
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
				
				if ( ((Classifier)pe).isIsAbstract() ) transformer.createAbstractClause( (Classifier)pe );				

				if (
					(pe instanceof Kind) || (pe instanceof SubKind) || (pe instanceof Collective) || (pe instanceof Quantity) || 
					(pe instanceof Category) || (pe instanceof Relator) || (pe instanceof Mode) || ((pe instanceof DataType) && !(pe instanceof PrimitiveType))
				)
					transformer.rigidElements.add((Classifier)pe);
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
		}
		for (PackageableElement pe : modelElementsMap.keySet())
		{
			if (pe instanceof Derivation)
			{
				transformer.transformDerivations((Derivation) pe);
			}
		}
	}
	
	/* ============================================================================*/
	
	private static void callTransformationGeneralizations() 
	{		
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof Class)
				for(Generalization gen : ((Class)pe).getGeneralization())
					transformer.transformGeneralizations(gen);
		}
	}
	
	/* ============================================================================*/
	
	private static void callTransformationGeneralizationSets() 
	{		
		for (PackageableElement pe : modelElementsMap.keySet())
		{			
			if (pe instanceof GeneralizationSet)
				transformer.transformGeneralizationSets((GeneralizationSet) pe);
		}
	}
	
}
