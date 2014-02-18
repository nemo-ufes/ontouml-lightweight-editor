package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MixinClass;
import RefOntoUML.Property;

//partEnds with the same type
public class CommonMixinSupertype extends OverlappingGroup {

	boolean hasOverlappingGS;
	Classifier closestSupertpe;
	ArrayList<Classifier> commonSupertypes;
	ArrayList<GeneralizationSet> genSets;
	
	public CommonMixinSupertype (ArrayList<Property> mixinProperties) throws Exception {
		super(mixinProperties);
		
		//all types must be mixins
		for (Classifier type : super.overlappingTypes) {
			if(!(type instanceof MixinClass))
				throw new Exception("VAR5: All parts must be mixins.");
		}
		
		//at most one mixin type may have sortal subtypes
		int mixinsWithSortalSubtype = 0;
		for (Classifier type : super.overlappingTypes)
			for (Classifier child : type.allChildren())
				if(!(child instanceof MixinClass)){
					mixinsWithSortalSubtype++;
					break;
				}
		if(mixinsWithSortalSubtype>1)
			throw new Exception("VAR5: Two part types specialized by sortal.");
		
		this.genSets = new ArrayList<GeneralizationSet>();
		this.commonSupertypes = new ArrayList<Classifier>();
		if (!OverlappingTypesIdentificator.allTypesOverlap(super.overlappingTypes, commonSupertypes, genSets))
			throw new Exception("VAR5: Disjoint by supertypes.");
		
		if(genSets.size()>0) 
			hasOverlappingGS = true;
		
		getClosestSupertype();
		
//		//get commmon supertypes; there must be at least one
//		commonSupertypes = wholeOver.getParser().getCommonSupertypesFromProperties(partEnds);
//		if(commonSupertypes.size()<1)
//			throw new Exception("VAR5: No common supertypes");
//
//		//collect generalizationSets
//		ArrayList<GeneralizationSet> generalizationSets = new ArrayList<>();
//		for (Classifier parent : commonSupertypes) {
//			generalizationSets = wholeOver.getParser().getSubtypesGeneralizationSets(parent);
//		}
//		
//		//verifies if there is a generalization set which makes the subtypes disjoint
//		for (GeneralizationSet gs : generalizationSets) {
//			int partsInDifferentGeneralizations = 0;
//			
//			for (Generalization g1 : gs.getGeneralization())
//				if(parts.contains(g1.getSpecific()) || ArrayListOperations.hasIntersection(parts, g1.getSpecific().allChildren()))
//					partsInDifferentGeneralizations++;
//			
//			if(partsInDifferentGeneralizations>1){
//				if(gs.isIsDisjoint())
//					throw new Exception("VAR5: Made disjoint by generalization set");
//				else
//					this.genSets.add(gs);
//			}		
//		}
		super.validGroup = true;
	}
	
	private void getClosestSupertype(){
		
		closestSupertpe = null;
		
		for (Classifier supertype : this.commonSupertypes) {
			if(closestSupertpe == null)
				closestSupertpe = supertype;
			else if (supertype.allParents().contains(closestSupertpe))
				closestSupertpe = supertype;				
		}
	}
	
	@Override
	public String toString(){
		String result =	"Overllaping Group: Mixin Classes with Common Supertype" +
						"\nCommon Supertypes: ";
		
		for (Classifier parent : this.commonSupertypes)
			result+="\n\t"+parent.getName();
		
		result += "\nMixin Part Ends: ";
		
		for (Property p : overlappingProperties)
			result+="\n\t("+p.getName()+") "+p.getType().getName();
		
		return result;
	}
	
	@Override
	public boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> partEnds) {
		
		if(!this.overlappingProperties.containsAll(partEnds))
			return false;
		
		ArrayList<GeneralizationSet> gss = OverlappingTypesIdentificator.getSubtypesGeneralizationSets(closestSupertpe);
		
		ArrayList<Classifier> subtypes = new ArrayList<> ();
		for (Property property : partEnds) {
			subtypes.add((Classifier) property.getType());
		}
		
		if(gss.size()==0)
			occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(closestSupertpe, subtypes));
		else{
			boolean isFixed = false;
			for (GeneralizationSet gs : gss) {
				ArrayList<Classifier> allGsChildren = new ArrayList<Classifier>();
				
				for (Generalization g : gs.getGeneralization()) {
					allGsChildren.add(g.getSpecific());
					allGsChildren.addAll(g.getSpecific().allChildren());
				}
				
				if (allGsChildren.containsAll(subtypes)){
					gs.setIsDisjoint(true);
					isFixed = true;
					break;
				}
			}
			
			if(!isFixed)
				occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(closestSupertpe, subtypes));
		}
			
		return true;
	}
	
}
