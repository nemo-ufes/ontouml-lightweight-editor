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
package br.ufes.inf.nemo.oled;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.util.AppCommandListener;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * This class manages and creates the application's main toolbar.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class MainToolbar implements ActionListener {

	JToolBar toolbar = new JToolBar();
	private List<AppCommandListener> listeners =
		new ArrayList<AppCommandListener>();
	private Map<String, JButton> buttonMap = new HashMap<String, JButton>();
	private JButton save;

	/**
	 * Constructor.
	 */
	public MainToolbar() {
		
		createButton("new");
		//createButton("newdiagram");
		createButton("open");
		save = createButton("save");
		toolbar.addSeparator();
		createButton("cut");
		createButton("copy");
		createButton("paste");
		toolbar.addSeparator();		
		createButton("erase");
		createButton("delete");
		toolbar.addSeparator();
		createButton("undo");
		createButton("redo");
		toolbar.addSeparator();
		createButton("warning");
		createButton("error");
		toolbar.addSeparator();
		createButton("verify");
		toolbar.addSeparator();		
		createButton("antipattern");
		toolbar.addSeparator();		
		createButton("generatealloy");
		createButton("generateowlsettings");
		createButton("generatesbvr");
		createButton("generatetext");
		
		enableButton("UNDO", false);
		enableButton("REDO", false);
		enableButton("CUT", false);
		enableButton("COPY", false);
		enableButton("PASTE", false);
		enableButton("DELETE", true);
	
		toolbar.setFloatable(false);
		toolbar.setMargin(new Insets(5,5,5,5));
	}

	/**
	 * Returns the toolbar.
	 * @return the toolbar
	 */
	public JToolBar getToolbar() { return toolbar; }

	/**
	 * Adds a CommandListener.
	 * @param l the CommandListener to add
	 */
	public void addCommandListener(AppCommandListener l) {
		listeners.add(l);
	}

	public void enableSaveButton(boolean value)
	{
		save.setEnabled(value);
	}
	
	/**
	 * Creates the button with the specified name.
	 * @param name the resource name
	 * @return the button
	 */
	private JButton createButton(String name) {
		String prefix = "maintoolbar." + name;
		JButton button = new JButton(
				IconLoader.getInstance().getIcon(getResourceString(prefix + ".icon")));
		button.setMargin(new Insets(1, 1, 1, 1));
		String command = getResourceString(prefix + ".command");
		button.setActionCommand(command);
		button.addActionListener(this);
		button.setBorderPainted(false);
	    button.setFocusable(false);
		buttonMap.put(command, button);
		toolbar.add(button);
		button.setToolTipText(getResourceString(prefix + ".tooltip"));
		return button;
	}

	/**
	 * Returns the specified resource as a String object.
	 * @param property the property name
	 * @return the property value or null if not found
	 */
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		for (AppCommandListener l : listeners) {
			l.handleCommand(e.getActionCommand());
		}
	}

	/**
	 * Enables or disables the specified button associated with a command.
	 * @param command the action command string
	 * @param flag true if enabled, false to disable
	 */
	public void enableButton(String command, boolean flag) {
		buttonMap.get(command).setEnabled(flag);
	}
}
