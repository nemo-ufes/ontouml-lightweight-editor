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
		
	public enum ClassStereotype { KIND, SUBKIND, COLLECTIVE, QUANTITY, PHASE, ROLE, ROLEMIXIN, CATEGORY,
		MIXIN, RELATOR, MODE, DATATYPE
	}
	
	public enum RelationStereotype { FORMAL, MATERIAL, CHARACTERIZATION, MEDIATION, DERIVATION, ASSOCIATION,
		COMPONENTOF, SUBQUANTITYOF, SUBCOLLECTIONOF, MEMBEROF, GENERALIZATION	
	}
	
	public RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	public RefOntoUML.Package root;	
	
	/** Constructor */
	public OutcomeFixer (RefOntoUML.Package root)
	{
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
		if (stereo.equals(ClassStereotype.CATEGORY)) { type = factory.createCategory(); ((Classifier)type).setIsAbstract(true); }	  
		if (stereo.equals(ClassStereotype.ROLEMIXIN)) { type = factory.createRoleMixin(); ((Classifier)type).setIsAbstract(true); }
		if (stereo.equals(ClassStereotype.MIXIN)) { type = factory.createMixin(); ((Classifier)type).setIsAbstract(true); }
		if (stereo.equals(ClassStereotype.MODE)) { type = factory.createMode();}
		if (stereo.equals(ClassStereotype.RELATOR)) { type = factory.createRelator();  }
		if (stereo.equals(ClassStereotype.DATATYPE)) { type = factory.createDataType();  }	  
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
		if (stereo.equals(RelationStereotype.MATERIAL)) { rel = factory.createMaterialAssociation(); ((MaterialAssociation)rel).setIsDerived(true); }
		if (stereo.equals(RelationStereotype.MEDIATION)) rel = factory.createMediation();
		if (stereo.equals(RelationStereotype.MEMBEROF)) { rel = factory.creatememberOf(); ((memberOf)rel).setIsShareable(true); }
		if (stereo.equals(RelationStereotype.SUBQUANTITYOF)) { rel = factory.createsubQuantityOf(); ((subQuantityOf)rel).setIsShareable(false); } 
		if (stereo.equals(RelationStereotype.SUBCOLLECTIONOF)) { rel = factory.createsubCollectionOf(); ((subCollectionOf)rel).setIsShareable(true); } 
		if (stereo.equals(RelationStereotype.COMPONENTOF)) { rel = factory.createcomponentOf(); ((componentOf)rel).setIsShareable(true); }
		if (stereo.equals(RelationStereotype.DERIVATION)) rel = factory.createDerivation();
		if (stereo.equals(RelationStereotype.ASSOCIATION)) rel = factory.createAssociation();	  
		if (rel instanceof Classifier){
			((Classifier)rel).setName("");		  
			((Classifier)rel).setVisibility(VisibilityKind.PUBLIC);
		}		
		return rel;			  
	}
	
	private GeneralizationSet createBasicGeneralizationSet(boolean isCovering, boolean isDisjoint){
		GeneralizationSet gs = factory.createGeneralizationSet();
		gs.setIsCovering(isCovering);
		gs.setIsDisjoint(isDisjoint);
		return gs;
	}

	/** Creates an association with default properties */
	public RefOntoUML.Relationship createAssociationWithProperties(RelationStereotype stereo)
	{
		if(stereo == RelationStereotype.GENERALIZATION) return null;
		Association association = (RefOntoUML.Association)createRelationship(stereo);
		createPropertiesByDefault(association);
		return association;
	}
	
	/**
	 * Create default properties for this association.
	 */
	public void createPropertiesByDefault(Association association)
	{
		// creation
		Property node1Property, node2Property;		
		node1Property = createProperty(null, 1, 1);			
		if(association instanceof componentOfImpl) node2Property = createProperty(null, 2, -1);
		else node2Property = createProperty(null, 1, 1);
		
		// read only
		if(association instanceof MediationImpl || association instanceof CharacterizationImpl || association instanceof DerivationImpl)
		{ 
			node2Property.setIsReadOnly(true);
		}
		
		// aggregation	
		if(association instanceof MeronymicImpl)
		{
			if(((Meronymic)association).isIsShareable()) node1Property.setAggregation(AggregationKind.SHARED);
			else node1Property.setAggregation(AggregationKind.COMPOSITE);	    				
		}
			
		// name
		String node1Name  = new String();		
		if(node1Property.getType()!=null)
		{ 
			node1Name = node1Property.getType().getName();	    		
			if(node1Name==null || node1Name.trim().isEmpty()) node1Name = "source";
			else node1Name = node1Name.trim().toLowerCase();
		}
		String node2Name  = new String();
		if(node2Property.getType()!=null)
		{ 
			node2Name = node2Property.getType().getName();	    		
			if(node2Name==null || node2Name.trim().isEmpty()) node2Name = "target";
			else node2Name = node2Name.trim().toLowerCase();
		}
		node1Property.setName(node1Name);
		node2Property.setName(node2Name);
			
		// ends
		association.getOwnedEnd().add(node1Property);
		association.getOwnedEnd().add(node2Property);		
		association.getMemberEnd().add(node1Property);
		association.getMemberEnd().add(node2Property);		
		if(association instanceof DirectedBinaryAssociationImpl || association instanceof FormalAssociationImpl || association instanceof MaterialAssociationImpl){
			association.getNavigableOwnedEnd().add(node1Property);
			association.getNavigableOwnedEnd().add(node2Property);
		} else {
			if(node1Property.getType() instanceof DataTypeImpl) association.getNavigableOwnedEnd().add(node1Property);	    		
			if(node2Property.getType() instanceof DataTypeImpl) association.getNavigableOwnedEnd().add(node2Property);
		}		
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
	/**
	 * Copy multiplicities and type of association's properties to another association's properties.
	 * It also copy the meta-attributes such as isDerived, isReadOnly, etc.
	 */
	public void copyPropertiesDatas(EObject source, EObject receiver)
	{
		if(source instanceof RefOntoUML.Association && receiver instanceof RefOntoUML.Association)
		{
			RefOntoUML.Association assoc = (RefOntoUML.Association)source;
			RefOntoUML.Association rcvAssoc = (RefOntoUML.Association)receiver;
			copyPropertyData(assoc.getMemberEnd().get(0),rcvAssoc.getMemberEnd().get(0));
			copyPropertyData(assoc.getMemberEnd().get(1),rcvAssoc.getMemberEnd().get(1));
		}
	}
	
	/**
	 * Copy multiplicities and type from a source property to a receiver property.
	 * It also copy the meta-attributes such as isDerived, isReadOnly, etc.
	 */
	public void copyPropertyData(EObject source, EObject receiver)
	{
		if(source instanceof RefOntoUML.Property && receiver instanceof RefOntoUML.Property)
		{
			RefOntoUML.Property property = (RefOntoUML.Property)source;
			RefOntoUML.Property rcvProperty = (RefOntoUML.Property)receiver;
			rcvProperty.setType(property.getType());
			LiteralInteger lowerBound = factory.createLiteralInteger();
			lowerBound.setValue(property.getLower());
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(property.getUpper());
			rcvProperty.setUpperValue(upperBound);
			rcvProperty.setLowerValue(lowerBound);
			
			copyMetaAttributes(source,receiver);
		}		
	}
	
	/** Copy all the meta-attributes from a source to a receiver: 
	 * 
	 *  Name, Visibility, IsAbstract, IsDerived, IsLeaf, 
	 *  IsEssential, IsImmutablePart, IsImmutableWhole,
	 *  IsInseparable, IsShareable
	 *  
	 * */
	public void copyMetaAttributes(EObject source, EObject receiver)
	{
		if (source instanceof RefOntoUML.Classifier && receiver instanceof RefOntoUML.Classifier)
		{
			RefOntoUML.Classifier classifier = (RefOntoUML.Classifier)source;
			RefOntoUML.Classifier rcvClassifier = (RefOntoUML.Classifier)receiver;			
			rcvClassifier.setName(classifier.getName());
			(rcvClassifier).setVisibility(classifier.getVisibility());
			rcvClassifier.setIsAbstract(classifier.isIsAbstract());
			rcvClassifier.setIsLeaf(classifier.isIsLeaf());
		}
		if (source instanceof RefOntoUML.Association && receiver instanceof RefOntoUML.Association)
		{
			RefOntoUML.Association assoc = (RefOntoUML.Association)source;
			RefOntoUML.Association rcvAssoc = (RefOntoUML.Association)receiver;			
			rcvAssoc.setIsDerived(assoc.isIsDerived());			
		}
		if (source instanceof RefOntoUML.Meronymic && receiver instanceof RefOntoUML.Meronymic)
		{
			RefOntoUML.Meronymic mer = (RefOntoUML.Meronymic)source;
			RefOntoUML.Meronymic rcvMer = (RefOntoUML.Meronymic)receiver;
			rcvMer.setIsEssential(mer.isIsEssential());
			rcvMer.setIsImmutablePart(mer.isIsImmutablePart());
			rcvMer.setIsImmutableWhole(mer.isIsImmutableWhole());
			rcvMer.setIsInseparable(mer.isIsInseparable());
			rcvMer.setIsShareable(mer.isIsShareable());
		}
		if (source instanceof RefOntoUML.GeneralizationSet && receiver instanceof RefOntoUML.GeneralizationSet)
		{
			RefOntoUML.GeneralizationSet genSet = (RefOntoUML.GeneralizationSet)source;
			RefOntoUML.GeneralizationSet rcvGenSet = (RefOntoUML.GeneralizationSet)receiver;
			rcvGenSet.setIsCovering(genSet.isIsCovering());
			rcvGenSet.setIsDisjoint(genSet.isIsDisjoint());
		}
		if (source instanceof RefOntoUML.Property && receiver instanceof RefOntoUML.Property)
		{
			RefOntoUML.Property property = (RefOntoUML.Property)source;
			RefOntoUML.Property rcvProperty = (RefOntoUML.Property)receiver;
	
			rcvProperty.setIsDerived(property.isIsDerived());
			rcvProperty.setIsDerivedUnion(property.isIsDerivedUnion());
			rcvProperty.setIsReadOnly(property.isIsReadOnly());
			rcvProperty.setIsOrdered(property.isIsOrdered());			
			rcvProperty.setIsUnique(property.isIsUnique());
			rcvProperty.setIsStatic(property.isIsStatic());			
		}	
	}
	
	/** Change all model references of an element to point to a new element */
	public Fix changeModelReferences(EObject element, EObject newElement)
	{
		Fix fix = new Fix();		
		
		if (!(element instanceof Type)) return fix;
		if (!(newElement instanceof Type)) return fix;
		
		for(RefOntoUML.PackageableElement p: root.getPackagedElement())
		{
			if (p instanceof RefOntoUML.Association)
			{
				RefOntoUML.Association assoc = (RefOntoUML.Association)p;
				Property source = assoc.getMemberEnd().get(0);
				Property target = assoc.getMemberEnd().get(1);
				if(source.getType()!=null && source.getType().equals(element)) 
				{
					source.setType((Type)newElement);
					fix.includeModified(source);
				}
				if(target.getType()!=null && target.getType().equals(element)) 
				{
					target.setType((Type)newElement);
					fix.includeModified(source);
				}
			}
			if (p instanceof RefOntoUML.Generalization)
			{
				Generalization gen = (Generalization)p;
				Classifier specific = gen.getSpecific();
				Classifier general = gen.getGeneral();
				if (specific.equals(element)) 
				{
					gen.setSpecific((Classifier)newElement);
					fix.includeModified(gen);
				}
				if (general.equals(element)) 
				{
					gen.setGeneral((Classifier)newElement);
					fix.includeModified(gen);
				}
			}			
		}
		return fix;
	}

	/** Copy eContainer from element to the receiver. Both will be in the same container. */
	public void copyContainer(EObject source, EObject receiver)
	{		
		EObject container = source.eContainer();
		if(container instanceof RefOntoUML.Package){
			((RefOntoUML.Package)container).getPackagedElement().add((PackageableElement)receiver);			
		}		
	}
	
	/** Change a class stereotype */
	public Fix changeClassStereotypeTo(EObject element, ClassStereotype newStereo)
	{
		Fix fixes = new Fix();		
		// create new element
		RefOntoUML.PackageableElement newElement = createClass(newStereo);		
		copyMetaAttributes(element,newElement);		
		fixes.includeAdded(newElement);
		// the same container
		copyContainer(element,newElement);
		fixes.includeModified(element.eContainer());						
		// change references
		Fix references = changeModelReferences(element,newElement); 		
		fixes.includeAllModified(references.getModified());		
		// delete element
		fixes.addAll(deleteElement(element));		
		return fixes;
	}
	
	/** Delete an element from the model*/
	public Fix deleteElement (EObject element){
		Fix fix = new Fix();
		EcoreUtil.delete(element); 
		fix.includeDeleted(element);
		return fix;
	}

	/** Get class stereotype */
	public ClassStereotype getClassStereotype(EObject element)
	{		
		if(element instanceof Kind) return ClassStereotype.KIND;
		else if (element instanceof SubKind) return ClassStereotype.SUBKIND;
		else if(element instanceof Collective) return ClassStereotype.COLLECTIVE;
		else if(element instanceof Quantity) return ClassStereotype.QUANTITY;
		else if(element instanceof Phase) return ClassStereotype.PHASE;
		else if(element instanceof Role) return ClassStereotype.ROLE;
		else if(element instanceof Category) return ClassStereotype.CATEGORY;
		else if(element instanceof Mixin) return ClassStereotype.MIXIN;
		else if(element instanceof RoleMixin) return ClassStereotype.ROLEMIXIN;
		else if(element instanceof Relator) return ClassStereotype.RELATOR;
		else if(element instanceof Mode) return ClassStereotype.MODE;
		else if(element instanceof DataType) return ClassStereotype.DATATYPE;
		else return ClassStereotype.KIND;
	}
	
	/** Change element to Role and add a supertype for it */
	public Fix changeClassStereotypeSubtyping(EObject element, ClassStereotype stereo){
		Fix fixes = new Fix();
		ClassStereotype stereoSupertype = getClassStereotype(element);
		//change to role
		Fix aux = changeClassStereotypeTo(element, stereo);
		EObject newElem = (EObject)aux.getAdded().get(0);
		fixes.addAll(aux);		
		//create a supertype for it
		Fix aux2 = addSuperType(newElem, stereoSupertype);
		fixes.addAll(aux2);
		return fixes;
	}
	
	/** Add a super-type with the given stereotype to the element*/
	public Fix addSuperType(EObject element, ClassStereotype stereoSuperType)
	{
		Fix fixes = new Fix();
		//create supertye
		RefOntoUML.PackageableElement supertype = createClass(stereoSuperType);
		supertype.setName("Supertype");
		fixes.includeAdded(supertype);
		//the same container as
		copyContainer(element,supertype);
		fixes.includeModified(element.eContainer());
		//create generalization
		Generalization gen = (Generalization)createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier)element);
		gen.setGeneral((RefOntoUML.Classifier)supertype);
		fixes.includeAdded(gen);
		return fixes;
	}
	
	/** Change a relationship stereotype */
	public Fix changeRelationStereotypeTo(EObject relationship, RelationStereotype newStereo)
	{
		Fix fixes = new Fix();		
		// create new relationship
		RefOntoUML.Relationship newRelationship = createAssociationWithProperties(newStereo);		
		copyMetaAttributes(relationship,newRelationship);
		copyPropertiesDatas(relationship,newRelationship);
		fixes.includeAdded(newRelationship);
		// the same container as
		copyContainer(relationship,newRelationship);
		fixes.includeModified(relationship.eContainer());		
		// change references
		Fix references = changeModelReferences(relationship,newRelationship); 		
		fixes.includeAllModified(references.getModified());
		// delete relationship
		EcoreUtil.delete(relationship,false);		
		fixes.includeDeleted(relationship);
		return fixes;
	}
	
	/** Create a subtype and connect it through a generalization to its type. */
	public Fix createSubTypeAs (EObject type, ClassStereotype subtypeStereo)
	{
		Fix fixes = new Fix();
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier)type).getName()+"Subtype");
		copyContainer(type,subtype);
		fixes.includeAdded(subtype);
		//create generalization
		Generalization gen = (Generalization)createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier)subtype);
		gen.setGeneral((RefOntoUML.Classifier)type);
		fixes.includeAdded(gen);		
		return fixes;
	}
	
	/** Create a supertype and connect it through a generalization to its type. */
	public Fix createSuperTypeAs (EObject type, ClassStereotype supertypeStereo)
	{
		Fix fixes = new Fix();
		// create subtype
		RefOntoUML.PackageableElement supertype = createClass(supertypeStereo);
		supertype.setName(((Classifier)type).getName()+"Subtype");
		copyContainer(type,supertype);
		fixes.includeAdded(supertype);
		//create generalization
		Generalization gen = (Generalization)createRelationship(RelationStereotype.GENERALIZATION);
		gen.setGeneral((RefOntoUML.Classifier)supertype);
		gen.setSpecific((RefOntoUML.Classifier)type);
		fixes.includeAdded(gen);	
		return fixes;
	}
	
	/** Create a subtype and connect it through a generalization to its type.
	 *  It also change the references in relation to point to the subtype instead of the type.
	 */	
	public Fix createSubTypeAsInvolvingLink (EObject type, ClassStereotype subtypeStereo, EObject relation)
	{
		Fix fixes = new Fix();		
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier)type).getName()+"Subtype");
		copyContainer(type,subtype);
		fixes.includeAdded(subtype);
		//create generalization
		Generalization gen = (Generalization)createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier)subtype);
		gen.setGeneral((RefOntoUML.Classifier)type);
		fixes.includeAdded(gen);		
		//change reference in relation
		if(!(relation instanceof Association)) return fixes;
		Property src = ((Association)relation).getMemberEnd().get(0);
		Property tgt = ((Association)relation).getMemberEnd().get(1);
		if (src.getType().equals(type)){
			src.setType((Type)subtype);
			fixes.includeModified(src);
		}
		if (tgt.getType().equals(type)){
			tgt.setType((Type)subtype);
			fixes.includeModified(tgt);
		}
		return fixes;
	}
	
	public Fix generateOCLRule (String contextName, String invName, String invRule){
		String oclRule ="context _'"+contextName+"'\n"+
						"inv "+invName+" : "+invRule;
		
		Fix fix = new Fix();
		fix.includeRule(oclRule);
		return fix;
	}
	
	public Fix createGeneralizationSet(ArrayList<Generalization> generalizations){
		Fix fix = new Fix();
		
		GeneralizationSet gs = createBasicGeneralizationSet(true, true);
		gs.getGeneralization().addAll(generalizations);
		
		fix.includeAdded(gs);
		fix.includeModified(generalizations);
		
		copyContainer(generalizations.get(0).getGeneral(),gs);
		
		return fix;
	}
	
	public Fix createGeneralizationSet(Classifier supertype, ArrayList<Classifier> subtypes){
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
		
		for (Classifier c : subtypes) {
			for (Generalization g : c.getGeneralization()) {
				if (g.getGeneral().equals(supertype) || g.getGeneral().allParents().contains(supertype))
					generalizations.add(g);
			}
		}
		
		return createGeneralizationSet(generalizations);
	}
	
	public Fix createGeneralization(Classifier specific, Classifier general){
		Fix fix = new Fix();
		
		Generalization g = (Generalization) createRelationship(RelationStereotype.GENERALIZATION);
		g.setGeneral(general);
		g.setSpecific(specific);
		
		fix.includeAdded(g);
		fix.includeModified(general);
		fix.includeModified(specific);
		
		return fix;
	}

	public RelationStereotype getRelationshipStereotype(EObject object) {

		if(object instanceof Mediation) return RelationStereotype.MEDIATION;
		if(object instanceof Characterization) return RelationStereotype.CHARACTERIZATION;
		if(object instanceof memberOf) return RelationStereotype.MEMBEROF;
		if(object instanceof componentOf) return RelationStereotype.COMPONENTOF;
		if(object instanceof subCollectionOf) return RelationStereotype.SUBCOLLECTIONOF;
		if(object instanceof subQuantityOf) return RelationStereotype.SUBQUANTITYOF;
		if(object instanceof FormalAssociation) return RelationStereotype.FORMAL;
		if(object instanceof MaterialAssociation) return RelationStereotype.MATERIAL;
		if(object instanceof Derivation) return RelationStereotype.DERIVATION;
		if(object instanceof Characterization) return RelationStereotype.CHARACTERIZATION;
		if(object instanceof Generalization) return RelationStereotype.GENERALIZATION;
		if(object instanceof Association) return RelationStereotype.ASSOCIATION;
		
		return null;
	}
	
	public Fix setUpperCardinalityOnRelatorSide (Mediation m, int upper)
	{
		Fix fix = new Fix();
		
		Type source = m.getMemberEnd().get(0).getType();
		Type target = m.getMemberEnd().get(1).getType();
		if(source instanceof Relator){
			Property src = m.getMemberEnd().get(0);
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(upper);			
			src.setUpperValue(upperBound);
			fix.includeModified(src);
		}
		if(target instanceof Relator){
			Property tgt = m.getMemberEnd().get(1);
			LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
			upperBound.setValue(upper);			
			tgt.setUpperValue(upperBound);
			fix.includeModified(tgt);
		}
		return fix;
	}
	
	/** Verifies if there is already a element in the model root with the same name as the object obj. If true, it returns that element. */
	public RefOntoUML.PackageableElement containsNameBased(RefOntoUML.Package root, EObject obj)
	{
		for(PackageableElement pe: root.getPackagedElement())
		{
			if(pe instanceof DataType){
				if (pe.getName().trim().compareToIgnoreCase(((NamedElement) obj).getName())==0) return pe;
			}
			if(pe instanceof RefOntoUML.Package || pe instanceof RefOntoUML.Model) return containsNameBased((Package) pe,obj);
		}
		return null;
	}
	
	public Fix createAttribute(EObject object, String attrName, ClassStereotype attrType, String attrTypeName)
	{
		Fix fix = new Fix();
		
		//create type
		EObject type = createClass(attrType);
		((NamedElement)type).setName(attrTypeName);
		// verify if it already exists
		RefOntoUML.NamedElement elem = (NamedElement)containsNameBased(root,type);
		if (elem==null){
			copyContainer(object, type);
			fix.includeAdded(type);
			fix.includeModified(object.eContainer());
		}else{
			type = elem;
		}
		//create the attribute (property)
		Property attr = createProperty((Classifier)type, 1, 1);
		attr.setType((Type)type);
		attr.setName(attrName);
		fix.includeAdded(attr);
		//include it on object
		if (object instanceof RefOntoUML.Class) ((RefOntoUML.Class)object).getOwnedAttribute().add(attr);
		if (object instanceof RefOntoUML.DataType) ((RefOntoUML.DataType)object).getOwnedAttribute().add(attr);		
		fix.includeModified(object);
		
		return fix;
	}
	
}
