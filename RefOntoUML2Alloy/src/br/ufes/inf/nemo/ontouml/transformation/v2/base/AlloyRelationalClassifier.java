package br.ufes.inf.nemo.ontouml.transformation.v2.base;

import br.ufes.inf.nemo.ontouml.transformation.v2.classifiers.AlloyClassifier;

public abstract class AlloyRelationalClassifier extends AlloyClassifier
{
	public String getStateName()
	{
		return this.getName();
	}
}
