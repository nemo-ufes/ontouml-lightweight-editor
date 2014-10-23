package br.ufes.inf.nemo.ontouml2temporalowl.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import RefOntoUML.*;
import RefOntoUML.Class;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;

public class TreeProcessor
{
	public LinkedList<NodeClass> nodes;
	public List<NodeBinAssociation> assocNodes;
	static HashMap<Class, NodeClass> class2node;
	public static List<String> kindsNames = null;
	public static List<String> collectivesNames = null;
	public static List<String> quantitiesNames = null;
	public static List<String> relatorsNames = null;
	public static List<String> modesNames = null;
	public static List<String> qualitiesNames = null;
	public static List<String> relationalquasNames = null;
	public static List<String> phasedquasNames = null;

	public TreeProcessor (Package p)
	{
		nodes = new LinkedList<NodeClass>();
		class2node = new HashMap<Class, NodeClass>();
		assocNodes = new LinkedList<NodeBinAssociation>();

		/*
		 	To maintain conformance with the OLED structure changes,
		 	the 2 following code line were added and the for iteration
		 	was adjusted for correctly access the packaged elements
		 */
		OntoUMLParser ontoParser = new OntoUMLParser(p);
		Set<Object> packagedElements = ontoParser.getAllInstances(Object.class);
		
		// Pre Process
		for (Object pe : packagedElements) {
		//for (PackageableElement pe : p.getPackagedElement()){
			if (pe instanceof Class)
				ProcessClass((Class) pe);
			if (pe instanceof Association && !(pe instanceof Derivation))
				ProcessAssociation((Association) pe);
						
		}

		// Set up the specialization tree
		ProcessNodes();		
				
	}

	private void ProcessClass (Class c)
	{
		NodeClass n = new NodeClass(c);
		nodes.add(n);
		class2node.put(c, n);
		
		if (c instanceof Kind)
		{
			if (kindsNames == null)
				kindsNames = new LinkedList<String>();
			kindsNames.add(c.getName());
		}
		else if (c instanceof Collective)
		{
			if (collectivesNames == null)
				collectivesNames = new LinkedList<String>();
			collectivesNames.add(c.getName());
		}
		else if (c instanceof Quantity)
		{
			if (quantitiesNames == null)
				quantitiesNames = new LinkedList<String>();
			quantitiesNames.add(c.getName());
		}
		else if (c instanceof Relator)
		{
			if (relatorsNames == null)
				relatorsNames = new LinkedList<String>();
			relatorsNames.add(c.getName());
		}
		else if (c instanceof Mode)
		{
			if (modesNames == null)
				modesNames = new LinkedList<String>();
			modesNames.add(c.getName());
		}
	}

	private void ProcessAssociation(Association a) 
	{
		NodeBinAssociation na = new NodeBinAssociation(a);
		assocNodes.add(na);
		
		/*
		 	To maintain conformance with the OLED structure changes,
		 	the following code couldn't find the classes envolvend
		 	in an association. 
		*/
		//String 	domain = na.getDomain().getName(),
		//		range = na.getRange().getName();
		String domain = a.getMemberEnd().get(0).getType().getName(),
				range = a.getMemberEnd().get(1).getType().getName();
		
		if (a instanceof MaterialAssociation)
		{
			na.mappingName = a.getName() + domain + range;
			na.addSuperAssociation(a.getName());
		} 
		else if (a instanceof FormalAssociation)
		{
			na.mappingName = a.getName() + domain + range;
			na.addSuperAssociation(a.getName());
		}
		else if (a instanceof Meronymic)
		{
			if (a instanceof componentOf)
				na.mappingName = "componentOf";
			else if (a instanceof subCollectionOf)
				na.mappingName = "subCollectionOf";
			else if (a instanceof subQuantityOf)
				na.mappingName = "subQuantityOf";
			else if (a instanceof memberOf)
				na.mappingName = "memberOf";
			//adding superProperty
			na.addSuperAssociation(na.mappingName);
			//renaming association
			na.mappingName += domain + range;
		}
		else if (a instanceof DependencyRelationship)
		{
			//na.mappingName = null => the property is not created
			if (a instanceof Mediation)
				na.addSuperAssociation("mediates"); //to be restricted
			else if (a instanceof Characterization)
				na.addSuperAssociation("inheresIn"); //to be restricted
		}
	}
	
	private void ProcessNodes ()
	{
		// For every node, add children and child partitions
		for (NodeClass n : nodes)
		{
			// Get the related class
			Class c = n.getRelatedClass();
			
			// Get the generalizations of the class
			for (Generalization g : c.getGeneralization())
			{
				// Get the parent
				Classifier parent = g.getGeneral();
				// Get the parent's node
				NodeClass parentNode = class2node.get(parent);
				// Add the node as a child of the parent
				parentNode.addChild(n);
				
				if (g.getGeneralizationSet().size() != 0)
				{
					// Has partition
					GeneralizationSet gs = g.getGeneralizationSet().get(0);
					// Add the gs as a child partition of the parent
					parentNode.addChildPartition(gs, n);
				}
				else
				{
					// Does not have partition
					// Add the node as a Solitary Child of the parent
					parentNode.addSChild(n);
				}
			}
			
			if (n.isUltimateSortal())
			{
				if (c instanceof Kind)
					n.setDisjointSiblingsNames(kindsNames);
				else if (c instanceof Quantity)
					n.setDisjointSiblingsNames(quantitiesNames);
				else if (c instanceof Collective)
					n.setDisjointSiblingsNames(collectivesNames);
			}
		}
		
	}

	public List<NodeClass> getNodes()
	{
		return nodes;
	}
	
	public static NodeClass getNode (Class c)
	{
		return class2node.get(c);
	}		

}
