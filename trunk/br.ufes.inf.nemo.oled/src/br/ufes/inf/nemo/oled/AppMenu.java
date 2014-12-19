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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import br.ufes.inf.nemo.oled.dialog.help.AboutDialog;
import br.ufes.inf.nemo.oled.dialog.help.LicensesDialog;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * This class manages the pulldown menu of the application.
 * 
 * @author Wei-ju Wu, John Guerson
 */
public class AppMenu implements ActionListener {

	private JMenuBar menubar;
	private List<AppCommandListener> listeners = new ArrayList<AppCommandListener>();
	private Map<String, JMenuItem> itemMap = new HashMap<String, JMenuItem>();
	private AppFrame frame;
	private JMenuItem toolboxItem;
	private JMenuItem browserItem;
	private JMenuItem bottomviewItem;
	
	/**
	 * Creates a new instance of MainMenu.
	 */
	public AppMenu(AppFrame frame) {
		this.frame = frame;
		menubar = new JMenuBar();
		//File
		createFileMenu();
		//Edit
		createEditMenu();
		//Diagram
		createDiagramMenu();
		//View
		createViewMenu();
		//Project
		createProjectMenu();
		//Verification
		createVerificationMenu();
		//Simulation/Transformation/Tool
		createTransformationMenu();
		//Help
		createHelpMenu();
	}
	
	private void createVerificationMenu(){
		JMenu projectMenu = new JMenu("Verification");
		menubar.add(projectMenu);
		
		JMenuItem parseMenuItem = createMenuItem(projectMenu,"parseocl");
		parseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));		
		
		createMenuItem(projectMenu,"verify");
		//JMenuItem verifyMenuItem = 
		//verifyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
	}
	
	@SuppressWarnings("unused")
	private void createTransformationMenu()
	{
		JMenu simulationMenu = new JMenu("Validation");
		menubar.add(simulationMenu);
		
		JMenuItem alloyItem = createMenuItem(simulationMenu, "generatealloy");
		JMenuItem antipatternItem = createMenuItem(simulationMenu, "antipattern");
		JMenuItem partWholeItem = createMenuItem(simulationMenu,"partwhole");
		
//		JMenuItem alloyItem = createMenuItem(simulationMenu, "generatealloy");
		//alloyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		
		//JMenuItem vizItem = createMenuItem(simulationMenu, "visualizer");
		//vizItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		//vizItem.setEnabled(false);
		
		JMenu transformMenu = createMenu("transformation");
		menubar.add(transformMenu);
				
		JMenuItem owlItem = createMenuItem(transformMenu, "generateowlsettings");
		//owlItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		
		JMenuItem sbvrItem = createMenuItem(transformMenu, "generatesbvr");
		//sbvrItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		
		JMenuItem textItem = createMenuItem(transformMenu, "generatetext");
		//textItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));

		//JMenuItem ecoreItem = createMenuItem(transformMenu, "generateecore");
		//
		
		//JMenuItem umlItem = createMenuItem(transformMenu, "generateuml");
		//
		
		JMenu toolMenu = createMenu("tool");
		menubar.add(toolMenu);
		
		JMenuItem assistantItem = createCheckBoxMenuItem(toolMenu, "assistant");
		toolMenu.setVisible(false);
		
		//Victor comentar
		assistantItem.setSelected(false);
		assistantItem.setEnabled(true);
		//assistantItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));		
		
	}
	
	public boolean isAssistantChecked()
	{
		return itemMap.get("ASSISTANT").isSelected();
	}
	
	/**
	 * Creates the File menu.
	 */
	private void createFileMenu() {
		JMenu fileMenu = createMenu("file");
		menubar.add(fileMenu);

		JMenuItem newItem = createMenuItem(fileMenu, "new");
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		JMenuItem openItem = createMenuItem(fileMenu, "open");
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		//fileMenu.addSeparator();
						
		//fileMenu.addSeparator();
		
		JMenuItem saveItem = createMenuItem(fileMenu, "save");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		createMenuItem(fileMenu, "saveas");
		
		JMenu importMenu = createMenu("Import");
		importMenu.setText("Import from...");
		fileMenu.add(importMenu);
		
		JMenuItem eaImportItem = createMenuItem(importMenu, "importxmi");
		eaImportItem.setToolTipText("Import from the Sparx Systems EA version 10");
		
		JMenuItem emfImportItem = createMenuItem(importMenu, "importecore");
		emfImportItem.setToolTipText("Import from the OntoUML infrastructure");
		
		createMenuItem(importMenu, "importpatterns");
		
		JMenu exportMenu = createMenu("Export");
		exportMenu.setText("Export as...");
		fileMenu.add(exportMenu);
		
		JMenuItem emfExportItem = createMenuItem(exportMenu, "exportecore");
		emfExportItem.setToolTipText("Export to the OntoUML infrastructure");
		
		JMenuItem umlItem = createMenuItem(exportMenu, "exportuml");
		umlItem.setToolTipText("Export to Eclipse MDT UML2 version 4");

		//createMenuItem(exportMenu, "exportpatterns");
		
		createMenuItem(fileMenu,"close");
		
		JMenuItem quitItem = createMenuItem(fileMenu, "quit");
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
	}
	
	private void createDiagramMenu()
	{
		JMenu diagramMenu = createMenu("Diagram");
		diagramMenu.setText("Diagram");
		menubar.add(diagramMenu);
		
		JMenuItem newdiagramItem = createMenuItem(diagramMenu,"newdiagram");
		newdiagramItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		
		JMenuItem selectAllItem = createMenuItem(diagramMenu,"selectall");
		selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
//		selectAllItem.setEnabled(false);
		
		createMenuItem(diagramMenu,"exportgfx");
		
		JMenuItem closediagramItem = createMenuItem(diagramMenu,"closediagram");
		closediagramItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
	}
	
	@SuppressWarnings("unused")
	private void createImportMenu()
	{
		JMenu importMenu = createMenu("Import");
		importMenu.setText("Import");
		menubar.add(importMenu);
		
		createMenuItem(importMenu, "importxmi");		
		createMenuItem(importMenu, "importecore");
		createMenuItem(importMenu, "importocl");
	}
	
	@SuppressWarnings("unused")
	private void createExportMenu()
	{
		JMenu exportMenu = createMenu("Export");
		exportMenu.setText("Export");
		menubar.add(exportMenu);
		
		createMenuItem(exportMenu, "exportecore");
		createMenuItem(exportMenu, "exportocl");		
	}

	/**
	 * Creates the Edit mnu.
	 */
	private void createEditMenu() {
		JMenu editMenu = createMenu("edit");
		menubar.add(editMenu);
		
		JMenuItem undoItem = createMenuItem(editMenu, "undo");
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		
		JMenuItem redoItem = createMenuItem(editMenu, "redo");
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
				
		//JMenuItem findItem = createMenuItem(editMenu, "find");
		//findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		
		//editMenu.addSeparator();
		/*
		 * createMenuItem(editMenu, "cut"); createMenuItem(editMenu, "copy");
		 * createMenuItem(editMenu, "paste");
		 */
		//createMenuItem(editMenu, "delete");

		// editMenu.addSeparator();
		// createMenuItem(editMenu, "settings");

		enableMenuItem("UNDO", true);
		enableMenuItem("REDO", true);
		// enableMenuItem("CUT", false);
		// enableMenuItem("COPY", false);
		// enableMenuItem("PASTE", false);
		//enableMenuItem("DELETE", false);
	}

	/**
	 * Creates the View menu.
	 */
	private void createViewMenu() {
		JMenu viewMenu = createMenu("view");
		menubar.add(viewMenu);
				 
		JMenuItem zoomInItem = createMenuItem(viewMenu,"zoomin");
		zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
		JMenuItem zoomOutItem = createMenuItem(viewMenu,"zoomout");
		zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		viewMenu.add(zoomInItem);
		viewMenu.add(zoomOutItem);
		
		viewMenu.addSeparator();
		
		JMenuItem showGrid = createCheckBoxMenuItem(viewMenu, "showgrid");
		showGrid.setSelected(true);
		//JMenuItem snapToGrid = createCheckBoxMenuItem(viewMenu, "snaptogrid");
		//snapToGrid.setSelected(true);
		
		toolboxItem = createCheckBoxMenuItem(viewMenu,"toolbox");
		toolboxItem.setSelected(true);
		
		browserItem = createCheckBoxMenuItem(viewMenu,"browser");
		browserItem.setSelected(true);

		bottomviewItem = createCheckBoxMenuItem(viewMenu,"bottomview");
		bottomviewItem.setSelected(false);
		
		//viewMenu.addSeparator();
		//createMenuItem(viewMenu, "redraw");
	}

	public JMenuItem getToolBoxItem()
	{
		return toolboxItem;
	}
	
	public JMenuItem getProjectBrowserItem()
	{
		return browserItem;
	}
	
	public JMenuItem getBottomViewItem()
	{
		return bottomviewItem;
	}
	
	public void createProjectMenu()
	{
		JMenu projectMenu = createMenu("project");
		menubar.add(projectMenu);
		
		JMenuItem findItem = createMenuItem(projectMenu, "find");
		findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

		createMenuItem(projectMenu, "statistics");
		//statisticItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

	}
	/**
	 * Creates the Help menu.
	 */
	private void createHelpMenu() {
		JMenu helpMenu = createMenu("help");
		menubar.add(helpMenu);
		
		JMenuItem aboutMenuItem = createMenuItem(helpMenu, "about");
		aboutMenuItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			AboutDialog.open(frame);
       		}
		});
		
		JMenuItem copyrightMenuItem = createMenuItem(helpMenu, "copyrights");
		copyrightMenuItem.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			LicensesDialog.open(frame);
       		}
		});
	}

	/**
	 * Generic helper method to construct a menu according to the resource
	 * strings.
	 * 
	 * @param name
	 *            the menu name
	 * @return the JMenu
	 */
	private JMenu createMenu(String name) {
		String prefix = "menu." + name;
		JMenu menu = new JMenu(getResourceString(prefix + ".name"));
		menu.setMnemonic(getResourceChar(prefix + ".mnemonic"));
		return menu;
	}

	/**
	 * Generic helper method to construct a menu according to the resource
	 * strings.
	 * 
	 * @param menu
	 *            the menu to create the item in
	 * @param name
	 *            the menu item name
	 * @return the JMenuItem
	 */
	private JMenuItem createMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JMenuItem menuitem = new JMenuItem(getResourceString(prefix + ".name"));
		addMenuItemInformation(menuitem, prefix);
		menu.add(menuitem);
		return menuitem;
	}

	/**
	 * Creates a radio button menu item.
	 * 
	 * @param menu
	 *            the menu to create this item under
	 * @param name
	 *            the resource name
	 * @return the menu item
	 */
	@SuppressWarnings("unused")
	private JRadioButtonMenuItem createRadioMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JRadioButtonMenuItem menuitem = new JRadioButtonMenuItem(
				getResourceString(prefix + ".name"));
		addMenuItemInformation(menuitem, prefix);
		menu.add(menuitem);
		return menuitem;
	}

	/**
	 * Creates a checkbox menu item.
	 * 
	 * @param menu
	 *            the menu to create this item under
	 * @param name
	 *            the resource name
	 * @return the menu item
	 */
	private JCheckBoxMenuItem createCheckBoxMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JCheckBoxMenuItem menuitem = new JCheckBoxMenuItem(
				getResourceString(prefix + ".name"));
		addMenuItemInformation(menuitem, prefix);
		menu.add(menuitem);
		return menuitem;
	}

	/**
	 * Adds the general menu item information to the specified menu item.
	 * 
	 * @param menuitem
	 *            the menu item
	 * @param prefix
	 *            the resource prefix
	 */
	private void addMenuItemInformation(JMenuItem menuitem, String prefix) {
		int mnemonic = getResourceChar(prefix + ".mnemonic");
		if (mnemonic > 0) {
			menuitem.setMnemonic(mnemonic);
		}
		String accel = getResourceString(prefix + ".accelerator");
		if (accel != null) {
			menuitem.setAccelerator(KeyStroke.getKeyStroke(accel));
		}
		String actionCommand = getResourceString(prefix + ".command");
		menuitem.setActionCommand(actionCommand);
		itemMap.put(actionCommand, menuitem);
		menuitem.addActionListener(this);

		// icon
		String iconType = getResourceString(prefix + ".icon");
		if (iconType != null) {
			menuitem.setIcon(IconLoader.getInstance().getIcon(iconType));
		}
	}

	/**
	 * Adds a CommandListener.
	 * 
	 * @param l
	 *            the CommandListener to add
	 */
	public void addCommandListener(AppCommandListener l) {
		listeners.add(l);
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
	 * Retrieves the menubar instance.
	 * 
	 * @return the managed menubar instance
	 */
	public JMenuBar getMenuBar() {
		return menubar;
	}

	/**
	 * Returns the specified resource as a String object.
	 * 
	 * @param property
	 *            the property name
	 * @return the property value or null if not found
	 */
	public static String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * Returns the first character of a resource property.
	 * 
	 * @param property
	 *            the property to retrieve
	 * @return the first character as an int
	 */
	private int getResourceChar(String property) {
		return ApplicationResources.getInstance().getChar(property);
	}

	/**
	 * Sets the enabled state for the menu item that corresponds to the
	 * specified command.
	 * 
	 * @param actionCommand
	 *            the action command string
	 * @param flag
	 *            the enable state
	 */
	public void enableMenuItem(String actionCommand, boolean flag) {
		itemMap.get(actionCommand).setEnabled(flag);
	}

	/**
	 * Returns the selection state of the specified menu item.
	 * 
	 * @param actionCommand
	 *            the action command string
	 * @return the selection state
	 */
	public boolean isSelected(String actionCommand) {
		return itemMap.get(actionCommand).isSelected();
	}
}
