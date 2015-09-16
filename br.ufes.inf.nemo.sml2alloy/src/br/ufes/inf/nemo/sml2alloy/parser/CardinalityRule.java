package br.ufes.inf.nemo.sml2alloy.parser;

import java.util.ArrayList;

import sml2.Participant;
import sml2.SituationType;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.api.AlloyAPI;
import br.ufes.inf.nemo.sml2alloy.exception.UnsupportedElementException;

public class CardinalityRule {
		
	/**
	 * Create Cardinality Constraints for a Participation.
	 * 
	 * For example: all x: Travel | # x.(has) >= 2
	 * @throws UnsupportedElementException 
	 */
	public static ArrayList<QuantificationExpression> createQuantificationExpressions (AlloyFactory factory, SMLParser smlparser, SituationType sit, Participant part) throws UnsupportedElementException 
	{	
		ArrayList<QuantificationExpression> qeList = new ArrayList<QuantificationExpression>();
		
		//int lowerSource = 0;
		//int upperSource = part.isShareable() ? -1 : 1;
		int lowerTarget = part.getMin();
		int upperTarget = part.getMax();
		BinaryOperation univBinJoin = factory.createBinaryOperation();
		
		if (lowerTarget > 1) 
		{
			univBinJoin = AlloyAPI.createBinaryOperation(factory, "x", BinaryOperator.JOIN, smlparser.getParticipationAlias(sit,part));

			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, smlparser.getAlias(sit), univBinJoin, CompareOperator.GREATER_EQUAL, lowerTarget);				
			if (qe!=null) qeList.add(qe);
		}
		
		if (upperTarget > 1 && upperTarget != -1) 
		{
			univBinJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, smlparser.getParticipationAlias(sit,part));

			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, smlparser.getAlias(sit), univBinJoin, CompareOperator.LESS_EQUAL, upperTarget);				
			if (qe!=null) qeList.add(qe);		
		}
		
		return qeList;
	}
	
	public static ArrayList<QuantificationExpression> createTransTemporalQuantificationExpressions (AlloyFactory factory, SMLParser smlparser, SituationType sit, Participant part) throws UnsupportedElementException 
	{	
		ArrayList<QuantificationExpression> qeList = new ArrayList<QuantificationExpression>();
		
		//int lowerSource = 0;
		int upperSource = -1;
		int lowerTarget = part.getMin();
		int upperTarget = part.getMax();
		BinaryOperation univBinJoin = factory.createBinaryOperation();
		
		if (upperSource >= 1 && upperSource != -1)
		{
			univBinJoin = AlloyAPI.createBinaryOperation(factory, smlparser.getParticipationAlias(sit,part), BinaryOperator.JOIN, "x");

			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, "World."+smlparser.getAlias(smlparser.getElementType(part)), univBinJoin, CompareOperator.LESS_EQUAL, upperSource);				
			if (qe!=null) qeList.add(qe);		
		}
		
		if (lowerTarget >= 1) 
		{
			univBinJoin = AlloyAPI.createBinaryOperation(factory, "x", BinaryOperator.JOIN, smlparser.getParticipationAlias(sit,part));

			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, "World."+smlparser.getAlias(sit), univBinJoin, CompareOperator.GREATER_EQUAL, lowerTarget);				
			if (qe!=null) qeList.add(qe);
		}
		
		if (upperTarget >= 1 && upperTarget != -1) 
		{
			univBinJoin = AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN, smlparser.getParticipationAlias(sit,part));

			QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory, "World."+smlparser.getAlias(sit), univBinJoin, CompareOperator.LESS_EQUAL, upperTarget);				
			if (qe!=null) qeList.add(qe);		
		}
		
		return qeList;
	}
}