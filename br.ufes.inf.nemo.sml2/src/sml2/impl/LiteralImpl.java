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
import sml2.Literal;
import sml2.SituationType;
import sml2.SituationTypeElement;
import sml2.Sml2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class LiteralImpl extends NodeImpl implements Literal {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sml2Package.Literals.LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SituationType getSituation() {
		if (getSituationBodyOCL == null) {
			EOperation eOperation = Sml2Package.Literals.SITUATION_TYPE_ELEMENT.getEOperations().get(0);
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setOperationContext(Sml2Package.Literals.SITUATION_TYPE_ELEMENT, eOperation);
			EAnnotation ocl = eOperation.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String body = ocl.getDetails().get("body");
			
			try {
				getSituationBodyOCL = helper.createQuery(body);
			} catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(getSituationBodyOCL);
	
		return (SituationType) query.evaluate(this);
	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == SituationTypeElement.class) {
			switch (baseOperationID) {
				case Sml2Package.SITUATION_TYPE_ELEMENT___GET_SITUATION: return Sml2Package.LITERAL___GET_SITUATION;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case Sml2Package.LITERAL___GET_SITUATION:
				return getSituation();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * The parsed OCL expression for the body of the '{@link #getSituation <em>Get Situation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSituation
	 * @generated
	 */
	private static OCLExpression<EClassifier> getSituationBodyOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/ocl/examples/OCL";
	
	private static final OCL OCL_ENV = OCL.newInstance();
} //LiteralImpl
