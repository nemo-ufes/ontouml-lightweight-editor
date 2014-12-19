/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.umldraw.structure;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Enumeration;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.NamedElement;
import RefOntoUML.NominalQuality;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.PackageableElement;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.StringExpression;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.StructurationImpl;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.LineConnectMethod;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlDiagramElement;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * Implementation of the DiagramElementFactory interface. A
 * DiagramElementFactory instance belongs to a particular UmlDiagram instance,
 * so it can automatically associate allElements to the diagram they belong to.
 *
 * @author Wei-ju Wu, John Guerson
 */
public class DiagramElementFactoryImpl implements DiagramElementFactory {

  private Map<ElementType, UmlDiagramElement> elementPrototypes = new HashMap<ElementType, UmlDiagramElement>();
  private Map<RelationType, UmlConnection> relationPrototypes = new HashMap<RelationType, UmlConnection>();
  private Map<ElementType, Integer> elementCounters = new HashMap<ElementType, Integer>();
  private Map<RelationType, Integer> relationCounters = new HashMap<RelationType, Integer>();
  
  private RefOntoUMLFactory factory;
  
  /**
   * Constructor.
   * @param aDiagram the diagram this factory belongs to
   */
  public DiagramElementFactoryImpl() {  
    setupElementMaps();
    setupConnectionMaps();
  }
   

  /**
   * Initializes the element map with the element prototypes.
   */

  private void setupElementMaps() {
	
	factory = ModelHelper.getFactory();
	
	NoteElement notePrototype = (NoteElement)
    NoteElement.getPrototype().clone();
    elementPrototypes.put(ElementType.NOTE, notePrototype);
    
    Kind kind = (RefOntoUML.Kind)createElement(ElementType.KIND);    
    ClassElement kindElement = (ClassElement) ClassElement.getPrototype().clone();
    kindElement.setClassifier(kind);
    elementPrototypes.put(ElementType.KIND, kindElement);
        
    Quantity quantity = (RefOntoUML.Quantity)createElement(ElementType.QUANTITY);
    ClassElement quantityElement = (ClassElement) ClassElement.getPrototype().clone();
    quantityElement.setClassifier(quantity);
    elementPrototypes.put(ElementType.QUANTITY, quantityElement);
        
    Collective collective = (RefOntoUML.Collective)createElement(ElementType.COLLECTIVE);   
    ClassElement collectiveElement = (ClassElement) ClassElement.getPrototype().clone();
    collectiveElement.setClassifier(collective);
    elementPrototypes.put(ElementType.COLLECTIVE, collectiveElement);
        
    SubKind subkind = (RefOntoUML.SubKind)createElement(ElementType.SUBKIND);   
    ClassElement subkindElement = (ClassElement) ClassElement.getPrototype().clone();
    subkindElement.setClassifier(subkind);
    elementPrototypes.put(ElementType.SUBKIND, subkindElement);
        
    Phase phase = (RefOntoUML.Phase)createElement(ElementType.PHASE);    
    ClassElement phaseElement = (ClassElement) ClassElement.getPrototype().clone();
    phaseElement.setClassifier(phase);
    elementPrototypes.put(ElementType.PHASE, phaseElement);
        
    Role role = (RefOntoUML.Role)createElement(ElementType.ROLE);  
    ClassElement roleElement = (ClassElement) ClassElement.getPrototype().clone();
    roleElement.setClassifier(role);
    elementPrototypes.put(ElementType.ROLE, roleElement);
        
    Category category = (RefOntoUML.Category)createElement(ElementType.CATEGORY);  
    ClassElement categoryElement = (ClassElement) ClassElement.getPrototype().clone();
    categoryElement.setClassifier(category);
    elementPrototypes.put(ElementType.CATEGORY, categoryElement);
        
    RoleMixin rolemixin = (RefOntoUML.RoleMixin)createElement(ElementType.ROLEMIXIN);   
    ClassElement rolemixinElement = (ClassElement) ClassElement.getPrototype().clone();
    rolemixinElement.setClassifier(rolemixin);
    elementPrototypes.put(ElementType.ROLEMIXIN, rolemixinElement);
        
    Mixin mixin = (RefOntoUML.Mixin)createElement(ElementType.MIXIN);   
    ClassElement mixinElement = (ClassElement) ClassElement.getPrototype().clone();
    mixinElement.setClassifier(mixin);
    elementPrototypes.put(ElementType.MIXIN, mixinElement);
        
    Mode mode = (RefOntoUML.Mode)createElement(ElementType.MODE);   
    ClassElement modeElement = (ClassElement) ClassElement.getPrototype().clone();
    modeElement.setClassifier(mode);
    elementPrototypes.put(ElementType.MODE, modeElement);
        
    Relator relator = (RefOntoUML.Relator)createElement(ElementType.RELATOR);   
    ClassElement relatorElement = (ClassElement) ClassElement.getPrototype().clone();
    relatorElement.setClassifier(relator);
    elementPrototypes.put(ElementType.RELATOR, relatorElement);
        
    DataType datatype = (RefOntoUML.DataType)createElement(ElementType.DATATYPE);
    ClassElement datatypeElement = (ClassElement) ClassElement.getPrototype().clone();
    datatypeElement.setClassifier(datatype);
    datatypeElement.setShowAttributes(true);
    elementPrototypes.put(ElementType.DATATYPE, datatypeElement);
    
    Enumeration enumeration = (RefOntoUML.Enumeration)createElement(ElementType.ENUMERATION);
    ClassElement enumElement = (ClassElement) ClassElement.getPrototype().clone();
    enumElement.setClassifier(enumeration);
    enumElement.setShowAttributes(true);
    elementPrototypes.put(ElementType.ENUMERATION, enumElement);    
    
    PrimitiveType primitive = (RefOntoUML.PrimitiveType)createElement(ElementType.PRIMITIVETYPE);
    ClassElement primitiveElement = (ClassElement) ClassElement.getPrototype().clone();
    primitiveElement.setClassifier(primitive);    
    elementPrototypes.put(ElementType.PRIMITIVETYPE, primitiveElement);    
    
    PerceivableQuality perceivableQuality = (RefOntoUML.PerceivableQuality)createElement(ElementType.PERCEIVABLEQUALITY);
    ClassElement pqualityElement = (ClassElement) ClassElement.getPrototype().clone();
    pqualityElement.setClassifier(perceivableQuality);    
    elementPrototypes.put(ElementType.PERCEIVABLEQUALITY, pqualityElement);
    
    NonPerceivableQuality nonperceivableQuality = (RefOntoUML.NonPerceivableQuality)createElement(ElementType.NONPERCEIVABLEQUALITY);
    ClassElement npqualityElement = (ClassElement) ClassElement.getPrototype().clone();
    npqualityElement.setClassifier(nonperceivableQuality);    
    elementPrototypes.put(ElementType.NONPERCEIVABLEQUALITY, npqualityElement);
    
    NominalQuality nominalQuality = (RefOntoUML.NominalQuality)createElement(ElementType.NOMINALQUALITY);
    ClassElement nqElement = (ClassElement) ClassElement.getPrototype().clone();
    nqElement.setClassifier(nominalQuality);    
    elementPrototypes.put(ElementType.NOMINALQUALITY, nqElement);
  }

  public void createPropertiesByDefault(Association association)
  {
		Property node1Property, node2Property;	
		
		node1Property = ModelHelper.createDefaultOwnedEnd(null, 1, 1);	    		
		//If the association is a ComponentOf, set the default cardinality to 2..*, to help in validation
		if(association instanceof MeronymicImpl) node2Property = ModelHelper.createDefaultOwnedEnd(null, 2, -1);
		else node2Property = ModelHelper.createDefaultOwnedEnd(null, 1, 1);
		
		if(association instanceof MeronymicImpl)
		{
			if(((Meronymic)association).isIsShareable()) node1Property.setAggregation(AggregationKind.SHARED);	    			
			else node1Property.setAggregation(AggregationKind.COMPOSITE);	    				
		}
		
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
		
		association.getOwnedEnd().add(node1Property);
		association.getOwnedEnd().add(node2Property);	    		
		association.getMemberEnd().add(node1Property);
		association.getMemberEnd().add(node2Property);
		
		if(association instanceof DirectedBinaryAssociationImpl || association instanceof FormalAssociationImpl || association instanceof MaterialAssociationImpl)
		{
			association.getNavigableOwnedEnd().add(node1Property);
			association.getNavigableOwnedEnd().add(node2Property);	    			
			//If the association is Mediation or Characterization, set target readonly to help in validation
			if(association instanceof MediationImpl || association instanceof CharacterizationImpl || 
			   association instanceof DerivationImpl || association instanceof StructurationImpl) 
			node2Property.setIsReadOnly(true);
		}
		else
		{
			if(node1Property.getType() instanceof DataTypeImpl) association.getNavigableOwnedEnd().add(node1Property);	    		
			if(node2Property.getType() instanceof DataTypeImpl) association.getNavigableOwnedEnd().add(node2Property);
		}		
  }
	
  /**
   * Initializes the map with the connection prototypes.
   */
  private void setupConnectionMaps() {

	factory = RefOntoUMLFactory.eINSTANCE;
		
    Generalization generalization = (RefOntoUML.Generalization)createRelationship(RelationType.GENERALIZATION);    
    GeneralizationElement generalizationElement = (GeneralizationElement) GeneralizationElement.getPrototype().clone();
    generalizationElement.setRelationship(generalization);
    relationPrototypes.put(RelationType.GENERALIZATION, generalizationElement);
        
    Characterization characterization = (RefOntoUML.Characterization)createRelationship(RelationType.CHARACTERIZATION);   
    AssociationElement characterizationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    characterizationElement.setRelationship(characterization);
    characterizationElement.setAssociationType(RelationType.CHARACTERIZATION);
    characterizationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.CHARACTERIZATION, characterizationElement);
    createPropertiesByDefault(characterization);
    
    FormalAssociation formalAssociation = (RefOntoUML.FormalAssociation)createRelationship(RelationType.FORMAL);
    AssociationElement formalAssociationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    formalAssociationElement.setRelationship(formalAssociation);
    formalAssociationElement.setAssociationType(RelationType.FORMAL);
    formalAssociationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.FORMAL, formalAssociationElement);
    createPropertiesByDefault(formalAssociation);
          
    MaterialAssociation materialAssociation = (RefOntoUML.MaterialAssociation)createRelationship(RelationType.MATERIAL);    
    AssociationElement materialAssociationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    materialAssociationElement.setRelationship(materialAssociation);
    materialAssociationElement.setAssociationType(RelationType.MATERIAL);
    materialAssociationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.MATERIAL, materialAssociationElement);
    createPropertiesByDefault(materialAssociation);
    
    Mediation mediation = (RefOntoUML.Mediation)createRelationship(RelationType.MEDIATION);   
    AssociationElement mediationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    mediationElement.setRelationship(mediation);
    mediationElement.setAssociationType(RelationType.MEDIATION);
    mediationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.MEDIATION, mediationElement);
    createPropertiesByDefault(mediation);
        
    memberOf memberof = (RefOntoUML.memberOf)createRelationship(RelationType.MEMBEROF);   
    AssociationElement memberofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    memberofElement.setAssociationType(RelationType.MEMBEROF);
    memberofElement.setRelationship(memberof);
    relationPrototypes.put(RelationType.MEMBEROF, memberofElement);
    createPropertiesByDefault(memberof);
    
    subQuantityOf subquantityof = (RefOntoUML.subQuantityOf)createRelationship(RelationType.SUBQUANTITYOF);    
    AssociationElement subquantityofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    subquantityofElement.setAssociationType(RelationType.SUBQUANTITYOF);
    subquantityofElement.setRelationship(subquantityof);
    relationPrototypes.put(RelationType.SUBQUANTITYOF, subquantityofElement);
    createPropertiesByDefault(subquantityof);    
    
    subCollectionOf subcollectionof = (RefOntoUML.subCollectionOf)createRelationship(RelationType.SUBCOLLECTIONOF);    
    AssociationElement subcollectionofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    subcollectionofElement.setAssociationType(RelationType.SUBCOLLECTIONOF);
    subcollectionofElement.setRelationship(subcollectionof);
    relationPrototypes.put(RelationType.SUBCOLLECTIONOF, subcollectionofElement);
    createPropertiesByDefault(subcollectionof);     
    
    componentOf componentof = (RefOntoUML.componentOf)createRelationship(RelationType.COMPONENTOF);    
    AssociationElement componentofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    componentofElement.setAssociationType(RelationType.COMPONENTOF);
    componentofElement.setRelationship(componentof);
    relationPrototypes.put(RelationType.COMPONENTOF, componentofElement);
    createPropertiesByDefault(componentof); 
    
    Derivation derivation = (RefOntoUML.Derivation)createRelationship(RelationType.DERIVATION); 
    AssociationElement derivationeElement = (AssociationElement) AssociationElement.getPrototype().clone();
    derivationeElement.setAssociationType(RelationType.DERIVATION);
    derivationeElement.setRelationship(derivation);
    derivationeElement.setIsDashed(true);
    derivationeElement.setShowOntoUmlStereotype(false);
    relationPrototypes.put(RelationType.DERIVATION, derivationeElement);
    createPropertiesByDefault(derivation); 

    Structuration structuration = (RefOntoUML.Structuration)createRelationship(RelationType.STRUCTURATION);
    AssociationElement structurationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    structurationElement.setRelationship(structuration);
    structurationElement.setShowOntoUmlStereotype(true);
    structurationElement.setAssociationType(RelationType.STRUCTURATION);
    relationPrototypes.put(RelationType.STRUCTURATION, structurationElement);     
    createPropertiesByDefault(structuration); 
    
    Association datatyperelationship = (RefOntoUML.Association)createRelationship(RelationType.ASSOCIATION);
    AssociationElement datatyperelationshipElement = (AssociationElement) AssociationElement.getPrototype().clone();
    datatyperelationshipElement.setRelationship(datatyperelationship);
    datatyperelationshipElement.setAssociationType(RelationType.ASSOCIATION);
    relationPrototypes.put(RelationType.ASSOCIATION, datatyperelationshipElement);     
    createPropertiesByDefault(datatyperelationship); 
    
    relationPrototypes.put(RelationType.NOTE_CONNECTOR, NoteConnection.getPrototype());
  }
  
  /**
   * Create only the RefOntoUML Type element and do not create the referred UmlNode on the Diagram.
   */
  public RefOntoUML.Element createElement(ElementType elementType)
  {
	  RefOntoUML.Element type = null;
	  if (elementType.equals(ElementType.PACKAGE)) type = factory.createPackage();
	  if (elementType.equals(ElementType.KIND)) type = factory.createKind();
	  if (elementType.equals(ElementType.COLLECTIVE)) type = factory.createCollective();
	  if (elementType.equals(ElementType.QUANTITY)) type = factory.createQuantity();
	  if (elementType.equals(ElementType.SUBKIND)) type = factory.createSubKind();
	  if (elementType.equals(ElementType.PHASE)) type = factory.createPhase();
	  if (elementType.equals(ElementType.ROLE)) type = factory.createRole();
	  if (elementType.equals(ElementType.CATEGORY)) { type = factory.createCategory(); ((Classifier)type).setIsAbstract(true); }	  
	  if (elementType.equals(ElementType.ROLEMIXIN)) { type = factory.createRoleMixin(); ((Classifier)type).setIsAbstract(true); }
	  if (elementType.equals(ElementType.MIXIN)) { type = factory.createMixin(); ((Classifier)type).setIsAbstract(true); }
	  if (elementType.equals(ElementType.MODE)) { type = factory.createMode();}
	  if (elementType.equals(ElementType.RELATOR)) { type = factory.createRelator();  }
	  if (elementType.equals(ElementType.DATATYPE)) { type = factory.createDataType();  }	  
	  if (elementType.equals(ElementType.ENUMERATION)) { type = factory.createEnumeration();  }
	  if (elementType.equals(ElementType.PRIMITIVETYPE)) { type = factory.createPrimitiveType();  }
	  if (elementType.equals(ElementType.PERCEIVABLEQUALITY)) { type = factory.createPerceivableQuality();  }
	  if (elementType.equals(ElementType.NONPERCEIVABLEQUALITY)) { type = factory.createNonPerceivableQuality();  }
	  if (elementType.equals(ElementType.NOMINALQUALITY)) { type = factory.createNominalQuality();  }
	  if (elementType.equals(ElementType.GENERALIZATIONSET)) { type = factory.createGeneralizationSet();  }
	  if (elementType.equals(ElementType.COMMENT)) { type = createComment();  }
	  if (elementType.equals(ElementType.CONSTRAINT)) { type = createConstraintx();  }
	  if(type instanceof NamedElement){
		  ((NamedElement)type).setName(ModelHelper.getStereotype(type)+nextElementCount(elementType)); 
		  ((NamedElement)type).setVisibility(VisibilityKind.PUBLIC);
	  }		 
	  return type;			  
	  
  }
    
  public Comment createComment()
  {
	  Comment c = factory.createComment();
	  c.setBody("");
	  return c;
  }
  
  public Constraintx createConstraintx()
  {
	  Constraintx c = factory.createConstraintx();
	  c.setName("");
	  StringExpression expr = ModelHelper.getFactory().createStringExpression();
	  expr.setSymbol("");
	  c.setSpecification(expr);
	  return c;
  }
  
  /**
   * Create an Element with the given stereotype that contains all the features of the given Type. 
   */
  public RefOntoUML.PackageableElement createElement(ElementType elementType, RefOntoUML.Type type)
  {
	  RefOntoUML.PackageableElement newType = (PackageableElement)createElement(elementType);
	  newType.setVisibility(type.getVisibility());
	  newType.setName(type.getName());
	  ((Classifier)newType).setIsAbstract(((Classifier)type).isIsAbstract());
	  return newType;
  }

  /**
   * Create only the RefOntoUML Relationship element and do not create the referred UmlConnection on the Diagram.
   */
  public RefOntoUML.Relationship createRelationship(RelationType relationType)
  {
	  RefOntoUML.Relationship rel = null;
	  if (relationType.equals(RelationType.GENERALIZATION)) rel = factory.createGeneralization();
	  if (relationType.equals(RelationType.CHARACTERIZATION)) rel = factory.createCharacterization();
	  if (relationType.equals(RelationType.FORMAL)) rel = factory.createFormalAssociation();
	  if (relationType.equals(RelationType.MATERIAL)) { rel = factory.createMaterialAssociation(); ((MaterialAssociation)rel).setIsDerived(true); }
	  if (relationType.equals(RelationType.MEDIATION)) rel = factory.createMediation();
	  if (relationType.equals(RelationType.MEMBEROF)) { rel = factory.creatememberOf(); ((memberOf)rel).setIsShareable(true); }
	  if (relationType.equals(RelationType.SUBQUANTITYOF)) { rel = factory.createsubQuantityOf(); ((subQuantityOf)rel).setIsShareable(false); } 
	  if (relationType.equals(RelationType.SUBCOLLECTIONOF)) { rel = factory.createsubCollectionOf(); ((subCollectionOf)rel).setIsShareable(true); } 
	  if (relationType.equals(RelationType.COMPONENTOF)) { rel = factory.createcomponentOf(); ((componentOf)rel).setIsShareable(true); }
	  if (relationType.equals(RelationType.DERIVATION)) rel = factory.createDerivation();
	  if (relationType.equals(RelationType.ASSOCIATION)) rel = factory.createAssociation();	  
	  if (relationType.equals(RelationType.STRUCTURATION)) rel = factory.createStructuration();
	  if (rel instanceof Classifier){
		  ((Classifier)rel).setName(ModelHelper.getStereotype(rel)+nextRelationCount(relationType));		  
		  ((Classifier)rel).setVisibility(VisibilityKind.PUBLIC);
	  }
	  return rel;			  
  }
  
  public RefOntoUMLFactory getFactory(){
	  return factory;
  }
  
  /**
   * {@inheritDoc} This method also create the referred RefOntoUML Type of the UmlNode. 
   */
  public UmlNode createNode(ElementType elementType, StructureDiagram diagram) 
  {
    UmlNode umlnode = (UmlNode) elementPrototypes.get(elementType).clone();
    
    if(umlnode.getClassifier() != null) umlnode.getClassifier().setName(umlnode.getClassifier().getName() + nextElementCount(elementType));
    
    umlnode.addNodeChangeListener(diagram);
    
    return umlnode;
  }
  
  public UmlNode createNode(RefOntoUML.Type type, EObject eContainer, StructureDiagram diagram) 
  {
    UmlNode umlnode = (UmlNode) elementPrototypes.get(ElementType.valueOf(type.eClass().getName().toUpperCase())).clone();
    
    ((ClassElement)umlnode).setClassifier((RefOntoUML.Classifier)type);
        
    umlnode.addNodeChangeListener(diagram);
    
    return umlnode;
  }
  
  public String nextElementCount(ElementType elementType)
  {	  
	  if (elementCounters.get(elementType)!=null) {
		  int count = elementCounters.get(elementType);
		  elementCounters.put(elementType, count+1);
		  return Integer.toString(count+1);
	  }else{
		  elementCounters.put(elementType, 0);
		  return "";
	  }
  }
  
  /**
   * {@inheritDoc}  This method also create the referred RefOntoUML Relationship of the UmlConnection. 
   */
  public UmlConnection createConnection(RelationType relationType, UmlNode node1, UmlNode node2) 
  {
    UmlConnection prototype = relationPrototypes.get(relationType);    
    UmlConnection conn = null;
    if (prototype != null) 
    {
      conn = (UmlConnection) prototype.clone();
      bindConnection(conn, node1, node2);
      if(conn.getRelationship() != null && conn.getRelationship() instanceof AssociationImpl)
      {
    	  Association association = (Association) conn.getRelationship();
    	  association.setName(association.getName() + nextRelationCount(relationType));
      }
    }    
    return conn;
  }
  
  /**
   * Create a UmlConnection from a relationship 
   */
  public UmlConnection createConnection(RefOntoUML.Relationship relationship, UmlNode node1, UmlNode node2) 
  {	
    UmlConnection prototype = relationPrototypes.get(RelationType.valueOf(ModelHelper.getStereotype(relationship).toUpperCase()));    
    UmlConnection conn = null;
    if (prototype != null) 
    {
		conn = (UmlConnection) prototype.clone();
		conn.setRelationship(relationship);
		bindConnection(conn, node1, node2);
    }
    return conn;
  }
  
  /**
   * {@inheritDoc} This method also create the referred RefOntoUML Relationship of the UmlConnection. 
   */
  @Override
  public UmlConnection createConnectionFromCon(RelationType relationType, UmlConnection c1, UmlNode node2) 
  {
	  UmlConnection prototype = relationPrototypes.get(relationType);	  
      UmlConnection conn = null;
      if (prototype != null) 
      {
	      conn = (UmlConnection) prototype.clone();
	      bindConnection(conn, c1, node2);
	      if(conn.getRelationship() != null && conn.getRelationship() instanceof AssociationImpl)
	      {
	    	  Association association = (Association) conn.getRelationship();
	    	  association.setName(association.getName() + nextRelationCount(relationType));
	      }
      }	    
      return conn;
  }

  /**
   * Create a UmlConnection from a relationship 
   */
  @Override
  public UmlConnection createConnectionFromCon(RefOntoUML.Relationship relationship, UmlConnection c1, UmlNode node2) 
  {
	  UmlConnection prototype = relationPrototypes.get(RelationType.valueOf(ModelHelper.getStereotype(relationship).toUpperCase()));	  
      UmlConnection conn = null;
      if (prototype != null) 
      {
	      conn = (UmlConnection) prototype.clone();
	      conn.setRelationship(relationship);
	      bindConnection(conn, c1, node2);
      }
      return conn;
  }
  
  /**
   * {@inheritDoc} This method also create the referred RefOntoUML Relationship of the UmlConnection. 
   */
  public UmlConnection createConnectionToCon(RelationType relationType, UmlNode node1, UmlConnection c2) 
  {
    UmlConnection prototype = relationPrototypes.get(relationType);    
    UmlConnection conn = null;
    if (prototype != null) 
    {
      conn = (UmlConnection) prototype.clone();
      bindConnection(conn, node1, c2);
      if(conn.getRelationship() != null && conn.getRelationship() instanceof AssociationImpl)
      {
    	  Association association = (Association) conn.getRelationship();
    	  association.setName(association.getName() + nextRelationCount(relationType));    	 
      }
    }    
    return conn;
  }

  /**
   *  Create a UmlConnection from a relationship
   */
  public UmlConnection createConnectionToCon(RefOntoUML.Relationship relationship, UmlNode node1, UmlConnection c2) 
  {
    UmlConnection prototype = relationPrototypes.get(RelationType.valueOf(ModelHelper.getStereotype(relationship).toUpperCase()));    
    UmlConnection conn = null;
    if (prototype != null) 
    {
      conn = (UmlConnection) prototype.clone();
      conn.setRelationship(relationship);
      bindConnection(conn, node1, c2);
    }    
    return conn;
  }
  
  /**
   * {@inheritDoc} This method also create the referred RefOntoUML Relationship of the UmlConnection. 
   */
  public UmlConnection createConnectionBetweenCon(RelationType relationType, UmlConnection c1, UmlConnection c2) 
  {
    UmlConnection prototype = relationPrototypes.get(relationType);    
    UmlConnection conn = null;
    if (prototype != null) 
    {
      conn = (UmlConnection) prototype.clone();
      bindConnection(conn, c1, c2);
      if(conn.getRelationship() != null && conn.getRelationship() instanceof AssociationImpl)
      {
    	  Association association = (Association) conn.getRelationship();
    	  association.setName(association.getName() + nextRelationCount(relationType));    	  
      }
    }    
    return conn;
  }

  /**
   *  Create a UmlConnection from a RefOntoUML Relationship
   */
  public UmlConnection createConnectionBetweenCon(RefOntoUML.Relationship relationship, UmlConnection c1, UmlConnection c2) 
  {
    UmlConnection prototype = relationPrototypes.get(RelationType.valueOf(ModelHelper.getStereotype(relationship).toUpperCase()));    
    UmlConnection conn = null;
    if (prototype != null) 
    {
      conn = (UmlConnection) prototype.clone();
      conn.setRelationship(relationship);
      bindConnection(conn, c1, c2);
    }    
    return conn;
  }
  
  public String nextRelationCount(RelationType relationType)
  {	  
	  if (relationCounters.get(relationType)!=null) {
		  int count = relationCounters.get(relationType);
		  relationCounters.put(relationType, count+1);
		  return Integer.toString(count+1);
	  }else{
		  relationCounters.put(relationType, 0);
		  return "";
	  }	  
  }
  
  /**
   * {@inheritDoc}
   */
  public LineConnectMethod getConnectMethod(RelationType relationType)
  {
    UmlConnection conn = relationPrototypes.get(relationType);
    return (conn == null) ? null : conn.getConnectMethod();
  }

  /**
   * Binds the UmlConnection to the nodes.
   * @param conn the Connection
   * @param node1 the Node 1
   * @param node2 the Node 2
   */
  public void bindConnection(UmlConnection conn, UmlNode node1, UmlNode node2) {
    conn.setNode1(node1);
    conn.setNode2(node2);
    if(node1!=null) node1.addConnection(conn);
    if(node2!=null) node2.addConnection(conn);
  }
  
  public void bindConnection(UmlConnection conn, UmlNode node1, Connection c2) {
	  conn.setNode1(node1);
	  conn.setConnection2(c2);
	  if(node1!=null) node1.addConnection(conn);
	  if(c2!=null) c2.addConnection(conn);  
  }
  
  public void bindConnection(UmlConnection conn, UmlConnection c1, UmlNode node2) {
	  conn.setNode2(node2);
	  conn.setConnection1(c1);
	  if(node2!=null) node2.addConnection(conn);
	  if(c1!=null) c1.addConnection(conn);  
  }
  
  public void bindConnection(UmlConnection conn, UmlConnection c1, UmlConnection c2) {
	  conn.setConnection2(c2);
	  conn.setConnection1(c1);
	  if(c2!=null) c2.addConnection(conn);
	  if(c1!=null) c1.addConnection(conn);  
  }

}
