package br.ufes.inf.nemo.validator.meronymic.derivation.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class DirectPatternActionDialog extends FixDialog<DerivedMeronymic>{
	
	private static final long serialVersionUID = -5156909868657121690L;

	private DirectActionPanel actionPanel;
	
	
	public DirectPatternActionDialog(JDialog parent, DerivedMeronymic derived) {
		super(parent, derived, true);
		
		setTitle(derived.getPatternString());
		setMainTitle(derived.getPatternString());
		setSubtitle("");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
		);
		
		
		actionPanel = new DirectActionPanel(derived);
		actionPanel.addActionListenerToAllRadios(radioListener);
		scrollPane.setViewportView(actionPanel);
		
		contentPanel.setLayout(groupLayout);
		
		saveButton.addActionListener(saveListener);
		saveButton.setEnabled(false);
	}
	
	private ActionListener saveListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			actionPanel.saveChoices();
			DirectPatternActionDialog.this.dispose();
		}
	};
	
	private ActionListener radioListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(actionPanel.canSave())
				saveButton.setEnabled(true);
			else
				saveButton.setEnabled(false);
		}
	};
}
