package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
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
		Property Source = null;
		Property Target = null;
		
		// invert sides if mediation's target is a relator
		if (assoc instanceof Mediation)
		{ 
			if((assoc.getMemberEnd().get(1).getType() instanceof Relator) && !(assoc.getMemberEnd().get(0).getType() instanceof Relator))
			{
				// invert sides
				Source = assoc.getMemberEnd().get(1);
				Target = assoc.getMemberEnd().get(0);						
			}else{
				Source = assoc.getMemberEnd().get(0);
				Target = assoc.getMemberEnd().get(1);
			}
			
		// invert sides if characterization's target is a mode
		}else if (assoc instanceof Characterization)
		{
			if ((assoc.getMemberEnd().get(1).getType() instanceof Mode) && !(assoc.getMemberEnd().get(0).getType() instanceof Mode))
			{
				// invert sides
				Source = assoc.getMemberEnd().get(1);
				Target = assoc.getMemberEnd().get(0);
			}else{
				Source = assoc.getMemberEnd().get(0);
				Target = assoc.getMemberEnd().get(1);
			}			

		// not invert...
		}else{
			Source = assoc.getMemberEnd().get(0);
			Target = assoc.getMemberEnd().get(1);
		}
		
		int lowerSource = Source.getLower();
		int upperSource = Source.getUpper();
		int lowerTarget = Target.getLower();
		int upperTarget = Target.getUpper();
		BinaryOperation univBinJoin = factory.createBinaryOperation();
		
		if (lowerSource > 1)
		{
			// Material association are ternary relations between mediaionts and the relator
			if (assoc instanceof MaterialAssociation){			
				BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory, ontoparser.getAlias(assoc), BinaryOperator.JOIN, "x");
				univBinJoin = AlloyAPI.createBinaryOperation(factory, "("+binJoin.toString()+")", BinaryOperator.JOIN, "univ");
			}else{
				univBinJoin = AlloyAPI.createBinaryOperation(factory, ontoparser.getAlias(assoc), BinaryOperator.JOIN, "x");
			}
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Target.getType()), univBinJoin, CompareOperator.GREATER_EQUAL, lowerSource);
			if (qe!=null) qeList.add(qe);
		}
		
		if (upperSource > 1 && upperSource != -1) 
		{
			// Material association are ternary relations between mediaionts and the relator
			if (assoc instanceof MaterialAssociation){
				BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory,ontoparser.getAlias(assoc), BinaryOperator.JOIN,"x");	
				univBinJoin = AlloyAPI.createBinaryOperation(factory, "("+binJoin.toString()+")", BinaryOperator.JOIN, "univ");
			}else{
				univBinJoin =AlloyAPI.createBinaryOperation(factory,ontoparser.getAlias(assoc), BinaryOperator.JOIN,"x");	
			}
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Target.getType()), univBinJoin, CompareOperator.LESS_EQUAL, upperSource);				
			if (qe!=null) qeList.add(qe);
		}
		
		if (lowerTarget > 1) 
		{
			// Material association are ternary relations between mediaionts and the relator
			if (assoc instanceof MaterialAssociation){
				BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, ontoparser.getAlias(assoc));	
				univBinJoin = AlloyAPI.createBinaryOperation(factory, "univ", BinaryOperator.JOIN, "("+binJoin.toString()+")");
			}else{
				univBinJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, ontoparser.getAlias(assoc));
			}
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Source.getType()), univBinJoin, CompareOperator.GREATER_EQUAL, lowerTarget);				
			if (qe!=null) qeList.add(qe);
		}
		
		if (upperTarget > 1 && upperTarget != -1) 
		{
			// Material association are ternary relations between mediaionts and the relator		
			if (assoc instanceof MaterialAssociation){
				BinaryOperation binJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, ontoparser.getAlias(assoc));
				univBinJoin = AlloyAPI.createBinaryOperation(factory, "univ", BinaryOperator.JOIN, "("+binJoin.toString()+")");
			}else{
				univBinJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, ontoparser.getAlias(assoc));
			}			
			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, ontoparser.getAlias(Source.getType()), univBinJoin, CompareOperator.LESS_EQUAL, upperTarget);				
			if (qe!=null) qeList.add(qe);		
		}
		return qeList;
	}
}
