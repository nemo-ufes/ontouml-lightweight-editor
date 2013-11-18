package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.IteratorExpImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLDifferentIndividualsAtom;
import org.semanticweb.owlapi.model.SWRLSameIndividualAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class IteratorExpImplFactory extends LoopExpImplFactory {

	public IteratorExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber) {
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		OCLExpressionImpl source = (OCLExpressionImpl) iteratorExpImpl.getSource();
		OCLExpressionImpl body = (OCLExpressionImpl) iteratorExpImpl.getBody();
		
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source);
		ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber);
		SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);//pega o ultimo
		
		this.bodyFactory = (OCLExpressionImplFactory) Factory.constructor(body);
		ArrayList<SWRLDArgument> retArgsY = this.bodyFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, repeatNumber); 
		SWRLDArgument varY = retArgsY.get(retArgsY.size()-1);//pega o ultimo
		
		//SWRLDArgument varZ = null;
		if(this.isUniqueIterator()){
			//varZ = 
			solveIsUnique(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, retArgsX, retArgsY, false);
		}
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(varX);
		retArgs.add(varY);
		return retArgs;
	}
	
	public ArrayList<SWRLDArgument> solveIsUnique(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, ArrayList<SWRLDArgument> referredArgsX, ArrayList<SWRLDArgument> referredArgsY, Boolean operatorNot) {
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		OCLExpressionImpl source = (OCLExpressionImpl) iteratorExpImpl.getSource();
		OCLExpressionImpl body = (OCLExpressionImpl) iteratorExpImpl.getBody();
		
		int repeatNumber = 2;
		SWRLDArgument var0 = referredArgsX.get(0);
		
		SWRLDArgument varX = referredArgsX.get(referredArgsX.size()-1);//pega o ultimo
		SWRLDArgument varY = referredArgsY.get(referredArgsY.size()-1);//pega o ultimo
		
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source);
		ArrayList<SWRLDArgument> retArgsX2 = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber);
		SWRLDArgument varX2 = retArgsX2.get(retArgsX2.size()-1);//pega o ultimo
		SWRLDArgument var0_2 = retArgsX2.get(0);
		
		this.bodyFactory = (OCLExpressionImplFactory) Factory.constructor(body);
		ArrayList<SWRLDArgument> retArgsY2 = this.bodyFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, varX2, operatorNot, repeatNumber); 
		SWRLDArgument varY2 = retArgsY2.get(retArgsY2.size()-1);//pega o ultimo
		
		SWRLSameIndividualAtom same0 = factory.getSWRLSameIndividualAtom((SWRLVariable)var0, (SWRLVariable)var0_2);
		antecedent.add(same0);
		
		SWRLDifferentIndividualsAtom diff = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varX, (SWRLVariable)varX2);
		antecedent.add(diff);
		
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
			SWRLSameIndividualAtom same = factory.getSWRLSameIndividualAtom((SWRLVariable)varY, (SWRLVariable)varY2);
			antecedent.add(same);
		}else if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			SWRLDifferentIndividualsAtom diff2 = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varY, (SWRLVariable)varY2);
			antecedent.add(diff2);
		}
		
		
		return null;
	}
	
	@Override
	public Boolean isUniqueIterator(){
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		String name = iteratorExpImpl.getName();
		
		if(name.equals("isUnique")){
			return true;
		}
		
		return false;
	}
}