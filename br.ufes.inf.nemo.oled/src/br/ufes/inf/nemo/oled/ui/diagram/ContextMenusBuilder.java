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

import javax.swing.JPopupMenu;

import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.Selection;
import br.ufes.inf.nemo.oled.ui.popup.MultiSelectionPopupMenu;
import br.ufes.inf.nemo.oled.ui.popup.SingleConnectionPopupMenu;
import br.ufes.inf.nemo.oled.ui.popup.SingleNodePopupMenu;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlDiagramElement;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.util.AppCommandListener;

/**
 * This class creates context menus, depending on the specified parameters.
 * 
 * @author Wei-ju Wu
 * @version 1.0
 */
public class ContextMenusBuilder {
	
	private SingleNodePopupMenu singleNodePopup;
	private SingleConnectionPopupMenu singleConnectionPopup;
	private MultiSelectionPopupMenu multiSelectinoPopup;
	
	public ContextMenusBuilder()
	{
		singleNodePopup = new SingleNodePopupMenu();
		singleConnectionPopup = new SingleConnectionPopupMenu();
		multiSelectinoPopup = new MultiSelectionPopupMenu();
	}
	
	/**
	 * Created a popup menu for the specified selection.
	 * 
	 * @param selection
	 *            the selection
	 * @return the popup menu
	 */
	public JPopupMenu setContext(Selection selection) {
		if (selection.getElements().size() > 1) {
			return multiSelectinoPopup;
		} else {
			UmlDiagramElement elem = (UmlDiagramElement) selection.getElement();
			if (elem instanceof Connection) {
				singleConnectionPopup.setConnection((Connection)elem);
				return singleConnectionPopup;
			}
			singleNodePopup.setNode((UmlNode)elem);
			return singleNodePopup;
		}
	}
	
	public JPopupMenu setContext(UmlDiagramElement diagramElement) {
		return null;
	}
	
	/**
	 * Adds the specified AppCommandListener.
	 * 
	 * @param l
	 *            the AppCommandListener to add
	 */
	public void addAppCommandListener(AppCommandListener l) {
		singleNodePopup.addAppCommandListener(l);
		singleConnectionPopup.addAppCommandListener(l);
		multiSelectinoPopup.addAppCommandListener(l);
	}
}
