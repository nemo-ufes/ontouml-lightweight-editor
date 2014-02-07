package br.ufes.inf.nemo.ontouml2z3py;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Equality;
import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.LogicalBinaryExpression;
import br.ufes.inf.nemo.z3py.OntoUMLZ3System;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.impl.Z3pyFactoryImpl;
import br.ufes.inf.nemo.z3py.impl.Z3pyFactoryImpl.LogicalBinaryExpressionTypes;
public class Transformer {
	
	private OntoUMLParser ontoparser;
	private Z3pyFactoryImpl factory = new MyZ3pyFactory();
	private String sourceModelPath;
	private OntoUMLZ3System generatedModel;
	
	
	public Transformer(){
		
	}
	
	private void populateWithWorldStructure(){
		
		//Crio as funções que utilizarei
		BooleanFunctionDefinition world, current, past, counterfactual, future, next, recursiveNext;
		addFunction("World",1);
		addFunction("CurrentWorld",1);
		addFunction("PastWorld",1);
		addFunction("CounterfactualWorld", 1);
		addFunction("FutureWorld", 1);
		addFunction("next", 2);
		addFunction("recursiveNext", 2);


		//Crio as fórmulas
		//1.	∀x,y (next(x,y) → World(x) ∧ World(y))		
		FunctionCall fc1 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1, 2}));
		FunctionCall fc2 = factory.createFunctionCall(getFunction("World"), getConstants(new int[]{1}));
		FunctionCall fc3 = factory.createFunctionCall(getFunction("World"), getConstants(new int[]{2}));
		LogicalBinaryExpression lbe1 = factory.createBinaryExpression(fc2, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		LogicalBinaryExpression lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "");
		
		//2.	∀x,y,z (next(x,y) ∧ next(z,y)) →x=z
		fc1 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1, 2}));
		fc2 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{3, 2}));
		lbe1 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.CONJUNCTION);
		Equality e1 = factory.createEquality(getConstants(new int[]{1, 3}));
		lbe2 = factory.createBinaryExpression(lbe1, e1 , LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "");
		
		
	}
	
	private BooleanFunctionDefinition addFunction(String name, int numberOfArguments){
		BooleanFunctionDefinition newFunction = factory.createFunction(name, numberOfArguments);
		generatedModel.getFunctions().add(newFunction);
		return newFunction;
	}

	private Quantification addFormula(boolean isUniversal, Expression exp, String comments){
		Quantification newFormula = factory.createQuantification(isUniversal, exp, comments);
		generatedModel.getFormulas().add(newFormula);
		return newFormula;
	}
	
	private BooleanFunctionDefinition getFunction (String name){
		for (BooleanFunctionDefinition i:generatedModel.getFunctions()){
			if (i.getName().equals(name)) return i;
		}
		return null;
	}
	
	//Cria as constantes sob demanda e retorna uma lista contendo as constantes solicitadas
	private List<IntConstant> getConstants(int[] constIds){
		List <IntConstant> consts, r; 
		r = new ArrayList <IntConstant>();
		consts = generatedModel.getConstants();
		for (int i : constIds){
			//Se aquele indice de variavel não existe crio variáveis até chegar em tal índice
			if (i> consts.size())
				for(int j = consts.size(); j<i;j++)
					addConstant();
			r.add(consts.get(i-1));							
		}
		return r;
	}

	private IntConstant addConstant(){
		IntConstant newConst = factory.createConstant();
		generatedModel.getConstants().add(newConst);
		return newConst;		
	}
	
	public OntoUMLZ3System run(){
		generatedModel = factory.createOntoUMLZ3System();
		populateWithWorldStructure();
		return generatedModel;
		
	}

}
