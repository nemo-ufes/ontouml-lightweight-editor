package br.ufes.inf.nemo.tocl2alloy;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

public class PivotOCLParser {

	public static void main(String[]args)
	{		
		String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";
		URI oclURI = URI.createFileURI(oclPath);
				
		String umlPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.uml";
		URI umlURI = URI.createFileURI(umlPath);	
	
		//=================================
		
		OCLstdlib.install();		
		org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();    	
    	org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();

		ResourceSet resourceSet = new ResourceSetImpl();
		UMLResourcesUtil.init(resourceSet);
		org.eclipse.ocl.examples.pivot.uml.UML2Pivot.initialize(resourceSet);		
		OCL.initialize(resourceSet);
		org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(resourceSet);
		org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(resourceSet);
						
		//=================================
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);			
	    Resource umlResource = resourceSet.getResource(umlURI,true);
	    org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
	    umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);
		
		//=============================

		PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(
			umlResource.getResourceSet().getPackageRegistry(), null);	    
	    OCL ocl = OCL.newInstance(environmentFactory);	    
	    Resource pivotResource = ocl.parse(oclURI);

	    for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();){
	    	EObject next = tit.next();
	    	System.out.println(next);
	    	if(next instanceof Constraint){	    		
	    		System.out.println(next);	    		
	    	}
    	}	    
	    System.out.println("done");
	}
}

		
		
		
		
		

