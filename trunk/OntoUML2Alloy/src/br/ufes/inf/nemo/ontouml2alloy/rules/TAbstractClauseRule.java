package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.api.OntoUMLAPI;

public class TAbstractClauseRule {

	/**
	 * Create Abstract Clause Rule Compare Operation in Alloy.
	 * 
	 * BinaryOperation with union operator(+) to represent the completeness
	 * between abstract father(Classifiers) and his concrete childs.
	 * 
	 * "abstract_father = concrete_child1 + concrete_child2 + concrete_child3 + ..." 
	 */
	public static CompareOperation createCompareOperation(OntoUMLParser ontoparser, AlloyFactory factory, Classifier c) 
	{		
		ArrayList<Classifier> concretes = new ArrayList<Classifier>();		
		OntoUMLAPI.getConcreteDescendants(ontoparser, concretes, c);
						
		if(concretes.size() > 0)
		{
			BinaryOperation bo = factory.createBinaryOperation();
			
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(ontoparser.getName(c));
			
			co.setLeftExpression(vr);
			
			int cont = 1;
			for(Classifier classifier : concretes)
			{
				if(concretes.size() == 1) 
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));					
					co.setRightExpression(vr);
					break;
				}
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != concretes.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == concretes.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));
					bo.setRightExpression(vr);
				}
				cont++;
			}
			return co;			
		}
		return null;
	}	
}
