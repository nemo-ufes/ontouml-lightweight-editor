package br.ufes.inf.nemo.ocl2swrl.factory;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.*;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.*;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.*;


public class Factory {
	Boolean isBodyExpression = false;
	
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) {
		throw new NonImplemented("solve()");
	}
	
	public ArrayList<SWRLDArgument> solveNegativeNumber(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot) {
		throw new NonImplemented("solveNegativeNumber()");
	}
	
	public static Object constructor(Object obj){
		Class<? extends Object> c = obj.getClass();
		if(c.equals(PropertyCallExpImpl.class)){
			return new PropertyCallExpImplFactory((PropertyCallExpImpl) obj);
		}else if(c.equals(OperationCallExpImpl.class)){
			return new OperationCallExpImplFactory((OperationCallExpImpl) obj);
		}else if(c.equals(VariableExpImpl.class)){
			return new VariableExpImplFactory((VariableExpImpl) obj);
		}else if(c.equals(IntegerLiteralExpImpl.class)){
			return new IntegerLiteralExpImplFactory((IntegerLiteralExpImpl) obj);
		}else if(c.equals(TypeExpImpl.class)){
			return new TypeExpImplFactory((TypeExpImpl) obj);
		}else if(c.equals(IteratorExpImpl.class)){
			return new IteratorExpImplFactory((IteratorExpImpl) obj);
		}else if(c.equals(CollectionLiteralExpImpl.class)){
			return new CollectionLiteralExpImplFactory((CollectionLiteralExpImpl) obj);
		}else if(c.equals(CollectionItemImpl.class)){
			return new CollectionItemImplFactory((CollectionItemImpl) obj);
		}else if(c.equals(BooleanLiteralExpImpl.class)){
			return new BooleanLiteralExpImplFactory((BooleanLiteralExpImpl) obj);
		}else if(c.equals(EnumLiteralExpImpl.class)){
			return new EnumLiteralExpImplFactory((EnumLiteralExpImpl) obj);
		}else{
			throw new NonSupported(c.getName());
		}
	}
		
	public Boolean isImpliesOperation(){
		return false;
	}
	
	public Boolean isNotOperation(){
		return false;
	}
	
	public Boolean isBodyExpression() {
		return isBodyExpression;
	}
	
	public void setIsBodyExpression(Boolean isBodyExpression) {
		this.isBodyExpression = isBodyExpression;
	}
	
	public Boolean isComparisonOperation(){
		return false;
	}
	
	public Boolean isArithmeticOperation(){
		return false;
	}
	
	public Boolean isKindOfOperation(){
		return false;
	}

	public Boolean isUniqueIterator(){
		return false;
	}
	
	public Boolean isIsEmptyOperation() {
		return false;
	}
	
	public Boolean isNotEmptyOperation() {
		return false;
	}
	
	public Boolean isAbsOperation() {
		return false;
	}
	
	public Boolean isIncludesOperation() {
		return false;
	}
	
	public Boolean isExcludesOperation() {
		return false;
	}
}
