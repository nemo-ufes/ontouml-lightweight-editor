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
package br.ufes.inf.nemo.oled.finder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Element;
import br.ufes.inf.nemo.oled.dialog.DiagramListDialog;
import br.ufes.inf.nemo.oled.dialog.properties.ElementDialogCaller;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;

/**
 * @author John Guerson
 */
public class FoundPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 2665584279780047982L;
	private EObject context;
	private JMenuItem findInProjectMenuItem;
	private JMenuItem findInDiagramMenuItem;
	private JMenuItem propertiesMenuItem;
	
	public FoundPopupMenu(final FoundElement context)
	{
		this.context = context.getElement();
				
		if( (this.context instanceof RefOntoUML.Class) || (this.context instanceof RefOntoUML.DataType) || (this.context instanceof RefOntoUML.Association) || (this.context instanceof RefOntoUML.Property)
		 || (this.context instanceof RefOntoUML.Generalization) || (this.context instanceof RefOntoUML.GeneralizationSet) || (this.context instanceof RefOntoUML.Constraintx) || (this.context instanceof RefOntoUML.Comment))
		{			
			propertiesMenuItem = new JMenuItem("Properties");
			add(propertiesMenuItem);
			propertiesMenuItem.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {				
					ElementDialogCaller.openDialog((RefOntoUML.Element)context.getElement(), ProjectBrowser.frame);		
				}
			});
		}
		
		findInProjectMenuItem = new JMenuItem("Find in Project Browser");
		add(findInProjectMenuItem);
		
		findInDiagramMenuItem = new JMenuItem("Find in Diagrams");
		add(findInDiagramMenuItem);
		
		findInProjectMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				
				ProjectBrowser.frame.getDiagramManager().getFrame().getBrowserManager().getProjectBrowser().getTree().checkModelElement(context.getElement());		
			}
		});
		
		findInDiagramMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				ArrayList<DiagramEditor> diagrams = ProjectBrowser.frame.getDiagramManager().getDiagramEditors((Element)context.getElement());
				DiagramListDialog.open(ProjectBrowser.frame, diagrams,(Element) context.getElement());
			}
		});
	}
}
