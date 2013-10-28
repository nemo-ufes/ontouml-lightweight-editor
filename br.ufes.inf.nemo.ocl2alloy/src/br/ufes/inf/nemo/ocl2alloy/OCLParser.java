package br.ufes.inf.nemo.ocl2alloy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.cst.CSTNode;
import org.eclipse.ocl.parser.OCLAnalyzer;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

/**
 * @author John Guerson
 */

public class OCLParser {
	
	public List<Constraint> umlconstraintsList;	
	public org.eclipse.ocl.uml.UMLEnvironment umlenv;	
    public Resource umlResource;            
    public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> umlHashMap;       
    public String logDetails = new String();
    public CSTNode cstree;    
    
    @SuppressWarnings("rawtypes") public org.eclipse.ocl.utilities.UMLReflection umlreflection;
	@SuppressWarnings("rawtypes") public OCLAnalyzer analyzer;
	
    /**
     * Get the OntoUML element which was transformed into this UML element. 
     * 
     * @param value
     * @return
     */
    public RefOntoUML.Element getOntoUMLElement(org.eclipse.uml2.uml.Element value) 
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
     * Constructor. It makes the parse.
     * 
     * @param refmodel
     * @param oclConstraints
     * @param umlPath
     */

    public OCLParser (String oclConstraints, OntoUMLParser refparser, String umlPath) throws ParserException,Exception
    {	
    	if (refparser==null) return;
    	if (umlPath==null) return;
    	
    	umlResource = OntoUML2UML.convertToUML(refparser,umlPath,true,false);		
    	umlHashMap = OntoUML2UML.getMap();
    	logDetails = OntoUML2UML.getLog();
    	
    	// this line was added due to a bug of Eclipse :
    	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
    	Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
    			
		org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);			
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		org.eclipse.ocl.uml.OCL myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);
				
		analyzer = myOCL.createAnalyzer(oclConstraints);
        cstree = analyzer.parseConcreteSyntax();
        
        if (cstree!=null && cstree.getStartToken().toString().equals("context")) 
        {
            oclConstraints = "package "+"_'"+umlmodel.getName()+"'\n\n"+oclConstraints+"\n endpackage\n\n";
                //String msg ="You need to specify your constraints inside a package declaration. \n\npackage PackageName\n...\nYour constraints\n...\nendpackage";
                //throw new Exception(msg);
        }
        
		OCLInput document = new OCLInput(oclConstraints);		
		umlconstraintsList = myOCL.parse(document);
		
		umlreflection = umlenv.getUMLReflection();	
    }
    
    /**
     * Constructor. It makes the parse.
     * 
     * @param oclAbsolutePath
     * @param refAbsolutePath
     * @throws IOException
     * @throws ParserException
     */
    public OCLParser (String oclAbsolutePath, String refAbsolutePath) throws IOException,ParserException,Exception
	{ 			
    	OntoUMLParser refparser = new OntoUMLParser(refAbsolutePath);
    	
    	umlResource = OntoUML2UML.convertToUML(refparser,refAbsolutePath.replace(".refontouml" , ".uml"),true,false);		
    	umlHashMap = OntoUML2UML.getMap();
    	logDetails = OntoUML2UML.getLog();
				
		// this line was added due to a bug of Eclipse :
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
		Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
				
		org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);		
		umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);		
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
				
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		org.eclipse.ocl.uml.OCL myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);
								
		 analyzer = myOCL.createAnalyzer(FileUtil.readFile(oclAbsolutePath));
         cstree = analyzer.parseConcreteSyntax();
         
         if (cstree!=null && cstree.getStartToken().toString().equals("context")) 
         {
                 String msg ="You need to specify your constraints inside a package declaration. \n\npackage PackageName\n...\nYour constraints\n...\nendpackage";
                 throw new Exception(msg);
         }
         
		InputStream input = new FileInputStream(oclAbsolutePath);
		org.eclipse.ocl.OCLInput document = new org.eclipse.ocl.OCLInput(input);	
			
		umlconstraintsList = myOCL.parse(document);
		umlreflection = umlenv.getUMLReflection();
	}
    
    /*
     * Getters...
     */
	public List<Constraint> getConstraints() { return umlconstraintsList; }
	public org.eclipse.ocl.uml.UMLEnvironment getUMLEnvironment() { return umlenv; }
	public String getDetails() { return logDetails; }	
    public Resource getUMLResource() { return umlResource; }    
    @SuppressWarnings("rawtypes") public  org.eclipse.ocl.utilities.UMLReflection getUMLReflection() { return umlreflection; }    
    public CSTNode getCSTree() { return cstree; }
    
    /**
     * Testing...
     */
    public static void main (String[] args)
    {    	
    	String refpath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.ocl2alloy\\model\\project.refontouml";
    	String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.ocl2alloy\\model\\project.ocl";
    	
    	try {
    		
    		new OCLParser(oclPath, refpath);
		
		} catch (IOException e) {			
			e.printStackTrace();
		} catch(ParserException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
    	
    	System.out.println("OCL parsed");
    }    
   
}
