/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mixin Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see RefOntoUML.RefOntoUMLPackage#getMixinClass()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MixinClassConstraint1 MixinClassConstraint2'"
 *        annotation="http://www.eclipse.org/ocl/examples/OCL MixinClassConstraint1='parents()->select( x | x.oclIsKindOf(SortalClass) )->isEmpty()' MixinClassConstraint2='isAbstract'"
 *        annotation="Comments MixinClassConstraint1='A MixinClass cannot have a Sortal parent (kind, quantity, collective, subkind, phase, role)' MixinClassConstraint2='A MixinClass is always Abstract'"
 * @generated
 */
public interface MixinClass extends ObjectClass {
} // MixinClass
