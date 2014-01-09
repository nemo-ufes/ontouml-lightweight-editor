package br.ufes.inf.nemo.antipattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Meronymic;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
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
	
	/** Change a class stereotype */
	public Fix changeClassStereotype(EObject element, ClassStereotype newStereo)
	{
		Fix fixes = new Fix();		
		// create new element
		RefOntoUML.PackageableElement newElement = createClass(newStereo);		
		copyMetaAttributes(element,newElement);		
		fixes.includeAdded(newElement);
		// the same container
		EObject container = element.eContainer();
		if(container instanceof RefOntoUML.Package){
			((RefOntoUML.Package)container).getPackagedElement().add(newElement);
			fixes.includeModified(element.eContainer());
		}				
		// change references
		Fix references = changeModelReferences(element,newElement); 		
		fixes.includeAllModified(references.getModified());		
		// delete element
		EcoreUtil.delete(element); 
		fixes.includeDeleted(element);		
		return fixes;
	}

	/** Change a relationship stereotype */
	public Fix changeRelationStereotype(EObject relationship, RelationStereotype newStereo)
	{
		Fix fixes = new Fix();		
		// create new relationship
		RefOntoUML.Relationship newRelationship = createAssociationWithProperties(newStereo);		
		copyMetaAttributes(relationship,newRelationship);
		copyPropertiesDatas(relationship,newRelationship);
		fixes.includeAdded(newRelationship);
		// the same container
		EObject container = relationship.eContainer();
		if(container instanceof RefOntoUML.Package && newRelationship instanceof PackageableElement){
			((RefOntoUML.Package)container).getPackagedElement().add((PackageableElement)newRelationship);
			fixes.includeModified(relationship.eContainer());
		}
		// change references
		Fix references = changeModelReferences(relationship,newRelationship); 		
		fixes.includeAllModified(references.getModified());
		// delete relationship
		EcoreUtil.delete(relationship,false);		
		fixes.includeDeleted(relationship);
		return fixes;
	}
	
	/** Create a subtype and connect it through a generalization to its type. */
	public Fix createSubType (EObject type, ClassStereotype subtypeStereo)
	{
		Fix fixes = new Fix();
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier)type).getName()+"Subtype");
		fixes.includeAdded(subtype);
		//create generalization
		Generalization gen = (Generalization)createRelationship(RelationStereotype.GENERALIZATION);
		gen.setSpecific((RefOntoUML.Classifier)subtype);
		gen.setGeneral((RefOntoUML.Classifier)type);
		fixes.includeAdded(gen);		
		return fixes;
	}
	
	/** Create a subtype and connect it through a generalization to its type.
	 *  It also change the references in relation to point to the subtype instead of the type.
	 */	
	public Fix createSubTypeInvolvingLink (EObject type, ClassStereotype subtypeStereo, EObject relation)
	{
		Fix fixes = new Fix();		
		// create subtype
		RefOntoUML.PackageableElement subtype = createClass(subtypeStereo);
		subtype.setName(((Classifier)type).getName()+"Subtype");
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
	
}
