/**
 */
package sml2.impl;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;
import sml2.AttributeLink;
import sml2.AttributeReference;
import sml2.Node;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class AttributeLinkImpl extends SituationTypeAssociationImpl implements AttributeLink {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.ATTRIBUTE_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getEntity() {
		if (getEntityBodyOCL == null) {
			EOperation eOperation = Sml2Package.Literals.ATTRIBUTE_LINK.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(Sml2Package.Literals.ATTRIBUTE_LINK, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getEntityBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getEntityBodyOCL);
	
		return (Node) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeReference getAttribute() {
		if (getAttributeBodyOCL == null) {
			EOperation eOperation = Sml2Package.Literals.ATTRIBUTE_LINK.getEOperations().get(1);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(Sml2Package.Literals.ATTRIBUTE_LINK, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getAttributeBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getAttributeBodyOCL);
	
		return (AttributeReference) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case Sml2Package.ATTRIBUTE_LINK___GET_ENTITY:
				return getEntity();
			case Sml2Package.ATTRIBUTE_LINK___GET_ATTRIBUTE:
				return getAttribute();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getEntity <em>Get Entity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntity
	 * @generated
	 */
	private static OCLExpression<EClassifier> getEntityBodyOCL;

	/**
	 * The parsed OCL expression for the body of the '{@link #getAttribute <em>Get Attribute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute
	 * @generated
	 */
	private static OCLExpression<EClassifier> getAttributeBodyOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //AttributeLinkImpl
