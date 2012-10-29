package br.ufes.inf.nemo.ontouml2alloy.rules;

import RefOntoUML.Generalization;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.api.AlloyAPI;

public class TGeneralizationRule {

	/**
	 *	Create Compare Operation for Generalization Rule in Alloy. 
	 *
	 *  The child Classifier is a subset of the father Classifier. 
	 * 
	 * "child in father"
	 */	
	public static CompareOperation createCompareOperation (OntoUMLParser ontoparser, AlloyFactory factory,Generalization g)
	{
		return AlloyAPI.createCompareOperation(factory, ontoparser.getName(g.getSpecific()), CompareOperator.SUBSET_LITERAL, ontoparser.getName(g.getGeneral()));
	}
}
