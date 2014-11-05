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
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetVisibilityCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetVisibilityCommand.Visibility;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * @author John Guerson
 */
public class MultiSelectionPopupMenu extends JPopupMenu implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Set<AppCommandListener> commandListeners = new HashSet<AppCommandListener>();
	private DiagramEditor editor;
	private ArrayList<DiagramElement> selected = new ArrayList<DiagramElement>();	
	private JMenuItem createGenSetItem;
	private JMenuItem deleteGenSetItem;	
	private JMenuItem resetMenuItem;
	private JMenu lineStyleItem;
	@SuppressWarnings("unused")
	private JMenuItem rectMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem treeStyleVerticalMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem treeStyleHorizontalMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem directMenuItem;
	private JMenu alignMenu;
	private JMenuItem alignTop;
	private JMenuItem alignBottom;
	private JMenuItem alignLeft;
	private JMenuItem alignRight;
	private JMenuItem alignCenterVertically;
	private JMenuItem alignCenterHorizontally;
	private JMenu visibilityMenu;
	private JMenuItem showRolesItem;
	private JMenuItem showNameItem;
	private JMenuItem showSubsettingItem;
	private JMenuItem showRedefiningItem;
	private JMenuItem showMultiplicitiesItem;
	private JMenuItem showStereotypeItem;
	private JMenuItem setColorItem;
	private JMenuItem unionItem;
	private JMenuItem exclusionItem;
	private JMenuItem intersectionItem;
	//private JMenuItem pastspecializationItem;
	private JMenuItem participationItem;
	@SuppressWarnings("unused")
	private JMenuItem deleteItem;
	private JMenu genSetMenu;
	private JMenu deriveMenu;	
	
	public MultiSelectionPopupMenu()
	{			
		genSetMenu = new JMenu("Genelization Set");
		createGenSetItem = createMenuItem(this, "creategenset");
		deleteGenSetItem = createMenuItem(this, "deletegenset");
		genSetMenu.add(createGenSetItem);
		genSetMenu.add(deleteGenSetItem);
		add(genSetMenu);
		
		resetMenuItem = createMenuItem(this, "resetpoints");
		lineStyleItem = new JMenu("Line Style");		
		directMenuItem = createMenuItem(lineStyleItem, "recttodirect");
		rectMenuItem = createMenuItem(lineStyleItem, "directtorect");
		treeStyleVerticalMenuItem = createMenuItem(lineStyleItem, "treestyle.vertical");
		treeStyleHorizontalMenuItem = createMenuItem(lineStyleItem, "treestyle.horizontal");
		add(lineStyleItem);
		
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
		
		visibilityMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.visibility.name"));
		add(visibilityMenu);
			
		showRolesItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showroles");
		showRolesItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				if(editor!=null) editor.execute(new SetVisibilityCommand((DiagramNotification)editor,selected,editor.getProject(),Visibility.ENDPOINTS,showRolesItem.isSelected()));				
			}
		});
		
		showSubsettingItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showsubsetting");
		showSubsettingItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(editor!=null) editor.execute(new SetVisibilityCommand((DiagramNotification)editor,selected,editor.getProject(),Visibility.SUBSETS,showSubsettingItem.isSelected()));				
			}
		});
		
		showRedefiningItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showredefining");
		showRedefiningItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(editor!=null) editor.execute(new SetVisibilityCommand((DiagramNotification)editor,selected,editor.getProject(),Visibility.REDEFINES,showRedefiningItem.isSelected()));				
			}
		});
		
		showMultiplicitiesItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showmultiplicities");
		showMultiplicitiesItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(editor!=null) editor.execute(new SetVisibilityCommand((DiagramNotification)editor,selected,editor.getProject(),Visibility.MULTIPLICITY,showMultiplicitiesItem.isSelected()));					
			}
		});
		
		showNameItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showname");
		showNameItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(editor!=null) editor.execute(new SetVisibilityCommand((DiagramNotification)editor,selected,editor.getProject(),Visibility.NAME,showNameItem.isSelected()));					
			}
		});
			
		showStereotypeItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showstereotype");
		showStereotypeItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(editor!=null) editor.execute(new SetVisibilityCommand((DiagramNotification)editor,selected,editor.getProject(),Visibility.STEREOTYPE,showStereotypeItem.isSelected()));					
			}
		});
		
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
		
		deriveMenu = new JMenu("Derive by");
		unionItem = createMenuItem(this, "derivedunion");
		exclusionItem = createMenuItem(this, "derivedexclusion");
		intersectionItem = createMenuItem(this, "derivedintersection");
		//pastspecializationItem = createMenuItem(this, "derivedpastspecialization");
		participationItem = createMenuItem(this, "derivedparticipation");
		deriveMenu.add(unionItem);
		deriveMenu.add(exclusionItem);
		deriveMenu.add(intersectionItem);
		//deriveMenu.add(pastspecializationItem);
		deriveMenu.add(participationItem);
		add(deriveMenu);
		
		addSeparator();
		
		deleteItem = createMenuItem(this, "exclude");
		deleteItem = createMenuItem(this, "delete");	
	}
	
	public void setSelectedElements(ArrayList<DiagramElement> selected, DiagramEditor editor)
	{
		this.selected = selected;
		this.editor = editor;
		// check if we can show the item that creates/delete generalization sets
		boolean containGenset=false;
		boolean areAllRelationships=true;
		boolean areAllGeneralizations=true;
		boolean showingName=false;
		boolean showingStereo=false;
		boolean showingMultiplicity=false;
		boolean showingEndPoints=false;
		boolean showingSubsetting=false;
		boolean showingRedefining=false;
		ArrayList<Generalization> gens = new ArrayList<Generalization>();
		for(DiagramElement dElem: selected){			
			if (dElem instanceof GeneralizationElement){
				if (((GeneralizationElement)dElem).showName()) showingName=true;
				Generalization gen = ((GeneralizationElement)dElem).getGeneralization();
				if(gen!=null)gens.add(gen);
				if(gen.getGeneralizationSet()!=null && !gen.getGeneralizationSet().isEmpty()) containGenset=true;
			}			
			if(dElem instanceof ClassElement){
				areAllRelationships=false;
				areAllGeneralizations=false;
			}
			if(dElem instanceof AssociationElement){
				if (((AssociationElement)dElem).showName()) showingName=true;
				if (((AssociationElement)dElem).showMultiplicities()) showingMultiplicity=true;
				if (((AssociationElement)dElem).showOntoUmlStereotype()) showingStereo=true;
				if (((AssociationElement)dElem).showRoles()) showingEndPoints=true;
				if (((AssociationElement)dElem).showSubsetting()) showingSubsetting=true;
				if (((AssociationElement)dElem).showRedefining()) showingRedefining=true;
				areAllGeneralizations=false;
			}
		}
		
		if(gens.size()<=1) { genSetMenu.setVisible(false); }
		else { genSetMenu.setVisible(true); createGenSetItem.setVisible(true); deleteGenSetItem.setVisible(false); }
		if(gens.size()>1 && containGenset==true) { genSetMenu.setVisible(true); deleteGenSetItem.setVisible(true); }	
		
		if(!areAllRelationships) { lineStyleItem.setVisible(false); resetMenuItem.setVisible(false); }
		else { lineStyleItem.setVisible(true); resetMenuItem.setVisible(true); }
		
		if(!areAllRelationships) { alignMenu.setVisible(true); }
		else { alignMenu.setVisible(false); }
		
		if(!areAllRelationships) { setColorItem.setVisible(true); }
		else { setColorItem.setVisible(false); }
		
		if(areAllRelationships){			
			visibilityMenu.setVisible(true);
			if(!areAllGeneralizations){
				showMultiplicitiesItem.setEnabled(true);
				showMultiplicitiesItem.setSelected(showingMultiplicity);
				showRolesItem.setEnabled(true);
				showRolesItem.setSelected(showingEndPoints);
				showSubsettingItem.setEnabled(true);
				showSubsettingItem.setSelected(showingSubsetting);
				showRedefiningItem.setEnabled(true);
				showRedefiningItem.setSelected(showingRedefining);
				showNameItem.setEnabled(true);
				showNameItem.setSelected(showingName);
				showStereotypeItem.setEnabled(true);
				showStereotypeItem.setSelected(showingStereo);
			}else{
				showMultiplicitiesItem.setSelected(false);
				showMultiplicitiesItem.setEnabled(false);
				showRolesItem.setSelected(false);
				showRolesItem.setEnabled(false);
				showSubsettingItem.setSelected(false);
				showSubsettingItem.setEnabled(false);
				showRedefiningItem.setSelected(false);
				showRedefiningItem.setEnabled(false);				
				showNameItem.setSelected(showingName);
				showStereotypeItem.setSelected(false);
				showStereotypeItem.setEnabled(false);
			}
		} else { 
			visibilityMenu.setVisible(false); 
		}
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

