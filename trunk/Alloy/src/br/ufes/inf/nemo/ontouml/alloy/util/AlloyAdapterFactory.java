/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.ontouml.alloy.util;

import br.ufes.inf.nemo.ontouml.alloy.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.ontouml.alloy.AlloyPackage
 * @generated
 */
public class AlloyAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AlloyPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlloyAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AlloyPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlloySwitch modelSwitch =
		new AlloySwitch() {
			public Object caseAlloyModule(AlloyModule object) {
				return createAlloyModuleAdapter();
			}
			public Object caseParametrizedModule(ParametrizedModule object) {
				return createParametrizedModuleAdapter();
			}
			public Object caseSignatureParameter(SignatureParameter object) {
				return createSignatureParameterAdapter();
			}
			public Object caseImporterModule(ImporterModule object) {
				return createImporterModuleAdapter();
			}
			public Object caseModuleImportation(ModuleImportation object) {
				return createModuleImportationAdapter();
			}
			public Object caseParagraph(Paragraph object) {
				return createParagraphAdapter();
			}
			public Object caseSignatureDeclaration(SignatureDeclaration object) {
				return createSignatureDeclarationAdapter();
			}
			public Object caseFactDeclaration(FactDeclaration object) {
				return createFactDeclarationAdapter();
			}
			public Object caseFunctionDeclaration(FunctionDeclaration object) {
				return createFunctionDeclarationAdapter();
			}
			public Object casePredicateDeclaration(PredicateDeclaration object) {
				return createPredicateDeclarationAdapter();
			}
			public Object caseAssertionDeclaration(AssertionDeclaration object) {
				return createAssertionDeclarationAdapter();
			}
			public Object caseCommandDeclaration(CommandDeclaration object) {
				return createCommandDeclarationAdapter();
			}
			public Object caseInheritance(Inheritance object) {
				return createInheritanceAdapter();
			}
			public Object caseScopeSpecification(ScopeSpecification object) {
				return createScopeSpecificationAdapter();
			}
			public Object caseGenericScope(GenericScope object) {
				return createGenericScopeAdapter();
			}
			public Object caseDetailedScope(DetailedScope object) {
				return createDetailedScopeAdapter();
			}
			public Object caseScopeable(Scopeable object) {
				return createScopeableAdapter();
			}
			public Object caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			public Object caseDeclaration(Declaration object) {
				return createDeclarationAdapter();
			}
			public Object caseSignatureReference(SignatureReference object) {
				return createSignatureReferenceAdapter();
			}
			public Object caseBlock(Block object) {
				return createBlockAdapter();
			}
			public Object caseBinaryOperation(BinaryOperation object) {
				return createBinaryOperationAdapter();
			}
			public Object caseUnaryOperation(UnaryOperation object) {
				return createUnaryOperationAdapter();
			}
			public Object caseLetDeclaration(LetDeclaration object) {
				return createLetDeclarationAdapter();
			}
			public Object caseCompareOperation(CompareOperation object) {
				return createCompareOperationAdapter();
			}
			public Object caseVariableReference(VariableReference object) {
				return createVariableReferenceAdapter();
			}
			public Object caseArrowOperation(ArrowOperation object) {
				return createArrowOperationAdapter();
			}
			public Object caseConstantExpression(ConstantExpression object) {
				return createConstantExpressionAdapter();
			}
			public Object caseExternalReference(ExternalReference object) {
				return createExternalReferenceAdapter();
			}
			public Object caseComprehensionExpression(ComprehensionExpression object) {
				return createComprehensionExpressionAdapter();
			}
			public Object caseFunctionInvocation(FunctionInvocation object) {
				return createFunctionInvocationAdapter();
			}
			public Object casePredicateInvocation(PredicateInvocation object) {
				return createPredicateInvocationAdapter();
			}
			public Object caseDisjointExpression(DisjointExpression object) {
				return createDisjointExpressionAdapter();
			}
			public Object caseImpliesOperation(ImpliesOperation object) {
				return createImpliesOperationAdapter();
			}
			public Object caseQuantificationExpression(QuantificationExpression object) {
				return createQuantificationExpressionAdapter();
			}
			public Object caseVariable(Variable object) {
				return createVariableAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.AlloyModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.AlloyModule
	 * @generated
	 */
	public Adapter createAlloyModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ParametrizedModule <em>Parametrized Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ParametrizedModule
	 * @generated
	 */
	public Adapter createParametrizedModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.SignatureParameter <em>Signature Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.SignatureParameter
	 * @generated
	 */
	public Adapter createSignatureParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ImporterModule <em>Importer Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ImporterModule
	 * @generated
	 */
	public Adapter createImporterModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ModuleImportation <em>Module Importation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ModuleImportation
	 * @generated
	 */
	public Adapter createModuleImportationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Paragraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Paragraph
	 * @generated
	 */
	public Adapter createParagraphAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.SignatureDeclaration <em>Signature Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.SignatureDeclaration
	 * @generated
	 */
	public Adapter createSignatureDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.FactDeclaration <em>Fact Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.FactDeclaration
	 * @generated
	 */
	public Adapter createFactDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.FunctionDeclaration <em>Function Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.FunctionDeclaration
	 * @generated
	 */
	public Adapter createFunctionDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.PredicateDeclaration <em>Predicate Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.PredicateDeclaration
	 * @generated
	 */
	public Adapter createPredicateDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.AssertionDeclaration <em>Assertion Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.AssertionDeclaration
	 * @generated
	 */
	public Adapter createAssertionDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.CommandDeclaration <em>Command Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.CommandDeclaration
	 * @generated
	 */
	public Adapter createCommandDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Inheritance <em>Inheritance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Inheritance
	 * @generated
	 */
	public Adapter createInheritanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ScopeSpecification <em>Scope Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ScopeSpecification
	 * @generated
	 */
	public Adapter createScopeSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.GenericScope <em>Generic Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.GenericScope
	 * @generated
	 */
	public Adapter createGenericScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.DetailedScope <em>Detailed Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.DetailedScope
	 * @generated
	 */
	public Adapter createDetailedScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Scopeable <em>Scopeable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Scopeable
	 * @generated
	 */
	public Adapter createScopeableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Declaration
	 * @generated
	 */
	public Adapter createDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.SignatureReference <em>Signature Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.SignatureReference
	 * @generated
	 */
	public Adapter createSignatureReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.BinaryOperation <em>Binary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.BinaryOperation
	 * @generated
	 */
	public Adapter createBinaryOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.UnaryOperation <em>Unary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.UnaryOperation
	 * @generated
	 */
	public Adapter createUnaryOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.LetDeclaration <em>Let Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.LetDeclaration
	 * @generated
	 */
	public Adapter createLetDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.CompareOperation <em>Compare Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.CompareOperation
	 * @generated
	 */
	public Adapter createCompareOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.VariableReference
	 * @generated
	 */
	public Adapter createVariableReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ArrowOperation <em>Arrow Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ArrowOperation
	 * @generated
	 */
	public Adapter createArrowOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ConstantExpression <em>Constant Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ConstantExpression
	 * @generated
	 */
	public Adapter createConstantExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ExternalReference
	 * @generated
	 */
	public Adapter createExternalReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ComprehensionExpression <em>Comprehension Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ComprehensionExpression
	 * @generated
	 */
	public Adapter createComprehensionExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.FunctionInvocation <em>Function Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.FunctionInvocation
	 * @generated
	 */
	public Adapter createFunctionInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.PredicateInvocation <em>Predicate Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.PredicateInvocation
	 * @generated
	 */
	public Adapter createPredicateInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.DisjointExpression <em>Disjoint Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.DisjointExpression
	 * @generated
	 */
	public Adapter createDisjointExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.ImpliesOperation <em>Implies Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.ImpliesOperation
	 * @generated
	 */
	public Adapter createImpliesOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.QuantificationExpression <em>Quantification Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.QuantificationExpression
	 * @generated
	 */
	public Adapter createQuantificationExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.ontouml.alloy.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.ontouml.alloy.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AlloyAdapterFactory
