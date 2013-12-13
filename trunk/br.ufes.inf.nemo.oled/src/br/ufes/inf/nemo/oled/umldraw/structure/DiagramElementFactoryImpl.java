/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.umldraw.structure;

import java.util.HashMap;
import java.util.Map;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.impl.AssociationImpl;
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
 * @author Wei-ju Wu
 * @version 1.0
 */
public class DiagramElementFactoryImpl implements DiagramElementFactory {

  private Map<ElementType, UmlDiagramElement> elementPrototypes = new HashMap<ElementType, UmlDiagramElement>();
  private Map<RelationType, UmlConnection> relationPrototypes = new HashMap<RelationType, UmlConnection>();
  private Map<ElementType, Integer> elementCounters = new HashMap<ElementType, Integer>();
  private Map<RelationType, Integer> relationCounters = new HashMap<RelationType, Integer>();
  private StructureDiagram diagram;
  @SuppressWarnings("unused")
private RefOntoUMLFactory factory;
  
  /**
   * Constructor.
   * @param aDiagram the diagram this factory belongs to
   */
  public DiagramElementFactoryImpl(StructureDiagram aDiagram) {
    diagram = aDiagram;
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
    elementCounters.put(ElementType.NOTE, 0);
    
    Kind kind = (RefOntoUML.Kind)ModelHelper.createType(ElementType.KIND,"Kind");
    ClassElement kindElement = (ClassElement) ClassElement.getPrototype().clone();
    kindElement.setClassifier(kind);
    elementPrototypes.put(ElementType.KIND, kindElement);
    elementCounters.put(ElementType.KIND, 0);
    
    Quantity quantity = (RefOntoUML.Quantity)ModelHelper.createType(ElementType.QUANTITY,"Quantity");
    ClassElement quantityElement = (ClassElement) ClassElement.getPrototype().clone();
    quantityElement.setClassifier(quantity);
    elementPrototypes.put(ElementType.QUANTITY, quantityElement);
    elementCounters.put(ElementType.QUANTITY, 0);
        
    Collective collective = (RefOntoUML.Collective)ModelHelper.createType(ElementType.COLLECTIVE,"Collective");   
    ClassElement collectiveElement = (ClassElement) ClassElement.getPrototype().clone();
    collectiveElement.setClassifier(collective);
    elementPrototypes.put(ElementType.COLLECTIVE, collectiveElement);
    elementCounters.put(ElementType.COLLECTIVE, 0);
        
    SubKind subkind = (RefOntoUML.SubKind)ModelHelper.createType(ElementType.SUBKIND,"SubKind");   
    ClassElement subkindElement = (ClassElement) ClassElement.getPrototype().clone();
    subkindElement.setClassifier(subkind);
    elementPrototypes.put(ElementType.SUBKIND, subkindElement);
    elementCounters.put(ElementType.SUBKIND, 0);
        
    Phase phase = (RefOntoUML.Phase)ModelHelper.createType(ElementType.PHASE,"Phase");    
    ClassElement phaseElement = (ClassElement) ClassElement.getPrototype().clone();
    phaseElement.setClassifier(phase);
    elementPrototypes.put(ElementType.PHASE, phaseElement);
    elementCounters.put(ElementType.PHASE, 0);
        
    Role role = (RefOntoUML.Role)ModelHelper.createType(ElementType.ROLE,"Role");  
    ClassElement roleElement = (ClassElement) ClassElement.getPrototype().clone();
    roleElement.setClassifier(role);
    elementPrototypes.put(ElementType.ROLE, roleElement);
    elementCounters.put(ElementType.ROLE, 0);
        
    Category category = (RefOntoUML.Category)ModelHelper.createType(ElementType.CATEGORY,"Category");  
    ClassElement categoryElement = (ClassElement) ClassElement.getPrototype().clone();
    categoryElement.setClassifier(category);
    elementPrototypes.put(ElementType.CATEGORY, categoryElement);
    elementCounters.put(ElementType.CATEGORY, 0);
        
    RoleMixin rolemixin = (RefOntoUML.RoleMixin)ModelHelper.createType(ElementType.ROLEMIXIN,"RoleMixin");   
    ClassElement rolemixinElement = (ClassElement) ClassElement.getPrototype().clone();
    rolemixinElement.setClassifier(rolemixin);
    elementPrototypes.put(ElementType.ROLEMIXIN, rolemixinElement);
    elementCounters.put(ElementType.ROLEMIXIN, 0);
        
    Mixin mixin = (RefOntoUML.Mixin)ModelHelper.createType(ElementType.MIXIN,"Mixin");   
    ClassElement mixinElement = (ClassElement) ClassElement.getPrototype().clone();
    mixinElement.setClassifier(mixin);
    elementPrototypes.put(ElementType.MIXIN, mixinElement);
    elementCounters.put(ElementType.MIXIN, 0);
        
    Mode mode = (RefOntoUML.Mode)ModelHelper.createType(ElementType.MODE,"Mode");   
    ClassElement modeElement = (ClassElement) ClassElement.getPrototype().clone();
    modeElement.setClassifier(mode);
    elementPrototypes.put(ElementType.MODE, modeElement);
    elementCounters.put(ElementType.MODE, 0);
        
    Relator relator = (RefOntoUML.Relator)ModelHelper.createType(ElementType.RELATOR,"Relator");   
    ClassElement relatorElement = (ClassElement) ClassElement.getPrototype().clone();
    relatorElement.setClassifier(relator);
    elementPrototypes.put(ElementType.RELATOR, relatorElement);
    elementCounters.put(ElementType.RELATOR, 0);
        
    DataType datatype = (RefOntoUML.DataType)ModelHelper.createType(ElementType.DATATYPE,"Datatype");
    ClassElement datatypeElement = (ClassElement) ClassElement.getPrototype().clone();
    datatypeElement.setClassifier(datatype);
    datatypeElement.setShowAttributes(true);
    elementPrototypes.put(ElementType.DATATYPE, datatypeElement);
    elementCounters.put(ElementType.DATATYPE, 0);
  }

  /**
   * Initializes the map with the connection prototypes.
   */
  private void setupConnectionMaps() {

	factory = RefOntoUMLFactory.eINSTANCE;
		
    Generalization generalization = (RefOntoUML.Generalization)ModelHelper.createRelationship(RelationType.GENERALIZATION, "Generalization");    
    GeneralizationElement generalizationElement = (GeneralizationElement) GeneralizationElement.getPrototype().clone();
    generalizationElement.setRelationship(generalization);
    relationPrototypes.put(RelationType.GENERALIZATION, generalizationElement);
    relationCounters.put(RelationType.GENERALIZATION, 0);
        
    Characterization characterization = (RefOntoUML.Characterization)ModelHelper.createRelationship(RelationType.CHARACTERIZATION, "Characterization");   
    AssociationElement characterizationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    characterizationElement.setRelationship(characterization);
    characterizationElement.setAssociationType(RelationType.CHARACTERIZATION);
    characterizationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.CHARACTERIZATION, characterizationElement);
    relationCounters.put(RelationType.CHARACTERIZATION, 0);
    
    FormalAssociation formalAssociation = (RefOntoUML.FormalAssociation)ModelHelper.createRelationship(RelationType.FORMAL, "Formal");
    AssociationElement formalAssociationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    formalAssociationElement.setRelationship(formalAssociation);
    formalAssociationElement.setAssociationType(RelationType.FORMAL);
    formalAssociationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.FORMAL, formalAssociationElement);
    relationCounters.put(RelationType.FORMAL, 0);
          
    MaterialAssociation materialAssociation = (RefOntoUML.MaterialAssociation)ModelHelper.createRelationship(RelationType.MATERIAL, "Material");    
    AssociationElement materialAssociationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    materialAssociationElement.setRelationship(materialAssociation);
    materialAssociationElement.setAssociationType(RelationType.MATERIAL);
    materialAssociationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.MATERIAL, materialAssociationElement);
    relationCounters.put(RelationType.MATERIAL, 0);
        
    Mediation mediation = (RefOntoUML.Mediation)ModelHelper.createRelationship(RelationType.MEDIATION, "Mediation");   
    AssociationElement mediationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    mediationElement.setRelationship(mediation);
    mediationElement.setAssociationType(RelationType.MEDIATION);
    mediationElement.setShowOntoUmlStereotype(true);
    relationPrototypes.put(RelationType.MEDIATION, mediationElement);
    relationCounters.put(RelationType.MEDIATION, 0);
        
    memberOf memberof = (RefOntoUML.memberOf)ModelHelper.createRelationship(RelationType.MEMBEROF, "MemberOf");   
    AssociationElement memberofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    memberofElement.setAssociationType(RelationType.MEMBEROF);
    memberofElement.setRelationship(memberof);
    relationPrototypes.put(RelationType.MEMBEROF, memberofElement);
    relationCounters.put(RelationType.MEMBEROF, 0);
        
    subQuantityOf subquantityof = (RefOntoUML.subQuantityOf)ModelHelper.createRelationship(RelationType.SUBQUANTITYOF, "SubQuantityOf");    
    AssociationElement subquantityofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    subquantityofElement.setAssociationType(RelationType.SUBQUANTITYOF);
    subquantityofElement.setRelationship(subquantityof);
    relationPrototypes.put(RelationType.SUBQUANTITYOF, subquantityofElement);
    relationCounters.put(RelationType.SUBQUANTITYOF, 0);  
        
    subCollectionOf subcollectionof = (RefOntoUML.subCollectionOf)ModelHelper.createRelationship(RelationType.SUBCOLLECTIONOF, "SubCollectionOf");    
    AssociationElement subcollectionofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    subcollectionofElement.setAssociationType(RelationType.SUBCOLLECTIONOF);
    subcollectionofElement.setRelationship(subcollectionof);
    relationPrototypes.put(RelationType.SUBCOLLECTIONOF, subcollectionofElement);
    relationCounters.put(RelationType.SUBCOLLECTIONOF, 0);
        
    componentOf componentof = (RefOntoUML.componentOf)ModelHelper.createRelationship(RelationType.COMPONENTOF, "ComponentOf");    
    AssociationElement componentofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    componentofElement.setAssociationType(RelationType.COMPONENTOF);
    componentofElement.setRelationship(componentof);
    relationPrototypes.put(RelationType.COMPONENTOF, componentofElement);
    relationCounters.put(RelationType.COMPONENTOF, 0);
         
    Derivation derivation = (RefOntoUML.Derivation)ModelHelper.createRelationship(RelationType.DERIVATION, "Derivation"); 
    AssociationElement derivationeElement = (AssociationElement) AssociationElement.getPrototype().clone();
    derivationeElement.setAssociationType(RelationType.DERIVATION);
    derivationeElement.setRelationship(derivation);
    derivationeElement.setIsDashed(true);
    relationPrototypes.put(RelationType.DERIVATION, derivationeElement);
    relationCounters.put(RelationType.DERIVATION, 0); 
         
    Association datatyperelationship = (RefOntoUML.Association)ModelHelper.createRelationship(RelationType.ASSOCIATION, "Association");
    AssociationElement datatyperelationshipElement = (AssociationElement) AssociationElement.getPrototype().clone();
    datatyperelationshipElement.setRelationship(datatyperelationship);
    datatyperelationshipElement.setAssociationType(RelationType.ASSOCIATION);
    relationPrototypes.put(RelationType.ASSOCIATION, datatyperelationshipElement);     
    relationCounters.put(RelationType.ASSOCIATION, 0);
    
    relationPrototypes.put(RelationType.NOTE_CONNECTOR, NoteConnection.getPrototype());
    relationCounters.put(RelationType.NOTE_CONNECTOR, 0);  
  }

  /**
   * {@inheritDoc} This method also create the referred RefOntoUML Type of the UmlNode. 
   */
  public UmlNode createNode(ElementType elementType) 
  {
    UmlNode umlnode = (UmlNode) elementPrototypes.get(elementType).clone();
    
    if(umlnode.getClassifier() != null) umlnode.getClassifier().setName(umlnode.getClassifier().getName() + nextElementCount(elementType));
    
    umlnode.addNodeChangeListener(diagram);
    
    return umlnode;
  }

  public String nextElementCount(ElementType elementType)
  {
	  int count = elementCounters.get(elementType);
	  count += 1;
	  elementCounters.put(elementType, count);
	  return Integer.toString(count);
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
   * {@inheritDoc} This method also create the referred RefOntoUML Relationship of the UmlConnection. 
   */
  @Override
  public UmlConnection createConnection(RelationType relationType, UmlConnection c1, UmlNode node2) 
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
   * {@inheritDoc} This method also create the referred RefOntoUML Relationship of the UmlConnection. 
   */
  public UmlConnection createConnection(RelationType relationType, UmlNode node1, UmlConnection c2) 
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

  public String nextRelationCount(RelationType relationType)
  {
	  int count = relationCounters.get(relationType);
	  count += 1;
	  relationCounters.put(relationType, count);
	  return Integer.toString(count);
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
  private void bindConnection(UmlConnection conn, UmlNode node1, UmlNode node2) {
    conn.setNode1(node1);
    conn.setNode2(node2);
    node1.addConnection(conn);
    node2.addConnection(conn);
  }
  
  private void bindConnection(UmlConnection conn, UmlNode node1, Connection c2) {
	  conn.setNode1(node1);
	  conn.setConnection2(c2);
	  node1.addConnection(conn);
	  c2.addConnection(conn);  
  }
  
  private void bindConnection(UmlConnection conn, Connection c1, UmlNode node2) {
	  conn.setNode2(node2);
	  conn.setConnection1(c1);
	  node2.addConnection(conn);
	  c1.addConnection(conn);  
  }

}
