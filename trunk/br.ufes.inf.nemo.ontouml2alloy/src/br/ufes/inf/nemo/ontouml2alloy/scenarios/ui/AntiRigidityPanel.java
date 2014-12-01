package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AntiRigidityScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AntiRigidityScenario.AntirigidityMode;

public class AntiRigidityPanel extends ScenarioPanel<AntiRigidityScenario> {

	private static final long serialVersionUID = 1L;

	private OntoUMLElementCombo classCombo;
	private JComboBox<AntirigidityMode> antiRigidityModeCombo;
	
	/**
	 * @wbp.parser.constructor
	 */
	public AntiRigidityPanel(OntoUMLParser parser){
		this(parser, null);
	}
	
	/**
	 * Create the panel.
	 */	
	public AntiRigidityPanel(OntoUMLParser parser, AntiRigidityScenario scenario) {
		super(parser, scenario);
		
		JLabel lblClass = new JLabel("Behavior:");
		JLabel lblNewLabel_1 = new JLabel("Anti-Rigid Class:");
		
		classCombo = new OntoUMLElementCombo(parser);
		classCombo.setAsAntiRigidCombo();
		
		antiRigidityModeCombo = new JComboBox<AntirigidityMode>();
		antiRigidityModeCombo.setModel(new DefaultComboBoxModel<AntirigidityMode>(AntirigidityMode.values()));
		
		combos.add(classCombo);
		combos.add(antiRigidityModeCombo);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblClass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(classCombo, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
						.addComponent(antiRigidityModeCombo, 0, 337, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1))
						.addComponent(classCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClass)
						.addComponent(antiRigidityModeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		setLayout(groupLayout);

	}

	
	
	@Override
	public void loadDefaultUIData() {
		classCombo.setSelectedIndex(0);
		antiRigidityModeCombo.setSelectedIndex(0);
	}

	@Override
	public void loadScenarioUIData() {
		classCombo.assignElement(scenario.getAntiRigid());
		antiRigidityModeCombo.setSelectedItem(scenario.getAntirigidityMode());
	}

	@Override
	public AntiRigidityScenario saveScenario() {
		
		if(scenario==null)
			scenario = new AntiRigidityScenario(parser);
		
		scenario.setAntiRigid(getSelectedAntirigid());
		scenario.setAntirigidityMode((AntirigidityMode) antiRigidityModeCombo.getSelectedItem());
		return null;
	}
	
	public Class getSelectedAntirigid(){
		OntoUMLElement e = (OntoUMLElement) classCombo.getSelectedItem();
		return (Class) e.getElement();
	}

	@Override
	public boolean canSave() {
		if(antiRigidityModeCombo.getSelectedIndex()!=-1 && classCombo.getSelectedIndex()!=-1)
			return true;
		
		return false;
	}
	
}
