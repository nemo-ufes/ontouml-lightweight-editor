package br.ufes.inf.nemo.tocl.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.file.FileUtil;
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
    public HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> tmap = new HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element> >();
	            
    //TOCL
    public ArrayList<String> oclIsKindOfList = new ArrayList<String>();
    public ArrayList<String> oclIsTypeOfList = new ArrayList<String>();
    public ArrayList<String> oclIsNewList = new ArrayList<String>();
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
    	tmap = OntoUML2UML.getTemporalMap();
        
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
   		tmap = OntoUML2UML.getTemporalMap();
      	
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
   		tmap = OntoUML2UML.getTemporalMap();
      	
        //re-configuration
      	org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);			                
		umlreflection = umlenv.getUMLReflection();
	}
    
    public String processOclIsNewOperation(String result, Pattern p)
    {
    	Matcher m = p.matcher(result);
		int jump = 0;
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end();
    		
    		if(indexBegin+(jump) < 0) indexBegin = 0;
    		if(indexEnd+(jump) > result.length()) indexEnd = result.length();
    		
    		String parameters = result.substring(indexBegin+9+(jump), indexEnd-1+(jump));
    		String typeVar = new String();
    		String worldVar = new String();
    		if(parameters.contains(",")){
    			String[] paramArray = parameters.split(",");
    			typeVar = paramArray[0];
    			worldVar = paramArray[1];
    		}else{
    			worldVar = parameters;
    			typeVar = "";
    		}
    		    		
    		String left = result.substring(0,indexBegin+9+typeVar.length()+(jump))+")";
    		String right = result.substring(indexEnd+(jump), result.length());
    		result = left+right;
    		
    		if(parameters.contains(",")) {
    			jump  = jump -1-worldVar.length();
    			if(result.substring(indexBegin+jump, indexEnd+jump).contains("oclIsNew")){
        			oclIsNewList.add(worldVar.trim());
    			}
    		} else {
    			if(result.substring(indexBegin+jump, indexEnd+jump).contains("oclIsNew")){
    				oclIsNewList.add(worldVar.trim());
    			}
    		}    		
    	}
    	return result;
    }
    
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
    		String typeVar = new String();
    		String worldVar = new String();
    		if(parameters.contains(",")){
    			String[] paramArray = parameters.split(",");
    			typeVar = paramArray[0];
    			worldVar = paramArray[1];
    		}else{
    			typeVar = parameters;
    			worldVar = "";
    		}
    		    		
    		String left = result.substring(0,indexBegin+12+typeVar.length()+(jump))+")";
    		String right = result.substring(indexEnd+(jump), result.length());
    		result = left+right;
    		
    		if(parameters.contains(",")) {
    			jump  = jump -1-worldVar.length();
    			if(result.substring(indexBegin+jump, indexEnd+jump).contains("oclIsKindOf")){
        			oclIsKindOfList.add(typeVar.trim()+","+worldVar.trim());    			
        		}else if(result.substring(indexBegin+jump, indexEnd+jump).contains("oclIsTypeOf")){
        			oclIsTypeOfList.add(typeVar.trim()+","+worldVar.trim());
        		}
    		} else {
    			if(result.substring(indexBegin+jump, indexEnd+jump).contains("oclIsKindOf")){
    				oclIsKindOfList.add(typeVar.trim());
    			}else if(result.substring(indexBegin+jump, indexEnd+jump).contains("oclIsTypeOf")){
    				oclIsTypeOfList.add(typeVar.trim());
    			}
    		}    		
    	}
    	return result;
    }
    
    public String getOclIsKindOfWorldParam(int index)
    {
    	int i = 0;
    	for(String str: oclIsKindOfList)
    	{
    		if(i == index) {
    			if(str.contains(",")){
	    			String array[] = str.split(",");
	    			return array[1];	    				
    			}else{
    				return "World";
    			}
    		}
    		i++;
    	}
    	return "<Unknown>";
    }
    
    public String getOclIsNewWorldParam(int index)
    {
    	int i = 0;
    	for(String str: oclIsNewList)
    	{
    		if(i == index) {
    			if(str.contains(",")){
	    			String array[] = str.split(",");
	    			return array[1];	    				
    			}else{
    				return str;
    			}
    		}
    		i++;
    	}
    	return "<Unknown>";
    }
    
    public String getOclIsTypeOfWorldParam(int index)
    {
    	int i = 0;
    	for(String str: oclIsTypeOfList)
    	{
    		if(i == index) {
    			if(str.contains(",")){
	    			String array[] = str.split(",");
	    			return array[1];	    				
    			}else{
    				return "World";
    			}
    		}
    		i++;
    	}
    	return "<Unknown>";
    }
    
    public String processInLineComments(String result)
    { 
    	return result.replaceAll("--.*\n","");    	    	    	    
    }
    
    public String processMultiLineComments(String result)
    { 
    	return result.replaceAll("(?s)/\\*.*?\\*/","");    	
    }
    
    public void checkInvalidOperationsAndNavigations(String result) throws ParserException
    {
    	if (result.contains("oclIsNew()")){
    		throw new ParserException("Cannot find operation (oclIsNew()). Missing world parameter. ");
    	}
    	
    	int match = countMatches("oclIsKindOf\\((_')*\\s*\\w+\\s*'*\\)", result);
    	if(match>0) {
    		throw new ParserException("Cannot find operation (oclIsKindOf(T)). Missing world parameter. ");
    	}    	match = countMatches("oclIsTypeOf\\((_')*\\s*\\w+\\s*'*\\)", result);
    	if(match>0) {
    		throw new ParserException("Cannot find operation (oclIsTypeOf(T)). Missing world parameter. ");    	
    	}
    }
    
    public HashMap<String,Integer> processTemporalProperty(String result)
    {
    	int char_added=0;
    	Pattern p = Pattern.compile("\\.\\w+");
    	Matcher m = p.matcher(result);
    	StringBuffer sb = new StringBuffer();
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end()+1;
    		
    		if(indexBegin < 0) indexBegin = 0;
    		if(indexEnd > result.length()) indexEnd = result.length();
    		
    		String regex = "\\.\\w+[^\\(]";
    		if(Pattern.matches(regex,result.subSequence(indexBegin, indexEnd)))  
    		{  
    		   m.appendReplacement(sb, result.subSequence(indexBegin, indexEnd-1)+"()"); 
    		   char_added+=2;
    		}
    	}    	
    	m.appendTail(sb);
    	String str = sb.toString();
    	m.reset();
    	HashMap<String,Integer> map = new HashMap<String,Integer>();
    	map.put(str,char_added);
    	return map;
    }
    
    public int countMatches(String regex, String text)
    {
    	Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) { count++; }
        return count;
    }
    
    public String processTempKeyword(String result) throws ParserException
    {
    	Pattern p = Pattern.compile("\\W(temp|inv|derive)\\W*(\\s*\\w*\\s*):");
		Matcher m = p.matcher(result);
		int jump = 0;
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end();
    		
    		if(indexBegin+(jump) < 0) indexBegin = 0;
    		if(indexEnd+(jump) > result.length()) indexEnd = result.length();
    		
    		if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("temp")){
    			
    			// "temp" takes place to "inv"...
    			// ==============================
    			String left = result.substring(0,indexBegin+(jump));
    			String middle = result.substring(indexBegin+(jump),indexEnd+(jump)).replaceFirst("temp","inv");
        		String right = result.substring(indexEnd+(jump), result.length());
        		jump  = jump -1;
        		constraintStereotypeList.add("temp");
        		
        		// endName/attrName takes place to endName()/attrName()...
    			// =======================================================
        		String expression = new String();
        		String therest = new String();
        		if(right.indexOf(":")!=-1){
        			expression = right.substring(0,right.indexOf(":"));
        			therest = right.substring(right.indexOf(":"),right.length());
        		}else{
        			expression = right.substring(0,right.length());
        		}
        		        		
        		checkInvalidOperationsAndNavigations(expression);        		
        		
        		HashMap<String,Integer>map = processTemporalProperty(expression);
	        	for(String key: map.keySet()){	        		
	        		expression = key;
		            jump = jump + map.get(key);		            	        
	        	}
	        	
        		result = left+middle+(expression+therest);        		        		
        		
    		}else if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("inv")){
    			
    			constraintStereotypeList.add("inv");
    			String rightExpression = result.substring(indexEnd+(jump), result.length());
    			String expression = new String();
    			if(rightExpression.indexOf(":")!=-1) {
    				if(rightExpression.indexOf("context")!=-1){    					
    					expression = rightExpression.substring(0,rightExpression.indexOf("context"));	
    				}else{
    					expression = rightExpression.substring(0,rightExpression.indexOf(":"));
    				}
    			} else expression = rightExpression.substring(0,rightExpression.length());
    			
    			if(expression.contains("World") || expression.contains("next()") || expression.contains("previous()") || expression.contains("hasNext()")|| expression.contains("allNext()") ||
				expression.contains("allPrevious()") || expression.contains("isOrigin()") || expression.contains("isTerminal()") || expression.contains("existsIn")){
    				throw new ParserException("Unrecognizable keyword \"inv\": A temporal constraint is defined by the keyword \"temp\"");
    			}
    			
    		}else if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("derive")){
    			
    			constraintStereotypeList.add("derive");
    			String rightExpression = result.substring(indexEnd+(jump), result.length());
    			String expression = new String();
    			if(rightExpression.indexOf(":")!=-1) {
    				if(rightExpression.indexOf("context")!=-1){    					
    					expression = rightExpression.substring(0,rightExpression.indexOf("context"));	
    				}else{
    					expression = rightExpression.substring(0,rightExpression.indexOf(":"));
    				}
    			} else expression = rightExpression.substring(0,rightExpression.length());
    			
    			if(expression.contains("World") || expression.contains("next()") || expression.contains("previous()") || expression.contains("hasNext()")|| expression.contains("allNext()") ||
				expression.contains("allPrevious()") || expression.contains("isOrigin()") || expression.contains("isTerminal()") || expression.contains("existsIn")){
    				throw new ParserException("Unrecognizable keyword \"derive\": A temporal constraint is defined by the keyword \"temp\"");
    			}
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
    
	private String processTemporalOCL(String oclTemporalContent) throws ParserException
    {
		String result = new String();
		result = oclTemporalContent;
		
		// remove comments...
		result = processInLineComments(result);
		result = processMultiLineComments(result);

    	// navigations such as endName[w]/attrName[w] will become endName(w)/attrName(w)
	    result = result.replaceAll("\\[","(");
	    result = result.replaceAll("\\]",")");
	    
	    // record which constraints are temporal
	    // process temporal properties
	    result = processTempKeyword(result);
	    
		// remove world parameter and record it
		Pattern p = Pattern.compile("oclIsKindOf\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
		result = processObjectOperation(result, p);
		
		// remove world parameter and record it
		p = Pattern.compile("oclIsTypeOf\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
		result = processObjectOperation(result, p);
		
		//remove world parameter and record it
		p = Pattern.compile("oclIsNew\\(\\s*\\w+\\s*\\)");		
		result = processOclIsNewOperation(result, p);
		
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
				if(!pe.getLocalizedMessage().contains("oclIsKindOf") && !pe.getLocalizedMessage().contains("oclIsTypeOf") &&				   
				   !pe.getLocalizedMessage().contains("allIntances") && !pe.getLocalizedMessage().contains("existsIn") &&
				   !pe.getLocalizedMessage().contains("next") && !pe.getLocalizedMessage().contains("previous") &&
				   !pe.getLocalizedMessage().contains("allNext") && !pe.getLocalizedMessage().contains("allPrevious") &&
				   !pe.getLocalizedMessage().contains("isTerminal") && !pe.getLocalizedMessage().contains("isOrigin") &&
				   !pe.getLocalizedMessage().contains("hasNext") && !pe.getLocalizedMessage().contains("hasPrevious") &&
				   !pe.getLocalizedMessage().contains("worlds") && !pe.getLocalizedMessage().contains("paths") &&
				   !pe.getLocalizedMessage().contains("oclIsNew") && !pe.getLocalizedMessage().contains("allIndividuals"))
				{
					String message = pe.getLocalizedMessage().replace("(World)","[World]");
					message = message.replace("operation", "association end-point ");
					throw new ParserException(message);
				}
			}
			if (pe.getLocalizedMessage().contains("operation") && pe.getLocalizedMessage().contains("oclIsNew")){
				//****** 
				//we do not want this parsing error to be displayed since we do support oclIsNew() in invariants
				//******
			}
			if (pe.getLocalizedMessage().contains("operation") && !pe.getLocalizedMessage().contains("World")){
				String message = pe.getLocalizedMessage().replace("()","");
				message = message.replace("operation", "association end-point ");
				message+=".\nMissing world argument using the syntax \"[ ]\"";
				throw new ParserException(message);				
			
			} else{				
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
    
    /** Get the OntoUML element related to the UML one. */
    @Override
    public RefOntoUML.Element getOntoUMLElement(org.eclipse.uml2.uml.Element value) 
    {   
        for (RefOntoUML.Element key : tmap.keySet()) 
        {
        	for(org.eclipse.uml2.uml.Element elem: tmap.get(key)){
        		if (elem.equals(value)) return key;
        	}
        }
        return super.getOntoUMLElement(value);
    }
}