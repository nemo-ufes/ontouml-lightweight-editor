package br.ufes.inf.nemo.tocl2alloy;

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextConstraintCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DerCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.InvCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PackageDeclarationCS;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import fr.supelec.temporalocl.TemporalOCLStandaloneSetup;
import fr.supelec.temporalocl.temporalOCLCST.TempCS;
import fr.supelec.temporalocl.temporalOCLCST.TemporalOCLDocumentCS;

public class Main {

	public static void main(String[]args)
	{		
		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.tocl";
		URI toclURI = URI.createFileURI(oclPath);
		
		String umlPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.uml";
		URI umlURI = URI.createFileURI(umlPath);	
	
    	// =======================================
    	// OCL Pivot Standalone Configuration
    	// =======================================
    	org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
    	    	
    	// =======================================    	
		// Initialize Temporal OCL Plugin
    	// =======================================
		TemporalOCLStandaloneSetup.doSetup();
		OCLstdlib.install();
		
		// =======================================
		// Loading Temporal OCL file
		// =======================================				
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource tocl = resourceSet.getResource(toclURI, true);
		TemporalOCLDocumentCS toclDocument = (TemporalOCLDocumentCS) tocl.getContents().get(0);

		// =======================================
    	// OCL Pivot Standalone Configuration
    	// =======================================
		org.eclipse.ocl.examples.pivot.OCL.initialize(resourceSet);
		org.eclipse.ocl.examples.pivot.uml.UML2Pivot.initialize(resourceSet);
		org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(resourceSet);
		org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(resourceSet);
				
		// =========================================
		// Loading the corresponding UML file
		// =========================================			
		UMLResourcesUtil.init(resourceSet);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);			
	    Resource umlResource = resourceSet.getResource(umlURI,true);
	    org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);

		// =========================================
		// Parsing OCL document
		// =========================================
		//OCL.initialize(umlResource.getResourceSet());
		//PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(resourceSet.getPackageRegistry(),new MetaModelManager());
	    //PivotEnvironment env = environmentFactory.createEnvironment();
	    //Environment.Registry.INSTANCE.registerEnvironment(env); 
		// this line above was added due to a bug of Eclipse : https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
	    //OCL ocl = OCL.newInstance(env);	    
	    //Resource pivotResource = ocl.parse(toclURI);
	    
    	// =======================================
	    // Generating the corresponding Pivot 
    	// =======================================
	    //MetaModelManager pivotManager = new MetaModelManager();
        ///UML2Pivot uml2Pivot = UML2Pivot.getAdapter(umlResource, pivotManager);
        //org.eclipse.ocl.examples.pivot.Package pivotRoot = uml2Pivot.getPivotRoot();        
        //Resource pivotResource = pivotRoot.eResource();        
        //pivotResource.setURI(pivotURI);
	    
    	
	    //System.out.println(tocl.getContents().get(0));
	    System.out.println(tocl.getAllContents());
	    
	    /*for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();){
	    	EObject next = tit.next();
	    	if(next instanceof Constraint){
	    		Constraint constraint = (Constraint)next;
	    		String stereotype = constraint.getStereotype();
	    		System.out.println(constraint);
	    		System.out.println(stereotype);
	    	}
	    }*/
	    
		// collecting all rules of the TOCL file
		ArrayList<ContextConstraintCS>  tocl_rules = new  ArrayList<ContextConstraintCS>();
		
		// collecting constraints outside the package declaration
		for (ContextDeclCS cd : toclDocument.getContexts()) 
		{			 
			tocl_rules.addAll(cd.getRules()); 
		}
		
		// collecting constraints inside the package declaration
		for (PackageDeclarationCS pd : toclDocument.getPackages())
		{
			for (ContextDeclCS cd : pd.getContexts()) 
			{ 							
				tocl_rules.addAll(cd.getRules()); 
			}
		}
				
		// do something with constraints
		for (ContextConstraintCS tocl_prop : tocl_rules)
		{
			if (tocl_prop instanceof TempCS)
			{
				//System.out.println(tocl_prop.getSpecification());

			}else if (tocl_prop instanceof InvCS)
			{
				//System.out.println(tocl_prop.getSpecification());
			}else if (tocl_prop instanceof DerCS)
			{
				//System.out.println(tocl_prop.getSpecification());
			}
		}	
		
	}
}

		
		
		
		
		
			
	   