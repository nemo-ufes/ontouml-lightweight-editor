package refontouml2alloy.bts.classifiers.relationalclassifiers;

import refontouml2alloy.bts.invalidModelException;
import RefOntoUML.GeneralizationSet;

public class invalidGeneralizationSetException extends invalidModelException
{
	private static final long serialVersionUID = 1L;
	public invalidGeneralizationSetException(GeneralizationSet gset) 
	{
		super(validGenName(gset)+" has more than one general class");
	}
	public static String validGenName(GeneralizationSet gset)
	{
		if(gset.getName()!=null)return gset.getName();
		else return "an unnamed generalization set";
	}
}
