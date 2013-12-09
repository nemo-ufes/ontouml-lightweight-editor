package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.RoleMixin;
import RefOntoUML.SortalClass;

public class OverlappingTypesIdentificator {

	public OverlappingTypesIdentificator() {	}
	//TODO: Fix to only consider selected elements
	//isVariation1: source equals target
	
	public static boolean isVariation1 (Classifier source, Classifier target){
//		System.out.println("V1: Analyzing...");
		return source.equals(target);	
	}
	
	//isVariation2: target subtype of source
	public static boolean isVariation2 (Classifier source, Classifier target){
//		System.out.println("V2: Analyzing...");
		return target.allParents().contains(source);	
	}
	
	//isVariation3: target subtype of source
	public static boolean isVariation3 (Classifier source, Classifier target){
//		System.out.println("V3: Analyzing...");
		return source.allParents().contains(target);	
	}
	

	public static boolean isVariation4 (Classifier source, Classifier target){
//		System.out.println("V4: Analyzing...");

		if (source instanceof SortalClass && target instanceof SortalClass){
		
			EList<Classifier> sourceAllParents = source.allParents();	
			EList<Classifier> targetAllParents = target.allParents();
			
			ArrayList<Classifier> commonSupertypes = new ArrayList<Classifier>();
			ArrayList<Classifier> result = new ArrayList<Classifier>();
			
		//	System.out.println("V4: Get common supertypes...");
			
			commonSupertypes.addAll(sourceAllParents);
			commonSupertypes.retainAll(targetAllParents);
			result.addAll(commonSupertypes);
			
		//	System.out.println("V4: Removing supertypes supertypes...");
			//removes common supertypes which are supertypes of other common supertypes
			for (Classifier parent : commonSupertypes) {
				ArrayList<Classifier> parentAllParents = new ArrayList<Classifier>();
		//		System.out.println("\tV4: Getting allChildren...");
				parentAllParents.addAll(parent.allParents());
		//		System.out.println("\tV4: retaining...");
				parentAllParents.retainAll(commonSupertypes);
		//		System.out.println("\tV4: Removing...");
				if(parentAllParents.size()>0)
					result.removeAll(parentAllParents);
			}
			
		//	System.out.println("V4: Removing mixin supertypes...");
			//removes common supertypes which are mixins, roleMixins, categories 
			for (Classifier parent : commonSupertypes) {
				if(parent instanceof Mixin || parent instanceof RoleMixin || parent instanceof Category)
					result.remove(parent);
			}
			
			if(result.size()>0) {
		//		System.out.println("V4: Getting relevant generalization sets...");
				//get relavant generalizationSets
				ArrayList<GeneralizationSet> genSets = new ArrayList<GeneralizationSet>();
				
				for (Generalization g : source.getGeneralization())
					genSets.addAll(g.getGeneralizationSet());
					
				for (Generalization g : source.getGeneralization())
					genSets.addAll(g.getGeneralizationSet());
				
				for (Classifier sourceParent : sourceAllParents)
					for (Generalization g : sourceParent.getGeneralization())
						genSets.addAll(g.getGeneralizationSet());
				
				for (Classifier targetParent : targetAllParents)
					for (Generalization g : targetParent.getGeneralization())
						genSets.addAll(g.getGeneralizationSet());
				
		//		System.out.println("V4: Removing supertypes that makes the related ends disjoint");
				//removes common supertypes whose generalization sets make the subtypes disjoint
				for (GeneralizationSet gs : genSets)
					if(gs.getGeneralization().size()>0 && result.contains(gs.getGeneralization().get(0).getGeneral()) && gs.isIsDisjoint()==true)
						for (Generalization g1 : gs.getGeneralization())
							for (Generalization g2 : gs.getGeneralization())
								if (!g1.equals(g2) && (g1.getSpecific().equals(source) || g1.getSpecific().allChildren().contains(source)) && (g2.getSpecific().equals(target) || g2.getSpecific().allChildren().contains(target)))
									result.remove(g1.getGeneral());
				
				if (result.size()>0)
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean isVariation5 (Classifier source, Classifier target){
		
//	System.out.println("V5: Analyzing...");

		if (source instanceof MixinClass && target instanceof MixinClass){
		
			EList<Classifier> sourceAllParents = source.allParents();
			EList<Classifier> sourceAllChildren = source.allChildren();
			EList<Classifier> targetAllParents = target.allParents();
			EList<Classifier> targetAllChildren = target.allChildren();
			
			//if one of the ends have sortal children, it does not characterize the occurrence
			for (Classifier sourceChild : sourceAllChildren)
				if(sourceChild instanceof SortalClass)
					return false;
			
			for (Classifier targetChild : targetAllChildren)
				if(targetChild instanceof SortalClass)
					return false;
			
			ArrayList<Classifier> commonSupertypes = new ArrayList<Classifier>();
			ArrayList<Classifier> result = new ArrayList<Classifier>();
			
			commonSupertypes.addAll(sourceAllParents);
			commonSupertypes.retainAll(targetAllParents);
			result.addAll(commonSupertypes);
			
			//removes common supertypes which are subtypes of other common supertypes
			for (Classifier parent : commonSupertypes) {
				if(hasIntersection(commonSupertypes, parent.allChildren()))
					result.remove(parent);
			}
			
			if(result.size()>0){
			
				//get relavant generalizationSets
				ArrayList<GeneralizationSet> genSets = new ArrayList<GeneralizationSet>();
				
				for (Generalization g : source.getGeneralization())
					genSets.addAll(g.getGeneralizationSet());
					
				for (Generalization g : source.getGeneralization())
					genSets.addAll(g.getGeneralizationSet());
				
				for (Classifier sourceParent : sourceAllParents)
					for (Generalization g : sourceParent.getGeneralization())
						genSets.addAll(g.getGeneralizationSet());
				
				for (Classifier targetParent : targetAllParents)
					for (Generalization g : targetParent.getGeneralization())
						genSets.addAll(g.getGeneralizationSet());
				
				//removes common supertypes whose generalization sets make the subtypes disjoint
				for (GeneralizationSet gs : genSets)
					if(gs.getGeneralization().size()>0 && result.contains(gs.getGeneralization().get(0).getGeneral()) && gs.isIsDisjoint()==true)
						for (Generalization g1 : gs.getGeneralization())
							for (Generalization g2 : gs.getGeneralization())
								if (!g1.equals(g2) && (g1.getSpecific().equals(source) || g1.getSpecific().allChildren().contains(source)) && (g2.getSpecific().equals(target) || g2.getSpecific().allChildren().contains(target)))
									result.remove(g1.getGeneral());
				
				if (result.size()>0)
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean isVariation6 (Classifier source, Classifier target){
		
//		System.out.println("V6: Analyzing...");
		
		if (source instanceof MixinClass && target instanceof MixinClass){
			
			EList<Classifier> sourceAllChildren = source.allChildren();
			EList<Classifier> targetAllChildren = target.allChildren();
			
			ArrayList<Classifier> commonSubtypes = new ArrayList<Classifier>();
			
			commonSubtypes.addAll(sourceAllChildren);
			commonSubtypes.retainAll(targetAllChildren);
			
			//there is a common subtype which is a sortalclass
			for (Classifier commonChild : commonSubtypes) {
				if(commonChild instanceof SortalClass)
					return true;
			}
			
			//return false if there is no sortalclass subtype but one of the ends has one
			if (commonSubtypes.size()>0){
				for (Classifier sourceChild : sourceAllChildren) {
					if(sourceChild instanceof SortalClass)
						return false;
				}
				
				for (Classifier targetChild : targetAllChildren) {
					if(targetChild instanceof SortalClass)
						return false;
				}
				//returns true if both mixins don't have sortal subtypes but have a mixinClass one..
				return true;
			}
		}
		return false;
	}
	
	private static <T,S> boolean hasIntersection(Collection<T> list1, Collection<S> list2){
		for (T o : list1) {
			if(list2.contains(o))
				return true;
		}
		return false;
	}

}
