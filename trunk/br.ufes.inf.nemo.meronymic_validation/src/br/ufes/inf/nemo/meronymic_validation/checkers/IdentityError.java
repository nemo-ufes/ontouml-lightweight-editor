package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class IdentityError extends MeronymicError<Classifier> {
	
	ArrayList<Classifier> identityProviders;
	
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
	public JDialog createDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fix fix() {
		// TODO Auto-generated method stub
		return null;
	}

}
