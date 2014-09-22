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

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.Classifier;
import RefOntoUML.Constraintx;
import RefOntoUML.StringExpression;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;

/**
 * @author John Guerson
 */
public class ConstraintEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private DiagramElement diagramElement;
	private Classifier element;
	private DiagramManager diagramManager;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboConstraintType;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnDelete;
	@SuppressWarnings("rawtypes")
	private JComboBox comboConstraint;
	private JScrollPane scrollPaneText;
	private JTextArea constraintTextArea;
	
	private ArrayList<Constraintx> constraintList = new ArrayList<Constraintx>();
	private JTextField textFieldName;

	private JLabel lblName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConstraintEditionPanel(DiagramManager diagramManager, DiagramElement diagramElement, Classifier element) 
	{
		setBorder(null);
		this.diagramManager = diagramManager;
		this.diagramElement = diagramElement;
		this.element = element;
		
		comboConstraint = new JComboBox();
		comboConstraint.setFocusable(false);
		comboConstraint.setToolTipText("This is the name of your constraint");
		comboConstraint.setPreferredSize(new Dimension(350, 20));
		comboConstraint.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				ConstraintElement ce = (ConstraintElement) comboConstraint.getSelectedItem();
				if(ce!=null) {
					constraintTextArea.setText(((StringExpression)ce.getConstraint().getSpecification()).getSymbol());
					textFieldName.setText(ce.getConstraint().getName());
				}
			}
		});
		
		btnSave = new JButton("");
		btnSave.setFocusable(false);
		btnSave.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/icons/x16/disk.png")));
		btnSave.setToolTipText("Save selected constraint");
		btnSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveConstraintActionPerformed();				
			}
		});
		
		btnDelete = new JButton("");
		btnDelete.setFocusable(false);
		btnDelete.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnDelete.setToolTipText("Delete seletected constraint");
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteConstraintActionPerformed();				
			}
		});
		
		comboConstraintType = new JComboBox();
		comboConstraintType.setToolTipText("The type of constraint to be created");
		comboConstraintType.setModel(new DefaultComboBoxModel(new String[] {"invariant", "derivation"}));
		
		
		btnAdd = new JButton("");
		btnAdd.setFocusable(false);
		btnAdd.setIcon(new ImageIcon(ConstraintEditionPanel.class.getResource("/resources/icons/x16/new.png")));
		btnAdd.setToolTipText("Add a new constraint to this class");
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createConstraintActionPerformed();				
			}
		});
		
		JLabel lblConstraint = new JLabel("Constraint:");
		
		lblName = new JLabel("Name:");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		constraintTextArea = new JTextArea();	
		constraintTextArea.setToolTipText("Click here to start writing constraints");
		
		scrollPaneText = new JScrollPane();
		scrollPaneText.setToolTipText("Click here to start writing constraints");
		scrollPaneText.setViewportView(constraintTextArea);
		
		lblNewLabel = new JLabel("Type:");
		
		lblNewLabel_1 = new JLabel("Content:");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(340, Short.MAX_VALUE)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblConstraint, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboConstraintType, 0, 387, Short.MAX_VALUE)
						.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addComponent(comboConstraint, 0, 387, Short.MAX_VALUE)
						.addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConstraint, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboConstraint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboConstraintType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(1))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {comboConstraint, comboConstraintType, textFieldName});
		setLayout(groupLayout);
		
		setInitialData();
	}
	
	/** Private Class: Constraint Element */
	private class ConstraintElement 
	{
		RefOntoUML.Constraintx c;
		public ConstraintElement(RefOntoUML.Constraintx c){
			this.c = c;
		}
		@Override
		public String toString(){
			String result = new String();
			result = c.getName();			
			return result;
		}
		public RefOntoUML.Constraintx getConstraint(){
			return c;
		}
	}
		
	private void enableConstraintArea(boolean value)
	{
		//constraintTextArea.setEnabled(value);
		//scrollPaneText.setEnabled(value);
		comboConstraint.setEnabled(value);
		//lblSelected.setEnabled(value);
		//if (!value) constraintTextArea.setBackground(UIManager.getColor("Panel.background"));
		//else constraintTextArea.setBackground(Color.WHITE);
		btnSave.setEnabled(value);
		btnDelete.setEnabled(value);
	}
	
	@SuppressWarnings("unchecked")
	public void setInitialData()
	{		
		OntoUMLParser refparser = diagramManager.getFrame().getBrowserManager().getProjectBrowser().getParser();
		for(Constraintx c: refparser.getAllInstances(RefOntoUML.Constraintx.class)){		
			for(RefOntoUML.Element elem: c.getConstrainedElement()) {
				if (elem.equals(element)) comboConstraint.addItem(new ConstraintElement(c));
			}
			constraintList.add(c);
		}		
		if (comboConstraint.getItemCount()>0) {
			comboConstraint.setSelectedIndex(0);
			Constraintx c= ((ConstraintElement)comboConstraint.getSelectedItem()).getConstraint();
			constraintTextArea.setText(((StringExpression)c.getSpecification()).getSymbol()+"\n\n");
		}else{
			enableConstraintArea(false);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createConstraintActionPerformed()
	{
		Constraintx c = diagramManager.getElementFactory().createConstraintx();		
		c.getConstrainedElement().add(element);
		String selectedItem = ((String)comboConstraintType.getSelectedItem());
		if(selectedItem.compareToIgnoreCase("invariant")==0) c.setName("Invariant");
		else c.setName("Derivation");
		ConstraintElement ce = new ConstraintElement(c);		
		comboConstraint.addItem(ce);				
		comboConstraint.setSelectedIndex(comboConstraint.getItemCount()-1);		
		if(selectedItem.compareToIgnoreCase("invariant")==0){
			constraintTextArea.setText("context <Type> \ninv: true");
			((StringExpression)c.getSpecification()).setSymbol("context <Type> \ninv: true");
		}else{
			constraintTextArea.setText("context <Type>::<Property>::<propertyType> \nderive: true");
			((StringExpression)c.getSpecification()).setSymbol("context <Type>::<Property>::<propertyType> \nderive: true");
		}
		enableConstraintArea(true);
	}

	public void saveConstraintActionPerformed()
	{
		if((ConstraintElement)comboConstraint.getSelectedItem()!=null){
			Constraintx c = ((ConstraintElement)comboConstraint.getSelectedItem()).getConstraint();
			((StringExpression)c.getSpecification()).setSymbol(constraintTextArea.getText());
			c.setName(textFieldName.getText());
			comboConstraint.repaint();
			comboConstraint.validate();
		}		
	}

	public void deleteConstraintActionPerformed()
	{
		if((ConstraintElement)comboConstraint.getSelectedItem()!=null){
			comboConstraint.removeItem(comboConstraint.getSelectedItem());
			comboConstraint.invalidate();	
			constraintTextArea.setText("");
			textFieldName.setText("");
		}			
		if (comboConstraint.getItemCount()>0) {
			enableConstraintArea(true);
		}else{
			enableConstraintArea(false);
		}
	}
	
	public ArrayList<Constraintx> getConstraints()
	{
		ArrayList<Constraintx> result = new ArrayList<Constraintx>();
		for(int i=0; i<comboConstraint.getItemCount();i++){
			ConstraintElement ce = (ConstraintElement)comboConstraint.getItemAt(i);
			if (ce!=null) result.add(ce.getConstraint());
		}
		return result;
	}
	
	public ArrayList<Constraintx> getConstraintx(RefOntoUML.Element element)
	{
		ArrayList<Constraintx> result = new ArrayList<Constraintx>();
		for(Constraintx c: constraintList){
			for(RefOntoUML.Element elem: c.getConstrainedElement()) {
				if (elem.equals(element)) result.add(c);
			}							
		}
		return result;
	}
	
	public void transferConstraintsData()
	{
		// added
		ArrayList<Constraintx> toBeAdded = new ArrayList<Constraintx>();
		for(Constraintx c: getConstraints()){			
			if (!getConstraintx(element).contains(c)){				
				toBeAdded.add(c);
			}
		}
		for(Constraintx cmt: toBeAdded) { diagramManager.addConstraintx(cmt, (RefOntoUML.Element)element); } 
			
		//removed
		ArrayList<Constraintx> toBeDeleted = new ArrayList<Constraintx>();
		for(Constraintx c: getConstraintx(element)){
			if (!getConstraints().contains(c)){
				toBeDeleted.add(c);
			}
		}
		for(Constraintx cmt: toBeDeleted) { diagramManager.deleteFromOLED(cmt,false); }	
		
	}
}
