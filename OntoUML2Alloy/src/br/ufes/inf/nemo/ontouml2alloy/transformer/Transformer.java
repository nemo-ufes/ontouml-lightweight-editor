package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

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
import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.ArrowOperation;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.BinaryOperator;
import br.ufes.inf.nemo.alloy.Block;
import br.ufes.inf.nemo.alloy.CommandDeclaration;
import br.ufes.inf.nemo.alloy.CompareOperation;
import br.ufes.inf.nemo.alloy.CompareOperator;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.alloy.Expression;
import br.ufes.inf.nemo.alloy.FactDeclaration;
import br.ufes.inf.nemo.alloy.FunctionDeclaration;
import br.ufes.inf.nemo.alloy.GenericScope;
import br.ufes.inf.nemo.alloy.ModuleImportation;
import br.ufes.inf.nemo.alloy.Multiplicity;
import br.ufes.inf.nemo.alloy.PredicateInvocation;
import br.ufes.inf.nemo.alloy.QuantificationExpression;
import br.ufes.inf.nemo.alloy.Quantificator;
import br.ufes.inf.nemo.alloy.Scopeable;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.SignatureReference;
import br.ufes.inf.nemo.alloy.UnaryOperation;
import br.ufes.inf.nemo.alloy.UnaryOperator;
import br.ufes.inf.nemo.alloy.Variable;
import br.ufes.inf.nemo.alloy.VariableReference;
import br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl;
import br.ufes.inf.nemo.alloy.util.AlloyResourceFactoryImpl;

/**
 *	This class is used as a Transformer for the transformation.
 */

public class Transformer {
	
	public ArrayList<String> defaultSignatures = new ArrayList<String>();		
	public static String path = "models/out.xmi";		

	/* =========================================================================================================*/
	
	/** Alloy model resource. */
	public static Resource alsresource;
	
	/** Alloy instance. */
	public static AlloyFactory factory = AlloyFactory.eINSTANCE;
	
	/** Alloy Module. */
	public AlloyModule module;
	
	/** Alloy Signature World. */
	public SignatureDeclaration world;
	
	/** Alloy World Field exists. */
	public Variable exists;
	
	/** Alloy Default Signature : sig Object{}. */
	public SignatureDeclaration Object;
	
	/** Alloy Default Signature : sig Property{}. */
	public SignatureDeclaration Property;
	
	/** Alloy Default Signature : sig DataType{}. */
	public SignatureDeclaration Datatype;
	
	/** Alloy association_properties Fact Declaration. */
	public FactDeclaration association_properties;
	
	/** Alloy derivation Fact Declaration. */
	public FactDeclaration derivations;
	
	/** Alloy all_rigid_classes Fact Declaration. */
	public FactDeclaration all_rigid_classes;
	
	/* =========================================================================================================*/
	
	/** List containing all the names of Substances Sortals that are disjoint. */
	public ArrayList<String> subsortalDisjNamesList = new ArrayList<String>();
	
	/** List containing all the names of Moment Classes that are disjoint. */
	public ArrayList<String> momentDisjNamesList = new ArrayList<String>();
	
	/** List containing all the names of DataTypes that are disjoint. */
	public ArrayList<String> datatypeDisjNamesList = new ArrayList<String>();
	
	/* =========================================================================================================*/
	
	/** List containing all the Object Class names. */
	public ArrayList<String> objectNamesList = new ArrayList<String>();	
	
	/** List containing all the Moment Class names. */
	public ArrayList<String> momentNamesList = new ArrayList<String>();
	
	/** List containing all the DataTypes names. */
	public ArrayList<String> datatypeNamesList = new ArrayList<String>();
	
	/* =========================================================================================================*/
	
	/** List containing all the rigid classifier. */
	public ArrayList<Classifier> rigidElements = new ArrayList<Classifier>();
	
	/* =========================================================================================================*/
	
	/** List containing all the facts for relator's rule. */
	public ArrayList<FactDeclaration> relator_rule = new ArrayList<FactDeclaration>();

	/** List containing all the facts for weak supplementation's rule. */
	public ArrayList<FactDeclaration> weak_supplementation_rule = new ArrayList<FactDeclaration>();
	
	/* =========================================================================================================*/
	
	public void init()
	{
		initAlsResource();
		
		createAlloyModule();
		
		Reader.callTransformationModel();
		
		saveAls();
	}
	
	/* =========================================================================================================*/
	
	/** Init Alloy Resource 'alsresource'. */
	public static void initAlsResource() 
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new AlloyResourceFactoryImpl() );
		resourceSet.getPackageRegistry().put(AlloyPackage.eNS_URI,AlloyPackage.eINSTANCE);
		AlloyPackageImpl.init();	
		alsresource = resourceSet.createResource(URI.createURI(path));
	}
	
	/* =========================================================================================================*/
	
	/** Save 'alsresource' content into a file (.als). */
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
	
	/* =========================================================================================================*/

	@SuppressWarnings("unchecked")
	private void createAlloyModule() 
	{
		// module
		module = factory.createAlloyModule();
		module.setName(Reader.mapper.refmodelname); 
		alsresource.getContents().add(module);		
		
		// abstract sig World  
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
		
		// sig Object{}
		// sig Property{}
		// sig DataType{}	 
		// abstract sig World { exists: some Object+Property+DataTYpe, }
		exists = factory.createVariable();
		exists.setName("exists");
		Declaration declaration = factory.createDeclaration();
		declaration.getVariable().add(exists);
		exists.setDeclaration(declaration);
		UnaryOperation uOp = factory.createUnaryOperation();
		uOp.setOperator(UnaryOperator.SOME_LITERAL);
		
		createDefaultSignatures();
		
		uOp.setExpression(createExpUnionObjectPropertyDatatype());
		declaration.setExpression(uOp);		
		world.getRelation().add(exists.getDeclaration());		
		module.getParagraph().add(world);
		
		// fact additional_facts { }
		FactDeclaration additional_facts = factory.createFactDeclaration();
		additional_facts.setName("additional_facts");
		Block block = factory.createBlock();
		additional_facts.setBlock(block);

		// linear_existence[exists] 
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("linear_existence");
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		block.getExpression().add(pI);
		
		// all_elements_exists[Object+Property+DataType,exists]
		pI = factory.createPredicateInvocation();
		pI.setPredicate("all_elements_exists");
		pI.getParameter().add(createExpUnionObjectPropertyDatatype());
		vr = factory.createVariableReference();
		vr.setVariable(exists.getName());
		pI.getParameter().add(vr);
		block.getExpression().add(pI);
		
		// always_exists[DataType,exists]
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
		
		// fact association_properties { }
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
	
	/* =========================================================================================================*/
	
	/** This method creates the Expression : "Object+Property+DataType" in Alloy. */
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
		
	/* =========================================================================================================*/
	
	/**
	 * This method is used for creating the default Signatures in Alloy module.
	 * Signatures Object{}, Property{} and DataType{}.
	 */
	private void createDefaultSignatures() 
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
			if(sigDecl.getName().equals("Object")) Object = sigDecl;
			if(sigDecl.getName().equals("Property")) Property = sigDecl;
			if(sigDecl.getName().equals("DataType")) Datatype = sigDecl;
			cont = cont + 1;
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Performs transformation of Classifiers.
	 * 
	 * @param c: RefOntoUML.Classifier
	 */
	public void transformClassifier(Classifier c) 
	{		
		if(c instanceof ObjectClass)
		{
			createObjectClassDeclaration((ObjectClass)c);			
			
			objectNamesList.add(Reader.mapper.elementsMap.get(c));

			if((c instanceof Kind) || (c instanceof Collective) || (c instanceof Quantity))
			{
				subsortalDisjNamesList.add(Reader.mapper.elementsMap.get(c));
			}			
			if (OntoUML2Alloy.weakSupplementationRuleFlag)
			{				
				if(c instanceof RigidSortalClass) createWeakSupplementationRule(c);
			}
		}
		
		if(c instanceof DataType && !(c instanceof PrimitiveType))
		{			
			createDatatypeDeclaration((DataType)c);			
			
			datatypeNamesList.add(Reader.mapper.elementsMap.get(c));
			
			// all dataTypes without fathers are naturally disjoint, 
			// which means that multiple inheritance between datatypes isn't allowed.			
			if(((DataType)c).getGeneralization().size() == 0)
			{
				datatypeDisjNamesList.add(Reader.mapper.elementsMap.get(c));
			}
		}
		if(c instanceof MomentClass)
		{
			if (OntoUML2Alloy.relatorRuleFlag)
			{
				if(c instanceof Relator) createRelatorRule((Relator) c);
			}
			
			createPropertyClassDeclaration((MomentClass)c);			
			
			momentNamesList.add(Reader.mapper.elementsMap.get(c));
			
			// all Properties without fathers are naturally disjoint, 
			// which means that multiple inheritance between Properties isn't allowed.			
			if(((MomentClass)c).getGeneralization().size() == 0)
			{
				momentDisjNamesList.add(Reader.mapper.elementsMap.get(c));
			}
		}		
		
		if (c.isIsAbstract()) createAbstractClauseRule(c);		
		
		if ( (c instanceof RigidSortalClass) || (c instanceof Category) || (c instanceof MomentClass) || ((c instanceof DataType)&&!(c instanceof PrimitiveType)) ) 
		{ 
			rigidElements.add(c); 
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 *
	 * Relator Rule.
	 *
	 * Let R be a relator universal and let {C1C2} be a set of universals mediated by R (related
	 * to R via a «mediation» relation). Finally, let lowerCi be the value of the minimum cardinality
	 * constraint of the association end connected to C(i) in the «mediation» relation. Then, we have
	 * that (Σ= n, i=1 lowerCi) ≥ 2.
	 * 
	 * @param c: RefOntoUML.Relator
	 */
	@SuppressWarnings("unchecked")
	public void createRelatorRule(Relator c) 
	{
		if (c.isIsAbstract()) return;
		
		// isAbstract from generalization Sets (Disjoint and Complete)
		if (isAbstractFromGeneralizationSets(c)) return;
		
		if (!isSourceOfMeronymicRelation(c)) return;
		
		// get all 'c' mediations
		ArrayList<String> associationNames = new ArrayList<String>();		
		getAllMediations(associationNames, c);		
		
		if(associationNames.size()>0)
		{			
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = createQuantificationExpression(associationNames, Reader.mapper.elementsMap.get(c));
			
			// create fact from q
			FactDeclaration RelatorRuleFact = factory.createFactDeclaration();
			RelatorRuleFact.setName("relator_rule_"+Reader.mapper.elementsMap.get(c));
			RelatorRuleFact.setBlock(factory.createBlock());			
			RelatorRuleFact.getBlock().getExpression().add(q);
			
			// add to list
			relator_rule.add(RelatorRuleFact);			
		}		
	}
	

	/* =========================================================================================================*/
	
	/**
	 * Weak Supplementation Rule.
	 * 
	 * Let U be a universal whose instances are wholes and let
	 * {C1C2} be a set of universals related to U via aggregation relations. Let lowerCi be the
	 * value of the minimum cardinality constraint of the association end connected to Ci in the
	 * aggregation relation. Then, we have that (Σ= n, i=1 lowerCi) ≥ 2.
	 *  
	 * @param c: RefOntoUML.RigidSortalClass (Functional Complexes)
	 */
	@SuppressWarnings("unchecked")
	public void createWeakSupplementationRule(Classifier c) 
	{
		if (c.isIsAbstract()) return;
				
		// isAbstract from generalization Sets (Disjoint and Complete)
		if (isAbstractFromGeneralizationSets(c)) return;	
		
		if (!isSourceOfMeronymicRelation(c)) return;
		
		// get all 'c' meronymics
		ArrayList<String> associationNames = new ArrayList<String>();		
		getAllMeronymics(associationNames, (RigidSortalClass)c);	
		
		if( associationNames.size() > 0)
		{
			//all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2
			QuantificationExpression q = createQuantificationExpression(associationNames, Reader.mapper.elementsMap.get(c));
			
			// create fact from q
			FactDeclaration WeakSupplementationFact = factory.createFactDeclaration();
			WeakSupplementationFact.setName("weak_supplementation_"+Reader.mapper.elementsMap.get(c));
			WeakSupplementationFact.setBlock(factory.createBlock());			
			WeakSupplementationFact.getBlock().getExpression().add(q);			
			
			// add to list
			weak_supplementation_rule.add(WeakSupplementationFact);			
		}	
	}
	
	/* =========================================================================================================*/
	
	/**
	 * This method creates a specific Quantification Expression in Alloy.
	 * QuantificationExpression = "[quantificator] [varibleName]: [typeName]".
	 * For Example = "all w: World".
	 */	 
	@SuppressWarnings("unchecked")
	private QuantificationExpression createQuantificationExpression(Quantificator quantificator, String variableName, String typeName)
	{
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(quantificator);		
		Declaration decl = factory.createDeclaration();
		Variable varbl = factory.createVariable();
		varbl.setName(variableName);		
		varbl.setDeclaration(decl);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(typeName);
		decl.setExpression(vr);
		qe.getDeclaration().add(decl);
		return qe;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * This method creates a specific Quantification Expression in Alloy.
	 * QuantificationExpression = "[quantificator] [varibleName]: [variableName2].[typeName]". 
	 * For Example: "all x: w.Enrollment".
	 */	 
	@SuppressWarnings("unchecked")
	private QuantificationExpression createQuantificationExpression(Quantificator quantificator, String variableName1, String variableName2, String typeName)
	{
		QuantificationExpression qe = factory.createQuantificationExpression();
		qe.setQuantificator(quantificator);
		Declaration decl = factory.createDeclaration();
		Variable varx = factory.createVariable();
		varx.setName(variableName1);
		varx.setDeclaration(decl);
		BinaryOperation bo = createBinaryOperation(variableName2,BinaryOperator.JOIN_LITERAL,typeName);		
		decl.setExpression(bo);
		qe.getDeclaration().add(decl);
		return qe;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * This method creates a specific BinaryOperation in Alloy.
	 * BinaryOperation = "[varibleName1] [binOperator] [variableName2]".
	 * For Example: "w.Enrollment".
	 */
	private BinaryOperation createBinaryOperation(String variableName1, BinaryOperator binOperator, String variableName2)
	{
		BinaryOperation bo = factory.createBinaryOperation();
		bo.setOperator(binOperator);
		VariableReference vrR = factory.createVariableReference();
		vrR.setVariable(variableName1);
		bo.setLeftExpression(vrR);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(variableName2);		
		bo.setRightExpression(vr);
		return bo; 
	}
	
	/* =========================================================================================================*/
	
	/**
	 * This method creates a specific BinaryOperation in Alloy.
	 * BinaryOperation = "[varibleName1] [binOperator] [binaryOperation]".
	 * For Example: "x.(w.Enrollment)".
	 */	 
	private BinaryOperation createBinaryOperation(String variableName1, BinaryOperator binOperator, BinaryOperation binOperation)
	{
		BinaryOperation bo = factory.createBinaryOperation();
		bo.setOperator(binOperator);
		VariableReference vrR = factory.createVariableReference();
		vrR.setVariable(variableName1);
		bo.setLeftExpression(vrR);		
		bo.setRightExpression(binOperation);
		return bo; 
	}
		
	/* =========================================================================================================*/
	
	/**
	 * This method creates a specific Quantification Expression in Alloy.
	 * QuantificationExpression = "all w: World | all x: w.<typeName> | # ( x.(w.assciationName1)+ x.(w.associationName2) + ...) >= 2".
	 * For Example: "all w: World | all x: w.RelatorName | # ( x.(w.possui) + x.(w.faz) ) >= 2". 
	 */	
	private QuantificationExpression createQuantificationExpression(ArrayList<String> associationNames, String typeName)
	{
		// all w: World
		QuantificationExpression qeWorld = createQuantificationExpression(Quantificator.ALL_LITERAL,"w","World");	
		// all x: w.name
		QuantificationExpression qe = createQuantificationExpression(Quantificator.ALL_LITERAL,"x","w",typeName);
		qeWorld.setExpression(qe);

		// # (...) >= 2
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.GREATER_EQUAL_LITERAL);		
		UnaryOperation uOp = factory.createUnaryOperation();		
		uOp.setOperator(UnaryOperator.CARDINALITY_LITERAL);		
		co.setLeftExpression(uOp);
		VariableReference vr = factory.createVariableReference();
		vr.setVariable("2");
		co.setRightExpression(vr);
		
		//( x.(w.name) + x.(w.name) + ... + x.(w.name) )
		int cont = 1;
		BinaryOperation bo = factory.createBinaryOperation();
		BinaryOperation bo2 = factory.createBinaryOperation();		
		for(String name : associationNames)
		{
			if(associationNames.size() == 1)
			{	
				// x.(w.name)
				bo = createBinaryOperation("x",BinaryOperator.JOIN_LITERAL, createBinaryOperation("w",BinaryOperator.JOIN_LITERAL,name));				
				uOp.setExpression(bo);
				break;
			}
			if(cont == 1)
			{
				bo.setOperator(BinaryOperator.UNION_LITERAL);
				// x.(w.name)
				bo2 = createBinaryOperation("x",BinaryOperator.JOIN_LITERAL, createBinaryOperation("w",BinaryOperator.JOIN_LITERAL,name)); 				
				bo.setLeftExpression(bo2);				
				uOp.setExpression(bo);
			}
			if(cont > 1 && cont != associationNames.size())
			{				
				bo.setRightExpression(factory.createBinaryOperation());
				((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);	
				// x.(w.name)
				bo2 = createBinaryOperation("x",BinaryOperator.JOIN_LITERAL, createBinaryOperation("w",BinaryOperator.JOIN_LITERAL,name));				
				((BinaryOperation)bo.getRightExpression()).setLeftExpression(bo2);				
				bo = ((BinaryOperation)bo.getRightExpression());
			}
			if(cont == associationNames.size())
			{
				// x.(w.name)
				bo2 = createBinaryOperation("x",BinaryOperator.JOIN_LITERAL, createBinaryOperation("w",BinaryOperator.JOIN_LITERAL,name));				
				bo.setRightExpression(bo2);
			}
			cont++;
		}
		qe.setExpression(co);
		return qeWorld;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if a Classifier 'c' is a General Classifier in a GeneralizationSet that is Disjoint and Complete
	 * What means that this Classifier is an Abstract Classifier.
	 * 
	 * @param c: Classifier c 
	 * @return
	 */
	private boolean isAbstractFromGeneralizationSets(Classifier c) 
	{
		for(PackageableElement pe : Reader.mapper.elementsMap.keySet())
		{
			if(pe instanceof GeneralizationSet)
			{
				GeneralizationSet gs = ((GeneralizationSet)pe);
				if(gs.isIsCovering())
				{
					for(Generalization gen : gs.getGeneralization())
					{
						if(gen.getGeneral().getName() == c.getName()) 
						{							
							return true;
						}
					}
				}
			}
		}		
		return false;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Verify if the Classifier 'c' is the source of some Meronymic Relation.
	 *  
	 * @param c: RefOntoUML.Classifier
	 * @return
	 */
	private boolean isSourceOfMeronymicRelation (Classifier c)
	{
		for(PackageableElement pe : Reader.mapper.elementsMap.keySet())
		{
			if (pe instanceof Meronymic) 
			{
				Meronymic assoc = (Meronymic)pe;				
				for( Property p : assoc.getMemberEnd())
				{
					if (!p.getAggregation().equals(AggregationKind.NONE))
					{
						if (p.getType().getName().equals(c.getName()))
						{							
							return true;
						}
					}
				}
			}
		}		
		return false;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Mediations relations that have as a source the Relator 'r' or one of its Super Types. 
	 * 
	 * @param list: Mediation Relations Names
	 * @param r: RefOntoUML.Relator
	 */
	private void getAllMediations(ArrayList<String> list, Relator r)
	{
		for(PackageableElement pe : Reader.mapper.elementsMap.keySet())
		{
			if(pe instanceof Mediation)
			{
				Mediation assoc = (Mediation)pe;
				for( Property p : assoc.getMemberEnd())
				{
					if (p.getType() instanceof Relator)
					{
						if (p.getType().getName().equals(r.getName()))
						{
							list.add(Reader.mapper.elementsMap.get(pe));							
						}
					}
				}
			}			
		}
		for(Generalization gen : ((Relator)r).getGeneralization())
		{							
			if (gen.getGeneral() instanceof Relator) getAllMediations(list,(Relator)gen.getGeneral());
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Get all Meronymic relations that have as a Whole the RigidSortalClass 'c' or one of its Super Types
	 * RigidSortalClass : Kind, Collective, Quantity, SubKind.
	 *   
	 * @param list: Meronymic Relations Names
	 * @param c: RefOntoUML.RigidSortalClass
	 * 
	 */	
	private void getAllMeronymics(ArrayList<String> list, RigidSortalClass c)
	{
		for(PackageableElement pe : Reader.mapper.elementsMap.keySet())
		{
			if(pe instanceof Meronymic)
			{
				Meronymic assoc = (Meronymic)pe;
				for( Property p : assoc.getMemberEnd())
				{
					if (!p.getAggregation().equals(AggregationKind.NONE))
					{
						if (p.getType().getName().equals(c.getName()))
						{
							list.add(Reader.mapper.elementsMap.get(pe));							
						}
					}
				}
			}
		}
		for(Generalization gen : ((RigidSortalClass)c).getGeneralization())
		{
			if (gen.getGeneral() instanceof RigidSortalClass) getAllMeronymics(list,(RigidSortalClass)gen.getGeneral());
		}
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Create Abstract Clause Rule.
	 * 
	 * BinaryOperation with union operator(+) to represent the completeness
	 * between father(Classifier) and child.
	 * 
	 * @param c: RefOntoUML.Classifier
	 */
	@SuppressWarnings("unchecked")
	public void createAbstractClauseRule(Classifier c) 
	{
		// get all generalizations
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		getAllGeneralizations(generalizations,c);
		
		int cont = 1;		
		BinaryOperation bo = factory.createBinaryOperation();
		if(generalizations.size() > 0)
		{
			CompareOperation co = factory.createCompareOperation();
			co.setOperator(CompareOperator.EQUAL_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.mapper.elementsMap.get(c));
			
			co.setLeftExpression(vr);
			for(Generalization gen : generalizations)
			{
				if(generalizations.size() == 1) 
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));					
					co.setRightExpression(vr);
					break;
				}
				if(cont == 1)
				{
					bo.setOperator(BinaryOperator.UNION_LITERAL);
					vr = factory.createVariableReference();
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == generalizations.size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
					bo.setRightExpression(vr);
				}
				cont++;
			}
			world.getBlock().getExpression().add(co);
		}
	}
			
	/* =========================================================================================================*/
	
	/**
	 * Get all Generalizations that the Classifier 'c' is the father.
	 * 
	 * @param generalizations: ArrayList RefOntoUML.Generalization
	 * @param c: RefOntoUML.Classifier
	 */
	private void getAllGeneralizations(ArrayList<Generalization> generalizations, Classifier c)
	{		
		for(PackageableElement elem : Reader.mapper.elementsMap.keySet() )
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
	}
	
	/* =========================================================================================================*/
	
	/** 
	 * This method creates a specific Declaration in Alloy.
	 * Declaration = "[variableName]: set exists:>[typeName],".
	 * For Example: "Imovel: set exists:>Object,". 
	 */
	private Declaration createDeclaration (String variableName, String typeName)
	{
		Declaration decl = factory.createDeclaration();
		Variable var = factory.createVariable();
		UnaryOperation uOp = factory.createUnaryOperation();
		BinaryOperation bo = factory.createBinaryOperation();
		VariableReference vr = factory.createVariableReference();
		SignatureReference sr = factory.createSignatureReference();		
		sr.setSignature(typeName);		
		vr.setVariable(exists.getName());		
		var.setName(variableName);
		var.setDeclaration(decl);		
		uOp.setOperator(UnaryOperator.SET_LITERAL);
		uOp.setExpression(bo);		
		bo.setOperator(BinaryOperator.RANGE_RESTRICTION_LITERAL);
		bo.setLeftExpression(vr);
		bo.setRightExpression(sr);		
		decl.setExpression(uOp);	
		return decl;
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Create Object Declaration. 
	 * 
	 * A field into the World Signature...
	 * 
	 * @param c: RefOntoUML.ObjectClass
	 */
	@SuppressWarnings("unchecked")
	public void createObjectClassDeclaration(ObjectClass c) 
	{
		// [c.name]: set exists:>Object,
		Declaration decl = createDeclaration(Reader.mapper.elementsMap.get(c), Object.getName());				
		world.getRelation().add(decl);
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Create DataType Declaration. 
	 * 
	 * A field into the World Signature...
	 * 
	 * @param c: RefOntoUML.DataType
	 */
	@SuppressWarnings("unchecked")	 
	public void createDatatypeDeclaration(DataType c) 
	{
		// [c.name]: set exists:>DataType,
		Declaration decl = createDeclaration(Reader.mapper.elementsMap.get(c), Datatype.getName());	
		world.getRelation().add(decl);
	}

	/* =========================================================================================================*/
	
	/**
	 * Create Property Declaration. 
	 * 
	 * A field into the World Signature...
	 *  
	 * @param c: RefOntoUML.MomentClass
	 */
	@SuppressWarnings("unchecked")	
	public void createPropertyClassDeclaration(MomentClass c) 
	{
		// [c.name]: set exists:>Property,
		Declaration decl = createDeclaration(Reader.mapper.elementsMap.get(c),Property.getName());
		world.getRelation().add(decl);
	}	
	
	/* =========================================================================================================*/
	
	/**
	 * Performs transformation of Generalizations
	 * 
	 * @param g: RefOntoUML.Generalization
	 */	
	public void transformGeneralizations(Generalization g) 
	{
		createGeneralizationRule(g);	
	}
	
	/* =========================================================================================================*/
	
	/**
	 * Create Generalization Rule.
	 * 
	 * The child Classifier is a subset of the father Classifier. 
	 * Create the rule "child in father" into the World signature.
	 * 
	 * @param g: RefOntoUML.Generalization
	 */
	@SuppressWarnings("unchecked")	 
	public void createGeneralizationRule(Generalization g)
	{
		CompareOperation co = factory.createCompareOperation();
		co.setOperator(CompareOperator.SUBSET_LITERAL);		
		VariableReference vr = factory.createVariableReference();
		vr.setVariable(Reader.mapper.elementsMap.get(g.getSpecific()));
		co.setLeftExpression(vr);		
		vr = factory.createVariableReference();
		vr.setVariable(Reader.mapper.elementsMap.get(g.getGeneral()));
		co.setRightExpression(vr);		
		world.getBlock().getExpression().add(co);
	}
	
	/* =========================================================================================================*/
	/* =========================================================================================================*/
	/* =========================================================================================================*/
	
	/**
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
			vr.setVariable(Reader.mapper.elementsMap.get(gs.getGeneralization().get(0).getGeneral()));
			
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
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
					bo.setLeftExpression(vr);
					co.setRightExpression(bo);
				}
				if(cont > 1 && cont != gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
					bo.setRightExpression(factory.createBinaryOperation());
					((BinaryOperation)bo.getRightExpression()).setOperator(BinaryOperator.UNION_LITERAL);
					((BinaryOperation)bo.getRightExpression()).setLeftExpression(vr);
					bo = ((BinaryOperation)bo.getRightExpression());
				}
				if(cont == gs.getGeneralization().size())
				{
					vr = factory.createVariableReference();
					vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
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
				vr.setVariable(Reader.mapper.elementsMap.get(gen.getSpecific()));
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
				material.setVariable(Reader.mapper.elementsMap.get(prop.getType()));
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
		for (PackageableElement pe : Reader.mapper.elementsMap.keySet())
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
						mediation1.setVariable(Reader.mapper.elementsMap.get(((Mediation)pe)));
						cont++;
					}
					else
					{
						mediation2.setVariable(Reader.mapper.elementsMap.get(((Mediation)pe)));
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
			fun.setName(Reader.mapper.assocEndMap.get(ass.getOwnedEnd().get(0)));
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.mapper.elementsMap.get(ass.getOwnedEnd().get(0).getType()));
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
			vr2.setVariable(Reader.mapper.elementsMap.get(ass.getOwnedEnd().get(1).getType()));
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
			vr2.setVariable(Reader.mapper.elementsMap.get(ass));
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(Reader.mapper.assocEndMap.get(ass.getOwnedEnd().get(0).getType()) == target.getVariable())
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
			fun.setName(Reader.mapper.assocEndMap.get(ass.getOwnedEnd().get(1)));
			UnaryOperation uOp = factory.createUnaryOperation();
			uOp.setOperator(UnaryOperator.SET_LITERAL);
			
			BinaryOperation bOp = factory.createBinaryOperation();
			bOp.setOperator(BinaryOperator.JOIN_LITERAL);
			
			VariableReference vr = factory.createVariableReference();
			vr.setVariable(Reader.mapper.elementsMap.get(ass.getOwnedEnd().get(1).getType()));
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
			vr2.setVariable(Reader.mapper.elementsMap.get(ass.getOwnedEnd().get(0).getType()));
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
			vr2.setVariable(Reader.mapper.elementsMap.get(ass));
			bOp.setLeftExpression(vr);
			bOp.setRightExpression(vr2);
			
			vr = factory.createVariableReference();
			vr.setVariable("x");
			
			BinaryOperation bOp2 = factory.createBinaryOperation();
			bOp2.setOperator(BinaryOperator.JOIN_LITERAL);
			if(Reader.mapper.elementsMap.get(ass.getOwnedEnd().get(1).getType()) == target.getVariable())
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
		var.setName(Reader.mapper.elementsMap.get(ass)); 
		var.setDeclaration(decl);
		
		association.setVariable(Reader.mapper.elementsMap.get(ass));	
		
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
		int lowerSource=-1, upperSource=-1, lowerTarget=-1, upperTarget=-1;
		
		for(Property prop : ass.getMemberEnd())
		{
			if(! prop.getAggregation().equals(AggregationKind.NONE))
			{
				source.setVariable(Reader.mapper.elementsMap.get(prop.getType()));				
				lowerSource = prop.getLower();
				upperSource = prop.getUpper();				
			}
			else
			{
				target.setVariable(Reader.mapper.elementsMap.get(prop.getType()));				
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
			param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
				param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
				param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
				param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
					source.setVariable(Reader.mapper.elementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(Reader.mapper.elementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
			param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
					source.setVariable(Reader.mapper.elementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(Reader.mapper.elementsMap.get(c.getType()));
					lowerTarget = c.getLower();
					upperTarget = c.getUpper();
				}
			}
		}
		
		PredicateInvocation pI = factory.createPredicateInvocation();
		pI.setPredicate("immutable_target");
		VariableReference param1 = factory.createVariableReference(),param2 = factory.createVariableReference();
		param1.setVariable(source.getVariable());
		param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
			param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
					source.setVariable(Reader.mapper.elementsMap.get(c.getType()));
					lowerSource = c.getLower();
					upperSource = c.getUpper();
					isSourceReadOnly = c.isIsReadOnly();
					cont++;
				}
				else
				{
					target.setVariable(Reader.mapper.elementsMap.get(c.getType()));
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
			param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
			param2.setVariable(Reader.mapper.elementsMap.get(ass));
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
		vr.setVariable(Reader.mapper.elementsMap.get(ass));
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
		vr.setVariable(Reader.mapper.elementsMap.get(ass));
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
		vr.setVariable(Reader.mapper.elementsMap.get(ass));
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
		vr.setVariable(Reader.mapper.elementsMap.get(ass));
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
	
	public void createRelatorRuleFact()
	{
		for (FactDeclaration f: relator_rule)
		{
			module.getParagraph().add(f);
		}
	}	
	
	/* ============================================================================*/
	
	public void createWeakSupplementationRuleFact()
	{
		for (FactDeclaration f: weak_supplementation_rule)
		{
			module.getParagraph().add(f);
		}
	}	
	
	/* ============================================================================*/
			
	public void finalAdditions()
	{		
		createRelatorRuleFact();
		
		createWeakSupplementationRuleFact();
		
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
				vr.setVariable(Reader.mapper.elementsMap.get(rigid));
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
		
		ArrayList<String> list = objectNamesList;
		if(param.compareTo("Object") == 0) {
			vr = factory.createVariableReference();
			vr.setVariable(Object.getName());
			list = objectNamesList;
		}
		if(param.compareTo("Property") == 0) {
			vr = factory.createVariableReference();
			vr.setVariable(Property.getName());
			list = momentNamesList;
		}
		if(param.compareTo("Datatype") == 0) {
			vr = factory.createVariableReference();
			vr.setVariable(Datatype.getName());
			list = datatypeNamesList;
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
		if(subsortalDisjNamesList.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String s : subsortalDisjNamesList)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(s);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
		
		//DataTypes
		if(datatypeDisjNamesList.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String datatype : datatypeDisjNamesList)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(datatype);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
		
		//Properties
		if(momentDisjNamesList.size()>1)
		{
			disj = factory.createDisjointExpression();
			for(String Property : momentDisjNamesList)
			{
				VariableReference vr = factory.createVariableReference();
				vr.setVariable(Property);
				disj.getSet().add(vr);
			}
			world.getBlock().getExpression().add(disj);
		}
	}

}
