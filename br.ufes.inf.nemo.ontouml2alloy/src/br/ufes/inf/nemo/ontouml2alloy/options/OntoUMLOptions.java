package br.ufes.inf.nemo.ontouml2alloy.options;

import java.util.ArrayList;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class OntoUMLOptions {
	
	public boolean weakSupplementationConstraint = true;	
	public boolean relatorConstraint = true;	
	public boolean identityPrinciple = true;	
	public boolean antiRigidity = false;	
	public boolean openAnalyzer = true;
	
	public OntoUMLOptions()	
	{
	
	}	
	
	/**
	 * This method checks if the Identity Principle option was checked and
	 * return model elements that does not have any identity (inherited by its ancestors or provided by its descendants).
	 * 
	 * @param refparser
	 * @param opt
	 * @return
	 */
	public ArrayList<RefOntoUML.Classifier> getIdentityValidityElements(OntoUMLParser refparser)
	{
		ArrayList<RefOntoUML.Classifier> elemsWithoutIndentity = new ArrayList<RefOntoUML.Classifier>();		
		elemsWithoutIndentity = refparser.getElementsWithoutIdentity();
		if (identityPrinciple && !elemsWithoutIndentity.isEmpty())
		{
			return elemsWithoutIndentity;
		}else{
			return new ArrayList<RefOntoUML.Classifier>();
		}
	}	
		
	public String getIdentityValidityMessage (OntoUMLParser refparser)
	{
		ArrayList<RefOntoUML.Classifier> elemsWithoutIndentity = getIdentityValidityElements(refparser);
		String result = new String();
		if (!elemsWithoutIndentity.isEmpty())
		{
			result += 
				"Checking the Identity Principle you are ensuring that every " + "\n"+
				"element of your model inherited/has an identity. If not, please uncheck it.";
				
		}		
		return result;
	}
	
	public String getAntirigityValidityMessage ()
	{
		String result = new String();
		if (antiRigidity)
		{
			result += 
				"Checking the Anti-rigidity you are ensuring that the simulation" + "\n" +
				"will always occur withat least two (2) Worlds.";
		}
		return result;
	}
}
