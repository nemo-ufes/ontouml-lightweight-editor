package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import RefOntoUML.Meronymic;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.checkers.MeronymicCycleError;

public class MeronymicCycleDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane question;
	private JTextPane warning;
	private JButton saveButton;
	private JButton cancelButton;
	private JTextPane cycleDescriptionText;
	private JScrollPane scrollPane_1;
	private JTextPane txtpnPleaseSelectThe;
	private JTextField currentPartField;
	private JLabel lblNewLabel_2;
	private JLabel lblPart;
	private JTextField currentWholeField;
	private JTextField currentStereotypeField;
	private JButton resetButton;
	private JRadioButton reverseRadio;
	private JRadioButton deleteRadio;
	private JRadioButton changeStereotypeRadio;
	private JComboBox<String> relationCombo;
	private JComboBox<RelationStereotype> newStereotypeCombo;
	private JButton registerButton;
	private JComboBox<String> combo;
	
	private MeronymicCycleError error;
	
	/**
	 * Create the dialog.
	 */
	public MeronymicCycleDialog(JDialog parent, MeronymicCycleError error) {
		super(parent);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.error = error;
		
		setBounds(100, 100, 616, 696);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		warning = new JTextPane();
		warning.setText("If none of the options provided are suitable, please close this window and fix your model manually.");
		warning.setForeground(new Color(255, 102, 51));
		warning.setEditable(false);
		warning.setBackground(UIManager.getColor("Button.background"));
		
		scrollPane_1 = new JScrollPane();
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
					.addGap(9))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(9)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 293, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		
		txtpnPleaseSelectThe = new JTextPane();
		txtpnPleaseSelectThe.setText("Please select the relation you would like to fix (one action can be set for each relation):");
		txtpnPleaseSelectThe.setEditable(false);
		txtpnPleaseSelectThe.setBackground(SystemColor.menu);
		
		relationCombo = createRelationCombo();
		relationCombo.addActionListener(relationAction);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Current Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(txtpnPleaseSelectThe, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(combo, 0, 580, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(txtpnPleaseSelectThe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 142, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		reverseRadio = new JRadioButton("Reverse Ends");
		reverseRadio.addActionListener(radioListener);
		deleteRadio = new JRadioButton("Delete");
		deleteRadio.addActionListener(radioListener);
		changeStereotypeRadio = new JRadioButton("Change stereotype (Non Part-Whole) to:");
		changeStereotypeRadio.addActionListener(radioListener);
		
		ButtonGroup group = new ButtonGroup();
		group.add(reverseRadio);
		group.add(deleteRadio);
		group.add(changeStereotypeRadio);
		
		newStereotypeCombo = createStereotypeCombo();
		newStereotypeCombo.setEnabled(false);
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(registerAction);
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(resetAction);
		
		setEnableToRelationComponents(false);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(reverseRadio)
						.addComponent(deleteRadio)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(changeStereotypeRadio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(newStereotypeCombo, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(122, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(384, Short.MAX_VALUE)
					.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(registerButton)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(reverseRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deleteRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeStereotypeRadio)
						.addComponent(newStereotypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(registerButton)
						.addComponent(resetButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		lblNewLabel_2 = new JLabel("Stereotype:");
		
		currentStereotypeField = new JTextField();
		currentStereotypeField.setEditable(false);
		currentStereotypeField.setColumns(10);
		
		lblPart = new JLabel("Part:");
		
		JLabel lblNewLabel_1 = new JLabel("Whole:");
		
		currentPartField = new JTextField();
		currentPartField.setEditable(false);
		currentPartField.setColumns(10);
		
		currentWholeField = new JTextField();
		currentWholeField.setEditable(false);
		currentWholeField.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(lblPart)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentStereotypeField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addComponent(currentPartField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addComponent(currentWholeField))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentStereotypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPart)
						.addComponent(currentPartField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentWholeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		
		cycleDescriptionText = new JTextPane();
		cycleDescriptionText.setEditable(false);
		scrollPane_1.setViewportView(cycleDescriptionText);
		cycleDescriptionText.setText(getCycleDescription());
		
		String message ="Every part-whole relation is natively acyclic at instance level. " +
						"This means that scenarios like A part of B, B part of C and C part of A can't be specified." +
						"\r\n\r\n" +
						"The following cycle of part-whole relations was identified in your model:";

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
		
		JLabel title = new JLabel("Cycle of Part-Whole Relations");
		
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

	private JComboBox<RelationStereotype> createStereotypeCombo() {
		DefaultComboBoxModel<RelationStereotype> model = new DefaultComboBoxModel<RelationStereotype>();
		model.addElement(RelationStereotype.FORMAL);
		model.addElement(RelationStereotype.MATERIAL);
		JComboBox<RelationStereotype> combo = new JComboBox<RelationStereotype>(model);
		return combo;
	}
	
	private ActionListener saveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MeronymicCycleDialog.this.dispose();
		}
	};
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			error.resetAllActions();
			MeronymicCycleDialog.this.dispose();
			
		}
	};
	
	private ActionListener relationAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Meronymic m = error.getMeronymicCycle().get(relationCombo.getSelectedIndex());
			setCurrentProperties(m);
			if(error.hasValidAction(m))
				setActionComponents(m);
			else{
				setDefaultActionComponents();
			}
			setEnableToRelationComponents(true);
		}
	};
	
	private ActionListener registerAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			error.saveValuesFor(error.getMeronymicCycle().get(relationCombo.getSelectedIndex()), deleteRadio.isSelected(), reverseRadio.isSelected(), changeStereotypeRadio.isSelected(), (RelationStereotype) newStereotypeCombo.getSelectedItem());
			enableSaveButton();
		}
	};
	
	
	private ActionListener resetAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			setDefaultActionComponents();
			
		}
	};

	private ActionListener radioListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(changeStereotypeRadio.isSelected())
				newStereotypeCombo.setEnabled(true);
			else
				newStereotypeCombo.setEnabled(false);
			
		}
	};
	
	private void setDefaultActionComponents() {
		reverseRadio.setSelected(true);
		deleteRadio.setSelected(false);
		changeStereotypeRadio.setSelected(false);
		newStereotypeCombo.setSelectedIndex(0);
		newStereotypeCombo.setEnabled(false);
	}
	
	private void enableSaveButton() {
		if(error.hasValidAction())
			saveButton.setEnabled(true);
		else
			saveButton.setEnabled(false);
	}
	
	private String getLine(Meronymic m){
		String line = OntoUMLParser.getPartEnd(m).getType().getName()+" is a ";
		if(m instanceof componentOf)
			line += "functional part";
		else if(m instanceof memberOf)
			line += "member";
		else if(m instanceof subQuantityOf)
			line += "sub-quantity";
		else if(m instanceof subCollectionOf)
			line += "sub-collection";
		
		line += " of "+OntoUMLParser.getWholeEnd(m).getType().getName();
		return line;
	}
	
	private String getCycleDescription(){
		String result = "";
		int i = 0;
		for (Meronymic m : error.getMeronymicCycle()) {
			if(i!=0) result += "\n";
			result += getLine(m);
			i++;
		}
		return result;
	}
	
	private JComboBox<String> createRelationCombo(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for (Meronymic m : error.getMeronymicCycle()) {
			model.addElement(OntoUMLNameHelper.getCompleteName(m));
		}
		combo = new JComboBox<String>(model);
		combo.setSelectedIndex(-1);
		return combo;
	}
	
	private void setCurrentProperties(Meronymic m){
		currentStereotypeField.setText(OntoUMLNameHelper.getTypeName(m, true));
		currentPartField.setText(OntoUMLNameHelper.getTypeAndName(OntoUMLParser.getPartEnd(m).getType(), true, true));
		currentWholeField.setText(OntoUMLNameHelper.getTypeAndName(OntoUMLParser.getWholeEnd(m).getType(), true, true));
	}
	
	public void setActionComponents(Meronymic m){
		deleteRadio.setSelected(error.isDelete(m));
		reverseRadio.setSelected(error.isReverse(m));
		changeStereotypeRadio.setSelected(error.isChange(m));
		
		if(error.isChange(m))
			newStereotypeCombo.setEnabled(true);
		else
			newStereotypeCombo.setEnabled(false);
		
		if(error.getSelectedStereotype(m)!=null)
			newStereotypeCombo.setSelectedItem(error.getSelectedStereotype(m));
		else
			newStereotypeCombo.setSelectedIndex(0);
		
	}

	private void setEnableToRelationComponents(boolean b) {
		registerButton.setEnabled(b);
		resetButton.setEnabled(b);
		deleteRadio.setEnabled(b);
		reverseRadio.setEnabled(b);
		changeStereotypeRadio.setEnabled(b);
	}
}
