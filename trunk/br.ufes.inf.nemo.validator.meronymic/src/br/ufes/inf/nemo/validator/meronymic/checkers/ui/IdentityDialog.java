package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import RefOntoUML.Classifier;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.validator.meronymic.checkers.IdentityError;

public class IdentityDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane question;
	private JTextPane warning;
	private JButton saveButton;
	private JButton cancelButton;
	private JLabel lblProvidesIdentity;
	private JLabel lblInheritedIdentities;
	private JTextField inheritedText;
	private JTextField providesIdentityText;
	private JTextField stereoText;
	private JTextField nameText;
	private JScrollPane scrollPane_1;
	private JLabel lblIdentityProviderssupertypes;
	private JList<String> providersList;
	private JTextPane txtpnHowWouldLike;
	private JPanel panel_1;
	private JLabel lblIdentityProvider;
	private JLabel label;
	private JLabel lblbothActionsWill;
	private JComboBox<ClassStereotype> providerStereoCombo;
	private JComboBox<ClassStereotype> notProviderStereoCombo;
	private JComboBox<String> notProviderInheritedCombo;
	private JRadioButton providerRadio;
	private JRadioButton notProviderRadio;
	
	private IdentityError error;
	private ArrayList<Classifier> identityProviders;
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public IdentityDialog(JDialog parent, IdentityError error) {
		super(parent);
		this.error = error;
		this.identityProviders = createIdentityProviderList();
		
		setBounds(100, 100, 627, 654);
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
		
		panel_1 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(8))
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		
		txtpnHowWouldLike = new JTextPane();
		txtpnHowWouldLike.setText("How would like to fix your model?");
		txtpnHowWouldLike.setEditable(false);
		txtpnHowWouldLike.setBackground(UIManager.getColor("Button.background"));
		
		providerStereoCombo = createSubstanceSortalCombo();
		
		providerRadio = new JRadioButton(OntoUMLNameHelper.getName(error.getElement(), false, true)+" is an Identity Provider");
		providerRadio.addActionListener(radioListener);
		
		notProviderRadio = new JRadioButton(OntoUMLNameHelper.getName(error.getElement(), false, true)+" is NOT an Identity Provider");
		notProviderRadio.addActionListener(radioListener);
		
		JLabel lblPleaseChooseThe = new JLabel("Stereotype:");
		
		lblIdentityProvider = new JLabel("Stereotype:");
		
		notProviderStereoCombo = createSortalCombo();
		
		label = new JLabel("Identity Provider:");
		
		notProviderInheritedCombo = createIdentityProviderCombo();
		
		lblbothActionsWill = new JLabel(" (Both actions will remove existing generalizations leading to all other identity providers)");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblbothActionsWill, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
						.addComponent(txtpnHowWouldLike, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21)
							.addComponent(lblPleaseChooseThe, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(providerStereoCombo, 0, 460, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(lblIdentityProvider, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(notProviderInheritedCombo, 0, 448, Short.MAX_VALUE)
								.addComponent(notProviderStereoCombo, 0, 448, Short.MAX_VALUE)))
						.addComponent(providerRadio, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
						.addComponent(notProviderRadio, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(txtpnHowWouldLike, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(providerRadio)
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPleaseChooseThe)
						.addComponent(providerStereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(notProviderRadio)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdentityProvider)
						.addComponent(notProviderStereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(notProviderInheritedCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(lblbothActionsWill))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblParentType = new JLabel("Name:");
		
		JLabel lblChildType = new JLabel("Stereotype:");
		
		ButtonGroup group = new ButtonGroup();
		group.add(notProviderRadio);
		group.add(providerRadio);
		
		setEnableToIsProviderComponents(false);
		setEnableToNotProviderComponents(false);
		
		lblProvidesIdentity = new JLabel("Provides Identity?");
		
		lblInheritedIdentities = new JLabel("Inherited Identities:");
		
		inheritedText = new JTextField();
		inheritedText.setEditable(false);
		inheritedText.setText(Integer.toString(error.numberOfIdentityProviders()));
		inheritedText.setColumns(10);
		
		providesIdentityText = new JTextField();
		providesIdentityText.setEditable(false);
		providesIdentityText.setColumns(10);
		fillProvidesText(providesIdentityText);
		
		stereoText = new JTextField();
		stereoText.setEditable(false);
		stereoText.setColumns(10);
		stereoText.setBounds(70, 34, 310, 20);
		stereoText.setText(OntoUMLNameHelper.getTypeName(error.getElement(), true));
		panel.add(stereoText);
		
		nameText = new JTextField();
		nameText.setEditable(false);
		nameText.setColumns(10);
		nameText.setText(error.getElement().getName());
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setViewportBorder(new LineBorder(UIManager.getColor("TextField.darkShadow")));
		
		lblIdentityProviderssupertypes = new JLabel("Identity Providers (Supertypes)");
		scrollPane_1.setColumnHeaderView(lblIdentityProviderssupertypes);
		
		providersList = createInheritedProviderList();
		scrollPane_1.setViewportView(providersList);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(70)
									.addComponent(nameText, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblParentType, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblProvidesIdentity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(lblChildType, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(326)
							.addComponent(lblInheritedIdentities)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(providesIdentityText, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(inheritedText, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lblParentType, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProvidesIdentity, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(providesIdentityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblChildType, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblInheritedIdentities, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(inheritedText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		
		String message ="Every individual has an unique identity, which never changes. We can count and differentiate individuals because of their identities. " +
				"An individual obtains its identity from a particular type it instantiates, its Identity Provider. These types are identified by the stereotypes Kind, Quantity and Collective."+
				"\r\n\r\n"+
				"This identity issue is present to you because the following type that has/inherit <"+error.numberOfIdentityProviders()+"> identity principles.";


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
		
		JLabel title = new JLabel("Identity Issue");
		
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
		
			if(providerRadio.isSelected())
				error.setAsIdentityProvider((ClassStereotype)providerStereoCombo.getSelectedItem());
			else if(notProviderRadio.isSelected()){
				error.setAsInheritsProvider((ClassStereotype)notProviderStereoCombo.getSelectedItem(), getSelectedIdentityProvider());
			}
			
			IdentityDialog.this.dispose();
			
		}
	};
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			IdentityDialog.this.dispose();
			
		}
	};
	
	private ActionListener radioListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(providerRadio.isSelected() || notProviderRadio.isSelected())
				saveButton.setEnabled(true);
			else
				saveButton.setEnabled(false);
			
			if(providerRadio.isSelected()) 
				setEnableToIsProviderComponents(true);
			else 
				setEnableToIsProviderComponents(false);
			
			if(notProviderRadio.isSelected()) 
				setEnableToNotProviderComponents(true);
			else 
				setEnableToNotProviderComponents(false);
			
		}
	};

	private void setEnableToIsProviderComponents(boolean b){
		providerStereoCombo.setEnabled(b);
	}
	
	private void setEnableToNotProviderComponents(boolean b){
		notProviderInheritedCombo.setEnabled(b);
		notProviderStereoCombo.setEnabled(b);
	}
	
	private void fillProvidesText(JTextField text){
		if(error.getElement() instanceof SubstanceSortal)
			text.setText("Yes");
		else
			text.setText("No");
	}
	
	private JComboBox<ClassStereotype> createSubstanceSortalCombo(){
		
		DefaultComboBoxModel<ClassStereotype> model = new DefaultComboBoxModel<ClassStereotype>();
		model.addElement(ClassStereotype.KIND);
		model.addElement(ClassStereotype.COLLECTIVE);
		model.addElement(ClassStereotype.QUANTITY);
		
		JComboBox<ClassStereotype> combo = new JComboBox<>(model);
		
		if(error.getElement() instanceof SubstanceSortal)
			combo.setSelectedItem(OutcomeFixer.getClassStereotype(error.getElement()));
		else
			combo.setSelectedIndex(0);
		
		return combo;
	}
	
	private JComboBox<ClassStereotype> createSortalCombo(){
		
		DefaultComboBoxModel<ClassStereotype> model = new DefaultComboBoxModel<ClassStereotype>();
		model.addElement(ClassStereotype.SUBKIND);
		model.addElement(ClassStereotype.ROLE);
		model.addElement(ClassStereotype.PHASE);
		
		JComboBox<ClassStereotype> combo = new JComboBox<>(model);
		
		Classifier classifier = error.getElement();
		
		if(classifier instanceof SubKind || classifier instanceof Phase || classifier instanceof Role)
			combo.setSelectedItem(OutcomeFixer.getClassStereotype(classifier));
		else
			combo.setSelectedIndex(0);
		
		return combo;
	}
	
	private JComboBox<String> createIdentityProviderCombo() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for (Classifier c : identityProviders) {
			
			String suffix = "";
			if(error.getIdentityProviders().contains(c))
				suffix = "*";
			
			model.addElement(OntoUMLNameHelper.getTypeAndName(c, true, true)+suffix);
		}
		
		JComboBox<String> combo = new JComboBox<String>(model);
		return combo;
	}
	
	private ArrayList<Classifier> createIdentityProviderList(){
		ArrayList<Classifier> list = new ArrayList<Classifier>(error.getIdentityProviders());
		
		for (Classifier c : error.getParser().getAllInstances(SubstanceSortal.class)) {
			if(!list.contains(c))
				list.add(c);
		}
		
		return list;
	}
	
	private Classifier getSelectedIdentityProvider(){
		if (notProviderInheritedCombo.getSelectedIndex()==-1)
			return null;
		else
			return identityProviders.get(notProviderInheritedCombo.getSelectedIndex());
	}
	
	private JList<String> createInheritedProviderList() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (Classifier c : error.getIdentityProviders()) {
			model.addElement(OntoUMLNameHelper.getTypeAndName(c, true, true));
		}
		
		JList<String> list = new JList<String>(model);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		return list;
	}
}
