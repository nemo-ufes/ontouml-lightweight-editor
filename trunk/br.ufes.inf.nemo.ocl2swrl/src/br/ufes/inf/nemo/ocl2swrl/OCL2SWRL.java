package br.ufes.inf.nemo.ocl2swrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.uml.ExpressionInOCL;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.javatuples.Pair;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;

public class OCL2SWRL {
	public static String log;		
	public static Boolean succeeds;
	
	public static void Transformation (OCLParser oclParser, OCL2SWRLOptions opt, OntoUMLParser refParser)	{
		//create a RefOntoUML associations hashMap where a tuple composed by the "class" and the "oposite end class" is the index and the association is the returned info
		//eg.: A is related to B. A tuple is composed by A and the member end connected to B. Analogously, the other tuple is composed by B and the member end connected to A
		HashMap<Pair<Class,Property>, Association> assocHashMap = createAssocHashMap(refParser);  
		
		String result = new String();			
		log = new String();		
		succeeds = false;
		
		//OCLVisitor myVisitor = new OCLVisitor(oclParser,refParser);
		
		for(Constraint ct: opt.getConstraintList())
		{
			ExpressionInOCL expr = (ExpressionInOCL) ct.getSpecification();
			
			if(expr.getBodyExpression().getClass() == OperationCallExpImpl.class){
				OperationCallExpImpl body = (OperationCallExpImpl) expr.getBodyExpression();
				String context = expr.getContextVariable().getType().getName();
				OCLExpression<Classifier> source = body.getSource();
				EList<OCLExpression<Classifier>> argument = body.getArgument();
				String referredOperationName = body.getReferredOperation().getName();
				
				String srcString = source.toString();
				String[] srcSplit = srcString.split("\\.");
				
				//Association assoc = associationsHashMap.get(context+srcSplit[1]);
				
				/*for (String string : srcSplit) {
					System.out.println(string);
				}*/
				//System.out.println(referredOperationName);
				/*
				for (OCLExpression<Classifier> oclExpression : argument) {
					System.out.println(oclExpression);
				}
				*/
				
				System.out.println();
			}
				
		}
	}
	
	public static HashMap<String, Class> createClassesHashMap(OntoUMLParser refParser){
		//get all classes from RefOntoUML
		Set<Class> classes = refParser.getAllInstances(RefOntoUML.Class.class);
		
		//create a RefOntoUML classes hashMap where the name of the class is the index and the class is the returned info 
		HashMap<String, Class> classesHashMap = new HashMap<String, Class>();
		//insert all RefOntoUML classes in the classes HashMap
		for (Class class1 : classes) {
			classesHashMap.put(class1.getName(), class1);
		}
		
		return classesHashMap;
	}
	
	public static HashMap<Pair<Class,Property>, Association> createAssocHashMap(OntoUMLParser refParser){
		//create a RefOntoUML classes hashMap where the name of the class is the index and the class is the returned info 
		HashMap<String, Class> classesHashMap = createClassesHashMap(refParser);
		
		//get all associations from RefOntoUML
		Set<Association> associations = refParser.getAllInstances(RefOntoUML.Association.class);
		
		//create a RefOntoUML associations hashMap where a tuple composed by the "class" and the "oposite end class" is the index and the association is the returned info
		//eg.: A is related to B. A tuple is composed by A and the member end connected to B. Analogously, the other tuple is composed by B and the member end connected to A
		HashMap<Pair<Class,Property>, Association> assocHashMap = new HashMap<Pair<Class,Property>, Association>();  
		
		//insert all RefOntoUML association in the associations HashMap
		for (Association assoc : associations) {
			//get the list of association member ends
			EList<Property> mEnds = assoc.getMemberEnd();
			/*
			String sourceRole = mEnds.get(0).getName();
			String sourceName = mEnds.get(0).getType().getName();
			String targetRole = mEnds.get(1).getName();
			String targetName = mEnds.get(1).getType().getName();
			*/
			//create the tuples composed by the Class and the Member End 
			Pair<Class,Property> tupleSrc = new Pair<Class, Property>(classesHashMap.get(mEnds.get(0).getType().getName()), mEnds.get(1));
			Pair<Class,Property> tupleTgt = new Pair<Class, Property>(classesHashMap.get(mEnds.get(1).getType().getName()), mEnds.get(0));
			
			//insert the tuples and the association into the association hash map 
			assocHashMap.put(tupleSrc, assoc);
			assocHashMap.put(tupleTgt, assoc);
		}
		
		return assocHashMap;
	}
	
	public static void main(String[] args) {
		//String refpath = "C:\\Users\\fredd_000\\Documents\\Projetos\\OLED-svn\\br.ufes.inf.nemo.ocl2swrl\\model\\john_examples\\project.refontouml";
    	//String oclPath = "C:\\Users\\fredd_000\\Documents\\Projetos\\OLED-svn\\br.ufes.inf.nemo.ocl2swrl\\model\\john_examples\\project.ocl";
    	
    	String refpath = "C:\\Users\\fredd_000\\Documents\\Projetos\\OLED-svn\\br.ufes.inf.nemo.ocl2swrl\\model\\example1\\example1.refontouml";
    	String oclPath = "C:\\Users\\fredd_000\\Documents\\Projetos\\OLED-svn\\br.ufes.inf.nemo.ocl2swrl\\model\\example1\\example1.ocl";
    	
    	OCLParser oclParser = null;
    	OntoUMLParser refParser = null;    	
    	OCL2SWRLOptions opt = null;
    	
    	try {
			oclParser = new OCLParser(oclPath, refpath);
			opt = new OCL2SWRLOptions(oclParser);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
			refParser = new OntoUMLParser(refpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Transformation(oclParser, opt, refParser);    	
    	
    	
    	//System.out.println(oclParser.toString());
    	
	}

}
