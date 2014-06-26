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
package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;


/**
 * This class implements a reversible command to reset a connection's points.
 *
 * @author Wei-ju Wu, John Guerson
 */
public class ResetConnectionPointsCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = -1321480473934728961L;
	private Connection connection;
	private List<Point2D> originalPoints;

	/**
	 * Constructor.
	 * @param aNotification the notification object
	 * @param conn the connection
	 */
	public ResetConnectionPointsCommand(DiagramNotification aNotification, Connection conn) {
		this.notification = aNotification;
		connection = conn;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		originalPoints = clonePointList(connection.getPoints());
		connection.resetPoints();
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(connection);
		
		DiagramEditor d = ((DiagramEditor)notification);
		//notify
		if (d!=null) {
			d.notifyChange((List<DiagramElement>) elements, ChangeType.CONNECTION_POINTS_RESET, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)d), this);
			for (UndoableEditListener l : ((DiagramEditor)d).editListeners)  l.undoableEditHappened(event);			
		}		
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
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(connection);
		notification.notifyChange(elements, ChangeType.CONNECTION_POINTS_RESET, NotificationType.UNDO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		redo = true;
		super.redo();
		run();
	}
}
