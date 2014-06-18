package br.ufes.inf.nemo.oled.popupmenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import RefOntoUML.Generalization;
import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AlignElementsCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AlignElementsCommand.Alignment;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetColorCommand;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

public class MultiSelectionPopupMenu extends JPopupMenu implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Set<AppCommandListener> commandListeners = new HashSet<AppCommandListener>();
	private ArrayList<DiagramElement> selected = new ArrayList<DiagramElement>();
	private JMenuItem createGenSetItem;
	private JMenuItem deleteGenSetItem;
	@SuppressWarnings("unused")
	private JMenuItem unionItem;
	@SuppressWarnings("unused")
	private JMenuItem exclusionItem;
	@SuppressWarnings("unused")
	private JMenuItem intersectionItem;
	@SuppressWarnings("unused")
	private JMenuItem deleteItem;
	@SuppressWarnings("unused")
	private JMenuItem rectMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem treeStyleVerticalMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem treeStyleHorizontalMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem directMenuItem;
	private JMenuItem resetMenuItem;
	private JMenu lineStyleItem;
	private JMenu alignMenu;
	private JMenuItem alignTop;
	private JMenuItem alignBottom;
	private JMenuItem alignLeft;
	private JMenuItem alignRight;
	private JMenuItem alignCenterVertically;
	private JMenuItem alignCenterHorizontally;
	private DiagramEditor editor;
	private JMenuItem setColorItem;
	
	public MultiSelectionPopupMenu()
	{			
		createGenSetItem = createMenuItem(this, "creategenset");
		deleteGenSetItem = createMenuItem(this, "deletegenset");
		
		resetMenuItem = createMenuItem(this, "resetpoints");
		lineStyleItem = new JMenu("Line Style");
		add(lineStyleItem);
		directMenuItem = createMenuItem(lineStyleItem, "recttodirect");
		rectMenuItem = createMenuItem(lineStyleItem, "directtorect");
		treeStyleVerticalMenuItem = createMenuItem(lineStyleItem, "treestyle.vertical");
		treeStyleHorizontalMenuItem = createMenuItem(lineStyleItem, "treestyle.horizontal");
		
		alignMenu = new JMenu("Align");
		alignTop = new JMenuItem("Align Top");
		alignTop.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null) editor.execute(new AlignElementsCommand((DiagramNotification)editor,selected,editor.getProject(),Alignment.TOP));
        	}
        });
		alignBottom = new JMenuItem("Align Bottom");
		alignBottom.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null) editor.execute(new AlignElementsCommand((DiagramNotification)editor,selected,editor.getProject(),Alignment.BOTTOM));
        	}
        });
		alignLeft = new JMenuItem("Align Left");
		alignLeft.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null) editor.execute(new AlignElementsCommand((DiagramNotification)editor,selected,editor.getProject(),Alignment.LEFT));
        	}
        });
		alignRight = new JMenuItem("Align Right");
		alignRight.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null) editor.execute(new AlignElementsCommand((DiagramNotification)editor,selected,editor.getProject(),Alignment.RIGHT));
        	}
        });
		alignCenterHorizontally = new JMenuItem("Align Center Horizontally");
		alignCenterHorizontally.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null) editor.execute(new AlignElementsCommand((DiagramNotification)editor,selected,editor.getProject(),Alignment.CENTER_HORIZONTAL));
        	}
        });
		alignCenterVertically = new JMenuItem("Align Center Vertically");
		alignCenterVertically.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null) editor.execute(new AlignElementsCommand((DiagramNotification)editor,selected,editor.getProject(),Alignment.CENTER_VERTICAL));
        	}
        });
		alignMenu.add(alignTop);
		alignMenu.add(alignBottom);
		alignMenu.add(alignLeft);
		alignMenu.add(alignRight);
		alignMenu.add(alignCenterHorizontally);
		alignMenu.add(alignCenterVertically);
		add(alignMenu);
		
		setColorItem = new JMenuItem("Set Color");
		setColorItem.addActionListener(new ActionListener() {				
			private Color color;        	
			@Override
        	public void actionPerformed(ActionEvent e) { 
				if(color==null) color = JColorChooser.showDialog(editor.getDiagramManager().getFrame(), "Select a Background Color", Color.LIGHT_GRAY);
				else color = JColorChooser.showDialog(editor.getDiagramManager().getFrame(), "Select a Background Color", color);
        		if (color != null){
        			editor.execute(new SetColorCommand((DiagramNotification)editor,selected,editor.getProject(),color));        			
        		}        		        		
        	}
        });
		add(setColorItem);
		
		unionItem = createMenuItem(this, "derivedunion");
		exclusionItem = createMenuItem(this, "derivedexclusion");
		intersectionItem = createMenuItem(this, "derivedintersection");
		//specializationItem = createMenuItem(this, "derivedspecialization");
		
		deleteItem = createMenuItem(this, "delete");	
	}
	
	public void setSelectedElements(ArrayList<DiagramElement> selected, DiagramEditor editor)
	{
		this.selected = selected;
		this.editor = editor;
		// check if we can show the item that creates/delete generalization sets
		boolean containGenset=false;
		boolean areAllAssociations=true;
		ArrayList<Generalization> gens = new ArrayList<Generalization>();
		for(DiagramElement dElem: selected){			
			if (dElem instanceof GeneralizationElement){
				Generalization gen = ((GeneralizationElement)dElem).getGeneralization();
				if(gen!=null)gens.add(gen);
				if(gen.getGeneralizationSet()!=null && !gen.getGeneralizationSet().isEmpty()) containGenset=true;
			}			
			if(dElem instanceof ClassElement){
				areAllAssociations=false;
			}
		}
		
		if(gens.size()<=1) { createGenSetItem.setVisible(false); deleteGenSetItem.setVisible(false); }
		else { createGenSetItem.setVisible(true); deleteGenSetItem.setVisible(false); }
		if(gens.size()>1 && containGenset==true) { deleteGenSetItem.setVisible(true); }	
		
		if(!areAllAssociations) { lineStyleItem.setVisible(false); resetMenuItem.setVisible(false); }
		else { lineStyleItem.setVisible(true); resetMenuItem.setVisible(true); }
		
		if(!areAllAssociations) { alignMenu.setVisible(true); }
		else { alignMenu.setVisible(false); }
		
		if(!areAllAssociations) { setColorItem.setVisible(true); }
		else { setColorItem.setVisible(false); }
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

