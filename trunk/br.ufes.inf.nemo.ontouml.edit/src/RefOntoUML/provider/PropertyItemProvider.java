/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package RefOntoUML.provider;


import RefOntoUML.Property;
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
 * This is the item provider adapter for a {@link RefOntoUML.Property} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyItemProvider
	extends StructuralFeatureItemProvider
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
	public PropertyItemProvider(AdapterFactory adapterFactory) {
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

			addIsDerivedPropertyDescriptor(object);
			addIsDerivedUnionPropertyDescriptor(object);
			addDefaultPropertyDescriptor(object);
			addAggregationPropertyDescriptor(object);
			addIsCompositePropertyDescriptor(object);
			addRedefinedPropertyPropertyDescriptor(object);
			addOppositePropertyDescriptor(object);
			addSubsettedPropertyPropertyDescriptor(object);
			addAssociationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Is Derived feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsDerivedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_isDerived_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_isDerived_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_IsDerived(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Derived Union feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsDerivedUnionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_isDerivedUnion_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_isDerivedUnion_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_IsDerivedUnion(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Default feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDefaultPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_default_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_default_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_Default(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Aggregation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAggregationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_aggregation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_aggregation_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_Aggregation(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Is Composite feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsCompositePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_isComposite_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_isComposite_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_IsComposite(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Redefined Property feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRedefinedPropertyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_redefinedProperty_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_redefinedProperty_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_RedefinedProperty(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Opposite feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOppositePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_opposite_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_opposite_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_Opposite(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Subsetted Property feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSubsettedPropertyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_subsettedProperty_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_subsettedProperty_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_SubsettedProperty(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Association feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAssociationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Property_association_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Property_association_feature", "_UI_Property_type"),
				 RefOntoUMLPackage.eINSTANCE.getProperty_Association(),
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
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue());
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
	 * This returns Property.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Property"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Property)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Property_type") :
			getString("_UI_Property_type") + " " + label;
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

		switch (notification.getFeatureID(Property.class)) {
			case RefOntoUMLPackage.PROPERTY__IS_DERIVED:
			case RefOntoUMLPackage.PROPERTY__IS_DERIVED_UNION:
			case RefOntoUMLPackage.PROPERTY__DEFAULT:
			case RefOntoUMLPackage.PROPERTY__AGGREGATION:
			case RefOntoUMLPackage.PROPERTY__IS_COMPOSITE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case RefOntoUMLPackage.PROPERTY__DEFAULT_VALUE:
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
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createOpaqueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createStringExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createComposedMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralInteger()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralDecimal()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralString()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralNull()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
				 RefOntoUMLFactory.eINSTANCE.createInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue(),
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
			childFeature == RefOntoUMLPackage.eINSTANCE.getMultiplicityElement_LowerValue() ||
			childFeature == RefOntoUMLPackage.eINSTANCE.getProperty_DefaultValue();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
