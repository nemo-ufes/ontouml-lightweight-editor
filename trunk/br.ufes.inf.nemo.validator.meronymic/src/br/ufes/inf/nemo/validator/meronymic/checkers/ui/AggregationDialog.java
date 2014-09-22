package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.validator.meronymic.checkers.AggregationKindError;

public class AggregationDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane question;
	private JTextPane warning;
	private JTextField type1;
	private JTextField type2;
	private JRadioButton type2WholeRadio;
	private JRadioButton type1WholeRadio;
	private JButton saveButton;
	private JButton cancelButton;
	private JComboBox<Object> aggregationCombo;
	
	private AggregationKindError error;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public AggregationDialog(JDialog parent, AggregationKindError error) {
		super(parent);
		this.error = error;
		
		setBounds(100, 100, 616, 477);
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 579, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
							.addGap(9))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(warning, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblParentType = new JLabel("Type 1:");
		
		type1 = new JTextField();
		type1.setText(OntoUMLNameHelper.getTypeAndName(error.getElement().getMemberEnd().get(0).getType(), true, true));
		type1.setEditable(false);
		type1.setColumns(10);
		
		JLabel lblChildType = new JLabel("Type 2:");
		
		type2 = new JTextField();
		type2.setText(OntoUMLNameHelper.getTypeAndName(error.getElement().getMemberEnd().get(1).getType(), true, true));
		type2.setEditable(false);
		type2.setColumns(10);

		type1WholeRadio = new JRadioButton("Type 1 as Whole and Type 2 as Part");
		type1WholeRadio.addActionListener(radioListener);
		
		type2WholeRadio = new JRadioButton("Type 1 as Part and Type 2 as Whole");
		type2WholeRadio.addActionListener(radioListener);
		
		ButtonGroup group = new ButtonGroup();
		group.add(type1WholeRadio);
		group.add(type2WholeRadio);
		
		JLabel lblAggregationKind = new JLabel("Aggregation Kind:");
		
		aggregationCombo = createAggregationCombo();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblParentType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblChildType, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(type1, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
								.addComponent(type2, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))
						.addComponent(type2WholeRadio, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
						.addComponent(type1WholeRadio, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAggregationKind)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(aggregationCombo, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblParentType)
						.addComponent(type1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChildType)
						.addComponent(type2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(type1WholeRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(type2WholeRadio)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAggregationKind)
						.addComponent(aggregationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
		String message ="The Aggregation Kind meta-attributte of part-whole relations is used to define which end corresponds to the Whole and which corresponds to the Part. "+
						"Because of that, every part-whole relation (componentOf, memberOf, subQuantityOf and memberOf) must have exactly one end defined as either 'Shared' or 'Composite'." +
						"\r\n\r\n" +
						"The following "+OntoUMLNameHelper.getTypeName(error.getElement(), true)+" relation has the aggregation meta-property";
		
		if(error.bothEndsAreNone())
			message += " on both ends defined as NONE";
		if(error.bothEndsAreSet())
			message += " set on both ends";
		
		message += ". Please the part and the whole on the relation:";

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
		
		JLabel title = new JLabel("Aggregation Kind Issue");
		
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

	private JComboBox<Object> createAggregationCombo() {
		Object[] values = {"Shared", "Composite"};
		JComboBox<Object> combo = new JComboBox<>(values);
		combo.setSelectedIndex(0);
		return combo;
	}
	
	private ActionListener saveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			boolean isComposite;
			
			if(aggregationCombo.getSelectedIndex()==0)
				isComposite = false;
			else
				isComposite = true;
			
			if(type1WholeRadio.isSelected())
				error.setSourceAsWhole(isComposite);
			else if(type2WholeRadio.isSelected())
				error.setTargetAsWhole(isComposite);
			
			AggregationDialog.this.dispose();
			
		}
	};
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			AggregationDialog.this.dispose();
			
		}
	};
	
	private ActionListener radioListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(type2WholeRadio.isSelected() || type1WholeRadio.isSelected())
				saveButton.setEnabled(true);
			else
				saveButton.setEnabled(false);
		}
	};

	

	
}
