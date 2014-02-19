package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.unaryPatterns.*;

/*
 * SPECIFIC DESCRIPTIONS
 * The specific descriptions are related with a specific relationship,
 * which is determined by the linked pair of types, i.e, a generalization
 * relationship between a kind and a role represents a description Pattern
 * that results in a specific description 
 * */

/*
 * GENERAL DESCRIPTION
 * The general description performs the link between the specific
 * descriptions, inserting the integration patterns, once a description
 * of a category encompass many patterns, these must be linked to ensure
 * a clear description
 * */

public class PortugueseLanguageAdaptor extends LanguageAdaptor {
	
	public PortugueseLanguageAdaptor(PortugueseDictionary dictionary){
		super(dictionary);
	}
	
	@Override
	protected String insertMultiplicity(PatternCategory target){	
		if(target.getMinMultiplicity() == 1 && target.getMaxMultiplicity() == 1){ // (1,1)
			return insertIndefiniteArticle(target.getLabel());
		}else if(target.getMinMultiplicity() == 1 && target.getMaxMultiplicity() == -1){ // (1,*)
			return insertIndefiniteArticle(target.getLabel()) + "ou mais ";	
		}else if(target.getMinMultiplicity() == 0 && target.getMaxMultiplicity() == 1){ // (0,1)
			return insertIndefiniteArticle(target.getLabel());	
		}else if(target.getMinMultiplicity() == 0 && target.getMaxMultiplicity() == -1){ // (0,*)
			if(dictionary.isMale(target.getLabel()))
				return "vários ";
			else
				return "várias ";
		}else if(target.getMinMultiplicity() == 2 && target.getMaxMultiplicity() == -1){ // (2,*)
			if(dictionary.isMale(target.getLabel()))
				return "dois ou mais ";
			else
				return "duas ou mais ";
		}else if(target.getMinMultiplicity() == 3 && target.getMaxMultiplicity() == -1){ // (3,*)
			return "três ou mais ";
		}else if(target.getMinMultiplicity() == 4 && target.getMaxMultiplicity() == -1){ // (4,*)
			return "quatro ou mais ";
		}else if(target.getMinMultiplicity() == 5 && target.getMaxMultiplicity() == -1){ // (5,*)
			return "cinco ou mais ";
		}else if(target.getMinMultiplicity() == 10 && target.getMaxMultiplicity() == -1){ // (10,*)
			return "dez ou mais ";
		}else if(target.getMinMultiplicity() == 20 && target.getMaxMultiplicity() == -1){ // (20,*)
			return "vinte ou mais ";
		}				
		
		// Standard description. Exception case
		if(dictionary.isMale(target.getLabel()))
			return "vários ";
		else
			return "várias ";
	}

	@Override
	protected String processTopPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Generating specific description
		if(!pattern.getDescribedCategory().getUserDescription().isEmpty())
			return parcialDescription += pattern.getDescribedCategory().getUserDescription().replace("Descrição:", "");
		else
			return "";
	}

	@Override
	protected String processHomogeneousGeneralizationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Generating specific description
		parcialDescription += " é " + 
				insertListing((NaryPattern)pattern, true, false);
		
		return parcialDescription;
	}

	@Override
	protected String processRigidHeterogeneousGeneralizationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {	
		
		// Integration
		if(previousPattern instanceof GeneralizationPattern || 
				previousPattern instanceof OrdinaryMediationRevPattern) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " é uma categoria de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		
		return parcialDescription;
	}

	@Override
	protected String processAntiRigidHeterogeneousGeneralizationIdPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Integration
		if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";

		// Generating specific description
		parcialDescription += " é um papel que " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true) +
				" pode desempenhar";
					
		return parcialDescription;
	}

	@Override
	protected String processAntiRigidHeterogeneousGeneralizationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Integration
		if(previousPattern instanceof GeneralizationPattern || 
				previousPattern instanceof OrdinaryMediationRevPattern) parcialDescription += ", como também ";

		// Generating specific description
		parcialDescription += " é " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}

	@Override
	protected String processPhaseDescriptionPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " é " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}
	
	@Override
	protected String processPhaseDescriptionRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " tem como fases: " + 
				insertListing((NaryPattern)pattern, false, false);
					
		return parcialDescription;
	}

	@Override
	protected String processCharacterizationAssociationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ". É";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += ", que é";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += ". É";
		else if(previousPattern instanceof PhasePattern) parcialDescription += ". É";
		else if(previousPattern == null) parcialDescription += " é";
		
		// Generating specific description
		parcialDescription += " caracterizado por ter " + 
				insertListing((NaryPattern)pattern, false, false);		
					
		return parcialDescription;
	}

	@Override
	protected String processCharacterizationAssociationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += ". É";
		
		// Generating specific description
		parcialDescription += " é uma característica de " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}

	@Override
	protected String processComponentOfPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += ", e";
		else if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += ", além disso,";
		
		// Generating specific description
		parcialDescription += " compõe " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
					
		return parcialDescription;
	}
	
	@Override
	protected String processComponentOfRevPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ". É";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += ". É";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e é";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e é";
		else if(previousPattern instanceof PhasePattern) parcialDescription += ", além disso, é";
		else if(previousPattern instanceof FormalPattern) parcialDescription += ", além disso, é";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += ", além disso, é";
		else if(previousPattern == null) parcialDescription += " é";
		
		// Generating specific description
		parcialDescription += " composto por " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}

	@Override
	protected String processMemberOfPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += " e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += ", e";
		else if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += ", além disso,";
					
		// Generating specific description
		parcialDescription += " é membro de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processMemberOfRevPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof FormalPattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += ", além disso,";
		
		// Generating specific description
		parcialDescription += " tem como membros " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}

	@Override
	protected String processSubcollectiveOfPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += ", além disso,";
					
		// Generating specific description
		parcialDescription += " é um subcoletivo de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
					
		return parcialDescription;
	}

	@Override
	protected String processFormalAssociationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += ", "+insertArticle(pattern.getDescribedCategory().getLabel())+" qual";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += "; como também";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += "; como também";
					
		// Generating specific description			
		parcialDescription += " se associa a " + 
				insertListing((NaryPattern)pattern, true, false);
		
		return parcialDescription;
	}

	@Override
	protected String processFormalAssociationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += ", "+insertArticle(pattern.getDescribedCategory().getLabel())+" qual";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += "; como também";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += "; como também";
					
		// Generating specific description			
		parcialDescription += " se associa a " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}
	
	@Override
	protected String processOptionalFormalAssociationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += " e pode";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " que pode";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += "; como também pode";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += "; como também pode";
		else if(previousPattern instanceof FormalAssociationPattern) parcialDescription += ", em adição, pode"; 
		else if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " se associar a " + 
				insertListing((NaryPattern)pattern, true, true);
					
		return parcialDescription;
	}

	@Override
	protected String processOptionalFormalAssociationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += " e pode";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " que pode";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += "; como também pode";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += "; como também pode";
		else if(previousPattern instanceof FormalAssociationPattern) parcialDescription += ", em adição, pode"; 
		else if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " se associar a " + 
				insertListing((NaryPattern)pattern, true, true);
					
		return parcialDescription;
	}

	@Override
	protected String processOrdinaryMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " que";	
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += ", e";
		//else if(previousPattern instanceof PhasePattern) parcialDescription += ", e";
		//else if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		else if(previousPattern instanceof MaterialPattern) parcialDescription += ", como também,";
		
		// Generating specific description
		parcialDescription += " envolve " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}
	
	@Override
	protected String processOrdinaryMediationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern) parcialDescription += " quando envolvido";	
		else if(previousPattern instanceof AttributePattern) parcialDescription += " e se envolve";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e se envolve";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += ", além disso, se envolve";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " , além disso, se envolve";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += ", além disso, se envolve";
		else if(previousPattern instanceof FormalPattern) parcialDescription += ", além disso, se envolve";
		else if(previousPattern instanceof MaterialPattern) parcialDescription += ", como também, se envolve";
		else if(previousPattern == null) parcialDescription += " se envolve";
		 
		// Generating specific description
		parcialDescription += " em " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processOrdinaryOptionalMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += " e pode";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += "e pode";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += ", além de poder";
		else if(previousPattern instanceof OrdinaryMediationPattern) parcialDescription += ", além de poder"; 
		else if(previousPattern instanceof ExceptionMediationPattern) parcialDescription += " e poder"; 
		else if(previousPattern instanceof FormalPattern) parcialDescription += ", além de poder";
		else if(previousPattern instanceof MaterialPattern) parcialDescription += ", além de poder";
		else if(previousPattern == null) parcialDescription += " pode";			
		
		// Generating specific description
		parcialDescription += " envolver " + 
				insertListing((NaryPattern)pattern, true, true);
					
		return parcialDescription;
	}

	@Override
	protected String processExceptionMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " que"; 
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " , além disso,";
		else if(previousPattern instanceof OrdinaryMediationPattern) parcialDescription += ", além disso,";
		else if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		else if(previousPattern instanceof MaterialPattern) parcialDescription += ", além disso";
		
		// Generating specific description
		parcialDescription += " se relaciona com " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}
	
	@Override
	protected String processOptionalExceptionMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += " e pode";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " e pode";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += ", além disso, pode";
		else if(previousPattern instanceof OrdinaryMediationPattern) parcialDescription += ", além de poder"; 
		else if(previousPattern instanceof ExceptionMediationPattern) parcialDescription += ", além de poder"; 
		else if(previousPattern instanceof FormalPattern) parcialDescription += " e pode";
		else if(previousPattern instanceof MaterialPattern) parcialDescription += " e pode";
		else if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " estar relacionado com " + 
				insertListing((NaryPattern)pattern, true, true);
					
		return parcialDescription;
	}
	
	@Override
	protected String processMaterialPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern, 
			String parcialDescription){
		
		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += " e pode";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += " que pode";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PhasePattern) parcialDescription += "; como também pode";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += "; como também pode";
		else if(previousPattern instanceof FormalAssociationPattern) parcialDescription += ", em adição, pode"; 
		else if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " se relacionar com " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}
	
	@Override
	protected String processAbstractMediationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ", e";
		else if(previousPattern instanceof GeneralizationPattern) parcialDescription += ", "+insertArticle(pattern.getDescribedCategory().getLabel())+" qual";
		else if(previousPattern instanceof ReflexivePattern) parcialDescription += " e";
		else if(previousPattern instanceof CharacterizationPattern) parcialDescription += " e";
		else if(previousPattern instanceof PartOfPattern) parcialDescription += " e";
		else if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		else if(previousPattern instanceof MaterialPattern) parcialDescription += " e";
				
		// Generating specific description
		parcialDescription += " é um papel envolvido em " + 
				insertListing((NaryPattern)pattern, true, false);
					
		return parcialDescription;
	}

	@Override
	protected String processGeneralizationSetRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof AttributePattern) parcialDescription += ";";
		else if(previousPattern instanceof GeneralizationPattern || 
				previousPattern instanceof OrdinaryMediationRevPattern || 
				previousPattern instanceof ReflexivePattern) parcialDescription += " e";	
		else if(previousPattern instanceof OrdinaryMediationPattern || 
				previousPattern instanceof ExceptionMediationPattern ||
				previousPattern instanceof OrdinaryOptionalMediationPattern ||
				previousPattern instanceof OptionalExceptionMediationPattern ||
				previousPattern instanceof AbstractMediationRevPattern ||
				previousPattern instanceof MaterialPattern ||
				previousPattern instanceof PhasePattern ||
				previousPattern instanceof FormalPattern ||
				previousPattern instanceof CharacterizationPattern ||
				previousPattern instanceof PartOfPattern) parcialDescription += ";";
		
		// Generating specific description
		parcialDescription += " pode ser dos tipos: " + 
				insertListing((NaryPattern)pattern, false, false);
					
		return parcialDescription;
	}

	@Override
	protected String processCustomPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {
		
		
		return parcialDescription;
	}
	
	@Override
	protected String processReflexivePattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {
		
		//Integration
		if(previousPattern instanceof GeneralizationPattern) parcialDescription += ",";
		
		// Generating specific description
		if(dictionary.isMale(pattern.getDescribedCategory().getLabel()))
			parcialDescription += " pode se relacionar com outro " + pattern.getDescribedCategory().getLabel();
		else
			parcialDescription += " pode se relacionar com outra " + pattern.getDescribedCategory().getLabel();
		
		return parcialDescription;
	}

	@Override
	protected String processAttributePatter(DescriptionPattern pattern,
			DescriptionPattern previousPattern, String parcialDescription) {
		
		int i, size = ((AttributePattern)pattern).getAttributes().size();
		
		//Integration
		if(previousPattern instanceof GeneralizationPattern) parcialDescription += ",";
		
		// Generating specific description
		parcialDescription += " tem ";
		for(i = 0; i < size - 1; i++){
			if(i < size - 2)
				parcialDescription += ((AttributePattern)pattern).getAttributes().get(i).getLabel() + ", ";
			else
				parcialDescription += ((AttributePattern)pattern).getAttributes().get(i).getLabel() + " e ";
		}
		
		parcialDescription += ((AttributePattern)pattern).getAttributes().get(i).getLabel();

		return parcialDescription;
	}
		
}
