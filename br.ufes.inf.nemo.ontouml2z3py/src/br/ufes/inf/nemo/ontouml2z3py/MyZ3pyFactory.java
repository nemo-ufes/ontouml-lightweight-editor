package br.ufes.inf.nemo.ontouml2z3py;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Equality;
import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.LogicalBinaryExpression;
import br.ufes.inf.nemo.z3py.LogicalNegation;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.impl.Z3pyFactoryImpl;

public class MyZ3pyFactory extends Z3pyFactoryImpl {

	enum LogicalBinaryExpressionTypes {IMPLICATION, DISJUNCTION, CONJUNCTION, BIIMPLICATION, EXCLUSIVEDISJUNCTION};
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
	
	public Quantification createQuantification(boolean isUniversal, Expression exp, String comments){
		Quantification newFormula;
		if (isUniversal) 
			newFormula = this.createUniversalQuantification();
		else
			newFormula = this.createExistentialQuantification();
		//Obtenho as constantes utilizadas na expressão para atribuir ao quantifiesOver
		newFormula.getQuantifiesOver().addAll(getExpressionConstants(exp));
		newFormula.setExpression(exp);
		newFormula.setComments(comments);
		return newFormula;
	}
	
	//Retorna as constantes utilizadas em uma expressão
	private Set<IntConstant> getExpressionConstants (Expression exp){
		Set<IntConstant> c = new HashSet<IntConstant>();
		if (exp instanceof FunctionCall)
			c.addAll(((FunctionCall) exp).getArguments());
		else if (exp instanceof LogicalBinaryExpression){
			c.addAll(getExpressionConstants(((LogicalBinaryExpression) exp).getOperand1()));
			c.addAll(getExpressionConstants(((LogicalBinaryExpression) exp).getOperand2()));
		}else if (exp instanceof LogicalNegation)
			c.addAll(getExpressionConstants(((LogicalNegation) exp).getOperand()));
		else if (exp instanceof Quantification)
			c.addAll(((Quantification) exp).getQuantifiesOver());	
		return c;
	}
	
	public FunctionCall createFunctionCall(BooleanFunctionDefinition called, List<IntConstant> arguments){
		FunctionCall newFunction = null;
		if (called.getNumberOfArguments()==arguments.size()){
			newFunction = this.createFunctionCall();
			newFunction.setCalledFunction(called);
			newFunction.getArguments().addAll(arguments);
		}
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
		case BIIMPLICATION:
			newExpression = this.createBiImplication();
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
	
	public Equality createEquality(List<IntConstant> args){
		Equality newEquality = this.createEquality();
		newEquality.setOperand1(args.get(0));
		newEquality.setOperand1(args.get(1));
		return newEquality;
	}
	

}
