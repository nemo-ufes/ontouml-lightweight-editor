package br.ufes.inf.nemo.ontouml2alloy.transformer;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Model;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl;
import br.ufes.inf.nemo.alloy.util.AlloyResourceFactoryImpl;
import br.ufes.inf.nemo.ontouml2alloy.mapper.NamesMapper;

/**
 *	This class is used as a Reader for the RefOntoUML.Model, performing the
 *  transformation of every element in the model.
 *  
 * 	@author John Guerson 
 *  @author Tiago Sales 
 *  @author Lucas Thom 
 */

public class Transformation {
	
	/** 
	 *  Provide a names mapper for ontouml model.
	 *  This mapper is used for associate the elements of the ontouml model 
	 *  with their modified names (i.e. without special characters: #, !, @, $, %, and etc...). 
	 */
	public static NamesMapper mapper;
	
	/** 
	 * Performs the transformation of ontouml elements. 
	 */
	public static Transformer transformer;
	
	/** 
	 * Alloy model resource. 
	 */
	public static Resource alsresource;
	
	/**
	 * Initialize the Reader and starts the transformation...
	 *  
	 * @param m: The root of .refontouml model (RefOntoUML.Model).
	 */	 
	public static void start(Model m)
	{
		mapper = new NamesMapper(m);
		
		transformer = new Transformer(mapper);		
		
		transformer.initialAditions();
		
		// Classifiers
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof Classifier) 
				
				transformer.transformClassifier( (Classifier)pe );			
		}
				
		// Generalizations
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof Class)
			{
				for(Generalization gen : ((Class)pe).getGeneralization())
				{
					transformer.transformGeneralizations(gen);
				}
			}
		}
		
		// GeneralizationSets
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof GeneralizationSet) 
				
				transformer.transformGeneralizationSets((GeneralizationSet) pe);			
		}
		
		// Associations
		for (PackageableElement pe : mapper.elementsMap.keySet())
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
						
		transformer.finalAdditions();
		
		createAlsResource();
		
		alsresource.getContents().add(transformer.module);
		
		saveAlsResourceToFile();		
	}	
			
	/** 
	 * Init Alloy Resource 'alsresource'. 
	 */
	public static void createAlsResource() 
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new AlloyResourceFactoryImpl() );
		resourceSet.getPackageRegistry().put(AlloyPackage.eNS_URI,AlloyPackage.eINSTANCE);
		AlloyPackageImpl.init();
		alsresource = resourceSet.createResource(URI.createURI("models/out.xmi"));
	}
	
	/** 
	 * Save 'alsresource' content into a file (.als). 
	 */
	public static void saveAlsResourceToFile() 
	{		
		try{			
			FileWriter fstream = new FileWriter(OntoUML2Alloy.alsPath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(alsresource.getContents().get(0).toString());			
			out.close();
		  }catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
		  }		
	}	
}
