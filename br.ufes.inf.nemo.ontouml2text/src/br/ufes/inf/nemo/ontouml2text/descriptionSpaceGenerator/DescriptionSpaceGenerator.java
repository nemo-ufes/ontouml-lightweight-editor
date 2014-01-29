package br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator;

import java.util.ArrayList;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionCategory;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionFunction;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.DescriptionSpace;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Category;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Collective;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Kind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Mixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Mode;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Quantity;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Relator;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Role;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.RoleMixin;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Subkind;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Characterization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.ComponentOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Formal;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Generalization;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.GeneralizationSet;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Material;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.Mediation;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.MemberOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.SubcollectiveOf;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionFunctions.SubquantityOf;
import br.ufes.inf.nemo.ontouml2text.glossaryExporter.HtmlGlossaryExporter;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseDictionary;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.PortugueseLanguageAdaptor;
import br.ufes.inf.nemo.ontouml2text.stringGenerator.StringGenerator;

public class DescriptionSpaceGenerator {
	private DescriptionSpace generalizationSpace;
	
	public DescriptionSpaceGenerator(DescriptionSpace generalizationSpace){
		this.generalizationSpace = generalizationSpace;
	}
	
	public DescriptionSpace getGeneralizationSpace() {
		return generalizationSpace;
	}	
	
public void PopulateDescriptionSpace(OntoUMLParser parser, Set<String> hashCategories){
		Set <RefOntoUML.Class> classfSet = parser.getAllInstances(RefOntoUML.Class.class);	
		
		for (RefOntoUML.Class classf : classfSet){
			
			DescriptionCategory mat;
					
			if(generalizationSpace.findCategory(classf.getName()) == null){
				mat = CreateCategoryClass(classf);
				generalizationSpace.addCategory(mat);
			}else											
				mat = generalizationSpace.findCategory(classf.getName());
			PopulateRelationships(parser.getRelationships(classf),mat,parser,hashCategories);	
		}

		for (DescriptionCategory c : generalizationSpace.getCategories())
			System.out.println("Nome: "+c.getLabel()+ "\n  Lista de funções: "+c.getFunctions()+ "\n");
		
		for (DescriptionFunction c : generalizationSpace.getFunctions()){
			if(c instanceof GeneralizationSet){
				System.out.println("Nome do target: "+c.getTarget().getLabel()+"Nome da generalizationSet: "+((GeneralizationSet)c).getLabel()+"\n");
				System.out.println("tamanho da genset: "+ ((GeneralizationSet)c).getGeneralizationElements().size());
			}
		}
		
		System.out.println("Tamanho da categories no DescriptionSpace:  " + generalizationSpace.getCategories().size());
		System.out.println("Tamanho da functions no DescriptionSpace:  " + generalizationSpace.getFunctions().size());
		
		StringGenerator glossaryGenerator = new StringGenerator(generalizationSpace, 
				new HtmlGlossaryExporter("Glossary","D:/","Glossário ANTT"), new PortugueseLanguageAdaptor(new PortugueseDictionary()));
		
		glossaryGenerator.generateGlossary();

}

public DescriptionCategory CreateCategoryClass(Class classf) {	
	if(classf instanceof RefOntoUML.Category){
		DescriptionCategory mat = new Category(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Collective){
		DescriptionCategory mat = new Collective(classf.getName());
		return mat;
	}	
	if(classf instanceof RefOntoUML.Kind){
		DescriptionCategory mat = new Kind(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.SubKind){
		DescriptionCategory mat = new Subkind(classf.getName());
		return mat;	
	}
	if(classf instanceof RefOntoUML.Mixin){
		DescriptionCategory mat = new Mixin(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Mode){
		DescriptionCategory mat = new Mode(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Quantity){
		DescriptionCategory mat = new Quantity(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Relator){
		DescriptionCategory mat = new Relator(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Role){
		DescriptionCategory mat = new Role(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.RoleMixin){
		DescriptionCategory mat = new RoleMixin(classf.getName());
		return mat;
	}
	
	return null;
}

public void PopulateRelationships(ArrayList<Relationship> eList, DescriptionCategory source, OntoUMLParser parser, Set<String> hashCategories) {
	
	int classNumberTarget;
	DescriptionCategory target;
	
	for(Relationship r : eList){

		if(r instanceof RefOntoUML.componentOf){ 
			System.out.println(((RefOntoUML.componentOf)r).getEndType().get(0));
		System.out.println(((RefOntoUML.componentOf)r).getEndType().get(1));
		}
		if(r instanceof RefOntoUML.Association){			
			
			classNumberTarget = ChooseTarget(((RefOntoUML.Association) r).getEndType().get(0).getName(),((RefOntoUML.Association) r).getEndType().get(1).getName(),source.getLabel());

			// Rule09's condition 
			if(r instanceof RefOntoUML.MaterialAssociation && existsRelator(((Association) r).getEndType().get(0),((Association) r).getEndType().get(1))){
				if(source instanceof Relator){
					continue;
				}else{
					if(generalizationSpace.findCategory(source.getLabel())!= null)
						continue;
					else{
						CreateCategory(((Association) r).getEndType().get(classNumberTarget));
					}		
				}
			}
			
			if(((RefOntoUML.Association) r).getEndType().get(0).getName().equals(((RefOntoUML.Association) r).getEndType().get(1).getName())){
				DescriptionCategory targetCreated = generalizationSpace.findCategory(((RefOntoUML.Association) r).getEndType().get(1).getName());
				createRelationship(r, targetCreated, source);
				continue;
			}
			
			if(generalizationSpace.findCategory(((Association) r).getEndType().get(classNumberTarget).getName()) == null){
				target = CreateCategory(((Association) r).getEndType().get(classNumberTarget));
				createRelationship(r,target,source);
				generalizationSpace.addCategory(target);
				continue;
			
			}else{
				if(hashCategories.contains(((RefOntoUML.Association) r).getEndType().get(classNumberTarget).getName())) //verifica se ta na hash,se ja foi percorrido
					continue;
				else{
					DescriptionCategory targetCreated = generalizationSpace.findCategory(((RefOntoUML.Association) r).getEndType().get(classNumberTarget).getName());
					createRelationship(r, targetCreated, source);
				} 
			}
		}
		
		if(r instanceof RefOntoUML.Generalization){
			Classifier searchObject;
		 	boolean isSon;
		 	
			// Rule05's condition 
			if(((RefOntoUML.Generalization) r).getGeneralizationSet().size() > 0){
				processRule05(((RefOntoUML.Generalization) r),source,hashCategories);
				continue;
			}
		 	
			if(source.getLabel().equals(((RefOntoUML.Generalization) r).getSpecific().getName())){
		 		searchObject = ((RefOntoUML.Generalization) r).getGeneral();
		 		isSon = true;
		 	}
		 	else{ 
		 		searchObject = ((RefOntoUML.Generalization) r).getSpecific(); 
		 		isSon = false;
		 	}		
		 	
			if(generalizationSpace.findCategory(searchObject.getName()) == null){

				target = CreateCategoryClass((Class) searchObject);
				
				if(isSon)
					createRelationship(r,target,source);
				else
					createRelationship(r,source,target);

				generalizationSpace.addCategory(target);
				continue;
			}
			
			else{ 
				
				if(hashCategories.contains(searchObject.getName())) 
					continue;	
				
				else{
					DescriptionCategory targetCreated = generalizationSpace.findCategory(searchObject.getName());			
					
					if(isSon)
						createRelationship(r,targetCreated,source);
					else
						createRelationship(r,source,targetCreated);
				}
			}
		}
	}
	hashCategories.add(source.getLabel());
}

private void processRule05(RefOntoUML.Generalization r, DescriptionCategory source, Set<String> hashCategories) {
	Classifier searchObject;
	DescriptionCategory target;
 	boolean isSon;
 	boolean disjoint; 
 	boolean complete;
 	String genSetName;
 	GeneralizationSet existsGenSet;
 	

 	for(RefOntoUML.GeneralizationSet genSet : r.getGeneralizationSet()){ 		
 		complete = genSet.isIsCovering();
 		disjoint = genSet.isIsDisjoint();
 		genSetName = genSet.getName();

 		
		if(source.getLabel().equals(r.getSpecific().getName())){ //se o source for a classe de baixo, procura o de cima
	 		searchObject = r.getGeneral();
	 		isSon = false;
	 	}
	 	else{ 																	//se o source for a classe de cima, procura o de baixo
	 		searchObject =r.getSpecific(); 
	 		isSon = true;
	 	}	
		
		if(generalizationSpace.findCategory(searchObject.getName()) == null){
			target = CreateCategoryClass((Class) searchObject);
			
			if(isSon){
				existsGenSet = FindGenSet(source, genSet.getName());
				if(existsGenSet != null){
					Generalization gen = new Generalization("",target,source,1,1,1,1);
					source.getFunctions().add(gen);
					target.getFunctions().add(gen);
					existsGenSet.getGeneralizationElements().add(gen);

				}else{
					GeneralizationSet gs = new GeneralizationSet(source,1,1,disjoint, complete, genSetName);
					Generalization gen = new Generalization("",target,source,1,1,1,1);
					source.getFunctions().add(gen);
					target.getFunctions().add(gen);
					gs.getGeneralizationElements().add(gen);
					source.getFunctions().add(gs);
					generalizationSpace.getFunctions().add(gs);
				}
			}
			else{				
				GeneralizationSet gs = new GeneralizationSet(target,1,1,disjoint, complete, genSetName);
				Generalization gen = new Generalization("",source,target,1,1,1,1);
				gs.getGeneralizationElements().add(gen);
				source.getFunctions().add(gen);
				target.getFunctions().add(gen);
				target.getFunctions().add(gs);
				generalizationSpace.getFunctions().add(gs);

			}
			generalizationSpace.addCategory(target);
			continue;
		}
		else{ 		
			if(hashCategories.contains(searchObject.getName()))
				continue;	
			
			else{	
				DescriptionCategory targetCreated = generalizationSpace.findCategory(searchObject.getName());			
				
				if(isSon){
					existsGenSet = FindGenSet(source, genSet.getName());

					if(existsGenSet != null){
						Generalization gen = new Generalization("",targetCreated,source,1,1,1,1);
						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						existsGenSet.getGeneralizationElements().add(gen);

					}else{
						GeneralizationSet gs = new GeneralizationSet(source,1,1,disjoint, complete, genSetName);
						Generalization gen = new Generalization("",targetCreated,source,1,1,1,1);
						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						gs.getGeneralizationElements().add(gen);
						source.getFunctions().add(gs);
						generalizationSpace.getFunctions().add(gs);

						}
				}else{
					existsGenSet = FindGenSet(targetCreated, genSet.getName());
					if(existsGenSet != null){
						Generalization gen = new Generalization("",source,targetCreated,1,1,1,1);
						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						existsGenSet.getGeneralizationElements().add(gen);
						
					}else{
						GeneralizationSet gs = new GeneralizationSet(targetCreated,1,1,disjoint, complete, genSetName);
						Generalization gen = new Generalization("",source,targetCreated,1,1,1,1);
						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						gs.getGeneralizationElements().add(gen);
						targetCreated.getFunctions().add(gs);
						generalizationSpace.getFunctions().add(gs);
					}
				}
			}
	}
 }
	
}

public GeneralizationSet FindGenSet(DescriptionCategory obj, String genLabel){
	for(DescriptionFunction f : obj.getFunctions()){
		if(f instanceof GeneralizationSet && ((GeneralizationSet) f).getLabel().equals(genLabel))
			return (GeneralizationSet) f;
	}
	return null;
}

private boolean existsRelator(Type t1, Type t2) {
		if (t1 instanceof RefOntoUML.Relator || t2 instanceof RefOntoUML.Relator)
			return true;
		return false;
}

public int ChooseTarget(String name, String name2, String label) {
	if(label.equals(name))
		return 1;
	if(label.equals(name2))
		return 0;
	return -1;	
}

private void createRelationship(Relationship r, DescriptionCategory target,DescriptionCategory source) {
	
	int sourceLower,sourceUpper,targetLower,targetUpper;
		
		if(r instanceof RefOntoUML.Generalization){
			DescriptionFunction mat = new Generalization("",source,target,1,1,1,1);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);
			return;
			}
	
		//Find multiplicity source and target
		sourceLower = FindLowerMultiplicity(((Association) r).getMemberEnd().get(0));
		sourceUpper = FindUpperMultiplicity(((Association) r).getMemberEnd().get(0));
		
		targetLower = FindLowerMultiplicity(((Association) r).getMemberEnd().get(1));
		targetUpper = FindUpperMultiplicity(((Association) r).getMemberEnd().get(1));
		
		
		if(r instanceof RefOntoUML.Characterization){	
			DescriptionFunction mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

			}
		if(r instanceof RefOntoUML.componentOf){
			DescriptionFunction mat = new ComponentOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, false, false, false);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.FormalAssociation){
			DescriptionFunction mat = new Formal(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.MaterialAssociation){
			DescriptionFunction mat = new Material(((Association)r).getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.Mediation){	
			DescriptionFunction mat = new Mediation(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.memberOf){
			DescriptionFunction mat = new MemberOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.subCollectionOf){
			DescriptionFunction mat = new SubcollectiveOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper, false, false, false);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.subQuantityOf){
			DescriptionFunction mat = new SubquantityOf(((Association)r).getName(), source, target, sourceLower, sourceUpper,targetLower,targetUpper, false, false, false);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);
		}
	}

public int FindLowerMultiplicity(Property p){
			return p.getLower();
		}
		
public int FindUpperMultiplicity(Property p){
			return p.getUpper();
		}
		
public DescriptionCategory CreateCategory(Type type){
	
	if(type instanceof RefOntoUML.Category){
		DescriptionCategory mat = new Category(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Collective){
		DescriptionCategory mat = new Collective(type.getName());
		return mat;
	}	
	if(type instanceof RefOntoUML.Kind){
		DescriptionCategory mat = new Kind(type.getName());
		return mat;
	}		
	if(type instanceof RefOntoUML.SubKind){
		DescriptionCategory mat = new Subkind(type.getName());
		return mat;	
	}
	if(type instanceof RefOntoUML.Mixin){
		DescriptionCategory mat = new Mixin(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Mode){
		DescriptionCategory mat = new Mode(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Quantity){
		DescriptionCategory mat = new Quantity(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Relator){
		DescriptionCategory mat = new Relator(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Role){
		DescriptionCategory mat = new Role(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.RoleMixin){
		DescriptionCategory mat = new RoleMixin(type.getName());
		return mat;
	}
	return null;
}		

}
