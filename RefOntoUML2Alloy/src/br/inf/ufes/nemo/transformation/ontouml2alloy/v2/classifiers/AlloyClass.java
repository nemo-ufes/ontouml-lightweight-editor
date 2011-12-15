package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.SortalClass;
import RefOntoUML.impl.ClassImpl;
import RefOntoUML.impl.MixinClassImpl;
import RefOntoUML.impl.MomentClassImpl;
import RefOntoUML.impl.RigidSortalClassImpl;
import RefOntoUML.impl.SortalClassImpl;

public abstract class AlloyClass extends AlloyClassifier implements AlloyIsAnAtom
{
	protected final Class ontoClass;
	protected int level;
	public AlloyClass(Class x)
	{
		ontoClass = x;
	}
	
	public String getName()
	{
		return ontoClass.getName();
	}
	public static Set<Class> extractAtomClasses(Classifier c, EList<EObject> list)
	{
		Set<Class> IDPCs = new HashSet<Class>();
		//SubstanceSortals and Moments provide identity to their instances, thus can be returned immediatly
		if(c instanceof RigidSortalClassImpl || c instanceof MomentClassImpl)
		{
			IDPCs.add((Class) c);
			return IDPCs;
		}
		
		//Sortals have only one Identity and is obtained by generalization
		//We only have to search for the General class in its general set.
		if(c instanceof SortalClassImpl)
		{
			for(Classifier potential_IDPC:c.getGeneral())
			{
				//within the General set, a substance sortal or a moment will provide its identity
				if(potential_IDPC instanceof RigidSortalClassImpl || potential_IDPC instanceof MomentClassImpl)
				{
					//potential_IDPC is in fact an Identity Provider Class
					//since c is a sortal class, potential_IDPC is the only Identity Provider Class.
					IDPCs.add((Class)potential_IDPC);
					return IDPCs;
				}
			}
		}
		
		//if the method hasn't returned yet, it means c is a Mixin.
		//we'll have to search AllObjects generalizations that target c,
		//and extract the identity from those classes

		//TODO Adaptar para RefOntoUML
		for (EObject eobj : list)
		{
			if(eobj instanceof ClassImpl)
				for(Classifier general : ((Classifier)eobj).getGeneral())
				{
					if(c == general)
					{
						//if subsumer_of_c is a sortal class, it will return quite trivially by the above mechanisms
						//else it will call this method recursively until it finds a sortal class
						IDPCs.addAll(extractAtomClasses((Classifier) eobj,list));
					}
				}
			
			
			//CLEANUP
			/*if(eobj instanceof GeneralizationImpl)
			{
				Generalization gen = (Generalization) eobj;
				if(c == gen.getGeneral())
				{
					Class subsumer_of_c = (Class)gen.getSpecific();
					//if subsumer_of_c is a sortal class, it will return quite trivially by the above mechanisms
					//else it will call this method recursively until it finds a sortal class
					IDPCs.addAll(extractAtomClasses(subsumer_of_c,list));					
				}				
			}*/
		}
		
		return IDPCs;
	}
	
	//FIXME: métodos usam isso, mas deviam se preocupa mais com as sigClasses (lembrar dos subkinds!)
	public static String extractAtomNames(Class c, EList<EObject> list)
	{
		//this method returns only one class in the case of sortals, but many in the case of mixins
		Set<Class> IDPCS = extractAtomClasses(c,list);
		//Well add () only to mixins
		String IDPCs_names=null;
		for(Class IDPC: IDPCS)
		{
			if(IDPCs_names ==null)
			{
				if(c instanceof MixinClassImpl)
				{
					IDPCs_names = "(" + IDPC.getName();
				}
				else
				{
					//sortals and moments have only one Identity Provider Class 
					return IDPC.getName();
				}
				
			}
			else IDPCs_names = IDPCs_names + " + " + IDPC.getName();
		}
		IDPCs_names = IDPCs_names+")";
		return IDPCs_names;		
	}
	abstract public String getStateName();
	
	protected static int findSortalLevel(Classifier x) 
	{
		int lvl = 0;
		//classes can have different generalization paths to the kind, we'll take the longest one.
		int maxLevel = 0;		
		//kinds will return without ++ the level (cause they only specialize mixins), thus, lvl 0.
		for(Generalization g: x.getGeneralization())
		{
			if(! (g.getGeneral() instanceof MixinClassImpl) )
			{
				lvl = findSortalLevel(g.getGeneral()) + 1;
				if(lvl > maxLevel)maxLevel = lvl;
			}
		}
		return maxLevel;
	}
	
	protected static int findMixinLevel(Classifier x, EList<EObject> list)
	{
		int lvl = 0;
		//classes can have different specialization paths to the sortal level, we'll take the longest one.
		int maxLevel = 0;
		for(EObject eo: list)
		{
			if( eo instanceof SortalClassImpl)
			{
				if( ((SortalClass) eo).getGeneral().contains(x))
				{
					for(Generalization g: ((SortalClass) eo).getGeneralization())
					{
						Classifier general = g.getGeneral();
						if(general instanceof MixinClassImpl)
						{
							lvl = genPathLength(x,general,0);
							if(lvl>maxLevel)maxLevel = lvl;
						}
					}
					
				}				
			}
		}		
		return maxLevel;		
	}
	private static int genPathLength(Classifier x, Classifier subsumer, int prevLevel) 
	{		
		if(x.equals(subsumer)) return prevLevel+1;
		int lvl = prevLevel+1;
		int maxLevel = lvl;
		for(Generalization g: subsumer.getGeneralization())
		{
			Classifier general = g.getGeneral();
			lvl = genPathLength(x,general,prevLevel+1);
			if(lvl > maxLevel)maxLevel = lvl;
		}
		return maxLevel;
	}
	public int getLevel() 
	{		
		return this.level;
	}	
}

