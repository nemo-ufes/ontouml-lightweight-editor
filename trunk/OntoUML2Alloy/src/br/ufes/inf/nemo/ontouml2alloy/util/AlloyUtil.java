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
 * This class is used to provide useful methods of manipulating the Alloy API.
 * 
 * @author John Guerson
 *
 */
public class AlloyUtil {

	/**
	 * Creates Alloy Signature World: 
	 * 
	 * abstract sig World {}
	 * 
	 * @return
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
	
	/* =========================================================================================================*/
	
	/**
	 * Creates Module Import
	 * 
	 * open world_structure[World]
	 * open ontological_propertis[World]
	 * 
	 * @param modulename: module name
	 * @return
	 */
	public static ModuleImportation createModuleImport (AlloyFactory factory, String modulename, String path, SignatureDeclaration param)
	{
		ModuleImportation mi = factory.createModuleImportation();		
		mi.setName(modulename);
		mi.setPath(path);
		mi.getParameters().add(param.getName());
		return mi;		
	}	
	
	/* =========================================================================================================*/
	
	/**
	 * Creates default Signatures.
	 * 
	 * sig Object{}
	 * sig Property{} 
	 * sig DataType{}
	 * ...
	 * 
	 * @param module
	 */
	public static void createDefaultSignatures(AlloyFactory factory, AlloyModule module, ArrayList<String> defaultSignatures) 
	{		
		for(String sigElement : defaultSignatures)
		{
			SignatureDeclaration sigDecl = factory.createSignatureDeclaration();
			sigDecl.setName(sigElement);			
			module.getParagraph().add(sigDecl);
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Creates exists Variable with Declaration.
	 * 
	 * exists: some Object + Property + DataType +...,
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Variable createExistsVariableDeclaration(AlloyFactory factory, ArrayList<String> defaultSignatures)
	{
		Variable exists = factory.createVariable();
		exists.setName("exists");
		Declaration declaration = factory.createDeclaration();
		declaration.getVariable().add(exists);
		exists.setDeclaration(declaration);
		UnaryOperation uOp = factory.createUnaryOperation();
		uOp.setOperator(UnaryOperator.SOME_LITERAL);	
		uOp.setExpression( createDefaultSignaturesUnionExpression(factory,defaultSignatures) );	
		declaration.setExpression(uOp);
		return exists;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Creates an union expression between default signatures.
	 * 
	 * Object + Property + DataType +...
	 *  
	 * @return
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
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					bo.setLeftExpression(sr);
					exp = bo;
				}
				if(cont > 1 && cont != defaultSignatures.size())
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
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
	
	/* =========================================================================================================*/
	
	/**
	 * Creates the "linear_existence" predicate invocation.
	 * 
	 * all_elements_exists[Object+Property+DataType,exists]
	 * 
	 * @param exists
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static PredicateInvocation createAllElementsExistsInvocation(AlloyFactory factory, Variable exists, ArrayList<String> defaultSignatures)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();	
		pI.setPredicate("all_elements_exists");
		pI.getParameter().add(createDefaultSignaturesUnionExpression(factory,defaultSignatures));
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Creates the "linear_existence" predicate invocation.
	 * 
	 * linear_existence[exists]
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static PredicateInvocation createLinearExistenceInvocation(AlloyFactory factory, Variable exists)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("linear_existence");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;
	}	
	
	/* =========================================================================================================*/
	
	/**
	 * Creates "always_exists" predicate invocation.
	 * 
	 * always_exists[DataType,exists]
	 * 
	 * @param exists
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static PredicateInvocation createAlwaysExistsInvocation(AlloyFactory factory, Variable exists, SignatureDeclaration sigDatatype)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("always_exists");
		SignatureReference srDataType = factory.createSignatureReference();
		srDataType.setSignature(sigDatatype.getName());
		pI.getParameter().add(srDataType);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		return pI;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Creates a specific Declaration in Alloy:
	 * 
	 * "[variableName]: set exists:>[typeName],".
	 *  
	 * @param variableName
	 * @param typeName
	 * @return
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
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		uOp.setExpression(bo);		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);		
		decl.setExpression(uOp);	
		return decl;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Creates a specific Quantification Expression in Alloy.
	 * 
	 * QuantificationExpression = "[quantificator] [varibleName]: [typeName]".
	 * 
	 * For Example = "all w: World".
	 */	 
	@SuppressWarnings("unchecked")
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
	
	/* =========================================================================================================*/
	
	/**
	 * Creates a specific Quantification Expression in Alloy.
	 * 
	 * QuantificationExpression = "[quantificator] [varibleName]: [variableName2].[typeName]".
	 *  
	 * For Example: "all x: w.Enrollment".
	 */	 
	@SuppressWarnings("unchecked")
	public static QuantificationExpression createQuantificationExpression(AlloyFactory factory, Quantificator quantificator, String variableName1, String variableName2, String typeName)
	{
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(quantificator);
		Declaration decl = factory.createDeclaration();
		Variable varx = factory.createVariable();
		varx.setName(variableName1);
		varx.setDeclaration(decl);
		BinaryOperation bo = createBinaryOperation(factory,variableName2,BinaryOperator.JOIN_LITERAL,typeName);		
		decl.setExpression(bo);
		qe.getDeclaration().add(decl);
		return qe;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Creates a specific BinaryOperation in Alloy.
	 * 
	 * BinaryOperation = "[varibleName1] [binOperator] [variableName2]".
	 * 
	 * For Example: "w.Enrollment".
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
	
	/* =========================================================================================================*/

	/**
	 * Creates a specific BinaryOperation in Alloy.
	 * 
	 * BinaryOperation = "[varibleName1] [binOperator] [binaryOperation]".
	 * 
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
	
	/* =========================================================================================================*/
	
	/**
	 * Creates a specific Quantification Expression in Alloy.
	 * 
	 * QuantificationExpression = "all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2".
	 * 
	 * For Example: "all w: World | all x: w.RelatorName | # ( x.(w.possui) + x.(w.faz) ) >= 2". 
	 */	
	public static QuantificationExpression createQuantificationExpression(AlloyFactory factory, ArrayList<String> associationNames, String typeName)
	{
		// all w: World
		QuantificationExpression qeWorld = AlloyUtil.createQuantificationExpression(factory,Quantificator.ALL_LITERAL,"w","World");	
		// all x: w.name
		QuantificationExpression qe = AlloyUtil.createQuantificationExpression(factory,Quantificator.ALL_LITERAL,"x","w",typeName);
		qeWorld.setExpression(qe);

		// # (...) >= 2
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.GREATER_EQUAL_LITERAL);		
		UnaryOperation uOp = factory.createUnaryOperation();		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);		
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
				bo = AlloyUtil.createBinaryOperation(factory,"x",BinaryOperator.JOIN_LITERAL, AlloyUtil.createBinaryOperation(factory,"w",BinaryOperator.JOIN_LITERAL,name));				
				uOp.setExpression(bo);
				break;
			}
			if(cont == 1)
			{
				bo.setOperator(BinaryOperator.UNION_LITERAL);
				// x.(w.name)
				bo2 = AlloyUtil.createBinaryOperation(factory,"x",BinaryOperator.JOIN_LITERAL, AlloyUtil.createBinaryOperation(factory,"w",BinaryOperator.JOIN_LITERAL,name)); 				
				bo.setLeftExpression(bo2);				
				uOp.setExpression(bo);
			}
			if(cont > 1 && cont != associationNames.size())
			{				
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);	
				// x.(w.name)
				bo2 = AlloyUtil.createBinaryOperation(factory,"x",BinaryOperator.JOIN_LITERAL, AlloyUtil.createBinaryOperation(factory,"w",BinaryOperator.JOIN_LITERAL,name));				
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(bo2);				
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == associationNames.size())
			{
				// x.(w.name)
				bo2 = AlloyUtil.createBinaryOperation(factory,"x",BinaryOperator.JOIN_LITERAL, AlloyUtil.createBinaryOperation(factory,"w",BinaryOperator.JOIN_LITERAL,name));				
				bo.setRightExpression(bo2);
			}
			cont++;
		}
		qe.setExpression(co);
		return qeWorld;
	}

	/* =========================================================================================================*/
	
	/**
	 *	Creates the specific Function for visibility in Alloy.
	 *
	 *  fun visible : World some -> some univ {	exists }
	 *  
	 */
	public static void createVisibleFunction (AlloyFactory factory, AlloyModule module, SignatureDeclaration world, Variable exists)
	{
		FunctionDeclaration fun = factory.createFunctionDeclaration();
		fun.setName("visible");
	
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
		fun.setExpression(vr);
		
		module.getParagraph().add(fun);
	}
	
	/* =========================================================================================================*/
	
	/**
	 *	Creates a Disjoint Expression in the Block of Signature World.
	 *
	 * 	disj[ DisjNamesList[0], DisjNamesList[1], ... ],
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void createDisjointExpressionInWorld (AlloyFactory factory, SignatureDeclaration world, ArrayList<String> DisjNamesList)
	{
		DisjointExpression disj = null;
		
		//Kinds, Quantities and Collectives
		if(DisjNamesList.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String s : DisjNamesList)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(s);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}		
	}
	
	/* =========================================================================================================*/
	
	/**
	 * 	Creates a Compare Operation in the Block of Signature World.
	 * 
	 *  exists:> defaultsig in NamesList[0] + NamesList[1] + ...
	 */
	@SuppressWarnings("unchecked")
	public static void createExistsCompareOperationInWorld(AlloyFactory factory, Variable exists, SignatureDeclaration world, SignatureDeclaration defaultsig, ArrayList<String> NamesList) 
	{		
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.SUBSET_LITERAL);
		
		BinaryOperation bOp = factory.createBinaryOperation();
		bOp.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		
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
				bo.setOperator(BinaryOperator.UNION_LITERAL);
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
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
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

	/* =========================================================================================================*/
	
	/**
	 *	 Creates the default Run command in Alloy.
	 *
	 *	 run { } for 10 but 3 World
	 */
	@SuppressWarnings("unchecked")
	public static void createDefaultRunComand(AlloyFactory factory, AlloyModule module) 
	{
		CommandDeclaration run = factory.createCommandDeclaration();
		run.setIsRun(true);
		
		GenericScope gs = factory.createGenericScope();		
		gs.setScopeSize(10);
		
		Scopeable s = factory.createScopeable();		
		s.setIsExactly(true);
		s.setScopeSize(3);
		s.setSignature("World");
		gs.getScopeable().add(s);
		
		run.setScope(gs);
		module.getParagraph().add(run);
	}
	
	/**
	 * Creates a rigidity predicate invocation in Alloy.
	 * 
	 * rigidity[ rigidClassName, defaultsig, exists ]
	 * 
	 */
	@SuppressWarnings("unchecked")
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
	 * 
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
}
