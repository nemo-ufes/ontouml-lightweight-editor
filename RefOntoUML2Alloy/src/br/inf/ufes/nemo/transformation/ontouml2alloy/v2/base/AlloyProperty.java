package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base;

import br.inf.ufes.nemo.transformation.ontouml2alloy.v2.classifiers.AlloyClassifier;
import RefOntoUML.Property;

public class AlloyProperty
{
	private final int lower;
	private final int upper;
	private final AlloyClassifier endType;
	private final boolean readOnly;
	public AlloyProperty (Property p, AlloyClassifier alloyClassifier )
	{
		lower = p.getLower();
		upper = p.getUpper();
		endType = alloyClassifier;
		readOnly = p.isIsReadOnly();
	}
	public String setMultiplicity()
	{
		if(lower == 0 )
		{
			//set || lone
			if(upper==1) return " lone ";
			else return " set ";
		}
		else
		{
			//some || one
			if(upper == 1) return " one ";
			else return " some ";
		}
	}
	//public String cardinality()
	public AlloyClassifier getEndType() 
	{
		return endType;
	}
	public int getLower() 
	{
		return lower;
	}
	public int getUpper() 
	{
		return upper;
	}
	public boolean isReadOnly()
	{
		return readOnly;
	}
}