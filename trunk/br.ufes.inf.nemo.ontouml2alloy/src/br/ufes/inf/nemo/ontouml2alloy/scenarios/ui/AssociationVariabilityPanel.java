package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationVariability;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationVariabilityScenario;
import javax.swing.SwingConstants;
import java.awt.Component;

public class AssociationVariabilityPanel extends ScenarioPanel<AssociationVariabilityScenario> {
	
	private static final long serialVersionUID = -270595094656326794L;
	
	private OntoUMLElementCombo associationCombo;
	private JTextField targetField;
	private JTextField sourceField;
	private JComboBox<AssociationVariability> variabilityCombo;

	/**
	 * @wbp.parser.constructor
	 */
	public AssociationVariabilityPanel(OntoUMLParser parser) {
		this(parser,null);
	}
	
	public AssociationVariabilityPanel(OntoUMLParser parser, AssociationVariabilityScenario scenario) {
		super(parser,scenario);
		
		JLabel lblNewLabel_1 = new JLabel("Source:");
		
		sourceField = new JTextField();
		sourceField.setEditable(false);
		
		JLabel lblStereotype = new JLabel("Association:");
		
		associationCombo = new OntoUMLElementCombo(parser);
		associationCombo.setAsAssociationCombo();
		associationCombo.addActionListener(associationListener);
		
		targetField = new JTextField();
		targetField.setEditable(false);
		
		JLabel lblTarget = new JLabel("Target:");
		
		JLabel lblMultiplicity = new JLabel("Changeability:");
		
		variabilityCombo = new JComboBox<AssociationVariability>();
		variabilityCombo.setModel(new DefaultComboBoxModel<AssociationVariability>(AssociationVariability.values()));
		
		reverseCheckbox = new JCheckBox("Reverse Ends?");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblStereotype, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTarget, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMultiplicity, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(targetField, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addComponent(sourceField, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addComponent(associationCombo, 0, 331, Short.MAX_VALUE)
						.addComponent(reverseCheckbox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addComponent(variabilityCombo, 0, 331, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(associationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStereotype))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sourceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(targetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTarget))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(reverseCheckbox)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMultiplicity)
						.addComponent(variabilityCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {sourceField, associationCombo, targetField, variabilityCombo});
		setLayout(groupLayout);
		
		combos.add(associationCombo);
		combos.add(variabilityCombo);

	}

	private ActionListener associationListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateTextFields();
		}		
	};
	private JCheckBox reverseCheckbox;
	
	private void updateTextFields() {
		if(associationCombo.getSelectedIndex()==-1){
			sourceField.setText("");
			targetField.setText("");
			return;
		}
		
		Association a = (Association) associationCombo.getElement();
		
		if(a.getMemberEnd().size()!=2)
			return;
		
		sourceField.setText(OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(0), true));
		targetField.setText(OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(1), true));
	}

	@Override
	public void loadDefaultUIData() {
		associationCombo.setSelectedItem(null);
		sourceField.setText("");
		targetField.setText("");
		
		reverseCheckbox.setSelected(false);
		variabilityCombo.setSelectedItem(null);
	}

	@Override
	public void loadScenarioUIData() {
		if(scenario==null)
			loadDefaultUIData();
		
		associationCombo.assignElement(scenario.getAssociation());
		updateTextFields();
		
		reverseCheckbox.setSelected(scenario.isReverse());
		variabilityCombo.setSelectedItem(scenario.getVariability());
	}

	@Override
	public AssociationVariabilityScenario saveScenario() {
		if(scenario==null)
			scenario = new AssociationVariabilityScenario(parser);
		
		scenario.setAssociation((Association) associationCombo.getElement());
		scenario.reverse(reverseCheckbox.isSelected());
			
		scenario.setVariability((AssociationVariability)variabilityCombo.getSelectedItem());
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		return associationCombo.getSelectedIndex()!=-1 && variabilityCombo.getSelectedIndex()!=-1;
	}
}
