package br.ufes.inf.nemo.ontouml2z3py;

import java.util.List;

import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.LogicalBinaryExpression;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.impl.Z3pyFactoryImpl;

public class MyZ3pyFactory extends Z3pyFactoryImpl {

	enum LogicalBinaryExpressionTypes {IMPLICATION, DISJUNCTION, CONJUNCTION, EQUIVALENCE, EXCLUSIVEDISJUNCTION};
	private int constId=1;
	
	public MyZ3pyFactory() {
		 
	}

	public BooleanFunctionDefinition createFunction(String name, int numberOfArguments){
		BooleanFunctionDefinition newFunction = this.createBooleanFunctionDefinition();
		newFunction.setName(name);
		newFunction.setNumberOfArguments(numberOfArguments);
		return newFunction;
	}
	
	public IntConstant createConstant(){
		IntConstant newConst = this.createIntConstant();
		newConst.setName("C!" + constId);
		constId++;		
		return newConst;		
	}
	
	public Quantification createFormula(boolean isUniversal, List<IntConstant> consts, Expression exp, String comments){
		Quantification newFormula;
		if (isUniversal) 
			newFormula = this.createUniversalQuantification();
		else
			newFormula = this.createExistentialQuantification();
		newFormula.getConstants().addAll(consts);
		newFormula.setExpression(exp);
		newFormula.setComments(comments);
		return newFormula;
	}
	
	public FunctionCall createFunctionCall(BooleanFunctionDefinition called, List<IntConstant> arguments){
		FunctionCall newFunction = this.createFunctionCall();
		newFunction.setCalledFunction(called);
		newFunction.getArguments().addAll(arguments);
		return newFunction;
	}
	
	public LogicalBinaryExpression createBinaryExpression(Expression op1, Expression op2, LogicalBinaryExpressionTypes type){
		LogicalBinaryExpression newExpression=null;
		switch (type){
		case CONJUNCTION:
			newExpression = this.createConjunction();
			break;
		case DISJUNCTION:
			newExpression = this.createDisjunction();
			break;
		case EQUIVALENCE:
			newExpression = this.createEquivalence();
			break;
		case EXCLUSIVEDISJUNCTION:
			newExpression = this.createExclusiveDisjunction();
			break;
		case IMPLICATION:
			newExpression = this.createImplication();
			break;
		}
		newExpression.setOperand1(op1);
		newExpression.setOperand2(op2);
		return newExpression;
	}
	

}
