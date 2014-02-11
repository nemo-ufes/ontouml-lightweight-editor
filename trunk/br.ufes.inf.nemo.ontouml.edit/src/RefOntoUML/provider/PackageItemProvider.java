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
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link RefOntoUML.Package} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PackageItemProvider
	extends NamespaceItemProvider
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
	public PackageItemProvider(AdapterFactory adapterFactory) {
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

			addOwnedTypePropertyDescriptor(object);
			addNestedPackagePropertyDescriptor(object);
			addNestingPackagePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Owned Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOwnedTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Package_ownedType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Package_ownedType_feature", "_UI_Package_type"),
				 RefOntoUMLPackage.eINSTANCE.getPackage_OwnedType(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Nested Package feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNestedPackagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Package_nestedPackage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Package_nestedPackage_feature", "_UI_Package_type"),
				 RefOntoUMLPackage.eINSTANCE.getPackage_NestedPackage(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Nesting Package feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNestingPackagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Package_nestingPackage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Package_nestingPackage_feature", "_UI_Package_type"),
				 RefOntoUMLPackage.eINSTANCE.getPackage_NestingPackage(),
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
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getPackage_PackageMerge());
			childrenFeatures.add(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement());
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
	 * This returns Package.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Package"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((RefOntoUML.Package)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Package_type") :
			getString("_UI_Package_type") + " " + label;
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

		switch (notification.getFeatureID(RefOntoUML.Package.class)) {
			case RefOntoUMLPackage.PACKAGE__PACKAGE_MERGE:
			case RefOntoUMLPackage.PACKAGE__PACKAGED_ELEMENT:
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
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackageMerge(),
				 RefOntoUMLFactory.eINSTANCE.createPackageMerge()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createPackage()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDependency()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createConstraintx()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createGeneralizationSet()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createOpaqueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createClass()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createModel()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDataType()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createStringExpression()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createInstanceSpecification()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createEnumeration()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createEnumerationLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalEnumeration()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createOrdinalLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createPrimitiveType()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createNominalStructure()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalStructure()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerOrdinalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalOrdinalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerIntervalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalIntervalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerRationalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalRationalDimension()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createMeasurementDomain()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDecimalMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createIntegerMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createComposedMeasurementRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createStringNominalRegion()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralInteger()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralDecimal()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralString()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralNull()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createLiteralUnlimitedNatural()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createSubKind()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createKind()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createQuantity()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createCollective()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createPhase()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createRole()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createCategory()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createRoleMixin()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createMixin()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createMode()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createNominalQuality()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createNonPerceivableQuality()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createPerceivableQuality()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createRelator()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createsubQuantityOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createsubCollectionOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.creatememberOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createcomponentOf()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createCharacterization()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createMediation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createDerivation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createStructuration()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createFormalAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement(),
				 RefOntoUMLFactory.eINSTANCE.createMaterialAssociation()));
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
			childFeature == RefOntoUMLPackage.eINSTANCE.getPackage_PackagedElement() ||
			childFeature == RefOntoUMLPackage.eINSTANCE.getNamespace_OwnedRule();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
