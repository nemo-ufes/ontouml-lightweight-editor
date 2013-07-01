package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Property;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyAPI;

public class CardinalityRule {
		
	/**
	 * Create Cardinality Constraints for an OntoUML Association.
	 * 
	 * For example: all x: Travel | # x.(w.has) >= 2
	 */
	public static ArrayList<QuantificationExpression> createQuantificationExpressions (AlloyFactory factory, OntoUMLParser ontoparser, Association assoc) 
	{	
		ArrayList<QuantificationExpression> qeList = new ArrayList<QuantificationExpression>();
		
		Property Source = assoc.getMemberEnd().get(0);
		Property Target = assoc.getMemberEnd().get(1);
		int lowerSource = Source.getLower();
		int upperSource = Source.getUpper();
		int lowerTarget = Target.getLower();
		int upperTarget = Target.getUpper();
		
		if (lowerSource > 1)
		{
			BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory, ontoparser.getAlias(assoc), BinaryOperator.JOIN, "x");			
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Target.getType()), binJoin, CompareOperator.GREATER_EQUAL, lowerSource);
			if (qe!=null) qeList.add(qe);
		}
		
		if (upperSource > 1 && upperSource != -1) 
		{
			BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory,ontoparser.getAlias(assoc), BinaryOperator.JOIN,"x");			
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Target.getType()), binJoin, CompareOperator.LESS_EQUAL, upperSource);				
			if (qe!=null) qeList.add(qe);
		}
		
		if (lowerTarget > 1) 
		{
			BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, ontoparser.getAlias(assoc));			
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Source.getType()), binJoin, CompareOperator.GREATER_EQUAL, lowerTarget);				
			if (qe!=null) qeList.add(qe);
		}
		
		if (upperTarget > 1 && upperTarget != -1) 
		{
			BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, ontoparser.getAlias(assoc));			
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Source.getType()), binJoin, CompareOperator.LESS_EQUAL, upperTarget);				
			if (qe!=null) qeList.add(qe);		
		}
		return qeList;
	}
}
