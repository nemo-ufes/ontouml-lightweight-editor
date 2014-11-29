package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.list.ArrayListOperations;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.IdentityDialog;

public class IdentityError extends MeronymicError<Classifier> {
	
	enum Action {SET_AS_IDENTITY_PROVIDER, SET_AS_INHERITS_IDENTITY};
	Action action;
	
	ArrayList<Classifier> identityProviders;

	private ClassStereotype stereotype;
	private Classifier identityProvider;
	
	public IdentityError(OntoUMLParser parser, Classifier type, ArrayList<Classifier> identityProviders) {
		super(parser,type);
		this.identityProviders = new ArrayList<Classifier>(identityProviders);
	}
	
	@Override
	public String getType() {
		if(identityProviders.size()==0)
			return "No identity provider defined";
		else if(identityProviders.size()>1)
			return "Multiple identity providers ("+identityProviders.size()+")";
		else if(element instanceof SubstanceSortal)
			return "Type already is an identity provider";
		return "UNDEFINED";
	}

	@Override
	public String getDescription() {
		String description = OntoUMLNameHelper.getTypeAndName(element, true, true);
		
		if(identityProviders.size()==0)
			return description;
		
		description+=" - Id. Prov.: ";
		int count = 0;
		int invParentsSize = identityProviders.size();
		
		for (Classifier invalidParent : identityProviders) {
			description += OntoUMLNameHelper.getTypeAndName(invalidParent, true, true);
			if(count<invParentsSize-1)
				description += ", ";
			count++;
		}
		return description;
	}

	@Override
	public JDialog createDialog(JDialog parent) {
		return new IdentityDialog(parent, this);
	}

	@Override
	public Fix fix() {

		if(action == Action.SET_AS_IDENTITY_PROVIDER)
			makeIdentityProvider();
		else if (action==Action.SET_AS_INHERITS_IDENTITY)
			makeInheritedIdentity();
		
		return fix;
	}
	
	@Override
	public boolean hasAction(){
		if(action == Action.SET_AS_IDENTITY_PROVIDER || action == Action.SET_AS_INHERITS_IDENTITY)
			return true;
		return false;
	}
	
	public int numberOfIdentityProviders(){
		return identityProviders.size();
	}

	public ArrayList<Classifier> getIdentityProviders() {
		return identityProviders;
	}

	public OntoUMLParser getParser() {
		return parser;
	}

	public void setAsIdentityProvider(ClassStereotype stereotype) {
		action = Action.SET_AS_IDENTITY_PROVIDER;
		this.stereotype = stereotype; 
	}

	public void setAsInheritsProvider(ClassStereotype stereotype, Classifier identityProvider) {
		action = Action.SET_AS_INHERITS_IDENTITY;
		this.stereotype = stereotype;
		this.identityProvider = identityProvider;
	}
	
	public void makeIdentityProvider(){
		
		Iterator<Generalization> iterator = element.getGeneralization().iterator();
		while(iterator.hasNext()){
			Generalization g = iterator.next();
			if(identityProviders.contains(g.getGeneral()) || ArrayListOperations.hasIntersection(identityProviders, g.getGeneral().allParents())){
				iterator.remove();
				fix.addAll(fixer.deleteElement(g));
			}
		}		
		
		if(OutcomeFixer.getClassStereotype(element)!=stereotype)
			fix.addAll(fixer.changeClassStereotypeTo(element, stereotype));
		
	}
	
	public void makeInheritedIdentity(){
		
		ArrayList<Classifier> identifiers = new ArrayList<Classifier>(identityProviders);
		identifiers.remove(identityProvider);

		Iterator<Generalization> iterator = element.getGeneralization().iterator();
		while(iterator.hasNext()){
			Generalization g = iterator.next();
			if(identifiers.contains(g.getGeneral()) || ArrayListOperations.hasIntersection(identifiers, g.getGeneral().allParents())){
				iterator.remove();
				fix.addAll(fixer.deleteElement(g));
			}
		}		
		
		if(!identityProviders.contains(identityProvider)){
			fix.addAll(fixer.createGeneralization(element, identityProvider));
		}
		
		
		if(OutcomeFixer.getClassStereotype(element)!=stereotype)
			fix.addAll(fixer.changeClassStereotypeTo(element, stereotype));
		
	}

}
