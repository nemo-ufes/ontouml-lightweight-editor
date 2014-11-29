package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.validator.meronymic.checkers.GeneralizationError;
import br.ufes.inf.nemo.validator.meronymic.ui.ClassStereotypeCombo;

public class GeneralizationDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane question;
	private JTextPane warning;
	private JTextField parentText;
	private JTextField childText;
	private ClassStereotypeCombo childCombo;
	private ClassStereotypeCombo parentCombo;
	private JRadioButton removeRadio;
	private JRadioButton reverseRadio;
	private JRadioButton stereotypeRadio;
	private JButton saveButton;
	private JButton cancelButton;
	
	private GeneralizationError error;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public GeneralizationDialog(JDialog parent, GeneralizationError error) {
		super(parent);
		this.error = error;
		
		setBounds(100, 100, 616, 514);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		warning = new JTextPane();
		warning.setText("If none of the options provided are suitable, please close this window and fix your model manually.");
		warning.setForeground(new Color(255, 102, 51));
		warning.setEditable(false);
		warning.setBackground(UIManager.getColor("Button.background"));
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
						.addComponent(warning, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 579, Short.MAX_VALUE))
					.addGap(9))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(0))
		);
		
		JLabel lblParentType = new JLabel("Parent Type:");
		
		parentText = new JTextField();
		parentText.setText(error.getParent().getName());
		parentText.setEditable(false);
		parentText.setColumns(10);
		parentText.setEnabled(false);
		
		JLabel lblChildType = new JLabel("Child Type:");
		
		childText = new JTextField();
		childText.setText(error.getChild().getName());
		childText.setEditable(false);
		childText.setColumns(10);
		childText.setEnabled(false);
		
		stereotypeRadio = new JRadioButton("Change stereotypes");
		stereotypeRadio.addActionListener(radioListener);

		reverseRadio = new JRadioButton("Reverse the generalization");
		reverseRadio.addActionListener(radioListener);
		
		removeRadio = new JRadioButton("Remove the generalization");
		removeRadio.addActionListener(radioListener);
		
		parentCombo = new ClassStereotypeCombo(error.getParent());
		childCombo = new ClassStereotypeCombo(error.getChild());
		
		ButtonGroup group = new ButtonGroup();
		group.add(stereotypeRadio);
		group.add(reverseRadio);
		group.add(removeRadio);
		
		setEnableToStereotypesComponents(false);
	
		JTextPane txtpnWarningChangingStereotypes = new JTextPane();
		txtpnWarningChangingStereotypes.setText("Warning: Changing stereotypes might introduce new errors in the model.");
		txtpnWarningChangingStereotypes.setForeground(new Color(255, 102, 51));
		txtpnWarningChangingStereotypes.setEditable(false);
		txtpnWarningChangingStereotypes.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(stereotypeRadio)
					.addGap(600))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(removeRadio, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
						.addComponent(reverseRadio, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
					.addGap(514))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnWarningChangingStereotypes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblParentType)
								.addComponent(lblChildType, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(childText)
								.addComponent(parentText, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(childCombo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(parentCombo, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))))
					.addGap(175))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(stereotypeRadio)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblParentType)
						.addComponent(parentText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(parentCombo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblChildType)
							.addComponent(childCombo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(childText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addComponent(reverseRadio)
					.addGap(6)
					.addComponent(removeRadio)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(txtpnWarningChangingStereotypes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {parentText, parentCombo});
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {childText, childCombo});
		panel.setLayout(gl_panel);
		
		
		String message ="In OntoUML imposes many constraints regarding how types can be specialized/generalized. " +
						"Theses rules consider different ontological properties, like identity and rigidity, and are intended to allow only the design of consistent models." +
						"\r\n\r\n" +
						"The following Generalization is invalid because the general type (the parent) cannot be specialized by the specif type (the child)." +
						"\r\n\r\n" +
						"To fix your model you can: change the types stereotypes, remove the generalization or revert it.";

		StyleContext context = new StyleContext();
		StyledDocument document = new DefaultStyledDocument(context);
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
		
		try {
			document.insertString(document.getLength(), message, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
				
		question = new JTextPane(document);
		question.setBackground(UIManager.getColor("menu"));
		question.setText(message);
		question.setEditable(false);
		scrollPane.setViewportView(question);
		
		
		contentPanel.setLayout(gl_contentPanel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.addActionListener(saveAction);
				saveButton.setEnabled(false);
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(cancelAction);
				buttonPane.add(cancelButton);
			}
		}
		
		JPanel titlePane = new JPanel();
		getContentPane().add(titlePane, BorderLayout.NORTH);
		
		JLabel title = new JLabel("Improper Generalization");
		
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 16));
		title.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_titlePane = new GroupLayout(titlePane);
		gl_titlePane.setHorizontalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_titlePane.setVerticalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		titlePane.setLayout(gl_titlePane);
	}
	
	private ActionListener saveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(reverseRadio.isSelected())
				error.setReverse();
			else if(removeRadio.isSelected())
				error.setRemove();
			else if(stereotypeRadio.isSelected()){
				if(childCombo.getSelectedIndex()!=-1 && !childCombo.getSelectedItem().equals(OutcomeFixer.getClassStereotype(error.getChild())))
					error.setChangeChildStereotype((ClassStereotype) childCombo.getSelectedItem());
				if(parentCombo.getSelectedIndex()!=-1 && !parentCombo.getSelectedItem().equals(OutcomeFixer.getClassStereotype(error.getParent())))
					error.setChangeParentStereotype((ClassStereotype) childCombo.getSelectedItem());
			}
			GeneralizationDialog.this.dispose();
			
		}
	};
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			GeneralizationDialog.this.dispose();
			
		}
	};
	
	private ActionListener radioListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(stereotypeRadio.isSelected() || removeRadio.isSelected() || reverseRadio.isSelected())
				saveButton.setEnabled(true);
			else
				saveButton.setEnabled(false);
			
			if(stereotypeRadio.isSelected()){
				setEnableToStereotypesComponents(true);
			}
			else{
				setEnableToStereotypesComponents(false);
			}
		}
	};

	private void setEnableToStereotypesComponents(boolean b){
		childCombo.setEnabled(b);
		parentCombo.setEnabled(b);
	}
	
	
	
}
