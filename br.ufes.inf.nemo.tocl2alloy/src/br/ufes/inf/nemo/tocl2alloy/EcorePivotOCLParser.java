package br.ufes.inf.nemo.tocl2alloy;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;

import br.ufes.inf.nemo.uml2ecore.UML2Ecore;

import fr.supelec.temporalocl.TemporalOCLStandaloneSetup;

public class EcorePivotOCLParser {
	
	public static void main (String[] args)
	{				
		String umlPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.uml";			
		String ecorePath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ecore";	
		URI ecoreURI = URI.createFileURI(ecorePath);
		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";
		URI oclURI = URI.createFileURI(oclPath);						
		String toclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.tocl";
		URI toclURI = URI.createFileURI(toclPath);
		
		 UML2Ecore.convertToEcore(umlPath,ecorePath);
		 		 
		 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		 ResourceSet ecoreResourceSet = new ResourceSetImpl();
		 ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		 ecoreResourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		    
		 Resource ecoreResource = ecoreResourceSet.getResource(ecoreURI,true);
		 EPackage ecoremodel = (EPackage) ecoreResource.getContents().get(0);
		 ecoreResource.getResourceSet().getPackageRegistry().put(null,ecoremodel);
		 
		 OCLstdlib.install();		
		 org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
	     org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();    	
	     org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
		 OCL.initialize(ecoreResourceSet);
		 org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(ecoreResourceSet);
		 org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(ecoreResourceSet);							
		 TemporalOCLStandaloneSetup.doSetup();
		 
	     PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(ecoreResource.getResourceSet().getPackageRegistry(), null);
	     OCL ocl = OCL.newInstance(environmentFactory);
	
	     Resource pivotResource = ocl.parse(toclURI);

	    for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();){
	    	EObject next = tit.next();	    	
	    	if(next instanceof Constraint){	    		
	    		System.out.println(next);	    		
	    	}
    	}	    
	    
		System.out.println("executed");
		
	}
}
