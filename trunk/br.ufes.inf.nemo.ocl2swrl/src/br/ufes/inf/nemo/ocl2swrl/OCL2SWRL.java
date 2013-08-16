package br.ufes.inf.nemo.ocl2swrl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.ecore.impl.VariableExpImpl;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.uml.ExpressionInOCL;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.javatuples.Pair;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;

public class OCL2SWRL {
	public static String log = new String();;		
	public static Boolean succeeds = false;
	
	public void Transformation (OCLParser oclParser, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace)	{
		//create a RefOntoUML classes hashMap where the name of the class is the index and the class is the returned info 
		HashMap<String, Class> classesHashMap = createClassesHashMap(refParser);
		
		//create a RefOntoUML associations hashMap where a tuple composed by the "class" and the "oposite end class" is the index and the association is the returned info
		//eg.: A is related to B. A tuple is composed by A and the member end connected to B. Analogously, the other tuple is composed by B and the member end connected to A
		HashMap<Pair<Class,String>, Association> assocHashMap = createAssocHashMap(refParser, classesHashMap);
		
		OWLOntology ontology = manager.getOntology(IRI.create(nameSpace.substring(0, nameSpace.length()-1)));
		OWLDataFactory factory = manager.getOWLDataFactory();
		
		//for(Constraint ct: opt.getConstraintList())
		for(Constraint ct: oclParser.getConstraints())
		{
			ExpressionInOCL expr = (ExpressionInOCL) ct.getSpecification();
			
			if(expr.getBodyExpression().getClass() == OperationCallExpImpl.class){
				//pega o corpo da operacao
				OperationCallExpImpl bodyExpression = (OperationCallExpImpl) expr.getBodyExpression();
				
				PropertyCallExpImpl source = (PropertyCallExpImpl)bodyExpression.getSource();
				
				//pega o context
				String context = expr.getContextVariable().getType().getName();
				//pega a parte esquerda da operacao
				OCLExpression<Classifier> bodySource = bodyExpression.getSource();
				//pega a parte direita da operacao
				EList<OCLExpression<Classifier>> argument = bodyExpression.getArgument();
				//pega o operador da operacao
				String referredOperationName = bodyExpression.getReferredOperation().getName();
				
				String[] srcSplit = bodySource.toString().split("\\.");
				
				Class contextClass = classesHashMap.get(context);
				
				Association assoc = assocHashMap.get(new Pair<Class,String>(contextClass, srcSplit[1]));
				
				String relName = generatesRelationName(ontology, nameSpace, contextClass, assoc);
				String classAssocMemberEndName = getClassNameByAssocMemberEnd(assoc, srcSplit[1]);
				String attrName =  classAssocMemberEndName + "." + srcSplit[2];
				String resultOperation = "";
				switch (referredOperationName) {
				case ">":
					resultOperation = "lessThanOrEqual";
					break;
				case ">=":
					resultOperation = "lessThan";
					break;
				case "<":
					resultOperation = "greaterThanOrEqual";
					break;
				case "<=":
					resultOperation = "greaterThan";
					break;
				}
				
				String result = classAssocMemberEndName + "(?x)" + "," + relName + "(?x, ?y)" + "," + attrName + "(?x, ?z)" + "," + resultOperation + "(?z," + argument.get(0) + ")" + "->" + "(not " + contextClass.getName() + ")" + "(?y)";
				
				System.out.println(result);
				
				
				
			}
				
		}
	}
	
	public static void solveSource(OWLOntologyManager manager, String nameSpace, PropertyCallExpImpl source, SWRLRule rule){
		if(!source.getSource().getClass().equals(VariableExpImpl.class)){
			solveSource(manager, nameSpace, (PropertyCallExpImpl) source.getSource(), rule);
		}
		OWLOntology ontology = manager.getOntology(IRI.create(nameSpace.substring(0, nameSpace.length()-1)));
		OWLDataFactory factory = manager.getOWLDataFactory();
		if(source.getReferredProperty().getAssociation() != null){
			OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+source.getReferredProperty().getAssociation().getName()));
			
			int qtVars = rule.getVariables().size();
			
			SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+"_"+(qtVars+1)));
			SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+"_"+(qtVars+2)));
			
			SWRLAtom diffYX = factory.getSWRLDifferentIndividualsAtom(varY, varX);
			
			Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
			antecedent.add(diffYX); //DifferentFrom(?x,?z)
			
			antecedent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?Y)
			
			Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
			consequent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?z)

			rule = factory.getSWRLRule(antecedent,consequent);		
			manager.applyChange(new AddAxiom(ontology, rule));
		}
	}
	
	//this function gets the name of the class that is situated at the memberEndName side  
	public static String getClassNameByAssocMemberEnd(Association assoc, String memberEndName){
		//get all association memberEnds (two in this case) 
		EList<Property> allMemberEnds = assoc.getMemberEnd();
		
		//verify if the actual memberEnd name is the same passed by parameter
		for (Property memberEnd : allMemberEnds) {
			if(memberEnd.getName().equals(memberEndName)){
				//return the member end name recovered
				return memberEnd.getType().getName();
			}
		}
		return "";
	}
	
	//this function generates the relation name according to the ontology created in the OntoUML2OWL_SWRL transformation
	public static String generatesRelationName(OWLOntology ontology, String nameSpace, Class contextClass, Association assoc){
		//get the class range from the association
		Type classRangeAssoc = assoc.getMemberEnd().get(1).getType();
		
		//if the context class (or his parents) is the range of the association, create the prefix INV. (because this relation will be a inverse one)
		String relNamePrefix = "";
		if(classRangeAssoc.equals(contextClass)){
			relNamePrefix += "INV.";
		}else{
			EList<RefOntoUML.Classifier> allParents = contextClass.allParents();
			for (RefOntoUML.Classifier parent : allParents) {
				if(parent.equals(contextClass)){
					relNamePrefix += "INV.";
				}
			}
		}
		
		//create a sufix like .DomainClassName.RangeClassName (for the cases that exists relations with the same name)
		String relNameSufix = ".";
		relNameSufix += assoc.getMemberEnd().get(0).getType().getName();
		relNameSufix += ".";
		relNameSufix += assoc.getMemberEnd().get(1).getType().getName();
		
		//create the relation name composed by prefix and sufix
		String relName = relNamePrefix + assoc.getName() + relNameSufix;
		
		//if the relation name sufixed doesn't exist, create the name only with prefix
		if(!ontology.containsObjectPropertyInSignature(IRI.create(nameSpace+relName))){
			relName = relNamePrefix + assoc.getName();
		}		
		
		return relName;
	}
	
	//this function create a hash map where the class name is the index and the class is the recovered information 
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
	
	//this function create a hash map where the class and the opposite member end name is the index and the association is the recovered information 
	public static HashMap<Pair<Class,String>, Association> createAssocHashMap(OntoUMLParser refParser, HashMap<String, Class> classesHashMap){
		//get all associations from RefOntoUML
		Set<Association> associations = refParser.getAllInstances(RefOntoUML.Association.class);
		
		//create a RefOntoUML associations hashMap where a tuple composed by the "class" and the "oposite end class" is the index and the association is the returned info
		//eg.: A is related to B. A tuple is composed by A and the member end connected to B. Analogously, the other tuple is composed by B and the member end connected to A
		HashMap<Pair<Class,String>, Association> assocHashMap = new HashMap<Pair<Class,String>, Association>();  
		
		//insert all RefOntoUML association in the associations HashMap
		for (Association assoc : associations) {
			//think in a model like below
			//a Person specialized by Man and Woman
			//a Vehicle specialized by Car and Bike
			//a relation Ownership between Vehicle (source member end named Fleet) and Person (target member end named Owner)
			
			Pair<Class,String> tuple;
			Set<RefOntoUML.Classifier> childrensSrc;
			Set<RefOntoUML.Classifier> childrensTgt;
			
			//get the list of association member ends
			EList<Property> mEnds = assoc.getMemberEnd();
			
			//get all children of the source class (eg.: Man or Woman, from Person)
			childrensTgt = refParser.getChildren(classesHashMap.get(mEnds.get(1).getType().getName()));
			//get all children of the source class (eg.: Car or Bike, from Vehicle)
			childrensSrc = refParser.getChildren(classesHashMap.get(mEnds.get(0).getType().getName()));
			
			/*
			String sourceRole = mEnds.get(0).getName();
			String sourceName = mEnds.get(0).getType().getName();
			String targetRole = mEnds.get(1).getName();
			String targetName = mEnds.get(1).getType().getName();
			*/
			//create the tuple composed by the source Class and the Member End (eg.: between Vehicle and Owner)
			tuple = new Pair<Class, String>(classesHashMap.get(mEnds.get(0).getType().getName()), mEnds.get(1).getName());
			//insert the tuple and the association into the association hash map 
			assocHashMap.put(tuple, assoc);
			
			//for each children, insert a tuple composed by children and the same association (eg.: Car and Owner, or Bike and Owner)
			for (RefOntoUML.Classifier childSrc : childrensSrc) {
				tuple = new Pair<Class, String>(classesHashMap.get(childSrc.getName()), mEnds.get(1).getName());
				assocHashMap.put(tuple, assoc);
			}
			
			//create the tuples composed by the target Class and the other Member End (eg.: Person and Fleet) 
			tuple = new Pair<Class, String>(classesHashMap.get(mEnds.get(1).getType().getName()), mEnds.get(0).getName());
			//insert the tuple and the association into the association hash map
			assocHashMap.put(tuple, assoc);
			
			//for each children, insert a tuple composed by children and the same association (eg.: Man and Fleet, or Woman and Fleet)
			for (RefOntoUML.Classifier childTgt : childrensTgt) {
				tuple = new Pair<Class, String>(classesHashMap.get(childTgt.getName()), mEnds.get(0).getName());
				assocHashMap.put(tuple, assoc);
			}
		}
		
		return assocHashMap;
	}
/*	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//String refpath = "C:\\Users\\fredd_000\\Documents\\Projetos\\OLED-svn\\br.ufes.inf.nemo.ocl2swrl\\model\\john_examples\\project.refontouml";
    	//String oclPath = "C:\\Users\\fredd_000\\Documents\\Projetos\\OLED-svn\\br.ufes.inf.nemo.ocl2swrl\\model\\john_examples\\project.ocl";
    	
    	String refpath = "C:\\Users\\fredd_000\\Documents\\Projetos\\ontouml-lightweight-editor\\br.ufes.inf.nemo.ocl2swrl\\model\\example1\\example1.refontouml";
    	String oclPath = "C:\\Users\\fredd_000\\Documents\\Projetos\\ontouml-lightweight-editor\\br.ufes.inf.nemo.ocl2swrl\\model\\example1\\example1.ocl";
    	
    	OCLParser oclParser = null;
    	OntoUMLParser refParser = null;    	
    	
    	try {
			oclParser = new OCLParser(oclPath, refpath);
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
    	
    	//Transformation(oclParser, refParser);    	
    	
    	
    	//System.out.println(oclParser.toString());
    	
	}
*/
}
