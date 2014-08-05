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
package br.ufes.inf.nemo.oled.draw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;


/**
 * A selection that holds a single rectangular shape. It is designed for reuse,
 * so once created, it can be reinitialized with another shape. A
 *
 * @author Wei-ju Wu
 */
public class NodeSelection implements Selection, NodeChangeListener {

	private static final Color ACCEPT_COLOR = ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM);
	private static final Color HANDLE_FILL_COLOR = ColorPalette.getInstance().getColor(ThemeColor.GREY_LIGHT);
	private static final Color HANDLE_BORDER_COLOR = ColorPalette.getInstance().getColor(ThemeColor.GREY_DARK); 
	
	private static final int HANDLE_SIZE = 6;
	private static final int HANDLE_NW = 0;
	private static final int HANDLE_NE = 1;
	private static final int HANDLE_SW = 2;
	private static final int HANDLE_SE = 3;
	private static final int HANDLE_NORTH = 4;
	private static final int HANDLE_WEST = 5;
	private static final int HANDLE_SOUTH = 6;
	private static final int HANDLE_EAST = 7;
	private Rectangle2D[] handles = new Rectangle2D[8];

	private AbstractNode node;
	private DiagramOperations editor;
	private boolean isMoving, isResizing;
	private Point2D startPos = new Point2D.Double();
	private Point2D anchor = new Point2D.Double();
	private Point2D tmpPos = new Point2D.Double();
	private Dimension2D tmpSize = new DoubleDimension();
	private int resizeDirection = -1;

	/**
	 * A boolean holder class. We avoid to use the CORBA package.
	 */
	static class BooleanHolder {
		private boolean value;
	}

	/**
	 * Constructor.
	 * @param anEditor the editor instance this selection belongs to
	 * @param aNode the node
	 */
	public NodeSelection(DiagramOperations anEditor, AbstractNode aNode) {
		editor = anEditor;
		for (int i = 0; i < 8; i++) {
			handles[i] = new Rectangle2D.Double();
		}
		node = aNode;
		node.addNodeChangeListener(this);
		updateDimensions();		
	}

	/**
	 * Constructor for testing purposes only.
	 */
	public NodeSelection() { }

	/**
	 * {@inheritDoc}
	 */
	public void updateDimensions() {
		copyShapeData();
		setHandlePositions();
	}

	/**
	 * {@inheritDoc}
	 */
	public DiagramElement getElement() { return node; }

	/**
	 * {@inheritDoc}
	 */
	public List<DiagramElement> getElements() {
		List<DiagramElement> result = new ArrayList<DiagramElement>();
		result.add(node);
		return result;
	}

	/**
	 * Copies the size and position data of the shape to the selection data.
	 */
	private void copyShapeData() {
		tmpPos.setLocation(node.getAbsoluteX1(), node.getAbsoluteY1());
		tmpSize.setSize(node.getSize());
	}

	/**
	 * Determines the handle positions.
	 */
	private void setHandlePositions() {
		double absx = node.getAbsoluteX1(), absy = node.getAbsoluteY1(),
		
		swidth = node.getSize().getWidth();
		
		double x = absx - HANDLE_SIZE + (HANDLE_SIZE/2), y = absy - HANDLE_SIZE + (HANDLE_SIZE/2),
		
		//first handle
		width = HANDLE_SIZE, height = HANDLE_SIZE;
		handles[0].setRect(x, y, width, height);

		/** middle */
		x = absx+(swidth/2)-(HANDLE_SIZE/2);
		handles[4].setRect(x, y, width, height);
		
		// second handle
		x = absx + swidth - (HANDLE_SIZE/2);
		handles[1].setRect(x, y, width, height);

		/** middle */
		x = absx - HANDLE_SIZE + (HANDLE_SIZE/2);
		y = absy + (node.getSize().getHeight()/2) - (HANDLE_SIZE/2);
		handles[5].setRect(x, y, width, height);
		
		// third handle
		x = absx - HANDLE_SIZE + (HANDLE_SIZE/2);
		y = absy + node.getSize().getHeight() - (HANDLE_SIZE/2);
		handles[2].setRect(x, y, width, height);

		/** middle */
		x = absx + (swidth/2) - (HANDLE_SIZE/2);		
		handles[6].setRect(x, y, width, height);
				
		// fourth handle
		x = absx + swidth - (HANDLE_SIZE/2);
		handles[3].setRect(x, y, width, height);

		/** middle */
		x = absx + swidth - (HANDLE_SIZE/2);	
		y = absy + (node.getSize().getHeight()/2) - (HANDLE_SIZE/2);
		handles[7].setRect(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDragging() {
		return isMoving || isResizing;
	}

	/**
	 * {@inheritDoc}
	 */
	public void startDragging(double x, double y) {
		
		startPos.setLocation(x, y);
		resizeDirection = getResizeHandle(x, y);
		isResizing = (resizeDirection >= 0);
		isMoving = !isResizing;		
		anchor.setLocation(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	public void stopDragging(double x, double y) {

		//Test if there was an effective move or resize
		if(x != startPos.getX() || y != startPos.getY())
		{
			if (isMoving) {				
				moveSelectedNode(x, y);
				//moved = true;
			} else if (isResizing) {
				editor.resizeElement(node, tmpPos, tmpSize);
			}
		}
		
		//CLEANUP
		//if(!moved & !isResizing)
		//	System.out.println("Didn't move \tX " + x + " Y " + y + "\n\t to\tX " + startPos.getX() + " Y " + startPos.getY());
		
		isMoving = false;
		isResizing = false;
		updateDimensions();
	}

	/**
	 * Action to move the selected node to the specified position.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	private void moveSelectedNode(double x, double y) {
		DiagramElement elem = editor.getDiagram().getChildAt(x, y);

		if (isDraggedToANewParent(elem)) {
			reparentNode((CompositeNode) elem);
		} else if (isDraggedOutOfParent(elem)) {
			reparentNode(editor.getDiagram());
		} else {
			Collection<Node> nodes = new ArrayList<Node>();
			nodes.add(node);
			
			Point2D pos = new Point2D.Double();
			pos.setLocation(tmpPos);
			
			MoveNodeOperation op = new MoveNodeOperation(node, node.getParent(), pos);
			editor.moveElements(new MoveNodeOperation[] {op});					
		}

	}

	/**
	 * Remove this node from its former parent and add it to the specified new
	 * parent node.
	 * @param newparent the new parent node
	 */
	private void reparentNode(CompositeNode newparent) {
		if(node != newparent)
		{
			Point2D pos = new Point2D.Double();
			pos.setLocation(tmpPos);
			
			MoveNodeOperation op = new MoveNodeOperation(node, newparent, pos);
			editor.moveElements(new MoveNodeOperation[] {op});
		}
	}

	/**
	 * Determines if the current element can be dropped into the specified
	 * target. The main conditions are that allElements can not be added to
	 * themselves or to their ancestors.
	 * @param dropTarget the drop target
	 * @return true if can be dropped, false otherwise
	 */
	private boolean isDraggedToANewParent(DiagramElement dropTarget) {
		return dropTarget != node && dropTarget.canNestElements() &&
		!dropTarget.isAncestor(node) &&
		dropTarget.getAbsoluteBounds().contains(getNodeTargetBounds());
	}

	/**
	 * Returns the target bounds of the current node.
	 * @return the target bounds
	 */
	private Rectangle2D getNodeTargetBounds() {
		Rectangle2D result = node.getAbsoluteBounds();
		result.setRect(tmpPos.getX(), tmpPos.getY(), result.getWidth(),
				result.getHeight());
		return result;
	}

	/**
	 * Determines whether the current element is going to be dragged out of a
	 * nesting element into the outermost nester (which is the diagram itself).
	 * @param dropTarget the dropTarget
	 * @return true if dragged out of a nester, false otherwise
	 */
	private boolean isDraggedOutOfParent(DiagramElement dropTarget) {
		return dropTarget == NullElement.getInstance() &&
		node.getParent() != editor.getDiagram();
	}

	/**
	 * {@inheritDoc}
	 */
	public void cancelDragging() { }

	/**
	 * {@inheritDoc}
	 */
	public void updatePosition(double x, double y) {
		double diffx = x - anchor.getX();
		double diffy = y - anchor.getY();
		if (isMoving) {
			moveSelection(diffx, diffy);
		} else {
			resizeSelection(diffx, diffy);
		}
	}

	/**
	 * Moves the selection by the specified amount.
	 * @param diffx the difference to the selection anchor x coordinate
	 * @param diffy the difference to the selection anchor y coordinate
	 */
	protected void moveSelection(double diffx, double diffy) {
		
		Diagram diagram = editor.getDiagram();
		// Can not move out of the left border
		if (node.getAbsoluteX1() + diffx < diagram.getOrigin().getX()) {
			diffx = diagram.getOrigin().getX() - node.getAbsoluteX1();
		}
		// and not out of the top border
		if (node.getAbsoluteY1() + diffy < diagram.getOrigin().getY()) {
			diffy = diagram.getOrigin().getY() - node.getAbsoluteY1();
		}

		tmpPos.setLocation(node.getAbsoluteX1() + diffx,
				node.getAbsoluteY1() + diffy);
		diagram.snap(tmpPos);
	}

	/**
	 * Resizes the specified selection.
	 * @param diffx the difference to the selection anchor x coordinate
	 * @param diffy the difference to the selection anchor y coordinate
	 */
	private void resizeSelection(double diffx, double diffy) {
		BooleanHolder xFlagHolder = new BooleanHolder();
		BooleanHolder yFlagHolder = new BooleanHolder();
		xFlagHolder.value = false;
		yFlagHolder.value = false;
		// parent truncation and minimum size truncation have to be mutual
		// exclusive
		diffx = truncateToMinimumWidth(diffx, xFlagHolder);
		if (!xFlagHolder.value) diffx = truncateToParentX(diffx, xFlagHolder);
		if (!xFlagHolder.value) diffx = truncateToParentWidth(diffx, xFlagHolder);
		diffy = truncateToMinimumHeight(diffy, yFlagHolder);
		if (!yFlagHolder.value) diffy = truncateToParentY(diffy, yFlagHolder);
		if (!yFlagHolder.value) diffy = truncateToParentHeight(diffy, yFlagHolder);

		switch (resizeDirection) {
		case HANDLE_SE:
			resizeSe(diffx, diffy);
			break;
		case HANDLE_NE:
			resizeNe(diffx, diffy);
			break;
		case HANDLE_NW:
			resizeNw(diffx, diffy);
			break;
		case HANDLE_SW:
			resizeSw(diffx, diffy);
			break;
		case HANDLE_NORTH:
			resizeN(diffx, diffy);
			break;
		case HANDLE_WEST:
			resizeW(diffx, diffy);
			break;	
		case HANDLE_SOUTH:
			resizeS(diffx, diffy);
			break;
		case HANDLE_EAST:
			resizeE(diffx, diffy);
			break;	
		default:
			break;
		}
	}

	/**
	 * Resize using the SE handle.
	 * @param diffx the diffx value
	 * @param diffy the diffy value
	 */
	protected void resizeSe(double diffx, double diffy) {
		tmpSize.setSize(node.getSize().getWidth() + diffx,
				node.getSize().getHeight() + diffy);
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapRightLower();
	}

	protected void resizeS(double diffx, double diffy) {
		tmpSize.setSize(node.getSize().getWidth(),
				node.getSize().getHeight() + diffy);
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapRightLower();
	}
	
	/**
	 * Resize using the NE handle.
	 * @param diffx the diffx value
	 * @param diffy the diffy value
	 */
	protected void resizeNe(double diffx, double diffy) {
		tmpPos.setLocation(node.getAbsoluteX1(),
				node.getAbsoluteY1() + diffy);
		tmpSize.setSize(node.getSize().getWidth() + diffx,
				node.getSize().getHeight() - diffy);
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapRightUpper();
	}
	
	protected void resizeE(double diffx, double diffy) {
		tmpSize.setSize(node.getSize().getWidth()+diffx,
				node.getSize().getHeight());
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapRightUpper();
	}
	
	/**
	 * Resize using the NW handle.
	 * @param diffx the diffx value
	 * @param diffy the diffy value
	 */
	protected void resizeNw(double diffx, double diffy) {		
		tmpPos.setLocation(node.getAbsoluteX1() + diffx,
				node.getAbsoluteY1() + diffy);
		tmpSize.setSize(node.getSize().getWidth() - diffx,
				node.getSize().getHeight() - diffy);
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapLeftUpper();
	}
	
	protected void resizeN(double diffx, double diffy)
	{
		tmpPos.setLocation(node.getAbsoluteX1(),
				node.getAbsoluteY1() + diffy);
		tmpSize.setSize(node.getSize().getWidth(),
				node.getSize().getHeight() - diffy);
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapLeftUpper();
	}
	
	/**
	 * Resize using the SW handle.
	 * @param diffx the diffx value
	 * @param diffy the diffy value
	 */
	protected void resizeSw(double diffx, double diffy) {
		tmpPos.setLocation(node.getAbsoluteX1() + diffx,
				node.getAbsoluteY1());
		tmpSize.setSize(node.getSize().getWidth() - diffx,
				node.getSize().getHeight() + diffy);
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapLeftLower();
	}

	protected void resizeW(double diffx, double diffy)
	{
		tmpPos.setLocation(node.getAbsoluteX1() + diffx,
				node.getAbsoluteY1());
		tmpSize.setSize(node.getSize().getWidth() - diffx,
				node.getSize().getHeight());
		new CornerSnap(editor.getDiagram(), tmpPos, tmpSize).snapLeftLower();
	}
	
	/**
	 * Truncate the specified diffx, depending on the resizing direction.
	 * A selection can not be made smaller than the Node's minimum size
	 * @param diffx the diffx value
	 * @param success a BooleanHolder to store the success result
	 * @return the diffx value truncated to the minimum size if necessary
	 */
	private double truncateToMinimumWidth(double diffx, BooleanHolder success) {
		double dx = westernSwap(diffx);
		if (node.getSize().getWidth() + dx < node.getMinimumSize().getWidth()) {
			dx = node.getMinimumSize().getWidth() - node.getSize().getWidth();
			success.value = true;
		}
		return westernSwap(dx);
	}

	/**
	 * If the resize direction is on one of the western handles, this function
	 * return the negative of x, otherwise x.
	 * @param x the x value
	 * @return -x if resizeDirection is in the west, x otherwise
	 */
	private double westernSwap(double x) {
		return (resizeDirection == HANDLE_NW || resizeDirection == HANDLE_SW || resizeDirection == HANDLE_WEST) ?
				-x : x;
	}

	/**
	 * If the resize direction is on one of the northern
	 * @param y the y value
	 * @return the negative y if the resize direction is in the north, y, else
	 */
	private double northernSwap(double y) {
		return (resizeDirection == HANDLE_NW || resizeDirection == HANDLE_NE || resizeDirection == HANDLE_NORTH ) ?
				-y : y;
	}

	/**
	 * Truncate the specified diffy, depending on the resizing direction.
	 * A selection can not be made smaller than the Node's minimum size
	 * @param diffy the diffy value
	 * @param success a BooleanHolder to store the success result
	 * @return the diffy value truncated to the minimum size if necessary
	 */
	private double truncateToMinimumHeight(double diffy, BooleanHolder success) {
		double dy = northernSwap(diffy);
		if (node.getSize().getHeight() + dy < node.getMinimumSize().getHeight()) {
			dy = node.getMinimumSize().getHeight() - node.getSize().getHeight();
			success.value = true;
		}
		return northernSwap(dy);
	}

	/**
	 * Truncates the drag position to the parent's absolute x position.
	 * @param diffx the diffx value
	 * @param success a BooleanHolder to store the success result
	 * @return the truncated value
	 */
	private double truncateToParentX(double diffx, BooleanHolder success) {
		double dx = diffx;
		if (node.getParent() != null &&
				node.getAbsoluteX1() + diffx < node.getParent().getAbsoluteX1()) {
			dx -= ((node.getAbsoluteX1() + diffx) - node.getParent().getAbsoluteX1());
			success.value = true;
		}
		return dx;
	}

	/**
	 * Truncates the drag position to the parent's absolute y position.
	 * @param diffy the diffy value
	 * @param success a BooleanHolder to store the success result
	 * @return the truncated value
	 */
	private double truncateToParentY(double diffy, BooleanHolder success) {
		double dy = diffy;
		if (node.getParent() != null &&
				node.getAbsoluteY1() + diffy < node.getParent().getAbsoluteY1()) {
			dy -= ((node.getAbsoluteY1() + diffy) - node.getParent().getAbsoluteY1());
			success.value = true;
		}
		return dy;
	}

	/**
	 * Truncates the specified diffx to the parent's width to prevent that
	 * it is drawn outside of the parent's bounds.
	 * @param diffx the diffx value
	 * @param success a BooleanHolder to store the success result
	 * @return the truncated diffx value
	 */
	private double truncateToParentWidth(double diffx, BooleanHolder success) {
		double dx = diffx;
		if (node.getParent() != null &&
				node.getAbsoluteX2() + diffx > node.getParent().getAbsoluteX2()) {
			dx -= (node.getAbsoluteX2() + diffx) - node.getParent().getAbsoluteX2();
			success.value = true;
		}
		return dx;
	}

	/**
	 * Truncates the specified diffy to the parents height to prevent that the
	 * selection is drawn outside of it's parent's bounds.
	 * @param diffy the diffy value
	 * @param success a BooleanHolder to store the success result
	 * @return the truncated diffy value
	 */
	private double truncateToParentHeight(double diffy, BooleanHolder success) {
		double dy = diffy;
		if (node.getParent() != null &&
				node.getAbsoluteY2() + diffy > node.getParent().getAbsoluteY2())  {
			dy -= (node.getAbsoluteY2() + diffy) - node.getParent().getAbsoluteY2();
			success.value = true;
		}
		return dy;
	}

	/**
	 * {@inheritDoc}
	 */
	public void draw(DrawingContext drawingContext) {
		if (isDragging()) {
			if (isMoving) drawDropTargetSilhouette(drawingContext);
			drawSilhouette(drawingContext);
		} else {
			drawHandles(drawingContext);
		}
	}

	/**
	 * Draws the rectangular silhouette of this selection.
	 * @param drawingContext the DrawingContext object
	 */
	private void drawSilhouette(DrawingContext drawingContext) {
		drawingContext.drawRectangle(tmpPos.getX(), tmpPos.getY(),
				tmpSize.getWidth(), tmpSize.getHeight(), null/*ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM),null*/);
	}

	/**
	 * Draws a drop target silhouette if possible.
	 * @param drawingContext the DrawingContext
	 */
	private void drawDropTargetSilhouette(DrawingContext drawingContext) {
		DiagramElement element = editor.getDiagram().getChildAt(tmpPos.getX(),
				tmpPos.getY());
		if (isDraggedToANewParent(element)) {
			Rectangle2D targetBounds = element.getAbsoluteBounds();
			// create an even larger bounds rectangle and draw it
			Rectangle2D targetSilhouette = (Rectangle2D) targetBounds.clone();
			targetSilhouette.setRect(targetBounds.getX() - 5, targetBounds.getY(),
					targetBounds.getWidth() + 10, targetBounds.getHeight() + 5);
			drawingContext.drawRectangle(targetBounds.getX() - 5,
					targetBounds.getY() - 5, targetBounds.getWidth() + 10,
					targetBounds.getHeight() + 10, ACCEPT_COLOR, null);
		}
	}

	/**
	 * A selected ClassShape displays resizing handles.
	 * @param drawingContext the DrawingContext
	 */
	private void drawHandles(DrawingContext drawingContext) {
		for (int i = 0; i < handles.length; i++) {
			drawingContext.drawRectangle(handles[i].getX(), handles[i].getY(),
					handles[i].getWidth(), handles[i].getHeight(), HANDLE_BORDER_COLOR, HANDLE_FILL_COLOR);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(double x, double y) {
		if (node.contains(x, y)) return true;
		return getResizeHandle(x, y) >= 0;
	}

	/**
	 * Returns the resize handle index at the specified position.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the handle number
	 */
	private int getResizeHandle(double x, double y) {
		for (int i = 0; i < 8; i++) {
			if (handles[i].contains(x, y)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	public Cursor getCursorForPosition(double x, double y) {
		
		switch (getResizeHandle(x, y)) {
			case HANDLE_NW:
				return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
			case HANDLE_NE:
				return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
			case HANDLE_SW:
				return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
			case HANDLE_SE:
				return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
			case HANDLE_NORTH:
				return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
			case HANDLE_SOUTH:
				return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
			case HANDLE_WEST:
				return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			case HANDLE_EAST:
				return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);			
			default:
			{
				if(isMoving)
					return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
				else
					return Cursor.getDefaultCursor();
			}
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	public void nodeResized(Node aNode) {
		updateDimensions();
	}

	/**
	 * {@inheritDoc}
	 */
	public void nodeMoved(Node aNode) { 
		
	}
}
