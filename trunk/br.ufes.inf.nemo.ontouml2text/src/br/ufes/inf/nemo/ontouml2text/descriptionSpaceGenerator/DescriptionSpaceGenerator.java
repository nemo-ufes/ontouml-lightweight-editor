package br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

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
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.descriptionCategories.Phase;
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

public class DescriptionSpaceGenerator {
	private DescriptionSpace generalizationSpace;
	
	public DescriptionSpaceGenerator(DescriptionSpace generalizationSpace){
		this.generalizationSpace = generalizationSpace;
	}
	
	public DescriptionSpace getGeneralizationSpace() {
		return generalizationSpace;
	}	
	
public void populateDescriptionSpace(OntoUMLParser parser, Set<String> hashCategories, DefaultListModel<String> conceptsWithoutDesc){
		Set <RefOntoUML.Class> classfSet = parser.getAllInstances(RefOntoUML.Class.class);	
		
		for (RefOntoUML.Class classf : classfSet){
			DescriptionCategory mat;
						
			if(generalizationSpace.findCategory(classf.getName()) == null){
				mat = createCategoryClass(classf);
				generalizationSpace.addCategory(mat);
			}else											
				mat = generalizationSpace.findCategory(classf.getName());
			
			populateRelationships(parser.getRelationships(classf),mat,parser,hashCategories);	
		}
		
		//relatorIheritance(generalizationSpace.getCategories());
		
		for (DescriptionCategory c : generalizationSpace.getCategories())
			System.out.println("Nome: "+c.getLabel() + "  type: "+c.toString() +"\n  Lista de funções: "+c.getFunctions()+ "\n");
		
		System.out.println("Tamanho da categories no DescriptionSpace:  " + generalizationSpace.getCategories().size());
		System.out.println("Tamanho da functions no DescriptionSpace:  " + generalizationSpace.getFunctions().size());

}

public void relatorIheritance(List<DescriptionCategory> categories) {
	Generalization gen;
	DescriptionCategory upCategory;
	
	for(DescriptionCategory c : categories){	//Percorro a lista de categorias, já populada.
		if(c instanceof Relator){				//Se for um relator, verifico se há generalization onde ele é o de baixo(source)
			gen = (Generalization) verifyGeneralization(c.getFunctions(),c);
			
			if( gen != null){ // se existir gen onde o elemento é o source, é pq tem mais pra cima.
				
				upCategory = upFunction(gen); // sobe todos os niveis de hierarquia e retorna o elemento topo
				// desço adicionando as mediations, até encontrar uma genSet
				getDownMediation(upCategory,c);	// desce os niveis ate chegar no elemento c, adicionando as mediations
			
			}else
				continue;
		}		
	}
}

public DescriptionCategory upFunction(Generalization gen) {

	DescriptionCategory target = gen.getTarget();
	Generalization genTarget;
	
	while(true){
		System.out.println("loop Up");
		genTarget = (Generalization) verifyGeneralization(target.getFunctions(),target); //verifica se tem algo acima
		if(genTarget != null)
			target = genTarget.getTarget();
		else 
			break;
	}
	return target;
}

private void getDownMediation(DescriptionCategory upCategory,DescriptionCategory c) {
	GeneralizationSet gen;
	DescriptionCategory downCategory = null;
	
	while(true){ //enquanto  nao chegar em uma generalization

		gen =  VerifyGeneralizationSet(upCategory.getFunctions(), upCategory);		//verifica se tem alguem embaixo dele

		if( gen != null){// existe alguem embaixo, passa todas as mediations
			downCategory = gen.getSource();
			
			addMediations(upCategory.getFunctions(),downCategory);					//Passa as informações
		}
		
		if(downCategory.equals(c))		//se for a categoria source, adiciono e saio do while.
			break;
		
		upCategory = downCategory;*/
	}
	
}

public GeneralizationSet VerifyGeneralizationSet(List <DescriptionFunction> arrayList, DescriptionCategory element){
	for(DescriptionFunction r : arrayList){
		if(r instanceof GeneralizationSet){
			return (GeneralizationSet) r;		//Se tiver uma generalizationSet, retorna a GenSet
		}
	}
	return null;
}

public void addMediations(List<DescriptionFunction> list, DescriptionCategory downCategory){
	for(DescriptionFunction m : list){
		if(m instanceof Mediation)
			if(!verifyEqualMediation(downCategory.getFunctions(),m))
				downCategory.getFunctions().add(m);
	}
}

public boolean verifyEqualMediation(List<DescriptionFunction> list ,DescriptionFunction m){
	for(DescriptionFunction df : list){
		if(df.equals(m))		//se a mediation ja existir
			return true;
	}
	return false;
}

private DescriptionFunction verifyGeneralizationTarget(List <DescriptionFunction> arrayList, DescriptionCategory element ) {
	for(DescriptionFunction r : arrayList){
		if(r instanceof Generalization){
			if(((Generalization) r).getTarget().equals(element))	//se o element for o target, existe mais para baixo ,retorna essa gen
			return r;
		}
	}
	return null;
}

private DescriptionFunction verifyGeneralization(List <DescriptionFunction> arrayList, DescriptionCategory element ) {
	for(DescriptionFunction r : arrayList){
		if(r instanceof Generalization){
			if(((Generalization) r).getSource().equals(element))	//se o element for o source, retorna essa gen
			return r;
		}
	}
	return null;
}

public DescriptionCategory createCategoryClass(Class classf) {	

	if(classf instanceof RefOntoUML.Category || classf.toString().contains("RefOntoUML.impl.ClassImpl@")){
		DescriptionCategory mat = new Category(classf.getName());
		return mat;
	}
	if(classf instanceof RefOntoUML.Phase){
		DescriptionCategory mat = new Phase(classf.getName());
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

public void populateRelationships(ArrayList<Relationship> eList, DescriptionCategory source, OntoUMLParser parser, Set<String> hashCategories) {
	DescriptionCategory target;
	String endType0;
	String endType1;

	int classNumberTarget;

	for(Relationship r : eList){

		if(r instanceof RefOntoUML.Association){	
			
			if(r instanceof RefOntoUML.Derivation || r.toString().contains("RefOntoUML.impl.AssociationImpl@") ){
				continue;
			}
			
			endType0 = ((RefOntoUML.Association) r).getEndType().get(0).getName();
			endType1 = ((RefOntoUML.Association) r).getEndType().get(1).getName();
			
			if(((RefOntoUML.Association) r).getEndType().get(0).getName().contains("/"))
				endType0 = ((RefOntoUML.Association) r).getEndType().get(0).getName().replace("/","");
			
			if(((RefOntoUML.Association) r).getEndType().get(1).getName().contains("/"))
				endType1 = ((RefOntoUML.Association) r).getEndType().get(1).getName().replace("/","");
			
			classNumberTarget = chooseTarget(endType0,endType1,source.getLabel());

			// Rule09's condition 
			if(r instanceof RefOntoUML.MaterialAssociation && existsRelator(((Association) r).getEndType().get(0),((Association) r).getEndType().get(1))){
				if(source instanceof Relator){
					continue;
				}else{
					if(generalizationSpace.findCategory(source.getLabel())!= null)
						continue;
					else{
						createCategory(((Association) r).getEndType().get(classNumberTarget));
					}		
				}
			}
			
			//Autorelacao
			if(endType0.equals(endType1)){
				createRelationship(r, source, source);
				continue;
			}
			
			if(generalizationSpace.findCategory(((Association) r).getEndType().get(classNumberTarget).getName()) == null){
				target = createCategory(((Association) r).getEndType().get(classNumberTarget));
				
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
				target = createCategoryClass((Class) searchObject);
				
				if(isSon){
					createRelationship(r,target,source);
				}else{
					createRelationship(r,source,target);
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
						createRelationship(r,targetCreated,source);
					}else{
						createRelationship(r,source,targetCreated);}
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
			target = createCategoryClass((Class) searchObject);
			
			if(isSon){
				if(genSetName == null){
		 			genSetName = source.getLabel();
		 			existsGenSet = findGenSet(source, genSetName);
				}else{
					existsGenSet = findGenSet(source, genSetName);
				}
				
				if(existsGenSet != null){
					Generalization gen = new Generalization("",target,source,1,1,1,1);
					//System.out.println(target.getLabel() + " --> " + source.getLabel());
					source.getFunctions().add(gen);
					target.getFunctions().add(gen);
					existsGenSet.getGeneralizationElements().add(gen);

				}else{
					GeneralizationSet gs = new GeneralizationSet(source,1,1,disjoint, complete, genSetName);
					Generalization gen = new Generalization("",target,source,1,1,1,1);
					//System.out.println(target.getLabel() + " --> " + source.getLabel());

					source.getFunctions().add(gen);
					target.getFunctions().add(gen);
					gs.getGeneralizationElements().add(gen);
					source.getFunctions().add(gs);
					generalizationSpace.getFunctions().add(gs);
				}
			}
			else{	
				if(genSetName == null){
		 			genSetName = target.getLabel();
		 			existsGenSet = findGenSet(target, genSetName);
				}else{
					existsGenSet = findGenSet(target, genSetName);
				}
				
				GeneralizationSet gs = new GeneralizationSet(target,1,1,disjoint, complete, genSetName);
				Generalization gen = new Generalization("",source,target,1,1,1,1);
				gs.getGeneralizationElements().add(gen);
				//System.out.println(source.getLabel() + " --> " + target.getLabel());

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
					
					if(genSetName == null){
			 			genSetName = source.getLabel();
			 			existsGenSet = findGenSet(source, genSetName);
					}else{
						existsGenSet = findGenSet(source, genSetName);
					}
										
					if(existsGenSet != null){
						Generalization gen = new Generalization("",targetCreated,source,1,1,1,1);
						//System.out.println(targetCreated.getLabel() + " --> " + source.getLabel());

						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						existsGenSet.getGeneralizationElements().add(gen);

					}else{
						GeneralizationSet gs = new GeneralizationSet(source,1,1,disjoint, complete, genSetName);
						Generalization gen = new Generalization("",targetCreated,source,1,1,1,1);
						//System.out.println(targetCreated.getLabel() + " --> " + source.getLabel());

						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						gs.getGeneralizationElements().add(gen);
						source.getFunctions().add(gs);
						generalizationSpace.getFunctions().add(gs);

						}
				}else{

					if(genSetName == null){
			 			genSetName = targetCreated.getLabel();
			 			existsGenSet = findGenSet(targetCreated, genSetName);
					}else{
						existsGenSet = findGenSet(targetCreated, genSetName);
					}
					
					if(existsGenSet != null){
						Generalization gen = new Generalization("",source,targetCreated,1,1,1,1);
						//System.out.println(source.getLabel() + " --> " + targetCreated.getLabel());

						source.getFunctions().add(gen);
						targetCreated.getFunctions().add(gen);
						existsGenSet.getGeneralizationElements().add(gen);
						
					}else{
						GeneralizationSet gs = new GeneralizationSet(targetCreated,1,1,disjoint, complete, genSetName);
						Generalization gen = new Generalization("",source,targetCreated,1,1,1,1);
						//System.out.println(source.getLabel() + " --> " + targetCreated.getLabel());

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

public GeneralizationSet findGenSet(DescriptionCategory obj, String genLabel){
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

public int chooseTarget(String name, String name2, String label) {
	if(label.equals(name))
		return 1;
	if(label.equals(name2))
		return 0;
	return -1;	
}

private void createRelationship(Relationship r, DescriptionCategory target,DescriptionCategory source) {
	DescriptionFunction mat;
	int sourceLower,sourceUpper,targetLower,targetUpper;
	boolean essential, inseparable, shareable;
		
		
	
		if(r instanceof RefOntoUML.Generalization){
			mat = new Generalization("",source,target,1,1,1,1);	
			//System.out.println(source.getLabel() + " --> " + target.getLabel());
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);
			return;
			}
	
		//AJEITAR MULTIPLICIDADE
		
		//Find multiplicity source and target
		sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
		sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
		
		targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
		targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
				
		if(r instanceof RefOntoUML.Characterization){
						
			if(source instanceof Mode)
				mat = new Characterization(((Association)r).getName(),target,source, sourceLower, sourceUpper, targetLower, targetUpper);
			else
				mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
			
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

			}
		if(r instanceof RefOntoUML.componentOf){
			
			inseparable = ((RefOntoUML.componentOf) r).isIsInseparable();
			essential = ((RefOntoUML.componentOf) r).isIsEssential();
			shareable = ((RefOntoUML.componentOf) r).isIsShareable();
						
			sourceLower = ((RefOntoUML.componentOf) r).wholeEnd().getLower();
			sourceUpper = ((RefOntoUML.componentOf) r).wholeEnd().getUpper();
			
			targetLower = ((RefOntoUML.componentOf) r).partEnd().getLower();
			targetUpper = ((RefOntoUML.componentOf) r).partEnd().getUpper();
			
			if(((RefOntoUML.componentOf) r).whole().getName().equals(source.getLabel()))
				mat = new ComponentOf(((Association)r).getName(),target, source,  targetLower, targetUpper, sourceLower, sourceUpper, essential, inseparable, shareable);
			else
				mat = new ComponentOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, essential, inseparable, shareable);
			
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.FormalAssociation){
			
			mat = new Formal(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.MaterialAssociation){
			mat = new Material(((Association)r).getName(), source,target, sourceLower, sourceUpper,targetLower, targetUpper);
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.Mediation){	
			if(source instanceof Relator)
				mat = new Mediation(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower,targetUpper);
			else
				mat = new Mediation(((Association)r).getName(),target,source, sourceLower, sourceUpper, targetLower,targetUpper);
			
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.memberOf){
			
			inseparable = ((RefOntoUML.memberOf) r).isIsInseparable();
			essential = ((RefOntoUML.memberOf) r).isIsEssential();
			shareable = ((RefOntoUML.memberOf) r).isIsShareable();
			
			sourceLower = ((RefOntoUML.memberOf) r).wholeEnd().getLower();
			sourceUpper = ((RefOntoUML.memberOf) r).wholeEnd().getUpper();
			
			targetLower = ((RefOntoUML.memberOf) r).partEnd().getLower();
			targetUpper = ((RefOntoUML.memberOf) r).partEnd().getUpper();
						
			if(((RefOntoUML.memberOf) r).whole().getName().equals(source.getLabel()))
				mat = new MemberOf(((Association)r).getName(),target, source,  targetLower, targetUpper, sourceLower, sourceUpper, essential, inseparable, shareable);
			else
				mat = new MemberOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, essential, inseparable, shareable);

			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.subCollectionOf){
			
			inseparable = ((RefOntoUML.subCollectionOf) r).isIsInseparable();
			essential = ((RefOntoUML.subCollectionOf) r).isIsEssential();
			shareable = ((RefOntoUML.subCollectionOf) r).isIsShareable();
			
			sourceLower = ((RefOntoUML.subCollectionOf) r).wholeEnd().getLower();
			sourceUpper = ((RefOntoUML.subCollectionOf) r).wholeEnd().getUpper();
			
			targetLower = ((RefOntoUML.subCollectionOf) r).partEnd().getLower();
			targetUpper = ((RefOntoUML.subCollectionOf) r).partEnd().getUpper();
						
			if(((RefOntoUML.subCollectionOf) r).whole().getName().equals(source.getLabel()))
				mat = new SubcollectiveOf(((Association)r).getName(),target, source,  targetLower, targetUpper, sourceLower, sourceUpper, essential, inseparable, shareable);
			else
				mat = new SubcollectiveOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, essential, inseparable, shareable);

			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.subQuantityOf){
			
			inseparable = ((RefOntoUML.subQuantityOf) r).isIsInseparable();
			essential = ((RefOntoUML.subQuantityOf) r).isIsEssential();
			shareable = ((RefOntoUML.subQuantityOf) r).isIsShareable();
			
			sourceLower = ((RefOntoUML.subQuantityOf) r).wholeEnd().getLower();
			sourceUpper = ((RefOntoUML.subQuantityOf) r).wholeEnd().getUpper();
			
			targetLower = ((RefOntoUML.subQuantityOf) r).partEnd().getLower();
			targetUpper = ((RefOntoUML.subQuantityOf) r).partEnd().getUpper();
									
			if(((RefOntoUML.subQuantityOf) r).whole().getName().equals(source.getLabel()))
				mat = new SubquantityOf(((Association)r).getName(),target, source,  targetLower, targetUpper, sourceLower, sourceUpper, essential, inseparable, shareable);
			else
				mat = new SubquantityOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, essential, inseparable,shareable);

			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);
		}
	}

public int findLowerMultiplicity(Property p){
			return p.getLower();
		}
		
public int findUpperMultiplicity(Property p){
			return p.getUpper();
		}
		
public DescriptionCategory createCategory(Type type){
	
	if(type instanceof RefOntoUML.Category || type.toString().contains("RefOntoUML.impl.ClassImpl@")){
		DescriptionCategory mat = new Category(type.getName());
		return mat;
	}
	if(type instanceof RefOntoUML.Phase){
		DescriptionCategory mat = new Phase(type.getName());
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
