package br.ufes.inf.nemo.ontouml2text.descriptionSpaceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2text.descriptionSpace.CategoryAttribute;
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
	
public void populateDescriptionSpace(OntoUMLParser parser, Set<String> hashCategories, boolean inheritMediations){
		Set <RefOntoUML.Class> classfSet = parser.getAllInstances(RefOntoUML.Class.class);	
		
		for (RefOntoUML.Class classf : classfSet){
			DescriptionCategory mat;
									
			if(generalizationSpace.findCategory(classf.getName()) == null){
				mat = createCategoryClass(classf, classfSet);
				generalizationSpace.addCategory(mat);
			}else											
				mat = generalizationSpace.findCategory(classf.getName());
			
			populateRelationships(parser.getDirectRelationships(classf),mat,parser,hashCategories,classfSet);	
		}

		if(inheritMediations)
			relatorIheritance(generalizationSpace.getCategories(),hashCategories);
}

public void relatorIheritance(List<DescriptionCategory> categories, Set<String> hashCategories) {
	Generalization gen;
	DescriptionCategory upCategory;
	hashCategories.clear();
	
	for(DescriptionCategory c : categories){	
		if(c instanceof Relator){				
			gen = (Generalization) verifyGeneralization(c.getFunctions(),c);
			
			if( gen != null){ 
				
				upCategory = upFunction(gen); 
				getDownMediation(upCategory,c,hashCategories);	
			
			}else
				continue;
		}		
	}
}

public DescriptionCategory upFunction(Generalization gen) {

	DescriptionCategory target = gen.getTarget();
	Generalization genTarget;
	
	while(true){
		genTarget = (Generalization) verifyGeneralization(target.getFunctions(),target); //verifica se tem algo acima
		if(genTarget != null)
			target = genTarget.getTarget();
		else 
			break;
	}
	return target;
}

private void getDownMediation(DescriptionCategory upCategory,DescriptionCategory c, Set<String> hashCategories) {
	
	ArrayList<DescriptionFunction> noIheritanceMeds = null;
	DescriptionCategory downCategory = null;
	Generalization gen;
	GeneralizationSet genSet;
	
	while(true){
		genSet = VerifyGeneralizationSet(upCategory.getFunctions(), upCategory);
		gen = (Generalization) verifyGeneralizationTarget(upCategory.getFunctions(), upCategory);		

		if(genSet != null){ 
			for(Generalization g : genSet.getGeneralizationElements()){			
				
				noIheritanceMeds = deleteMediations(g);
				addMediations(upCategory.getFunctions(), g.getSource(), noIheritanceMeds);
				getDownMediation(g.getSource(),c,hashCategories);   
			}
		}
		if( gen != null){		
			noIheritanceMeds = deleteMediations(gen);
			downCategory = gen.getSource();
			addMediations(upCategory.getFunctions(),downCategory, noIheritanceMeds);					
		}
		
		if(gen == null)		
			break;
		
		upCategory = downCategory;
	}
	
}

public ArrayList<DescriptionFunction> deleteMediations(Generalization gen){
	
	ArrayList<DescriptionFunction> noIheritanceMeds = new ArrayList<>();
	DescriptionCategory up = gen.getTarget();
	DescriptionCategory down = gen.getSource();
	DescriptionCategory sourceMed;
	DescriptionCategory targetMed;
	
	for(DescriptionFunction medUp : up.getFunctions()){	
			if(medUp instanceof Mediation){				
				
				targetMed = medUp.getTarget();		
				
				for(DescriptionFunction medDown : down.getFunctions()){	
						
					sourceMed = medDown.getTarget();
					if (MedIheritance(sourceMed, targetMed)){
						noIheritanceMeds.add(medUp);		
					}
				}
			
			}
	}
	return noIheritanceMeds;
	
}

private boolean MedIheritance(DescriptionCategory sourceMed, DescriptionCategory targetMed) {
	for(DescriptionFunction g :sourceMed.getFunctions()){
		if(g instanceof Generalization)
			if(g.getTarget().equals(targetMed)){
				return true;
			}
	}
	return false;
}

public GeneralizationSet VerifyGeneralizationSet(List <DescriptionFunction> arrayList, DescriptionCategory element){
	for(DescriptionFunction r : arrayList){
		if(r instanceof GeneralizationSet){
			return (GeneralizationSet) r;		//Se tiver uma generalizationSet, retorna a GenSet
		}
	}
	return null;
}

public void addMediations(List<DescriptionFunction> list, DescriptionCategory downCategory, ArrayList<DescriptionFunction> noIheritanceMeds){
	for(DescriptionFunction m : list){
		if(m instanceof Mediation){
			if(  (!verifyEqualMediation(downCategory.getFunctions(),m)) && (!notAddMediation (noIheritanceMeds, m))  ){
				DescriptionFunction med = new Mediation("", downCategory, m.getTarget(), ((Mediation) m).getSourceMinMultiplicity(), ((Mediation) m).getSourceMaxMultiplicity(), m.getTargetMinMultiplicity(), m.getTargetMaxMultiplicity());
				downCategory.getFunctions().add(med);
			}
		}
	}
}

private boolean notAddMediation(ArrayList<DescriptionFunction> noIheritanceMeds, DescriptionFunction m) {

	for(DescriptionFunction med : noIheritanceMeds ) //se a mediation que eu quiser adicionar estiver na lista de nao adicionar, retorna true
		if(med.equals(m))
			return true;

	return false;
}

public boolean verifyEqualMediation(List<DescriptionFunction> list ,DescriptionFunction m){
	for(DescriptionFunction df : list){
		if(df.getTarget().equals(m.getTarget())){
			return true;
		}
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

public DescriptionCategory createCategoryClass(Class classf, Set<Class> classfSet) {	

	DescriptionCategory mat = null;
	String userDesc;
	String t = null;
	
	if(classf instanceof RefOntoUML.Category || classf.toString().contains("RefOntoUML.impl.ClassImpl@"))
		mat = new Category(classf.getName());

	if(classf instanceof RefOntoUML.Phase)
		mat = new Phase(classf.getName());

	if(classf instanceof RefOntoUML.Collective)
		mat = new Collective(classf.getName());
		
	if(classf instanceof RefOntoUML.Kind)
		mat = new Kind(classf.getName());
	
	if(classf instanceof RefOntoUML.SubKind)
		mat = new Subkind(classf.getName());
	
	if(classf instanceof RefOntoUML.Mixin)
		mat = new Mixin(classf.getName());
	
	if(classf instanceof RefOntoUML.Mode)
		mat = new Mode(classf.getName());
	
	if(classf instanceof RefOntoUML.Quantity)
		mat = new Quantity(classf.getName());
	
	if(classf instanceof RefOntoUML.Relator)
		mat = new Relator(classf.getName());
	
	if(classf instanceof RefOntoUML.Role)
		mat = new Role(classf.getName());
	
	if(classf instanceof RefOntoUML.RoleMixin)
		mat = new RoleMixin(classf.getName());
	
	for(Property atribute: classf.getAttribute()){

		if(atribute.getName() != null ){
			if(atribute.getName().equals(""))
			System.out.println(mat.getLabel());
			
		}
		if(atribute.getType() != null)
			t = atribute.getType().getName();
		
		CategoryAttribute at = new CategoryAttribute(atribute.getName(),t);
		mat.getAttributes().add(at);
	}
	
	userDesc = getUserDescription(mat.getLabel(),classfSet);
	if(!userDesc.equals(""))
		mat.setUserDescription(userDesc);

	return mat;
}

public String getUserDescription(String string, Set<Class> classfSet) {
	for(Class cl : classfSet){
		if(cl.getName().equals(string) && cl.getOwnedComment().size() != 0){
			return cl.getOwnedComment().get(0).getBody().replace("Definition=", "");
		}
	}
	return "";
}

public void populateRelationships(ArrayList<Relationship> eList, DescriptionCategory source, OntoUMLParser parser, 
		Set<String> hashCategories, Set<Class> classfSet) {
	
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

			if(r instanceof RefOntoUML.MaterialAssociation && existsRelator(((Association) r).getEndType().get(0),((Association) r).getEndType().get(1))){
				if(source instanceof Relator){
					continue;
				}else{
					if(generalizationSpace.findCategory(source.getLabel())!= null)
						continue;
					else{
						createCategory(((Association) r).getEndType().get(classNumberTarget),classfSet);
					}		
				}
			}
			
			if(endType0.equals(endType1)){
				createRelationship(r, source, source,parser);
				continue;
			}
			
			if(generalizationSpace.findCategory(((Association) r).getEndType().get(classNumberTarget).getName()) == null){
				target = createCategory(((Association) r).getEndType().get(classNumberTarget),classfSet);
				
				createRelationship(r,target,source,parser);
				generalizationSpace.addCategory(target);
				continue;
			
			}else{
				if(hashCategories.contains(((RefOntoUML.Association) r).getEndType().get(classNumberTarget).getName())) //verifica se ta na hash,se ja foi percorrido
					continue;
				else{
					DescriptionCategory targetCreated = generalizationSpace.findCategory(((RefOntoUML.Association) r).getEndType().get(classNumberTarget).getName());
					createRelationship(r, targetCreated, source,parser);
				} 
			}	
			
		}
		
		if(r instanceof RefOntoUML.Generalization){
			
			Classifier searchObject;
			boolean isSon;
			String specificName=null;
			String searchObjName =null;
			
			if(((RefOntoUML.Generalization) r).getGeneralizationSet().size() > 0){
				processGeneralizationSet(((RefOntoUML.Generalization) r),source,hashCategories, classfSet);
				continue;
			}
		 	
			specificName = ((RefOntoUML.Generalization) r).getSpecific().getName();
		
			if(specificName.contains("/"))
				specificName = specificName.replace("/","");
			
			if(source.getLabel().equals(specificName)){
		 		searchObject = ((RefOntoUML.Generalization) r).getGeneral();
		 		isSon = true;
		 	}
		 	else{ 
		 		searchObject = ((RefOntoUML.Generalization) r).getSpecific();
		 		isSon = false;
		 	}		
		 	
		
			if(generalizationSpace.findCategory(searchObject.getName()) == null){
				target = createCategoryClass((Class) searchObject, classfSet);
				
				if(isSon){
					createRelationship(r,target,source,parser);
				}else{
					createRelationship(r,source,target,parser);
				}
				generalizationSpace.addCategory(target);
				continue;
			}
			
			else{ 
				
				searchObjName = searchObject.getName();
				
				if(searchObjName.contains("/")){
					searchObjName = searchObjName.replace("/","");
				}
				
				if(hashCategories.contains(searchObjName)) 
					continue;	
				
				else{
					DescriptionCategory targetCreated = generalizationSpace.findCategory(searchObject.getName());			
	
					if(isSon){
						createRelationship(r,targetCreated,source,parser);
					}else{
						createRelationship(r,source,targetCreated,parser);}
				}
			}	
		}
	}
	hashCategories.add(source.getLabel());
}

private void processGeneralizationSet(RefOntoUML.Generalization r, DescriptionCategory source, Set<String> hashCategories, Set<Class> classfSet) {
	Classifier searchObject;
	DescriptionCategory target;
 	boolean isSon;
 	boolean disjoint; 
 	boolean complete;
 	String genSetName;
 	GeneralizationSet existsGenSet;
 	String searchObjName = null;
	String specificName = null;

 	for(RefOntoUML.GeneralizationSet genSet : r.getGeneralizationSet()){ 		
 		complete = genSet.isIsCovering();
 		disjoint = genSet.isIsDisjoint(); 			
 		genSetName = genSet.getName();

 		specificName = ((RefOntoUML.Generalization) r).getSpecific().getName();
		
		if(specificName.contains("/"))
			specificName = specificName.replace("/","");
		
 		
		if(source.getLabel().equals(specificName)){ 
	 		searchObject = r.getGeneral();
	 		isSon = false;
	 	}
	 	else{ 																	
	 		searchObject =r.getSpecific(); 
	 		isSon = true;
	 	}	
		
		if(generalizationSpace.findCategory(searchObject.getName()) == null){
			target = createCategoryClass((Class) searchObject, classfSet);
			
			if(isSon){
				if(genSetName == null){
		 			genSetName = source.getLabel();
		 			existsGenSet = findGenSet(source, genSetName);
				}else{
					existsGenSet = findGenSet(source, genSetName);
				}
				
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
				if(genSetName == null){
		 			genSetName = target.getLabel();
		 			existsGenSet = findGenSet(target, genSetName);
				}else{
					existsGenSet = findGenSet(target, genSetName);
				}
				
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
			
			searchObjName = searchObject.getName();
			
			if(searchObjName.contains("/")){
				searchObjName = searchObjName.replace("/","");
			}
			
			if(hashCategories.contains(searchObjName))
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

					if(genSetName == null){
			 			genSetName = targetCreated.getLabel();
			 			existsGenSet = findGenSet(targetCreated, genSetName);
					}else{
						existsGenSet = findGenSet(targetCreated, genSetName);
					}
					
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

private void createRelationship(Relationship r, DescriptionCategory target,DescriptionCategory source, OntoUMLParser parser) {
	DescriptionFunction mat;
	int sourceLower,sourceUpper,targetLower,targetUpper;
	boolean essential, inseparable, shareable;
	
		if(r instanceof RefOntoUML.Generalization){
					
			mat = new Generalization("",source,target,1,1,1,1);	
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);
			return;
			}

				
		if(r instanceof RefOntoUML.Characterization){

			if(source instanceof Mode && target instanceof Mode){
			
				if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));

					mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
				}else{
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));

					mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
				}
				
				source.getFunctions().add(mat);
				target.getFunctions().add(mat);
				generalizationSpace.getFunctions().add(mat);
				return;
			}
			
			if(source instanceof Mode){
				if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					
					mat = new Characterization(((Association)r).getName(),target,source, sourceLower, sourceUpper, targetLower, targetUpper);
				}
				else{
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));

					mat = new Characterization(((Association)r).getName(),target,source,targetLower, targetUpper, sourceLower, sourceUpper);

				}
			} else{
					if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
						sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
						sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
						targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
						targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));

						mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
					}
					else{
						sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
						sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
						targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
						targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
						
						mat = new Characterization(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);

					}				
			}

			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

			}
		if(r instanceof RefOntoUML.componentOf){
			
			inseparable = ((RefOntoUML.componentOf) r).isIsInseparable();
			essential = ((RefOntoUML.componentOf) r).isIsEssential();
			shareable = ((RefOntoUML.componentOf) r).isIsShareable();		
			
			targetLower = ((RefOntoUML.componentOf) r).wholeEnd().getLower();
			targetUpper = ((RefOntoUML.componentOf) r).wholeEnd().getUpper();
			
			sourceLower = ((RefOntoUML.componentOf) r).partEnd().getLower();
			sourceUpper = ((RefOntoUML.componentOf) r).partEnd().getUpper();
			
			if(((RefOntoUML.componentOf) r).whole().getName().equals(source.getLabel()))	
				mat = new ComponentOf(((Association)r).getName(),target, source, sourceLower, sourceUpper,targetLower, targetUpper, essential, inseparable, shareable);
			else
				mat = new ComponentOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, essential, inseparable, shareable);
			
			
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.FormalAssociation){
			
			if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
								
				sourceLower = ((Association) r).getMemberEnd().get(0).getLower();
				sourceUpper = ((Association) r).getMemberEnd().get(0).getUpper();
				
				targetLower = ((Association) r).getMemberEnd().get(1).getLower();
				targetUpper = ((Association) r).getMemberEnd().get(1).getUpper();

				mat = new Formal(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			
			}else{
							
				sourceLower = ((Association) r).getMemberEnd().get(1).getLower();
				sourceUpper = ((Association) r).getMemberEnd().get(1).getUpper();
				
				targetLower = ((Association) r).getMemberEnd().get(0).getLower();
				targetUpper = ((Association) r).getMemberEnd().get(0).getUpper();

				mat = new Formal(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			}
			
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.MaterialAssociation){
			
			if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
				
				sourceLower = ((Association) r).getMemberEnd().get(0).getLower();
				sourceUpper = ((Association) r).getMemberEnd().get(0).getUpper();
				
				targetLower = ((Association) r).getMemberEnd().get(1).getLower();
				targetUpper = ((Association) r).getMemberEnd().get(1).getUpper();

				mat = new Material(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			
			}else{
							
				sourceLower = ((Association) r).getMemberEnd().get(1).getLower();
				sourceUpper = ((Association) r).getMemberEnd().get(1).getUpper();
				
				targetLower = ((Association) r).getMemberEnd().get(0).getLower();
				targetUpper = ((Association) r).getMemberEnd().get(0).getUpper();

				mat = new Material(((Association)r).getName(), source, target, sourceLower,sourceUpper, targetLower, targetUpper);
			}
				
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.Mediation){	
			
			if(source instanceof Relator && target instanceof Relator){
				
				if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));

					mat = new Mediation(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
				}else{
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));

					mat = new Mediation(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
				}
				
				source.getFunctions().add(mat);
				target.getFunctions().add(mat);
				generalizationSpace.getFunctions().add(mat);
				return;
			}
			
			if(source instanceof Relator){
				if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
					
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
										
					mat = new Mediation(((Association)r).getName(),source,target, sourceLower, sourceUpper, targetLower, targetUpper);
				}
				else{
					sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
					sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
					targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
					targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
					
					mat = new Mediation(((Association)r).getName(),source,target,targetLower, targetUpper, sourceLower, sourceUpper);

				}
			} else{
					if(source.getLabel().equals(((Association) r).getMemberEnd().get(0).getType().getName())){
						sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
						sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
						targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
						targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
						
						mat = new Mediation(((Association)r).getName(),target,source, sourceLower, sourceUpper, targetLower, targetUpper);
					}
					else{
						sourceLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(0));
						sourceUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(0));
						targetLower = findLowerMultiplicity(((Association) r).getMemberEnd().get(1));
						targetUpper = findUpperMultiplicity(((Association) r).getMemberEnd().get(1));
						
						mat = new Mediation(((Association)r).getName(),target,source, sourceLower, sourceUpper, targetLower, targetUpper);

					}				
			}
			
			source.getFunctions().add(mat);
			target.getFunctions().add(mat);
			generalizationSpace.getFunctions().add(mat);

		}
		if(r instanceof RefOntoUML.memberOf){
			
			inseparable = ((RefOntoUML.memberOf) r).isIsInseparable();
			essential = ((RefOntoUML.memberOf) r).isIsEssential();
			shareable = ((RefOntoUML.memberOf) r).isIsShareable();
			
			targetLower = ((RefOntoUML.memberOf) r).wholeEnd().getLower();
			targetUpper = ((RefOntoUML.memberOf) r).wholeEnd().getUpper();
			
			sourceLower = ((RefOntoUML.memberOf) r).partEnd().getLower();
			sourceUpper = ((RefOntoUML.memberOf) r).partEnd().getUpper();
			
			if(((RefOntoUML.memberOf) r).whole().getName().equals(source.getLabel()))	
				mat = new MemberOf(((Association)r).getName(),target, source, sourceLower, sourceUpper,targetLower, targetUpper, essential, inseparable, shareable);
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
			
			targetLower = ((RefOntoUML.subCollectionOf) r).wholeEnd().getLower();
			targetUpper = ((RefOntoUML.subCollectionOf) r).wholeEnd().getUpper();
			
			sourceLower = ((RefOntoUML.subCollectionOf) r).partEnd().getLower();
			sourceUpper = ((RefOntoUML.subCollectionOf) r).partEnd().getUpper();
			
			if(((RefOntoUML.subCollectionOf) r).whole().getName().equals(source.getLabel()))	
				mat = new SubcollectiveOf(((Association)r).getName(),target, source, sourceLower, sourceUpper,targetLower, targetUpper, essential, inseparable, shareable);
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
			
			targetLower = ((RefOntoUML.subQuantityOf) r).wholeEnd().getLower();
			targetUpper = ((RefOntoUML.subQuantityOf) r).wholeEnd().getUpper();
			
			sourceLower = ((RefOntoUML.subQuantityOf) r).partEnd().getLower();
			sourceUpper = ((RefOntoUML.subQuantityOf) r).partEnd().getUpper();
			
			if(((RefOntoUML.subQuantityOf) r).whole().getName().equals(source.getLabel()))	
				mat = new SubquantityOf(((Association)r).getName(),target, source, sourceLower, sourceUpper,targetLower, targetUpper, essential, inseparable, shareable);
			else
				mat = new SubquantityOf(((Association)r).getName(), source, target, sourceLower, sourceUpper, targetLower, targetUpper, essential, inseparable, shareable);
		
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
		
public DescriptionCategory createCategory(Type type, Set<Class> classfSet){

	DescriptionCategory mat = null;
	String userDesc;
	String t = null;
	
	if(type instanceof RefOntoUML.Category || type.toString().contains("RefOntoUML.impl.ClassImpl@"))
		mat = new Category(type.getName());
	
	if(type instanceof RefOntoUML.Phase)
		mat = new Phase(type.getName());
	
	if(type instanceof RefOntoUML.Collective)
		mat = new Collective(type.getName());
		
	if(type instanceof RefOntoUML.Kind)
		mat = new Kind(type.getName());
		
	if(type instanceof RefOntoUML.SubKind)
		mat = new Subkind(type.getName());
	
	if(type instanceof RefOntoUML.Mixin)
		mat = new Mixin(type.getName());
	
	if(type instanceof RefOntoUML.Mode)
		mat = new Mode(type.getName());
	
	if(type instanceof RefOntoUML.Quantity)
		mat = new Quantity(type.getName());
	
	if(type instanceof RefOntoUML.Relator)
		mat = new Relator(type.getName());
	
	if(type instanceof RefOntoUML.Role)
		mat = new Role(type.getName());
	
	if(type instanceof RefOntoUML.RoleMixin)
		mat = new RoleMixin(type.getName());
	
	for(Property atribute: ((Classifier) type).getAttribute()){
		if(atribute.getName() != null ){
			if(atribute.getName().equals(""))
			System.out.println(mat.getLabel());
		}
		if(atribute.getType() != null)
			t = atribute.getType().getName();
		
		CategoryAttribute at = new CategoryAttribute(atribute.getName(), t);
		mat.getAttributes().add(at);
	}
	
	userDesc = getUserDescription(mat.getLabel(),classfSet);
	if(!userDesc.equals(""))
		mat.setUserDescription(userDesc);

	return mat;
}		

}
