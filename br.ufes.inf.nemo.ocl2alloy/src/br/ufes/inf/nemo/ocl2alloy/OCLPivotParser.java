package br.ufes.inf.nemo.ocl2alloy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManagerResourceSetAdapter;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

/**
 * @author John Guerson
 */

public class OCLPivotParser {
	
	public OCL ocl;
	public Resource pivotResource;
	public Map<String,ExpressionInOCL> constraintMap;
	
	public Resource umlResource;
	public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> umlHashMap;    
	
    public OCLPivotParser (String refAbsolutePath)  throws IOException
	{ 	
    	org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
    	    	
    	OntoUMLParser refparser = new OntoUMLParser(refAbsolutePath);
    	
    	// Transform to UML
    	umlResource = OntoUML2UML.Transformation(refparser,refAbsolutePath.replace(".refontouml" , ".uml"),true);		
    	umlHashMap = OntoUML2UML.transformer.mydealer.mymap;
    	    	   	
    	// Register UML resource set and OCL
    	org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);		
		umlResource.getResourceSet().getPackageRegistry().put(refAbsolutePath.replace(".refontouml" , ".uml"),umlmodel);		
		
		OCL.initialize(umlResource.getResourceSet());
		org.eclipse.ocl.examples.pivot.uml.UML2Pivot.initialize(umlResource.getResourceSet());
		//org.eclipse.uml2.uml.resources.util.UMLResourcesUtil.init(umlResource.getResourceSet())		
		StandaloneProjectMap projectMap = org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(umlResource.getResourceSet());
		org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(umlResource.getResourceSet());
		
		// MetaModel Manager
		MetaModelManager metaModelManager = new MetaModelManager(projectMap);		
		MetaModelManagerResourceSetAdapter.getAdapter(umlResource.getResourceSet(), metaModelManager);
		
		// Create OCL						
    	PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(umlResource.getResourceSet().getPackageRegistry(), metaModelManager);
    	ocl = OCL.newInstance(environmentFactory.createEnvironment());
    	    	
    	System.out.println("OCL Pivot initialized");
	}
        
    public void parse(String oclAbsolutePath) throws ParserException
    {
    	File oclfile = new File(oclAbsolutePath);
    	URI ocluri = getURI(oclfile);  	
    	pivotResource = ocl.parse(ocluri);
    	
    	for(Constraint constraint: getConstraints())
    	{    			
			constraintMap = new HashMap<String, ExpressionInOCL>();
	    		    	
	    	ExpressionInOCL expressionInOCL = ocl.getSpecification(constraint);
	    	if (expressionInOCL != null){
	    		String name = constraint.getName();	    		
	    		constraintMap.put(name, expressionInOCL);
	    		System.out.printf("%s: %s\n",name,expressionInOCL.getBodyExpression());	    			    	
	    	}
    	}
    	
    	System.out.println("OCL parsed");
    }
        
    public ArrayList<Constraint> getConstraints()
    {    	
    	ArrayList<Constraint> constraintList = new ArrayList<Constraint>();
    	for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();) {
    		EObject next = tit.next();
    		if (next instanceof Constraint){
    			Constraint constraint = (Constraint)next;
    			constraintList.add(constraint);
    		}
    	}
    	return constraintList;
    }
    
    /**
	 * Convert a file name to an EMF URI
	 * 
	 * @param file
	 * @return EMF URI
	 */
	public static URI getURI(File file) {
		return URI.createFileURI(file.getAbsolutePath());
	}
	
	
    public static void main (String[] args)
    {    	
    	String refpath = "model/project.refontouml";
    	String oclPath = "model/project.ocl";
    	
    	try {
    		
    		OCLPivotParser oclparser = new OCLPivotParser(refpath);
    		oclparser.parse(oclPath);
    		
		} catch (IOException e) {			
			e.printStackTrace();		
		} catch(Exception e){
			e.printStackTrace();
		}
    }    
   
}
