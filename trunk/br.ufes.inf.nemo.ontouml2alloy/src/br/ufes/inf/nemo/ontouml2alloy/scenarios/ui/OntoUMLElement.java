package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;

/**
 * @author John Guerson
 */

public class OntoUMLElement {
	
	protected Classifier classifier;
	
	public OntoUMLElement (Classifier classifier) 
	{
		this.classifier = classifier;
	}
	
	/**
	 * To String.
	 */
	@Override
	public String toString() 
	{
		String s = OntoUMLNameHelper.getNameAndType(classifier, true, false);
		
		if(classifier instanceof Association){
			Association a = (Association) classifier;
			s+="("+OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(0))+" -> "+OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(0))+")";
		}
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		return classifier.equals(obj);
	}

	public Classifier getClassifier() {
		return classifier;
	}
}
