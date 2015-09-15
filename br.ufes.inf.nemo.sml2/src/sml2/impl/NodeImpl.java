/**
 */
package sml2.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;
import sml2.Node;
import sml2.SituationTypeAssociation;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class NodeImpl extends MinimalEObjectImpl.Container implements Node {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SituationTypeAssociation> sourceRelation() {
		if (sourceRelationBodyOCL == null) {
			EOperation eOperation = Sml2Package.Literals.NODE.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(Sml2Package.Literals.NODE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				sourceRelationBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(sourceRelationBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<SituationTypeAssociation> result = (Collection<SituationTypeAssociation>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<SituationTypeAssociation>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SituationTypeAssociation> targetRelation() {
		if (targetRelationBodyOCL == null) {
			EOperation eOperation = Sml2Package.Literals.NODE.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(Sml2Package.Literals.NODE, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				targetRelationBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(targetRelationBodyOCL);
	
		@SuppressWarnings("unchecked")
		Collection<SituationTypeAssociation> result = (Collection<SituationTypeAssociation>) query.evaluate(this);
		return new BasicEList.UnmodifiableEList<SituationTypeAssociation>(result.size(), result.toArray());
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case Sml2Package.NODE___SOURCE_RELATION:
				return sourceRelation();
			case Sml2Package.NODE___TARGET_RELATION:
				return targetRelation();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #sourceRelation <em>Source Relation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #sourceRelation
	 * @generated
	 */
	private static OCLExpression<EClassifier> sourceRelationBodyOCL;

	/**
	 * The parsed OCL expression for the body of the '{@link #targetRelation <em>Target Relation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #targetRelation
	 * @generated
	 */
	private static OCLExpression<EClassifier> targetRelationBodyOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //NodeImpl
