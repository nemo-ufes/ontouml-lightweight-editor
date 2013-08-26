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
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;

import uk.ac.manchester.cs.owl.owlapi.SWRLLiteralArgumentImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;
import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonInitialized;

public class OCL2SWRL {
	//public static String log = new String();;		
	//public static Boolean succeeds = false;
	private String nameSpace = null;
	private OCLParser oclParser = null;
	private OntoUMLParser refParser = null;
	private OWLOntologyManager manager = null;
	private OWLDataFactory factory = null;
	private OWLOntology ontology = null;
	
	public OCL2SWRL(OCLParser oclParser, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace) {
		this.nameSpace = nameSpace;
		this.oclParser = oclParser;
		this.refParser = refParser;
		this.manager = manager;
		this.factory = manager.getOWLDataFactory();
		this.ontology = manager.getOntology(IRI.create(nameSpace.substring(0, nameSpace.length()-1)));
	}
	
	@SuppressWarnings("unused")
	private OCL2SWRL() {
		//this constructor is private to force the use of the specific constructor
	}
	
	//this function verifies if all necessary attributes were initialized
	private void verifyVariablesInitialization(){
		if(this.nameSpace == null){
			throw new NonInitialized("nameSpace");
		}else if(this.nameSpace.equals("")){
			throw new NonInitialized("nameSpace");
		}
		
		if(this.oclParser == null){
			throw new NonInitialized("oclParser");
		}
		
		if(this.refParser == null){
			throw new NonInitialized("refParser");
		}
		
		if(this.manager == null){
			throw new NonInitialized("manager");
		}
		
		if(this.factory == null){
			throw new NonInitialized("factory");
		}
		
		if(this.ontology == null){
			throw new NonInitialized("ontology");
		}
	}
	
	//this main function transform OCL constraints in SWRL rules
	public void Transformation ()	{
		this.verifyVariablesInitialization();

		for(Constraint ct: this.oclParser.getConstraints())
		{
			ExpressionInOCL expr = (ExpressionInOCL) ct.getSpecification();
			
			if(expr.getBodyExpression().getClass() == OperationCallExpImpl.class){
				//get the body expression
				OperationCallExpImpl bodyExpression = (OperationCallExpImpl) expr.getBodyExpression();
				
				//create SWRL rule for the expression
				solveBodyOperation(bodyExpression);				
			}
				
		}
	}
	
	//this function solves a operation like (a < b)
	private void solveBodyOperation(OperationCallExpImpl bodyExpression){
		//create antecedent and consequent atoms for the rule
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		
		//create antecedents for the body expression source and argument
		solveOperation(bodyExpression, antecedent, consequent);
		
		//create a rule with the incremented antecedents and consequents
		SWRLRule rule = this.manager.getOWLDataFactory().getSWRLRule(antecedent,consequent);
		
		//make different all created variables  
		rule = makeAllVariablesDiff(rule);
		
		//apply changes in the owl manager
		this.manager.applyChange(new AddAxiom(ontology, rule));
	}
	
	//this function solves expressions (arithmetic) like (a*b+c*d) and returns an variable equivalent to the expression
	private SWRLDArgument solveExpression(OCLExpression<Classifier> expression, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		//VERIFICAR DOUBLE E FLOAT
		
		//a variable is created according to the type (other expression [recursively], literal values, or a property from a class)
		SWRLDArgument varZ = null;
		if(expression.getClass().equals(IntegerLiteralExpImpl.class)){//literal integer
			varZ = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((IntegerLiteralExpImpl)expression).getIntegerSymbol()));
			
		}else if(expression.getClass().equals(BooleanLiteralExpImpl.class)){//literal boolean
			varZ = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((BooleanLiteralExpImpl)expression).getBooleanSymbol()));
			
		}else if(expression.getClass().equals(StringLiteralExpImpl.class)){//literal string
			varZ = this.factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)expression).getStringSymbol()));
			
		}else if(expression.getClass().equals(PropertyCallExpImpl.class)){//class property
			//this function is called to solve associations, class attributes and self variables
			solveProperties((PropertyCallExpImpl) expression, antecedent, consequent);
			
			String varXName = ((PropertyCallExpImpl) expression).getReferredProperty().getName();
			varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName));
			
			
		}else if(expression.getClass().equals(VariableExpImpl.class)){//class property
			//get the self variable
			VariableExpImpl selfVar = (VariableExpImpl)expression;
			String selfName = selfVar.getReferredVariable().getType().getName();
			
			//create a swrl variable with the self name
			varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+selfName));
			
			
		}else if(expression.getClass().equals(OperationCallExpImpl.class)){//expression
			//from a expression (eg.: a*b+c*d), get the source (a*b) and the argument (c*d)
			OCLExpression<Classifier> srcExpression = ((OperationCallExpImpl) expression).getSource();
			OCLExpression<Classifier> argExpression = ((OperationCallExpImpl) expression).getArgument().get(0);
			
			//call recursively the solveExpression to solve the source and the argument
			SWRLDArgument varX = solveExpression(srcExpression, antecedent, consequent);
			SWRLDArgument varY = solveExpression(argExpression, antecedent, consequent);
			
			//an argument list for the expression is created
			List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
			
			String varXName = "";
			String varYName = "";
			
			//generates a name to the varX (source) name
			if(varX.getClass().equals(SWRLLiteralArgumentImpl.class)){
				varXName = "_" + ((SWRLLiteralArgumentImpl)varX).getLiteral().getLiteral();
			}else if(varX.getClass().equals(SWRLVariableImpl.class)){
				IRI iri = (IRI)((SWRLVariableImpl)varX).getIRI();
				varXName = iri.getFragment();
			}
			
			//generates a name to the varY (argument) name
			if(varY.getClass().equals(SWRLLiteralArgumentImpl.class)){
				varYName = "_" + ((SWRLLiteralArgumentImpl)varY).getLiteral().getLiteral();
			}else if(varY.getClass().equals(SWRLVariableImpl.class)){
				IRI iri = (IRI)((SWRLVariableImpl)varY).getIRI();
				varYName = iri.getFragment();
			}
			
			//a built-in name and a name for the output variable is chosen according to the expression operation
			String builtInName = "";
			SWRLBuiltInAtom builtIn = null;
			String refOperationName = ((OperationCallExpImpl) expression).getReferredOperation().getName();
			switch (refOperationName) {
			case "+":
				varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_add_"+varYName));
				builtInName = "add";
				break;
			case "-":
				varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_sub_"+varYName));
				builtInName = "subtract";
				break;
			case "*":
				varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_mul_"+varYName));
				builtInName = "multiply";
				break;
			case "/":
				varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_div_"+varYName));
				builtInName = "divide";
				break;
			}//fazer para ABS, MAX, MIN, -x
			
			//variables are inserted in the array of arguments 
			args.add(varZ);
			args.add(varX);
			args.add(varY);
			
			//the built in is created
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create(builtInName), args);
			
			//and added in the antecedent atoms
			antecedent.add(builtIn);
		}
		
		return varZ;
	}
	
	//ADAPTAR FUNCAO SOLVE ASSOCIATION PARA FUNCIONAR RECURSIVAMENTE
	private SWRLRule makeAllVariablesDiff(SWRLRule rule){
		Set<SWRLVariable> setOfVariables = rule.getVariables();
		Set<SWRLAtom> antecedent = rule.getBody();
		Set<SWRLAtom> consequent = rule.getHead();
		
		Object[] variables = setOfVariables.toArray();
		
		for(int i = 0; i < variables.length-1; i++){
			SWRLVariable varX = (SWRLVariable)variables[i];
			SWRLVariable varY = (SWRLVariable)variables[i+1];
			
			antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varX, varY)); //DifferentFrom(?x,?z)
		}
		
		SWRLRule outputRule = this.manager.getOWLDataFactory().getSWRLRule(antecedent, consequent);
		
		return outputRule;
	}
	
	//this function solves operations (logic) like (eg.: a>b or a<>b) and returns an variable equivalent to the operation
	private void solveOperation(OperationCallExpImpl bodyExpression, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		OCLExpression<Classifier> source = bodyExpression.getSource();
		OCLExpression<Classifier> argument = bodyExpression.getArgument().get(0);
		
		//variables referents of the source and argument expressions are created
		SWRLDArgument varX = solveExpression(source, antecedent, consequent);
		SWRLDArgument varY = solveExpression(argument, antecedent, consequent);
		
		//a list of arguments is created
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		args.add(varX);
		args.add(varY);
		
		//a built-in name and a name for the output variable is chosen according to the operation
		//note that the chosen operation is always the inverse of the used operation
		//this happens to  create the restriction in SWRL when the rule isn't followed
		String referredOperationName = bodyExpression.getReferredOperation().getName();
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
		case "=":
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create("notEqual"), args);
			break;
		case "<>":
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create("equal"), args);
			break;
		}
		
		//the built-in is added to the antecedent atom
		antecedent.add(builtIn);
	}
	
	//this function solves 
	private void solveProperties(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		if(bodyExpressionSource.getSource().getClass().equals(PropertyCallExpImpl.class)){
			this.solveProperties((PropertyCallExpImpl) bodyExpressionSource.getSource(), antecedent, consequent);
		}else if(bodyExpressionSource.getSource().getName().equals("self")){
			this.solveSelfVariable(bodyExpressionSource, consequent);
		}

		if(bodyExpressionSource.getClass().equals(PropertyCallExpImpl.class)){
			//object property
			if(bodyExpressionSource.getReferredProperty().getAssociation() != null){
				this.solveAssociation(bodyExpressionSource, antecedent);
			}
			//data property
			if(bodyExpressionSource.getReferredProperty().getType().getClass().equals(PrimitiveTypeImpl.class)){
				this.solveClassAttribute(bodyExpressionSource, antecedent);
			}
		
		}
	}
	
	//this function solves class attributes (eg.: the attribute age from a Person)
	private void solveClassAttribute(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent){
		ClassImpl containerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
		
		//generate a variable name likes in the OntoUML2OWL_SWRL
		String variableName = containerClass.getName() + "." + bodyExpressionSource.getReferredProperty().getName();
		OWLDataProperty variable = this.factory.getOWLDataProperty(IRI.create(this.nameSpace+variableName));
		
		//generates variables involved in the attribute
		String nameVarX = containerClass.getName();
		String nameVarY = bodyExpressionSource.getReferredProperty().getName();
		
		SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarX));
		SWRLVariable varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarY));
		
		//add the atom to the antecedents atoms
		//antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
		antecedent.add(this.factory.getSWRLDataPropertyAtom(variable, varX, varY)); //prop(?x,?Y)
	}
	
	private void solveSelfVariable(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> consequent){
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
	
	//this function solves associations (eg. self.owner)
	private void solveAssociation(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent){
		//get the relation
		OWLObjectProperty rel = this.factory.getOWLObjectProperty(IRI.create(this.nameSpace+bodyExpressionSource.getReferredProperty().getAssociation().getName()));
		
		String nameVarX = "";
		String nameVarY = "";
		
		if(bodyExpressionSource.getSource().getName().equals("self")){
			VariableExpImpl selfVar = (VariableExpImpl)bodyExpressionSource.getSource();
			ClassImpl classSelf = (ClassImpl)selfVar.getReferredVariable().getType();
			
			Boolean selfIsKindOfMember0 = false;
			Boolean selfIsKindOfMember1 = false;
			
			//get the class member situated in the first association end
			ClassImpl classMember0 = (ClassImpl)bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(0).getType();
			
			//verify if self is the source class of the association 
			if(!classSelf.equals(classMember0)){
				EList<Classifier> selfParents = classSelf.allParents();
				for (Classifier classifier : selfParents) {
					ClassImpl parent = (ClassImpl)classifier;
					if(parent.equals(classMember0)){
						selfIsKindOfMember0 = true;
					}
				}
			}
			
			//verify if self is the target class of the association
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
			
			//if self is the source class of the association, the varX receives the self name
			//otherwise, the varX receives the name of the source class
			if(selfIsKindOfMember0){
				nameVarX = classSelf.getName();
			}else{
				nameVarX = bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(0).getType().getName();
			}
			
			//if self is the target class of the association, the varX receives the self name
			//otherwise, the varX receives the name of the target class
			if(selfIsKindOfMember1){
				nameVarY = classSelf.getName();
			}else{
				nameVarY = bodyExpressionSource.getReferredProperty().getAssociation().getMemberEnds().get(1).getType().getName();
			}
		}
		
		//variables are created to the source and the target class
		SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarX));
		SWRLVariable varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarY));
		
		//the atoms are added to the antecedents atoms
		//antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
		antecedent.add(this.factory.getSWRLObjectPropertyAtom(rel, varX, varY)); //prop(?x,?Y)
	}
	
		
	//this function generates the relation name according to the ontology created in the OntoUML2OWL_SWRL transformation
	private String generatesRelationName(Class contextClass, Association assoc){
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
}
