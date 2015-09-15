/**
 */
package sml2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Comparative Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see sml2.Sml2Package#getComparativeKind()
 * @model
 * @generated
 */
public enum ComparativeKind implements Enumerator {
	/**
	 * The '<em><b>Less Than</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_VALUE
	 * @generated
	 * @ordered
	 */
	LESS_THAN(0, "lessThan", "lessThan"),

	/**
	 * The '<em><b>Less Than Equal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_EQUAL_VALUE
	 * @generated
	 * @ordered
	 */
	LESS_THAN_EQUAL(1, "lessThanEqual", "lessThanEqual"),

	/**
	 * The '<em><b>Greater Than</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_THAN(2, "greaterThan", "greaterThan"),

	/**
	 * The '<em><b>Greater Than Equal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_EQUAL_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_THAN_EQUAL(3, "greaterThanEqual", "greaterThanEqual");

	/**
	 * The '<em><b>Less Than</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Less Than</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN
	 * @model name="lessThan"
	 * @generated
	 * @ordered
	 */
	public static final int LESS_THAN_VALUE = 0;

	/**
	 * The '<em><b>Less Than Equal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Less Than Equal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_EQUAL
	 * @model name="lessThanEqual"
	 * @generated
	 * @ordered
	 */
	public static final int LESS_THAN_EQUAL_VALUE = 1;

	/**
	 * The '<em><b>Greater Than</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Greater Than</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN
	 * @model name="greaterThan"
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_THAN_VALUE = 2;

	/**
	 * The '<em><b>Greater Than Equal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Greater Than Equal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_EQUAL
	 * @model name="greaterThanEqual"
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_THAN_EQUAL_VALUE = 3;

	/**
	 * An array of all the '<em><b>Comparative Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ComparativeKind[] VALUES_ARRAY =
		new ComparativeKind[] {
			LESS_THAN,
			LESS_THAN_EQUAL,
			GREATER_THAN,
			GREATER_THAN_EQUAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Comparative Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ComparativeKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Comparative Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComparativeKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComparativeKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Comparative Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComparativeKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComparativeKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Comparative Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComparativeKind get(int value) {
		switch (value) {
			case LESS_THAN_VALUE: return LESS_THAN;
			case LESS_THAN_EQUAL_VALUE: return LESS_THAN_EQUAL;
			case GREATER_THAN_VALUE: return GREATER_THAN;
			case GREATER_THAN_EQUAL_VALUE: return GREATER_THAN_EQUAL;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ComparativeKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ComparativeKind
