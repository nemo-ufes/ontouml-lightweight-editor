package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SortalClass;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
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
import br.ufes.inf.nemo.alloy.Quantificator;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.ontouml2alloy.rules.AbstractnessClassRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.CardinalityRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.GeneralizationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.GeneralizationSetRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.RedefinesRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.RelatorAxiomRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.SubsetsRule;
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
	public OntoUML2AlloyOptions options;
	public AlloyFactory factory;	
	public AlloyModule module;
	
	public SignatureDeclaration world;	
	public Variable exists;	
	
	public SignatureDeclaration sigObject;	
	public SignatureDeclaration sigProperty;	
	public SignatureDeclaration sigDatatype;	
	public SignatureDeclaration sigString;	
	public SignatureDeclaration sigChar;	
	public SignatureDeclaration sigReal;
	
	public ArrayList<SignatureDeclaration> dataTypesSignatures = new ArrayList<SignatureDeclaration>();	
	public ArrayList<EnumDeclaration> enumerationSignatures = new ArrayList<EnumDeclaration>();
	
	public FunctionDeclaration visibiliyFun;
	public FactDeclaration additionalFact;	
	public FactDeclaration dataTypeCompletenessFact;	
	public FactDeclaration dataTypeTopLevelFact;			
	public FactDeclaration association_properties;	
		
	public ArrayList<FactDeclaration> rigidityFactList = new ArrayList<FactDeclaration>();	
	public ArrayList<FactDeclaration> antirigidityFactList = new ArrayList<FactDeclaration>();	
	public ArrayList<FactDeclaration> genSetFactList = new ArrayList<FactDeclaration>();	
	public ArrayList<FactDeclaration> genFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> relatorAxiomFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> weakSupplementationAxiomFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> meronymicAcyclicFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> characterizationAcyclicFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> derivationRelationshipFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> subsettedPropertiesFactList = new ArrayList<FactDeclaration>();
	
	/**
	 * Constructor
	 */
	public Transformer (OntoUMLParser ontoparser, AlloyFactory factory, OntoUML2AlloyOptions options)
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
		ModuleImportation mi5 = AlloyAPI.createModuleImport(factory,"boolean","util", null);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		module.getImports().add(mi3);		
		module.getImports().add(mi4);
		module.getImports().add(mi5);
			
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
				if (p.getName().compareToIgnoreCase("char")==0)
				{
					sigChar = factory.createSignatureDeclaration();
					sigChar.setName("Char");					
					module.getParagraph().add(sigChar);					
				}
				if (p.getName().compareToIgnoreCase("real")==0)
				{
					sigReal = factory.createSignatureDeclaration();
					sigReal.setName("Real");					
					module.getParagraph().add(sigReal);					
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
		populatesWithAssociationEnds();		
		populatesWithAssociationCardinalities();
				
		populatesWithCompleteness();
		
		populatesWithTopLevelClassDisjointness();
		
		CommandDeclaration cmd = AlloyAPI.createDefaultRunComand(factory, module);	
		module.getParagraph().add(cmd);				
	}
		
	public class DeclarationComparator implements Comparator<Declaration> 
	{
	   @Override
	   public int compare(Declaration o1, Declaration o2) {
	      return o1.toString().compareToIgnoreCase(o2.toString());
	   }
	}
	/** 
	 * Populates With Classes 
	 */
	protected void populatesWithClasses()
	{
		ArrayList<Declaration> classesDeclaration = new ArrayList<Declaration>();
		for(RefOntoUML.Class c: ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{			
			// ObjectClassName: set exists:>Object,
			if(c instanceof ObjectClass)
			{
				// we now support Phases of Relators and Modes
				if (c instanceof Phase)
				{
					EList<Classifier> parents = (EList<Classifier>) c.allParents();
					boolean containObject=false; boolean containProperty = false;
					for (Classifier classifier: parents) { if (classifier instanceof MomentClass) containProperty=true; else if (classifier instanceof SortalClass) containObject=true; }
					if (containProperty && !containObject) 
					{ 
						//PhaseName: set exists:>Property,
						Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(c), sigProperty.getName());	
						if (decl!=null) classesDeclaration.add(decl);
					}else {
						
						//PhaseName: set exists:>Object,
						Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(c), sigObject.getName());			
						if (decl!=null) classesDeclaration.add(decl);
					}
					
				}else{ 
					Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(c), sigObject.getName());			
					if (decl!=null) classesDeclaration.add(decl);
				}
			}
			
			// PropertyName: set exists:>Property,			 
			if(c instanceof MomentClass)
			{			
				Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(c),sigProperty.getName());			
				if (decl!=null) classesDeclaration.add(decl);
			}
							
			// Abstract_Father = Concrete_Child1 + Concrete_Child2 + Concrete_Child3 + ...			 
			if (c.isIsAbstract())
			{
				CompareOperation co = AbstractnessClassRule.createCompareOperation(ontoparser, factory, c);			
				if (co!=null) world.getBlock().getExpression().add(co);			
			}			
		}
		// Sort classes declarations in the signature world
		Collections.sort(classesDeclaration, new DeclarationComparator());
		
		world.getRelation().addAll(classesDeclaration);
	}
	
	/**
	 * Populates With Attributes.
	 */
	protected void populatesWithAttributes()
	{
		ArrayList<Declaration> attributesDeclaration = new ArrayList<Declaration>();
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
						if (decl!=null) attributesDeclaration.add(decl);					
					}					
					
					// AttributeName: set ClassOwner -> String_,
					else if (attr.getType().getName().compareToIgnoreCase("String")==0)
					{
						ArrowOperation aOp  = factory.createArrowOperation();
						aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,"String_",attr.getLower(),attr.getUpper());
						Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);	
						if (decl!=null) attributesDeclaration.add(decl);					
					}
					
					// AttributeName: set ClassOwner -> Char,
					else if (attr.getType().getName().compareToIgnoreCase("Char")==0)
					{
						ArrowOperation aOp  = factory.createArrowOperation();
						aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,"Char",attr.getLower(),attr.getUpper());
						Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);
						if (decl!=null) attributesDeclaration.add(decl);					
					}
					
					// AttributeName: set ClassOwner -> Real,
					else if (attr.getType().getName().compareToIgnoreCase("Real")==0)
					{
						ArrowOperation aOp  = factory.createArrowOperation();
						aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,"Real",attr.getLower(),attr.getUpper());
						Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);
						if (decl!=null) attributesDeclaration.add(decl);					
					}
					
					else if (attr.getType().getName().compareToIgnoreCase("Boolean")==0)
					{
					    //  AttributeName: set exists:> Object,
						if (attr.eContainer() instanceof ObjectClass)
						{
							Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(attr.eContainer()), sigObject.getName());
							if (decl!=null) attributesDeclaration.add(decl);
						}
						//  AttributeName: set exists:> Property,
						else if (attr.eContainer() instanceof MomentClass)
						{
							Declaration decl = AlloyAPI.createDeclaration(factory,exists,ontoparser.getAlias(attr.eContainer()), sigProperty.getName());
							if (decl!=null) attributesDeclaration.add(decl);
						}
					}
					
				}else{					
					ArrowOperation aOp  = factory.createArrowOperation();
					aOp = AlloyAPI.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,ontoparser.getAlias(attr.getType()),attr.getLower(),attr.getUpper());
					Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(attr), aOp);
					if (decl!=null) attributesDeclaration.add(decl);
				}
			}
		}
		// Sort classes declarations in the signature world
		Collections.sort(attributesDeclaration, new DeclarationComparator());
		
		world.getRelation().addAll(attributesDeclaration);
	}		
	
	
	/**
	 * Populates With Axioms
	 */
	protected void populatesWithAxioms()
	{
		for(RefOntoUML.Class c: ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			// fact weak_supplementation_constraint { all w: World | all x: w.<RigidSortalName> | # ( x.(w.meronymicName1)+ x.(w.meronymicName2) + ...) >= 2 }			 
			if ((c instanceof RigidSortalClass) && (options.weakSupplementation))
			{				
				FactDeclaration fact = WeakSupplementationAxiomRule.createFactDeclaration(ontoparser, factory, c);				
				if (fact!=null) {
					weakSupplementationAxiomFactList.add(fact);
					module.getParagraph().add(fact);
				}				
			}			
			
			// fact relator_constraint { all w: World | all x: w.<RelatorName> | # ( x.(w.<associationName1>)+ x.(w.<associationName2>) + ...) >= 2 }
			if ((c instanceof Relator) && (options.relatorConstraint))
			{			
				FactDeclaration fact = RelatorAxiomRule.createFactDeclaration(ontoparser, factory, (Relator)c);			
				if (fact!= null) {
					relatorAxiomFactList.add(fact);
					module.getParagraph().add(fact);
				}				
			}
		}
		
		// fact acyclicMeronymic { all w: World | acyclic [w.meronymicName, w.wholeName] }
		for(RefOntoUML.Meronymic m: ontoparser.getAllInstances(RefOntoUML.Meronymic.class))
		{
			FactDeclaration meronymicAcyclicFact = factory.createFactDeclaration();
			meronymicAcyclicFact.setName("acyclicMeronymic");
			Block block = factory.createBlock();
			meronymicAcyclicFact.setBlock(block);		
			
			QuantificationExpression qeWorld = AlloyAPI.createQuantificationExpression(factory,Quantificator.ALL,"w","World");
			PredicateInvocation acyclicInvocation = AlloyAPI.createAcyclicInvocation(factory, ontoparser.getAlias(m), ontoparser.getAlias(m.getMemberEnd().get(0).getType()));
			qeWorld.setExpression(acyclicInvocation);
			meronymicAcyclicFact.getBlock().getExpression().add(qeWorld);
			
			weakSupplementationAxiomFactList.add(meronymicAcyclicFact);
			module.getParagraph().add(meronymicAcyclicFact);
		}
		
		// fact acyclicCharacterization { all w: World | acyclic [w.characterizationName, w.SourceName] }
		for(RefOntoUML.Characterization c: ontoparser.getAllInstances(RefOntoUML.Characterization.class))
		{
			FactDeclaration characterizationAcyclicFact = factory.createFactDeclaration();
			characterizationAcyclicFact.setName("acyclicCharacterization");
			Block block = factory.createBlock();
			characterizationAcyclicFact.setBlock(block);					
			QuantificationExpression qeWorld = AlloyAPI.createQuantificationExpression(factory,Quantificator.ALL,"w","World");
			
			//treat cases in which characterization's target is a mode, that is, the relation is inverted
			if((c.getMemberEnd().get(1).getType() instanceof Mode) && !(c.getMemberEnd().get(0).getType() instanceof Mode))
			{
				PredicateInvocation acyclicInvocation = AlloyAPI.createAcyclicInvocation(factory, ontoparser.getAlias(c), ontoparser.getAlias(c.getMemberEnd().get(1).getType()));
				qeWorld.setExpression(acyclicInvocation);
			}else{
				PredicateInvocation acyclicInvocation = AlloyAPI.createAcyclicInvocation(factory, ontoparser.getAlias(c), ontoparser.getAlias(c.getMemberEnd().get(0).getType()));
				qeWorld.setExpression(acyclicInvocation);
			}			
			characterizationAcyclicFact.getBlock().getExpression().add(qeWorld);
			
			characterizationAcyclicFactList.add(characterizationAcyclicFact);
			module.getParagraph().add(characterizationAcyclicFact);
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
		PredicateInvocation pI = AlloyAPI.createContinuousExistenceInvocation(factory,exists);
		
		if (pI!=null) block.getExpression().add(pI);
		ArrayList<String> existsSignatures = new ArrayList<String>();
		if(sigObject!=null) existsSignatures.add(sigObject.getName());
		if(sigProperty!=null) existsSignatures.add(sigProperty.getName());		
		
		// all_elements_exists[Object+Property,exists]
		PredicateInvocation pI2 = AlloyAPI.createElementsExistenceInvocation(factory,exists,existsSignatures);
		
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
	 * Populates With Read Only Properties.
	 */
	protected void populatesWithAssociationProperties()
	{		
		association_properties = factory.createFactDeclaration();
		association_properties.setName("associationProperties");
		association_properties.setBlock(factory.createBlock());
				
		for (Association assoc : ontoparser.getAllInstances(Association.class))
		{	
			if (!(assoc instanceof Derivation))
			{
				Property Source = null;
				Property Target = null;
				boolean isSourceImmutable = false;
				boolean isTargetImmutable = false;
				
				// invert sides if mediation's target is a relator
				if (assoc instanceof Mediation)
				{ 
					if((assoc.getMemberEnd().get(1).getType() instanceof Relator) && !(assoc.getMemberEnd().get(0).getType() instanceof Relator))
					{
						Source = assoc.getMemberEnd().get(1);
						Target = assoc.getMemberEnd().get(0);
						isTargetImmutable = Source.isIsReadOnly();
						isSourceImmutable = Target.isIsReadOnly();
					}else{
						Source = assoc.getMemberEnd().get(0);
						Target = assoc.getMemberEnd().get(1);
						isSourceImmutable = Source.isIsReadOnly();
						isTargetImmutable = Target.isIsReadOnly();
					}
				
				// invert sides if characterization's target is a mode
				}else if (assoc instanceof Characterization)
				{ 
					if((assoc.getMemberEnd().get(1).getType() instanceof Mode) && !(assoc.getMemberEnd().get(0).getType() instanceof Mode))
					{
						Source = assoc.getMemberEnd().get(1);
						Target = assoc.getMemberEnd().get(0);
						isTargetImmutable = Source.isIsReadOnly();
						isSourceImmutable = Target.isIsReadOnly();
					}else{
						Source = assoc.getMemberEnd().get(0);
						Target = assoc.getMemberEnd().get(1);
						isSourceImmutable = Source.isIsReadOnly();
						isTargetImmutable = Target.isIsReadOnly();
					}						
				
				// for other do not invert...	
				} else{
					Source = assoc.getMemberEnd().get(0);
					Target = assoc.getMemberEnd().get(1);
					isSourceImmutable = Source.isIsReadOnly();
					isTargetImmutable = Target.isIsReadOnly();
				}
								
				if (assoc instanceof Meronymic) 
				{ 
					Meronymic m = ((Meronymic)assoc);
					if (m.isIsInseparable() || m.isIsImmutableWhole() || m.sourceEnd().isIsReadOnly()) isSourceImmutable = true;
					if (m.isIsEssential() || m.isIsImmutablePart() || m.targetEnd().isIsReadOnly()) isTargetImmutable = true;
				}
								
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
		}
		module.getParagraph().add(association_properties);
	}
	
	/**
	 * Populates With Association Ends, Subsetted and Redefined Properties
	 */
	protected void populatesWithAssociationEnds()
	{		
		for (Property p: ontoparser.getAllInstances(Property.class))
		{
			if (p.getAssociation()!=null && p.getName()!=null && !p.getName().isEmpty() && !(p.getAssociation() instanceof Derivation))
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
					
				// invert sides if mediation's target is a relator
				if (assoc instanceof Mediation)
				{ 
					if((assoc.getMemberEnd().get(1).getType() instanceof Relator) && !(assoc.getMemberEnd().get(0).getType() instanceof Relator))
					{ String exchangeAux = returnName; returnName = paramName; paramName = exchangeAux; } 
				}
				
				// invert sides if characterization's target is a mode
				if (assoc instanceof Characterization)
				{ 
					if((assoc.getMemberEnd().get(1).getType() instanceof Mode) && !(assoc.getMemberEnd().get(0).getType() instanceof Mode))
					{ String exchangeAux = returnName; returnName = paramName; paramName = exchangeAux; } 
				}
								
				boolean materialIsTernary = false;				
				if (assoc instanceof MaterialAssociation) { 
					Derivation derivation = ontoparser.getDerivation((MaterialAssociation)assoc);
					if (derivation !=null) materialIsTernary = true; 
				} 
				
				// for single snapshot
				FunctionDeclaration fun = AlloyAPI.createFunctionDeclaration(factory, world, isSourceProperty, functionName, paramName, returnName, assocName, materialIsTernary);				
				module.getParagraph().add(fun);		
				// for all worlds
				fun = AlloyAPI.createTemporalFunctionDeclaration(factory, world, isSourceProperty, functionName, paramName, returnName, assocName, materialIsTernary);				
				module.getParagraph().add(fun);		
				
				// SubSetted properties
				if (p.getSubsettedProperty().size()>0) {
					FactDeclaration subSFact = factory.createFactDeclaration();		
					subSFact.setName("subsetted");
					subSFact.setBlock(factory.createBlock());	
					subsettedPropertiesFactList.add(subSFact);				
					for(Property subsetted: p.getSubsettedProperty())
					{
						QuantificationExpression qe = SubsetsRule.createQuantificationExpression(factory, ontoparser, p, subsetted);
						subSFact.getBlock().getExpression().add(qe);	
					}				
					module.getParagraph().add(subSFact);
				}
				
				// Redefined properties
				if (p.getRedefinedProperty().size()>0){
					FactDeclaration redefFact = factory.createFactDeclaration();		
					redefFact.setName("redefined");
					redefFact.setBlock(factory.createBlock());	
					subsettedPropertiesFactList.add(redefFact);				
					for(Property redefined: p.getRedefinedProperty())
					{
						QuantificationExpression qe = RedefinesRule.createQuantificationExpression(factory, ontoparser, p, redefined);
						redefFact.getBlock().getExpression().add(qe);	
					}				
					module.getParagraph().add(redefFact);
				}				
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
			if (!(assoc instanceof Derivation))
			{
				ArrayList<QuantificationExpression> qeList = CardinalityRule.createQuantificationExpressions(factory, ontoparser, assoc);
				for (QuantificationExpression qe: qeList) world.getBlock().getExpression().add(qe);
			}
		}
	}
		
	/**
	 * Populates with Mediations
	 * @return 
	 */
	protected ArrayList<Declaration> populatesWithMediations(ArrayList<Declaration> associationsDeclaration)
	{
		for(Mediation assoc: ontoparser.getAllInstances(Mediation.class))
		{
			VariableReference source = factory.createVariableReference();
			VariableReference target = factory.createVariableReference();
			int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;			
			Property Source = assoc.getMemberEnd().get(0);
			Property Target = assoc.getMemberEnd().get(1);
			if((Target.getType() instanceof Relator) && !(Source.getType() instanceof Relator))
			{
				// invert sides
				source.setVariable(ontoparser.getAlias(Target.getType()));
				lowerSource = Target.getLower();
				upperSource = Target.getUpper();
				target.setVariable(ontoparser.getAlias(Source.getType()));				
				lowerTarget = Source.getLower();				
				upperTarget = Source.getUpper();
			}else{
				source.setVariable(ontoparser.getAlias(Source.getType()));				
				lowerSource = Source.getLower();
				upperSource = Source.getUpper();				
				target.setVariable(ontoparser.getAlias(Target.getType()));				
				lowerTarget = Target.getLower();				
				upperTarget = Target.getUpper();
			}
			ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, source.getVariable(), lowerSource, upperSource, target.getVariable(), lowerTarget, upperTarget);			
			Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(assoc), aOp);				
			if (decl!=null) associationsDeclaration.add(decl);
		}
		return associationsDeclaration;
	}
	
	/**
	 * Populates with Characterizations
	 * @return 
	 */
	protected ArrayList<Declaration> populatesWithCharacterizations(ArrayList<Declaration> associationsDeclaration)
	{
		for(Characterization assoc: ontoparser.getAllInstances(Characterization.class))
		{
			VariableReference source = factory.createVariableReference();
			VariableReference target = factory.createVariableReference();
			int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;			
			Property Source = assoc.getMemberEnd().get(0);
			Property Target = assoc.getMemberEnd().get(1);
			if((Target.getType() instanceof Mode) && !(Source.getType() instanceof Mode))
			{
				// invert sides
				source.setVariable(ontoparser.getAlias(Target.getType()));
				lowerSource = Target.getLower();
				upperSource = Target.getUpper();
				target.setVariable(ontoparser.getAlias(Source.getType()));				
				lowerTarget = Source.getLower();				
				upperTarget = Source.getUpper();
			}else{
				source.setVariable(ontoparser.getAlias(Source.getType()));				
				lowerSource = Source.getLower();
				upperSource = Source.getUpper();				
				target.setVariable(ontoparser.getAlias(Target.getType()));				
				lowerTarget = Target.getLower();				
				upperTarget = Target.getUpper();
			}
			ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, source.getVariable(), lowerSource, upperSource, target.getVariable(), lowerTarget, upperTarget);			
			Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(assoc), aOp);				
			if (decl!=null) associationsDeclaration.add(decl);
		}
		return associationsDeclaration;
	}
		
	/**
	 * Populates With Derivations
	 */
	protected void populatesWithDerivations()
	{
		for(Derivation d: ontoparser.getAllInstances(Derivation.class))
		{
			MaterialAssociation material = (MaterialAssociation) (d.getMemberEnd().get(0).getType() instanceof MaterialAssociation ? d.getMemberEnd().get(0).getType() : d.getMemberEnd().get(1).getType());
			Relator relator = (Relator) (d.getMemberEnd().get(0).getType() instanceof Relator ? d.getMemberEnd().get(0).getType() : d.getMemberEnd().get(1).getType());
			Property Source = material.getMemberEnd().get(0);
			Property Target = material.getMemberEnd().get(1);
			String SourceName = ontoparser.getAlias(Source.getType());
			String TargetName = ontoparser.getAlias(Target.getType());
			String RelatorName = ontoparser.getAlias(relator);
			String MaterialName = ontoparser.getAlias(material);
			
			//found mediations
			Mediation mediationSource = null;
			Mediation mediationTarget = null;			
			for (Mediation md: ontoparser.getAllInstances(Mediation.class))
			{
				RefOntoUML.Type src = md.getMemberEnd().get(0).getType();
				RefOntoUML.Type tgt = md.getMemberEnd().get(1).getType();
									
				if(src.equals(relator) && tgt.equals(Source.getType())) { mediationSource = md; }				
				else if(src.equals(relator) && tgt.equals(Target.getType())) { mediationTarget = md; }						
					
				// now, in the case where mediation is inverted
				else if(src.equals(Target.getType()) && tgt.equals(relator)) { mediationTarget = md; }
				else if(src.equals(Source.getType()) && tgt.equals(relator)) { mediationSource = md; }
				
			}
			String mediationSourceName = (String) (mediationSource == null ? "null" : ontoparser.getAlias(mediationSource));
			String mediationTargetName = (String) (mediationTarget == null ? "null" : ontoparser.getAlias(mediationTarget));
			
			// fact derivationRelationship { all w: World, x:w.Source, y:w.Target, r:w.Relator | (x->r->y) in w.Material iff ( x in r.(w.Mediation1) and y in r.(w.Mediation2) )  }		
			FactDeclaration derivationRelationshipFact = factory.createFactDeclaration();
			derivationRelationshipFact.setName("derivationRelationship");
			Block block = factory.createBlock();
			derivationRelationshipFact.setBlock(block);		
			
			QuantificationExpression qeWorld = AlloyAPI.createQuantificationExpression(factory,Quantificator.ALL,"w","World","x","w."+SourceName,"y","w."+TargetName,"r","w."+RelatorName);				
			ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, "x", "r", "y");	
			CompareOperation cOp = AlloyAPI.createCompareOperation(factory, aOp.toString(), CompareOperator.SUBSET, "w."+MaterialName);
			CompareOperation cOp2 = AlloyAPI.createCompareOperation(factory, "x", CompareOperator.SUBSET, "r.(w."+mediationSourceName+")");
			CompareOperation cOp3 = AlloyAPI.createCompareOperation(factory, "y", CompareOperator.SUBSET, "r.(w."+mediationTargetName+")");
			BinaryOperation bOp2 = AlloyAPI.createBinaryOperation(factory, cOp2.toString()+" ", BinaryOperator.AND, " "+cOp3.toString());
			BinaryOperation bOp = AlloyAPI.createBinaryOperation(factory,cOp.toString()+" ", BinaryOperator.IFF, " "+bOp2);
			qeWorld.setExpression(bOp);
			derivationRelationshipFact.getBlock().getExpression().add(qeWorld);
			
			derivationRelationshipFactList.add(derivationRelationshipFact);
			module.getParagraph().add(derivationRelationshipFact);
		}
	}
	
	/**
	 * Populates with Materials 
	 * @return 
	 */
	protected ArrayList<Declaration> populatesWithMaterials(ArrayList<Declaration> associationsDeclaration)
	{
		HashMap<MaterialAssociation,Derivation> relationsMap = new HashMap<MaterialAssociation,Derivation>();
		
		// initialize the map with material associations and their derivations
		for (Derivation d: ontoparser.getAllInstances(Derivation.class))
		{
			for(Property prop : d.getMemberEnd())
			{
				if(prop.getType() instanceof MaterialAssociation) relationsMap.put((MaterialAssociation)prop.getType(),d);
			}
		}
		for (MaterialAssociation m: ontoparser.getAllInstances(MaterialAssociation.class))
		{
			if (!(relationsMap.containsKey(m))) relationsMap.put(m, null);
		}
		
		// now transforms each material association of the map...
		for (MaterialAssociation m: relationsMap.keySet())
		{			
			Property materialSourceProperty = m.getMemberEnd().get(0);
			Property materialTargetProperty = m.getMemberEnd().get(1);
			VariableReference source = factory.createVariableReference();
			VariableReference target = factory.createVariableReference();
			source.setVariable(ontoparser.getAlias(materialSourceProperty.getType()));
			target.setVariable(ontoparser.getAlias(materialTargetProperty.getType()));
			
			// there's a derivation related to this material association
			if (relationsMap.get(m) != null)
			{
				Derivation derivation = relationsMap.get(m);
				Relator relator = (Relator) (derivation.getMemberEnd().get(0).getType() instanceof Relator ? derivation.getMemberEnd().get(0).getType() : derivation.getMemberEnd().get(1).getType());
				
				int lowerTgtMediationSource=-1; int upperTgtMediationSource=-1; 
				int lowerSrcMediationSource=-1; int upperSrcMediationSource=-1;
				int lowerTgtMediationTarget=-1; int upperTgtMediationTarget=-1; 
				int lowerSrcMediationTarget=-1; int upperSrcMediationTarget=-1;		
				
				VariableReference relatorVarReference = factory.createVariableReference();
				relatorVarReference.setVariable(ontoparser.getAlias(relator));
				
				for (Mediation md: ontoparser.getAllInstances(Mediation.class))
				{
					RefOntoUML.Type src = md.getMemberEnd().get(0).getType();
					RefOntoUML.Type tgt = md.getMemberEnd().get(1).getType();
										
					if(src.equals(relator) && tgt.equals(materialSourceProperty.getType())) {											 
						lowerSrcMediationSource = md.getMemberEnd().get(0).getLower(); upperSrcMediationSource = md.getMemberEnd().get(0).getUpper();
						lowerTgtMediationSource = md.getMemberEnd().get(1).getLower(); upperTgtMediationSource = md.getMemberEnd().get(1).getUpper();
					}										
					else if(src.equals(relator) && tgt.equals(materialTargetProperty.getType())) {						
						lowerSrcMediationTarget = md.getMemberEnd().get(0).getLower(); upperSrcMediationTarget = md.getMemberEnd().get(0).getUpper();
						lowerTgtMediationTarget = md.getMemberEnd().get(1).getLower(); upperTgtMediationTarget = md.getMemberEnd().get(1).getUpper();
					}
					// now, in the case where mediation is inverted
					else if(src.equals(materialTargetProperty.getType()) && tgt.equals(relator)) {						
						lowerSrcMediationTarget = md.getMemberEnd().get(1).getLower(); upperSrcMediationTarget = md.getMemberEnd().get(1).getUpper();
						lowerTgtMediationTarget = md.getMemberEnd().get(0).getLower(); upperTgtMediationTarget = md.getMemberEnd().get(0).getUpper();
						
					}
					else if(src.equals(materialSourceProperty.getType()) && tgt.equals(relator)) {						
						lowerSrcMediationSource = md.getMemberEnd().get(1).getLower(); upperSrcMediationSource = md.getMemberEnd().get(1).getUpper();
						lowerTgtMediationSource = md.getMemberEnd().get(0).getLower(); upperTgtMediationSource = md.getMemberEnd().get(0).getUpper();
					}
				}

				ArrowOperation aOp = AlloyAPI.createArrowOperation(
					factory, 
					source.getVariable(), lowerTgtMediationSource, upperTgtMediationSource, 
					relatorVarReference.getVariable(), lowerSrcMediationSource, upperSrcMediationSource, lowerSrcMediationTarget,  upperSrcMediationTarget,
				    target.getVariable(), lowerTgtMediationTarget, upperTgtMediationTarget
				);			
				Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(m), aOp);				
				if (decl!=null) associationsDeclaration.add(decl);
				
			}
			
			// no derivation related to the material association
			else {				
				int lowerSource = materialSourceProperty.getLower();
				int upperSource = materialSourceProperty.getUpper();								
				int lowerTarget = materialTargetProperty.getLower();				
				int upperTarget = materialTargetProperty.getUpper();
				ArrowOperation aOp = AlloyAPI.createArrowOperation(factory, source.getVariable(), lowerSource, upperSource, target.getVariable(), lowerTarget, upperTarget);			
				Declaration decl = AlloyAPI.createDeclaration(factory, ontoparser.getAlias(m), aOp);				
				if (decl!=null) associationsDeclaration.add(decl);
			}
		}
		return associationsDeclaration;
	}
	
	/**
	 * Populates With Associations
	 */
	protected void populatesWithAssociations()
	{
		ArrayList<Declaration> associationsDeclaration = new ArrayList<Declaration>();
		
		associationsDeclaration = populatesWithMediations(associationsDeclaration);
		
		associationsDeclaration = populatesWithCharacterizations(associationsDeclaration);
		
		associationsDeclaration = populatesWithMaterials(associationsDeclaration);
		
		populatesWithDerivations();
		
		for(Association assoc: ontoparser.getAllInstances(Association.class))
		{
			if(!(assoc instanceof Mediation) && !(assoc instanceof Derivation) && !(assoc instanceof Characterization) && !(assoc instanceof MaterialAssociation)) 
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
				if (decl!=null) associationsDeclaration.add(decl);
			}
		}
		// Sort associations declarations in the signature world
		Collections.sort(associationsDeclaration, new DeclarationComparator());
		
		world.getRelation().addAll(associationsDeclaration);
		
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
		ListIterator<EObject> it = objectClassesList.listIterator();
 		// verify if the phase, which is a ObjectClass, have a Moment as ancestor. In this case, we are supporting phases of relators
		// and the phase should not appear in this list, it need to be removed.
		while(it.hasNext()) {
			EObject obj = (EObject)it.next();
			boolean containMoment = false;
			boolean containSortal = false;
			if (obj instanceof Phase){
				EList<Classifier> parents = (EList<Classifier>)((Phase)obj).allParents();
				for(Classifier c: parents) { if (c instanceof MomentClass) containMoment=true; else if (c instanceof SortalClass) containSortal=true; }
				if (containMoment && !containSortal) it.remove();				
			}
		}
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