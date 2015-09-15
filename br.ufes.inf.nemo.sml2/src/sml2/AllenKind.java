/**
 */
package sml2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Allen Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see sml2.Sml2Package#getAllenKind()
 * @model
 * @generated
 */
public enum AllenKind implements Enumerator {
	/**
	 * The '<em><b>Before</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BEFORE_VALUE
	 * @generated
	 * @ordered
	 */
	BEFORE(0, "before", "before"),

	/**
	 * The '<em><b>After</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AFTER_VALUE
	 * @generated
	 * @ordered
	 */
	AFTER(1, "after", "after"),

	/**
	 * The '<em><b>Meets</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEETS_VALUE
	 * @generated
	 * @ordered
	 */
	MEETS(2, "meets", "meets"),

	/**
	 * The '<em><b>Met By</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MET_BY_VALUE
	 * @generated
	 * @ordered
	 */
	MET_BY(3, "metBy", "metBy"),

	/**
	 * The '<em><b>Overlaps</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OVERLAPS_VALUE
	 * @generated
	 * @ordered
	 */
	OVERLAPS(4, "overlaps", "overlaps"),

	/**
	 * The '<em><b>Overlapped By</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OVERLAPPED_BY_VALUE
	 * @generated
	 * @ordered
	 */
	OVERLAPPED_BY(5, "overlappedBy", "overlappedBy"),

	/**
	 * The '<em><b>Finishes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FINISHES_VALUE
	 * @generated
	 * @ordered
	 */
	FINISHES(6, "finishes", "finishes"),

	/**
	 * The '<em><b>Finished By</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FINISHED_BY_VALUE
	 * @generated
	 * @ordered
	 */
	FINISHED_BY(7, "finishedBy", "finishedBy"),

	/**
	 * The '<em><b>Includes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCLUDES_VALUE
	 * @generated
	 * @ordered
	 */
	INCLUDES(8, "includes", "includes"),

	/**
	 * The '<em><b>During</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DURING_VALUE
	 * @generated
	 * @ordered
	 */
	DURING(9, "during", "during"),

	/**
	 * The '<em><b>Starts</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTS_VALUE
	 * @generated
	 * @ordered
	 */
	STARTS(10, "starts", "starts"),

	/**
	 * The '<em><b>Started By</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTED_BY_VALUE
	 * @generated
	 * @ordered
	 */
	STARTED_BY(11, "startedBy", "startedBy"),

	/**
	 * The '<em><b>Coincides</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COINCIDES_VALUE
	 * @generated
	 * @ordered
	 */
	COINCIDES(12, "coincides", "coincides");

	/**
	 * The '<em><b>Before</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Before</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BEFORE
	 * @model name="before"
	 * @generated
	 * @ordered
	 */
	public static final int BEFORE_VALUE = 0;

	/**
	 * The '<em><b>After</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>After</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AFTER
	 * @model name="after"
	 * @generated
	 * @ordered
	 */
	public static final int AFTER_VALUE = 1;

	/**
	 * The '<em><b>Meets</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Meets</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MEETS
	 * @model name="meets"
	 * @generated
	 * @ordered
	 */
	public static final int MEETS_VALUE = 2;

	/**
	 * The '<em><b>Met By</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Met By</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MET_BY
	 * @model name="metBy"
	 * @generated
	 * @ordered
	 */
	public static final int MET_BY_VALUE = 3;

	/**
	 * The '<em><b>Overlaps</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Overlaps</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OVERLAPS
	 * @model name="overlaps"
	 * @generated
	 * @ordered
	 */
	public static final int OVERLAPS_VALUE = 4;

	/**
	 * The '<em><b>Overlapped By</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Overlapped By</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OVERLAPPED_BY
	 * @model name="overlappedBy"
	 * @generated
	 * @ordered
	 */
	public static final int OVERLAPPED_BY_VALUE = 5;

	/**
	 * The '<em><b>Finishes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Finishes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FINISHES
	 * @model name="finishes"
	 * @generated
	 * @ordered
	 */
	public static final int FINISHES_VALUE = 6;

	/**
	 * The '<em><b>Finished By</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Finished By</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FINISHED_BY
	 * @model name="finishedBy"
	 * @generated
	 * @ordered
	 */
	public static final int FINISHED_BY_VALUE = 7;

	/**
	 * The '<em><b>Includes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Includes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCLUDES
	 * @model name="includes"
	 * @generated
	 * @ordered
	 */
	public static final int INCLUDES_VALUE = 8;

	/**
	 * The '<em><b>During</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>During</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DURING
	 * @model name="during"
	 * @generated
	 * @ordered
	 */
	public static final int DURING_VALUE = 9;

	/**
	 * The '<em><b>Starts</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Starts</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTS
	 * @model name="starts"
	 * @generated
	 * @ordered
	 */
	public static final int STARTS_VALUE = 10;

	/**
	 * The '<em><b>Started By</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Started By</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTED_BY
	 * @model name="startedBy"
	 * @generated
	 * @ordered
	 */
	public static final int STARTED_BY_VALUE = 11;

	/**
	 * The '<em><b>Coincides</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Coincides</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COINCIDES
	 * @model name="coincides"
	 * @generated
	 * @ordered
	 */
	public static final int COINCIDES_VALUE = 12;

	/**
	 * An array of all the '<em><b>Allen Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AllenKind[] VALUES_ARRAY =
		new AllenKind[] {
			BEFORE,
			AFTER,
			MEETS,
			MET_BY,
			OVERLAPS,
			OVERLAPPED_BY,
			FINISHES,
			FINISHED_BY,
			INCLUDES,
			DURING,
			STARTS,
			STARTED_BY,
			COINCIDES,
		};

	/**
	 * A public read-only list of all the '<em><b>Allen Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AllenKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Allen Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllenKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AllenKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Allen Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllenKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AllenKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Allen Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllenKind get(int value) {
		switch (value) {
			case BEFORE_VALUE: return BEFORE;
			case AFTER_VALUE: return AFTER;
			case MEETS_VALUE: return MEETS;
			case MET_BY_VALUE: return MET_BY;
			case OVERLAPS_VALUE: return OVERLAPS;
			case OVERLAPPED_BY_VALUE: return OVERLAPPED_BY;
			case FINISHES_VALUE: return FINISHES;
			case FINISHED_BY_VALUE: return FINISHED_BY;
			case INCLUDES_VALUE: return INCLUDES;
			case DURING_VALUE: return DURING;
			case STARTS_VALUE: return STARTS;
			case STARTED_BY_VALUE: return STARTED_BY;
			case COINCIDES_VALUE: return COINCIDES;
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
	private AllenKind(int value, String name, String literal) {
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
	
} //AllenKind
