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
package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.undo.AbstractUndoableEdit;


import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.util.Command;


/**
 * This class implements a reversible command to reset a connection's points.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class ResetConnectionPointsCommand extends AbstractUndoableEdit
implements Command {

	private static final long serialVersionUID = -1321480473934728961L;
	private DiagramEditorNotification notification;
	private Connection connection;
	private List<Point2D> originalPoints;

	/**
	 * Constructor.
	 * @param aNotification the notification object
	 * @param conn the connection
	 */
	public ResetConnectionPointsCommand(DiagramEditorNotification aNotification,
			Connection conn) {
		notification = aNotification;
		connection = conn;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		originalPoints = clonePointList(connection.getPoints());
		connection.resetPoints();
		notification.notifyElementsMoved();
	}

	/**
	 * Makes a defensive copy of a point list.
	 * @param points the point list to clone
	 * @return the cloned point list
	 */
	private List<Point2D> clonePointList(List<Point2D> points) {
		List<Point2D> result = new ArrayList<Point2D>();
		for (Point2D point : points) {
			result.add((Point2D) point.clone());
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
		connection.setPoints(originalPoints);
		notification.notifyElementsMoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		super.redo();
		run();
	}
}
