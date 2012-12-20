/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy.util;

import br.ufes.inf.nemo.alloy.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.alloy.AlloyPackage
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
	@Override
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
	protected AlloySwitch<Adapter> modelSwitch =
		new AlloySwitch<Adapter>() {
			@Override
			public Adapter caseAlloyModule(AlloyModule object) {
				return createAlloyModuleAdapter();
			}
			@Override
			public Adapter caseParametrizedModule(ParametrizedModule object) {
				return createParametrizedModuleAdapter();
			}
			@Override
			public Adapter caseSignatureParameter(SignatureParameter object) {
				return createSignatureParameterAdapter();
			}
			@Override
			public Adapter caseImporterModule(ImporterModule object) {
				return createImporterModuleAdapter();
			}
			@Override
			public Adapter caseModuleImportation(ModuleImportation object) {
				return createModuleImportationAdapter();
			}
			@Override
			public Adapter caseParagraph(Paragraph object) {
				return createParagraphAdapter();
			}
			@Override
			public Adapter caseSignatureDeclaration(SignatureDeclaration object) {
				return createSignatureDeclarationAdapter();
			}
			@Override
			public Adapter caseFactDeclaration(FactDeclaration object) {
				return createFactDeclarationAdapter();
			}
			@Override
			public Adapter caseFunctionDeclaration(FunctionDeclaration object) {
				return createFunctionDeclarationAdapter();
			}
			@Override
			public Adapter casePredicateDeclaration(PredicateDeclaration object) {
				return createPredicateDeclarationAdapter();
			}
			@Override
			public Adapter caseAssertionDeclaration(AssertionDeclaration object) {
				return createAssertionDeclarationAdapter();
			}
			@Override
			public Adapter caseCommandDeclaration(CommandDeclaration object) {
				return createCommandDeclarationAdapter();
			}
			@Override
			public Adapter caseInheritance(Inheritance object) {
				return createInheritanceAdapter();
			}
			@Override
			public Adapter caseScopeSpecification(ScopeSpecification object) {
				return createScopeSpecificationAdapter();
			}
			@Override
			public Adapter caseGenericScope(GenericScope object) {
				return createGenericScopeAdapter();
			}
			@Override
			public Adapter caseDetailedScope(DetailedScope object) {
				return createDetailedScopeAdapter();
			}
			@Override
			public Adapter caseScopeable(Scopeable object) {
				return createScopeableAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseDeclaration(Declaration object) {
				return createDeclarationAdapter();
			}
			@Override
			public Adapter caseSignatureReference(SignatureReference object) {
				return createSignatureReferenceAdapter();
			}
			@Override
			public Adapter caseBlock(Block object) {
				return createBlockAdapter();
			}
			@Override
			public Adapter caseBinaryOperation(BinaryOperation object) {
				return createBinaryOperationAdapter();
			}
			@Override
			public Adapter caseUnaryOperation(UnaryOperation object) {
				return createUnaryOperationAdapter();
			}
			@Override
			public Adapter caseLetDeclaration(LetDeclaration object) {
				return createLetDeclarationAdapter();
			}
			@Override
			public Adapter caseCompareOperation(CompareOperation object) {
				return createCompareOperationAdapter();
			}
			@Override
			public Adapter caseVariableReference(VariableReference object) {
				return createVariableReferenceAdapter();
			}
			@Override
			public Adapter caseArrowOperation(ArrowOperation object) {
				return createArrowOperationAdapter();
			}
			@Override
			public Adapter caseConstantExpression(ConstantExpression object) {
				return createConstantExpressionAdapter();
			}
			@Override
			public Adapter caseExternalReference(ExternalReference object) {
				return createExternalReferenceAdapter();
			}
			@Override
			public Adapter caseComprehensionExpression(ComprehensionExpression object) {
				return createComprehensionExpressionAdapter();
			}
			@Override
			public Adapter caseFunctionInvocation(FunctionInvocation object) {
				return createFunctionInvocationAdapter();
			}
			@Override
			public Adapter casePredicateInvocation(PredicateInvocation object) {
				return createPredicateInvocationAdapter();
			}
			@Override
			public Adapter caseDisjointExpression(DisjointExpression object) {
				return createDisjointExpressionAdapter();
			}
			@Override
			public Adapter caseImpliesOperation(ImpliesOperation object) {
				return createImpliesOperationAdapter();
			}
			@Override
			public Adapter caseQuantificationExpression(QuantificationExpression object) {
				return createQuantificationExpressionAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseEnumDeclaration(EnumDeclaration object) {
				return createEnumDeclarationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
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
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.AlloyModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.AlloyModule
	 * @generated
	 */
	public Adapter createAlloyModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ParametrizedModule <em>Parametrized Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ParametrizedModule
	 * @generated
	 */
	public Adapter createParametrizedModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.SignatureParameter <em>Signature Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.SignatureParameter
	 * @generated
	 */
	public Adapter createSignatureParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ImporterModule <em>Importer Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ImporterModule
	 * @generated
	 */
	public Adapter createImporterModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ModuleImportation <em>Module Importation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ModuleImportation
	 * @generated
	 */
	public Adapter createModuleImportationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Paragraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Paragraph
	 * @generated
	 */
	public Adapter createParagraphAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.SignatureDeclaration <em>Signature Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.SignatureDeclaration
	 * @generated
	 */
	public Adapter createSignatureDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.FactDeclaration <em>Fact Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.FactDeclaration
	 * @generated
	 */
	public Adapter createFactDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.FunctionDeclaration <em>Function Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.FunctionDeclaration
	 * @generated
	 */
	public Adapter createFunctionDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.PredicateDeclaration <em>Predicate Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.PredicateDeclaration
	 * @generated
	 */
	public Adapter createPredicateDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.AssertionDeclaration <em>Assertion Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.AssertionDeclaration
	 * @generated
	 */
	public Adapter createAssertionDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.CommandDeclaration <em>Command Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.CommandDeclaration
	 * @generated
	 */
	public Adapter createCommandDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Inheritance <em>Inheritance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Inheritance
	 * @generated
	 */
	public Adapter createInheritanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ScopeSpecification <em>Scope Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ScopeSpecification
	 * @generated
	 */
	public Adapter createScopeSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.GenericScope <em>Generic Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.GenericScope
	 * @generated
	 */
	public Adapter createGenericScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.DetailedScope <em>Detailed Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.DetailedScope
	 * @generated
	 */
	public Adapter createDetailedScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Scopeable <em>Scopeable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Scopeable
	 * @generated
	 */
	public Adapter createScopeableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Declaration
	 * @generated
	 */
	public Adapter createDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.SignatureReference <em>Signature Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.SignatureReference
	 * @generated
	 */
	public Adapter createSignatureReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.BinaryOperation <em>Binary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.BinaryOperation
	 * @generated
	 */
	public Adapter createBinaryOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.UnaryOperation <em>Unary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.UnaryOperation
	 * @generated
	 */
	public Adapter createUnaryOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.LetDeclaration <em>Let Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.LetDeclaration
	 * @generated
	 */
	public Adapter createLetDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.CompareOperation <em>Compare Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.CompareOperation
	 * @generated
	 */
	public Adapter createCompareOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.VariableReference
	 * @generated
	 */
	public Adapter createVariableReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ArrowOperation <em>Arrow Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ArrowOperation
	 * @generated
	 */
	public Adapter createArrowOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ConstantExpression <em>Constant Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ConstantExpression
	 * @generated
	 */
	public Adapter createConstantExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ExternalReference
	 * @generated
	 */
	public Adapter createExternalReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ComprehensionExpression <em>Comprehension Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ComprehensionExpression
	 * @generated
	 */
	public Adapter createComprehensionExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.FunctionInvocation <em>Function Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.FunctionInvocation
	 * @generated
	 */
	public Adapter createFunctionInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.PredicateInvocation <em>Predicate Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.PredicateInvocation
	 * @generated
	 */
	public Adapter createPredicateInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.DisjointExpression <em>Disjoint Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.DisjointExpression
	 * @generated
	 */
	public Adapter createDisjointExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.ImpliesOperation <em>Implies Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.ImpliesOperation
	 * @generated
	 */
	public Adapter createImpliesOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.QuantificationExpression <em>Quantification Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.QuantificationExpression
	 * @generated
	 */
	public Adapter createQuantificationExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link br.ufes.inf.nemo.alloy.EnumDeclaration <em>Enum Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see br.ufes.inf.nemo.alloy.EnumDeclaration
	 * @generated
	 */
	public Adapter createEnumDeclarationAdapter() {
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
