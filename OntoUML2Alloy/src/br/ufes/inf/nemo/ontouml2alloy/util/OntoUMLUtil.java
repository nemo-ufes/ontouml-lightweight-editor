package br.ufes.inf.nemo.ontouml2alloy.util;

import java.util.ArrayList;

import br.ufes.inf.nemo.ontouml2alloy.mapper.NamesMapper;

import RefOntoUML.AggregationKind;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;

/**
 * This class is used to provide useful methods of manipulating the OntoUML API.
 * 
 * @author John Guerson
 *
 */
public class OntoUMLUtil {

	/**
	 * Verify if the Classifier 'c' is the source of some Meronymic Relation.
	 *  
	 * @param c: RefOntoUML.Classifier
	 * @return
	 */
	public static boolean isSourceOfMeronymicRelation (NamesMapper refmapper, Classifier c)
	{
		for(PackageableElement pe : refmapper.elementsMap.keySet())
		{
			if (pe instanceof Meronymic) 
			{
				Meronymic assoc = (Meronymic)pe;				
				for( Property p : assoc.getMemberEnd())
				{
					if (!p.getAggregation().equals(AggregationKind.NONE))
					{
						if (p.getType().getName().equals(c.getName()))
						{							
							return true;
						}
					}
				}
			}
		}		
		return false;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if a Classifier 'c' is a General Classifier in a GeneralizationSet that is Disjoint and Complete
	 * What means that this Classifier is an Abstract Classifier.
	 * 
	 * @param c: Classifier c 
	 * @return
	 */
	public static boolean isAbstractFromGeneralizationSets(NamesMapper refmapper, Classifier c) 
	{
		for(PackageableElement pe : refmapper.elementsMap.keySet())
		{
			if(pe instanceof GeneralizationSet)
			{
				GeneralizationSet gs = ((GeneralizationSet)pe);
				if(gs.isIsCovering())
				{
					for(Generalization gen : gs.getGeneralization())
					{
						if(gen.getGeneral().getName() == c.getName()) 
						{							
							return true;
						}
					}
				}
			}
		}		
		return false;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Mediations relations that have as a source the Relator 'r' or one of its Super Types. 
	 * 
	 * @param list: Mediation Relations Names
	 * @param r: RefOntoUML.Relator
	 */
	public static void getAllMediations(NamesMapper refmapper, ArrayList<String> list, Relator r)
	{
		for(PackageableElement pe : refmapper.elementsMap.keySet())
		{
			if(pe instanceof Mediation)
			{
				Mediation assoc = (Mediation)pe;
				for( Property p : assoc.getMemberEnd())
				{
					if (p.getType() instanceof Relator)
					{
						if (p.getType().getName().equals(r.getName()))
						{
							list.add(refmapper.elementsMap.get(pe));							
						}
					}
				}
			}			
		}
		for(Generalization gen : ((Relator)r).getGeneralization())
		{							
			if (gen.getGeneral() instanceof Relator) getAllMediations(refmapper,list,(Relator)gen.getGeneral());
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Meronymic relations that have as a Whole the RigidSortalClass 'c' or one of its Super Types
	 * RigidSortalClass : Kind, Collective, Quantity, SubKind.
	 *   
	 * @param list: Meronymic Relations Names
	 * @param c: RefOntoUML.RigidSortalClass
	 * 
	 */	
	public static void getAllMeronymics(NamesMapper refmapper, ArrayList<String> list, RigidSortalClass c)
	{
		for(PackageableElement pe : refmapper.elementsMap.keySet())
		{
			if(pe instanceof Meronymic)
			{
				Meronymic assoc = (Meronymic)pe;
				for( Property p : assoc.getMemberEnd())
				{
					if (!p.getAggregation().equals(AggregationKind.NONE))
					{
						if (p.getType().getName().equals(c.getName()))
						{
							list.add(refmapper.elementsMap.get(pe));							
						}
					}
				}
			}
		}
		for(Generalization gen : ((RigidSortalClass)c).getGeneralization())
		{
			if (gen.getGeneral() instanceof RigidSortalClass) getAllMeronymics(refmapper,list,(RigidSortalClass)gen.getGeneral());
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Generalizations that the Classifier 'c' is the father.
	 * 
	 * @param generalizations: ArrayList RefOntoUML.Generalization
	 * @param c: RefOntoUML.Classifier
	 */
	public static void getAllGeneralizations(NamesMapper refmapper, ArrayList<Generalization> generalizations, Classifier c)
	{		
		for(PackageableElement elem : refmapper.elementsMap.keySet() )
		{
			if(elem instanceof Classifier)
			{
				for(Generalization gen : ((Classifier)elem).getGeneralization() )
				{
					if(((Generalization) gen).getGeneral().getName() == c.getName())
					{
						generalizations.add((Generalization) gen);
					}
				}
			}
		}
	}
	
}
