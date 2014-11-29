package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Property;
import RefOntoUML.RoleMixin;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.partover.PartOverOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.CommonMixinSubtypeComposite;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;


public class CommonMixinSubtype extends OverlappingGroup {
	
	ArrayList<Classifier> commonSubtypes;
	
	public CommonMixinSubtype (ArrayList<Property> mixinProperties, Antipattern<?> antipattern)throws Exception {
		super(mixinProperties, antipattern);
		
		//all types must be mixins, roleMixins or categories
		for (Classifier type : super.overlappingTypes) {
			if(!(type instanceof MixinClass))
				throw new Exception("VAR6: All parts must be mixins, roleMixins or categories.");
		}
		
		//get commmon subtypes; there must be at least one
		commonSubtypes = antipattern.getParser().getCommonSubtypesFromProperties(mixinProperties);
		if(commonSubtypes.size()<1)
			throw new Exception("VAR6: No common subtypes");

		//verify if there is a generalization set which makes them disjoint
		ArrayList<GeneralizationSet> genSets = new ArrayList<GeneralizationSet>();
		if(!antipattern.getParser().allTypesOverlap(super.overlappingTypes, genSets))
			throw new Exception("VAR6: Disjoint from supertypes.");
		
		super.validGroup = true;
		
	}
	
	@Override
	public String toString(){
		String result =	"Overllaping Group: Mixin Classes with Common Subtypes" +
						"\nCommon Subtypes: ";
		
		for (Classifier child : this.commonSubtypes)
			result+="\n\t"+OntoUMLNameHelper.getTypeAndName(child, true, false);
		
		result += "\nProperties: ";
		
		for (Property p : overlappingProperties)
			result+="\n\t"+OntoUMLNameHelper.getNameAndType(p);
		
		return result;
	}

	@Override
	public boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> mixinProperties) {
		ClassStereotype supertypeStereotype;
		ArrayList<Classifier> partTypes = new ArrayList<Classifier>();
		ArrayList<Generalization> createdGeneralizations;
		Fix fix1,fix2,fix3;
		int numbeOfCategory = 0, numberOfRoleMixin = 0; 
		boolean hasMixin = false;
		
		if(!this.overlappingProperties.containsAll(mixinProperties) || mixinProperties.size()<1)
			return false;
		
		//define new supertype stereotype --roleMixin if all roleMixin, category if allCategory, mixin otherwise
		for (Property property : mixinProperties) {
			if(property.getType() instanceof Category)
				numbeOfCategory++;
			if(property.getType() instanceof RoleMixin)
				numberOfRoleMixin++;
			if(property.getType() instanceof Mixin){
				hasMixin=true;
				break;
			}
		}
		
		if(hasMixin)
			supertypeStereotype = ClassStereotype.MIXIN;
		else if (numberOfRoleMixin==mixinProperties.size())
			supertypeStereotype = ClassStereotype.ROLEMIXIN;
		else if (numbeOfCategory==mixinProperties.size())
			supertypeStereotype = ClassStereotype.CATEGORY;
		else
			supertypeStereotype = ClassStereotype.MIXIN;
		
		for (Property mixinProperty : mixinProperties) 
			partTypes.add((Classifier) mixinProperty.getType());
		
		//create common supertype and generalizationSet complete
		fix1 = occurrence.getFixer().createCommonSuperType(partTypes, supertypeStereotype);
		createdGeneralizations = new ArrayList<Generalization>();
		createdGeneralizations.addAll(fix1.getAddedByType(Generalization.class));
		fix1.addAll(occurrence.getFixer().createGeneralizationSet(createdGeneralizations, false, true, "NewGS1"));
		
		createdGeneralizations = new ArrayList<Generalization>();
		fix2 = new Fix();
		int i = 1;
		for (Property p : mixinProperties) {
			//create new type as a subytpe of the created supertype
			Fix auxFix = occurrence.getFixer().createSubTypeAs(fix1.getAddedByType(MixinClass.class).get(0), supertypeStereotype);
			Classifier newSubtype = auxFix.getAddedByType(MixinClass.class).get(0);
			
			if(occurrence instanceof WholeOverOccurrence)
				newSubtype.setName("NewPartType"+i);
			else if (occurrence instanceof PartOverOccurrence)
				newSubtype.setName("NewWholeType"+i);
			else if (occurrence instanceof RelOverOccurrence)
				newSubtype.setName("NewMediatedType"+i);
			else
				newSubtype.setName("NewType"+i);
			i++;
			
			//modify property type to new type
			p.setType(auxFix.getAddedByType(MixinClass.class).get(0));
			createdGeneralizations.addAll(auxFix.getAddedByType(Generalization.class));
			fix2.addAll(auxFix);
			fix2.includeModified(p);
		}
		
		fix3 = occurrence.getFixer().createGeneralizationSet(createdGeneralizations, true, true);
		
		occurrence.getFix().addAll(fix1);
		occurrence.getFix().addAll(fix2);
		occurrence.getFix().addAll(fix3);
		
		return true;
	}

	public ArrayList<Classifier> getCommonSubtypes() {
		return commonSubtypes;
	}

	@Override
	public String getType() {
		return "Common Mixin Subtype";
	}
	
	@Override
	public Composite createComposite(Composite parent, int style) {
		return new CommonMixinSubtypeComposite(parent, style, this);
	}	
	
//	
//	private ClassStereotype defineDefaultStereotype(Classifier mixinSupertype, Classifier commonSubtype){
//		
//		if(mixinSupertype instanceof Mixin){
//			if(commonSubtype instanceof SubstanceSortal || commonSubtype instanceof SubKind)
//				return ClassStereotype.SUBKIND;
//			else if(commonSubtype instanceof Phase)
//				return ClassStereotype.PHASE;
//			else if(commonSubtype instanceof Role)
//				return ClassStereotype.ROLE;
//			else if(commonSubtype instanceof Mixin)
//				return ClassStereotype.MIXIN;
//			else if(commonSubtype instanceof RoleMixin)
//				return ClassStereotype.ROLEMIXIN;
//			else if(commonSubtype instanceof Category)
//				return ClassStereotype.CATEGORY;
//		}
//		
//		if(mixinSupertype instanceof RoleMixin){
//			if(commonSubtype instanceof SortalClass)
//				return ClassStereotype.ROLE;
//			else if(commonSubtype instanceof RoleMixin)
//				return ClassStereotype.ROLEMIXIN;
//			else
//				return null;
//		}
//		
//		else if(mixinSupertype instanceof Category){
//			if(commonSubtype instanceof SubstanceSortal || commonSubtype instanceof SubKind)
//				return ClassStereotype.SUBKIND;
//			else if(commonSubtype instanceof Category)
//				return ClassStereotype.CATEGORY;
//			else
//				return null;
//		}
//		return null;
//	
//	}
	
}
