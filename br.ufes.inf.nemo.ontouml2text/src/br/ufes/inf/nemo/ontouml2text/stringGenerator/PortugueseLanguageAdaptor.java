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
			parcialDescription += " é " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof RigidHeterogeneousGeneralizationPattern){
			parcialDescription += " é uma categoria de " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern){
			parcialDescription += " é um papel que " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true) +
					" pode desempenhar";
		}
		
		if(pattern instanceof AntiRigidHeterogeneousGeneralizationPattern){
			parcialDescription += " é " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof CharacterizationAssociationPattern){
			// Integration
			if(previousPattern instanceof HomogeneousGeneralizationPattern) parcialDescription += ",";
			
			// Generating specific description
			parcialDescription += " é caracterizado por ter " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof FormalAssociationPattern){
			parcialDescription += " está associado a " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof ComponentOfPattern){
			parcialDescription += " compõe " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof MemberOfPattern){
			parcialDescription += " é membro de " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
		}
		
		if(pattern instanceof SubcollectiveOfPattern){
			parcialDescription += " é um subcoletivo de " + 
					insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
		}
		
		if(pattern instanceof AbstractMediationPattern){
			parcialDescription += " é um papel envolvido em " + 
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
			parcialDescription += " tem como fases: " + 
					insertListing((NaryPattern)pattern, false);
		}
		
		if(pattern instanceof OrdinaryMediationPattern){
			// Integration
			if(previousPattern instanceof HomogeneousGeneralizationPattern) parcialDescription += " que";
			
			// Generating specific description
			parcialDescription += " envolve " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof OrdinaryOptionalMediationPattern){
			// Integration
			if(previousPattern != null) parcialDescription += " pode"; else parcialDescription += " poder";
			
			// Generating specific description
			parcialDescription += " envolver " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof DirectMediationPattern){
			// Integration
			if(previousPattern instanceof HomogeneousGeneralizationPattern) parcialDescription += " que";
			
			// Generating specific description
			parcialDescription += " envolve " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof OptionalDirectMediationPattern){
			// Integration
			if(previousPattern != null) parcialDescription += " pode"; else parcialDescription += " poder";
			
			// Generating specific description
			parcialDescription += " envolver " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof ExceptionMediationPattern){
			// Integration
			if(previousPattern instanceof OrdinaryMediationPattern) parcialDescription += " está"; else parcialDescription += ", além de estar";
			
			// Generating specific description
			parcialDescription += " relacionado com " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof OptionalExceptionMediationPattern){
			// Integration
			if(previousPattern != null) parcialDescription += " pode"; else parcialDescription += " poder";
			
			// Generating specific description
			parcialDescription += " estar relacionado com " + 
					insertListing((NaryPattern)pattern, true);
		}
		
		if(pattern instanceof GeneralizationSetRevPattern){
			parcialDescription += " pode ser dos tipos: " + 
					insertListing((NaryPattern)pattern, false);
		}
		
		return parcialDescription;
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
