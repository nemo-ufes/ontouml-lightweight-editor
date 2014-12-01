package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.BinaryOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.CustomQuantification;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.CustomQuantificationType;

public class CustomQuantificationPanel extends JPanel {

	private static final long serialVersionUID = 7601319868493305715L;

	private JLabel contextLabel;
	private JComboBox<CustomQuantificationType> quantificationCombo;
	private JSpinner spinner;
	private JLabel nLabel;
	private JComboBox<BinaryOperator> operatorCombo;
	private JLabel lblOperator;

	public CustomQuantificationPanel() {
		contextLabel = new JLabel("Context:");
		
		quantificationCombo = new JComboBox<CustomQuantificationType>();
		quantificationCombo.setModel(new DefaultComboBoxModel<CustomQuantificationType>(CustomQuantificationType.values()));
		quantificationCombo.addActionListener(quantificationAction);
		nLabel = new JLabel("n =");
		spinner = new JSpinner();
		operatorCombo = new JComboBox<BinaryOperator>();
		operatorCombo.setModel(new DefaultComboBoxModel<BinaryOperator>(BinaryOperator.values()));
		lblOperator = new JLabel("Operator:");
		
		GroupLayout gl_quantificationPanel = new GroupLayout(this);
		gl_quantificationPanel.setHorizontalGroup(
			gl_quantificationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_quantificationPanel.createSequentialGroup()
					.addGroup(gl_quantificationPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOperator, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(contextLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_quantificationPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_quantificationPanel.createSequentialGroup()
							.addComponent(operatorCombo, 0, 244, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(nLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addComponent(quantificationCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_quantificationPanel.setVerticalGroup(
			gl_quantificationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_quantificationPanel.createSequentialGroup()
					.addGroup(gl_quantificationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(quantificationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(contextLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_quantificationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nLabel)
						.addComponent(lblOperator)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(239, Short.MAX_VALUE))
		);
		gl_quantificationPanel.linkSize(SwingConstants.VERTICAL, new Component[] {quantificationCombo, spinner, operatorCombo});
		setLayout(gl_quantificationPanel);
	}
	
	private ActionListener quantificationAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			enableOperator();
		}
	};
		
	private void enableOperator() {
		CustomQuantificationType cqt = ((CustomQuantificationType)quantificationCombo.getSelectedItem());
		
		boolean isComparison = CustomQuantificationType.COMPARISON==cqt;
		
		operatorCombo.setEnabled(isComparison);
		spinner.setEnabled(isComparison);
	}

	public ArrayList<JComboBox<?>> getAllCombos() {
		ArrayList<JComboBox<?>> list = new ArrayList<JComboBox<?>>();
		list.add(quantificationCombo);
		list.add(operatorCombo);
		return list;
	}

	public JSpinner getSpinner() {
		return spinner;
	}
	
	public void loadDefautUIData(){
		quantificationCombo.setSelectedItem(null);
		operatorCombo.setSelectedItem(null);
		spinner.setValue(1);
		spinner.setEnabled(false);
	}
	
	public void loadQuantificationUIData(CustomQuantification q){
		
		quantificationCombo.setSelectedItem(q.getType());
		
		if(q.isComparison()){
			operatorCombo.setSelectedItem(q.getOperator());
			spinner.setValue(q.getValue());
		}
	}
	
	public void saveQuantificationData(CustomQuantification q){
		
		CustomQuantificationType cqt = (CustomQuantificationType) quantificationCombo.getSelectedItem();
		
		if(cqt==null)
			return;
				
		switch (cqt) {
		case ALL:
			q.setAsAll();
			break;
		case COMPARISON:
			q.setAsComparison((BinaryOperator) operatorCombo.getSelectedItem(), (int) spinner.getValue());
			break;
		case NO:
			q.setAsNo();
			break;
		case ONE:
			q.setAsOne();
			break;
		case SOME:
			q.setAsSome();
			break;		
		}
		
	}
	
	public boolean canSave(){
		CustomQuantificationType cqt = (CustomQuantificationType) quantificationCombo.getSelectedItem();
		
		if(cqt==null)
			return false;
				
		switch (cqt) {
		case ALL:
		case NO:
		case ONE:
		case SOME:
			return true;
		case COMPARISON:
			return spinner.getValue()!=null;
		}
		
		return false;
	}

}
