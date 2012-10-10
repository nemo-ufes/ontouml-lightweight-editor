package br.ufes.inf.nemo.ontouml.antipattern;

import java.io.File;
import java.util.Collection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.util.Tuple;

import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import RefOntoUML.Association;
import RefOntoUML.Model;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Relator;

public class QueryPerformer {
	public static void main(String[] args) {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
		//URI fileURI = URI.createFileURI(new File("models/XML Models/ImpreciseAbstraction.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/Surgery.xmi").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("models/XML Models/ImpreciseAbstraction.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
		
		NamesMapper mapper = new NamesMapper(m);
			
		try {
		    	    
		    Collection<Association> result = STRIdentifier.STRQuery(m);
		    
		    System.out.println("#SelfTypeRelationship Antipatterns: "+result.size());
		    String type;
		    String association;
		    String cardinality = "4";
		    
		    for (Association c : result) {
		    	type = mapper.elementsMap.get(c.getOwnedEnd().get(0).getType());
		    	association = mapper.elementsMap.get(c);
		    	  	
		    	System.out.println(STRAlloyGenerator.SymmetricAlloyPredicate(type, association, cardinality));
		    	System.out.println(STRAlloyGenerator.AntisymmetricAlloyPredicate(type, association, cardinality));
		    	System.out.println(STRAlloyGenerator.ReflexiveAlloyPredicate(type, association, cardinality));
				System.out.println(STRAlloyGenerator.IrreflexiveAlloyPredicate(type, association, cardinality));
		    	System.out.println(STRAlloyGenerator.TransitiveAlloyPredicate(type, association, cardinality));
		    	System.out.println(STRAlloyGenerator.IntransitiveAlloyPredicate(type, association, cardinality));
		    	
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
		    	System.out.println(RBOSAlloyGenerator.DisjointParticipantsAlloyPredicate(c, mapper));
		    	System.out.println(RBOSAlloyGenerator.OverlappingParticipantsAlloyPredicate(c, mapper));
		    }
		    
		    Collection<Tuple<Association,Association>> result4 = RSIdentifier.RSQuery(m);
		    System.out.println("#Relation Specialization Antipatterns: "+result4.size());
		    
		    for (Tuple<Association,Association> o : result4) {
		    	System.out.println(RSAlloyGenerator.subsetAlloyPredicate((Association)o.getValue("a1"), (Association)o.getValue("a2"), mapper));
		    	System.out.println(RSAlloyGenerator.notSubsetAlloyPredicate((Association)o.getValue("a1"), (Association)o.getValue("a2"), mapper));
		    	System.out.println(RSAlloyGenerator.redefineAlloyPredicate((Association)o.getValue("a1"), (Association)o.getValue("a2"), mapper));
		    	System.out.println(RSAlloyGenerator.disjointAlloyPredicate((Association)o.getValue("a1"), (Association)o.getValue("a2"), mapper));
		    }
		    
		    Collection<Association> result5 = IAIdentifier.IAQuery(m);
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result5.size());
		    
		    for (Association a : result5){
		    	System.out.println(IAAlloyGenerator.impreciseAbstractionPredicates(a, mapper));
		    }
		    		    
		} catch (ParserException e) {
		    // record failure to parse
		    //valid = false;
		    System.err.println(e.getLocalizedMessage());
		}
		
		
		
        
	}
}