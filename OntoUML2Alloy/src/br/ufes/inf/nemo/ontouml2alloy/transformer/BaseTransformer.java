package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;

import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Kind;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Quantity;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyModule;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.api.AlloyAPI;

public class BaseTransformer {
	
	/** Alloy Module. */
	public AlloyModule module;
	
	/** Alloy Signature World. */
	public SignatureDeclaration world;
	
	/** Alloy World Field exists. */
	public Variable exists;	

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
	
	/*===========================================*/
	
	// additional informations
	
	/** Alloy Default Signatures Names. */
	public ArrayList<String> defaultSignatures = new ArrayList<String>();

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
	
	/*===========================================*/
	
	/** 
	 * Constructor
	 */
	public BaseTransformer (OntoUMLParser ontoparser, AlloyFactory alsfactory)
	{
		initializeDefaultSignatures(ontoparser, alsfactory);
		
		initializeNamesLists(ontoparser,alsfactory);
		
		makeSkeleton(ontoparser,alsfactory);
	}
	
	/*===========================================*/
	
	/**
	 * Initialize Default Signatures (i.e. Object, Property and DataType).
	 */
	private void initializeDefaultSignatures (OntoUMLParser ontoparser, AlloyFactory factory)
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
	
	/**
	 * Initialize Names Lists.
	 */
	private void initializeNamesLists(OntoUMLParser ontoparser, AlloyFactory factory)
	{
		for (PackageableElement pe : ontoparser.getPackageableElements())
		{			
			if (pe instanceof ObjectClass) 
			{
				objectNamesList.add(ontoparser.getAlias(pe));

				if((pe instanceof Kind) || (pe instanceof Collective) || (pe instanceof Quantity))
				{
					subsortalDisjNamesList.add(ontoparser.getAlias(pe));
				}
			}
			if (pe instanceof MomentClass) 
			{
				propertyNamesList.add(ontoparser.getAlias(pe));
				
				// all Properties without fathers are naturally disjoint, 
				// which means that multiple inheritance between Properties isn't allowed.
				
				if(((MomentClass)pe).getGeneralization().size() == 0)
				{
					propertyDisjNamesList.add(ontoparser.getAlias(pe));
				}
			}
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) 
			{
				datatypeNamesList.add(ontoparser.getAlias(pe));
				
				// all dataTypes without fathers are naturally disjoint, 
				// which means that multiple inheritance between dataTypes isn't allowed.
				
				if(((DataType)pe).getGeneralization().size() == 0)
				{
					datatypeDisjNamesList.add(ontoparser.getAlias(pe));
				}
			}			
		}
	}	
	
	/**
	 *	Creates a skeleton Alloy Module as Initial Additions.
	 */
	@SuppressWarnings("unchecked")
	public void makeSkeleton(OntoUMLParser ontoparser, AlloyFactory factory) 
	{		
		module = factory.createAlloyModule();
		module.setName(ontoparser.getModelName());				
	
		// abstract sig World {}
		
		world = AlloyAPI.createSigWorld(factory);
		
		// open world_structure[World]
		// open ontological_propertis[World]
		// open util/relation
		
		ModuleImportation mi1 = AlloyAPI.createModuleImport(factory,"world_structure","", world);		
		ModuleImportation mi2 = AlloyAPI.createModuleImport(factory,"ontological_properties","", world);
		ModuleImportation mi3 = AlloyAPI.createModuleImport(factory,"relation","util", null);
		module.getImports().add(mi1);
		module.getImports().add(mi2);
		module.getImports().add(mi3);
		
		// sig Object{}
		// sig Property{} 
		// sig DataType{}
		
		AlloyAPI.createDefaultSignatures(factory,module,defaultSignatures);
		
		// exists: some Object+Property+DataType,
		
		exists = AlloyAPI.createExistsVariableDeclaration(factory,defaultSignatures);
		
		world.getRelation().add(exists.getDeclaration());
		
		module.getParagraph().add(world);
						
		// fact additional_facts {}
		
		additional_facts = factory.createFactDeclaration();
		additional_facts.setName("additional_facts");
		Block block = factory.createBlock();
		additional_facts.setBlock(block);

		// linear_existence[exists]
		
		PredicateInvocation pI = AlloyAPI.createLinearExistenceInvocation(factory,exists);
		if (pI!=null)block.getExpression().add(pI);
		
		// all_elements_exists[Object+Property+DataType,exists]
		
		PredicateInvocation pI2 = AlloyAPI.createAllElementsExistsInvocation(factory,exists,defaultSignatures);
		if (pI!=null) block.getExpression().add(pI2);
		
		if(sigDatatype != null) 
		{
			// always_exists[DataType,exists]
			
			PredicateInvocation pI3 = AlloyAPI.createAlwaysExistsInvocation(factory,exists,sigDatatype);
			if (pI3!=null) block.getExpression().add(pI3);
		}
		
		module.getParagraph().add(additional_facts);
		
		// fact association_properties {}
		
		association_properties = factory.createFactDeclaration();
		association_properties.setName("association_properties");
		association_properties.setBlock(factory.createBlock());
		
		module.getParagraph().add(association_properties);
		
		// fact derivations {}
		
		derivations = factory.createFactDeclaration();
		derivations.setName("derivations");
		derivations.setBlock(factory.createBlock());
		
		module.getParagraph().add(derivations);		
		
		// fact all_rigid_classes { }
		
		all_rigid_classes = factory.createFactDeclaration();
		all_rigid_classes.setName("all_rigid_classes");
		all_rigid_classes.setBlock(factory.createBlock());
		
		// fact all_antirigid_classes { }
		
		all_antirigid_classes = factory.createFactDeclaration();
		all_antirigid_classes.setName("all_antirigid_classes");
		all_antirigid_classes.setBlock(factory.createBlock());
				
	}
}
