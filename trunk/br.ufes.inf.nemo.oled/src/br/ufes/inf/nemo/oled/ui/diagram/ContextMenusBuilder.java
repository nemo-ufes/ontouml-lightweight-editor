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
package br.ufes.inf.nemo.oled.ui.diagram;

import java.util.ArrayList;

import javax.swing.JPopupMenu;

import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Selection;
import br.ufes.inf.nemo.oled.popupmenu.DiagramPopupMenu;
import br.ufes.inf.nemo.oled.popupmenu.MultiSelectionPopupMenu;
import br.ufes.inf.nemo.oled.popupmenu.SingleConnectionPopupMenu;
import br.ufes.inf.nemo.oled.popupmenu.SingleNodePopupMenu;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlDiagramElement;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;

/**
 * This class creates context menus, depending on the specified parameters.
 * 
 * @author Wei-ju Wu, John Guerson
 */
public class ContextMenusBuilder {
	
	private DiagramEditor editor;
	private SingleNodePopupMenu singleNodePopup;	
	private SingleConnectionPopupMenu singleConnectionPopup;	
	private MultiSelectionPopupMenu multiSelectinoPopup;
	@SuppressWarnings("unused")
	private DiagramPopupMenu diagramPopup;
	
	public ContextMenusBuilder(DiagramEditor editor)
	{
		this.editor = editor;
		singleNodePopup = new SingleNodePopupMenu();
		singleConnectionPopup = new SingleConnectionPopupMenu();		
		multiSelectinoPopup = new MultiSelectionPopupMenu();
		diagramPopup = new DiagramPopupMenu(editor);
	}
	
	/**
	 * Created a popup menu for the specified selection.
	 * 
	 * @param selection
	 *            the selection
	 * @return the popup menu
	 */
	public JPopupMenu setContext(Selection selection, double x, double y) {
		if (selection.getElements().size() > 1) {
			multiSelectinoPopup.setSelectedElements((ArrayList<DiagramElement>) selection.getElements(),editor);
			return multiSelectinoPopup;
		} else {
			UmlDiagramElement elem = (UmlDiagramElement) selection.getElement();
			if (elem instanceof Connection) {
				if(elem instanceof AssociationElement){
					// detects when the click is close to the edges...
					double cx1 = ((Connection)elem).getAbsoluteX1();
					double cx2 = ((Connection)elem).getAbsoluteX2();
					double cy1 = ((Connection)elem).getAbsoluteY1();
					double cy2 = ((Connection)elem).getAbsoluteY2();
					double diffx1 = (x-cx1); double diffy1 = (y-cy1);
					double diffx2 = (x-cx2); double diffy2 = (y-cy2);
					if (diffx1<0) diffx1 = diffx1*(-1); if (diffy1<0) diffy1 = diffy1*(-1);
					if (diffx2<0) diffx2 = diffx2*(-1); if (diffy2<0) diffy2 = diffy2*(-1);
					if(diffx1<30 && diffy1<30){	
						singleConnectionPopup.setConnection((UmlConnection)elem,editor,true);
						return singleConnectionPopup;
					}else if(diffx2<30 && diffy2<30){
						singleConnectionPopup.setConnection((UmlConnection)elem,editor,false);
						return singleConnectionPopup;
					}
				}				
				singleConnectionPopup.setConnection((Connection)elem,editor);
				return singleConnectionPopup;				
			}
			singleNodePopup.setNode((UmlNode)elem,editor);
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
