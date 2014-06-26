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
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single selection of a connection. It is used to
 * edit the control points of a connection. The basic idea is to copy the
 * points, edit them in the selection point and set them anew.
 * TODO: Generalize by copying all the points for any case, simple connections
 * draw from the center of the node, the first and last nodes are simply
 * intersection poiints.
 *
 * @author Wei-ju Wu
 */
public abstract class ConnectionSelection implements Selection {

	private DiagramOperations editor;
	private static final Color HANDLE_FILL_COLOR = new Color(0xCCCCCC); //TODO Get from ColorPalette
	private static final Color HANDLE_BORDER_COLOR = new Color(0x666666); //TODO Get from ColorPalette
	private static final int HANDLE_SIZE = 6;
	
	private Connection connection;
	private boolean isDragControlPoint = false, isDragSegment = false;
	private Point2D startPos = new Point2D.Double();
	private static final double EPS_CONTROLPOINT = 3.0;

	/**
	 * Constructor.
	 * @param operations the DiagramOperations object
	 * @param conn the Connection object
	 */
	protected ConnectionSelection(DiagramOperations operations,
			Connection conn) {
		editor = operations;
		connection = conn;
	}

	/**
	 * Returns this selection's connection object.
	 * @return the Connection object
	 */
	protected Connection getConnection() { return connection; }

	/**
	 * Returns the currently edited points.
	 * @return the edited points
	 */
	public abstract List<Point2D> getEditPoints();

	/**
	 * Returns the value of the isDragControlPoint flag.
	 * @return the flag value
	 */
	protected boolean isDragControlPoint() { return isDragControlPoint; }

	/**
	 * Sets the isDragControlPoint flag.
	 * @param flag the flag value
	 */
	protected void setIsDragControlPoint(boolean flag) {
		isDragControlPoint = flag;
	}

	/**
	 * Returns the isDragSegment flag.
	 * @return isDragSegment flag
	 */
	protected boolean isDragSegment() { return isDragSegment; }

	/**
	 * Sets the isDragSegment flag.
	 * @param flag the flag value
	 */
	protected void setIsDragSegment(boolean flag) {
		isDragSegment = flag;
	}

	/**
	 * {@inheritDoc}
	 */
	public DiagramElement getElement() { return connection; }

	/**
	 * {@inheritDoc}
	 */
	public List<DiagramElement> getElements() {
		List<DiagramElement> result = new ArrayList<DiagramElement>();
		result.add(connection);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDragging() { return isDragControlPoint || isDragSegment; }

	/**
	 * {@inheritDoc}
	 */
	public void startDragging(double x, double y) {
		startPos.setLocation(x, y);
		if (getControlPoint(x, y) != null) {
			startDragControlPoint(x, y);
		} else {
			startDragSegment(x, y);
		}
	}

	/**
	 * Returns the control point at the specified coordinates or null if no
	 * control point is found.
	 * @param xcoord the x coordinate
	 * @param ycoord the y coordinate
	 * @return the control point or null
	 */
	protected Point2D getControlPoint(double xcoord, double ycoord) {
		for (Point2D controlPoint : connection.getPoints()) {
			if (controlPoint.distance(xcoord, ycoord) <= EPS_CONTROLPOINT)
				return controlPoint;
		}
		return null;
	}

	/**
	 * Initiate dragging a control point.
	 * @param xcoord the x coordinate
	 * @param ycoord the y coordinate
	 */
	protected abstract void startDragControlPoint(double xcoord, double ycoord);

	/**
	 * Initiate dragging a segment.
	 * @param xcoord the x coordinate
	 * @param ycoord the y coordinate
	 */
	protected abstract void startDragSegment(double xcoord, double ycoord);

	/**
	 * {@inheritDoc}
	 */
	public void stopDragging(double x, double y) {
		//Test if there was an effective move
		if(x != startPos.getX() || y != startPos.getY())
		{
			if (isDragSegment) {
				finishDragSegment(x, y);
				editor.setNewConnectionPoints(getConnection(), copyEditPoints());
			} else if (isDragControlPoint) {
				finishDragControlPoint(x, y);
				editor.setNewConnectionPoints(getConnection(), copyEditPoints());
			}
		}
		
		isDragControlPoint = false;
		isDragSegment = false;
	}

	/**
	 * Returns a copy of the edit points.
	 * @return the edit points copy
	 */
	private List<Point2D> copyEditPoints() {
		List<Point2D> result = new ArrayList<Point2D>();
		for (Point2D p : getEditPoints()) {
			result.add((Point2D) p.clone());
		}
		return result;
	}

	/**
	 * Finish dragging a segment.
	 * @param xcoord the final x coordinate
	 * @param ycoord the final y coordinate
	 */
	protected abstract void finishDragSegment(double xcoord, double ycoord);

	/**
	 * Finish dragging a control point.
	 * @param xcoord the final x coordinate
	 * @param ycoord the final y coordinate
	 */
	protected abstract void finishDragControlPoint(double xcoord, double ycoord);

	/**
	 * {@inheritDoc}
	 */
	public void cancelDragging() {
		isDragControlPoint = false;
		isDragSegment = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void draw(DrawingContext drawingContext) {
		if (isDragging()) {
			drawSilhouette(drawingContext);
		}
		for (Point2D point : connection.getPoints()) {

			drawingContext.drawRectangle(point.getX() - (HANDLE_SIZE/2), point.getY() - (HANDLE_SIZE/2), HANDLE_SIZE, HANDLE_SIZE,
					HANDLE_BORDER_COLOR, HANDLE_FILL_COLOR);

		}
	}

	/**
	 * Draws the selection silhouette.
	 * @param drawingContext the DrawingContext
	 */
	private void drawSilhouette(DrawingContext drawingContext) {
		Point2D from = getEditPoints().get(0);
		for (int i = 1; i < getEditPoints().size(); i++) {
			Point2D to = getEditPoints().get(i);
			drawingContext.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
			from = to;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(double xcoord, double ycoord) {
		return connection.contains(xcoord, ycoord);
	}

	/**
	 * {@inheritDoc}
	 */
	public Cursor getCursorForPosition(double xcoord, double ycoord) {
		return Cursor.getDefaultCursor();
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateDimensions() { }
}
