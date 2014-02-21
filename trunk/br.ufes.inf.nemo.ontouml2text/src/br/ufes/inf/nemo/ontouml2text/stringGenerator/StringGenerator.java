package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.unaryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.*;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.*;

public class StringGenerator {
	private DescriptionSpace descriptionSpace;
	private GlossaryExporter exporter;
	private LanguageAdaptor languageAdaptor;
	
	public StringGenerator(DescriptionSpace descriptionSpace, 
			GlossaryExporter exporter, LanguageAdaptor languageAdaptor){
		this.descriptionSpace = descriptionSpace;
		this.exporter = exporter;
		this.languageAdaptor = languageAdaptor;
	}
	
	public List<String> verifyMissingUserDescription(){
		List<String> missingUserDescriptionCategories = new ArrayList<String>();
		
		for(DescriptionCategory category : descriptionSpace.getCategories()){ // Simple case, the category is 'isolated'
			if(!hasRelationsToDescribe(category)){
				if(category.getUserDescription().isEmpty())
					missingUserDescriptionCategories.add(category.getLabel());
			}
		}

		return missingUserDescriptionCategories;
	}
	
	public List<String> verifyIsolatedConcepts(){
		List<String> isolatedConcepts = new ArrayList<String>();
		
		for(DescriptionCategory category : descriptionSpace.getCategories()){ 
			if(category.getFunctions().size() == 0){
				isolatedConcepts.add(category.getLabel());
			}
		}

		return isolatedConcepts;
	}
	
	public List<String> verfifyNonDeterminedRelationships(){
		List<String> nonDeterminedRelationships = new ArrayList<String>();
		
		for(DescriptionCategory category : descriptionSpace.getCategories()){ 		
			if(identifyPatterns(category).size() == 0){
				nonDeterminedRelationships.add(category.getLabel());
			}
		}

		return nonDeterminedRelationships;
	}
	
	private boolean hasRelationsToDescribe(DescriptionCategory category){
		int relationCount = category.getFunctions().size();
		int targetCount;
		
		if(relationCount < 2){
			if(relationCount == 1){
				if((category.getFunctions().get(0) instanceof Generalization) && 
						(((DescriptionFunction)category.getFunctions().get(0)).getTarget() == category))
					return false;
			}else{
				return false;
			}
		}else{ // Special case, analyzing the generalization relationship	
			if(countFunctionType(category,"GeneralizationSet") == 0){ // There is only generalizations (not generalization sets)
				if(countFunctionType(category,"Generalization") == relationCount){
					targetCount = 0;
					
					for(DescriptionFunction function : category.getFunctions()){ // Verifying if the category is target at all
						if(function.getTarget() == category)
							targetCount++;					
					}
					
					if(targetCount == relationCount)
						return false;
				}
			}
		}
		
		return true;
	}
	
	
	public void generateGlossary(){
		int i;
		List<DescriptionPattern> patterns = null;
		DescriptionCategory describedCategory = null;
		String categoryDescription;
		
		exporter.initilizeExportFile();
		
		for(i = 0; i < descriptionSpace.getCategories().size(); i++){
			describedCategory = descriptionSpace.getCategories().get(i);
			
			// Identifying patterns
			patterns = identifyPatterns(describedCategory);
						
			categoryDescription = languageAdaptor.generateCategoryDescription(describedCategory, patterns);				
			
			exporter.saveDescription(describedCategory, categoryDescription);
		}
		
		exporter.finalizeExportFile();
	}
	
	public DescriptionSpace getGeneralizationSpace() {
		return descriptionSpace;
	}
	
	public GlossaryExporter getExporter() {
		return exporter;
	}
	
	public LanguageAdaptor getLanguageAdaptor() {
		return languageAdaptor;
	}
	
	private List<DescriptionPattern> identifyPatterns(DescriptionCategory describedCategory){
		int j;
		DescriptionFunction function = null;
		List<DescriptionPattern> patterns = new ArrayList<DescriptionPattern>();
		
		for(j = 0; j < describedCategory.getFunctions().size(); j++){
			function = describedCategory.getFunctions().get(j);
			
			if(function instanceof BinaryDescriptionFunction){// Binary Functions	
				if(function instanceof Generalization){
					identifyGeneralizationPattern(patterns, describedCategory, function);				
				}else if(function instanceof Mediation){
					identifyMediationPattern(patterns, describedCategory, function);
				}else if(function instanceof Formal){ 
					identifyFormalPattern(patterns, describedCategory, function);
				}else if(function instanceof Characterization){ 
					identifyCharacterizationPattern(patterns, describedCategory, function);	
				}else if(function instanceof Material){
					identifyMaterialPattern(patterns, describedCategory, function);
				}else if(function instanceof ComponentOf){ 
					identifyComponentOfPattern(patterns, describedCategory, function);
				}else if(function instanceof MemberOf){
					identifyMemberOfPattern(patterns, describedCategory, function);
				}else if(function instanceof SubcollectiveOf){
					identifySubcollectiveOfPattern(patterns, describedCategory, function);
				}
			}else{ // N-ary and Unary functions
				if(function instanceof GeneralizationSet){
					identifyGeneralizationSetPattern(patterns, describedCategory, function);
				}
			}
		}
		
		if(describedCategory.getAttributes().size() > 0)
			identifyAttributePattern(patterns, describedCategory);
		
		if(patterns.size() == 0)
			identifyTopPattern(patterns, describedCategory, function);
		
		return patterns;
	}
	
	private void identifyAttributePattern(List<DescriptionPattern> patterns,
		DescriptionCategory describedCategory) {
		AttributePattern pattern = new AttributePattern(describedCategory);
		
		for(CategoryAttribute attribute : describedCategory.getAttributes())
			pattern.getAttributes().add(attribute);
		
		patterns.add(pattern);
	}

	// Top Pattern
	private void identifyTopPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		
		if(!hasRelationsToDescribe(describedCategory)){
			patterns.add(new TopPattern(describedCategory));
		}
	}
	
	// Generalization
	private void identifyGeneralizationPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();
		
		NaryPattern naryPattern;
	
		if(describedCategory == source){ // Ensuring unidirectionality
			// Homogeneous Generalization Pattern
			if(target.getClass() == source.getClass() || 
					(target instanceof Kind && source instanceof Subkind)){
				naryPattern = (NaryPattern)searchPattern(patterns, "HomogeneousGeneralizationPattern");
				
				if(naryPattern == null){
					naryPattern = new HomogeneousGeneralizationPattern(describedCategory);
					patterns.add(naryPattern);
				}
							
				naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
						function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));				
			}
			
			// Anti-Rigid Heterogeneous Generalization Pattern
			if((target instanceof RoleMixin && source instanceof Role) || 
					(target instanceof Category && source instanceof RoleMixin) ||
					(target instanceof Mixin && source instanceof RoleMixin)){
				naryPattern = (NaryPattern)searchPattern(patterns, "AntiRigidHeterogeneousGeneralizationPattern");
				
				if(naryPattern == null){
					naryPattern = new AntiRigidHeterogeneousGeneralizationPattern(describedCategory);
					patterns.add(naryPattern);
				}	
				
				naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
						function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
			}
		
			// Rigid Heterogeneous Generalization Pattern
			if((target instanceof Category || target instanceof Mixin) && (source instanceof Kind || source instanceof Subkind || source instanceof Collective))
				patterns.add(new RigidHeterogeneousGeneralizationPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));	
		
			// Anti-Rigid Heterogeneous Generalization Pattern With Id
			if((target instanceof Kind || target instanceof Subkind || target instanceof Quantity || 
					target instanceof Collective || target instanceof Category) && source instanceof Role)
				patterns.add(new AntiRigidHeterogeneousGeneralizationIdPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
			
			// Phase Description Pattern
			if((target instanceof Kind || target instanceof Subkind || target instanceof Role) && source instanceof Phase){
				patterns.add(new PhaseDescriptionPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
			}
		}
	}
	
	// Generalization Set
	private void identifyGeneralizationSetPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){	
		int i;
		NaryPattern naryPattern;
		DescriptionCategory sourceElementSample = ((Generalization)((GeneralizationSet)function).getGeneralizationElements().get(0)).getSource();
		
		// Phase Description Rev Pattern
		if(sourceElementSample instanceof Phase){
			naryPattern = (NaryPattern)searchPattern(patterns, "PhaseDescriptionRevPattern");
			
			if(naryPattern == null){
				naryPattern = new PhaseDescriptionRevPattern(describedCategory);
				patterns.add(naryPattern);
			}	
		}else{ // Generalization Set Rev Pattern
			naryPattern = (NaryPattern)searchPattern(patterns, "GeneralizationSetRevPattern");
			
			if(naryPattern == null){
				naryPattern = new GeneralizationSetRevPattern(describedCategory);
				patterns.add(naryPattern);
			}				
		}
		
		for(i = 0; i < ((GeneralizationSet)function).getGeneralizationElements().size(); i++){
			Generalization generalizationElement = ((GeneralizationSet)function).getGeneralizationElements().get(i);
			
			naryPattern.getTargetCategories().add(new PatternCategory(generalizationElement.getSource().getLabel(), 
					generalizationElement.getSourceMinMultiplicity(), generalizationElement.getSourceMaxMultiplicity()));
		}
	}
	
	// Characterization
	private void identifyCharacterizationPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();
		
		NaryPattern naryPattern;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			naryPattern = (NaryPattern)searchPattern(patterns, "CharacterizationAssociationPattern");
			
			if(naryPattern == null){
				naryPattern = new CharacterizationAssociationPattern(describedCategory);
				patterns.add(naryPattern);
			}
			
			naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
					function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
		}else{ // Characterization Association Rev Pattern
			naryPattern = (NaryPattern)searchPattern(patterns, "CharacterizationAssociationRevPattern");
			
			if(naryPattern == null){
				naryPattern = new CharacterizationAssociationRevPattern(describedCategory);
				patterns.add(naryPattern);
			}
			
			naryPattern.getTargetCategories().add(new PatternCategory(source.getLabel(), 
					((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
					((BinaryDescriptionFunction)function).getSourceMaxMultiplicity()));	
		}
	}
	
	// Formal
	private void identifyFormalPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		NaryPattern naryPattern = null;
		
		if(target != source){ // Checking if is reflexive, if not, identify formal pattern, otherwise, create reflexive pattern					
			if(describedCategory == source){ // Ensuring correct multiplicity assignment		
				if(function.getTargetMinMultiplicity() > 0){
					naryPattern = (NaryPattern)searchPattern(patterns, "FormalAssociationPattern");
					
					if(naryPattern == null){
						naryPattern = new FormalAssociationPattern(describedCategory);
						patterns.add(naryPattern);
					}				
				}else{
					naryPattern = (NaryPattern)searchPattern(patterns, "OptionalFormalAssociationPattern");
					
					if(naryPattern == null){
						naryPattern = new OptionalFormalAssociationPattern(describedCategory);
						patterns.add(naryPattern);
					}
				}
				
				naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
						function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
			}else{ // Formal Association Rev Pattern		
				if(((BinaryDescriptionFunction)function).getSourceMinMultiplicity() > 0){
					naryPattern = (NaryPattern)searchPattern(patterns, "FormalAssociationPattern");
					
					if(naryPattern == null){
						naryPattern = new FormalAssociationPattern(describedCategory);
						patterns.add(naryPattern);
					}				
				}else{
					naryPattern = (NaryPattern)searchPattern(patterns, "OptionalFormalAssociationPattern");
					
					if(naryPattern == null){
						naryPattern = new OptionalFormalAssociationPattern(describedCategory);
						patterns.add(naryPattern);
					}
				}
				
				naryPattern.getTargetCategories().add(new PatternCategory(source.getLabel(), 
						((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
						((BinaryDescriptionFunction)function).getSourceMaxMultiplicity()));	
			}	
		}else{ // Creating reflexive pattern
			naryPattern = (NaryPattern)searchPattern(patterns, "ReflexivePattern");
			
			if(naryPattern == null){
				naryPattern = new ReflexivePattern(describedCategory);
				patterns.add(naryPattern);
			}
			
			naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
					function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
		}
	}
	
	// Component of
	private void identifyComponentOfPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new ComponentOfPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), 
							function.getTargetMaxMultiplicity())));	
							
		}else{ //Component Of Rev Pattern
			NaryPattern naryPattern = (NaryPattern)searchPattern(patterns, "ComponentOfRevPattern");
			
			if(naryPattern == null){
				naryPattern = new ComponentOfRevPattern(describedCategory);
				patterns.add(naryPattern);
			}
			
			naryPattern.getTargetCategories().add(new PatternCategory(source.getLabel(), 
					((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
					((BinaryDescriptionFunction)function).getSourceMaxMultiplicity()));
					
		}
	}
	
	// Member of
	private void identifyMemberOfPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new MemberOfPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), 
							function.getTargetMaxMultiplicity())));	
		}else{ // Member Of Rev Pattern
			NaryPattern naryPattern = (NaryPattern)searchPattern(patterns, "MemberOfRevPattern");
			
			if(naryPattern == null){
				naryPattern = new MemberOfRevPattern(describedCategory);
				patterns.add(naryPattern);
			}
			
			naryPattern.getTargetCategories().add(new PatternCategory(source.getLabel(), 
					((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
					((BinaryDescriptionFunction)function).getSourceMaxMultiplicity()));
		}
	}
	
	// Subcollective of
	private void identifySubcollectiveOfPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new SubcollectiveOfPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		}
	}	
	
	// Mediation
	private void identifyMediationPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();
		
		NaryPattern naryPattern = null;
		
		if(describedCategory == source){ // Ensuring unidirectionality		
			// Ordinary Mediation Pattern
			if((target instanceof Role || target instanceof RoleMixin || 
					target instanceof Kind || target instanceof Subkind || 
					target instanceof Category || target instanceof Mixin) && source instanceof Relator){					
				if(function.getTargetMinMultiplicity() > 0){
					naryPattern = (NaryPattern)searchPattern(patterns, "OrdinaryMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new OrdinaryMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}															
				}else{
					naryPattern = (NaryPattern)searchPattern(patterns, "OrdinaryOptionalMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new OrdinaryOptionalMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}	
				}
			}
			
			// Exception Mediation Pattern
			if(target instanceof Relator && source instanceof Relator){
				if(function.getTargetMinMultiplicity() > 0){
					naryPattern = (NaryPattern)searchPattern(patterns, "ExceptionMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new ExceptionMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}															
				}else{
					naryPattern = (NaryPattern)searchPattern(patterns, "OptionalExceptionMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new OptionalExceptionMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}	
				}								
			}
			
			if(naryPattern != null)
				naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
						function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
		}else{ // Described is the target
			// Abstract Mediation Pattern
			if(target instanceof RoleMixin && source instanceof Relator){
				naryPattern = (NaryPattern)searchPattern(patterns, "AbstractMediationRevPattern");
				
				if(naryPattern == null){
					naryPattern = new AbstractMediationRevPattern(describedCategory);
					patterns.add(naryPattern);
				}															
			}
			
			// Ordinary Mediation Rev Pattern
			// Verifying if there is just one mediation relationship
			if(countFunctionType(describedCategory,"Mediation") == 1){
				// Processing Pattern
				if((target instanceof Role || target instanceof Kind || 
						target instanceof Subkind || target instanceof Category || 
						target instanceof Mixin) && source instanceof Relator){
					patterns.add(new OrdinaryMediationRevPattern(describedCategory, 
							new PatternCategory(source.getLabel(), 
									((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
									((BinaryDescriptionFunction)function).getSourceMaxMultiplicity())));														
				}
			}
			
			// Exception Mediation Pattern
			if(target instanceof Relator && source instanceof Relator){
				if(((BinaryDescriptionFunction)function).getSourceMinMultiplicity() > 0){
					naryPattern = (NaryPattern)searchPattern(patterns, "ExceptionMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new ExceptionMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}															
				}else{
					naryPattern = (NaryPattern)searchPattern(patterns, "OptionalExceptionMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new OptionalExceptionMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}	
				}								
			}
			
			if(naryPattern != null)
				naryPattern.getTargetCategories().add(new PatternCategory(source.getLabel(), 
						((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
						((BinaryDescriptionFunction)function).getSourceMaxMultiplicity()));	
		}	
	}
	
	// Material
	private void identifyMaterialPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();
		
		NaryPattern naryPattern = (NaryPattern)searchPattern(patterns, "MaterialPattern");
		
		if(naryPattern == null){
			naryPattern = new MaterialPattern(describedCategory);
			patterns.add(naryPattern);
		}
		
		if(describedCategory == source){
			naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
					function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
		}else{
			naryPattern.getTargetCategories().add(new PatternCategory(source.getLabel(), 
					((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
					((BinaryDescriptionFunction)function).getSourceMaxMultiplicity()));	
		}
	}
	
	private DescriptionPattern searchPattern(List<DescriptionPattern> patterns, String patternName){
		int i;
		
		for(i = 0; i < patterns.size(); i++){
			if(patterns.get(i).getClass().toString().contains(patternName))
				return patterns.get(i);
		}
		
		return null;
	}
	
	private int countFunctionType(DescriptionCategory describedCategory, String functionType){
		int i, count = 0;
		
		for(i = 0; i < describedCategory.getFunctions().size(); i++){
			if(describedCategory.getFunctions().get(i).getClass().toString().contains(functionType))
				count++;
		}
		
		return count;
	}
	
}
