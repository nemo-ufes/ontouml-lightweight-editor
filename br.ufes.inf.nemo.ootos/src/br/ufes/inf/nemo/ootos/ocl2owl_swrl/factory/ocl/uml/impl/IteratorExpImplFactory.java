package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.IteratorExpImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLDifferentIndividualsAtom;
import org.semanticweb.owlapi.model.SWRLSameIndividualAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class IteratorExpImplFactory extends LoopExpImplFactory {

	public IteratorExpImplFactory(NamedElementImpl m_NamedElementImpl) throws Ocl2Owl_SwrlException{
		super(m_NamedElementImpl);
		
		//verifies if the iterator is unsupported
		if(this.isUnsupported()){
			//since the factory is created according to the rule fragment, the fragment is got as a iterator fragment
			IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
			//get the iterator name
			String name = iteratorExpImpl.getName();
			//get the string containing the rule
			String rule = getStrRule(this.m_NamedElementImpl);
			//throw a new exception
			throw new NonSupported(name, rule);
		}
	}
	
	public Boolean isUnsupported(){
		//since the factory is created according to the rule fragment, the fragment is got as a iterator fragment
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		//get the iterator name
		String name = iteratorExpImpl.getName();
		
		if(name == null){
			return false;
		}
		
		//if the iterator is one above, it's unsupported
		if(		name.equals("isEmpty") |
				name.equals("notEmpty") |
				name.equals("exist") |
				name.equals("select") |
				name.equals("reject") |
				//name.equals("collect") |
				name.equals("one")){
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a iterator fragment
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		//then, the source of the iterator is got
		OCLExpressionImpl source = (OCLExpressionImpl) iteratorExpImpl.getSource();
		//then, the body of the iterator is got
		OCLExpressionImpl body = (OCLExpressionImpl) iteratorExpImpl.getBody();
		
		//and a factory is created according to the source class 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source, this.m_NamedElementImpl);
		//the source is solved and the and the returned arguments from the sourceSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber, leftSideOfImplies);
		SWRLDArgument varX = null;
		if(retArgsX != null){
			varX = retArgsX.get(retArgsX.size()-1);//get the last
		}		
		
		//and a factory is created according to the body class 
		this.bodyFactory = (OCLExpressionImplFactory) Factory.constructor(body, this.m_NamedElementImpl);
		//the body is solved and the and the returned arguments from the bodySolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsY = this.bodyFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, repeatNumber, leftSideOfImplies); 
		SWRLDArgument varY = null;
		if(retArgsY != null){
			varY = retArgsY.get(retArgsY.size()-1);//get the last
		}
		
		if(retArgsX == null || retArgsY == null){
			return null;
		}
		if(retArgsY.size() > 0 && retArgsX.size() > 0 && (org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) || org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype))){
			SWRLDArgument varY0 = retArgsY.get(0);
			
			retArgsX.set(0, varY0);
		}
		
		if(this.isTempVariable(varY)){
			varY = varX;
		}
		
		//SWRLDArgument varZ = null;
		if(this.isUniqueIterator()){
			//varZ =
			//solve the unique iterator
			solveIsUnique(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, retArgsX, retArgsY, false, leftSideOfImplies);
		}
		
		//the varX and varY are returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(varX);
		retArgs.add(varY);
		return retArgs;
	}
	
	public ArrayList<SWRLDArgument> solveIsUnique(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, ArrayList<SWRLDArgument> referredArgsX, ArrayList<SWRLDArgument> referredArgsY, Boolean operatorNot, Boolean leftSideOfImplies) throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a iterator fragment
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		//then, the source of the iterator is got
		OCLExpressionImpl source = (OCLExpressionImpl) iteratorExpImpl.getSource();
		//then, the body of the iterator is got
		OCLExpressionImpl body = (OCLExpressionImpl) iteratorExpImpl.getBody();
		
		int repeatNumber = 2;
		SWRLDArgument var0 = referredArgsX.get(0);
		
		SWRLDArgument varX = referredArgsX.get(referredArgsX.size()-1);//pega o ultimo
		SWRLDArgument varY = referredArgsY.get(referredArgsY.size()-1);//pega o ultimo
		
		//above, the iterator is solved again in order to create individuals and relations to be compared with the main individuals
		//and verify if these individuals are the same or different, according to the documentation
		//to create different individuals, the repeatNumber is used.
		
		//and a factory is created according to the source class 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source, this.m_NamedElementImpl);
		//the source is solved and the and the returned arguments from the sourceSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX2 = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, repeatNumber, leftSideOfImplies);
		SWRLDArgument varX2 = retArgsX2.get(retArgsX2.size()-1);//pega o ultimo
		SWRLDArgument var0_2 = retArgsX2.get(0);
		
		//and a factory is created according to the body class 
		this.bodyFactory = (OCLExpressionImplFactory) Factory.constructor(body, this.m_NamedElementImpl);
		//the body is solved and the and the returned arguments from the bodySolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsY2 = this.bodyFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, varX2, operatorNot, repeatNumber, leftSideOfImplies); 
		SWRLDArgument varY2 = retArgsY2.get(retArgsY2.size()-1);//pega o ultimo
		
		if(!org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) && !org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			//var0 and var0_2 are always the same
			SWRLSameIndividualAtom same0 = factory.getSWRLSameIndividualAtom((SWRLVariable)var0, (SWRLVariable)var0_2);
			//antecedent.add(same0);
			
			//and are inserted in the antecedent or in the consequent
			this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, same0);
		}
		
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(same0);
		}else{
			antecedent.add(same0);
		}
		*/
		
		if(retArgsY2.size() > 0 && (org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) || org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype))){
			varX2 = retArgsY2.get(0);
		}
		//varX and varX_2 are always differents
		SWRLDifferentIndividualsAtom diff = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varX, (SWRLVariable)varX2);
		//antecedent.add(diff);
		
		//and are inserted in the antecedent or in the consequent
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, diff);
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(diff);
		}else{
			antecedent.add(diff);
		}
		*/
		SWRLAtom atom = null;
		//now, if the stereotype is INVARIANT and this fragment rule is on the left side of the implies operator, varY and varY2 are considered the same
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) && leftSideOfImplies == false){
			SWRLSameIndividualAtom same = factory.getSWRLSameIndividualAtom((SWRLVariable)varY, (SWRLVariable)varY2);
			//antecedent.add(same);
			atom = same;
		}else{//else, varY and varY2 are considered different
			//else if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			SWRLDifferentIndividualsAtom diff2 = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varY, (SWRLVariable)varY2);
			//antecedent.add(diff2);
			atom = diff2;
		}
		//and are inserted in the antecedent or in the consequent
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, atom);
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(atom);
		}else{
			antecedent.add(atom);
		}
		*/
		return null;
	}
	
	@Override
	public Boolean isUniqueIterator(){
		//since the factory is created according to the rule fragment, the fragment is got as a iterator fragment
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		//get the iterator name
		String name = iteratorExpImpl.getName();
		
		//returns true if is isUnique
		if(name.equals("isUnique")){
			return true;
		}
		
		return false;
	}
	
	@Override
	public OWLObjectProperty getOWLObjectProperty(OCLExpressionImpl oclExpression, String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) throws Ocl2Owl_SwrlException {
		//since the factory is created according to the rule fragment, the fragment is got as a iterator fragment
		IteratorExpImpl iteratorExpImpl = (IteratorExpImpl) this.m_NamedElementImpl; 
		//then, the source of the iterator is got
		OCLExpressionImpl source = (OCLExpressionImpl) iteratorExpImpl.getSource();
		//and a factory is created according to the source class 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source, this.m_NamedElementImpl);
		
		return this.sourceFactory.getOWLObjectProperty(oclExpression, nameSpace, refParser, factory);
		
	}
}