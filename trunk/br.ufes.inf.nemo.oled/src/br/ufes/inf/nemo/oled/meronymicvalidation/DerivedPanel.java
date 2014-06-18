package br.ufes.inf.nemo.oled.meronymicvalidation;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivedMeronymic;
import br.ufes.inf.nemo.meronymic_validation.derivation.FunctionalParthoodDerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.MembershipDerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.SubQuantityDerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.DerivedTable;

public class DerivedPanel extends ValidationPanel<DerivedMeronymic> {

	private static final long serialVersionUID = 5989549633804270001L;
	
	private DerivedTable table;
	private JScrollPane scrollPane;
	private JCheckBox functionalCheck;
	private JCheckBox membershipCheck;
	private JCheckBox quantityCheck;
	private JButton buttonCheck;
	private JButton buttonStop;
	private JProgressBar progressBar;
	private JLabel labelResult;
	private JLabel labelIntroduction;
	private JButton buttonFix;
	
	private OntoUMLParser parser;
	private FunctionalParthoodDerivationTask functionalTask;
	private MembershipDerivationTask membershipTask;
	private SubQuantityDerivationTask quantityTask;
	
	/**
	 * Create the panel.
	 */
	public DerivedPanel(JDialog dialog, OntoUMLParser parser, JButton saveButton, JButton applyButton) {
		super(dialog,saveButton,applyButton);
		
		this.parser = parser;
		
		table = new DerivedTable();
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
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
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
		btnHelp1.setRolloverIcon(new ImageIcon(DerivedPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help-rollover.png")));
		btnHelp1.setIcon(new ImageIcon(DerivedPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		
		functionalCheck = new JCheckBox("Derive Functional Parthood");
		GridBagConstraints gbc_functionalCheck = new GridBagConstraints();
		gbc_functionalCheck.anchor = GridBagConstraints.WEST;
		gbc_functionalCheck.insets = new Insets(0, 0, 5, 5);
		gbc_functionalCheck.gridx = 1;
		gbc_functionalCheck.gridy = 0;
		panel.add(functionalCheck, gbc_functionalCheck);
		
		JButton button = new JButton("");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		panel.add(button, gbc_button);
		button.setIcon(new ImageIcon(DerivedPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		button.setPreferredSize(new Dimension(20, 20));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		membershipCheck = new JCheckBox("Derive Membership and SubCollections");
		GridBagConstraints gbc_membershipCheck = new GridBagConstraints();
		gbc_membershipCheck.anchor = GridBagConstraints.WEST;
		gbc_membershipCheck.insets = new Insets(0, 0, 5, 5);
		gbc_membershipCheck.gridx = 1;
		gbc_membershipCheck.gridy = 1;
		panel.add(membershipCheck, gbc_membershipCheck);
		
		JButton button_1 = new JButton("");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 2;
		panel.add(button_1, gbc_button_1);
		button_1.setIcon(new ImageIcon(DerivedPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		button_1.setPreferredSize(new Dimension(20, 20));
		button_1.setOpaque(false);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		
		quantityCheck = new JCheckBox("Derive SubQuantities");
		quantityCheck.setToolTipText("");
		GridBagConstraints gbc_quantityCheck = new GridBagConstraints();
		gbc_quantityCheck.anchor = GridBagConstraints.WEST;
		gbc_quantityCheck.insets = new Insets(0, 0, 0, 5);
		gbc_quantityCheck.gridx = 1;
		gbc_quantityCheck.gridy = 2;
		panel.add(quantityCheck, gbc_quantityCheck);
		
		buttonFix = new JButton("Fix");
		
		labelResult = new JLabel("The following part-whole relations can be inferred from your model:");
		labelResult.setHorizontalAlignment(SwingConstants.LEFT);
		
		labelIntroduction = new JLabel("Please select the derivations you would like to validate on your model:");
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
							.addComponent(buttonFix, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addGap(16)
							.addComponent(buttonCheck)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonStop))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelIntroduction, GroupLayout.PREFERRED_SIZE, 775, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(buttonFix)
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
		setLayout(groupLayout);
	}
	
	private ActionListener actionStop = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			functionalTask.cancel(true);
			System.out.println("Pre-conditions analysis stopped by user!");
			progressBar.setValue(0);
			buttonStop.setEnabled(false);
			buttonCheck.setEnabled(true);
			
		}

	};
	
	private ActionListener actionCheck = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			table.getModel().clear();
			
			if(functionalCheck.isSelected()){
				functionalTask = new FunctionalParthoodDerivationTask(parser, table.getModel());
				functionalTask.addPropertyChangeListener(progressListener);
				functionalTask.execute();
			}
			
			if(membershipCheck.isSelected()){
				membershipTask = new MembershipDerivationTask(parser, table.getModel());
				membershipTask.addPropertyChangeListener(progressListener);
				membershipTask.execute();
			}
			
			if(quantityCheck.isSelected()){
				quantityTask = new SubQuantityDerivationTask(parser, table.getModel());
				quantityTask.addPropertyChangeListener(progressListener);
				quantityTask.execute();
			}
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
				}
				progressBar.setValue(value);
			}
		}
	};

	@Override
	public ArrayList<DerivedMeronymic> getTableResults() {
		return table.getModel().getAllRows();
	}
	
	@Override
	public void clearTable() {
		table.getModel().clear();
	}

	@Override
	public Fix runFixes() {
		Fix fix = new Fix();
		
		for (DerivedMeronymic derived : table.getModel().getAllRows()) {
			fix.addAll(derived.fix());
		}
		
		return fix;
	}
}
