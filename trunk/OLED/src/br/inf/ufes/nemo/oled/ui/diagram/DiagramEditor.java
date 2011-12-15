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
package br.inf.ufes.nemo.oled.ui.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import br.inf.ufes.nemo.oled.draw.Connection;
import br.inf.ufes.nemo.oled.draw.DiagramElement;
import br.inf.ufes.nemo.oled.draw.DiagramOperations;
import br.inf.ufes.nemo.oled.draw.DrawingContext;
import br.inf.ufes.nemo.oled.draw.DrawingContext.FontType;
import br.inf.ufes.nemo.oled.draw.DrawingContextImpl;
import br.inf.ufes.nemo.oled.draw.Label;
import br.inf.ufes.nemo.oled.draw.MultiLineLabel;
import br.inf.ufes.nemo.oled.draw.Node;
import br.inf.ufes.nemo.oled.draw.NodeChangeListener;
import br.inf.ufes.nemo.oled.draw.RectilinearConnection;
import br.inf.ufes.nemo.oled.draw.Scaling;
import br.inf.ufes.nemo.oled.draw.SimpleConnection;
import br.inf.ufes.nemo.oled.model.ElementType;
import br.inf.ufes.nemo.oled.model.RelationEndType;
import br.inf.ufes.nemo.oled.model.RelationType;
import br.inf.ufes.nemo.oled.model.UmlProject;
import br.inf.ufes.nemo.oled.ui.AppFrame;
import br.inf.ufes.nemo.oled.ui.DiagramManager;
import br.inf.ufes.nemo.oled.ui.diagram.commands.ConvertConnectionTypeCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.DiagramEditorNotification;
import br.inf.ufes.nemo.oled.ui.diagram.commands.EditConnectionPointsCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.MoveElementCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.ResetConnectionPointsCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.ResizeElementCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.SetConnectionNavigabilityCommand;
import br.inf.ufes.nemo.oled.ui.diagram.commands.SetLabelTextCommand;
import br.inf.ufes.nemo.oled.umldraw.shared.UmlConnection;
import br.inf.ufes.nemo.oled.umldraw.structure.AssociationElement;
import br.inf.ufes.nemo.oled.umldraw.structure.ClassElement;
import br.inf.ufes.nemo.oled.umldraw.structure.GeneralizationElement;
import br.inf.ufes.nemo.oled.umldraw.structure.StructureDiagram;
import br.inf.ufes.nemo.oled.util.AppCommandListener;
import br.inf.ufes.nemo.oled.util.Command;


/**
 * This class represents the diagram editor. It mainly acts as the
 * component to draw the diagram and to handle the events from the input
 * system. The actual drawing is handled by the UmlDiagram class and its sub
 * elements.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class DiagramEditor extends JComponent
implements ActionListener, MouseListener, MouseMotionListener,
DiagramEditorNotification, DiagramOperations, NodeChangeListener {

	private static final long serialVersionUID = 4210158437374056534L;
	// For now, we define the margins of the diagram as constants
	private static final double MARGIN_TOP = 25;
	private static final double MARGIN_LEFT = 25;
	private static final double MARGIN_RIGHT = 30;
	private static final double MARGIN_BOTTOM = 30;
	private static final double ADDSCROLL_HORIZONTAL = 800;
	private static final double ADDSCROLL_VERTICAL = 600;
	private transient DrawingContext drawingContext = new DrawingContextImpl();
	private transient EditorMode editorMode;
	private transient SelectionHandler selectionHandler;
	private transient CreationHandler creationHandler;
	private transient LineHandler lineHandler;
	private transient List<UndoableEditListener> editListeners =
		new ArrayList<UndoableEditListener>();
	private transient Scaling scaling = Scaling.SCALING_100;

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

	private DiagramManager manager;
	
	public DiagramManager getManager() {
		return manager;
	}

	// The command processor to hold this diagram's operations.
	private UndoManager undoManager = new UndoManager();

	/**
	 * Empty constructor for testing. Do not use !
	 */
	public DiagramEditor() { }

	/**
	 * Constructor. Basic setup of the layout area.
	 * @param aFrame the frame
	 * @param diagramManager 
	 * @param aDiagram the diagram
	 */
	public DiagramEditor(AppFrame aFrame, DiagramManager aManager, StructureDiagram aDiagram) {
		frame = aFrame;
		manager = aManager;
		diagram = aDiagram;
		diagram.addNodeChangeListener(this);
		initEditorMembers();

		// Make sure the this component has no layout manager, is opaque and has
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

		// Editor listeners
		captionEditor.addActionListener(this);

		// install Escape and Delete KeyBinding
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
		"cancelEditing");
		getActionMap().put("cancelEditing", new AbstractAction() {

			private static final long serialVersionUID = 4266982722845577768L;

			/** {@inheritDoc} */
			public void actionPerformed(ActionEvent e) { cancelEditing(); }
		});
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"),
		"deleteSelection");
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
	}

	/**
	 * Removes the current selection.
	 */
	public void deleteSelection() {
		Collection<DiagramElement> elements = getSelectedElements();
		execute(new DeleteElementCommand(this, elements, manager.getCurrentProject()));
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
		clearScreen(g, bounds, background);
		drawingContext.setGraphics2D(g2d, bounds);	
		diagram.draw(drawingContext);
		// Draw user interface specific elements (e.g. selections)
		if (toScreen) {
			editorMode.draw(drawingContext);
		}
				
		restoreRenderingHints(g2d);
		diagram.setGridVisible(gridVisible);
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
		if (currentEditor != null && currentEditor.isVisible()) {
			String text = currentEditor.getText();
			Label label = currentEditor.getLabel();
			SetLabelTextCommand command = new SetLabelTextCommand(label, text);
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
	public void mouseExited(MouseEvent e) { }

	/**
	 * {@inheritDoc}
	 */
	public void mouseEntered(MouseEvent e) { }

	/**
	 * {@inheritDoc}
	 */
	public void mouseMoved(MouseEvent e) {
		EditorMouseEvent evt = convertMouseEvent(e);
		editorMode.mouseMoved(evt);
		notifyCoordinateListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseDragged(MouseEvent e) {
		EditorMouseEvent evt = convertMouseEvent(e);
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
	// ***** Editor information
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
	 * Clears the undo manager.
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
	// ***** Editor commands. These are invoked by external clients, the main
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
		repaint();
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
			execute(new ConvertConnectionTypeCommand(this, conn,
					new SimpleConnection()));
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
			execute(new ConvertConnectionTypeCommand(this, conn,
					new RectilinearConnection()));
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
				execute(new SetConnectionNavigabilityCommand(this, conn, endType,
						true)); //FIXME Improve this = !relationship.isNavigableToElement1()
			}
			if (endType == RelationEndType.TARGET) {
				execute(new SetConnectionNavigabilityCommand(this, conn, endType,
						true));
			}
		}
	}

	/**
	 * Runs the specified command by this editor's CommandProcessor, which makes
	 * the operation reversible.
	 * @param command the command to run
	 */
	public void execute(Command command) {
		UndoableEditEvent event = new UndoableEditEvent(this, command);
		for (UndoableEditListener l : editListeners) {
			l.undoableEditHappened(event);
		}
		
		// We need to run() after notifying the UndoManager in order to ensure
		// correct menu behaviour
		command.run();
	}

	/**
	 * Notifies the listeners about a state change.
	 */
	private void notifyStateChanged() {
		for (EditorStateListener l : editorListeners) {
			l.stateChanged(this);
		}
	}

	// *************************************************************************
	// ***** Editor callbacks
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
	// ***** DiagramEditorNotification
	// *********************************
	/**
	 * Update method called after a state change from a Command. Such state
	 * changes include move operations.
	 */
	public void notifyElementsMoved() {
		editorMode.stateChanged();
		notifyStateChanged();
		repaint();
		
		frame.showStatus("Element moved");
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyElementAdded(DiagramElement element) {
		for (EditorStateListener l : editorListeners) {
			l.elementAdded(this);
		}
		repaint();
		
		frame.showStatus("Element added");
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyElementRemoved(DiagramElement element) {
		for (EditorStateListener l : editorListeners) {
			l.elementRemoved(this);
		}
		selectionHandler.elementRemoved(element);
		repaint();
		
		frame.showStatus("Element removed");
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyElementResized(DiagramElement element) {
		editorMode.stateChanged();
		notifyStateChanged();
		repaint();
		
		frame.showStatus("Element resized");
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
			EditClassDialog dialog = new EditClassDialog(frame, manager, classElement, true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			redraw();
		} else if (element instanceof AssociationElement) {
			AssociationElement association = (AssociationElement) element;
			EditAssociationDialog dialog = new EditAssociationDialog(frame,
					association, true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			redraw();
		} else if (element instanceof GeneralizationElement) {
			GeneralizationElement generalization = (GeneralizationElement) element;
			EditGeneralizationDialogNew dialog = new EditGeneralizationDialogNew(frame,
					generalization, manager, true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			redraw();
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void moveElements(Command[] moveOperations) {
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
		manager.requestFocus();		
	}
	
	public DiagramManager getDiagramManager() {
		return manager;
	}
}
