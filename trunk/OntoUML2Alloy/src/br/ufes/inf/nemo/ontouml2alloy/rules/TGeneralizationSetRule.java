package br.ufes.inf.nemo.ontouml2alloy.rules;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class TGeneralizationSetRule {


	/**
	 * Create Complete Compare Operation in Alloy for Generalization Sets that are complete.
	 * 
	 *  disj[Child1, Child2, Child3,...]
	 */
	@SuppressWarnings("unchecked")
	public static DisjointExpression createDisjointExpression (OntoUMLParser ontoparser, AlloyFactory factory, GeneralizationSet gs)
	{
		DisjointExpression disj = factory.createDisjointExpression();
		for(Generalization gen : gs.getGeneralization())
		{
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(ontoparser.getName(gen.getSpecific()));
			disj.getSet().add(vr);
		}
		return disj;
	}
	
	/**
	 * Create Complete Compare Operation in Alloy for Generalization Sets that are complete.
	 * 
	 * Father = Child1 + Child2 + Child3 + ...
	 */
	public static CompareOperation createCompleteCompareOperation (OntoUMLParser ontoparser, AlloyFactory factory, GeneralizationSet gs)
	{
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.EQUAL_LITERAL);
		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(ontoparser.getName(gs.getGeneralization().get(0).getGeneral()));
		
		co.setLeftExpression(vr);
		
		int cont = 1;
		BinaryOperation bo = factory.createBinaryOperation();
		for(Generalization gen : gs.getGeneralization())
		{
			if(gs.getGeneralization().size() == 1)
			{
				VariableReference vr1 = factory.createVariableReference();
				vr1.setVariable(ontoparser.getName(gen.getSpecific()));					
				co.setRightExpression(vr);				
				break;
			}
			if(cont == 1)
			{
				bo.setOperator(BinaryOperator.UNION_LITERAL);
				vr = factory.createVariableReference();
				vr.setVariable(ontoparser.getName(gen.getSpecific()));
				bo.setLeftExpression(vr);
				co.setRightExpression(bo);
			}
			if(cont > 1 && cont != gs.getGeneralization().size())
			{
				vr = factory.createVariableReference();
				vr.setVariable(ontoparser.getName(gen.getSpecific()));
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == gs.getGeneralization().size())
			{
				vr = factory.createVariableReference();
				vr.setVariable(ontoparser.getName(gen.getSpecific()));
				bo.setRightExpression(vr);
			}
			cont++;
		}
		return co;
	}
}
