package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationMultiplicityScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.BinaryOperator;
import javax.swing.JCheckBox;
import java.awt.Component;

public class AssociationMultiplicityPanel extends ScenarioPanel<AssociationMultiplicityScenario> {
	
	private static final long serialVersionUID = -270595094656326794L;
	
	private OntoUMLElementCombo associationCombo;
	private JTextField targetField;
	private JTextField sourceField;
	private JComboBox<BinaryOperator> operatorCombo;
	private JSpinner multiplicitySpinner;
	private CustomQuantificationPanel classQuantificationPanel;
	private CustomQuantificationPanel worldQuantificationPanel;

	
	
	/**
	 * @wbp.parser.constructor
	 */
	public AssociationMultiplicityPanel(OntoUMLParser parser) {
		this(parser,null);
	}
	
	public AssociationMultiplicityPanel(OntoUMLParser parser, AssociationMultiplicityScenario scenario) {
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
		
		JLabel lblMultiplicity = new JLabel("Operator:");
		
		operatorCombo = new JComboBox<BinaryOperator>();
		operatorCombo.setModel(new DefaultComboBoxModel<BinaryOperator>(BinaryOperator.values()));
		
		JLabel lblX = new JLabel("x =");
		lblX.setHorizontalAlignment(SwingConstants.LEFT);
		
		multiplicitySpinner = new JSpinner();
		
		classQuantificationPanel = new CustomQuantificationPanel();
		classQuantificationPanel.setBorder(new TitledBorder(null, "Base Class Quantification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		worldQuantificationPanel = new CustomQuantificationPanel();
		worldQuantificationPanel.setBorder(new TitledBorder(null, "World Quantification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		reverseCheckbox = new JCheckBox("Reverse Ends?");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(worldQuantificationPanel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
						.addComponent(classQuantificationPanel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMultiplicity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTarget, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStereotype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(targetField, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
								.addComponent(sourceField, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
								.addComponent(associationCombo, 0, 418, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(operatorCombo, 0, 144, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(multiplicitySpinner, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(reverseCheckbox)))))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMultiplicity)
						.addComponent(reverseCheckbox)
						.addComponent(multiplicitySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX))
					.addGap(15)
					.addComponent(classQuantificationPanel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(worldQuantificationPanel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {sourceField, associationCombo, targetField, operatorCombo, multiplicitySpinner});
		setLayout(groupLayout);
		
		combos.add(associationCombo);
		combos.add(operatorCombo);
		combos.addAll(classQuantificationPanel.getAllCombos());
		combos.addAll(worldQuantificationPanel.getAllCombos());
		
		spinners.add(classQuantificationPanel.getSpinner());
		spinners.add(worldQuantificationPanel.getSpinner());
		spinners.add(multiplicitySpinner);

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
		operatorCombo.setSelectedItem(null);
		multiplicitySpinner.setValue(1);
		
		classQuantificationPanel.loadDefautUIData();
		worldQuantificationPanel.loadDefautUIData();
	}

	@Override
	public void loadScenarioUIData() {
		if(scenario==null)
			loadDefaultUIData();
		
		associationCombo.assignElement(scenario.getAssociation());
		updateTextFields();
		
		reverseCheckbox.setSelected(scenario.isReverse());
		
		operatorCombo.setSelectedItem(scenario.getOperator());
		multiplicitySpinner.setValue(scenario.getValue());
		
		classQuantificationPanel.loadQuantificationUIData(scenario.getClassQuantification());
		worldQuantificationPanel.loadQuantificationUIData(scenario.getWorldQuantification());
	}

	@Override
	public AssociationMultiplicityScenario saveScenario() {
		if(scenario==null)
			scenario = new AssociationMultiplicityScenario(parser);
		
		scenario.setAssociation((Association) associationCombo.getElement());
		scenario.reverse(reverseCheckbox.isSelected());
			
		scenario.setOperator((BinaryOperator)operatorCombo.getSelectedItem());
		scenario.setValue((int) multiplicitySpinner.getValue());
		
		classQuantificationPanel.saveQuantificationData(scenario.getClassQuantification());
		worldQuantificationPanel.saveQuantificationData(scenario.getWorldQuantification());
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		return associationCombo.getSelectedIndex()!=-1 && operatorCombo.getSelectedIndex()!=-1 && ((int)multiplicitySpinner.getValue())>=0 &&
				classQuantificationPanel.canSave() && worldQuantificationPanel.canSave();
	}
}
