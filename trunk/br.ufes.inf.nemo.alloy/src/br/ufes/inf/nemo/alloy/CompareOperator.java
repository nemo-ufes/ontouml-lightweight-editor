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
 * A representation of the literals of the enumeration '<em><b>Compare Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getCompareOperator()
 * @model
 * @generated
 */
public final class CompareOperator extends AbstractEnumerator {
	/**
	 * The '<em><b>Subset</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Subset</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUBSET_LITERAL
	 * @model name="subset" literal="in"
	 * @generated
	 * @ordered
	 */
	public static final int SUBSET = 6;

	/**
	 * The '<em><b>Equal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Equal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EQUAL_LITERAL
	 * @model name="equal" literal="="
	 * @generated
	 * @ordered
	 */
	public static final int EQUAL = 1;

	/**
	 * The '<em><b>Less Than</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Less Than</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_LITERAL
	 * @model name="lessThan" literal="<"
	 * @generated
	 * @ordered
	 */
	public static final int LESS_THAN = 2;

	/**
	 * The '<em><b>Greater Then</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Greater Then</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_THEN_LITERAL
	 * @model name="greaterThen" literal=">"
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_THEN = 3;

	/**
	 * The '<em><b>Less Equal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Less Equal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUAL_LITERAL
	 * @model name="lessEqual" literal="=<"
	 * @generated
	 * @ordered
	 */
	public static final int LESS_EQUAL = 4;

	/**
	 * The '<em><b>Greater Equal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Greater Equal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUAL_LITERAL
	 * @model name="greaterEqual" literal=">="
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_EQUAL = 5;

	/**
	 * The '<em><b>Subset</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUBSET
	 * @generated
	 * @ordered
	 */
	public static final CompareOperator SUBSET_LITERAL = new CompareOperator(SUBSET, "subset", "in");

	/**
	 * The '<em><b>Equal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUAL
	 * @generated
	 * @ordered
	 */
	public static final CompareOperator EQUAL_LITERAL = new CompareOperator(EQUAL, "equal", "=");

	/**
	 * The '<em><b>Less Than</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN
	 * @generated
	 * @ordered
	 */
	public static final CompareOperator LESS_THAN_LITERAL = new CompareOperator(LESS_THAN, "lessThan", "<");

	/**
	 * The '<em><b>Greater Then</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_THEN
	 * @generated
	 * @ordered
	 */
	public static final CompareOperator GREATER_THEN_LITERAL = new CompareOperator(GREATER_THEN, "greaterThen", ">");

	/**
	 * The '<em><b>Less Equal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUAL
	 * @generated
	 * @ordered
	 */
	public static final CompareOperator LESS_EQUAL_LITERAL = new CompareOperator(LESS_EQUAL, "lessEqual", "=<");

	/**
	 * The '<em><b>Greater Equal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUAL
	 * @generated
	 * @ordered
	 */
	public static final CompareOperator GREATER_EQUAL_LITERAL = new CompareOperator(GREATER_EQUAL, "greaterEqual", ">=");

	/**
	 * An array of all the '<em><b>Compare Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CompareOperator[] VALUES_ARRAY =
		new CompareOperator[] {
			SUBSET_LITERAL,
			EQUAL_LITERAL,
			LESS_THAN_LITERAL,
			GREATER_THEN_LITERAL,
			LESS_EQUAL_LITERAL,
			GREATER_EQUAL_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Compare Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Compare Operator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CompareOperator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CompareOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Compare Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CompareOperator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CompareOperator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Compare Operator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CompareOperator get(int value) {
		switch (value) {
			case SUBSET: return SUBSET_LITERAL;
			case EQUAL: return EQUAL_LITERAL;
			case LESS_THAN: return LESS_THAN_LITERAL;
			case GREATER_THEN: return GREATER_THEN_LITERAL;
			case LESS_EQUAL: return LESS_EQUAL_LITERAL;
			case GREATER_EQUAL: return GREATER_EQUAL_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CompareOperator(int value, String name, String literal) {
		super(value, name, literal);
	}

} //CompareOperator
