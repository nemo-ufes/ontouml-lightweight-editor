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
import org.semanticweb.owlapi.model.SWRLDifferentIndividualsAtom;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLSameIndividualAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import uk.ac.manchester.cs.owl.owlapi.SWRLLiteralArgumentImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
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
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated, int repeatNumber) {
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
		ArrayList<SWRLDArgument> retArgsY = null;
		if(arguments.size()>0){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				repeatNumber=2;
			}
			
			OCLExpressionImpl argument = (OCLExpressionImpl) operationCallExpImpl.getArgument().get(0);
			this.argumentFactory = (OCLExpressionImplFactory) Factory.constructor(argument);
			retArgsY = this.argumentFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, oclConsequentShouldBeNegated, operationNegated, repeatNumber);
			varY = retArgsY.get(retArgsY.size()-1);//pega o ultimo
			
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				repeatNumber=1;
			}
		}
		
		Boolean sourceOclConsequentShouldBeNegated = oclConsequentShouldBeNegated;
		if(this.isBodyExpression()){
			sourceOclConsequentShouldBeNegated = false;
		}
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source);
		
		ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, sourceOclConsequentShouldBeNegated, operationNegated, repeatNumber);
		SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);//pega o ultimo
		
		ArrayList<SWRLDArgument> retArgsZ = null;
		if(this.isComparisonOperation()){
			retArgsZ = solveComparison(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isImpliesOperation()){
			//do nothing
		}else if(this.isArithmeticOperation()){
			retArgsZ = solveArithmetic(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isKindOfOperation()){
			retArgsZ = solveOCLIsKindOf(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isIsEmptyOperation()){
			retArgsZ = solveIsEmpty(nameSpace, manager, factory, ontology, antecedent, consequent, varX, referredArgument, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isNotEmptyOperation()){
			retArgsZ = solveNotEmpty(nameSpace, manager, factory, ontology, antecedent, consequent, varX, referredArgument, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isAbsOperation()){
			retArgsZ = solveAbs(nameSpace, manager, factory, ontology, antecedent, consequent, varX, oclConsequentShouldBeNegated, operationNegated);
		}else if(this.isIncludesOperation()){
			retArgsZ = solveIncludes(ctStereotype, factory, antecedent, consequent, retArgsX, retArgsY);
		}else if(this.isExcludesOperation()){
			retArgsZ = solveExcludes(ctStereotype, factory, antecedent, consequent, retArgsX, retArgsY);
		}else{
			retArgsZ = retArgsX;
		}
		
		if(retArgsZ == null){
			retArgsZ = retArgsX;
		}
		
		return retArgsZ;
	}
	
	public ArrayList<SWRLDArgument> solveIncludes(String ctStereotype, OWLDataFactory factory, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, ArrayList<SWRLDArgument> referredArgsX, ArrayList<SWRLDArgument> referredArgsY) {
		SWRLDArgument varX1 = referredArgsX.get(0);
		SWRLDArgument varX2 = referredArgsX.get(1);
		
		SWRLDArgument varY1 = referredArgsY.get(0);
		SWRLDArgument varY2 = referredArgsY.get(1);
		
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
			SWRLDifferentIndividualsAtom diff = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varX1, (SWRLVariable)varY1);
			antecedent.add(diff);
		}else if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			SWRLSameIndividualAtom same1 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX1, (SWRLVariable)varY1);
			antecedent.add(same1);
		}
		
		SWRLSameIndividualAtom same2 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX2, (SWRLVariable)varY2);
		antecedent.add(same2);
		
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveExcludes(String ctStereotype, OWLDataFactory factory, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, ArrayList<SWRLDArgument> referredArgsX, ArrayList<SWRLDArgument> referredArgsY) {
		SWRLDArgument varX1 = referredArgsX.get(0);
		SWRLDArgument varX2 = referredArgsX.get(1);
		
		SWRLDArgument varY1 = referredArgsY.get(0);
		SWRLDArgument varY2 = referredArgsY.get(1);
		
		SWRLSameIndividualAtom same1 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX1, (SWRLVariable)varY1);
		antecedent.add(same1);
		
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
			SWRLSameIndividualAtom same2 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX2, (SWRLVariable)varY2);
			antecedent.add(same2);
		}else if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			SWRLDifferentIndividualsAtom diff = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varX2, (SWRLVariable)varY2);
			antecedent.add(diff);
		}
		
		
		
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveAbs(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
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
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(varZ);
		
		return retArgs;
	}
		
	public ArrayList<SWRLDArgument> solveIsEmpty(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
		OWLClass owlClass = factory.getOWLClass(iri);
		SWRLClassAtom atom = factory.getSWRLClassAtom(owlClass, (SWRLIArgument) referredArgY);
		antecedent.add(atom);
		
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveNotEmpty(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
		SWRLClassAtom atom = null;
		OWLClass owlClass = factory.getOWLClass(iri);
		OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
		atom = factory.getSWRLClassAtom(complementOf, (SWRLIArgument) referredArgY);
		antecedent.add(atom);
		
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveOCLIsKindOf(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
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
			if(((oclConsequentShouldBeNegated && !expressionIsNegated) || (!oclConsequentShouldBeNegated && expressionIsNegated)) && org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
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
	
	public ArrayList<SWRLDArgument> solveComparison(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
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
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				iriName = "lessThanOrEqual";
			}else{
				iriName = "greaterThan";
			}			
			break;
		case ">=":
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				iriName = "lessThan";
			}else{
				iriName = "greaterThanOrEqual";
			}
			break;
		case "<":
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				iriName = "greaterThanOrEqual";
			}else{
				iriName = "lessThan";
			}
			break;
		case "<=":
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				iriName = "greaterThan";
			}else{
				iriName = "lessThanOrEqual";
			}
			break;	
		case "=":
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				iriName = "notEqual";
			}else{
				iriName = "equal";
			}
			break;
		case "<>":
			if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
				iriName = "equal";
			}else{
				iriName = "notEqual";
			}
			break;
		}
		SWRLBuiltInAtom builtIn = factory.getSWRLBuiltInAtom(IRI.create(iriName), args);
		//the built-in is added to the antecedent atom
		antecedent.add(builtIn);
		
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveArithmetic(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean oclConsequentShouldBeNegated, Boolean expressionIsNegated) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();
		SWRLDArgument varZ;
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		
		if(referredArgY == null && referredOperationName.equals("-")){
			if(referredArgX.getClass().equals(SWRLLiteralArgumentImpl.class)){
				OCLExpression<?> literal = ((OperationCallExpImpl)m_NamedElementImpl).getSource();
				NumericLiteralExpImplFactory numericFactory = (NumericLiteralExpImplFactory) Factory.constructor(literal);
				
				retArgs = numericFactory.solveNegativeNumber(nameSpace, manager, factory, ontology, antecedent, consequent, referredArgX, false, false);
				
				return retArgs;
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
		
		retArgs.add(varZ);
		
		return retArgs;
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
	public Boolean isIsEmptyOperation() {
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
	public Boolean isNotEmptyOperation() {
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
	public Boolean isAbsOperation() {
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
	
	@Override
	public Boolean isIncludesOperation() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("includes")	||
					oprName.equals("includesAll")	||
					oprName.equals("intersection")	||
					oprName.equals("including")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isExcludesOperation() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("excludes")	||
					oprName.equals("excludesAll")	||
					oprName.equals("excluding")
				){
				return true;
			}
		}
		
		return false;
	}
}