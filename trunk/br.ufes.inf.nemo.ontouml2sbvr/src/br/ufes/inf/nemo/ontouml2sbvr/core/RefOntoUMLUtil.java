package br.ufes.inf.nemo.ontouml2sbvr.core;

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;

public class RefOntoUMLUtil
{
	public static Classifier getGSetGeneral (GeneralizationSet gs)
	{
		return gs.getGeneralization().get(0).getGeneral();
	}	                                                     
	
	public static LinkedList<Classifier> getGSetSpecifics (GeneralizationSet gs)
	{
		LinkedList<Classifier> specifics = new LinkedList<Classifier>();
		
		for (Iterator<Generalization> gens2 = gs.getGeneralization().iterator(); gens2.hasNext();)
		{
			specifics.add(gens2.next().getSpecific());
		}
		
		return specifics;		
	}
	
	public static LinkedList<String> getGSetSpecificsName (GeneralizationSet gs)
	{
		LinkedList<String> specifics = new LinkedList<String>();
				
		for (Iterator<Generalization> gens2 = gs.getGeneralization().iterator(); gens2.hasNext();)
		{
			specifics.add(gens2.next().getSpecific().getName());
		}
		
		return specifics;
	}
	
	public static LinkedList<String> IncludedInCs (Class c)
	{
		LinkedList<String> gsets = new LinkedList<String>();
		
		for (Iterator<Generalization> it = c.getGeneralization().iterator(); it.hasNext();)
		{
			EList<GeneralizationSet> aux = it.next().getGeneralizationSet();
			if (aux.size() != 0)
			{
				GeneralizationSet gs = aux.get(0);
				gsets.add(getGSetName(gs));
			}
		}
		
		if (gsets.size() == 0)
			return null;
		
		return gsets;
	}
	
	public static String getGSetName (GeneralizationSet gs)
	{
		String gsName;
		
		if (gs.getName() != null)
		{
			// If it has a name
			gsName = gs.getName();
		}
		else
		{
			gsName = "";
			
			// The General
			gsName += getGSetGeneral(gs).getName() + ": ";
			
			// The Specifics
			LinkedList<String> specifics = getGSetSpecificsName(gs);
			for (Iterator<String> it = specifics.iterator(); it.hasNext();)
			{
				gsName += it.next();
				if (it.hasNext())
					gsName += ", ";
			}
		}
		
		return gsName;
	}
	
	/*public String getGSets (Class c)
	{
		String gsets = null;
		
		// For every Generalization, get the Generalization Set
		for (Iterator<Generalization> gens = c.getGeneralization().iterator(); gens.hasNext(); )
		{
			// Considering that every Generalization has at most one Generalization Set
			Generalization g = gens.next();
			
			// If there is a Generalization Set
			if (g.getGeneralizationSet().size() > 0)
			{
				GeneralizationSet gs = g.getGeneralizationSet().get(0);
				
				if (gsets == null)
					gsets = "";
				else
					gsets += " and ";
				
				gsets += getGSetName(gs);
			}
		}
					
		return gsets;
	}*/
}
