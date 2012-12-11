package ontouml2text.core;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.*;
import RefOntoUML.Class;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class Writer {
	
	/**
	 * The class that parses the model to obtain information
	 */
	OntoUMLParser ontoParser;

	public Writer (OntoUMLParser ontoParser)
	{
		this.ontoParser = ontoParser;
	}
	
	public String runDescriptionGeneration()
	{
		String modelDescr = new String();
		
		Set<Classifier> classfSet = ontoParser.getAllInstances(Classifier.class);
		
		for (Classifier classf : classfSet)
		{
			if (classf instanceof Class || classf instanceof DataType)
			{
				String singleElemDescr = classf.getName();
				
				singleElemDescr += processGeneralizations(classf);
				
				if (classf.getGeneralization().size() > 0 && classf.getAttribute().size() > 0)
				{
					singleElemDescr += Phrases.andVerb().substring(0, 2);
				}
				
				singleElemDescr += processAttributes(classf) + ". ";
				
				modelDescr += singleElemDescr + "\n";
			}
		}
		
		return modelDescr;
	}

	private String processGeneralizations (Classifier classf)
	{
		String genDescr = new String();
			
		EList<Generalization> genList = classf.getGeneralization();
		
		if (genList.size() > 0)
		{
			genDescr += Phrases.subtypesVerb();
			String delim = "";
			int i = 1;
			
			for (Generalization gen : genList)
			{
				genDescr += delim + gen.getGeneral().getName();
				delim = ", ";
				i++;
				if (i == genList.size())
				{
					delim = Phrases.andVerb();
				}
			}
		}
		
		return genDescr;
	}
	
	private String processAttributes (Classifier classf)
	{
		String attDescr = new String();
			
		EList<Property> attList = classf.getAttribute();
		
		if (attList.size() > 0)
		{
			attDescr = Phrases.ownsVerb();
			String delim = "";
			int i = 1;
			
			for (Property prop : attList)
			{
				String mult = Phrases.processMultiplicity(prop);
				attDescr += delim + mult + " " + prop.getName();
				delim = ", ";
				i++;
				if (i == classf.getAttribute().size())
				{
					delim = Phrases.andVerb();
				}
			}
		}
		
		return attDescr;
	}
	
	public String processAssociations (Map<Association, Integer> assocMap)
	{
		String assocDescr = new String();
		
		Set<PackageableElement> pelSet = ontoParser.getAllInstances(PackageableElement.class);
		
		for (PackageableElement pel : pelSet)
		{
			if (assocMap == null)
			{
				break;
			}
			
			if (pel instanceof Association)
			{
				Association assoc = (Association) pel;
				String verb, mult1, mult2;
				if (assoc.getName() == "" || assoc.getName() == null)
				{
					verb = Phrases.processVerb(assoc);
				}
				else
					verb = " " + assoc.getName() + " ";
				
				mult1 = Phrases.processMultiplicity(assoc.getMemberEnd().get(0));
				mult2 = Phrases.processMultiplicity(assoc.getMemberEnd().get(1));
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
	
}
