package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PrimitiveType;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.EnumDeclaration;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;
import br.ufes.inf.nemo.ontouml2alloy.rules.TGeneralizationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TGeneralizationSetRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TTopLevelRule;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyUtil;

public class BaseTransformer {
	
	/** OntoUML Parser. */
	public OntoUMLParser ontoparser;

	/** Transformation Options. */
	public OntoUMLOptions options;
	
	/** Alloy Factory Instance.  */
	public AlloyFactory factory;	
	
	/** Alloy Module. */
	public AlloyModule module;
	
	/** Alloy Signature World. */
	public SignatureDeclaration world;
	
	/** Alloy World Field exists. */
	public Variable exists;	

	/** Alloy datatypes constraints */
	public FactDeclaration datatypes_facts; 
	
	public ArrayList<FactDeclaration> genSetFactList = new ArrayList<FactDeclaration>();
	public ArrayList<FactDeclaration> genFactList = new ArrayList<FactDeclaration>();
	
	/** Alloy Top Level Data Types Disjointness Rules Fact Declarations. */
	public FactDeclaration toplevel_datatype_disj_fact;
	
	/** Alloy additional_facts Declaration */
	public FactDeclaration additional_facts;
	
	/** Alloy association_properties Fact Declaration. */
	public FactDeclaration association_properties;

	/** Alloy derivation Fact Declaration. */
	public FactDeclaration derivations;
	
	/** Alloy all_antirigid_classes Fact Declaration. */
	public FactDeclaration all_antirigid_classes;

	/** Alloy all_rigid_classes Fact Declaration. */
	public FactDeclaration all_rigid_classes;
	
	/** Alloy Default Signature : sig Object{}. */
	public SignatureDeclaration sigObject;
	
	/** Alloy Default Signature : sig Property{}. */
	public SignatureDeclaration sigProperty;
	
	/** Alloy Default Signature : sig DataType{}. */
	public SignatureDeclaration sigDatatype;
	
	/** List of DataTypes Signatures. e.g. sig datatype in DataType {} .*/
	public ArrayList<SignatureDeclaration> dataTypesSignatures = new ArrayList<SignatureDeclaration>();	
	
	/** List of Enumeration Signatures. e.g. enum enumeration { literals } .*/
	public ArrayList<EnumDeclaration> enumerationSignatures = new ArrayList<EnumDeclaration>();

	/** 
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public BaseTransformer (OntoUMLParser ontoparser, AlloyFactory factory, OntoUMLOptions options)
	{
		this.ontoparser = ontoparser;		
		this.factory = factory;	
		this.options = options;
		
		module = factory.createAlloyModule();
		module.setName(ontoparser.getModelName());				
	
		/* abstract sig World {} 
		 */		
		world = AlloyUtil.createSigWorld(factory);
				
		/* open world_structure[World]
		 * open ontological_propertis[World]
		 * open util/relation
		 */		
		
		ModuleImportation mi1 = AlloyUtil.createModuleImport(factory,"world_structure","", world);		
		ModuleImportation mi2 = AlloyUtil.createModuleImport(factory,"ontological_properties","", world);
		ModuleImportation mi3 = AlloyUtil.createModuleImport(factory,"relation","util", null);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		module.getImports().add(mi3);
			
		/* sig Object{}
		 */
		if (ontoparser.getAllInstances(ObjectClass.class).size()>0)
		{						
			sigObject = factory.createSignatureDeclaration();
			sigObject.setName("Object");
			module.getParagraph().add(sigObject);
		}
		
		/* sig Property{}
		 */
		if (ontoparser.getAllInstances(MomentClass.class).size()>0)
		{	
			sigProperty = factory.createSignatureDeclaration();
			sigProperty.setName("Property");
			module.getParagraph().add(sigProperty);			
		}
		
		/* sig DataType{}
		 */
		if (ontoparser.getAllInstances(DataType.class).size()>0)
		{			
			sigDatatype = factory.createSignatureDeclaration();
			sigDatatype.setName("DataType");			
			module.getParagraph().add(sigDatatype);
		}
		
		/* sig datatype in DataType {}
		 */
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
		
		/* sig enum { literals }
		 */ 
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
		
		/* abstract sig World {
		 *    exists: some Object+Property,
		 * }
		 */
		{
			ArrayList<String> existsSignatures = new ArrayList<String>();
			if(sigObject!=null) existsSignatures.add(sigObject.getName());
			if(sigProperty!=null) existsSignatures.add(sigProperty.getName());
			exists = AlloyUtil.createExistsDeclaration(factory,existsSignatures);
			world.getRelation().add(exists.getDeclaration());			
		}
		module.getParagraph().add(world);
				
		/* fact additional_facts {...}
		 */
		{
			additional_facts = factory.createFactDeclaration();
			additional_facts.setName("additional_facts");
			Block block = factory.createBlock();
			additional_facts.setBlock(block);
			
			PredicateInvocation pI = AlloyUtil.createLinearExistenceInvocation(factory,exists);
			if (pI!=null) block.getExpression().add(pI);			

			ArrayList<String> existsSignatures = new ArrayList<String>();
			if(sigObject!=null) existsSignatures.add(sigObject.getName());
			if(sigProperty!=null) existsSignatures.add(sigProperty.getName());
			PredicateInvocation pI2 = AlloyUtil.createAllElementsExistsInvocation(factory,exists,existsSignatures);
			if (pI!=null) block.getExpression().add(pI2);
		
			module.getParagraph().add(additional_facts);
		}
		
		/* fact association_properties {}
		 */
		{
			association_properties = factory.createFactDeclaration();
			association_properties.setName("association_properties");
			association_properties.setBlock(factory.createBlock());			
		}
		
		/* fact derivations {}
		 */
		{
			derivations = factory.createFactDeclaration();		
			derivations.setName("derivations");
			derivations.setBlock(factory.createBlock());			
		}
		
		/* fact datatypes_facts {...}
		 */
		{			
			datatypes_facts = factory.createFactDeclaration();
			datatypes_facts.setName("datatypes_facts");
			datatypes_facts.setBlock(factory.createBlock());
			populateDataTypeFactDeclaration();
			if (datatypes_facts.getBlock().getExpression().size()>0) module.getParagraph().add(datatypes_facts);
		}

		/* fact  topLevelDataTypesDisjointnes {...}
		 */
		{			
			toplevel_datatype_disj_fact = factory.createFactDeclaration();
			toplevel_datatype_disj_fact.setName("topLevelDataTypesDisjointnes");
			toplevel_datatype_disj_fact.setBlock(factory.createBlock());
			populateTopLevelDataTypeDisjointnessRules();
			if (toplevel_datatype_disj_fact.getBlock().getExpression().size()>0) module.getParagraph().add(toplevel_datatype_disj_fact);
		}
		
		/* fact all_rigid_classes {...}
		 */
		{
			all_rigid_classes = factory.createFactDeclaration();		
			all_rigid_classes.setName("all_rigid_classes");
			all_rigid_classes.setBlock(factory.createBlock());	
			populateAllRigidFactDeclaration();
			if (all_rigid_classes.getBlock().getExpression().size()>0) module.getParagraph().add(all_rigid_classes);
		}
		
		/* fact all_antirigid_classes {...}
		 */
		{
			all_antirigid_classes = factory.createFactDeclaration();
			all_antirigid_classes.setName("all_antirigid_classes");
			all_antirigid_classes.setBlock(factory.createBlock());			
			if (options.antiRigidity) 
			{
				populateAntiRigidFactDeclaration();
				if (all_antirigid_classes.getBlock().getExpression().size()>0)  module.getParagraph().add(all_antirigid_classes);
			}
		}		
		
		/* abstract World {
		 * 
		 * }{
		 *    disj[TopLevelObjects]
		 *    disj[TopLevelProperties]    
		 * }
		 */
		populateWithTopLevelDisjointnessRules();		
		populateWithGeneralizationRules();
		populateWithGeneralizationSetRules();
	}
	
	/**
	 * 	Populate all_rigid_classes Fact Declaration.
	 */
	@SuppressWarnings("unchecked")
	protected void populateAllRigidFactDeclaration() 
	{
		ArrayList<Classifier> rigidElementsList = new ArrayList<Classifier>();
		rigidElementsList.addAll(ontoparser.getRigidClasses());		
		if(rigidElementsList.size()>0)
		{
			for(Classifier rigid :rigidElementsList)
			{	
				/* ObjectClass
				 */
				if(rigid instanceof ObjectClass)
				{
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigObject, exists, ontoparser.getAlias(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}
				/* MomentClass
				 */
				if(rigid instanceof MomentClass)
				{
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigProperty, exists, ontoparser.getAlias(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}							
			}			
		}
	}
	
	/**
	 * 	Populate all_antirigid_classes Fact Declaration.
	 */
	@SuppressWarnings("unchecked")
	protected void populateAntiRigidFactDeclaration() 
	{
		ArrayList<Classifier> antirigidElementsList = new ArrayList<Classifier>();
		antirigidElementsList.addAll(ontoparser.getAntiRigidClasses());		
		if(antirigidElementsList.size()>0)
		{						
			for(Classifier antirigid : antirigidElementsList)
			{
				/* ObjectClass
				 */
				if(antirigid instanceof ObjectClass)
				{
					PredicateInvocation pI = AlloyUtil.createAntiRigidityInvocation(factory,sigObject, exists, ontoparser.getAlias(antirigid));					
					all_antirigid_classes.getBlock().getExpression().add(pI);
				}
			}			
		}
	}
	
	/**
	 * Populate Data types facts Declaration.
	 */
	@SuppressWarnings("unchecked")
	protected void populateDataTypeFactDeclaration()
	{
		ArrayList<EObject> dataTypeList = new ArrayList<EObject>();
		dataTypeList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.DataType.class));
		dataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.Enumeration.class));
		dataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.PrimitiveType.class));				
		
		if (dataTypeList.size()>0)
		{
			CompareOperation cOp = AlloyUtil.createCompareOperation( factory, sigDatatype.getName(), CompareOperator.EQUAL_LITERAL, 
			AlloyUtil.createUnionExpression(factory, ontoparser.getAlias(dataTypeList)).toString());		
			datatypes_facts.getBlock().getExpression().add(cOp);
		}
	}

	/**
	 * 	Populates Top Level DataTypes Disjointness Rules.
	 */
	@SuppressWarnings("unchecked")
	protected void populateTopLevelDataTypeDisjointnessRules()
	{
		ArrayList<Classifier> topDataTypeList = new ArrayList<Classifier>();
		topDataTypeList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.DataType.class));	
		topDataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.Enumeration.class));
		topDataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.PrimitiveType.class));
		if(topDataTypeList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TTopLevelRule.createTopLevelDisjointRules(ontoparser, factory, topDataTypeList);			
			for (DisjointExpression disj : rulesList) 
			{
				toplevel_datatype_disj_fact.getBlock().getExpression().add(disj); 
			}
		}
	}
	
	/**
	 * 	Populates Top Level Rules Disjointness Rules..
	 */
	@SuppressWarnings("unchecked")
	protected void populateWithTopLevelDisjointnessRules()
	{
		// ObjectClass		 
		ArrayList<Classifier> topObjectClassList = new ArrayList<Classifier>();
		topObjectClassList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.ObjectClass.class));		
		if(topObjectClassList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TTopLevelRule.createTopLevelDisjointRules(ontoparser, factory, topObjectClassList);			
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
			ArrayList<DisjointExpression> rulesList = TTopLevelRule.createTopLevelDisjointRules(ontoparser, factory, topMomentClassList);			
			for (DisjointExpression disj : rulesList) 
			{ 
				world.getBlock().getExpression().add(disj); 
			}
		}
	}
	
	/**
	 * 	Populates Generalization Rules.
	 */
	@SuppressWarnings("unchecked")
	protected void populateWithGeneralizationRules()
	{
		for(Generalization g: ontoparser.getAllInstances(Generalization.class))
		{
			CompareOperation co = TGeneralizationRule.createCompareOperation(ontoparser, factory, g);
		
			if ((g.getGeneral() instanceof DataType) || (g.getSpecific() instanceof DataType))
			{				
				//DataTypes
				if (co!=null) 
				{
					FactDeclaration genFact = factory.createFactDeclaration();		
					genFact.setName("generalization");
					genFact.setBlock(factory.createBlock());	
					genFact.getBlock().getExpression().add(co);
					genFactList.add(genFact);					
				}
			}else{				
				//Classes
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
	 * 	Populates Generalization Set Rules.
	 */
	@SuppressWarnings("unchecked")
	protected void populateWithGeneralizationSetRules()
	{
		for(GeneralizationSet gs: ontoparser.getAllInstances(GeneralizationSet.class))
		{
			CompareOperation co = factory.createCompareOperation();
			DisjointExpression disj = factory.createDisjointExpression();
					
			if(gs.isIsCovering())  co = TGeneralizationSetRule.createCompleteCompareOperation(ontoparser, factory, gs);		
			if(gs.isIsDisjoint()) disj =  TGeneralizationSetRule.createDisjointExpression(ontoparser, factory, gs);			
			
			FactDeclaration genSetFact = factory.createFactDeclaration();		
			genSetFact.setName("generalizationSet");
			genSetFact.setBlock(factory.createBlock());	
			genSetFact.getBlock().getExpression().add(co);
			genSetFactList.add(genSetFact);
			
			if (gs.getGeneralization().get(0).getGeneral() instanceof DataType)
			{
				//DataTypes
				if (co!=null) genSetFact.getBlock().getExpression().add(co); 
				if (disj!=null) genSetFact.getBlock().getExpression().add(disj);				
			}else {				
				//Classes
				genSetFact.getBlock().getExpression().add(co);
				genSetFact.getBlock().getExpression().add(disj);
			}
		}
		for(FactDeclaration fact: genSetFactList) module.getParagraph().add(fact);
	}
}