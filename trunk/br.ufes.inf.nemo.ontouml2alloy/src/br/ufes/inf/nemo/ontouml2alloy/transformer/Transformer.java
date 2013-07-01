package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Meronymic;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.EnumDeclaration;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.Onto2AlloyOptions;
import br.ufes.inf.nemo.ontouml2alloy.rules.AbstractnessClassRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.CardinalityRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.DerivationRelationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.GeneralizationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.GeneralizationSetRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.RelatorAxiomRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TopLevelClassRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.WeakSupplementationAxiomRule;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyAPI;

/**
 * Performs the transformation from OntoUML to Alloy
 * 
 * @authors John Guerson, Tiago Prince and Lucas Thom
 *
 */

public class Transformer {
	
	public OntoUMLParser ontoparser;
	public Onto2AlloyOptions options;
	public AlloyFactory factory;	
	public AlloyModule module;
	
	public SignatureDeclaration world;	
	public Variable exists;	
	
	public SignatureDeclaration sigObject;	
	public SignatureDeclaration sigProperty;	
	public SignatureDeclaration sigDatatype;	
	public SignatureDeclaration sigString;	
	public SignatureDeclaration sigChar;	
	
	public ArrayList<SignatureDeclaration> dataTypesSignatures = new ArrayList<SignatureDeclaration>();	
	public ArrayList<EnumDeclaration> enumerationSignatures = new ArrayList<EnumDeclaration>();
	
	public FunctionDeclaration visibiliyFun;
	public FactDeclaration additionalFact;	
	public FactDeclaration dataTypeCompletenessFact;	
	public FactDeclaration dataTypeTopLevelFact;			
	public FactDeclaration association_properties;	
	public FactDeclaration derivations;	
	
	public ArrayList<FactDeclaration> rigidityFactList = new ArrayList<FactDeclaration>();	
	public ArrayList<FactDeclaration> antirigidityFactList = new ArrayList<FactDeclaration>();	
	public ArrayList<FactDeclaration> genSetFactList = new ArrayList<FactDeclaration>();	
	public ArrayList<FactDeclaration> genFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> relatorAxiomFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> weakSupplementationAxiomFactList = new ArrayList<FactDeclaration>();
	
	/**
	 * Constructor
	 */
	public Transformer (OntoUMLParser ontoparser, AlloyFactory factory, Onto2AlloyOptions options)
	{
		this.ontoparser = ontoparser;		
		this.factory = factory;	
		this.options = options;
	}
	
	/**
	 * Performs the Transformation!
	 */
	public void run ()
	{
		module = factory.createAlloyModule();
		module.setName(ontoparser.getModelName());				
	
		world = AlloyAPI.createSigWorld(factory);
				
		ModuleImportation mi1 = AlloyAPI.createModuleImport(factory,"world_structure","", world);		
		ModuleImportation mi2 = AlloyAPI.createModuleImport(factory,"ontological_properties","", world);
		ModuleImportation mi3 = AlloyAPI.createModuleImport(factory,"relation","util", null);
		ModuleImportation mi4 = AlloyAPI.createModuleImport(factory,"ternary","util", null);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		module.getImports().add(mi3);		
		module.getImports().add(mi4);
			
		sigObject = factory.createSignatureDeclaration();
		sigObject.setName("Object");
		module.getParagraph().add(sigObject);
		
		sigProperty = factory.createSignatureDeclaration();
		sigProperty.setName("Property");
		module.getParagraph().add(sigProperty);			

		sigDatatype = factory.createSignatureDeclaration();
		sigDatatype.setName("DataType");			
		module.getParagraph().add(sigDatatype);
				
		if (ontoparser.getAllInstances(PrimitiveType.class).size()>0)
		{
			for(PrimitiveType p: ontoparser.getAllInstances(PrimitiveType.class))
			{
				if (p.getName().compareToIgnoreCase("string")==0)
				{
					sigString = factory.createSignatureDeclaration();
					sigString.setName("String_");					
					module.getParagraph().add(sigString);					
				}				
			}
		}
		
		if (ontoparser.getAllInstances(PrimitiveType.class).size()>0)
		{
			for(PrimitiveType p: ontoparser.getAllInstances(PrimitiveType.class))
			{
				if (p.getName().compareToIgnoreCase("char")==0)
				{
					sigChar = factory.createSignatureDeclaration();
					sigChar.setName("Char");					
					module.getParagraph().add(sigChar);					
				}				
			}
		}
		
		for(DataType dt: ontoparser.getAllInstances(DataType.class))
		{
			if (!(dt instanceof PrimitiveType) && !(dt instanceof Enumeration))
			{
				SignatureDeclaration sigDt = factory.createSignatureDeclaration();
				sigDt.setName(ontoparser.getAlias(dt));
				sigDt.setInheritance(factory.createInheritance());
				sigDt.getInheritance().setIsSubset(true);
				sigDt.getInheritance().setSupertype(sigDatatype.getName());
				module.getParagraph().add(sigDt);
				dataTypesSignatures.add(sigDt);
			}
		}
		
		for (Enumeration e: ontoparser.getAllInstances(Enumeration.class))
		{
			EnumDeclaration sigEnumeration = factory.createEnumDeclaration();			
			sigEnumeration.setName(ontoparser.getAlias(e));
			for(EnumerationLiteral l: e.getOwnedLiteral())
			{
				sigEnumeration.getLiterals().add(ontoparser.getAlias(l));
			}
			module.getParagraph().add(sigEnumeration);
			enumerationSignatures.add(sigEnumeration);
		}			
		
		{
			ArrayList<String> existsSignatures = new ArrayList<String>();						
			if(ontoparser.getAllInstances(ObjectClass.class).size()>0) existsSignatures.add(sigObject.getName());
			if(ontoparser.getAllInstances(MomentClass.class).size()>0) existsSignatures.add(sigProperty.getName());			
			if (existsSignatures.size() > 0)
			{
				exists = AlloyAPI.createExistsDeclaration(factory,existsSignatures);			
				world.getRelation().add(exists.getDeclaration());
			}
		}
		module.getParagraph().add(world);		
		
		// ======================================================================================================
		// If there is no element in the model, the resultant code should be a skeleton created until this point.
		// ======================================================================================================
		
		if(ontoparser.getAllInstances(RefOntoUML.Class.class).size()==0 && ontoparser.getAllInstances(RefOntoUML.DataType.class).size()==0) 
		return;
		
		if(ontoparser.getAllInstances(RefOntoUML.Class.class).size()>0) populatesWihAdditionalFact();
		
		populatesWithClasses();
		populatesWithAttributes();
		
		populatesWithAxioms();

		populatesWithRigidity();		
		populatesWithAntiRigidity();
		
		populatesWithGeneralizations();		
		populatesWithGeneralizationSets();
		
		populatesWithDataTypeCompleteness();		
		populatesWithTopLevelDataTypeDisjointness();
		
		populatesElementsVisibility();
		
		populatesWithAssociationProperties();		
		populatesWithAssociations();		
		try { populatesWithDerivations(); } catch (Exception e1) { e1.printStackTrace(); }				
		populatesWithAssociationEnds();		
		populatesWithAssociationCardinalities();
				
		populatesWithCompleteness();
		
		populatesWithTopLevelClassDisjointness();
		
		CommandDeclaration cmd = AlloyAPI.createDefaultRunComand(factory, module);	
		module.getParagraph().add(cmd);				
	}
		
	/** 
	 * Populates With Classes 
	 */
	protected void populatesWithClasses()
	{
		for(RefOntoUML.Class c: ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{			
			// ObjectClassName: set exists:>Object,
			if(c instanceof ObjectClass)
			{
				Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(c), sigObject.getName());			
				if (decl!=null) world.getRelation().add(decl);			
			}
			
			// PropertyName: set exists:>Property,			 
			if(c instanceof MomentClass)
			{			
				Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(c),sigProperty.getName());			
				world.getRelation().add(decl);
			}
							
			// Abstract_Father = Concrete_Child1 + Concrete_Child2 + Concrete_Child3 + ...			 
			if (c.isIsAbstract())
			{
				CompareOperation co = AbstractnessClassRule.createCompareOperation(ontoparser, factory, c);			
				if (co!=null) world.getBlock().getExpression().add(co);			
			}			
		}
	}
	
	/**
	 * Populates With Attributes.
	 */
	protected void populatesWithAttributes()
	{
		for(Property attr: ontoparser.getAttributes())		
		{			
			if (attr.getType().getName()!=null) 
			{
				if (attr.getType() instanceof PrimitiveType)
				{
					// AttributeName: set ClassOwner -> Int,
					if (attr.getType().getName().compareToIgnoreCase("int")==0 || attr.getType().getName().compareToIgnoreCase("Integer")==0)
					{
						ArrowOperation aOp  = factory.createArrowOperation();
						aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,"Int",attr.getLower(),attr.getUpper());
						Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);	
						if (decl!=null) world.getRelation().add(decl);					
					}					
					
					// AttributeName: set ClassOwner -> String_,
					else if (attr.getType().getName().compareToIgnoreCase("String")==0)
					{
						ArrowOperation aOp  = factory.createArrowOperation();
						aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,"String_",attr.getLower(),attr.getUpper());
						Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);	
						if (decl!=null) world.getRelation().add(decl);					
					}
					
					// AttributeName: set ClassOwner -> Char,
					else if (attr.getType().getName().compareToIgnoreCase("Char")==0)
					{
						ArrowOperation aOp  = factory.createArrowOperation();
						aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,"Char",attr.getLower(),attr.getUpper());
						Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);
						if (decl!=null) world.getRelation().add(decl);					
					}
					
					else if (attr.getType().getName().compareToIgnoreCase("Boolean")==0)
					{
					//  AttributeName: set exists:> Object,
						if (attr.eContainer() instanceof ObjectClass)
						{
							Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(attr.eContainer()), sigObject.getName());
							if (decl!=null) world.getRelation().add(decl);
						}
						//  AttributeName: set exists:> Property,
						else if (attr.eContainer() instanceof MomentClass)
						{
							Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(attr.eContainer()), sigProperty.getName());
							if (decl!=null) world.getRelation().add(decl);
						}
					}
					
				}else{					
					ArrowOperation aOp  = factory.createArrowOperation();
					aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,ontoparser.getAlias(attr.getType()),attr.getLower(),attr.getUpper());
					Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);
					if (decl!=null) world.getRelation().add(decl);
				}
			}
		}
	}		
	
	
	/**
	 * Populates With Axioms
	 */
	protected void populatesWithAxioms()
	{
		for(RefOntoUML.Class c: ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			// fact weak_supplementation_constraint { all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2 }			 
			if ((c instanceof RigidSortalClass) && (options.weakSupplementationAxiom))
			{				
				FactDeclaration fact = WeakSupplementationAxiomRule.createFactDeclaration(ontoparser, factory, c);				
				if (fact!=null) {
					weakSupplementationAxiomFactList.add(fact);
					module.getParagraph().add(fact);
				}				
			}			
			
			// fact relator_constraint { all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2 }
			if ((c instanceof Relator) && (options.relatorAxiom))
			{			
				FactDeclaration fact = RelatorAxiomRule.createFactDeclaration(ontoparser, factory, (Relator)c);			
				if (fact!= null) {
					relatorAxiomFactList.add(fact);
					module.getParagraph().add(fact);
				}				
			}
		}
		
	}
	
	/**
	 * 	Populates With Rigidity.
	 */
	protected void populatesWithRigidity() 
	{
		for(Classifier rigid : ontoparser.getRigidClasses())
		{				
			// rigidity [ObjectClassName, Object, exists]
			if(rigid instanceof ObjectClass)
			{
				FactDeclaration rigidityFact = factory.createFactDeclaration();		
				rigidityFact.setName("rigidity");
				rigidityFact.setBlock(factory.createBlock());
				PredicateInvocation pI = AlloyAPI.createRigidityInvocation(factory, sigObject, exists, ontoparser.getAlias(rigid));
				rigidityFact.getBlock().getExpression().add(pI);
				rigidityFactList.add(rigidityFact);
			}
			// rigidity [PropertyClassName, Property, exists]
			if(rigid instanceof MomentClass)
			{
				FactDeclaration rigidityFact = factory.createFactDeclaration();		
				rigidityFact.setName("rigidity");
				rigidityFact.setBlock(factory.createBlock());
				PredicateInvocation pI = AlloyAPI.createRigidityInvocation(factory, sigProperty, exists, ontoparser.getAlias(rigid));
				rigidityFact.getBlock().getExpression().add(pI);
				rigidityFactList.add(rigidityFact);
			}				
		}
		for(FactDeclaration fact: rigidityFactList) module.getParagraph().add(fact);
	}
	
	/**
	 * 	Populates With Anti Rigidity.
	 */
	protected void populatesWithAntiRigidity() 
	{						
		if(options.antiRigidity)
		{						
			for(Classifier antirigid : ontoparser.getAntiRigidClasses())
			{				
				// antirigidity [antirigidClassName, Object, exists]
				if(antirigid instanceof ObjectClass)
				{
					FactDeclaration antirigidityFact = factory.createFactDeclaration();
					antirigidityFact.setName("antirigidity");
					antirigidityFact.setBlock(factory.createBlock());
					PredicateInvocation pI = AlloyAPI.createAntiRigidityInvocation(factory,sigObject, exists, ontoparser.getAlias(antirigid));					
					antirigidityFact.getBlock().getExpression().add(pI);
					antirigidityFactList.add(antirigidityFact);
				}				
			}			
		}
		for(FactDeclaration fact: antirigidityFactList) module.getParagraph().add(fact);
	}	
	
	/**
	 * 	Populates With Generalizations.
	 */
	protected void populatesWithGeneralizations()
	{
		for(Generalization g: ontoparser.getAllInstances(Generalization.class))
		{
			// Child in Father
			CompareOperation co = GeneralizationRule.createCompareOperation(ontoparser, factory, g);
			
			if ((g.getGeneral() instanceof DataType) || (g.getSpecific() instanceof DataType))
			{				
				// DataTypes
				if (co!=null) 
				{
					FactDeclaration genFact = factory.createFactDeclaration();		
					genFact.setName("generalization");
					genFact.setBlock(factory.createBlock());	
					genFact.getBlock().getExpression().add(co);
					genFactList.add(genFact);					
				}
			}else{				
				// Classes
				if (co!=null) 
				{
					FactDeclaration genFact = factory.createFactDeclaration();		
					genFact.setName("generalization");
					genFact.setBlock(factory.createBlock());	
					genFact.getBlock().getExpression().add(co);
					genFactList.add(genFact);					
				}
			}		
		}
		for(FactDeclaration fact: genFactList) module.getParagraph().add(fact);
	}
	
	/**
	 * 	Populates With Generalization Set.
	 */
	protected void populatesWithGeneralizationSets()
	{
		for(GeneralizationSet gs: ontoparser.getAllInstances(GeneralizationSet.class))
		{
			if (ontoparser.retainSelected(gs.getGeneralization()).size()>0) 
			{				
				if(gs.isIsCovering() || gs.isIsDisjoint())
				{
					FactDeclaration genSetFact = factory.createFactDeclaration();		
					genSetFact.setName("generalizationSet");
					genSetFact.setBlock(factory.createBlock());				
					genSetFactList.add(genSetFact);				
					
					// Father = Child1 + Child2 +...+ ChildN
					if(gs.isIsCovering())  
					{
						CompareOperation co = GeneralizationSetRule.createCompleteCompareOperation(ontoparser, factory, gs);
						if (co!=null) genSetFact.getBlock().getExpression().add(co); 
					}
					
					// disj[Child1, Child2, ..., ChildN]
					if(gs.isIsDisjoint())
					{
						DisjointExpression disj =  GeneralizationSetRule.createDisjointExpression(ontoparser, factory, gs);
						if (disj!=null && disj.getSet().size()>1) genSetFact.getBlock().getExpression().add(disj);	
					}				
				}
			}
		}
		for(FactDeclaration fact: genSetFactList) module.getParagraph().add(fact);
	}
	
	/**
	 * 	Populates With Additional Fact.
	 */
	protected void populatesWihAdditionalFact()
	{
		additionalFact = factory.createFactDeclaration();
		additionalFact.setName("additionalFacts");
		Block block = factory.createBlock();
		additionalFact.setBlock(block);		

		// linear_existence[exists]
		PredicateInvocation pI = AlloyAPI.createLinearExistenceInvocation(factory,exists);
		
		if (pI!=null) block.getExpression().add(pI);
		ArrayList<String> existsSignatures = new ArrayList<String>();
		if(sigObject!=null) existsSignatures.add(sigObject.getName());
		if(sigProperty!=null) existsSignatures.add(sigProperty.getName());		
		
		// all_elements_exists[Object+Property,exists]
		PredicateInvocation pI2 = AlloyAPI.createAllElementsExistsInvocation(factory,exists,existsSignatures);
		
		if (pI!=null) block.getExpression().add(pI2);	
		module.getParagraph().add(additionalFact);
	}
		
	/**
	 * Populates With Data Type Completeness.
	 */
	protected void populatesWithDataTypeCompleteness()
	{
		ArrayList<EObject> dataTypeList = new ArrayList<EObject>();
		dataTypeList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.DataType.class));
		dataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.Enumeration.class));
		dataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.PrimitiveType.class));				

		dataTypeCompletenessFact = factory.createFactDeclaration();
		dataTypeCompletenessFact.setName("dataTypeCompleteness");
		dataTypeCompletenessFact.setBlock(factory.createBlock());

		// Datatype =  DatatypeName1 + DatatypeName2 +...+ datatypeNameN
		if (dataTypeList.size()>0)
		{
			if (dataTypeList.size()==1)
			{				
				CompareOperation cOp = AlloyAPI.createCompareOperation( factory, sigDatatype.getName(), CompareOperator.EQUAL, ontoparser.getAlias(dataTypeList.get(0)));
				dataTypeCompletenessFact.getBlock().getExpression().add(cOp);
			}else{						
				CompareOperation cOp = AlloyAPI.createCompareOperation( factory, sigDatatype.getName(), CompareOperator.EQUAL, 
				AlloyAPI.createUnionExpression(factory, ontoparser.getAlias(dataTypeList)).toString());
				dataTypeCompletenessFact.getBlock().getExpression().add(cOp);
			}				
			module.getParagraph().add(dataTypeCompletenessFact);
		}		
	}

	/**
	 * 	Populates With Top Level DataTypes Disjointness.
	 */
	protected void populatesWithTopLevelDataTypeDisjointness()
	{
		ArrayList<Classifier> topDataTypeList = new ArrayList<Classifier>();
		topDataTypeList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.DataType.class));	
		topDataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.Enumeration.class));
		topDataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.PrimitiveType.class));
		
		dataTypeTopLevelFact = factory.createFactDeclaration();
		dataTypeTopLevelFact.setName("topLevelDataTypesDisjointnes");
		dataTypeTopLevelFact.setBlock(factory.createBlock());
		
		// disj[DatatypeName1 + DatatypeName2 +...+ datatypeNameN\		
		if(topDataTypeList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TopLevelClassRule.createTopLevelDisjointExpressions(ontoparser, factory, topDataTypeList);			
			for (DisjointExpression disj : rulesList) 
			{
				dataTypeTopLevelFact.getBlock().getExpression().add(disj); 
			}
			module.getParagraph().add(dataTypeTopLevelFact);
		}
	}
	
	/**
	 * 	Populates With Top Level Classes Disjointness
	 */
	protected void populatesWithTopLevelClassDisjointness()
	{
		// ObjectClass		 
		ArrayList<Classifier> topObjectClassList = new ArrayList<Classifier>();
		topObjectClassList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.ObjectClass.class));		
		if(topObjectClassList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TopLevelClassRule.createTopLevelDisjointExpressions(ontoparser, factory, topObjectClassList);			
			for (DisjointExpression disj : rulesList) 
			{ 
				world.getBlock().getExpression().add(disj); 
			}
		}
		
		// MomentClass
		ArrayList<Classifier> topMomentClassList = new ArrayList<Classifier>();
		topMomentClassList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.MomentClass.class));		
		if(topMomentClassList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TopLevelClassRule.createTopLevelDisjointExpressions(ontoparser, factory, topMomentClassList);			
			for (DisjointExpression disj : rulesList) 
			{ 
				world.getBlock().getExpression().add(disj); 
			}
		}
	}	
	
	/**
	 * 	Populates Elements Visibility.
	 */
	protected void populatesElementsVisibility()
	{	
		visibiliyFun = AlloyAPI.createVisibilityFunction("visible", factory, world);

		ArrayList<String> visibilityList  = new ArrayList<String>();

		// Classes
		visibilityList.add("exists");
		
		// DataTypes from Associations
		for(Association assoc: ontoparser.getAllInstances(Association.class))
		{
			RefOntoUML.Type sourceType = assoc.getMemberEnd().get(0).getType();
			RefOntoUML.Type targetType =assoc.getMemberEnd().get(1).getType();			
			String paramName = ontoparser.getAlias(assoc);
			
			// If DataType is the source
			if ((sourceType != null) && (sourceType instanceof DataType) )
			{				
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("select12");
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(paramName);
				pI.getParameter().add(vr);
				
				if (sourceType instanceof Enumeration) { visibilityList.add(pI.toString()); }
				else if (sourceType instanceof PrimitiveType) { visibilityList.add(pI.toString()); }
				else { visibilityList.add(pI.toString()); } 				
			}
			
			// If DataType is the target
			if ((targetType != null) && (targetType instanceof DataType) )
			{
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("select13");
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(paramName);
				pI.getParameter().add(vr);
				
				if (targetType instanceof Enumeration) { visibilityList.add(pI.toString()); } 
				else if (targetType instanceof PrimitiveType) { visibilityList.add(pI.toString()); } 
				else { visibilityList.add(pI.toString()); }  
			}			
		}				
		
		// DataTypes from Attributes
		for(Property p: ontoparser.getAttributes())
		{			
			RefOntoUML.Type sourceType = (RefOntoUML.Type)p.eContainer();
			RefOntoUML.Type targetType = p.getType();			
			String paramName = ontoparser.getAlias(p);
			
			// If DataType is the source
			if ((sourceType != null) && (sourceType instanceof DataType) )
			{
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("select12");
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(paramName);
				pI.getParameter().add(vr);
				
				if (sourceType instanceof Enumeration) { visibilityList.add(pI.toString()); }
				else if (sourceType instanceof PrimitiveType) { visibilityList.add(pI.toString()); }
				else { visibilityList.add(pI.toString()); }				
			}
			
			// If DataType is the target
			if ((targetType != null) && (targetType instanceof DataType) )
			{
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("select13");
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(paramName);
				pI.getParameter().add(vr);
				
				if (targetType instanceof Enumeration) { visibilityList.add(pI.toString()); }
				else if (targetType instanceof PrimitiveType) { visibilityList.add(pI.toString()); }
				else { visibilityList.add(pI.toString()); } 
			}			
		}	
		
		if(visibilityList.size()>0) 
		{ 			
			visibiliyFun.getBlock().getExpression().add(AlloyAPI.createUnionExpression(factory, visibilityList));
			module.getParagraph().add(visibiliyFun); 
		}				
	}
	
	/**
	 * Populates With Derivations.
	 */
	protected void populatesWithDerivations() throws Exception
	{		
		derivations = factory.createFactDeclaration();		
		derivations.setName("derivations");
		derivations.setBlock(factory.createBlock());
		
		for (Derivation d: ontoparser.getAllInstances(Derivation.class))
		{
			PredicateInvocation pI = DerivationRelationRule.createPredicateInvocation(ontoparser, factory, d);		
			if (pI!=null) derivations.getBlock().getExpression().add(pI);
			module.getParagraph().add(derivations);
		}
	}
	
	/**
	 * Populates With Read Only Properties.
	 */
	protected void populatesWithAssociationProperties()
	{		
		association_properties = factory.createFactDeclaration();
		association_properties.setName("associationProperties");
		association_properties.setBlock(factory.createBlock());
				
		for (Association assoc : ontoparser.getAllInstances(Association.class))
		{		
			Property Source = assoc.getMemberEnd().get(0);
			Property Target = assoc.getMemberEnd().get(1);
			boolean isSourceImmutable = false;
			boolean isTargetImmutable = false;
			
			if (assoc instanceof Meronymic) 
			{ 
				Meronymic m = ((Meronymic)assoc);
				if (m.isIsInseparable() || m.isIsImmutableWhole() || m.sourceEnd().isIsReadOnly()) isSourceImmutable = true;
				if (m.isIsEssential() || m.isIsImmutablePart() || m.targetEnd().isIsReadOnly()) isTargetImmutable = true;
			}
			if(Source.isIsReadOnly()) isSourceImmutable = true;
			if(Target.isIsReadOnly()) isTargetImmutable = true;
			
			if(isSourceImmutable) 
			{				
				PredicateInvocation pI =  AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_source", ontoparser.getAlias(Target.getType()), ontoparser.getAlias(assoc));				
				association_properties.getBlock().getExpression().add(pI);
			}
			
			if(isTargetImmutable)
			{								
				PredicateInvocation pI = AlloyAPI.createImmutablePredicateInvocation(factory, "immutable_target", ontoparser.getAlias(Source.getType()), ontoparser.getAlias(assoc));			
				association_properties.getBlock().getExpression().add(pI);
			}
		}
		module.getParagraph().add(association_properties);
	}
	
	/**
	 * Populates With Association Ends.
	 */
	protected void populatesWithAssociationEnds()
	{
		for (Property p: ontoparser.getAllInstances(Property.class))
		{
			if (p.getAssociation()!=null && !p.getName().isEmpty())
			{
				Association assoc = p.getAssociation();
				RefOntoUML.Type type = p.getType();
				boolean isSourceProperty = false;
				
				RefOntoUML.Type otherType;
				if (assoc.getMemberEnd().get(0).equals(p)) { isSourceProperty = true; otherType = assoc.getMemberEnd().get(1).getType(); }
				else { otherType = assoc.getMemberEnd().get(0).getType(); isSourceProperty = false; }
								
				String functionName = ontoparser.getAlias(p);			
				String returnName = ontoparser.getAlias(type);			
				if (!(type instanceof DataType)) returnName="World."+returnName;
				String paramName = ontoparser.getAlias(otherType);			
				if (!(otherType instanceof DataType)) paramName="World."+paramName;
				String assocName = ontoparser.getAlias(assoc);
							
				FunctionDeclaration fun = AlloyAPI.createFunctionDeclaration(factory, world, isSourceProperty, functionName, paramName, returnName, assocName);														
				module.getParagraph().add(fun);				
			}
		}
	}
	
	/**
	 * Populates With Association Cardinalities. 
	 */
	protected void populatesWithAssociationCardinalities()
	{
		for (Association assoc: ontoparser.getAllInstances(Association.class))
		{
			ArrayList<QuantificationExpression> qeList = CardinalityRule.createQuantificationExpressions(factory, ontoparser, assoc);
			for (QuantificationExpression qe: qeList) world.getBlock().getExpression().add(qe);
		}
	}
	
	/**
	 * Populates With Associations
	 */
	protected void populatesWithAssociations()
	{
		for(Association assoc: ontoparser.getAllInstances(Association.class))
		{
			VariableReference source = factory.createVariableReference();
			VariableReference target = factory.createVariableReference();
			int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;		
			Property Source = assoc.getMemberEnd().get(0);
			Property Target = assoc.getMemberEnd().get(1);
			source.setVariable(ontoparser.getAlias(Source.getType()));				
			lowerSource = Source.getLower();
			upperSource = Source.getUpper();	
			target.setVariable(ontoparser.getAlias(Target.getType()));				
			lowerTarget = Target.getLower();				
			upperTarget = Target.getUpper();				
			ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, source.getVariable(), lowerSource, upperSource, target.getVariable(), lowerTarget, upperTarget);
			Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(assoc), aOp);				
			if (decl!=null) world.getRelation().add(decl);	
		}
	}
	
	/**
	 * Populates With Completeness
	 */
	protected void populatesWithCompleteness()
	{	
		// exists:>Object in subsortalNamesList[0] +...+ subsortalNamesList[N] 
		 
		ArrayList<EObject> subsSortalList = new ArrayList<EObject>();
		subsSortalList.addAll(ontoparser.getAllInstances(SubstanceSortal.class));
		if(options.identityPrinciple && subsSortalList.size() > 0)
		{
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigObject, ontoparser.getAlias(subsSortalList));
		}
		
		// disj[SubSortalNamesList[0],...,SubSortalNamesList[N]],
		 
		if (subsSortalList.size()>1)
		{
			DisjointExpression disj = AlloyAPI.createDisjointExpression(factory, ontoparser.getAlias(subsSortalList));	
			if (disj!=null) world.getBlock().getExpression().add(disj);
		}
		
		// exists:>Object in objectNamesList[0] +...+ objectNamesList[N]
		 
		ArrayList<EObject> objectClassesList = new ArrayList<EObject>();
		objectClassesList.addAll(ontoparser.getAllInstances(ObjectClass.class));		
		if(!options.identityPrinciple && objectClassesList.size() > 0)
		{
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigObject, ontoparser.getAlias(objectClassesList));
		}
		
		// exists:>Property in propertyNamesList[0] +...+ propertyNamesList[N]
		 
		ArrayList<EObject> momentClassesList = new ArrayList<EObject>();
		momentClassesList.addAll(ontoparser.getAllInstances(MomentClass.class));		
		if(momentClassesList.size() > 0)
		{
			AlloyAPI.createExistsCompareOperationInWorld(factory, exists, world, sigProperty, ontoparser.getAlias(momentClassesList));
		}		
	}
}