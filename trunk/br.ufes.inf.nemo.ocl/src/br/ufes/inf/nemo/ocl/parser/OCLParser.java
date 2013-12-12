package br.ufes.inf.nemo.ocl.parser;

import java.io.File;
import java.io.IOException;
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

	// Uml
	public Resource umlResource;		
	public org.eclipse.ocl.uml.UMLEnvironment umlenv;                
    public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> umlHashMap;            
    public String umlFile;
    public org.eclipse.uml2.uml.Package umlRoot;    
    
    //Ocl
    public org.eclipse.ocl.uml.OCL myOCL;
    public List<Constraint> umlconstraintsList;
    public CSTNode cstree;
    @SuppressWarnings("rawtypes") public OCLAnalyzer analyzer;
    @SuppressWarnings("rawtypes") public org.eclipse.ocl.utilities.UMLReflection umlreflection;
	
    //OntoUml
    public OntoUMLParser refparser;
    
    //both
    public String logDetails = new String();
    
    /**
     * Constructor. 
     * It uses a OntoUML2UML transformation behind the scenes to orchestrate the constraints parsing.
     * 
     * @param refparser: OntoUML Parser
     * @param tempDirPath: Temporary Directory to store the background files needed fro parsing.
     * @param backgroundModelName: A name for the UML background model to be generated behind the scenes. If Null or empty the name will be "model" by default.
     */
	public OCLParser (OntoUMLParser refparser, String tempDirPath, String backgroundModelName)
    {	
    	if (refparser==null) return;
    	
    	this.refparser = refparser;
    	
    	if (tempDirPath==null) tempDirPath="";
    	
    	String umlPath = new String();
    	
    	if (backgroundModelName.isEmpty() || backgroundModelName ==null) backgroundModelName = "model";
    	
    	if (tempDirPath.endsWith(File.separator)) umlPath += tempDirPath + backgroundModelName + ".uml";
    	else umlPath += tempDirPath + File.separator + backgroundModelName + ".uml";
    	
    	umlResource = OntoUML2UML.convertToUML(refparser,umlPath,true,false);		
    	umlHashMap = OntoUML2UML.getMap();
    	logDetails = OntoUML2UML.getLog();
    	    	
    	umlFile = umlPath.substring(umlPath.lastIndexOf(File.separator)+1);
    	
    	// this line was added due to a bug of Eclipse :
    	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
    	Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
    			
		umlRoot = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		umlResource.getResourceSet().getPackageRegistry().put(null,umlRoot);			
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);
		
		umlreflection = umlenv.getUMLReflection();	
    }
	
	 /**
     * Constructor. 
     * It uses a OntoUML2UML transformation behind the scenes to orchestrate the constraints parsing.
     * 
     * @param rootPackage: OntoUML root Package
     * @param tempDirPath: Temporary Directory to store the background files needed fro parsing.
     * @param backgroundModelName: A name for the UML background model to be generated behind the scenes. If Null or empty the name will be "model" by default.
     */
	public OCLParser (RefOntoUML.Package rootPackage, String tempDirPath, String backgroundModelName)
    {	
    	if (rootPackage==null) return;
    	
    	this.refparser = new OntoUMLParser(rootPackage);
    	
    	if (tempDirPath==null) tempDirPath="";
    	
    	String umlPath = new String();
    	
    	if (backgroundModelName.isEmpty() || backgroundModelName ==null) backgroundModelName = "model";
    	
    	if (tempDirPath.endsWith(File.separator)) umlPath += tempDirPath + backgroundModelName + ".uml";
    	else umlPath += tempDirPath + File.separator + backgroundModelName + ".uml";
    	
    	umlResource = OntoUML2UML.convertToUML(refparser,umlPath,true,false);		
    	umlHashMap = OntoUML2UML.getMap();
    	logDetails = OntoUML2UML.getLog();
    	    	
    	umlFile = umlPath.substring(umlPath.lastIndexOf(File.separator)+1);
    	
    	// this line was added due to a bug of Eclipse :
    	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
    	Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
    			
		umlRoot = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		umlResource.getResourceSet().getPackageRegistry().put(null,umlRoot);			
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);
		
		umlreflection = umlenv.getUMLReflection();	
    }
	
    /**
     * Constructor.
     * It uses a OntoUML2UML transformation behind the scenes to orchestrate the constraints parsing.
     * 
      */
    public OCLParser (String refAbsolutePath) throws IOException,ParserException,Exception
	{ 			
    	this.refparser = new OntoUMLParser(refAbsolutePath);
    	
    	umlResource = OntoUML2UML.convertToUML(refparser,refAbsolutePath.replace(".refontouml" , ".uml"),true,false);		
    	umlHashMap = OntoUML2UML.getMap();
    	logDetails = OntoUML2UML.getLog();
		
    	String umlPath = refAbsolutePath.replace(".refontouml" , ".uml");    	
    	umlFile = umlPath.substring(umlPath.lastIndexOf(File.separator)+1);
    	
		// this line was added due to a bug of Eclipse :
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
		Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());
				
		umlRoot = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);		
		umlResource.getResourceSet().getPackageRegistry().put(null,umlRoot);		
		org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());
				
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);
			                
		umlreflection = umlenv.getUMLReflection();
	}
            
    /**
	 * Parse Constraints from a File.
     */
    public void parse(File oclFile) throws IOException, ParserException
    {
    	String oclContent = FileUtil.readFile(oclFile.getAbsolutePath());
    	parse(oclContent);
    }
    
	/**
	 * Parse constraints from text.
	 */
	public void parse(String oclConstraints) throws ParserException
	{
		analyzer = myOCL.createAnalyzer(oclConstraints);
        cstree = analyzer.parseConcreteSyntax();
        
        if (cstree!=null && cstree.getStartToken().toString().equals("context")) 
        {
            oclConstraints = "package "+"_'"+umlRoot.getName()+"'\n\n"+oclConstraints+"\n endpackage\n\n";
        }
                
        oclConstraints = processOCLContent(oclConstraints);
        
		OCLInput document = new OCLInput(oclConstraints);		
		umlconstraintsList = myOCL.parse(document);
		
		umlreflection = umlenv.getUMLReflection();
	}
	    
    /**
     * Get the OntoUML element related to the UML one. 
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
     * Process the OCL content. For instance, if there is an import declaration, it should not exist one since
     * the old UML binding do not allow the keyword import. Or if there is no package declaration, it should exist one.
     * And Any other processing before the parsing might be done here.
     */
    private String processOCLContent(String oclContent)
	{		
		if(oclContent.contains("import"))
		{
			oclContent = oclContent.replace("import '"+umlFile+"'","");		
		}
		if (!oclContent.contains("import") && !oclContent.contains("package "))
		{
			oclContent = "package _'"+umlRoot.getName()+"'"+"\n\n"+oclContent+"\n\nendpackage";
			return oclContent;
		}		
		
		return oclContent;
	}
    
    //Getters    
	public List<Constraint> getConstraints() { return umlconstraintsList; }
	public org.eclipse.ocl.uml.UMLEnvironment getUMLEnvironment() { return umlenv; }
	public String getDetails() { return logDetails; }	
    public Resource getUMLResource() { return umlResource; }    
    @SuppressWarnings("rawtypes") public  org.eclipse.ocl.utilities.UMLReflection getUMLReflection() { return umlreflection; }    
    public CSTNode getCSTree() { return cstree; }      
    public OntoUMLParser getOntoUMLParser() { return refparser; }
    /*
    @Deprecated
    public OCLParser (String oclConstraints, OntoUMLParser refparser, String umlPath) throws ParserException,Exception
    {   
        if (refparser==null) return;
        if (umlPath==null) return;        
        umlResource = OntoUML2UML.convertToUML(refparser,umlPath,true,false);           
        umlHashMap = OntoUML2UML.getMap();
        logDetails = OntoUML2UML.getLog();                
        umlFile = umlPath.substring(umlPath.lastIndexOf(File.separator)+1);        
        // this line was added due to a bug of Eclipse :
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=372258
        Environment.Registry.INSTANCE.registerEnvironment(new UMLEnvironmentFactory().createEnvironment());                        
        umlRoot = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
        umlResource.getResourceSet().getPackageRegistry().put(null,umlRoot);                    
        org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());        
        org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
        umlenv = envFactory.createEnvironment();                
        org.eclipse.ocl.uml.OCL myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
        myOCL.setParseTracingEnabled(true);        
        analyzer = myOCL.createAnalyzer(oclConstraints);
        cstree = analyzer.parseConcreteSyntax();        
        if (cstree!=null && cstree.getStartToken().toString().equals("context")) 
        {
            oclConstraints = "package "+"_'"+umlRoot.getName()+"'\n\n"+oclConstraints+"\n endpackage\n\n";
        }                
        oclConstraints = processOCLContent(oclConstraints);        
        OCLInput document = new OCLInput(oclConstraints);               
        umlconstraintsList = myOCL.parse(document);        
        umlreflection = umlenv.getUMLReflection();      
    }
    */
        
}
