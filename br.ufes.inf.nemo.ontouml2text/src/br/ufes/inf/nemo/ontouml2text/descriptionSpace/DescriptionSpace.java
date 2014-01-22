package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class DescriptionSpace {
	
	private List<DescriptionCategory> categories;
	private List<DescriptionFunction> functions;
	public OntoUMLParser ontoParser;
	
	public DescriptionSpace(){
		this.categories = new ArrayList<DescriptionCategory>();
		this.functions = new ArrayList<DescriptionFunction>();
	}
	
	public List<DescriptionCategory> getCategories() {
		return categories;
	}
	
	public List<DescriptionFunction> getFunctions() {
		return functions;
	}
	
	public void ParserTest(){
		System.out.println(ontoParser.getModelName());
	}
	
	public void SetParser(OntoUMLParser parser){
		this.ontoParser = parser;
	}
	/*
	public void PopulateLists(){
		Set <Classifier> classfSet = ontoParser.getAllInstances(Classifier.class);	
		Set<Generalization> gen = ontoParser.getAllInstances(Generalization.class);
		
		int sourceLower,sourceUpper,targetLower,targetUpper;
		DescriptionCategory source;
		DescriptionCategory target;
		
		//Populate the Lists (Categories and Functions)
		for (Classifier classf : classfSet){
			
			if(classf instanceof RefOntoUML.Association){
				source = FindCategory(((Association) classf).getEndType().get(0));
				target = FindCategory(((Association) classf).getEndType().get(1));
				
				//Find Multiplicity
				sourceLower = FindLowerMultiplicity(((Association) classf).getMemberEnd().get(0));
				sourceUpper = FindUpperMultiplicity(((Association) classf).getMemberEnd().get(0));
				
				targetLower = FindLowerMultiplicity(((Association) classf).getMemberEnd().get(1));
				targetUpper = FindUpperMultiplicity(((Association) classf).getMemberEnd().get(1));
				
				if(classf instanceof RefOntoUML.Characterization){	
					DescriptionFunction mat = new Characterization(classf.getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.componentOf){
					DescriptionFunction mat = new ComponentOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, false, false, false);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.FormalAssociation){
					DescriptionFunction mat = new Formal(classf.getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.MaterialAssociation){
					DescriptionFunction mat = new Material(classf.getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.Mediation){			
					DescriptionFunction mat = new Mediation(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.memberOf){
					DescriptionFunction mat = new MemberOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.subCollectionOf){
					DescriptionFunction mat = new SubcollectiveOf(classf.getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
					functions.add(mat);
				}
				if(classf instanceof RefOntoUML.subQuantityOf){
					DescriptionFunction mat = new SubquantityOf(classf.getName(), source, target, sourceLower, sourceUpper,targetLower,targetUpper, false, false, false);
					functions.add(mat);
				}
				//Categoria abstrata
				//	if(classf instanceof RefOntoUML.partof){}
					
			} else {
			
			if(classf instanceof RefOntoUML.Category){
				DescriptionCategory mat = new Category(classf.getName());
				categories.add(mat);
			}
			
			if(classf instanceof RefOntoUML.Collective){
				DescriptionCategory mat = new Collective(classf.getName());
				categories.add(mat);
			}
			if(classf instanceof RefOntoUML.Kind){
				DescriptionCategory mat = new Kind(classf.getName());
				categories.add(mat);
			}
			if(classf instanceof RefOntoUML.Mixin){
				DescriptionCategory mat = new Mixin(classf.getName());
				categories.add(mat);
			}
			if(classf instanceof RefOntoUML.Mode){
				DescriptionCategory mat = new Mode(classf.getName());
				categories.add(mat);
			}			
			if(classf instanceof RefOntoUML.Quantity){
				DescriptionCategory mat = new Quantity(classf.getName());
				categories.add(mat);
			}
			if(classf instanceof RefOntoUML.Relator){
				DescriptionCategory mat = new Relator(classf.getName());
				categories.add(mat);
			}
			if(classf instanceof RefOntoUML.Role){
				DescriptionCategory mat = new Role(classf.getName());
				categories.add(mat);
			}
			if(classf instanceof RefOntoUML.RoleMixin){
				DescriptionCategory mat = new RoleMixin(classf.getName());
				categories.add(mat);
			}
			}
			
		}

		//Populate the GeneralizationList 
		for(Generalization g : gen){
			DescriptionFunction gn = new br.ufes.inf.nemo.ontouml2text.descriptionSpace.Generalization("", null, null, 0, 0, 0, 0);
			functions.add(gn);
		}
	}
	
	*/
	

	
}