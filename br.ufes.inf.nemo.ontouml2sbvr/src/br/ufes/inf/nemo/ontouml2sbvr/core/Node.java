package br.ufes.inf.nemo.ontouml2sbvr.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import RefOntoUML.*;
import RefOntoUML.Class;

public class Node
{
	RefOntoUML.Class myclass;
	
	// All children
	LinkedList<Node> children;
	// Children without partition
	LinkedList<Node> schildren;
	// Children with partition
	LinkedList<Node> pchildren;
	
	// Has the node as member end
	LinkedList<Association> associations;
	// Has the node as first member end 
	LinkedList<Association> ownedAssociations;
	
	LinkedList<ChildPartition> childPartitions;
	HashMap<GeneralizationSet, ChildPartition> gs2partition;
	
	public Node (Class c)
	{
		myclass = c;
		
		children = new LinkedList<Node>();
		schildren = new LinkedList<Node>();
		pchildren = new LinkedList<Node>();
		
		childPartitions = new LinkedList<ChildPartition>();
		associations = new LinkedList<Association>();
		ownedAssociations = new LinkedList<Association>();
		
		gs2partition = new HashMap<GeneralizationSet, ChildPartition>();
	}
	
	public Class getRelatedClass()
	{
		return myclass;
	}
	
	public void addChild (Node child)
	{
		children.add(child);
	}
	
	public void addSChild (Node child)
	{
		schildren.add(child);
	}
	
	public void addPChild (Node child)
	{
		pchildren.add(child);
	}
	
	public void addChildPartition (GeneralizationSet gs, Node child)
	{
		ChildPartition cp = gs2partition.get(gs);
		if (cp == null)
		{
			cp = new ChildPartition(gs);			
			gs2partition.put(gs, cp);
			childPartitions.add(cp);
		}
		cp.addChild(child);
		
		pchildren.add(child);
	}
	
	public void addAssociation (Association a)
	{
		associations.add(a);
	}
	
	public void addOwnedAssociation (Association a)
	{
		ownedAssociations.add(a);
	}
	
	public boolean hasParents()
	{
		return myclass.parents().size() != 0;
	}
	
	public boolean hasChildren()
	{
		return children.size() != 0;
	}
	
	public boolean hasSChildren()
	{
		return schildren.size() != 0;
	}
	
	public boolean hasPChildren()
	{
		return pchildren.size() != 0;
	}
	
	public boolean hasAssociations()
	{
		return associations.size() != 0;
	}
	
	public boolean hasOwnedAssociations()
	{
		return ownedAssociations.size() != 0;
	}
	
	public boolean hasToggle()
	{
		return hasChildren() || hasAssociations();
	}
		
	public void PrintHierarchy()
	{
		System.out.println(myclass.getName());
		
		for (Node child : children)
		{
			child.PrintHierarchy();	
		}
	}
	
	public List<ChildPartition> getChildPartitions()
	{
		return childPartitions;
	}
	
	public List<Node> getChildren()
	{
		return children;
	}
	
	public List<Node> getSChildren()
	{
		return schildren;
	}
		
	public List<Association> getAssociations()
	{
		return associations;
	}
	
	public List<Association> getOwnedAssociations()
	{
		return ownedAssociations;
	}	
}
