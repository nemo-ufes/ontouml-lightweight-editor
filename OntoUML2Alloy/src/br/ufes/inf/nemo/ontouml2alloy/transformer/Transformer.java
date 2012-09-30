package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;

import RefOntoUML.AggregationKind;
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
import br.ufes.inf.nemo.alloy.Quantificator;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.UnaryOperator;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.ontouml2alloy.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyUtil;
import br.ufes.inf.nemo.ontouml2alloy.util.OntoUMLUtil;

/**
 *	This class is used to transform every element of the model into alloy. 
 *	First, is created an alloy module skeleton as a base for transformation.
 *  Then, every element of the model is transformed. (i.e. Classifiers, Generalizations, 
 *  GenearlizationSets and Associations). 
 */

public class Transformer {
				
	/* =========================================================================================================*/

	/** 
	 *  Provide a names mapper for ontouml model.
	 *  This mapper is used for associate the elements of the ontouml model 
	 *  with their modified names (i.e. without special characters: #, !, @, $, %, and etc...). 
	 */
	public NamesMapper refmapper;
			
	/** 
	 * Alloy instance. 
	 */
	public AlloyFactory factory = AlloyFactory.eINSTANCE;

	/* =========================================================================================================*/
	
	// initialized by initializeDefaultSignatures() method 
	
	/** Alloy Default Signatures Names. */
	public ArrayList<String> defaultSignatures = new ArrayList<String>();

	/** Alloy Default Signature : sig Object{}. */
	public SignatureDeclaration sigObject;
	
	/** Alloy Default Signature : sig Property{}. */
	public SignatureDeclaration sigProperty;
	
	/** Alloy Default Signature : sig DataType{}. */
	public SignatureDeclaration sigDatatype;
	
	/* =========================================================================================================*/
	
	// initialized by initializeNamesList() method
	
	/** List containing all the Object Class names. */
	public ArrayList<String> objectNamesList = new ArrayList<String>();	
	
	/** List containing all the Moment Class names. */
	public ArrayList<String> propertyNamesList = new ArrayList<String>();
	
	/** List containing all the DataTypes names. */
	public ArrayList<String> datatypeNamesList = new ArrayList<String>();
	
	/** List containing all the names of Substances Sortals that are disjoint. */
	public ArrayList<String> subsortalDisjNamesList = new ArrayList<String>();
	
	/** List containing all the names of Moment Classes that are disjoint. */
	public ArrayList<String> propertyDisjNamesList = new ArrayList<String>();
	
	/** List containing all the names of DataTypes that are disjoint. */
	public ArrayList<String> datatypeDisjNamesList = new ArrayList<String>();
		
	/** List containing all the rigid classifier. */
	public ArrayList<Classifier> rigidElementsList = new ArrayList<Classifier>();
	
	/* =========================================================================================================*/

	// initialized by method initialAditions() :
	
	/** Alloy Module. */
	public AlloyModule module;
	
	/** Alloy Signature World. */
	public SignatureDeclaration world;
	
	/** Alloy World Field exists. */
	public Variable exists;
	
	/** Alloy association_properties Fact Declaration. */
	public FactDeclaration association_properties;

	/** Alloy derivation Fact Declaration. */
	public FactDeclaration derivations;

	/* =========================================================================================================*/
	
	// initialized by transformClassifier()
	
	/** List containing all the facts for relator's rule. */
	public ArrayList<FactDeclaration> relatorConstraintFactList = new ArrayList<FactDeclaration>();

	/** List containing all the facts for weak supplementation's rule. */
	public ArrayList<FactDeclaration> weakSupplementationFactList = new ArrayList<FactDeclaration>();
	
	/* =========================================================================================================*/
	
	/**
	 * Constructor().
	 * 
	 * @param mapper
	 */
	public Transformer (NamesMapper mapper)
	{
		refmapper = mapper;
		
		initializeDefaultSignatures();
		
		initializeNamesLists();
	}
			
	/* =========================================================================================================*/
	
	/**
	 * Initialize Names Lists.
	 */
	private void initializeNamesLists()
	{
		for (PackageableElement pe : refmapper.elementsMap.keySet())
		{			
			if (pe instanceof ObjectClass) 
			{
				objectNamesList.add(refmapper.elementsMap.get(pe));

				if((pe instanceof Kind) || (pe instanceof Collective) || (pe instanceof Quantity))
				{
					subsortalDisjNamesList.add(refmapper.elementsMap.get(pe));
				}
			}
			if (pe instanceof MomentClass) 
			{
				propertyNamesList.add(Transformation.mapper.elementsMap.get(pe));
				
				// all Properties without fathers are naturally disjoint, 
				// which means that multiple inheritance between Properties isn't allowed.
				
				if(((MomentClass)pe).getGeneralization().size() == 0)
				{
					propertyDisjNamesList.add(refmapper.elementsMap.get(pe));
				}
			}
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) 
			{
				datatypeNamesList.add(refmapper.elementsMap.get(pe));
				
				// all dataTypes without fathers are naturally disjoint, 
				// which means that multiple inheritance between dataTypes isn't allowed.
				
				if(((DataType)pe).getGeneralization().size() == 0)
				{
					datatypeDisjNamesList.add(refmapper.elementsMap.get(pe));
				}
			}
			if ( (pe instanceof RigidSortalClass) || (pe instanceof Category) || (pe instanceof MomentClass) || ((pe instanceof DataType)&&!(pe instanceof PrimitiveType)) ) 
			{ 
				rigidElementsList.add((Classifier)pe); 
			}
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Initialize Default Signatures (i.e. Object, Property and DataType).
	 */
	private void initializeDefaultSignatures ()
	{
		for (PackageableElement pe : refmapper.elementsMap.keySet())
		{			
			if (pe instanceof ObjectClass) 
			{
				defaultSignatures.add("Object");
				
				sigObject = factory.createSignatureDeclaration();
				sigObject.setName("Object");
				
				break;
			}
		}
		for (PackageableElement pe : refmapper.elementsMap.keySet())
		{			
			if (pe instanceof MomentClass) 
			{
				defaultSignatures.add("Property");
				
				sigProperty = factory.createSignatureDeclaration();
				sigProperty.setName("Property");
				
				break;
			}
		}
		for (PackageableElement pe : refmapper.elementsMap.keySet())
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
	 *	Creates a skeleton Alloy Module.
	 *   
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void initialAditions() 
	{		
		module = factory.createAlloyModule();
		module.setName(refmapper.refmodelname);				
	
		// abstract sig World {}
		
		world = AlloyUtil.createSigWorld(factory);
		
		// open world_structure[World]
		// open ontological_propertis[World]
		
		ModuleImportation mi1 = AlloyUtil.createModuleImport(factory,"world_structure","", world);		
		ModuleImportation mi2 = AlloyUtil.createModuleImport(factory,"ontological_properties","", world);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		
		// sig Object{}
		// sig Property{} 
		// sig DataType{}
		
		AlloyUtil.createDefaultSignatures(factory,module,defaultSignatures);
		
		// exists: some Object+Property+DataType,
		
		exists = AlloyUtil.createExistsVariableDeclaration(factory,defaultSignatures);
		
		world.getRelation().add(exists.getDeclaration());
		
		module.getParagraph().add(world);
						
		// fact additional_facts {}
		
		FactDeclaration additional_facts = factory.createFactDeclaration();
		additional_facts.setName("additional_facts");
		Block block = factory.createBlock();
		additional_facts.setBlock(block);

		// linear_existence[exists]
		
		PredicateInvocation pI = AlloyUtil.createLinearExistenceInvocation(factory,exists);
		block.getExpression().add(pI);
		
		// all_elements_exists[Object+Property+DataType,exists]
		
		PredicateInvocation pI2 = AlloyUtil.createAllElementsExistsInvocation(factory,exists,defaultSignatures);
		block.getExpression().add(pI2);
		
		if(sigDatatype != null) 
		{
			// always_exists[DataType,exists]
			
			PredicateInvocation pI3 = AlloyUtil.createAlwaysExistsInvocation(factory,exists,sigDatatype);
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
	
	public void finalAdditions()
	{		
		if(objectNamesList.size() > 0) 
			
			// exists:>Object in NamesList[0] + NamesList[1] + ...
			AlloyUtil.createExistsCompareOperationInWorld(factory, exists, world, sigObject, objectNamesList);
		
		if(propertyNamesList.size() > 0) 
		
			// exists:>Property in NamesList[0] + NamesList[1] + ...
			AlloyUtil.createExistsCompareOperationInWorld(factory, exists, world, sigProperty, propertyNamesList);
		
		if(datatypeNamesList.size() > 0) 
		
			// exists:>DataType in NamesList[0] + NamesList[1] + ...
			AlloyUtil.createExistsCompareOperationInWorld(factory, exists, world, sigDatatype, datatypeNamesList);
		
		// disj[ DisjNamesList[0], DisjNamesList[1], ... ],
		
		AlloyUtil.createDisjointExpressionInWorld(factory, world, subsortalDisjNamesList);	
		
		AlloyUtil.createDisjointExpressionInWorld(factory, world, datatypeDisjNamesList);
		
		AlloyUtil.createDisjointExpressionInWorld(factory, world, propertyDisjNamesList);		
		
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
		
		AlloyUtil.createVisibleFunction(factory, module, world, exists);

		// fact all_rigid_classes { rigidity[...] }
		
		createAllRigidClassesFacts();	
		
		//  run { } for 10 but 3 World
		
		AlloyUtil.createDefaultRunComand(factory, module);			
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
					
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigObject, exists, refmapper.elementsMap.get(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}	
				if(rigid instanceof MomentClass)
				{
					// rigidity[ rigidClassName, Property, exists]
					
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigProperty, exists, refmapper.elementsMap.get(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}
				if(rigid instanceof DataType && !(rigid instanceof PrimitiveType))
				{
					// rigidity[ rigidClassName, DataType, exists]
					
					PredicateInvocation pI = AlloyUtil.createRigidityInvocation(factory, sigDatatype, exists, refmapper.elementsMap.get(rigid));
					all_rigid_classes.getBlock().getExpression().add(pI);
				}				
			}			
			module.getParagraph().add(all_rigid_classes);
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Classifiers into Alloy
	 * 
	 * @param c: RefOntoUML.Classifier
	 */
	@SuppressWarnings("unchecked")
	public void transformClassifier(Classifier c) 
	{
		if(c instanceof ObjectClass)
		{
			// ObjectClassName: set exists:>Object,
			
			Declaration decl = AlloyUtil.createDeclaration(factory,exists,refmapper.elementsMap.get(c), sigObject.getName());				
			world.getRelation().add(decl);			

			if ((c instanceof RigidSortalClass) && (OntoUML2Alloy.weakSupplementationRuleFlag))
			{				
				FactDeclaration fact = createWeakSupplementation(c);
				if (fact!=null) weakSupplementationFactList.add(fact);
					
			}
		}
		
		if(c instanceof DataType && !(c instanceof PrimitiveType))
		{			
			// DataTypeName: set exists:>DataType,
			
			Declaration decl = AlloyUtil.createDeclaration(factory,exists,refmapper.elementsMap.get(c), sigDatatype.getName());	
			world.getRelation().add(decl);									
		}
		
		if(c instanceof MomentClass)
		{
			// PropertyName: set exists:>Property,
			
			Declaration decl = AlloyUtil.createDeclaration(factory,exists,refmapper.elementsMap.get(c),sigProperty.getName());
			world.getRelation().add(decl);
			
			if ((c instanceof Relator) && (OntoUML2Alloy.relatorRuleFlag))
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
	 * 
	 * @param c: RefOntoUML.Relator
	 */
	@SuppressWarnings("unchecked")
	private FactDeclaration createRelatorConstraintFact(Relator c) 
	{
		if (c.isIsAbstract()) return null;
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if ( OntoUMLUtil.isAbstractFromGeneralizationSets(refmapper,c)) return null;
		
		if (! OntoUMLUtil.isSourceOfMediationRelation(refmapper,c)) return null;
		
		// get all 'c' mediations
		ArrayList<String> associationNames = new ArrayList<String>();		
		OntoUMLUtil.getAllMediations(refmapper,associationNames, c);		
		
		if(associationNames.size()>0)
		{			
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyUtil.createQuantificationExpression(factory,associationNames, refmapper.elementsMap.get(c));
			
			// create fact from q
			FactDeclaration RelatorRuleFact = factory.createFactDeclaration();
			RelatorRuleFact.setName("relator_constraint_"+refmapper.elementsMap.get(c));
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
  	 *
	 * @param c: RefOntoUML.RigidSortalClass (Functional Complexes)
	 */
	@SuppressWarnings("unchecked")
	private FactDeclaration createWeakSupplementation(Classifier c) 
	{
		if (c.isIsAbstract()) return null;
				
		// isAbstract from generalization Sets (Disjoint and Complete)
		if ( OntoUMLUtil.isAbstractFromGeneralizationSets(refmapper,c)) return null;	
		
		if (! OntoUMLUtil.isSourceOfMeronymicRelation(refmapper,c)) return null;
		
		// get all 'c' meronymics
		ArrayList<String> associationNames = new ArrayList<String>();		
		OntoUMLUtil.getAllMeronymics(refmapper,associationNames, (RigidSortalClass)c);	
		
		if( associationNames.size() > 0)
		{
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = AlloyUtil.createQuantificationExpression(factory,associationNames, refmapper.elementsMap.get(c));
			
			// create fact from q
			FactDeclaration WeakSupplementationFact = factory.createFactDeclaration();
			WeakSupplementationFact.setName("weak_supplementation_"+refmapper.elementsMap.get(c));
			WeakSupplementationFact.setBlock(factory.createBlock());			
			WeakSupplementationFact.getBlock().getExpression().add(q);			
			
			return WeakSupplementationFact;					
		}	
		return null;
	}

	/* =========================================================================================================*/

	/**
	 * Transforms OntoUML Generalizations into Alloy.
	 * 
	 * The child Classifier is a subset of the father Classifier. 
	 * 
	 * "child in father"
	 * 
	 * @param g: RefOntoUML.Generalization
	 */
	@SuppressWarnings("unchecked")
	public void transformGeneralizations(Generalization g) 
	{
		CompareOperation co = AlloyUtil.createCompareOperation( factory, 
				refmapper.elementsMap.get(g.getSpecific()), 
				CompareOperator.SUBSET_LITERAL, 
				refmapper.elementsMap.get(g.getGeneral())
		);
		world.getBlock().getExpression().add(co);
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Create Abstract Clause Rule.
	 * 
	 * BinaryOperation with union operator(+) to represent the completeness
	 * between father(Classifier) and childs.
	 * 
	 * "father = child1 + child2 + child3 + ..." 
	 * 
	 * @param c: RefOntoUML.Classifier
	 */
	@SuppressWarnings("unchecked")
	public void createAbstractClauseRule(Classifier c) 
	{
		// get all generalizations
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		OntoUMLUtil.getAllGeneralizations(refmapper, generalizations, c);
		
		int cont = 1;		
		BinaryOperation bo = factory.createBinaryOperation();
		if(generalizations.size() > 0)
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(refmapper.elementsMap.get(c));
			
			co.setLeftExpression(vr);
			for(Generalization gen : generalizations)
			{
				if(generalizations.size() == 1) 
				{
					vr = factory.createVariableReference();
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));					
					co.setRightExpression(vr);
					break;
				}
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
					bo.setRightExpression(vr);
				}
				cont++;
			}
			world.getBlock().getExpression().add(co);
		}
	}
			
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Generalizations Sets into Alloy.
	 * 
	 * @param gs: RefOntoUML.GeneralizationSet
	 */
	@SuppressWarnings("unchecked")
	public void transformGeneralizationSets(GeneralizationSet gs) 
	{
		if(gs.isIsCovering())
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(refmapper.elementsMap.get(gs.getGeneralization().get(0).getGeneral()));
			
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
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
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
				vr.setVariable(refmapper.elementsMap.get(gen.getSpecific()));
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Transforms OntoUML Derivation Associations into Alloy.
	 * 
	 * @param d: RefOntoUML.Derivation
	 */
	@SuppressWarnings("unchecked")
	public void transformDerivations(Derivation d)
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("derivation");
		
		// material association
		VariableReference material = factory.createVariableReference();
		MaterialAssociation ma = null;		
		for(Property prop : d.getMemberEnd())
		{
			if(prop.getType() instanceof MaterialAssociation)
			{
				material.setVariable(refmapper.elementsMap.get(prop.getType()));
				ma = (MaterialAssociation)prop.getType();
			}
		}		
				
		// source and target of material association
		String class1 = "",class2 = "";		
		int cont = 1;		
		for(Property prop : ma.getMemberEnd())
		{
			if(prop.getType() instanceof Class)
			{
				if(cont==1)
				{
					class1 = prop.getType().getName();
					cont++;
				}
				else
					class2 = prop.getType().getName();
			}
		}

		// relator class
		Relator derRelator = (Relator) (d.relator() instanceof Relator ? d.relator() : d.material());
		
		// mediations associations
		VariableReference mediation1 = factory.createVariableReference();
		VariableReference mediation2 = factory.createVariableReference();		
		cont = 1;
		for (PackageableElement pe : refmapper.elementsMap.keySet())
		{
			if(pe instanceof Mediation)
			{
				int cont2 = 1;
				Relator relator = null;
				Class mediated = null;
				
				for(Property prop : ((Mediation)pe).getMemberEnd())
				{	
					if(prop.getType() instanceof Relator)
					{
						relator = (Relator) prop.getType();
						if(cont2 > 1)
							mediated = (Relator) prop.getType();
						cont2++;
					}
					else if(prop.getType() instanceof Class)
					{
						mediated = (Class) prop.getType();
					}
					
				}
				cont2 = 1;
				if(relator.getName() == derRelator.getName() && (mediated.getName() == class1 || mediated.getName() == class2))
				{
					if(cont == 1)
					{
						mediation1.setVariable(refmapper.elementsMap.get(((Mediation)pe)));
						cont++;
					}
					else
					{
						mediation2.setVariable(refmapper.elementsMap.get(((Mediation)pe)));
						break;
					}
				}
			}
		}
		pI.getParameter().add(material);
		pI.getParameter().add(mediation1);
		pI.getParameter().add(mediation2);
		derivations.getBlock().getExpression().add(pI);
	}
	
	/* ============================================================================*/
	
	/**
	 * Transforms OntoUML Associations into Alloy.
	 * 
	 * @param ass: RefOntoUML.Association
	 */
	@SuppressWarnings("unchecked")
	public void transformAssociations(Association ass) 
	{
		Variable var = factory.createVariable();
		VariableReference source = factory.createVariableReference();
		VariableReference target = factory.createVariableReference();
		VariableReference association = factory.createVariableReference();
		Declaration decl = factory.createDeclaration();
		UnaryOperation uOp = factory.createUnaryOperation();
		ArrowOperation aOp = factory.createArrowOperation();
		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		var.setName(refmapper.elementsMap.get(ass)); 
		var.setDeclaration(decl);
		
		association.setVariable(refmapper.elementsMap.get(ass));	
		
		decl.setExpression(uOp);		
		
		// Characterization
		if(ass instanceof Characterization)
			prepareCharacterizationAssociation(ass, source, target, aOp);
		
		// Mediation
		else if(ass instanceof Mediation)
			prepareMediationAssociation(ass, source, target, aOp);
		
		// Meronymic
		else if(ass instanceof Meronymic)
			prepareMeronymicAssociation((Meronymic)ass, source, target, aOp);
		
		// Association
		else if(!(ass instanceof Derivation))
			prepareAssociation(ass, source, target, aOp);
		
		// Association Ends
		if(ass.getMemberEnd().get(0).getName() != null || ass.getMemberEnd().get(1).getName() != null)
			transformAssociationsEnds(ass,source,target);		
		
		uOp.setExpression(aOp);
		world.getRelation().add(decl);
	}
	
	/* ============================================================================*/
	
	/**
	 * 
	 * Transforms Meronymic Association into Alloy.
	 * 
	 * @param ass: RefOntoUML.Meronymic ass
	 * @param source: VariableReference source
	 * @param target: VariableReference target
	 * @param aOp: ArrowOperation aOp
	 */
	@SuppressWarnings("unchecked")
	private void prepareMeronymicAssociation(Meronymic ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		
		for(Property prop : ass.getMemberEnd())
		{
			if(! prop.getAggregation().equals(AggregationKind.NONE))
			{
				source.setVariable(refmapper.elementsMap.get(prop.getType()));				
				lowerSource = prop.getLower();
				upperSource = prop.getUpper();				
			}
			else
			{
				target.setVariable(refmapper.elementsMap.get(prop.getType()));				
				lowerTarget = prop.getLower();				
				upperTarget = prop.getUpper();
			}
		}
		
		if(ass instanceof subQuantityOf)
		{
			PredicateInvocation pI = factory.createPredicateInvocation();
			pI.setPredicate("immutable_target");
			VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
			param1.setVariable(source.getVariable());
			param2.setVariable(refmapper.elementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
			if(ass.sourceEnd().isIsReadOnly() || ass.isIsInseparable() || ass.isIsImmutableWhole())
			{
				pI = factory.createPredicateInvocation();
				pI.setPredicate("immutable_source");
				param1 = factory.createVariableReference();
				param2 = factory.createVariableReference();
				param1.setVariable(target.getVariable());
				param2.setVariable(refmapper.elementsMap.get(ass));
				pI.getParameter().add(param1);
				pI.getParameter().add(param2);
				association_properties.getBlock().getExpression().add(pI);
			}
		}
		else
		{
			if(ass.targetEnd().isIsReadOnly() || ass.isIsEssential() || ass.isIsImmutablePart())
			{
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("immutable_target");
				VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
				param1.setVariable(source.getVariable());
				param2.setVariable(refmapper.elementsMap.get(ass));
				pI.getParameter().add(param1);
				pI.getParameter().add(param2);
				association_properties.getBlock().getExpression().add(pI);
			}
			if(ass.sourceEnd().isIsReadOnly() || ass.isIsInseparable() || ass.isIsImmutableWhole())
			{
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("immutable_source");
				VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
				param1.setVariable(target.getVariable());
				param2.setVariable(refmapper.elementsMap.get(ass));
				pI.getParameter().add(param1);
				pI.getParameter().add(param2);
				association_properties.getBlock().getExpression().add(pI);
			}
		}
					
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
	/**
	 * 
	 * Transforms Mediation Association into Alloy.
	 * 
	 * @param ass: RefOntoUML.Meronymic ass
	 * @param source: VariableReference source
	 * @param target: VariableReference target
	 * @param aOp: ArrowOperation aOp
	 */
	@SuppressWarnings("unchecked")
	private void prepareMediationAssociation(Association ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int cont = 1;
		boolean isSourceReadOnly = false;
		for(Property c : ass.getOwnedEnd())
		{
			if(c.getType() instanceof Class)
			{
				if(c.getType() instanceof Relator && cont == 1)
				{
					source.setVariable(refmapper.elementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(refmapper.elementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(refmapper.elementsMap.get(ass));
		pI.getParameter().add(param1);
		pI.getParameter().add(param2);
		association_properties.getBlock().getExpression().add(pI);
		
		if(isSourceReadOnly)
		{
			pI = factory.createPredicateInvocation();
			pI.setPredicate("immutable_source");
			param1 = factory.createVariableReference();
			param2 = factory.createVariableReference();
			param1.setVariable(target.getVariable());
			param2.setVariable(refmapper.elementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
	/**
	 * 
	 * Transforms Characterization Association into Alloy.
	 * 
 	 * @param ass: RefOntoUML.Meronymic ass
	 * @param source: VariableReference source
	 * @param target: VariableReference target
	 * @param aOp: ArrowOperation aOp
	 */
	@SuppressWarnings("unchecked")
	private void prepareCharacterizationAssociation(Association ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int cont = 1;
		boolean isSourceReadOnly = false;
		for(Property c : ass.getOwnedEnd())
		{
			if(c.getType() instanceof Class)
			{
				if(c.getType() instanceof Mode && cont == 1)
				{
					source.setVariable(refmapper.elementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(refmapper.elementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(refmapper.elementsMap.get(ass));
		pI.getParameter().add(param1);
		pI.getParameter().add(param2);
		association_properties.getBlock().getExpression().add(pI);
		
		if(isSourceReadOnly)
		{
			pI = factory.createPredicateInvocation();
			pI.setPredicate("immutable_source");
			param1 = factory.createVariableReference();
			param2 = factory.createVariableReference();
			param1.setVariable(target.getVariable());
			param2.setVariable(refmapper.elementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
	/**
	 * 
	 * Transforms Association that are not Derivations into Alloy.
	 * 
 	 * @param ass: RefOntoUML.Meronymic ass
	 * @param source: VariableReference source
	 * @param target: VariableReference target
	 * @param aOp: ArrowOperation aOp
	 */
	@SuppressWarnings("unchecked")
	private void prepareAssociation(Association ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		int cont = 1;
		boolean isTargetReadOnly = false, isSourceReadOnly = false;
		if(ass instanceof Derivation)
			return;
		for(Property c : ass.getOwnedEnd())
		{
			if(c.getType() instanceof Classifier)
			{
				if(cont == 1)
				{
					source.setVariable(refmapper.elementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(refmapper.elementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
					isTargetReadOnly = c.isIsReadOnly();
				}
			}
		}
		
		if(isTargetReadOnly)
		{
			PredicateInvocation pI = factory.createPredicateInvocation();
			pI.setPredicate("immutable_target");
			VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
			param1.setVariable(source.getVariable());
			param2.setVariable(refmapper.elementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		if(isSourceReadOnly)
		{
			PredicateInvocation pI = factory.createPredicateInvocation();
			pI.setPredicate("immutable_source");
			VariableReference param1 = factory.createVariableReference(), param2 = factory.createVariableReference();
			param1.setVariable(target.getVariable());
			param2.setVariable(refmapper.elementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
	/**
	 * 
	 * Set cardinalities of Alloy Arrow Operation...
	 * 
	 * @param aOp: Arrow Operation
	 * @param lowerSource
	 * @param upperSource
	 * @param lowerTarget
	 * @param upperTarget
	 * @param source : VariableReference source
	 * @param target : VariableReference target
	 * @param ass: RefOntoUML.Association
	 */
	private void setCardinalities(ArrowOperation aOp, int lowerSource, int upperSource, int lowerTarget, int upperTarget, 
	VariableReference source, VariableReference target, Association ass) 
	{
		// Source Cardinality
		if(lowerSource == 1 && upperSource == 1)
			aOp.setLeftMultiplicity(Multiplicity.ONE_LITERAL);
		else
			if(lowerSource == 0 && upperSource == 1)
				aOp.setLeftMultiplicity(Multiplicity.LONE_LITERAL);
			else
				if(lowerSource >= 1)
					aOp.setLeftMultiplicity(Multiplicity.SOME_LITERAL);
				else
					aOp.setLeftMultiplicity(Multiplicity.SET_LITERAL);
		
		// Target Cardinality
		if(lowerTarget == 1 && upperTarget == 1)
			aOp.setRightMultiplicity(Multiplicity.ONE_LITERAL);
		else
			if(lowerTarget == 0 && upperTarget == 1)
				aOp.setRightMultiplicity(Multiplicity.LONE_LITERAL);
			else
				if(lowerTarget >= 1)
					aOp.setRightMultiplicity(Multiplicity.SOME_LITERAL);
				else
					aOp.setRightMultiplicity(Multiplicity.SET_LITERAL);
		
		// Cardinalities that are different of 1, 0 or *
		if(lowerSource > 1)
		{
			lowerSourceCardinalities(lowerSource, target, ass);
		}
		if (upperSource > 1 && upperSource != -1)
		{
			upperSourceCardinalities(upperSource, target, ass);
		}
		if(lowerTarget > 1)
		{
			lowerTargetCardinalities(lowerTarget, source, ass);
		}
		if (upperTarget > 1 && upperTarget != -1)
		{
			upperTargetCardinalities(upperTarget, source, ass);
		}
	}
	
	/* ============================================================================*/
	
	/**
	 * 
	 * @param upperSource
	 * @param target
	 * @param ass
	 */
	@SuppressWarnings("unchecked")
	private void upperTargetCardinalities(int upperSource, VariableReference target, Association ass) 
	{		
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(Quantificator.ALL_LITERAL);
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(target.getVariable());
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		
		BinaryOperation boJoin = factory.createBinaryOperation();
		CompareOperation co = factory.createCompareOperation();
		UnaryOperation uOp = factory.createUnaryOperation();
		
		boJoin.setOperator(BinaryOperator.JOIN_LITERAL);
		vr = factory.createVariableReference();
		vr.setVariable(Transformation.mapper.elementsMap.get(ass));
		boJoin.setRightExpression(vr);
		vr = factory.createVariableReference();
		vr.setVariable(var.getName());
		boJoin.setLeftExpression(vr);
		
		co.setOperator(CompareOperator.LESS_EQUAL_LITERAL);
		co.setLeftExpression(uOp);
		vr = factory.createVariableReference();
		vr.setVariable(String.valueOf(upperSource));
		co.setRightExpression(vr);
		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);
		uOp.setExpression(boJoin);
		
		qe.setExpression(co);
		
		world.getBlock().getExpression().add(qe);
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	private void lowerTargetCardinalities(int lowerSource, VariableReference target, Association ass) 
	{		
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(Quantificator.ALL_LITERAL);
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(target.getVariable());
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		
		BinaryOperation boJoin = factory.createBinaryOperation();
		CompareOperation co = factory.createCompareOperation();
		UnaryOperation uOp = factory.createUnaryOperation();
		
		boJoin.setOperator(BinaryOperator.JOIN_LITERAL);
		vr = factory.createVariableReference();
		vr.setVariable(Transformation.mapper.elementsMap.get(ass));
		boJoin.setRightExpression(vr);
		vr = factory.createVariableReference();
		vr.setVariable(var.getName());
		boJoin.setLeftExpression(vr);
		
		co.setOperator(CompareOperator.GREATER_EQUAL_LITERAL);
		co.setLeftExpression(uOp);
		vr = factory.createVariableReference();
		vr.setVariable(String.valueOf(lowerSource));
		co.setRightExpression(vr);
		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);
		uOp.setExpression(boJoin);
		
		qe.setExpression(co);
		
		world.getBlock().getExpression().add(qe);
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	private void upperSourceCardinalities(int upperSource, VariableReference target, Association ass) 
	{		
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(Quantificator.ALL_LITERAL);
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(target.getVariable());
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		
		BinaryOperation boJoin = factory.createBinaryOperation();
		CompareOperation co = factory.createCompareOperation();
		UnaryOperation uOp = factory.createUnaryOperation();
		
		boJoin.setOperator(BinaryOperator.JOIN_LITERAL);
		vr = factory.createVariableReference();
		vr.setVariable(Transformation.mapper.elementsMap.get(ass));
		boJoin.setLeftExpression(vr);
		vr = factory.createVariableReference();
		vr.setVariable(var.getName());
		boJoin.setRightExpression(vr);
		
		co.setOperator(CompareOperator.LESS_EQUAL_LITERAL);
		co.setLeftExpression(uOp);
		vr = factory.createVariableReference();
		vr.setVariable(String.valueOf(upperSource));
		co.setRightExpression(vr);
		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);
		uOp.setExpression(boJoin);
		
		qe.setExpression(co);
		
		world.getBlock().getExpression().add(qe);
	}

	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	private void lowerSourceCardinalities(int lowerSource, VariableReference target, Association ass) 
	{		
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(Quantificator.ALL_LITERAL);
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(target.getVariable());
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		
		BinaryOperation boJoin = factory.createBinaryOperation();
		CompareOperation co = factory.createCompareOperation();
		UnaryOperation uOp = factory.createUnaryOperation();
		
		boJoin.setOperator(BinaryOperator.JOIN_LITERAL);
		vr = factory.createVariableReference();
		vr.setVariable(Transformation.mapper.elementsMap.get(ass));
		boJoin.setLeftExpression(vr);
		vr = factory.createVariableReference();
		vr.setVariable(var.getName());
		boJoin.setRightExpression(vr);
		
		co.setOperator(CompareOperator.GREATER_EQUAL_LITERAL);
		co.setLeftExpression(uOp);
		vr = factory.createVariableReference();
		vr.setVariable(String.valueOf(lowerSource));
		co.setRightExpression(vr);
		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);
		uOp.setExpression(boJoin);
		
		qe.setExpression(co);
		
		world.getBlock().getExpression().add(qe);
	}

	

	/**
	 * Transforms OntoUML Association Ends into Alloy.
	 * 
	 * @param ass: RefOntoUML.Association
	 * @param source: VariableReference source
	 * @param target: VariableRefernce target 
	 */
	@SuppressWarnings("unchecked")
	public void transformAssociationsEnds(Association ass, VariableReference source, VariableReference target) 
	{		
		if(ass.getMemberEnd().get(0).getName() != null)
		if(ass.getMemberEnd().get(0).getName().compareTo("") != 0)	
		{
			FunctionDeclaration fun = factory.createFunctionDeclaration();
			fun.setName(refmapper.assocEndMap.get(ass.getOwnedEnd().get(0)));
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(refmapper.elementsMap.get(ass.getOwnedEnd().get(0).getType()));
			VariableReference vr2 = factory.createVariableReference();
			vr2.setVariable(world.getName());
			bOp.setLeftExpression(vr2);
			bOp.setRightExpression(vr);
			
			uOp.setExpression(bOp);
			fun.setType(uOp);
			
			Declaration decl = factory.createDeclaration();
			Variable var = factory.createVariable();
			var.setName("x");
			var.setDeclaration(decl);
			
			bOp = factory.createBinaryOperation();
			vr = factory.createVariableReference();
			vr2 = factory.createVariableReference();
			vr.setVariable(world.getName());
			vr2.setVariable(refmapper.elementsMap.get(ass.getOwnedEnd().get(1).getType()));
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			decl.setExpression(bOp);
			
			fun.getParameter().add(decl);
			
			decl = factory.createDeclaration();
			var = factory.createVariable();
			var.setName("w");
			var.setDeclaration(decl);
			
			vr = factory.createVariableReference();
			vr.setVariable(world.getName());
			decl.setExpression(vr);
			
			fun.getParameter().add(decl);
						
			bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			vr = factory.createVariableReference();
			vr.setVariable("w");
			vr2 = factory.createVariableReference();
			vr2.setVariable(refmapper.elementsMap.get(ass));
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(refmapper.elementsMap.get(ass.getMemberEnd().get(0).getType()) == target.getVariable())
			{
				bOp2.setLeftExpression(vr);
				bOp2.setRightExpression(bOp);
			}
			else
			{
				bOp2.setLeftExpression(bOp);
				bOp2.setRightExpression(vr);
			}
			
			fun.setExpression(bOp2);
			
			module.getParagraph().add(fun);			
		}
		
		if(ass.getMemberEnd().get(1).getName() != null)
		if(ass.getMemberEnd().get(1).getName().compareTo("") != 0)
		{
			FunctionDeclaration fun = factory.createFunctionDeclaration();
			fun.setName(refmapper.assocEndMap.get(ass.getOwnedEnd().get(1)));
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(refmapper.elementsMap.get(ass.getOwnedEnd().get(1).getType()));
			VariableReference vr2 = factory.createVariableReference();
			vr2.setVariable(world.getName());
			bOp.setLeftExpression(vr2);
			bOp.setRightExpression(vr);
			
			uOp.setExpression(bOp);
			fun.setType(uOp);
			
			Declaration decl = factory.createDeclaration();
			Variable var = factory.createVariable();
			var.setName("x");
			var.setDeclaration(decl);
			
			bOp = factory.createBinaryOperation();
			vr = factory.createVariableReference();
			vr2 = factory.createVariableReference();
			vr.setVariable(world.getName());
			vr2.setVariable(refmapper.elementsMap.get(ass.getOwnedEnd().get(0).getType()));
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			decl.setExpression(bOp);
			
			fun.getParameter().add(decl);
			
			decl = factory.createDeclaration();
			var = factory.createVariable();
			var.setName("w");
			var.setDeclaration(decl);
			
			vr = factory.createVariableReference();
			vr.setVariable(world.getName());
			decl.setExpression(vr);
			
			fun.getParameter().add(decl);			
			
			bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			vr = factory.createVariableReference();
			vr.setVariable("w");
			vr2 = factory.createVariableReference();
			vr2.setVariable(refmapper.elementsMap.get(ass));
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(refmapper.elementsMap.get(ass.getMemberEnd().get(1).getType()) == target.getVariable())
			{
				bOp2.setLeftExpression(vr);
				bOp2.setRightExpression(bOp);
			}
			else
			{
				bOp2.setLeftExpression(bOp);
				bOp2.setRightExpression(vr);
			}
			
			fun.setExpression(bOp2);
			
			module.getParagraph().add(fun);			
		}	
	}

}
