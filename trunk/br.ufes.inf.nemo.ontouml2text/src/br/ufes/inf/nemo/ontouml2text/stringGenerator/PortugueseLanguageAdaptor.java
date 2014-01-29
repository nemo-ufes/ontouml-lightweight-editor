package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class PortugueseLanguageAdaptor implements LanguageAdaptor {
	private PortugueseDictionary dictionary;
	
	public PortugueseLanguageAdaptor(PortugueseDictionary dictionary){
		this.dictionary = dictionary;
	}

	public String generateCategoryDescription(List<DescriptionPattern> patterns){
		int i;
		String description = "";
		DescriptionPattern pattern; 
		DescriptionPattern previousPattern = null;
		
		if(patterns.size() > 0){
			priorizeDescriptionPatterns(patterns);
			
			description += patterns.get(0).getDescribedCategory().getLabel();
	
			for(i = 0; i < patterns.size(); i++){
				pattern = patterns.get(i);
				
				if(pattern instanceof UnaryPattern)
					description += processUnaryPattern(pattern, previousPattern);
				
				if(pattern instanceof BinaryPattern)
					description += processBinaryPattern(pattern, previousPattern);
				
				if(pattern instanceof NaryPattern)
					description += processNaryPattern(pattern, previousPattern);
				
				previousPattern = pattern;
			}
		}
		
		return description+".";
	}
	
	private void priorizeDescriptionPatterns(List<DescriptionPattern> patterns){
		Collections.sort(patterns, new Comparator<DescriptionPattern>(){
			private Integer determineValue(DescriptionPattern d){
				if(d instanceof GeneralizationPattern) return 1;
				if(d instanceof PhasePattern) return 2;
				if(d instanceof CharacterizationPattern) return 3;
				if(d instanceof PartOfPattern) return 4;
				if(d instanceof FormalPattern) return 5;
				// Mediation
				if(d instanceof MediationPattern) return 7;
				if(d instanceof GeneralizationSetRevPattern) return 8;	
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
	
	private String  processUnaryPattern(DescriptionPattern pattern, DescriptionPattern previousPattern){
		String parcialDescription = "";
		
		if(pattern instanceof TopPattern){

		}
		
		return parcialDescription;
	}
	
	private String  processBinaryPattern(DescriptionPattern pattern, DescriptionPattern previousPattern){
		String parcialDescription = "";
		
		if(pattern instanceof HomogeneousGeneralizationPattern){
			// Generating specific description
			parcialDescription += " é " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof RigidHeterogeneousGeneralizationPattern){
			// Generating specific description
			parcialDescription += " é uma categoria de " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern){
			// Generating specific description
			parcialDescription += " é um papel que " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true) +
					" pode desempenhar";
		}
		
		if(pattern instanceof AntiRigidHeterogeneousGeneralizationPattern){
			// Generating specific description
			parcialDescription += " é " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof CharacterizationAssociationPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += ",";
			
			// Generating specific description
			parcialDescription += " é caracterizado por ter " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof FormalAssociationPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += ", o qual";
						
			// Generating specific description			
			parcialDescription += " está associado a " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof ComponentOfPattern){
			// Generating specific description
			parcialDescription += " compõe " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof MemberOfPattern){
			// Generating specific description
			parcialDescription += " é membro de " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof SubcollectiveOfPattern){
			// Generating specific description
			parcialDescription += " é um subcoletivo de " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof OrdinaryMediationRevPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += " ao se envolver";
			if(previousPattern == null) parcialDescription += " se envolve";
			
			// Generating specific description
			parcialDescription += " com " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof DirectMediationRevPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += " ao se envolver";
			if(previousPattern == null) parcialDescription += " se envolve";
			
			// Generating specific description
			parcialDescription += " com " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof CustomPattern){
			
		}
		
		return parcialDescription;
	}
	
	private String  processNaryPattern(DescriptionPattern pattern, DescriptionPattern previousPattern){
		String parcialDescription = "";
		
		if(pattern instanceof ContextualAntiRigidHeterogeneousGeneralizationIdPattern){
			
		}
		
		if(pattern instanceof PhaseDescriptionPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
			
			// Generating specific description
			parcialDescription += " tem como fases: " + 
					insertListing((NaryPattern)pattern, false);
		}
		
		if(pattern instanceof OrdinaryMediationPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += " que";
			
			// Generating specific description
			parcialDescription += " envolve " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof OrdinaryOptionalMediationPattern){
			// Integration
			if(previousIsHeterogeneousMediation(previousPattern)) parcialDescription += ", além de poder"; 
			if(previousPattern instanceof ExceptionMediationPattern) parcialDescription += " e pode"; 
			if(previousPattern == null) parcialDescription += " pode";			
			
			// Generating specific description
			parcialDescription += " envolver " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof DirectMediationPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += " que";
			
			// Generating specific description
			parcialDescription += " envolve " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof OptionalDirectMediationPattern){
			// Integration
			if(previousIsHeterogeneousMediation(previousPattern)) parcialDescription += ", além de poder"; 
			if(previousPattern instanceof ExceptionMediationPattern) parcialDescription += " e pode"; 
			if(previousPattern == null) parcialDescription += " pode";
			
			// Generating specific description
			parcialDescription += " envolver " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof ExceptionMediationPattern){
			// Integration
			if(previousPattern instanceof OrdinaryMediationPattern) parcialDescription += ", além de estar";
			if(previousPattern == null) parcialDescription += " está";
			
			// Generating specific description
			parcialDescription += " relacionado com " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof OptionalExceptionMediationPattern){
			// Integration
			if(previousIsHeterogeneousMediation(previousPattern) || previousPattern instanceof ExceptionMediationPattern) parcialDescription += ", além de poder"; 
			if(previousPattern == null) parcialDescription += " pode";
			
			// Generating specific description
			parcialDescription += " estar relacionado com " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof AbstractMediationPattern){
			// Generating specific description
			parcialDescription += " é um papel envolvido em " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof GeneralizationSetRevPattern){
			// Integration
			if(previousIsGeneralization(previousPattern)) parcialDescription += " e";
			
			if(previousIsHeterogeneousMediation(previousPattern) || previousIsOptionalHeterogeneousMediation(previousPattern)) parcialDescription += ";";
			
			// Generating specific description
			parcialDescription += " pode ser dos tipos: " + 
					insertListing((NaryPattern)pattern, false);
		}
		
		return parcialDescription;
	}
	
	private boolean previousIsGeneralization(DescriptionPattern previousPattern){
		return previousPattern instanceof HomogeneousGeneralizationPattern ||
				previousPattern instanceof RigidHeterogeneousGeneralizationPattern ||
				previousPattern instanceof AntiRigidHeterogeneousGeneralizationPattern ||
				previousPattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern ||
				previousPattern instanceof ContextualAntiRigidHeterogeneousGeneralizationIdPattern;
	}
	
	private boolean previousIsHeterogeneousMediation(DescriptionPattern previousPattern){
		return previousPattern instanceof OrdinaryMediationPattern ||
				previousPattern instanceof DirectMediationPattern ||
				previousPattern instanceof AbstractMediationPattern;
	}
	
	private boolean previousIsOptionalHeterogeneousMediation(DescriptionPattern previousPattern){
		return previousPattern instanceof OrdinaryOptionalMediationPattern ||
				previousPattern instanceof OptionalDirectMediationPattern;
	}
	
	private String insertTarget(PatternCategory target, boolean withMultiplicity){
		if(withMultiplicity){
			String targetDescription = insertMultiplicity(target);
		
			if(target.getMaxMultiplicity() == -1 || target.getMaxMultiplicity() > 1)
				targetDescription += dictionary.getPlural(target.getLabel());
			else
				targetDescription += target.getLabel();
		
			return targetDescription;
		}else{
			return target.getLabel();
		}
	}
	
	private String insertMultiplicity(PatternCategory target){
		if(target.getMinMultiplicity() == 1 && target.getMaxMultiplicity() == 1) // (1,1)
			return insertIndefiniteArticle(target.getLabel());
		
		if(target.getMinMultiplicity() == 1 && target.getMaxMultiplicity() == -1) // (1,*)
			return insertIndefiniteArticle(target.getLabel()) + "ou mais ";
		
		return "$INDEF$";
	}
	
	private String insertListing(NaryPattern pattern, boolean withMultiplicity){
		int i, size = pattern.getTargetCategories().size();
		String listing = "";
		
		for(i = 0; i < size - 1; i++){
			if(i < size - 2)
				listing += insertTarget(pattern.getTargetCategories().get(i), withMultiplicity) + ", ";
			else
				listing += insertTarget(pattern.getTargetCategories().get(i), withMultiplicity) + " e ";
		}
		
		listing += insertTarget(pattern.getTargetCategories().get(i), withMultiplicity);
		
		return listing;
	}
	
	private String insertIndefiniteArticle(String label){
		if(dictionary.isMale(label))
			return "um ";
		else
			return "uma ";
	}
		
}
