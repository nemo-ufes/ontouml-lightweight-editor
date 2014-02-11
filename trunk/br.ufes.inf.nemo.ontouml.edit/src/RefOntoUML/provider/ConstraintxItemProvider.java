/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.provider;


import RefOntoUML.Constraintx;
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
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link RefOntoUML.Constraintx} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConstraintxItemProvider
	extends PackageableElementItemProvider
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
	public ConstraintxItemProvider(AdapterFactory adapterFactory) {
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

			addConstrainedElementPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Constrained Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConstrainedElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Constraintx_constrainedElement_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Constraintx_constrainedElement_feature", "_UI_Constraintx_type"),
				 RefOntoUMLPackage.eINSTANCE.getConstraintx_ConstrainedElement(),
				 true,
				 false,
				 true,
				 null,
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
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification());
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
	 * This returns Constraintx.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Constraintx"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Constraintx)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Constraintx_type") :
			getString("_UI_Constraintx_type") + " " + label;
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

		switch (notification.getFeatureID(Constraintx.class)) {
			case RefOntoUMLPackage.CONSTRAINTX__SPECIFICATION:
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
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createOpaqueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createStringExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createComposedMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralInteger()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralDecimal()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralString()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralNull()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralUnlimitedNatural()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == RefOntoUMLPackage.eINSTANCE.getNamedElement_NameExpression() ||
			childFeature == RefOntoUMLPackage.eINSTANCE.getConstraintx_Specification();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
