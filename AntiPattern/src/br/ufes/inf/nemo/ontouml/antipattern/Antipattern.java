package br.ufes.inf.nemo.ontouml.antipattern;

import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Package;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class Antipattern {
	/*Model that contains only the relevant classes for the transformation to alloy*/
	Package model;
	
	/*specific parser for the model*/
	OntoUMLParser parser;
	
	/*mapper between the original model and the reduced one*/
	Copier copier;
	
	protected abstract void buildModel();
}
