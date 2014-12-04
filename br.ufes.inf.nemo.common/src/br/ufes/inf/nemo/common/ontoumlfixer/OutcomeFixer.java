package br.ufes.inf.nemo.common.ontoumlfixer;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.AggregationKind;
import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.NamedElement;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.CategoryImpl;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.CollectiveImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.GeneralizationImpl;
import RefOntoUML.impl.KindImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.MixinImpl;
import RefOntoUML.impl.ModeImpl;
import RefOntoUML.impl.PhaseImpl;
import RefOntoUML.impl.PrimitiveTypeImpl;
import RefOntoUML.impl.QuantityImpl;
import RefOntoUML.impl.RelatorImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.RoleMixinImpl;
import RefOntoUML.impl.SubKindImpl;
import RefOntoUML.impl.componentOfImpl;
import RefOntoUML.impl.memberOfImpl;
import RefOntoUML.impl.subCollectionOfImpl;
import RefOntoUML.impl.subQuantityOfImpl;
import br.ufes.inf.nemo.common.ontoumlverificator.MultiplicityValidator;

public class OutcomeFixer{

	public enum SpecializationType {
		SUBSET, REDEFINE, SPECIALIZE
	}
	
	public RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	public RefOntoUML.Package root;

	/** Constructor */
	public OutcomeFixer(RefOntoUML.Package root) {
		this.root = root;
	}

	/** Create class from stereotype. */
	public RefOntoUML.PackageableElement createClass(ClassStereotype stereo) 
	{
		RefOntoUML.PackageableElement type = null;
		if (stereo.equals(ClassStereotype.KIND)) type = factory.createKind();
		if (stereo.equals(ClassStereotype.COLLECTIVE)) type = factory.createCollective();
		if (stereo.equals(ClassStereotype.QUANTITY)) type = factory.createQuantity();
		if (stereo.equals(ClassStereotype.SUBKIND)) type = factory.createSubKind();
		if (stereo.equals(ClassStereotype.PHASE)) type = factory.createPhase();
		if (stereo.equals(ClassStereotype.ROLE)) type = factory.createRole();
		if (stereo.equals(ClassStereotype.CATEGORY)) { type = factory.createCategory(); ((Classifier) type).setIsAbstract(true); }
		if (stereo.equals(ClassStereotype.ROLEMIXIN)) { type = factory.createRoleMixin(); ((Classifier) type).setIsAbstract(true); }
		if (stereo.equals(ClassStereotype.MIXIN)) { type = factory.createMixin(); ((Classifier) type).setIsAbstract(true); }
		if (stereo.equals(ClassStereotype.MODE)) { type = factory.createMode(); }
		if (stereo.equals(ClassStereotype.RELATOR)) { type = factory.createRelator(); }
		if (stereo.equals(ClassStereotype.DATATYPE)) { type = factory.createDataType(); }
		if (stereo.equals(ClassStereotype.PRIMITIVETYPE)) { type = factory.createPrimitiveType(); }
		type.setName("");
		type.setVisibility(VisibilityKind.PUBLIC);
		return type;
	}
	
	/** Create relationship from stereotype */
	public RefOntoUML.Relationship createRelationship(RelationStereotype stereo) 
	{
		RefOntoUML.Relationship rel = null;
		if (stereo.equals(RelationStereotype.GENERALIZATION)) rel = factory.createGeneralization();
		if (stereo.equals(RelationStereotype.CHARACTERIZATION)) rel = factory.createCharacterization();
		if (stereo.equals(RelationStereotype.FORMAL)) rel = factory.createFormalAssociation();
		if (stereo.equals(RelationStereotype.MATERIAL)) { rel = factory.createMaterialAssociation(); ((MaterialAssociation) rel).setIsDerived(true); }
		if (stereo.equals(RelationStereotype.MEDIATION)) rel = factory.createMediation();
		if (stereo.equals(RelationStereotype.MEMBEROF)) { rel = factory.creatememberOf(); ((memberOf) rel).setIsShareable(true); }
		if (stereo.equals(RelationStereotype.SUBQUANTITYOF)) { rel = factory.createsubQuantityOf(); ((subQuantityOf) rel).setIsShareable(false); }
		if (stereo.equals(RelationStereotype.SUBCOLLECTIONOF)) { rel = factory.createsubCollectionOf(); ((subCollectionOf) rel).setIsShareable(true); }
		if (stereo.equals(RelationStereotype.COMPONENTOF)) { rel = factory.createcomponentOf(); ((componentOf) rel).setIsShareable(true); }
		if (stereo.equals(RelationStereotype.DERIVATION)) rel = factory.createDerivation();
		if (stereo.equals(RelationStereotype.ASSOCIATION)) rel = factory.createAssociation();
		if (rel instanceof Classifier) {
			((Classifier) rel).setName("");
			((Classifier) rel).setVisibility(VisibilityKind.PUBLIC);
		}
		return rel;
	}

	/** Create a basic generalization Set with an empty name */
	private GeneralizationSet createBasicGeneralizationSet(boolean isDisjoint, boolean isCovering) 
	{
		GeneralizationSet gs = factory.createGeneralizationSet();
		gs.setIsCovering(isCovering);
		gs.setIsDisjoint(isDisjoint);
		gs.setName("");
		return gs;
	}

	/** Creates an association with default properties */
	public RefOntoUML.Relationship createAssociationWithProperties(RelationStereotype stereo) 
	{
		if (stereo == RelationStereotype.GENERALIZATION) return null;
		Association association = (RefOntoUML.Association) createRelationship(stereo);
		createPropertiesByDefault(association);
		return association;
	}

	/**
	 * Set the properties of this association to 'source' and 'target'
	 * properties.
	 */
	public void setEnds(Association association, Property srcProperty, Property tgtProperty) 
	{
		association.getOwnedEnd().add(srcProperty);
		association.getOwnedEnd().add(tgtProperty);
		association.getMemberEnd().add(srcProperty);
		association.getMemberEnd().add(tgtProperty);
		if (association instanceof DirectedBinaryAssociationImpl || association instanceof FormalAssociationImpl || association instanceof MaterialAssociationImpl) 
		{
			association.getNavigableOwnedEnd().add(srcProperty);
			association.getNavigableOwnedEnd().add(tgtProperty);
		} else {
			if (srcProperty.getType() instanceof DataTypeImpl) association.getNavigableOwnedEnd().add(srcProperty);
			if (tgtProperty.getType() instanceof DataTypeImpl) association.getNavigableOwnedEnd().add(tgtProperty);
		}
	}

	/** Invert sides of an association */
	public Fix invertEnds(Relationship relationship)
	{
		Fix fix = new Fix();
		
		if(relationship instanceof Association){
			Association association = (Association) relationship;
			Property src = association.getMemberEnd().get(0);
			Property tgt = association.getMemberEnd().get(1);
			
			Type type = src.getType();
			src.setType(tgt.getType());
			tgt.setType(type);
			
			String name = src.getName();
			src.setName(tgt.getName());
			tgt.setName(name);
			
			boolean isDerived = src.isIsDerived();
			src.setIsDerived(tgt.isIsDerived());
			tgt.setIsDerived(isDerived);
			
			boolean isReadOnly = src.isIsReadOnly();
			src.setIsReadOnly(tgt.isIsReadOnly());
			tgt.setIsReadOnly(isReadOnly);
			
			boolean isUnique = src.isIsUnique();
			src.setIsUnique(tgt.isIsUnique());
			tgt.setIsUnique(isUnique);
			
			boolean isOrdered = src.isIsOrdered();
			src.setIsOrdered(tgt.isIsOrdered());
			tgt.setIsOrdered(isOrdered);
			
			AggregationKind aggregation = src.getAggregation();
			src.setAggregation(tgt.getAggregation());
			tgt.setAggregation(aggregation);
			
			int lower = src.getLower();
			int upper = src.getUpper();
			changePropertyMultiplicity(src, tgt.getLower(), tgt.getUpper(), false);
			changePropertyMultiplicity(src, lower, upper, false);
			
			ArrayList<Property> redefined = new ArrayList<Property>(src.getRedefinedProperty());
			ArrayList<Property> subsetted = new ArrayList<Property>(src.getSubsettedProperty());
			
			src.getRedefinedProperty().clear();
			src.getRedefinedProperty().addAll(tgt.getRedefinedProperty());
			src.getSubsettedProperty().clear();
			src.getSubsettedProperty().addAll(tgt.getSubsettedProperty());
			
			tgt.getRedefinedProperty().clear();
			tgt.getRedefinedProperty().addAll(redefined);
			tgt.getSubsettedProperty().clear();
			tgt.getSubsettedProperty().addAll(subsetted);
		}
		
		if(relationship instanceof Meronymic){
		
			Property source = ((Meronymic) relationship).getMemberEnd().get(0), target = ((Meronymic) relationship).getMemberEnd().get(1);
			
			if(source.getAggregation()==AggregationKind.NONE){
				if(target.getAggregation()==AggregationKind.NONE || target.getAggregation()==null){
					source.setAggregation(AggregationKind.COMPOSITE);
				}			
				else{
					source.setAggregation(target.getAggregation());
				}
			}
			
			target.setAggregation(AggregationKind.NONE);
		}
		
		if(relationship instanceof Generalization){
			Generalization g = (Generalization)relationship;
			Classifier specific = g.getSpecific();
			Classifier general = g.getGeneral();
			
			g.setGeneral(specific);
			g.setSpecific(general);
		}
		
		fix.includeModified(relationship);
		return fix;
	}
	
	/** Create a Property */
	public Property createProperty(Classifier classifier, int lower, int upper) 
	{
		Property property = factory.createProperty();
		property.setType(classifier);
		LiteralInteger lowerBound = factory.createLiteralInteger();
		lowerBound.setValue(lower);
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		upperBound.setValue(upper);
		property.setLowerValue(lowerBound);
		property.setUpperValue(upperBound);
		return property;
	}

	/** Create a Property */
	public Property createProperty(Classifier classifier, int lower, int upper, String name) {
		Property p = createProperty(classifier, lower, upper);
		p.setName(name);
		return p;
	}

	/** Change a property multiplicity */		
	public Fix changePropertyMultiplicity(Property property, int lower, int upper, boolean addToModified)
	{
		Fix fix = new Fix();	
		LiteralInteger lowerBound = factory.createLiteralInteger();
		lowerBound.setValue(lower);
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		upperBound.setValue(upper);
		property.setUpperValue(upperBound);
		property.setLowerValue(lowerBound);		
		if(addToModified) fix.includeModified(property);	
		return fix;
	}
	
	public static String getPropertyMultiplicity(Property property)
	{
		String strUpper = new String();
		String strLower = new String();
		int upper = property.getUpper();
		int lower = property.getLower();		
		if(upper==-1) strUpper = "*";
		if(lower==-1) strLower = "*";
		if(upper==lower) return strUpper;
		else return strLower+".."+strUpper;		
	}
	
	/** Change a property multiplicity from the format: 1..*, 0..*, 0..-1, 1, 3, and so on and so forth */	
	public Fix changePropertyMultiplicity(Property property, String multiplicity) 
	{
		MultiplicityValidator validator = new MultiplicityValidator(multiplicity);
		int lowerValue;
		int upperValue;
		
		if(validator.isValid()){
			lowerValue = validator.getLower();
			upperValue = validator.getUpper();
		}
		else{
			lowerValue = 1;
			upperValue = 1;
		}
		
		Fix fix = new Fix();	
		LiteralInteger lowerBound = factory.createLiteralInteger();
		lowerBound.setValue(lowerValue);
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		upperBound.setValue(upperValue);
		property.setUpperValue(upperBound);
		property.setLowerValue(lowerBound);		
		fix.includeModified(property);	
		return fix;
	}
	
	/**
	 * Create default properties for this association.
	 */
	public void createPropertiesByDefault(Association association) 
	{
		// creation
		Property node1Property, node2Property;
		node1Property = createProperty(null, 1, 1);
		if (association instanceof componentOfImpl) node2Property = createProperty(null, 2, -1);
		else node2Property = createProperty(null, 1, 1);

		// read only
		if (association instanceof MediationImpl || association instanceof CharacterizationImpl	|| association instanceof DerivationImpl) 
		{
			node2Property.setIsReadOnly(true);
		}

		// aggregation
		if (association instanceof MeronymicImpl) 
		{
			if (((Meronymic) association).isIsShareable()) node1Property.setAggregation(AggregationKind.SHARED);
			else node1Property.setAggregation(AggregationKind.COMPOSITE);
		}

		// name
		String node1Name = new String();
		if (node1Property.getType() != null) 
		{
			node1Name = node1Property.getType().getName();
			if (node1Name == null || node1Name.trim().isEmpty()) node1Name = "source";
			else node1Name = node1Name.trim().toLowerCase();
		}
		String node2Name = new String();
		if (node2Property.getType() != null) 
		{
			node2Name = node2Property.getType().getName();
			if (node2Name == null || node2Name.trim().isEmpty()) node2Name = "target";
			else node2Name = node2Name.trim().toLowerCase();
		}
		node1Property.setName(node1Name);
		node2Property.setName(node2Name);

		// ends
		setEnds(association, node1Property, node2Property);
	}

	/**
	 * Copy multiplicities and type of association's properties to another
	 * association's properties. It also copy the meta-attributes such as
	 * isDerived, isReadOnly, etc.
	 */
	public void copyPropertiesDatas(EObject srcAssoc, EObject receiverAssoc, boolean copyMetaAttributes) 
	{
		if (srcAssoc instanceof RefOntoUML.Association && receiverAssoc instanceof RefOntoUML.Association) 
		{
			RefOntoUML.Association assoc = (RefOntoUML.Association) srcAssoc;
			RefOntoUML.Association rcvAssoc = (RefOntoUML.Association) receiverAssoc;
			copyPropertyData(assoc.getMemberEnd().get(0), rcvAssoc.getMemberEnd().get(0),copyMetaAttributes);
			copyPropertyData(assoc.getMemberEnd().get(1), rcvAssoc.getMemberEnd().get(1),copyMetaAttributes);
		}
	}

	/**
	 * Copy multiplicities and type from a source property to a receiver
	 * property. It also copy the meta-attributes such as isDerived, isReadOnly,
	 * etc.
	 */
	public void copyPropertyData(Property source, Property receiver, boolean copyMetaAttributes) 
	{
		if (source instanceof RefOntoUML.Property && receiver instanceof RefOntoUML.Property) 
		{
			receiver.setType(source.getType());
			LiteralInteger lowerBound = factory.createLiteralInteger();
			lowerBound.setValue(source.getLower());
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(source.getUpper());
			receiver.setUpperValue(upperBound);
			receiver.setLowerValue(lowerBound);
			receiver.setName(source.getName());
			
			receiver.getRedefinedProperty().clear();
			receiver.getRedefinedProperty().addAll(source.getRedefinedProperty());
			receiver.getSubsettedProperty().clear();
			receiver.getSubsettedProperty().addAll(source.getSubsettedProperty());
		
			if (copyMetaAttributes) 
				copyMetaAttributes(source, receiver);
		}
	}
	
	/**
	 * Copy all meta-atributes of an association but the types and names
	 */
	public void copyOnlyMetaProperties(Association source, Association receiver) 
	{
			copyOnlyMetaProperty(source.getMemberEnd().get(0), receiver.getMemberEnd().get(0));
			copyOnlyMetaProperty(source.getMemberEnd().get(1), receiver.getMemberEnd().get(1));
	}

	/**
	 * Copy multiplicities and type from a source property to a receiver
	 * property. It also copy the meta-attributes such as isDerived, isReadOnly,
	 * etc.
	 */
	public void copyOnlyMetaProperty(Property source, Property receiver) 
	{
			LiteralInteger lowerBound = factory.createLiteralInteger();
			lowerBound.setValue(source.getLower());
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(source.getUpper());
			receiver.setUpperValue(upperBound);
			receiver.setLowerValue(lowerBound);
			copyMetaAttributes(source, receiver);
	}

	/**
	 * Copy all the meta-attributes from a source to a receiver:
	 * 
	 * Name, Visibility, IsAbstract, IsDerived, IsLeaf, IsEssential,
	 * IsImmutablePart, IsImmutableWhole, IsInseparable, IsShareable
	 * 
	 * */
	public void copyMetaAttributes(EObject source, EObject receiver) 
	{
		if (source instanceof RefOntoUML.Classifier 	&& receiver instanceof RefOntoUML.Classifier) 
		{
			RefOntoUML.Classifier classifier = (RefOntoUML.Classifier) source;
			RefOntoUML.Classifier rcvClassifier = (RefOntoUML.Classifier) receiver;
			rcvClassifier.setName(classifier.getName());
			(rcvClassifier).setVisibility(classifier.getVisibility());			
			rcvClassifier.setIsLeaf(classifier.isIsLeaf());
			if(rcvClassifier instanceof MixinClass) rcvClassifier.setIsAbstract(true);
			else rcvClassifier.setIsAbstract(false);
		}
		if (source instanceof RefOntoUML.Association && receiver instanceof RefOntoUML.Association) 
		{
			RefOntoUML.Association assoc = (RefOntoUML.Association) source;
			RefOntoUML.Association rcvAssoc = (RefOntoUML.Association) receiver;
			if(rcvAssoc instanceof MaterialAssociation) rcvAssoc.setIsDerived(true);
			else if(rcvAssoc instanceof Derivation) rcvAssoc.setIsDerived(true);
			else rcvAssoc.setIsDerived(assoc.isIsDerived());
		}
		if (source instanceof RefOntoUML.Meronymic && receiver instanceof RefOntoUML.Meronymic) 
		{
			RefOntoUML.Meronymic mer = (RefOntoUML.Meronymic) source;
			RefOntoUML.Meronymic rcvMer = (RefOntoUML.Meronymic) receiver;
			rcvMer.setIsEssential(mer.isIsEssential());
			rcvMer.setIsImmutablePart(mer.isIsImmutablePart());
			rcvMer.setIsImmutableWhole(mer.isIsImmutableWhole());
			rcvMer.setIsInseparable(mer.isIsInseparable());
			if(rcvMer instanceof subQuantityOf) rcvMer.setIsShareable(false);
			else rcvMer.setIsShareable(true);
		}
		if (source instanceof RefOntoUML.GeneralizationSet && receiver instanceof RefOntoUML.GeneralizationSet) 
		{
			RefOntoUML.GeneralizationSet genSet = (RefOntoUML.GeneralizationSet) source;
			RefOntoUML.GeneralizationSet rcvGenSet = (RefOntoUML.GeneralizationSet) receiver;
			rcvGenSet.setIsCovering(genSet.isIsCovering());
			rcvGenSet.setIsDisjoint(genSet.isIsDisjoint());
		}
		if (source instanceof RefOntoUML.Property && receiver instanceof RefOntoUML.Property) 
		{
			RefOntoUML.Property property = (RefOntoUML.Property) source;
			RefOntoUML.Property rcvProperty = (RefOntoUML.Property) receiver;
			rcvProperty.setIsDerived(property.isIsDerived());
			rcvProperty.setIsDerivedUnion(property.isIsDerivedUnion());
			rcvProperty.setIsReadOnly(property.isIsReadOnly());
			rcvProperty.setIsOrdered(property.isIsOrdered());
			rcvProperty.setIsUnique(property.isIsUnique());
			rcvProperty.setIsStatic(property.isIsStatic());
		}
	}

	/** Change all references of type in the association to point to the new type */
	public Fix changeReferencesInAssociation(Association relation, Type type, Type newtype)
	{
		Fix fixes = new Fix();
		if (!(relation instanceof Association)) return fixes;
		Property src = ((Association) relation).getMemberEnd().get(0);
		Property tgt = ((Association) relation).getMemberEnd().get(1);
		if (src.getType().equals(type)) {
			src.setType((Type) newtype);
			fixes.includeModified(src);
			if (src.getName() == null || src.getName().isEmpty()) 
				src.setName(newtype.getName().toLowerCase().trim());
		}
		if (tgt.getType().equals(type)) {
			tgt.setType((Type) newtype);
			fixes.includeModified(tgt);
			if (tgt.getName() == null || tgt.getName().isEmpty()) 
				tgt.setName(newtype.getName().toLowerCase().trim());
		}
		return fixes;
	}
	
	/** Change all references of type in the generalization to point to the new type */
	public Fix changeReferencesInGeneralization(Generalization gen, Type type, Type newtype)
	{
		Fix fix = new Fix();		
		Classifier specific = gen.getSpecific();
		Classifier general = gen.getGeneral();
		if (specific.equals(type)) {
			gen.setSpecific((Classifier) newtype);
			fix.includeModified(gen);
		}
		if (general.equals(type)) {
			gen.setGeneral((Classifier) newtype);
			fix.includeModified(gen);
		}		
		return fix;
	}
	
	/** Change all model references pointing to an element, to point to a new element */
	public Fix changeModelReferences(EObject element, EObject newElement) 
	{
		Fix fix = new Fix();
		if (!(element instanceof Type)) return fix;
		if (!(newElement instanceof Type)) return fix;
		
		TreeIterator<EObject> iterator = root.eAllContents();
		
		while (iterator.hasNext()) 
		{
			EObject content = iterator.next();
			if (content instanceof Association) 
				fix.addAll(changeReferencesInAssociation((Association)content,(Type)element,(Type)newElement));			
			if (content instanceof Generalization) 
				fix.addAll(changeReferencesInGeneralization((Generalization)content, (Type)element, (Type)newElement));
		}
		return fix;
	}

	/**
	 * Copy eContainer from element to the receiver. Both will be in the same
	 * container.
	 */
	public void copyContainer(EObject source, EObject receiver) 
	{
		EObject container = source.eContainer();
		if (container instanceof RefOntoUML.Package) 
		{
			((RefOntoUML.Package) container).getPackagedElement().add((PackageableElement) receiver);
		}
	}

	/** Change a class stereotype */
	public Fix changeClassStereotypeTo(EObject element,	ClassStereotype newStereo) 
	{
		Classifier oldClass = (Classifier)element;
		Fix fixes = new Fix();
		
		// create new element
		Classifier newClass = (Classifier) createClass(newStereo);
		copyMetaAttributes(element, newClass);
		fixes.includeAdded(newClass);
		
		// the same container
		copyContainer(element, newClass);
		fixes.includeModified(element.eContainer());
		
		// move attributes to new class -- doesn't add fix on purpose (source will be deleted and target is being created)
		fixes.addAll(changeAttributeReferences(oldClass, newClass));
		
		// change references (generalizations and associations) to new class
		Fix references = changeModelReferences(element, newClass);
		fixes.includeAllModified(references.getModified());
		
		// delete element
		fixes.addAll(deleteElement(element));
		
		return fixes;
	}

	private Fix changeAttributeReferences(Classifier source, Classifier target) {
		Fix fix = new Fix();
		Iterator<Property> iterator = null;
		
		if(source instanceof Class)
			iterator = ((Class) source).getOwnedAttribute().iterator();
		if(source instanceof DataType)
			iterator = ((DataType) source).getOwnedAttribute().iterator();
		
		while(iterator!=null && iterator.hasNext()){
			Property attr = iterator.next();
			iterator.remove();
			
			if(source instanceof Class)
				((Class) target).getOwnedAttribute().add(attr);
			if(source instanceof DataType)
				((DataType) target).getOwnedAttribute().add(attr);
			
			fix.includeModified(attr);
		}
		
		fix.includeModified(source);
		fix.includeModified(target);
		
		return fix;
		
	}

	/** Delete an element from the model */
	public Fix deleteElement(EObject element) 
	{
		Fix fix = new Fix();
		EcoreUtil.delete(element,true);
		fix.includeDeleted(element);
		return fix;
	}

	/** Get class stereotype */
	public static ClassStereotype getClassStereotype(EObject element) 
	{
		if (element instanceof Kind) return ClassStereotype.KIND;
		else if (element instanceof SubKind) return ClassStereotype.SUBKIND;
		else if (element instanceof Collective) return ClassStereotype.COLLECTIVE;
		else if (element instanceof Quantity) return ClassStereotype.QUANTITY;
		else if (element instanceof Phase) return ClassStereotype.PHASE;
		else if (element instanceof Role) return ClassStereotype.ROLE;
		else if (element instanceof Category) return ClassStereotype.CATEGORY;
		else if (element instanceof Mixin) return ClassStereotype.MIXIN;
		else if (element instanceof RoleMixin) return ClassStereotype.ROLEMIXIN;
		else if (element instanceof Relator) return ClassStereotype.RELATOR;
		else if (element instanceof Mode) return ClassStereotype.MODE;
		else if (element instanceof DataType) return ClassStereotype.DATATYPE;
		else return ClassStereotype.KIND;
	}

	/** Get a default stereotype for a subclass of a given class */
	public static ClassStereotype getDefaultSubtypeStereotype(Class type)
	{
		if (type instanceof Relator) return ClassStereotype.RELATOR;
		if (type instanceof Mode) return ClassStereotype.MODE;
		if (type instanceof DataType) return ClassStereotype.DATATYPE;
		if (type instanceof Category) return ClassStereotype.CATEGORY;
		if (type instanceof RoleMixin) return ClassStereotype.ROLEMIXIN;
		if (type instanceof Mixin) return ClassStereotype.MIXIN;
		if (type instanceof Phase) return ClassStereotype.PHASE;
		if (type instanceof Role) return ClassStereotype.ROLE;
		if (type instanceof Kind || type instanceof Collective || type instanceof Quantity || type instanceof SubKind) return ClassStereotype.SUBKIND;
		else return null;
	}

	public static ClassStereotype getClassStereotype(java.lang.Class<?> type)
	{
		if(type.equals(Relator.class) || type.equals(RelatorImpl.class)) return ClassStereotype.RELATOR;
		if(type.equals(Mode.class) || type.equals(ModeImpl.class)) return ClassStereotype.MODE;
		if(type.equals(DataType.class) || type.equals(DataTypeImpl.class)) return ClassStereotype.DATATYPE;
		if(type.equals(Kind.class) || type.equals(KindImpl.class)) return ClassStereotype.KIND;
		if(type.equals(Collective.class) || type.equals(CollectiveImpl.class)) return ClassStereotype.COLLECTIVE;
		if(type.equals(Quantity.class) || type.equals(QuantityImpl.class)) return ClassStereotype.QUANTITY;
		if(type.equals(SubKind.class) || type.equals(SubKindImpl.class)) return ClassStereotype.SUBKIND;
		if(type.equals(Role.class) || type.equals(RoleImpl.class)) return ClassStereotype.ROLE;
		if(type.equals(Phase.class) || type.equals(PhaseImpl.class)) return ClassStereotype.PHASE;
		if(type.equals(Category.class) || type.equals(CategoryImpl.class)) return ClassStereotype.CATEGORY;
		if(type.equals(RoleMixin.class) || type.equals(RoleMixinImpl.class)) return ClassStereotype.ROLEMIXIN;
		if(type.equals(Mixin.class) || type.equals(MixinImpl.class)) return ClassStereotype.MIXIN;
		if(type.equals(PrimitiveType.class) || type.equals(PrimitiveTypeImpl.class)) return ClassStereotype.PRIMITIVETYPE;
		return null;
	}

	/** Get class stereotype */
	public ClassStereotype getClassStereotype(String stereo) 
	{
		if (stereo.compareToIgnoreCase("Kind")==0) return ClassStereotype.KIND;
		else if (stereo.compareToIgnoreCase("SubKind")==0) return ClassStereotype.SUBKIND;
		else if (stereo.compareToIgnoreCase("Collective")==0) return ClassStereotype.COLLECTIVE;
		else if (stereo.compareToIgnoreCase("Quantity")==0) return ClassStereotype.QUANTITY;
		else if (stereo.compareToIgnoreCase("Phase")==0) return ClassStereotype.PHASE;
		else if (stereo.compareToIgnoreCase("Role")==0) return ClassStereotype.ROLE;
		else if (stereo.compareToIgnoreCase("Category")==0) return ClassStereotype.CATEGORY;
		else if (stereo.compareToIgnoreCase("Mixin")==0) return ClassStereotype.MIXIN;
		else if (stereo.compareToIgnoreCase("RoleMixin")==0) return ClassStereotype.ROLEMIXIN;
		else if (stereo.compareToIgnoreCase("Relator")==0) return ClassStereotype.RELATOR;
		else if (stereo.compareToIgnoreCase("Mode")==0) return ClassStereotype.MODE;
		else if (stereo.compareToIgnoreCase("DataType")==0) return ClassStereotype.DATATYPE;
		else if (stereo.compareToIgnoreCase("PrimitiveType")==0) return ClassStereotype.PRIMITIVETYPE;
		else if (stereo.compareToIgnoreCase("PerceivableQuality")==0) return ClassStereotype.PERCEIVABLEQUALITY;
		else if (stereo.compareToIgnoreCase("NonPerceivableQuality")==0) return ClassStereotype.NONPERCEIVABLEQUALITY;
		else if (stereo.compareToIgnoreCase("NominalQuality")==0) return ClassStereotype.NOMINALQUALITY;
		else return ClassStereotype.KIND;
	}
	
	/** Change element to Role and add a supertype for it */
	public Fix changeClassStereotypeSubtyping(EObject element, ClassStereotype stereo) 
	{
		Fix fixes = new Fix();
		ClassStereotype stereoSupertype = getClassStereotype(element);
		// change to role
		Fix aux = changeClassStereotypeTo(element, stereo);
		EObject newElem = (EObject) aux.getAdded().get(0);
		fixes.addAll(aux);
		// create a supertype for it
		Fix aux2 = createSuperType(newElem, stereoSupertype);
		fixes.addAll(aux2);
		return fixes;
	}

	/** Add a super-type with the given stereotype to the element */
	public Fix createSuperType(EObject element, ClassStereotype stereoSuperType) 
	{
		Fix fixes = new Fix();
		// create supertye
		RefOntoUML.PackageableElement supertype = createClass(stereoSuperType);
		supertype.setName("Supertype");
		fixes.includeAdded(supertype);
		// the same container as
		copyContainer(element, supertype);
		fixes.includeModified(element.eContainer());
		// create generalization
		Generalization gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier) element);
		gen.setGeneral((RefOntoUML.Classifier) supertype);
		fixes.includeAdded(gen);
		return fixes;

	}
	
	

	/**
	 * Add a common supertype to a list of subtypes. It creates a supertype and
	 * all the generalizations linking a subtype to the common supertype.
	 */
	public Fix createCommonSuperType(ArrayList<Classifier> subtypes, ClassStereotype stereoSuperType) 
	{
		Fix fixes = new Fix();
		// create supertye
		RefOntoUML.PackageableElement supertype = createClass(stereoSuperType);
		supertype.setName("CommonSupertype");
		fixes.includeAdded(supertype);
		// the same container as
		copyContainer(subtypes.get(0), supertype);
		fixes.includeModified(subtypes.get(0).eContainer());
		for (Classifier subtype : subtypes) 
		{
			// create generalization
			Generalization gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
			gen.setSpecific(subtype);
			gen.setGeneral((RefOntoUML.Classifier) supertype);
			fixes.includeAdded(gen);
			fixes.includeModified(subtype);
		}
		return fixes;
	}

	/** Change a relationship stereotype */
	public Fix changeRelationStereotypeTo(EObject relationship, RelationStereotype newStereo) 
	{
		Fix fixes = new Fix();		
		// create new relationship
		RefOntoUML.Relationship newRelationship = createAssociationWithProperties(newStereo);		
		copyPropertiesDatas(relationship, newRelationship,true);
		fixes.includeAdded(newRelationship);
		// the same container as
		copyContainer(relationship, newRelationship);
		fixes.includeModified(relationship.eContainer());
		// change references
		Fix references = changeModelReferences(relationship, newRelationship);
		fixes.includeAllModified(references.getModified());		
		// copies name if associations
		if(relationship instanceof Association && newRelationship instanceof Association){
			((Association) newRelationship).setName(((Association) relationship).getName());	
		}		
		// delete element
		fixes.addAll(deleteElement(relationship));		
		
		if(newStereo==RelationStereotype.COMPONENTOF || newStereo==RelationStereotype.MEMBEROF || newStereo==RelationStereotype.SUBCOLLECTIONOF || newStereo==RelationStereotype.SUBQUANTITYOF){
			Property source = ((Association) newRelationship).getMemberEnd().get(0), target = ((Association) newRelationship).getMemberEnd().get(1);
			
			if(source.getAggregation()==AggregationKind.NONE){
				if(target.getAggregation()==AggregationKind.NONE || target.getAggregation()==null){
					source.setAggregation(AggregationKind.COMPOSITE);
				}			
				else{
					source.setAggregation(target.getAggregation());
				}
			}
			
			target.setAggregation(AggregationKind.NONE);
		}
		
		return fixes;
	}

	/** Change a relationship stereotype */
	public Fix changeRelationStereotypeTo(EObject relationship, RelationStereotype newStereo, boolean invertSides) 
	{
		Fix fixes = changeRelationStereotypeTo(relationship, newStereo);
		
		//invert sides
		if(invertSides){
			invertEnds(fixes.getAddedByType(Relationship.class).get(0));
		}
		
		return fixes;
	}
	
	public Fix createSubTypeAs(EObject type, ClassStereotype subtypeStereo, String name){
		Fix fix = createSubTypeAs(type, subtypeStereo);
		fix.getAddedByType(Class.class).get(0).setName(name);
		return fix;
		
	}
	
	/** Create a subtype and connect it through a generalization to its type. */
	public Fix createSubTypeAs(EObject type, ClassStereotype subtypeStereo) 
	{
		Fix fixes = new Fix();
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier) type).getName() + "Subtype");
		copyContainer(type, subtype);
		fixes.includeAdded(subtype);
		// create generalization
		Generalization gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier) subtype);
		gen.setGeneral((RefOntoUML.Classifier) type);
		fixes.includeAdded(gen);
		return fixes;
	}

	public Fix createSuperTypeAs(EObject type, ClassStereotype supertypeStereo, String name){
		Fix fix = createSuperTypeAs(type, supertypeStereo);
		fix.getAddedByType(Class.class).get(0).setName(name);
		return fix;
		
	}
	
	/** Create a supertype and connect it through a generalization to its type. */
	public Fix createSuperTypeAs(EObject type, ClassStereotype supertypeStereo) 
	{
		Fix fixes = new Fix();
		// create subtype
		RefOntoUML.PackageableElement supertype = createClass(supertypeStereo);
		supertype.setName(((Classifier) type).getName() + "Subtype");
		copyContainer(type, supertype);
		fixes.includeAdded(supertype);
		// create generalization
		Generalization gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		gen.setGeneral((RefOntoUML.Classifier) supertype);
		gen.setSpecific((RefOntoUML.Classifier) type);
		fixes.includeAdded(gen);
		return fixes;
	}

	/** Create a new part to the whole via componentOf */
	public Fix createPartWithComponentOfTo(EObject whole, ClassStereotype partStereo, String partName, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole)
	{
		Fix fixes = new Fix();
		// create part
		RefOntoUML.PackageableElement part = createClass(partStereo);
		part.setName(partName);
		copyContainer(whole, part);
		fixes.includeAdded(part);
		// create new relationship
		RefOntoUML.Relationship componentOf = createAssociationWithProperties(RelationStereotype.COMPONENTOF);
		((Association)componentOf).getMemberEnd().get(0).setType((Type)whole);
		((Association)componentOf).getMemberEnd().get(1).setType((Type)part);
		((Association)componentOf).setName(componentOfName);
		((Meronymic)componentOf).setIsEssential(isEssential);
		((Meronymic)componentOf).setIsInseparable(isInseparable);
		((Meronymic)componentOf).setIsShareable(isShareable);
		((Meronymic)componentOf).setIsImmutablePart(isImmutablePart);
		((Meronymic)componentOf).setIsImmutableWhole(isImmutableWhole);
		copyContainer(whole, componentOf);
		fixes.includeAdded(componentOf);
		fixes.includeModified(whole.eContainer());		
		return fixes;
	}
	
	/** Create a sub-part (by specializing the part) and connect the whole to this sub-part with a componentOf */
	public Fix createSubPartWithSubComponentOfTo(EObject whole, Property partEnd,ClassStereotype subpartStereo, String subpartName, String subcomponentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole)
	{
		Fix fixes = new Fix();
		// create sub-part
		Fix fix = createSubTypeAs(partEnd.getType(), subpartStereo);
		RefOntoUML.Type subpart= null;
		for(Object obj: fix.getAdded()) { if(obj instanceof RefOntoUML.Class) subpart = (Classifier)obj; }		
		fixes.addAll(fix);				
		// create new relationship
		RefOntoUML.Relationship subcomponentOf = createAssociationWithProperties(RelationStereotype.COMPONENTOF);
		((Association)subcomponentOf).getMemberEnd().get(0).setType((Type)whole);
		((Association)subcomponentOf).getMemberEnd().get(1).setType((Type)subpart);
		((Association)subcomponentOf).setName(subcomponentOfName);
		((Meronymic)subcomponentOf).setIsEssential(isEssential);
		((Meronymic)subcomponentOf).setIsInseparable(isInseparable);
		((Meronymic)subcomponentOf).setIsShareable(isShareable);
		((Meronymic)subcomponentOf).setIsImmutablePart(isImmutablePart);
		((Meronymic)subcomponentOf).setIsImmutableWhole(isImmutableWhole);
		copyContainer(whole, subcomponentOf);
		fixes.includeAdded(subcomponentOf);
		fixes.includeModified(whole.eContainer());		
		// add {subsets}
		((Association)subcomponentOf).getMemberEnd().get(1).getSubsettedProperty().add(partEnd);
		return fixes;
	}
	
	/** Create a subsetted-componentOf relation to an existing sub-part. Suppose a partOf relation between a Whole and Part. And also a subtype of Part called SubPart. 
	 *  This method link the Whole to the existing SubPart via componentOf relation */ 
	public Fix createSubComponentOfToExistingSubPart(Classifier whole, Property partEnd, Type subpart, String subcomponentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole) 
	{
		Fix fixes = new Fix();
		// create new relationship
		RefOntoUML.Relationship subcomponentOf = createAssociationWithProperties(RelationStereotype.COMPONENTOF);
		((Association)subcomponentOf).getMemberEnd().get(0).setType((Type)whole);
		((Association)subcomponentOf).getMemberEnd().get(1).setType((Type)subpart);
		((Association)subcomponentOf).setName(subcomponentOfName);
		((Meronymic)subcomponentOf).setIsEssential(isEssential);
		((Meronymic)subcomponentOf).setIsInseparable(isInseparable);
		((Meronymic)subcomponentOf).setIsShareable(isShareable);
		((Meronymic)subcomponentOf).setIsImmutablePart(isImmutablePart);
		((Meronymic)subcomponentOf).setIsImmutableWhole(isImmutableWhole);
		copyContainer(whole, subcomponentOf);
		fixes.includeAdded(subcomponentOf);
		fixes.includeModified(whole.eContainer());		
		// add {subsets}
		((Association)subcomponentOf).getMemberEnd().get(1).getSubsettedProperty().add(partEnd);
		
		return fixes;
	}

	
	/**
	 * Create a subtype and connect it through a generalization to its type. It
	 * also change the references in relation to point to the subtype instead of
	 * the type.
	 */
	public Fix createSubTypeAsInvolvingLink(EObject type, ClassStereotype subtypeStereo, EObject relation) 
	{
		Fix fixes = new Fix();
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier) type).getName() + "Subtype");
		copyContainer(type, subtype);
		fixes.includeAdded(subtype);
		// create generalization
		Generalization gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier) subtype);
		gen.setGeneral((RefOntoUML.Classifier) type);
		fixes.includeAdded(gen);
		// change reference in relation
		if (!(relation instanceof Association)) return fixes;
		fixes.addAll(changeReferencesInAssociation((Association)relation, (Type)type, (Type)subtype));		
		return fixes;
	}
	
	public Fix createSuperTypeEnvolvingLink(EObject element, ClassStereotype stereoSuperType, EObject relation){
		Fix fixes = new Fix();
		
		//create supertype
		fixes.addAll(createSuperType(element, stereoSuperType));
		
		//change reference in relation
		if (!(relation instanceof Association)) 
			return fixes;
		
		fixes.addAll(changeReferencesInAssociation((Association)relation, (Type)element, (Type)fixes.getAddedByType(Type.class).get(0)));
		return fixes;
	}

	/**
	 * Creates ad subtype and a sub-subtype. It also change the references in
	 * the relation 'link' to point to the sub-subtype instead of the type.
	 */
	public Fix createSubSubTypeAsInvolvingLink(EObject type, ClassStereotype subtypeStereo, ClassStereotype subsubtypeStereo, Association link) 
	{
		Fix fixes = new Fix();
		// create intermediate
		RefOntoUML.PackageableElement intermediate = createClass(subsubtypeStereo);
		intermediate.setName(((Classifier) type).getName() + "Intermediate");
		copyContainer(type, intermediate);
		fixes.includeAdded(intermediate);
		// create Generalization
		Generalization gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier) intermediate);
		gen.setGeneral((RefOntoUML.Classifier) type);
		fixes.includeAdded(gen);
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier) type).getName() + "Subtype");
		copyContainer(type, subtype);
		fixes.includeAdded(subtype);
		// create generalization
		gen = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier) subtype);
		gen.setGeneral((RefOntoUML.Classifier) intermediate);
		fixes.includeAdded(gen);
		//change references in a relation
		fixes.addAll(changeReferencesInAssociation(link, (Type)type, (Type)subtype));
		return fixes;
	}

	/** Generate an OCL invariant */
	public Fix generateOCLInvariant(String contextName, String invName, String invExpr) 
	{
		String oclRule = "context _'" + contextName + "'\n" + "inv " + invName+ " : " + invExpr;
		Fix fix = new Fix();
		fix.includeRule(oclRule);
		return fix;
	}
	
	/** Generate an OCL derivation */
	public Fix generateOCLDerivation(String typeName, String propertyName, String propertyReturnType, String deriveExpr)
	{
		String oclRule = "context _'" + typeName+"::_'"+propertyName+"':" +propertyReturnType+ "\n" + "derive: " + deriveExpr;
		Fix fix = new Fix();
		fix.includeRule(oclRule);
		return fix;
	}
	
	/**
	 * Create a default generalization set which is covering=true and
	 * disjoint=true and its name is null
	 * @param isDisjoint TODO
	 * @param isCovering TODO
	 */
	public Fix createGeneralizationSet(ArrayList<Generalization> generalizations, boolean isDisjoint, boolean isCovering) 
	{
		return createGeneralizationSet(generalizations, isDisjoint, isCovering, null);
	}

	/** Create a default generalization set from a list of generalizations */
	public Fix createGeneralizationSet(ArrayList<Generalization> generalizations, boolean isDisjoint, boolean isCovering, String gsName) 
	{
		Fix fix = new Fix();
		// create generalization set
		GeneralizationSet gs = createBasicGeneralizationSet(isDisjoint,isCovering);
		gs.getGeneralization().addAll(generalizations);
		for(Generalization g: generalizations) { g.getGeneralizationSet().add(gs); }
		if (gsName != null) gs.setName(gsName);
		else gs.setName("NewGS_" + generalizations.get(0).getGeneral().getName());
		fix.includeAdded(gs);
		// modified all generalizations
		for (Generalization generalization : generalizations) 
		{
			fix.includeModified(generalization);
		}
		// same container as...
		copyContainer(generalizations.get(0).getGeneral(), gs);
		return fix;
	}

	/**
	 * Create a generalization set between a supertype and a list of subtypes.
	 * Note that this method assumes the existence of generalizations between
	 * the supertype and all the subtypes
	 * @param isDisjoint TODO
	 * @param isCovering TODO
	 */
	public Fix createGeneralizationSet(Classifier supertype, ArrayList<Classifier> subtypes, boolean isDisjoint, boolean isCovering) 
	{
		// get existent generalizations
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		for (Classifier c : subtypes) 
		{
			for (Generalization g : c.getGeneralization()) 
			{
				if (g.getGeneral().equals(supertype) || g.getGeneral().allParents().contains(supertype)) generalizations.add(g);
			}
		}
		// create generalization set from the generalizations
		return createGeneralizationSet(generalizations, isDisjoint, isCovering);
	}

	/** Create a Generalization from a specific and general classifiers */
	public Fix createGeneralization(Classifier specific, Classifier general) 
	{
		Fix fix = new Fix();
		// create generalization
		Generalization g = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		g.setGeneral(general);
		g.setSpecific(specific);
		fix.includeAdded(g);		
		return fix;
	}

	/** Get relationship stereotype from element */
	public RelationStereotype getRelationshipStereotype(EObject object) 
	{
		if (object instanceof Mediation) return RelationStereotype.MEDIATION;
		if (object instanceof Characterization) return RelationStereotype.CHARACTERIZATION;
		if (object instanceof memberOf) return RelationStereotype.MEMBEROF;
		if (object instanceof componentOf) return RelationStereotype.COMPONENTOF;
		if (object instanceof subCollectionOf) return RelationStereotype.SUBCOLLECTIONOF;
		if (object instanceof subQuantityOf) return RelationStereotype.SUBQUANTITYOF;
		if (object instanceof FormalAssociation) return RelationStereotype.FORMAL;
		if (object instanceof MaterialAssociation) return RelationStereotype.MATERIAL;
		if (object instanceof Derivation) return RelationStereotype.DERIVATION;		
		if (object instanceof Generalization) return RelationStereotype.GENERALIZATION;
		if (object instanceof Association) return RelationStereotype.ASSOCIATION;
		return RelationStereotype.ASSOCIATION;
	}

	/** Get relationship stereotype from element */
	public RelationStereotype getRelationshipStereotype(String stereo) 
	{
		if (stereo.compareToIgnoreCase("Mediation")==0)  return RelationStereotype.MEDIATION;
		else if (stereo.compareToIgnoreCase("Characterization")==0) return RelationStereotype.CHARACTERIZATION;
		else if (stereo.compareToIgnoreCase("MemberOf")==0) return RelationStereotype.MEMBEROF;
		else if (stereo.compareToIgnoreCase("ComponentOf")==0) return RelationStereotype.COMPONENTOF;
		else if (stereo.compareToIgnoreCase("SubCollectionOf")==0) return RelationStereotype.SUBCOLLECTIONOF;
		else if (stereo.compareToIgnoreCase("SubQuantityOf")==0) return RelationStereotype.SUBQUANTITYOF;
		else if (stereo.compareToIgnoreCase("Formal")==0) return RelationStereotype.FORMAL;
		else if (stereo.compareToIgnoreCase("Material")==0) return RelationStereotype.MATERIAL;
		else if (stereo.compareToIgnoreCase("Derivation")==0) return RelationStereotype.DERIVATION;		
		else if (stereo.compareToIgnoreCase("Generalization")==0) return RelationStereotype.GENERALIZATION;
		else if (stereo.compareToIgnoreCase("Structuration")==0) return RelationStereotype.STRUCTURATION;
		else if (stereo.compareToIgnoreCase("Association")==0) return RelationStereotype.ASSOCIATION;
		return RelationStereotype.ASSOCIATION;
	}
	
	public static RelationStereotype getRelationStereotype(java.lang.Class<?> type)
	{		
		if(type.equals(Mediation.class) || type.equals(MediationImpl.class)) return RelationStereotype.MEDIATION;
		if(type.equals(Characterization.class) || type.equals(CharacterizationImpl.class)) return RelationStereotype.CHARACTERIZATION;
		if(type.equals(MaterialAssociation.class) || type.equals(MaterialAssociationImpl.class)) return RelationStereotype.MATERIAL;
		if(type.equals(FormalAssociation.class) || type.equals(FormalAssociationImpl.class)) return RelationStereotype.FORMAL;
		if(type.equals(componentOf.class) || type.equals(componentOfImpl.class)) return RelationStereotype.COMPONENTOF;
		if(type.equals(memberOf.class) || type.equals(memberOfImpl.class)) return RelationStereotype.MEMBEROF;
		if(type.equals(subCollectionOf.class) || type.equals(subCollectionOfImpl.class)) return RelationStereotype.SUBCOLLECTIONOF;
		if(type.equals(subQuantityOf.class) || type.equals(subQuantityOfImpl.class)) return RelationStereotype.SUBQUANTITYOF;
		if(type.equals(Derivation.class) || type.equals(DerivationImpl.class)) return RelationStereotype.DERIVATION;
		if(type.equals(Generalization.class) || type.equals(GeneralizationImpl.class)) return RelationStereotype.GENERALIZATION;
		if(type.equals(Association.class) || type.equals(AssociationImpl.class)) return RelationStereotype.ASSOCIATION;		
		return null;
	}
	
	/** Set up the upper cardinality on relator's side */
	public Fix setUpperCardinalityOnRelatorSide(Mediation m, int upper) 
	{
		Fix fix = new Fix();
		Type source = m.getMemberEnd().get(0).getType();
		Type target = m.getMemberEnd().get(1).getType();
		if (source instanceof Relator) 
		{
			// change upper value of property
			Property src = m.getMemberEnd().get(0);
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(upper);
			src.setUpperValue(upperBound);
			fix.includeModified(src);
		}
		if (target instanceof Relator) 
		{
			// change upper value of property
			Property tgt = m.getMemberEnd().get(1);
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(upper);
			tgt.setUpperValue(upperBound);
			fix.includeModified(tgt);
		}
		return fix;
	}

	/** Set up the upper cardinality on  mediated side */
	public Fix setUpperCardinalityOnMediatedSide(Mediation m, int upper) 
	{
		Fix fix = new Fix();
		Type source = m.getMemberEnd().get(0).getType();
		Type target = m.getMemberEnd().get(1).getType();
		if (!(source instanceof Relator)) 
		{
			// change upper value of property
			Property src = m.getMemberEnd().get(0);
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(upper);
			src.setUpperValue(upperBound);
			fix.includeModified(src);
		}
		if (!(target instanceof Relator)) 
		{
			// change upper value of property
			Property tgt = m.getMemberEnd().get(1);
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(upper);
			tgt.setUpperValue(upperBound);
			fix.includeModified(tgt);
		}
		return fix;
	}
	
	/**
	 * Verifies if there is already a element in the model with the same name as
	 * the object obj. If true, it returns that element.
	 */
	public RefOntoUML.PackageableElement containsNameBased(RefOntoUML.Package root, EObject obj) 
	{
		for (PackageableElement pe : root.getPackagedElement()) 
		{
			if (pe instanceof DataType) {
				if (pe.getName().trim().compareToIgnoreCase(((NamedElement) obj).getName()) == 0) return pe;
			}
			if (pe instanceof RefOntoUML.Package|| pe instanceof RefOntoUML.Model)
			{
				return containsNameBased((Package) pe, obj);
			}
		}
		return null;
	}

	/** Create an attribute in the class 'owner' */
	public Fix createAttribute(EObject owner, String attrName, ClassStereotype attrType, String attrTypeName) 
	{
		Fix fix = new Fix();
		// create type
		EObject type = createClass(attrType);
		((NamedElement) type).setName(attrTypeName);
		// verify if it already exists
		RefOntoUML.NamedElement elem = (NamedElement) containsNameBased(root,type);
		if (elem == null) {
			copyContainer(owner, type);
			fix.includeAdded(type);
			fix.includeModified(owner.eContainer());
		} else {
			type = elem;
		}
		// create the attribute (property)
		Property attr = createProperty((Classifier) type, 1, 1);
		attr.setType((Type) type);
		attr.setName(attrName);
		fix.includeAdded(attr);
		// include it on object
		if (owner instanceof RefOntoUML.Class) ((RefOntoUML.Class) owner).getOwnedAttribute().add(attr);
		if (owner instanceof RefOntoUML.DataType) ((RefOntoUML.DataType) owner).getOwnedAttribute().add(attr);
		fix.includeModified(owner);
		return fix;
	}

	/** Create an association between the types. */
	public Fix createAssociationBetween(RelationStereotype stereo, String relName, Type source, Type target) 
	{
		Fix fix = new Fix();
		// create assoc
		EObject rel = createRelationship(stereo);
		((NamedElement) rel).setName(relName);
		fix.includeAdded(rel);
		// same container
		copyContainer(source, rel);
		fix.includeModified(source.eContainer());
		// creating and adding assoc ends...
		Property srcProperty = createProperty((Classifier) source, 1, 1, source.getName().trim().toLowerCase());
		Property tgtProperty = createProperty((Classifier) target, 1, 1, target.getName().trim().toLowerCase());
		setEnds((Association) rel, srcProperty, tgtProperty);
		if(stereo == RelationStereotype.MEDIATION) tgtProperty.setIsReadOnly(true);
		if(stereo == RelationStereotype.CHARACTERIZATION) tgtProperty.setIsReadOnly(true);
		if(stereo == RelationStereotype.DERIVATION) tgtProperty.setIsReadOnly(true);
		return fix;
	}

	/** Create new mediated types in the relator from a mapping containing the name and stereotype of the new mediated type. */
	public Fix createNewMediatedTypes(Relator relator, HashMap<String,String> nameAndStereotypeMap) 
	{
		Fix fix = new Fix();
		for(String name: nameAndStereotypeMap.keySet()){
			String stereo = nameAndStereotypeMap.get(name);
			ClassStereotype classStereo = getClassStereotype(stereo);
			//create new class with given stereotype
			EObject obj = createClass(classStereo);
			((NamedElement) obj).setName(name);
			fix.includeAdded(obj);
			//same container as...
			copyContainer(relator, obj);
			fix.includeModified(relator.eContainer());
			//create mediation
			EObject rel = createAssociationWithProperties(RelationStereotype.MEDIATION);
			fix.includeAdded(rel);
			//the same container as...
			copyContainer(relator, rel);
			fix.includeModified(relator.eContainer());
			// set types on mediation sides
			((Association)rel).setName("med_"+relator.getName()+"_"+((Type)obj).getName());
			((Association)rel).getMemberEnd().get(0).setType(relator);
			((Association)rel).getMemberEnd().get(1).setType((Type)obj);
		}
		return fix;
	}

	/** Convenient method for getting the stereotype of an refontouml element */
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}	
	
	/** Change stereotype of all relations, the nature of the source and the nature of the target types*/
	public Fix changeAllRelationsTo(ArrayList<? extends Association> list, RelationStereotype relStereo, ClassStereotype sourceNature, ClassStereotype targetNature)
	{
		Fix fix = new Fix();		
		
		for (Association a : list) {
			
			if(sourceNature!=null){
				Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
				fix.addAll(changeNature(source, new ArrayList<Classifier>(), sourceNature));
			}
			
			if(targetNature!=null){
				Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
				fix.addAll(changeNature(target, new ArrayList<Classifier>(), targetNature));
			}
			
			if(getRelationshipStereotype(a)!=relStereo)
				fix.addAll(changeRelationStereotypeTo(a, relStereo));		
		}
		
		return fix;
	}
	
	/** Convenient method for getting duplicated elements of a list */
	@SuppressWarnings({ "rawtypes", "hiding", "serial" })
	public static <T,Integer> HashMap getDuplicated(Collection<T> list) {

	    final HashMap<T,Integer> duplicatedObjects = new HashMap<T,Integer>();
	    Set<T> set = new HashSet<T>() {
	    @SuppressWarnings("unchecked")
		@Override
	    public boolean add(T e) {
	        if (!contains(e)) {
	            duplicatedObjects.put(e, (Integer) new java.lang.Integer(0));
	        }else{
	        	int actual = (java.lang.Integer) duplicatedObjects.get(e);
	        	duplicatedObjects.put(e, (Integer) new java.lang.Integer(actual+1));	        	
	        }
	        return super.add(e);
	    }
	    };
	   for (T t : list) {
	        set.add(t);
	    }
	    return duplicatedObjects;
	}
	
	/** Get direct super types in common */
	@SuppressWarnings("unchecked")
	public ArrayList<Classifier> getDirectSuperTypeInCommon (ArrayList<Classifier> typeList)
	{
		ArrayList<Classifier> result = new ArrayList<Classifier>();
		
		// unite all direct parents in one list
		ArrayList<Classifier> directParentsList = new ArrayList<Classifier>();
		for(Classifier c: typeList) directParentsList.addAll(c.parents()); 
		
		// get the duplicated elements and the number of duplicates from the united direct parents list
		HashMap<Classifier ,Integer> map = getDuplicated(directParentsList);
		
		// give the result
		for(Classifier c: map.keySet()){
			int n = map.get(c);
			if (n==typeList.size()) result.add(c);
		}
		
		return result;
	}
	
	/** Create a super-type in common to a list of classes using a particular algorithm */
	public Fix createSuperTypeInCommonTo(ArrayList<Classifier> typeList, String superTypeName)
	{
		Fix fix = new Fix();
		
		// if all rigid, then create a category
		// if all anti-rigid, then create a roleMixin
		// if rigid and anti-rigid mixed, then create a Mixin
		// if all subkind and a super-type in common is found, then create a super-subkind, and specialize it from the former super-type
		// if all role and a super-type in common is found, then create a super-role, and specialize it from the former super-type
		// if all phase and a super-type in common is found, then create a super-phase, and specialize it from the former super-type
		
		int nRigid=0,nAntiRigid=0,nSubKind=0,nRole=0,nPhase=0;
		for(Classifier obj: typeList)
		{			
			if (obj instanceof RigidSortalClass || obj instanceof RigidMixinClass) nRigid++;			
			if (obj instanceof AntiRigidSortalClass || obj instanceof AntiRigidMixinClass) nAntiRigid++;
			if (obj instanceof SubKind) nSubKind++;
			if (obj instanceof Role) nRole++;
			if (obj instanceof Phase) nPhase++;
		}
		// create new Class
		EObject newSuperType = null;
		if (nSubKind==typeList.size()) { 
			Classifier supertype = getDirectSuperTypeInCommon(typeList).get(0); 
			if (supertype!=null) newSuperType = createClass(ClassStereotype.SUBKIND); 
			if (supertype!=null && newSuperType!=null) fix.addAll(createGeneralization((Classifier)newSuperType, supertype));
		}
		else if (nRole==typeList.size()) { 
			Classifier supertype = getDirectSuperTypeInCommon(typeList).get(0); 
			if (supertype!=null) newSuperType = createClass(ClassStereotype.ROLE); 
			if (supertype!=null && newSuperType!=null) fix.addAll(createGeneralization((Classifier)newSuperType, supertype));
		}
		else if (nPhase==typeList.size()) { 
			Classifier supertype = getDirectSuperTypeInCommon(typeList).get(0); 
			if (supertype!=null) newSuperType = createClass(ClassStereotype.PHASE); 
			if (supertype!=null && newSuperType!=null) fix.addAll(createGeneralization((Classifier)newSuperType, supertype));
		}
		else if(nRigid==typeList.size()) {
			newSuperType = createClass(ClassStereotype.CATEGORY);
		}
		else if(nAntiRigid==typeList.size()) {
			newSuperType = createClass(ClassStereotype.ROLEMIXIN);
		}
		else if (nRigid>0 && nAntiRigid >0) {
			newSuperType = createClass(ClassStereotype.MIXIN);	
		}
		if (newSuperType!=null){
			((Classifier)newSuperType).setName(superTypeName);
			fix.includeAdded(newSuperType);
			// the same container
			copyContainer(typeList.get(0), newSuperType);
			fix.includeModified(typeList.get(0).eContainer());	
		}
		//create the generalizations from the classes to the new SuperType created 
		for(Classifier obj: typeList){
			fix.addAll(createGeneralization((Classifier)obj, (Classifier)newSuperType));
		}
		return fix;
	}
	
	/** Create a middle part between a partOf relation i.e., Whole ->(memberOf)-> MiddelPart -> Part1,Part2,...,PartN*/
	public Fix changeAllToOneSuperMember(ArrayList<Association> partOfList) 
	{	
		Fix fix = new Fix();
	
		//create a super type in common to all the parts, named MemberPart
		ArrayList<Classifier> parts = new ArrayList<Classifier>();
		Classifier whole = ((Meronymic)partOfList.get(0)).whole();
		for(Association assoc: partOfList){
			parts.add(((Meronymic)assoc).part());
		}
		Fix partialFix = createSuperTypeInCommonTo(parts,"MemberPart");
		fix.addAll(partialFix);

		//Get created class namely MemberPart
		Object memberPart = null;		
		for(Object obj: partialFix.getAdded()) if (obj instanceof RefOntoUML.Class) memberPart = obj;
		
		// create new <<memberOf>>
		EObject rel = createAssociationWithProperties(RelationStereotype.MEMBEROF);
		((NamedElement) rel).setName("NewMemberOf");
		fix.includeAdded(rel);
		// same container
		copyContainer(partOfList.get(0), rel);
		fix.includeModified(partOfList.get(0).eContainer());
		
		//link the new <<memberOf>> from Whole to MemberPart
		if(memberPart!=null) {
			((Meronymic)rel).getMemberEnd().get(0).setType(whole);
			((Meronymic)rel).getMemberEnd().get(1).setType((Type)memberPart);
		}
		
		// change references from partOf relations to point to the new <<memberOf>> relation
		for(Association assoc: partOfList)
		{		
			Fix references = changeModelReferences(assoc, rel);
			fix.includeAllModified(references.getModified());
			// delete element
			fix.addAll(deleteElement(assoc));	
		}
		return fix;
	}
	
	/**  This method gives a name for the property if it is (i) null or (ii) empty 
	 *   Return true if the name was fixed and false otherwise */
	public Fix fixPropertyName(Property p)
	{
		Fix fix = new Fix();		
		
		if(p==null)
			return fix;
		
		String name = p.getName();
		
		if(name==null || name.replaceAll("\\s+","").isEmpty())
		{
			String p1Name, p2Name;
			
			//if self-type relationship and both ends have null name, fix both ends of the relation
			Property opposite = p.getOpposite();
			if(opposite!=null && opposite.getType().equals(p.getType()) && (opposite.getName()==null || opposite.getName().trim().isEmpty()))
			{
				p1Name = p.getType().getName().trim().toLowerCase()+"_1";
				p1Name.replaceAll("\\s+","");
				p.setName(p1Name);
				p2Name = p.getType().getName().trim().toLowerCase()+"_2";
				p2Name.replaceAll("\\s+","");
				p.getOpposite().setName(p2Name);
				fix.includeModified(p);
				fix.includeModified(p.getOpposite());
			} else{
				p1Name = p.getType().getName().trim().toLowerCase();
				p1Name.replaceAll("\\s+","");
				p.setName(p1Name);
				fix.includeModified(p);
			}			
		}
		
		if(p!=null && p.getOpposite()!=null && p.getName()!=null && p.getOpposite().getName()!=null && p.getName().compareToIgnoreCase(p.getOpposite().getName())==0)
		{
			p.setName(p.getName()+"_1");
			fix.includeModified(p);
		}		
		return fix;
	}
		
	public Fix subsetProperty(Property general, Property specific, SpecializationType option, boolean includeModified)
	{
		Fix fix = new Fix();		
		if (option==SpecializationType.SUBSET) specific.getSubsettedProperty().add(general);		
		else if (option==SpecializationType.REDEFINE) specific.getRedefinedProperty().add(general);		
		else if (option==SpecializationType.SPECIALIZE && specific.getAssociation() instanceof Association && general.getAssociation() instanceof Association)
		{	
			Generalization g = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
			g.setGeneral(general.getAssociation());
			g.setSpecific(specific.getAssociation());
			fix.includeAdded(g);
		}		
		if (includeModified && (option==SpecializationType.SUBSET || option==SpecializationType.REDEFINE)) fix.includeModified(specific);		
		return fix;
	}

	public Fix deriveMaterialMultiplicities(MaterialAssociation material, Mediation m1, Mediation m2) {
		
		Property 	m1RelatorEnd = m1.getMemberEnd().get(0), m1MediatedEnd = m1.getMemberEnd().get(1), 
					m2RelatorEnd = m2.getMemberEnd().get(0), m2MediatedEnd = m2.getMemberEnd().get(1), 
					matSourceEnd, matTargetEnd;
		
		if(material.getMemberEnd().get(0).getType().equals(m1MediatedEnd.getType())){
			matSourceEnd = material.getMemberEnd().get(0);
			matTargetEnd = material.getMemberEnd().get(1);
		}
		else {
			matSourceEnd = material.getMemberEnd().get(1);
			matTargetEnd = material.getMemberEnd().get(0);
		}
		
		Fix fix = new Fix();
		
		int sourceLower, sourceUpper, targetLower, targetUpper;
		
		sourceLower = m2RelatorEnd.getLower()*m1MediatedEnd.getLower();
		if(m1MediatedEnd.getUpper()==-1 || m2RelatorEnd.getUpper()==-1)
			sourceUpper = -1;
		else
			sourceUpper = m2RelatorEnd.getUpper()*m1MediatedEnd.getUpper();
		
		targetLower = m1RelatorEnd.getLower()*m2MediatedEnd.getLower();
		if(m1RelatorEnd.getUpper()==-1 || m2MediatedEnd.getUpper()==-1)
			targetUpper = -1;
		else
			targetUpper = m1RelatorEnd.getUpper()*m2MediatedEnd.getUpper();
		
		fix.addAll(changePropertyMultiplicity(matSourceEnd,sourceLower,sourceUpper,true));
		fix.addAll(changePropertyMultiplicity(matTargetEnd, targetLower, targetUpper, true));
		
		return fix;		
	}
	
	/**
	 * 
	 * @param c Classifier whose nature will be changed (Possible natures: Functional Complex, COllection and QUatity
	 * @param visited 
	 * @param newNature
	 * @return
	 */
	public Fix changeNature(Classifier c, ArrayList<Classifier> visited, ClassStereotype newNature){
		Fix fix = new Fix();
		
		if(newNature!=ClassStereotype.KIND && newNature!=ClassStereotype.COLLECTIVE && newNature!=ClassStereotype.QUANTITY)
			return fix;
		
		visited.add(c);
		
		if(c instanceof Kind || c instanceof Quantity || c instanceof Collective){
			if(getClassStereotype(c)==newNature)
				return fix;
			else{
				fix.addAll(changeClassStereotypeTo(c, newNature));
				return fix;
			}
		}
			
		
		if(c instanceof SubKind || c instanceof Role || c instanceof Phase){
			for (Classifier parent : c.parents()) {
				if(!visited.contains(parent))
					fix.addAll(changeNature(parent, visited, newNature));
			}
		}
		
		if(c instanceof MixinClass){
			for (Classifier child : c.children()) {
				if(!visited.contains(child))
					fix.addAll(changeNature(child, visited, newNature));
			}
		}
		
		return fix;
	}
	
	private boolean hasSubstanceSortalParent(SortalClass sortal){

		for (Classifier parent : sortal.allParents()) {
			if(parent instanceof SubstanceSortal)
				return true;
		}
		
		return false;
	}
	
	public Fix removeIdentity(Classifier c, ArrayList<Classifier> visited) {
		Fix currentFix = new Fix();
		
		if(!visited.contains(c))
			visited.add(c);
		else
			return currentFix;
		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass){
			
			Iterator<Generalization> iterator = c.getGeneralization().iterator();
			while(iterator.hasNext()){
				Generalization g = iterator.next();
				Classifier general = g.getGeneral();
				
				if(general instanceof SubstanceSortal || 
						((general instanceof SubKind || general instanceof AntiRigidSortalClass) && hasSubstanceSortalParent((SortalClass) general))
					){
					iterator.remove();
					currentFix.addAll(deleteElement(g));
				}
				else{
					if(!visited.contains(general)){
						currentFix.addAll(removeIdentity(general, visited));
					}
				}
			}
		}
		else if (c instanceof MixinClass){
			
			ArrayList<Classifier> children = new ArrayList<Classifier>(c.children());
			for (Classifier child : children) {
				if(child instanceof SubstanceSortal || 
						((child instanceof SubKind || child instanceof AntiRigidSortalClass) && hasSubstanceSortalParent((SortalClass) child))
					){
					
					Iterator<Generalization> iterator = child.getGeneralization().iterator();
					while(iterator.hasNext()){
						Generalization g = iterator.next();
						if(g.getGeneral().equals(c)){
							iterator.remove();
							currentFix.addAll(deleteElement(g));
						}
					}
				}
				else {
					if(!visited.contains(child)){
						currentFix.addAll(removeIdentity(child, visited));
					}
				}
			}
		}
		
		return currentFix;
	}
}
