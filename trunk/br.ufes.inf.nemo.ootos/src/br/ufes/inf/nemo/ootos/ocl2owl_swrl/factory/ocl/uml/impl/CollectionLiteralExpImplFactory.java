package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.CollectionItemImpl;
import org.eclipse.ocl.uml.impl.CollectionLiteralExpImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.uml2.uml.internal.impl.TypedElementImplFactory;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class CollectionLiteralExpImplFactory extends LiteralExpImplFactory {
	public TypedElementImplFactory partFactory;
	
	public CollectionLiteralExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);		
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a collection literal fragment
		CollectionLiteralExpImpl collectionLiteralExpImpl = (CollectionLiteralExpImpl) this.m_NamedElementImpl;
		
		//then, the part of the colllection item is got
		CollectionItemImpl part = (CollectionItemImpl)collectionLiteralExpImpl.getPart().get(0);
		
		//and a factory is created according to the part class 
		this.partFactory = (TypedElementImplFactory) Factory.constructor(part, this.m_NamedElementImpl);
		
		//the part is solved and the and the returned arguments from the partSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX = this.partFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber, leftSideOfImplies);
		
		return retArgsX;
	}
	
	@Override
	public OWLObjectProperty getOWLObjectProperty(OCLExpressionImpl oclExpression, String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a collection literal fragment
		CollectionLiteralExpImpl collectionLiteralExpImpl = (CollectionLiteralExpImpl) this.m_NamedElementImpl;
		//then, the part of the colllection item is got
		CollectionItemImpl part = (CollectionItemImpl)collectionLiteralExpImpl.getPart().get(0);
		
		//and a factory is created according to the part class 
		this.partFactory = (TypedElementImplFactory) Factory.constructor(part, this.m_NamedElementImpl);
		
		//the factory found the OWL Object Property and return it
		return this.partFactory.getOWLObjectProperty(oclExpression, nameSpace, refParser, factory);
		
		
	}
}