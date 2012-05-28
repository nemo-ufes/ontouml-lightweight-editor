package refontouml2alloy.bts.classifiers.relationalclassifiers;

import refontouml2alloy.bts.classifiers.AlloyClassifier;

public abstract class AlloyRelationalClassifier implements AlloyClassifier
{
	public String getStateName()
	{
		return this.getName();
	}
}