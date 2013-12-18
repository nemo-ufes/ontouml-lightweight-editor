package br.ufes.inf.nemo.ocl2owl_swrl.factory;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.ocl.uml.impl.*;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2owl_swrl.exceptions.*;
import br.ufes.inf.nemo.ocl2owl_swrl.factory.ocl.uml.impl.*;
import br.ufes.inf.nemo.ocl2owl_swrl.tags.Tag;


public class Factory {
	Boolean isBodyExpression = false;
	String errors = "";
	
	//this method has to solve the rule an is overridden according to the rule fragment type 
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws Ocl2Owl_SwrlException{
		return null;
		//throw new NonImplemented("solve()");
	}
	
	/**
	 * This function verify if the atom has to be inserted in the antecedent or consequent considering their stereotype and if it's at the left side of the implies
	 * 
	 * @param ctStereotype
	 * @param leftSideOfImplies
	 */
	public Boolean hasToBeInsertedInConsequent(String ctStereotype, Boolean leftSideOfImplies){
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype) || org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(ctStereotype)){
			return false;//invariants are inserted on antecedents, excepting when tags are used are override the ctStereotype
		}else if(ctStereotype.equals(Tag.derive.toString()) && leftSideOfImplies == true){
			return false;//atoms in rules tagged as Derive are inserted on antecedents if they are on the left side of the operator Implies 
		}
		return true;
	}
	
	/**
	 * This function returns if a tag is used and insert the atom at the antecedent or consequent 
	 */
	public void insertOnAntecedentOrConsequent(String ctStereotype, Boolean leftSideOfImplies, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLAtom atom){
		if(ctStereotype.equals(Tag.reflexive.toString()) || 
				ctStereotype.equals(Tag.irreflexive.toString()) || 
				ctStereotype.equals(Tag.symmetric.toString()) || 
				ctStereotype.equals(Tag.asymmetric.toString()) || 
				ctStereotype.equals(Tag.transitive.toString()) || 
				ctStereotype.equals(Tag.subrelationof.toString()) || 
				ctStereotype.equals(Tag.cardinality.toString())){
			return;
		}
		
		if(hasToBeInsertedInConsequent(ctStereotype, leftSideOfImplies)){
			consequent.add(atom);
		}else{
			antecedent.add(atom);
		}
	}
	
	
	/**
	 * This function creates a new factory according to the type of the rule fragment
	 * 
	 * @param obj - contains the rule fragment
	 * @param m_NamedElementImpl - contains the rule
	 */
	public static Factory constructor(Object obj, NamedElementImpl m_NamedElementImpl) throws Ocl2Owl_SwrlException{
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
		}else if(c.equals(LetExpImpl.class)){
			return new LetExpImplFactory((LetExpImpl) obj);
		}else if(c.equals(VariableImpl.class)){
			return new VariableImplFactory((VariableImpl) obj);
		}else{
			String rule = Factory.getStrRule(m_NamedElementImpl);
			throw new NonSupported(c.getName(), rule);
		}
	}
	
	/**
	 * This function returns the string of the rule
	 * 
	 * @param m_NamedElementImpl - contains the rule fragment
	 */
	public static String getStrRule(NamedElementImpl m_NamedElementImpl) throws Ocl2Owl_SwrlException {
		if(m_NamedElementImpl == null){
			return "";
		}
		Element owner = m_NamedElementImpl.getOwner();
		while(!owner.getClass().equals(ExpressionInOCLImpl.class)){
			owner = owner.getOwner();
		}
		String context = ((ExpressionInOCLImpl) owner).getContextVariable().toString();
		
		String body = ((ExpressionInOCLImpl) owner).getBodyExpression().toString();
				
		return context + "\n" + body;
	}
	
	//above, all methods to be override
	
	/**
	 * This function solve negative numbers
	 * 
	 */
	public ArrayList<SWRLDArgument> solveNegativeNumber(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot)  throws Ocl2Owl_SwrlException {
		return null;
		//throw new NonImplemented("solveNegativeNumber()");
	}
	
	/**
	 * This function verify if is an implies operation
	 * 
	 */
	public Boolean isImpliesOperation(){
		return false;
	}
	
	/**
	 * This function verify if is a not operation
	 * 
	 */
	public Boolean isNotOperation(){
		return false;
	}
	
	/**
	 * This function verify if is a comparison operation
	 * 
	 */
	public Boolean isComparisonOperation(){
		return false;
	}
	
	/**
	 * This function verify if is an arithmetic operation
	 * 
	 */
	public Boolean isArithmeticOperation(){
		return false;
	}
	
	/**
	 * This function verify if is a kind of operation
	 * 
	 */
	public Boolean isKindOfOperation(){
		return false;
	}

	/**
	 * This function verify if is an unique iterator
	 * 
	 */
	public Boolean isUniqueIterator(){
		return false;
	}
	
	/**
	 * This function verify if is an is empty operation
	 * 
	 */
	public Boolean isIsEmptyOperation() {
		return false;
	}
	
	/**
	 * This function verify if is a not empty operation
	 * 
	 */
	public Boolean isNotEmptyOperation() {
		return false;
	}
	
	/**
	 * This function verify if is an abs operation
	 * 
	 */
	public Boolean isAbsOperation() {
		return false;
	}
	
	/**
	 * This function verify if is an includes operation
	 * 
	 */
	public Boolean isIncludesOperation() {
		return false;
	}
	
	/**
	 * This function verify if is an excludes operation
	 * 
	 */
	public Boolean isExcludesOperation() {
		return false;
	}
	
	/**
	 * This function returns the OWLObjectProperty based in a namespace
	 * 
	 */
	public OWLObjectProperty getOWLObjectProperty(OCLExpressionImpl oclExpression, String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) throws Ocl2Owl_SwrlException{
		String ruleString = Factory.getStrRule(oclExpression);
		throw new NonImplemented("getOWLObjectProperty", ruleString);
	}
}
