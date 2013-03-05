package br.ufes.inf.nemo.ontouml2owl;

import java.util.LinkedList;

import org.semanticweb.owlapi.model.OWLObject;

import RefOntoUML.Element;
import RefOntoUML.PackageableElement;

public class TETable {
	private LinkedList<TransformedElement> table = new LinkedList<TransformedElement>();
	
	public void set(Element source, OWLObject target){
		TransformedElement te = new TransformedElement();
		te.set(source, target);
		
		table.add(te);
	}
	
	public void show(){
		for ( TransformedElement te : table){
			System.out.println(te.getSource() + " <-> " + te.getTarget());
		}
	}
	
	public OWLObject getOWLCorrespondence(Element ont_obj){
		for ( TransformedElement te : table )
		{
			if ( te.getSource() == ont_obj ) return te.getTarget();
		}
		System.out.println("ERRO: Objeto não encontrado. " + ont_obj);
		return null;
	}
}
