package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.EnumDeclaration;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;
import br.ufes.inf.nemo.ontouml2alloy.rules.TGeneralizationRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TGeneralizationSetRule;
import br.ufes.inf.nemo.ontouml2alloy.rules.TTopLevelRule;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyUtil;

public class BaseTransformer {
	
	public OntoUMLParser ontoparser;

	public OntoUMLOptions options;

	public AlloyFactory factory;	
	
	public AlloyModule module;
	
	public SignatureDeclaration world;
	
	public Variable exists;	

	public SignatureDeclaration sigObject;
	
	public SignatureDeclaration sigProperty;
	
	public SignatureDeclaration sigDatatype;
	
	public SignatureDeclaration sigString;
	
	public ArrayList<SignatureDeclaration> dataTypesSignatures = new ArrayList<SignatureDeclaration>();	
	
	public ArrayList<EnumDeclaration> enumerationSignatures = new ArrayList<EnumDeclaration>();
	
	public FactDeclaration additionalFact;
	
	public FactDeclaration dataTypeCompletenessFact;
	
	public FactDeclaration dataTypeTopLevelFact;
	
	public FunctionDeclaration visibiliyFun;
	//public FunctionDeclaration dataTypeVisibilityFun;	
	//public FunctionDeclaration enumerationVisibilityFun;
	//public FunctionDeclaration primitiveTypeVisibilityFun;
	
	public ArrayList<FactDeclaration> rigidityFactList = new ArrayList<FactDeclaration>();;
	
	public ArrayList<FactDeclaration> antirigidityFactList = new ArrayList<FactDeclaration>();;
	
	public ArrayList<FactDeclaration> genSetFactList = new ArrayList<FactDeclaration>();
	
	public ArrayList<FactDeclaration> genFactList = new ArrayList<FactDeclaration>();
			
	public FactDeclaration association_properties;
	
	public FactDeclaration derivations;	

	/** 
	 * Constructor
	 */
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
		ModuleImportation mi4 = AlloyUtil.createModuleImport(factory,"boolean","util", null);
		ModuleImportation mi5 = AlloyUtil.createModuleImport(factory,"ternary","util", null);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		module.getImports().add(mi3);
		module.getImports().add(mi4);
		module.getImports().add(mi5);
			
		/* sig Object{}
		 */
		//if (ontoparser.getAllInstances(ObjectClass.class).size()>0)
		//{						
			sigObject = factory.createSignatureDeclaration();
			sigObject.setName("Object");
			module.getParagraph().add(sigObject);
		//}
		
		/* sig Property{}
		 */
		//if (ontoparser.getAllInstances(MomentClass.class).size()>0)
		//{	
			sigProperty = factory.createSignatureDeclaration();
			sigProperty.setName("Property");
			module.getParagraph().add(sigProperty);			
		//}
		
		/* sig DataType{}
		 */
		//if (ontoparser.getAllInstances(DataType.class).size()>0)
		//{			
			sigDatatype = factory.createSignatureDeclaration();
			sigDatatype.setName("DataType");			
			module.getParagraph().add(sigDatatype);
		//}
		
		/* sig String {}
		 */
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
		
		/* sig Datatype in DataType {}
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
		
		/* sig Enum { Literals }
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
						
			if(ontoparser.getAllInstances(ObjectClass.class).size()>0)
				existsSignatures.add(sigObject.getName());
			if(ontoparser.getAllInstances(MomentClass.class).size()>0)
				existsSignatures.add(sigProperty.getName());				
			
			if (existsSignatures.size() > 0)
			{
				exists = AlloyUtil.createExistsDeclaration(factory,existsSignatures);			
				world.getRelation().add(exists.getDeclaration());
			}	
		}
		module.getParagraph().add(world);
						
		/* fact association_properties {}
		 */
		{
			association_properties = factory.createFactDeclaration();
			association_properties.setName("associationProperties");
			association_properties.setBlock(factory.createBlock());			
		}
		
		/* fact derivations {}
		 */
		{
			derivations = factory.createFactDeclaration();		
			derivations.setName("derivations");
			derivations.setBlock(factory.createBlock());			
		}
		
		if(ontoparser.getAllInstances(RefOntoUML.Class.class).size()==0 &&
		   ontoparser.getAllInstances(RefOntoUML.DataType.class).size()==0) 
		return;
		
		if(ontoparser.getAllInstances(RefOntoUML.Class.class).size()>0)
			populateWihAdditionalRules();			
				
		populateWithRigidityRules();
		populateWithAntiRigidityRules();
		populateWithGeneralizationRules();			
		populateWithGeneralizationSetRules();
		populateWithVisibilityRule();		
		populateDataTypeCompletenessRule();		
		populateWithTopLevelDataTypeDisjointnessRules();
		populateWithTopLevelDisjointnessRules();		
				
	}
		
	/**
	 * Populates With Attributes Rules.
	 */
	protected void populateWithAttributeRules()
	{
		for(Property attr: ontoparser.getAttributes())		
		{			
			if (attr.getType().getName()!=null) 
			{
				String target = new String();
				ArrowOperation aOp  = factory.createArrowOperation();
				if (attr.getType() instanceof PrimitiveType)
				{
					if (attr.getType().getName().compareToIgnoreCase("int")==0)
					{
						target = "Int";
						aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,target,attr.getLower(),attr.getUpper());
					}
					else if (attr.getType().getName().compareToIgnoreCase("Boolean")==0) 
					{
						target = "Bool";
						aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,target,attr.getLower(),attr.getUpper());
					}
					else if (attr.getType().getName().compareToIgnoreCase("String")==0)
					{
						target = "String_";
						aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,target,attr.getLower(),attr.getUpper());
					}
					else if (attr.getType().getName().compareToIgnoreCase("char")==0)
					{
						target = "String_";
						aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,target,attr.getLower(),attr.getUpper());
					}			
					
				}else{
					
					aOp = AlloyUtil.createArrowOperation(factory,ontoparser.getAlias(attr.eContainer()),0,-1,ontoparser.getAlias(attr.getType()),attr.getLower(),attr.getUpper());
				}
						
				Declaration decl = AlloyUtil.createDeclaration(factory, ontoparser.getAlias(attr), aOp);		
				if (decl!=null) world.getRelation().add(decl);
			}
		}		
	}
	
	/**
	 * 	Populates With Additional Rules.
	 */
	protected void populateWihAdditionalRules()
	{
		additionalFact = factory.createFactDeclaration();
		additionalFact.setName("additionalFacts");
		Block block = factory.createBlock();
		additionalFact.setBlock(block);
		
		PredicateInvocation pI = AlloyUtil.createLinearExistenceInvocation(factory,exists);
		if (pI!=null) block.getExpression().add(pI);			

		ArrayList<String> existsSignatures = new ArrayList<String>();
		if(sigObject!=null) existsSignatures.add(sigObject.getName());
		if(sigProperty!=null) existsSignatures.add(sigProperty.getName());
		
		PredicateInvocation pI2 = AlloyUtil.createAllElementsExistsInvocation(factory,exists,existsSignatures);
		if (pI!=null) block.getExpression().add(pI2);
	
		module.getParagraph().add(additionalFact);
	}
	
	/**
	 * 	Populates With Rigidity Rules.
	 */
	protected void populateWithRigidityRules() 
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
					FactDeclaration rigidityFact = factory.createFactDeclaration();		
					rigidityFact.setName("rigidity");
					rigidityFact.setBlock(factory.createBlock());
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigObject, exists, ontoparser.getAlias(rigid));
					rigidityFact.getBlock().getExpression().add(pI);
					rigidityFactList.add(rigidityFact);
				}
				/* MomentClass
				 */
				if(rigid instanceof MomentClass)
				{
					FactDeclaration rigidityFact = factory.createFactDeclaration();		
					rigidityFact.setName("rigidity");
					rigidityFact.setBlock(factory.createBlock());
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigProperty, exists, ontoparser.getAlias(rigid));
					rigidityFact.getBlock().getExpression().add(pI);
					rigidityFactList.add(rigidityFact);
				}				
			}			
		}
		for(FactDeclaration fact: rigidityFactList) module.getParagraph().add(fact);
	}
	
	/**
	 * 	Populates With Anti Rigidity Rules.
	 */
	protected void populateWithAntiRigidityRules() 
	{
		ArrayList<Classifier> antirigidElementsList = new ArrayList<Classifier>();
		antirigidElementsList.addAll(ontoparser.getAntiRigidClasses());
						
		if(antirigidElementsList.size()>0 && options.antiRigidity)
		{						
			for(Classifier antirigid : antirigidElementsList)
			{				
				/* ObjectClass
				 */
				if(antirigid instanceof ObjectClass)
				{
					FactDeclaration antirigidityFact = factory.createFactDeclaration();
					antirigidityFact.setName("antirigidity");
					antirigidityFact.setBlock(factory.createBlock());
					PredicateInvocation pI = AlloyUtil.createAntiRigidityInvocation(factory,sigObject, exists, ontoparser.getAlias(antirigid));					
					antirigidityFact.getBlock().getExpression().add(pI);
					antirigidityFactList.add(antirigidityFact);
				}				
			}			
		}
		for(FactDeclaration fact: antirigidityFactList) module.getParagraph().add(fact);
	}
	
	/**
	 * Populates With Data Type Completeness Rule.
	 */
	protected void populateDataTypeCompletenessRule()
	{
		ArrayList<EObject> dataTypeList = new ArrayList<EObject>();
		dataTypeList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.DataType.class));
		dataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.Enumeration.class));
		dataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.PrimitiveType.class));				

		dataTypeCompletenessFact = factory.createFactDeclaration();
		dataTypeCompletenessFact.setName("dataTypeCompleteness");
		dataTypeCompletenessFact.setBlock(factory.createBlock());

		if (dataTypeList.size()>0)
		{
			if (dataTypeList.size()==1)
			{
				CompareOperation cOp = AlloyUtil.createCompareOperation( factory, sigDatatype.getName(), CompareOperator.EQUAL, ontoparser.getAlias(dataTypeList.get(0)));
				dataTypeCompletenessFact.getBlock().getExpression().add(cOp);
			}else{
						
				CompareOperation cOp = AlloyUtil.createCompareOperation( factory, sigDatatype.getName(), CompareOperator.EQUAL, 
				AlloyUtil.createUnionExpression(factory, ontoparser.getAlias(dataTypeList)).toString());
				dataTypeCompletenessFact.getBlock().getExpression().add(cOp);
			}	
			
			module.getParagraph().add(dataTypeCompletenessFact);
		}		
	}

	/**
	 * 	Populates With Top Level DataTypes Disjointness Rules.
	 */
	protected void populateWithTopLevelDataTypeDisjointnessRules()
	{
		ArrayList<Classifier> topDataTypeList = new ArrayList<Classifier>();
		topDataTypeList.addAll(ontoparser.getTopLevelInstances(RefOntoUML.DataType.class));	
		topDataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.Enumeration.class));
		topDataTypeList.removeAll(ontoparser.getTopLevelInstances(RefOntoUML.PrimitiveType.class));
		
		dataTypeTopLevelFact = factory.createFactDeclaration();
		dataTypeTopLevelFact.setName("topLevelDataTypesDisjointnes");
		dataTypeTopLevelFact.setBlock(factory.createBlock());
				
		if(topDataTypeList.size() > 1)
		{
			ArrayList<DisjointExpression> rulesList = TTopLevelRule.createTopLevelDisjointRules(ontoparser, factory, topDataTypeList);			
			for (DisjointExpression disj : rulesList) 
			{
				dataTypeTopLevelFact.getBlock().getExpression().add(disj); 
			}
			module.getParagraph().add(dataTypeTopLevelFact);
		}
	}
	
	/**
	 * 	Populates With  Top Level Rules Disjointness Rules..
	 */
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
	 * 	Populates With Generalization Rules.
	 */
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
	 * 	Populates With Generalization Set Rules.
	 */
	protected void populateWithGeneralizationSetRules()
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
				
					if(gs.isIsCovering())  
					{
						CompareOperation co = TGeneralizationSetRule.createCompleteCompareOperation(ontoparser, factory, gs);
						if (co!=null) genSetFact.getBlock().getExpression().add(co); 
					}
					
					if(gs.isIsDisjoint())
					{
						DisjointExpression disj =  TGeneralizationSetRule.createDisjointExpression(ontoparser, factory, gs);
						if (disj!=null && disj.getSet().size()>1) genSetFact.getBlock().getExpression().add(disj);	
					}				
				}
			}
		}
		for(FactDeclaration fact: genSetFactList) module.getParagraph().add(fact);
	}
	
	/**
	 * 	Populates With DataTypes Visibility Rule.
	 */
	protected void populateWithVisibilityRule()
	{		
		//dataTypeVisibilityFun = AlloyUtil.createVisibilityFunction("dataTypeVisibility", factory, world);
		//primitiveTypeVisibilityFun = AlloyUtil.createVisibilityFunction("primitiveTypeVisibility", factory, world);		
		//enumerationVisibilityFun = AlloyUtil.createVisibilityFunction("enumerationVisibility", factory, world);	
		visibiliyFun = AlloyUtil.createVisibilityFunction("visible", factory, world);
		
		//ArrayList<String> enumPredList = new ArrayList<String>();
		//ArrayList<String> dataTypePredList = new ArrayList<String>();
		//ArrayList<String> primitivePredList = new ArrayList<String>();
		ArrayList<String> visibilityList  = new ArrayList<String>();
		visibilityList.add("exists");
		
		for(Association assoc: ontoparser.getAllInstances(Association.class))
		{
			RefOntoUML.Type sourceType = assoc.getMemberEnd().get(0).getType();
			RefOntoUML.Type targetType =assoc.getMemberEnd().get(1).getType();			
			String paramName = ontoparser.getAlias(assoc);
			
			if ( (sourceType != null) && (sourceType instanceof DataType) )
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
			if ( (targetType != null) && (targetType instanceof DataType) )
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
		
		for(Property p: ontoparser.getAttributes())
		{			
			RefOntoUML.Type sourceType = (RefOntoUML.Type)p.eContainer();
			RefOntoUML.Type targetType = p.getType();			
			String paramName = ontoparser.getAlias(p);
			
			if ( (sourceType != null) && (sourceType instanceof DataType) )
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
			if ( (targetType != null) && (targetType instanceof DataType) )
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
			visibiliyFun.getBlock().getExpression().add(AlloyUtil.createUnionExpression(factory, visibilityList));
			module.getParagraph().add(visibiliyFun); 
		}		
		//if(primitivePredList.size()>0) 
		//{			
		//	primitiveTypeVisibilityFun.getBlock().getExpression().add(AlloyUtil.createUnionExpression(factory, primitivePredList));
		//	module.getParagraph().add(primitiveTypeVisibilityFun);
		//}
		//if(dataTypePredList.size()>0) 
		//{
		//	dataTypeVisibilityFun.getBlock().getExpression().add(AlloyUtil.createUnionExpression(factory, dataTypePredList));
		//	module.getParagraph().add(dataTypeVisibilityFun);
		//}
	}
}