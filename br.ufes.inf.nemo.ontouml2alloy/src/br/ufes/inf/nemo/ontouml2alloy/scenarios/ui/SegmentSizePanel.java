package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Segment;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentSizeScenario;

public class SegmentSizePanel extends ScenarioPanel<SegmentSizeScenario> {
	
	private static final long serialVersionUID = 7760732581730900034L;
	private JSpinner minimumSpinner;
	private JSpinner maximumSpinner;
	
	private SegmentPanel segmentPanel;
	private WorldQuantificationPanel quantificationpanel;

	/**
	 * @wbp.parser.constructor
	 */
	public SegmentSizePanel(OntoUMLParser parser){
		this(parser, null);
	}
	
	public SegmentSizePanel(OntoUMLParser parser, SegmentSizeScenario scenario) {
		super(parser, scenario);
		
		JLabel lblMinimum = new JLabel("Minimum:");
		
		JLabel lblMaximum = new JLabel("Maximum:");
		minimumSpinner = new JSpinner();
		
		maximumSpinner = new JSpinner();
		
		segmentPanel = new SegmentPanel(parser);
		
		quantificationpanel = new WorldQuantificationPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(quantificationpanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblMaximum, GroupLayout.PREFERRED_SIZE, 74, Short.MAX_VALUE)
								.addComponent(lblMinimum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(maximumSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
								.addComponent(minimumSpinner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
						.addComponent(segmentPanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(segmentPanel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMinimum)
						.addComponent(minimumSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximum)
						.addComponent(maximumSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quantificationpanel, GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {minimumSpinner, maximumSpinner});
		setLayout(groupLayout);
		combos.addAll(segmentPanel.getAllCombos());
		combos.add(quantificationpanel.getCombo());
		spinners.add(quantificationpanel.getSpinner());
		spinners.add(minimumSpinner);
		spinners.add(maximumSpinner);
	}

	@Override
	public void loadDefaultUIData() {
				
		minimumSpinner.setValue(0);
		maximumSpinner.setValue(1);
		
		segmentPanel.loadDefaultUIData();
		quantificationpanel.loadDefautUIData();
		
	}

	@Override
	public void loadScenarioUIData() {
		
		if(scenario==null){
			loadDefaultUIData();
			return;
		}
		
		Segment s = scenario.getSegment();
		segmentPanel.loadSegmentData(s);
		
		maximumSpinner.setValue(scenario.getMaximum());
		minimumSpinner.setValue(scenario.getMinimum());
		
		quantificationpanel.loadScenarioUIData(scenario);
	}
	
	@Override
	public SegmentSizeScenario saveScenario() {
		
		if(scenario==null)
			scenario = new SegmentSizeScenario(parser);
		
		segmentPanel.saveSegmentData(scenario.getSegment());
		
		scenario.setMaximum((int) maximumSpinner.getValue());
		scenario.setMinimum((int) minimumSpinner.getValue());
		
		quantificationpanel.saveQuantificationData(scenario);
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		return quantificationpanel.canSave() && segmentPanel.canSave() && maximumSpinner.getValue()!=null && minimumSpinner.getValue()!=null;
	}
}
