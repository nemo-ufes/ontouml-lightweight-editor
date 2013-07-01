package br.ufes.inf.nemo.ontouml2alloy;

import java.util.ArrayList;

import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class Onto2AlloyOptions {
	
	public boolean identityPrinciple = true;	
	public boolean antiRigidity = false;
	public boolean weakSupplementationAxiom = true;	
	public boolean relatorAxiom = true;
	public boolean openAnalyzer = true;
		
	/**
	 * Message in the case of elements without any identity in the model.
	 */
	public String getIdentityOptionMessage (OntoUMLParser refparser)
	{
		ArrayList<RefOntoUML.Classifier> elemsWithoutIndentity = getElementsWithoutIdentity(refparser);
		String result = new String();
		if (!elemsWithoutIndentity.isEmpty())
		{
			if (identityPrinciple)
			{
				result += 
				"There are classes in your model that does not inherit an identity principle from substance sortals." + "\n"+
				"Please, fix this issue before continue. Otherwise, uncheck the Identity option if you are aware of the consequences. ";
			}
		}
		return result;
	}
	
	/**
	 * Message in the case of AntiRigidity option be enforced
	 */
	public String getAntirigityOptionMessage ()
	{
		String result = new String();
		if (antiRigidity)
		{
			result += 
				"Checking the Anti-rigidity option you are ensuring that by running the simulation" + "\n" +
				"you will always simulate the model with at least two (2) Worlds.";
		}
		return result;
	}
	
	
	/**
	 * Message in the case of Relator axiom be enforced
	 */
	public String getRelatorOptionMessage (OntoUMLParser refparser)
	{
		ArrayList<Relator> relators = getRelatorsInvalidAxiom(refparser);
		String result = new String();
		if (!relators.isEmpty())
		{			
			if (relatorAxiom)
			{
				result += 
					"There are relators taht are not in accordance with the Relator Axiom." + "\n" +
					"that is, the sum of upper cardinalities of its mediations are less or equal to 2."+ "\n"+
					"Uncheck the Relator Axiom box to disable its enforcement.";
			}
		}
		return result;
	}
	
   /** 
    * This method checks if the Identity Principle option was checked and
	* return model elements that does not have any identity (inherited by 
	* its ancestors or provided by its descendants).
	*/
	private ArrayList<RefOntoUML.Classifier> getElementsWithoutIdentity(OntoUMLParser refparser)
	{
		ArrayList<RefOntoUML.Classifier> elemsWithoutIndentity = new ArrayList<RefOntoUML.Classifier>();		
		elemsWithoutIndentity = refparser.getElementsWithIdentityMissing();		
		if (identityPrinciple && !elemsWithoutIndentity.isEmpty())
		{
			return elemsWithoutIndentity;
		}else{
			return new ArrayList<RefOntoUML.Classifier>();
		}
	}	
	
	/**
	 * Check if there is at least one relator that has the sum of its mediations upper cardinality
	 * less or equal to 2. 
	 */
	private ArrayList<Relator> getRelatorsInvalidAxiom (OntoUMLParser refparser)
	{
		ArrayList<Relator> relators = new ArrayList<Relator>();
		for (Relator r: refparser.getAllInstances(Relator.class))
		{
			ArrayList<Mediation> mediations = new ArrayList<Mediation>();
			try {  refparser.getAllMediations(r, mediations); } catch (Exception e) {e.printStackTrace();}					
			
			int sum=0;
			for(Mediation m: mediations)
			{
				sum += m.getMemberEnd().get(1).getUpper();
			}
			
			if (sum <=2) relators.add(r);
		}
		return relators;
	}

}
