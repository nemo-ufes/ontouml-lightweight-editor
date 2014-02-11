package br.ufes.inf.nemo.ontouml2z3py;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import RefOntoUML.Kind;
import RefOntoUML.ObjectClass;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.ResourceUtil;
import br.ufes.inf.nemo.z3py.BooleanFunctionDefinition;
import br.ufes.inf.nemo.z3py.Equality;
import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.FunctionCall;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.LogicalBinaryExpression;
import br.ufes.inf.nemo.z3py.LogicalNegation;
import br.ufes.inf.nemo.z3py.OntoUMLZ3System;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.impl.Z3pyFactoryImpl;
import br.ufes.inf.nemo.z3py.impl.Z3pyFactoryImpl.LogicalBinaryExpressionTypes;
public class Transformer {
	
	private OntoUMLParser ontoparser;
	private String sourceModelPath;
	private Z3pyFactoryImpl factory = new Z3pyFactoryImpl();
	private OntoUMLZ3System generatedModel;
	
	
	public Transformer(){
		
	}
	
	public Transformer(String sourceModelPath){
		this.sourceModelPath= sourceModelPath;
		
	}
	public OntoUMLZ3System run(){
		
		generatedModel = factory.createOntoUMLZ3System();
		//Gero toda a axiomatização referente a estrutura de mundos
		populateWithBranchInTimeWorldStructure();
		//Adiciono a função que utilizarei para verificar se um dado elemento existe em um dado mundo
		addFunction("exists", 2);
		Resource resource;
		try {
			resource = ResourceUtil.loadReferenceOntoUML(sourceModelPath);
			RefOntoUML.Package root  = (RefOntoUML.Package)resource.getContents().get(0);
			ontoparser = new OntoUMLParser(root);	
			populateWithObjectClasses();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return generatedModel;
		
	}
	
	private void populateWithObjectClasses(){
		for(RefOntoUML.Class p: ontoparser.getAllInstances(ObjectClass.class)){
			//Crio a função que representa o fato de um dado individuo ser daquele tipo em um dado mundo
			addFunction(p.getName(),2);
			if (p instanceof Kind){
				System.out.println("KIND");
						
			}
			
		}
	}
	
	
	private void populateWithBranchInTimeWorldStructure(){
		
		//Crio as funções que utilizarei		
		addFunction("World",1);
		addFunction("CurrentWorld",1);
		addFunction("PastWorld",1);
		addFunction("CounterfactualWorld", 1);
		addFunction("FutureWorld", 1);
		addFunction("next", 2);
		addFunction("recursiveNext", 2);


		//Crio as fórmulas
		//1.	1.	∀x,y (next(x,y) → World(x) ∧ World(y))		
		FunctionCall fc1 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1, 2}));
		FunctionCall fc2 = factory.createFunctionCall(getFunction("World"), getConstants(new int[]{1}));
		FunctionCall fc3 = factory.createFunctionCall(getFunction("World"), getConstants(new int[]{2}));
		LogicalBinaryExpression lbe1 = factory.createBinaryExpression(fc2, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		LogicalBinaryExpression lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "The two arguments fo the function next are worlds");
		
		//2.	∀x,y,z (next(x,y) ∧ next(z,y)) →x=z
		fc1 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1, 2}));
		fc2 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{3, 2}));
		lbe1 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.CONJUNCTION);
		Equality e1 = factory.createEquality(getConstants(new int[]{1, 3}));
		lbe2 = factory.createBinaryExpression(lbe1, e1 , LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "Every world is the next of, at most, one world");
		
		//3.	∀x (¬next(x,x))
		fc1 = factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1, 1}));
		LogicalNegation ln = factory.createLogicalNegation(fc1);
		addFormula(true, ln, "The function next is irreflexive");
		
		//4.	∀x,y (recursiveNext(x,y) ↔ next(x,y) ∨ ∃z (next(z,y) ∧ recursiveNext(x,z)))
		fc1= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{1, 2}));//1-x, 2-z, 3-y
		fc2= factory.createFunctionCall(getFunction("next"), getConstants(new int[]{2, 3}));
		lbe1 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.CONJUNCTION);
		Quantification qt = factory.createQuantification(false, lbe1, new HashSet<IntConstant>(getConstants(new int[]{2})), "");
		fc1= factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1, 3}));//1-x, 2-z, 3-y
		lbe1 = factory.createBinaryExpression(fc1, qt, LogicalBinaryExpressionTypes.DISJUNCTION);
		fc1= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{1, 3}));//1-x, 2-z, 3-y
		lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.BIIMPLICATION);
		addFormula(true,lbe2,"Here we defined the recursiveNext function as a helper to express the transitive closure of the function next");
				
		//5.	∀x,y (recursiveNext(x,y) →¬ recursiveNext(y,x))
		fc1= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{1, 2}));//1-x, 2-y		
		fc2= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{2, 1}));//1-x, 2-y
		ln = factory.createLogicalNegation(fc2);
		lbe1 = factory.createBinaryExpression(fc1, ln, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true,lbe1,"The next function is assimetric when considering its transitive closure");
		
		//6.	∀x (World(x) ↔ CurrentWorld(x) ∨ PastWorld(x) ∨ CounterFactualWorld(x) ∨ FutureWorld(x))
		fc1= factory.createFunctionCall(getFunction("CounterfactualWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("FutureWorld"), getConstants(new int[]{1}));
		lbe1 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.DISJUNCTION);
		fc1= factory.createFunctionCall(getFunction("PastWorld"), getConstants(new int[]{1}));
		lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		fc1= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{1}));
		lbe1 = factory.createBinaryExpression(fc1, lbe2, LogicalBinaryExpressionTypes.DISJUNCTION);
		fc1= factory.createFunctionCall(getFunction("World"), getConstants(new int[]{1}));
		lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.BIIMPLICATION);
		addFormula(true, lbe2, "There are four World types: Past, Counterfactual, Current and Future");
		
/*		∀x (CurrentWorld(x) ∧ ¬(PastWorld(x) ∨ CounterFactualWorld(x) ∨ FutureWorld(x))) ∨
		(PastWorld (x) ∧ ¬ (CurrentWorld (x) ∨ CounterFactualWorld(x) ∨ FutureWorld(x))) ∨
		(CounterFactualWorld(x) ∧ ¬ (CurrentWorld (x) ∨ PastWorld(x) ∨ FutureWorld(x))) ∨
		(FutureWorld(x) ∧ ¬ (CurrentWorld (x) ∨ CounterFactualWorld(x) ∨ PastWorld(x))) */
		fc1= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("PastWorld"), getConstants(new int[]{1}));
		fc3= factory.createFunctionCall(getFunction("CounterfactualWorld"), getConstants(new int[]{1}));
		FunctionCall fc4= factory.createFunctionCall(getFunction("FutureWorld"), getConstants(new int[]{1}));
		
		lbe1 = factory.createBinaryExpression(fc3, fc4, LogicalBinaryExpressionTypes.DISJUNCTION);
		lbe2 = factory.createBinaryExpression(fc2, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		ln = factory.createLogicalNegation(lbe2);
		LogicalBinaryExpression lbe3 = factory.createBinaryExpression(fc1, ln, LogicalBinaryExpressionTypes.CONJUNCTION);
		
		lbe1 = factory.createBinaryExpression(fc3, fc4, LogicalBinaryExpressionTypes.DISJUNCTION);
		lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		ln = factory.createLogicalNegation(lbe2);
		lbe1 = factory.createBinaryExpression(fc2, ln, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe3 = factory.createBinaryExpression(lbe3, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		
		lbe1 = factory.createBinaryExpression(fc2, fc4, LogicalBinaryExpressionTypes.DISJUNCTION);
		lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		ln = factory.createLogicalNegation(lbe2);
		lbe1 = factory.createBinaryExpression(fc3, ln, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe3 = factory.createBinaryExpression(lbe3, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		
		lbe1 = factory.createBinaryExpression(fc2, fc3, LogicalBinaryExpressionTypes.DISJUNCTION);
		lbe2 = factory.createBinaryExpression(fc1, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		ln = factory.createLogicalNegation(lbe2);
		lbe1 = factory.createBinaryExpression(fc4, ln, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe3 = factory.createBinaryExpression(lbe3, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		
		addFormula(true, lbe3, "The four world types are disjoint");
		//8.	∃x (CurrentWorld(x))
		fc1= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{1}));
		addFormula(false, fc1, "There is at least one current world");
		
		//9.	∀x,y (CurrentWorld(x) ∧ CurrentWorld(y) → x=y)
		fc1= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{2}));
		lbe1 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.CONJUNCTION);
		e1 = factory.createEquality(getConstants(new int[]{1, 2}));
		lbe2 = factory.createBinaryExpression(lbe1, e1, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "There is at most one Current World");
		
		//10.	∀x,y (CurrentWorld(x) ∧ next(x,y) → FutureWorld(y))
		fc1= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("FutureWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1,2}));
		lbe1 = factory.createBinaryExpression(fc1, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe2 = factory.createBinaryExpression(lbe1, fc2, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "The next world of a current world is a future world");
		
		//11.	∀x,y (FutureWorld(x) ∧ next(x,y) → FutureWorld(y))
		fc1= factory.createFunctionCall(getFunction("FutureWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("FutureWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1,2}));
		lbe1 = factory.createBinaryExpression(fc1, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe2 = factory.createBinaryExpression(lbe1, fc2, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "The next world of a future world is a future world");
		
		//12.	∀y (FutureWorld(y) → ∃x (CurrentWorld(x) ∧ recursiveNext(x,y)))
		fc1= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("FutureWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{1,2}));
		lbe1 = factory.createBinaryExpression(fc1, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		qt = factory.createQuantification(false, lbe1, new HashSet<IntConstant>(getConstants(new int[]{1})), "");
		lbe1 = factory.createBinaryExpression(fc2, qt, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe1, "Every future world is acessible from the current world by the recursiveNext transitive closure");
		
		//13.	∀x,y (PastWorld(x) ∧ next(x,y) → (CurrentWorld(y) ∨ PastWorld(y) ∨ CounterFactualWorld(y)))
		fc1= factory.createFunctionCall(getFunction("PastWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("CounterfactualWorld"), getConstants(new int[]{2}));
		fc4= factory.createFunctionCall(getFunction("PastWorld"), getConstants(new int[]{2}));
		lbe1 = factory.createBinaryExpression(fc3, fc4, LogicalBinaryExpressionTypes.DISJUNCTION);
		lbe1 = factory.createBinaryExpression(fc2, lbe1, LogicalBinaryExpressionTypes.DISJUNCTION);
		fc2= factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1,2}));
		lbe2 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe2 = factory.createBinaryExpression(lbe2, lbe1, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "The next world of a past world may be a cuurent world, a counterfactual world or another past world");
		
		//14.	∀x (PastWorld(x) → ∃y (CurrentWorld(y) ∧ recursiveNext(x,y)))
		fc1= factory.createFunctionCall(getFunction("PastWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("CurrentWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{1,2}));
		lbe1 = factory.createBinaryExpression(fc2, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		qt = factory.createQuantification(false, lbe1, new HashSet<IntConstant>(getConstants(new int[]{2})), "");
		lbe1 = factory.createBinaryExpression(fc1, qt, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe1, "The current world is acessible from all past worlds by the recursiveNext transitive closure");
		
		//15.	∀x,y (CounterFactualWorld (x) ∧ next(x,y) → CounterFactualWorld (y))
		fc1= factory.createFunctionCall(getFunction("CounterfactualWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("CounterfactualWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("next"), getConstants(new int[]{1,2}));
		lbe1 = factory.createBinaryExpression(fc1, fc3, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe2 = factory.createBinaryExpression(lbe1, fc2, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "The next world of a contrafactual world is another contrafactual world");
		
		//16.	∀x,y (PastWorld(x) ∧ CounterFactualWorld (y) →  recursiveNext(x,y))
		fc1= factory.createFunctionCall(getFunction("PastWorld"), getConstants(new int[]{1}));
		fc2= factory.createFunctionCall(getFunction("CounterfactualWorld"), getConstants(new int[]{2}));
		fc3= factory.createFunctionCall(getFunction("recursiveNext"), getConstants(new int[]{1,2}));
		lbe1 = factory.createBinaryExpression(fc1, fc2, LogicalBinaryExpressionTypes.CONJUNCTION);
		lbe2 = factory.createBinaryExpression(lbe1, fc3, LogicalBinaryExpressionTypes.IMPLICATION);
		addFormula(true, lbe2, "A contrafactual world is acessible from all past worlds by the recursiveNext transitive closure");
	}
	
	private BooleanFunctionDefinition addFunction(String name, int numberOfArguments){
		BooleanFunctionDefinition newFunction = factory.createFunction(name, numberOfArguments);
		generatedModel.getFunctions().add(newFunction);
		return newFunction;
	}

	private Quantification addFormula(boolean isUniversal, Expression exp, String comments){
		Quantification newFormula = factory.createFormula(isUniversal, exp, comments);
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
			//Se aquele indice de variavel nÃ£o existe crio variÃ¡veis atÃ© chegar em tal Ã­ndice
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
	

}
