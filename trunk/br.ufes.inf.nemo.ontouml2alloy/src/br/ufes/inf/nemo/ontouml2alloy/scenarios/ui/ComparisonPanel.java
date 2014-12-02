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
import br.ufes.inf.nemo.ontouml2alloy.scenarios.BinaryOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ExtensionOperator;

public class ComparisonPanel extends ScenarioPanel<ComparisonScenario> {

	private static final long serialVersionUID = 374650019535929676L;
	private JComboBox<ComparisonType> comparisonTypeCombo;
	private JComboBox<Object> operatorCombo;
	
	private JLabel comparisonLabel;
	private JLabel operatorLabel;
	
	private SegmentPanel segmentPanel1;
	private SegmentPanel segmentPanel2;
	private WorldQuantificationPanel quantificationPanel;

	
	/**
	 * @wbp.parser.constructor
	 */
	public ComparisonPanel(OntoUMLParser parser) {
		this(parser, null);
	}
	
	public ComparisonPanel(OntoUMLParser parser, ComparisonScenario scenario) {
		super(parser, scenario);
		comparisonLabel = new JLabel("Comparison:");
		
		comparisonTypeCombo = new JComboBox<ComparisonType>();
		comparisonTypeCombo.setModel(new DefaultComboBoxModel<ComparisonType>(ComparisonType.values()));
		comparisonTypeCombo.addActionListener(comparisonTypeComboAction);

		operatorLabel = new JLabel("Op.:");
		operatorCombo = new JComboBox<Object>();
		segmentPanel1 = new SegmentPanel(parser,"Segment (L):", "Class (L):", "Assoc. (L):", "Meta. (L):");
		segmentPanel2 = new SegmentPanel(parser,"Segment (R):", "Class: (R)", "Assoc. (R):", "Meta. (R):");
		quantificationPanel = new WorldQuantificationPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(quantificationPanel, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addComponent(segmentPanel1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comparisonLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comparisonTypeCombo, 0, 181, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(operatorLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addComponent(segmentPanel2, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(segmentPanel1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(segmentPanel2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comparisonLabel)
						.addComponent(comparisonTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(operatorLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quantificationPanel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
				
		setLayout(groupLayout);
		
		combos.add(comparisonTypeCombo);
		combos.add(operatorCombo);
		
		combos.add(quantificationPanel.getCombo());
		spinners.add(quantificationPanel.getSpinner());
		combos.addAll(segmentPanel1.getAllCombos());
		combos.addAll(segmentPanel2.getAllCombos());
		
	}
	
	private ActionListener comparisonTypeComboAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ComparisonType ct = (ComparisonType) comparisonTypeCombo.getSelectedItem();
			
			if(ct==ComparisonType.EXT)
				setExtensionOperators();
			else
				setSizeOperators();
		}		
	};
	
	public void setSizeOperators(){
		operatorCombo.setModel(new DefaultComboBoxModel<Object>(BinaryOperator.values()));
	}
	
	public void setExtensionOperators(){
		operatorCombo.setModel(new DefaultComboBoxModel<Object>(ExtensionOperator.values()));
	}
	
	@Override
	public void loadDefaultUIData() {
		segmentPanel1.loadDefaultUIData();
		segmentPanel2.loadDefaultUIData();
		
		comparisonTypeCombo.setSelectedItem(null);
		operatorCombo.setModel(new DefaultComboBoxModel<>());
		
		quantificationPanel.loadDefautUIData();
	
	}

	@Override
	public void loadScenarioUIData() {
		
		segmentPanel1.loadSegmentData(scenario.getLeftSegment());
		segmentPanel2.loadSegmentData(scenario.getRightSegment());
		
		comparisonTypeCombo.setSelectedItem(scenario.getComparisonType());
		if(scenario.isSizeComparison())
			setSizeOperators();
		else
			setExtensionOperators();
		
		quantificationPanel.loadScenarioUIData(scenario);
	}

	@Override
	public ComparisonScenario saveScenario() {
		
		if(scenario==null)
			scenario = new ComparisonScenario(parser);
		
		segmentPanel1.saveSegmentData(scenario.getLeftSegment());
		segmentPanel2.saveSegmentData(scenario.getRightSegment());
		
		ComparisonType ct = (ComparisonType) comparisonTypeCombo.getSelectedItem();
		if(ct==ComparisonType.EXT)
			scenario.setExtesionComparison((ExtensionOperator) operatorCombo.getSelectedItem());
		else
			scenario.setSizeComparison((BinaryOperator) operatorCombo.getSelectedItem());
		
		quantificationPanel.saveQuantificationData(scenario);
		
		return scenario;
	}
	
	@Override
	public boolean canSave() {
		return segmentPanel1.canSave() && segmentPanel2.canSave() && comparisonTypeCombo.getSelectedIndex()!=-1
				&& operatorCombo.getSelectedIndex()!=-1 && quantificationPanel.canSave();
	}
	
}
