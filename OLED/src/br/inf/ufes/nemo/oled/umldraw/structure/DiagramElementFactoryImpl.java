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
package br.inf.ufes.nemo.oled.umldraw.structure;

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
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import br.inf.ufes.nemo.oled.draw.LineConnectMethod;
import br.inf.ufes.nemo.oled.model.ElementType;
import br.inf.ufes.nemo.oled.model.RelationType;
import br.inf.ufes.nemo.oled.umldraw.shared.UmlConnection;
import br.inf.ufes.nemo.oled.umldraw.shared.UmlDiagramElement;
import br.inf.ufes.nemo.oled.umldraw.shared.UmlNode;
import br.inf.ufes.nemo.oled.util.ModelHelper;

/**
 * Implementation of the DiagramElementFactory interface. A
 * DiagramElementFactory instance belongs to a particular UmlDiagram instance,
 * so it can automatically associate elements to the diagram they belong to.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class DiagramElementFactoryImpl implements DiagramElementFactory {

  private Map<ElementType, UmlDiagramElement> elementPrototypes =
    new HashMap<ElementType, UmlDiagramElement>();
  private Map<RelationType, UmlConnection> connectionPrototypes =
    new HashMap<RelationType, UmlConnection>();
  private StructureDiagram diagram;

  /**
   * Constructor.
   * @param aDiagram the diagram this factory belongs to
   */
  public DiagramElementFactoryImpl(StructureDiagram aDiagram) {
    diagram = aDiagram;
    setupElementPrototypeMap();
    setupConnectionPrototypeMap();
  }

  /**
   * Initializes the element map with the element prototypes.
   */

private void setupElementPrototypeMap() {
	
	RefOntoUMLFactory factory = ModelHelper.getFactory();
	
    NoteElement notePrototype = (NoteElement)
      NoteElement.getPrototype().clone();
    elementPrototypes.put(ElementType.NOTE, notePrototype);
    
    Kind kind = factory.createKind();
    kind.setName("Kind1");
    kind.setVisibility(VisibilityKind.PUBLIC);
    ClassElement kindElement = (ClassElement) ClassElement.getPrototype().clone();
    kindElement.setClassifier(kind);
    elementPrototypes.put(ElementType.KIND, kindElement);
    
    Quantity quantity = factory.createQuantity();
    quantity.setName("Quantity1");
    quantity.setVisibility(VisibilityKind.PUBLIC);
    ClassElement quantityElement = (ClassElement) ClassElement.getPrototype().clone();
    quantityElement.setClassifier(quantity);
    elementPrototypes.put(ElementType.QUANTITY, quantityElement);
    
    Collective collective = factory.createCollective();
    collective.setName("Collective1");
    collective.setVisibility(VisibilityKind.PUBLIC);
    ClassElement collectiveElement = (ClassElement) ClassElement.getPrototype().clone();
    collectiveElement.setClassifier(collective);
    elementPrototypes.put(ElementType.COLLECTIVE, collectiveElement);
    
    SubKind subkind = factory.createSubKind();
    subkind.setName("SubKind1");
    subkind.setVisibility(VisibilityKind.PUBLIC);
    ClassElement subkindElement = (ClassElement) ClassElement.getPrototype().clone();
    subkindElement.setClassifier(subkind);
    elementPrototypes.put(ElementType.SUBKIND, subkindElement);
    
    Phase phase = factory.createPhase();
    phase.setName("Phase1");
    phase.setVisibility(VisibilityKind.PUBLIC);
    ClassElement phaseElement = (ClassElement) ClassElement.getPrototype().clone();
    phaseElement.setClassifier(phase);
    elementPrototypes.put(ElementType.PHASE, phaseElement);
    
    Role role = factory.createRole();
    role.setName("Role1");
    role.setVisibility(VisibilityKind.PUBLIC);
    ClassElement roleElement = (ClassElement) ClassElement.getPrototype().clone();
    roleElement.setClassifier(role);
    elementPrototypes.put(ElementType.ROLE, roleElement);
    
    Category category = factory.createCategory();
    category.setName("Category1");
    category.setIsAbstract(true);
    category.setVisibility(VisibilityKind.PUBLIC);
    ClassElement categoryElement = (ClassElement) ClassElement.getPrototype().clone();
    categoryElement.setClassifier(category);
    elementPrototypes.put(ElementType.CATEGORY, categoryElement);
    
    RoleMixin rolemixin = factory.createRoleMixin();
    rolemixin.setName("RoleMixin1");
    rolemixin.setIsAbstract(true);
    rolemixin.setVisibility(VisibilityKind.PUBLIC);
    ClassElement rolemixinElement = (ClassElement) ClassElement.getPrototype().clone();
    rolemixinElement.setClassifier(rolemixin);
    elementPrototypes.put(ElementType.ROLEMIXIN, rolemixinElement);
    
    Mixin mixin = factory.createMixin();
    mixin.setName("Mixin1");
    mixin.setIsAbstract(true);
    mixin.setVisibility(VisibilityKind.PUBLIC);
    ClassElement mixinElement = (ClassElement) ClassElement.getPrototype().clone();
    mixinElement.setClassifier(mixin);
    elementPrototypes.put(ElementType.MIXIN, mixinElement);
    
    Mode mode = factory.createMode();
    mode.setName("Mode1");
    mode.setVisibility(VisibilityKind.PUBLIC);
    ClassElement modeElement = (ClassElement) ClassElement.getPrototype().clone();
    modeElement.setClassifier(mode);
    elementPrototypes.put(ElementType.MODE, modeElement);
    
    Relator relator = factory.createRelator();
    relator.setName("Relator1");
    relator.setVisibility(VisibilityKind.PUBLIC);
    ClassElement relatorElement = (ClassElement) ClassElement.getPrototype().clone();
    relatorElement.setClassifier(relator);
    elementPrototypes.put(ElementType.RELATOR, relatorElement);
       
    DataType datatype = factory.createDataType();
    datatype.setName("Datatype1");
    datatype.setVisibility(VisibilityKind.PUBLIC);
    ClassElement datatypeElement = (ClassElement) ClassElement.getPrototype().clone();
    datatypeElement.setClassifier(datatype);
    datatypeElement.setShowAttributes(true);
    elementPrototypes.put(ElementType.DATATYPE, datatypeElement);
  }

  /**
   * Initializes the map with the connection prototypes.
   */
  private void setupConnectionPrototypeMap() {

	RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	  
    Generalization generalization = factory.createGeneralization();
    GeneralizationElement generalizationElement = (GeneralizationElement) GeneralizationElement.getPrototype().clone();
    generalizationElement.setRelationship(generalization);
    connectionPrototypes.put(RelationType.GENERALIZATION, generalizationElement);
  
    Characterization characterization = factory.createCharacterization();
    characterization.setVisibility(VisibilityKind.PUBLIC);
    AssociationElement characterizationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    characterizationElement.setRelationship(characterization);
    characterizationElement.setAssociationType(RelationType.CHARACTERIZATION);
    characterizationElement.setShowOntoUmlStereotype(true);
    connectionPrototypes.put(RelationType.CHARACTERIZATION, characterizationElement);
        
    FormalAssociation formalAssociation = factory.createFormalAssociation();
    formalAssociation.setVisibility(VisibilityKind.PUBLIC);
    AssociationElement formalAssociationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    formalAssociationElement.setRelationship(formalAssociation);
    formalAssociationElement.setAssociationType(RelationType.FORMAL);
    formalAssociationElement.setShowOntoUmlStereotype(true);
    connectionPrototypes.put(RelationType.FORMAL, formalAssociationElement);
    
    MaterialAssociation materialAssociation = factory.createMaterialAssociation();
    materialAssociation.setVisibility(VisibilityKind.PUBLIC);
    AssociationElement materialAssociationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    materialAssociationElement.setRelationship(materialAssociation);
    materialAssociationElement.setAssociationType(RelationType.MATERIAL);
    materialAssociationElement.setShowOntoUmlStereotype(true);
    connectionPrototypes.put(RelationType.MATERIAL, materialAssociationElement);
    
    Mediation mediation = factory.createMediation();
    mediation.setVisibility(VisibilityKind.PUBLIC);
    AssociationElement mediationElement = (AssociationElement) AssociationElement.getPrototype().clone();
    mediationElement.setRelationship(mediation);
    mediationElement.setAssociationType(RelationType.MEDIATION);
    mediationElement.setShowOntoUmlStereotype(true);
    connectionPrototypes.put(RelationType.MEDIATION, mediationElement);
    
    memberOf memberof = factory.creatememberOf();
    memberof.setVisibility(VisibilityKind.PUBLIC);
    memberof.setIsShareable(true);
    AssociationElement memberofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    memberofElement.setAssociationType(RelationType.MEMBEROF);
    memberofElement.setRelationship(memberof);
    connectionPrototypes.put(RelationType.MEMBEROF, memberofElement);
    
    subQuantityOf subquantityof = factory.createsubQuantityOf();
    subquantityof.setVisibility(VisibilityKind.PUBLIC);
    subquantityof.setIsShareable(false);
    AssociationElement subquantityofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    subquantityofElement.setAssociationType(RelationType.SUBQUANTITYOF);
    subquantityofElement.setRelationship(subquantityof);
    connectionPrototypes.put(RelationType.SUBQUANTITYOF, subquantityofElement);
    
    subCollectionOf subcollectionof = factory.createsubCollectionOf();
    subcollectionof.setVisibility(VisibilityKind.PUBLIC);
    subcollectionof.setIsShareable(true);
    AssociationElement subcollectionofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    subcollectionofElement.setAssociationType(RelationType.SUBCOLLECTIONOF);
    subcollectionofElement.setRelationship(subcollectionof);
    connectionPrototypes.put(RelationType.SUBCOLLECTIONOF, subcollectionofElement);
    
    componentOf componentof = factory.createcomponentOf();
    componentof.setVisibility(VisibilityKind.PUBLIC);
    componentof.setIsShareable(true);
    AssociationElement componentofElement = (AssociationElement) AssociationElement.getPrototype().clone();
    componentofElement.setAssociationType(RelationType.COMPONENTOF);
    componentofElement.setRelationship(componentof);
    connectionPrototypes.put(RelationType.COMPONENTOF, componentofElement);
    
    Derivation derivation = factory.createDerivation();
    derivation.setVisibility(VisibilityKind.PUBLIC);
    AssociationElement derivationeElement = (AssociationElement) AssociationElement.getPrototype().clone();
    derivationeElement.setAssociationType(RelationType.DERIVATION);
    derivationeElement.setRelationship(derivation);
    derivationeElement.setIsDashed(true);
    connectionPrototypes.put(RelationType.DERIVATION, derivationeElement);
   
    Association datatyperelationship = factory.createAssociation();
    datatyperelationship.setVisibility(VisibilityKind.PUBLIC);
    AssociationElement datatyperelationshipElement = (AssociationElement) AssociationElement.getPrototype().clone();
    datatyperelationshipElement.setRelationship(datatyperelationship);
    datatyperelationshipElement.setAssociationType(RelationType.ASSOCIATION);
    connectionPrototypes.put(RelationType.ASSOCIATION, datatyperelationshipElement);
    
    connectionPrototypes.put(RelationType.NOTE_CONNECTOR, NoteConnection.getPrototype());
  }

  /**
   * {@inheritDoc}
   */
  public UmlNode createNode(ElementType elementType) {
    UmlNode umlnode = (UmlNode) elementPrototypes.get(elementType).clone();
    umlnode.addNodeChangeListener(diagram);
    return umlnode;
  }

  /**
   * {@inheritDoc}
   */
  public UmlConnection createConnection(RelationType relationType,
    UmlNode node1, UmlNode node2) {
    UmlConnection prototype = connectionPrototypes.get(relationType);
    UmlConnection conn = null;
    if (prototype != null) {
      conn = (UmlConnection) prototype.clone();
      bindConnection(conn, node1, node2);
    }
    return conn;
  }

  /**
   * {@inheritDoc}
   */
  public LineConnectMethod getConnectMethod(RelationType relationType) {
    UmlConnection conn = connectionPrototypes.get(relationType);
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
}
