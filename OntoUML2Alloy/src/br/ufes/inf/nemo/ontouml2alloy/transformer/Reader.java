package br.ufes.inf.nemo.ontouml2alloy.transformer;

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

import br.ufes.inf.nemo.ontouml2alloy.mapper.NamesMapper;

/**
 *	This class is used as a Reader for the RefOntoUML.Model, performing the
 *  transformation of ever single element in the model.
 *  
 *  @author Tiago Salles
 *  @author John Guerson    
 *  @author Luca Thom
 */

public class Reader {
	
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
	 * Initialize the Reader and starts the transformation...
	 *  
	 * @param m: The root of .refontouml model (RefOntoUML.Model).
	 */	 
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
