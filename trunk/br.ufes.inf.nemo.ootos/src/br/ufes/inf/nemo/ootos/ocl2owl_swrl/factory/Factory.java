package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.ocl.uml.impl.BooleanLiteralExpImpl;
import org.eclipse.ocl.uml.impl.CollectionItemImpl;
import org.eclipse.ocl.uml.impl.CollectionLiteralExpImpl;
import org.eclipse.ocl.uml.impl.EnumLiteralExpImpl;
import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl;
import org.eclipse.ocl.uml.impl.IteratorExpImpl;
import org.eclipse.ocl.uml.impl.LetExpImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.ocl.uml.impl.TypeExpImpl;
import org.eclipse.ocl.uml.impl.VariableExpImpl;
import org.eclipse.ocl.uml.impl.VariableImpl;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLArgument;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLVariable;

import uk.ac.manchester.cs.owl.owlapi.SWRLObjectPropertyAtomImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.NonImplemented;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.UnsupportedByReasoner;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.BooleanLiteralExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.CollectionItemImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.CollectionLiteralExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.EnumLiteralExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.IntegerLiteralExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.IteratorExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.LetExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.OperationCallExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.PropertyCallExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.TypeExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.VariableExpImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl.VariableImplFactory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.tags.Tag;


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
			//the atom can be inserted in consequent if does not exist in antecedent
			//the function atomExistAtoms was created 
			if(!this.atomExistAtoms(antecedent, atom, false)){
				consequent.add(atom);
			}			
		}else{
			this.atomExistAtoms(consequent, atom, true);
			antecedent.add(atom);
		}
	}
	
	public Boolean hasImpliesOperation(OCLExpressionImpl expr){
		String strExpr = expr.toString();
		if(strExpr.contains("implies")){
			return true;
		}
		return false;
	}
	
	public Boolean atomExistAtoms(Set<SWRLAtom> atoms, SWRLAtom atom, Boolean removeIt){
		@SuppressWarnings("rawtypes")
		Class atomClass = atom.getClass();
		Collection<SWRLArgument> atomArgs = atom.getAllArguments();
		int qtAtomArgs = atomArgs.size();
		if(qtAtomArgs > 1){
			Collections.sort((List<SWRLArgument>) atomArgs);
		}
		for (SWRLAtom auxAtom : atoms) {
			if(auxAtom.getClass().equals(atomClass)){
				Collection<SWRLArgument> auxAtomArgs = auxAtom.getAllArguments();
				if(auxAtomArgs.size() == qtAtomArgs){
					if(qtAtomArgs > 1){
						Collections.sort((List<SWRLArgument>) auxAtomArgs);
					}
					Boolean itEqual = true;
					for(int i = 0; i < qtAtomArgs; i++){
						Object[] list1 = atomArgs.toArray();
						Object[] list2 = auxAtomArgs.toArray();
						
						IRI iri1 = ((SWRLVariableImpl)list1[i]).getIRI();
						IRI iri2 = ((SWRLVariableImpl)list2[i]).getIRI();
						//IRI iri1 = ((SWRLVariableImpl)(((ArrayList<SWRLArgument>)atomArgs).get(i))).getIRI();
						//IRI iri2 = ((SWRLVariableImpl)(((ArrayList<SWRLArgument>)auxAtomArgs).get(i))).getIRI();
						if(!iri1.getFragment().equals(iri2.getFragment())){
							itEqual = false;
						}
					}
					
					if(itEqual){
						if(removeIt){
							atoms.remove(atom);
						}
						
						return true;
					}					
					//verificar se todos os elementos sao iguais
					//eliminar elemento se removeIt == true
					//se forem, retornar true
				}				
			}
		}
		return false;
	}
	
	public static Boolean variableExistsInAtom(Set<SWRLAtom> swrlAtoms, SWRLVariable variable){
		for (SWRLAtom atom : swrlAtoms) {
			Collection<SWRLArgument> swrlArguments = atom.getAllArguments();
			for (SWRLArgument argument : swrlArguments) {
				if(argument.equals(variable)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static String allConsequentVariablesExistsInAntecedent(Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		String variableName = "";
		for (SWRLAtom cons : consequent) {
			Collection<SWRLArgument> swrlArguments = cons.getAllArguments();
			for (SWRLArgument swrlArgument : swrlArguments) {
				if(!variableExistsInAtom(antecedent, (SWRLVariable) swrlArgument)){
					SWRLVariableImpl var = (SWRLVariableImpl) swrlArgument;
					IRI iri = var.getIRI();
					variableName = iri.getFragment();
					return variableName;
				}
			}
		}
		
		return variableName;
	}
	
	public static void replaceNonexistentVariableInAtom(OWLDataFactory factory, Set<SWRLAtom> swrlAtoms, SWRLVariable oldVariable, SWRLVariable newVariable){
		//int i = 0;
		//Set<SWRLAtom> swrlAtomsArray = new HashSet<SWRLAtom>();
		
		//for (int atomIndex = 0; atomIndex < swrlAtomsArray.size(); atomIndex++) {
		for (SWRLAtom swrlAtom : swrlAtoms) {
			ArrayList<SWRLArgument> swrlArguments = (ArrayList<SWRLArgument>) swrlAtom.getAllArguments();
			Boolean newAtomCreated = false;
			SWRLAtom atomNew = null;
			for (int argIndex = 0; argIndex < swrlArguments.size(); argIndex++) {
				if(swrlArguments.get(argIndex).equals(oldVariable)){
					SWRLVariable var1 = null;
					SWRLVariable var2 = null;
					if(swrlAtom.getClass().equals(SWRLObjectPropertyAtomImpl.class)){
						if(argIndex == 0){
							var1 = newVariable;
							var2 = (SWRLVariable) swrlArguments.get(1);
						}else{
							var1 = (SWRLVariable) swrlArguments.get(0);
							var2 = newVariable;
						}
						atomNew = factory.getSWRLObjectPropertyAtom((OWLObjectPropertyExpression) swrlAtom.getPredicate(), var1, var2);
						newAtomCreated = true;
					}
					//swrlAtomsArray.remove(atomIndex);
					//swrlAtomsArray.add(atomNew);
					
					//return;
				}else if(!newAtomCreated){
					newAtomCreated = false;
					
				}
			}
			if(newAtomCreated){
				
				swrlAtoms.remove(swrlAtom);
				swrlAtoms.add(atomNew);
			}else{
				//swrlAtomsArray.add(swrlAtom);
			}
			newAtomCreated = false;
			//atomIndex++;
		}
		//return swrlAtomsArray;
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
		//Element owner = m_NamedElementImpl.getOwner();
		Element owner = m_NamedElementImpl;
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
	 * @throws UnsupportedByReasoner 
	 * 
	 */
	public Boolean isComparisonOperation() throws UnsupportedByReasoner{
		return false;
	}
	
	/**
	 * This function verify if is an arithmetic operation
	 * @throws UnsupportedByReasoner 
	 * 
	 */
	public Boolean isArithmeticOperation() throws UnsupportedByReasoner{
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
	
	public Boolean isTempVariable(SWRLDArgument argument){
		if(argument != null){
			if(argument.getClass().equals(SWRLVariableImpl.class)){
				SWRLVariableImpl variable = (SWRLVariableImpl)argument;
				
				try {
					String varName = variable.getIRI().getFragment();
//					String teste = varName.substring(4);
//					int t2 = Integer.parseInt(teste);
					
					if(varName.startsWith("temp")){
						return true;
					}
				} catch (Exception e) {
					//apenas um teste para saber se o restante do nome da variavel eh um inteiro
				}
			}
		}
		return false;
	}
}
