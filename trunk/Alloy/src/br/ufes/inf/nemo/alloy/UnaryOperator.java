/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package br.ufes.inf.nemo.alloy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Unary Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getUnaryOperator()
 * @model
 * @generated
 */
public final class UnaryOperator extends AbstractEnumerator {
	/**
	 * The '<em><b>Transposition</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Transposition</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSPOSITION_LITERAL
	 * @model name="transposition" literal="~"
	 * @generated
	 * @ordered
	 */
	public static final int TRANSPOSITION = 11;

	/**
	 * The '<em><b>Transitivity Closure</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Transitivity Closure</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSITIVITY_CLOSURE_LITERAL
	 * @model name="transitivityClosure" literal="^"
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITIVITY_CLOSURE = 1;

	/**
	 * The '<em><b>Reflexive Transity Closure</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Reflexive Transity Closure</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REFLEXIVE_TRANSITY_CLOSURE_LITERAL
	 * @model name="reflexiveTransityClosure" literal="*"
	 * @generated
	 * @ordered
	 */
	public static final int REFLEXIVE_TRANSITY_CLOSURE = 2;

	/**
	 * The '<em><b>Not</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_LITERAL
	 * @model name="not"
	 * @generated
	 * @ordered
	 */
	public static final int NOT = 4;

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
	public static final int NO = 5;

	/**
	 * The '<em><b>Set</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Set</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SET_LITERAL
	 * @model name="set"
	 * @generated
	 * @ordered
	 */
	public static final int SET = 6;

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
	public static final int SOME = 7;

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
	public static final int LONE = 8;

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
	public static final int ONE = 9;

	/**
	 * The '<em><b>Cardinality</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cardinality</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CARDINALITY_LITERAL
	 * @model name="cardinality" literal="#"
	 * @generated
	 * @ordered
	 */
	public static final int CARDINALITY = 10;

	/**
	 * The '<em><b>Transposition</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSPOSITION
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator TRANSPOSITION_LITERAL = new UnaryOperator(TRANSPOSITION, "transposition", "~");

	/**
	 * The '<em><b>Transitivity Closure</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSITIVITY_CLOSURE
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator TRANSITIVITY_CLOSURE_LITERAL = new UnaryOperator(TRANSITIVITY_CLOSURE, "transitivityClosure", "^");

	/**
	 * The '<em><b>Reflexive Transity Closure</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REFLEXIVE_TRANSITY_CLOSURE
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator REFLEXIVE_TRANSITY_CLOSURE_LITERAL = new UnaryOperator(REFLEXIVE_TRANSITY_CLOSURE, "reflexiveTransityClosure", "*");

	/**
	 * The '<em><b>Not</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator NOT_LITERAL = new UnaryOperator(NOT, "not", "not");

	/**
	 * The '<em><b>No</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator NO_LITERAL = new UnaryOperator(NO, "no", "no");

	/**
	 * The '<em><b>Set</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SET
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator SET_LITERAL = new UnaryOperator(SET, "set", "set");

	/**
	 * The '<em><b>Some</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOME
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator SOME_LITERAL = new UnaryOperator(SOME, "some", "some");

	/**
	 * The '<em><b>Lone</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LONE
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator LONE_LITERAL = new UnaryOperator(LONE, "lone", "lone");

	/**
	 * The '<em><b>One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONE
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator ONE_LITERAL = new UnaryOperator(ONE, "one", "one");

	/**
	 * The '<em><b>Cardinality</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINALITY
	 * @generated
	 * @ordered
	 */
	public static final UnaryOperator CARDINALITY_LITERAL = new UnaryOperator(CARDINALITY, "cardinality", "#");

	/**
	 * An array of all the '<em><b>Unary Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final UnaryOperator[] VALUES_ARRAY =
		new UnaryOperator[] {
			TRANSPOSITION_LITERAL,
			TRANSITIVITY_CLOSURE_LITERAL,
			REFLEXIVE_TRANSITY_CLOSURE_LITERAL,
			NOT_LITERAL,
			NO_LITERAL,
			SET_LITERAL,
			SOME_LITERAL,
			LONE_LITERAL,
			ONE_LITERAL,
			CARDINALITY_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Unary Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Unary Operator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnaryOperator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnaryOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Unary Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnaryOperator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnaryOperator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Unary Operator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnaryOperator get(int value) {
		switch (value) {
			case TRANSPOSITION: return TRANSPOSITION_LITERAL;
			case TRANSITIVITY_CLOSURE: return TRANSITIVITY_CLOSURE_LITERAL;
			case REFLEXIVE_TRANSITY_CLOSURE: return REFLEXIVE_TRANSITY_CLOSURE_LITERAL;
			case NOT: return NOT_LITERAL;
			case NO: return NO_LITERAL;
			case SET: return SET_LITERAL;
			case SOME: return SOME_LITERAL;
			case LONE: return LONE_LITERAL;
			case ONE: return ONE_LITERAL;
			case CARDINALITY: return CARDINALITY_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private UnaryOperator(int value, String name, String literal) {
		super(value, name, literal);
	}

} //UnaryOperator
