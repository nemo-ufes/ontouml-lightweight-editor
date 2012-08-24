package br.ufes.inf.nemo.refontouml2alloy;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import RefOntoUML.Association;
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
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.ontouml.alloy.AlloyFactory;
import br.ufes.inf.nemo.ontouml.alloy.AlloyModule;
import br.ufes.inf.nemo.ontouml.alloy.AlloyPackage;
import br.ufes.inf.nemo.ontouml.alloy.ArrowOperation;
import br.ufes.inf.nemo.ontouml.alloy.BinaryOperation;
import br.ufes.inf.nemo.ontouml.alloy.BinaryOperator;
import br.ufes.inf.nemo.ontouml.alloy.Block;
import br.ufes.inf.nemo.ontouml.alloy.CommandDeclaration;
import br.ufes.inf.nemo.ontouml.alloy.CompareOperation;
import br.ufes.inf.nemo.ontouml.alloy.CompareOperator;
import br.ufes.inf.nemo.ontouml.alloy.Declaration;
import br.ufes.inf.nemo.ontouml.alloy.DisjointExpression;
import br.ufes.inf.nemo.ontouml.alloy.Expression;
import br.ufes.inf.nemo.ontouml.alloy.FactDeclaration;
import br.ufes.inf.nemo.ontouml.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.ontouml.alloy.GenericScope;
import br.ufes.inf.nemo.ontouml.alloy.ModuleImportation;
import br.ufes.inf.nemo.ontouml.alloy.Multiplicity;
import br.ufes.inf.nemo.ontouml.alloy.PredicateInvocation;
import br.ufes.inf.nemo.ontouml.alloy.QuantificationExpression;
import br.ufes.inf.nemo.ontouml.alloy.Quantificator;
import br.ufes.inf.nemo.ontouml.alloy.Scopeable;
import br.ufes.inf.nemo.ontouml.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.ontouml.alloy.SignatureReference;
import br.ufes.inf.nemo.ontouml.alloy.UnaryOperation;
import br.ufes.inf.nemo.ontouml.alloy.UnaryOperator;
import br.ufes.inf.nemo.ontouml.alloy.Variable;
import br.ufes.inf.nemo.ontouml.alloy.VariableReference;
import br.ufes.inf.nemo.ontouml.alloy.impl.AlloyPackageImpl;
import br.ufes.inf.nemo.ontouml.alloy.util.AlloyResourceFactoryImpl;

public class Transformer {
	
	public static Resource alsresource;	
	
	/* ============================================================================*/
	
	public static AlloyFactory factory = AlloyFactory.eINSTANCE;
	public AlloyModule module;
	public SignatureDeclaration world;
	public Variable exists;
	public SignatureDeclaration Object = null;
	public SignatureDeclaration Property = null;
	public SignatureDeclaration Datatype = null;
	public FactDeclaration association_properties = null;
	public FactDeclaration derivations = null;	
	public ArrayList<String> defaultSignatures = new ArrayList<String>();
	
	/* ============================================================================*/
	
	public static String path = "models/out.xmi";
	public FactDeclaration all_rigid_classes;			
	public ArrayList<String> ObjectsList = new ArrayList<String>();
	public ArrayList<String> PropertiesList = new ArrayList<String>();
	public ArrayList<String> datatypesList = new ArrayList<String>();	
	public ArrayList<Classifier> rigidElements = new ArrayList<Classifier>();	
	public ArrayList<String> PropertysListDisj = new ArrayList<String>();
	public ArrayList<String> datatypeListDisj = new ArrayList<String>();
	public ArrayList<String> kindsListDisj = new ArrayList<String>();
	
	/* ============================================================================*/
		
	public ArrayList<String> sigsTop = new ArrayList<String>();
	public BinaryOperation boSMD;

	/* ============================================================================*/
	
	public void init()
	{
		initAlsResource();
		
		createAlloyModule();
		
		Reader.callTransformationModel();
		
		saveAls();
	}
	
	/* ============================================================================*/
	
	public static void initAlsResource() 
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new AlloyResourceFactoryImpl() );
		resourceSet.getPackageRegistry().put(AlloyPackage.eNS_URI,AlloyPackage.eINSTANCE);
		AlloyPackageImpl.init();	
		alsresource = resourceSet.createResource(URI.createURI(path));
	}
	
	/* ============================================================================*/
			
	@SuppressWarnings("unchecked")
	private void createAlloyModule() 
	{
		// module
		module = factory.createAlloyModule();
		module.setName(Reader.modelName); 
		alsresource.getContents().add(module);		
		
		// World 
		world = factory.createSignatureDeclaration();
		world.setExists(false);
		world.setIsAbstract(true);
		world.setBlock(factory.createBlock());
		world.setName("World");
				
		// open world_structure[World]
		ModuleImportation ws = factory.createModuleImportation();		
		ws.setName("world_structure");
		ws.setPath(""); 
		module.getImports().add(ws);
		ws.getParameters().add(world.getName());
		
		// open ontological_propertis[World]
		ModuleImportation op = factory.createModuleImportation();
		op.setName("ontological_properties");
		op.setPath(""); 
		module.getImports().add(op);		
		op.getParameters().add(world.getName());
		
		// exists
		exists = factory.createVariable();
		exists.setName("exists");
		Declaration declaration = factory.createDeclaration();
		declaration.getVariable().add(exists);
		exists.setDeclaration(declaration);
		UnaryOperation uOp = factory.createUnaryOperation();
		uOp.setOperator(UnaryOperator.SOME_LITERAL);
		
		// Object, Property, DataType
		createSigSubtantialPropertyDatatype();
		
		uOp.setExpression(createExpUnionObjectPropertyDatatype());
		declaration.setExpression(uOp);
		
		world.getRelation().add(exists.getDeclaration());
		
		module.getParagraph().add(world);
		
		// additional_facts
		FactDeclaration additional_facts = factory.createFactDeclaration();
		additional_facts.setName("additional_facts");
		Block block = factory.createBlock();
		additional_facts.setBlock(block);

		// linear_existence predicate
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("linear_existence");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		block.getExpression().add(pI);
		
		// all_elements_exists predicate
		pI = factory.createPredicateInvocation();
		pI.setPredicate("all_elements_exists");
		pI.getParameter().add(createExpUnionObjectPropertyDatatype());
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		block.getExpression().add(pI);
		
		// always_exists DataType predicate
		if(Datatype != null) {
			pI = factory.createPredicateInvocation();
			pI.setPredicate("always_exists");
			SignatureReference srDataType = factory.createSignatureReference();
			srDataType.setSignature(Datatype.getName());
			pI.getParameter().add(srDataType);
			vr = factory.createVariableReference();
			vr.setVariable(exists.getName());
			pI.getParameter().add(vr);
			block.getExpression().add(pI);
		}
		module.getParagraph().add(additional_facts);
		
		// association_properties
		Block b1 = factory.createBlock();
		association_properties = factory.createFactDeclaration();
		association_properties.setName("association_properties");
		association_properties.setBlock(b1);
		module.getParagraph().add(association_properties);
		
		// derivation
		b1 = factory.createBlock();
		derivations = factory.createFactDeclaration();
		derivations.setName("derivations");
		derivations.setBlock(b1);
		module.getParagraph().add(derivations);
		
	}
	
	/* ============================================================================*/
	
	private Expression createExpUnionObjectPropertyDatatype() 
	{
		int cont = 1;		
		BinaryOperation bo = null;
		Expression exp = null;
		for(String sigElement : defaultSignatures)
		{
			if(defaultSignatures.size() == 1)
			{
				SignatureReference sr = factory.createSignatureReference();
				sr.setSignature(sigElement);
				exp = sr;
			} else {
				if(cont == 1)
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo = factory.createBinaryOperation();
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					bo.setLeftExpression(sr);
					exp = bo;
				}
				if(cont > 1 && cont != defaultSignatures.size())
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(sr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == defaultSignatures.size())
				{
					SignatureReference sr = factory.createSignatureReference();
					sr.setSignature(sigElement);
					bo.setRightExpression(sr);
				}
			}
			cont = cont + 1;
		}
		return exp;
	}
		
	private void createSigSubtantialPropertyDatatype() 
	{
		int cont = 1;
		SignatureDeclaration sigDecl = null;
		for(String sigElement : defaultSignatures)
		{
			if(cont == 1)
			{
				sigDecl = factory.createSignatureDeclaration();
				sigDecl.setName(sigElement);
				module.getParagraph().add(sigDecl);
			}
			if(cont > 1 && cont != defaultSignatures.size())
			{
				sigDecl = factory.createSignatureDeclaration();
				sigDecl.setName(sigElement);
				module.getParagraph().add(sigDecl);
			}
			if(cont > 1 && cont == defaultSignatures.size())
			{
				sigDecl = factory.createSignatureDeclaration();
				sigDecl.setName(sigElement);
				module.getParagraph().add(sigDecl);
			}
			if(sigDecl.getName().equals("Object"))
				Object = sigDecl;
			if(sigDecl.getName().equals("Property"))
				Property = sigDecl;
			if(sigDecl.getName().equals("DataType"))
				Datatype = sigDecl;
			cont = cont + 1;
		}
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void createAbstractClause(Classifier c) 
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		
		// Get all generalizations that the Classifier c is the father
		for(PackageableElement elem : Reader.modelElementsMap.keySet() )
		{
			if(elem instanceof Classifier)
			{
				for(Generalization gen : ((Classifier)elem).getGeneralization() )
				{
					if(((Generalization) gen).getGeneral().getName() == c.getName())
					{
						generalizations.add((Generalization) gen);
					}
				}
			}
		}
		
		int cont = 1;		
		
		// Create BinaryOperation with union operator(+) to represent the completeness
		// between father(Classifier) and sons
		
		BinaryOperation bo = factory.createBinaryOperation();
		if(generalizations.size() > 0)
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.modelElementsMap.get(c));
			
			co.setLeftExpression(vr);
			for(Generalization gen : generalizations)
			{
				if(generalizations.size() == 1) break;
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
					bo.setRightExpression(vr);
				}
				cont++;
			}
			world.getBlock().getExpression().add(co);
		}
	}
			
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void createRelatorAssociations(Relator c) 
	{
		// For each Relator c in a Package p define a rule in alloy
		for(PackageableElement pe : Reader.modelElementsMap.keySet())
		{
			if(pe instanceof GeneralizationSet)
			{
				GeneralizationSet gs = ((GeneralizationSet)pe);
				if(gs.isIsCovering())
					for(Generalization gen : gs.getGeneralization())
						if(gen.getGeneral().getName() == c.getName())
							return;
			}
		}
		
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(Quantificator.ALL_LITERAL);
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		var.setName("x");
		var.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(Reader.modelElementsMap.get(c));
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		
		CompareOperation co = factory.createCompareOperation();
		UnaryOperation uOp = factory.createUnaryOperation();		
		
		co.setOperator(CompareOperator.GREATER_EQUAL_LITERAL);
		co.setLeftExpression(uOp);
		vr = factory.createVariableReference();
		vr.setVariable("2");
		co.setRightExpression(vr);

		ArrayList<String> associationNames = new ArrayList<String>();
		
		auxMethodRelator(associationNames, c);		
		
		int cont = 1;
		BinaryOperation bo = factory.createBinaryOperation();
		BinaryOperation bo2 = factory.createBinaryOperation();
		for(String name : associationNames)
		{
			if(associationNames.size() == 1)
			{
				bo.setOperator(BinaryOperator.JOIN_LITERAL);
				vr = factory.createVariableReference();
				vr.setVariable("x");
				bo.setLeftExpression(vr);
				vr = factory.createVariableReference();
				vr.setVariable(name);
				bo.setRightExpression(vr);
				uOp.setExpression(bo);
				break;
			}
			if(cont == 1)
			{
				bo.setOperator(BinaryOperator.UNION_LITERAL);

				bo2.setOperator(BinaryOperator.JOIN_LITERAL);
				vr = factory.createVariableReference();
				vr.setVariable("x");
				bo2.setLeftExpression(vr);
				vr = factory.createVariableReference();
				vr.setVariable(name);
				bo2.setRightExpression(vr);
				
				bo.setLeftExpression(bo2);
				
				uOp.setExpression(bo);
			}
			if(cont > 1 && cont != associationNames.size())
			{
				
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
				
				bo2 = factory.createBinaryOperation();
				bo2.setOperator(BinaryOperator.JOIN_LITERAL);
				vr = factory.createVariableReference();
				vr.setVariable("x");
				bo2.setLeftExpression(vr);
				vr = factory.createVariableReference();
				vr.setVariable(name);
				bo2.setRightExpression(vr);				
				
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(bo2);				
				
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == associationNames.size())
			{
				bo2 = factory.createBinaryOperation();
				bo2.setOperator(BinaryOperator.JOIN_LITERAL);
				vr = factory.createVariableReference();
				vr.setVariable("x");
				bo2.setLeftExpression(vr);
				vr = factory.createVariableReference();
				vr.setVariable(name);
				bo2.setRightExpression(vr);
				
				bo.setRightExpression(bo2);
			}
			cont++;
		}		
		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);
		
		qe.setExpression(co);
		
		if(associationNames.size()>0)
			world.getBlock().getExpression().add(qe);
	}
	
	public void auxMethodRelator(ArrayList<String> list, Relator r)
	{
		for(PackageableElement pe : Reader.modelElementsMap.keySet())
		{
			if(pe instanceof Mediation)
			{
				if( ((Mediation)pe).sourceEnd().getType() instanceof Relator )
				{
					if(((Mediation)pe).sourceEnd().getType().getName() == r.getName())
					{
						list.add(Reader.modelElementsMap.get(pe));
						for(Generalization gen : ((Relator)((Mediation)pe).sourceEnd().getType()).getGeneralization())
							auxMethodRelator(list,(Relator)gen.getGeneral());
					}
				}
				else if( ((Mediation)pe).targetEnd().getType() instanceof Relator )
				{
					if(((Mediation)pe).targetEnd().getType().getName() == r.getName())
					{
						list.add(Reader.modelElementsMap.get(pe));
						for(Generalization gen : ((Relator)((Mediation)pe).targetEnd().getType()).getGeneralization())
							auxMethodRelator(list,(Relator)gen.getGeneral());
					}
				}				
			}
		}
	}
	
	/* ============================================================================*/
	
	public void transformClassifier(Classifier c) 
	{		
		if(c instanceof ObjectClass)
		{
			createObjectClassDeclaration((ObjectClass)c);
			ObjectsList.add(Reader.modelElementsMap.get(c));
			if((c instanceof Kind) || (c instanceof Collective) || (c instanceof Quantity))
			{
				kindsListDisj.add(Reader.modelElementsMap.get(c));
			}
		}
		if(c instanceof DataType && !(c instanceof PrimitiveType))
		{
			createDatatypeDeclaration((DataType)c);
			datatypesList.add(Reader.modelElementsMap.get(c));
			
			// all datatypes without fathers are naturally disjoint, 
			// which means that multiple inheritance between datatypes isn't allowed.
			
			if(((DataType)c).getGeneralization().size() == 0)
				datatypeListDisj.add(Reader.modelElementsMap.get(c));
		}
		if(c instanceof MomentClass)
		{
			if(c instanceof Relator && !(c.isIsAbstract()))
					createRelatorAssociations((Relator) c);
			createPropertyClassDeclaration((MomentClass)c);
			PropertiesList.add(Reader.modelElementsMap.get(c));
			
			// all Propertys without fathers are naturally disjoint, 
			// which means that multiple inheritance between Propertys isn't allowed.
			
			if(((MomentClass)c).getGeneralization().size() == 0)
				PropertysListDisj.add(Reader.modelElementsMap.get(c));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void createObjectClassDeclaration(ObjectClass c) 
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();
		
		sr.setSignature(Object.getName());
		
		vr.setVariable(exists.getName());
		
		var.setName(Reader.modelElementsMap.get(c));
		var.setDeclaration(decl);
		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		uOp.setExpression(bo);
		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);
		
		decl.setExpression(uOp);
		
		world.getRelation().add(decl);
	}
	
	@SuppressWarnings("unchecked")
	private void createDatatypeDeclaration(DataType c) 
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();
		
		sr.setSignature(Datatype.getName());
		
		vr.setVariable(exists.getName());
		
		var.setName(Reader.modelElementsMap.get(c));
		
		var.setDeclaration(decl);
		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		uOp.setExpression(bo);
		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);
		
		decl.setExpression(uOp);
		
		world.getRelation().add(decl);
	}

	@SuppressWarnings("unchecked")
	private void createPropertyClassDeclaration(MomentClass c) 
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();
		
		sr.setSignature(Property.getName());
		
		vr.setVariable(exists.getName());
		
		var.setName(Reader.modelElementsMap.get(c));
		var.setDeclaration(decl);
		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		uOp.setExpression(bo);
		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);
		
		decl.setExpression(uOp);
		
		world.getRelation().add(decl);
	}	
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void transformGeneralizations(Generalization g) 
	{
		// That method creates a rule in alloy for every Generalization
		
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.SUBSET_LITERAL);
		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(Reader.modelElementsMap.get(g.getSpecific()));
		co.setLeftExpression(vr);
		
		vr = factory.createVariableReference();
		vr.setVariable(Reader.modelElementsMap.get(g.getGeneral()));
		co.setRightExpression(vr);
		
		world.getBlock().getExpression().add(co);		
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void transformGeneralizationSets(GeneralizationSet gs) 
	{
		if(gs.isIsCovering())
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.modelElementsMap.get(gs.getGeneralization().get(0).getGeneral()));
			
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
					vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
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
				vr.setVariable(Reader.modelElementsMap.get(gen.getSpecific()));
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void transformDerivations(Derivation d) 
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("derivation");
		VariableReference material = factory.createVariableReference(),mediation1 = factory.createVariableReference(),mediation2 = factory.createVariableReference();
		MaterialAssociation ma = null;
		
		for(Property prop : d.getOwnedEnd())
		{
			if(prop.getType() instanceof MaterialAssociation)
			{
				material.setVariable(Reader.modelElementsMap.get(prop.getType()));
				ma = (MaterialAssociation)prop.getType();
			}
		}		
		
		Relator derRelator = (Relator) (d.relator() instanceof Relator ? d.relator() : d.material());		
		
		String class1 = "",class2 = "";
		
		int cont = 1;
		for(Property prop : ma.getOwnedEnd())
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
		
		cont = 1;
		for (PackageableElement pe : Reader.modelElementsMap.keySet())
		{
			if(pe instanceof Mediation)
			{
				int cont2 = 1;
				Relator relator = null;
				Class mediated = null;
				for(Property prop : ((Mediation)pe).getOwnedEnd())
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
						mediation1.setVariable(Reader.modelElementsMap.get(((Mediation)pe)));
						cont++;
					}
					else
					{
						mediation2.setVariable(Reader.modelElementsMap.get(((Mediation)pe)));
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
	
	@SuppressWarnings("unchecked")
	public void transformAssociationsEnds(Association ass, VariableReference source, VariableReference target) 
	{		
		//Create function for the first associationEnd
		
		if(ass.getOwnedEnd().get(0).getName() != null)
		if(ass.getOwnedEnd().get(0).getName().compareTo("") != 0)	
		{
			FunctionDeclaration fun = factory.createFunctionDeclaration();
			fun.setName(Reader.modelAssocEndMap.get(ass.getOwnedEnd().get(0)));
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.modelElementsMap.get(ass.getOwnedEnd().get(0).getType()));
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
			vr2.setVariable(Reader.modelElementsMap.get(ass.getOwnedEnd().get(1).getType()));
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
			vr2.setVariable(Reader.modelElementsMap.get(ass));
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(Reader.modelAssocEndMap.get(ass.getOwnedEnd().get(0).getType()) == target.getVariable())
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
		
		//Create function for the second associationEnd
		
		if(ass.getOwnedEnd().get(1).getName() != null)
		if(ass.getOwnedEnd().get(1).getName().compareTo("") != 0)
		{
			FunctionDeclaration fun = factory.createFunctionDeclaration();
			fun.setName(Reader.modelAssocEndMap.get(ass.getOwnedEnd().get(1)));
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.modelElementsMap.get(ass.getOwnedEnd().get(1).getType()));
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
			vr2.setVariable(Reader.modelElementsMap.get(ass.getOwnedEnd().get(0).getType()));
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
			vr2.setVariable(Reader.modelElementsMap.get(ass));
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(Reader.modelElementsMap.get(ass.getOwnedEnd().get(1).getType()) == target.getVariable())
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
	
	/* ============================================================================*/
	
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
		var.setName(Reader.modelElementsMap.get(ass)); 
		var.setDeclaration(decl);
		
		association.setVariable(Reader.modelElementsMap.get(ass));	
		
		decl.setExpression(uOp);		
		
		if(ass instanceof Characterization)
			prepareCharacterizationAssociation(ass, source, target, aOp);
		else if(ass instanceof Mediation)
			prepareMediationAssociation(ass, source, target, aOp);
		else if(ass instanceof Meronymic)
			prepareMeronymicAssociation((Meronymic)ass, source, target, aOp);
		else if(!(ass instanceof Derivation))
			prepareAssociation(ass, source, target, aOp);
		
		if(ass.getOwnedEnd().get(0).getName() != null || ass.getOwnedEnd().get(1).getName() != null)
			transformAssociationsEnds(ass,source,target);		
		
		uOp.setExpression(aOp);
		world.getRelation().add(decl);
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	private void prepareMeronymicAssociation(Meronymic ass, VariableReference source, VariableReference target, ArrowOperation aOp) 
	{
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1,cont=0;
		
		for(Property prop : ass.getOwnedEnd())
		{
			if(prop.getAggregation() != null)
				//if(prop.getAggregation().getName().compareTo("none") != 0)
				if(cont==0)
				{
					source.setVariable(Reader.modelElementsMap.get(ass.whole()));
					lowerSource = ass.wholeEnd().getLower();
					upperSource = ass.wholeEnd().getUpper();
					cont++;
				}
				else
				{
					target.setVariable(Reader.modelElementsMap.get(ass.part()));
					lowerTarget = ass.partEnd().getLower();
					upperTarget = ass.partEnd().getUpper();
				}
		}
		
		if(ass instanceof subQuantityOf)
		{
			PredicateInvocation pI = factory.createPredicateInvocation();
			pI.setPredicate("immutable_target");
			VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
			param1.setVariable(source.getVariable());
			param2.setVariable(Reader.modelElementsMap.get(ass));
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
				param2.setVariable(Reader.modelElementsMap.get(ass));
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
				param2.setVariable(Reader.modelElementsMap.get(ass));
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
				param2.setVariable(Reader.modelElementsMap.get(ass));
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
					source.setVariable(Reader.modelElementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(Reader.modelElementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(Reader.modelElementsMap.get(ass));
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
			param2.setVariable(Reader.modelElementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
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
					source.setVariable(Reader.modelElementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(Reader.modelElementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(Reader.modelElementsMap.get(ass));
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
			param2.setVariable(Reader.modelElementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
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
					source.setVariable(Reader.modelElementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(Reader.modelElementsMap.get(c.getType()));
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
			param2.setVariable(Reader.modelElementsMap.get(ass));
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
			param2.setVariable(Reader.modelElementsMap.get(ass));
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	/* ============================================================================*/
	
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
		vr.setVariable(Reader.modelElementsMap.get(ass));
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
		vr.setVariable(Reader.modelElementsMap.get(ass));
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
		vr.setVariable(Reader.modelElementsMap.get(ass));
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
		vr.setVariable(Reader.modelElementsMap.get(ass));
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
	
	/* ============================================================================*/
	
	public void saveAls() 
	{		
		try{			
			FileWriter fstream = new FileWriter(OntoUML2Alloy.alsPath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(alsresource.getContents().get(0).toString());			
			out.close();
		  }catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
		  }		
	}	

	/* ============================================================================*/
	
	public void finalAdditions()
	{		
		createRigidityFact();		
		
		createVisible();
		 
		createRun();
	}

	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	private void createRun() 
	{
		CommandDeclaration run = factory.createCommandDeclaration();
		run.setIsRun(true);
		
		GenericScope gs = factory.createGenericScope();		
		gs.setScopeSize(10);
		
		Scopeable s = factory.createScopeable();		
		s.setIsExactly(true);
		s.setScopeSize(3);
		s.setSignature("World");
		gs.getScopeable().add(s);
		
		run.setScope(gs);
		module.getParagraph().add(run);
	}

	/* ============================================================================*/
	
	private void createVisible() 
	{
		VariableReference vr;
		FunctionDeclaration fun = factory.createFunctionDeclaration();
		fun.setName("visible");
		
		ArrowOperation aOp = factory.createArrowOperation();
		SignatureReference sr;
		sr = factory.createSignatureReference();
		sr.setSignature(world.getName());
		vr = factory.createVariableReference();
		vr.setVariable("univ");
		
		aOp.setLeftExpression(sr);
		aOp.setRightExpression(vr);
		
		fun.setType(aOp);
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		fun.setExpression(vr);
		
		module.getParagraph().add(fun);
	}

	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	private void createRigidityFact() 
	{
		if(rigidElements.size()>0)
		{
			all_rigid_classes = factory.createFactDeclaration();
			all_rigid_classes.setName("all_rigid_classes");
			all_rigid_classes.setBlock(factory.createBlock());
			
			for(Classifier rigid : rigidElements)
			{
				PredicateInvocation pI = factory.createPredicateInvocation();
				pI.setPredicate("rigidity");
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(Reader.modelElementsMap.get(rigid));
				pI.getParameter().add(vr);
				if(rigid instanceof ObjectClass)
				{
					vr = factory.createVariableReference();
					vr.setVariable(Object.getName());
					pI.getParameter().add(vr);
				}
				if(rigid instanceof MomentClass)
				{
					vr = factory.createVariableReference();
					vr.setVariable(Property.getName());
					pI.getParameter().add(vr);
				}
				if(rigid instanceof DataType && !(rigid instanceof PrimitiveType))
				{
					vr = factory.createVariableReference();
					vr.setVariable(Datatype.getName());
					pI.getParameter().add(vr);
				}
				vr = factory.createVariableReference();
				vr.setVariable(exists.getName());
				pI.getParameter().add(vr);
				all_rigid_classes.getBlock().getExpression().add(pI);
			}
			
			module.getParagraph().add(all_rigid_classes);
		}
	}	
			
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void createExists(String param) 
	{		
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.SUBSET_LITERAL);
		
		BinaryOperation bOp = factory.createBinaryOperation();
		bOp.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		bOp.setLeftExpression(vr);
		
		ArrayList<String> list = ObjectsList;
		if(param.compareTo("Object") == 0) {
			vr = factory.createVariableReference();
			vr.setVariable(Object.getName());
			list = ObjectsList;
		}
		if(param.compareTo("Property") == 0) {
			vr = factory.createVariableReference();
			vr.setVariable(Property.getName());
			list = PropertiesList;
		}
		if(param.compareTo("Datatype") == 0) {
			vr = factory.createVariableReference();
			vr.setVariable(Datatype.getName());
			list = datatypesList;
		}
		
		bOp.setRightExpression(vr);
		co.setLeftExpression(bOp);
		
		int cont = 1;
		BinaryOperation bo = null;
		for(String subs : list)
		{
			
			if(list.size() == 1)
			{
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				co.setRightExpression(vr);
			}
			if(cont == 1 && list.size() != 1)
			{
				bo = factory.createBinaryOperation();
				bo.setOperator(BinaryOperator.UNION_LITERAL);
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				bo.setLeftExpression(vr);
				co.setRightExpression(bo);
			}
			if(cont > 1 && cont != list.size())
			{
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == list.size() && cont != 1)
			{
				vr = factory.createVariableReference();
				vr.setVariable(subs);
				bo.setRightExpression(vr);
			}
			cont = cont + 1;
		}
		world.getBlock().getExpression().add(co);
	}
	
	/* ============================================================================*/
	
	@SuppressWarnings("unchecked")
	public void createKindDatatypePropertyDisjoint() 
	{
		DisjointExpression disj = null;
		
		//Kinds, Quantities and Collectives
		if(kindsListDisj.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String kind : kindsListDisj)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(kind);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
		
		//DataTypes
		if(datatypeListDisj.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String datatype : datatypeListDisj)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(datatype);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
		
		//Properties
		if(PropertysListDisj.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String Property : PropertysListDisj)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(Property);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
	}

}
