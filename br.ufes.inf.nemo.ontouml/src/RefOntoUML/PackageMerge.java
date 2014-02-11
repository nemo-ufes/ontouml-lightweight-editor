/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Merge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A package merge defines how the contents of one package are extended by the contents of another package.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link RefOntoUML.PackageMerge#getMergedPackage <em>Merged Package</em>}</li>
 *   <li>{@link RefOntoUML.PackageMerge#getReceivingPackage <em>Receiving Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see RefOntoUML.RefOntoUMLPackage#getPackageMerge()
 * @model
 * @generated
 */
public interface PackageMerge extends DirectedRelationship {
	/**
	 * Returns the value of the '<em><b>Merged Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Package that is to be merged with the receiving package of the PackageMerge.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merged Package</em>' reference.
	 * @see #setMergedPackage(RefOntoUML.Package)
	 * @see RefOntoUML.RefOntoUMLPackage#getPackageMerge_MergedPackage()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	RefOntoUML.Package getMergedPackage();

	/**
	 * Sets the value of the '{@link RefOntoUML.PackageMerge#getMergedPackage <em>Merged Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merged Package</em>' reference.
	 * @see #getMergedPackage()
	 * @generated
	 */
	void setMergedPackage(RefOntoUML.Package value);

	/**
	 * Returns the value of the '<em><b>Receiving Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link RefOntoUML.Package#getPackageMerge <em>Package Merge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Package that is being extended with the contents of the merged package of the PackageMerge.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Receiving Package</em>' container reference.
	 * @see #setReceivingPackage(RefOntoUML.Package)
	 * @see RefOntoUML.RefOntoUMLPackage#getPackageMerge_ReceivingPackage()
	 * @see RefOntoUML.Package#getPackageMerge
	 * @model opposite="packageMerge" required="true" transient="false" ordered="false"
	 * @generated
	 */
	RefOntoUML.Package getReceivingPackage();

	/**
	 * Sets the value of the '{@link RefOntoUML.PackageMerge#getReceivingPackage <em>Receiving Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receiving Package</em>' container reference.
	 * @see #getReceivingPackage()
	 * @generated
	 */
	void setReceivingPackage(RefOntoUML.Package value);

} // PackageMerge
