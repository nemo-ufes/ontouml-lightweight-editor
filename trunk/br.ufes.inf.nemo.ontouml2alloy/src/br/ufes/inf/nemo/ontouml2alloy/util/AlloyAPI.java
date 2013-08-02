package br.ufes.inf.nemo.ontouml2alloy.util;

import java.util.ArrayList;

import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.GenericScope;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.Multiplicity;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.Quantificator;
import br.ufes.inf.nemo.alloy.Scopeable;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.SignatureReference;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.UnaryOperator;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;


/**
 * Provide useful methods (as an API) for manipulating our Alloy Metamodel.
 * 
 * 	@authors John Guerson, Tiago Prince and Lucas Thom
 *
 */
public class AlloyAPI {	
	
	/**
	 * Create a Declaration. e.g. name : set ArrowOperation.
	 */
	public static Declaration createDeclaration(AlloyFactory factory,String name, ArrowOperation aOp)
	{
		Declaration decl = factory.createDeclaration();
		UnaryOperation uOp = factory.createUnaryOperation();
		decl.setExpression(uOp);				
		uOp.setOperator(UnaryOperator.SET);		
		Variable var = factory.createVariable();
		var.setName(name);
		var.setDeclaration(decl);		
		uOp.setExpression(aOp);
		return decl;
	}
	
	/**
	 * Creates an Arrow Operation. e.g. sourceName lower/upper -> targetName lower/upper.
	 */
	public static ArrowOperation createArrowOperation(AlloyFactory factory, String sourceName, int lowerSource, int upperSource, String targetName, int lowerTarget, int upperTarget)
	{
		ArrowOperation aOp = factory.createArrowOperation();
		VariableReference source = factory.createVariableReference();
		VariableReference target = factory.createVariableReference();
		source.setVariable(sourceName);
		target.setVariable(targetName);
		//source
		if(lowerSource == 1 && upperSource == 1) aOp.setLeftMultiplicity(Multiplicity.ONE);		
		else if(lowerSource == 0 && upperSource == 1) aOp.setLeftMultiplicity(Multiplicity.LONE);
		else if(lowerSource >= 1) aOp.setLeftMultiplicity(Multiplicity.SOME);
	    else aOp.setLeftMultiplicity(Multiplicity.SET);		
		//target
		if(lowerTarget == 1 && upperTarget == 1) aOp.setRightMultiplicity(Multiplicity.ONE);
		else if(lowerTarget == 0 && upperTarget == 1) aOp.setRightMultiplicity(Multiplicity.LONE);
		else if(lowerTarget >= 1) aOp.setRightMultiplicity(Multiplicity.SOME);
		else aOp.setRightMultiplicity(Multiplicity.SET);			
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		return aOp;
	}
	
	/**
	 * Creates Alloy Signature World. e.g. abstract sig World {}.
	 */
	public static SignatureDeclaration createSigWorld(AlloyFactory factory)
	{
		SignatureDeclaration world = factory.createSignatureDeclaration();
		world.setExists(false);
		world.setIsAbstract(true);
		world.setBlock(factory.createBlock());
		world.setName("World");
		return world;
	}

	/**
	 * Creates Module Import. e.g. open world_structure[World]
	 */
	public static ModuleImportation createModuleImport (AlloyFactory factory, String modulename, String path, SignatureDeclaration param)
	{
		ModuleImportation mi = factory.createModuleImportation();		
		mi.setName(modulename);
		mi.setPath(path);
		if(param!=null) mi.getParameters().add(param.getName());
		return mi;		
	}	

	/**
	 * Creates exists Declaration. e.g. exists: some Object + Property + ..., .
	 */
	public static Variable createExistsDeclaration(AlloyFactory factory, ArrayList<String> defaultSignatures)
	{
		Variable exists = factory.createVariable();
		exists.setName("exists");
		Declaration declaration = factory.createDeclaration();
		declaration.getVariable().add(exists);
		exists.setDeclaration(declaration);
		UnaryOperation uOp = factory.createUnaryOperation();
		uOp.setOperator(UnaryOperator.SOME);	
		uOp.setExpression( createDefaultSignaturesUnionExpression(factory,defaultSignatures) );	
		declaration.setExpression(uOp);
		return exists;
	}

	/**
	 * Creates an union expression between default signatures. e.g. Object + Property + ... .
	 */
	public static Expression createDefaultSignaturesUnionExpression(AlloyFactory factory, ArrayList<String> defaultSignatures) 
	{
		int cont = 1;		
		BinaryOperation bo = null;
		Expression exp = null;
		for(String sigElement : defaultSignatures)
		{
			if(defaultSignatures.size() == 1)
			{
				SignatureReference sr = factory.createSignatureReference();
				sr.setSignature(sigElement);
				exp = sr;
			} else {
				if(cont == 1)
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo = factory.createBinaryOperation();
					bo.setOperator(BinaryOperator.UNION);
					bo.setLeftExpression(sr);
					exp = bo;
				}
				if(cont > 1 && cont != defaultSignatures.size())
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(sr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == defaultSignatures.size())
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo.setRightExpression(sr);
				}
			}
			cont = cont + 1;
		}
		return exp;
	}

	/**
	 * Creates the "elements_existence" predicate invocation. e.g. elements_existence[Object+Property,exists]
	 */
	public static PredicateInvocation createElementsExistenceInvocation(AlloyFactory factory, Variable exists, ArrayList<String> defaultSignatures)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();	
		pI.setPredicate("elements_existence");
		pI.getParameter().add(createDefaultSignaturesUnionExpression(factory,defaultSignatures));
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;
	}

	/**
	 * Creates the "continuous_existence" predicate invocation. e.g. continuous_existence[exists].
	 */
	public static PredicateInvocation createContinuousExistenceInvocation(AlloyFactory factory, Variable exists)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("continuous_existence");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;
	}	
	
	/**
	 * Creates "necessarily_exist" predicate invocation. e.g.  necessarily_exist[DataType,exists] .
	 */
	public static PredicateInvocation createNecessarilyExistInvocation(AlloyFactory factory, Variable exists, SignatureDeclaration sigDatatype)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("necessarily_exist");
		SignatureReference srDataType = factory.createSignatureReference();
		srDataType.setSignature(sigDatatype.getName());
		pI.getParameter().add(srDataType);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;
	}

	/**
	 * Creates Declaration. e.g. variableName: set exists:>typeName,
	 */
	public static Declaration createDeclaration (AlloyFactory factory, Variable exists, String variableName, String typeName)
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();		
		sr.setSignature(typeName);		
		vr.setVariable(exists.getName());		
		var.setName(variableName);
		var.setDeclaration(decl);		
		uOp.setOperator(UnaryOperator.SET);
		uOp.setExpression(bo);		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);		
		decl.setExpression(uOp);	
		return decl;
	}

	/**
	 * Creates a specific Declaration in Alloy. e.g. x: World.name
	 */
	public static Declaration createDeclaration (AlloyFactory factory,SignatureDeclaration world, String name)
	{	
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);	
		BinaryOperation bOp = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		VariableReference vr2 = factory.createVariableReference();
		vr.setVariable(world.getName());
		vr2.setVariable(name);
		bOp.setOperator(BinaryOperator.JOIN);
		bOp.setLeftExpression(vr);
		bOp.setRightExpression(vr2);
		decl.setExpression(bOp);
		return decl;
	}

	/**
	 *	Creates a specific Declaration in Alloy.
	 *  i.e., w: World
	 */
	public static Declaration createDeclaration (AlloyFactory factory,SignatureDeclaration world)
	{	
		Declaration decl = factory.createDeclaration();		
		Variable var = factory.createVariable();
		var.setName("w");
		var.setDeclaration(decl);	
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(world.getName());
		decl.setExpression(vr);
		return decl;
	}

	/**
	 * Creates a specific Quantification Expression in Alloy.
	 * QuantificationExpression = "quantificator varibleName: typeName".
	 * For Example = "all w: World".
	 */	 
	public static QuantificationExpression createQuantificationExpression(AlloyFactory factory, Quantificator quantificator, String variableName, String typeName)
	{
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(quantificator);		
		Declaration decl = factory.createDeclaration();
		Variable varbl = factory.createVariable();
		varbl.setName(variableName);		
		varbl.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(typeName);
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		return qe;
	}
	
	/**
	 * Creates a specific Quantification Expression in Alloy.
	 * QuantificationExpression = "quantificator varibleName: variableName2.typeName".
	 * For Example: "all x: w.Enrollment".
	 */	 
	public static QuantificationExpression createQuantificationExpression(AlloyFactory factory, Quantificator quantificator, String variableName1, String variableName2, String typeName)
	{
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(quantificator);
		Declaration decl = factory.createDeclaration();
		Variable varx = factory.createVariable();
		varx.setName(variableName1);
		varx.setDeclaration(decl);
		BinaryOperation bo = createBinaryOperation(factory,variableName2,BinaryOperator.JOIN,typeName);		
		decl.setExpression(bo);
		qe.getDeclaration().add(decl);
		return qe;
	}
	
	/**
	 * Creates a specific BinaryOperation in Alloy.
	 * BinaryOperation = "varibleName1 binOperator variableName2".
	 * For Example: w.Enrollment.
	 */
	public static BinaryOperation createBinaryOperation(AlloyFactory factory, String variableName1, BinaryOperator binOperator, String variableName2)
	{
		BinaryOperation bo = factory.createBinaryOperation();
		bo.setOperator(binOperator);
		VariableReference vrR = factory.createVariableReference();
		vrR.setVariable(variableName1);
		bo.setLeftExpression(vrR);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(variableName2);		
		bo.setRightExpression(vr);
		return bo; 
	}
	
	/**
	 * Creates a specific BinaryOperation in Alloy.
	 * BinaryOperation = "[varibleName1] [binOperator] [binaryOperation]".
	 * For Example: "x.(w.Enrollment)".
	 */	 
	public static BinaryOperation createBinaryOperation(AlloyFactory factory, String variableName1, BinaryOperator binOperator, BinaryOperation binOperation)
	{
		BinaryOperation bo = factory.createBinaryOperation();
		bo.setOperator(binOperator);
		VariableReference vrR = factory.createVariableReference();
		vrR.setVariable(variableName1);
		bo.setLeftExpression(vrR);		
		bo.setRightExpression(binOperation);
		return bo; 
	}
	
	/**
	 * Creates a specific Quantification Expression in Alloy.
	 * QuantificationExpression = "all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2".
	 * For Example: "all w: World | all x: w.RelatorName | # ( x.(w.possui) + x.(w.faz) ) >= 2". 
	 */	
	public static QuantificationExpression createQuantificationExpression(AlloyFactory factory, ArrayList<String> associationNames, String typeName)
	{
		// all w: World
		QuantificationExpression qeWorld = AlloyAPI.createQuantificationExpression(factory,Quantificator.ALL,"w","World");	
		// all x: w.name
		QuantificationExpression qe = AlloyAPI.createQuantificationExpression(factory,Quantificator.ALL,"x","w",typeName);
		qeWorld.setExpression(qe);

		// # (...) >= 2
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.GREATER_EQUAL);		
		UnaryOperation uOp = factory.createUnaryOperation();		
		uOp.setOperator(UnaryOperator.CARDINALITY);		
		co.setLeftExpression(uOp);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable("2");
		co.setRightExpression(vr);
		
		//( x.(w.name) + x.(w.name) + ... + x.(w.name) )
		int cont = 1;
		BinaryOperation bo = factory.createBinaryOperation();
		BinaryOperation bo2 = factory.createBinaryOperation();		
		for(String name : associationNames)
		{
			if(associationNames.size() == 1)
			{	
				// x.(w.name)
				bo = AlloyAPI.createBinaryOperation(factory,"x",BinaryOperator.JOIN, AlloyAPI.createBinaryOperation(factory,"w",BinaryOperator.JOIN,name));				
				uOp.setExpression(bo);
				break;
			}
			if(cont == 1)
			{
				bo.setOperator(BinaryOperator.UNION);
				// x.(w.name)
				bo2 = AlloyAPI.createBinaryOperation(factory,"x",BinaryOperator.JOIN, AlloyAPI.createBinaryOperation(factory,"w",BinaryOperator.JOIN,name)); 				
				bo.setLeftExpression(bo2);				
				uOp.setExpression(bo);
			}
			if(cont > 1 && cont != associationNames.size())
			{				
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION);	
				// x.(w.name)
				bo2 = AlloyAPI.createBinaryOperation(factory,"x",BinaryOperator.JOIN, AlloyAPI.createBinaryOperation(factory,"w",BinaryOperator.JOIN,name));				
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(bo2);				
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == associationNames.size())
			{
				// x.(w.name)
				bo2 = AlloyAPI.createBinaryOperation(factory,"x",BinaryOperator.JOIN, AlloyAPI.createBinaryOperation(factory,"w",BinaryOperator.JOIN,name));				
				bo.setRightExpression(bo2);
			}
			cont++;
		}
		qe.setExpression(co);
		return qeWorld;
	}

	/**
	 *	Creates the specific Function for visibility in Alloy.
	 *  fun visible : World some -> some univ {	exists }
	 */
	public static void createVisibleFunction (AlloyFactory factory, AlloyModule module, SignatureDeclaration world, Variable exists)
	{
		FunctionDeclaration fun = factory.createFunctionDeclaration();
		fun.setName("visible");
		fun.setBlock(factory.createBlock());
		VariableReference vr;
		ArrowOperation aOp = factory.createArrowOperation();
		SignatureReference sr;
		sr = factory.createSignatureReference();
		sr.setSignature(world.getName());
		vr = factory.createVariableReference();
		vr.setVariable("univ");	
		aOp.setLeftExpression(sr);
		aOp.setRightExpression(vr);	
		fun.setType(aOp);
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		fun.getBlock().getExpression().add(vr);		
		module.getParagraph().add(fun);
	}
	
	/**
	 * Creates a Visibility Function without a Expression. e.g.  fun funName : World -> univ { } 
	 */
	public static FunctionDeclaration createVisibilityFunction (String funName, AlloyFactory factory, SignatureDeclaration world)
	{
		FunctionDeclaration fun = factory.createFunctionDeclaration();
		fun.setName(funName);		
		fun.setBlock(factory.createBlock());	
		ArrowOperation aOp = factory.createArrowOperation();		
		SignatureReference sr = factory.createSignatureReference();
		sr.setSignature(world.getName());
		VariableReference vr = factory.createVariableReference();
		vr.setVariable("univ");	
		aOp.setLeftExpression(sr);
		aOp.setRightExpression(vr);	
		fun.setType(aOp);
		return fun;
	}
		
	/**
	 *	Creates a Disjoint Expression in Alloy
	 * 	disj[ DisjNamesList[0], DisjNamesList[1], ... ],
	 */
	public static DisjointExpression createDisjointExpression (AlloyFactory factory, ArrayList<String> DisjNamesList)
	{
		DisjointExpression disj = null;		
		if(DisjNamesList.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String s : DisjNamesList)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(s);
				disj.getSet().add(vr);
			}			
		}			
		return disj;
	}
	
	/**
	 * 	Creates a Compare Operation in the Block of Signature World.
	 *  exists:> defaultsig in NamesList[0] + NamesList[1] + ...
	 */
	public static void createExistsCompareOperationInWorld(AlloyFactory factory, Variable exists, SignatureDeclaration world, SignatureDeclaration defaultsig, ArrayList<String> NamesList) 
	{		
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.SUBSET);		
		BinaryOperation bOp = factory.createBinaryOperation();
		bOp.setOperator(BinaryOperator.RANGE_RESTRICTION);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		bOp.setLeftExpression(vr);		
		vr = factory.createVariableReference();
		vr.setVariable(defaultsig.getName());						
		bOp.setRightExpression(vr);
		co.setLeftExpression(bOp);		
		int cont = 1;
		BinaryOperation bo = null;
		for(String subs : NamesList)
		{			
			if(NamesList.size() == 1)
			{
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				co.setRightExpression(vr);
			}
			if(cont == 1 && NamesList.size() != 1)
			{
				bo = factory.createBinaryOperation();
				bo.setOperator(BinaryOperator.UNION);
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				bo.setLeftExpression(vr);
				co.setRightExpression(bo);
			}
			if(cont > 1 && cont != NamesList.size())
			{
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION);
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == NamesList.size() && cont != 1)
			{
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				bo.setRightExpression(vr);
			}
			cont = cont + 1;
		}
		world.getBlock().getExpression().add(co);
	}
	
	/**
	 *	 Creates the default Run command in Alloy.
	 *	 run { } for 10 but 3 World,7Int
	 */
	public static CommandDeclaration createDefaultRunComand(AlloyFactory factory, AlloyModule module) 
	{
		CommandDeclaration run = factory.createCommandDeclaration();
		run.setIsRun(true);		
		GenericScope gs = factory.createGenericScope();		
		gs.setScopeSize(10);		
		Scopeable s1 = factory.createScopeable();		
		s1.setIsExactly(false);
		s1.setScopeSize(3);
		s1.setSignature("World");
		gs.getScopeable().add(s1);		
		Scopeable s2 = factory.createScopeable();
		s2.setIsExactly(false);
		s2.setScopeSize(7);
		s2.setSignature("int");
		gs.getScopeable().add(s2);		
		run.setScope(gs);
		return run;
	}
	
	/**
	 * Creates a rigidity predicate invocation in Alloy.
	 * rigidity[rigidClassName,defaultsig,exists]
	 */
	public static PredicateInvocation createRigidityInvocation (AlloyFactory factory, SignatureDeclaration defaultsig, Variable exists, String rigidClassName)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("rigidity");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(rigidClassName);
		pI.getParameter().add(vr);
		vr = factory.createVariableReference();
		vr.setVariable(defaultsig.getName());
		pI.getParameter().add(vr);	
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;		
	}
	
	/**
	 * Creates a antirigidity predicate invocation in Alloy.
	 * antirigidity[antirigidClassName,Object,exists]
	 */
	public static PredicateInvocation createAntiRigidityInvocation (AlloyFactory factory, SignatureDeclaration Object, Variable exists, String antirigidClassName)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("antirigidity");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(antirigidClassName);
		pI.getParameter().add(vr);
		vr = factory.createVariableReference();
		vr.setVariable(Object.getName());
		pI.getParameter().add(vr);	
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;		
	}
	
	/**
	 *  Creates a Compare Operation in Alloy.
	 *  leftName CompareOperator rightName.
	 *  For Example: child in father
	 */
	public static CompareOperation createCompareOperation (AlloyFactory factory, String leftName, CompareOperator cop, String rightName)
	{
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(cop);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(leftName);
		co.setLeftExpression(vr);		
		vr = factory.createVariableReference();
		vr.setVariable(rightName);
		co.setRightExpression(vr);
		return co;
	}
	
	/**
	 * Creates a specific Function Declaration in Alloy.
	 * fun nameFunction [x: World.paramName,w: World] : set World.returnName {
	 * 		x.(w.assocName)
	 * }
	 *  or  
	 * fun nameFunction [x: World.paramName,w: World] : set World.returnName {
	 * 		(w.assocName).x
	 * }
	 */
	public static FunctionDeclaration createFunctionDeclaration (AlloyFactory factory, SignatureDeclaration world, boolean isSourceProperty, String functionName, String paramName, String returnName, String assocName )
	{
		FunctionDeclaration fun = factory.createFunctionDeclaration();
		fun.setName(functionName);
		fun.setBlock(factory.createBlock());
		
		// set returnName
		UnaryOperation uOp = factory.createUnaryOperation();
		uOp.setOperator(UnaryOperator.SET);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(returnName);				
		uOp.setExpression(vr);
		fun.setType(uOp);
	
		// x: paramName
		Declaration decl = factory.createDeclaration();				
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);	
		vr = factory.createVariableReference();
		vr.setVariable(paramName);
		decl.setExpression(vr);		
		fun.getParameter().add(decl);
	
		// w: World
		decl = createDeclaration(factory,world);	
		fun.getParameter().add(decl);
		
		// w.assocName
		BinaryOperation bOp = createBinaryOperation(factory,"w",BinaryOperator.JOIN,assocName);
			
		// x.(w.assocName)  or  (w.assocName).x
		vr = factory.createVariableReference();
		vr.setVariable("x");
		BinaryOperation bOp2 = factory.createBinaryOperation();
		bOp2.setOperator(BinaryOperator.JOIN);
		
		if(returnName.contains(world.getName())) returnName = returnName.replace("World.", "");
				
		if(!isSourceProperty)
		{
			bOp2.setLeftExpression(vr);
			bOp2.setRightExpression(bOp);
		}
		else
		{
			bOp2.setLeftExpression(bOp);
			bOp2.setRightExpression(vr);
		}	
		
		fun.getBlock().getExpression().add(bOp2);
		
		return fun;
	}
	
	/**
	 *	Creates a specific Imuttable Predicate Invocation.
	 *  predName[typeName,assocName].
	 *  For Example: immutable_source[Enrollment,enr].
	 *  For Example: immutable_target[Integer,dt]
	 */
	public static PredicateInvocation createImmutablePredicateInvocation (AlloyFactory factory, String predName, String typeName, String assocName)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate(predName);
		VariableReference param1 = factory.createVariableReference();
		VariableReference param2 = factory.createVariableReference();
		param1.setVariable(typeName);
		param2.setVariable(assocName);
		pI.getParameter().add(param1);
		pI.getParameter().add(param2);
		return pI;
	}

	/**
	 * Create a specific Quantification Expression.
	 * all x: type | # binOperation CompOperation value
	 */
	public static QuantificationExpression createQuantificationExpression (AlloyFactory factory, String type, BinaryOperation binOperation, CompareOperator CompOperator, int value)
	{
		QuantificationExpression qe = createQuantificationExpression(factory, Quantificator.ALL, "x", type);					
		CompareOperation co = factory.createCompareOperation();		
		UnaryOperation uOp = factory.createUnaryOperation();		
		uOp.setOperator(UnaryOperator.CARDINALITY);
		uOp.setExpression(binOperation);		
		co.setLeftExpression(uOp);		
		co.setOperator(CompOperator);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(String.valueOf(value));		
		co.setRightExpression(vr);		
		qe.setExpression(co);
		return qe;
	}
	
	/**
	 *	Create Union Expression.
	 *  list[0] + list[1] + list[2]...
	 */
	public static BinaryOperation createUnionExpression (AlloyFactory factory, ArrayList<String> list)
	{	
		int cont = 1;
		BinaryOperation bo = factory.createBinaryOperation();
		BinaryOperation main = bo;
		for(String str : list)
		{
			if(list.size() == 1) 
			{	
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(str);
				bo.setLeftExpression(vr);
				break;
			}
			if(cont == 1)
			{
				bo.setOperator(BinaryOperator.UNION);
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(str);
				bo.setLeftExpression(vr);				
			}
			if(cont > 1 && cont != list.size())
			{
				bo.setRightExpression(factory.createBinaryOperation());
												
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION);
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(str);
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == list.size())
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(str);
				bo.setRightExpression(vr);
			}
			cont++;
		}
		return main;
	}
	
	/**
	 * Set Cardinality of Arrow Operation according to the values.
	 */
	public static void setCardinalitiesArrowOperation (ArrowOperation aOp, int lowerSource, int upperSource, int lowerTarget, int upperTarget) 
	{
		// Source 
		if (lowerSource == 1 && upperSource == 1) aOp.setLeftMultiplicity(Multiplicity.ONE);		
		else {			
			if(lowerSource == 0 && upperSource == 1) aOp.setLeftMultiplicity(Multiplicity.LONE);
			else {
				if(lowerSource >= 1) aOp.setLeftMultiplicity(Multiplicity.SOME);
				else aOp.setLeftMultiplicity(Multiplicity.SET);
			}
		}		
		// Target 
		if(lowerTarget == 1 && upperTarget == 1) aOp.setRightMultiplicity(Multiplicity.ONE);
		else {
			if(lowerTarget == 0 && upperTarget == 1) aOp.setRightMultiplicity(Multiplicity.LONE);
			else {
				if(lowerTarget >= 1) aOp.setRightMultiplicity(Multiplicity.SOME);
				else aOp.setRightMultiplicity(Multiplicity.SET);
			}
		}
	}
}
