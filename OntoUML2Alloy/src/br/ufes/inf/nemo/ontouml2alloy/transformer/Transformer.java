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

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
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
import br.ufes.inf.nemo.ontouml2alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;
import br.ufes.inf.nemo.ontouml2alloy.rules.TAbstractClauseRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TDerivationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TGeneralizationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TGeneralizationSetRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TRelatorRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TTopLevelRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TWeakSupplementationRule;

/**
 *	This class is used to transform every element of the model into alloy. 
 *	First, is created an alloy module skeleton as a base for transformation.
 *  Then, every element of the model is transformed. (i.e. Classifiers, Generalizations, 
 *  GenearlizationSets and Associations). 
 *  
 * 	@authors Tiago Sales, John Guerson and Lucas Thom
 */

public class Transformer extends BaseTransformer {
		
	/** 
	 *  Provide the ontouml model elements.
	 *  It is also used for associate the elements of the ontouml model 
	 *  with their modified names (i.e. without special characters: #, !, @, $, %, and etc...). 
	 */
	public OntoUMLParser ontoparser;
			
	/**
	 * Alloy Factory Instance.
	 */
	public AlloyFactory factory;
	
	/**
	 * Transformation Options.
	 */
	public OntoUMLOptions options;
		
	/**
	 * Constructor().
	 */
	public Transformer (OntoUMLParser parser, AlloyFactory factory, OntoUMLOptions opt)
	{		
		super(parser,factory);
		
		this.ontoparser = parser;		
		
		this.factory = factory;
		
		this.options = opt;		
	}
			
	/* =========================================================================================================*/
	
	// initialized by transformClassifier()
	
	/** List containing all the facts for relator's rule. */
	private ArrayList<FactDeclaration> relatorConstraintFactList = new ArrayList<FactDeclaration>();

	/** List containing all the facts for weak supplementation's rule. */
	private ArrayList<FactDeclaration> weakSupplementationFactList = new ArrayList<FactDeclaration>();
	
	/**
	 * Creates final additions to alloy code.
	 */
	@SuppressWarnings("unchecked")
	public void finalAdditions()
	{		
		if(topLevelElementsList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TTopLevelRule.createTopLevelDisjointRules(ontoparser, factory, topLevelElementsList);
			
			for (DisjointExpression disj : rulesList) 
			{ 
				world.getBlock().getExpression().add(disj); 
			}
		}
		
		if(options.identityPrinciple && subsortalDisjNamesList.size() > 0) 
			
			// exists:>Object in subsortalNamesList[0] + subsortalNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigObject, subsortalDisjNamesList);
		
		if(!options.identityPrinciple && objectNamesList.size() > 0)
			
			// exists:>Object in objectNamesList[0] + objectNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigObject,objectNamesList);
		
		if(propertyNamesList.size() > 0) 
		
			// exists:>Property in propertyNamesList[0] + propertyNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigProperty, propertyNamesList);
		
		if(datatypeNamesList.size() > 0) 
		
			// exists:>DataType in datatypeNamesList[0] + datatypeNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigDatatype, datatypeNamesList);
		
		// disj[ DisjNamesList[0], DisjNamesList[1], ... ],
		
		DisjointExpression disj = AlloyAPI.createDisjointExpression(factory, subsortalDisjNamesList);	
		if (disj!=null) world.getBlock().getExpression().add(disj);
		
		// not needed anymore....
		//AlloyAPI.createDisjointExpressionInWorld(factory, world, datatypeDisjNamesList);
		
		// not needed anymore....
		//AlloyAPI.createDisjointExpressionInWorld(factory, world, propertyDisjNamesList);		
		
		// fact relator_constraint { } 
		
		for (FactDeclaration f: relatorConstraintFactList)
		{
			module.getParagraph().add(f);
		}

		// fact weak_supplementation { }
		
		for (FactDeclaration f: weakSupplementationFactList)
		{
			module.getParagraph().add(f);
		}
						
		// fun visible : World some -> some univ {	exists }
		
		AlloyAPI.createVisibleFunction(factory, module, world, exists);

		// fact all_rigid_classes { rigidity[...] }
		
		createAllRigidClassesFacts();	
		
		// fact all_antirigid_classes { antirigidity[...] }
		
		if (options.antiRigidity) createAllAntiRigidClassesFacts();
		
		//  run { } for 10 but 3 World
		
		AlloyAPI.createDefaultRunComand(factory, module);			
	}
	
	/* =========================================================================================================*/
	
	/**
	 * 	Creates " all_rigid_classes" Fact Declaration in Alloy.
	 *  
	 *  fact all_rigid_classes { ... }			
	 */
	@SuppressWarnings("unchecked")
	private void createAllRigidClassesFacts() 
	{
		if(rigidElementsList.size()>0)
		{						
			for(Classifier rigid :rigidElementsList)
			{				
				if(rigid instanceof ObjectClass)
				{	
					// rigidity[ rigidClassName, Object, exists]
					
					PredicateInvocation pI = AlloyAPI.createRigidityInvocation(factory, sigObject, exists, ontoparser.getName(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}	
				if(rigid instanceof MomentClass)
				{
					// rigidity[ rigidClassName, Property, exists]
					
					PredicateInvocation pI = AlloyAPI.createRigidityInvocation(factory, sigProperty, exists, ontoparser.getName(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}
				if(rigid instanceof DataType && !(rigid instanceof PrimitiveType))
				{
					// rigidity[ rigidClassName, DataType, exists]
					
					PredicateInvocation pI = AlloyAPI.createRigidityInvocation(factory, sigDatatype, exists, ontoparser.getName(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}				
			}			
			module.getParagraph().add(all_rigid_classes);
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * 	Creates " all_antirigid_classes" Fact Declaration in Alloy.
	 *  
	 *  fact all_antirigid_classes { ... }			
	 */
	@SuppressWarnings("unchecked")
	private void createAllAntiRigidClassesFacts() 
	{
		if(antirigidElementsList.size()>0)
		{						
			for(Classifier antirigid : antirigidElementsList)
			{
				if(antirigid instanceof ObjectClass)
				{	
					// antirigidity[ antirigidClassName, Object, exists]
					
					PredicateInvocation pI = AlloyAPI.createAntiRigidityInvocation(factory,sigObject, exists, ontoparser.getName(antirigid));
					
					all_antirigid_classes.getBlock().getExpression().add(pI);
				}					
			}			
			module.getParagraph().add(all_antirigid_classes);
		}
	}
	
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Classifiers.
	 */
	@SuppressWarnings("unchecked")
	public void transformClassifier(Classifier c) 
	{
		if(c instanceof ObjectClass)
		{
			// ObjectClassName: set exists:>Object,
			
			Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getName(c), sigObject.getName());				
			
			if (decl!=null) world.getRelation().add(decl);			

			if ((c instanceof RigidSortalClass) && (options.weakSupplementationConstraint))
			{				
				// all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2
						
				FactDeclaration fact = TWeakSupplementationRule.createFactDeclaration(ontoparser, factory, c);
				
				if (fact!=null) weakSupplementationFactList.add(fact);					
			}
		}
		
		if(c instanceof DataType && !(c instanceof PrimitiveType))
		{			
			// DataTypeName: set exists:>DataType,
			
			Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getName(c), sigDatatype.getName());	
			
			if (decl!=null) world.getRelation().add(decl);									
		}
		
		if(c instanceof MomentClass)
		{
			// PropertyName: set exists:>Property,
			
			Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getName(c),sigProperty.getName());
			
			world.getRelation().add(decl);
			
			if ((c instanceof Relator) && (options.relatorConstraint))
			{
				// all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2
				
				FactDeclaration fact = TRelatorRule.createFactDeclaration(ontoparser, factory, (Relator)c);				
				
				if (fact!= null) relatorConstraintFactList.add(fact);
			}									
		}
		
		if (c.isIsAbstract()) 
		{
			// abstract_father = concrete_child1 + concrete_child2 + concrete_child3 + ...
			
			CompareOperation co = TAbstractClauseRule.createCompareOperation(ontoparser, factory, c);
			
			if (co!=null) world.getBlock().getExpression().add(co);			
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Generalizations.
	 */
	@SuppressWarnings("unchecked")
	public void transformGeneralizations(Generalization g) 
	{
		// child in father
		
		CompareOperation co = TGeneralizationRule.createCompareOperation(ontoparser, factory, g);
		
		if (co!=null) world.getBlock().getExpression().add(co);
	}
				
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Generalizations Sets.
	 */
	@SuppressWarnings("unchecked")
	public void transformGeneralizationSets(GeneralizationSet gs) 
	{
		if(gs.isIsCovering())
		{
			// Father = Child1 + Child2 + Child3 + ...
			
			CompareOperation co = TGeneralizationSetRule.createCompleteCompareOperation(ontoparser, factory, gs);
			
			if (co!=null) world.getBlock().getExpression().add(co);			
		}
		if(gs.isIsDisjoint())
		{
			// disj[Child1, Child2, Child3,...]
			
			DisjointExpression disj = TGeneralizationSetRule.createDisjointExpression(ontoparser, factory, gs);
						
			if (disj!=null) world.getBlock().getExpression().add(disj);
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Derivation Associations into Alloy.
	 */
	@SuppressWarnings("unchecked")
	public void transformDerivations(Derivation d)
	{
		PredicateInvocation pI = TDerivationRule.createPredicateInvocation(ontoparser, factory, d);		
		if (pI!=null) derivations.getBlock().getExpression().add(pI);		
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
		var.setName(ontoparser.getName(ass)); 
		var.setDeclaration(decl);
		
		association.setVariable(ontoparser.getName(ass));	
		
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
			String functionName = ontoparser.getName(ass.getMemberEnd().get(0));			
			String returnName = ontoparser.getName(ass.getMemberEnd().get(0).getType());			
			String paramName = ontoparser.getName(ass.getMemberEnd().get(1).getType());			
			String assocName = ontoparser.getName(ass);
			
			FunctionDeclaration fun = 
					AlloyAPI.createFunctionDeclaration(factory, world, target, functionName, paramName, returnName, assocName);
												
			module.getParagraph().add(fun);			
		}
		
		if(ass.getMemberEnd().get(1).getName() != null)
			
		if(ass.getMemberEnd().get(1).getName().compareTo("") != 0)
		{
			String functionName = ontoparser.getName(ass.getMemberEnd().get(1));			
			String returnName = ontoparser.getName(ass.getMemberEnd().get(1).getType());			
			String paramName = ontoparser.getName(ass.getMemberEnd().get(0).getType());			
			String assocName = ontoparser.getName(ass);
			
			FunctionDeclaration fun = 
					AlloyAPI.createFunctionDeclaration(factory, world, target, functionName, paramName, returnName, assocName);
												
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
				source.setVariable(ontoparser.getName(prop.getType()));				
				lowerSource = prop.getLower();
				upperSource = prop.getUpper();
				count++;
			}
			else
			{
				target.setVariable(ontoparser.getName(prop.getType()));				
				lowerTarget = prop.getLower();				
				upperTarget = prop.getUpper();
			}
		}
	
		if(ass instanceof subQuantityOf)
		{
			PredicateInvocation pI = 
					AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getName(ass));
			
			association_properties.getBlock().getExpression().add(pI);
			
			if(ass.sourceEnd().isIsReadOnly() || ass.isIsInseparable() || ass.isIsImmutableWhole())
			{
				PredicateInvocation pIS = 
						AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getName(ass));
				
				association_properties.getBlock().getExpression().add(pIS);
			}
		}
		else
		{
			if(ass.targetEnd().isIsReadOnly() || ass.isIsEssential() || ass.isIsImmutablePart())
			{
				PredicateInvocation pI = 
					AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getName(ass));
				
				association_properties.getBlock().getExpression().add(pI);
			}
			
			if(ass.sourceEnd().isIsReadOnly() || ass.isIsInseparable() || ass.isIsImmutableWhole())
			{
				PredicateInvocation pI = 
					AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getName(ass));
				
				association_properties.getBlock().getExpression().add(pI);
			}
		}
					
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
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
					source.setVariable(ontoparser.getName(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(ontoparser.getName(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = 
				AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getName(ass));
		
		association_properties.getBlock().getExpression().add(pI);
		
		if(isSourceReadOnly)
		{
			PredicateInvocation pIS = 
				AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getName(ass));
			
			association_properties.getBlock().getExpression().add(pIS);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
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
					source.setVariable(ontoparser.getName(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(ontoparser.getName(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = 
				AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getName(ass));
		
		association_properties.getBlock().getExpression().add(pI);
		
		if(isSourceReadOnly)
		{
			PredicateInvocation pIS = 
					AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getName(ass));
			
			association_properties.getBlock().getExpression().add(pIS);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
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
					source.setVariable(ontoparser.getName(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(ontoparser.getName(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
					isTargetReadOnly = c.isIsReadOnly();
				}
			}
		}
		
		if(isTargetReadOnly)
		{
			PredicateInvocation pI = 
					AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", source.getVariable(), ontoparser.getName(ass));
			
			association_properties.getBlock().getExpression().add(pI);			
		}
		
		if(isSourceReadOnly)
		{
			PredicateInvocation pIS = 
					AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_source", target.getVariable(), ontoparser.getName(ass));
			
			association_properties.getBlock().getExpression().add(pIS);			
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		
		// Set Cardinalities
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Set cardinalities of the Arrow Operations according to OntoUML Associations.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void setCardinalities(ArrowOperation aOp, int lowerSource, int upperSource, int lowerTarget, int upperTarget, 
	VariableReference source, VariableReference target, Association ass) 
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
					AlloyAPI.createBinaryOperation(factory, ontoparser.getName(ass), BinaryOperator.JOIN_LITERAL, "x");
			
			QuantificationExpression qe =					
				AlloyAPI.createQuantificationExpression(factory, target, binJoin, CompareOperator.GREATER_EQUAL_LITERAL, lowerSource);
			
			world.getBlock().getExpression().add(qe);
		}
		
		if (upperSource > 1 && upperSource != -1) 
		{
			BinaryOperation binJoin = 
				AlloyAPI.createBinaryOperation(factory,ontoparser.getName(ass), BinaryOperator.JOIN_LITERAL,"x");
			
			QuantificationExpression qe =					
				AlloyAPI.createQuantificationExpression(factory, target, binJoin, CompareOperator.LESS_EQUAL_LITERAL, upperSource);
				
			world.getBlock().getExpression().add(qe);
		}
		
		if (lowerTarget > 1) 
		{
			BinaryOperation binJoin = 
					AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN_LITERAL,ontoparser.getName(ass));
			
			QuantificationExpression qe =					
				AlloyAPI.createQuantificationExpression(factory, source, binJoin, CompareOperator.GREATER_EQUAL_LITERAL, lowerTarget);
				
			world.getBlock().getExpression().add(qe);
		}
		
		if (upperTarget > 1 && upperTarget != -1) 
		{
			BinaryOperation binJoin = 
					AlloyAPI.createBinaryOperation(factory,"x", BinaryOperator.JOIN_LITERAL,ontoparser.getName(ass));			
			
			QuantificationExpression qe =					
				AlloyAPI.createQuantificationExpression(factory, source, binJoin, CompareOperator.LESS_EQUAL_LITERAL, upperTarget);
				
			world.getBlock().getExpression().add(qe);		
		}
	}	

}
