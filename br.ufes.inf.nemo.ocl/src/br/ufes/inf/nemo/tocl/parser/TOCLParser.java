package br.ufes.inf.nemo.tocl.parser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.ocl.parser.OCLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLUtil;

/**
 * This class is the main class for parsing TOCL constraints over OntoUML models.
 * It uses a UML model in background to orchestrate the parsing.
 *
 * @author John Guerson
 */

public class TOCLParser extends OCLParser{
	
    // Temporal UML
    public HashMap<RefOntoUML.Element, List<org.eclipse.uml2.uml.Element>> tmap = new HashMap<RefOntoUML.Element, List<org.eclipse.uml2.uml.Element> >();
	            
    //TOCL
    public ArrayList<String> oclIsKindOfList = new ArrayList<String>();
    public ArrayList<String> oclIsTypeOfList = new ArrayList<String>();    
    public ArrayList<String> oclAsTypeList = new ArrayList<String>();
    public ArrayList<String> oclCeasesToBeList = new ArrayList<String>();
    public ArrayList<String> oclBecomesList = new ArrayList<String>();
    public ArrayList<String> constraintStereotypeList = new ArrayList<String>();
    public ArrayList<org.eclipse.uml2.uml.Association> historicalRelationshipsList = new ArrayList<org.eclipse.uml2.uml.Association>();
    
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
    }
	
	public void autoConfigure()
	{
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
	}
      
    /**
     * Constructor.
     * It uses a OntoUML2UML transformation behind the scenes to orchestrate the constraints parsing.
     * 
      */
    public TOCLParser (String refAbsolutePath, String tempDirPath, String backgroundModelName) throws IOException,ParserException,Exception
	{ 	
    	super(refAbsolutePath,tempDirPath,backgroundModelName);
    	
    	umlResource = OntoUML2UML.includeTemporalStructure(umlRoot,umlPath,true);
   		tmap = OntoUML2UML.getTemporalMap();
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

    /** Return the world variable from the oclAsType operation at the "index" */
    public String getOclAsTypeWorldParam(int index)
    {
    	int i = 0;
    	for(String str: oclAsTypeList)
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
    public HashMap<String,Integer> processTypeConformanceOperations (String textDoc, Pattern p)
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
    	HashMap<String,Integer> map = new HashMap<String,Integer>();
    	map.put(textDoc,charRemoved);
    	return map;
    }
    
    /** Process oclAsType */
    public HashMap<String,Integer> processOclAsTypeOperation (String textDoc, Pattern p)
    {
		Matcher m = p.matcher(textDoc);		
		int qtdeLetters = (new String("oclAsType")).length();	
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
    			if(operation.contains("oclAsType")) oclAsTypeList.add(typeVar.trim()+","+worldVar.trim());        		        		
    		}else{    			    			
    			if(operation.contains("oclAsType")) oclAsTypeList.add(typeVar.trim());    			    			    			
    		}
    		    		 
    		String left = textDoc.substring(0,indexBegin+qtdeLetters+1+typeVar.length())+")";    		
    		String right = textDoc.substring(indexEnd, textDoc.length());
    		//System.out.println("Left :"+left);
    		//System.out.println("Right :"+right);
    		textDoc = left+right;    		
    		
    		charRemoved = charRemoved + 1 + worldVar.length();
    	}    	
    	HashMap<String,Integer> map = new HashMap<String,Integer>();
    	map.put(textDoc,charRemoved);
    	return map;
    }
    
    /** Process oclBecomes 
     * 
     * @throws ParserException */
    public HashMap<String,Integer> processOclBecomesOperation (String textDoc, Pattern p) throws ParserException
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
    	HashMap<String,Integer> map = new HashMap<String,Integer>();
    	map.put(textDoc,charRemoved);
    	return map;
    }
    
    /** Process oclCeasesToBe 
     * 
     * @throws ParserException */
    public HashMap<String,Integer> processOclCeasesToBeOperation (String textDoc, Pattern p) throws ParserException
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
    		checkInvalidType(typeVar);
    		
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
    	HashMap<String,Integer> map = new HashMap<String,Integer>();
    	map.put(textDoc,charRemoved);
    	return map;
    }
    
    private int countMatches(String regex, String text)
    {
    	Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) { count++; }
        return count;
    }
    
    public void checkInvalidType(String typeName) throws ParserException
    {    	
    	for(RefOntoUML.Element elem: umap.keySet()){
    		if(elem instanceof RefOntoUML.NamedElement){
    			RefOntoUML.NamedElement namedElem = (RefOntoUML.NamedElement)elem;
    			if(namedElem.getName().equals(typeName)) return;
    		}
    	}
    	throw new ParserException ("Unrecognized Variable: ("+typeName+")");    	
    }
    
    /** Check if a world parameter is missing */
    public void checkInvalidOperations(String oclExpression) throws ParserException
    {
    	int match = countMatches("oclIsKindOf\\((_')*\\s*\\w+\\s*'*\\)", oclExpression);
    	if(match>0) throw new ParserException("Cannot find operation (oclIsKindOf(T)). Missing world parameter. ");
    	
    	match = countMatches("oclIsTypeOf\\((_')*\\s*\\w+\\s*'*\\)", oclExpression);
    	if(match>0) throw new ParserException("Cannot find operation (oclIsTypeOf(T)). Missing world parameter. ");    	
    	
    	match = countMatches("oclAsType\\((_')*\\s*\\w+\\s*'*\\)", oclExpression);
    	if(match>0) throw new ParserException("Cannot find operation (oclAsType(T)). Missing world parameter. ");
    	
    	match = countMatches("oclBecomes\\((_')*\\s*\\w+\\s*'*\\)", oclExpression);
    	if(match>0) throw new ParserException("Cannot find operation (oclBecomes(T)). Missing world parameter. ");
    	
    	match = countMatches("oclCeasesToBe\\((_')*\\s*\\w+\\s*'*\\)", oclExpression);
    	if(match>0) throw new ParserException("Cannot find operation (oclCeasesToBe(T)). Missing world parameter. ");
    }
    
    /** Process Temporal Navigations with the syntax "[]" */
    public HashMap<String,Integer> processTemporalNavigations(String result)
    {
    	// navigations such as endName[w]/attrName[w] will become endName(w)/attrName(w)
	    result = result.replaceAll("\\[","(");
	    result = result.replaceAll("\\]",")");
	    
	    //navigations such as endName/attrName will become endName()/attrName()
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
    
    public void checkInvalidHistoricalContext(String context)
    {
    	Pattern p = Pattern.compile("(\\w*)(\\s*)::(\\s*)(\\w*)(\\s*):(\\s*)(\\w*)");
		Matcher m = p.matcher(context);		
		if(!m.matches()) {
			new ParserException("Wrong historical relationship context: \""+context+"\"\n"+
			"The declaration should be in the form: \"Src-Class-Name :: Relationship-Name :: Tgt-Class-Name\"");
		}    	
    }
    
    public void checkInvalidHistoricalDeclaration(String declaration)
    {
    	Pattern p = Pattern.compile("(\\s*\\w*\\s*):(\\s*\\w*\\s*)[(\\w*)](\\s*),(\\s*\\w*\\s*):(\\s*\\w*\\s*)[(\\w*)](\\s*)");
		Matcher m = p.matcher(declaration);		
		if(!m.matches()) {
			new ParserException("Wrong historical relationship declaration: \""+declaration+"\"\n"+
			"The declaration should be in the form: \"Src-End-Name: Src-Class-Name[Src-End-Mult], Tgt-End-Name: Tgt-Class-Name[Tgt-End-Mult]\"");
		}    	
    }
    
    public void processHistoricalRelationship(String context, String declaration) throws ParserException, ParseException
    {
    	context = context.replace("context", "");
    	declaration = declaration.replace("{", "");
    	declaration = declaration.replace("}", "");
    	
    	checkInvalidHistoricalContext(context);
    	checkInvalidHistoricalDeclaration(declaration);
    	
    	String[] array = context.trim().split("::");
    	String[] array2 = array[1].trim().split(":");
    	    	
    	String sorceName = array[0].trim();
    	checkInvalidType(sorceName);
    	String relName = array2[0].trim();
    	String targetName = array2[1].trim();
    	checkInvalidType(targetName);
    	
//		System.out.println("<"+sorceName+">");
//		System.out.println("<"+relName+">");
//		System.out.println("<"+targetName+">");
		
    	String[] declArray = declaration.trim().split(",");
    	String[] leftDecl = declArray[0].trim().split(":");
    	String[] rightDecl = declArray[1].trim().split(":");
    	
    	String srcEndName = leftDecl[0].trim();
    	String tgtEndName = rightDecl[0].trim();
    	
    	String[] array3 = leftDecl[1].trim().split("\\[");
    	String[] array4 = rightDecl[1].trim().split("\\[");
    	
    	String srcType = array3[0].trim();
    	String srcMult = array3[1].trim().split("]")[0].trim();
    	
    	String tgtType = array4[0].trim();
    	String tgtMult = array4[1].trim().split("]")[0].trim();
	
//    	System.out.println("<"+srcEndName+">");
//		System.out.println("<"+srcType+">");
//		System.out.println("<"+srcMult+">");
//		System.out.println("<"+tgtEndName+">");
//		System.out.println("<"+tgtType+">");
//		System.out.println("<"+tgtMult+">");
		
    	org.eclipse.uml2.uml.Association rel = OntoUML2UML.includeHistoricalRelationship(
    		umlRoot, srcType, relName, tgtType, srcEndName, srcMult, tgtEndName, tgtMult
    	);
    	
    	historicalRelationshipsList.add(rel);    	
	}    
    
    public boolean isHistoricalRelationship(org.eclipse.uml2.uml.Property p)
    {
    	if(p.getAssociation()!=null){
    		return historicalRelationshipsList.contains(p.getAssociation());
    	}
    	return false;
    }
    
    /** Process keyword "temp" */
    public String process(String result) throws ParserException, ParseException
    {
    	Pattern p = Pattern.compile("(temp|inv|derive)(\\s*\\w*\\s*):");
		Matcher m = p.matcher(result);
		int jump = 0;
    	while (m.find()) 
    	{ 
    		int indexBegin = m.start();
    		int indexEnd = m.end();    		
    		if(indexBegin+(jump) < 0) indexBegin = 0;
    		if(indexEnd+(jump) > result.length()) indexEnd = result.length();
    		
    		String left = result.substring(0,indexBegin+(jump));
			String keyword = result.substring(indexBegin+(jump),indexEnd+(jump));
    		String right = result.substring(indexEnd+(jump), result.length());
    		
    		if (keyword.contains("temp")){
    			
    			/** get the ocl expression as string */
    			String oclExpression = new String();
        		String remaining = new String();
        		if(right.contains("inv") || right.contains("derive") || right.contains("temp") || right.contains("def")) // has next constraint
    			{
    				if(right.contains("context")) {
    					oclExpression = right.substring(0,right.indexOf("context"));
    					remaining = right.substring(right.indexOf("context"),right.length());
    				}else{    					
    					oclExpression = right.substring(0,right.indexOf(":"));
    					remaining = right.substring(right.indexOf(":"),right.length());
    				}
    			}else { //this is the last constraint...        				
    				oclExpression = right.substring(0, right.indexOf("endpackage"));
    				remaining = right.substring(right.indexOf("endpackage"), right.length());
    			}            		

    			/** historical relationship */
        		if(oclExpression.contains("{") || oclExpression.contains("}")) 
        		{        			
        			String context = left.substring(left.lastIndexOf("context"), left.length());        			
        			processHistoricalRelationship(context, oclExpression);        			
        			result = left.replace(context,"")+remaining;        			
        			jump = jump - context.length() - keyword.length() - oclExpression.length();        			
        		}
        		
    			/** temporal OCL invariant */
        		else{
        			checkInvalidOperations(oclExpression);
        			
        			constraintStereotypeList.add("temp");
        			
        			/** process keyword declaration */
            		keyword = keyword.replaceFirst("temp","inv");        		
            		jump  = jump -1;

					/** process temporal navigations */
            		HashMap<String,Integer>map = processTemporalNavigations(oclExpression);
    	        	for(String key: map.keySet())
    	        	{	        		
    	        		oclExpression = key;
    		            jump = jump + map.get(key);		            	        
    	        	}
    	        	
    	        	// remove world parameter and record it
    	    		Pattern patern = Pattern.compile("oclIsKindOf\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
    	    		map = processTypeConformanceOperations(oclExpression, patern);
    	        	for(String key: map.keySet())
    	        	{	        		
    	        		oclExpression = key;
    		            jump = jump - map.get(key);		            	        
    	        	}
    	        	
    	    		// remove world parameter and record it
    	    		patern = Pattern.compile("oclIsTypeOf\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
    	    		map = processTypeConformanceOperations(oclExpression, patern);
    	        	for(String key: map.keySet())
    	        	{	        		
    	        		oclExpression = key;
    		            jump = jump - map.get(key);		            	        
    	        	}
    	        	
    	    		// remove world parameter and record it
    	    		patern = Pattern.compile("oclAsType\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
    	    		map = processOclAsTypeOperation(oclExpression, patern);
    	    		for(String key: map.keySet())
    	        	{	        		
    	        		oclExpression = key;
    		            jump = jump - map.get(key);		            	        
    	        	}
    	        		
    	    		// remove world parameter and record it
    	    		patern = Pattern.compile("oclBecomes\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
    	    		map = processOclBecomesOperation(oclExpression, patern);
    	    		for(String key: map.keySet())
    	        	{	        		
    	        		oclExpression = key;
    		            jump = jump - map.get(key);		            	        
    	        	}
    	        	    	    		
    	    		// remove world parameter and record it
    	    		patern = Pattern.compile("oclCeasesToBe\\((_')*\\s*\\w+\\s*'*(,\\s*\\w+\\s*)*\\)");		
    	    		map = processOclCeasesToBeOperation(oclExpression, patern);
    	    		for(String key: map.keySet())
    	        	{	        		
    	        		oclExpression = key;
    		            jump = jump - map.get(key);		            	        
    	        	}
    	        	    	    		
            		result = left+keyword+(oclExpression+remaining);
        		}        		
    		}else if (keyword.contains("inv") || keyword.contains("derive")){
    			
    			if(keyword.contains("inv")) constraintStereotypeList.add("inv");
    			if(keyword.contains("derive")) constraintStereotypeList.add("derive");
    			
    			if(right.indexOf(":")!=-1) // has next constraint
    			{    				
    				if(right.indexOf("context")!=-1) {
    					/** Check Invalid OclExpression */
    					checkInvalidOclExpression(right.substring(0,right.indexOf("context")));    						
    				}else{
    					/** Check Invalid OclExpression */
    					checkInvalidOclExpression(right.substring(0,right.indexOf(":")));
    				}    				
    			}else { //this is the last constraint...
    				
    				/** Check Invalid OclExpression */
    				checkInvalidOclExpression(right);
    			}    			
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
	private String processDocument(String oclTextualDocument) throws ParserException, ParseException
    {
		String result = new String();
		result = oclTextualDocument;
		
		// remove comments...
		result = processInLineComments(result);
		result = processMultiLineComments(result);
	    
		//process
	    result = process(result);
	    System.out.println(result);
	    
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlRoot);
		
		//System.out.println(result);
	    return result;
    }

	/** Parse temporal OCL constraints from text. */
	private OCLInput textualProcessing(String oclTextualDocument) throws ParserException, ParseException
	{		
		oclTextualDocument = preProcessOCL(oclTextualDocument);
		String processedOCLDoc = processDocument(oclTextualDocument);		
		OCLInput document = new OCLInput(processedOCLDoc);
		return document;
	}
	
	public void parseTemporalOCL(String oclTextualDocument) throws ParserException, ParseException
	{
		OCLInput doc = textualProcessing(oclTextualDocument);
		autoConfigure();
		parseOCL(doc);
	}
	
	private void parseOCL(OCLInput document) throws ParserException 
	{	
		try{
			umlconstraintsList = myOCL.parse(document);
		}catch(ParserException pe){				
			throw new ParserException(pe.getLocalizedMessage());
		}		
		umlreflection = umlenv.getUMLReflection();
	}

    /** Parse temporal OCL Constraints from a File. */
    public void parseTemporalOCL(File temporalOCLFile) throws IOException, ParserException, ParseException
    {    	
   		String oclContent = FileUtil.readFile(temporalOCLFile.getAbsolutePath());   		
   		parseTemporalOCL(oclContent);
    }
}