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
    public ArrayList<String> oclCeasesToBeList = new ArrayList<String>();
    public ArrayList<String> oclBecomesList = new ArrayList<String>();
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
    	
    	umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath,true);
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

		umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath,true);
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

    	umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath,true);
   		tmap = OntoUML2UML.getTemporalMap();
      	
        //re-configuration
      	org.eclipse.ocl.uml.OCL.initialize(umlResource.getResourceSet());		
		org.eclipse.ocl.uml.UMLEnvironmentFactory envFactory = new org.eclipse.ocl.uml.UMLEnvironmentFactory(umlResource.getResourceSet());
		umlenv = envFactory.createEnvironment();		
		myOCL = org.eclipse.ocl.uml.OCL.newInstance(umlenv);
		myOCL.setParseTracingEnabled(true);			                
		umlreflection = umlenv.getUMLReflection();
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
    
    /** Return the indexes of the constraints recorded as temporal */
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
    
    /** Return the world variable from the oclIsKindOf operation at the "index" */
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
 
    /** Return the world variable from the oclIsTyopeOf operation at the "index" */
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
    
    /** Return the world variable from the oclBecomes operation at the "index" */
    public String getOclBecomesWorldParam(int index)
    {
    	int i = 0;
    	for(String str: oclBecomesList)
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
    
    /** Return the type from the oclBecomes operation at the "index" */
    public String getOclBecomesTypeParam(int index)
    {
    	int i = 0;
    	for(String str: oclBecomesList)
    	{
    		if(i == index) {
    			if(str.contains(",")){
	    			String array[] = str.split(",");
	    			return array[0];	    				
    			}else{
    				return "<Unknown>";
    			}
    		}
    		i++;
    	}
    	return "<Unknown>";
    }
    
    /** Return the world variable from the oclCeasesToBe operation at the "index" */
    public String getCeasesToBeWorldParam(int index)
    {
    	int i = 0;
    	for(String str: oclCeasesToBeList)
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
    
    /** Return the type from the oclCeasesToBe operation at the "index" */
    public String getCeasesToBeTypeParam(int index)
    {
    	int i = 0;
    	for(String str: oclCeasesToBeList)
    	{
    		if(i == index) {
    			if(str.contains(",")){
	    			String array[] = str.split(",");
	    			return array[0];	    				
    			}else{
    				return "<Unknown>";
    			}
    		}
    		i++;
    	}
    	return "<Unknown>";
    }
    
    /** Process In-Line Comments */
    public String processInLineComments(String result)
    { 
    	return result.replaceAll("--.*\n","\n");    	    	    	    
    }
    
    /** Process Multi-line Comments */
    public String processMultiLineComments(String result)
    { 
    	return result.replaceAll("(?s)/\\*.*?\\*/","");    	
    }
    
    /** Process oclIsKindOf, oclIsTypeOf */
    public String processTypeConformanceOperations (String textDoc, Pattern p)
    {
		Matcher m = p.matcher(textDoc);		
		int qtdeLetters = (new String("oclIsKindOf")).length();	
		int charRemoved = 0;
    	while (m.find()) 
    	{    		
    		int indexBegin = m.start()-charRemoved;
    		int indexEnd = m.end()-charRemoved;   
    		
    		if(indexBegin < 0) indexBegin = 0;
    		if(indexEnd > textDoc.length()) indexEnd = textDoc.length();    		
    		
    		String operation = textDoc.substring(indexBegin, indexBegin+qtdeLetters);
    		//System.out.println("Operation: "+operation);
    		
    		String parameters = textDoc.substring(indexBegin+qtdeLetters+1, indexEnd-1);    		
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
    		//System.out.println("Parameters: "+parameters +", Type: "+typeVar+", World: "+worldVar);
    		
    		if(parameters.contains(",")) {    			    			
    			if(operation.contains("oclIsKindOf")) oclIsKindOfList.add(typeVar.trim()+","+worldVar.trim());    			
        		else if(operation.contains("oclIsTypeOf")) oclIsTypeOfList.add(typeVar.trim()+","+worldVar.trim());        		
    		}else{    			    			
    			if(operation.contains("oclIsKindOf")) oclIsKindOfList.add(typeVar.trim());
    			else if(operation.contains("oclIsTypeOf")) oclIsTypeOfList.add(typeVar.trim());    			    			
    		}
    		    		 
    		String left = textDoc.substring(0,indexBegin+qtdeLetters+1+typeVar.length())+")";    		
    		String right = textDoc.substring(indexEnd, textDoc.length());
    		//System.out.println("Left :"+left);
    		//System.out.println("Right :"+right);
    		textDoc = left+right;    		
    		
    		charRemoved = charRemoved + 1 + worldVar.length();
    	}    	
    	return textDoc;
    }
    
    /** Process oclBecomes 
     * 
     * @throws ParserException */
    public String processOclBecomesOperation (String textDoc, Pattern p) throws ParserException
    {
    	Matcher m = p.matcher(textDoc);		
		int qtdeLetters = (new String("oclBecomes")).length();	
		int charRemoved = 0;
    	while (m.find()) 
    	{    		
    		int indexBegin = m.start()-charRemoved;
    		int indexEnd = m.end()-charRemoved;   
    		
    		if(indexBegin < 0) indexBegin = 0;
    		if(indexEnd > textDoc.length()) indexEnd = textDoc.length();    		
    		
    		String operation = textDoc.substring(indexBegin, indexBegin+qtdeLetters);
    		//System.out.println("Operation: "+operation);
    		
    		String parameters = textDoc.substring(indexBegin+qtdeLetters+1, indexEnd-1);    		
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
    		if(isInvalidTypeVariable(typeVar)) throw new ParserException ("Unrecognized Variable: ("+typeVar+")");
    		
    		//System.out.println("Parameters: "+parameters +", Type: "+typeVar+", World: "+worldVar);
    		
    		if(parameters.contains(",")) {    			    			
    			if(operation.contains("oclBecomes")) oclBecomesList.add(typeVar.trim()+","+worldVar.trim());        		        		
    		}else{    			    			
    			if(operation.contains("oclBecomes")) oclBecomesList.add(typeVar.trim());    			    			    			
    		}
    		 
    		String left = textDoc.substring(0,indexBegin+qtdeLetters+1);    		
    		String right = textDoc.substring(indexEnd-worldVar.length()-1, textDoc.length());
    		//System.out.println("Left :"+left);
    		//System.out.println("Right :"+right);
    		textDoc = left+right;    		
    		
    		charRemoved = charRemoved + 1 + typeVar.length();
    	}    	
    	return textDoc;
    }
    
    /** Process oclCeasesToBe 
     * 
     * @throws ParserException */
    public String processOclCeasesToBeOperation (String textDoc, Pattern p) throws ParserException
    {
    	Matcher m = p.matcher(textDoc);		
		int qtdeLetters = (new String("oclCeasesToBe")).length();	
		int charRemoved = 0;
    	while (m.find()) 
    	{    		
    		int indexBegin = m.start()-charRemoved;
    		int indexEnd = m.end()-charRemoved;   
    		
    		if(indexBegin < 0) indexBegin = 0;
    		if(indexEnd > textDoc.length()) indexEnd = textDoc.length();    		
    		
    		String operation = textDoc.substring(indexBegin, indexBegin+qtdeLetters);
    		//System.out.println("Operation: "+operation);
    		
    		String parameters = textDoc.substring(indexBegin+qtdeLetters+1, indexEnd-1);    		
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
    		if(isInvalidTypeVariable(typeVar)) throw new ParserException ("Unrecognized Variable: ("+typeVar+")");
    		
    		//System.out.println("Parameters: "+parameters +", Type: "+typeVar+", World: "+worldVar);
    		
    		if(parameters.contains(",")) {    			    			
    			if(operation.contains("oclCeasesToBe")) oclCeasesToBeList.add(typeVar.trim()+","+worldVar.trim());        		        		
    		}else{    			    			
    			if(operation.contains("oclCeasesToBe")) oclCeasesToBeList.add(typeVar.trim());    			    			    			
    		}
    		    		 
    		String left = textDoc.substring(0,indexBegin+qtdeLetters+1);    		
    		String right = textDoc.substring(indexEnd-worldVar.length()-1, textDoc.length());
    		//System.out.println("Left :"+left);
    		//System.out.println("Right :"+right);
    		textDoc = left+right;    		
    		
    		charRemoved = charRemoved + 1 + typeVar.length();
    	}    	
    	return textDoc;
    }
    
    private int countMatches(String regex, String text)
    {
    	Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) { count++; }
        return count;
    }
    
    public Boolean isInvalidTypeVariable(String variable)
    {    	
    	for(RefOntoUML.Element elem: umap.keySet()){
    		if(elem instanceof RefOntoUML.NamedElement){
    			RefOntoUML.NamedElement namedElem = (RefOntoUML.NamedElement)elem;
    			if(namedElem.getName().equals(variable)) return false;
    		}
    	}
    	return true;
    }
       
    /** Check if a world parameter is missing */
    public void checkInvalidOperations(String result) throws ParserException
    {
    	int match = countMatches("oclIsKindOf\\((_')*\\s*\\w+\\s*'*\\)", result);
    	if(match>0) throw new ParserException("Cannot find operation (oclIsKindOf(T)). Missing world parameter. ");
    	    	
    	match = countMatches("oclIsTypeOf\\((_')*\\s*\\w+\\s*'*\\)", result);
    	if(match>0) throw new ParserException("Cannot find operation (oclIsTypeOf(T)). Missing world parameter. ");    	
    	
    	match = countMatches("oclBecomes\\((_')*\\s*\\w+\\s*'*\\)", result);
    	if(match>0) throw new ParserException("Cannot find operation (oclBecomes(T)). Missing world parameter. ");
    	
    	match = countMatches("oclCeasesToBe\\((_')*\\s*\\w+\\s*'*\\)", result);
    	if(match>0) throw new ParserException("Cannot find operation (oclCeasesToBe(T)). Missing world parameter. ");
    }
    
    /** Process Temporal Navigations with the syntax "[]" */
    public HashMap<String,Integer> processTemporalNavigations(String result)
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
    
    /** Process keyword "temp" */
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
        		String oclExpression = new String();
        		String therest = new String();
        		if(right.indexOf(":")!=-1){
        			oclExpression = right.substring(0,right.indexOf(":"));
        			therest = right.substring(right.indexOf(":"),right.length());
        		}else{
        			oclExpression = right.substring(0,right.length());
        		}
        		        		
        		checkInvalidOperations(oclExpression);        		
        		
        		HashMap<String,Integer>map = processTemporalNavigations(oclExpression);
	        	for(String key: map.keySet()){	        		
	        		oclExpression = key;
		            jump = jump + map.get(key);		            	        
	        	}
	        	
        		result = left+middle+(oclExpression+therest);        		        		
        		
    		}else if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("inv")){
    			
    			constraintStereotypeList.add("inv");
    			String rightExpression = result.substring(indexEnd+(jump), result.length());
    			String oclExpression = new String();
    			if(rightExpression.indexOf(":")!=-1) 
    			{
    				if(rightExpression.indexOf("context")!=-1) oclExpression = rightExpression.substring(0,rightExpression.indexOf("context"));	
    				else oclExpression = rightExpression.substring(0,rightExpression.indexOf(":"));    				
    			}else {
    				oclExpression = rightExpression.substring(0,rightExpression.length());
    			}    			
    			checkInvalidOclExpression(oclExpression);
    			
    		}else if (result.substring(indexBegin+(jump),indexEnd+(jump)).contains("derive")){
    			
    			constraintStereotypeList.add("derive");
    			String rightExpression = result.substring(indexEnd+(jump), result.length());
    			String oclExpression = new String();
    			if(rightExpression.indexOf(":")!=-1) 
    			{
    				if(rightExpression.indexOf("context")!=-1) oclExpression = rightExpression.substring(0,rightExpression.indexOf("context"));	
    				else oclExpression = rightExpression.substring(0,rightExpression.indexOf(":"));    				
    			}else{
    				oclExpression = rightExpression.substring(0,rightExpression.length());
    			}    			
    			checkInvalidOclExpression(oclExpression);
    		}    	
    	}
    	
    	return result;    	
    }
    
    /** Check if the static OCL expression should be declared as dynamic  */
    private void checkInvalidOclExpression(String oclExpression) throws ParserException
    {
    	if(oclExpression.contains("World") || oclExpression.contains("Path") || oclExpression.contains("next(") || oclExpression.contains("previous(") || oclExpression.contains("hasNext(")|| oclExpression.contains("hasPrevious(") || oclExpression.contains("allNext(") ||
		oclExpression.contains("allPrevious(") || oclExpression.contains("isOrigin(") || oclExpression.contains("isTerminal(") || oclExpression.contains("existsIn(") || oclExpression.contains("paths(") || oclExpression.contains("worlds(") || 
		oclExpression.contains("oclIsCreated(") || oclExpression.contains("oclCeasesToBe(") || oclExpression.contains("oclIsDeleted(") || oclExpression.contains("oclBecomes("))
		{
			throw new ParserException("A temporal constraint should be defined by the keyword \"temp\"");
		}
    }
    
    /** Process OCL Textual Document */
	private String processDocument(String oclTextualDocument) throws ParserException
    {
		String result = new String();
		result = oclTextualDocument;
		
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
		result = processTypeConformanceOperations(result, p);
		
		// remove world parameter and record it
		p = Pattern.compile("oclIsTypeOf\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
		result = processTypeConformanceOperations(result, p);
		
		// remove world parameter and record it
		p = Pattern.compile("oclBecomes\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
		result = processOclBecomesOperation(result, p);
		
		// remove world parameter and record it
		p = Pattern.compile("oclCeasesToBe\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
		result = processOclCeasesToBeOperation(result, p);
		
		//System.out.println(result);
	    return result;
    }

	/** Parse temporal OCL constraints from text. */
	public void parseTemporalOCL(String oclTextualDocument) throws ParserException
	{		
		oclTextualDocument = preProcessOCL(oclTextualDocument);
		String processedOCLDoc = processDocument(oclTextualDocument);		
		OCLInput document = new OCLInput(processedOCLDoc);
		try{
			umlconstraintsList = myOCL.parse(document);
		}catch(ParserException pe){				
			throw new ParserException(pe.getLocalizedMessage());
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