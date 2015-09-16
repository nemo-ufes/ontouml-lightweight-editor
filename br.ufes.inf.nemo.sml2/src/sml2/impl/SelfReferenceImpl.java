/**
 */
package sml2.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.OCL;
import sml2.SelfReference;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Self Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SelfReferenceImpl extends SituationParticipantImpl implements SelfReference {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SelfReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.SELF_REFERENCE;
	}

	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	private static final OCL OCL_ENV = OCL.newInstance();

} //SelfReferenceImpl
