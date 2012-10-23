package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.util.ArrayList;

import RefOntoUML.Category;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Kind;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Quantity;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;

import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;

import br.ufes.inf.nemo.ontouml2alloy.api.OntoUMLAPI;

import br.ufes.inf.nemo.ontouml2alloy.parser.OntoUMLParser;

public class Info {

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

	
	/** List containing all the rigid classifiers. */
	public ArrayList<Classifier> rigidElementsList = new ArrayList<Classifier>();
	
	/** List containing all the top level classifiers. */
	public ArrayList<Classifier> topLevelElementsList = new ArrayList<Classifier>();
	
	/** List containing all the antirigid classifiers. */
	public ArrayList<Classifier> antirigidElementsList = new ArrayList<Classifier>();
	
	
	/** Alloy Default Signature : sig Object{}. */
	public SignatureDeclaration sigObject;
	
	/** Alloy Default Signature : sig Property{}. */
	public SignatureDeclaration sigProperty;
	
	/** Alloy Default Signature : sig DataType{}. */
	public SignatureDeclaration sigDatatype;
	
	
	/** 
	 * Constructor
	 */
	public Info (OntoUMLParser ontoparser, AlloyFactory factory) 
	{
		initializeDefaultSignatures(ontoparser,factory);
		
		initializeNamesLists(ontoparser, factory);		
	}
	
	/**
	 * Initialize Default Signatures (i.e. Object, Property and DataType).
	 */
	public void initializeDefaultSignatures (OntoUMLParser ontoparser, AlloyFactory factory)
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
	public void initializeNamesLists(OntoUMLParser ontoparser, AlloyFactory factory)
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
				if (OntoUMLAPI.isTopLevel((Classifier)pe)) topLevelElementsList.add((Classifier)pe);
			}
			
			if ((pe instanceof Role) || (pe instanceof RoleMixin) || (pe instanceof Phase))
			{
				antirigidElementsList.add((Classifier)pe);
			}
		}
	}	
}
