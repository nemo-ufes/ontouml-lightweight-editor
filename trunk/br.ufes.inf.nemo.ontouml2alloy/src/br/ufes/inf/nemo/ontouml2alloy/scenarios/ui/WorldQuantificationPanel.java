package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.QuantifiedScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.WorldQuantification;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.WorldQuantificationType;

public class WorldQuantificationPanel extends JPanel {

	private static final long serialVersionUID = 7601319868493305715L;

	private JLabel contextLabel;
	private JComboBox<WorldQuantificationType> combo;
	private JSpinner spinner;
	private JLabel nLabel;

	public WorldQuantificationPanel() {
		contextLabel = new JLabel("Context:");
		
		combo = new JComboBox<WorldQuantificationType>();
		combo.setModel(new DefaultComboBoxModel<WorldQuantificationType>(WorldQuantificationType.values()));
		combo.addActionListener(quantificationAction);
		
		
		nLabel = new JLabel("n =");
		
		spinner = new JSpinner();
		
		GroupLayout gl_quantificationPanel = new GroupLayout(this);
		gl_quantificationPanel.setHorizontalGroup(
			gl_quantificationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_quantificationPanel.createSequentialGroup()
					.addComponent(contextLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(combo, 0, 175, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
		);
		gl_quantificationPanel.setVerticalGroup(
			gl_quantificationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_quantificationPanel.createSequentialGroup()
					.addGroup(gl_quantificationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(nLabel)
						.addComponent(contextLabel)
						.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(gl_quantificationPanel);
	}
	
	private ActionListener quantificationAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			enableSpinner();
		}
	};
	
	private void enableSpinner() {
		boolean requiresNumber = false;
		
		WorldQuantificationType wqt = ((WorldQuantificationType)combo.getSelectedItem());
		
		if(wqt instanceof WorldQuantificationType)
			requiresNumber = wqt.isNumeric();
		
		spinner.setEnabled(requiresNumber);
	}

	public JComboBox<?> getCombo() {
		return combo;
	}

	public JSpinner getSpinner() {
		return spinner;
	}
	
	public void loadDefautUIData(){
		combo.setSelectedItem(null);
		spinner.setValue(1);
		spinner.setEnabled(false);
	}
	
	public void loadScenarioUIData(QuantifiedScenario scenario){
		
		WorldQuantification wq = scenario.getWorldQuantification();
		combo.setSelectedItem(wq.getType());
		
		if(wq.isNumeric())
			spinner.setValue(wq.getValue());
		
		enableSpinner();	
	}
	
	public void saveQuantificationData(QuantifiedScenario scenario){
		
		WorldQuantification wq = scenario.getWorldQuantification();
		wq.setType((WorldQuantificationType) combo.getSelectedItem());
		
		if(wq.isNumeric())
			wq.setValue((int) spinner.getValue());	
		
	}
	
	public boolean canSave(){
		WorldQuantificationType wqt = (WorldQuantificationType) combo.getSelectedItem();
		
		if(wqt==null)
			return false;
				
		switch (wqt) {
		case EVERY:
		case NO:
		case SOME:
		case STORY:
			return true;
		case EXACTLY:
		case ATLEAST:
		case ATMOST:
			return spinner.getValue()!=null;
		}
		
		return false;
	}

}
