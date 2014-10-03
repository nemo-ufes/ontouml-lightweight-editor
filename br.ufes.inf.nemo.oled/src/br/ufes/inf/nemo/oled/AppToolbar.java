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
package br.ufes.inf.nemo.oled;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.ui.diagram.DiagramToolbar;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * This class manages and creates the application's main toolbar.
 *
 * @author Wei-ju Wu, John Guerson
 */
public class AppToolbar implements ActionListener {

	@SuppressWarnings("unused")
	private AppFrame frame;
	private JToolBar toolbar = new JToolBar();
	private List<AppCommandListener> listeners =
		new ArrayList<AppCommandListener>();
	private Map<String, JButton> jbuttonMap = new HashMap<String, JButton>();
	
	private JButton save;	
	private JToggleButton btnToolBox;
	private JToggleButton btnBrowser;
	private JToggleButton btnBottomView;
	
	public JToggleButton getToolBoxButton() { return btnToolBox; }
	public JToggleButton getProjectBrowserButton() { return btnBrowser; }
	public JToggleButton getBottomViewButton() { return btnBottomView; }
	
	/**
	 * Constructor.
	 */
	public AppToolbar(final AppFrame frame) {
		this.frame = frame;
		
		createButton("new");
		//createButton("newdiagram");
		createButton("open");
		save = createButton("save");		
		//createButton("cut");
		//createButton("copy");
		//createButton("paste");
		createButton("undo");
		createButton("redo");
		createButton("find");		
		createButton("verify");
		
		btnToolBox = new JToggleButton("");
		btnToolBox.setSelected(frame.getMainMenu().isSelected("TOOLBOX"));
		btnToolBox.setToolTipText("Show/hide Toolbox Sidebar");
		btnToolBox.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.getMainMenu().getToolBoxItem().setSelected(btnToolBox.isSelected());
        		frame.showToolBox();
        	}
        });
		btnToolBox.setFocusable(false);
		btnToolBox.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/hammer_screwdriver.png")));
		toolbar.add(btnToolBox);
		
		btnBrowser = new JToggleButton("");
		btnBrowser.setSelected(frame.getMainMenu().isSelected("BROWSER"));
		btnBrowser.setToolTipText("Show/hide Project Browser");
		btnBrowser.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.getMainMenu().getProjectBrowserItem().setSelected(btnBrowser.isSelected());
        		frame.showProjectBrowser();
        	}
        });
		btnBrowser.setFocusable(false);
		btnBrowser.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/door_in.png")));
		toolbar.add(btnBrowser);
		
		btnBottomView = new JToggleButton("");
		btnBottomView.setSelected(frame.getMainMenu().isSelected("BOTTOMVIEW"));
		btnBottomView.setToolTipText("Show/hide Information Footer");
		btnBottomView.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.getMainMenu().getBottomViewItem().setSelected(btnBottomView.isSelected());
        		frame.showBottomView();
        	}
        });
		btnBottomView.setFocusable(false);
		btnBottomView.setIcon(new ImageIcon(AppToolbar.class.getResource("/resources/icons/x16/layout_select_footer.png")));
		toolbar.add(btnBottomView);
		
		createButton("statistics");
		
		toolbar.addSeparator();
		JButton btnText = createButton("generatetext");
		btnText.setToolTipText("" +
			"<html>Glossary of Terms: Transform your ontology into a Textual Description in Portuguese-BR.<br><br>"+
			"TIP: You may customize which packages or elements do you want to transform <br>" +
			"just deselecting/selecting them on the project browser.<br><br>" +
			"</html>");
		JButton btnAlloy = createButton("generatealloy");
		btnAlloy.setToolTipText("" +
			"<html>Simulation & Checking: Validate your ontology using Alloy and its Analyzer. <br><br>"+
			"TIP: You may customize which packages or elements do you want to transform <br>" +
			"just deselecting/selecting them on the project browser.<br><br>" +
			"</html>");		
		JButton btnOWL = createButton("generateowlsettings");
		btnOWL.setToolTipText("" +
			"<html>Semantic Web: Publish and Reason over your ontology using OWL/SWRL.<br><br>"+
			"TIP: You may customize which packages or elements  do you want to transform <br>" +
			"just deselecting/selecting them on the project browser.<br><br>" +
			"</html>");
		JButton btnSBVR = createButton("generatesbvr");
		btnSBVR.setToolTipText("" +
			"<html>Business Vocabulary: Transform your ontology into a SBVR Document in HTML.<br><br>"+
			"TIP: You may customize which packages or elements  do you want to transform <br>" +
			"just deselecting/selecting them on the project browser.<br><br>" +
			"</html>");
		JButton btnAntiPattern = createButton("antipattern");
		btnAntiPattern.setToolTipText("" +
			"<html>Anti-Patterns: Check and Fix your ontology detecting Anti-Patterns occurrences, if any. <br><br>"+
			"TIP: You may customize which packages or elements do you want to consider <br>" +
			"just deselecting/selecting them on the project browser.<br><br>" +
			"</html>");	
		JButton btnMeronymic = createButton("partwhole");
		btnMeronymic.setToolTipText("" +
			"<html>Part-Whole Relations: Evaluate your ontology regarding the transitivity of meronymic relations.<br><br>"+
			"TIP: You may customize which packages or elements do you want to consider <br>" +
			"just deselecting/selecting them on the project browser.<br><br>" +
			"</html>");	
		enableButton("UNDO", true);
		enableButton("REDO", true);
		//enableButton("CUT", false);
		//enableButton("COPY", false);
		//enableButton("PASTE", false);
		
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
		jbuttonMap.put(command, button);
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
		jbuttonMap.get(command).setEnabled(flag);
	}
}
