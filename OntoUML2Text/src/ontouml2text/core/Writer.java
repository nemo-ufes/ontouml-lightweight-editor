package ontouml2text.core;

import java.util.Map;
import java.util.Set;

import RefOntoUML.*;
import RefOntoUML.Class;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class Writer {
	
	OntoUMLParser ontoParser;

	public Writer (OntoUMLParser ontoParser)
	{
		this.ontoParser = ontoParser;
	}

	public String processGeneralizations ()
	{
		String genDescr = new String();
		
		Set<Generalization> genSet = ontoParser.getGeneralizations();
		
		for (Generalization gen : genSet)
		{
			genDescr += gen.getSpecific().getName() + " é subtipo de " + gen.getGeneral().getName() + "\n";
		}
		
		return genDescr;
	}
	
	public String processAttributes ()
	{
		String attDescr = new String();
		
		Set<PackageableElement> pelSet = ontoParser.getPackageableElements();
		
		for (PackageableElement pel : pelSet)
		{
			if (pel instanceof Class || pel instanceof DataType)
			{
				Classifier classf = (Classifier) pel;
				
				for (Property prop : classf.getAttribute())
				{
					String mult = processMultiplicity(prop);
					attDescr += classf.getName() + " possui " + mult + " " + prop.getName() + "\n";
				}
			}
		}
		
		return attDescr;
	}
	
	public String processAssociations (Map<Association, Integer> assocMap)
	{
		String assocDescr = new String();
		
		Set<PackageableElement> pelSet = ontoParser.getPackageableElements();
		
		for (PackageableElement pel : pelSet)
		{
			if (pel instanceof Association)
			{
				Association assoc = (Association) pel;
				String verb, mult1, mult2;
				if (assoc.getName() == "" || assoc.getName() == null)
				{
					verb = processVerb(assoc);
				}
				else
					verb = " " + assoc.getName() + " ";
				
				mult1 = processMultiplicity(assoc.getMemberEnd().get(0));
				mult2 = processMultiplicity(assoc.getMemberEnd().get(1));
				String singAssocDescr = mult1 + " " + assoc.getMemberEnd().get(0).getType().getName() + verb +
						mult2 + " " + assoc.getMemberEnd().get(1).getType().getName() + "\n";
				String singAssocDescr2 = mult2 + " " + assoc.getMemberEnd().get(1).getType().getName() + verb +
						mult1 + " " + assoc.getMemberEnd().get(0).getType().getName() + "\n";
				
				if (assocMap.get(assoc) == 0)
				{
					assocDescr += singAssocDescr;
				}
				if (assocMap.get(assoc) == 1)
				{
					assocDescr += singAssocDescr2;
				}
				if (assocMap.get(assoc) == 2)
				{
					assocDescr += singAssocDescr + singAssocDescr2;
				}
			}
		}
		
		return assocDescr;
	}
	
	private String processVerb(Association assoc)
	{
		if (assoc instanceof Mediation)
		{
			return " media ";
		}
		else if (assoc instanceof Characterization)
		{
			return " caracteriza ";
		}
		else if (assoc instanceof Derivation)
		{
			return " deriva ";
		}
		else if (assoc instanceof componentOf)
		{
			return " é composto de ";
		}
		else if (assoc instanceof memberOf)
		{
			return " é membro de ";
		}
		else if (assoc instanceof subQuantityOf)
		{
			return " é sub-quantity de ";
		}
		else if (assoc instanceof subCollectionOf)
		{
			return " é sub-collection de ";
		}
		else 
			return " ";
	}
	
	private String processMultiplicity (Property prop)
	{
		String mult = new String();
		if (prop.getLower() == 1 && prop.getUpper() == 1)
		{
			mult += "um";
		}
		else if (prop.getLower() == 1 && prop.getUpper() == -1)
		{
			mult += "no mínimo um";
		}
		else if (prop.getLower() == 0 && prop.getUpper() == 1)
		{
			mult += "no máximo um";
		}
		else if (prop.getLower() == 0 && prop.getUpper() == -1)
		{
			mult += "zero ou mais";
		}
		else if (prop.getLower() == -1 && prop.getUpper() == -1)
		{
			mult += "zero ou mais";
		}
		else
			mult += "muitos";
		
		return mult;
	}
	
}
