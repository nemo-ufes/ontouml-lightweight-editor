package br.ufes.inf.nemo.validator.meronymic.derivation.ui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class DirectPatternPropertyDialog extends FixDialog<DerivedMeronymic>{
	
	private static final long serialVersionUID = 9121771568558051493L;
	
	private JPanel meronymicPanel;

	public DirectPatternPropertyDialog(JDialog parent, DerivedMeronymic derived) {
		super(parent, derived, true);
		setTitle(derived.getPatternString());
		setMainTitle(derived.getPatternString());
		setSubtitle("");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		JTextPane txtpnTheFollowingRelation = new JTextPane();
		txtpnTheFollowingRelation.setText("The following relation was inferred from your model:");
		
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnTheFollowingRelation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtpnTheFollowingRelation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		meronymicPanel = new DerivedMeronymicPanel(derived);
		scrollPane.setViewportView(meronymicPanel);
		
		contentPanel.setLayout(groupLayout);
		
		saveButton.setEnabled(false);
		backButton.setEnabled(false);
	}
	
}
