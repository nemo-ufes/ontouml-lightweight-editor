package br.ufes.inf.nemo.ontouml.antipattern.util;

import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Type;

public class OntoUMLCopier {
	
	public static Association copyAssociation (Association a, Type new_source, Type new_target) {
		
		Association clonedAssociation = EcoreUtil.copy(a);
		clonedAssociation.getMemberEnd().get(0).setType(new_source);
		clonedAssociation.getMemberEnd().get(1).setType(new_target);
		
		return clonedAssociation;
	}
	
	public static Classifier copyClassifier (Classifier c) {
		
		Classifier clonedClassifier = EcoreUtil.copy(c);
		c.getGeneralization().clear();
		
		return clonedClassifier;
	}
	
}
