package br.ufes.inf.nemo.ontouml2alloy.api;

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

import java.util.ArrayList;

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
import br.ufes.inf.nemo.ontouml2alloy.parser.Parser;

/**
 * This class is used to provide useful methods of manipulating the OntoUML model instances.
 * 
 * 	@authors Tiago Sales, John Guerson and Lucas Thom 
 *
 */
public class OntoUMLAPI {

	/**
	 * Verify if the Classifier 'c' is the source or target of some Meronymic Relation.
	 */
	public static boolean hasMeronymicRelation (Parser ontoparser, Classifier c)
	{
		for(PackageableElement pe : ontoparser.getPackageableElements())
		{
			if (pe instanceof Meronymic) 
			{
				Meronymic assoc = (Meronymic)pe;				
				for( Property p : assoc.getMemberEnd())
				{
					if(! p.getAggregation().equals(AggregationKind.NONE))
					{
						if (p.getType().getName().equals(c.getName())) return true;						
					}
				}
			}
		}		
		return false;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if the Classifier 'c' is the source or target of some Mediation Relation.
	 */
	public static boolean hasMediationRelation (Parser ontoparser, Relator c)
	{
		for(PackageableElement pe : ontoparser.getPackageableElements())
		{
			if (pe instanceof Mediation) 
			{
				for(Property p : ((Mediation)pe).getMemberEnd())
				{
					if(p.getType() instanceof Relator)
					{						
						if (p.getType().getName().equals(c.getName())) return true;
					}
				}								
			}
		}
		return false;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if a Classifier 'c' is a General Classifier in a GeneralizationSet that is Disjoint and Complete
	 * Which means that this Classifier is an Abstract Classifier.
	 */
	public static boolean isAbstractFromGeneralizationSets(Parser ontoparser, Classifier c) 
	{
		for(PackageableElement pe : ontoparser.getPackageableElements())
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
	 * Get all Mediations relations Names that have as a source the Relator 'r' or one of its Super Types. 
	 */
	public static void getAllMediationsNames(Parser ontoparser, ArrayList<String> list, Relator r)
	{
		for(PackageableElement pe : ontoparser.getPackageableElements())
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
							list.add(ontoparser.getName(pe));							
						}
					}
				}
			}			
		}
		for(Generalization gen : ((Relator)r).getGeneralization())
		{							
			if (gen.getGeneral() instanceof Relator) getAllMediationsNames(ontoparser,list,(Relator)gen.getGeneral());
		}
	}
		
	/* =========================================================================================================*/
	
	/**
	 * Get all Mediations that have as a source the Relator 'r' or one of its Super Types. 
	 */
	public static void getAllMediations(Parser ontoparser, ArrayList<Mediation> list, Relator r)
	{
		for(PackageableElement pe : ontoparser.getPackageableElements())
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
							list.add((Mediation)pe);							
						}
					}
				}
			}			
		}
		for(Generalization gen : ((Relator)r).getGeneralization())
		{							
			if (gen.getGeneral() instanceof Relator) getAllMediations(ontoparser,list,(Relator)gen.getGeneral());
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Meronymic relations Names that have as a Whole the RigidSortalClass 'c' or one of its Super Types
	 * 
	 * RigidSortalClass : Kind, Collective, Quantity, SubKind.
	 * 
	 */	
	public static void getAllMeronymics(Parser ontoparser, ArrayList<String> list, RigidSortalClass c)
	{
		for(PackageableElement pe : ontoparser.getPackageableElements())
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
							list.add(ontoparser.getName(pe));							
						}						
					}
				}
			}
		}
		for(Generalization gen : ((RigidSortalClass)c).getGeneralization())
		{
			if (gen.getGeneral() instanceof RigidSortalClass) getAllMeronymics(ontoparser,list,(RigidSortalClass)gen.getGeneral());
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Generalizations that the Classifier 'c' is the father.
	 */
	public static void getAllGeneralizations(Parser ontoparser, ArrayList<Generalization> generalizations, Classifier c)
	{		
		for(PackageableElement elem : ontoparser.getPackageableElements() )
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
	
	/* =========================================================================================================*/
	
	/**
	 *  Get all non-abstract descendants of Classifier c.
	 */
	public static void getConcreteDescendants(Parser ontoparser, ArrayList<Classifier> list, Classifier c)
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		getAllGeneralizations(ontoparser, generalizations, c);
		for(Generalization g: generalizations)
		{
			if (! g.getSpecific().isIsAbstract())
			{
				if (!list.contains((Classifier)g.getSpecific()))
				{
					list.add((Classifier)g.getSpecific());
				}
			}
			getConcreteDescendants(ontoparser, list, g.getSpecific());
		}
	}	
	
	/* =========================================================================================================*/
	
	/**
	 * Get all descendants of Classifier c.
	 */
	public static void getDescendants(Parser ontoparser, ArrayList<Classifier> list, Classifier c)
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		getAllGeneralizations(ontoparser, generalizations, c);
		for(Generalization g: generalizations)
		{
			if (!list.contains((Classifier)g.getSpecific()))
			{
				list.add((Classifier)g.getSpecific());
			}			
			getDescendants(ontoparser, list, g.getSpecific());			
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if the lists have at least one element in common.
	 */
	public static Classifier isOverlapping (ArrayList<Classifier> list1, ArrayList<Classifier> list2)
	{
		for (Classifier c1: list1)
		{
			for (Classifier c2: list2)
			{
				if (c2.equals(c1)) return c1;
			}
		}
		return null;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if the Classifier 'c' is a Top Level Classifier.
	 */
	public static boolean isTopLevel (Classifier c)
	{
		if (((Classifier) c).getGeneralization().size() == 0 )
		{
			return true;
		}
		return false;
	}
	
}
