/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.ontouml.alloy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Constant</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.ontouml.alloy.AlloyPackage#getConstant()
 * @model
 * @generated
 */
public final class Constant extends AbstractEnumerator {
	/**
	 * The '<em><b>None</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model name="none"
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 3;

	/**
	 * The '<em><b>Univ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Univ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNIV_LITERAL
	 * @model name="univ"
	 * @generated
	 * @ordered
	 */
	public static final int UNIV = 1;

	/**
	 * The '<em><b>Iden</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Iden</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IDEN_LITERAL
	 * @model name="iden"
	 * @generated
	 * @ordered
	 */
	public static final int IDEN = 2;

	/**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final Constant NONE_LITERAL = new Constant(NONE, "none", "none");

	/**
	 * The '<em><b>Univ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIV
	 * @generated
	 * @ordered
	 */
	public static final Constant UNIV_LITERAL = new Constant(UNIV, "univ", "univ");

	/**
	 * The '<em><b>Iden</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IDEN
	 * @generated
	 * @ordered
	 */
	public static final Constant IDEN_LITERAL = new Constant(IDEN, "iden", "iden");

	/**
	 * An array of all the '<em><b>Constant</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Constant[] VALUES_ARRAY =
		new Constant[] {
			NONE_LITERAL,
			UNIV_LITERAL,
			IDEN_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Constant</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Constant</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Constant get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Constant result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Constant</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Constant getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Constant result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Constant</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Constant get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case UNIV: return UNIV_LITERAL;
			case IDEN: return IDEN_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Constant(int value, String name, String literal) {
		super(value, name, literal);
	}

} //Constant
