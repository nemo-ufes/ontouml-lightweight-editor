package br.ufes.inf.nemo.meronymic_validation.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.DerivedResultPanel;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.FunctionalParthoodResultPanel;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.MembershipResultPanel;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.SubQuantityResultPanel;

public class ValidationDialog extends JDialog {
	
	private static final long serialVersionUID = -5685846942258192440L;

	private OntoUMLParser parser;
	
	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;
	private JButton deriveButton;
	private JButton saveButton;
	private JButton cancelButton;
	private JTabbedPane tabbedPane;
	
	private DerivedResultPanel[] resultPanels = new DerivedResultPanel[3];

	/**
	 * Create the dialog.
	 */
	public ValidationDialog(OntoUMLParser parser) {
		setTitle("Meronymic Validation");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		this.setParser(parser);
		setBounds(100, 100, 818, 684);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
		);
		
		resultPanels[0] = new FunctionalParthoodResultPanel(parser);
		tabbedPane.addTab("Functional Parthood", null, resultPanels[0], null);
				
		resultPanels[1] = new MembershipResultPanel(parser);
		tabbedPane.addTab("Collections & Members", null, resultPanels[1], null);
		
		resultPanels[2] = new SubQuantityResultPanel(parser);
		tabbedPane.addTab("Quantities & SubQuantities", null, resultPanels[2], null);
		
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");

		saveButton = new JButton("Save");
		saveButton.setActionCommand("OK");
		saveButton.setEnabled(false);
		getRootPane().setDefaultButton(saveButton);
	
		deriveButton = new JButton("Derive");
		deriveButton.addActionListener(deriveAction);		
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(deriveButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(saveButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cancelButton)
					.addContainerGap())
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(saveButton)
							.addComponent(deriveButton)))
					.addContainerGap())
		);
		buttonPane.setLayout(gl_buttonPane);
		
	}
	
	public OntoUMLParser getParser() {
		return parser;
	}

	private void setParser(OntoUMLParser parser) {
		this.parser = parser;
	}

	PropertyChangeListener progressListener = new PropertyChangeListener() {			
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if(event.getPropertyName().compareTo("progress")==0){
				Integer value = (Integer) event.getNewValue();
				
				if(value<100){
					deriveButton.setEnabled(false);
					
					progressBar.setIndeterminate(true);
				}
				else{
					deriveButton.setEnabled(true);
					progressBar.setIndeterminate(false);
					
					for (DerivedResultPanel resultPane : resultPanels) {
						if(!resultPane.isTableEmpty())
							saveButton.setEnabled(true);
					}
					
				}
				progressBar.setValue(value);
			}
		}
	};
	
	ActionListener deriveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			DerivedResultPanel resultPanel = resultPanels[tabbedPane.getSelectedIndex()];
			
			resultPanel.setTask();
			resultPanel.getTask().addPropertyChangeListener(progressListener);
			resultPanel.executeTask();
			
		}
	};
}
