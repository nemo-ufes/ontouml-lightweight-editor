package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class IdentityChecker {

	OntoUMLParser parser;
	HashMap<Classifier,ArrayList<Classifier>> identityHash;
	
	public IdentityChecker(OntoUMLParser parser) {
		this.parser = parser;
		this.identityHash = null;
	}
	
	/**
	 * 
	 * @return false if there is a problem
	 */
	public void check(){

		if(identityHash==null)
			this.identityHash = new HashMap<Classifier,ArrayList<Classifier>>();
		else
			this.identityHash.clear();
		
		for (Classifier objectClass : parser.getAllInstances(SortalClass.class)) {
			if(objectClass instanceof SortalClass){
				ArrayList<Classifier> identityProviders = new ArrayList<Classifier>();
				identityHash.put(objectClass, identityProviders);
				for (Classifier parent : parser.retainSelected(objectClass.allParents())) {
					if(parent instanceof SubstanceSortal){
						identityProviders.add((SubstanceSortal) parent);
					}
				}
			}
		}
	}
	
	public boolean hasProblem(Classifier sortal){
		if(identityHash==null)
			check();
		
		ArrayList<Classifier> identityProviders = identityHash.get(sortal);
		if(identityProviders==null)
			return false;
		
		if(sortal instanceof SubstanceSortal)
			return !(identityProviders.size()==0);
		
		else if(sortal instanceof SubKind || sortal instanceof AntiRigidSortalClass)
			return !(identityProviders.size()==1);
		
		return false;
	}

	public ArrayList<Classifier> getClassesWithError() {
		ArrayList<Classifier> classesWithError = new ArrayList<Classifier>();

		for (Classifier sortal : identityHash.keySet()) {
			if(hasProblem(sortal))
				classesWithError.add(sortal);
		}
		
		return classesWithError;
	}

	public ArrayList<Classifier> getIdentityProviders(Classifier sortalClass) {
		return identityHash.get(sortalClass);
	}

	
	
	
	
}
