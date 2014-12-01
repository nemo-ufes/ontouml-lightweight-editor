package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Segment;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentBehavior;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentVariability;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentVariabilityScenario;

public class SegmentVariabilityPanel extends ScenarioPanel<SegmentVariabilityScenario> {

	private static final long serialVersionUID = 130209972599157206L;
	private JComboBox<SegmentBehavior> behaviorCombo;
	private JComboBox<SegmentVariability> variabilityCombo;
	private SegmentPanel segmentPanel;

	/**
	 * @wbp.parser.constructor
	 */
	public SegmentVariabilityPanel(OntoUMLParser parser) {
		this(parser,null);
	}
	
	public SegmentVariabilityPanel(OntoUMLParser parser, SegmentVariabilityScenario scenario) {
		super(parser, scenario);
		
		JLabel lblMinimum = new JLabel("Behavior:");
		
		JLabel lblMaximum = new JLabel("Variability:");
		
		behaviorCombo = new JComboBox<SegmentBehavior>();
		behaviorCombo.setModel(new DefaultComboBoxModel<SegmentBehavior>(SegmentBehavior.values()));
		behaviorCombo.addActionListener(behaviorAction);
		
		variabilityCombo = new JComboBox<SegmentVariability>();
		variabilityCombo.setModel(new DefaultComboBoxModel<SegmentVariability>(SegmentVariability.values()));
		
		segmentPanel = new SegmentPanel(parser);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(segmentPanel, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMinimum, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaximum, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(variabilityCombo, 0, 411, Short.MAX_VALUE)
								.addComponent(behaviorCombo, 0, 411, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(segmentPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(behaviorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMinimum))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(variabilityCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaximum))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

		combos.add(behaviorCombo);
		combos.add(variabilityCombo);
		combos.addAll(segmentPanel.getAllCombos());
	}

	private ActionListener behaviorAction = new ActionListener() { 

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(SegmentBehavior.CONST.equals(behaviorCombo.getSelectedItem()) || behaviorCombo.getSelectedItem()==null)
				variabilityCombo.setEnabled(false);
			else
				variabilityCombo.setEnabled(true);
		}
	};
	
	@Override
	public void loadDefaultUIData() {
		segmentPanel.loadDefaultUIData();
		
		behaviorCombo.setSelectedItem(null);
		loadDefaultVariability();
		
	}

	@Override
	public void loadScenarioUIData() {
		if(scenario==null)
			loadDefaultUIData();
		
		segmentPanel.loadSegmentData(scenario.getSegment());
		behaviorCombo.setSelectedItem(scenario.getBehavior());
		
		if(scenario.getBehavior()==null || scenario.getBehavior()==SegmentBehavior.CONST)
			loadDefaultVariability();
		else
			variabilityCombo.setSelectedItem(scenario.getVariability());
		
	}

	private void loadDefaultVariability() {
		variabilityCombo.setSelectedItem(null);
		variabilityCombo.setEnabled(false);
	}

	@Override
	public SegmentVariabilityScenario saveScenario() {
		
		if(scenario==null)
			scenario = new SegmentVariabilityScenario(parser, new Segment(parser));
		
		segmentPanel.saveSegmentData(scenario.getSegment());
		
		if(SegmentBehavior.CONST.equals(behaviorCombo.getSelectedItem()))
			scenario.setAsConstant();
		else{
			SegmentVariability sv = (SegmentVariability) variabilityCombo.getSelectedItem();
			switch (sv) {
			case INC:
				scenario.setAsIncremental();
				break;
			case DEC:
				scenario.setAsDecremental();
				break;
			case RAND:
				scenario.setAsRandom();
				break;
			}
		}
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		return segmentPanel.canSave() && 
				(SegmentBehavior.CONST.equals(behaviorCombo.getSelectedItem()) || 
						SegmentBehavior.VAR.equals(behaviorCombo.getSelectedItem()) && variabilityCombo.getSelectedItem() instanceof SegmentVariability);
	}
}
