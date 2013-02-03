package br.ufes.inf.nemo.ontouml2text.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import RefOntoUML.DataType;
import RefOntoUML.Package;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class Writer {
	
	/**
	 * The class that parses the model to obtain information
	 */
	OntoUMLParser ontoParser;
	/**
	 * Map that contains the class description (both model description and term definition)
	 */
	Map<Classifier, String[]> descrMap = new HashMap<Classifier, String[]>();
	
	/**
	 * Creates a Writer from an already instantiated OntoUML Parser
	 * @param ontoParser class that reads and interprets the model
	 */
	public Writer(OntoUMLParser ontoParser)
	{
		this.ontoParser = ontoParser;
	}
	
	/**
	 * Creates a Writer from a root OntoUML Package/Model, instantiating the OntoUML Parser
	 * @param root the root OntoUML Package/Model
	 */
	public Writer(Package root)
	{
		this.ontoParser = new OntoUMLParser(root);
	}
	
	public Map<Classifier, String[]> getDescriptionsMap()
	{
		return this.descrMap;
	}
	
	public void runDescriptionGeneration(Map<Association,Integer> assDirectionMap, boolean importDef, 
			boolean importGen, boolean importSpec, boolean importAtt, boolean importAssoc)
	{		
		Set<Classifier> classfSet = ontoParser.getAllInstances(Classifier.class);
		
		for (Classifier classf : classfSet)
		{
			if (classf instanceof Class || classf instanceof DataType)
			{
				String singleElemDescr = removeBar(classf.getName());
				
				if (importGen) singleElemDescr += processGeneralizations(classf);
				
				if (importSpec) singleElemDescr += processSpecializations(classf);
				
				if (importAtt) singleElemDescr += processAttributes(classf);
				
				if (importAssoc) singleElemDescr += processAssociations(classf, assDirectionMap);
				
				String elemDef = new String();
				if (importDef) elemDef = processDefinition(classf);
				
				String[] defs = {elemDef, singleElemDescr};
				
				descrMap.put(classf, defs);
			}
		}
	}

	private String processGeneralizations(Classifier classf)
	{
		String genDescr = new String();
			
		List<Generalization> genList = classf.getGeneralization();
		
		if (genList.size() > 0)
		{
			genDescr += Phrases.subtypesVerb();
			String delim = "";
			int i = 1;
			
			for (Generalization gen : genList)
			{
				genDescr += delim + removeBar(gen.getGeneral().getName());
				delim = ", ";
				i++;
				if (i == genList.size())
				{
					delim = Phrases.andVerb();
				}
			}
			genDescr += ". ";
		}
		
		return genDescr;
	}
	
	private String processSpecializations(Classifier classf)
	{
		String specDescr = new String();
		
		Set<Generalization> specList = ontoParser.getGeneralizations(classf);
		
		if (specList.size() > 0)
		{
			specDescr += Phrases.supertypesVerb();
			String delim = "";
			int i = 1;
			
			for (Generalization gen : specList)
			{
				specDescr += delim + removeBar(gen.getSpecific().getName());
				delim = ", ";
				i++;
				if (i == specList.size())
				{
					delim = Phrases.andVerb();
				}
			}
			specDescr += ". ";
		}
		
		return specDescr;
	}
	
	private String processAttributes(Classifier classf)
	{
		String attDescr = new String();
		
		List<Property> attList = classf.getAttribute();
		
		if (attList.size() > 0)
		{
			attDescr = Phrases.ownsVerb();
			String delim = "";
			int i = 1;
			
			for (Property prop : attList)
			{
				if (prop.getName() != null)
				{
					String mult = Phrases.processMultiplicity(prop);
					attDescr += delim + mult + " " + prop.getName();
					delim = ", ";
				}
				i++;
				if (i == classf.getAttribute().size() && delim.equals(", "))
				{
					delim = Phrases.andVerb();
				}
			}
			if (delim != "")
			{
				attDescr += ". ";
			}
		}
		
		return attDescr;
	}
	
	private String processAssociations(Classifier classf, Map<Association, Integer> assocMap)
	{
		String assocDescr = new String();
		
		if (assocMap == null)
		{
			return assocDescr;
		}
		
		Set<Entry<Association, Integer>> allAssoc = assocMap.entrySet();
		for (Entry<Association, Integer> entry : allAssoc)
		{
			Association assoc = entry.getKey();
			Integer i = entry.getValue();
			if (assoc.getMemberEnd().get(0).getType().equals(classf) || assoc.getMemberEnd().get(1).getType().equals(classf))
			{
				String verb, mult1, mult2;
				if (assoc.getName() == "" || assoc.getName() == null)
				{
					verb = Phrases.processVerb(assoc);
				}
				else
					verb = " " + removeBar(assoc.getName()) + " ";
				
				mult1 = Phrases.processMultiplicity(assoc.getMemberEnd().get(0));
				mult2 = Phrases.processMultiplicity(assoc.getMemberEnd().get(1));
				String member0 = assoc.getMemberEnd().get(0).getType().getName();
				String member1 = assoc.getMemberEnd().get(1).getType().getName();
				String singAssocDescr = mult1.substring(0, 1).toUpperCase()+mult1.substring(1, mult1.length())
						+" "+removeBar(member0)+verb+mult2+" "+removeBar(member1)+". ";
				String singAssocDescr2 = mult2.substring(0, 1).toUpperCase()+mult2.substring(1, mult2.length())
						+" "+removeBar(member1)+verb+mult1+" "+removeBar(member0)+". ";
				
				if (i == 1)
				{
					assocDescr += singAssocDescr;
				}
				if (i == -1)
				{
					assocDescr += singAssocDescr2;
				}
				if (i == 0)
				{
					assocDescr += singAssocDescr + singAssocDescr2;
				}
			}
		}
		
		return assocDescr;
	}
	
	private String processDefinition(Classifier classf)
	{
		List<Comment> commList = classf.getOwnedComment();
		
		for(Comment comment : commList)
		{
			if (comment.getBody().startsWith("Definition="))
			{
				return comment.getBody().replaceFirst("Definition=", "").replace("\n", " ");
			}
		}
		
		return "";
	}
	
	public void exportToCSS()
	{
		exportToCSS(this.descrMap);
	}
	
	public void exportToCSS(Map<Classifier, String[]> descrMap)
	{
		Set<Entry<Classifier, String[]>> allElem = descrMap.entrySet();
		try
		{
			FileWriter fstream = new FileWriter("modelDescription.csv");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("Name;Definition;Description\n");
			
			for (Entry<Classifier, String[]> entry : allElem)
			{
				Classifier elem = entry.getKey();
				String[] descr = entry.getValue();
				out.write(removeBar(elem.getName())+";"+descr[0]+";"+descr[1]+"\n");
			}
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private String removeBar(String name)
	{
		return name.replace("/ ", "").replace("/", "");
	}
	
	public String getTextDescription()
	{
		String textDescr = new String();
		Set<Entry<Classifier, String[]>> allElem = descrMap.entrySet();
		for (Entry<Classifier, String[]> entry : allElem)
		{
			String[] descr = entry.getValue();
			if (descr[0].equals("") || descr[0].equals(null))
				textDescr += descr[1]+"\n\n";
			else
				textDescr += descr[0]+"\n"+descr[1]+"\n\n";
		}
		return textDescr;
	}
	
}
