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
package br.ufes.inf.nemo.oled.explorer;

import java.awt.Insets;

import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.DiagramManager;

/**
 * @author John Guerson
 */
public class ProjectToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ProjectTree tree;
	@SuppressWarnings("unused")
	private DiagramManager diagramManager;
//	private JButton btnCompleteSelection;
//	private JButton btnRefresh;
		
	public ProjectToolBar (ProjectTree tree, final DiagramManager diagramManager)
	{		
		this.tree = tree;
		this.diagramManager = diagramManager;
		
		setFloatable(false);
		setMargin(new Insets(5,5,5,5));
		
//		btnRefresh = new JButton("");
//		btnRefresh.setFocusable(false);
//		btnRefresh.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/arrow_refresh.png")));
//		add(btnRefresh);
//		
//		btnCompleteSelection = new JButton("");
//		btnCompleteSelection.addActionListener(new ActionListener() {				
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		diagramManager.getEditorDispatcher().autoComplete();
//        	}
//        });		
//		btnCompleteSelection.setToolTipText("<html>Check the elements dependencies on the tree <br>and complete the selection with the missing dependencies</html>");
//		btnCompleteSelection.setFocusable(false);
//		btnCompleteSelection.setIcon(new ImageIcon(ProjectToolBar.class.getResource("/resources/icons/x16/accept.png")));
//		add(btnCompleteSelection);
	}
}