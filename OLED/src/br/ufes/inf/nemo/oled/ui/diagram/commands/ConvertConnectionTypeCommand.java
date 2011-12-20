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

import javax.swing.undo.AbstractUndoableEdit;


import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.util.Command;


/**
 * This class converts wrapped connection types.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class ConvertConnectionTypeCommand extends AbstractUndoableEdit
implements Command {

	private static final long serialVersionUID = -8661812094443443847L;
	private UmlConnection connection;
	private Connection newconnection;
	private DiagramEditorNotification notification;
	private Connection oldconnection;

	/**
	 * Constructor.
	 * @param aNotification the Notification object
	 * @param umlconn the UmlConnection wrapped object
	 * @param theNewConnection the new connection to be wrapped
	 */
	public ConvertConnectionTypeCommand(DiagramEditorNotification aNotification,
			UmlConnection umlconn, Connection theNewConnection) {
		notification = aNotification;
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
		notification.notifyElementsMoved();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
		connection.setConnection(oldconnection);
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
