package br.ufes.inf.nemo.meronymic_validation.derivation;

import java.util.ArrayList;

import RefOntoUML.AggregationKind;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;

public class DerivedMeronymic {

	private enum Action {UNSPECIFIED, PERSIST, FORBID};
	enum PatternType {	DIRECT_FUNCTIONAL_PARTHOOD, INDIRECT_FUNCTIONAL_PATHOOD_TYPE1, INDIRECT_FUNCTIONAL_PARTHOOD_TYPE2, 
						DIRECT_SUBCOLLECTION_PARTHOOD, DIRECT_MEMBERSHIP, 
						DIRECT_SUBQUANTITY_PARTHOOD};
	
	Meronymic existingMeronymic;
	
	OutcomeFixer.RelationStereotype stereotype;
	Classifier whole, part;
	String name;
	String wholeEndName, partEndName;
	boolean isEssential, isImmutablePart, isInseparable, isImmutableWhole, isShareable;
	boolean isReadOnlyWhole, isReadOnlyPart, isDerivedWhole, isDerivedPart, isDerived;
	boolean isOrderedPart, isOrderedWhole, isUniquePart, isUniqueWhole;
	int lowerPart, upperPart, lowerWhole, upperWhole;
	AggregationKind wholeAggregation, partAggregation;
	ArrayList<Property> derivationPath;
	String oclDerivationRule;
	
	
	boolean isAllowed;
	Action action;
	PatternType pattern;
	
	public DerivedMeronymic(RelationStereotype stereotype) {
		this.stereotype = stereotype;
	}
	
	public DerivedMeronymic(Meronymic existingMeronymic) {
		this.existingMeronymic = existingMeronymic;
		
		if(existingMeronymic instanceof componentOf)
			this.stereotype = RelationStereotype.COMPONENTOF;
		if(existingMeronymic instanceof subCollectionOf)
			this.stereotype = RelationStereotype.SUBCOLLECTIONOF;
		if(existingMeronymic instanceof memberOf)
			this.stereotype = RelationStereotype.MEMBEROF;
		if(existingMeronymic instanceof subQuantityOf)
			this.stereotype = RelationStereotype.SUBQUANTITYOF;
		
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
	
	public OutcomeFixer.RelationStereotype getStereotype() {
		return stereotype;
	}

	public void setStereotype(OutcomeFixer.RelationStereotype stereotype) {
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

	public ArrayList<Property> getDerivationPath() {
		return derivationPath;
	}

	public String getDerivationPathString() {
		String path = OntoUMLNameHelper.getName(getWhole());
		
		for (Property p : derivationPath) {
			path += " -> "+OntoUMLNameHelper.getName(p.getType());
		}
		
		return path;
	}
	
	public void setDerivationPath(ArrayList<Property> derivationPath) {
		this.derivationPath = derivationPath;
	}

	public String getOclDerivationRule() {
		return oclDerivationRule;
	}

	public void setOclDerivationRule(String oclDerivationRule) {
		this.oclDerivationRule = oclDerivationRule;
	}

	public boolean isAllowed() {
		return isAllowed;
	}

	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public void setPersist() {
		this.action = Action.PERSIST;
	}
	
	public void setUnspecified() {
		this.action = Action.UNSPECIFIED;
	}
	
	public void setForbid() {
		this.action = Action.FORBID;
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

	public Action getAction() {
		return action;
	}

	//derives both ends cardinalities and meta-properties of the comp with regard to the provided path
	public boolean setEndsMetaPropertiesFromPath (){
		
		if(derivationPath==null || whole==null || part==null)
			return false;
		
		int size = derivationPath.size();
		boolean wholeIsComposite = true;
		
		lowerPart = upperPart = lowerWhole = upperWhole = 1;
		isReadOnlyPart = isOrderedPart = isReadOnlyWhole = isOrderedWhole = true;
		isShareable = isEssential = isInseparable = isImmutableWhole = isImmutablePart = true;
	
		for (int i = 0; i < size; i++) {
			
			Property currentPartEnd = derivationPath.get(i);
			Property currentWholeEnd = derivationPath.get(i).getOpposite();
			Meronymic currentMernonymic = (Meronymic) derivationPath.get(i).getAssociation();
			
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
			
			isOrderedWhole = isOrderedWhole && currentWholeEnd.isIsOrdered();
			isReadOnlyWhole = isReadOnlyWhole && currentWholeEnd.isIsReadOnly();
			wholeIsComposite = wholeIsComposite && currentWholeEnd.isIsComposite();
		}
		
		if(wholeIsComposite)
			wholeAggregation = AggregationKind.COMPOSITE;
		else
			wholeAggregation = AggregationKind.SHARED;
		
		partAggregation = AggregationKind.NONE;
		
		//if pattern Type-2, the lower value on the part side will always be 0
		if (part.allParents().contains(derivationPath.get(size-1).getType()))
			lowerPart = 0;

		//if pattern Type-3, the lower value on the whole side will always be 0
		if (whole.allParents().contains(derivationPath.get(0).getOpposite().getType()))
			lowerWhole = 0;
		
		return true;
	}
	
	public String getPatternString(){
		if(pattern == PatternType.DIRECT_FUNCTIONAL_PARTHOOD)
			return "Direct Functional";
		if(pattern == PatternType.INDIRECT_FUNCTIONAL_PATHOOD_TYPE1)
			return "Indirect Functional - Type 1";
		if(pattern == PatternType.INDIRECT_FUNCTIONAL_PARTHOOD_TYPE2)
			return "Indirect Functional - Type 2";
		if(pattern == PatternType.DIRECT_SUBCOLLECTION_PARTHOOD)
			return "Direct SubCollection";
		if(pattern == PatternType.DIRECT_MEMBERSHIP)
			return "Direct Membership";
		if(pattern == PatternType.DIRECT_SUBQUANTITY_PARTHOOD)
			return "Direct Quantity";
		return "No Pattern";
	}
	
	public void generateOCLRule(){
		if(derivationPath==null || derivationPath.size()==0 || pattern==null)
			return;
		
		String contextName = "_'"+getWhole().getName()+"'";
		String rule = "self";
		
		if(pattern==PatternType.INDIRECT_FUNCTIONAL_PARTHOOD_TYPE2)
			rule+=".oclAsType(_'"+getWhole().getName()+"')";
		
		//TODO fix properties names
		for (Property p : this.derivationPath) {
			rule+="._'"+p.getName()+"'";
		}
		
		if(pattern==PatternType.INDIRECT_FUNCTIONAL_PATHOOD_TYPE1)
			rule+="->select( x | x.oclIsKindOf(_'"+getPart().getName()+"'))";
		
		oclDerivationRule = "context "+contextName+"::"+partEndName+" : Set(_'"+getPart().getName()+")\n"+
							"derive : "+rule;
	}
}
