package refontouml2alloy;

/**
 * This class is used as a Transformer for this transformation 
 * 
 * @author Lucas Thom
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.common.util.URI;

import br.ufes.inf.nemo.ontouml.alloy.*;
import br.ufes.inf.nemo.ontouml.alloy.impl.*;
import br.ufes.inf.nemo.ontouml.alloy.util.*;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
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
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.PackageableElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Class;
import RefOntoUML.Relator;
import RefOntoUML.subQuantityOf;
import RefOntoUML.Package;

public class Transformer {
	
	public static Resource resource;
	public static AlloyFactory factory = AlloyFactory.eINSTANCE;
	public ArrayList<String> sigsTop = new ArrayList<String>();
	
	public AlloyModule module;
	public SignatureDeclaration world;
	public SignatureDeclaration Object = null;
	public SignatureDeclaration Property = null;
	public SignatureDeclaration Datatype = null;
	public FactDeclaration association_properties = null;
	public FactDeclaration derivations = null;
	public Variable exists;
	public FactDeclaration all_rigid_classes;
	public BinaryOperation boSMD;
	
	public ArrayList<String> defaultSignatures = new ArrayList<String>();
		
	public static String path = "models/out.xmi";
	
	public ArrayList<String> ObjectsList = new ArrayList<String>();
	public ArrayList<String> PropertiesList = new ArrayList<String>();
	public ArrayList<String> datatypesList = new ArrayList<String>();
	
	public ArrayList<Classifier> rigidElements = new ArrayList<Classifier>();
	
	public ArrayList<String> PropertysListDisj = new ArrayList<String>();
	public ArrayList<String> datatypeListDisj = new ArrayList<String>();
	public ArrayList<String> kindsListDisj = new ArrayList<String>();
	
	/**
	 * That method initializes elements to do the transformation
	 * Every OntoUML inputted to the transformation has one element name Model, 
	 * which is the root of all the other elements in the model. 
	 * Its name defines the name of the produced Alloy module.
	 */
	public void init(Model m)
	{
		//Creates a ResourceSet to manage the model 
		initResource();
		
		//Creates a Module
		createModule();
		
		//Read for transformation
		Reader.transformModel(Reader.p);
		
		//Save alloy model as als file
		saveAls();
	}
	
	/**
	 * For each abstract class.
	 */
	@SuppressWarnings("unchecked")
	public void createAbstractClause(Classifier c, Package p) 
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		
		//Get all generalizations that the Classifier c is the father
		for(PackageableElement elem : p.getPackagedElement() )
			if(elem instanceof Classifier)
				for(Generalization gen : ((Classifier)elem).getGeneralization() )
					if(((Generalization) gen).getGeneral().getName() == c.getName())
						generalizations.add((Generalization) gen);
		
		int cont = 1;
		
		//Create BinaryOperation with union operator(+) to represent the completeness
		//between father(Classifier) and sons
		BinaryOperation bo = factory.createBinaryOperation();
		if(generalizations.size() > 0)
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(c.getName());
			
			co.setLeftExpression(vr);
			for(Generalization gen : generalizations)
			{
				if(generalizations.size() == 1)
					break;
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(gen.getSpecific().getName());
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(gen.getSpecific().getName());
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(gen.getSpecific().getName());
					bo.setRightExpression(vr);
				}
				cont++;
			}
			world.getBlock().getExpression().add(co);
		}
	}
	
	public void auxMethodRelator(ArrayList<String> list, Relator r, Package p)
	{
		for(PackageableElement pe : p.getPackagedElement())
		{
			if(pe instanceof Mediation)
			{
				if( ((Mediation)pe).sourceEnd().getType() instanceof Relator )
				{
					if(((Mediation)pe).sourceEnd().getType().getName() == r.getName())
					{
						list.add(pe.getName());
						for(Generalization gen : ((Relator)((Mediation)pe).sourceEnd().getType()).getGeneralization())
							auxMethodRelator(list,(Relator)gen.getGeneral(),p);
					}
				}
				else if( ((Mediation)pe).targetEnd().getType() instanceof Relator )
				{
					if(((Mediation)pe).targetEnd().getType().getName() == r.getName())
					{
						list.add(pe.getName());
						for(Generalization gen : ((Relator)((Mediation)pe).targetEnd().getType()).getGeneralization())
							auxMethodRelator(list,(Relator)gen.getGeneral(),p);
					}
				}				
			}
		}
	}
	
	/** For each Relator c in a Package p define a rule in alloy */
	@SuppressWarnings("unchecked")
	public void createRelatorAssociations(Relator c, Package p) 
	{
		for(PackageableElement pe : p.getPackagedElement())
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
		vr.setVariable(c.getName());
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
		
		auxMethodRelator(associationNames, c, p);		
		
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
	
	/**
	 * A general rule that define a basic common rules for classifiers
	 * Every generated specification has at most four signature declarations: 
	 * World, Substantial, Moment and Datatype. The Substantial signature is created 
	 * if the input model contains at least one class stereotyped as kind, collective, 
	 * quantity, role, phase, subkind, category, roleMixin or Mixin, whilst 
	 * the Moment signature if there are modes or relators and the Datatype if 
	 * there are any datatypes.
	 * @Classifier
	 * @Package
	 */
	public void transformClassifier(Classifier c, Package p) 
	{
		String name = c.getName();
		if(c instanceof ObjectClass)
		{
			createObjectClassDeclaration(name);
			ObjectsList.add(name);
			if((c instanceof Kind) || (c instanceof Collective) || (c instanceof Quantity))
			{
				kindsListDisj.add(name);
			}
		}
		if(c instanceof DataType && !(c instanceof PrimitiveType))
		{
			createDatatypeDeclaration(name);
			datatypesList.add(name);
			
			//all datatypes without fathers are naturally disjoint, which means that multiple inheritance between datatypes isn't allowed.
			if(((DataType)c).getGeneralization().size() == 0)
				datatypeListDisj.add(name);
		}
		if(c instanceof MomentClass)
		{
			if(c instanceof Relator && !(c.isIsAbstract()))
					createRelatorAssociations((Relator) c, p);
			createPropertyClassDeclaration(name);
			PropertiesList.add(name);
			
			//all Propertys without fathers are naturally disjoint, which means that multiple inheritance between Propertys isn't allowed.
			if(((MomentClass)c).getGeneralization().size() == 0)
				PropertysListDisj.add(name);
		}
	}
	
	// TODO: Verificar casos de especializa��o de rala��o, se os targets s�o diferentes
	
	/** That method creates a rule in alloy for every Generalization  */
	@SuppressWarnings("unchecked")
	public void transformGeneralizations(Generalization g) 
	{
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.SUBSET_LITERAL);
		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(g.getSpecific().getName());
		co.setLeftExpression(vr);
		
		vr = factory.createVariableReference();
		vr.setVariable(g.getGeneral().getName());
		co.setRightExpression(vr);
		
		world.getBlock().getExpression().add(co);		
	}
	
	@SuppressWarnings("unchecked")
	public void transformGeneralizationSets(GeneralizationSet gs) 
	{
		if(gs.isIsCovering())
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(gs.getGeneralization().get(0).getGeneral().getName());
			
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
					vr.setVariable(gen.getSpecific().getName());
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(gen.getSpecific().getName());
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(gen.getSpecific().getName());
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
				vr.setVariable(gen.getSpecific().getName());
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void transformDerivations(Derivation d, Package p) 
	{
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("derivation");
		VariableReference material = factory.createVariableReference(),mediation1 = factory.createVariableReference(),mediation2 = factory.createVariableReference();
		MaterialAssociation ma = null;
		
		for(Property prop : d.getOwnedEnd())
		{
			if(prop.getType() instanceof MaterialAssociation)
			{
				material.setVariable(prop.getType().getName());
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
		for (PackageableElement pe : p.getPackagedElement())
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
						mediation1.setVariable(((Mediation)pe).getName());
						cont++;
					}
					else
					{
						mediation2.setVariable(((Mediation)pe).getName());
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
	
	@SuppressWarnings("unchecked")
	public void transformAssociationsEnds(Association ass, VariableReference source, VariableReference target) 
	{		
		//Create function for the first associationEnd
		if(ass.getOwnedEnd().get(0).getName() != null)
		if(ass.getOwnedEnd().get(0).getName().compareTo("") != 0)	
		{
			FunctionDeclaration fun = factory.createFunctionDeclaration();
			fun.setName(ass.getOwnedEnd().get(0).getName());
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(ass.getOwnedEnd().get(0).getType().getName());
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
			vr2.setVariable(ass.getOwnedEnd().get(1).getType().getName());
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
			vr2.setVariable(ass.getName());
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(ass.getOwnedEnd().get(0).getType().getName() == target.getVariable())
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
			fun.setName(ass.getOwnedEnd().get(1).getName());
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(ass.getOwnedEnd().get(1).getType().getName());
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
			vr2.setVariable(ass.getOwnedEnd().get(0).getType().getName());
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
			vr2.setVariable(ass.getName());
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(ass.getOwnedEnd().get(1).getType().getName() == target.getVariable())
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
		var.setName(ass.getName());
		var.setDeclaration(decl);
		
		//TODO: Tratar associa��o sem nome		
		association.setVariable(ass.getName());	
		
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
					source.setVariable(ass.whole().getName());
					lowerSource = ass.wholeEnd().getLower();
					upperSource = ass.wholeEnd().getUpper();
					cont++;
				}
				else
				{
					target.setVariable(ass.part().getName());
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
			param2.setVariable(ass.getName());
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
				param2.setVariable(ass.getName());
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
				param2.setVariable(ass.getName());
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
				param2.setVariable(ass.getName());
				pI.getParameter().add(param1);
				pI.getParameter().add(param2);
				association_properties.getBlock().getExpression().add(pI);
			}
		}
					
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
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
					source.setVariable(c.getType().getName());
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(c.getType().getName());
					lowerSource = c.getLower();
					upperSource = c.getUpper();
				}
			}
		}
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(ass.getName());
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
			param2.setVariable(ass.getName());
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
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
					source.setVariable(c.getType().getName());
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(c.getType().getName());
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(ass.getName());
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
			param2.setVariable(ass.getName());
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
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
					source.setVariable(c.getType().getName());
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(c.getType().getName());
					lowerSource = c.getLower();
					upperSource = c.getUpper();
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
			param2.setVariable(ass.getName());
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
			param2.setVariable(ass.getName());
			pI.getParameter().add(param1);
			pI.getParameter().add(param2);
			association_properties.getBlock().getExpression().add(pI);
		}
		
		aOp.setLeftExpression(source);
		aOp.setRightExpression(target);
		setCardinalities(aOp, lowerSource, upperSource, lowerTarget,upperTarget,source,target,ass);
	}
	
	private void setCardinalities(ArrowOperation aOp, int lowerSource, int upperSource, int lowerTarget, int upperTarget, 
	VariableReference source, VariableReference target, Association ass) 
	{
		//SOURCE CARDINALITY
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
		
		//TARGET CARDINALITY
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
		
		//Cardinalities that are different of 1, 0 or *
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
		vr.setVariable(ass.getName());
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
		vr.setVariable(ass.getName());
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
		vr.setVariable(ass.getName());
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
		vr.setVariable(ass.getName());
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
	
	void saveAls() 
	{
		// save XMI model
		// try { 	 
		//	 resource.save(null);
		// } catch (IOException e) {
		//	 e.printStackTrace();
		// }
			
		try{			
			//Create file 
			FileWriter fstream = new FileWriter(OntoUML2Alloy.alsPath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(resource.getContents().get(0).toString());
			
			//Close the output stream
			out.close();
		  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		  }		
	}

	@SuppressWarnings("unchecked")
	private void createModule() 
	{
		module = factory.createAlloyModule();
		module.setName(Reader.modelname);
		resource.getContents().add(module);		
		
		/**
		* Create Module Importation
		* In all executions of the transformation, two Alloy modules are 
		* imported: world_structure and ontological_properties, 
		* both parameterized by the World signature. The first defines 
		* the Kripke  world structure [35], with past, future and 
		* counterfactual worlds, whilst the second provides functions and 
		* predicates that characterize ontological properties, such as 
		* rigidity and dependence.
		*/
		
		ModuleImportation mi = factory.createModuleImportation();
		ModuleImportation mi2 = factory.createModuleImportation();
		mi.setName("world_structure");
		mi2.setName("ontological_properties");
		
		//TODO: Change the way to get path
		mi.setPath("");
		mi2.setPath("");
		
		//Link to the module
		module.getImports().add(mi);
		module.getImports().add(mi2);
		
		//Creates the Signature 'World'
		world = factory.createSignatureDeclaration();
		world.setExists(false);
		world.setIsAbstract(true);
		world.setBlock(factory.createBlock());
		world.setName("World");
		mi.getParameters().add(world.getName());
		mi2.getParameters().add(world.getName());
		
		//Creates exists
		exists = factory.createVariable();
		exists.setName("exists");
		Declaration declaration = factory.createDeclaration();
		declaration.getVariable().add(exists);
		exists.setDeclaration(declaration);
		UnaryOperation uOp = factory.createUnaryOperation();
		uOp.setOperator(UnaryOperator.SOME_LITERAL);
		
		newSigSubtantialPropertyDatatype();
		
		uOp.setExpression(newBOPSubtantialPropertyDatatype());
		declaration.setExpression(uOp);
		
		world.getRelation().add(exists.getDeclaration());
		
		module.getParagraph().add(world);
		
		//Creates fact additional_facts
		FactDeclaration additional_facts = factory.createFactDeclaration();
		additional_facts.setName("additional_facts");
		Block block = factory.createBlock();
		additional_facts.setBlock(block);
		
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("linear_existence");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		block.getExpression().add(pI);
		
		pI = factory.createPredicateInvocation();
		pI.setPredicate("all_elements_exists");
		pI.getParameter().add(newBOPSubtantialPropertyDatatype());
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		block.getExpression().add(pI);
		
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
		
		Block b1 = factory.createBlock();
		association_properties = factory.createFactDeclaration();
		association_properties.setName("association_properties");
		association_properties.setBlock(b1);
		module.getParagraph().add(association_properties);
		
		b1 = factory.createBlock();
		derivations = factory.createFactDeclaration();
		derivations.setName("derivations");
		derivations.setBlock(b1);
		module.getParagraph().add(derivations);
		
	}

	public void finalAdditions()
	{
		//Creates Rigidity Fact
		createRigidityFact();
		
		//Creates Fun Visible
		createVisible();
		 
		//Run
		createRun();
	}
	
	private void createRun() 
	{
		CommandDeclaration run = factory.createCommandDeclaration();
		run.setIsRun(true);
		
		GenericScope gs = factory.createGenericScope();
		gs.setScopeSize(5);
		
		run.setScope(gs);
		module.getParagraph().add(run);
	}

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
				vr.setVariable(rigid.getName());
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

	private void newSigSubtantialPropertyDatatype() 
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
	
	private Expression newBOPSubtantialPropertyDatatype() 
	{
		int cont = 1;
		//boSMD is an operation containing the union between Object, Property and Datatype
		BinaryOperation bo = null;
		Expression exp = null;
		for(String sigElement : defaultSignatures)
		{
			if(defaultSignatures.size() == 1)
			{
				SignatureReference sr = factory.createSignatureReference();
				sr.setSignature(sigElement);
				exp = sr;
			}
			else
			{
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
	
	@SuppressWarnings("unchecked")
	private void createObjectClassDeclaration(String name) 
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();
		
		sr.setSignature(Object.getName());
		
		vr.setVariable(exists.getName());
		
		var.setName(name);
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
	private void createDatatypeDeclaration(String name) 
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();
		
		sr.setSignature(Datatype.getName());
		
		vr.setVariable(exists.getName());
		
		var.setName(name);
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
	private void createPropertyClassDeclaration(String name) 
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();
		
		sr.setSignature(Property.getName());
		
		vr.setVariable(exists.getName());
		
		var.setName(name);
		var.setDeclaration(decl);
		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		uOp.setExpression(bo);
		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);
		
		decl.setExpression(uOp);
		
		world.getRelation().add(decl);
	}
	
	public static void initResource() 
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new AlloyResourceFactoryImpl() );
		resourceSet.getPackageRegistry().put(AlloyPackage.eNS_URI,AlloyPackage.eINSTANCE);
		AlloyPackageImpl.init();
	
		//Create URI for output model
		resource = resourceSet.createResource(URI.createURI(path));
	}
	
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
	
	@SuppressWarnings("unchecked")
	public void createKindDatatypePropertyDisjoint() 
	{
		DisjointExpression disj = null;
		
		//Kinds, Quantyties and Collectives
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
		
		//Datatypes
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
		
		//Propertys
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
