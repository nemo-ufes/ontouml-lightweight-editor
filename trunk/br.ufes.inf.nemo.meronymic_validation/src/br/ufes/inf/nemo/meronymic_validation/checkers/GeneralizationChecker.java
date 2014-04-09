package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Mixin;
import RefOntoUML.ObjectClass;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class GeneralizationChecker {

	OntoUMLParser parser;
	HashMap<Classifier,ArrayList<Classifier>> forbiddenParentsHash;
	
	public GeneralizationChecker(OntoUMLParser parser) {
		this.parser = parser;
		this.forbiddenParentsHash = null;
	}
	
	/**
	 * 
	 * @return false if there is a problem
	 */
	public void check(){
		
		if(forbiddenParentsHash==null)
			forbiddenParentsHash = new HashMap<Classifier,ArrayList<Classifier>>();
		else
			forbiddenParentsHash.clear();
		
		for (Classifier objectClass : parser.getAllInstances(ObjectClass.class)) {
			
			ArrayList<Classifier> forbiddenParents = new ArrayList<Classifier>();
			forbiddenParentsHash.put(objectClass, forbiddenParents);
			
			//Substance Sortals can only be generalized into Categories and Mixins
			if(objectClass instanceof SubstanceSortal){
				for (Classifier parent : parser.getParents(objectClass)) {
					if(!(parent instanceof Category) && !(parent instanceof Mixin)){
						forbiddenParents.add(parent);
					}
				}
			}
			
			//Subkinds can't be generalized into Roles, Phases and RoleMixins
			else if(objectClass instanceof SubKind){
				for (Classifier parent : parser.getParents(objectClass)) {
					if(parent instanceof Role || parent instanceof Phase && parent instanceof RoleMixin){
						forbiddenParents.add(parent);
					}
				}
			}
			
			//Roles and Phases can't be generalized into Categories
			else if(objectClass instanceof Role || objectClass instanceof Phase){
				for (Classifier parent : parser.getParents(objectClass)) {
					if(parent instanceof Category){
						forbiddenParents.add(parent);
					}
				}
			}
			
			//Categories can only be generalized into Mixins and Categories
			else if(objectClass instanceof Category || objectClass instanceof Mixin){
				for (Classifier parent : parser.getParents(objectClass)) {
					if(!(parent instanceof Category) && !(parent instanceof Mixin)){
						forbiddenParents.add(parent);
					}
				}
			}
			
			//RoleMixins can only be generalized into RoleMixins, Mixins and Categories
			else if(objectClass instanceof RoleMixin){
				for (Classifier parent : parser.getParents(objectClass)) {
					if(!(parent instanceof Category) && !(parent instanceof Mixin) && !(parent instanceof RoleMixin)){
						forbiddenParents.add(parent);
					}
				}
			}		
		}
	}
	
	public boolean hasProblem(Classifier sortal){
		if(forbiddenParentsHash==null)
			check();
		
		ArrayList<Classifier> forbiddenParents = forbiddenParentsHash.get(sortal);
		
		if(forbiddenParents==null)
			return false;
		
		return forbiddenParents.size()>0;
	}

	public ArrayList<Classifier> getClassesWithError() {
		ArrayList<Classifier> classesWithError = new ArrayList<Classifier>();

		for (Classifier objectClass : forbiddenParentsHash.keySet()) {
			if(hasProblem(objectClass))
				classesWithError.add(objectClass);
		}
		
		return classesWithError;
	}

	public ArrayList<Classifier> getForbiddenParents(Classifier objectClass) {
		return forbiddenParentsHash.get(objectClass);
	}
}
