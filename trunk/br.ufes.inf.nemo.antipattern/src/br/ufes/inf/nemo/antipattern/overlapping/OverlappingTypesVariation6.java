package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.antipattern.Fix;
import br.ufes.inf.nemo.antipattern.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.antipattern.OverlappingTypesIdentificator;


public class OverlappingTypesVariation6 extends OverlappingTypesVariation {
	
	ArrayList<Classifier> commonSubtypes;
	
	public OverlappingTypesVariation6 (OverlappingOccurrence occurrence, ArrayList<Property> mixinProperties)throws Exception {
		super(occurrence, mixinProperties);
		
		//all types must be mixins, roleMixins or categories
		for (Classifier type : super.overlappingTypes) {
			if(!(type instanceof MixinClass))
				throw new Exception("VAR6: All parts must be mixins, roleMixins or categories.");
		}
		
		//get commmon subtypes; there must be at least one
		commonSubtypes = OverlappingTypesIdentificator.getCommonSubtypesFromProperties(mixinProperties);
		if(commonSubtypes.size()<1)
			throw new Exception("VAR6: No common subtypes");
		
		//verify if there is a generalization set which makes them disjoint
		ArrayList<GeneralizationSet> genSets = new ArrayList<GeneralizationSet>();
		if(!OverlappingTypesIdentificator.allTypesOverlap(super.overlappingTypes, genSets))
			throw new Exception("VAR6: Disjoint from supertypes.");
		
		super.validVariation = true;
		
		
	}
	
	@Override
	public String toString(){
		String result =	"VAR6" +
						"\nCommon Subtypes: ";
		
		for (Classifier parent : this.commonSubtypes) {
			result+="\n\t"+occurrence.getParser().getStringRepresentation(parent);
		}
		
		result += "\nProperties: ";
		
		for (Property p : overlappingProperties) {
			result+="\n\t"+occurrence.getParser().getStringRepresentation(p);
		}
		
		return result;
	}

	@Override
	public boolean makeEndsDisjoint(ArrayList<Property> mixinProperties) {
		if(!this.overlappingProperties.containsAll(mixinProperties) || mixinProperties.size()<1)
			return false;
		
		//For each common subtype
		for (Classifier sub : commonSubtypes) {
			ArrayList<Classifier> subtypes = new ArrayList<Classifier>();
			
			
			for (Property mixinProperty : mixinProperties) {
				ClassStereotype newStereotype = defineDefaultStereotype((Classifier) mixinProperty.getType(),sub);
				
				//creates a subtype of the partEnd
				Fix currentFix = occurrence.getFixer().createSubTypeAs(sub, newStereotype);
				occurrence.getFix().addAll(currentFix);
				subtypes.addAll(currentFix.getAddedByType(RefOntoUML.Class.class));
				
				//move the generalization specific from the common subtype to the created subtype
				for (Generalization g : sub.getGeneralization()) {
					if(g.getGeneral().equals(mixinProperty.getType()) || g.getGeneral().allParents().contains(mixinProperty.getType()))
						g.setSpecific(currentFix.getAddedByType(RefOntoUML.Class.class).get(0));
				}
			}
			occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(sub, subtypes));
		}
		
		return true;
	}
	
	private ClassStereotype defineDefaultStereotype(Classifier mixinSupertype, Classifier commonSubtype){
		
		if(mixinSupertype instanceof Mixin){
			if(commonSubtype instanceof SubstanceSortal || commonSubtype instanceof SubKind)
				return ClassStereotype.SUBKIND;
			else if(commonSubtype instanceof Phase)
				return ClassStereotype.PHASE;
			else if(commonSubtype instanceof Role)
				return ClassStereotype.ROLE;
			else if(commonSubtype instanceof Mixin)
				return ClassStereotype.MIXIN;
			else if(commonSubtype instanceof RoleMixin)
				return ClassStereotype.ROLEMIXIN;
			else if(commonSubtype instanceof Category)
				return ClassStereotype.CATEGORY;
		}
		
		if(mixinSupertype instanceof RoleMixin){
			if(commonSubtype instanceof SortalClass)
				return ClassStereotype.ROLE;
			else if(commonSubtype instanceof RoleMixin)
				return ClassStereotype.ROLEMIXIN;
			else
				return null;
		}
		
		else if(mixinSupertype instanceof Category){
			if(commonSubtype instanceof SubstanceSortal || commonSubtype instanceof SubKind)
				return ClassStereotype.SUBKIND;
			else if(commonSubtype instanceof Category)
				return ClassStereotype.CATEGORY;
			else
				return null;
		}
		return null;
	
	}
	
}
