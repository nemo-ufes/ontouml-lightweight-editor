/**
 */
package br.ufes.inf.nemo.z3py.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufes.inf.nemo.z3py.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Z3pyFactoryImpl extends EFactoryImpl implements Z3pyFactory {
	
	public enum LogicalBinaryExpressionTypes {IMPLICATION, DISJUNCTION, CONJUNCTION, BIIMPLICATION, EXCLUSIVEDISJUNCTION};
	private int constId=1;
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Z3pyFactory init() {
		try {
			Z3pyFactory theZ3pyFactory = (Z3pyFactory)EPackage.Registry.INSTANCE.getEFactory("http://z3py/1.0"); 
			if (theZ3pyFactory != null) {
				return theZ3pyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Z3pyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Z3pyFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Z3pyPackage.FUNCTION_CALL: return createFunctionCall();
			case Z3pyPackage.UNIVERSAL_QUANTIFICATION: return createUniversalQuantification();
			case Z3pyPackage.EXISTENTIAL_QUANTIFICATION: return createExistentialQuantification();
			case Z3pyPackage.CONJUNCTION: return createConjunction();
			case Z3pyPackage.DISJUNCTION: return createDisjunction();
			case Z3pyPackage.EXCLUSIVE_DISJUNCTION: return createExclusiveDisjunction();
			case Z3pyPackage.LOGICAL_NEGATION: return createLogicalNegation();
			case Z3pyPackage.IMPLICATION: return createImplication();
			case Z3pyPackage.BI_IMPLICATION: return createBiImplication();
			case Z3pyPackage.BOOLEAN_FUNCTION_DEFINITION: return createBooleanFunctionDefinition();
			case Z3pyPackage.INT_CONSTANT: return createIntConstant();
			case Z3pyPackage.ONTO_UMLZ3_SYSTEM: return createOntoUMLZ3System();
			case Z3pyPackage.EQUALITY: return createEquality();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionCall createFunctionCall() {
		FunctionCallImpl functionCall = new FunctionCallImpl();
		return functionCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniversalQuantification createUniversalQuantification() {
		UniversalQuantificationImpl universalQuantification = new UniversalQuantificationImpl();
		return universalQuantification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExistentialQuantification createExistentialQuantification() {
		ExistentialQuantificationImpl existentialQuantification = new ExistentialQuantificationImpl();
		return existentialQuantification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conjunction createConjunction() {
		ConjunctionImpl conjunction = new ConjunctionImpl();
		return conjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Disjunction createDisjunction() {
		DisjunctionImpl disjunction = new DisjunctionImpl();
		return disjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExclusiveDisjunction createExclusiveDisjunction() {
		ExclusiveDisjunctionImpl exclusiveDisjunction = new ExclusiveDisjunctionImpl();
		return exclusiveDisjunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalNegation createLogicalNegation() {
		LogicalNegationImpl logicalNegation = new LogicalNegationImpl();
		return logicalNegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implication createImplication() {
		ImplicationImpl implication = new ImplicationImpl();
		return implication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BiImplication createBiImplication() {
		BiImplicationImpl biImplication = new BiImplicationImpl();
		return biImplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanFunctionDefinition createBooleanFunctionDefinition() {
		BooleanFunctionDefinitionImpl booleanFunctionDefinition = new BooleanFunctionDefinitionImpl();
		return booleanFunctionDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntConstant createIntConstant() {
		IntConstantImpl intConstant = new IntConstantImpl();
		return intConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OntoUMLZ3System createOntoUMLZ3System() {
		OntoUMLZ3SystemImpl ontoUMLZ3System = new OntoUMLZ3SystemImpl();
		return ontoUMLZ3System;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Equality createEquality() {
		EqualityImpl equality = new EqualityImpl();
		return equality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Z3pyPackage getZ3pyPackage() {
		return (Z3pyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Z3pyPackage getPackage() {
		return Z3pyPackage.eINSTANCE;
	}
	
	public BooleanFunctionDefinition createFunction(String name, int numberOfArguments){
		BooleanFunctionDefinition newFunction = this.createBooleanFunctionDefinition();
		newFunction.setName(name);
		newFunction.setNumberOfArguments(numberOfArguments);
		return newFunction;
	}
	
	public IntConstant createConstant(){
		IntConstant newConst = this.createIntConstant();
		newConst.setName("C" + constId);
		constId++;		
		return newConst;		
	}
	
	public Quantification createQuantification(boolean isUniversal, Expression exp, Set<IntConstant> qtf, String comments){
		Quantification newFormula;
		if (isUniversal) 
			newFormula = this.createUniversalQuantification();
		else
			newFormula = this.createExistentialQuantification();
		newFormula.getQuantifiesOver().addAll(qtf);
		newFormula.setExpression(exp);
		newFormula.setComments(comments);
		return newFormula;
	}
	
	public Quantification createFormula(boolean isUniversal, Expression exp, String comments){
		return createQuantification(isUniversal, exp, getExpressionConstants(exp) , comments);
	}
	
	
	//Retorna as constantes utilizadas em uma expressão
	private Set<IntConstant> getExpressionConstants (Expression exp){
		Set<IntConstant> c = new HashSet<IntConstant>();
		if (exp instanceof FunctionCall)
			c.addAll(((FunctionCall) exp).getArguments());
		else if (exp instanceof LogicalBinaryExpression){
			c.addAll(getExpressionConstants(((LogicalBinaryExpression) exp).getOperand1()));
			c.addAll(getExpressionConstants(((LogicalBinaryExpression) exp).getOperand2()));
		}else if (exp instanceof LogicalNegation)
			c.addAll(getExpressionConstants(((LogicalNegation) exp).getOperand()));
		else if (exp instanceof Quantification){
			c.addAll(getExpressionConstants(((Quantification) exp).getExpression()));
			//Removo as variáveis já quantificadas por esse quantificador. Assim evito que elas sejam repassadas para um quantificador mais externo
			//Isso me permitira fazer formulas como ∀x,y (recursiveNext(x,y) ↔ next(x,y) ∨ ∃z (next(z,y) ∧ recursiveNext(x,z))) ou
			//∀x, ∃z (f(x,z))
			c.removeAll(((Quantification) exp).getQuantifiesOver());	
		}
		return c;
	}
	
	public FunctionCall createFunctionCall(BooleanFunctionDefinition called, List<IntConstant> arguments){
		FunctionCall newFunction = null;
		if (called.getNumberOfArguments()==arguments.size()){
			newFunction = this.createFunctionCall();
			newFunction.setCalledFunction(called);
/*			int i=0;
			for(IntConstant c: arguments){
				newFunction.getArguments().add(i,c);
				i++;
			}*/
			newFunction.getArguments().addAll(arguments);
		}
		return newFunction;
	}
	
	public LogicalBinaryExpression createBinaryExpression(Expression op1, Expression op2, LogicalBinaryExpressionTypes type){
		LogicalBinaryExpression newExpression=null;
		switch (type){
		case CONJUNCTION:
			newExpression = this.createConjunction();
			break;
		case DISJUNCTION:
			newExpression = this.createDisjunction();
			break;
		case BIIMPLICATION:
			newExpression = this.createBiImplication();
			break;
		case EXCLUSIVEDISJUNCTION:
			newExpression = this.createExclusiveDisjunction();
			break;
		case IMPLICATION:
			newExpression = this.createImplication();
			break;
		}
		newExpression.setOperand1(op1);
		newExpression.setOperand2(op2);
		return newExpression;
	}
	
	public Equality createEquality(List<IntConstant> args){
		Equality newEquality = this.createEquality();
		newEquality.setOperand1(args.get(0));
		newEquality.setOperand2(args.get(1));
		return newEquality;
	}
	
	public LogicalNegation createLogicalNegation(Expression op){
		LogicalNegation l = this.createLogicalNegation();
		l.setOperand(op);
		return l;
	}

} //Z3pyFactoryImpl
