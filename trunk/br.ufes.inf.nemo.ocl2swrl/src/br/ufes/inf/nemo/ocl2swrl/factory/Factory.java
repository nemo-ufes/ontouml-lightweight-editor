package br.ufes.inf.nemo.ocl2swrl.factory;

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
import br.ufes.inf.nemo.ocl2swrl.exceptions.*;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.*;
import br.ufes.inf.nemo.ocl2swrl.tags.Tag;


public class Factory {
	Boolean isBodyExpression = false;
	String errors = "";
	
	public OWLObjectProperty getOWLObjectProperty(String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) throws NonImplemented, NonSupported{
		return null;
	}
	
	public void insertOnAntecedentOrConsequent(String ctStereotype, Boolean leftSideOfImplies, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLAtom atom){
		if(ctStereotype.equals(Tag.Reflexive.toString()) || 
				ctStereotype.equals(Tag.Irreflexive.toString()) || 
				ctStereotype.equals(Tag.Symmetric.toString()) || 
				ctStereotype.equals(Tag.Asymmetric.toString()) || 
				ctStereotype.equals(Tag.Transitive.toString()) || 
				ctStereotype.equals(Tag.SubRelationOf.toString()) || 
				ctStereotype.equals(Tag.Cardinality.toString())){
			return;
		}
		
		if(hasToBeInsertedInConsequent(ctStereotype, leftSideOfImplies)){
			consequent.add(atom);
		}else{
			antecedent.add(atom);
		}
	}
	
	public Boolean hasToBeInsertedInConsequent(String ctStereotype, Boolean leftSideOfImplies){
		if(org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(ctStereotype)){
			return false;//invariants are inserted on antecedents, excepting when tags are used are override the ctStereotype
		}else if(ctStereotype.equals(Tag.Derive.toString()) && leftSideOfImplies == true){
			return false;//atoms in rules tagged as Derive are inserted on antecedents if they are on the left side of the operator Implies 
		}
		return true;
	}
	
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies) throws NonImplemented, NonSupported{
		return null;
		//throw new NonImplemented("solve()");
	}
	
	public ArrayList<SWRLDArgument> solveNegativeNumber(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot)  throws NonImplemented, NonSupported {
		return null;
		//throw new NonImplemented("solveNegativeNumber()");
	}
	
	public static Factory constructor(Object obj, NamedElementImpl m_NamedElementImpl) throws NonImplemented, NonSupported{
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
	
	public static String getStrRule(NamedElementImpl m_NamedElementImpl) throws NonImplemented, NonSupported {
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
}
