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
package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.oled.DiagramManager;

/**
 * @author John Guerson
 */
public class TabPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -7280793052746143720L;
	@SuppressWarnings("unused")
	private JTabbedPane pane;
	@SuppressWarnings("unused")
	private Component comp;
	
	public TabPopupMenu(final JTabbedPane pane, final Component comp)
	{
		this.pane = pane;
		this.comp = comp;
		
		JMenuItem close = new JMenuItem("Close");
		JMenuItem closeOthers = new JMenuItem("Close Others");
		JMenuItem closeAll = new JMenuItem("Close All");
		
		add(close);
		add(closeOthers);
		add(closeAll);
		
		close.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {				
				DiagramManager.closeTab(pane.indexOfComponent(comp),pane);
			}
		});
		closeOthers.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {				
				DiagramManager.closeOthers(pane,comp);
			}
		});
		closeAll.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {				
				DiagramManager.closeAll(pane);
			}
		});
	}
}
