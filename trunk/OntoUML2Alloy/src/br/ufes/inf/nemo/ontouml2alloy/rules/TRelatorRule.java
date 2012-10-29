package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import RefOntoUML.Relator;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.common.ontouml.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ontouml2alloy.api.OntoUMLAPI;

public class TRelatorRule {

	/**
	 *
	 * Create Relator Constraint Fact Declaration in Alloy.
	 * 
	 * all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2
	 */
	@SuppressWarnings("unchecked")
	public static FactDeclaration createFactDeclaration(OntoUMLParser ontoparser, AlloyFactory factory, Relator c) 
	{
		if (c.isIsAbstract()) return null;
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if ( OntoUMLAPI.isAbstractFromGeneralizationSets(ontoparser,c)) return null;
		
		if (! OntoUMLAPI.hasMediationRelation(ontoparser,c)) return null;
		
		// get all 'c' mediations
		ArrayList<String> associationNames = new ArrayList<String>();		
		OntoUMLAPI.getAllMediationsNames(ontoparser,associationNames, c);		
		
		if(associationNames.size()>0)
		{			
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyAPI.createQuantificationExpression(factory,associationNames, ontoparser.getName(c));
			
			// create fact from q
			FactDeclaration RelatorRuleFact = factory.createFactDeclaration();
			RelatorRuleFact.setName("relator_constraint_"+ontoparser.getName(c));
			RelatorRuleFact.setBlock(factory.createBlock());			
			RelatorRuleFact.getBlock().getExpression().add(q);
			
			return RelatorRuleFact;						
		}		
		
		return null;
	}
}
