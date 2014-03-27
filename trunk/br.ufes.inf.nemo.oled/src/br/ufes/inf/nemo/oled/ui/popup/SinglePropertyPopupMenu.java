package br.ufes.inf.nemo.oled.ui.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import RefOntoUML.Association;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.util.AppCommandListener;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;

public class SinglePropertyPopupMenu  extends JPopupMenu implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Set<AppCommandListener> commandListeners = new HashSet<AppCommandListener>();	
	private DiagramEditor editor;
	private UmlConnection con;
	private boolean isSource;
	private JMenu multiplicityMenu;
	@SuppressWarnings("unused")
	private JMenuItem propertyItem;
	private JRadioButtonMenuItem zerooneItem;
	private JRadioButtonMenuItem zeromanyItem;
	private JRadioButtonMenuItem oneItem;
	private JRadioButtonMenuItem onemanyItem;
	private JRadioButtonMenuItem twomanyItem;
	private JRadioButtonMenuItem twoItem;
	private JRadioButtonMenuItem otherItem;
	private Property property;
	private JMenuItem endNameItem;
	
	public SinglePropertyPopupMenu()
	{
		//propertyItem = createMenuItem(this, "editproperties");
		//propertyItem.setAccelerator(KeyStroke.getKeyStroke("F9"));
		
		multiplicityMenu = new JMenu(ApplicationResources.getInstance().getString("submenu.multiplicity.name"));
		add(multiplicityMenu);
		
		endNameItem = new JMenuItem("Set End-Point Name");
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
	}
	
	public void setProperty (UmlConnection con, boolean isSource, DiagramEditor editor)
	{		
		this.editor = editor;	
		this.con=con;
		this.isSource = isSource;	
		
		Association assoc = (Association)con.getRelationship();		
		if (this.isSource) property = assoc.getMemberEnd().get(0);
		else property = assoc.getMemberEnd().get(1);
		
		if (property.getLower()==0 && property.getUpper()==-1) zeromanyItem.setSelected(true); 
		if (property.getLower()==0 && property.getUpper()==1) zerooneItem.setSelected(true);
		if (property.getLower()==1 && property.getUpper()==1) oneItem.setSelected(true);
		if (property.getLower()==1 && property.getUpper()==-1) onemanyItem.setSelected(true);
		if (property.getLower()==2 && property.getUpper()==-1) twomanyItem.setSelected(true);
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

