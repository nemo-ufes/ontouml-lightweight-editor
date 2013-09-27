package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLBuiltInAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLVariable;

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
	public SWRLDArgument solve(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		OCLExpressionImpl source = (OCLExpressionImpl) operationCallExpImpl.getSource();
		OCLExpressionImpl argument = (OCLExpressionImpl) operationCallExpImpl.getArgument().get(0);
		
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source);
		SWRLDArgument varX = this.sourceFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument);
		
		this.argumentFactory = (OCLExpressionImplFactory) Factory.constructor(argument);
		SWRLDArgument varY = this.argumentFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, null);
		
		SWRLDArgument varZ = null;
		if(isComparisonOperation(operationCallExpImpl)){
			varZ = solveComparison(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY);
		}else if(isImpliesOperation(operationCallExpImpl)){
			
		}else if(isArithmeticOperation(operationCallExpImpl)){
			varZ = solveArithmetic(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY);
		}else if(isKindOfOperation(operationCallExpImpl)){
			varZ = solveOCLIsKindOf(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY);
		}
		
		return varZ;
	}
	
	public SWRLDArgument solveOCLIsKindOf(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();

		IRI iri = ((SWRLVariableImpl) referredArgY).getIRI();
		SWRLClassAtom atom = null;
		
		switch (referredOperationName) {
		case "not":
			//OCLExpression<Classifier> assoc = ((CollectionItemImpl)((CollectionLiteralExpImpl)((IteratorExpImpl)bodyExpression.getOwner()).getSource()).getPart().get(0)).getItem();
			
			//Classifier referClass = ((TypeExpImpl) ((OperationCallExpImpl) source).getArgument().get(0)).getReferredType();
			//solveVariable(assoc, false, antecedent, consequent, (ClassImpl) referClass); 
			break;
		case "oclIsKindOf":
			OWLClass owlClass = factory.getOWLClass(iri);
			
			atom = factory.getSWRLClassAtom(owlClass, (SWRLIArgument) referredArgX);

			break;
		}
		
		antecedent.add(atom);
		
		return null;
	}
	
	public SWRLDArgument solveComparison(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();
		
		//a list of arguments is created
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		args.add(referredArgX);
		args.add(referredArgY);
				
		//a built-in name and a name for the output variable is chosen according to the operation
		//note that the chosen operation is always the inverse of the used operation
		//this happens to  create the restriction in SWRL when the rule isn't followed
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
		case "=":
			builtIn = factory.getSWRLBuiltInAtom(IRI.create("notEqual"), args);
			break;
		case "<>":
			builtIn = factory.getSWRLBuiltInAtom(IRI.create("equal"), args);
			break;
		}

		//the built-in is added to the antecedent atom
		antecedent.add(builtIn);
		
		return null;
	}
	
	public SWRLDArgument solveArithmetic(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY) {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();
		
		//an argument list for the expression is created
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		
		String varXName = Util.generateVarName(referredArgX, null);
		String varYName = Util.generateVarName(referredArgY, null);
		
		//a built-in name and a name for the output variable is chosen according to the expression operation
		String builtInName = "";
		SWRLBuiltInAtom builtIn = null;

		switch (referredOperationName) {
		case "+":
			//varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_add_"+varYName));
			builtInName = "add";
			break;
		case "-":
			//varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_sub_"+varYName));
			builtInName = "subtract";
			break;
		case "*":
			//varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_mul_"+varYName));
			builtInName = "multiply";
			break;
		case "/":
			//varZ = this.factory.getSWRLVariable(IRI.create(this.nameSpace+varXName+"_div_"+varYName));
			builtInName = "divide";
			break;
		}//fazer para ABS, MAX, MIN, -x
		
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+varXName+"_"+builtInName+varYName+"_"));
		
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
	
	private Boolean isImpliesOperation(OperationCallExpImpl operationCallExpImpl){
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
	
	private Boolean isComparisonOperation(OperationCallExpImpl operationCallExpImpl){
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
	
	private Boolean isArithmeticOperation(OperationCallExpImpl bodyExpression){
		Operation operation = bodyExpression.getReferredOperation();
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
	
	private Boolean isKindOfOperation(Object bodyExpression){
		if(!bodyExpression.getClass().equals(OperationCallExpImpl.class)){
			return false;
		}
		Operation operation = ((OperationCallExpImpl) bodyExpression).getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("oclIsKindOf") ||
					oprName.equals("oclIsTypeOf")
				){
				return true;
			}
		}
		
		return false;
	}
}