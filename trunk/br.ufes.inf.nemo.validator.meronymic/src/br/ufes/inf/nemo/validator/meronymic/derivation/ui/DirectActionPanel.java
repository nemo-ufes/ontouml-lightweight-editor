package br.ufes.inf.nemo.validator.meronymic.derivation.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic;

public class DirectActionPanel extends JPanel {
	
	private static final long serialVersionUID = -2765412296419928549L;
	
	private DerivedMeronymic derived;
	private JRadioButton incorrectRadio;
	private JRadioButton correctRadio;
	private JRadioButton persistRadio;
	private JRadioButton dontPersistRadio;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JPanel fourthPanel;
	private JPanel firstPanel;
	private JRadioButton removeRadio;
	private JRadioButton memberOfPathRadio;
	private JRadioButton changeStereotypeRadio;
	private JRadioButton reverseRadio;
	private JComboBox<String> removeCombo;
	private JComboBox<String> reverseCombo;
	private JComboBox<RelationStereotype> changeStereotypeCombo;
	private JRadioButton inferFunctionalRadio;
	private JRadioButton inferSubQuantityRadio;
	private JRadioButton inferSubCollectionRadio;
	private JRadioButton inferMembershipRadio;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JRadioButton alwaysDisable;
	private JLabel secondQuestionLabel;
	private JLabel thirdQuestionLabel;
	private JLabel fourthQuestionLabel;
	
	/**
	 * Create the panel.
	 */
	public DirectActionPanel(DerivedMeronymic derived) {
		this.setDerived(derived);
		
		secondPanel = new JPanel();
		
		thirdPanel = new JPanel();
		
		memberOfPathRadio = new JRadioButton("Change relations in the path to memberOf  and the types to collections");
		
		removeRadio = new JRadioButton("Remove relation in the derivation path:");
		
		thirdQuestionLabel = new JLabel("In order for NO inference to be made, the following actions may be taken:");
		
		removeCombo = new JComboBox<String>();
		removeCombo.setModel(createRelationComboModel());
		
		reverseRadio = new JRadioButton("Reverse relation in the derivation path:");
		
		reverseCombo = new JComboBox<String>();
		reverseCombo.setModel(createRelationComboModel());
		
		changeStereotypeRadio = new JRadioButton("Change to non part-whole stereotype:");
		
		changeStereotypeCombo = new JComboBox<RelationStereotype>();
		changeStereotypeCombo.setModel(createStereotypeComboModel());
		
		changeRelationCombo = new JComboBox<String>();
		changeRelationCombo.setModel(createRelationComboModel());
		
		GroupLayout gl_thirdPanel = new GroupLayout(thirdPanel);
		gl_thirdPanel.setHorizontalGroup(
			gl_thirdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thirdPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(memberOfPathRadio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
						.addGroup(gl_thirdPanel.createSequentialGroup()
							.addGroup(gl_thirdPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(removeRadio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(reverseRadio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(changeStereotypeRadio, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_thirdPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_thirdPanel.createSequentialGroup()
									.addComponent(changeStereotypeCombo, 0, 125, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(changeRelationCombo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addComponent(removeCombo, 0, 254, Short.MAX_VALUE)
								.addComponent(reverseCombo, 0, 254, Short.MAX_VALUE))
							.addGap(10))
						.addComponent(thirdQuestionLabel, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_thirdPanel.setVerticalGroup(
			gl_thirdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thirdPanel.createSequentialGroup()
					.addComponent(thirdQuestionLabel)
					.addGap(7)
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeRadio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(reverseRadio)
						.addComponent(reverseCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeStereotypeRadio)
						.addComponent(changeStereotypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(changeRelationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(memberOfPathRadio)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_thirdPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {removeRadio, reverseRadio, changeStereotypeRadio});
		thirdPanel.setLayout(gl_thirdPanel);
		
		fourthPanel = new JPanel();
		
		firstPanel = new JPanel();
		
		separator = new JSeparator();
		
		separator_1 = new JSeparator();
		
		separator_2 = new JSeparator();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
								.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
							.addGap(15))
						.addComponent(fourthPanel, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
						.addComponent(thirdPanel, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
						.addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
						.addComponent(firstPanel, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
					.addGap(3))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(firstPanel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(secondPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(thirdPanel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fourthPanel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		correctRadio = new JRadioButton("Yes");
		correctRadio.addActionListener(correctAction);
		
		incorrectRadio = new JRadioButton("No");
		incorrectRadio.addActionListener(incorrectAction);
		
		JLabel lblIsTheRelation = new JLabel("Is the relation correctly inferred?");
		
		
		GroupLayout gl_firstPanel = new GroupLayout(firstPanel);
		gl_firstPanel.setHorizontalGroup(
			gl_firstPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_firstPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_firstPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(incorrectRadio)
						.addComponent(correctRadio)
						.addComponent(lblIsTheRelation, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(299, Short.MAX_VALUE))
		);
		gl_firstPanel.setVerticalGroup(
			gl_firstPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_firstPanel.createSequentialGroup()
					.addComponent(lblIsTheRelation)
					.addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
					.addComponent(correctRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(incorrectRadio)
					.addContainerGap())
		);
		firstPanel.setLayout(gl_firstPanel);
		
		fourthQuestionLabel = new JLabel("In order for OTHER type of inference to be made, the possible actions are:");
		
		inferMembershipRadio = new JRadioButton("Infer membership relation (memberOf)");
		if(derived.isMembership()){
			alwaysDisable = inferMembershipRadio;
			inferMembershipRadio.setEnabled(false);
		}
		
		inferSubCollectionRadio = new JRadioButton("Infer subcollection relation (subCollectionOf)");
		if(derived.isSubCollection()){
			alwaysDisable = inferSubCollectionRadio;
			inferSubCollectionRadio.setEnabled(false);
		}
		
		inferSubQuantityRadio = new JRadioButton("Infer subquantity relation (subQuantityOf)");
		if(derived.isSubQuantity()){
			alwaysDisable = inferSubQuantityRadio;
			inferSubQuantityRadio.setEnabled(false);
		}
		
		inferFunctionalRadio = new JRadioButton("Infer functional parthood relation (componentOf)");
		if(derived.isFunctional()){
			alwaysDisable = inferFunctionalRadio;
			inferFunctionalRadio.setEnabled(false);
		}
		
		GroupLayout gl_fourthPanel = new GroupLayout(fourthPanel);
		gl_fourthPanel.setHorizontalGroup(
			gl_fourthPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_fourthPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(fourthQuestionLabel, GroupLayout.PREFERRED_SIZE, 472, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_fourthPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fourthPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(inferFunctionalRadio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addComponent(inferSubQuantityRadio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addComponent(inferSubCollectionRadio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addComponent(inferMembershipRadio, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_fourthPanel.setVerticalGroup(
			gl_fourthPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_fourthPanel.createSequentialGroup()
					.addComponent(fourthQuestionLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
					.addComponent(inferMembershipRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inferSubCollectionRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inferSubQuantityRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inferFunctionalRadio)
					.addContainerGap())
		);
		fourthPanel.setLayout(gl_fourthPanel);
		
		dontPersistRadio = new JRadioButton("No");
		
		persistRadio = new JRadioButton("Yes");
		
		secondQuestionLabel = new JLabel("Would you like to persist the derived relation in your model?");
	
		GroupLayout gl_secondPanel = new GroupLayout(secondPanel);
		gl_secondPanel.setHorizontalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addGroup(gl_secondPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_secondPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(secondQuestionLabel, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
						.addGroup(gl_secondPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(persistRadio))
						.addGroup(gl_secondPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(dontPersistRadio, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_secondPanel.setVerticalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addComponent(secondQuestionLabel)
					.addGap(7)
					.addComponent(persistRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dontPersistRadio)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		secondPanel.setLayout(gl_secondPanel);
		setLayout(groupLayout);
		
		setEnabledToSecondPanel(false);
		setEnabledToThirdPanel(false);
		setEnabledToFourthPanel(false);
		
		ButtonGroup firstGroup = new ButtonGroup();
		firstGroup.add(correctRadio);
		firstGroup.add(incorrectRadio);
		
		ButtonGroup correctGroup = new ButtonGroup();
		correctGroup.add(persistRadio);
		correctGroup.add(dontPersistRadio);
		
		ButtonGroup incorrectGroup = new ButtonGroup();
		incorrectGroup.add(removeRadio);
		incorrectGroup.add(reverseRadio);
		incorrectGroup.add(changeStereotypeRadio);
		incorrectGroup.add(memberOfPathRadio);
		incorrectGroup.add(inferFunctionalRadio);
		incorrectGroup.add(inferMembershipRadio);
		incorrectGroup.add(inferSubCollectionRadio);
		incorrectGroup.add(inferSubQuantityRadio);
	}
	
	private DefaultComboBoxModel<String> createRelationComboModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for (Property p : derived.getPath()) {
			model.addElement(OntoUMLNameHelper.getCompleteName(p.getAssociation()));
		}
		
		return model;
	}
	
	private DefaultComboBoxModel<RelationStereotype> createStereotypeComboModel(){
		DefaultComboBoxModel<RelationStereotype> model = new DefaultComboBoxModel<RelationStereotype>();
		
		model.addElement(RelationStereotype.FORMAL);
		model.addElement(RelationStereotype.MATERIAL);
		
		return model;
	}
	
	public void setEnabledToSecondPanel(boolean b){
		secondQuestionLabel.setEnabled(b);
		persistRadio.setEnabled(b);
		dontPersistRadio.setEnabled(b);
	}
	
	public void setEnabledToThirdPanel(boolean b){
		thirdQuestionLabel.setEnabled(b);
		
		removeRadio.setEnabled(b);
		removeCombo.setEnabled(b);
		reverseRadio.setEnabled(b);
		reverseCombo.setEnabled(b);
		changeStereotypeRadio.setEnabled(b);
		changeStereotypeCombo.setEnabled(b);
		changeRelationCombo.setEnabled(b);
		memberOfPathRadio.setEnabled(b);
	}
	
	public void setEnabledToFourthPanel(boolean b){
		fourthQuestionLabel.setEnabled(b);
		
		if(!inferFunctionalRadio.equals(alwaysDisable))
			inferFunctionalRadio.setEnabled(b);
		if(!inferMembershipRadio.equals(alwaysDisable))
			inferMembershipRadio.setEnabled(b);
		if(!inferSubCollectionRadio.equals(alwaysDisable))
			inferSubCollectionRadio.setEnabled(b);
		if(!inferSubQuantityRadio.equals(alwaysDisable))
			inferSubQuantityRadio.setEnabled(b);
	}
	
	public DerivedMeronymic getDerived() {
		return derived;
	}

	public void setDerived(DerivedMeronymic derived) {
		this.derived = derived;
	}

	private ActionListener correctAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setEnabledToSecondPanel(true);
			setEnabledToThirdPanel(false);
			setEnabledToFourthPanel(false);
			
		}
	};
	
	private ActionListener incorrectAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setEnabledToSecondPanel(false);
			setEnabledToThirdPanel(true);
			setEnabledToFourthPanel(true);
			
		}
	};
	private JComboBox<String> changeRelationCombo;
	
	public void saveChoices(){
	
		if(correctRadio.isSelected()){
			if(persistRadio.isSelected())
				derived.setPersist();
			else
				derived.setDontPersist();
		}
		else if (incorrectRadio.isSelected()){
			if(removeRadio.isSelected())
				derived.setRemove(getRelation(removeCombo.getSelectedIndex()));
			else if(reverseRadio.isSelected())
				derived.setReverse(getRelation(reverseCombo.getSelectedIndex()));
			else if(changeStereotypeRadio.isSelected())
				derived.setChangeStereotype(getRelation(changeRelationCombo.getSelectedIndex()),(RelationStereotype) changeStereotypeCombo.getSelectedItem());
			else if(memberOfPathRadio.isSelected())
				derived.setMemberOfPath();
			else if(inferFunctionalRadio.isSelected())
				derived.setInferFunctional();
			else if(inferMembershipRadio.isSelected())
				derived.setInferMembership();
			else if(inferSubCollectionRadio.isSelected())
				derived.setInferSubCollection();
			else if(inferSubQuantityRadio.isSelected())
				derived.setInferSubQuantity();
		}
		
	}
	
	public boolean canSave(){
		if(correctRadio.isSelected() && (persistRadio.isSelected() || dontPersistRadio.isSelected()))
			return true;
		
		if (incorrectRadio.isSelected() && 
				(removeRadio.isSelected() || reverseRadio.isSelected() || changeStereotypeRadio.isSelected() ||
				memberOfPathRadio.isSelected() || inferFunctionalRadio.isSelected() || inferMembershipRadio.isSelected() ||
				inferSubCollectionRadio.isSelected() || inferSubQuantityRadio.isSelected() ))
			return true;
		
		return false;
	}

	private Meronymic getRelation(int selectedIndex) {
		return (Meronymic) derived.getPath().get(selectedIndex).getAssociation();
	}
	
	public void addActionListenerToAllRadios(ActionListener b){
		persistRadio.addActionListener(b);
		dontPersistRadio.addActionListener(b);
		
		removeRadio.addActionListener(b);
		removeCombo.addActionListener(b);
		reverseRadio.addActionListener(b);
		reverseCombo.addActionListener(b);
		changeStereotypeRadio.addActionListener(b);
		changeStereotypeCombo.addActionListener(b);
		changeRelationCombo.addActionListener(b);
		memberOfPathRadio.addActionListener(b);
		
		inferFunctionalRadio.addActionListener(b);
		inferMembershipRadio.addActionListener(b);
		inferSubCollectionRadio.addActionListener(b);
		inferSubQuantityRadio.addActionListener(b);
	}
}
