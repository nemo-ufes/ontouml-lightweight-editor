package br.ufes.inf.nemo.ontouml2temporalowl.tree;

import java.util.LinkedList;

import RefOntoUML.*;

public class ChildPartition
{
	GeneralizationSet gs;
	LinkedList<NodeClass> children;	
		
	public ChildPartition(GeneralizationSet gset)
	{
		gs = gset;
		children = new LinkedList<NodeClass>();
	}
	
	public void addChild (NodeClass child)
	{
		children.add(child);
	}
	
	public LinkedList<NodeClass> getChildren()
	{
		return children;
	}
	
	public GeneralizationSet getGS ()
	{
		return gs;
	}
}
