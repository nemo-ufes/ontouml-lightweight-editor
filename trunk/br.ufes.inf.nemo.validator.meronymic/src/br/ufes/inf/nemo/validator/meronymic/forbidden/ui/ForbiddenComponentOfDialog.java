package br.ufes.inf.nemo.validator.meronymic.forbidden.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.validator.meronymic.MeronymicItem;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ForbiddenComponentOf;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class ForbiddenComponentOfDialog extends FixDialog<ForbiddenComponentOf>{
	
	private static final long serialVersionUID = -7154684747072122098L;
	
	private JTextField wholeField;
	private JTextField memberField;
	private JTextField stereotypeField;
	private JTextField nameField;
	private JTextPane pathText;
	private JRadioButton flattenRadio;
	private JRadioButton reverseRadio;
	private JRadioButton removeRadio;
	private JComboBox<String> reverseCombo;
	private JComboBox<String> removeCombo;
	
	
	
	public ForbiddenComponentOfDialog(JDialog parent, ForbiddenComponentOf forbidden) {
		super(parent, forbidden, false);
		setTitle("Improper Functional Dependence");
		setMainTitle("Improper Functional Dependence");
		setSubtitle("");

		
		JTextPane introductionText = new JTextPane();
		introductionText.setText("The functional parthood presented below characterizes an error.");
		introductionText.setBackground(UIManager.getColor("Button.background"));
		introductionText.setBounds(92, 34, 500, 20);
		contentPanel.add(introductionText);

		JLabel wholeLabel = new JLabel("Whole:");
		wholeLabel.setBounds(10, 93, 57, 14);
		contentPanel.add(wholeLabel);
		
		wholeField = new JTextField();
		wholeField.setBounds(86, 90, 86, 20);
		wholeField.setEditable(false);
		wholeField.setColumns(10);
		contentPanel.add(wholeField);
		
		JLabel partLabel = new JLabel("Part:");
		partLabel.setBounds(10, 116, 110, 20);
		contentPanel.add(partLabel);
		
		memberField = new JTextField();
		memberField.setBounds(96, 116, 57, 20);
		memberField.setEditable(false);
		memberField.setColumns(10);
		contentPanel.add(memberField);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(111, 208, 143, 35);
		contentPanel.add(lblName);
		
		stereotypeField = new JTextField();
		stereotypeField.setBounds(197, 245, 143, 20);
		stereotypeField.setEditable(false);
		stereotypeField.setColumns(10);
		contentPanel.add(stereotypeField);
		
		JLabel lblStereotype = new JLabel("Stereotype:");
		lblStereotype.setBounds(10, 161, 99, 20);
		contentPanel.add(lblStereotype);
		
		nameField = new JTextField();
		nameField.setBounds(96, 64, 99, 20);
		nameField.setEditable(false);
		nameField.setColumns(10);
		contentPanel.add(nameField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 276, 653, 8);
		contentPanel.add(separator);
		
		JTextPane commandText = new JTextPane();
		commandText.setBounds(10, 288, 653, 20);
		commandText.setText("Please choose one of the following options to fix your model:");
		commandText.setBackground(UIManager.getColor("Button.background"));
		contentPanel.add(commandText);
		
		removeRadio = new JRadioButton("Remove relation:");
		removeRadio.setSelected(true);
		removeRadio.setBounds(10, 322, 110, 20);
		contentPanel.add(removeRadio);
		
		flattenRadio = new JRadioButton("Flatten parthood path");
		flattenRadio.setBounds(10, 368, 173, 20);
		contentPanel.add(flattenRadio);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(10, 215, 57, 20);
		contentPanel.add(lblPath);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 147, 267, 50);
		contentPanel.add(scrollPane);
		
		reverseRadio = new JRadioButton("Reverse relation:");
		reverseRadio.setBounds(10, 345, 110, 20);
		contentPanel.add(reverseRadio);
		
		ButtonGroup group = new ButtonGroup();
		group.add(removeRadio);
		group.add(reverseRadio);
		group.add(flattenRadio);
		
		removeCombo = new JComboBox<String>();
		removeCombo.setBounds(411, 184, 143, 20);
		removeCombo.setModel(createRelationComboModel(true));
		removeCombo.setSelectedIndex(0);
		contentPanel.add(removeCombo);
		
		reverseCombo = new JComboBox<String>();
		reverseCombo.setBounds(264, 215, 163, 20);
		reverseCombo.setModel(createRelationComboModel(false));
		reverseCombo.setSelectedIndex(0);
		contentPanel.add(reverseCombo);
		
		pathText = new JTextPane();
		pathText.setMinimumSize(new Dimension(300, 200));
		scrollPane.setViewportView(pathText);
		pathText.setEditable(false);
		
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(flattenRadio, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addComponent(introductionText, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPath, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(partLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(wholeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStereotype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
								.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
								.addComponent(stereotypeField, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
								.addComponent(wholeField, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
								.addComponent(memberField, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addComponent(commandText, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(reverseRadio, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(removeRadio, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(removeCombo, 0, 426, Short.MAX_VALUE)
								.addComponent(reverseCombo, Alignment.LEADING, 0, 426, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(introductionText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStereotype)
						.addComponent(stereotypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(wholeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(wholeLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(memberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(partLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPath)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(commandText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeRadio)
						.addComponent(removeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(reverseCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(reverseRadio))
					.addGap(7)
					.addComponent(flattenRadio)
					.addGap(18))
		);
		
		contentPanel.setLayout(groupLayout);
		
		fillData();
		saveButton.addActionListener(saveListener);
	}
	
	private void fillData(){
		nameField.setText(item.getMeronymic().getName());
		stereotypeField.setText(OntoUMLNameHelper.getTypeName(item.getMeronymic(), true));
		wholeField.setText(OntoUMLNameHelper.getTypeAndName(item.getWhole(), true, true));
		memberField.setText(OntoUMLNameHelper.getTypeAndName(item.getPart(), true, true));
		pathText.setText(MeronymicItem.getFullPathText(item.getPath()));
		
		if(item.hasAction()){
			removeRadio.setSelected(item.isRemove());
			reverseRadio.setSelected(item.isReverse());
			flattenRadio.setSelected(item.isFlatten());
			
			if(removeRadio.isSelected())
				removeCombo.setSelectedIndex(getIndexOf(item.getRelationToRemove(), true));
			if(reverseRadio.isSelected())
				reverseCombo.setSelectedIndex(getIndexOf(item.getRelationToReverse(), false));
		}
	}
	
	private DefaultComboBoxModel<String> createRelationComboModel(boolean isRemove){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		if(isRemove)
			model.addElement(OntoUMLNameHelper.getCompleteName(item.getMeronymic()));

		for (Meronymic m : item.getMeronymicPath()) {
			model.addElement(OntoUMLNameHelper.getCompleteName(m));
		}
		
		return model;
	}
	
	private Meronymic getSelectedMeronymic(int index, boolean isRemove){
		if(isRemove){
			if(index==0)
				return item.getMeronymic();
			
			return item.getMeronymicPath().get(index-1);
		}
		
		return item.getMeronymicPath().get(index);
	}
	
	private int getIndexOf(Meronymic m, boolean isRemove){
		if(m.equals(item.getMeronymic()))
			return 0;
		
		int index = item.getMeronymicPath().indexOf(m);

		if(isRemove)
			return index+1;
		return index;
	}
	
	private ActionListener saveListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(removeRadio.isSelected()){
				item.setRemove(getSelectedMeronymic(removeCombo.getSelectedIndex(), true));
			}
			else if(reverseRadio.isSelected()){
				item.setReverse(getSelectedMeronymic(reverseCombo.getSelectedIndex(), false));
			}
			else if(flattenRadio.isSelected()){
				item.setFlatten();
			}
			
			ForbiddenComponentOfDialog.this.dispose();
		}
	};
}
