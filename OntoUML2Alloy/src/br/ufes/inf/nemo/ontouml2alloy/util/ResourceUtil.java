package br.ufes.inf.nemo.ontouml2alloy.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * This class is used as a Resource utility.
 * 
 * @author John Guerson
 */

public class ResourceUtil {

	/**
	 * Load Reference OntoUML Model to a Resource.
	 * 
	 * @param path: RefOntoUML Model Absolute Path
	 * @return
	 * @throws IOException
	 */
	public static Resource loadOntoUML (String path) throws IOException
	{
		ResourceSet rset = new ResourceSetImpl();				
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new XMIResourceFactoryImpl());	
		
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
				
		File file = new File(path);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());		
		Resource resource = rset.createResource(fileURI);		
		
		resource.load(Collections.emptyMap());
		
		return resource;		
	}
}
