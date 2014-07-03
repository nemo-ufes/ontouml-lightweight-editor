package br.ufes.inf.nemo.ontouml2temporalowl.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.*;
import RefOntoUML.Class;

public class NodeClass
{
	RefOntoUML.Class myclass;
	
	// All children
	LinkedList<NodeClass> children;
	// Children without partition
	LinkedList<NodeClass> schildren;
	// Children with partition
	LinkedList<NodeClass> pchildren;
	
	List<String> siblings = null;
	
	// Has the node as member end
	LinkedList<Association> associations;
	// Has the node as first member end 
	LinkedList<Association> ownedAssociations;
	
	LinkedList<ChildPartition> childPartitions;
	HashMap<GeneralizationSet, ChildPartition> gs2partition;
	
	public NodeClass (Class c)
	{
		myclass = c;
		
		children = new LinkedList<NodeClass>();
		schildren = new LinkedList<NodeClass>();
		pchildren = new LinkedList<NodeClass>();
		
		childPartitions = new LinkedList<ChildPartition>();
		associations = new LinkedList<Association>();
		ownedAssociations = new LinkedList<Association>();
		
		gs2partition = new HashMap<GeneralizationSet, ChildPartition>();
	}
	
	public String getName()
	{
		return myclass.getName();
	}

	public String getName(Boolean addTS)
	{
		return myclass.getName() + (addTS ? "TS" : "");
	}

	public String getTSName()
	{
		return getName(true);
	}
	
	public String getReifiedName()
	{
		if (isRigid())
			return myclass.getName();
		else
			return "Qua" + myclass.getName();
	}

	public Boolean isRigid()
	{
		return ((myclass instanceof RigidMixinClass) || (myclass instanceof RigidSortalClass) 
				|| (myclass instanceof MomentClass));
	}

	public Boolean isUltimateSortal()
	{
		return (myclass instanceof SubstanceSortal);
	}

	public Boolean isMoment()
	{
		return (myclass instanceof MomentClass);
	}

	public Class getRelatedClass()
	{
		return myclass;
	}
	
	public void addChild (NodeClass child)
	{
		children.add(child);
	}
	
	public void addSChild (NodeClass child)
	{
		schildren.add(child);
	}
	
	public void addChildPartition (GeneralizationSet gs, NodeClass child)
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
	
	public List<ChildPartition> getChildPartitions()
	{
		return childPartitions;
	}
	
	public List<NodeClass> getChildren()
	{
		return children;
	}
		
	public List<String> getDisjointSiblingsNames(Boolean addTS, Boolean reification)
	{
		if (isUltimateSortal())
		{
			if (!addTS)
				return siblings;
			else 
			{
				List<String> siblingsTS = new LinkedList<String>();
				for (String s : siblings)
					siblingsTS.add(s + "TS");
				return siblingsTS;
			}
		}
		
		List<String> disjointClasses = new LinkedList<String>();
	
		// For every Generalization of the Class
		for (Generalization g : myclass.getGeneralization())
		{
			EList<GeneralizationSet> gsets = g.getGeneralizationSet();
			
			// If the Generalization has a Generalization Set
			if (gsets.size() > 0)
			{
				GeneralizationSet gs = gsets.get(0);
				
				// If the Generalization Set is Disjoint
				if (gs.isIsDisjoint())
				{
					// For every Generalization of such Generalization Set (generalization of the brothers)
					for (Generalization bg : gs.getGeneralization())
						if (bg != g)
						{
							NodeClass n = TreeProcessor.getNode((Class) bg.getSpecific());
							if (reification)
								disjointClasses.add(n.getReifiedName());
							else
								disjointClasses.add(n.getName(addTS));
						}
				}
			}
		}
		if (disjointClasses.size() > 0)
			return disjointClasses;
		else
			return null;
	}

	public void setDisjointSiblingsNames(List<String> lsiblings)
	{
		if (lsiblings == null) return;
		if (siblings == null)
			siblings = new LinkedList<String>();
		String name = getName(); 
		for (String s : lsiblings)
			if (!name.equals(s))
				siblings.add(s);
	}
	
/*	public List<String> getDijointSiblingsNames(Boolean addTS)
	{
		if (addTS && (siblings != null))
		{
			List<String> l = new LinkedList<String>();
			for (String s : siblings)
				l.add(s + "TS");
			return l;
		}
		return siblings;
	}
*/	
	public String getStructuralParent()
	{
		Class c = myclass;
		if (c instanceof Kind)
			return "FunctionalComplex";
		else if (c instanceof Collective)
			return "Collective";
		else if (c instanceof Quantity)
			return "Quantity";
		else if (c instanceof Relator)
			return "Relator";
		else if (c instanceof Mode)
			return "Mode";
		else if (c instanceof Role)
			return "RelationalQuaIndividual";
		else if (c instanceof Phase)
			return "PhasedQuaIndividual";
		else return null;
	}
	
	public List<String> getParentsNames(Boolean addTS)
	{
		if (isUltimateSortal())
		{
			List<String> lParents = new LinkedList<String>();
			lParents.add(getStructuralParent() + (addTS ? "TS" : "") );
			return lParents;
		}
		return getParentsNames(false, addTS);
	}

	public List<String> getRigidParentsNames(Boolean addTS)
	{
		return getParentsNames(true, addTS);
	}
	
	private List<String> getParentsNames(Boolean justRigid, Boolean addTS)
	{
		List<String> lStParents = null;
		if (isMoment()) // || ((getStructuralParent() != null) && !reified))
		{
			lStParents = new LinkedList<String>();
			lStParents.add(getStructuralParent() + (addTS ? "TS" : ""));
		}

		List<String> lparents = null;
		if (myclass.parents() != null)
		{
			lparents = new LinkedList<String>();
			List<Classifier> classifierList = new LinkedList<Classifier>();
			List<Classifier> auxList = new LinkedList<Classifier>();

			// recursively looking for non-reified parent(s)
			classifierList.addAll(myclass.parents());
			while ((lparents.isEmpty()) && !classifierList.isEmpty())
			{
				for (Classifier cp : classifierList)
				{
					NodeClass n = TreeProcessor.getNode((Class) cp);
					if (!justRigid || n.isRigid())
						lparents.add(n.getName(addTS));
					auxList.addAll(cp.parents());
				}
				//if a suitable parent is not found, 
				//it is to be looked for among the parents of parents
				if (lparents.isEmpty())
				{
					classifierList.clear();
					classifierList.addAll(auxList);
					auxList.clear();
				}
			}
		}

		if ((lStParents != null) && !lStParents.isEmpty())
		{
			if (lparents != null)
			{
				lStParents.addAll(lparents);
			}
			return lStParents;
		}
		else if ((lparents != null) && !lparents.isEmpty())
			{
				return lparents;
			}
		else
			return null;
	}

	public List<String> getReifiedParentsNames()
	{
		//FIXME: Verify
		//if (!reified) return null;
		List<String> lparents = null;
		Class c = myclass;
		if (c.parents() != null)
		{
			lparents = new LinkedList<String>();
			List<Classifier> classifierList = new LinkedList<Classifier>();
			List<Classifier> auxList = new LinkedList<Classifier>();
			
			// recursively looking for reified parent(s)
			classifierList.addAll(c.parents());
			while ((lparents.isEmpty()) && !classifierList.isEmpty())
			{
				for (Classifier cp : classifierList)
				{
					NodeClass n = TreeProcessor.getNode((Class) cp);
					if (!n.isRigid())
						lparents.add(n.getReifiedName());
					auxList.addAll(cp.parents());
				}
				//if a suitalbe parent is not found, 
				//it is to be looked for among the parents of parents
				if (lparents.isEmpty())
				{
					classifierList.clear();
					classifierList.addAll(auxList);
					auxList.clear();
				}
			}
		}
		if (lparents.size() > 0)
			return lparents;
		else
			return null;
	}

	public List<List<String>> getCompleteChildren (Boolean addTS, Boolean is4DView, Boolean isReifView)
	{
		Boolean complete = true;
		//Mixin Classes are always defined as the conjunction of its children	
		if (myclass instanceof MixinClass)
		{
			complete = false;
			List<List<String>> allChildren = getChildrenByPartition(complete, addTS, is4DView, isReifView);
			List<List<String>> result = null;
			if (allChildren != null)
			{
				List<String> children = new LinkedList<String>();
				for (List<String> ls : allChildren)
					children.addAll(ls);
				result = new LinkedList<List<String>>();
				result.add(children);
			}
			return result;
		}
		else
			return getChildrenByPartition(complete, addTS, is4DView, isReifView);
	}
	
	private List<List<String>> getChildrenByPartition (Boolean complete, Boolean addTS, Boolean is4DView, Boolean isReifView)
	{
		List<List<String>> childrenByPartition = null;

		// For each Generalization Set
		for (ChildPartition cp : getChildPartitions())
		{
			if (cp.getGS().isIsCovering() || !complete)
			{
				if (childrenByPartition == null)
					childrenByPartition = new LinkedList<List<String>>();
				List<String> names = new LinkedList<String>();
				
				// Children in the Generalization Set
				for (NodeClass child : cp.getChildren())
				{
					if ((is4DView && !addTS && !child.isRigid()) || 
						(isReifView && !child.isRigid()))
					{
						//if it is required the complete partition but one does not fit
						//the it return none of them
						if (complete)
							names.clear();
						break;
					}
					names.add(child.getName(addTS));
				}
				if (!names.isEmpty())
					childrenByPartition.add(names);
			}
		}
		if ((childrenByPartition == null) || (childrenByPartition.size() == 0))
			return null;
		else 
			return childrenByPartition;
	}
	
}


