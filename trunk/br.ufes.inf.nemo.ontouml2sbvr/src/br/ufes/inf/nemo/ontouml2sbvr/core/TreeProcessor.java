package br.ufes.inf.nemo.ontouml2sbvr.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import RefOntoUML.*;
import RefOntoUML.Class;

public class TreeProcessor
{
	LinkedList<Node> nodes;
	LinkedList<Node> mainNodes;
	HashMap<Class, Node> class2node;
	HashMap<String, Classifier> associationRoles;
	
	public TreeProcessor()
	{
		nodes = new LinkedList<Node>();
		mainNodes = new LinkedList<Node>();
		class2node = new HashMap<Class, Node>();
		associationRoles = new HashMap<String, Classifier>();
	}
	
	public void ProcessClass (Class c)
	{
		Node n = new Node(c);
		nodes.add(n);
		class2node.put(c, n);
	}
	
	public void ProcessAssociation (Association a)
	{
		// just binary associations (not treating derivation)
		if (a.getMemberEnd().size() != 2) return;
		
		Property p1, p2;
		Type t1, t2;
		Node n1, n2;
		
		p1 = a.getMemberEnd().get(0);
		p2 = a.getMemberEnd().get(1);
		t1 = p1.getType();
		t2 = p2.getType();
		
		if (t1 instanceof Class)
		{
			n1 = class2node.get((Class)t1);	
			n1.addAssociation(a);
			n1.addOwnedAssociation(a);
						
			if (t2 instanceof Class)
			{
				n2 = class2node.get((Class)t2);
				if (n1.getRelatedClass() != n2.getRelatedClass())
					n2.addAssociation(a);
			}
			
			if ((p1.getName() != null) && (p1.getName().length() != 0))
				associationRoles.put(p1.getName(), (Classifier)t1);
									
			if ((p2.getName() != null) && (p2.getName().length() != 0))
				associationRoles.put(p2.getName(), (Classifier)t2);
		}
	}
	
	public void ProcessNodes ()
	{
		// For every node, add children and child partitions
		for (Node n : nodes)
		{
			// Get the related class
			Class c = n.getRelatedClass();
			
			// Get the generalizations of the class
			for (Generalization g : c.getGeneralization())
			{
				// Get the parent
				Classifier parent = g.getGeneral();
				// Get the parent's node
				Node parentNode = class2node.get(parent);
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
		}
		
		// For every node, get the toplevel ones
		for (Node n : nodes)
		{
			if (!n.hasParents())
			{
				mainNodes.add(n);
			}
		}
	}
	
	public List<Node> getMainNodes()
	{
		return mainNodes;
	}
	
	public HashMap<String, Classifier> getAssociationRoles()
	{
		return associationRoles;
	}
}
