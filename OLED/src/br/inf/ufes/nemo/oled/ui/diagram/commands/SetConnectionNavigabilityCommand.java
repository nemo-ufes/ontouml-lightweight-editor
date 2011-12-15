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
package br.inf.ufes.nemo.oled.ui.diagram.commands;

import javax.swing.undo.AbstractUndoableEdit;

import RefOntoUML.Association;
import RefOntoUML.impl.AssociationImpl;
import br.inf.ufes.nemo.oled.model.RelationEndType;
import br.inf.ufes.nemo.oled.umldraw.shared.UmlConnection;
import br.inf.ufes.nemo.oled.util.Command;


/**
 * This class implements a Command to set the navigability of a connection.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class SetConnectionNavigabilityCommand extends AbstractUndoableEdit
implements Command {

	private static final long serialVersionUID = -3010234913328981820L;
	private DiagramEditorNotification notification;
	private UmlConnection connection;
	private RelationEndType endType;
	private boolean oldflag, newflag;

	/**
	 * Constructor.
	 * @param aNotification the Notification object
	 * @param aConnection the Connection object
	 * @param theEndType the relation end type
	 * @param flag the new value
	 */
	public SetConnectionNavigabilityCommand(DiagramEditorNotification
			aNotification, UmlConnection aConnection, RelationEndType theEndType,
			boolean flag) {
		notification = aNotification;
		connection = aConnection;
		endType = theEndType;
		newflag = flag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		super.redo();
		run();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
		setNavigability(oldflag);
		notification.notifyElementsMoved();
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		oldflag = getNavigability();
		setNavigability(newflag);
		notification.notifyElementsMoved();
	}

	/**
	 * Returns the navigability for the current end type.
	 * @return the navigability for the current end type
	 */
	private boolean getNavigability() {
		if(connection.getClassifier() instanceof AssociationImpl)
		{
			Association association = (Association) connection.getClassifier();
			if (endType == RelationEndType.SOURCE) {
				//Does navigable ends contains the source end?
				return association.getNavigableOwnedEnd().contains(association.getOwnedEnd().get(0));
			} 
			else
			{
				//Does navigable ends contains the target end?
				return association.getNavigableOwnedEnd().contains(association.getOwnedEnd().get(1));
			}
		}
		return false;
	}

	/**
	 * Sets the navigability for the current end type.
	 * @param navigable the navigability
	 */
	private void setNavigability(boolean navigable) {
		if(connection.getClassifier() instanceof AssociationImpl)
		{
			Association association = (Association) connection.getClassifier();
			if (endType == RelationEndType.SOURCE) {
				if(navigable)
				{
					//Put the source end in the navigable collection
					association.getNavigableOwnedEnd().add(association.getOwnedEnd().get(0));
				}
				else
				{
					//Remove the source end from the navigable collection
					association.getNavigableOwnedEnd().remove(association.getOwnedEnd().get(0));
				}
			} 
			else
			{
				if(navigable)
				{
					//Put the target end in the navigable collection
					association.getNavigableOwnedEnd().add(association.getOwnedEnd().get(1));
				}
				else
				{
					//Remove the target end from the navigable collection
					association.getNavigableOwnedEnd().remove(association.getOwnedEnd().get(1));
				}
			}
		}
	}
}
