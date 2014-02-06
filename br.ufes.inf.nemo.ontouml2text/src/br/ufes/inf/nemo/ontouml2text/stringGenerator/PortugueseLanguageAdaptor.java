package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns.*;

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
	protected void priorizeDescriptionPatterns(List<DescriptionPattern> patterns){
		Collections.sort(patterns, new Comparator<DescriptionPattern>(){
			private Integer determineValue(DescriptionPattern d){
				if(d instanceof GeneralizationPattern) return 1;		
				if(d instanceof PhasePattern) return 3;
				if(d instanceof CharacterizationPattern) return 5;
				if(d instanceof PartOfPattern) return 7;
				if(d instanceof FormalPattern) return 9;
				// Material
				if(d instanceof MediationPattern){ 
					if(d instanceof OptionalMultiplicityPattern)
						return 14;
					else
						return 13;
				}
				if(d instanceof GeneralizationSetRevPattern) return 15;	
				// Custom
				
				return 10;
			}
			
			public int compare(DescriptionPattern d1, DescriptionPattern d2){
				Integer v1 = determineValue(d1);
				Integer v2 = determineValue(d2);

				return v1.compareTo(v2);
			}
		});
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
		}	
		
		return "$INDEF$ ";
	}
	
	@Override
	protected String insertIndefiniteArticle(String label){
		if(dictionary.isMale(label))
			return "um ";
		else
			return "uma ";
	}

	@Override
	protected String processTopPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {
		
		return parcialDescription += pattern.getDescribedCategory().getUserDescription();
	}

	@Override
	protected String processHomogeneousGeneralizationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Generating specific description
		parcialDescription += " é " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		
		return parcialDescription;
	}

	@Override
	protected String processRigidHeterogeneousGeneralizationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {	
		
		// Generating specific description
		parcialDescription += " é uma categoria de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		
		return parcialDescription;
	}

	@Override
	protected String processAntiRigidHeterogeneousGeneralizationIdPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

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

		// Generating specific description
		parcialDescription += " é " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processPhaseDescriptionPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " é " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processCharacterizationAssociationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += ", que ";
		
		// Generating specific description
		parcialDescription += " é caracterizado por ter " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
					
		return parcialDescription;
	}

	@Override
	protected String processCharacterizationAssociationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " é uma característica de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processComponentOfPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		if(previousPattern instanceof FormalPattern) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " compõe " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
					
		return parcialDescription;
	}

	@Override
	protected String processMemberOfPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		if(previousPattern instanceof FormalPattern) parcialDescription += " e";
					
		// Generating specific description
		parcialDescription += " é membro de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processSubcollectiveOfPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		if(previousPattern instanceof FormalPattern) parcialDescription += " e";
					
		// Generating specific description
		parcialDescription += " é um subcoletivo de " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
					
		return parcialDescription;
	}

	@Override
	protected String processOrdinaryMediationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " quando envolvido";
		if(previousPattern == null) parcialDescription += " se envolve";
		
		// Generating specific description
		parcialDescription += " em " + 
				insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
					
		return parcialDescription;
	}

	@Override
	protected String processPhaseDescriptionRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		
		// Generating specific description
		parcialDescription += " tem como fases: " + 
				insertListing((NaryPattern)pattern, false, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processFormalAssociationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof PhaseDescriptionRevPattern) parcialDescription += "; como também";
		if(previousIsGeneralization(previousPattern)) parcialDescription += ", o qual";
					
		// Generating specific description			
		parcialDescription += " está associado a " + 
				insertListing((NaryPattern)pattern, true, "e");
		
		return parcialDescription;
	}

	@Override
	protected String processFormalAssociationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof PhaseDescriptionRevPattern) parcialDescription += "; como também";
		if(previousIsGeneralization(previousPattern)) parcialDescription += ", o qual";
					
		// Generating specific description			
		parcialDescription += " está associado a " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}
	
	@Override
	protected String processOptionalFormalAssociationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof FormalAssociationPattern || previousPattern instanceof FormalAssociationRevPattern) parcialDescription += ", em adição, pode"; 
		if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " estar associado a " + 
				insertListing((NaryPattern)pattern, true, "ou");
					
		return parcialDescription;
	}

	@Override
	protected String processOptionalFormalAssociationRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {
		
		// Integration
		if(previousPattern instanceof FormalAssociationPattern || previousPattern instanceof FormalAssociationRevPattern) parcialDescription += ", em adição, pode"; 
		if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " estar associado a " + 
				insertListing((NaryPattern)pattern, true, "ou");
					
		return parcialDescription;
	}

	@Override
	protected String processComponentOfRevPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		if(previousPattern instanceof FormalPattern) parcialDescription += ", além disso, ";
		
		// Generating specific description
		parcialDescription += " é composto por: " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processMemberOfRevPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		if(previousPattern instanceof FormalPattern) parcialDescription += ", além disso, ";
		
		// Generating specific description
		parcialDescription += " tem como membros: " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processOrdinaryMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " que";
		
		// Generating specific description
		parcialDescription += " envolve " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processOrdinaryOptionalMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsHeterogeneousMediation(previousPattern)) parcialDescription += ", além de poder"; 
		if(previousPattern instanceof ExceptionMediationPattern) parcialDescription += " e pode"; 
		if(previousPattern == null) parcialDescription += " pode";			
		
		// Generating specific description
		parcialDescription += " envolver " + 
				insertListing((NaryPattern)pattern, true, "ou");
					
		return parcialDescription;
	}

	@Override
	protected String processDirectMediationPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " que";
		
		// Generating specific description
		parcialDescription += " envolve " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processOptionalDirectMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsHeterogeneousMediation(previousPattern)) parcialDescription += ", além de poder"; 
		if(previousPattern instanceof ExceptionMediationPattern) parcialDescription += " e pode"; 
		if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " envolver " + 
				insertListing((NaryPattern)pattern, true, "ou");
					
		return parcialDescription;
	}

	@Override
	protected String processExceptionMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousPattern instanceof OrdinaryMediationPattern) parcialDescription += ", além de estar";
		if(previousPattern == null) parcialDescription += " está";
		
		// Generating specific description
		parcialDescription += " relacionado com " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processOptionalExceptionMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsHeterogeneousMediation(previousPattern) || previousPattern instanceof ExceptionMediationPattern) parcialDescription += ", além de poder"; 
		if(previousPattern == null) parcialDescription += " pode";
		
		// Generating specific description
		parcialDescription += " estar relacionado com " + 
				insertListing((NaryPattern)pattern, true, "ou");
					
		return parcialDescription;
	}

	@Override
	protected String processAbstractMediationPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Generating specific description
		parcialDescription += " é um papel envolvido em " + 
				insertListing((NaryPattern)pattern, true, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processGeneralizationSetRevPattern(
			DescriptionPattern pattern, DescriptionPattern previousPattern,
			String parcialDescription) {

		// Integration
		if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
		
		if(previousIsHeterogeneousMediation(previousPattern) || 
				previousIsOptionalHeterogeneousMediation(previousPattern) ||
				previousPattern instanceof FormalPattern) parcialDescription += ";";
		
		// Generating specific description
		parcialDescription += " pode ser dos tipos: " + 
				insertListing((NaryPattern)pattern, false, "e");
					
		return parcialDescription;
	}

	@Override
	protected String processCustomPattern(DescriptionPattern pattern,
			DescriptionPattern previousPattern,
			String parcialDescription) {
		
		
		return parcialDescription;
	}
		
}
