package br.ufes.inf.nemo.validator.meronymic.forbidden;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.validator.meronymic.MeronymicItem;

public abstract class ForbiddenMeronymic<M extends Meronymic> extends MeronymicItem{
	M meronymic;
	
	public ForbiddenMeronymic(M meronymic, OntoUMLParser parser) {
		super(parser);
		this.meronymic = meronymic;
		path = new ArrayList<Property>();
		meronymicPath = new ArrayList<Meronymic>();
		whole = (Classifier) OntoUMLParser.getWholeEnd(meronymic).getType();
		part = (Classifier) OntoUMLParser.getPartEnd(meronymic).getType();
		action = null;
	}

	public M getMeronymic() {
		return meronymic;
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
