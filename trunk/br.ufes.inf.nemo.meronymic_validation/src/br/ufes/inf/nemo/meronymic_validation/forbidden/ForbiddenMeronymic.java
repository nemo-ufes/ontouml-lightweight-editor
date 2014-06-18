package br.ufes.inf.nemo.meronymic_validation.forbidden;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.Item;

public abstract class ForbiddenMeronymic<M extends Meronymic> extends Item{
	Enum<?> action;
	M meronymic;
	Classifier whole, part;
	ArrayList<Property> path;
	
	public ForbiddenMeronymic(M meronymic, OntoUMLParser parser) {
		super(parser);
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
		this.path.clear();
		this.path.addAll(path);
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
	
	public Package getRootPackage(){
		EObject currentContainer = meronymic.eContainer();
		
		while(currentContainer.eContainer()!=null)
			currentContainer = currentContainer.eContainer();
		
		if(currentContainer instanceof Package)
			return (Package) currentContainer;
			
		return null;
	}
	
	@Override
	public boolean hasAction() {
		return action!=null;
	}
	
	public abstract String getDescription();

}
