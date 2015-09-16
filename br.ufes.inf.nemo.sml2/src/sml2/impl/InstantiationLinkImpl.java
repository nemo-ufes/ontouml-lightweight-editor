/**
 */
package sml2.impl;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import sml2.InstantiationLink;
import sml2.Participant;
import sml2.Sml2Package;
import sml2.TypeLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instantiation Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class InstantiationLinkImpl extends FormalRelationImpl implements InstantiationLink {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstantiationLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.INSTANTIATION_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant getSource() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLiteral getTarget() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case Sml2Package.INSTANTIATION_LINK___GET_SOURCE:
				return getSource();
			case Sml2Package.INSTANTIATION_LINK___GET_TARGET:
				return getTarget();
		}
		return super.eInvoke(operationID, arguments);
	}

} //InstantiationLinkImpl
