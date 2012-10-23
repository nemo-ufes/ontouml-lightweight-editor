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
		//URI fileURI = URI.createFileURI(new File("models/XML Models/ImpreciseAbstraction.xmi").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("models/XML Models/Surgery.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOSSimple.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
		
		NamesMapper mapper = new NamesMapper(m);
			
		try {
		    	    
		    ArrayList<STRAntiPattern> result = AntiPatternIdentifier.identifySTR(m, mapper);
		    System.out.println("#SelfTypeRelationship Antipatterns: "+result.size()+"\n");
		    
		    for (STRAntiPattern str : result) {
		    	System.out.println(str);
		    	/*System.out.println(str.generateTransitivePredicate(4));
		    	System.out.println(str.generateIntransitivePredicate(4));
		    	System.out.println(str.generateReflexivePredicate(4));
		    	System.out.println(str.generateIrreflexivePredicate(4));
		    	System.out.println(str.generateSymmetricPredicate(4));
		    	System.out.println(str.generateAntisymmetricPredicate(4));    */	
		    	
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RWORAntiPattern> result21 = AntiPatternIdentifier.identifyRWOR(m, mapper);
		    System.out.println("#Relator With Overlapping Roles Antipatterns: "+result21.size()+"\n");
		    for (RWORAntiPattern rwor : result21){
		    	System.out.println(rwor);
		    	/*System.out.println(rwor.generateExclusivePredicate());
		    	System.out.println(rwor.generateNonExclusivePredicate());
		    	System.out.println(rwor.generateMultipleExclusivePredicate());*/
		    }
		    System.out.println("**************************************************************");
		    
		    
		    ArrayList<RBOSAntiPattern> result3 = AntiPatternIdentifier.identifyRBOS(m, mapper);
		    System.out.println("#Relation Between Overlapping Subtypes Antipatterns: "+result3.size()+"\n");
		    for (RBOSAntiPattern rbos : result3){
		    	System.out.println(rbos);
		    	/*System.out.println(rbos.generateDisjointPredicate());
		    	System.out.println(rbos.generateOverlappingPredicate());*/
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RSAntiPattern> result4 = AntiPatternIdentifier.identifyRS(m, mapper);
		    System.out.println("#Relation Specialization Antipatterns: "+result4.size()+"\n");
		    for (RSAntiPattern rs : result4) {
		    	System.out.println(rs+"\n");
		    	/*System.out.println(rs.generateSubsetPredicate());
		    	System.out.println(rs.generateRedefinePredicate());
		    	System.out.println(rs.generateNotSubsetPredicate());
		    	System.out.println(rs.generateDisjointPredicate());
		    	*/		    	
		    }
		    System.out.println("**************************************************************");
		    
		    Collection<Association> result5 = IAIdentifier.IAQuery(m);
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result5.size()+"\n");
		    for (Association a : result5){
		    	System.out.println(new IAAntiPattern(a, mapper)+"\n");
		    	//System.out.println(IAAlloyGenerator.impreciseAbstractionPredicates(a, mapper));
		    }
		    
		    ArrayList<IAAntiPattern> result51 = AntiPatternIdentifier.identifyIA(m, mapper);
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result51.size()+"\n");
		    for (IAAntiPattern ia : result51){
		    	System.out.println(ia+"\n");
		    	//System.out.println(IAAlloyGenerator.impreciseAbstractionPredicates(a, mapper));
		    }
				    		    
		} catch (ParserException e) {
		    // record failure to parse
		    //valid = false;
		    System.err.println(e.getLocalizedMessage());
		}
		
		
		
        
	}
}