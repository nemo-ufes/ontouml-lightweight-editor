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
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ForbiddenComponentOfTask;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ForbiddenMemberOfTask;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ForbiddenMeronymic;
import br.ufes.inf.nemo.meronymic_validation.userinterface.ForbiddenTable;

public class ForbiddenPanel extends ValidationPanel<ForbiddenMeronymic<?>> {

	private static final long serialVersionUID = 5989549633804270001L;
	
	private ForbiddenTable table;
	private JScrollPane scrollPane;
	private JCheckBox checkMemberOf;
	private JCheckBox checkComponentOf;
	private JButton buttonCheck;
	private JButton buttonStop;
	private JProgressBar progressBar;
	private JLabel labelResult;
	private JLabel labelIntroduction;
	private JButton buttonFix;
	
	private OntoUMLParser parser;
	private ForbiddenMemberOfTask memberOfTask;
	private ForbiddenComponentOfTask componentOfTask;
	
	/**
	 * Create the panel.
	 */
	public ForbiddenPanel(JDialog dialog, OntoUMLParser parser, JButton saveButton, JButton applyButton) {
		super(dialog,saveButton,applyButton);
		this.parser = parser;
		
		table = new ForbiddenTable();
		scrollPane = new JScrollPane();
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
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
		gbl_panel.rowWeights = new double[]{0.0, 0.0};
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
		btnHelp1.setRolloverIcon(new ImageIcon(ForbiddenPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help-rollover.png")));
		btnHelp1.setIcon(new ImageIcon(ForbiddenPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		
		checkMemberOf = new JCheckBox("Forbidden MemberOf");
		GridBagConstraints gbc_checkHierarchyCycle = new GridBagConstraints();
		gbc_checkHierarchyCycle.anchor = GridBagConstraints.WEST;
		gbc_checkHierarchyCycle.insets = new Insets(0, 0, 5, 5);
		gbc_checkHierarchyCycle.gridx = 1;
		gbc_checkHierarchyCycle.gridy = 0;
		panel.add(checkMemberOf, gbc_checkHierarchyCycle);
		
		JButton button = new JButton("");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		panel.add(button, gbc_button);
		button.setIcon(new ImageIcon(ForbiddenPanel.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		button.setPreferredSize(new Dimension(20, 20));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		checkComponentOf = new JCheckBox("Forbidden ComponentOf");
		GridBagConstraints gbc_checkValidSpecialization = new GridBagConstraints();
		gbc_checkValidSpecialization.anchor = GridBagConstraints.WEST;
		gbc_checkValidSpecialization.insets = new Insets(0, 0, 5, 5);
		gbc_checkValidSpecialization.gridx = 1;
		gbc_checkValidSpecialization.gridy = 1;
		panel.add(checkComponentOf, gbc_checkValidSpecialization);
		
		buttonFix = new JButton("Fix");
		
		labelResult = new JLabel("The following part-whole relations characterize errors:");
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
			
			if(componentOfTask!=null)
				componentOfTask.cancel(true);
			
			if(memberOfTask!=null)
				memberOfTask.cancel(true);
			
			System.out.println("Forbidden parthood analysis stopped by user!");
			
			progressBar.setValue(0);
			progressBar.setIndeterminate(false);
			buttonStop.setEnabled(false);
			buttonCheck.setEnabled(true);
			
		}

	};

	
	
	private ActionListener actionCheck = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			table.getModel().clear();
			
			if(checkMemberOf.isSelected()){
				
				memberOfTask = new ForbiddenMemberOfTask(parser, table.getModel(), (JTabbedPane) ForbiddenPanel.this.getParent());
				memberOfTask.addPropertyChangeListener(progressListener);
				memberOfTask.execute();
			}
			
			if(checkComponentOf.isSelected()){
				componentOfTask = new ForbiddenComponentOfTask(parser, table.getModel(), (JTabbedPane) ForbiddenPanel.this.getParent());
				componentOfTask.addPropertyChangeListener(progressListener);
				componentOfTask.execute();
			}
			
		}

	};
	
	PropertyChangeListener progressListener = new PropertyChangeListener() {			
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if(event.getPropertyName().compareTo("progress")==0){
				
				Integer value = (Integer) event.getNewValue();
				
				if((value)<100){
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
	
	public int getNumberOfSelected(){
		int result = 0;
		
		if(checkComponentOf.isSelected())
			result++;
		if(checkMemberOf.isSelected())
			result++;
		
		return result;
	}
	
	@Override
	public Fix runFixes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clearTable() {
		table.getModel().clear();
	}

	@Override
	public ArrayList<ForbiddenMeronymic<?>> getTableResults() {
		// TODO Auto-generated method stub
		return null;
	}
}
