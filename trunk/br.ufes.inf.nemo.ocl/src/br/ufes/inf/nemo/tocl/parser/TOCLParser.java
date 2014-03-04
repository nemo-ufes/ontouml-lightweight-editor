package br.ufes.inf.nemo.tocl.parser;

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

public class TOCLParser {
		
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
	        
    //TOCL
    public ArrayList<String> objectOperationParamList = new ArrayList<String>();
    
    /**
     * Constructor. 
     * It uses a OntoUML2UML transformation behind the scenes to orchestrate the constraints parsing.
     * 
     * @param refparser: OntoUML Parser
     * @param tempDirPath: Temporary Directory to store the background files needed fro parsing.
     * @param backgroundModelName: A name for the UML background model to be generated behind the scenes. If Null or empty the name will be "model" by default.
     */
	public TOCLParser (OntoUMLParser refparser, String tempDirPath, String backgroundModelName)
    {	
    	if (refparser==null) return;
    	
    	this.refparser = refparser;
    	
    	if (tempDirPath==null) tempDirPath="";
    	
    	String umlPath = new String();
    	
    	if (backgroundModelName == null || backgroundModelName.isEmpty()) backgroundModelName = "model";
    	
    	if (tempDirPath.endsWith(File.separator)) umlPath += tempDirPath + backgroundModelName + ".uml";
    	else umlPath += tempDirPath + File.separator + backgroundModelName + ".uml";
    	
    	// ======== TOCL ========
    	umlResource = OntoUML2UML.convertToTemporalUML(refparser,umlPath,new OntoUML2UMLOption(true,false));
    	tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
        tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();   
    	// ======================
        
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
	public TOCLParser (RefOntoUML.Package rootPackage, String tempDirPath, String backgroundModelName)
    {	
    	if (rootPackage==null) return;

    	this.refparser = new OntoUMLParser(rootPackage);
    	
    	if (tempDirPath==null) tempDirPath="";
    	
    	String umlPath = new String();
    	
    	if (backgroundModelName.isEmpty() || backgroundModelName ==null) backgroundModelName = "model";
    	
    	if (tempDirPath.endsWith(File.separator)) umlPath += tempDirPath + backgroundModelName + ".uml";
    	else umlPath += tempDirPath + File.separator + backgroundModelName + ".uml";

    	// ======== TOCL ========
   		umlResource = OntoUML2UML.convertToTemporalUML(refparser,umlPath,new OntoUML2UMLOption(true,false));
   		tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
       	tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();
    	// ======================
       	
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
    public TOCLParser (String refAbsolutePath) throws IOException,ParserException,Exception
	{ 			
    	this.refparser = new OntoUMLParser(refAbsolutePath);

    	// ======== TOCL ========   		
    	umlResource = OntoUML2UML.convertToTemporalUML(refparser,refAbsolutePath.replace(".refontouml" , ".uml"),new OntoUML2UMLOption(true,false));
    	tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
        tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();
    	// ======================
    	
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
    
    /**
     * Patterns: oclIsKindOf(*,*), oclIsKindOf(_'*',*), "oclIsTypeOf(*,*)", "oclIsTypeOf(_'*',*)"
     * 
     * @param result: String result
     * @param p: Pattern
     * @return
     */
    public String processObjectOperation (String result, Pattern p)
    {
		Matcher m = p.matcher(result);
		int jump = 0;
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end();
    		
    		if(indexBegin+(jump) < 0) indexBegin = 0;
    		if(indexEnd+(jump) > result.length()) indexEnd = result.length();
    		
    		String parameters = result.substring(indexBegin+12+(jump), indexEnd-1+(jump));    		
    		String[] paramArray = parameters.split(",");
    		String typeVar = paramArray[0];
    		String worldVar = paramArray[1];
    		    		
    		String left = result.substring(0,indexBegin+12+typeVar.length()+(jump))+")";
    		String right = result.substring(indexEnd+(jump), result.length());
    		result = left+right;    		
    		jump  = jump -1-worldVar.length();
    		
    		objectOperationParamList.add(typeVar.trim()+","+worldVar.trim());    		
    	}
    	return result;
    }
    
	private String processTemporalOCL(String oclTemporalContent)
    {
		String result = new String();
		result = oclTemporalContent;
		
		Pattern p = Pattern.compile("oclIsKindOf\\(\\s*\\w+\\s*,\\s*\\w+\\s*\\)");		
		result = processObjectOperation(result, p);
		
		p = Pattern.compile("oclIsTypeOf\\(\\s*\\w+\\s*,\\s*\\w+\\s*\\)");		
		result = processObjectOperation(result, p);
		
    	p = Pattern.compile("oclIsKindOf\\(_'[\\s|\\w]*',\\s*\\w+\\s*\\)");		
		result = processObjectOperation(result, p);
		
    	p = Pattern.compile("oclIsTypeOf\\(_'[\\s|\\w]*',\\s*\\w+\\s*\\)");		
		result = processObjectOperation(result, p);
		
    	// navigations such as roleName[w] will become roleName(w)
	    result = result.replaceAll("\\[","(");
	    result = result.replaceAll("\\]",")");
	    
    	return result;
    }

	/** Parse temporal OCL constraints from text. */
	public void parseTemporalOCL(String oclTemporalConstraints) throws ParserException
	{		
		oclTemporalConstraints = preProcessOCL(oclTemporalConstraints);

		String processedTempOCL = processTemporalOCL(oclTemporalConstraints);
		
		OCLInput document = new OCLInput(processedTempOCL);
		try{
			umlconstraintsList = myOCL.parse(document);
		}catch(ParserException pe){
			if (pe.getLocalizedMessage().contains("World")){
				String message = pe.getLocalizedMessage().replace("(World)","[World]");
				message = message.replace("operation", "association end-point ");
				throw new ParserException(message);
			}
		}	
		
		umlreflection = umlenv.getUMLReflection();
	}

    /** Parse temporal OCL Constraints from a File. */
    public void parseTemporalOCL(File temporalOCLFile) throws IOException, ParserException
    {    	
   		String oclContent = FileUtil.readFile(temporalOCLFile.getAbsolutePath());
   		parseTemporalOCL(oclContent);
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