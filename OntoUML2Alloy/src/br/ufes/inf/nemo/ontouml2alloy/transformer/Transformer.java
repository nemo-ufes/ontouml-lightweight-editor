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
import java.util.HashMap;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Type;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.Multiplicity;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.UnaryOperator;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.ontouml2alloy.Options;
import br.ufes.inf.nemo.ontouml2alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ontouml2alloy.api.OntoUMLAPI;
import br.ufes.inf.nemo.ontouml2alloy.parser.Parser;

/**
 *	This class is used to transform every element of the model into alloy. 
 *	First, is created an alloy module skeleton as a base for transformation.
 *  Then, every element of the model is transformed. (i.e. Classifiers, Generalizations, 
 *  GenearlizationSets and Associations). 
 *  
 * 	@authors Tiago Sales, John Guerson and Lucas Thom
 */

public class Transformer {
		
	/** 
	 *  Provide the ontouml model elements.
	 *  It is also used for associate the elements of the ontouml model 
	 *  with their modified names (i.e. without special characters: #, !, @, $, %, and etc...). 
	 */
	public Parser ontoparser;
			
	/**
	 * Transformation Options.
	 */
	public Options options;
	
	/** 
	 * Alloy instance. 
	 */
	public AlloyFactory factory = AlloyFactory.eINSTANCE;

	/* =========================================================================================================*/

	// initialized by method initialAditions() :
	
	/** Alloy Module. */
	public AlloyModule module;
	
	/** Alloy Signature World. */
	private SignatureDeclaration world;
	
	/** Alloy World Field exists. */
	private Variable exists;
	
	/** Alloy association_properties Fact Declaration. */
	private FactDeclaration association_properties;

	/** Alloy derivation Fact Declaration. */
	private FactDeclaration derivations;

	/* =========================================================================================================*/

	// initialized by initializeDefaultSignatures() method 
	
	/** Alloy Default Signatures Names. */
	private ArrayList<String> defaultSignatures = new ArrayList<String>();

	/** Alloy Default Signature : sig Object{}. */
	private SignatureDeclaration sigObject;
	
	/** Alloy Default Signature : sig Property{}. */
	private SignatureDeclaration sigProperty;
	
	/** Alloy Default Signature : sig DataType{}. */
	private SignatureDeclaration sigDatatype;
	
	/* =========================================================================================================*/
	
	// initialized by initializeNamesList() method
	
	/** List containing all the Object Class names. */
	private ArrayList<String> objectNamesList = new ArrayList<String>();	
	
	/** List containing all the Moment Class names. */
	private ArrayList<String> propertyNamesList = new ArrayList<String>();
	
	/** List containing all the DataTypes names. */
	private ArrayList<String> datatypeNamesList = new ArrayList<String>();
	
	/** List containing all the names of Substances Sortals that are disjoint. */
	private ArrayList<String> subsortalDisjNamesList = new ArrayList<String>();
	
	/** List containing all the names of Moment Classes that are disjoint. */
	private ArrayList<String> propertyDisjNamesList = new ArrayList<String>();
	
	/** List containing all the names of DataTypes that are disjoint. */
	private ArrayList<String> datatypeDisjNamesList = new ArrayList<String>();
		
	/** List containing all the rigid classifiers. */
	private ArrayList<Classifier> rigidElementsList = new ArrayList<Classifier>();
	
	/** List containing all the top level classifiers. */
	private ArrayList<Classifier> topLevelElementsHashMap = new ArrayList<Classifier>();
	
	/* =========================================================================================================*/
	
	// initialized by transformClassifier()
	
	/** List containing all the facts for relator's rule. */
	private ArrayList<FactDeclaration> relatorConstraintFactList = new ArrayList<FactDeclaration>();

	/** List containing all the facts for weak supplementation's rule. */
	private ArrayList<FactDeclaration> weakSupplementationFactList = new ArrayList<FactDeclaration>();
	
	/* =========================================================================================================*/
	
	/**
	 * Constructor().
	 */
	public Transformer (Parser parser, Options opt)
	{
		ontoparser = parser;
		
		options = opt;
		
		initializeDefaultSignatures();
		
		initializeNamesLists();
	}
			
	/* =========================================================================================================*/
	
	/**
	 * Initialize Names Lists.
	 */
	private void initializeNamesLists()
	{
		for (PackageableElement pe : ontoparser.getPackageableElements())
		{			
			if (pe instanceof ObjectClass) 
			{
				objectNamesList.add(ontoparser.getName(pe));

				if((pe instanceof Kind) || (pe instanceof Collective) || (pe instanceof Quantity))
				{
					subsortalDisjNamesList.add(ontoparser.getName(pe));
				}
			}
			if (pe instanceof MomentClass) 
			{
				propertyNamesList.add(ontoparser.getName(pe));
				
				// all Properties without fathers are naturally disjoint, 
				// which means that multiple inheritance between Properties isn't allowed.
				
				if(((MomentClass)pe).getGeneralization().size() == 0)
				{
					propertyDisjNamesList.add(ontoparser.getName(pe));
				}
			}
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) 
			{
				datatypeNamesList.add(ontoparser.getName(pe));
				
				// all dataTypes without fathers are naturally disjoint, 
				// which means that multiple inheritance between dataTypes isn't allowed.
				
				if(((DataType)pe).getGeneralization().size() == 0)
				{
					datatypeDisjNamesList.add(ontoparser.getName(pe));
				}
			}
			if ( (pe instanceof RigidSortalClass) || (pe instanceof Category) || (pe instanceof MomentClass) || ((pe instanceof DataType)&&!(pe instanceof PrimitiveType)) ) 
			{ 
				rigidElementsList.add((Classifier)pe); 
			}
			
			if ( pe instanceof Class)
			{				
				if (OntoUMLAPI.isTopLevel((Classifier)pe)) topLevelElementsHashMap.add((Classifier)pe);
			}
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Initialize Default Signatures (i.e. Object, Property and DataType).
	 */
	private void initializeDefaultSignatures ()
	{
		for (PackageableElement pe : ontoparser.getPackageableElements())
		{			
			if (pe instanceof ObjectClass) 
			{
				defaultSignatures.add("Object");
				
				sigObject = factory.createSignatureDeclaration();
				sigObject.setName("Object");
				
				break;
			}
		}
		for (PackageableElement pe : ontoparser.getPackageableElements())
		{			
			if (pe instanceof MomentClass) 
			{
				defaultSignatures.add("Property");
				
				sigProperty = factory.createSignatureDeclaration();
				sigProperty.setName("Property");
				
				break;
			}
		}
		for (PackageableElement pe : ontoparser.getPackageableElements())
		{			
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) 
			{
				defaultSignatures.add("DataType");
				
				sigDatatype = factory.createSignatureDeclaration();
				sigDatatype.setName("DataType");
												
				break;
			}
		}
	}
	
	/* =========================================================================================================*/

	/**
	 *	Creates a skeleton Alloy Module as Initial Additions.
	 */
	@SuppressWarnings("unchecked")
	public void initialAditions() 
	{		
		module = factory.createAlloyModule();
		module.setName(ontoparser.getModelName());				
	
		// abstract sig World {}
		
		world = AlloyAPI.createSigWorld(factory);
		
		// open world_structure[World]
		// open ontological_propertis[World]
		
		ModuleImportation mi1 = AlloyAPI.createModuleImport(factory,"world_structure","", world);		
		ModuleImportation mi2 = AlloyAPI.createModuleImport(factory,"ontological_properties","", world);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		
		// sig Object{}
		// sig Property{} 
		// sig DataType{}
		
		AlloyAPI.createDefaultSignatures(factory,module,defaultSignatures);
		
		// exists: some Object+Property+DataType,
		
		exists = AlloyAPI.createExistsVariableDeclaration(factory,defaultSignatures);
		
		world.getRelation().add(exists.getDeclaration());
		
		module.getParagraph().add(world);
						
		// fact additional_facts {}
		
		FactDeclaration additional_facts = factory.createFactDeclaration();
		additional_facts.setName("additional_facts");
		Block block = factory.createBlock();
		additional_facts.setBlock(block);

		// linear_existence[exists]
		
		PredicateInvocation pI = AlloyAPI.createLinearExistenceInvocation(factory,exists);
		block.getExpression().add(pI);
		
		// all_elements_exists[Object+Property+DataType,exists]
		
		PredicateInvocation pI2 = AlloyAPI.createAllElementsExistsInvocation(factory,exists,defaultSignatures);
		block.getExpression().add(pI2);
		
		if(sigDatatype != null) 
		{
			// always_exists[DataType,exists]
			
			PredicateInvocation pI3 = AlloyAPI.createAlwaysExistsInvocation(factory,exists,sigDatatype);
			block.getExpression().add(pI3);
		}
		
		module.getParagraph().add(additional_facts);
		
		// fact association_properties {}
		
		Block b1 = factory.createBlock();
		association_properties = factory.createFactDeclaration();
		association_properties.setName("association_properties");
		association_properties.setBlock(b1);
		
		module.getParagraph().add(association_properties);
		
		// fact derivations {}
		
		Block b2 = factory.createBlock();
		derivations = factory.createFactDeclaration();
		derivations.setName("derivations");
		derivations.setBlock(b2);
		
		module.getParagraph().add(derivations);				
	}
		
	/* =========================================================================================================*/
	
	/**
	 * Creates final additions to alloy code.
	 */
	public void finalAdditions()
	{		
		if(topLevelElementsHashMap.size() > 1)
			
			createTopLevelDisjointRule();
		
		if(options.identityPrinciple && subsortalDisjNamesList.size() > 0) 
			
			// exists:>Object in subsortalNamesList[0] + subsortalNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigObject, subsortalDisjNamesList);
		
		if(!options.identityPrinciple && objectNamesList.size() > 0)
			
			// exists:>Object in objectNamesList[0] + objectNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigObject, objectNamesList);
		
		if(propertyNamesList.size() > 0) 
		
			// exists:>Property in propertyNamesList[0] + propertyNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigProperty, propertyNamesList);
		
		if(datatypeNamesList.size() > 0) 
		
			// exists:>DataType in datatypeNamesList[0] + datatypeNamesList[1] + ...
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigDatatype, datatypeNamesList);
		
		// disj[ DisjNamesList[0], DisjNamesList[1], ... ],
		
		AlloyAPI.createDisjointExpressionInWorld(factory, world, subsortalDisjNamesList);	
		
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
			FactDeclaration all_rigid_classes = factory.createFactDeclaration();
			all_rigid_classes.setName("all_rigid_classes");
			all_rigid_classes.setBlock(factory.createBlock());
			
			for(Classifier rigid : rigidElementsList)
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
	 * Transforms OntoUML Classifiers into Alloy
	 */
	@SuppressWarnings("unchecked")
	public void transformClassifier(Classifier c) 
	{
		if(c instanceof ObjectClass)
		{
			// ObjectClassName: set exists:>Object,
			
			Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getName(c), sigObject.getName());				
			world.getRelation().add(decl);			

			if ((c instanceof RigidSortalClass) && (options.weakSupplementationConstraint))
			{				
				FactDeclaration fact = createWeakSupplementation(c);
				if (fact!=null) weakSupplementationFactList.add(fact);
					
			}
		}
		
		if(c instanceof DataType && !(c instanceof PrimitiveType))
		{			
			// DataTypeName: set exists:>DataType,
			
			Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getName(c), sigDatatype.getName());	
			world.getRelation().add(decl);									
		}
		
		if(c instanceof MomentClass)
		{
			// PropertyName: set exists:>Property,
			
			Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getName(c),sigProperty.getName());
			world.getRelation().add(decl);
			
			if ((c instanceof Relator) && (options.relatorConstraint))
			{
				FactDeclaration fact = createRelatorConstraintFact((Relator) c);				
				if (fact!= null) relatorConstraintFactList.add(fact);
			}									
		}
		
		if (c.isIsAbstract()) createAbstractClauseRule(c);
	}
	
	/* =========================================================================================================*/
	
	/**
	 *
	 * Relator Constraint.
	 * 
	 * all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2
	 */
	@SuppressWarnings("unchecked")
	private FactDeclaration createRelatorConstraintFact(Relator c) 
	{
		if (c.isIsAbstract()) return null;
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if ( OntoUMLAPI.isAbstractFromGeneralizationSets(ontoparser,c)) return null;
		
		if (! OntoUMLAPI.hasMediationRelation(ontoparser,c)) return null;
		
		// get all 'c' mediations
		ArrayList<String> associationNames = new ArrayList<String>();		
		OntoUMLAPI.getAllMediationsNames(ontoparser,associationNames, c);		
		
		if(associationNames.size()>0)
		{			
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyAPI.createQuantificationExpression(factory,associationNames, ontoparser.getName(c));
			
			// create fact from q
			FactDeclaration RelatorRuleFact = factory.createFactDeclaration();
			RelatorRuleFact.setName("relator_constraint_"+ontoparser.getName(c));
			RelatorRuleFact.setBlock(factory.createBlock());			
			RelatorRuleFact.getBlock().getExpression().add(q);
			
			return RelatorRuleFact;						
		}		
		
		return null;
	}
	

	/* =========================================================================================================*/
	
	/**
	 * Weak Supplementation.
  	 *
  	 * all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2
	 */
	@SuppressWarnings("unchecked")
	private FactDeclaration createWeakSupplementation(Classifier c) 
	{
		if (c.isIsAbstract()) { return null; }
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if (OntoUMLAPI.isAbstractFromGeneralizationSets(ontoparser,c)) { return null; }	
		
		if (! OntoUMLAPI.hasMeronymicRelation(ontoparser,c)) { return null; } 
				
		// get all 'c' meronymics
		ArrayList<String> associationNames = new ArrayList<String>();		
		OntoUMLAPI.getAllMeronymics(ontoparser,associationNames, (RigidSortalClass)c);	
		
		if( associationNames.size() > 0)
		{
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyAPI.createQuantificationExpression(factory,associationNames, ontoparser.getName(c));
			
			// create fact from q
			FactDeclaration WeakSupplementationFact = factory.createFactDeclaration();
			WeakSupplementationFact.setName("weak_supplementation_"+ontoparser.getName(c));
			WeakSupplementationFact.setBlock(factory.createBlock());			
			WeakSupplementationFact.getBlock().getExpression().add(q);			
			
			return WeakSupplementationFact;					
		}	
		return null;
	}

	/* =========================================================================================================*/

	/**
	 * Create Abstract Clause Rule.
	 * 
	 * BinaryOperation with union operator(+) to represent the completeness
	 * between abstract father(Classifiers) and his concrete childs.
	 * 
	 * "abstract_father = concrete_child1 + concrete_child2 + concrete_child3 + ..." 
	 */
	@SuppressWarnings("unchecked")
	private void createAbstractClauseRule(Classifier c) 
	{		
		ArrayList<Classifier> concretes = new ArrayList<Classifier>();
		
		OntoUMLAPI.getConcreteDescendants(ontoparser, concretes, c);
		
		int cont = 1;		
		BinaryOperation bo = factory.createBinaryOperation();
		if(concretes.size() > 0)
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(ontoparser.getName(c));
			
			co.setLeftExpression(vr);
			for(Classifier classifier : concretes)
			{
				if(concretes.size() == 1) 
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));					
					co.setRightExpression(vr);
					break;
				}
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != concretes.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == concretes.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(classifier));
					bo.setRightExpression(vr);
				}
				cont++;
			}
			world.getBlock().getExpression().add(co);
		}
	}	
		
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Generalizations into Alloy.
	 * 
	 * The child Classifier is a subset of the father Classifier. 
	 * 
	 * "child in father"
	 */
	@SuppressWarnings("unchecked")
	public void transformGeneralizations(Generalization g) 
	{
		CompareOperation co = AlloyAPI.createCompareOperation( factory, 
				ontoparser.getName(g.getSpecific()), 
				CompareOperator.SUBSET_LITERAL, 
				ontoparser.getName(g.getGeneral())
		);
		world.getBlock().getExpression().add(co);
	}
				
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Generalizations Sets into Alloy.
	 * 
	 * Father = Child1 + Child2 + Child3 + ...
	 * 
	 * disj[Child1, Child2, Child3,...]
	 * 	
	 */
	@SuppressWarnings("unchecked")
	public void transformGeneralizationSets(GeneralizationSet gs) 
	{
		if(gs.isIsCovering())
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(ontoparser.getName(gs.getGeneralization().get(0).getGeneral()));
			
			co.setLeftExpression(vr);
			
			int cont = 1;
			BinaryOperation bo = factory.createBinaryOperation();
			for(Generalization gen : gs.getGeneralization())
			{
				if(gs.getGeneralization().size() == 1)
					break;
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(ontoparser.getName(gen.getSpecific()));
					bo.setRightExpression(vr);
				}
				cont++;
			}
			world.getBlock().getExpression().add(co);			
		}
		if(gs.isIsDisjoint())
		{
			DisjointExpression disj = factory.createDisjointExpression();
			for(Generalization gen : gs.getGeneralization())
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(ontoparser.getName(gen.getSpecific()));
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
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
		int count = 0;
		
		for(Property prop : ass.getMemberEnd())
		{			
			if(count == 0)
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
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Derivation Associations into Alloy.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void transformDerivations(Derivation d)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("derivation");
		
		// Get Material Association
		VariableReference vrMaterial = factory.createVariableReference();
		MaterialAssociation material = null;		
		for(Property prop : d.getMemberEnd())
		{
			if(prop.getType() instanceof MaterialAssociation)
			{
				vrMaterial.setVariable(ontoparser.getName(prop.getType()));
				material = (MaterialAssociation)prop.getType();
			}
		}		
				
		// Get Source and Target of Material Association
		Type sourceType= null;
		Type targetType = null;
		int cont = 1;		
		for(Property prop : material.getMemberEnd())
		{
			if(prop.getType() instanceof Class)
			{
				if(cont==1) {
					sourceType = prop.getType();
					cont++;
				} else {
					targetType = prop.getType();
				}
			}
		}
						
		// Get Relator
		Relator derRelator = (Relator) (d.relator() instanceof Relator ? d.relator() : d.material());
		
		VariableReference mediation1 = factory.createVariableReference();
		VariableReference mediation2 = factory.createVariableReference();
		
		// Get Mediations
		ArrayList<Mediation> mediations = new ArrayList<Mediation>();
		OntoUMLAPI.getAllMediations(ontoparser, mediations, derRelator);
				
		// Get the two first mediations related with source and target of the material relation
		int cont1=1;
		for (Mediation med: mediations)
		{
			int cont2=1;			
			Type mediated = null;
			for(Property prop : med.getMemberEnd())
			{	
				if(prop.getType() instanceof Relator && cont2==1)
				{											
					cont2++;
				}else{
					mediated = prop.getType();
				}
			}
			
			if(mediated == sourceType || mediated == targetType)
			{
				if(cont1 == 1)
				{						
					mediation1.setVariable(ontoparser.getName(med));
					cont1++;						
				}
				else
				{					
					mediation2.setVariable(ontoparser.getName(med));						
					break;
				}
			}			
		}	
		
		pI.getParameter().add(vrMaterial);
		pI.getParameter().add(mediation1);
		pI.getParameter().add(mediation2);
		
		derivations.getBlock().getExpression().add(pI);
	}	
	
	/* =========================================================================================================*/
	
	/**
	 * Create Rule for Top Levels Classifiers in the model. 
	 */
	private void createTopLevelDisjointRule ()
	{
		HashMap< ArrayList<Classifier>, Integer > listsHashMap = new HashMap< ArrayList<Classifier>,Integer >();
		
		for (Classifier c1: topLevelElementsHashMap)
		{
			ArrayList<Classifier> descendants1 = new ArrayList<Classifier>();
			OntoUMLAPI.getDescendants(ontoparser, descendants1, c1);

			// creates a single List containing the topLevel classifier 'c1' 
			// and the top levels classifiers that have their descendants overlapping 
			// with the descendants of the classifier 'c1'
			
			ArrayList<Classifier> singleList = new ArrayList<Classifier>();
			singleList.add(c1);
			
			for (Classifier c2: topLevelElementsHashMap)
			{
				if (!c2.equals(c1)) 
				{
					ArrayList<Classifier> descendants2 = new ArrayList<Classifier>();
					OntoUMLAPI.getDescendants(ontoparser, descendants2, c2);
										
					Classifier overlap = OntoUMLAPI.isOverlapping(descendants1, descendants2);
					if (overlap != null) singleList.add(c2);						
				}
			}
			
			listsHashMap.put(singleList,0);			
		}
		
		// Now we remove lists that overlapping with others 
		for (ArrayList<Classifier> l1 : listsHashMap.keySet())
		{
			if (listsHashMap.get(l1)==0) 
			{
				for (ArrayList<Classifier> l2 : listsHashMap.keySet())
				{
					if(!l2.equals(l1) && listsHashMap.get(l2)==0)
					{
						Classifier overlap = OntoUMLAPI.isOverlapping(l1, l2);
						if (overlap!=null)
						{
							if(l1.size() < l2.size()) listsHashMap.put(l1,1);
							else listsHashMap.put(l2, 1);
						}					
					}
				}			
			}
		}

		// create a union (+) String expression for every singleList that has the value equal to 0
		ArrayList<String> exprList = new ArrayList<String>();
		for (ArrayList<Classifier> singleList : listsHashMap.keySet())
		{
			if (listsHashMap.get(singleList)==0)
			{
				int count = 0;
				String expr = new String();
				for(Classifier c: singleList) 
				{
					if (count==0) { expr = ontoparser.getName(c); count++; }
					else expr += "+"+ontoparser.getName(c);
				}
				exprList.add(expr);
			}			
		}
		
		//create Top Level Disjoint Rule
		AlloyAPI.createDisjointExpressionInWorld(factory, world, exprList);
	}
	
}
