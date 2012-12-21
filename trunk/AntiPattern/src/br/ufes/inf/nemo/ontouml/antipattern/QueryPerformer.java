package br.ufes.inf.nemo.ontouml.antipattern;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.ParserException;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Model;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class QueryPerformer {
	public static void main(String[] args) throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		@SuppressWarnings("unused")
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
		//URI fileURI = URI.createFileURI(new File("models/XML Models/ImpreciseAbstraction.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/Surgery.xmi").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("models/XML Models/alocação de recursos.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOS_regular_and_inverted.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOSSimple.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
		
		OntoUMLParser parser = new OntoUMLParser(m);
		
		
		
		
		try {
		    	    
		    ArrayList<STRAntiPattern> result = AntiPatternIdentifier.identifySTR(parser);
		    System.out.println("#SelfTypeRelationship Antipatterns: "+result.size()+"\n");
		    
		    for (STRAntiPattern str : result) {
		    	System.out.println(str+"\n");
		    	/*System.out.println(str.generateTransitivePredicate(4,mapper));
		    	System.out.println(str.generateIntransitivePredicate(4,mapper));
		    	System.out.println(str.generateReflexivePredicate(4,mapper));
		    	System.out.println(str.generateIrreflexivePredicate(4,mapper));
		    	System.out.println(str.generateSymmetricPredicate(4,mapper));
		    	System.out.println(str.generateAntisymmetricPredicate(4,mapper));*/
		    	
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RWORAntiPattern> result21 = AntiPatternIdentifier.identifyRWOR(parser);
		    System.out.println("Number of identified RWOR Anti-Patterns: "+result21.size()+"\n");
		    for (RWORAntiPattern rwor : result21){
		    	System.out.println(rwor);
		    	/*System.out.println(rwor.generateExclusivePredicate(mapper, 1));
		    	System.out.println(rwor.generateOverlappingPredicate(mapper, 1));*/
		    	
		    	/*ArrayList<Classifier> classes = new ArrayList<>();
		    	classes.addAll(rwor.getMediations().values());
		    	System.out.println(rwor.generateExclusiveOcl(classes));
		    	
		    	
		    	System.out.println("#######");*/
		    	
		    	/*	
		    	ArrayList<Mediation> list1, list2;
		    	ArrayList<ArrayList<Mediation>> matrix = new ArrayList<>();
		    	
		    	list1 = new ArrayList<>(); 
		    	list1.addAll(rwor.getMediations().keySet());
		    	list1.remove(0);
		    	matrix.add(list1);
		    	System.out.println(rwor.generateMultipleExclusivePredicate(matrix, mapper, 1));
		    	
		    	list2 = new ArrayList<>();
		    	list2.addAll(rwor.getMediations().keySet());
		    	list2.remove(list2.size()-1);
		    	list2.remove(list2.size()-1);
		    	matrix.add(list2);
		    	System.out.println(rwor.generateMultipleExclusivePredicate(matrix, mapper, 1));
		    	
		    	System.out.println("#######");*/
		    }
		    System.out.println("**************************************************************");
		    
		    
		    ArrayList<RBOSAntiPattern> result3 = AntiPatternIdentifier.identifyRBOS(parser);
		    System.out.println("#Relation Between Overlapping Subtypes Antipatterns: "+result3.size()+"\n");
		    for (RBOSAntiPattern rbos : result3){
		    	System.out.println(rbos);
		    	/*System.out.println(rbos.generateIrreflexiveOcl());
		    	System.out.println(rbos.generateReflexiveOcl());
		    	System.out.println(rbos.generateDisjointPredicate(mapper));
		    	System.out.println(rbos.generateOverlappingPredicate(mapper));*/
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RSAntiPattern> result4 = AntiPatternIdentifier.identifyRS(parser);
		    System.out.println("#Relation Specialization Antipatterns: "+result4.size()+"\n");
		    for (RSAntiPattern rs : result4) {
		    	System.out.println(rs);
		    	/*System.out.println(rs.generateOcl(RSAntiPattern.SUBSET));
		    	System.out.println(rs.generateOcl(RSAntiPattern.REDEFINE));
		    	System.out.println(rs.generateOcl(RSAntiPattern.NONSUBSET));
		    	System.out.println(rs.generateOcl(RSAntiPattern.DISJOINT));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.SUBSET));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.REDEFINE));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.NONSUBSET));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.DISJOINT));
		    	System.out.println();*/
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<IAAntiPattern> result5 = AntiPatternIdentifier.identifyIA(parser);
		    
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result5.size()+"\n");
		    for (IAAntiPattern ia : result5){
		    	System.out.println(ia+"\n");
		    	//System.out.println(ia.generateTargetPredicate(ia.getTargetChildren(), mapper));
		    	/*ArrayList<Classifier> subtypes = new ArrayList<>();
		    	subtypes.addAll(ia.getSourceChildren());
		    	
		    	System.out.println(ia.generateSourceOcl(subtypes));
		    	
		    	subtypes = new ArrayList<>();
		    	subtypes.addAll(ia.getTargetChildren());
		    	
		    	System.out.println(ia.generateTargetOcl(subtypes));
		    	
		    	//System.out.println(ia.generateSourcePredicate(subtypes, mapper));
		    	//System.out.println(ia.generateImpreciseAbstractionPredicates(mapper));*/
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<ACAntiPattern> result6 = AntiPatternIdentifier.identifyAC(parser);
			System.out.println("# of Generic Cycles Identified: "+result6.size());
			for (ACAntiPattern ac : result6) {
				System.out.println(ac);
				/*System.out.println(ac.generateCycleOcl(ACAntiPattern.CLOSED));
				System.out.println(ac.generateCycleOcl(ACAntiPattern.OPEN));
				*/System.out.println(ac.generatePredicate(parser, 2, ACAntiPattern.OPEN));
				System.out.println(ac.generatePredicate(parser, 2, ACAntiPattern.CLOSED));
			}
				    		    
		} catch (ParserException e) {
		    // record failure to parse
		    //valid = false;
		    System.err.println(e.getLocalizedMessage());
		}
		
		for (EObject k : parser.getAllInstances(AntiRigidSortalClass.class)) {
			System.out.println(parser.getStringRepresentation(k));
		}
		
        
	}
}