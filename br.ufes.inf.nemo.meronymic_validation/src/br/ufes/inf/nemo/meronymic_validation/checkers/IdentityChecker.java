package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class IdentityChecker extends Checker<Classifier>{

	
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
				for (Classifier parent : parser.retainSelected(objectClass.allParents())) {
					if(parent instanceof SubstanceSortal){
						identityProviders.add((SubstanceSortal) parent);
					}
				}
			}
		}
		
		if(errors!=null)
			errors.clear();
		else
			errors = new ArrayList<Classifier>();
		
		for (Classifier c : identityHash.keySet()) {
			if(hasProblem(c))
				errors.add(c);
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
	public String getErrorDescription(int i) {
		String description = OntoUMLNameHelper.getTypeAndName(getErrors().get(i), true, true);
		
		if(getIdentityProviders(errors.get(i)).size()==0)
			return description;
		
		description+=" - Iden. Prov.: ";
		int count = 0;
		int invParentsSize = getIdentityProviders(errors.get(i)).size();
		
		for (Classifier invalidParent : getIdentityProviders(errors.get(i))) {
			description += OntoUMLNameHelper.getTypeAndName(invalidParent, true, true);
			if(count<invParentsSize-1)
				description += ", ";
			count++;
		}
		return description;
	}

	@Override
	public String getErrorType(int i) {
		if(getIdentityProviders(errors.get(i)).size()==0)
			return "No identity provider defined";
		else
			return "Multiple identity providers ("+getIdentityProviders(errors.get(i)).size()+")";
	}

	
	
	
	
}
