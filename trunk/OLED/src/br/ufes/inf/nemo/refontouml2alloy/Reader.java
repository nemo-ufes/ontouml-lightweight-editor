package br.ufes.inf.nemo.refontouml2alloy;

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

import br.ufes.inf.nemo.refontouml2alloy.mapper.NamesMapper;

/**
 *	This class is used as a Reader for the transformation.
 */

public class Reader {
	
	public static Transformer transformer;	
	
	public static NamesMapper mapper;
	

	public static void init(Model m)
	{	
		transformer = new Transformer();
		
		mapper = new NamesMapper(m);
			
		setDefaultSigsTransformer(m);	
		
		transformer.init();				
	}	
	
	private static void setDefaultSigsTransformer(Package p) 
	{					
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof ObjectClass) 
			{
				transformer.defaultSignatures.add("Object");
				break;
			}
		}
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof MomentClass) 
			{
				transformer.defaultSignatures.add("Property");
				break;
			}
		}
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) 
			{
				transformer.defaultSignatures.add("DataType");
				break;
			}
		}
	}
	
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
	
	private static void callTransformationClassifiers() 
	{
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof Classifier) 
			{
				transformer.transformClassifier( (Classifier)pe );								
			}
		}
	}	
	
	private static void callTransformationGeneralizations() 
	{		
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
	}
	
	private static void callTransformationGeneralizationSets() 
	{		
		for (PackageableElement pe : mapper.elementsMap.keySet())
		{			
			if (pe instanceof GeneralizationSet)
			{
				transformer.transformGeneralizationSets((GeneralizationSet) pe);
			}
		}
	}
	
	private static void callTransformationAssociations() 
	{		
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
	}
	
}
