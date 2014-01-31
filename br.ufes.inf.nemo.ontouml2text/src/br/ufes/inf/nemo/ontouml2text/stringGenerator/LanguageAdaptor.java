package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.List;

import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.BinaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.DescriptionPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.NaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.PatternCategory;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.UnaryPattern;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.unaryPatterns.TopPattern;

public abstract class LanguageAdaptor {

	protected Dictionary dictionary;
	
	public LanguageAdaptor(Dictionary dictionary){
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
				
				if(pattern instanceof UnaryPattern){
					description += processUnaryPattern(pattern, previousPattern);
				}else if(pattern instanceof BinaryPattern){
					description += processBinaryPattern(pattern, previousPattern);			
				}else if(pattern instanceof NaryPattern){
					description += processNaryPattern(pattern, previousPattern);
				}
				
				previousPattern = pattern;
			}
		}
		
		return description+".";
	}
	
	private String processUnaryPattern(DescriptionPattern pattern, DescriptionPattern previousPattern){
		String parcialDescription = "";
		
		if(pattern instanceof TopPattern)
			return processTopPattern(pattern, previousPattern, parcialDescription);
		
		return parcialDescription;
	}
	
	private String processBinaryPattern(DescriptionPattern pattern, DescriptionPattern previousPattern){
		String parcialDescription = "";
		
		if(pattern instanceof HomogeneousGeneralizationPattern){
			return processHomogeneousGeneralizationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof RigidHeterogeneousGeneralizationPattern){
			return processRigidHeterogeneousGeneralizationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern){
			return processAntiRigidHeterogeneousGeneralizationIdPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof AntiRigidHeterogeneousGeneralizationPattern){
			return processAntiRigidHeterogeneousGeneralizationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof PhaseDescriptionPattern){
			return processPhaseDescriptionPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof CharacterizationAssociationPattern){
			return processCharacterizationAssociationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof CharacterizationAssociationRevPattern){
			return processCharacterizationAssociationRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof ComponentOfPattern){
			return processComponentOfPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof MemberOfPattern){
			return processMemberOfPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof SubcollectiveOfPattern){
			return processSubcollectiveOfPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OrdinaryMediationRevPattern){
			return processOrdinaryMediationRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof CustomPattern){
			return processCustomPattern(pattern, previousPattern, parcialDescription);
		}
		
		return parcialDescription;
	}
	
	private String processNaryPattern(DescriptionPattern pattern, DescriptionPattern previousPattern){
		String parcialDescription = "";
		
		if(pattern instanceof PhaseDescriptionRevPattern){
			return processPhaseDescriptionRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof FormalAssociationPattern){
			return processFormalAssociationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof FormalAssociationRevPattern){
			return processFormalAssociationRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OptionalFormalAssociationPattern){
			return processOptionalFormalAssociationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OptionalFormalAssociationRevPattern){
			return processOptionalFormalAssociationRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof ComponentOfRevPattern){
			return processComponentOfRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof MemberOfRevPattern){
			return processMemberOfRevPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OrdinaryMediationPattern){
			return processOrdinaryMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OrdinaryOptionalMediationPattern){
			return processOrdinaryOptionalMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof DirectMediationPattern){
			return processDirectMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OptionalDirectMediationPattern){
			return processOptionalDirectMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof ExceptionMediationPattern){
			return processExceptionMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof OptionalExceptionMediationPattern){
			return processOptionalExceptionMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof AbstractMediationPattern){
			return processAbstractMediationPattern(pattern, previousPattern, parcialDescription);
		}else if(pattern instanceof GeneralizationSetRevPattern){
			return processGeneralizationSetRevPattern(pattern, previousPattern, parcialDescription);
		}
		
		return parcialDescription;
	}
	
	protected boolean previousIsGeneralization(DescriptionPattern previousPattern){
		return previousPattern instanceof HomogeneousGeneralizationPattern ||
				previousPattern instanceof RigidHeterogeneousGeneralizationPattern ||
				previousPattern instanceof AntiRigidHeterogeneousGeneralizationPattern ||
				previousPattern instanceof AntiRigidHeterogeneousGeneralizationIdPattern;
	}
	
	protected boolean previousIsHeterogeneousMediation(DescriptionPattern previousPattern){
		return previousPattern instanceof OrdinaryMediationPattern ||
				previousPattern instanceof DirectMediationPattern ||
				previousPattern instanceof AbstractMediationPattern;
	}
	
	protected boolean previousIsOptionalHeterogeneousMediation(DescriptionPattern previousPattern){
		return previousPattern instanceof OrdinaryOptionalMediationPattern ||
				previousPattern instanceof OptionalDirectMediationPattern;
	}
	
	protected String insertTarget(PatternCategory target, boolean withMultiplicity){
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
	
	protected String insertListing(NaryPattern pattern, boolean withMultiplicity, String finalSeparator){
		int i, size = pattern.getTargetCategories().size();
		String listing = "";
		
		for(i = 0; i < size - 1; i++){
			if(i < size - 2)
				listing += insertTarget(pattern.getTargetCategories().get(i), withMultiplicity) + ", ";
			else
				listing += insertTarget(pattern.getTargetCategories().get(i), withMultiplicity) + " " + finalSeparator + " ";
		}
		
		listing += insertTarget(pattern.getTargetCategories().get(i), withMultiplicity);
		
		return listing;
	}
	
	protected abstract void priorizeDescriptionPatterns(List<DescriptionPattern> patterns);
	
	protected abstract String insertMultiplicity(PatternCategory target);
	
	protected abstract String insertIndefiniteArticle(String label);
	
	protected abstract String processTopPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processHomogeneousGeneralizationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processRigidHeterogeneousGeneralizationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processAntiRigidHeterogeneousGeneralizationIdPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processAntiRigidHeterogeneousGeneralizationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processPhaseDescriptionPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processCharacterizationAssociationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processCharacterizationAssociationRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processComponentOfPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processMemberOfPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processSubcollectiveOfPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOrdinaryMediationRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processPhaseDescriptionRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processFormalAssociationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processFormalAssociationRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOptionalFormalAssociationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOptionalFormalAssociationRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processComponentOfRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processMemberOfRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOrdinaryMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOrdinaryOptionalMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processDirectMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOptionalDirectMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processExceptionMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processOptionalExceptionMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processAbstractMediationPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processGeneralizationSetRevPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
	protected abstract String processCustomPattern(DescriptionPattern pattern, DescriptionPattern previousPattern, String parcialDescription);
	
}
