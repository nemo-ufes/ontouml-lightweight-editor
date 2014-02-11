/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.impl;

import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLPackage;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;

import org.eclipse.ocl.ecore.OCL;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derivation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DerivationImpl extends DependencyRelationshipImpl implements Derivation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RefOntoUMLPackage.eINSTANCE.getDerivation();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property materialEnd() {
		if (materialEndBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getDerivation().getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getDerivation(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				materialEndBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(materialEndBodyOCL);
	
		return (Property) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property relatorEnd() {
		if (relatorEndBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getDerivation().getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getDerivation(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				relatorEndBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(relatorEndBodyOCL);
	
		return (Property) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier relator() {
		if (relatorBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getDerivation().getEOperations().get(2);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getDerivation(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				relatorBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(relatorBodyOCL);
	
		return (Classifier) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier material() {
		if (materialBodyOCL == null) {
			EOperation eOperation = RefOntoUMLPackage.eINSTANCE.getDerivation().getEOperations().get(3);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(RefOntoUMLPackage.eINSTANCE.getDerivation(), eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				materialBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(materialBodyOCL);
	
		return (Classifier) query.evaluate(this);
	
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #materialEnd <em>Material End</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #materialEnd
	 * @generated
	 */
	private static OCLExpression<EClassifier> materialEndBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #relatorEnd <em>Relator End</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #relatorEnd
	 * @generated
	 */
	private static OCLExpression<EClassifier> relatorEndBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #relator <em>Relator</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #relator
	 * @generated
	 */
	private static OCLExpression<EClassifier> relatorBodyOCL;
	
	/**
	 * The parsed OCL expression for the body of the '{@link #material <em>Material</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #material
	 * @generated
	 */
	private static OCLExpression<EClassifier> materialBodyOCL;
	
	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //DerivationImpl
