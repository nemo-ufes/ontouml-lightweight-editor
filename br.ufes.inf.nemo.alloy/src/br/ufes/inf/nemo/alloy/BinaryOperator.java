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
 * A representation of the literals of the enumeration '<em><b>Binary Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see br.ufes.inf.nemo.alloy.AlloyPackage#getBinaryOperator()
 * @model
 * @generated
 */
public final class BinaryOperator extends AbstractEnumerator {
	/**
	 * The '<em><b>Difference</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Difference</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIFFERENCE_LITERAL
	 * @model name="difference" literal="-"
	 * @generated
	 * @ordered
	 */
	public static final int DIFFERENCE = 1;

	/**
	 * The '<em><b>Intersection</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Intersection</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERSECTION_LITERAL
	 * @model name="intersection" literal="&"
	 * @generated
	 * @ordered
	 */
	public static final int INTERSECTION = 2;

	/**
	 * The '<em><b>Join</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Join</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JOIN_LITERAL
	 * @model name="join" literal="."
	 * @generated
	 * @ordered
	 */
	public static final int JOIN = 3;

	/**
	 * The '<em><b>Union</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Union</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNION_LITERAL
	 * @model name="union" literal="+"
	 * @generated
	 * @ordered
	 */
	public static final int UNION = 4;

	/**
	 * The '<em><b>Domain Restriction</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Domain Restriction</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOMAIN_RESTRICTION_LITERAL
	 * @model name="domainRestriction" literal="<:"
	 * @generated
	 * @ordered
	 */
	public static final int DOMAIN_RESTRICTION = 5;

	/**
	 * The '<em><b>Range Restriction</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Range Restriction</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RANGE_RESTRICTION_LITERAL
	 * @model name="rangeRestriction" literal=":>"
	 * @generated
	 * @ordered
	 */
	public static final int RANGE_RESTRICTION = 6;

	/**
	 * The '<em><b>Relational Override</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Relational Override</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RELATIONAL_OVERRIDE_LITERAL
	 * @model name="relationalOverride" literal="++"
	 * @generated
	 * @ordered
	 */
	public static final int RELATIONAL_OVERRIDE = 7;

	/**
	 * The '<em><b>Iff</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Iff</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IFF_LITERAL
	 * @model name="iff"
	 * @generated
	 * @ordered
	 */
	public static final int IFF = 8;

	/**
	 * The '<em><b>Or</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Or</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OR_LITERAL
	 * @model name="or"
	 * @generated
	 * @ordered
	 */
	public static final int OR = 9;

	/**
	 * The '<em><b>And</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>And</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AND_LITERAL
	 * @model name="and"
	 * @generated
	 * @ordered
	 */
	public static final int AND = 10;

	/**
	 * The '<em><b>Box Join</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Box Join</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOX_JOIN_LITERAL
	 * @model name="boxJoin" literal="[]"
	 * @generated
	 * @ordered
	 */
	public static final int BOX_JOIN = 11;

	/**
	 * The '<em><b>Difference</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIFFERENCE
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator DIFFERENCE_LITERAL = new BinaryOperator(DIFFERENCE, "difference", "-");

	/**
	 * The '<em><b>Intersection</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERSECTION
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator INTERSECTION_LITERAL = new BinaryOperator(INTERSECTION, "intersection", "&");

	/**
	 * The '<em><b>Join</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOIN
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator JOIN_LITERAL = new BinaryOperator(JOIN, "join", ".");

	/**
	 * The '<em><b>Union</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNION
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator UNION_LITERAL = new BinaryOperator(UNION, "union", "+");

	/**
	 * The '<em><b>Domain Restriction</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOMAIN_RESTRICTION
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator DOMAIN_RESTRICTION_LITERAL = new BinaryOperator(DOMAIN_RESTRICTION, "domainRestriction", "<:");

	/**
	 * The '<em><b>Range Restriction</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RANGE_RESTRICTION
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator RANGE_RESTRICTION_LITERAL = new BinaryOperator(RANGE_RESTRICTION, "rangeRestriction", ":>");

	/**
	 * The '<em><b>Relational Override</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELATIONAL_OVERRIDE
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator RELATIONAL_OVERRIDE_LITERAL = new BinaryOperator(RELATIONAL_OVERRIDE, "relationalOverride", "++");

	/**
	 * The '<em><b>Iff</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IFF
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator IFF_LITERAL = new BinaryOperator(IFF, "iff", "iff");

	/**
	 * The '<em><b>Or</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator OR_LITERAL = new BinaryOperator(OR, "or", "or");

	/**
	 * The '<em><b>And</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator AND_LITERAL = new BinaryOperator(AND, "and", "and");

	/**
	 * The '<em><b>Box Join</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOX_JOIN
	 * @generated
	 * @ordered
	 */
	public static final BinaryOperator BOX_JOIN_LITERAL = new BinaryOperator(BOX_JOIN, "boxJoin", "[]");

	/**
	 * An array of all the '<em><b>Binary Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BinaryOperator[] VALUES_ARRAY =
		new BinaryOperator[] {
			DIFFERENCE_LITERAL,
			INTERSECTION_LITERAL,
			JOIN_LITERAL,
			UNION_LITERAL,
			DOMAIN_RESTRICTION_LITERAL,
			RANGE_RESTRICTION_LITERAL,
			RELATIONAL_OVERRIDE_LITERAL,
			IFF_LITERAL,
			OR_LITERAL,
			AND_LITERAL,
			BOX_JOIN_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Binary Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Binary Operator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BinaryOperator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BinaryOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Binary Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BinaryOperator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BinaryOperator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Binary Operator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BinaryOperator get(int value) {
		switch (value) {
			case DIFFERENCE: return DIFFERENCE_LITERAL;
			case INTERSECTION: return INTERSECTION_LITERAL;
			case JOIN: return JOIN_LITERAL;
			case UNION: return UNION_LITERAL;
			case DOMAIN_RESTRICTION: return DOMAIN_RESTRICTION_LITERAL;
			case RANGE_RESTRICTION: return RANGE_RESTRICTION_LITERAL;
			case RELATIONAL_OVERRIDE: return RELATIONAL_OVERRIDE_LITERAL;
			case IFF: return IFF_LITERAL;
			case OR: return OR_LITERAL;
			case AND: return AND_LITERAL;
			case BOX_JOIN: return BOX_JOIN_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private BinaryOperator(int value, String name, String literal) {
		super(value, name, literal);
	}

} //BinaryOperator
