package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLParser;

public class IdentityChecker extends Checker<IdentityError>{

	
	HashMap<Classifier,ArrayList<Classifier>> identityHash;
	
	public IdentityChecker(OntoUMLParser parser) {
		super(parser);
		this.identityHash = null;
	}
	
	/**
	 * 
	 * @return false if there is a problem
	 */
	@Override
	public boolean check(){

		if(identityHash==null)
			this.identityHash = new HashMap<Classifier,ArrayList<Classifier>>();
		else
			this.identityHash.clear();
		
		for (Classifier objectClass : parser.getAllInstances(SortalClass.class)) {
			if(objectClass instanceof SortalClass){
				ArrayList<Classifier> identityProviders = new ArrayList<Classifier>();
				identityHash.put(objectClass, identityProviders);
				for (Classifier parent : parser.getAllParents(objectClass)) {
					if(parent instanceof SubstanceSortal){
						identityProviders.add((SubstanceSortal) parent);
					}
				}
			}
		}
		
		errors.clear();
		
		for (Classifier c : identityHash.keySet()) {
			if(hasProblem(c))
				errors.add(new IdentityError(parser, c, getIdentityProviders(c)));
		}
		
		if(errors.size()>0)
			return false;
		
		return true;
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

	public ArrayList<Classifier> getIdentityProviders(Classifier sortalClass) {
		return identityHash.get(sortalClass);
	}

	@Override
	public String checkerName() {
		return "Valid Identities";
	}
	
}
