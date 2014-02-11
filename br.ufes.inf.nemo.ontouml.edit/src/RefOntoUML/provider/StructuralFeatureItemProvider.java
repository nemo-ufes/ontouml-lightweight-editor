/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.provider;


import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.StructuralFeature;

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
 * This is the item provider adapter for a {@link RefOntoUML.StructuralFeature} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StructuralFeatureItemProvider
	extends FeatureItemProvider
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
	public StructuralFeatureItemProvider(AdapterFactory adapterFactory) {
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

			addTypePropertyDescriptor(object);
			addIsOrderedPropertyDescriptor(object);
			addIsUniquePropertyDescriptor(object);
			addUpperPropertyDescriptor(object);
			addLowerPropertyDescriptor(object);
			addIsReadOnlyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TypedElement_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TypedElement_type_feature", "_UI_TypedElement_type"),
				 RefOntoUMLPackage.eINSTANCE.getTypedElement_Type(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Ordered feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsOrderedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiplicityElement_isOrdered_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_isOrdered_feature", "_UI_MultiplicityElement_type"),
				 RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Unique feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsUniquePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiplicityElement_isUnique_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_isUnique_feature", "_UI_MultiplicityElement_type"),
				 RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Upper feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUpperPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiplicityElement_upper_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_upper_feature", "_UI_MultiplicityElement_type"),
				 RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_Upper(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Lower feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLowerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiplicityElement_lower_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiplicityElement_lower_feature", "_UI_MultiplicityElement_type"),
				 RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_Lower(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Read Only feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsReadOnlyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StructuralFeature_isReadOnly_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_StructuralFeature_isReadOnly_feature", "_UI_StructuralFeature_type"),
				 RefOntoUMLPackage.eINSTANCE.getStructuralFeature_IsReadOnly(),
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
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue());
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue());
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((StructuralFeature)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_StructuralFeature_type") :
			getString("_UI_StructuralFeature_type") + " " + label;
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

		switch (notification.getFeatureID(StructuralFeature.class)) {
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__IS_ORDERED:
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__IS_UNIQUE:
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__UPPER:
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__LOWER:
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__IS_READ_ONLY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__UPPER_VALUE:
			case RefOntoUMLPackage.STRUCTURAL_FEATURE__LOWER_VALUE:
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
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createOpaqueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createStringExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createComposedMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralInteger()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralDecimal()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralString()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralNull()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralUnlimitedNatural()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createOpaqueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createStringExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createComposedMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralInteger()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralDecimal()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralString()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralNull()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
				 RefOntoUMLFactory.eINSTANCE.createInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(),
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
			childFeature == RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_UpperValue() ||
			childFeature == RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
