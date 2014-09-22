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
package br.ufes.inf.nemo.oled.validator.meronymic;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.validator.meronymic.checkers.MeronymicError;
import br.ufes.inf.nemo.validator.meronymic.checkers.PreConditionTask;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.CheckerTable;

/**
 * @author Tiago Sales
 */
public class PreConditionPanel extends ValidationPanel<MeronymicError<?>> {

	private static final long serialVersionUID = 5989549633804270001L;
	
	private CheckerTable table;
	private JScrollPane scrollPane;
	private JCheckBox checkHierarchyCycle;
	private JCheckBox checkValidSpecialization;
	private JCheckBox checkValidIdentities;
	private JCheckBox checkAggregationKind;
	private JCheckBox checkWellFormedPartWhole;
	private JCheckBox checkPartWholeCycles;
	private JButton buttonCheck;
	private JButton buttonStop;
	private JProgressBar progressBar;
	private JLabel labelResult;
	private JLabel labelIntroduction;
	private JButton fixButton;
	
	private OntoUMLParser parser;
	private PreConditionTask task;
	
	
	public boolean isComplete;
	
	/**
	 * Create the panel.
	 */
	public PreConditionPanel(JDialog dialog, OntoUMLParser parser, JButton saveButton, JButton applyButton) {
		super(dialog,saveButton,applyButton);
		this.parser = parser;
		
		isComplete = false;
		
		table = new CheckerTable();
	    ListSelectionModel selectionModel = table.getSelectionModel();
	    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    selectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	       if(table.getSelectedRow()!=-1)
	    	   fixButton.setEnabled(true);
	       else
	    	   fixButton.setEnabled(false);
	      }
	    });
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		buttonCheck = new JButton("Check");
		buttonCheck.addActionListener(actionCheck);
		
		buttonStop = new JButton("Stop");
		buttonStop.addActionListener(actionStop);
		buttonStop.setEnabled(false);
		
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.5};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JButton btnHelp1 = new JButton("");
		GridBagConstraints gbc_btnHelp1 = new GridBagConstraints();
		gbc_btnHelp1.insets = new Insets(0, 0, 5, 5);
		gbc_btnHelp1.gridx = 0;
		gbc_btnHelp1.gridy = 0;
		panel.add(btnHelp1, gbc_btnHelp1);
		btnHelp1.setPreferredSize(new Dimension(20, 20));
		btnHelp1.setBorderPainted(false);
		btnHelp1.setContentAreaFilled(false);
		btnHelp1.setOpaque(false);
		btnHelp1.setRolloverIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help-rollover.png")));
		btnHelp1.setIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help.png")));
		
		checkHierarchyCycle = new JCheckBox("Hierarchy Cycle");
		GridBagConstraints gbc_checkHierarchyCycle = new GridBagConstraints();
		gbc_checkHierarchyCycle.anchor = GridBagConstraints.WEST;
		gbc_checkHierarchyCycle.insets = new Insets(0, 0, 5, 5);
		gbc_checkHierarchyCycle.gridx = 1;
		gbc_checkHierarchyCycle.gridy = 0;
		panel.add(checkHierarchyCycle, gbc_checkHierarchyCycle);
		
		JButton button_2 = new JButton("");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 0;
		panel.add(button_2, gbc_button_2);
		button_2.setIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help.png")));
		button_2.setPreferredSize(new Dimension(20, 20));
		button_2.setOpaque(false);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		
		checkAggregationKind = new JCheckBox("Aggregation Kind Defined");
		GridBagConstraints gbc_checkAggregationKind = new GridBagConstraints();
		gbc_checkAggregationKind.weightx = 1.0;
		gbc_checkAggregationKind.anchor = GridBagConstraints.WEST;
		gbc_checkAggregationKind.insets = new Insets(0, 0, 5, 5);
		gbc_checkAggregationKind.gridx = 3;
		gbc_checkAggregationKind.gridy = 0;
		panel.add(checkAggregationKind, gbc_checkAggregationKind);
		
		JButton button = new JButton("");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		panel.add(button, gbc_button);
		button.setIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help.png")));
		button.setPreferredSize(new Dimension(20, 20));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		checkValidSpecialization = new JCheckBox("Generalization Between Object Classes");
		GridBagConstraints gbc_checkValidSpecialization = new GridBagConstraints();
		gbc_checkValidSpecialization.anchor = GridBagConstraints.WEST;
		gbc_checkValidSpecialization.insets = new Insets(0, 0, 5, 5);
		gbc_checkValidSpecialization.gridx = 1;
		gbc_checkValidSpecialization.gridy = 1;
		panel.add(checkValidSpecialization, gbc_checkValidSpecialization);
		
		JButton button_6 = new JButton("");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 2;
		gbc_button_6.gridy = 1;
		panel.add(button_6, gbc_button_6);
		button_6.setIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help.png")));
		button_6.setPreferredSize(new Dimension(20, 20));
		button_6.setOpaque(false);
		button_6.setContentAreaFilled(false);
		button_6.setBorderPainted(false);
		
		checkWellFormedPartWhole = new JCheckBox("Well-formed Part-Whole Relations");
		GridBagConstraints gbc_checkWellFormedPartWhole = new GridBagConstraints();
		gbc_checkWellFormedPartWhole.anchor = GridBagConstraints.WEST;
		gbc_checkWellFormedPartWhole.insets = new Insets(0, 0, 5, 5);
		gbc_checkWellFormedPartWhole.gridx = 3;
		gbc_checkWellFormedPartWhole.gridy = 1;
		panel.add(checkWellFormedPartWhole, gbc_checkWellFormedPartWhole);
		
		JButton button_1 = new JButton("");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 2;
		panel.add(button_1, gbc_button_1);
		button_1.setIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help.png")));
		button_1.setPreferredSize(new Dimension(20, 20));
		button_1.setOpaque(false);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		checkValidIdentities = new JCheckBox("Valid Identities");
		GridBagConstraints gbc_checkValidIdentities = new GridBagConstraints();
		gbc_checkValidIdentities.anchor = GridBagConstraints.WEST;
		gbc_checkValidIdentities.insets = new Insets(0, 0, 0, 5);
		gbc_checkValidIdentities.gridx = 1;
		gbc_checkValidIdentities.gridy = 2;
		panel.add(checkValidIdentities, gbc_checkValidIdentities);
		
		JButton button_7 = new JButton("");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 0, 5);
		gbc_button_7.gridx = 2;
		gbc_button_7.gridy = 2;
		panel.add(button_7, gbc_button_7);
		button_7.setIcon(new ImageIcon(PreConditionPanel.class.getResource("/br/ufes/inf/nemo/validator/meronymic/resources/help.png")));
		button_7.setPreferredSize(new Dimension(20, 20));
		button_7.setOpaque(false);
		button_7.setContentAreaFilled(false);
		button_7.setBorderPainted(false);
		
		fixButton = new JButton("Fix");
		fixButton.addActionListener(fixAction);
		fixButton.setEnabled(false);
		
		labelResult = new JLabel("The following erros were found on the model:");
		labelResult.setHorizontalAlignment(SwingConstants.LEFT);
		
		labelIntroduction = new JLabel("To validate the transitivity of part-whole relations, the model must pass the following tests:");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelResult, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(fixButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addGap(16)
							.addComponent(buttonCheck)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonStop))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelIntroduction, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
							.addGap(1)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelIntroduction)
					.addGap(4)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(fixButton)
						.addComponent(labelResult))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonStop)
						.addComponent(buttonCheck))
					.addContainerGap())
		);
		
		checkPartWholeCycles = new JCheckBox("Part-Whole Relations Cycles");
		GridBagConstraints gbc_checkPartWholeCycles = new GridBagConstraints();
		gbc_checkPartWholeCycles.insets = new Insets(0, 0, 0, 5);
		gbc_checkPartWholeCycles.anchor = GridBagConstraints.WEST;
		gbc_checkPartWholeCycles.gridx = 3;
		gbc_checkPartWholeCycles.gridy = 2;
		panel.add(checkPartWholeCycles, gbc_checkPartWholeCycles);
		setLayout(groupLayout);
	}
	
	private ActionListener actionStop = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			task.cancel(true);
			System.out.println("Pre-conditions analysis stopped by user!");
			progressBar.setValue(0);
			progressBar.setIndeterminate(false);
			buttonStop.setEnabled(false);
			buttonCheck.setEnabled(true);
			
		}

	};
	
	private ActionListener actionCheck = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			task = new PreConditionTask(parser, table.getModel());
			
			task.setCheckHierarchyCycle(checkHierarchyCycle.isSelected());
			task.setCheckGeneralization(checkValidSpecialization.isSelected());
			task.setCheckIdentity(checkValidIdentities.isSelected());
			task.setCheckAggregation(checkAggregationKind.isSelected());
			task.setCheckEnds(checkWellFormedPartWhole.isSelected());
			task.setCheckMeronymicCycle(checkPartWholeCycles.isSelected());
			
			task.addPropertyChangeListener(progressListener);
			
			table.getModel().clear();
			fixButton.setEnabled(false);
			
			task.execute();
		}

	};
	
	private ActionListener fixAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			int row = table.getSelectedRow();
			
			if(row==-1) 
				return;
			
			JDialog dialog = table.getModel().getRow(row).createDialog(PreConditionPanel.this.dialogParent);
			dialog.setAlwaysOnTop(true);
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.addWindowListener(exitListener);
			dialog.setVisible(true);
		}
	};
	
	WindowListener exitListener = new WindowAdapter() {

        @Override
        public void windowClosed(WindowEvent e) {
        	for (MeronymicError<?> error : table.getModel().getAllRows()) {
    			if(error.hasAction()){
    				saveButton.setEnabled(true);
    				applyButton.setEnabled(true);
    				table.getModel().fireTableDataChanged();
    				return;
    			}
    		}
        	
        	saveButton.setEnabled(false);
        	saveButton.setEnabled(false);
        }
    };
	
	PropertyChangeListener progressListener = new PropertyChangeListener() {			
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if(event.getPropertyName().compareTo("progress")==0){
				Integer value = (Integer) event.getNewValue();
				
				if(value<100){
					buttonCheck.setEnabled(false);
					buttonStop.setEnabled(true);
					progressBar.setIndeterminate(true);
				}
				else{
					buttonCheck.setEnabled(true);
					buttonStop.setEnabled(false);
					progressBar.setIndeterminate(false);
					if(checkAggregationKind.isSelected() && checkHierarchyCycle.isSelected() && checkPartWholeCycles.isSelected() 
							&& checkValidIdentities.isSelected() && checkValidSpecialization.isSelected() && checkWellFormedPartWhole.isSelected()
							&& table.getModel().getAllRows().size()==0){
						((JTabbedPane) getParent()).setEnabledAt(1,true);
					}
					else
						((JTabbedPane) getParent()).setEnabledAt(1,false);
				}
				progressBar.setValue(value);
			}
		}
	};

	@Override
	public ArrayList<MeronymicError<?>> getTableResults() {
		return table.getModel().getAllRows();
	}

	@Override
	public Fix runFixes() {
		Fix fix = new Fix();
		
		for (MeronymicError<?> error : table.getModel().getAllRows()) {
			fix.addAll(error.fix());
		}
		
		return fix;
	}

	@Override
	public void clearTable() {
		table.getModel().clear();
	}
}
