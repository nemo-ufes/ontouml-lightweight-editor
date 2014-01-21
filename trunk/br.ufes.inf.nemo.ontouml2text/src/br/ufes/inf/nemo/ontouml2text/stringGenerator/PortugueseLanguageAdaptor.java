package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.List;

public class PortugueseLanguageAdaptor implements LanguageAdaptor {
	private PortugueseDictionary dictionary;
	
	public PortugueseLanguageAdaptor(PortugueseDictionary dictionary){
		this.dictionary = dictionary;
	}

	public String generateCategoryDescription(List<DescriptionPattern> patterns){
		int i;
		String description = "";
		DescriptionPattern pattern; 
		
		priorizeDescriptionPatterns(patterns);
		
		description += patterns.get(0).getDescribedCategory().getLabel();

		for(i = 0; i < patterns.size(); i++){
			pattern = patterns.get(i);
			
			// Unary Patterns ------------------------------------------------------
			if(pattern instanceof TopPattern){

			}
			
			// Binary Patterns ------------------------------------------------------
			if(pattern instanceof HomogeneousGeneralizationPattern){
				description += " é " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
			}
			
			if(pattern instanceof RigidHeterogeneousGeneralizationPattern){
				description += " é uma categoria de " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
			}
			
			if(pattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern){
				description += " é um papel que " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), true) +
						" pode desempenhar";
			}
			
			if(pattern instanceof AntiRigidHeterogeneousGeneralizationPattern){
				description += " é " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
			}
			
			if(pattern instanceof CharacterizationPattern){
				description += " é caracterizado por ter " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
			}
			
			if(pattern instanceof FormalPattern){
				description += " está associado a " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
			}
			
			if(pattern instanceof ComponentOfPattern){
				description += " compõe " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
			}
			
			if(pattern instanceof MemberOfPattern){
				description += " é membro de " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
			}
			
			if(pattern instanceof SubcollectiveOfPattern){
				description += " é um subcoletivo de " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), false);
			}
			
			if(pattern instanceof AbstractMediationPattern){
				description += " é um papel envolvido em " + 
						insertTarget(((BinaryPattern)pattern).getTargetCategory(), true);
			}
			
			if(pattern instanceof CustomPattern){
				
			}
			
			// Nary Patterns ------------------------------------------------------
			if(pattern instanceof ContextualAntiRigidHeterogeneousGeneralizationIdPattern){
				
			}
			
			if(pattern instanceof PhaseDescriptionPattern){
				description += " tem como fases: " + 
						insertListing((NaryPattern)pattern, false);
			}
			
			if(pattern instanceof OrdinaryMediationPattern){
				description += " envolve " + 
						insertListing((NaryPattern)pattern, true);
			}
			
			if(pattern instanceof OrdinaryOptionalMediationPattern){
				
			}
			
			if(pattern instanceof DirectMediationPattern){
				description += " envolve " + 
						insertListing((NaryPattern)pattern, true);
			}
			
			if(pattern instanceof OptionalDirectMediationPattern){
				
			}
			
			if(pattern instanceof ExceptionMediationPattern){
				
			}
			
			if(pattern instanceof GeneralizationSetRevPattern){
				description += " pode ser dos tipos: " + 
						insertListing((NaryPattern)pattern, false);
			}
		}
		
		return description;
	}
	
	private void priorizeDescriptionPatterns(List<DescriptionPattern> patterns){
		
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
