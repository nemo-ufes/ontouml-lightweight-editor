package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl;
import org.eclipse.ocl.uml.impl.LetExpImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.VariableExpImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectCardinalityRestriction;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
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
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.OCL2OWL_SWRL;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.UnexpectedOperator;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.UnsupportedByReasoner;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.tags.Tag;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.util.SelectedReasoner;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.util.Util;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
 */
public class OperationCallExpImplFactory extends FeatureCallExpImplFactory {

	public OCLExpressionImplFactory argumentFactory;
	
	public OperationCallExpImplFactory(NamedElementImpl m_NamedElementImpl) throws Ocl2Owl_SwrlException{
		super(m_NamedElementImpl);
		
		//verify if the operator is unsupported and throw a new exception
		if(this.isUnsupported()){
			OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
			Operation operation = operationCallExpImpl.getReferredOperation();
			String oprName = operation.getName();
			String rule = getStrRule(this.m_NamedElementImpl);
			throw new NonSupported(oprName, rule);
		}
	}
	
	public Boolean isUnsupported(){
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		
		if(oprName == null){
			return false;
		}
		
		if(		oprName.equals("isEmpty") |
				oprName.equals("notEmpty")|
				oprName.equals("symmetricDifference")|
				oprName.equals("product")|
				oprName.equals("union")|
				//oprName.equals("or")|
				oprName.equals("xor")|
				oprName.equals("oclIsUndefined")|
				oprName.equals("allInstances")){
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a operation fragment
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName.equals("and") && !this.isImpliesOperation() && org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
			String rule = getStrRule(this.m_NamedElementImpl);
			throw new UnexpectedOperator(oprName, ctStereotype, rule);
		}
		
		//the source will be considered as on the left side of the operator implies if the actual operator is implies or if the owner is on the left side 
		Boolean sourceIsLeftSideOfImplies = false;
		if(this.isImpliesOperation() || leftSideOfImplies == true){
			sourceIsLeftSideOfImplies = true;
		}
		
		//verify if is not operator
		if(this.isNotOperation() && operatorNot == false){
			operatorNot = true;
		}
		
		//the source of the operation is got
		OCLExpressionImpl source = (OCLExpressionImpl) operationCallExpImpl.getSource();
		
		//the arguments of the operation is got
		EList<OCLExpression<Classifier>> arguments = operationCallExpImpl.getArgument();
		
		SWRLDArgument varY = null;
		ArrayList<SWRLDArgument> retArgsY = null;
		if(arguments.size()>0){
			//verify if is includes or excludes operations to determine the repetition of recurrence
			if((this.isIncludesOperation() || this.isExcludesOperation()) && !ctStereotype.equals(Tag.derive.toString())){
				repeatNumber=2;
			}
			
			//the argument of the operation is got
			OCLExpressionImpl argument = (OCLExpressionImpl) operationCallExpImpl.getArgument().get(0);
			
			if(!sourceIsLeftSideOfImplies && this.hasImpliesOperation(argument)){
				sourceIsLeftSideOfImplies = true;
			}
			
			//and a factory is created according to the argument class 
			this.argumentFactory = (OCLExpressionImplFactory) Factory.constructor(argument, this.m_NamedElementImpl);
			//the argument is solved and the and the returned arguments from the argumentSolveMethod above are returned 
			retArgsY = this.argumentFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber, leftSideOfImplies);
			if(retArgsY != null){
				varY = retArgsY.get(retArgsY.size()-1);//get the last
			}
			
			//verify if is includes or excludes operations to reset the variable
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				repeatNumber=1;
			}
		}
		
		//and a factory is created according to the source class 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source, this.m_NamedElementImpl);
		
		//do refArgAux as referredArgument, excepting cases that source is part of a LET
		SWRLDArgument refArgAux = referredArgument;
		if(isPartOfLetExp(source)){
			refArgAux = null;
		}
		
		//the source is solved and the and the returned arguments from the sourceSolveMethod above are returned 
		ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, refArgAux, operatorNot, repeatNumber, sourceIsLeftSideOfImplies);
		SWRLDArgument varX = null; 
		if(retArgsX != null){
			if(retArgsX.size()>0){
				varX = retArgsX.get(retArgsX.size()-1);//get the last
			}
		}
		

		//esse trecho foi incluído para tratar uso variáveis (regra: 70/71)
		if(source.getClass().equals(VariableExpImpl.class) && refArgAux != null){
			VariableExpImpl variable = (VariableExpImpl)source;
			if(!variable.getName().equals("self") && !this.isTempVariable(varX)){
				SWRLSameIndividualAtom sameAs = factory.getSWRLSameIndividualAtom((SWRLVariable)varX, (SWRLVariable)refArgAux);
				if(!antecedent.contains(sameAs)){
					this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, sameAs);
				}				
			}			
		}
		
		//above, specific methods are called according to the operator
		ArrayList<SWRLDArgument> retArgsZ = null;
		if(!ctStereotype.equals(Tag.cardinality.toString()) && this.isSizeOperation()){
			throw new UnexpectedOperator(operationCallExpImpl.getReferredOperation().getName(), ctStereotype, getStrRule(operationCallExpImpl));
			
		}else if(ctStereotype.equals(Tag.reflexive.toString())){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				solveReflexive(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
			
		}else if(ctStereotype.equals(Tag.irreflexive.toString())){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				solveIrreflexive(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
		}else if(ctStereotype.equals(Tag.symmetric.toString())){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				solveSymmetric(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
			
		}else if(ctStereotype.equals(Tag.asymmetric.toString())){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				solveAsymmetric(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
		}else if(ctStereotype.equals(Tag.transitive.toString())){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				solveTransitive(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
		}else if(ctStereotype.equals(Tag.subrelationof.toString())){
			if(this.isIncludesOperation() || this.isExcludesOperation()){
				solveSubRelationOf(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
		}else if(ctStereotype.equals(Tag.cardinality.toString())){
			if(this.isComparisonOperation()){
				solveCardinality(operationCallExpImpl, refParser, nameSpace, manager, factory, ontology);
			}
			
		}else if(this.isComparisonOperation()){
			retArgsZ = solveComparison(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, operatorNot, leftSideOfImplies);
		}else if(this.isImpliesOperation()){
			//do nothing
		}else if(this.isArithmeticOperation()){
			retArgsZ = solveArithmetic(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, operatorNot, leftSideOfImplies);
		}else if(this.isKindOfOperation()){
			retArgsZ = solveOCLIsKindOf(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, varY, operatorNot, leftSideOfImplies);
		}/*else if(this.isIsEmptyOperation()){
			retArgsZ = solveIsEmpty(nameSpace, manager, factory, ontology, antecedent, consequent, varX, referredArgument, operatorNot);
		}else if(this.isNotEmptyOperation()){
			retArgsZ = solveNotEmpty(nameSpace, manager, factory, ontology, antecedent, consequent, varX, referredArgument, operatorNot);
		}*/else if(this.isAbsOperation()){
			retArgsZ = solveAbs(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, leftSideOfImplies);
		}else if(this.isIncludesOperation()){
			retArgsZ = solveIncludes(ctStereotype, factory, antecedent, consequent, retArgsX, retArgsY, leftSideOfImplies);
		}else if(this.isExcludesOperation()){
			retArgsZ = solveExcludes(ctStereotype, factory, antecedent, consequent, retArgsX, retArgsY, leftSideOfImplies);
		}else{
			retArgsZ = retArgsX;
		}
		
		if(retArgsZ == null){
			retArgsZ = retArgsX;
		}
		
		if(operatorNot == true){
			operatorNot = false;
		}
		
		return retArgsZ;
	}
	
	public Boolean isPartOfLetExp(OCLExpressionImpl expression){
		NamedElementImpl test = expression;
		
		while(test != null){
			if(test.getClass().equals(LetExpImpl.class)){
				return true;
			}
			if(test.getOwner().getClass().equals(NamedElementImpl.class)){
				test = (NamedElementImpl) test.getOwner();
			}else{
				return false;
			}
			
		}
			
		return false;
	}
	
	public void solveCardinality(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		//the source of the operation is got
		//and a factory is created according to the source class 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation of the source
		OWLObjectProperty relation = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		//get the cardinality
		int cardinality = 0;
		if(operationCallExpImpl.getArgument().size()>0){
			@SuppressWarnings("rawtypes")
			OCLExpression argument = operationCallExpImpl.getArgument().get(0);

			if(argument.getClass().equals(IntegerLiteralExpImpl.class)){
				cardinality = ((IntegerLiteralExpImpl) argument).getIntegerSymbol();
			}
		}
				
		//get the operation
		String referredOperation = operationCallExpImpl.getReferredOperation().getName();
		
		if(referredOperation.equals("<")){
			cardinality -= 1;
		}else if(referredOperation.equals(">")){
			cardinality += 1;
		}
		
		//get the context class and create de cardinality restriction
		OWLClass contextClass = this.getContextClass(nameSpace, factory);
		OWLObjectCardinalityRestriction cardRest = null;
		
		switch(referredOperation){
			case "<":
			case "<=":
				cardRest = factory.getOWLObjectMaxCardinality(cardinality, relation, contextClass);
				break;
			case ">":
			case ">=":
				cardRest = factory.getOWLObjectMinCardinality(cardinality, relation, contextClass);
				break;
			case "=":
				cardRest = factory.getOWLObjectExactCardinality(cardinality, relation, contextClass);
				break;
		}
		
		//set the axiom at the subClassOf
		OWLSubClassOfAxiom subClassOf = factory.getOWLSubClassOfAxiom(contextClass, cardRest);
		manager.applyChange(new AddAxiom(ontology, subClassOf));
		
		//factory.getOWLSubClassOfAxiom(arg0, arg1)
		//OWLSymmetricObjectPropertyAxiom symmetric = factory.getOWLSymmetricObjectPropertyAxiom(relation);
		
		//apply changes in the owl manager
		//manager.applyChange(new AddAxiom(ontology, symmetric));
				
		//System.out.println();
	}
	
	public OWLClass getContextClass(String nameSpace, OWLDataFactory factory){
		//get the owner
		Element owner = this.m_NamedElementImpl.getOwner();
		Variable<Classifier, Parameter> contextVariable = null;
		while(owner!=null){
			if(owner.getClass().equals(ExpressionInOCLImpl.class)){
				contextVariable = ((ExpressionInOCLImpl) owner).getContextVariable();
				break;
			}
			
			owner = owner.getOwner();
						
		}
		
		//get the type of the context var
		Classifier classContVar = contextVariable.getType();
		
		//create a swrl variable with the self name
		String contVarName = classContVar.getName();
		String iriName = nameSpace+contVarName;
		iriName = iriName.replace(" ", "_");
		IRI iri = IRI.create(iriName);
		
		OWLClass contextClass = factory.getOWLClass(iri);
		
		return contextClass;
	}
	
	public void solveSymmetric(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation
		OWLObjectProperty relation = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		//set the relation as symmetric
		OWLSymmetricObjectPropertyAxiom symmetric = factory.getOWLSymmetricObjectPropertyAxiom(relation);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, symmetric));
				
		//System.out.println();
	}
	
	public void solveAsymmetric(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation
		OWLObjectProperty relation = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		//set the relation as asymmetric
		OWLAsymmetricObjectPropertyAxiom asymmetric = factory.getOWLAsymmetricObjectPropertyAxiom(relation);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, asymmetric));
				
		//System.out.println();
	}
	
	public void solveReflexive(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation
		OWLObjectProperty relation = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		//set the relation as reflexive
		OWLReflexiveObjectPropertyAxiom reflexive = factory.getOWLReflexiveObjectPropertyAxiom(relation);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, reflexive));
				
		//System.out.println();
	}
	
	public void solveTransitive(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation
		OWLObjectProperty relation = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		//set the relation as transition
		OWLTransitiveObjectPropertyAxiom transitive = factory.getOWLTransitiveObjectPropertyAxiom(relation);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, transitive));
		
		//System.out.println();
	}
	
	public void solveSubRelationOf(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation1
		OWLObjectProperty relation1 = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		if(operationCallExpImpl.getArgument().size()>0){
			this.argumentFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getArgument().get(0), this.m_NamedElementImpl);
		}
		
		//get the relation2
		OWLObjectProperty relation2 = this.argumentFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		/*
		System.out.println(relation1.getIRI().getFragment());
		System.out.println(relation2.getIRI().getFragment());
		System.out.println();
		*/
		//set relation2 as subRelationOf relation1
		OWLSubObjectPropertyOfAxiom subRelationOf = factory.getOWLSubObjectPropertyOfAxiom(relation2, relation1);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, subRelationOf));
				
		//System.out.println();
	}
	
	@Override
	public OWLObjectProperty getOWLObjectProperty(OCLExpressionImpl oclExpression, String nameSpace, OntoUMLParser refParser, OWLDataFactory factory)  throws Ocl2Owl_SwrlException{
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		return this.sourceFactory.getOWLObjectProperty(oclExpression, nameSpace, refParser, factory);
	}
	
	public void solveIrreflexive(OperationCallExpImpl operationCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException{
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(operationCallExpImpl.getSource(), this.m_NamedElementImpl);
		
		//get the relation
		OWLObjectProperty relation = this.sourceFactory.getOWLObjectProperty(operationCallExpImpl, nameSpace, refParser, factory);
		
		//set the relations as irreflexive
		OWLIrreflexiveObjectPropertyAxiom irreflexive = factory.getOWLIrreflexiveObjectPropertyAxiom(relation);
		
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, irreflexive));
				
		//System.out.println();
	}
	
	public ArrayList<SWRLDArgument> solveIncludes(String ctStereotype, OWLDataFactory factory, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, ArrayList<SWRLDArgument> referredArgsX, ArrayList<SWRLDArgument> referredArgsY, Boolean leftSideOfImplies) {
		SWRLDArgument varX1 = null;
		if((org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) || org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype))){
			varX1 = referredArgsX.get(0);
		}else{
			varX1 = referredArgsX.get(referredArgsX.size()-1);
		}
		
		SWRLDArgument varX2 = null;
		if(referredArgsX.size()>1){
			varX2 = referredArgsX.get(1);
		}
		
		SWRLDArgument varY1 = null;
		if((org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) || org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype))){
			varY1 = referredArgsY.get(0);
		}else{
			varY1 = referredArgsY.get(referredArgsY.size()-1);
		}
		SWRLDArgument varY2 = null;
		if(referredArgsY.size()>1){
			varY2 = referredArgsY.get(1);
		}		
		
		SWRLAtom atom = null;
		//in invariants case that the fragment are in the right side, varX1 and varY1 are differents
		//else, they are the same
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) && leftSideOfImplies == false){
			SWRLDifferentIndividualsAtom diff = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varX1, (SWRLVariable)varY1);
			//antecedent.add(diff);
			atom = diff;
		}else{// if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			SWRLSameIndividualAtom same1 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX1, (SWRLVariable)varY1);
			//antecedent.add(same1);
			atom = same1;
		}

		if(!Factory.variableExistsInAtom(antecedent, (SWRLVariable) varX1) && leftSideOfImplies == false){
			Factory.replaceNonexistentVariableInAtom(factory, consequent, (SWRLVariable) varX1, (SWRLVariable) varY1); 
		}else{
			this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, atom);
		}		
		
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(atom);
		}else{
			antecedent.add(atom);
		}
		*/
		//varX2 and varY2 are always the same
		if(varY2 != null && varX2 != null){
			SWRLSameIndividualAtom same2 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX2, (SWRLVariable)varY2);
			//antecedent.add(same2);
			this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, same2);
		}
		
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(same2);
		}else{
			antecedent.add(same2);
		}
		*/
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveExcludes(String ctStereotype, OWLDataFactory factory, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, ArrayList<SWRLDArgument> referredArgsX, ArrayList<SWRLDArgument> referredArgsY, Boolean leftSideOfImplies) {
		SWRLDArgument varX1 = referredArgsX.get(0);
		SWRLDArgument varX2 = referredArgsX.get(1);
		
		SWRLDArgument varY1 = referredArgsY.get(0);
		SWRLDArgument varY2 = referredArgsY.get(1);
		
		//varX1 and varY1 are always the same
		SWRLSameIndividualAtom same1 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX1, (SWRLVariable)varY1);
		//antecedent.add(same1);
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, same1);
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(same1);
		}else{
			antecedent.add(same1);
		}
		*/
		SWRLAtom atom = null;
		//in invariants case that the fragment are in the right side, varX2 and varY2 are differents
		//else, they are the same
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) && leftSideOfImplies == false){
			SWRLSameIndividualAtom same2 = factory.getSWRLSameIndividualAtom((SWRLVariable)varX2, (SWRLVariable)varY2);
			//antecedent.add(same2);
			atom = same2;
		}else{// if(org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			SWRLDifferentIndividualsAtom diff = factory.getSWRLDifferentIndividualsAtom((SWRLVariable)varX2, (SWRLVariable)varY2);
			//antecedent.add(diff);
			atom = diff;
		}
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
	
	public ArrayList<SWRLDArgument> solveAbs(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, Boolean operatorNot, Boolean leftSideOfImplies) {
		//generate the variable name of the absolute value
		String varName = "abs.";
		if(referredArgX.getClass().equals(SWRLLiteralArgumentImpl.class)){
			OWLLiteral literal = ((SWRLLiteralArgumentImpl)referredArgX).getLiteral();
			varName += literal.getLiteral();
		}else{
			IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
			varName += iri.getFragment();
		}
		
		SWRLVariable varZ = factory.getSWRLVariable(IRI.create(nameSpace+varName));
		
		//create the builtin abs
		List<SWRLDArgument> args = new ArrayList<SWRLDArgument>();
		args.add(referredArgX);
		args.add(varZ);
		SWRLBuiltInAtom builtIn = factory.getSWRLBuiltInAtom(IRI.create("abs"), args);
		
		//antecedent.add(builtIn);
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, builtIn);
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(builtIn);
		}else{
			antecedent.add(builtIn);
		}
		*/
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(varZ);
		
		return retArgs;
	}
	/*	
	public ArrayList<SWRLDArgument> solveIsEmpty(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean operatorNot, Boolean leftSideOfImplies) {
		IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
		OWLClass owlClass = factory.getOWLClass(iri);
		SWRLClassAtom atom = factory.getSWRLClassAtom(owlClass, (SWRLIArgument) referredArgY);
		//antecedent.add(atom);
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, atom);
		
		//if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
		//	consequent.add(atom);
		//}else{
		//	antecedent.add(atom);
		}
		
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveNotEmpty(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean operatorNot, Boolean leftSideOfImplies) {
		IRI iri = ((SWRLVariableImpl) referredArgX).getIRI();
		SWRLClassAtom atom = null;
		OWLClass owlClass = factory.getOWLClass(iri);
		OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
		atom = factory.getSWRLClassAtom(complementOf, (SWRLIArgument) referredArgY);
		//antecedent.add(atom);
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, atom);
		
		//if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
		//	consequent.add(atom);
		//}else{
		//	antecedent.add(atom);
		//}
		
		return null;
	}
	*/
	public ArrayList<SWRLDArgument> solveOCLIsKindOf(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean operatorNot, Boolean leftSideOfImplies) {
		//OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		//String referredOperationName = operationCallExpImpl.getReferredOperation().getName();

		IRI iri = ((SWRLVariableImpl) referredArgY).getIRI();
		SWRLClassAtom atom = null;
		OWLClass owlClass = factory.getOWLClass(iri);
		
		//complement of is created only when the fragment is INVARIANT and is in the left side
		Boolean creationOfComplementOf = false;
		if(operatorNot == false && org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) && leftSideOfImplies == false){
			creationOfComplementOf = true;
		}else if (operatorNot == true && org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			creationOfComplementOf = true;
		}
		/*
		switch (referredOperationName) {
		case "not":
			//OCLExpression<Classifier> assoc = ((CollectionItemImpl)((CollectionLiteralExpImpl)((IteratorExpImpl)bodyExpression.getOwner()).getSource()).getPart().get(0)).getItem();
			
			//Classifier referClass = ((TypeExpImpl) ((OperationCallExpImpl) source).getArgument().get(0)).getReferredType();
			//solveVariable(assoc, false, antecedent, consequent, (ClassImpl) referClass); 
			break;
		case "oclIsKindOf":
		case "oclIsTypeOf":
		case "oclAsType":
		*/
			//if(((oclConsequentShouldBeNegated && !expressionIsNegated) || (!oclConsequentShouldBeNegated && expressionIsNegated)) && org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
			if(creationOfComplementOf){	
				OWLObjectComplementOf complementOf = factory.getOWLObjectComplementOf(owlClass);
				
				atom = factory.getSWRLClassAtom(complementOf, (SWRLIArgument) referredArgX);
			}else{
				atom = factory.getSWRLClassAtom(owlClass, (SWRLIArgument) referredArgX);
			}			
		/*
			break;
		}
		*/
		//antecedent.add(atom);
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
	
	public ArrayList<SWRLDArgument> solveComparison(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean operatorNot, Boolean leftSideOfImplies) {
		Boolean hasToBeInverted = false;
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) && leftSideOfImplies == false){
			hasToBeInverted = true;
		}
		
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
			if(hasToBeInverted){
				iriName = "lessThanOrEqual";
			}else{
				iriName = "greaterThan";
			}			
			break;
		case ">=":
			if(hasToBeInverted){
				iriName = "lessThan";
			}else{
				iriName = "greaterThanOrEqual";
			}
			break;
		case "<":
			if(hasToBeInverted){
				iriName = "greaterThanOrEqual";
			}else{
				iriName = "lessThan";
			}
			break;
		case "<=":
			if(hasToBeInverted){
				iriName = "greaterThan";
			}else{
				iriName = "lessThanOrEqual";
			}
			break;	
		case "=":
			if(hasToBeInverted){
				iriName = "notEqual";
			}else{
				iriName = "equal";
			}
			break;
		case "<>":
			if(hasToBeInverted){
				iriName = "equal";
			}else{
				iriName = "notEqual";
			}
			break;
		}
		SWRLBuiltInAtom builtIn = factory.getSWRLBuiltInAtom(IRI.create(iriName), args);
		//the built-in is added to the antecedent atom
		//antecedent.add(builtIn);
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, builtIn);
		/*
		if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
			consequent.add(builtIn);
		}else{
			antecedent.add(builtIn);
		}
		*/
		return null;
	}
	
	public ArrayList<SWRLDArgument> solveArithmetic(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgX, SWRLDArgument referredArgY, Boolean operatorNot, Boolean leftSideOfImplies)  throws Ocl2Owl_SwrlException{
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl;
		String referredOperationName = operationCallExpImpl.getReferredOperation().getName();
		SWRLDArgument varZ;
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		
		if(referredArgY == null && referredOperationName.equals("-")){
			if(referredArgX.getClass().equals(SWRLLiteralArgumentImpl.class)){
				OCLExpression<?> literal = ((OperationCallExpImpl)m_NamedElementImpl).getSource();
				NumericLiteralExpImplFactory numericFactory = (NumericLiteralExpImplFactory) Factory.constructor(literal, this.m_NamedElementImpl);
				
				retArgs = numericFactory.solveNegativeNumber(nameSpace, manager, factory, ontology, antecedent, consequent, referredArgX, false);
				
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
			//antecedent.add(builtIn);
			this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, builtIn);
			/*
			if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == false){
				consequent.add(builtIn);
			}else{
				antecedent.add(builtIn);
			}
			*/
		}
		
		retArgs.add(varZ);
		
		return retArgs;
	}
	
	@Override
	public Boolean isComparisonOperation() throws UnsupportedByReasoner{
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
				if(OCL2OWL_SWRL.selectedReasoner.equals(SelectedReasoner.Hermit)){
					throw new UnsupportedByReasoner(OCL2OWL_SWRL.selectedReasoner.toString(), oprName);
				}
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isArithmeticOperation() throws UnsupportedByReasoner{
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
				if(OCL2OWL_SWRL.selectedReasoner.equals(SelectedReasoner.Hermit)){
					throw new UnsupportedByReasoner(OCL2OWL_SWRL.selectedReasoner.toString(), oprName);
				}
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
					oprName.equals("oclIsTypeOf") //||
					//oprName.equals("oclAsType")
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

	public Boolean isSizeOperation() {
		OperationCallExpImpl operationCallExpImpl = (OperationCallExpImpl) this.m_NamedElementImpl; 
		Operation operation = operationCallExpImpl.getReferredOperation();
		String oprName = operation.getName();
		if(oprName != null){
			if(		oprName.equals("size")
				){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean isNotOperation() {
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
					oprName.equals("includesAll")//	||
					//oprName.equals("intersection")	||
					//oprName.equals("including")
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
					oprName.equals("excludesAll")//	||
					//oprName.equals("excluding")
				){
				return true;
			}
		}
		
		return false;
	}
}