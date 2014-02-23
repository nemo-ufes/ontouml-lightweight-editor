package br.ufes.inf.nemo.ocl.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLOption;

/**
 * This class is the main class for parsing OCL constraints over OntoUML models.
 * It uses a UML model in background to orchestrate the parsing.
 * 
 * @author John Guerson
 */

public class OCLParser {

	public boolean isTemporalOCL=false;
	
	// OntoUML
    public OntoUMLParser refparser;
    
	// UML
	public Resource umlResource;		
	public org.eclipse.ocl.uml.UMLEnvironment umlenv;                
    public String umlFile;
    public org.eclipse.uml2.uml.Package umlRoot;    
    
    // Pure UML
    public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> pureUmlMap;

    // Temporal UML
    public HashMap<RefOntoUML.Association, ArrayList<org.eclipse.uml2.uml.Classifier>> tempUmlAssocMap = new HashMap<RefOntoUML.Association, ArrayList<org.eclipse.uml2.uml.Classifier> >();
	public HashMap<RefOntoUML.Property, ArrayList<org.eclipse.uml2.uml.Element>> tempUmlAttrMap = new HashMap<RefOntoUML.Property, ArrayList<org.eclipse.uml2.uml.Element> >();
    
    // OCL
    public org.eclipse.ocl.uml.OCL myOCL;
    public List<Constraint> umlconstraintsList;
    public CSTNode cstree;
    @SuppressWarnings("rawtypes") public OCLAnalyzer analyzer;
    @SuppressWarnings("rawtypes") public org.eclipse.ocl.utilities.UMLReflection umlreflection;	
    public String logDetails = new String();
    
    /**
     * Constructor. 
     * It uses a OntoUML2UML transformation behind the scenes to orchestrate the constraints parsing.
     * 
     * @param refparser: OntoUML Parser
     * @param tempDirPath: Temporary Directory to store the background files needed fro parsing.
     * @param backgroundModelName: A name for the UML background model to be generated behind the scenes. If Null or empty the name will be "model" by default.
     */
	public OCLParser (OntoUMLParser refparser, String tempDirPath, String backgroundModelName, boolean isTemporalOCL)
    {	
    	if (refparser==null) return;
    	
    	this.isTemporalOCL=isTemporalOCL;
    	this.refparser = refparser;
    	
    	if (tempDirPath==null) tempDirPath="";
    	
    	String umlPath = new String();
    	
    	if (backgroundModelName == null || backgroundModelName.isEmpty()) backgroundModelName = "model";
    	
    	if (tempDirPath.endsWith(File.separator)) umlPath += tempDirPath + backgroundModelName + ".uml";
    	else umlPath += tempDirPath + File.separator + backgroundModelName + ".uml";
    	if (isTemporalOCL){
    		umlResource = OntoUML2UML.convertToTemporalUML(refparser,umlPath,new OntoUML2UMLOption(true,false));
    		tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
        	tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();   
    	}else{
    		umlResource = OntoUML2UML.convertToUML(refparser,umlPath,new OntoUML2UMLOption(true,false));
    	}
    	
    	pureUmlMap = OntoUML2UML.getMap();
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
	public OCLParser (RefOntoUML.Package rootPackage, String tempDirPath, String backgroundModelName, boolean isTemporalOCL)
    {	
    	if (rootPackage==null) return;

    	this.isTemporalOCL=isTemporalOCL;
    	this.refparser = new OntoUMLParser(rootPackage);
    	
    	if (tempDirPath==null) tempDirPath="";
    	
    	String umlPath = new String();
    	
    	if (backgroundModelName.isEmpty() || backgroundModelName ==null) backgroundModelName = "model";
    	
    	if (tempDirPath.endsWith(File.separator)) umlPath += tempDirPath + backgroundModelName + ".uml";
    	else umlPath += tempDirPath + File.separator + backgroundModelName + ".uml";
    	
    	if(isTemporalOCL){    		
    		umlResource = OntoUML2UML.convertToTemporalUML(refparser,umlPath,new OntoUML2UMLOption(true,false));
    		tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
        	tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();
    	}else{
    		umlResource = OntoUML2UML.convertToUML(refparser,umlPath,new OntoUML2UMLOption(true,false));
    	}
    	
    	pureUmlMap = OntoUML2UML.getMap();
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
    public OCLParser (String refAbsolutePath, boolean isTemporalOCL) throws IOException,ParserException,Exception
	{ 			
    	this.isTemporalOCL=isTemporalOCL;
    	this.refparser = new OntoUMLParser(refAbsolutePath);

    	if(isTemporalOCL){    		
    		umlResource = OntoUML2UML.convertToTemporalUML(refparser,refAbsolutePath.replace(".refontouml" , ".uml"),new OntoUML2UMLOption(true,false));
    		tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
        	tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();
    	}else{
    		umlResource = OntoUML2UML.convertToUML(refparser,refAbsolutePath.replace(".refontouml" , ".uml"),new OntoUML2UMLOption(true,false));
    	}
    	pureUmlMap = OntoUML2UML.getMap();
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
     * Pre-process the OCL content. For instance, if there is an import declaration, it should not exist one since
     * the old UML binding do not allow the keyword import. Or if there is no package declaration, it should exist one.
     * And Any other processing before the parsing might be done here.
     */
    private String preProcessOCL(String oclContent)
	{	
    	analyzer = myOCL.createAnalyzer(oclContent);
        cstree = analyzer.parseConcreteSyntax();
        
        if (cstree!=null && cstree.getStartToken().toString().equals("context")) 
        {
        	oclContent = "package "+"_'"+umlRoot.getName()+"'\n\n"+oclContent+"\n endpackage\n\n";
        }
        
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
    
	private String processTemporalOCL(String oclTemporalContent)
    {
		//search for [*] pattern
		Pattern p = Pattern.compile("\\[\\w+\\]");		
    	Matcher m = p.matcher(oclTemporalContent);    	
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end();
    		if(indexBegin < 0) indexBegin = 0;
    		if(indexEnd > oclTemporalContent.length()) indexEnd = oclTemporalContent.length();
    		String worldVar = oclTemporalContent.substring(indexBegin+1, indexEnd-1);   		
    		System.out.println(worldVar);    		
    	}		
    	return oclTemporalContent;
    }

	/** Parse temporal OCL constraints from text. */
	public void parseTemporalOCL(String oclTemporalConstraints) throws ParserException
	{		
		if(isTemporalOCL){ 
			oclTemporalConstraints = preProcessOCL(oclTemporalConstraints);

			processTemporalOCL(oclTemporalConstraints);
			
			OCLInput document = new OCLInput(oclTemporalConstraints);		
			umlconstraintsList = myOCL.parse(document);		
			umlreflection = umlenv.getUMLReflection();
		}else{
			new Exception("This parser was created to parse standard OCL and not temporal OCL as you specified.");
		}
	}

    /** Parse temporal OCL Constraints from a File. */
    public void parseTemporalOCL(File temporalOCLFile) throws IOException, ParserException
    {    	
    	if(isTemporalOCL){ 
    		String oclContent = FileUtil.readFile(temporalOCLFile.getAbsolutePath());
    		parseTemporalOCL(oclContent);
    	}else{
    		new Exception("This parser was created to parse standard OCL and not temporal OCL as you specified.");
    	}    	
    }
    
    /** Parse standard OCL Constraints from a File. */
    public void parseStandardOCL(File oclFile) throws IOException, ParserException
    {
    	if(!isTemporalOCL){ 
    		String oclContent = FileUtil.readFile(oclFile.getAbsolutePath());
    		parseStandardOCL(oclContent);
    	}else{
    		new Exception("This parser was created to parse temporal OCL  and not OCL standard as you specified.");
    	}
    }
    
	/** Parse standard OCL constraints from text. */
	public void parseStandardOCL(String oclStandardConstraints) throws ParserException
	{
		if(!isTemporalOCL){ 
			oclStandardConstraints = preProcessOCL(oclStandardConstraints);        
			OCLInput document = new OCLInput(oclStandardConstraints);		
			umlconstraintsList = myOCL.parse(document);		
			umlreflection = umlenv.getUMLReflection();
		}else{
			new Exception("This parser was created to parse temporal OCL  and not OCL standard as you specified.");
		}
	}

	//Getters    
	public List<Constraint> getConstraints() { return umlconstraintsList; }
	public org.eclipse.ocl.uml.UMLEnvironment getUMLEnvironment() { return umlenv; }
	public String getDetails() { return logDetails; }	
    public Resource getUMLResource() { return umlResource; }    
    @SuppressWarnings("rawtypes") public  org.eclipse.ocl.utilities.UMLReflection getUMLReflection() { return umlreflection; }    
    public CSTNode getCSTree() { return cstree; }      
    public OntoUMLParser getOntoUMLParser() { return refparser; }
    
    /** Get the OntoUML element related to the UML one. */
    public RefOntoUML.Element getOntoUMLElement(org.eclipse.uml2.uml.Element value) 
    {    	
        for (Entry<RefOntoUML.Element,org.eclipse.uml2.uml.Element> entry : pureUmlMap.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }
}
