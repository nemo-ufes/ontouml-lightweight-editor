package br.ufes.inf.nemo.ocl2alloy.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

/**
 * @author John Guerson
 */

public class OCLParser {
	
	public List<Constraint> umlconstraintsList;
	
	public org.eclipse.ocl.uml.UMLEnvironment umlenv;
	
    public org.eclipse.ocl.utilities.UMLReflection <
    	org.eclipse.uml2.uml.Package,
    	org.eclipse.uml2.uml.Classifier,
    	org.eclipse.uml2.uml.Operation,
    	org.eclipse.uml2.uml.Property,
    	org.eclipse.uml2.uml.EnumerationLiteral,
    	org.eclipse.uml2.uml.Parameter,
    	org.eclipse.uml2.uml.State,
    	org.eclipse.uml2.uml.CallOperationAction,
    	org.eclipse.uml2.uml.SendSignalAction,
    	org.eclipse.uml2.uml.Constraint
    > umlreflection;
    
    public Resource umlResource;    
        
    public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> umlHashMap;
    
    /**
     * Get OntoUML Element from UML Element.
     * 
     * @param value
     * @return
     */
    public RefOntoUML.Element getKeyByValue(org.eclipse.uml2.uml.Element value) 
    {
        for (Entry<RefOntoUML.Element,org.eclipse.uml2.uml.Element> entry : umlHashMap.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }
    
    /**
     * Constructor.
     * 
     * @param refmodel
     * @param oclConstraints
     * @param umlPath
     */

    public OCLParser (String oclConstraints, RefOntoUML.Package refmodel, String umlPath) throws ParserException
    {	
    	if (refmodel==null) return;
    	if (umlPath == null) return;
    	
		umlResource = OntoUML2UML.Transformation(refmodel,umlPath);
		
		umlHashMap = OntoUML2UML.transformer.mydealer.mymap;
		
		org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);			
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
		
		// this line was added due to a bug of Eclipse :
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
		Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		org.eclipse.ocl.uml.OCL myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);		
		
		OCLInput document = new OCLInput(oclConstraints);		
		
		umlconstraintsList = myOCL.parse(document);
		umlreflection = umlenv.getUMLReflection();	
    }
    
    /**
     * Constructor.
     * 
     * @param oclAbsolutePath
     * @param refAbsolutePath
     * @throws IOException
     * @throws ParserException
     */
    public OCLParser (String oclAbsolutePath, String refAbsolutePath) throws IOException,ParserException
	{ 			
		umlResource = OntoUML2UML.Transformation(refAbsolutePath);							
				
		umlHashMap = OntoUML2UML.transformer.mydealer.mymap;
		
		org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);		
		umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);		
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
		
		// this line was added due to a bug of Eclipse :
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
		Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		org.eclipse.ocl.uml.OCL myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);
		
		InputStream input = new FileInputStream(oclAbsolutePath);
		org.eclipse.ocl.OCLInput document = new org.eclipse.ocl.OCLInput(input);	
			
		umlconstraintsList = myOCL.parse(document);
		umlreflection = umlenv.getUMLReflection();
	}
    
	public List<Constraint> getConstraints()
	{
		return umlconstraintsList;
	} 

	public org.eclipse.ocl.uml.UMLEnvironment getUMLEnvironment()
	{
		return umlenv;
	}

    public Resource getUMLResource()
    {
    	return umlResource;
    }
    
    public  org.eclipse.ocl.utilities.UMLReflection <
			org.eclipse.uml2.uml.Package,
			org.eclipse.uml2.uml.Classifier,
			org.eclipse.uml2.uml.Operation,
			org.eclipse.uml2.uml.Property,
			org.eclipse.uml2.uml.EnumerationLiteral,
			org.eclipse.uml2.uml.Parameter,
			org.eclipse.uml2.uml.State,
			org.eclipse.uml2.uml.CallOperationAction,
			org.eclipse.uml2.uml.SendSignalAction,
			org.eclipse.uml2.uml.Constraint > 
    getUMLReflection()
	{
		return umlreflection;
	}
    
    /**
     * Test.
     */
    public static void main (String[] args)
    {    	
    	String refpath = "C:\\Users\\John\\SVNs\\SVN-OLED\\OCL2Alloy\\model\\project.refontouml";
    	String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\OCL2Alloy\\model\\project.ocl";
    	
    	try {
    		
    		new OCLParser(oclPath, refpath);
		
		} catch (IOException | ParserException e) {			
			e.printStackTrace();
		}
    }    
}
