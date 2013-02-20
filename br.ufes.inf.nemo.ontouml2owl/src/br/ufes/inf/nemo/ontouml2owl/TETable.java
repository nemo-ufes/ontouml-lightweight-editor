package br.ufes.inf.nemo.ontouml2owl;

import java.util.LinkedList;

import org.semanticweb.owlapi.model.OWLObject;

import RefOntoUML.PackageableElement;

public class TETable {
	private LinkedList<TransformedElement> table = new LinkedList<TransformedElement>();
	
	public void set(PackageableElement source, OWLObject target){
		TransformedElement te = new TransformedElement();
		te.set(source, target);
		
		table.add(te);
	}
}
