package br.ufes.inf.nemo.ontouml2owl;

import org.semanticweb.owlapi.model.OWLObject;

import RefOntoUML.Element;

public class TransformedElement {
	private Element sourceElement;
	private OWLObject targetElement;
		
	public void set( Element s, OWLObject t){
		this.sourceElement = s;
		this.targetElement = t;
	}
	
	public Element getSource() {
		return this.sourceElement;
	}
	
	public OWLObject getTarget(){
		return this.targetElement;
	}
}

