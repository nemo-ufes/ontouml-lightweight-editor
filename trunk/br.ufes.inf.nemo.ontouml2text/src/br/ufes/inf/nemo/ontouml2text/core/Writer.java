package br.ufes.inf.nemo.ontouml2text.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.PrimitiveType;
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
	
//	GrammarChecker gc;
	
	/**
	 * Creates a Writer from an already instantiated OntoUML Parser
	 * @param ontoParser class that reads and interprets the model
	 */
	public Writer(OntoUMLParser ontoParser){}
	
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
			if (!(classf instanceof Association) && !(classf instanceof PrimitiveType))
			{
				String singleElemDescr = format(classf.getName()).substring(0,1).toUpperCase() + format(classf.getName()).substring(1);
				
				List<Generalization> genList = classf.getGeneralization();
				List<Classifier> specList = classf.children();
				List<Property> attList = getAttributes(classf);
				
				//Processing Generalizations
				if (importGen && genList.size() > 0)
				{
					singleElemDescr += " " + Phrases.subtypesVerb(); //TODO Get from UI
					String delim = " ";
					int i = 1;
					
					for (Generalization gen : genList)
					{
						singleElemDescr += delim + Phrases.article() + " " + format(gen.getGeneral().getName());
						delim = ", ";
						i++;
						if (i == genList.size())
						{
							delim = " " + Phrases.andVerb() + " "; //TODO Get from UI
						}
					}
//					CheckDocument document = new CheckDocument(singleElemDescr);
//					gc.analyze(document);
//					for (Mistake m : document.getMistakes())
//					{
//						if (m.getSuggestions().length > 1) System.out.println("Mais de uma sugestão para " + document.getText());
//						singleElemDescr = document.getText().substring(0, m.getStart()) + 
//								m.getSuggestions()[0] + document.getText().substring(m.getEnd());
//					}
				}
				
				//Processing Specializations
				if (importSpec && specList.size() > 0)
				{
					if (genList.size() == 0 || !importGen)
						singleElemDescr += " " + Phrases.supertypesVerb(); //TODO Get from UI
					else
						singleElemDescr += " " + Phrases.connector() + " " + Phrases.supertypesVerb(); //TODO Get from UI (connector gen/spec)
							
					String delim = " ";
					int i = 1;
					
					for (Classifier spec : specList)
					{
						singleElemDescr += delim + Phrases.article() + " " + format(spec.getName());
						delim = ", ";
						i++;
						if (i == specList.size())
						{
							delim = " " + Phrases.orVerb() + " "; //TODO Get from UI
						}
					}
//					CheckDocument document = new CheckDocument(singleElemDescr);
//					gc.analyze(document);
//					for (Mistake m : document.getMistakes())
//					{
//						System.out.println(document.getText());
//						System.out.println(m.getFullMessage());
//						if (m.getSuggestions().length > 1) System.out.println("Mais de uma sugestão para " + document.getText());
//						singleElemDescr = document.getText().substring(0, m.getStart()) + 
//								m.getSuggestions()[0] + document.getText().substring(m.getEnd());
//					}
				}
				
				//Process Attributes
				if (importAtt && attList.size() > 0)
				{
					if ((genList.size() == 0 || !importGen) && (specList.size() == 0 || !importSpec))
						singleElemDescr += " " + Phrases.ownsVerb(); //TODO Get from UI
					else
						//TODO Se for começo de frase tem que colocar letra maiúscula
						singleElemDescr += ". " + Phrases.ownsVerb().substring(0,1).toUpperCase()+
								Phrases.ownsVerb().substring(1, Phrases.ownsVerb().length()); //TODO Get from UI (connector toAtt)
					
					String delim = " ";
					int i = 1;
					
					for (Property prop : attList)
					{
						String mult = Phrases.processMultiplicity(prop); //TODO Get from UI
						singleElemDescr += delim + mult + " " + format(prop.getName());
						delim = ", ";
						i++;
						if (i == attList.size())
						{
							delim = " " + Phrases.andVerb() + " "; //TODO Get from UI
						}
					}
//					CheckDocument document = new CheckDocument(singleElemDescr);
//					gc.analyze(document);
//					for (Mistake m : document.getMistakes())
//					{
//						if (m.getSuggestions().length > 1) System.out.println("Mais de uma sugestão para " + document.getText());
//						singleElemDescr = document.getText().substring(0, m.getStart()) + 
//								m.getSuggestions()[0] + document.getText().substring(m.getEnd());
//					}
				}
				
				singleElemDescr += ". ";
				
				if (importAssoc) singleElemDescr += processAssociations(classf, assDirectionMap);
				
				String elemDef = new String();
				if (importDef) elemDef = processDefinition(classf);
				
				String[] defs = {elemDef, singleElemDescr};
				
				descrMap.put(classf, defs);
			}
		}
	}
	
	private List<Property> getAttributes(Classifier classf)
	{
		List<Property> attList = classf.getAttribute();
			
		for (Property prop : attList)
		{
			if (prop.getName() == null || prop.getAssociation() != null)
			{
				attList.remove(prop);
			}
		}
		
		return attList;
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
					verb = " " + Phrases.processVerb(assoc) + " ";
				}
				else
					verb = " " + format(assoc.getName()) + " ";
				
				mult1 = Phrases.processMultiplicity(assoc.getMemberEnd().get(0));
				mult2 = Phrases.processMultiplicity(assoc.getMemberEnd().get(1));
				String member0 = assoc.getMemberEnd().get(0).getType().getName();
				String member1 = assoc.getMemberEnd().get(1).getType().getName();
				String singAssocDescr = mult1.substring(0, 1).toUpperCase()+mult1.substring(1, mult1.length())
						+" "+format(member0)+verb+mult2+" "+format(member1)+". ";
				String singAssocDescr2 = mult2.substring(0, 1).toUpperCase()+mult2.substring(1, mult2.length())
						+" "+format(member1)+verb+mult1+" "+format(member0)+". ";
				
//				CheckDocument document = new CheckDocument(singAssocDescr);
//				gc.analyze(document);
//				for (Mistake m : document.getMistakes())
//				{
//					if (m.getSuggestions().length > 1) System.out.println("Mais de uma sugestão para " + document.getText());
//					singAssocDescr = document.getText().substring(0, m.getStart()) + 
//							m.getSuggestions()[0] + document.getText().substring(m.getEnd());
//				}
//				
//				document.setText(singAssocDescr2);
//				gc.analyze(document);
//				for (Mistake m : document.getMistakes())
//				{
//					if (m.getSuggestions().length > 1) System.out.println("Mais de uma sugestão para " + document.getText());
//					singAssocDescr2 = document.getText().substring(0, m.getStart()) + 
//							m.getSuggestions()[0] + document.getText().substring(m.getEnd());
//				}
				
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
	
	public void exportToCSV(String path)
	{
		exportToCSV(this.descrMap, path);
	}
	
	public void exportToCSV(Map<Classifier, String[]> descrMap, String path)
	{
		Set<Entry<Classifier, String[]>> allElem = descrMap.entrySet();
		try
		{
			FileWriter fstream = new FileWriter(path+".csv");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("Name;Definition;Description\n");
			
			for (Entry<Classifier, String[]> entry : allElem)
			{
				Classifier elem = entry.getKey();
				String[] descr = entry.getValue();
				out.write(format(elem.getName())+";"+descr[0]+";"+descr[1]+"\n");
			}
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private String format(String name)
	{
		return name.replace("/", "").trim().toLowerCase();
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
