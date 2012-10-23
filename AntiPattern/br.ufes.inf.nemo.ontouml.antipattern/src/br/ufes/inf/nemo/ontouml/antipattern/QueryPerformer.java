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
import br.ufes.inf.nemo.ontouml.antipattern.deprecated.RBOSIdentifier;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import RefOntoUML.Association;
import RefOntoUML.Model;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Relator;

public class QueryPerformer {
	public static void main(String[] args) throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
		//URI fileURI = URI.createFileURI(new File("models/XML Models/ImpreciseAbstraction.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/Surgery.xmi").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("models/XML Models/RBOSSimple.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
		
		NamesMapper mapper = new NamesMapper(m);
			
		try {
		    	    
		    ArrayList<STRAntiPattern> result = AntiPatternIdentifier.identifySTR(m, mapper);
		    
		    System.out.println("#SelfTypeRelationship Antipatterns: "+result.size());
		    
		    for (STRAntiPattern str : result) {
		    	System.out.println(str+"\n");
		    	/*System.out.println(str.generateTransitivePredicate(4));
		    	System.out.println(str.generateIntransitivePredicate(4));
		    	System.out.println(str.generateReflexivePredicate(4));
		    	System.out.println(str.generateIrreflexivePredicate(4));
		    	System.out.println(str.generateSymmetricPredicate(4));
		    	System.out.println(str.generateAntisymmetricPredicate(4));    */	
		    	
		    }
		    
		    Collection<Relator>result2 = RWORIdentifier.RWORQuery(m);
		    System.out.println("#Relator With Overlapping Roles Antipatterns: "+result2.size());
	
		    for (Relator c : result2) {
		    	System.out.println(RWORAlloyGenerator.ExclusiveRolesAlloyPredicate(c, mapper));
		    	System.out.println(RWORAlloyGenerator.NonExclusiveRolesAlloyPredicate(c, mapper));
		    	System.out.println(RWORAlloyGenerator.MultipleExclusiveRolesAlloyPredicate(c,mapper));

		    }
		    
		    Collection<Association> result3 = RBOSIdentifier.RBOSQuery(m);
		    System.out.println("#Relation Between Overlapping Subtypes Antipatterns: "+result3.size());
		    for (Association c : result3) {
		    	System.out.println(new RBOSAntiPattern(c, mapper)+"\n");
		    	/*System.out.println(RBOSAlloyGenerator.DisjointParticipantsAlloyPredicate(c, mapper));
		    	System.out.println(RBOSAlloyGenerator.OverlappingParticipantsAlloyPredicate(c, mapper));*/
		    }
		    
		    ArrayList<RSAntiPattern> result4 = AntiPatternIdentifier.identifyRS(m, mapper);
		    System.out.println("#Relation Specialization Antipatterns: "+result4.size());
		    
		    for (RSAntiPattern rs : result4) {
		    	System.out.println(rs+"\n");
		    	/*System.out.println(rs.generateSubsetPredicate());
		    	System.out.println(rs.generateRedefinePredicate());
		    	System.out.println(rs.generateNotSubsetPredicate());
		    	System.out.println(rs.generateDisjointPredicate());
		    	*/		    	
		    }
		    
		    Collection<Association> result5 = IAIdentifier.IAQuery(m);
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result5.size());
		    
		    for (Association a : result5){
		    	System.out.println(IAAlloyGenerator.impreciseAbstractionPredicates(a, mapper));
		    }
		    
		    ACIdentifier.IAQuery(m, 5);
		    		    
		} catch (ParserException e) {
		    // record failure to parse
		    //valid = false;
		    System.err.println(e.getLocalizedMessage());
		}
		
		
		
        
	}
}