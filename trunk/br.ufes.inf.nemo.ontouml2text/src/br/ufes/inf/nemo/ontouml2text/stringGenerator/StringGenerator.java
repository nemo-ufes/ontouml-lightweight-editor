package br.ufes.inf.nemo.ontouml2text.stringGenerator;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.binaryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.naryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.patterns.unaryPatterns.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.*;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Category;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Collective;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Kind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Phase;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Relator;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Role;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.RoleMixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Subkind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Characterization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.ComponentOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Formal;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Generalization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.GeneralizationSet;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Mediation;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.MemberOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.SubcollectiveOf;
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
	
	public void generateGlossary(){
		int i;
		List<DescriptionPattern> patterns = null;
		DescriptionCategory describedCategory = null;
		
		exporter.initilizeExportFile();
		
		for(i = 0; i < descriptionSpace.getCategories().size(); i++){
			describedCategory = descriptionSpace.getCategories().get(i);
			
			// Identifying patterns
			patterns = identifyPatterns(describedCategory);
					
			exporter.saveDescription(describedCategory, languageAdaptor.generateCategoryDescription(patterns));
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
		
		System.out.println(describedCategory.getLabel()+" "+describedCategory.getFunctions().size());
		
		// Matching top pattern (without relations)
		if(describedCategory.getFunctions().size() == 0){
			patterns.add(new TopPattern(describedCategory));
		}
		
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
				}else if(function instanceof ComponentOf){ 
					identifyComponentOfPattern(patterns, describedCategory, function);
				}else if(function instanceof MemberOf){
					identifyMemberOfPattern(patterns, describedCategory, function);
				}else if(function instanceof SubcollectiveOf){
					identifySubcollectiveOfPattern(patterns, describedCategory, function);
				}
			}else{ // N-ary functions
				if(function instanceof GeneralizationSet){
					identifyGeneralizationSetPattern(patterns, describedCategory, function);
				}
			}
		}
		
		return patterns;
	}
	
	private void identifyGeneralizationPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			// Homogeneous Generalization Pattern
			if(target.getClass() == source.getClass())
				patterns.add(new HomogeneousGeneralizationPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		
			// Rigid Heterogeneous Generalization Pattern
			if(target instanceof Category && (source instanceof Kind || source instanceof Collective))
				patterns.add(new RigidHeterogeneousGeneralizationPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));	
		
			// Anti-Rigid Heterogeneous Generalization Pattern With Id
			if((target instanceof Kind || target instanceof Collective || target instanceof Category) && source instanceof Role)
				patterns.add(new AntiRigidHeterogeneousGeneralizationIdPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		
			// Anti-Rigid Heterogeneous Generalization Pattern
			if(target instanceof RoleMixin && source instanceof Role)
				patterns.add(new AntiRigidHeterogeneousGeneralizationPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
			
			// Phase Description Pattern
			if((target instanceof Kind || target instanceof Subkind) && source instanceof Phase){
				NaryPattern naryPattern = (NaryPattern)searchPattern(patterns, "PhaseDescriptionPattern");
				
				if(naryPattern == null){
					naryPattern = new PhaseDescriptionPattern(describedCategory);
					patterns.add(naryPattern);
				}		
				
				naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
						function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));
			}
		}else{
			// Phase Description Rev Pattern
			if((target instanceof Kind || target instanceof Subkind) && source instanceof Phase){
				patterns.add(new PhaseDescriptionRevPattern(describedCategory, 
						new PatternCategory(source.getLabel(), 
								((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
								((BinaryDescriptionFunction)function).getSourceMaxMultiplicity())));
			}
		}
	}
	
	private void identifyGeneralizationSetPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		
		NaryPattern naryPattern = (NaryPattern)searchPattern(patterns, "GeneralizationSetRevPattern");
		
		if(naryPattern == null){
			naryPattern = new GeneralizationSetRevPattern(describedCategory);
			patterns.add(naryPattern);
		}		
		
		naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
				function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));
	}
	
	private void identifyCharacterizationPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new CharacterizationAssociationPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		}else{ // Characterization Association Rev Pattern
			patterns.add(new CharacterizationAssociationRevPattern(describedCategory, 
					new PatternCategory(source.getLabel(), 
							((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
							((BinaryDescriptionFunction)function).getSourceMaxMultiplicity())));
		}
	}
	
	
	private void identifyFormalPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new FormalAssociationPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		}else{ // Formal Association Rev Pattern
			patterns.add(new FormalAssociationRevPattern(describedCategory, 
					new PatternCategory(source.getLabel(), 
							((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
							((BinaryDescriptionFunction)function).getSourceMaxMultiplicity())));
		}
	}
	
	
	private void identifyComponentOfPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new ComponentOfPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		}else{ //Component Of Rev Pattern
			patterns.add(new ComponentOfRevPattern(describedCategory, 
					new PatternCategory(source.getLabel(), 
							((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
							((BinaryDescriptionFunction)function).getSourceMaxMultiplicity())));
		}
	}
	
	
	private void identifyMemberOfPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			patterns.add(new MemberOfPattern(describedCategory, 
					new PatternCategory(target.getLabel(), 
							function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
		}else{ // Member Of Rev Pattern
			patterns.add(new MemberOfRevPattern(describedCategory, 
					new PatternCategory(source.getLabel(), 
							((BinaryDescriptionFunction)function).getSourceMinMultiplicity(), 
							((BinaryDescriptionFunction)function).getSourceMaxMultiplicity())));
		}
	}
	
	
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
	
	private void identifyMediationPattern(List<DescriptionPattern> patterns, 
			DescriptionCategory describedCategory,  DescriptionFunction function){
		DescriptionCategory target = function.getTarget(); 
		DescriptionCategory	source = ((BinaryDescriptionFunction)function).getSource();;
		
		if(describedCategory == source){ // Ensuring unidirectionality
			NaryPattern naryPattern = null;
			
			// Ordinary Mediation Pattern
			if(target instanceof Role && source instanceof Relator){					
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
			
			// Direct Mediation Pattern
			if(target instanceof Kind && source instanceof Relator){
				if(function.getTargetMinMultiplicity() > 0){
					naryPattern = (NaryPattern)searchPattern(patterns, "DirectMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new DirectMediationPattern(describedCategory);
						patterns.add(naryPattern);
					}															
				}else{
					naryPattern = (NaryPattern)searchPattern(patterns, "OptionalDirectMediationPattern");
					
					if(naryPattern == null){
						naryPattern = new OptionalDirectMediationPattern(describedCategory);
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
			
			// Abstract Mediation Pattern
			if(target instanceof RoleMixin && source instanceof Relator){
				patterns.add(new AbstractMediationPattern(describedCategory, 
						new PatternCategory(target.getLabel(), 
								function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity())));
			}
			
			if(naryPattern != null)
				naryPattern.getTargetCategories().add(new PatternCategory(target.getLabel(), 
						function.getTargetMinMultiplicity(), function.getTargetMaxMultiplicity()));	
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
	
}
