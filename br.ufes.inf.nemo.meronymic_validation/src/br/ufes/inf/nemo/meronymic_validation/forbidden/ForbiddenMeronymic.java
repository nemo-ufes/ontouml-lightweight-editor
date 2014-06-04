package br.ufes.inf.nemo.meronymic_validation.forbidden;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.Package;

public abstract class ForbiddenMeronymic<M extends Meronymic> {
	Enum<?> action;
	M meronymic;
	Classifier whole, part;
	ArrayList<Property> path;
	
	public ForbiddenMeronymic(M meronymic) {
		this.meronymic = meronymic;
		path = new ArrayList<Property>();
		whole = (Classifier) OntoUMLParser.getWholeEnd(meronymic).getType();
		part = (Classifier) OntoUMLParser.getPartEnd(meronymic).getType();
		action = null;
	}

	public ArrayList<Property> getPath() {
		return path;
	}

	public void setPath(ArrayList<Property> path) {
		this.path = new ArrayList<Property>(path);
	}

	public M getMeronymic() {
		return meronymic;
	}
	
	public Classifier getWhole() {
		return whole;
	}

	public Classifier getPart() {
		return part;
	}
	
	public boolean isActionSet(){
		return action!=null;
	}
	
	public Package getRootPackage(){
		EObject currentContainer = meronymic.eContainer();
		
		while(currentContainer.eContainer()!=null)
			currentContainer = currentContainer.eContainer();
		
		if(currentContainer instanceof Package)
			return (Package) currentContainer;
			
		return null;
	}

	public abstract String getDescription();
	
	public abstract Fix runFix();

}
