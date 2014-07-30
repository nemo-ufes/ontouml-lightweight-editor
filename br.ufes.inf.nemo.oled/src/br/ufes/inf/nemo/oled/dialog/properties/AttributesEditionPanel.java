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
package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Element;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * @author John Guerson
 */
public class AttributesEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ClassElement classElement;	
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private Component parent;
		
	private Map<String, DataType> modelDataTypes; 
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnUp;
	private JButton btnDown;
	private JScrollPane scrollpane;
	private JTable table;
	private AttributeTableModel attributesTableModel;
	private JPanel panel;
	private JButton btnEdit;
	private JCheckBox cbxVisible;
			
	public AttributesEditionPanel(final Component parent, final DiagramManager diagramManager, final ClassElement classElement, final Classifier element) 
	{
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = element;
		this.parent=parent;
						
		attributesTableModel = new AttributeTableModel(element);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
		);
		
		scrollpane = new JScrollPane();		
		scrollpane.setMinimumSize(new Dimension(0, 0));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		table = new JTable();		
		scrollpane.setViewportView(table);
		table.setModel(attributesTableModel);
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
		table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
		table.setSelectionForeground(Color.BLACK);
		table.setFocusable(false);	    
		table.setRowHeight(23);
		
		btnCreate = new JButton("");
		btnCreate.setFocusable(false);
		btnCreate.setToolTipText("Add new attribute to this class");
		btnCreate.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/new.png")));
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addAttribute(arg0);
			}
		});
		
		btnDelete = new JButton("");
		btnDelete.setFocusable(false);
		btnDelete.setToolTipText("Delete selected attribute");
		btnDelete.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteAttribute(arg0);
			}
		});
		
		btnUp = new JButton("");
		btnUp.setFocusable(false);
		btnUp.setToolTipText("Move up selected attribute");
		btnUp.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/arrow_up.png")));
		btnUp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveUpAttribute();
			}
		});
		
		btnDown = new JButton("");
		btnDown.setFocusable(false);
		btnDown.setToolTipText("Move down selected attribute");
		btnDown.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/arrow_down.png")));
		btnDown.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveDownAttribute();
			}
		});
		
		btnEdit = new JButton("");
		btnEdit.setEnabled(true);
		btnEdit.setFocusable(false);
		btnEdit.setToolTipText("Edit selected attribute");
		btnEdit.setIcon(new ImageIcon(AttributesEditionPanel.class.getResource("/resources/icons/x16/pencil.png")));
		btnEdit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row>=0){
					Property p = attributesTableModel.getEntry(row);
					if (parent instanceof JFrame){
						AttributeDialog dialog = new AttributeDialog((JFrame)parent,diagramManager,	classElement, element, p, false);
		    			dialog.setLocationRelativeTo(parent);
		    			dialog.setVisible(true);	
					}else if (parent instanceof JDialog) {
						AttributeDialog dialog = new AttributeDialog((JDialog)parent,diagramManager, classElement, element, p, false);
		    			dialog.setLocationRelativeTo(parent);
		    			dialog.setVisible(true);
					}
				}
			}
		});
		
		cbxVisible = new JCheckBox("Turn attributes visible");
		cbxVisible.setPreferredSize(new Dimension(140, 20));
		cbxVisible.setHorizontalAlignment(SwingConstants.LEFT);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollpane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxVisible, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDown, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxVisible, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		this.setLayout(groupLayout);
		
		setSize(450,221);
		
		if (classElement !=null) { cbxVisible.setSelected(classElement.showAttributes());cbxVisible.setEnabled(true); }
		else { cbxVisible.setSelected(false); cbxVisible.setEnabled(false); }
		
		myPostInit();
	}	

	public void refreshData()
	{
		attributesTableModel.fireTableDataChanged();
	}
	
	private void myPostInit() 
	{
		modelDataTypes = new HashMap<String, DataType>();
		List<DataType> dataTypes = ModelHelper.getModelDataTypes(diagramManager.getCurrentProject());
		for (DataType item : dataTypes) {			
			modelDataTypes.put(item.getName(), item);
		}
		
		TableColumn typeColumn = table.getColumnModel().getColumn(1);	
		typeColumn.setCellEditor(createEditor(modelDataTypes.keySet().toArray()));

		TableColumn typeColumn2 = table.getColumnModel().getColumn(2);	
		typeColumn2.setCellEditor(createEditor(new String[]{"1","0..1","0..*","1..*"}));
		
		table.setSurrendersFocusOnKeystroke(true);
		
		if(element instanceof DataType)
		{
			DataType dataType = (DataType)element;			
			for (Property attribute : dataType.getAttribute()) {
				attributesTableModel.addEntry(attribute);
			}
		} else {
			Class umlclass = (Class) element;
			for (Property attribute : umlclass.getAttribute()) {
				attributesTableModel.addEntry(attribute);
			}			
		}
	}
	
	private TableCellEditor createEditor(Object[] objects) 
	{
        @SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox combo = new JComboBox(objects) {
        	private static final long serialVersionUID = 1L;			
			@Override
			protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) 
			{
				boolean retValue = super.processKeyBinding(ks, e, condition,pressed);
                if (!retValue && isStartingCellEdit() && editor != null) {
                    // this is where the magic happens
                    // not quite right; sets the value, but doesn't advance the
                    // cursor position for AC
                    editor.setItem(String.valueOf(ks.getKeyChar()));
                }
                return retValue;
			}			
            private boolean isStartingCellEdit() 
            {
                JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, this);
                return table != null && table.isFocusOwner() && !Boolean.FALSE.equals((Boolean)table.getClientProperty("JTable.autoStartsEdit"));
            }
        };        
        //AutoCompleteDecorator.decorate(combo);
        combo.setEditable(true);
        return new DefaultCellEditor(combo);
    }
	
	private void moveUpAttribute() 
	{
		int row = table.getSelectedRow();
		if (row >=0  && row < table.getRowCount()) 
		{
			attributesTableModel.moveUpEntry(row);
			table.setRowSelectionInterval(row - 1, row - 1);
		}
	}

	private void moveDownAttribute() 
	{
		int row = table.getSelectedRow();
		if (row >=0  && row < table.getRowCount()) 
		{
			attributesTableModel.moveDownEntry(row);
			table.setRowSelectionInterval(row + 1, row + 1);
		}
	}
	
	protected void deleteAttribute(ActionEvent evt) 
	{
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < attributesTableModel.getRowCount()) 
		{
			attributesTableModel.removeEntryAt(selectedRow);
		}
	}
	
	protected void addAttribute(ActionEvent evt) 
	{
		attributesTableModel.addEmptyEntry();		
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void transferAttributesData()
	{
		List<Property> classAttributes = attributesTableModel.getEntries();
		
		if(cbxVisible.isSelected()==false){
			if (classAttributes.size()>0) {
				cbxVisible.setSelected(true);
			}
		}
		if (classElement !=null) classElement.setShowAttributes(cbxVisible.isSelected());
		diagramManager.updateOLEDFromInclusion(element);
		
		transferDataTypes();	
		deleteAttributes(classAttributes);
		transferAddedAttributes(classAttributes);
		
		classElement.reinitAttributesCompartment();
		classElement.invalidate();
	}
	
	private void deleteAttributes(List<Property> classAttributes )
	{
		ArrayList<Property> attributes = new ArrayList<Property>();
		
		if(element instanceof DataType){
			attributes.addAll(((DataType)element).getOwnedAttribute());
			for(Property p: attributes){
				if(!classAttributes.contains(p)) {					
					((DataType)element).getOwnedAttribute().remove(p);
					diagramManager.updateOLEDFromDeletion(p);
				}
			}
		}
		if(element instanceof RefOntoUML.Class){
			attributes.addAll(((RefOntoUML.Class)element).getOwnedAttribute());
			for(Property p: attributes){
				if(!classAttributes.contains(p)) {
					((RefOntoUML.Class)element).getOwnedAttribute().remove(p);
					diagramManager.updateOLEDFromDeletion(p);
				}
			}
		}
	}
	
	private void transferAddedAttributes(List<Property> classAttributes )
	{
		for (Property property : classAttributes) 
		{			
			if(!property.getName().isEmpty() || !property.getType().getName().isEmpty())
			{				
				DataType existingType = modelDataTypes.get(property.getType().getName().trim());
				if(existingType != null){ 
					property.setType(existingType); 
				}				
				if(element instanceof DataType){
					((DataType)element).getOwnedAttribute().add(property);					
				}else{				
					((Class)element).getOwnedAttribute().add(property);
				}
				diagramManager.updateOLEDFromInclusion(property);
			}
		}
	}
	
	private void transferDataTypes() 
	{
		List<Property> classAttributes = attributesTableModel.getEntries();
		ArrayList<Element> createdList = new ArrayList<Element>();
		for (Property property : classAttributes) 
		{
			//Avoid the creation of duplicated types			
			if(modelDataTypes.keySet().contains(property.getType().getName().trim()) == false)
			{	
				UmlProject project = diagramManager.getCurrentProject();				
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), property.getType());
				project.getEditingDomain().getCommandStack().execute(cmd);				
				modelDataTypes.put(property.getType().getName(),(DataType)property.getType());
				createdList.add((Element) property.getType());
			}
		}		
		
		for(Element element: createdList) diagramManager.updateOLEDFromInclusion(element);		
	}
}
