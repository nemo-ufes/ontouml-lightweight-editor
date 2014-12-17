package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.VariableExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.util.Util;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class VariableExpImplFactory extends OCLExpressionImplFactory {

	public VariableExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		//since the factory is created according to the rule fragment, the fragment is got as a variable exp fragment
		VariableExpImpl variableExpImpl = (VariableExpImpl) this.m_NamedElementImpl;
		
		Object type;
		if(variableExpImpl.getName().equals("self")){
			type = variableExpImpl.getReferredVariable().getType();
			//a linha abaixo foi adicionada para corrigir para corrigir a geracao do nome de variaveis da regra DR69 
			referredArgument = null;
		}else{
			type = variableExpImpl;
			referredArgument = null;
		}
		
		//a variable name is generated from the variable exp name and the referred argument
		String varName = Util.generateVarName(type, referredArgument);
		//if the generated variable name returns null or a blank name, we ge the variableExpImpl name
		if(varName == null){
			varName = variableExpImpl.getName();
		}
		if(varName.equals("")){
			varName = variableExpImpl.getName();
		}
		//if the repeat number is greater than 1, the number is added to the end of variable name
		//the repeat number is used in cases that is necessary to compare more than one individual of the same type and names like ?individual and ?individual2 are created
		if(repeatNumber > 1){
			varName += repeatNumber;
		}
		
		//and a swrl variable is created from the varName
		SWRLVariable var = factory.getSWRLVariable(IRI.create(nameSpace+varName));
		
		//the variable is added to a variable array and returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(var);
		
		return retArgs;
	}
}