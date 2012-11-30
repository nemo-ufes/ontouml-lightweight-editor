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
import RefOntoUML.ObjectClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * This class is used to provide useful methods of manipulating the OntoUML model instances.
 * 
 * 	@authors Tiago Sales, John Guerson and Lucas Thom 
 *
 */
public class OntoUMLAPI {

	/**
	 * Verify if a Classifier 'c' is a General Classifier in a GeneralizationSet that is Disjoint and Complete
	 * Which means that this Classifier is an Abstract Classifier.
	 */
	public static boolean isAbstractFromGeneralizationSets(OntoUMLParser ontoparser, Classifier c) 
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
						if (ontoparser.isSelected(gen))
						{
							if(gen.getGeneral().equals(c)) return true;
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
	public static void getAllMediationsNames(OntoUMLParser ontoparser, ArrayList<String> list, Relator r)
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
						if (p.getType().equals(r))
						{
							list.add(ontoparser.getAlias(pe));							
						}
					}
				}
			}			
		}
		for(Generalization gen : ((Relator)r).getGeneralization())
		{						
			if (ontoparser.isSelected(gen))
			{
				if (gen.getGeneral() instanceof Relator) getAllMediationsNames(ontoparser,list,(Relator)gen.getGeneral());
			}
		}
	}
		
	/* =========================================================================================================*/
	
	/**
	 * Get all Mediations that have as a source the Relator 'r' or one of its Super Types. 
	 */
	public static void getAllMediations(OntoUMLParser ontoparser, ArrayList<Mediation> list, Relator r)
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
						if (p.getType().equals(r))
						{
							list.add((Mediation)pe);							
						}
					}
				}
			}			
		}
		for(Generalization gen : ((Relator)r).getGeneralization())
		{						
			if(ontoparser.isSelected(gen))
			{
				if (gen.getGeneral() instanceof Relator) getAllMediations(ontoparser,list,(Relator)gen.getGeneral());
			}
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Meronymic relations Names that have as a Whole the RigidSortalClass 'c' or one of its Super Types
	 * 
	 * RigidSortalClass : Kind, Collective, Quantity, SubKind.
	 * 
	 */
	public static void getAllMeronymics(OntoUMLParser ontoparser, ArrayList<String> list, Classifier c)
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
						if (p.getType().equals(c))
						{
							list.add(ontoparser.getAlias(pe));							
						}						
					}
				}
			}
		}
		for(Generalization gen : c.getGeneralization())
		{
			if (ontoparser.isSelected(gen))
			{
				if (gen.getGeneral() instanceof ObjectClass) getAllMeronymics(ontoparser,list,gen.getGeneral());
			}
		}
	}	
}
