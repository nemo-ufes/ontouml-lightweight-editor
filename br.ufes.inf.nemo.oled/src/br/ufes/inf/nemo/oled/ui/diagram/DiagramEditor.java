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
package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.DiagramOperations;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.DrawingContextImpl;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.MoveOperation;
import br.ufes.inf.nemo.oled.draw.MultiLineLabel;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.draw.NodeChangeListener;
import br.ufes.inf.nemo.oled.draw.NullElement;
import br.ufes.inf.nemo.oled.draw.RectilinearConnection;
import br.ufes.inf.nemo.oled.draw.Scaling;
import br.ufes.inf.nemo.oled.draw.SimpleConnection;
import br.ufes.inf.nemo.oled.draw.SimpleLabel;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationEndType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.modellingassistant.core.LogAssistant;
import br.ufes.inf.nemo.oled.modellingassistant.core.ModellingAssistant;
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.BaseEditor;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.diagram.commands.ConvertConnectionTypeCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.EditConnectionPointsCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.MoveElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.ResetConnectionPointsCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.ResizeElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetConnectionNavigabilityCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetLabelTextCommand;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationLabel;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.AppCommandListener;
import br.ufes.inf.nemo.oled.util.Command;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class represents the diagram editor. It mainly acts as the
 * component to draw the diagram and to handle the events from the input
 * system. The actual drawing is handled by the UmlDiagram class and its sub
 * allElements.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class DiagramEditor extends BaseEditor implements ActionListener, MouseListener, MouseWheelListener, MouseMotionListener, DiagramNotification, DiagramOperations, NodeChangeListener {

	private static final long serialVersionUID = 4210158437374056534L;
	// For now, we define the margins of the diagram as constants
	private static final double MARGIN_TOP = 0;
	private static final double MARGIN_LEFT = 0;
	private static final double MARGIN_RIGHT = 0;
	private static final double MARGIN_BOTTOM = 0;
	private static final double ADDSCROLL_HORIZONTAL = 0;
	private static final double ADDSCROLL_VERTICAL = 0;
	private transient DrawingContext drawingContext = new DrawingContextImpl();
	private transient EditorMode editorMode;
	private transient SelectionHandler selectionHandler;
	private transient CreationHandler creationHandler;
	private transient LineHandler lineHandler;
	private transient List<UndoableEditListener> editListeners = new ArrayList<UndoableEditListener>();
	private transient Scaling scaling = Scaling.SCALING_100;
	
	// this might be null when the application is started and the pointer still did not move or had the focus of the editor
	private static MouseEvent currentPointerPosition;
	
	private transient ModellingAssistant assistant;

	/**
	 * Reset the transient values for serialization.
	 * @param stream an ObjectInputStream
	 * @throws IOException if I/O error occured
	 * @throws ClassNotFoundException if class was not found
	 */
	private void readObject(ObjectInputStream stream)
			throws IOException, ClassNotFoundException {
		initEditorMembers();
	}

	/**
	 * Initializes the transient editor members.
	 */
	private void initEditorMembers() {
		drawingContext = new DrawingContextImpl();
		selectionHandler = new SelectionHandler(this);
		creationHandler = new CreationHandler(this);
		lineHandler = new LineHandler(this);
		editorMode = selectionHandler;
		mouseEvent = new EditorMouseEvent();
		scaling = Scaling.SCALING_100;
	}

	/**
	 * It is nice to report the mapped coordinates to listeners, so it can be
	 * used for debug output.
	 */
	private List<EditorStateListener> editorListeners =
			new ArrayList<EditorStateListener>();

	/**
	 * To edit the captions in the diagram.
	 */
	private CaptionEditor captionEditor = new CaptionEditor();
	private MultilineEditor multilineEditor = new MultilineEditor();

	/**
	 * This is the root of the shape hierarchy.
	 */
	private StructureDiagram diagram;

	// MouseEvent wrapper
	private transient EditorMouseEvent mouseEvent = new EditorMouseEvent();

	private AppFrame frame;

	private DiagramManager diagramManager;

	public DiagramManager getManager() {
		return diagramManager;
	}

	// The command processor to hold this diagram's operations.
	private UndoManager undoManager = new UndoManager();

	/**
	 * Empty constructor for testing. Do not use !
	 */
	public DiagramEditor() { }

	/**
	 * Constructor. Basic setup of the layout area.
	 * @param frame the frame
	 * @param diagramManager 
	 * @param diagram the diagram
	 */
	public DiagramEditor(AppFrame frame, DiagramManager diagramManager, StructureDiagram diagram) {
		this.frame = frame;
		this.diagramManager = diagramManager;
		this.diagram = diagram;
		this.diagram.addNodeChangeListener(this);
		initEditorMembers();

		assistant = new ModellingAssistant(this);

		// Make sure the this component has no layout diagramManager, is opaque and has
		// no double buffer
		setLayout(null);
		setOpaque(true);
		setDoubleBuffered(true);

		add(captionEditor);
		add(multilineEditor);
		editListeners.add(undoManager);
		captionEditor.getDocument().addUndoableEditListener(undoManager);
		multilineEditor.getDocument().addUndoableEditListener(undoManager);
		diagram.setOrigin(MARGIN_LEFT, MARGIN_TOP);

		installHandlers();
		setToDiagramSize();
	}

	public UmlProject getProject()
	{
		return diagram.getProject();
	}

	/**
	 * Adds an EditorStateListener.
	 * @param l a listener
	 */
	public void addEditorStateListener(EditorStateListener l) {
		editorListeners.add(l);
	}
	
	public int getScalingPercentual()
	{
		return (int)((scaling.getScaleFactor()*100)/100);
	}

	/**
	 * Adjusts this component's preferredSize attribute to the diagram's size.
	 * This also influences the scroll pane which the component is contained in.
	 */
	private void setToDiagramSize() {
		setPreferredSize(new Dimension(
				(int) (diagram.getSize().getWidth() + MARGIN_RIGHT + MARGIN_LEFT + ADDSCROLL_HORIZONTAL),
				(int) (diagram.getSize().getHeight() + MARGIN_BOTTOM + MARGIN_TOP + ADDSCROLL_VERTICAL)));

		invalidate();
	}

	/**
	 * Adds the event handlers.
	 */
	private void installHandlers() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		// BaseEditor listeners
		captionEditor.addActionListener(this);
		
		//Diagram PopupMenu
		addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mouseClicked(MouseEvent e) 
			{			
			    if (SwingUtilities.isRightMouseButton(e))
	            {	            	     	
			    	openDiagramPopupMenu(e);
	            }
			}	       
	    });					
		
		// install Scape KeyBinding
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(' '),"openToolBoxMenu");
		getActionMap().put("openToolBoxMenu", new AbstractAction() {

			private static final long serialVersionUID = 4266982722845577768L;

			/** {@inheritDoc} */
			public void actionPerformed(ActionEvent e) { openToolBoxPopupMenu(); }
		});
		
		// install Escape KeyBinding
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),"cancelEditing");
		getActionMap().put("cancelEditing", new AbstractAction() {

			private static final long serialVersionUID = 4266982722845577768L;

			/** {@inheritDoc} */
			public void actionPerformed(ActionEvent e) { cancelEditing(); }
		});
				
		// install Delete KeyBinding
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"),"deleteSelection");		
		getActionMap().put("deleteSelection", new AbstractAction() {

			private static final long serialVersionUID = -6375878624042384546L;
			
			/** {@inheritDoc} */
			public void actionPerformed(ActionEvent e) { deleteSelection(); }
		});				
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.META_MASK),"deleteSelection");
		getActionMap().put("deleteSelection", new AbstractAction() {
					
			private static final long serialVersionUID = -6375878624042384546L;
		
			/** {@inheritDoc} */
			public void actionPerformed(ActionEvent e) { deleteSelection(); }
		});
	}

	/**
	 * Cancels the current edit action.
	 */
	private void cancelEditing() {
		
		if (captionEditor.isVisible()) {
			captionEditor.hideEditor();
		}
		editorMode.cancel();
		redraw();
		
		// FIXME trying to cancel the drop action at the creation of an element in the diagram.. fail!
		//selectionHandler.getSelection().cancelDragging();
		//if (frame.getToolManager().getOpenPalette().getSelectedElement()!=null) frame.getToolManager().getOpenPalette().getSelectedElement().setSelected(false);
	}

	/**
	 * Removes the current selection.
	 */
	public void deleteSelection() {
		Collection<DiagramElement> elements = getSelectedElements();
		execute(new DeleteElementCommand(this, elements, diagram.getProject()));
	}
	
	/**
	 * Open Diagram PopupMenu
	 */
	public void openDiagramPopupMenu(MouseEvent e)
	{
		DiagramPopupMenu popup = new DiagramPopupMenu(frame);
		popup.show(e.getComponent(),e.getX(),e.getY());
	}
	
	/**
	 * Open ToolBox Menu.
	 */
	public void openToolBoxPopupMenu()
	{
		if (currentPointerPosition==null) return;
		int xp = currentPointerPosition.getX();
		int yp = currentPointerPosition.getY();
		if (xp <= diagram.getAbsoluteX1() || xp >= diagram.getAbsoluteX2()) return;
		if (yp < diagram.getAbsoluteY1() || yp > diagram.getAbsoluteY2()) return;
				
		DiagramElement elem = diagram.getChildAt(currentPointerPosition.getX(), currentPointerPosition.getY());		
		if (elem instanceof NullElement){
			ToolboxPopupMenu menu = new ToolboxPopupMenu(frame);
			menu.show((Component)diagramManager.getCurrentDiagramEditor(), (int)currentPointerPosition.getX(), (int) currentPointerPosition.getY());				
		}
	}

	// *************************************************************************
	// ***** Drawing the component
	// *******************************************

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
		Rectangle clipBounds = new Rectangle();
		g.getClipBounds(clipBounds);
		paintComponent(g, clipBounds, true);
	}

	/**
	 * Paints the component into a non-screen Graphics object.
	 * @param g the Graphics object
	 */
	public void paintComponentNonScreen(Graphics g) {
		Dimension canvasSize = getTotalCanvasSize();
		Rectangle clipBounds = new Rectangle(0, 0, canvasSize.width,
				canvasSize.height);
		g.setClip(clipBounds);
		paintComponent(g, clipBounds, false);
	}
		
	/**
	 * Paints this component with a specified bounds object.
	 * @param g the graphics context
	 * @param bounds the bounding rectangle to repaint
	 * @param toScreen true if rendered to screen, false otherwise
	 * otherwise
	 */
	private void paintComponent(Graphics g, Rectangle bounds, boolean toScreen) {
		Graphics2D g2d = (Graphics2D) g;
		setRenderingHints(g2d);
		if (scaling.getScaleFactor() != 1.0) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		}

		boolean gridVisible = diagram.isGridVisible();
		Color background = Color.WHITE;
		if (toScreen) {
			// Scaling is only interesting if rendering to screen
			scaleDiagram(g2d);			
		} else {
			diagram.setGridVisible(false);
			background = Color.WHITE;
		}

		int width = (int)(diagram.getSize().getWidth()+ MARGIN_RIGHT + MARGIN_LEFT + ADDSCROLL_HORIZONTAL);
		int height = (int)(diagram.getSize().getHeight() + MARGIN_BOTTOM + MARGIN_TOP + ADDSCROLL_VERTICAL);
		int newHeigth = (int)(height*scaling.getScaleFactor());
		int newWidth = (int)(width*scaling.getScaleFactor());
		setPreferredSize(new Dimension(newWidth, newHeigth));
				
		bounds = new Rectangle((int)width,(int)height);
		clearScreen(g, bounds, background);
		drawingContext.setGraphics2D(g2d, bounds);				
		diagram.draw(drawingContext);
		// Draw user interface specific allElements (e.g. selections)
		if (toScreen) {
			editorMode.draw(drawingContext);
		}
		restoreRenderingHints(g2d);
		diagram.setGridVisible(gridVisible);
		
		revalidate();
		repaint();
	}

	/**
	 * Sets the rendering hints used in the editor.
	 * @param g2d the Graphics2D object
	 */
	private void setRenderingHints(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * Resets the rendering hints used in the editor.
	 * @param g2d the Graphics2D object
	 */
	private void restoreRenderingHints(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
		/*
    if (scaling.getScaleFactor() != 1.0) {
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }*/
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_DEFAULT);
	}

	/**
	 * Fills the screen with the background color.
	 * @param g the Graphics object
	 * @param bounds the bounds to draw within
	 * @param color the background color
	 */
	private void clearScreen(Graphics g, Rectangle bounds, Color color) {
		g.setColor(color);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	/**
	 * Scales the diagram.
	 * @param g2d the Graphics2D object
	 */
	private void scaleDiagram(Graphics2D g2d) {
		double scaleFactor = scaling.getScaleFactor();
		g2d.scale(scaleFactor, scaleFactor);		
	}

	// ************************************************************************
	// ***** ActionListener
	// *********************************

	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		stopEditing();
	}

	/**
	 * Stops the editing process if one was active.
	 * @return true if editor was closed, false if nothing happened
	 */
	private boolean stopEditing() {
		TextEditor currentEditor = null;
		if (captionEditor.isVisible()) {
			currentEditor = captionEditor;
		}
		if (multilineEditor.isVisible()) {
			currentEditor = multilineEditor;
		}

		//O problema est� aqui. � necess�rio veirificar o modo do editor
		if (currentEditor != null && currentEditor.isVisible()) {
			String text = currentEditor.getText();
			Label label = currentEditor.getLabel();
			SetLabelTextCommand command = new SetLabelTextCommand(this, label, text,diagramManager.getCurrentProject());
			execute(command);
			currentEditor.hideEditor();
			repaint();
			return true;
		}
		return false;
	}

	// ************************************************************************
	// ***** MouseListener
	// *********************************

	/**
	 * {@inheritDoc}
	 */
	public void mousePressed(MouseEvent e) {
		if (!stopEditing()) {
			editorMode.mousePressed(convertMouseEvent(e));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseReleased(MouseEvent e) {
		if (!stopEditing()) {
			editorMode.mouseReleased(convertMouseEvent(e));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseClicked(MouseEvent e) {
		if (!stopEditing()) {			
			editorMode.mouseClicked(convertMouseEvent(e));
		}
	}

	// ************************************************************************
	// ***** MouseMotionListener
	// *********************************

	/**
	 * {@inheritDoc}
	 */
	public void mouseExited(MouseEvent e) { 
		EditorMouseEvent evt = convertMouseEvent(e);
		currentPointerPosition = evt.getMouseEvent();
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseEntered(MouseEvent e) {
		EditorMouseEvent evt = convertMouseEvent(e);
		currentPointerPosition = evt.getMouseEvent();
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseMoved(MouseEvent e) {
		EditorMouseEvent evt = convertMouseEvent(e);
		currentPointerPosition = evt.getMouseEvent();		
		editorMode.mouseMoved(evt);
		notifyCoordinateListeners();		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void mouseDragged(MouseEvent e) {		
		EditorMouseEvent evt = convertMouseEvent(e);
		currentPointerPosition = evt.getMouseEvent();
		editorMode.mouseDragged(evt);
		notifyCoordinateListeners();
	}

	/**
	 * Notifies the coordinate listeners.
	 * Precondition: Mouse coordinates have been previously transformed.
	 */
	private void notifyCoordinateListeners() {
		for (EditorStateListener l : editorListeners) {
			l.mouseMoved(mouseEvent);
		}
	}

	/**
	 * Converts the java.awt.MouseEvent into an EditorMouseEvent.
	 * @param e the MouseEvent
	 * @return the converted event
	 */
	private EditorMouseEvent convertMouseEvent(MouseEvent e) {
		mouseEvent.setMouseEvent(e, scaling);
		return mouseEvent;
	}

	// ************************************************************************
	// ***** BaseEditor information
	// ************************************************************************

	/**
	 * Returns the diagram.
	 * @return the diagram
	 */
	public StructureDiagram getDiagram() { return diagram; }

	/**
	 * Returns the canUndo status.
	 * @return true if can undo, false otherwise
	 */
	public boolean canUndo() { return undoManager.canUndo(); }

	/**
	 * Returns the canRedo status.
	 * @return true if can redo, false otherwise
	 */
	public boolean canRedo() { return undoManager.canRedo(); }

	/**
	 * Clears the undo diagramManager.
	 */
	public void clearUndoManager() {
		undoManager.discardAllEdits();
	}

	/**
	 * Returns the current selection.
	 * @return the selected element
	 */
	public List<DiagramElement> getSelectedElements() {
		return selectionHandler.getSelectedElements();
	}

	/**
	 * Returns the total canvas size for export functions. The total size
	 * includes the margins
	 * @return the total canvas size
	 */
	public Dimension getTotalCanvasSize() {
		Dimension2D diagramSize = diagram.getSize();
		Dimension result = new Dimension();
		result.width = (int) (diagramSize.getWidth() + MARGIN_LEFT + MARGIN_RIGHT);
		result.height = (int) (diagramSize.getHeight() + MARGIN_TOP +
				MARGIN_BOTTOM);
		return result;
	}

	// ************************************************************************
	// ***** BaseEditor commands. These are invoked by external clients, the main
	// ***** purpose is to provide the external interface for menu commands
	// ***** and sorts.
	// ************************************************************************

	/**
	 * Undoes the last operation.
	 */
	public void undo() { undoManager.undo(); }

	/**
	 * Redoes the last operation.
	 */
	public void redo() { undoManager.redo(); }

	/**
	 * Rescales the view.
	 * @param aScaling a Scaling object
	 */
	public void setScaling(Scaling aScaling) {
		scaling = aScaling;		
		frame.getStatusBar().reportZoomPercentual(aScaling.toString());
		revalidate();
		repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.isControlDown()){
            if (e.getWheelRotation() < 0)
            {
            	for (int i = 0; i< Math.abs(e.getWheelRotation());i++) zoomIn();
            }
            if (e.getWheelRotation() > 0)
            {
            	for (int i = 0; i< Math.abs(e.getWheelRotation());i++) zoomOut();
            }
		}
	}
	
	public void zoomOut()
	{			
		if (scaling.equals(Scaling.SCALING_150)) setScaling(Scaling.SCALING_145); 
		else if (scaling.equals(Scaling.SCALING_145)) setScaling(Scaling.SCALING_140);
		else if (scaling.equals(Scaling.SCALING_140)) setScaling(Scaling.SCALING_135);
		else if (scaling.equals(Scaling.SCALING_135)) setScaling(Scaling.SCALING_130);
		else if (scaling.equals(Scaling.SCALING_130)) setScaling(Scaling.SCALING_125);
		else if (scaling.equals(Scaling.SCALING_125)) setScaling(Scaling.SCALING_120);
		else if (scaling.equals(Scaling.SCALING_120)) setScaling(Scaling.SCALING_115);
		else if (scaling.equals(Scaling.SCALING_115)) setScaling(Scaling.SCALING_110);
		else if (scaling.equals(Scaling.SCALING_110)) setScaling(Scaling.SCALING_105);
		else if (scaling.equals(Scaling.SCALING_105)) setScaling(Scaling.SCALING_100);		
		else if (scaling.equals(Scaling.SCALING_100)) setScaling(Scaling.SCALING_95);
		else if (scaling.equals(Scaling.SCALING_95)) setScaling(Scaling.SCALING_90);
		else if (scaling.equals(Scaling.SCALING_90)) setScaling(Scaling.SCALING_85);
		else if (scaling.equals(Scaling.SCALING_85)) setScaling(Scaling.SCALING_80);
		else if (scaling.equals(Scaling.SCALING_80)) setScaling(Scaling.SCALING_75);
		else if (scaling.equals(Scaling.SCALING_75)) setScaling(Scaling.SCALING_70);
		else if (scaling.equals(Scaling.SCALING_70)) setScaling(Scaling.SCALING_65);
		else if (scaling.equals(Scaling.SCALING_65)) setScaling(Scaling.SCALING_60);
		else if (scaling.equals(Scaling.SCALING_60)) setScaling(Scaling.SCALING_55);
		else if (scaling.equals(Scaling.SCALING_55)) setScaling(Scaling.SCALING_50);
	}
	
	public void zoomIn()
	{	
		if (scaling.equals(Scaling.SCALING_50)) setScaling(Scaling.SCALING_55);
		else if (scaling.equals(Scaling.SCALING_55)) setScaling(Scaling.SCALING_60);
		else if (scaling.equals(Scaling.SCALING_60)) setScaling(Scaling.SCALING_65);
		else if (scaling.equals(Scaling.SCALING_65)) setScaling(Scaling.SCALING_70);
		else if (scaling.equals(Scaling.SCALING_70)) setScaling(Scaling.SCALING_75);
		else if (scaling.equals(Scaling.SCALING_75)) setScaling(Scaling.SCALING_80);
		else if (scaling.equals(Scaling.SCALING_80)) setScaling(Scaling.SCALING_85);
		else if (scaling.equals(Scaling.SCALING_85)) setScaling(Scaling.SCALING_90);
		else if (scaling.equals(Scaling.SCALING_90)) setScaling(Scaling.SCALING_95);
		else if (scaling.equals(Scaling.SCALING_95)) setScaling(Scaling.SCALING_100);
		else if (scaling.equals(Scaling.SCALING_100)) setScaling(Scaling.SCALING_105);
		else if (scaling.equals(Scaling.SCALING_105)) setScaling(Scaling.SCALING_110);
		else if (scaling.equals(Scaling.SCALING_110)) setScaling(Scaling.SCALING_115);
		else if (scaling.equals(Scaling.SCALING_115)) setScaling(Scaling.SCALING_120);		
		else if (scaling.equals(Scaling.SCALING_120)) setScaling(Scaling.SCALING_125);
		else if (scaling.equals(Scaling.SCALING_125)) setScaling(Scaling.SCALING_130);
		else if (scaling.equals(Scaling.SCALING_130)) setScaling(Scaling.SCALING_135);
		else if (scaling.equals(Scaling.SCALING_135)) setScaling(Scaling.SCALING_140);
		else if (scaling.equals(Scaling.SCALING_140)) setScaling(Scaling.SCALING_145);
		else if (scaling.equals(Scaling.SCALING_145)) setScaling(Scaling.SCALING_150);		
	}
	
	/**
	 * Sets the editor into selection mode.
	 */
	public void setSelectionMode() {
		editorMode = selectionHandler;
		//FIXME Treat select/unselect with shift key pressed
	}

	/**
	 * Switches the editor into creation mode.
	 * @param elementType the ElementType that indicates what to create
	 */
	public void setCreationMode(ElementType elementType) {
		creationHandler.setElementType(elementType);
		editorMode = creationHandler;
	}

	/**
	 * Switches the editor into connection creation mode.
	 * @param relationType the RelationType to create
	 */
	public void setCreateConnectionMode(RelationType relationType) {
		lineHandler.setRelationType(relationType,
				getDiagram().getElementFactory().getConnectMethod(relationType));
		editorMode = lineHandler;
	}

	/**
	 * Immediate redraw of the view.
	 */
	public void redraw() {
		paintImmediately(0, 0, getWidth(), getHeight());
	}

	/**
	 * Sets the grid to visible.
	 * @param flag true for visible grid, false otherwise
	 */
	public void showGrid(boolean flag) {
		diagram.setGridVisible(flag);
		repaint();
	}

	/**
	 * Activates grid snapping.
	 * @param flag true if snapping should be supported, false otherwise
	 */
	public void snapToGrid(boolean flag) {
		diagram.setSnapToGrid(flag);
	}

	/**
	 * Resets the current connection's points.
	 */
	public void resetConnectionPoints() {
		DiagramElement elem = selectionHandler.getSelectedElements().get(0);
		if (elem instanceof Connection) {
			execute(new ResetConnectionPointsCommand(this, (Connection) elem));
		}
	}

	/**
	 * Brings the current selection to the front.
	 */
	public void bringToFront() {
		if (selectionHandler.getSelectedElements().size() > 0) {
			diagram.bringChildToFront(selectionHandler.getSelectedElements().get(0));
			redraw();
		}
	}

	/**
	 * Puts the current selection to the back.
	 */
	public void putToBack() {
		if (getSelectedElements().size() > 0) {
			diagram.putChildToBack(getSelectedElements().get(0));
			redraw();
		}
	}

	/**
	 * Edits the current selection's properties.
	 */
	public void editProperties() {
		if (getSelectedElements().size() > 0) {
			editProperties(getSelectedElements().get(0));
		}
	}

	/**
	 * Switches a rectilinear connection to a direct one.
	 */
	public void rectilinearToDirect() {
		if (getSelectedElements().size() > 0 &&
				getSelectedElements().get(0) instanceof UmlConnection) {
			UmlConnection conn = (UmlConnection) getSelectedElements().get(0);
			execute(new ConvertConnectionTypeCommand(this, conn, new SimpleConnection()));
			// we can only tell the selection handler to forget about the selection
			selectionHandler.deselectAll();
		}
	}

	/**
	 * Switches a direct connection into a rectilinear one.
	 */
	public void directToRectilinear() {
		if (getSelectedElements().size() > 0 &&
				getSelectedElements().get(0) instanceof UmlConnection) {
			UmlConnection conn = (UmlConnection) getSelectedElements().get(0);
			execute(new ConvertConnectionTypeCommand(this, conn, new RectilinearConnection()));
			// we can only tell the selection handler to forget about the selection
			selectionHandler.deselectAll();
		}
	}

	/**
	 * Sets the end type navigability of the current selected connection.
	 * @param endType the RelationEndType
	 */
	public void setNavigability(RelationEndType endType) {
		if (getSelectedElements().size() > 0 &&
				getSelectedElements().get(0) instanceof UmlConnection) {
			UmlConnection conn = (UmlConnection) getSelectedElements().get(0);
			//Relationship relationship = (Relationship) conn.getModelElement();
			// Setup a toggle
			if (endType == RelationEndType.SOURCE) {
				execute(new SetConnectionNavigabilityCommand(this, conn, endType, true)); //FIXME Improve this = !relationship.isNavigableToElement1()
			}
			if (endType == RelationEndType.TARGET) {
				execute(new SetConnectionNavigabilityCommand(this, conn, endType, true));
			}
		}
	}

	/**
	 * Runs the specified command by this editor's CommandProcessor, which makes
	 * the operation reversible.
	 * @param command the command to run
	 */
	public void execute(Command command) {

		//DeleteCommands may delete elements which must cascade the deletion to others. 
		//It is the case for Classes and associations and generalizations connected to them.
		if (command instanceof DeleteElementCommand){

			DeleteElementCommand delete = (DeleteElementCommand) command;
			for (DiagramElement elem : delete.getElements()) {

				if(elem instanceof Node){

					Collection<DiagramElement> dependencies = new ArrayList<>();
					dependencies.addAll(((Node)elem).getConnections());

					for (DiagramElement diagramElement : dependencies) {
						Collection<DiagramElement> removeList = new ArrayList<>();
						removeList.add(diagramElement);
						execute(new DeleteElementCommand(delete.getNotification(), removeList, delete.getProject()));
					}		
				}
			}
		}



		//


		UndoableEditEvent event = new UndoableEditEvent(this, command);
		for (UndoableEditListener l : editListeners) {
			l.undoableEditHappened(event);
		}

		// We need to run() after notifying the UndoManager in order to ensure
		// correct menu behaviour
		command.run();

		diagramManager.updateUI();

	}

	/*
	 * Notifies the listeners about a state change.
	 //CLEANUP
	private void notifyStateChanged() {
		for (EditorStateListener l : editorListeners) {
			l.stateChanged(this);
		}
	}*/

	// *************************************************************************
	// ***** BaseEditor callbacks
	// *********************************

	/**
	 * Adds the specified SelectionListener.
	 * @param l the SelectionListener to add
	 */
	public void addSelectionListener(SelectionListener l) {
		selectionHandler.addSelectionListener(l);
	}

	/**
	 * Adds the specified AppCommandListener.
	 * @param l the AppCommandListener to add
	 */
	public void addAppCommandListener(AppCommandListener l) {
		selectionHandler.addAppCommandListener(l);
	}

	// *************************************************************************
	// ***** ModelNotification
	// *********************************

	public void notifyChange(List<DiagramElement> elements, ChangeType changeType, NotificationType notificationType)
	{
		editorMode.stateChanged();

		for (EditorStateListener l : editorListeners) {
			l.stateChanged(this, changeType);
		}

		repaint();

		if(changeType == ChangeType.ELEMENTS_REMOVED || (changeType == ChangeType.ELEMENTS_ADDED && notificationType == NotificationType.UNDO))
			selectionHandler.elementRemoved(elements);

		//In case of the three commands  
		if(changeType == ChangeType.ELEMENTS_ADDED || changeType == ChangeType.ELEMENTS_REMOVED || changeType == ChangeType.LABEL_TEXT_SET || changeType == ChangeType.CONNECTION_NAVEGABILITY_SET)
		{
			getProject().setSaveModelNeeded(true);
		}

		diagram.setSaveNeeded(true);		

		showStatus(elements, changeType, notificationType);

		assistant.notifyChange(elements, changeType, notificationType);
	}

	private void showStatus(List<DiagramElement> elements, ChangeType commandType, NotificationType notificationType)
	{

		StringBuilder sb = new StringBuilder();

		if(notificationType == NotificationType.UNDO)
		{
			sb.append("undo");
		}

		else if (notificationType == NotificationType.REDO)
		{
			sb.append("redo");
		}

		switch (commandType) {
		case ELEMENTS_ADDED: 
			if(notificationType == NotificationType.DO) sb.append("added"); else sb.append(" add");
			break;
		case ELEMENTS_REMOVED:
			if(notificationType == NotificationType.DO) sb.append("removed"); else sb.append(" remove");
			break;
		case ELEMENTS_MOVED:
			if(notificationType == NotificationType.DO) sb.append("moved"); else sb.append(" move");
			break;
		case ELEMENTS_RESIZED:
			if(notificationType == NotificationType.DO) sb.append("resized"); else sb.append(" resize");
			break;
		case CONNECTION_NAVEGABILITY_SET:
			if(notificationType == NotificationType.DO) sb.append("navegability set"); else sb.append(" set navegability");
			break;
		case CONNECTION_TYPE_CONVERTED:
			if(notificationType == NotificationType.DO) sb.append("connnection type changed"); else sb.append(" change connnection type");
			break;
		case CONNECTION_POINT_EDITED:
			if(notificationType == NotificationType.DO) sb.append("connection point changed"); else sb.append(" change connnection point");
			break;
		case CONNECTION_POINTS_RESET:
			if(notificationType == NotificationType.DO) sb.append("connection points reset"); else sb.append(" reset connection points");
			break;
		case LABEL_TEXT_SET:
			if(notificationType == NotificationType.DO) sb.append("label text set"); else sb.append(" set label text");
			break;
		default:
			break;	
		}

		sb.append(" : ");

		for (int i = 0; i < elements.size(); i++) {

			DiagramElement element = elements.get(i);

			if(element instanceof ClassElement)
				sb.append(ModelHelper.handleName(((ClassElement)element).getClassifier()) + (i < elements.size()-1 ? ", " : ""));
			else if(element instanceof BaseConnection)
				sb.append(ModelHelper.handleName(((BaseConnection)element).getRelationship()) + (i < elements.size()-1 ? ", " : ""));
			else if (element instanceof SimpleLabel || element instanceof AssociationLabel)
				sb.append(((Label) element).getSource().getLabelText());
		}

		//For modelling assistant
		LogAssistant.getInstance().addLogAction(capitalize(sb.toString()));

		frame.showStatus(capitalize(sb.toString()));
	}

	private String capitalize(String s) {
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}


	// *************************************************************************
	// ***** DiagramEditorOperations
	// *********************************

	/**
	 * {@inheritDoc}
	 */
	public void editProperties(DiagramElement element) {
		if (element instanceof ClassElement) {
			ClassElement classElement = (ClassElement) element;
			EditClassDialog dialog = new EditClassDialog(frame, diagramManager, classElement, true);
			//EditClassDialog2 dialog = new EditClassDialog2(frame);
			//EditClassDialog2 dialog = new EditClassDialog2(frame, diagramManager, classElement, true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			redraw();
		} else if (element instanceof AssociationElement) {
			AssociationElement association = (AssociationElement) element;
			EditAssociationDialog dialog = new EditAssociationDialog(frame, diagramManager, association, true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			redraw();
		} else if (element instanceof GeneralizationElement) {
			GeneralizationElement generalization = (GeneralizationElement) element;
			EditGeneralizationDialogNew dialog = new EditGeneralizationDialogNew(frame,
					generalization, diagramManager, true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			redraw();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void moveElements(MoveOperation[] moveOperations) {
		MoveElementCommand cmd = new MoveElementCommand(this, moveOperations);
		execute(cmd);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNewConnectionPoints(Connection conn, List<Point2D> points) {
		execute(new EditConnectionPointsCommand(this, conn, points));
	}

	/**
	 * {@inheritDoc}
	 */
	public void resizeElement(Node element, Point2D newpos, Dimension2D size) {
		ResizeElementCommand cmd = new ResizeElementCommand(this, element, newpos, size);
		execute(cmd);
	}

	/**
	 * Open an editor for the specified Label object.
	 * @param label the Label object
	 */
	public void editLabel(Label label) {
		if (label != null) {
			if (label instanceof MultiLineLabel) {
				multilineEditor.setFont(drawingContext.getFont(FontType.DEFAULT));
				multilineEditor.showEditor(label, getGraphics());
			} else {
				captionEditor.showEditor(label, getGraphics());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void nodeResized(Node node) { setToDiagramSize(); }

	/**
	 * {@inheritDoc}
	 */
	public void nodeMoved(Node node) { }

	public void requestFocusInEditor() {
		diagramManager.requestFocus();		
	}

	public DiagramManager getDiagramManager() {
		return diagramManager;
	}

	/**
	 * {@inheritDoc}
	 */
	public EditorNature getEditorNature()
	{
		return EditorNature.ONTOUML;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSaveNeeded()
	{
		return diagram.getProject().isSaveModelNeeded() || diagram.isSaveNeeded();
	}

	@Override
	public void dispose() {

	}

}
