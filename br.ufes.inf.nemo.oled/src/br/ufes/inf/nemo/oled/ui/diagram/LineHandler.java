/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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

package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.geom.Point2D;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.LineConnectMethod;
import br.ufes.inf.nemo.oled.draw.NullElement;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddConnectionCommand;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class is a handler for line shaped allElements.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.1
 */
public class LineHandler implements EditorMode {

  private DiagramEditor editor;
  private Point2D anchor = new Point2D.Double();
  private Point2D tmpPos = new Point2D.Double();
  private DiagramElement source;
  private boolean isDragging;
  private RelationType relationType;
  private LineConnectMethod connectMethod;

  /**
   * Constructor.
   * @param anEditor a DiagramEditor
   */
  public LineHandler(DiagramEditor anEditor) {
    editor = anEditor;
  }

  /**
   * Sets the association type.
   * @param anAssociationType the association type
   * @param aConnectMethod the connect method
   */
  public void setRelationType(RelationType anAssociationType,
    LineConnectMethod aConnectMethod) {
    connectMethod = aConnectMethod;
    relationType = anAssociationType;   
  }

  /**
   * Returns the isDragging property for testing.
   * @return the status for the isDragging property
   */
  public boolean isDragging() { return isDragging; }

  /**
   * {@inheritDoc}
   */
  public void stateChanged() { }

  /**
   * {@inheritDoc}
   */
  public void cancel() { isDragging = false; }

  /**
   * Generic Method for Creating Lines in the Diagram (by John)
   */
  public void createLine(RelationType relationType, DiagramElement source, DiagramElement target)
  {
	  Point2D sourcePoint = new Point2D.Double();
	  Point2D targetPoint = new Point2D.Double();
	  if(source instanceof ClassElement) sourcePoint.setLocation(((ClassElement)source).getAbsCenterX(),((ClassElement)source).getAbsCenterY());
	  if(target instanceof ClassElement) targetPoint.setLocation(((ClassElement)target).getAbsCenterX(),((ClassElement)target).getAbsCenterY());
	  if(source instanceof AssociationElement) sourcePoint.setLocation(((AssociationElement)source).getAbsCenterX(),((AssociationElement)source).getAbsCenterY());
	  if(target instanceof AssociationElement) targetPoint.setLocation(((AssociationElement)target).getAbsCenterX(),((AssociationElement)target).getAbsCenterY());	  
	  LineConnectMethod connectMethod = editor.getDiagram().getElementFactory().getConnectMethod(relationType);
	  if (source!=null && ! (source instanceof NullElement)){
	      if (source instanceof UmlNode) source = (UmlNode) source;
	      else  source = (UmlConnection) source;
	  }
	  connect(editor, connectMethod, relationType, source, target, sourcePoint, targetPoint);    
	  editor.redraw();
  }
  
  /**
   * {@inheritDoc}
   */
  public void mousePressed(EditorMouseEvent event) {
    double mx = event.getX(), my = event.getY();
    DiagramElement elem = editor.getDiagram().getChildAt(mx, my);
    if (elem!=null && ! (elem instanceof NullElement)) {
      anchor.setLocation(mx, my); //TODO Change the anchor to the edge of the Diagram Element
      tmpPos.setLocation(mx, my);
      isDragging = true;
      if (elem instanceof UmlNode)
    	  source = (UmlNode) elem;
      else
    	  source = (UmlConnection) elem;
    }
  }

  /**
   * {@inheritDoc}
   * FIXME Allow Self-Relationships !
   *
   */
  public void mouseReleased(EditorMouseEvent event) {
    double mx = event.getX(), my = event.getY();
    DiagramElement target = editor.getDiagram().getChildAt(mx, my);    
    tmpPos.setLocation(mx, my);
    connect(editor, connectMethod, relationType, source, target, anchor, tmpPos);    
    isDragging = false;
    editor.redraw();
  }

  /**
   * This method performs the connection between the lines itself (by John)
   */
  public void connect(DiagramEditor editor, LineConnectMethod connectMethod, RelationType relationType, DiagramElement source, DiagramElement target, Point2D anchor, Point2D tmpPos)
  {
	    // UmlNode ->(connectedTo) -> UmlNode
	    if (source != null && source instanceof UmlNode && target instanceof UmlNode && target != source) 
	    {
	    	 //invert sides if characterization is pushed from a UmlNode that is not a Mode. It should be from a Mode.
	        if ( (relationType == RelationType.CHARACTERIZATION && ! (((UmlNode)source).getClassifier() instanceof RefOntoUML.Mode) && (((UmlNode)target).getClassifier() instanceof RefOntoUML.Mode)) ||  
	        	 (relationType == RelationType.MEDIATION && ! (((UmlNode)source).getClassifier() instanceof RefOntoUML.Relator) && (((UmlNode)target).getClassifier() instanceof RefOntoUML.Relator)) )
	        {
	        	UmlConnection conn = editor.getDiagram().getElementFactory().createConnection(relationType, (UmlNode) target, (UmlNode) source);
	        	
	        	//Add mapping from the refontouml element to the diagram element
	            ModelHelper.addMapping(((UmlConnection)conn).getRelationship(), conn);
	            
		  	    connectMethod.generateAndSetPointsToConnection(conn, (UmlNode) target, (UmlNode)source, anchor, tmpPos);      
		  	    AddConnectionCommand command = new AddConnectionCommand(editor, editor.getDiagram(), conn.getRelationship(), (Classifier) ((UmlNode)target).getClassifier(), (Classifier) ((UmlNode)source).getClassifier(), editor.getDiagram().getProject(),true,true);
		  	    editor.execute(command);        	
	        }else{
		        UmlConnection conn = editor.getDiagram().getElementFactory().createConnection(relationType, (UmlNode) source, (UmlNode) target);
		        
		        //Add mapping from the refontouml element to the diagram element
	            ModelHelper.addMapping(((UmlConnection)conn).getRelationship(), conn);
	            
		        connectMethod.generateAndSetPointsToConnection(conn, (UmlNode)source, (UmlNode) target, anchor, tmpPos);      
		        AddConnectionCommand command = new AddConnectionCommand(editor, editor.getDiagram(), conn.getRelationship(), (Classifier) ((UmlNode)source).getClassifier(), (Classifier) ((UmlNode)target).getClassifier(), editor.getDiagram().getProject(),true,true);
		        editor.execute(command);
	        }
	    }
	        
	    // UmlNode ->(connectedTo) -> UmlConnection
	    if(source != null && source instanceof UmlNode && target instanceof UmlConnection && target != source)
	    {    	 
	    	 //invert sides if derivation is pushed from the UmlNode (relator), it should be from the UmlConnection (material)
	         if (relationType == RelationType.DERIVATION) { 
	    	     UmlConnection conn = editor.getDiagram().getElementFactory().createConnection(relationType, (UmlConnection) target, (UmlNode) source);   
	    	     
	    	     //Add mapping from the refontouml element to the diagram element
		         ModelHelper.addMapping(((UmlConnection)conn).getRelationship(), conn);
		            
	             connectMethod.generateAndSetPointsToConnection(conn, (UmlConnection) target, (UmlNode)source, anchor, tmpPos);         
		         AddConnectionCommand command = new AddConnectionCommand(editor, editor.getDiagram(), conn.getRelationship(), (Classifier) ((AssociationElement)target).getRelationship(), (Classifier) ((UmlNode)source).getClassifier(), editor.getDiagram().getProject(),true,true);
		         editor.execute(command);
	         }
	    }
	    //UmlConnection ->(connectedTo) -> UmlNode
	    if(target!=null && target instanceof UmlNode && source instanceof UmlConnection && target != source)
	    {
	    	 UmlConnection conn = editor.getDiagram().getElementFactory().createConnection(relationType, (UmlConnection) source, (UmlNode) target);
	    	 
	    	 //Add mapping from the refontouml element to the diagram element
	         ModelHelper.addMapping(((UmlConnection)conn).getRelationship(), conn);
	            
	         connectMethod.generateAndSetPointsToConnection(conn, (UmlConnection)source,  (UmlNode)target, anchor, tmpPos);         
	         AddConnectionCommand command = new AddConnectionCommand(editor, editor.getDiagram(), conn.getRelationship(), (Classifier) ((AssociationElement)source).getRelationship(), (Classifier) ((UmlNode)target).getClassifier(), editor.getDiagram().getProject(),true,true);
	         editor.execute(command);
	    }	  
  }
  
  /**
   * {@inheritDoc}
   */
  public void mouseClicked(EditorMouseEvent event) { }


  /**
   * {@inheritDoc}
   */
  public void mouseDragged(EditorMouseEvent event) {
    double mx = event.getX(), my = event.getY();
    tmpPos.setLocation(mx, my);
    editor.redraw();
  }

  /**
   * {@inheritDoc}
   */
  public void mouseMoved(EditorMouseEvent event) { }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    if (isDragging) {
      connectMethod.drawLineSegments(drawingContext, anchor, tmpPos);
    }
  }
}
