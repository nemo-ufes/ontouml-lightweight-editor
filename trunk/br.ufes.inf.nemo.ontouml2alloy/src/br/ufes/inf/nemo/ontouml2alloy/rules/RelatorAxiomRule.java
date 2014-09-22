package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyAPI;

public class RelatorAxiomRule {

	/**
	 *
	 * Create Relator Constraint Fact Declaration in Alloy.
	 * 
	 * all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2
	 * @throws Exception 
	 */
	public static FactDeclaration createFactDeclaration(OntoUMLParser ontoparser, AlloyFactory factory, Relator c) 
	{
		if (c.isIsAbstract()) return null;
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if ( ontoparser.isAbstractFromGSet(c)) return null;
		
		//if (! OntoUMLAPI.hasMediationRelation(ontoparser,c)) return null;
		
		// get all 'c' mediations				
		ArrayList<Mediation> mediations = new ArrayList<Mediation>();
		try {
			ontoparser.getAllMediations(c, mediations);
		} catch (Exception e) {			
			e.printStackTrace();
		}
				
		// get all c mediations names
		ArrayList<EObject> eobjects = new ArrayList<EObject>();
		eobjects.addAll(mediations);		
		ArrayList<String> associationNames = new ArrayList<String>();
		associationNames = ontoparser.getAlias(eobjects);		
		
		if(associationNames.size()==0) return null; 
			
		if(associationNames.size()>0)
		{			
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyAPI.createQuantificationExpression(factory,associationNames, ontoparser.getAlias(c));
			
			// create fact from q
			FactDeclaration RelatorRuleFact = factory.createFactDeclaration();
			RelatorRuleFact.setName("relatorConstraint");
			RelatorRuleFact.setBlock(factory.createBlock());			
			RelatorRuleFact.getBlock().getExpression().add(q);
			
			return RelatorRuleFact;						
		}		
		
		return null;
	}
}
