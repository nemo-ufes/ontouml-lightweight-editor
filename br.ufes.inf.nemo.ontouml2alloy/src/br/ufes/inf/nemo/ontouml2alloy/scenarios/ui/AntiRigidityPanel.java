package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

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
		JLabel lblNewLabel_1 = new JLabel("Choose an anti-rigid class:");
		
		classCombo = OntoUMLElementCombo.createAntiRigidCombo(parser);
		
		antiRigidityModeCombo = new JComboBox<AntirigidityMode>();
		antiRigidityModeCombo.setModel(new DefaultComboBoxModel<AntirigidityMode>(AntirigidityMode.values()));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(classCombo, 0, 350, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblClass)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(antiRigidityModeCombo, 0, 350, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(classCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(antiRigidityModeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClass))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblClass, lblNewLabel_1});
		setLayout(groupLayout);
		
		combos.add(classCombo);
		combos.add(antiRigidityModeCombo);

	}

	
	
	@Override
	public void loadDefaultUIData() {
		classCombo.setSelectedIndex(0);
		antiRigidityModeCombo.setSelectedIndex(0);
	}

	@Override
	public void loadScenarioUIData() {
		classCombo.setSelectedElement(scenario.getAntiRigid());
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
