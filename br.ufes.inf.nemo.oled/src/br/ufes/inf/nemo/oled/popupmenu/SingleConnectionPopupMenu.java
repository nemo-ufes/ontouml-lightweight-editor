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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import RefOntoUML.Association;
import RefOntoUML.Generalization;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.dialog.properties.FeatureListDialog;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetVisibilityCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetVisibilityCommand.Visibility;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDesign;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

/**
 * @author John Guerson
 */
public class SingleConnectionPopupMenu extends JPopupMenu implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Set<AppCommandListener> commandListeners = new HashSet<AppCommandListener>();
	private DiagramEditor editor;
	private Connection con;
	private JMenuItem showRolesItem;
	private JMenuItem showSubsettingItem;
	private JMenuItem showRedefiningItem;
	private JMenuItem showNameItem;
	private JMenuItem showMultiplicitiesItem;
	private JMenuItem showStereotypeItem;
	private JMenuItem rectMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem treeStyleVerticalMenuItem;
	@SuppressWarnings("unused")
	private JMenuItem treeStyleHorizontalMenuItem;
	private JMenuItem readToSourceItem;
	private JMenuItem readToDestinationItem;
	private JMenuItem readNoIndicatorItem;
	private JMenu visibilityMenu;
	private JMenu readingDirectionMenu;
	private boolean isSource;
	private Property property;
	private JMenu multiplicityMenu;
	private JRadioButtonMenuItem zerooneItem;
	private JRadioButtonMenuItem zeromanyItem;
	private JRadioButtonMenuItem oneItem;
	private JRadioButtonMenuItem onemanyItem;
	private JRadioButtonMenuItem twomanyItem;
	private JRadioButtonMenuItem twoItem;
	private JRadioButtonMenuItem otherItem;	
	private JMenuItem endNameItem;
	private JMenu metaPropertiesMenu;
	private JCheckBoxMenuItem essential;
	private JCheckBoxMenuItem inseparable;
	private JCheckBoxMenuItem immutablewhole;
	private JCheckBoxMenuItem immutablepart;
	private JCheckBoxMenuItem shareable;
	private JMenuItem findInProjectItem;
	private JMenu lineStyleItem;
	private JMenuItem invertEndPointsItem;
	private JMenu invertMenu;
	private JMenuItem subsettingItem;
	private JMenuItem redefinesItem;
	private RelationStereotypeChangeMenu changeMenu;
	private JMenuItem invertEndMultiplicitiesItem;
	private JMenuItem invertEndNamesItem;
	private JMenuItem invertEndTypesItem;
	@SuppressWarnings("unused")
	private JMenuItem specializationItem;
	private JMenuItem pastSpecializationItem;
	private JMenuItem exclusionItem;
	
	public SingleConnectionPopupMenu()
	{   
		createPropertyMenu();		
		addSeparator();		
		createFindInProjectMenu();		
		addSeparator();		
		createDeriveByMenu();		
		addSeparator();		
		createEndPointMenu();
		createSubsettingMenu();
		createRedefiningMenu();
		addSeparator();
		createResetPointsMenu();
		createLineStyleMenu();	
		createChangeMenu();
		createInvertMenu();		
		createMultiplicityMenu();
		createMetaPropertyMenu();
		createVisibilityMenu();		
		createReadingDirectionMenu();		
		addSeparator();		
		createMenuItem(this, "exclude");		
		createMenuItem(this, "delete");
	}
	
	public JMenu createDeriveByMenu()
	{
		JMenu deriveMenu = new JMenu("Derive by");
		specializationItem = createMenuItem(deriveMenu, "derivedspecialization");	
		exclusionItem = createMenuItem(deriveMenu, "derivedexclusion");
		pastSpecializationItem = createMenuItem(deriveMenu, "derivedpastspecialization");	
		add(deriveMenu);
		return deriveMenu;
	}
	
	
	
	public JMenuItem createPropertyMenu()
	{
		JMenuItem propertyItem = createMenuItem(this, "editproperties");
		propertyItem.setAccelerator(KeyStroke.getKeyStroke("F9"));
		return propertyItem;
	}
	
	public JMenuItem createResetPointsMenu()
	{
		return createMenuItem(this, "resetpoints");		
	}
	
	public JMenu createLineStyleMenu()
	{		
		lineStyleItem = new JMenu("Line Style");
		add(lineStyleItem);
		createMenuItem(lineStyleItem, "recttodirect");
		rectMenuItem = createMenuItem(lineStyleItem, "directtorect");
		treeStyleVerticalMenuItem = createMenuItem(lineStyleItem, "treestyle.vertical");
		treeStyleHorizontalMenuItem = createMenuItem(lineStyleItem, "treestyle.horizontal");
		return lineStyleItem;
	}
	
	public JMenu createChangeMenu()
	{
		changeMenu = new RelationStereotypeChangeMenu();
		add(changeMenu);
		return changeMenu;
	}
	
	public JMenu createReadingDirectionMenu()
	{
		readingDirectionMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.readingdirection.name"));
		add(readingDirectionMenu);
		
		ButtonGroup group = new ButtonGroup();
		
		readToSourceItem = createRadioMenuItem(readingDirectionMenu, "readingdirection.source");
		group.add(readToSourceItem);
		readToSourceItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((AssociationElement)con).setReadingDesign(ReadingDesign.SOURCE);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}
			}
		});

		readToDestinationItem = createRadioMenuItem(readingDirectionMenu, "readingdirection.destination");
		group.add(readToDestinationItem);
		readToDestinationItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((AssociationElement)con).setReadingDesign(ReadingDesign.DESTINATION);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}							
			}
		});
		
		readNoIndicatorItem = createRadioMenuItem(readingDirectionMenu, "readingdirection.none");
		readNoIndicatorItem.setSelected(true);
		group.add(readNoIndicatorItem);
		readNoIndicatorItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if (con instanceof AssociationElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();					
					((AssociationElement)con).setReadingDesign(ReadingDesign.UNDEFINED);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}														
			}
		});		
		
		return readingDirectionMenu;
	}
	
	public JMenu createVisibilityMenu()
	{
		visibilityMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.visibility.name"));
		add(visibilityMenu);
			
		showRolesItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showroles");
		showRolesItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					list.add(con);
					if(editor!=null) {
						editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.ENDPOINTS,showRolesItem.isSelected()));					
					}					
				}
			}
		});
		
		showSubsettingItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showsubsetting");
		showSubsettingItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					list.add(con);
					if(editor!=null) {
						editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.SUBSETS,showSubsettingItem.isSelected()));					
					}					
				}
			}
		});
		
		showRedefiningItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showredefining");
		showRedefiningItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					list.add(con);
					if(editor!=null) {
						editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.REDEFINES,showRedefiningItem.isSelected()));					
					}					
				}
			}
		});
		
		showMultiplicitiesItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showmultiplicities");
		showMultiplicitiesItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if (con instanceof AssociationElement) {
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					list.add(con);
					if(editor!=null) {
						editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.MULTIPLICITY,showMultiplicitiesItem.isSelected()));					
					}					
				}								
			}
		});
		
		showNameItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showname");
		showNameItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
				list.add(con);
				if(editor!=null) {
					editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.NAME,showNameItem.isSelected()));					
				}
			}
		});
			
		showStereotypeItem = createCheckBoxMenuItem(visibilityMenu, "visibility.showstereotype");
		showStereotypeItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {					
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					list.add(con);
					if(editor!=null) {
						editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.STEREOTYPE,showStereotypeItem.isSelected()));					
					}
				}				
			}
		});
		return visibilityMenu;
	}
	
	public JMenuItem createFindInProjectMenu()
	{
		findInProjectItem = new JMenuItem("Find in Project Browser");
		add(findInProjectItem);
		findInProjectItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {						
					Relationship c = ((AssociationElement)con).getRelationship();
					editor.getDiagramManager().getFrame().getBrowserManager().getProjectBrowser().getTree().checkModelElement(c);					
				}
				if (con instanceof GeneralizationElement) {						
					Relationship c = ((GeneralizationElement)con).getRelationship();
					editor.getDiagramManager().getFrame().getBrowserManager().getProjectBrowser().getTree().checkModelElement(c);					
				}
			}
		});
		return findInProjectItem;
	}
	
	public JMenuItem createSubsettingMenu()
	{
		subsettingItem = new JMenuItem("Subsets");
		add(subsettingItem);
		subsettingItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null){
        			FeatureListDialog.open(editor.getDiagramManager().getFrame(),null, "Subsetted", property, 
        			ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser());
        			SwingUtilities.invokeLater(new Runnable() {						
						@Override
						public void run() {
							ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
							list.add(con);					
							editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.SUBSETS,true));
						}
        			});
        		}
        	}
        });
		return subsettingItem;
	}
	
	public JMenuItem createRedefiningMenu()
	{
		redefinesItem = new JMenuItem("Redefines");
		add(redefinesItem);
		redefinesItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(editor!=null){
        			FeatureListDialog.open(editor.getDiagramManager().getFrame(),null, "Redefined", property, 
        			ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser());
        			SwingUtilities.invokeLater(new Runnable() {						
						@Override
						public void run() {
		        			ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
							list.add(con);					
							editor.execute(new SetVisibilityCommand((DiagramNotification)editor,list,editor.getProject(),Visibility.REDEFINES,true));							
						}
					});					
        		}
        	}
        });
		return redefinesItem;
	}
	
	public JMenu createInvertMenu()
	{
		invertMenu = new JMenu("Invert");
		add(invertMenu);
		
		invertEndPointsItem = new JMenuItem("End Points");
		invertMenu.add(invertEndPointsItem);    			
		invertEndPointsItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.getDiagramManager().invertEndPoints((Association)((AssociationElement)con).getRelationship());
        	}
        });
		
		invertEndNamesItem = new JMenuItem("End Names");
		invertMenu.add(invertEndNamesItem);    			
		invertEndNamesItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.getDiagramManager().invertEndNames((Association)((AssociationElement)con).getRelationship());
        	}
        });
		
		invertEndMultiplicitiesItem = new JMenuItem("End Multiplicities");
		invertMenu.add(invertEndMultiplicitiesItem);    			
		invertEndMultiplicitiesItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.getDiagramManager().invertEndMultiplicities((Association)((AssociationElement)con).getRelationship());
        	}
        });
		
		invertEndTypesItem = new JMenuItem("End Types");
		invertMenu.add(invertEndTypesItem);    			
		invertEndTypesItem.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.getDiagramManager().invertEndTypes((Association)((AssociationElement)con).getRelationship());
        	}
        });
		return invertMenu;
	}
	
	public JMenu createMetaPropertyMenu()
	{
		metaPropertiesMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.metaproperties.name"));
		add(metaPropertiesMenu);
		
		essential = createCheckBoxMenuItem(metaPropertiesMenu, "metaproperties.essential");
		essential.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	 
					((Meronymic)((AssociationElement) con).getRelationship()).setIsEssential(essential.isSelected());
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((AssociationElement)con).setShowMetaProperties(true);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}
			}
		});
		
		inseparable = createCheckBoxMenuItem(metaPropertiesMenu, "metaproperties.inseparable");
		inseparable.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	 
					((Meronymic)((AssociationElement) con).getRelationship()).setIsInseparable(inseparable.isSelected());
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((AssociationElement)con).setShowMetaProperties(true);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}
			}
		});
		
		immutablewhole = createCheckBoxMenuItem(metaPropertiesMenu, "metaproperties.immutablewhole");
		immutablewhole.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	 
					((Meronymic)((AssociationElement) con).getRelationship()).setIsImmutableWhole(immutablewhole.isSelected());
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((AssociationElement)con).setShowMetaProperties(true);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}
			}
		});
		
		immutablepart = createCheckBoxMenuItem(metaPropertiesMenu, "metaproperties.immutablepart");
		immutablepart.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	 
					((Meronymic)((AssociationElement) con).getRelationship()).setIsImmutablePart(immutablepart.isSelected());
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					((AssociationElement)con).setShowMetaProperties(true);
					list.add(con);
					editor.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}
			}
		});
		
		shareable = createCheckBoxMenuItem(metaPropertiesMenu, "metaproperties.shareable");
		shareable.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (con instanceof AssociationElement) {	 
					((Meronymic)((AssociationElement) con).getRelationship()).setIsShareable(shareable.isSelected());					
					editor.getDiagramManager().refreshDiagramElement(((AssociationElement) con).getRelationship());					
				}
			}
		});
		return metaPropertiesMenu;
	}
	
	public JMenuItem createEndPointMenu()
	{	
		endNameItem = new JMenuItem("End-Point Name");
		add(endNameItem);
		endNameItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				 String name = (String)JOptionPane.showInputDialog(editor.getDiagramManager().getFrame(), 
					     "Specify the end-point name: ",
					     "Set end-point name",
						 JOptionPane.PLAIN_MESSAGE,
						 null,
						 null,
						 property.getType().getName().toLowerCase().trim()
				 );
				 if(name!=null){
					 property.setName(name);
					 ((AssociationElement)con).setShowRoles(true);
					 editor.getDiagramManager().refreshDiagramElement(property.getAssociation());
				 }
			}
		});
		return endNameItem;
	}
	
	public JMenu createMultiplicityMenu()
	{		
		multiplicityMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.multiplicity.name"));
		add(multiplicityMenu);
				
		ButtonGroup group = new ButtonGroup();
		
		zerooneItem = createRadioMenuItem(multiplicityMenu, "multiplicity.zeroone");
		group.add(zerooneItem);
		zerooneItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				editor.getDiagramManager().changeMultiplicity(property, 0, 1);
			}
		});
		
		zeromanyItem = createRadioMenuItem(multiplicityMenu, "multiplicity.zeromany");
		group.add(zeromanyItem);
		zeromanyItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				editor.getDiagramManager().changeMultiplicity(property, 0, -1);
			}
		});
		
		oneItem = createRadioMenuItem(multiplicityMenu, "multiplicity.one");
		group.add(oneItem);
		oneItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				editor.getDiagramManager().changeMultiplicity(property, 1, 1);
			}
		});
		
		onemanyItem = createRadioMenuItem(multiplicityMenu, "multiplicity.onemany");
		group.add(onemanyItem);
		onemanyItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				editor.getDiagramManager().changeMultiplicity(property, 1, -1);
			}
		});
		
		twoItem = createRadioMenuItem(multiplicityMenu, "multiplicity.two");
		group.add(twoItem);
		twoItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				editor.getDiagramManager().changeMultiplicity(property, 2, 2);
			}
		});		
		
		twomanyItem = createRadioMenuItem(multiplicityMenu, "multiplicity.twomany");
		group.add(twomanyItem);
		twomanyItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				editor.getDiagramManager().changeMultiplicity(property, 2, -1);
			}
		});		
		
		multiplicityMenu.addSeparator();
		
		otherItem = createRadioMenuItem(multiplicityMenu, "multiplicity.other");
		group.add(otherItem);
		otherItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				 String multiplicity = (String)JOptionPane.showInputDialog(editor.getDiagramManager().getFrame(), 
				     "Specify the new multiplicity: ",
				     "Set multiplicity",
					 JOptionPane.PLAIN_MESSAGE,
					 null,
					 null,
					 "0..2"
				);
				 if(multiplicity!=null){
					 try{
						 editor.getDiagramManager().changeMultiplicity(property, multiplicity);
					 }catch(Exception e){
						 editor.getDiagramManager().getFrame().showErrorMessageDialog("Parsing multiplicity string", e.getLocalizedMessage());
					 }
				 }
			}
		});
		return multiplicityMenu;
	}
	
	public void setConnection(Connection con, DiagramEditor editor)
	{
		this.con = con;
		this.editor = editor;
		if (con instanceof AssociationElement){
			visibilityMenu.setEnabled(true);
			readingDirectionMenu.setEnabled(true);
			if(((AssociationElement) con).getReadingDesign()==ReadingDesign.DESTINATION) readToDestinationItem.setSelected(true);
			else if(((AssociationElement) con).getReadingDesign()==ReadingDesign.SOURCE) readToSourceItem.setSelected(true);
			else readNoIndicatorItem.setSelected(true);
			showMultiplicitiesItem.setEnabled(true);
			showMultiplicitiesItem.setSelected(((AssociationElement)con).showMultiplicities());
			showRolesItem.setEnabled(true);
			showRolesItem.setSelected(((AssociationElement)con).showRoles());
			showSubsettingItem.setEnabled(true);
			showSubsettingItem.setSelected(((AssociationElement)con).showSubsetting());
			showRedefiningItem.setEnabled(true);
			showRedefiningItem.setSelected(((AssociationElement)con).showRedefining());
			showNameItem.setSelected(((AssociationElement)con).showName());
			showStereotypeItem.setEnabled(true);
			showStereotypeItem.setSelected(((AssociationElement)con).showOntoUmlStereotype());
			invertMenu.setVisible(true);			
		}else{			
			showNameItem.setSelected(((GeneralizationElement)con).showName());
			readingDirectionMenu.setEnabled(false);
			showMultiplicitiesItem.setSelected(false);
			showMultiplicitiesItem.setEnabled(false);			
			showRolesItem.setSelected(false);
			showRolesItem.setEnabled(false);
			showSubsettingItem.setSelected(false);
			showSubsettingItem.setEnabled(false);
			showRedefiningItem.setSelected(false);
			showRedefiningItem.setEnabled(false);
			showStereotypeItem.setSelected(false);
			showStereotypeItem.setEnabled(false);			
			invertMenu.setVisible(false);
		}
		if(con instanceof AssociationElement){
			if(((AssociationElement)con).getRelationship() instanceof Meronymic){
				metaPropertiesMenu.setVisible(true);
				shareable.setSelected(((Meronymic)((AssociationElement) con).getRelationship()).isIsShareable());
				inseparable.setSelected(((Meronymic)((AssociationElement) con).getRelationship()).isIsInseparable());
				immutablepart.setSelected(((Meronymic)((AssociationElement) con).getRelationship()).isIsImmutablePart());
				immutablewhole.setSelected(((Meronymic)((AssociationElement) con).getRelationship()).isIsImmutableWhole());
				essential.setSelected(((Meronymic)((AssociationElement) con).getRelationship()).isIsEssential());
			}else{
				metaPropertiesMenu.setVisible(false);
			}
		}
		if(con instanceof GeneralizationElement) metaPropertiesMenu.setVisible(false);
		rectMenuItem.setEnabled(true);
		
		if((con.getConnection1()!=null || con.getConnection2()!=null))
		{
			if (con instanceof AssociationElement){
				Relationship rel = ((AssociationElement)con).getRelationship();
				if (rel instanceof Association){
					Type source = ((Association)rel).getMemberEnd().get(0).getType();
					Type target = ((Association)rel).getMemberEnd().get(1).getType();
					if (source instanceof Relationship || target instanceof Relationship) rectMenuItem.setEnabled(false);
				}
			}
			if (con instanceof GeneralizationElement){
				Relationship rel = ((GeneralizationElement)con).getRelationship();
				if (rel instanceof Generalization){
					Type source = ((Generalization)rel).getGeneral();
					Type target = ((Generalization)rel).getSpecific();
					if (source instanceof Relationship || target instanceof Relationship) rectMenuItem.setEnabled(false);
				}	
			}			
		}
		
		multiplicityMenu.setVisible(false);
		endNameItem.setVisible(false);
		subsettingItem.setVisible(false);
		redefinesItem.setVisible(false);
		
		if(((BaseConnection)con).getRelationship() instanceof Association)
		{
			changeMenu.setElement(((BaseConnection)con).getRelationship());
			changeMenu.setDiagramManager(editor.getDiagramManager());
		}
	}		
	
	public void setConnection (UmlConnection con, DiagramEditor editor, boolean isSourceEndPoint)
	{		
		setConnection(con,editor);
		
		this.editor = editor;	
		this.con=con;
		this.isSource = isSourceEndPoint;	
		
		Association assoc = (Association)con.getRelationship();		
		if (this.isSource) property = assoc.getMemberEnd().get(0);
		else property = assoc.getMemberEnd().get(1);
		
		if (property.getLower()==0 && property.getUpper()==-1) zeromanyItem.setSelected(true); 
		if (property.getLower()==0 && property.getUpper()==1) zerooneItem.setSelected(true);
		if (property.getLower()==1 && property.getUpper()==1) oneItem.setSelected(true);
		if (property.getLower()==1 && property.getUpper()==-1) onemanyItem.setSelected(true);
		if (property.getLower()==2 && property.getUpper()==-1) twomanyItem.setSelected(true);
		
		multiplicityMenu.setVisible(true);
		endNameItem.setVisible(true);
		subsettingItem.setVisible(true);
		redefinesItem.setVisible(true);
	}	
	
	/**
	 * Adds the navigability setting menu.
	 * 
	 * @param menu
	 *            the parent menu
	 * @param conn
	 *            the connection
	 */
	//private void addNavigabilityMenu(JPopupMenu menu, UmlConnection conn) {
	//	Relationship relationship = (Relationship) conn.getClassifier();
	//	if (relationship == null)
	//		return; // e.g. NoteConnection has no relation
		//JMenu submenu = null;
		// TODO Uncomment me if used
		/*
		 * if (relationship.canSetElement1Navigability() ||
		 * relationship.canSetElement2Navigability()) { submenu = new
		 * JMenu(ApplicationResources.getInstance().getString(
		 * "submenu.navigableto.name")); menu.add(submenu); } if
		 * (relationship.canSetElement1Navigability()) { JCheckBoxMenuItem
		 * nav2Elem1 = createCheckBoxMenuItem(submenu, "navigabletosource");
		 * nav2Elem1.setSelected(relationship.isNavigableToElement1()); } if
		 * (relationship.canSetElement2Navigability()) { JCheckBoxMenuItem
		 * nav2Elem2 = createCheckBoxMenuItem(submenu, "navigabletotarget");
		 * nav2Elem2.setSelected(relationship.isNavigableToElement2()); }
		 */
	//}
	
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
		JCheckBoxMenuItem menuitem = new JCheckBoxMenuItem(getResourceString(prefix + ".name"));
		// Command
		String actionCommand = getResourceString(prefix + ".command");
		menuitem.setActionCommand(actionCommand);
		menuitem.addActionListener(this);
		
		menu.add(menuitem);
		return menuitem;
	}
	
	private JRadioButtonMenuItem createRadioMenuItem(JMenu menu, String name) {
		String prefix = "menuitem." + name;
		JRadioButtonMenuItem menuitem = new JRadioButtonMenuItem(
				getResourceString(prefix + ".name"));
		// Command
		String actionCommand = getResourceString(prefix + ".command");
		menuitem.setActionCommand(actionCommand);
		menuitem.addActionListener(this);
		
		menu.add(menuitem);
		return menuitem;
	}

}
