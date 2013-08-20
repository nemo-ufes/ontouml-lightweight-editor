package br.ufes.inf.nemo.ocl2swrl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.uml.ExpressionInOCL;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.ocl.uml.impl.VariableExpImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLBuiltInAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;

public class OCL2SWRL {
	public static String log = new String();;		
	public static Boolean succeeds = false;
	
	public void Transformation (OCLParser oclParser, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace)	{
		//for(Constraint ct: opt.getConstraintList())
		for(Constraint ct: oclParser.getConstraints())
		{
			ExpressionInOCL expr = (ExpressionInOCL) ct.getSpecification();
			
			if(expr.getBodyExpression().getClass() == OperationCallExpImpl.class){
				//get the body expression
				OperationCallExpImpl bodyExpression = (OperationCallExpImpl) expr.getBodyExpression();
				
				//create SWRL rule for the expression
				solveBodyExpression(manager, nameSpace, bodyExpression);
				/*
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
				*/
				
				
			}
				
		}
	}
	
	public static void solveBodyExpression(OWLOntologyManager manager, String nameSpace, OperationCallExpImpl bodyExpression){
		//create antecedent and consequent atoms for the rule
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		
		//get the expression source 
		PropertyCallExpImpl bodyExpressionSource = (PropertyCallExpImpl)bodyExpression.getSource();
		
		//create antecedents e consequents for the body expression source
		solveBodyExpressionSource(manager.getOWLDataFactory(), nameSpace, bodyExpressionSource, antecedent, consequent);
		
		//create antecedents e consequents for the body expression argument
		solveBodyExpressionArgument(manager.getOWLDataFactory(), nameSpace, bodyExpression, antecedent, consequent);
		
		//create rule with the incremented antecedents and consequents
		SWRLRule rule = manager.getOWLDataFactory().getSWRLRule(antecedent,consequent);
		
		//apply changes
		manager.applyChange(new AddAxiom(manager.getOntology(IRI.create(nameSpace.substring(0, nameSpace.length()-1))), rule));
		
		//System.out.println();
	}
	
	public static void solveBodyExpressionArgument(OWLDataFactory factory, String nameSpace, OperationCallExpImpl bodyExpression, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		PropertyCallExpImpl bodyExpressionSource = (PropertyCallExpImpl)bodyExpression.getSource();
		ClassImpl conteinerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
		String referredOperationName = bodyExpression.getReferredOperation().getName();
		
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+conteinerClass.getName()));
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		SWRLVariable varY = factory.getSWRLVariable(IRI.create("18"));
		args.add(varX);
		args.add(varY);
		SWRLBuiltInAtom builtIn = null;
		
		switch (referredOperationName) {
		case ">":
			builtIn = factory.getSWRLBuiltInAtom(IRI.create("lessThanOrEqual"), args);
			break;
		case ">=":
			builtIn = factory.getSWRLBuiltInAtom(IRI.create("lessThan"), args);
			break;
		case "<":
			builtIn = factory.getSWRLBuiltInAtom(IRI.create("greaterThanOrEqual"), args);
			break;
		case "<=":
			builtIn = factory.getSWRLBuiltInAtom(IRI.create("greaterThan"), args);
			break;
		}
		
		antecedent.add(builtIn);		
	}
	
	public static void solveBodyExpressionSource(OWLDataFactory factory, String nameSpace, PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		if(bodyExpressionSource.getSource().getClass().equals(PropertyCallExpImpl.class)){
			solveBodyExpressionSource(factory, nameSpace, (PropertyCallExpImpl) bodyExpressionSource.getSource(), antecedent, consequent);
		}else if(bodyExpressionSource.getSource().getName().equals("self")){
			VariableExpImpl selfVar = (VariableExpImpl)bodyExpressionSource.getSource();
			String selfName = selfVar.getReferredVariable().getType().getName();
			SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+selfName));
			OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(factory.getOWLClass(IRI.create(nameSpace+selfName)));
			SWRLClassAtom atom = factory.getSWRLClassAtom(complementOf, varX);
			consequent.add(atom);
		}

		if(bodyExpressionSource.getClass().equals(PropertyCallExpImpl.class)){
			//object property
			if(bodyExpressionSource.getReferredProperty().getAssociation() != null){
				solveObjectProperty(factory, nameSpace, bodyExpressionSource, antecedent, consequent);
			}
			//data property
			if(bodyExpressionSource.getReferredProperty().getType().getClass().equals(PrimitiveTypeImpl.class)){
				ClassImpl containerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
				
				String variableName = containerClass.getName() + "." + bodyExpressionSource.getReferredProperty().getName();
				OWLDataProperty variable = factory.getOWLDataProperty(IRI.create(nameSpace+variableName));
				
				String nameVarX = containerClass.getName();
				String nameVarY = bodyExpressionSource.getReferredProperty().getName();
				
				SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+nameVarX));
				SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+nameVarY));
				
				antecedent.add(factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
				antecedent.add(factory.getSWRLDataPropertyAtom(variable, varX, varY)); //prop(?x,?Y)
				System.out.println();
			}
		
		}
	}
	
	public static void solveObjectProperty(OWLDataFactory factory, String nameSpace, PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		OWLObjectProperty rel = factory.getOWLObjectProperty(IRI.create(nameSpace+bodyExpressionSource.getReferredProperty().getAssociation().getName()));
		
		String nameVarX = "";
		String nameVarY = "";
		
		if(bodyExpressionSource.getSource().getName().equals("self")){
			VariableExpImpl selfVar = (VariableExpImpl)bodyExpressionSource.getSource();
			ClassImpl classSelf = (ClassImpl)selfVar.getReferredVariable().getType();
			
			Boolean selfIsKindOfMember0 = false;
			Boolean selfIsKindOfMember1 = false;
			
			ClassImpl classMember0 = (ClassImpl)bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(0).getType();
			if(!classSelf.equals(classMember0)){
				EList<Classifier> selfParents = classSelf.allParents();
				for (Classifier classifier : selfParents) {
					ClassImpl parent = (ClassImpl)classifier;
					if(parent.equals(classMember0)){
						selfIsKindOfMember0 = true;
					}
				}
			}
			ClassImpl classMember1 = (ClassImpl)bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(1).getType();
			if(!classSelf.equals(classMember1)){
				EList<Classifier> selfParents = classSelf.allParents();
				for (Classifier classifier : selfParents) {
					ClassImpl parent = (ClassImpl)classifier;
					if(parent.equals(classMember1)){
						selfIsKindOfMember1 = true;
					}
				}
			}
			
			if(selfIsKindOfMember0){
				nameVarX = classSelf.getName();
			}else{
				nameVarX = bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(0).getType().getName();
			}
			
			if(selfIsKindOfMember1){
				nameVarY = classSelf.getName();
			}else{
				nameVarY = bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(1).getType().getName();
			}
		}
		
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+nameVarX));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+nameVarY));
		
		antecedent.add(factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
		antecedent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?Y)
		//System.out.println();
		//consequent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?z)
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
