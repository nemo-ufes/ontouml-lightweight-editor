package br.ufes.inf.nemo.tocl.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.parser.OCLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;

/**
 * This class is the main class for parsing TOCL constraints over OntoUML models.
 * It uses a UML model in background to orchestrate the parsing.
 *
 * @author John Guerson
 */

public class TOCLParser extends OCLParser{
	
    // Temporal UML
    public HashMap<RefOntoUML.Association, ArrayList<org.eclipse.uml2.uml.Classifier>> tempUmlAssocMap = new HashMap<RefOntoUML.Association, ArrayList<org.eclipse.uml2.uml.Classifier> >();
	public HashMap<RefOntoUML.Property, ArrayList<org.eclipse.uml2.uml.Element>> tempUmlAttrMap = new HashMap<RefOntoUML.Property, ArrayList<org.eclipse.uml2.uml.Element> >();
            
    //TOCL
    public ArrayList<String> objectOperationParamList = new ArrayList<String>();
    public ArrayList<String> constraintStereotypeList = new ArrayList<String>();
    
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
    	super(refparser,tempDirPath,backgroundModelName);
    	
    	umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath);
    	tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
        tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();    
        
        //re-configuration
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
		super(rootPackage,tempDirPath,backgroundModelName);

		umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath);
   		tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
      	tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();	
      	
        //re-configuration
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
    	super(refAbsolutePath);
    	
    	this.refparser = new OntoUMLParser(refAbsolutePath);

    	umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath);
   		tempUmlAssocMap = OntoUML2UML.getTempAssociationsMap();
      	tempUmlAttrMap = OntoUML2UML.getTempAttributesMap();
      	
        //re-configuration
      	org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);			                
		umlreflection = umlenv.getUMLReflection();
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
    
    public String processTempKeyword(String result)
    {
    	Pattern p = Pattern.compile("([^\\w])(temp|inv|derive)([^\\w])");
		Matcher m = p.matcher(result);
		int jump = 0;
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end();
    		
    		if(indexBegin+(jump) < 0) indexBegin = 0;
    		if(indexEnd+(jump) > result.length()) indexEnd = result.length();
    		    		
    		if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("temp")){
    			String left = result.substring(0,indexBegin+(jump));
    			String middle = result.substring(indexBegin+(jump),indexEnd+(jump)).replace("temp","inv");
        		String right = result.substring(indexEnd+(jump), result.length());
        		result = left+middle+right;    			
        		jump  = jump -1;
        		constraintStereotypeList.add("temp");        		
    		}else if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("inv")){    			
    			constraintStereotypeList.add("inv");
    		}else if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("derive")){
    			constraintStereotypeList.add("derive");
    		}    		
    	
    	}
    	return result;    	
    }

    public ArrayList<Integer> getTemporalConstraintsIndexes()
    {
    	ArrayList<Integer> indexes = new ArrayList<Integer>();
    	int i=0;
    	for(String stereo: constraintStereotypeList)
    	{
    		if(stereo=="temp") indexes.add(i);
    		i++;
    	}
    	return indexes;
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
	    
	    result = processTempKeyword(result);
	    
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
			}else{
				throw new ParserException(pe.getLocalizedMessage());
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
}