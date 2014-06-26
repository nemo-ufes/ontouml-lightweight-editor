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

import java.util.List;

import br.ufes.inf.nemo.oled.draw.DiagramElement;

/**
 * Defines an interface to the diagram editor that handles change notifications.
 * 
 * @author Antognoni Albuquerque
 */
public interface DiagramNotification {

	/**
	 * Represents the action type made in a diagram by a command 
	 * @author Antognoni Albuquerque
	 */
	public enum NotificationType {
		DO, 
		UNDO, 
		REDO
	}

	/**
	 * Represents the change type made in a diagram by a command 
	 * @author Antognoni Albuquerque
	 */
	public enum ChangeType {
		ELEMENTS_ADDED,
		ELEMENTS_DRAGGED, 
		ELEMENTS_REMOVED, 
		ELEMENTS_MODIFIED, 
		ELEMENTS_MOVED, 
		ELEMENTS_RESIZED, 
		LABEL_TEXT_SET, 
		CONNECTION_NAVEGABILITY_SET, 
		CONNECTION_POINTS_RESET, 
		CONNECTION_POINT_EDITED, 
		CONNECTION_TYPE_CONVERTED,
		ELEMENTS_ALIGNED,
		VISIBILITY_CHANGED,
		ELEMENTS_COLORED
	}

	/**
	 * This method is called when there is a change done by a Command.
	 * @param element the element which was added
	 */
	void notifyChange(List<DiagramElement> elements, ChangeType changeType, NotificationType notificationType);
}
