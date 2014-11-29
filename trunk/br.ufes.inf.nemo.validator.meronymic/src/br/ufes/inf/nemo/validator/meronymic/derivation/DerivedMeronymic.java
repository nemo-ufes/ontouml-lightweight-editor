package br.ufes.inf.nemo.validator.meronymic.derivation;

import java.awt.Dimension;

import javax.swing.JDialog;

import RefOntoUML.AggregationKind;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.MeronymicItem;
import br.ufes.inf.nemo.validator.meronymic.derivation.ui.DirectPatternActionDialog;
import br.ufes.inf.nemo.validator.meronymic.derivation.ui.DirectPatternPropertyDialog;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class DerivedMeronymic extends MeronymicItem{

	private enum Action {	PERSIST, NO_PERSIST, REMOVE, REVERSE, CHANGE, 
							MEMBEROF_PATH, INFER_SUBCOLLECTION, INFER_FUNCTIONAL, INFER_SUBQUANTITY, INFER_MEMBERSHIP,};
	
	public enum PatternType {	DIRECT_FUNCTIONAL, INDIRECT_FUNCTIONAL_TYPE1, INDIRECT_FUNCTIONAL_TYPE2, 
								DIRECT_SUBCOLLECTION_PARTHOOD, DIRECT_MEMBERSHIP, 
								DIRECT_SUBQUANTITY_PARTHOOD};
	private PatternType pattern;		
	private RelationStereotype stereotype;
	private Meronymic existingMeronymic;
	
	private String name;
	private String oclDerivationRule;
	private String wholeEndName, partEndName;
	private boolean isEssential, isImmutablePart, isInseparable, isImmutableWhole, isShareable;
	private boolean isReadOnlyWhole, isReadOnlyPart, isDerivedWhole, isDerivedPart, isDerived;
	private boolean isOrderedPart, isOrderedWhole, isUniquePart, isUniqueWhole;
	private int lowerPart, upperPart, lowerWhole, upperWhole;
	private AggregationKind wholeAggregation, partAggregation;

	public DerivedMeronymic(RelationStereotype stereotype, OntoUMLParser parser) {
		super(parser);
		this.stereotype = stereotype;
		fix = new Fix();
	}
	
	public DerivedMeronymic(Meronymic existingMeronymic, OntoUMLParser parser) {
		super(parser);
		this.existingMeronymic = existingMeronymic;
		
		if(existingMeronymic instanceof componentOf)
			this.stereotype = RelationStereotype.COMPONENTOF;
		if(existingMeronymic instanceof subCollectionOf)
			this.stereotype = RelationStereotype.SUBCOLLECTIONOF;
		if(existingMeronymic instanceof memberOf)
			this.stereotype = RelationStereotype.MEMBEROF;
		if(existingMeronymic instanceof subQuantityOf)
			this.stereotype = RelationStereotype.SUBQUANTITYOF;
		
		fix = new Fix();
	}
	
	public PatternType getPattern() {
		return pattern;
	}

	public void setPattern(PatternType pattern) {
		this.pattern = pattern;
	}

	public Meronymic getExistingMeronymic() {
		return existingMeronymic;
	}

	public boolean existsMeronymic(){
		return existingMeronymic!=null;
	}
	
	public RelationStereotype getStereotype() {
		return stereotype;
	}
	
	public String getStereotypeName(){
		if(stereotype==RelationStereotype.COMPONENTOF)
			return "ComponentOf";
		else if(stereotype==RelationStereotype.SUBCOLLECTIONOF)
			return "SubCollectionOf";
		else if(stereotype==RelationStereotype.MEMBEROF)
			return "MemberOf";
		else if(stereotype==RelationStereotype.SUBQUANTITYOF)
			return "SubQuantityOf";
		
		return null;
	}

	public void setStereotype(RelationStereotype stereotype) {
		this.stereotype = stereotype;
	}

	public Classifier getWhole() {
		return whole;
	}

	public void setWhole(Classifier whole) {
		this.whole = whole;
		
		if(whole!=null && whole.getName()!=null)
			this.wholeEndName = whole.getName().toLowerCase().trim();
		else
			this.wholeEndName = "whole";
	}

	public Classifier getPart() {
		return part;
	}

	public void setPart(Classifier part) {
		this.part = part;
		
		if(part!=null && part.getName()!=null)
			this.partEndName = part.getName().toLowerCase().trim();
		else
			this.partEndName = "part";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWholeEnd() {
		return wholeEndName;
	}

	public void setWholeEnd(String wholeEnd) {
		this.wholeEndName = wholeEnd;
	}

	public String getPartEnd() {
		return partEndName;
	}

	public void setPartEnd(String partEnd) {
		this.partEndName = partEnd;
	}

	public boolean isEssential() {
		return isEssential;
	}

	public void setEssential(boolean isEssential) {
		this.isEssential = isEssential;
	}

	public boolean isImmutablePart() {
		return isImmutablePart;
	}

	public void setImmutablePart(boolean isImmutablePart) {
		this.isImmutablePart = isImmutablePart;
	}

	public boolean isInseparable() {
		return isInseparable;
	}

	public void setInseparable(boolean isInseparable) {
		this.isInseparable = isInseparable;
	}

	public boolean isImmutableWhole() {
		return isImmutableWhole;
	}

	public void setImmutableWhole(boolean isInseparablePart) {
		this.isImmutableWhole = isInseparablePart;
	}

	public boolean isShareable() {
		return isShareable;
	}

	public void setShareable(boolean isShareable) {
		this.isShareable = isShareable;
	}

	public boolean isReadOnlyWhole() {
		return isReadOnlyWhole;
	}

	public void setReadOnlyWhole(boolean isReadOnlyWhole) {
		this.isReadOnlyWhole = isReadOnlyWhole;
	}

	public boolean isReadOnlyPart() {
		return isReadOnlyPart;
	}

	public void setReadOnlyPart(boolean isReadOnlyPart) {
		this.isReadOnlyPart = isReadOnlyPart;
	}
	
	public boolean isDerivedWhole() {
		return isDerivedWhole;
	}

	public void setDerivedWhole(boolean isDerivedWhole) {
		this.isDerivedWhole = isDerivedWhole;
	}

	public boolean isDerivedPart() {
		return isDerivedPart;
	}

	public void setDerivedPart(boolean isDerivedPart) {
		this.isDerivedPart = isDerivedPart;
	}

	public boolean isDerived() {
		return isDerived;
	}

	public void setDerived(boolean isDerived) {
		this.isDerived = isDerived;
	}

	public AggregationKind getWholeAggregation() {
		return wholeAggregation;
	}

	public void setWholeAggregation(AggregationKind wholeAggregation) {
		this.wholeAggregation = wholeAggregation;
	}

	public AggregationKind getPartAggregation() {
		return partAggregation;
	}

	public void setPartAggregation(AggregationKind partAggregation) {
		this.partAggregation = partAggregation;
	}



	public String getOclDerivationRule() {
		return oclDerivationRule;
	}

	public void setOclDerivationRule(String oclDerivationRule) {
		this.oclDerivationRule = oclDerivationRule;
	}	
	
	public boolean isUniquePart() {
		return isUniquePart;
	}

	public boolean isUniqueWhole() {
		return isUniqueWhole;
	}	

	public void setUniquePart(boolean isUniquePart) {
		this.isUniquePart = isUniquePart;
	}

	public void setUniqueWhole(boolean isUniqueWhole) {
		this.isUniqueWhole = isUniqueWhole;
	}

	public String getWholeEndName() {
		return wholeEndName;
	}

	public String getPartEndName() {
		return partEndName;
	}

	public boolean isOrderedPart() {
		return isOrderedPart;
	}

	public boolean isOrderedWhole() {
		return isOrderedWhole;
	}

	public int getLowerPart() {
		return lowerPart;
	}

	public int getUpperPart() {
		return upperPart;
	}

	public int getLowerWhole() {
		return lowerWhole;
	}

	public int getUpperWhole() {
		return upperWhole;
	}

	//derives both ends cardinalities and meta-properties of the comp with regard to the provided path
	public boolean setEndsMetaPropertiesFromPath (){
		
		if(path==null || whole==null || part==null)
			return false;
		
		int size = path.size();
		boolean wholeIsComposite = true;
		
		lowerPart = upperPart = lowerWhole = upperWhole = 1;
		isReadOnlyPart = isOrderedPart = isReadOnlyWhole = isOrderedWhole = isUniqueWhole = isUniquePart = true;
		isShareable = isEssential = isInseparable = isImmutableWhole = isImmutablePart = true;
	
		for (int i = 0; i < size; i++) {
			
			Property currentPartEnd = path.get(i);
			Property currentWholeEnd = path.get(i).getOpposite();
			Meronymic currentMernonymic = (Meronymic) path.get(i).getAssociation();
			
			lowerPart *= currentPartEnd.getLower();
			lowerWhole *= currentWholeEnd.getLower();
			
			if(upperPart!=-1 && currentPartEnd.getUpper()!=-1)
				upperPart *= currentPartEnd.getUpper();
			else 
				upperPart = -1;
			
			if(upperWhole!=-1 && currentWholeEnd.getUpper()!=-1)
				upperWhole *= currentWholeEnd.getUpper();
			else 
				upperWhole = -1;
			
			isShareable = isShareable && currentMernonymic.isIsShareable();
			isEssential = isEssential && currentMernonymic.isIsEssential();
			isInseparable = isInseparable && currentMernonymic.isIsEssential();
			isImmutablePart = isImmutablePart && currentMernonymic.isIsImmutablePart();
			isImmutableWhole = isImmutableWhole && currentMernonymic.isIsImmutableWhole();
			
			isReadOnlyPart = isReadOnlyPart && currentPartEnd.isIsReadOnly();
			isOrderedPart = isOrderedPart && currentPartEnd.isIsOrdered();
			isUniquePart = isUniquePart && currentPartEnd.isIsUnique();
			
			isOrderedWhole = isOrderedWhole && currentWholeEnd.isIsOrdered();
			isReadOnlyWhole = isReadOnlyWhole && currentWholeEnd.isIsReadOnly();
			isUniqueWhole = isUniqueWhole && currentWholeEnd.isIsUnique();
			wholeIsComposite = wholeIsComposite && currentWholeEnd.isIsComposite();
		}
		
		if(wholeIsComposite)
			wholeAggregation = AggregationKind.COMPOSITE;
		else
			wholeAggregation = AggregationKind.SHARED;
		
		partAggregation = AggregationKind.NONE;
		
		isDerivedWhole = false;
		isDerivedPart = true;
		
		//if pattern Type-2, the lower value on the part side will always be 0
		if (part.allParents().contains(path.get(size-1).getType()))
			lowerPart = 0;

		//if pattern Type-3, the lower value on the whole side will always be 0
		if (whole.allParents().contains(path.get(0).getOpposite().getType()))
			lowerWhole = 0;
		
		return true;
	}
	
	public String getPatternString(){
		if(pattern == PatternType.DIRECT_FUNCTIONAL)
			return "Direct Functional Parthood";
		if(pattern == PatternType.INDIRECT_FUNCTIONAL_TYPE1)
			return "Indirect Functional Parthood (Type 1)";
		if(pattern == PatternType.INDIRECT_FUNCTIONAL_TYPE2)
			return "Indirect Functional Parthood (Type 2)";
		if(pattern == PatternType.DIRECT_SUBCOLLECTION_PARTHOOD)
			return "Direct SubCollection";
		if(pattern == PatternType.DIRECT_MEMBERSHIP)
			return "Direct Membership";
		if(pattern == PatternType.DIRECT_SUBQUANTITY_PARTHOOD)
			return "Direct SubQuantity";
		return "No Pattern";
	}
	
	public void generateOCLRule(boolean isPreview){
		
		if(path==null || path.size()==0 || pattern==null)
			return;
		
		String contextName = "_'"+getWhole().getName()+"'";
		String rule = "self";
		
		if(pattern==PatternType.INDIRECT_FUNCTIONAL_TYPE2)
			rule+=".oclAsType(_'"+getWhole().getName()+"')";
		
		for (Property p : this.path) {
			if(!isPreview)
				fix.addAll(fixer.fixPropertyName(p));
			rule+="._'"+p.getName()+"'";
		}
		
		if(pattern==PatternType.INDIRECT_FUNCTIONAL_TYPE1)
			rule+="->select( x | x.oclIsKindOf(_'"+getPart().getName()+"'))";
		
		Fix newFix = fixer.generateOCLDerivation(contextName, partEndName, getPart().getName(), rule);
		if(!isPreview)
			fix.addAll(newFix);
		
		oclDerivationRule = newFix.getAddedRules().get(0);
	}

	public Fix fix() {
		if(isPersist())
			persist();
		else if(isRemove())
			remove();
		else if(isReverse())
			reverse();
		else if(isChangeStereotype())
			changeStereotype();
		else if(isMemberOfPath())
			makeMembershipPath();
		else if(isInferFunctional())
			makeFunctionalDerivationPath();
		else if(isInferSubCollection())
			makeSubCollectionDerivationPath();
		else if(isInferMembership())
			makeMembershipDerivationPath();
		else if(isInferSubQuantity())
			makeSubQuantityDerivationPath();
		
		return fix;
	}
	
	public void persist(){
		Meronymic m = (Meronymic) fixer.createAssociationBetween(stereotype, name, whole, part).getAdded().get(0);
		m.setIsDerived(isDerived);
		m.setIsShareable(isShareable);
		m.setIsEssential(isEssential);
		m.setIsImmutablePart(isImmutablePart);
		m.setIsInseparable(isInseparable);
		m.setIsImmutableWhole(isImmutableWhole);
		
		Property wholeEnd = m.getMemberEnd().get(0);
		wholeEnd.setName(wholeEndName);
		wholeEnd.setIsDerived(isDerivedWhole);
		wholeEnd.setIsOrdered(isOrderedWhole);
		wholeEnd.setIsUnique(isUniqueWhole);
		wholeEnd.setIsReadOnly(isReadOnlyWhole);
		wholeEnd.setAggregation(wholeAggregation);
		fixer.changePropertyMultiplicity(wholeEnd, lowerWhole, upperWhole, false);
		
		Property partEnd = m.getMemberEnd().get(1);
		partEnd.setName(partEndName);
		partEnd.setIsDerived(isDerivedPart);
		partEnd.setIsOrdered(isOrderedPart);
		partEnd.setIsUnique(isUniquePart);
		partEnd.setIsReadOnly(isReadOnlyPart);
		partEnd.setAggregation(partAggregation);
		fixer.changePropertyMultiplicity(partEnd, lowerPart, upperPart, false);
		
		fix.includeAdded(m);
		generateOCLRule(false);
	}
	
	

	@Override
	public FixDialog<?> createDialog(JDialog parent) {			
		DirectPatternPropertyDialog propertyDialog = new DirectPatternPropertyDialog(parent, this);
		propertyDialog.setMinimumSize(new Dimension(660, 790));
		DirectPatternActionDialog actionDialog = new DirectPatternActionDialog(parent, this);
		actionDialog.setSize(propertyDialog.getSize());
		propertyDialog.setNextPage(actionDialog);
		return propertyDialog;
	}
	
	public boolean isFunctional(){
		return pattern==PatternType.DIRECT_FUNCTIONAL || pattern==PatternType.INDIRECT_FUNCTIONAL_TYPE2 || pattern==PatternType.INDIRECT_FUNCTIONAL_TYPE1;
	}
	
	public boolean isSubCollection(){
		return pattern==PatternType.DIRECT_SUBCOLLECTION_PARTHOOD;
	}
	
	public boolean isSubQuantity(){
		return pattern==PatternType.DIRECT_SUBQUANTITY_PARTHOOD;
	}
	
	public boolean isMembership(){
		return pattern==PatternType.DIRECT_MEMBERSHIP;
	}

	public void setPersist() {
		this.action = Action.PERSIST;
	}
	
	public void setDontPersist() {
		this.action = Action.NO_PERSIST;
	}
	
	public void setRemove(Meronymic relation) {
		this.action = Action.REMOVE;
		relationToRemove = relation;
	}
	
	public void setReverse(Meronymic relation) {
		this.action = Action.REVERSE;
		relationToReverse = relation;
	}
	
	public void setChangeStereotype(Meronymic relation, RelationStereotype stereotype) {
		this.action = Action.CHANGE;
		relationToChange = relation;
		newStereotype = stereotype;
	}
	
	public void setMemberOfPath() {
		action = Action.MEMBEROF_PATH;
	}

	public void setInferFunctional() {
		action = Action.INFER_FUNCTIONAL;
	}
	
	public void setInferMembership() {
		action = Action.INFER_MEMBERSHIP;
	}
	
	public void setInferSubQuantity() {
		action = Action.INFER_SUBQUANTITY;
	}
	
	public void setInferSubCollection() {
		action = Action.INFER_SUBCOLLECTION;
	}
	
	public boolean isPersist() {
		return action == Action.PERSIST;
	}
	
	public boolean isDontPersist() {
		return action == Action.NO_PERSIST;
	}
	
	public boolean isRemove() {
		return action == Action.REMOVE;
	}
	
	public boolean isReverse() {
		return action == Action.REVERSE;
	}
	
	public boolean isChangeStereotype() {
		return action == Action.CHANGE;
	}
	
	public boolean isMemberOfPath() {
		return action == Action.MEMBEROF_PATH;
	}

	public boolean isInferFunctional() {
		return action == Action.INFER_FUNCTIONAL;
	}
	
	public boolean isInferMembership() {
		return action == Action.INFER_MEMBERSHIP;
	}
	
	public boolean isInferSubQuantity() {
		return action == Action.INFER_SUBQUANTITY;
	}
	
	public boolean isInferSubCollection() {
		return action == Action.INFER_SUBCOLLECTION;
	}

	

	
}
