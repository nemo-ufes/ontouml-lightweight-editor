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

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;

/**
 * This class converts wrapped connection types.
 *
 * @author Wei-ju Wu, John Guerson
 */
public class ConvertConnectionTypeCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = -8661812094443443847L;
	private UmlConnection connection;
	private Connection newconnection;
	private Connection oldconnection;

	/**
	 * Constructor.
	 * @param aNotification the Notification object
	 * @param umlconn the UmlConnection wrapped object
	 * @param theNewConnection the new connection to be wrapped
	 */
	public ConvertConnectionTypeCommand(DiagramNotification aNotification, UmlConnection umlconn, Connection theNewConnection) {
		this.notification = aNotification;
		connection = umlconn;
		newconnection = theNewConnection;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		oldconnection = connection.getConnection();
		newconnection.copyData(oldconnection);
		connection.setConnection(newconnection);
		//in case of a derivation connected to a material, reset derivation's points
		for(Connection c: connection.getConnections()){
			c.resetPoints();
		}
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(connection);
		
		DiagramEditor d = ((DiagramEditor)notification);
		//notify
		if (d!=null) {
			d.notifyChange((List<DiagramElement>) elements, ChangeType.CONNECTION_TYPE_CONVERTED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)d), this);
			for (UndoableEditListener l : ((DiagramEditor)d).editListeners)  l.undoableEditHappened(event);			
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
		connection.setConnection(oldconnection);
	
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(connection);
		notification.notifyChange(elements, ChangeType.CONNECTION_TYPE_CONVERTED, NotificationType.UNDO);
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
