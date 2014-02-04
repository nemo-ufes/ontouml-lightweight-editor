package br.ufes.inf.nemo.common.ontoumlfixer;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
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
import RefOntoUML.Mode;
import RefOntoUML.NamedElement;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.Type;
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.componentOfImpl;

public class OutcomeFixer {

	public enum ClassStereotype {
		KIND, SUBKIND, COLLECTIVE, QUANTITY, PHASE, ROLE, ROLEMIXIN, CATEGORY, MIXIN, RELATOR, MODE, DATATYPE
	}

	public enum RelationStereotype {
		FORMAL, MATERIAL, CHARACTERIZATION, MEDIATION, DERIVATION, ASSOCIATION, COMPONENTOF, SUBQUANTITYOF, SUBCOLLECTIONOF, MEMBEROF, GENERALIZATION
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
	public RefOntoUML.Relationship createAssociationWithProperties(	RelationStereotype stereo) 
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
	public Fix invertEnds(Association association)
	{
		Fix fix = new Fix();
		Property src = association.getMemberEnd().get(0);
		Property tgt = association.getMemberEnd().get(1);
		association.getOwnedEnd().clear();
		association.getOwnedMember().clear();
		association.getNavigableOwnedEnd().clear();
		setEnds(association,tgt,src);
		fix.includeModified(association);
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
	public void copyPropertiesDatas(EObject source, EObject receiver) 
	{
		if (source instanceof RefOntoUML.Association && receiver instanceof RefOntoUML.Association) 
		{
			RefOntoUML.Association assoc = (RefOntoUML.Association) source;
			RefOntoUML.Association rcvAssoc = (RefOntoUML.Association) receiver;
			copyPropertyData(assoc.getMemberEnd().get(0), rcvAssoc.getMemberEnd().get(0));
			copyPropertyData(assoc.getMemberEnd().get(1), rcvAssoc.getMemberEnd().get(1));
		}
	}

	/**
	 * Copy multiplicities and type from a source property to a receiver
	 * property. It also copy the meta-attributes such as isDerived, isReadOnly,
	 * etc.
	 */
	public void copyPropertyData(EObject source, EObject receiver) 
	{
		if (source instanceof RefOntoUML.Property && receiver instanceof RefOntoUML.Property) 
		{
			RefOntoUML.Property property = (RefOntoUML.Property) source;
			RefOntoUML.Property rcvProperty = (RefOntoUML.Property) receiver;
			rcvProperty.setType(property.getType());
			LiteralInteger lowerBound = factory.createLiteralInteger();
			lowerBound.setValue(property.getLower());
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(property.getUpper());
			rcvProperty.setUpperValue(upperBound);
			rcvProperty.setLowerValue(lowerBound);
			copyMetaAttributes(source, receiver);
		}
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
			rcvClassifier.setIsAbstract(classifier.isIsAbstract());
			rcvClassifier.setIsLeaf(classifier.isIsLeaf());
		}
		if (source instanceof RefOntoUML.Association && receiver instanceof RefOntoUML.Association) 
		{
			RefOntoUML.Association assoc = (RefOntoUML.Association) source;
			RefOntoUML.Association rcvAssoc = (RefOntoUML.Association) receiver;
			rcvAssoc.setIsDerived(assoc.isIsDerived());
		}
		if (source instanceof RefOntoUML.Meronymic && receiver instanceof RefOntoUML.Meronymic) 
		{
			RefOntoUML.Meronymic mer = (RefOntoUML.Meronymic) source;
			RefOntoUML.Meronymic rcvMer = (RefOntoUML.Meronymic) receiver;
			rcvMer.setIsEssential(mer.isIsEssential());
			rcvMer.setIsImmutablePart(mer.isIsImmutablePart());
			rcvMer.setIsImmutableWhole(mer.isIsImmutableWhole());
			rcvMer.setIsInseparable(mer.isIsInseparable());
			rcvMer.setIsShareable(mer.isIsShareable());
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
			if (src.getName() != null && !src.getName().isEmpty()) newtype.setName(src.getName());
		}
		if (tgt.getType().equals(type)) {
			tgt.setType((Type) newtype);
			fixes.includeModified(tgt);
			if (tgt.getName() != null && !tgt.getName().isEmpty()) newtype.setName(tgt.getName());
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
		for (RefOntoUML.PackageableElement p : root.getPackagedElement()) 
		{
			fix.addAll(changeReferencesInAssociation((Association)p,(Type)element,(Type)newElement));			
			fix.addAll(changeReferencesInGeneralization((Generalization)p, (Type)element, (Type)newElement));
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
		Fix fixes = new Fix();
		// create new element
		RefOntoUML.PackageableElement newElement = createClass(newStereo);
		copyMetaAttributes(element, newElement);
		fixes.includeAdded(newElement);
		// the same container
		copyContainer(element, newElement);
		fixes.includeModified(element.eContainer());
		// change references
		Fix references = changeModelReferences(element, newElement);
		fixes.includeAllModified(references.getModified());
		// delete element
		fixes.addAll(deleteElement(element));
		return fixes;
	}

	/** Delete an element from the model */
	public Fix deleteElement(EObject element) 
	{
		Fix fix = new Fix();
		EcoreUtil.delete(element);
		fix.includeDeleted(element);
		return fix;
	}

	/** Get class stereotype */
	public ClassStereotype getClassStereotype(EObject element) 
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
		Fix aux2 = addSuperType(newElem, stereoSupertype);
		fixes.addAll(aux2);
		return fixes;
	}

	/** Add a super-type with the given stereotype to the element */
	public Fix addSuperType(EObject element, ClassStereotype stereoSuperType) 
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
	public Fix addCommonSuperType(ArrayList<Classifier> subtypes, ClassStereotype stereoSuperType) 
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
		copyMetaAttributes(relationship, newRelationship);
		copyPropertiesDatas(relationship, newRelationship);
		fixes.includeAdded(newRelationship);
		// the same container as
		copyContainer(relationship, newRelationship);
		fixes.includeModified(relationship.eContainer());
		// change references
		Fix references = changeModelReferences(relationship, newRelationship);
		fixes.includeAllModified(references.getModified());
		// delete relationship
		EcoreUtil.delete(relationship, false);
		fixes.includeDeleted(relationship);
		return fixes;
	}

	/** Change a relationship stereotype */
	public Fix changeRelationStereotypeTo(EObject relationship, RelationStereotype newStereo, boolean invertSides) 
	{
		Fix fixes = new Fix();
		// create new relationship
		RefOntoUML.Relationship newRelationship = createAssociationWithProperties(newStereo);
		copyMetaAttributes(relationship, newRelationship);
		copyPropertiesDatas(relationship, newRelationship);
		fixes.includeAdded(newRelationship);
		// the same container as
		copyContainer(relationship, newRelationship);
		fixes.includeModified(relationship.eContainer());
		// change references
		Fix references = changeModelReferences(relationship, newRelationship);
		fixes.includeAllModified(references.getModified());
		// delete relationship
		EcoreUtil.delete(relationship, false);
		fixes.includeDeleted(relationship);
		//invert sides
		if (relationship instanceof RefOntoUML.Association) fixes.addAll(invertEnds((RefOntoUML.Association)newRelationship));
		return fixes;
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
	public Fix generateOCLRule(String contextName, String invName, String invRule) 
	{
		String oclRule = "context _'" + contextName + "'\n" + "inv " + invName+ " : " + invRule;
		Fix fix = new Fix();
		fix.includeRule(oclRule);
		return fix;
	}

	/**
	 * Create a default generalization set which is covering=true and
	 * disjoint=true and its name is null
	 */
	public Fix createGeneralizationSet(ArrayList<Generalization> generalizations) 
	{
		return createGeneralizationSet(generalizations, true, true, null);
	}

	/** Create a default generalization set from a list of generalizations */
	public Fix createGeneralizationSet(ArrayList<Generalization> generalizations, boolean isDisjoint, boolean isCovering, String gsName) 
	{
		Fix fix = new Fix();
		// create generalization set
		GeneralizationSet gs = createBasicGeneralizationSet(isDisjoint,isCovering);
		gs.getGeneralization().addAll(generalizations);
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
	 */
	public Fix createGeneralizationSet(Classifier supertype, ArrayList<Classifier> subtypes) 
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
		return createGeneralizationSet(generalizations);
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
		fix.includeModified(general);
		fix.includeModified(specific);
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
		if (object instanceof Characterization) return RelationStereotype.CHARACTERIZATION;
		if (object instanceof Generalization) return RelationStereotype.GENERALIZATION;
		if (object instanceof Association) return RelationStereotype.ASSOCIATION;
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
		return fix;
	}

	//return true if the name was fixed and false otherwise
	public Fix fixPropertyName(Property p){
		Fix fix = new Fix();
		
		if(p!=null && (p.getName()==null || p.getName().trim().isEmpty())){
			String p1Name, p2Name;
			
			//if self-type relationship and both ends have null name, fix both ends of the relation
			Property opposite = p.getOpposite();
			if(opposite!=null && opposite.getType().equals(p.getType()) && (opposite.getName()==null || opposite.getName().trim().isEmpty())){
				p1Name = p.getType().getName().trim().toLowerCase()+"_1";
				p1Name.replaceAll("\\s+","");
				p.setName(p1Name);
				p2Name = p.getType().getName().trim().toLowerCase()+"_2";
				p2Name.replaceAll("\\s+","");
				p.getOpposite().setName(p2Name);
				fix.includeAdded(p);
				fix.includeAdded(p.getOpposite());
			}
			else{
				p1Name = p.getType().getName().trim().toLowerCase();
				p1Name.replaceAll("\\s+","");
				p.setName(p1Name);
				fix.includeAdded(p);
			}			
		}
		
		if(p!=null && p.getOpposite()!=null && p.getName()!=null && p.getOpposite().getName()!=null && p.getName().compareToIgnoreCase(p.getOpposite().getName())==0){
			p.setName(p.getName()+"_1");
			fix.includeAdded(p);
		}
		
		return fix;
	}

}
