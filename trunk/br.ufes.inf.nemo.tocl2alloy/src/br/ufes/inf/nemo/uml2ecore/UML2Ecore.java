package br.ufes.inf.nemo.uml2ecore;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.uml2.uml.util.UMLUtil;

import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLUtil;

public class UML2Ecore {

	public static Collection<EPackage> convertToEcore (org.eclipse.uml2.uml.Package umlmodel, String ecorePath)
	{
	    ResourceSet ecoreResourceSet = new ResourceSetImpl();
	    URI ecoreURI = URI.createFileURI(ecorePath);

	    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	    ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	    ecoreResourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
	    
		Collection<EPackage> ecoreList = UMLUtil.convertToEcore(umlmodel, null);
		
		for (Iterator<EPackage> iterator = ecoreList.iterator(); iterator.hasNext();)
		{			
			EPackage ecoremodel = (EPackage) iterator.next();			
			ecoreResourceSet.getPackageRegistry().put(null,ecoremodel);
			Resource ecoreResource = ecoreResourceSet.createResource(ecoreURI);
			ecoreResource.getContents().add(ecoremodel);			
		    try {
		    	ecoreResource.save(Collections.EMPTY_MAP);
		    } catch (IOException e) {		      
		      e.printStackTrace();
		    }
		}
		return ecoreList;
	}
			
	public static Collection<EPackage> convertToEcore (String umlPath, String ecorePath)
	{			
		org.eclipse.uml2.uml.Package umlmodel = OntoUML2UMLUtil.readUML(umlPath);
	    
		return convertToEcore(umlmodel,ecorePath);		
	}
}
