package br.ufes.inf.nemo.ontouml2owl;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLObject;

import RefOntoUML.PackageableElement;

public class TransformedElement {
	private PackageableElement sourceElement;
	private OWLObject targetElement;
	
	public void set( PackageableElement s, OWLObject t){
		this.sourceElement = s;
		this.targetElement = t;
	}
	
	public PackageableElement getSource() {
		return this.sourceElement;
	}
	
	public OWLObject getTarget(){
		return this.targetElement;
	}
}

