package br.ufes.inf.nemo.ocl2swrl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.uml.ExpressionInOCL;
import org.eclipse.ocl.uml.impl.BooleanLiteralExpImpl;
import org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.ocl.uml.impl.StringLiteralExpImpl;
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
import org.semanticweb.owlapi.model.SWRLLiteralArgument;
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
	private String nameSpace;
	OCLParser oclParser;
	OntoUMLParser refParser;
	OWLOntologyManager manager;
	OWLDataFactory factory;
	OWLOntology ontology;
	
	public OCL2SWRL(OCLParser oclParser, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace) {
		this.nameSpace = nameSpace;
		this.oclParser = oclParser;
		this.refParser = refParser;
		this.manager = manager;
		this.factory = manager.getOWLDataFactory();
		this.ontology = manager.getOntology(IRI.create(nameSpace.substring(0, nameSpace.length()-1)));
	}
	
	public void Transformation ()	{
		//for(Constraint ct: opt.getConstraintList())
		for(Constraint ct: this.oclParser.getConstraints())
		{
			ExpressionInOCL expr = (ExpressionInOCL) ct.getSpecification();
			
			if(expr.getBodyExpression().getClass() == OperationCallExpImpl.class){
				//get the body expression
				OperationCallExpImpl bodyExpression = (OperationCallExpImpl) expr.getBodyExpression();
				
				//create SWRL rule for the expression
				solveBodyExpression(bodyExpression);
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
	
	public void solveBodyExpression(OperationCallExpImpl bodyExpression){
		//create antecedent and consequent atoms for the rule
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		
		//get the expression source 
		PropertyCallExpImpl bodyExpressionSource = (PropertyCallExpImpl)bodyExpression.getSource();
		
		//create antecedents e consequents for the body expression source
		solveBodyExpressionSource(bodyExpressionSource, antecedent, consequent);
		
		//create antecedents e consequents for the body expression argument
		solveBodyExpressionArgument(bodyExpression, antecedent, consequent);
		
		//create rule with the incremented antecedents and consequents
		SWRLRule rule = this.manager.getOWLDataFactory().getSWRLRule(antecedent,consequent);
		
		//apply changes
		this.manager.applyChange(new AddAxiom(this.manager.getOntology(IRI.create(this.nameSpace.substring(0, this.nameSpace.length()-1))), rule));
		
		//System.out.println();
		
	}
	
	public void solveBodyExpressionArgument(OperationCallExpImpl bodyExpression, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		PropertyCallExpImpl bodyExpressionSource = (PropertyCallExpImpl)bodyExpression.getSource();
		ClassImpl conteinerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
		String referredOperationName = bodyExpression.getReferredOperation().getName();
		EList<OCLExpression<Classifier>> argument = bodyExpression.getArgument();
		argument.get(0);
		SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+conteinerClass.getName()));
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		//SWRLVariable varY = this.factory.getSWRLVariable(IRI.create("18"));
		
		SWRLLiteralArgument varY = null;
		if(argument.get(0).getClass().equals(IntegerLiteralExpImpl.class)){
			varY = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((IntegerLiteralExpImpl)argument.get(0)).getIntegerSymbol()));
		}else if(argument.get(0).getClass().equals(BooleanLiteralExpImpl.class)){
			varY = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((BooleanLiteralExpImpl)argument.get(0)).getBooleanSymbol()));
		}else if(argument.get(0).getClass().equals(StringLiteralExpImpl.class)){
			varY = factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)argument.get(0)).getStringSymbol()));
		}/*else if(argument.get(0).getClass().equals(StringLiteralExpImpl.class)){
			varY = factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)argument.get(0)).getStringSymbol()));
		}*///verificar para double e float
		
		args.add(varX);
		args.add(varY);
		SWRLBuiltInAtom builtIn = null;
		
		switch (referredOperationName) {
		case ">":
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create("lessThanOrEqual"), args);
			break;
		case ">=":
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create("lessThan"), args);
			break;
		case "<":
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create("greaterThanOrEqual"), args);
			break;
		case "<=":
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create("greaterThan"), args);
			break;
		}
		
		antecedent.add(builtIn);		
	}
	
	public void solveBodyExpressionSource(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		if(bodyExpressionSource.getSource().getClass().equals(PropertyCallExpImpl.class)){
			solveBodyExpressionSource((PropertyCallExpImpl) bodyExpressionSource.getSource(), antecedent, consequent);
		}else if(bodyExpressionSource.getSource().getName().equals("self")){
			//get the self variable
			VariableExpImpl selfVar = (VariableExpImpl)bodyExpressionSource.getSource();
			String selfName = selfVar.getReferredVariable().getType().getName();
			
			//create a swrl variable with the self name
			SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+selfName));
			
			//get the complement of the self
			OWLObjectComplementOf complementOf = this.factory.getOWLObjectComplementOf(this.factory.getOWLClass(IRI.create(this.nameSpace+selfName)));
			
			//create a swrl atom that means swrlVariable isn't self
			SWRLClassAtom atom = this.factory.getSWRLClassAtom(complementOf, varX);
			consequent.add(atom);
		}

		if(bodyExpressionSource.getClass().equals(PropertyCallExpImpl.class)){
			//object property
			if(bodyExpressionSource.getReferredProperty().getAssociation() != null){
				solveObjectProperty(bodyExpressionSource, antecedent, consequent);
			}
			//data property
			if(bodyExpressionSource.getReferredProperty().getType().getClass().equals(PrimitiveTypeImpl.class)){
				ClassImpl containerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
				
				String variableName = containerClass.getName() + "." + bodyExpressionSource.getReferredProperty().getName();
				OWLDataProperty variable = this.factory.getOWLDataProperty(IRI.create(this.nameSpace+variableName));
				
				String nameVarX = containerClass.getName();
				String nameVarY = bodyExpressionSource.getReferredProperty().getName();
				
				SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarX));
				SWRLVariable varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarY));
				
				antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
				antecedent.add(this.factory.getSWRLDataPropertyAtom(variable, varX, varY)); //prop(?x,?Y)
				System.out.println();
			}
		
		}
	}
	
	public void solveSelfVariable(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> atoms){
		//get the self variable
		VariableExpImpl selfVar = (VariableExpImpl)bodyExpressionSource.getSource();
		String selfName = selfVar.getReferredVariable().getType().getName();
		
		//create a swrl variable with the self name
		SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(getNameSpace()+selfName));
		
		//get the complement of the self
		OWLObjectComplementOf complementOf = this.factory.getOWLObjectComplementOf(this.factory.getOWLClass(IRI.create(getNameSpace()+selfName)));
		
		//create a swrl atom that means swrlVariable isn't self
		SWRLClassAtom atom = this.factory.getSWRLClassAtom(complementOf, varX);
		atoms.add(atom);
	}
	
	public void solveObjectProperty(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		OWLObjectProperty rel = this.factory.getOWLObjectProperty(IRI.create(this.nameSpace+bodyExpressionSource.getReferredProperty().getAssociation().getName()));
		
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
		
		SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarX));
		SWRLVariable varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarY));
		
		antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
		antecedent.add(this.factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?Y)
		//System.out.println();
		//consequent.add(factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?z)
	}
		
	//this function generates the relation name according to the ontology created in the OntoUML2OWL_SWRL transformation
	public String generatesRelationName(Class contextClass, Association assoc){
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
		if(!this.ontology.containsObjectPropertyInSignature(IRI.create(this.nameSpace+relName))){
			relName = relNamePrefix + assoc.getName();
		}		
		
		return relName;
	}

	public String getNameSpace() {
		return this.nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
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
