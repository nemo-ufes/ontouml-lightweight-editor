package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClassifier;

public abstract class AlloyRelationalClassifier extends AlloyClassifier
{
	public String getStateName()
	{
		return this.getName();
	}
}
