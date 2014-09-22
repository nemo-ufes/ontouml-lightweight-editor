package RefOntoUML.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class RefOntoUMLResourceUtil {

	/** Load reference model to a resource. */
	public static Resource loadModel (String refontoumlpath) throws IOException
	{
		ResourceSet rset = new ResourceSetImpl();			
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new RefOntoUMLResourceFactoryImpl());
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
	    File file = new File(refontoumlpath);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());		
		Resource resource = rset.createResource(fileURI);		
		/**Load options that significantly improved the performance of loading EMF Model instances*/
		Map<Object,Object> loadOptions = ((XMLResourceImpl)resource).getDefaultLoadOptions();
		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		resource.load(loadOptions);		
		return resource;		
	}
	
	/** Save reference model to a resource. */	
	public static Resource saveModel (String refontoumlpath, RefOntoUML.Package refmodel) 
	{
		ResourceSet rset = new ResourceSetImpl();				
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("refontouml",new RefOntoUMLResourceFactoryImpl());
		rset.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,	RefOntoUML.RefOntoUMLPackage.eINSTANCE);
		URI fileURI = URI.createFileURI(refontoumlpath);    
	    final Resource resource = rset.createResource(fileURI);    	
	    resource.getContents().add(refmodel);    	
	    try{
	    	resource.save(Collections.emptyMap());
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    return resource;		   	
	}
}
