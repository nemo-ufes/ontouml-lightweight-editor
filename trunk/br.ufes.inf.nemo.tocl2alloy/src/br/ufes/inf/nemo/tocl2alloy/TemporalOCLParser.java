package br.ufes.inf.nemo.tocl2alloy;

import java.util.ArrayList;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.pivot.uml.UML2Pivot;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.examples.xtext.base.baseCST.SpecificationCS;
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

public class TemporalOCLParser {

	public static void main(String[]args) throws ParserException
	{		
		String toclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.tocl";
		URI toclURI = URI.createFileURI(toclPath);

		String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";
		URI oclURI = URI.createFileURI(oclPath);
		
		String umlPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.uml";
		URI umlURI = URI.createFileURI(umlPath);
		
		String pivotPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.pivot";
		URI pivotURI = URI.createFileURI(pivotPath);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		//===========================
		//CONFIG
		
		TemporalOCLStandaloneSetup.doSetup();
		
		OCLstdlib.install();		
		org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();    	
    	org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
		
		UMLResourcesUtil.init(resourceSet);
		org.eclipse.ocl.examples.pivot.uml.UML2Pivot.initialize(resourceSet);		
		OCL.initialize(resourceSet);
		org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(resourceSet);
		org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(resourceSet);
		
		//===========================
		//UML
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);			
	    Resource umlResource = resourceSet.getResource(umlURI,true);
	    org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
	    umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);
		
		//===========================
		//Pivot
	    
	    MetaModelManager pivotManager = new MetaModelManager();
        UML2Pivot uml2Pivot = UML2Pivot.getAdapter(umlResource, pivotManager);
        org.eclipse.ocl.examples.pivot.Package pivotRoot = uml2Pivot.getPivotRoot();
        Resource pivotResource = pivotRoot.eResource();
        pivotResource.setURI(pivotURI);
                
		//===========================
        //OCL
        
        Resource toclResource = resourceSet.getResource(toclURI, true);
                
        PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(
				pivotResource.getResourceSet().getPackageRegistry(), pivotManager);	    
	    OCL ocl = OCL.newInstance(environmentFactory);	    
	    pivotResource = ocl.parse(toclURI);

	    for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();){
	    	EObject next = tit.next();
	    	System.out.println(next);
	    	if(next instanceof Constraint){	    		
	    		System.out.println(next);	    		
	    	}
    	}	    
	    System.out.println("done");
                		
		//===========================		
	    
	    TemporalOCLDocumentCS toclDocument = (TemporalOCLDocumentCS) toclResource.getContents().get(0);
				
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
				SpecificationCS spec = tocl_prop.getSpecification();
		
				System.out.println(spec);
								
			}else if (tocl_prop instanceof InvCS)
			{
				System.out.println(tocl_prop.getSpecification());
			}else if (tocl_prop instanceof DerCS)
			{
				System.out.println(tocl_prop.getSpecification());
			}
		}
		System.out.println("executed");
		
	}
	
}

		
