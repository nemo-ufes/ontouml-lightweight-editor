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
import br.ufes.inf.nemo.validator.meronymic.forbidden.ForbiddenMemberOf;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class ForbiddenMemberOfDialog extends FixDialog<ForbiddenMemberOf>{
	
	private static final long serialVersionUID = -5302715398789924092L;
	private JTextField collectionField;
	private JTextField memberField;
	private JTextField stereotypeField;
	private JTextField nameField;
	private JTextPane pathText;
	private JRadioButton changeToSubCollectionOfRadio;
	private JRadioButton keepAsMemberOfRadio;
	private JRadioButton reverseRadio;
	private JRadioButton removeRadio;
	private JComboBox<String> reverseCombo;
	private JComboBox<String> removeCombo;
	private JRadioButton changeToComponentOfRadio;
	private JRadioButton changeToSubQuantityOfRadio;
	
	
	
	public ForbiddenMemberOfDialog(JDialog parent, ForbiddenMemberOf forbidden) {
		super(parent, forbidden, false);
		setTitle("Transitive MemberOf");
		setMainTitle("Transitive MemberOf");
		setSubtitle("");
//		contentPanel.setLayout(null);
		
		JTextPane introductionText = new JTextPane();
		introductionText.setText("The membership relation presented below characterizes an error.");
		introductionText.setBackground(UIManager.getColor("Button.background"));
		introductionText.setBounds(92, 34, 500, 20);
		contentPanel.add(introductionText);

		JLabel collectionLabel = new JLabel("Collection:");
		collectionLabel.setBounds(10, 93, 57, 14);
		contentPanel.add(collectionLabel);
		
		collectionField = new JTextField();
		collectionField.setBounds(86, 90, 86, 20);
		collectionField.setEditable(false);
		collectionField.setColumns(10);
		contentPanel.add(collectionField);
		
		JLabel memberLabel = new JLabel("Member:");
		memberLabel.setBounds(10, 116, 110, 20);
		contentPanel.add(memberLabel);
		
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
		
		keepAsMemberOfRadio = new JRadioButton("Keep as memberOf (change relations on the path to subCollectionOf, but the last)");
		keepAsMemberOfRadio.setBounds(10, 368, 173, 20);
		contentPanel.add(keepAsMemberOfRadio);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(10, 215, 57, 20);
		contentPanel.add(lblPath);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 147, 267, 50);
		contentPanel.add(scrollPane);
		
		changeToSubCollectionOfRadio = new JRadioButton("Change to subCollectionOf (change relations on the path to subCollectionOf)");
		changeToSubCollectionOfRadio.setBounds(10, 391, 653, 20);
		contentPanel.add(changeToSubCollectionOfRadio);
		
		changeToComponentOfRadio = new JRadioButton("Change to componentOf (change relations on the path to componentOf and types to functional complexes)");
		changeToComponentOfRadio.setBounds(10, 438, 586, 20);
		contentPanel.add(changeToComponentOfRadio);
		
		changeToSubQuantityOfRadio = new JRadioButton("Change to subQuantityOf (change relations on the path to subQuantityOf and types to quantities)");
		changeToSubQuantityOfRadio.setBounds(10, 414, 653, 20);
		contentPanel.add(changeToSubQuantityOfRadio);
		
		reverseRadio = new JRadioButton("Reverse relation:");
		reverseRadio.setBounds(10, 345, 110, 20);
		contentPanel.add(reverseRadio);
		
		ButtonGroup group = new ButtonGroup();
		group.add(removeRadio);
		group.add(reverseRadio);
		group.add(keepAsMemberOfRadio);
		group.add(changeToComponentOfRadio);
		group.add(changeToSubCollectionOfRadio);
		group.add(changeToSubQuantityOfRadio);
		
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(changeToSubQuantityOfRadio, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addComponent(changeToComponentOfRadio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addComponent(keepAsMemberOfRadio, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(removeRadio)
							.addGap(4)
							.addComponent(removeCombo, 0, 495, Short.MAX_VALUE))
						.addComponent(introductionText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPath, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(memberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(collectionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStereotype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
								.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
								.addComponent(stereotypeField, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
								.addComponent(collectionField, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
								.addComponent(memberField, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(reverseRadio)
							.addGap(2)
							.addComponent(reverseCombo, 0, 495, Short.MAX_VALUE))
						.addComponent(changeToSubCollectionOfRadio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addComponent(commandText, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
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
						.addComponent(collectionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(collectionLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(memberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(memberLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPath)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(commandText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeRadio)
						.addComponent(removeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(reverseRadio)
						.addComponent(reverseCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(keepAsMemberOfRadio)
					.addGap(5)
					.addComponent(changeToSubCollectionOfRadio)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(changeToComponentOfRadio)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(changeToSubQuantityOfRadio)
					.addGap(19))
		);
		
		contentPanel.setLayout(groupLayout);
		
		fillData();
		saveButton.addActionListener(saveListener);
	}
	
	private void fillData(){
		nameField.setText(item.getMeronymic().getName());
		stereotypeField.setText(OntoUMLNameHelper.getTypeName(item.getMeronymic(), true));
		collectionField.setText(OntoUMLNameHelper.getTypeAndName(item.getWhole(), true, true));
		memberField.setText(OntoUMLNameHelper.getTypeAndName(item.getPart(), true, true));
		pathText.setText(MeronymicItem.getFullPathText(item.getPath()));
		
		if(item.hasAction()){
			removeRadio.setSelected(item.isRemove());
			
			if(item.getRelationToRemove()!=null)
				removeCombo.setSelectedIndex(getIndexOf(item.getRelationToRemove(), true));
			
			reverseRadio.setSelected(item.isReverse());
			
			if(item.getRelationToReverse()!=null)
				reverseCombo.setSelectedIndex(getIndexOf(item.getRelationToReverse(), false));
			
			keepAsMemberOfRadio.setSelected(item.isMakeMemberOfValid());
			changeToComponentOfRadio.setSelected(item.isChangeAllToComponentOf());
			changeToSubQuantityOfRadio.setSelected(item.isChangeAllToSubQuantityOf());
			changeToSubCollectionOfRadio.setSelected(item.isChangeAllToSubCollectionOf());
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
	
	private ActionListener saveListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(removeRadio.isSelected()){
				item.setRemove(getSelectedMeronymic(removeCombo.getSelectedIndex(), true));
			}
			else if(reverseRadio.isSelected()){
				item.setReverse(getSelectedMeronymic(reverseCombo.getSelectedIndex(), false));
			}
			else if(keepAsMemberOfRadio.isSelected()){
				item.setMakeMemberOfValid();
			}
			else if(changeToComponentOfRadio.isSelected()){
				item.setChangeAllToComponentOf();
			}
			else if(changeToSubCollectionOfRadio.isSelected()){
				item.setChangeAllToSubCollectionOf();
			}
			else if(changeToSubQuantityOfRadio.isSelected()){
				item.setChangeAllToSubQuantityOf();
			}
			
			ForbiddenMemberOfDialog.this.dispose();
		}
	};
	
	private int getIndexOf(Meronymic m, boolean isRemove){
		if(m.equals(item.getMeronymic()))
			return 0;
		
		int index = item.getMeronymicPath().indexOf(m);

		if(isRemove)
			return index+1;
		return index;
	}
}
