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

import java.awt.Dimension;
import java.text.Normalizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.MixinClass;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

/**
 * @author John Guerson
 */
public class ClassEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	
	private JCheckBox btnAbstract;		
	private JCheckBox btnExtensional;
	private JTextField nameField;
	private JPanel classPropPanel;
	private JLabel lblStereo;
	@SuppressWarnings("rawtypes")
	protected JComboBox stereoCombo;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClassEditionPanel(final DiagramManager diagramManager, final ClassElement classElement, RefOntoUML.Classifier element) 
	{	
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = element;
						
		classPropPanel = new JPanel();
		classPropPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(classPropPanel, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(classPropPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		
		JLabel lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		btnAbstract = new JCheckBox("Abstract");
		btnExtensional = new JCheckBox("Extensional");
		
		lblStereo = new JLabel("Classifier:");
		
		stereoCombo = new JComboBox();
		stereoCombo.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Collective", "Quantity", "Role", "Phase", "Category", "Mixin", "RoleMixin", "Relator", "Mode", "DataType", "Enumeration", "PrimitiveType", "PerceivableQuality", "NonPerceivableQuality", "NominalQuality"}));
		
		GroupLayout gl_classPropPanel = new GroupLayout(classPropPanel);
		gl_classPropPanel.setHorizontalGroup(
			gl_classPropPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPropPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_classPropPanel.createSequentialGroup()
							.addComponent(stereoCombo, 0, 184, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAbstract)
							.addGap(7)
							.addComponent(btnExtensional))
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
					.addGap(17))
		);
		gl_classPropPanel.setVerticalGroup(
			gl_classPropPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPropPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStereo)
						.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExtensional)
						.addComponent(btnAbstract))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		classPropPanel.setLayout(gl_classPropPanel);
		this.setLayout(groupLayout);		
		
		setInitialData();
		
		setPreferredSize(new Dimension(451, 80));
	}
	
	public void setInitialData()
	{		
		if (element instanceof MixinClass) btnAbstract.setEnabled(false);
		else btnAbstract.setEnabled(true);
		if (element instanceof Collective) btnExtensional.setEnabled(true);
		else btnExtensional.setEnabled(false);
		nameField.setText(element.getName());		
		btnAbstract.setSelected(element.isIsAbstract());
		stereoCombo.setSelectedItem(getStereotype(element).trim());
		stereoCombo.setEnabled(true);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void selectNameText()
	{
		nameField.selectAll();
	}
	
	public void transferClassData()
	{
		element.setName(nameField.getText());
		if (element instanceof Collective) ((Collective) element).setIsExtensional(btnExtensional.isSelected());
		element.setIsAbstract(btnAbstract.isSelected());
		
		diagramManager.updateOLEDFromModification(element,false);	
	}
}
