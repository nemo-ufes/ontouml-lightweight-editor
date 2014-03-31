package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import RefOntoUML.Generalization;
import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

public class MultiSelectionPopupMenu extends JPopupMenu implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Set<AppCommandListener> commandListeners = new HashSet<AppCommandListener>();
	@SuppressWarnings("unused")
	private ArrayList<DiagramElement> selected = new ArrayList<DiagramElement>();
	private JMenuItem createGenSetItem;
	private JMenuItem deleteGenSetItem;
	@SuppressWarnings("unused")
	private JMenuItem unionItem;
	@SuppressWarnings("unused")
	private JMenuItem exclusionItem;
	@SuppressWarnings("unused")
	private JMenuItem deleteItem;
	
	public MultiSelectionPopupMenu()
	{			
		createGenSetItem = createMenuItem(this, "creategenset");
		deleteGenSetItem = createMenuItem(this, "deletegenset");
		
		unionItem = createMenuItem(this, "derivedunion");
		exclusionItem = createMenuItem(this, "derivedexclusion");
		
		deleteItem = createMenuItem(this, "delete");	
	}
	
	public void setSelectedElements(ArrayList<DiagramElement> selected)
	{
		this.selected = selected;

		// check if we can show the item that creates/delete generalization sets
		boolean containGenset=false;
		ArrayList<Generalization> gens = new ArrayList<Generalization>();
		for(DiagramElement dElem: selected){
			if (dElem instanceof GeneralizationElement){
				Generalization gen = ((GeneralizationElement)dElem).getGeneralization();
				if(gen!=null)gens.add(gen);
				if(gen.getGeneralizationSet()!=null && !gen.getGeneralizationSet().isEmpty()) containGenset=true;
			}
		}
		if(gens.size()<=1) { createGenSetItem.setVisible(false); deleteGenSetItem.setVisible(false); }
		else { createGenSetItem.setVisible(true); deleteGenSetItem.setVisible(false); }
		if(gens.size()>1 && containGenset==true) { deleteGenSetItem.setVisible(true); }		
	}
	
	/**
	 * Adds the specified AppCommandListener.
	 * 
	 * @param l
	 *            the AppCommandListener to add
	 */
	public void addAppCommandListener(AppCommandListener l) {
		commandListeners.add(l);
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
	public JMenuItem createMenuItem(JComponent menu, String name) {
		String prefix = "menuitem." + name;
		JMenuItem menuitem = new JMenuItem(getResourceString(prefix + ".name"));

		// Command
		String actionCommand = getResourceString(prefix + ".command");
		menuitem.setActionCommand(actionCommand);
		menuitem.addActionListener(this);

		// icon
		String iconType = getResourceString(prefix + ".icon");
		if (iconType != null) {
			menuitem.setIcon(IconLoader.getInstance().getIcon(iconType));
		}
		menu.add(menuitem);
		return menuitem;
	}
	
	/**
	 * Returns the specified resource as a String object.
	 * 
	 * @param property
	 *            the property name
	 * @return the property value or null if not found
	 */
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		for (AppCommandListener l : commandListeners) {
			l.handleCommand(e.getActionCommand());
		}
	}
	
	/**
	 * Helper method to construct a check box menu item according to resource
	 * strings.
	 * 
	 * @param menu
	 *            the parent menu
	 * @param name
	 *            the menuitem name
	 * @return the menu item
	 */
	@SuppressWarnings("unused")
	private JCheckBoxMenuItem createCheckBoxMenuItem(JComponent menu,
			String name) {
		String prefix = "menuitem." + name;
		JCheckBoxMenuItem menuitem = new JCheckBoxMenuItem(
				getResourceString(prefix + ".name"));

		// Command
		String actionCommand = getResourceString(prefix + ".command");
		menuitem.setActionCommand(actionCommand);
		menuitem.addActionListener(this);
		menu.add(menuitem);
		return menuitem;
	}
}
