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
import javax.swing.KeyStroke;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetColorCommand;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * @author John Guerson
 */
public class SingleNodePopupMenu extends JPopupMenu implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Set<AppCommandListener> commandListeners = new HashSet<AppCommandListener>();
	private UmlNode node;
	final JMenuItem showAttrItem;
	final JMenuItem showOperItem;	
	final JMenuItem findInProjectItem;
	private DiagramEditor editor;
	private JMenuItem setColorItem;
	private JMenuItem copyColorItem;
	private JMenuItem pasteColorItem;
	private Color copiedColor;
	private JMenu colorMenu;
	@SuppressWarnings("unused")
	private JMenuItem specializationItem;
	private JMenuItem pastspecializationItem;
	private JMenuItem exclusionItem;
	private ClassStereotypeChangeMenu changeMenu;
	private JMenuItem relatedItem;
	
	public SingleNodePopupMenu()
	{
		JMenuItem propertyItem = createMenuItem(this, "editproperties");
		propertyItem.setAccelerator(KeyStroke.getKeyStroke("F9"));		
		
		addSeparator();
		
		findInProjectItem = new JMenuItem("Find in Project Browser");
		add(findInProjectItem);
		findInProjectItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (node instanceof ClassElement) {						
					Classifier c = ((ClassElement)node).getClassifier();
					editor.getDiagramManager().getFrame().getBrowserManager().getProjectBrowser().getTree().checkModelElement(c);					
				}				
			}
		});
		
		addSeparator();
		
		createBringRelatedElementsMenu();
		createPatternMenu();
		
		JMenu deriveMenu = new JMenu("Derive by");
		specializationItem = createMenuItem(deriveMenu, "derivedspecialization");	
		specializationItem = createMenuItem(deriveMenu, "derivedpastspecialization");
		specializationItem = createMenuItem(deriveMenu, "derivedexclusion");
		add(deriveMenu);
		
		addSeparator();
		createColorMenu();	
		
		changeMenu = new ClassStereotypeChangeMenu();
		add(changeMenu);
		
		JMenu drawOrderMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.draworder.name"));
		add(drawOrderMenu);
		
		createMenuItem(drawOrderMenu, "draworder.tofront");
		createMenuItem(drawOrderMenu, "draworder.toback");
		
		JMenu visibilityMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.visibility.name"));
		add(visibilityMenu);
		
		showOperItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showoperation");
		showOperItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (node instanceof ClassElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((ClassElement)node).setShowOperations(showOperItem.isSelected());
					list.add(node);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}				
			}
		});
		
		showAttrItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showattribute");
		showAttrItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (node instanceof ClassElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((ClassElement)node).setShowAttributes(showAttrItem.isSelected());
					list.add(node);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}				
			}
		});
		
		// FIXME: Trying to hide the stereotype displays a bug
//		final JMenuItem showStereoItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showstereotype");
//		showStereoItem.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {				
//				if (node instanceof ClassElement) {	
//					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
//					((ClassElement)node).setShowStereotypes(showStereoItem.isSelected());
//					list.add(node);
//					editor.notifyChange(list, ChangeType.ELEMENTS_CHANGED, NotificationType.DO);
//				}				
//			}
//		});
		
		addSeparator();
		
		createMenuItem(this, "exclude");
		
		createMenuItem(this, "delete");	
		
//		addSeparator();				
//		changeMenu = new ClassStereotypeChangeMenu(editor.getDiagramManager());
//		add(changeMenu);
	}
	
	public void createBringRelatedElementsMenu()
	{	
		relatedItem = new JMenuItem("Add All Related Elements");
		relatedItem.addActionListener(new ActionListener() {				
			@Override
        	public void actionPerformed(ActionEvent e) { 
				editor.getDiagramManager().addAllRelatedElements(node, editor);
			}
		});
		add(relatedItem);
	}
	
	public void createColorMenu()
	{
		colorMenu = new JMenu("Color");
		add(colorMenu);
		
		setColorItem = new JMenuItem("Setup");
		setColorItem.addActionListener(new ActionListener() {				
			private Color color;        	
			@Override
        	public void actionPerformed(ActionEvent e) { 
				ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
				list.add(node);
				if(color==null) color = JColorChooser.showDialog(editor.getDiagramManager().getFrame(), "Select a Background Color", Color.LIGHT_GRAY);
				else color = JColorChooser.showDialog(editor.getDiagramManager().getFrame(), "Select a Background Color", color);
        		if (color != null){
        			editor.execute(new SetColorCommand((DiagramNotification)editor,list,editor.getProject(),color));        			
        		}        		        		
        	}
        });
		colorMenu.add(setColorItem);
		
		copyColorItem = new JMenuItem("Copy");		
		copyColorItem.addActionListener(new ActionListener() {				
			@Override
        	public void actionPerformed(ActionEvent e) {
				copiedColor = ((ClassElement)node).getBackgroundColor();				     		        		
        	}
        });
		colorMenu.add(copyColorItem);
				
		pasteColorItem = new JMenuItem("Paste");
		pasteColorItem.addActionListener(new ActionListener() {				
			@Override
        	public void actionPerformed(ActionEvent e) { 
				ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
				list.add(node);				
        		if (copiedColor != null){
        			editor.execute(new SetColorCommand((DiagramNotification)editor,list,editor.getProject(),copiedColor));        			
        		}        		        		
        	}
        });
		colorMenu.add(pasteColorItem);
	}
	
	public void createPatternMenu()
	{
//		JMenu applyPatternMenu = new JMenu("Apply Pattern");
//		
//		createMenuItem(applyPatternMenu,"pattern.generalizationspecialization");
//		createMenuItem(applyPatternMenu,"pattern.partitionpattern");
//
//		createMenuItem(applyPatternMenu,"pattern.addsupertype");
//		createMenuItem(applyPatternMenu,"pattern.addsubtype");
//		
//		add(applyPatternMenu);
	}
	
	public void setNode (UmlNode node, DiagramEditor editor)
	{
		this.node = node;
		this.editor = editor;
		if (node instanceof ClassElement){
			showAttrItem.setSelected(((ClassElement)node).showAttributes());
			showOperItem.setSelected(((ClassElement)node).showOperations());
		}
		if(changeMenu!=null) {
			changeMenu.setElement(((ClassElement)node).getClassifier());
			changeMenu.setDiagramManager(editor.getDiagramManager());
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
