package br.ufes.inf.nemo.ontouml2alloy.transformer;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.Multiplicity;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.UnaryOperator;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;
import br.ufes.inf.nemo.ontouml2alloy.rules.TAbstractClauseRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TDerivationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TRelatorRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TWeakSupplementationRule;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyUtil;

/**
 * 	@authors Tiago Sales, John Guerson and Lucas Thom
 */

public class Transformer extends BaseTransformer {
		
	/** List containing all the facts for relator's rule. */
	private ArrayList<FactDeclaration> relatorConstraintFactList = new ArrayList<FactDeclaration>();

	/** List containing all the facts for weak supplementation's rule. */
	private ArrayList<FactDeclaration> weakSupplementationFactList = new ArrayList<FactDeclaration>();
		
	/**
	 * Constructor().
	 */
	public Transformer (OntoUMLParser parser, AlloyFactory factory, OntoUMLOptions opt)
	{		
		super(parser,factory,opt);		
	}
			
	/**
	 * Transforms Classifiers.
	 */
	@SuppressWarnings("unchecked")
	public void transformClassifier(Classifier c) 
	{
		/* ObjectClassName: set exists:>Object,
		 */
		if(c instanceof ObjectClass)
		{
			Declaration decl = AlloyUtil.createDeclaration(factory,exists,ontoparser.getAlias(c), sigObject.getName());			
			if (decl!=null) world.getRelation().add(decl);			
		}
			
		/* fact weak_supplementation_constraint { all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2 }
		 */
		if ((c instanceof RigidSortalClass) && (options.weakSupplementationConstraint))
		{				
			FactDeclaration fact = TWeakSupplementationRule.createFactDeclaration(ontoparser, factory, c);				
			if (fact!=null) weakSupplementationFactList.add(fact);		
		}
		
		/* PropertyName: set exists:>Property,
		 */
		if(c instanceof MomentClass)
		{			
			Declaration decl = AlloyUtil.createDeclaration(factory,exists,ontoparser.getAlias(c),sigProperty.getName());			
			world.getRelation().add(decl);
		}
		
		/* fact relator_constraint { all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2 }
		 */
		if ((c instanceof Relator) && (options.relatorConstraint))
		{			
			FactDeclaration fact = TRelatorRule.createFactDeclaration(ontoparser, factory, (Relator)c);			
			if (fact!= null) relatorConstraintFactList.add(fact);
		}		
						
		/* abstract_father = concrete_child1 + concrete_child2 + concrete_child3 + ...
		 */
		if (c.isIsAbstract()) 
		{
			CompareOperation co = TAbstractClauseRule.createCompareOperation(ontoparser, factory, c);			
			if (co!=null) world.getBlock().getExpression().add(co);			
		}
	}
	
	/**
	 * Transforms Attributes
	 */
	@SuppressWarnings("unchecked")
	public void transformAttribute(Classifier c, Property attr)
	{		
		String target = new String();
		ArrowOperation aOp  = factory.createArrowOperation();
		
		if (attr.getType() instanceof PrimitiveType)
		{
			if (attr.getType().getName().equals("int")) target = "Int";
			if (attr.getType().getName().equals("Boolean")) target = "Bool";
			aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(c),0,-1,target,attr.getLower(),attr.getUpper());
			
		}else{
			
			aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(c),0,-1,ontoparser.getAlias(attr.getType()),attr.getLower(),attr.getUpper());
		}
				
		Declaration decl = AlloyUtil.createDeclaration(factory, ontoparser.getAlias(attr), aOp);		
		if (decl!=null) world.getRelation().add(decl);
	}
		
	/**
	 * Final additions.
	 */
	@SuppressWarnings("unchecked")
	public void finalAdditions()
	{	
		/* exists:>Object in subsortalNamesList[0] + subsortalNamesList[1] + ...
		 */
		ArrayList<EObject> subsSortalList = new ArrayList<EObject>();
		subsSortalList.addAll(ontoparser.getAllInstances(SubstanceSortal.class));
		if(options.identityPrinciple && subsSortalList.size() > 0)
		{
			AlloyUtil.createExistsCompareOperationInWorld(factory, exists, world, sigObject, ontoparser.getAlias(subsSortalList));
		}
		
		/* disj[ SubSortalNamesList[0], SubSortalNamesList[1], ... ],
		 */
		DisjointExpression disj = AlloyUtil.createDisjointExpression(factory, ontoparser.getAlias(subsSortalList));	
		if (disj!=null) world.getBlock().getExpression().add(disj);
		
		/* exists:>Object in objectNamesList[0] + objectNamesList[1] + ...
		 */
		ArrayList<EObject> objectClassesList = new ArrayList<EObject>();
		objectClassesList.addAll(ontoparser.getAllInstances(ObjectClass.class));		
		if(!options.identityPrinciple && objectClassesList.size() > 0)
		{
			AlloyUtil.createExistsCompareOperationInWorld(factory, exists, world, sigObject, ontoparser.getAlias(objectClassesList));
		}
		
		/* exists:>Property in propertyNamesList[0] + propertyNamesList[1] + ...
		 */
		ArrayList<EObject> momentClassesList = new ArrayList<EObject>();
		momentClassesList.addAll(ontoparser.getAllInstances(MomentClass.class));		
		if(momentClassesList.size() > 0)
		{
			AlloyUtil.createExistsCompareOperationInWorld(factory, exists, world, sigProperty, ontoparser.getAlias(momentClassesList));
		}
		
			
		/* fact relator_constraint {...}
		 */
		for (FactDeclaration f: relatorConstraintFactList)
		{
			module.getParagraph().add(f);
		}

		/* fact weak_supplementation {...}
		 */
		for (FactDeclaration f: weakSupplementationFactList)
		{
			module.getParagraph().add(f);
		}
						
		/* fun visible : World some -> some univ {	exists }
		 */
		AlloyUtil.createVisibleFunction(factory, module, world, exists);
				
		/*  run { } for 10 but 3 World
		 */
		AlloyUtil.createDefaultRunComand(factory, module);			
	}
	
	/**
	 * Transforms Derivation.
	 */
	@SuppressWarnings("unchecked")
	public void transformDerivations(Derivation d)
	{
		PredicateInvocation pI = TDerivationRule.createPredicateInvocation(ontoparser, factory, d);		
		if (pI!=null) derivations.getBlock().getExpression().add(pI);
		module.getParagraph().add(derivations);
	}
		
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Associations into Alloy. 
	 */
	@SuppressWarnings("unchecked")
	public void transformAssociations(Association ass) 
	{
		Variable var = factory.createVariable();		
		Declaration decl = factory.createDeclaration();
		UnaryOperation uOp = factory.createUnaryOperation();
		ArrowOperation aOp = factory.createArrowOperation();
		VariableReference source = factory.createVariableReference();
		VariableReference target = factory.createVariableReference();
		VariableReference association = factory.createVariableReference();
		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		var.setName(ontoparser.getAlias(ass)); 
		var.setDeclaration(decl);
		
		association.setVariable(ontoparser.getAlias(ass));	
		
		decl.setExpression(uOp);
		
		if(ass instanceof Characterization)

			// Characterization
			transformCharacterizationAssociation(ass, source, target, aOp);
				
		else if(ass instanceof Mediation)

			// Mediation
			transformMediationAssociation(ass, source, target, aOp);
		
		else if(ass instanceof Meronymic)
			
			// Meronymic
			transformMeronymicAssociation((Meronymic)ass, source, target, aOp);
		
		else if(!(ass instanceof Derivation))
			
			transformOtherAssociation(ass, source, target, aOp);
		
		if(ass.getMemberEnd().get(0).getName() != null || ass.getMemberEnd().get(1).getName() != null)
			
			// Association Ends
			transformAssociationsEnds(ass,source,target);		
		
		uOp.setExpression(aOp);
		world.getRelation().add(decl);	
		
	}

	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Association Ends into Alloy Module.
	 */
	private void transformAssociationsEnds(Association ass, VariableReference source, VariableReference target) 
	{
		if(ass.getMemberEnd().get(0).getName() != null)
			
		if(ass.getMemberEnd().get(0).getName().compareTo("") != 0)	
		{
			RefOntoUML.Type paramType = ass.getMemberEnd().get(1).getType();
			RefOntoUML.Type returnType = ass.getMemberEnd().get(0).getType();
			
			String functionName = ontoparser.getAlias(ass.getMemberEnd().get(0));			
			String returnName = new String();
			returnName = ontoparser.getAlias(returnType);			
			String paramName = new String();
			paramName = ontoparser.getAlias(paramType);			
			String assocName = ontoparser.getAlias(ass);
			
			if (!(paramType instanceof DataType)) paramName="World."+ontoparser.getAlias(paramType);
			if (!(returnType instanceof DataType)) returnName="World."+ontoparser.getAlias(returnType);
			
			FunctionDeclaration fun = 
					AlloyUtil.createFunctionDeclaration(factory, world, target, functionName, paramName, returnName, assocName);
												
			module.getParagraph().add(fun);			
		}
		
		if(ass.getMemberEnd().get(1).getName() != null)
			
		if(ass.getMemberEnd().get(1).getName().compareTo("") != 0)
		{
			RefOntoUML.Type paramType = ass.getMemberEnd().get(0).getType();
			RefOntoUML.Type returnType = ass.getMemberEnd().get(1).getType();
			
			String functionName = ontoparser.getAlias(ass.getMemberEnd().get(1));			
			String returnName = new String();
			returnName = ontoparser.getAlias(returnType);			
			String paramName = new String();
			paramName = ontoparser.getAlias(paramType);
			String assocName = ontoparser.getAlias(ass);
			
			if (!(paramType instanceof DataType)) paramName="World."+ontoparser.getAlias(paramType);
			if (!(returnType instanceof DataType)) returnName="World."+ontoparser.getAlias(returnType);
			
			FunctionDeclaration fun = 
					AlloyUtil.createFunctionDeclaration(factory, world, target, functionName, paramName, returnName, assocName);
												
			module.getParagraph().add(fun);			
		}	
	}
	
	/* =========================================================================================================*/
	
	/** 
	 * Transforms Meronymic Association into Alloy.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void transformMeronymicAssociation(Meronymic ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int count = 1;
		
		for(Property prop : ass.getMemberEnd())
		{			
			if(!prop.getAggregation().equals(AggregationKind.NONE) && count==1)
			{
				source.setVariable(ontoparser.getAlias(prop.getType()));				
				lowerSource = prop.getLower();
				upperSource = prop.getUpper();
				count++;
			}
			else
			{
				target.setVariable(ontoparser.getAlias(prop.getType()));				
				lowerTarget = prop.getLower();				
				upperTarget = prop.getUpper();
			}
		}
	
		if(ass instanceof subQuantityOf)
		{
			PredicateInvocation pI = 
					AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getAlias(ass));
			
			association_properties.getBlock().getExpression().add(pI);
			
			if(ass.sourceEnd().isIsReadOnly() || ass.isIsInseparable() || ass.isIsImmutableWhole())
			{
				PredicateInvocation pIS = 
						AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getAlias(ass));
				
				association_properties.getBlock().getExpression().add(pIS);
			}
		}
		else
		{
			if(ass.targetEnd().isIsReadOnly() || ass.isIsEssential() || ass.isIsImmutablePart())
			{
				PredicateInvocation pI = 
					AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getAlias(ass));
				
				association_properties.getBlock().getExpression().add(pI);
			}
			
			if(ass.sourceEnd().isIsReadOnly() || ass.isIsInseparable() || ass.isIsImmutableWhole())
			{
				PredicateInvocation pI = 
					AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getAlias(ass));
				
				association_properties.getBlock().getExpression().add(pI);
			}
		}
					
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ontoparser.getAlias(ass));
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms Mediation Association into Alloy.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void transformMediationAssociation(Association ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int cont = 1;
		boolean isSourceReadOnly = false;
		
		for(Property c : ass.getMemberEnd())
		{
			if(c.getType() instanceof Class)
			{
				if(c.getType() instanceof Relator && cont == 1)
				{
					source.setVariable(ontoparser.getAlias(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(ontoparser.getAlias(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = 
				AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getAlias(ass));
		
		association_properties.getBlock().getExpression().add(pI);
		
		if(isSourceReadOnly)
		{
			PredicateInvocation pIS = 
				AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getAlias(ass));
			
			association_properties.getBlock().getExpression().add(pIS);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ontoparser.getAlias(ass));
	}
	
	/* =========================================================================================================*/
	
	/** 
	 * Transforms Characterization Association into Alloy.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void transformCharacterizationAssociation(Association ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int cont = 1;
		boolean isSourceReadOnly = false;
		
		for(Property c : ass.getMemberEnd())
		{
			if(c.getType() instanceof Class)
			{
				if(c.getType() instanceof Mode && cont == 1)
				{
					source.setVariable(ontoparser.getAlias(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(ontoparser.getAlias(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = 
				AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getAlias(ass));
		
		association_properties.getBlock().getExpression().add(pI);
		
		if(isSourceReadOnly)
		{
			PredicateInvocation pIS = 
					AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getAlias(ass));
			
			association_properties.getBlock().getExpression().add(pIS);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ontoparser.getAlias(ass));
	}
	
	/* =========================================================================================================*/
	
	/** 
	 * Transforms Formal, Material and DataTypeRelationships into Alloy.
	 */
	@SuppressWarnings("unchecked")
	private void transformOtherAssociation(Association ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int cont = 1;
		boolean isTargetReadOnly = false, isSourceReadOnly = false;
		
		if(ass instanceof Derivation)
			return;
		
		for(Property c : ass.getMemberEnd())
		{
			if(c.getType() instanceof Classifier)
			{
				if(cont == 1)
				{
					source.setVariable(ontoparser.getAlias(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(ontoparser.getAlias(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
					isTargetReadOnly = c.isIsReadOnly();
				}
			}
		}
		
		if(isTargetReadOnly)
		{
			PredicateInvocation pI = 
					AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getAlias(ass));
			
			association_properties.getBlock().getExpression().add(pI);			
		}
		
		if(isSourceReadOnly)
		{
			PredicateInvocation pIS = 
					AlloyUtil.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getAlias(ass));
			
			association_properties.getBlock().getExpression().add(pIS);			
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ontoparser.getAlias(ass));
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Set cardinalities of the Arrow Operations according to OntoUML Associations.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void setCardinalities(ArrowOperation aOp, int lowerSource, int upperSource, int lowerTarget, int upperTarget, 
	VariableReference source, VariableReference target, String assocName) 
	{
		// Source Cardinality
		if(lowerSource == 1 && upperSource == 1) aOp.setLeftMultiplicity(Multiplicity.ONE_LITERAL);		
		else 
		{
			if(lowerSource == 0 && upperSource == 1) aOp.setLeftMultiplicity(Multiplicity.LONE_LITERAL);
			else
			{
				if(lowerSource >= 1) aOp.setLeftMultiplicity(Multiplicity.SOME_LITERAL);
				else aOp.setLeftMultiplicity(Multiplicity.SET_LITERAL);
			}
		}
		
		// Target Cardinality
		if(lowerTarget == 1 && upperTarget == 1) aOp.setRightMultiplicity(Multiplicity.ONE_LITERAL);
		else
		{
			if(lowerTarget == 0 && upperTarget == 1) aOp.setRightMultiplicity(Multiplicity.LONE_LITERAL);
			else
			{
				if(lowerTarget >= 1) aOp.setRightMultiplicity(Multiplicity.SOME_LITERAL);
				else aOp.setRightMultiplicity(Multiplicity.SET_LITERAL);
			}
		}
		
		// Cardinalities that are different of 1, 0 or *		
		
		if (lowerSource > 1)
		{
			BinaryOperation binJoin = 
					AlloyUtil.createBinaryOperation(factory, assocName, BinaryOperator.JOIN_LITERAL, "x");
			
			QuantificationExpression qe =					
				AlloyUtil.createQuantificationExpression(factory, target, binJoin, CompareOperator.GREATER_EQUAL_LITERAL, lowerSource);
			
			world.getBlock().getExpression().add(qe);
		}
		
		if (upperSource > 1 && upperSource != -1) 
		{
			BinaryOperation binJoin = 
				AlloyUtil.createBinaryOperation(factory,assocName, BinaryOperator.JOIN_LITERAL,"x");
			
			QuantificationExpression qe =					
				AlloyUtil.createQuantificationExpression(factory, target, binJoin, CompareOperator.LESS_EQUAL_LITERAL, upperSource);
				
			world.getBlock().getExpression().add(qe);
		}
		
		if (lowerTarget > 1) 
		{
			BinaryOperation binJoin = 
					AlloyUtil.createBinaryOperation(factory,"x", BinaryOperator.JOIN_LITERAL,assocName);
			
			QuantificationExpression qe =					
				AlloyUtil.createQuantificationExpression(factory, source, binJoin, CompareOperator.GREATER_EQUAL_LITERAL, lowerTarget);
				
			world.getBlock().getExpression().add(qe);
		}
		
		if (upperTarget > 1 && upperTarget != -1) 
		{
			BinaryOperation binJoin = 
					AlloyUtil.createBinaryOperation(factory,"x", BinaryOperator.JOIN_LITERAL,assocName);			
			
			QuantificationExpression qe =					
				AlloyUtil.createQuantificationExpression(factory, source, binJoin, CompareOperator.LESS_EQUAL_LITERAL, upperTarget);
				
			world.getBlock().getExpression().add(qe);		
		}
	}	

}
