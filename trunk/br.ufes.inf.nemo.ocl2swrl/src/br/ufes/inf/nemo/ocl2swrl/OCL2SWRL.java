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
	
	public void Transformation ()	{
		this.verifyVariablesInitialization();

		for(Constraint ct: this.oclParser.getConstraints())
		{
			ExpressionInOCL expr = (ExpressionInOCL) ct.getSpecification();
			
			if(expr.getBodyExpression().getClass() == OperationCallExpImpl.class){
				//get the body expression
				OperationCallExpImpl bodyExpression = (OperationCallExpImpl) expr.getBodyExpression();
				
				//create SWRL rule for the expression
				solveBodyExpression(bodyExpression);				
				
			}
				
		}
	}
	
	private void solveBodyExpression(OperationCallExpImpl bodyExpression){
		//create antecedent and consequent atoms for the rule
		Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
		Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
		
		//get the expression source 
		PropertyCallExpImpl bodyExpressionSource = (PropertyCallExpImpl)bodyExpression.getSource();
		
		//create antecedents e consequents for the body expression source
		solveBodyExpressionSource(bodyExpressionSource, antecedent, consequent);
		
		//create antecedents e consequents for the body expression argument
		solveBodyExpressionArgument(bodyExpression, antecedent);
		
		//create rule with the incremented antecedents and consequents
		SWRLRule rule = this.manager.getOWLDataFactory().getSWRLRule(antecedent,consequent);
		
		//apply changes
		this.manager.applyChange(new AddAxiom(this.manager.getOntology(IRI.create(this.nameSpace.substring(0, this.nameSpace.length()-1))), rule));
		
		//System.out.println();
		
	}
	
	private SWRLDArgument solveArgumentVars(OCLExpression<Classifier> argument, Set<SWRLAtom> antecedent){
		//OCLExpression<Classifier> source = bodyExpression.getSource();
		//OCLExpression<Classifier> argument = bodyExpression.getArgument().get(0);
		
		SWRLDArgument varY = null;
		if(argument.getClass().equals(IntegerLiteralExpImpl.class)){
			varY = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((IntegerLiteralExpImpl)argument).getIntegerSymbol()));
		}else if(argument.getClass().equals(BooleanLiteralExpImpl.class)){
			varY = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((BooleanLiteralExpImpl)argument).getBooleanSymbol()));
		}else if(argument.getClass().equals(StringLiteralExpImpl.class)){
			varY = factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)argument).getStringSymbol()));
		}else if(argument.getClass().equals(OperationCallExpImpl.class)){
			OperationCallExpImpl operation = (OperationCallExpImpl)argument;
			
			OCLExpression<Classifier> srcOperation = operation.getSource();
			OCLExpression<Classifier> argOperation = operation.getArgument().get(0);
			SWRLDArgument varZ = solveArgumentVars(srcOperation, antecedent);
			SWRLDArgument varW = solveArgumentVars(argOperation, antecedent);
			
			/*if(source.getClass().equals(IntegerLiteralExpImpl.class)){
				varZ = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((IntegerLiteralExpImpl)source).getIntegerSymbol()));
			}else if(source.getClass().equals(BooleanLiteralExpImpl.class)){
				varZ = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((BooleanLiteralExpImpl)source).getBooleanSymbol()));
			}else if(argument.getClass().equals(StringLiteralExpImpl.class)){
				varZ = factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)argument).getStringSymbol()));
			}else if(){
				
			}*/
			
			List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
			
			String varZName = "";
			String varWName = "";
			
			if(varZ.getClass().equals(SWRLLiteralArgumentImpl.class)){
				varZName = ((SWRLLiteralArgumentImpl)varZ).getLiteral().getLiteral();
			}else if(varZ.getClass().equals(SWRLVariableImpl.class)){
				IRI iri = (IRI)((SWRLVariableImpl)varZ).getIRI();
				varZName = iri.getFragment();
			}
			
			if(varW.getClass().equals(SWRLLiteralArgumentImpl.class)){
				varWName = ((SWRLLiteralArgumentImpl)varW).getLiteral().getLiteral();
			}else if(varW.getClass().equals(SWRLVariableImpl.class)){
				IRI iri = (IRI)((SWRLVariableImpl)varW).getIRI();
				varWName = iri.getFragment();
			}
			
			String builtInName = "";
			
			SWRLBuiltInAtom builtIn = null;
			String refOperationName = operation.getReferredOperation().getName();
			switch (refOperationName) {
			case "+":
				varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+"_"+varZName+"add"+varWName));
				builtInName = "add";
				break;
			case "-":
				varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+"_"+varZName+"subtract"+varWName));
				builtInName = "subtract";
				break;
			case "*":
				varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+"_"+varZName+"multiply"+varWName));
				builtInName = "multiply";
				break;
			case "/":
				varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+"_"+varZName+"divide"+varWName));
				builtInName = "divide";
				break;
			}//fazer para ABS, MAX, MIN, -x
			
			args.add(varY);
			args.add(varZ);
			args.add(varW);
			
			builtIn = this.factory.getSWRLBuiltInAtom(IRI.create(builtInName), args);
			
			antecedent.add(builtIn);
		}
		
		return varY;
	}
	
	private void solveBodyExpressionArgument(OperationCallExpImpl bodyExpression, Set<SWRLAtom> antecedent){
		OCLExpression<Classifier> source = bodyExpression.getSource();
		OCLExpression<Classifier> argument = bodyExpression.getArgument().get(0);
		
		SWRLVariable varX = null;
		if(source.getClass().equals(PropertyCallExpImpl.class)){
			PropertyCallExpImpl bodyExpressionSource = (PropertyCallExpImpl)bodyExpression.getSource();
			ClassImpl conteinerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
			String varXName = bodyExpressionSource.getReferredProperty().getName();
			varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+conteinerClass.getName()+"."+varXName));
		}/*else if(source.getClass().equals(IntegerLiteralExpImpl.class)){
			
		}else if(argument.getClass().equals(StringLiteralExpImpl.class)){
			
		}*/
		SWRLDArgument varY = solveArgumentVars(argument, antecedent);
		/*	
		SWRLLiteralArgument varY = null;
		if(argument.getClass().equals(IntegerLiteralExpImpl.class)){
			varY = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((IntegerLiteralExpImpl)argument).getIntegerSymbol()));
		}else if(argument.getClass().equals(BooleanLiteralExpImpl.class)){
			varY = this.factory.getSWRLLiteralArgument(this.factory.getOWLLiteral(((BooleanLiteralExpImpl)argument).getBooleanSymbol()));
		}else if(argument.getClass().equals(StringLiteralExpImpl.class)){
			varY = factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)argument).getStringSymbol()));
		}else if(argument.getClass().equals(OperationCallExpImpl.class)){
			OperationCallExpImpl argOperation = (OperationCallExpImpl)argument;
			
			System.out.println();
		}
		*/
		/*else if(argument.get(0).getClass().equals(StringLiteralExpImpl.class)){
			varY = factory.getSWRLLiteralArgument(factory.getOWLLiteral(((StringLiteralExpImpl)argument.get(0)).getStringSymbol()));
		}*///verificar para double e float
		
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		args.add(varX);
		args.add(varY);
		
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
		}
		
		antecedent.add(builtIn);		
	}
	
	private void solveBodyExpressionSource(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		if(bodyExpressionSource.getSource().getClass().equals(PropertyCallExpImpl.class)){
			solveBodyExpressionSource((PropertyCallExpImpl) bodyExpressionSource.getSource(), antecedent, consequent);
		}else if(bodyExpressionSource.getSource().getName().equals("self")){
			solveSelfVariable(bodyExpressionSource, consequent);
		}

		if(bodyExpressionSource.getClass().equals(PropertyCallExpImpl.class)){
			//object property
			if(bodyExpressionSource.getReferredProperty().getAssociation() != null){
				solveObjectProperty(bodyExpressionSource, antecedent);
			}
			//data property
			if(bodyExpressionSource.getReferredProperty().getType().getClass().equals(PrimitiveTypeImpl.class)){
				this.solveDataProperty(bodyExpressionSource, antecedent);
			}
		
		}
	}
	
	private void solveDataProperty(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent){
		ClassImpl containerClass = (ClassImpl)bodyExpressionSource.getReferredProperty().getOwner();
		
		String variableName = containerClass.getName() + "." + bodyExpressionSource.getReferredProperty().getName();
		OWLDataProperty variable = this.factory.getOWLDataProperty(IRI.create(this.nameSpace+variableName));
		
		String nameVarX = containerClass.getName();
		String nameVarY = bodyExpressionSource.getReferredProperty().getName();
		
		SWRLVariable varX = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarX));
		SWRLVariable varY = this.factory.getSWRLVariable(IRI.create(this.nameSpace+nameVarY));
		
		antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varY, varX)); //DifferentFrom(?x,?z)
		antecedent.add(this.factory.getSWRLDataPropertyAtom(variable, varX, varY)); //prop(?x,?Y)
		//System.out.println();
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
	
	private void solveObjectProperty(PropertyCallExpImpl bodyExpressionSource, Set<SWRLAtom> antecedent){
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
