package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.RigidSortalClass;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.common.ontouml.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ontouml2alloy.api.OntoUMLAPI;

public class TWeakSupplementationRule {

	/**
	 * Create Weak Supplementation Fact Declaration in Alloy
  	 *
  	 * all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2
	 */
	@SuppressWarnings("unchecked")
	public static FactDeclaration createFactDeclaration(OntoUMLParser ontoparser, AlloyFactory factory, Classifier c) 
	{
		if (c.isIsAbstract()) { return null; }
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if (OntoUMLAPI.isAbstractFromGeneralizationSets(ontoparser,c)) { return null; }	
		
		if (! OntoUMLAPI.hasMeronymicRelation(ontoparser,c)) { return null; } 
				
		// get all 'c' meronymics
		ArrayList<String> associationNames = new ArrayList<String>();		
		OntoUMLAPI.getAllMeronymics(ontoparser,associationNames, (RigidSortalClass)c);	
		
		if( associationNames.size() > 0)
		{
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyAPI.createQuantificationExpression(factory,associationNames, ontoparser.getName(c));
			
			// create fact from q
			FactDeclaration WeakSupplementationFact = factory.createFactDeclaration();
			WeakSupplementationFact.setName("weak_supplementation_"+ontoparser.getName(c));
			WeakSupplementationFact.setBlock(factory.createBlock());			
			WeakSupplementationFact.getBlock().getExpression().add(q);			
			
			return WeakSupplementationFact;					
		}	
		return null;
	}
}
