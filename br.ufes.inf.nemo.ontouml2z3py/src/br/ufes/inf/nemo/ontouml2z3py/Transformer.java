package br.ufes.inf.nemo.ontouml2z3py;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2z3py.MyZ3pyFactory.LogicalBinaryExpressionTypes;
import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Conjunction;
import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.LogicalBinaryExpression;
import br.ufes.inf.nemo.z3py.OntoUMLZ3System;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.Z3pyFactory;

public class Transformer {
	
	private OntoUMLParser ontoparser;
	private MyZ3pyFactory factory = new MyZ3pyFactory();
	private String sourceModelPath;
	private OntoUMLZ3System generatedModel;
	
	
	public Transformer(){
		
	}
	
	private void populateWithWorldStructure(){
		//Crio 3 constantes que utilizarei nas fórmulas
		IntConstant x, y, z;
		x = addConstant();
		y = addConstant();
		z = addConstant();
		
		//Crio as funções que utilizarei
		BooleanFunctionDefinition world, current, past, counterfactual, future, next, recursiveNext;
		world = addFunction("World",1);
		current = addFunction("CurrentWorld",1);
		past = addFunction("PastWorld",1);
		counterfactual = addFunction("CounterfactualWorld", 1);
		future = addFunction("FutureWorld", 1);
		next = addFunction("next", 2);
		recursiveNext = addFunction("recursiveNext", 2);
		
		//Crio as fórmulas
		//1.	∀x,y (next(x,y) → World(x) ∧ World(y))
		List<IntConstant> args1 = new ArrayList<IntConstant>();
		args1.add(x);
		args1.add(y);
		FunctionCall fc1 = factory.createFunctionCall(next, args1);
		List<IntConstant> args2 = new ArrayList<IntConstant>();
		args2.add(x);
		FunctionCall fc2 = factory.createFunctionCall(world, args2);
		List<IntConstant> args3 = new ArrayList<IntConstant>();
		args3.add(y);
		FunctionCall fc3 = factory.createFunctionCall(world, args3);
		LogicalBinaryExpression lbe1 = factory.createBinaryExpression(fc2, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		LogicalBinaryExpression lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, args1, lbe2, "");
	}
	
	private BooleanFunctionDefinition addFunction(String name, int numberOfArguments){
		BooleanFunctionDefinition newFunction = factory.createFunction(name, numberOfArguments);
		generatedModel.getFunctions().add(newFunction);
		return newFunction;
	}
	
	private IntConstant addConstant(){
		IntConstant newConst = factory.createConstant();
		generatedModel.getConstants().add(newConst);
		return newConst;		
	}
	
	private Quantification addFormula(boolean isUniversal, List<IntConstant> consts, Expression exp, String comments){
		Quantification newFormula = factory.createFormula(isUniversal, consts, exp, comments);
		generatedModel.getFormulas().add(newFormula);
		return newFormula;
	}

	
	public OntoUMLZ3System run(){
		generatedModel = factory.createOntoUMLZ3System();
		populateWithWorldStructure();
		return generatedModel;
		
	}

}
