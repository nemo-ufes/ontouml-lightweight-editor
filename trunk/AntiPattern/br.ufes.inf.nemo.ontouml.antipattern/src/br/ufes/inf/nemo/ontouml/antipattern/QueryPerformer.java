package br.ufes.inf.nemo.ontouml.antipattern;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import RefOntoUML.Association;
import RefOntoUML.Model;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;

public class QueryPerformer {
	public static void main(String[] args) throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(new File("models/XML Models/Georreferenciamento.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/Surgery.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOSSimple.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
		
		NamesMapper mapper = new NamesMapper(m);
			
		try {
		    	    
		    ArrayList<STRAntiPattern> result = AntiPatternIdentifier.identifySTR(m);
		    System.out.println("#SelfTypeRelationship Antipatterns: "+result.size()+"\n");
		    
		    for (STRAntiPattern str : result) {
		    	System.out.println(str+"\n");
		    	System.out.println(str.generateTransitivePredicate(4,mapper));
		    	System.out.println(str.generateIntransitivePredicate(4,mapper));
		    	System.out.println(str.generateReflexivePredicate(4,mapper));
		    	System.out.println(str.generateIrreflexivePredicate(4,mapper));
		    	System.out.println(str.generateSymmetricPredicate(4,mapper));
		    	System.out.println(str.generateAntisymmetricPredicate(4,mapper));
		    	
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RWORAntiPattern> result21 = AntiPatternIdentifier.identifyRWOR(m);
		    System.out.println("#Relator With Overlapping Roles Antipatterns: "+result21.size()+"\n");
		    for (RWORAntiPattern rwor : result21){
		    	System.out.println(rwor);
		    	System.out.println(rwor.generateExclusivePredicate(null));
		    	System.out.println(rwor.generateNonExclusivePredicate(null));
		    	System.out.println(rwor.generateAllMultipleExclusivePredicate(null));
		    }
		    System.out.println("**************************************************************");
		    
		    
		    ArrayList<RBOSAntiPattern> result3 = AntiPatternIdentifier.identifyRBOS(m);
		    System.out.println("#Relation Between Overlapping Subtypes Antipatterns: "+result3.size()+"\n");
		    for (RBOSAntiPattern rbos : result3){
		    	System.out.println(rbos);
		    	System.out.println(rbos.generateDisjointPredicate(mapper));
		    	System.out.println(rbos.generateOverlappingPredicate(mapper));
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RSAntiPattern> result4 = AntiPatternIdentifier.identifyRS(m);
		    System.out.println("#Relation Specialization Antipatterns: "+result4.size()+"\n");
		    for (RSAntiPattern rs : result4) {
		    	System.out.println(rs+"\n");
		    	System.out.println(rs.generateSubsetPredicate(mapper));
		    	System.out.println(rs.generateRedefinePredicate(mapper));
		    	System.out.println(rs.generateNotSubsetPredicate(mapper));
		    	System.out.println(rs.generateDisjointPredicate(mapper));
		    			    	
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<IAAntiPattern> result5 = AntiPatternIdentifier.identifyIA(m);
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result5.size()+"\n");
		    for (IAAntiPattern ia : result5){
		    	System.out.println(ia+"\n");
		    	System.out.println(ia.generateImpreciseAbstractionPredicates(mapper));
		    }
				    		    
		} catch (ParserException e) {
		    // record failure to parse
		    //valid = false;
		    System.err.println(e.getLocalizedMessage());
		}
		
		
		
        
	}
}