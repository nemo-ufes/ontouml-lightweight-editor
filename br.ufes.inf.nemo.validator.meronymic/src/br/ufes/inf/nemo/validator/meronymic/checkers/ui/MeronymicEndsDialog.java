package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.checkers.MeronymicEndsError;

public class MeronymicEndsDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane question;
	private JTextPane warning;
	private JButton saveButton;
	private JButton cancelButton;
	private JPanel panel;
	private JLabel lblName;
	private JTextField wholeStereotypeField;
	private JTextField wholeNatureField;
	private JTextField wholeNameField;
	private JTextField partStereotypeField;
	private JTextField partNatureField;
	private JTextField partNameField;
	private JPanel panel_2;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField nameField;
	private JTextField stereotypeField;
	private JCheckBox changeStereotypeCheckbox;
	private JCheckBox wholeIsFunctionalCheckbox;
	private JCheckBox wholeIsCollectionCheckbox;
	private JCheckBox wholeIsQuantityCheckbox;
	private JCheckBox partIsFunctionalCheckbox;
	private JCheckBox partIsCollectionCheckbox;
	private JCheckBox partIsQuantityCheckbox;
	private JComboBox<RelationStereotype> stereotypeCombo;
	private JCheckBox wholeIsModeCheckbox;
	private JCheckBox partIsModeCheckbox;
	private JCheckBox wholeIsDatatypeCheckbox;
	private MeronymicEndsError error;
	private JCheckBox partIsDatatypeCheckbox;
	private JCheckBox partIsRelatorCheckbox;
	private JCheckBox wholeIsRelatorCheckbox;
	private JComboBox<RelationStereotype> combo;
	private JLabel errorLabel;
	private JSeparator separator;
	/**
	 * Create the dialog.
	 */
	public MeronymicEndsDialog(JDialog parent, MeronymicEndsError error) {
		super(parent);
		this.error = error;
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 594, 676);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Whole End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Part End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("Name:");
		
		JLabel label_1 = new JLabel("Stereotype:");
		
		JLabel label_2 = new JLabel("Nature:");
		
		partStereotypeField = new JTextField();
		partStereotypeField.setEditable(false);
		partStereotypeField.setColumns(10);
		
		partNatureField = new JTextField();
		partNatureField.setEditable(false);
		partNatureField.setColumns(10);
		
		partNameField = new JTextField();
		partNameField.setEditable(false);
		partNameField.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(partNatureField, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addComponent(partNameField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(partStereotypeField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(partNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(partStereotypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(partNatureField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		label_4 = new JLabel("Stereotype:");
		
		label_5 = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setColumns(10);
		
		stereotypeField = new JTextField();
		stereotypeField.setEditable(false);
		stereotypeField.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 278, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4)
						.addComponent(label_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addComponent(stereotypeField, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 125, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(stereotypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JTextPane txtpnPleaseChooseOne = new JTextPane();
		txtpnPleaseChooseOne.setText("Would you like to:");
		
		changeStereotypeCheckbox = new JCheckBox("Change relation stereotype to:");
		changeStereotypeCheckbox.addActionListener(stereoCheckboxAction);
		
		stereotypeCombo = createStereotypeCombo();
		stereotypeCombo.addActionListener(comboAction);
		stereotypeCombo.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("Set WHOLE nature as:");
		
		wholeIsFunctionalCheckbox = new JCheckBox("Functional Complex");
		wholeIsFunctionalCheckbox.addActionListener(natureCheckAction);
		
		wholeIsCollectionCheckbox = new JCheckBox("Collection");
		wholeIsCollectionCheckbox.addActionListener(natureCheckAction);
		
		wholeIsQuantityCheckbox = new JCheckBox("Quantity");
		wholeIsQuantityCheckbox.addActionListener(natureCheckAction);
		
		JLabel lblChangePartNature = new JLabel("Set PART nature as:");
		
		partIsFunctionalCheckbox = new JCheckBox("Functional Complex");
		partIsFunctionalCheckbox.addActionListener(natureCheckAction);
		
		partIsCollectionCheckbox = new JCheckBox("Collection");
		partIsCollectionCheckbox.addActionListener(natureCheckAction);
		
		partIsQuantityCheckbox = new JCheckBox("Quantity");
		partIsQuantityCheckbox.addActionListener(natureCheckAction);
		
		wholeIsModeCheckbox = new JCheckBox("Mode");
		wholeIsModeCheckbox.addActionListener(natureCheckAction);
		
		partIsModeCheckbox = new JCheckBox("Mode");
		partIsModeCheckbox.addActionListener(natureCheckAction);
		
		wholeIsDatatypeCheckbox = new JCheckBox("Datatype");
		wholeIsDatatypeCheckbox.addActionListener(natureCheckAction);
		
		partIsDatatypeCheckbox = new JCheckBox("Datatype");
		partIsDatatypeCheckbox.addActionListener(natureCheckAction);
		
		partIsRelatorCheckbox = new JCheckBox("Relator");
		partIsRelatorCheckbox.addActionListener(natureCheckAction);
		
		wholeIsRelatorCheckbox = new JCheckBox("Relator");
		wholeIsRelatorCheckbox.addActionListener(natureCheckAction);
		
		warning = new JTextPane();
		warning.setText("Automatic nature fix ONLY available if only one type is selected. If more complex solutions are required, please fix your model manually.");
		warning.setForeground(new Color(255, 102, 51));
		warning.setEditable(false);
		warning.setBackground(UIManager.getColor("Button.background"));
		
		errorLabel = new JLabel("Forbidden combination of natures and stereotype! Please try other combination.");
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		separator = new JSeparator();
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
						.addComponent(txtpnPleaseChooseOne, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblChangePartNature, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(changeStereotypeCheckbox)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(combo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(wholeIsFunctionalCheckbox)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(wholeIsCollectionCheckbox))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(partIsFunctionalCheckbox)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(partIsCollectionCheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(partIsQuantityCheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(wholeIsQuantityCheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(partIsModeCheckbox, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(partIsDatatypeCheckbox, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(wholeIsModeCheckbox)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(wholeIsDatatypeCheckbox)))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(partIsRelatorCheckbox)
								.addComponent(wholeIsRelatorCheckbox, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
						.addComponent(warning, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(txtpnPleaseChooseOne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeStereotypeCheckbox)
						.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(wholeIsFunctionalCheckbox)
						.addComponent(wholeIsCollectionCheckbox)
						.addComponent(wholeIsQuantityCheckbox)
						.addComponent(wholeIsModeCheckbox)
						.addComponent(wholeIsDatatypeCheckbox)
						.addComponent(wholeIsRelatorCheckbox))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblChangePartNature)
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(partIsFunctionalCheckbox)
						.addComponent(partIsQuantityCheckbox)
						.addComponent(partIsCollectionCheckbox)
						.addComponent(partIsModeCheckbox)
						.addComponent(partIsDatatypeCheckbox)
						.addComponent(partIsRelatorCheckbox))
					.addGap(18)
					.addComponent(errorLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		
		lblName = new JLabel("Name:");
		
		JLabel lblStereotype = new JLabel("Stereotype:");
		
		JLabel lblNature = new JLabel("Nature:");
		
		wholeStereotypeField = new JTextField();
		wholeStereotypeField.setEditable(false);
		wholeStereotypeField.setColumns(10);
		
		wholeNatureField = new JTextField();
		wholeNatureField.setEditable(false);
		wholeNatureField.setColumns(10);
		
		wholeNameField = new JTextField();
		wholeNameField.setEditable(false);
		wholeNameField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStereotype)
						.addComponent(lblName)
						.addComponent(lblNature, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(wholeNatureField, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
						.addComponent(wholeNameField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(wholeStereotypeField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(wholeNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStereotype)
						.addComponent(wholeStereotypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNature)
						.addComponent(wholeNatureField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		String message ="Every kind of part-whole relation can only be specified between certain kinds of classes." +
						"\r\n\r\n" +
						"The following part-whole relation is incorrectly defined:";

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
		
		JLabel title = new JLabel("Part-Whole Relation - Type Issue");
		
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
		
		setValues();
	}

	private JComboBox<RelationStereotype> createStereotypeCombo() {
		DefaultComboBoxModel<RelationStereotype> model = new DefaultComboBoxModel<RelationStereotype>();
		model.addElement(RelationStereotype.COMPONENTOF);
		model.addElement(RelationStereotype.SUBCOLLECTIONOF);
		model.addElement(RelationStereotype.MEMBEROF);
		model.addElement(RelationStereotype.SUBQUANTITYOF);
		model.addElement(RelationStereotype.FORMAL);
		model.addElement(RelationStereotype.MATERIAL);
		combo = new JComboBox<RelationStereotype>(model);
		combo.setSelectedIndex(0);
		return combo;
	}
	
	private ActionListener saveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			error.setChangeRelationStereotype((RelationStereotype) combo.getSelectedItem());
			error.setChangeWholeNature(wholeIsFunctionalCheckbox.isSelected(), wholeIsCollectionCheckbox.isSelected(), wholeIsQuantityCheckbox.isSelected(), wholeIsRelatorCheckbox.isSelected(), wholeIsModeCheckbox.isSelected(), wholeIsDatatypeCheckbox.isSelected());
			error.setChangePartNature(partIsFunctionalCheckbox.isSelected(), partIsCollectionCheckbox.isSelected(), partIsQuantityCheckbox.isSelected(), partIsRelatorCheckbox.isSelected(), partIsModeCheckbox.isSelected(), partIsDatatypeCheckbox.isSelected());
			
			MeronymicEndsDialog.this.dispose();
		}
	};
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MeronymicEndsDialog.this.dispose();
			
		}
	};
	
	private ActionListener natureCheckAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JCheckBox current = (JCheckBox) e.getSource();
			
			verifyWholeCheckboxes(current);
			verifyPartCheckboxes(current);
			
			setEnableSaveButton();
			
		}

		private void verifyWholeCheckboxes(JCheckBox current){
			verifyCheckboxes(current, wholeIsFunctionalCheckbox, wholeIsCollectionCheckbox, wholeIsQuantityCheckbox, wholeIsRelatorCheckbox, wholeIsModeCheckbox, wholeIsDatatypeCheckbox);
		}
		
		private void verifyPartCheckboxes(JCheckBox current){
			verifyCheckboxes(current, partIsFunctionalCheckbox, partIsCollectionCheckbox, partIsQuantityCheckbox, partIsRelatorCheckbox, partIsModeCheckbox, partIsDatatypeCheckbox);
		}
		
		private void verifyCheckboxes(JCheckBox current, JCheckBox wholeIsFunctionalCheckbox, JCheckBox wholeIsCollectionCheckbox, JCheckBox wholeIsQuantityCheckbox, JCheckBox wholeIsRelatorCheckbox, JCheckBox wholeIsModeCheckbox, JCheckBox wholeIsDatatypeCheckbox) {
			if(current.equals(wholeIsFunctionalCheckbox) || current.equals(wholeIsCollectionCheckbox) || current.equals(wholeIsQuantityCheckbox)){
				wholeIsDatatypeCheckbox.setSelected(false);
				wholeIsModeCheckbox.setSelected(false);
				wholeIsRelatorCheckbox.setSelected(false);
			}
			
			if(current.equals(wholeIsModeCheckbox)){
				wholeIsRelatorCheckbox.setSelected(false);
				wholeIsDatatypeCheckbox.setSelected(false);
			}
			
			if(current.equals(wholeIsRelatorCheckbox)){
				wholeIsModeCheckbox.setSelected(false);
				wholeIsDatatypeCheckbox.setSelected(false);
			}
			
			if(current.equals(wholeIsDatatypeCheckbox)){
				wholeIsRelatorCheckbox.setSelected(false);
				wholeIsModeCheckbox.setSelected(false);
			}
			
			if(current.equals(wholeIsDatatypeCheckbox) || current.equals(wholeIsRelatorCheckbox) || current.equals(wholeIsModeCheckbox)){
				wholeIsFunctionalCheckbox.setSelected(false);
				wholeIsCollectionCheckbox.setSelected(false);
				wholeIsQuantityCheckbox.setSelected(false);
			}
		}
	};
	
	private ActionListener comboAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			setEnableSaveButton();
			
		}
	};
	
	private ActionListener stereoCheckboxAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(changeStereotypeCheckbox.isSelected())
				stereotypeCombo.setEnabled(true);
			else
				stereotypeCombo.setEnabled(false);
			
			setEnableSaveButton();
			
		}
	};
	
	
	private void setEnableSaveButton() {
		
		if(compatibleNatureStereotype()){
			saveButton.setEnabled(true);
			errorLabel.setVisible(false);
		}
		else{
			saveButton.setEnabled(false);
			errorLabel.setVisible(true);
		}
	}

	private boolean compatibleNatureStereotype() {
		
		RelationStereotype currentStereotype;
		if(changeStereotypeCheckbox.isSelected())
			currentStereotype = (RelationStereotype) stereotypeCombo.getSelectedItem();
		else
			currentStereotype = error.getStereotype();
		
		boolean isWholeFunctional, isWholeCollection, isWholeQuantity, isWholeRelator, isWholeMode, isWholeDatatype;
		boolean isPartFunctional, isPartCollection, isPartQuantity, isPartRelator, isPartMode, isPartDatatype;
		
		if(noWholeCheckboxSelected()){
			isWholeFunctional = error.isWholeFunctional();
			isWholeCollection = error.isWholeCollection();
			isWholeQuantity = error.isWholeQuantity();
			isWholeRelator = error.isWholeRelator();
			isWholeMode = error.isWholeMode();
			isWholeDatatype = error.isWholeDatatype();
		}
		else {
			isWholeFunctional = wholeIsFunctionalCheckbox.isSelected();
			isWholeCollection = wholeIsCollectionCheckbox.isSelected();
			isWholeQuantity = wholeIsQuantityCheckbox.isSelected();
			isWholeRelator = wholeIsDatatypeCheckbox.isSelected();
			isWholeMode = wholeIsModeCheckbox.isSelected();
			isWholeDatatype = wholeIsRelatorCheckbox.isSelected();
		}
		
		if(noPartCheckboxSelected()){
			isPartFunctional = error.isPartFunctional();
			isPartCollection = error.isPartCollection();
			isPartQuantity = error.isPartQuantity();
			isPartRelator = error.isPartRelator();
			isPartMode = error.isPartMode();
			isPartDatatype = error.isPartDatatype();
		}
		else {
			isPartFunctional = partIsFunctionalCheckbox.isSelected();
			isPartCollection = partIsCollectionCheckbox.isSelected();
			isPartQuantity = partIsQuantityCheckbox.isSelected();
			isPartRelator = partIsDatatypeCheckbox.isSelected();
			isPartMode = partIsModeCheckbox.isSelected();
			isPartDatatype = partIsRelatorCheckbox.isSelected();
		}
		
		if(currentStereotype.equals(RelationStereotype.COMPONENTOF) &&
				isWholeFunctional && isPartFunctional && 
				!(isWholeCollection || isWholeQuantity || isWholeMode || isWholeRelator || isWholeDatatype) &&
				!(isPartCollection || isPartQuantity || isPartMode || isPartRelator || isPartDatatype))
			return true;
		
		if(currentStereotype.equals(RelationStereotype.MEMBEROF) &&
				isWholeCollection && (isPartFunctional || isPartCollection) && 
				!(isWholeFunctional || isWholeQuantity || isWholeMode || isWholeRelator || isWholeDatatype) &&
				!(isPartQuantity || isPartMode || isPartRelator || isPartDatatype))
			return true;
		if(currentStereotype.equals(RelationStereotype.SUBCOLLECTIONOF) &&
				isWholeCollection && isPartCollection && 
				!(isWholeFunctional || isWholeQuantity || isWholeMode || isWholeRelator || isWholeDatatype) &&
				!(isPartFunctional || isPartQuantity || isPartMode || isPartRelator || isPartDatatype))
			return true;
		if(currentStereotype.equals(RelationStereotype.SUBQUANTITYOF) &&
				isWholeQuantity && isPartQuantity && 
				!(isWholeFunctional || isWholeCollection || isWholeMode || isWholeRelator || isWholeDatatype) &&
				!(isPartFunctional || isPartCollection || isPartMode || isPartRelator || isPartDatatype))
			return true;
		if(currentStereotype.equals(RelationStereotype.CHARACTERIZATION) &&
				isWholeMode && !isPartDatatype && 
				!(isWholeFunctional || isWholeCollection || isWholeQuantity || isWholeRelator || isWholeDatatype) &&
				(isPartFunctional || isPartCollection || isPartQuantity || isPartMode || isPartRelator))
			return true;
		if(currentStereotype.equals(RelationStereotype.MEDIATION) &&
				isWholeRelator && (isPartFunctional || isPartCollection || isPartQuantity) && 
				!(isWholeFunctional || isWholeCollection || isWholeQuantity || isWholeMode || isWholeDatatype) &&
				!(isPartMode || isPartRelator || isPartDatatype))
			return true;
		if(currentStereotype.equals(RelationStereotype.FORMAL) || currentStereotype.equals(RelationStereotype.MATERIAL))
			return true;
		
		return false;
	}

	private boolean noWholeCheckboxSelected(){
		return !wholeIsFunctionalCheckbox.isSelected() && 
				!wholeIsCollectionCheckbox.isSelected() && 
				!wholeIsQuantityCheckbox.isSelected() && 
				!wholeIsDatatypeCheckbox.isSelected() && 
				!wholeIsModeCheckbox.isSelected() && 
				!wholeIsRelatorCheckbox.isSelected();
	}
	
	private boolean noPartCheckboxSelected(){
		return !partIsFunctionalCheckbox.isSelected() && 
				!partIsCollectionCheckbox.isSelected() && 
				!partIsQuantityCheckbox.isSelected() && 
				!partIsDatatypeCheckbox.isSelected() && 
				!partIsModeCheckbox.isSelected() && 
				!partIsRelatorCheckbox.isSelected();
	}
	
	private void setValues(){
		RefOntoUML.Type whole = OntoUMLParser.getWholeEnd(error.getElement()).getType();
		RefOntoUML.Type part = OntoUMLParser.getPartEnd(error.getElement()).getType();
		
		nameField.setText(error.getElement().getName());
		stereotypeField.setText(OntoUMLNameHelper.getTypeName(error.getElement(), true));
		
		wholeNameField.setText(whole.getName());
		wholeStereotypeField.setText(OntoUMLNameHelper.getTypeName(whole, true));
		wholeNatureField.setText(error.getWholeNature());
		
		partNameField.setText(part.getName());
		partStereotypeField.setText(OntoUMLNameHelper.getTypeName(part, true));
		partNatureField.setText(error.getPartNature());
		
		if(error.hasAction()){
			changeStereotypeCheckbox.setSelected(error.getChangeStereotype());
			
			if(error.getChangeStereotype()){
				combo.setSelectedItem(error.getNewStereotype());
				combo.setEnabled(true);
			}
			
			wholeIsFunctionalCheckbox.setSelected(error.isWholeSetAsFunctional());
			wholeIsCollectionCheckbox.setSelected(error.isWholeSetAsCollection());
			wholeIsQuantityCheckbox.setSelected(error.isWholeSetAsQuantity());
			wholeIsRelatorCheckbox.setSelected(error.isWholeSetAsRelator());
			wholeIsModeCheckbox.setSelected(error.isWholeSetAsMode());
			wholeIsDatatypeCheckbox.setSelected(error.isWholeSetAsDatatype());
			
			partIsFunctionalCheckbox.setSelected(error.isPartSetAsFunctional());
			partIsCollectionCheckbox.setSelected(error.isPartSetAsCollection());
			partIsQuantityCheckbox.setSelected(error.isPartSetAsQuantity());
			partIsRelatorCheckbox.setSelected(error.isPartSetAsRelator());
			partIsModeCheckbox.setSelected(error.isPartSetAsMode());
			partIsDatatypeCheckbox.setSelected(error.isPartSetAsDatatype());
			
			setEnableSaveButton();
		}
	}
}
