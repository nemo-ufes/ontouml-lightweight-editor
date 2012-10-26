package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import RefOntoUML.Class;
import RefOntoUML.Derivation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.ontouml2alloy.api.OntoUMLAPI;
import br.ufes.inf.nemo.ontouml2alloy.parser.OntoUMLParser;

public class DerivationRule {

	/**
	 *	Create Derivation Predicate Invocation
	 *
	 *  derivation[MaterialName,MediationName1,MediationName2];
	 *  
	 */
	@SuppressWarnings("unchecked")
	public static PredicateInvocation createPredicateInvocation (OntoUMLParser ontoparser, AlloyFactory factory, Derivation d)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("derivation");
	
		// 	Get Material Association
		VariableReference vrMaterial = factory.createVariableReference();
		MaterialAssociation material = null;		
		for(Property prop : d.getMemberEnd())
		{
			if(prop.getType() instanceof MaterialAssociation)
			{
				vrMaterial.setVariable(ontoparser.getName(prop.getType()));
				material = (MaterialAssociation)prop.getType();
			}
		}		
			
		// Get Source and Target of Material Association
		Type sourceType= null;
		Type targetType = null;
		int cont = 1;		
		for(Property prop : material.getMemberEnd())
		{
			if(prop.getType() instanceof Class)
			{
				if(cont==1) {
					sourceType = prop.getType();
					cont++;
				} else {
					targetType = prop.getType();
				}
			}
		}
					
		// 	Get Relator
		Relator derRelator = (Relator) (d.relator() instanceof Relator ? d.relator() : d.material());
	
		VariableReference mediation1 = factory.createVariableReference();
		VariableReference mediation2 = factory.createVariableReference();
	
		// Get Mediations
		ArrayList<Mediation> mediations = new ArrayList<Mediation>();
		OntoUMLAPI.getAllMediations(ontoparser, mediations, derRelator);
			
		// Get the two first mediations related with source and target of the material relation
		int cont1=1;
		for (Mediation med: mediations)
		{
			int cont2=1;			
			Type mediated = null;
			for(Property prop : med.getMemberEnd())
			{	
				if(prop.getType() instanceof Relator && cont2==1)
				{
					cont2++;
				}else{
					mediated = prop.getType();
				}
			}
			
			if(mediated == sourceType || mediated == targetType)
			{
				if(cont1 == 1)
				{						
					mediation1.setVariable(ontoparser.getName(med));
					cont1++;						
				}
				else
				{					
					mediation2.setVariable(ontoparser.getName(med));						
					break;
				}
			}			
		}	
	
		pI.getParameter().add(vrMaterial);
		pI.getParameter().add(mediation1);
		pI.getParameter().add(mediation2);
		
		return pI;
	}
}
