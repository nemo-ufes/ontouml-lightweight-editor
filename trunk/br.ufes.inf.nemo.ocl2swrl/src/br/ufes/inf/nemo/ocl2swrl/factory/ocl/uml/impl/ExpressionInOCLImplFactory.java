package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.Set;

import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.internal.impl.NamedElementImplFactory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class ExpressionInOCLImplFactory extends OpaqueExpressionImplFactory {

	public OCLExpressionImplFactory bodyExpressionFactory;
	
	public ExpressionInOCLImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Override
	public SWRLDArgument solve(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent) {
		ExpressionInOCLImpl expressionInOCLImpl = (ExpressionInOCLImpl) this.m_NamedElementImpl;
		OCLExpressionImpl bodyExpression = (OCLExpressionImpl) expressionInOCLImpl.getBodyExpression();
		
		bodyExpressionFactory = (OCLExpressionImplFactory) NamedElementImplFactory.constructor(bodyExpression);
		bodyExpressionFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent);
		
		return null;
		
	}
}