/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.provider;


import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link RefOntoUML.Class} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassItemProvider
	extends ClassifierItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSuperClassPropertyDescriptor(object);
			addIsActivePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Super Class feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSuperClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Class_superClass_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Class_superClass_feature", "_UI_Class_type"),
				 RefOntoUMLPackage.eINSTANCE.getClass_SuperClass(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Active feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsActivePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Class_isActive_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Class_isActive_feature", "_UI_Class_type"),
				 RefOntoUMLPackage.eINSTANCE.getClass_IsActive(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier());
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getClass_OwnedAttribute());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Class.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Class"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((RefOntoUML.Class)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Class_type") :
			getString("_UI_Class_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(RefOntoUML.Class.class)) {
			case RefOntoUMLPackage.CLASS__IS_ACTIVE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case RefOntoUMLPackage.CLASS__NESTED_CLASSIFIER:
			case RefOntoUMLPackage.CLASS__OWNED_ATTRIBUTE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createClass()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createDataType()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createEnumeration()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalEnumeration()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createPrimitiveType()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createNominalStructure()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalStructure()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerOrdinalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalOrdinalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerIntervalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalIntervalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerRationalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalRationalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createMeasurementDomain()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createSubKind()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createKind()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createQuantity()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createCollective()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createPhase()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createRole()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createCategory()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createRoleMixin()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createMixin()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createMode()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createNominalQuality()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createNonPerceivableQuality()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createPerceivableQuality()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createRelator()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createsubQuantityOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createsubCollectionOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.creatememberOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createcomponentOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createCharacterization()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createMediation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createDerivation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createStructuration()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createFormalAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_NestedClassifier(),
				 RefOntoUMLFactory.eINSTANCE.createMaterialAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getClass_OwnedAttribute(),
				 RefOntoUMLFactory.eINSTANCE.createProperty()));
	}

}
