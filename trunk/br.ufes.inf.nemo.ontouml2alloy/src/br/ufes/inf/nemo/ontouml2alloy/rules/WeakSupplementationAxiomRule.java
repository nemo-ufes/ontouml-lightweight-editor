package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyAPI;

public class WeakSupplementationAxiomRule {

	/**
	 * Create Weak Supplementation Fact Declaration in Alloy
  	 *
  	 * all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2
	 */
	public static FactDeclaration createFactDeclaration(OntoUMLParser ontoparser, AlloyFactory factory, Classifier c) 
	{
		if (c.isIsAbstract()) { return null; }
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if (ontoparser.isAbstractFromGSet(c)) { return null; }	
		
		//if (! OntoUMLAPI.hasMeronymicRelation(ontoparser,c)) { return null; } 
				
		// get all 'c' meronymics				
		ArrayList<Meronymic> meronymics = new ArrayList<Meronymic>();
		ontoparser.getAllMeronymics(c, meronymics);
		
		// get all c meronymics names
		ArrayList<EObject> eobjects = new ArrayList<EObject>();
		eobjects.addAll(meronymics);		
		ArrayList<String> associationNames = new ArrayList<String>();
		associationNames = ontoparser.getAlias(eobjects);
		
		if(associationNames.size()==0) return null;
		
		if( associationNames.size() > 0)
		{
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyAPI.createQuantificationExpression(factory,associationNames, ontoparser.getAlias(c));
			
			// create fact from q
			FactDeclaration WeakSupplementationFact = factory.createFactDeclaration();
			WeakSupplementationFact.setName("weakSupplementationConstraint");
			WeakSupplementationFact.setBlock(factory.createBlock());			
			WeakSupplementationFact.getBlock().getExpression().add(q);			
			
			return WeakSupplementationFact;					
		}	
		return null;
	}
}
