package br.ufes.inf.nemo.ontouml.editor.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import br.ufes.inf.nemo.ontouml.editor.util.Resources;

@SuppressWarnings("serial")
public class MainMenu extends JMenuBar implements ActionListener {

	private List<CommandListener> listeners = new ArrayList<CommandListener>();
	private Map<String, JMenuItem> itemMap = new HashMap<String, JMenuItem>();
	
	public MainMenu() {
		super();
		createFileMenu();
//		createEditMenu();
//		createViewMenu();
//		createHelpMenu();
	}
	
	/**
	 * Creates the File menu.
	 */
	private void createFileMenu() {
		JMenu fileMenu = createMenu("file");
		add(fileMenu);

		createMenuItem(fileMenu, "new");
		createMenuItem(fileMenu, "open");
		createMenuItem(fileMenu, "save");
		createMenuItem(fileMenu, "saveas");
		fileMenu.addSeparator();
		createMenuItem(fileMenu, "quit");
	}
	
	private JMenu createMenu(String name) {
		String prefix = "mainmenu." + name;
		JMenu menu = new JMenu(Resources.getString(prefix + ".name"));
		menu.setMnemonic(Resources.getChar(prefix + ".mnemonic"));
		return menu;
	}
	
	private JMenuItem createMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JMenuItem menuitem = new JMenuItem(Resources.getString(prefix + ".name"));
		addMenuItemInformation(menuitem, prefix);
		menu.add(menuitem);
		return menuitem;
	}
	
	@SuppressWarnings("unused")
	private JRadioButtonMenuItem createRadioMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JRadioButtonMenuItem menuitem = new JRadioButtonMenuItem(Resources.getString(prefix + ".name"));
		addMenuItemInformation(menuitem, prefix);
		menu.add(menuitem);
		return menuitem;
	}
	
	@SuppressWarnings("unused")
	private JCheckBoxMenuItem createCheckBoxMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JCheckBoxMenuItem menuitem = new JCheckBoxMenuItem(Resources.getString(prefix + ".name"));
		addMenuItemInformation(menuitem, prefix);
		menu.add(menuitem);
		return menuitem;
	}
	
	private void addMenuItemInformation(JMenuItem menuitem, String prefix) {
		
		int mnemonic = Resources.getChar(prefix + ".mnemonic");
		if (mnemonic > 0) {
			menuitem.setMnemonic(mnemonic);
		}
		
		String accel = Resources.getString(prefix + ".accelerator");
		if (accel != null) {
			menuitem.setAccelerator(KeyStroke.getKeyStroke(accel));
		}
		
		String actionCommand = Resources.getString(prefix + ".command");
		menuitem.setActionCommand(actionCommand);
		itemMap.put(actionCommand, menuitem);
		menuitem.addActionListener(this);

		// icon
		menuitem.setIcon(Resources.getIcon(prefix + ".icon"));
	}

	public void enableMenuItem(String actionCommand, boolean flag) {
		itemMap.get(actionCommand).setEnabled(flag);
	}

	public boolean isSelected(String actionCommand) {
		return itemMap.get(actionCommand).isSelected();
	}
	
	public void addCommandListener(CommandListener listener) {
		listeners.add(listener);
	}
	
	public void actionPerformed(ActionEvent evt) {
		for (CommandListener listener : listeners) {
			listener.handleCommand(evt.getActionCommand());
		}
	}
}
