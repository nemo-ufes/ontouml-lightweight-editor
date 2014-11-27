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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.OntoUMLElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * @author John Guerson
 */
public class PropertyEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Component parent;
	
	@SuppressWarnings("unused")
	private DiagramElement ownerDiagramElement;	
	private Classifier ownerElement;
	private Property property;
	private DiagramManager diagramManager;
	
	private JTextField nameField;
	private JLabel lblMultiplicity;
	@SuppressWarnings("rawtypes")
	private JComboBox multCombo;
	private JLabel lblAggregationKind;
	@SuppressWarnings("rawtypes")
	private JComboBox aggregCombo;
	private JButton btnRedefined;
	private JButton btnSubsetted;
	private JCheckBox cbxDerived;
	private JCheckBox cbxUnique;
	private JCheckBox cbxOrdered;
	private JCheckBox cbxReadOnly;
	@SuppressWarnings("rawtypes")
	private JComboBox typeCombo;
	private JLabel lblName;
	private JLabel lblType;
	JPanel mainPanel;
	JPanel optionsPanel;
	JPanel listPanel;
	JPanel multPanel;
	private JTextField subsettedText;
	private JTextField redefinedText;
	
	public PropertyEditionPanel(Component parent, final DiagramManager diagramManager, DiagramElement ownerDiagramElement, RefOntoUML.Classifier ownerElem, final Property property)
	{
		this.parent=parent;
		initData(diagramManager,ownerDiagramElement,ownerElem,property);
		initGUI();		
	}
	
//	/**
//	 * @wbp.parser.constructor 
//	 */
//	public PropertyEditionPanel(JFrame parent, final DiagramManager diagramManager, DiagramElement ownerDiagramElement, RefOntoUML.Classifier ownerElem, final Property property)
//	{
//		this.parent=parent;
//		initData(diagramManager,ownerDiagramElement,ownerElem,property);
//		initGUI();		
//	}
		
	public void initData(final DiagramManager diagramManager, DiagramElement ownerDiagramElement, RefOntoUML.Classifier ownerElem, final Property property)
	{
		this.diagramManager = diagramManager;
		this.property = property;
		this.ownerDiagramElement = ownerDiagramElement;
		this.ownerElement = ownerElem;		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGUI () 
	{	
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		listPanel = new JPanel();
		listPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		multPanel = new JPanel();
		multPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		lblMultiplicity = new JLabel("Multiplicity:");
		
		multCombo = new JComboBox();
		multCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "0..1", "0..*", "1..*"}));
		multCombo.setPreferredSize(new Dimension(80, 20));
		multCombo.setEditable(true);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(multPanel, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
				.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
				.addComponent(listPanel, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(multPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2))
		);
		
		lblAggregationKind = new JLabel("Aggregation Kind:");
		
		aggregCombo = new JComboBox();
		aggregCombo.setModel(new DefaultComboBoxModel(new String[] {"none", "shared", "composite"}));
		aggregCombo.setPreferredSize(new Dimension(80, 20));
		GroupLayout gl_multPanel = new GroupLayout(multPanel);
		gl_multPanel.setHorizontalGroup(
			gl_multPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_multPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_multPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMultiplicity, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAggregationKind, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_multPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(aggregCombo, 0, 134, Short.MAX_VALUE)
						.addComponent(multCombo, 0, 134, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_multPanel.setVerticalGroup(
			gl_multPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_multPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_multPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMultiplicity)
						.addComponent(multCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_multPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(aggregCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAggregationKind))
					.addContainerGap())
		);
		multPanel.setLayout(gl_multPanel);
		
		btnSubsetted = new JButton("");
		btnSubsetted.setPreferredSize(new Dimension(30, 25));
		btnSubsetted.setIcon(new ImageIcon(PropertyEditionPanel.class.getResource("/resources/icons/x16/pencil.png")));
		btnSubsetted.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (parent instanceof JFrame)
					FeatureListDialog.open((JFrame)parent,subsettedText, "Subsetted", property, 
					diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser());
				else
					FeatureListDialog.open((JDialog)parent,subsettedText, "Subsetted", property, 
					diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser());
			}
		});
		
		subsettedText = new JTextField();
		subsettedText.setEditable(false);
		subsettedText.setColumns(10);
		subsettedText.setPreferredSize(new Dimension(300, 20));
		
		redefinedText = new JTextField();
		redefinedText.setEditable(false);
		redefinedText.setColumns(10);
		redefinedText.setSize(new Dimension(100, 50));
		
		btnRedefined = new JButton("");
		btnRedefined.setPreferredSize(new Dimension(30, 25));
		btnRedefined.setIcon(new ImageIcon(PropertyEditionPanel.class.getResource("/resources/icons/x16/pencil.png")));
		btnRedefined.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (parent instanceof JFrame)
					FeatureListDialog.open((JFrame)parent,redefinedText, "Redefined", property, 
					diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser());
				else
					FeatureListDialog.open((JDialog)parent,redefinedText, "Redefined", property,
					diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser());
			}
		});
		
		JLabel lblSubsetted = new JLabel("Subsetted:");
		
		JLabel lblRedefined = new JLabel("Redefined:");
		
		GroupLayout gl_listPanel = new GroupLayout(listPanel);
		gl_listPanel.setHorizontalGroup(
			gl_listPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_listPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_listPanel.createSequentialGroup()
							.addComponent(lblSubsetted)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(subsettedText, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
						.addGroup(gl_listPanel.createSequentialGroup()
							.addComponent(lblRedefined)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(redefinedText, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
					.addGap(6)
					.addGroup(gl_listPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSubsetted, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRedefined, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_listPanel.setVerticalGroup(
			gl_listPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_listPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSubsetted, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_listPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(subsettedText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSubsetted)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_listPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_listPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(redefinedText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblRedefined))
						.addComponent(btnRedefined, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		listPanel.setLayout(gl_listPanel);
		
		cbxDerived = new JCheckBox("Derived");
		cbxDerived.setPreferredSize(new Dimension(70, 20));
		
		cbxUnique = new JCheckBox("Unique");
		cbxUnique.setPreferredSize(new Dimension(95, 20));
		
		cbxOrdered = new JCheckBox("Ordered");
		cbxOrdered.setPreferredSize(new Dimension(75, 20));
		
		cbxReadOnly = new JCheckBox("Read Only");
		cbxReadOnly.setPreferredSize(new Dimension(100, 20));
		GroupLayout gl_optionsPanel = new GroupLayout(optionsPanel);
		gl_optionsPanel.setHorizontalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addComponent(cbxDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(cbxUnique, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addComponent(cbxOrdered, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxReadOnly, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		gl_optionsPanel.setVerticalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxDerived, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxUnique, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxReadOnly, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxOrdered, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		optionsPanel.setLayout(gl_optionsPanel);
		
		lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		lblType = new JLabel("Type:");
		
		typeCombo = new JComboBox();

//USELESS: model is set later
//		if (property.getType()!=null){
//			typeCombo.setModel(new DefaultComboBoxModel(new String[] {property.getType().getName()}));
//		}
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(typeCombo, 0, 334, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(typeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		mainPanel.setLayout(gl_mainPanel);
		setLayout(groupLayout);
		
		setInitialData();
		
		setPreferredSize(new Dimension(456, 241));
	}
			
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setInitialData()
	{
		nameField.setText(property.getName());
		cbxDerived.setSelected(property.isIsDerived());
		cbxOrdered.setSelected(property.isIsOrdered());
		cbxReadOnly.setSelected(property.isIsReadOnly());
		cbxUnique.setSelected(property.isIsUnique());
		aggregCombo.setSelectedItem(property.getAggregation().getName());
		
		if (ownerElement instanceof Mediation || ownerElement instanceof Characterization){
			if (property.equals(((Association)ownerElement).getMemberEnd().get(0))) cbxReadOnly.setEnabled(true);
			else cbxReadOnly.setEnabled(false);
		}		
		
		if (ownerElement instanceof Meronymic){
			aggregCombo.setEnabled(true);
		}else{
			aggregCombo.setEnabled(false);			
		}
		
		String multiplicity = new String();
		if (property.getLower()==property.getUpper() && property.getUpper()!=-1) multiplicity = Integer.toString(property.getLower());
		else if (property.getLower()==property.getUpper() && property.getUpper()==-1) multiplicity = "*";
		else if (property.getUpper()==-1) multiplicity = property.getLower()+".."+"*";
		else multiplicity = property.getLower()+".."+property.getUpper();
		
		if(multiplicity.compareToIgnoreCase("1")==0) multCombo.setSelectedIndex(0);
		else if(multiplicity.compareToIgnoreCase("0..1")==0)multCombo.setSelectedIndex(1);
		else if(multiplicity.compareToIgnoreCase("0..*")==0)multCombo.setSelectedIndex(2);
		else if(multiplicity.compareToIgnoreCase("1..*")==0) multCombo.setSelectedIndex(3);
		else { multCombo.setSelectedItem(multiplicity); }
		
		String str = new String();
    	int i=0;    	
    	for(Property p: property.getSubsettedProperty())
    	{    
    		str += OntoUMLNameHelper.getNameAndType(p, false);
    		if (i<property.getSubsettedProperty().size()-1) 
    			str+=", ";    		
    		i++;
    	}	
		subsettedText.setText(str);
		
		str = new String();
    	i=0;    	
    	for(Property p: property.getRedefinedProperty())
    	{    		
    		str += OntoUMLNameHelper.getNameAndType(p, false);
    		if (i<property.getRedefinedProperty().size()-1) 
    			str+=", ";    		
    		i++;
    	}	
		redefinedText.setText(str);		
		
		ArrayList<OntoUMLElement> list = new ArrayList<OntoUMLElement>();
		OntoUMLElement value = null;
		OntoUMLParser refparser = diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser();
		if (property.getType()!=null) value = new OntoUMLElement(property.getType(),"");
		else value = new OntoUMLElement(null,"");			    	
    	for(RefOntoUML.Type t: refparser.getAllInstances(RefOntoUML.Type.class))
    	{
			if(t instanceof RefOntoUML.Class || t instanceof RefOntoUML.DataType || t instanceof RefOntoUML.Association)
			{
				if (((OntoUMLElement) value).getElement()!=null && t.equals(((OntoUMLElement) value).getElement())) list.add((OntoUMLElement)value);				
    			else list.add(new OntoUMLElement(t,""));	    			
    		}	    					
    	}
    	if (((OntoUMLElement) value).getElement()==null) list.add((OntoUMLElement)value);
    	else if (!refparser.getAllInstances(RefOntoUML.Type.class).contains(property.getType())) list.add((OntoUMLElement)value);    	
    	Collections.sort(list,new CustomComparator());	    	
    	typeCombo.setModel(new DefaultComboBoxModel(list.toArray()));
    	typeCombo.setSelectedItem(value);    	
	}
	
	public class CustomComparator implements Comparator<OntoUMLElement> 
    {
        @Override
        public int compare(OntoUMLElement o1, OntoUMLElement o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
	 
	public void transferPropertyData() 
	{
		boolean redesign = false;
		
		try{			
			property.setName(nameField.getText());
			property.setIsDerived(cbxDerived.isSelected());
			if(cbxDerived.isSelected()) nameField.setText(nameField.getText().replace("/",""));
			nameField.repaint();
			nameField.validate();
			property.setIsOrdered(cbxOrdered.isSelected());
			property.setIsReadOnly(cbxReadOnly.isSelected());
			property.setIsUnique(cbxUnique.isSelected());
			if(((String)aggregCombo.getSelectedItem()).compareToIgnoreCase("shared")==0) property.setAggregation(AggregationKind.SHARED);
			else if (((String)aggregCombo.getSelectedItem()).compareToIgnoreCase("composite")==0) property.setAggregation(AggregationKind.COMPOSITE);
			else property.setAggregation(AggregationKind.NONE);			
			ModelHelper.setMultiplicityFromString(property, (String)multCombo.getSelectedItem());
			RefOntoUML.Type type = (RefOntoUML.Type)((OntoUMLElement)typeCombo.getSelectedItem()).getElement();			
			if (type!=null && !type.equals(property.getType())) redesign = true;
			property.setType(type);

		}catch(Exception e){
			diagramManager.getFrame().showErrorMessageDialog("Transfering data to property", e.getLocalizedMessage());
		}
					
		diagramManager.updateOLEDFromModification(ownerElement, redesign);
		diagramManager.updateOLEDFromModification(property, redesign);
	}
}
