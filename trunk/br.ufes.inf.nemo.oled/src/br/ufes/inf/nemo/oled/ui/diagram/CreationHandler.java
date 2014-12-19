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
package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddNodeCommand;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;


/**
 * This class implements the editor creation mode. Creation is triggered on the
 * mouse down event, which has the nice side-effect that the created element
 * will then be selected after creation. All other methods have an empty
 * implementation.
 *
 * @author Wei-ju Wu, John Guerson
 */
public class CreationHandler implements EditorMode {

  private static final Color ACCEPT_COLOR = new Color(0, 220, 50);
  private DiagramEditor editor;
  private ElementType elementType;
  private Node element;
  private Point2D tmpPos = new Point2D.Double();
  private Rectangle2D cachedBounds;
  
  public boolean isDragging =false;

  public void setPattern(ElementType elementType) 
  {
	this.elementType = elementType;	
	element = null;
	tmpPos = new Point2D.Double();
	cachedBounds = null;
  }
  
  public void clear()
  {
	  element = null;
	  elementType=null;
	  tmpPos = new Point2D.Double();
	  cachedBounds = null;
  }
  
  /**
   * Constructor.
   * @param editor the DiagramEditor
   */
  public CreationHandler(DiagramEditor editor) {
    this.editor = editor;
  }

  /**
   * Artificially set the cached bounds from outside for testing.
   * @param bounds the bounds
   */
  public void setCachedBounds(Rectangle2D bounds) {
    cachedBounds = bounds;
  }

  
  public Node createNode(ElementType stereotype) {
	isDragging = false;
    elementType = stereotype;
    element = editor.getDiagramManager().getElementFactory().createNode(elementType,editor.getDiagram());

    //Add mapping from the refontouml element to the diagram element
    ModelHelper.addMapping(((ClassElement)element).getClassifier(), element);
    
    element.setParent(editor.getDiagram());
    
    cachedBounds = null;
    
    return element;
  }
  
  public Node createNode(RefOntoUML.Type type, EObject eContainer) {
	isDragging = true;
    elementType = ElementType.valueOf(type.eClass().getName().toUpperCase());
    
    element = editor.getDiagramManager().getElementFactory().createNode(type,eContainer,editor.getDiagram());
    
    //Add mapping from the refontouml element to the diagram element
    ModelHelper.addMapping(((ClassElement)element).getClassifier(), element);
    
    element.setParent(editor.getDiagram());
    cachedBounds = null;
    
    return element;
  }
  
  public Node createNodeCloning(ElementType stereotype, RefOntoUML.Type type) {
    elementType = stereotype;
    element = editor.getDiagramManager().getElementFactory().createNode(elementType,editor.getDiagram());

    //Add mapping from the refontouml element to the diagram element
    ModelHelper.addMapping(((ClassElement)element).getClassifier(), element);
    
    if(((ClassElement)element).getClassifier() != null) 
	{
		  RefOntoUML.Classifier classifier = (RefOntoUML.Classifier)((ClassElement)element).getClassifier();
		  classifier.setName(((RefOntoUML.Classifier)type).getName());
		  classifier.setVisibility(type.getVisibility());		  
	}
    
    element.setParent(editor.getDiagram());
    cachedBounds = null;
    
    return element;
  }
  
//  public void addNode(ElementType elementType, double x, double y)
//  {
//	  createNode(elementType);
//	  CompositeNode parent = editor.getDiagram();
//	  DiagramElement possibleParent = editor.getDiagram().getChildAt(x, y);
//	  if (isNestingCondition(possibleParent)) parent = (CompositeNode) possibleParent;
//	  AddNodeCommand createCommand = new AddNodeCommand(editor, parent, ((ClassElement)element).getClassifier(), x, y, editor.getDiagram().getProject(), null);
//	  editor.execute(createCommand);
//  }
//  
//  public void addNode(UmlNode umlnode, double x, double y)
//  {
//	  CompositeNode parent = editor.getDiagram();
//	  DiagramElement possibleParent = editor.getDiagram().getChildAt(x, y);
//	  if (isNestingCondition(possibleParent)) parent = (CompositeNode) possibleParent;
//	  AddNodeCommand createCommand = new AddNodeCommand(editor, parent, ((ClassElement)umlnode).getClassifier(), x, y, editor.getDiagram().getProject(),null);
//	  editor.execute(createCommand);
//  }
//  
  /**
   * {@inheritDoc}
   */
  public void mouseClicked(EditorMouseEvent event) 
  { 
	  
  }

  /**
   * {@inheritDoc}
   */
  public void mousePressed(EditorMouseEvent event) {
    CompositeNode parent = editor.getDiagram();
    DiagramElement possibleParent = editor.getDiagram().getChildAt(tmpPos.getX(), tmpPos.getY());
    
    if (isNestingCondition(possibleParent)) {
    	parent = (CompositeNode) possibleParent;
    }
    
    if(element!=null){
	    Classifier elem = ((ClassElement)element).getClassifier();
	    
	    AddNodeCommand addcmd = new AddNodeCommand(editor, parent, ((ClassElement)element).getClassifier(), tmpPos.getX(), tmpPos.getY(), editor.getDiagram().getProject(),(RefOntoUML.Package)((ClassElement)element).getClassifier().eContainer());
	    editor.execute(addcmd);
	        
	    //move all its generalizations too
	    editor.getDiagramManager().moveGeneralizationsToDiagram(elem, elem.eContainer(), editor);
	    
	    //FIXME: Inseri esse código para mover as generalizações. by Tiago
	    editor.getDiagramManager().moveAssociationsToDiagram(elem, elem.eContainer(),editor);
	    
	    
//	    if (!isDragging) {
//	    	editor.getDiagramManager().openModellingAssistant(elem);
//	    }
	    
    }else{	   
    	// CASSIO : DERIVED TYPES PATTERNS
    	editor.cancelEditing();
    	if(elementType == ElementType.UNION){
    		editor.getDiagramManager().openDerivedTypePatternUnion(tmpPos.getX(),tmpPos.getY());
    	}else if(elementType == ElementType.EXCLUSION){
    		editor.getDiagramManager().openDerivedTypePatternExclusion(tmpPos.getX(),tmpPos.getY());
    	}else if(elementType == ElementType.INTERSECTION){
    		editor.getDiagramManager().openDerivedTypePatternIntersection(tmpPos.getX(),tmpPos.getY());
    	}else if (elementType == ElementType.SPECIALIZATION){
    		editor.getDiagramManager().openDerivedTypePatternSpecialization(tmpPos.getX(),tmpPos.getY());
    	}else if (elementType == ElementType.PASTSPECIALIZATION){
    		editor.getDiagramManager().openDerivedTypePatternPastSpecialization(tmpPos.getX(),tmpPos.getY());
    	}else if (elementType == ElementType.PARTICIPATION){
    		editor.getDiagramManager().openDerivedTypePatternParticipation(tmpPos.getX(),tmpPos.getY());
    	}else if(elementType == ElementType.DOMAIN_PATTERN){
    		//Victor and Fabiano Domain Patterns
    		editor.getDiagramManager().runDomainPattern(tmpPos.getX(),tmpPos.getY());
    	}else{
	    	//Victor trying to run some pattern
	    	editor.getDiagramManager().runPattern(elementType, tmpPos.getX(),tmpPos.getY());
    	}
    }
  }

  /**
   * {@inheritDoc}
   */
  public void mouseReleased(EditorMouseEvent event) {
	  
	  
  }

  /**
   * {@inheritDoc}
   */
  public void mouseDragged(EditorMouseEvent event) { }

  /**
   * {@inheritDoc}
   */
  public void mouseMoved(EditorMouseEvent event) {
    tmpPos.setLocation(event.getX(), event.getY());
    editor.redraw();
  }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    DiagramElement elem = editor.getDiagram().getChildAt(tmpPos.getX(),
      tmpPos.getY());
    if (isNestingCondition(elem, drawingContext)) {
      Rectangle2D bounds = cachedBounds;
      drawingContext.drawRectangle(bounds.getX() - 5, bounds.getY() - 5,
        bounds.getWidth() + 10, bounds.getHeight() + 10, ACCEPT_COLOR, null);
    }
    drawSilhouette(drawingContext);
  }

  /**
   * Determines if the node can be nested in the specified nester object.
   * This is the case if canNestElements() is true and the node is within
   * the nester's bounds. This version uses the cached bounds object in
   * order to be used without a drawing context.
   * @param nester the nester object
   * @return true if the nesting condition is true, false otherwise
   */
  private boolean isNestingCondition(DiagramElement nester) {
    if (cachedBounds == null) return false;
    return nester.canNestElements() &&
      nester.getAbsoluteBounds().contains(cachedBounds);
  }

  /**
   * Determines if the node can be nested in the specified nester object.
   * This is the case if canNestElements() is true and the node is within
   * the nester's bounds.
   * @param nester the nester object
   * @param drawingContext the drawingContext
   * @return true if the nesting condition is true, false otherwise
   */
  private boolean isNestingCondition(DiagramElement nester,
    DrawingContext drawingContext) {
    return nester.canNestElements() &&
      nester.getAbsoluteBounds().contains(getElementBounds(drawingContext));
  }

  /**
   * Returns the element bounds for the current node to be created. The size
   * of the element is only known if put into a drawing context and setting
   * a temporary parent.
   * @param drawingContext the drawing context
   * @return the element bounds
   */
  private Rectangle2D getElementBounds(DrawingContext drawingContext) {
    if (cachedBounds == null && element!=null) {
       element.recalculateSize(drawingContext);
       cachedBounds = element.getAbsoluteBounds();
    }
    if(cachedBounds!=null)cachedBounds.setRect(tmpPos.getX(), tmpPos.getY(), cachedBounds.getWidth(), cachedBounds.getHeight());
    return cachedBounds;
  }

  /**
   * Draws the silhouette of the element to be added.
   * @param drawingContext the drawing context
   */
  private void drawSilhouette(DrawingContext drawingContext) {
	  if(drawingContext!=null){
		  Rectangle2D bounds = getElementBounds(drawingContext);
		  if(bounds!=null){
			  drawingContext.drawRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), null);
		  }
	  }  
  }

  /**
   * {@inheritDoc}
   */
  public void stateChanged() { }

  /**
   * {@inheritDoc}
   */
  public void cancel() { }


}
