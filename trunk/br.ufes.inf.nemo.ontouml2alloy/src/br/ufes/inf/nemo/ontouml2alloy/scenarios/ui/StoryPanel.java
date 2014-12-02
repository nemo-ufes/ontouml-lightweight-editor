package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.BinaryOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario.Limit;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryType;

public class StoryPanel extends ScenarioPanel<StoryScenario> {

	private static final long serialVersionUID = 3894596408424196719L;
	private JSpinner depthSpinner;
	private JSpinner numberOfWorldsSpinner;
	private JComboBox<Limit> limitCombo;
	private JComboBox<BinaryOperator> operatorCombo;
	private JComboBox<StoryType> storyTypeCombo;
	private JCheckBox depthCheckbox;
	private JLabel worldLabel;
	private JLabel structureLabel;
	
	/**
	 * Create the panel.
	 */
	public StoryPanel(StoryScenario scenario) {
		super(null, scenario);
		structureLabel = new JLabel("Story Structure:");
		
		numberOfWorldsSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 100, 1));
		
		depthSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		storyTypeCombo = new JComboBox<StoryType>();
		storyTypeCombo.setModel(new DefaultComboBoxModel<StoryType>(StoryType.values()));
		
		operatorCombo = new JComboBox<BinaryOperator>();
		operatorCombo.setModel(new DefaultComboBoxModel<BinaryOperator>(BinaryOperator.values()));
		
		limitCombo = new JComboBox<Limit>();
		limitCombo.setModel(new DefaultComboBoxModel<Limit>(Limit.values()));
		
		worldLabel = new JLabel("# Worlds:");
		
		depthCheckbox = new JCheckBox("Set Depth?");
		//TODO: Continue here standardizing the groupLayouts;
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(structureLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(worldLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(depthCheckbox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(storyTypeCombo, 0, 343, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(limitCombo, 0, 151, Short.MAX_VALUE)
								.addComponent(operatorCombo, 0, 151, Short.MAX_VALUE))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(depthSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(numberOfWorldsSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(structureLabel)
						.addComponent(storyTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(numberOfWorldsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(worldLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(depthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(limitCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(depthCheckbox))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {numberOfWorldsSpinner, depthSpinner, storyTypeCombo, operatorCombo, limitCombo});
		setLayout(groupLayout);
		
		loadUIData();
		
		combos.add(storyTypeCombo);
		combos.add(operatorCombo);
		combos.add(limitCombo);
		
		spinners.add(depthSpinner);
		spinners.add(numberOfWorldsSpinner);
		
		checks.add(depthCheckbox);
	}
	
	@Override
	public void loadScenarioUIData(){
		storyTypeCombo.setSelectedItem(scenario.getStoryType());
		
		operatorCombo.setSelectedItem(scenario.getOperator());
		numberOfWorldsSpinner.setValue(scenario.getNumberOfWorlds());
		
		if(scenario.isDepthSet()){
			depthCheckbox.setSelected(true);
			limitCombo.setSelectedItem(scenario.getLimit());
			depthSpinner.setValue(scenario.getDepth());
		}
		else
			setDefaultDepth();
		
	}

	@Override
	public void loadDefaultUIData(){
		setDefaultStoryType();
		setDefaultWorlds();
		setDefaultDepth();
	}
	
	public void setDefaultStoryType() {
		storyTypeCombo.setSelectedIndex(-1);
	}
	
	public void setDefaultWorlds(){
		operatorCombo.setSelectedIndex(-1);
		numberOfWorldsSpinner.setValue(1);
	}
	
	public void setDefaultDepth() {
		depthCheckbox.setSelected(false);
		limitCombo.setSelectedItem(null);
		depthSpinner.setValue(1);
		
	}
	
	@Override
	public StoryScenario saveScenario() {
		
		if(scenario==null)
			scenario = new StoryScenario();
		
		scenario.setType((StoryType) storyTypeCombo.getSelectedItem());
		scenario.setNumberOfWorlds((BinaryOperator)operatorCombo.getSelectedItem(), (int) numberOfWorldsSpinner.getValue());
		scenario.setDepth((Limit)limitCombo.getSelectedItem(), (int) depthSpinner.getValue());
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		if(storyTypeCombo.getSelectedIndex()!=-1)
			return true;
		return false;
	}
	
	
}
