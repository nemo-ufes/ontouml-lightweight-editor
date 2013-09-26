package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLBuiltInAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.ocl2swrl.factory.Factory;



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
		SWRLDArgument varX = this.sourceFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, null);
		
		this.argumentFactory = (OCLExpressionImplFactory) Factory.constructor(argument);
		SWRLDArgument varY = this.argumentFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent, null);
		
		SWRLDArgument varZ = null;
		if(isComparisonOperation(operationCallExpImpl)){
			varZ = solveComparison(nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY);
		}else if(isImpliesOperation(operationCallExpImpl)){
			
		}
		
		return varZ;
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
}