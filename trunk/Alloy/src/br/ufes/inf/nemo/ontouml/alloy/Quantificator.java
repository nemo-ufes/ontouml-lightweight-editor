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
 * A representation of the literals of the enumeration '<em><b>Quantificator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.ontouml.alloy.AlloyPackage#getQuantificator()
 * @model
 * @generated
 */
public final class Quantificator extends AbstractEnumerator {
	/**
	 * The '<em><b>All</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>All</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL_LITERAL
	 * @model name="all"
	 * @generated
	 * @ordered
	 */
	public static final int ALL = 6;

	/**
	 * The '<em><b>Sum</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Sum</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUM_LITERAL
	 * @model name="sum"
	 * @generated
	 * @ordered
	 */
	public static final int SUM = 1;

	/**
	 * The '<em><b>No</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_LITERAL
	 * @model name="no"
	 * @generated
	 * @ordered
	 */
	public static final int NO = 2;

	/**
	 * The '<em><b>Some</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Some</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SOME_LITERAL
	 * @model name="some"
	 * @generated
	 * @ordered
	 */
	public static final int SOME = 3;

	/**
	 * The '<em><b>Lone</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Lone</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LONE_LITERAL
	 * @model name="lone"
	 * @generated
	 * @ordered
	 */
	public static final int LONE = 4;

	/**
	 * The '<em><b>One</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONE_LITERAL
	 * @model name="one"
	 * @generated
	 * @ordered
	 */
	public static final int ONE = 5;

	/**
	 * The '<em><b>All</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @generated
	 * @ordered
	 */
	public static final Quantificator ALL_LITERAL = new Quantificator(ALL, "all", "all");

	/**
	 * The '<em><b>Sum</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUM
	 * @generated
	 * @ordered
	 */
	public static final Quantificator SUM_LITERAL = new Quantificator(SUM, "sum", "sum");

	/**
	 * The '<em><b>No</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO
	 * @generated
	 * @ordered
	 */
	public static final Quantificator NO_LITERAL = new Quantificator(NO, "no", "no");

	/**
	 * The '<em><b>Some</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOME
	 * @generated
	 * @ordered
	 */
	public static final Quantificator SOME_LITERAL = new Quantificator(SOME, "some", "some");

	/**
	 * The '<em><b>Lone</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LONE
	 * @generated
	 * @ordered
	 */
	public static final Quantificator LONE_LITERAL = new Quantificator(LONE, "lone", "lone");

	/**
	 * The '<em><b>One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE
	 * @generated
	 * @ordered
	 */
	public static final Quantificator ONE_LITERAL = new Quantificator(ONE, "one", "one");

	/**
	 * An array of all the '<em><b>Quantificator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Quantificator[] VALUES_ARRAY =
		new Quantificator[] {
			ALL_LITERAL,
			SUM_LITERAL,
			NO_LITERAL,
			SOME_LITERAL,
			LONE_LITERAL,
			ONE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Quantificator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Quantificator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Quantificator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Quantificator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Quantificator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Quantificator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Quantificator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Quantificator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Quantificator get(int value) {
		switch (value) {
			case ALL: return ALL_LITERAL;
			case SUM: return SUM_LITERAL;
			case NO: return NO_LITERAL;
			case SOME: return SOME_LITERAL;
			case LONE: return LONE_LITERAL;
			case ONE: return ONE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Quantificator(int value, String name, String literal) {
		super(value, name, literal);
	}

} //Quantificator
