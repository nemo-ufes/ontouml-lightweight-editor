package br.ufes.inf.nemo.ocl2alloy;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class OCLPivotParser {
	            

	/**
	 * Convert a file name to an EMF URI
	 * 
	 * @param file
	 * @return EMF URI
	 */
	public static URI getURI(File file) {
		return URI.createFileURI(file.getAbsolutePath());
	}
	
    public OCLPivotParser (String oclAbsolutePath, String refAbsolutePath)  throws IOException
	{ 			    	
    	OntoUMLParser refparser = new OntoUMLParser(refAbsolutePath);
    	
    	EPackage.Registry registry = new EPackageRegistryImpl();
    	registry.put(null,refparser.getModel());
    	
    	PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(registry, null);
    	OCL ocl = OCL.newInstance(environmentFactory);
    	
    	File oclfile = new File(oclAbsolutePath);
    	URI ocluri = getURI(oclfile);
    	
    	ocl.parse(ocluri);		
	}
        
    /** Testing...  */
    public static void main (String[] args)
    {    	
    	String refpath = "model/project.refontouml";
    	String oclPath = "model/project.ocl";
    	
    	try {
    		
    		new OCLPivotParser(oclPath, refpath);
		
		} catch (IOException e) {			
			e.printStackTrace();		
		} catch(Exception e){
			e.printStackTrace();
		}
    }    
   
}
