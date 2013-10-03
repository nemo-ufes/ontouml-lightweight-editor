package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLBuiltInAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLVariable;

import uk.ac.manchester.cs.owl.owlapi.SWRLLiteralArgumentImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.util.Util;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:13
 */
public class OperationCallExpImplFactory extends FeatureCallExpImplFactory {

	public OCLExpressionImplFactory argumentFactory;
	
	public OperationCallExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);	
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public SWRLDArgument solve(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Boolean operationNegated = false;
		if(this.isNegatedOperation() && !expressionIsNegated){
			operationNegated = true;
		}else if(!this.isNegatedOperation() && expressionIsNegated){
			operationNegated = true;
		}
		
		OCLExpressionImpl source = (OCLExpressionImpl) operationCallExpImpl.getSource();
		
		EList<OCLExpression<Classifier>> arguments = operationCallExpImpl.getArgument();
		
		SWRLDArgument varY = null;
		if(arguments.size()>0){
			OCLExpressionImpl argument = (OCLExpressionImpl) operationCallExpImpl.getArgument().get(0);
			this.argumentFactory = (OCLExpressionImplFactory) Factory.constructor(argument);
			varY = this.argumentFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, oclConsequentShouldBeNegated, operationNegated, repeatNumber);
		}
		
		Boolean sourceOclConsequentShouldBeNegated = oclConsequentShouldBeNegated;
		if(this.isBodyExpression()){
			sourceOclConsequentShouldBeNegated = false;
		}
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source);
		SWRLDArgument varX = this.sourceFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, sourceOclConsequentShouldBeNegated, operationNegated, repeatNumber);
		
		SWRLDArgument varZ = null;
		if(this.isComparisonOperation()){
			varZ = solveComparison(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isImpliesOperation()){
			//do nothing
		}else if(this.isArithmeticOperation()){
			varZ = solveArithmetic(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isKindOfOperation()){
			varZ = solveOCLIsKindOf(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isEmpty()){
			varZ = solveIsEmpty(nameSpace, manager, factory, ontology, antecedent, consequent, varX, referredArgument, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.notEmpty()){
			varZ = solveNotEmpty(nameSpace, manager, factory, ontology, antecedent, consequent, varX, referredArgument, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isAbs()){
			varZ = solveAbs(nameSpace, manager, factory, ontology, antecedent, consequent, varX, oclConsequentShouldBeNegated, operationNegated);
		}else{
			varZ = varX;
		}
		
		return varZ;
	}
	
	public SWRLDArgument solveAbs(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		String varName = "abs.";
		if(referredArgX.getClass().equals(SWRLLiteralArgumentImpl.class)){
			OWLLiteral literal = ((SWRLLiteralArgumentImpl)referredArgX).getLiteral();
			varName += literal.getLiteral();
		}else{
			IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
			varName += iri.getFragment();
		}
		
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+varName));
		
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		args.add(referredArgX);
		args.add(varZ);
		SWRLBuiltInAtom builtIn = factory.getSWRLBuiltInAtom(IRI.create("abs"), args);
		
		antecedent.add(builtIn);
		
		return varZ;
	}
		
	public SWRLDArgument solveIsEmpty(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
		OWLClass owlClass = factory.getOWLClass(iri);
		SWRLClassAtom atom = factory.getSWRLClassAtom(owlClass, (SWRLIArgument) referredArgY);
		antecedent.add(atom);
		
		return null;
	}
	
	public SWRLDArgument solveNotEmpty(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
		SWRLClassAtom atom = null;
		OWLClass owlClass = factory.getOWLClass(iri);
		OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
		atom = factory.getSWRLClassAtom(complementOf, (SWRLIArgument) referredArgY);
		antecedent.add(atom);
		
		return null;
	}
	
	public SWRLDArgument solveOCLIsKindOf(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();

		IRI iri = ((SWRLVariableImpl) referredArgY).getIRI();
		SWRLClassAtom atom = null;
		OWLClass owlClass = factory.getOWLClass(iri);
		switch (referredOperationName) {
		case "not":
			//OCLExpression<Classifier> assoc = ((CollectionItemImpl)((CollectionLiteralExpImpl)((IteratorExpImpl)bodyExpression.getOwner()).getSource()).getPart().get(0)).getItem();
			
			//Classifier referClass = ((TypeExpImpl) ((OperationCallExpImpl) source).getArgument().get(0)).getReferredType();
			//solveVariable(assoc, false, antecedent, consequent, (ClassImpl) referClass); 
			break;
		case "oclIsKindOf":
		case "oclIsTypeOf":
		case "oclAsType":
			if((oclConsequentShouldBeNegated && !expressionIsNegated) || (!oclConsequentShouldBeNegated && expressionIsNegated)){
				OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
				
				atom = factory.getSWRLClassAtom(complementOf, (SWRLIArgument) referredArgX);
			}else{
				atom = factory.getSWRLClassAtom(owlClass, (SWRLIArgument) referredArgX);
			}			

			break;
		}
		
		antecedent.add(atom);
		
		return null;
	}
	
	public SWRLDArgument solveComparison(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();
		
		//a list of arguments is created
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		args.add(referredArgX);
		args.add(referredArgY);
				
		//a built-in name and a name for the output variable is chosen according to the operation
		//note that the chosen operation is always the inverse of the used operation
		//this happens to  create the restriction in SWRL when the rule isn't followed
		String iriName = "";
		switch (referredOperationName) {
		case ">":
			iriName = "lessThanOrEqual";
			//builtIn = factory.getSWRLBuiltInAtom(IRI.create("lessThanOrEqual"), args);
			break;
		case ">=":
			iriName = "lessThan";
			//builtIn = factory.getSWRLBuiltInAtom(IRI.create("lessThan"), args);
			break;
		case "<":
			iriName = "greaterThanOrEqual";
			//builtIn = factory.getSWRLBuiltInAtom(IRI.create("greaterThanOrEqual"), args);
			break;
		case "<=":
			iriName = "greaterThan";
			//builtIn = factory.getSWRLBuiltInAtom(IRI.create("greaterThan"), args);
			break;	
		case "=":
			iriName = "notEqual";
			//builtIn = factory.getSWRLBuiltInAtom(IRI.create("notEqual"), args);
			break;
		case "<>":
			iriName = "equal";
			//builtIn = factory.getSWRLBuiltInAtom(IRI.create("equal"), args);
			break;
		}
		SWRLBuiltInAtom builtIn = factory.getSWRLBuiltInAtom(IRI.create(iriName), args);
		//the built-in is added to the antecedent atom
		antecedent.add(builtIn);
		
		return null;
	}
	
	public SWRLDArgument solveArithmetic(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();
		SWRLDArgument varZ;
		
		if(referredArgY == null && referredOperationName.equals("-")){
			if(referredArgX.getClass().equals(SWRLLiteralArgumentImpl.class)){
				OCLExpression<?> literal = ((OperationCallExpImpl)m_NamedElementImpl).getSource();
				NumericLiteralExpImplFactory numericFactory = (NumericLiteralExpImplFactory) Factory.constructor(literal);
				
				varZ = numericFactory.solveNegativeNumber(nameSpace, manager, factory, ontology, antecedent, consequent, referredArgX, false, false);
				
				return varZ;
			}
		}
		
		//an argument list for the expression is created
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		
		String varXName = Util.generateVarName(referredArgX, null);
		String varYName = Util.generateVarName(referredArgY, null);
		
		//a built-in name and a name for the output variable is chosen according to the expression operation
		String builtInName = "";
		SWRLBuiltInAtom builtIn = null;

		switch (referredOperationName) {
		case "+":
			builtInName = "add";
			break;
		case "-":
			builtInName = "subtract";
			break;
		case "*":
			builtInName = "multiply";
			break;
		case "/":
			builtInName = "divide";
			break;
		}//fazer para ABS, MAX, MIN, -x
		
		varZ = factory.getSWRLVariable(IRI.create(nameSpace+varXName+"_"+builtInName+varYName+"_"));
		
		//variables are inserted in the array of arguments 
		args.add(varZ);
		args.add(referredArgX);
		args.add(referredArgY);
		
		if(!builtInName.equals("")){
			//the built in is created
			builtIn = factory.getSWRLBuiltInAtom(IRI.create(builtInName), args);
			
			//and added in the antecedent atoms
			antecedent.add(builtIn);
		}
		
		return varZ;
	}
	
	@Override
	public Boolean isComparisonOperation(){
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals(">") ||
					oprName.equals(">=") ||
					oprName.equals("<") ||
					oprName.equals("<=") ||
					oprName.equals("=") ||
					oprName.equals("<>")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isArithmeticOperation(){
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("add") ||
					oprName.equals("subtract") ||
					oprName.equals("divide") ||
					oprName.equals("multiply") ||
					oprName.equals("+") ||
					oprName.equals("-") ||
					oprName.equals("/") ||
					oprName.equals("*")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isKindOfOperation(){
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("oclIsKindOf") ||
					oprName.equals("oclIsTypeOf") ||
					oprName.equals("oclAsType")
				){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Boolean isImpliesOperation() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("implies")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isNegatedOperation() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("not")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isEmpty() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("isEmpty")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean notEmpty() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("notEmpty")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isAbs() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("abs")
				){
				return true;
			}
		}
		
		return false;
	}
}