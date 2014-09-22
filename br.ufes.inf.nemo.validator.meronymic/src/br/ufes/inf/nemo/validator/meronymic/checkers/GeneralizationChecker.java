package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.ObjectClass;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLParser;

public class GeneralizationChecker extends Checker<GeneralizationError>{

	HashMap<Classifier,ArrayList<Classifier>> forbiddenParentsHash;
	
	public GeneralizationChecker(OntoUMLParser parser) {
		super(parser);
		this.forbiddenParentsHash = null;
	}
	
	/**
	 * 
	 * @return false if there is a problem
	 */
	@Override
	public boolean check(){
		
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
		
		errors.clear();
		
		for (Classifier child : forbiddenParentsHash.keySet()) {
			for (Classifier parent : forbiddenParentsHash.get(child)) {
				for (Generalization g : getGeneralizationsBetween(child,parent)) {
					errors.add(new GeneralizationError(parser,g));
				}
			}
		}
		
		if(errors.size()>0)
			return false;
		
		return true;
	}
	
	public boolean hasProblem(Classifier sortal){
		if(forbiddenParentsHash==null)
			check();
		
		ArrayList<Classifier> forbiddenParents = forbiddenParentsHash.get(sortal);
		
		if(forbiddenParents==null)
			return false;
		
		return forbiddenParents.size()>0;
	}

	public ArrayList<Classifier> getForbiddenParents(Classifier objectClass) {
		return forbiddenParentsHash.get(objectClass);
	}
	
	public ArrayList<Generalization> getGeneralizationsBetween(Classifier child, Classifier parent){
		ArrayList<Generalization> list = new ArrayList<Generalization>();
		
		for (Generalization g : child.getGeneralization()) {
			if(g.getGeneral().equals(parent))
				list.add(g);
		}
		
		return list;
	}

	@Override
	public String checkerName() {
		return "Valid Specialization";
	}
	
}
