/**
 */
package br.ufes.inf.nemo.z3py.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import br.ufes.inf.nemo.z3py.Expression;
import br.ufes.inf.nemo.z3py.IntConstant;
import br.ufes.inf.nemo.z3py.Quantification;
import br.ufes.inf.nemo.z3py.Z3pyPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quantification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl#getQuantifiesOver <em>Quantifies Over</em>}</li>
 *   <li>{@link br.ufes.inf.nemo.z3py.impl.QuantificationImpl#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QuantificationImpl extends ExpressionImpl implements Quantification {
	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The cached value of the '{@link #getQuantifiesOver() <em>Quantifies Over</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantifiesOver()
	 * @generated
	 * @ordered
	 */
	protected EList<IntConstant> quantifiesOver;

	/**
	 * The default value of the '{@link #getComments() <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected String comments = COMMENTS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuantificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Z3pyPackage.Literals.QUANTIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		if (expression != null && expression.eIsProxy()) {
			InternalEObject oldExpression = (InternalEObject)expression;
			expression = (Expression)eResolveProxy(oldExpression);
			if (expression != oldExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Z3pyPackage.QUANTIFICATION__EXPRESSION, oldExpression, expression));
			}
		}
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.QUANTIFICATION__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntConstant> getQuantifiesOver() {
		if (quantifiesOver == null) {
			quantifiesOver = new EObjectResolvingEList<IntConstant>(IntConstant.class, this, Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER);
		}
		return quantifiesOver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComments(String newComments) {
		String oldComments = comments;
		comments = newComments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Z3pyPackage.QUANTIFICATION__COMMENTS, oldComments, comments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				if (resolve) return getExpression();
				return basicGetExpression();
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				return getQuantifiesOver();
			case Z3pyPackage.QUANTIFICATION__COMMENTS:
				return getComments();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				getQuantifiesOver().clear();
				getQuantifiesOver().addAll((Collection<? extends IntConstant>)newValue);
				return;
			case Z3pyPackage.QUANTIFICATION__COMMENTS:
				setComments((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				getQuantifiesOver().clear();
				return;
			case Z3pyPackage.QUANTIFICATION__COMMENTS:
				setComments(COMMENTS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Z3pyPackage.QUANTIFICATION__EXPRESSION:
				return expression != null;
			case Z3pyPackage.QUANTIFICATION__QUANTIFIES_OVER:
				return quantifiesOver != null && !quantifiesOver.isEmpty();
			case Z3pyPackage.QUANTIFICATION__COMMENTS:
				return COMMENTS_EDEFAULT == null ? comments != null : !COMMENTS_EDEFAULT.equals(comments);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 */
/*	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (comments: ");
		result.append(comments);
		result.append(')');
		return result.toString();
	}
	*/
	public String toString() {
		int i;
		
		String result;
		if (this instanceof UniversalQuantificationImpl)
			result = "ForAll (";
		else
			result = "Exists (";
		
		if (this.quantifiesOver.size()==1)
			result = result.concat(this.quantifiesOver.get(0).getName() + ", ");
		else{
			result = result.concat("[");
			for(i=0; i<this.quantifiesOver.size()-1;i++){
				result = result.concat(this.quantifiesOver.get(i).getName()+",");
			}
			result = result.concat(this.quantifiesOver.get(i).getName()+"], ");
		}
		result = result.concat(this.expression.toString() + ")");
		return result;
	}


} //QuantificationImpl
