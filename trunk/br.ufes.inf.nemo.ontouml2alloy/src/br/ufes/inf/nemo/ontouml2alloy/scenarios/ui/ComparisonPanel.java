package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Stereotype;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.BinaryOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ExtensionOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Segment;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.WorldQuantification;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.WorldQuantificationType;

public class ComparisonPanel extends ScenarioPanel<ComparisonScenario> {

	private static final long serialVersionUID = 391650019535929676L;
	private static final String EMPTY = "EMPTY";
	
	private JLabel lblClass;
	private JLabel lblMinimum;
	private JComboBox<SegmentType> segmentCombo1;
	private JComboBox<ComparisonType> comparisonTypeCombo;
	private JComboBox<WorldQuantificationType> quantificationCombo;
	private JComboBox operatorCombo;
	private JComboBox<SegmentType> segmentCombo2;
	private JComboBox<Object> classCombo1;
	private JLabel classLabel1;
	
	private JPanel cards1;
	private JPanel associationPanel1;
	private JPanel stereotypePanel1;
	private JPanel classPanel1;
	private JLabel associationLabel1;
	private JComboBox<Object> associationCombo1;
	private JLabel stereotypeLabel1;
	private StereotypeCombo stereotypeCombo1;
	
	private ArrayList<OntoUMLElement> classes;
	private ArrayList<OntoUMLElement> associations;
	private JPanel emptyPanel2;
	private JPanel classPanel2;
	private JLabel classLabel2;
	private JComboBox<Object> classCombo2;
	private JPanel associationPanel2;
	private JLabel associationLabel2;
	private JComboBox<Object> associationCombo2;
	private JPanel stereotypePanel2;
	private StereotypeCombo stereotypeCombo2;
	private JLabel stereotypeLabel2;
	private JPanel cards2;
	private JPanel emptyPanel1;
	
	/**
	 * @wbp.parser.constructor
	 */
	public ComparisonPanel(OntoUMLParser parser) {
		this(parser, null);
	}
	
	public ComparisonPanel(OntoUMLParser parser, ComparisonScenario scenario) {
		super(parser, scenario);
		
		classes = getClasses();
		associations = getAssociations();
		
		lblClass = new JLabel("Segment 1:");
		
		lblMinimum = new JLabel("Segment 2:");
		
		JLabel lblMaximum = new JLabel("Comparison:");
		
		JLabel lblConstraintType = new JLabel("Context:");
		
		quantificationCombo = new JComboBox<WorldQuantificationType>();
		quantificationCombo.setModel(new DefaultComboBoxModel<WorldQuantificationType>(WorldQuantificationType.values()));
		
		segmentCombo1 = new JComboBox<SegmentType>();
		segmentCombo1.setModel(new DefaultComboBoxModel<SegmentType>(SegmentType.values()));
		segmentCombo1.addActionListener(segment1ComboAction);
		
		segmentCombo2 = new JComboBox<SegmentType>();
		segmentCombo2.setModel(new DefaultComboBoxModel<SegmentType>(SegmentType.values()));
		segmentCombo2.addActionListener(segment2ComboAction);
		
		comparisonTypeCombo = new JComboBox<ComparisonType>();
		comparisonTypeCombo.setModel(new DefaultComboBoxModel<ComparisonType>(ComparisonType.values()));
		comparisonTypeCombo.addActionListener(comparisonTypeComboAction);
		
		JLabel lblN = new JLabel("n =");
		
		nSpinner = new JSpinner();

		
		JLabel lblMode = new JLabel("Op.:");
		
		operatorCombo = new JComboBox<>();
		
		cards1 = new JPanel();
		cards1.setLayout(new CardLayout(0, 0));
		
		cards2 = new JPanel();
		cards2.setLayout(new CardLayout(0, 0));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(cards1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblConstraintType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblClass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblMinimum, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
								.addComponent(lblMaximum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(segmentCombo1, 0, 390, Short.MAX_VALUE)
								.addComponent(segmentCombo2, Alignment.LEADING, 0, 390, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(quantificationCombo, 0, 197, Short.MAX_VALUE)
										.addComponent(comparisonTypeCombo, Alignment.TRAILING, 0, 197, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblMode, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblN, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(nSpinner)
										.addComponent(operatorCombo, 0, 159, Short.MAX_VALUE)))))
						.addComponent(cards2, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(segmentCombo1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClass))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMinimum)
						.addComponent(segmentCombo2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximum)
						.addComponent(comparisonTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMode))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(quantificationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConstraintType)
						.addComponent(lblN))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {quantificationCombo, segmentCombo1, segmentCombo2, comparisonTypeCombo, nSpinner, operatorCombo});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblClass, lblMinimum, lblMaximum, lblConstraintType});
		
		emptyPanel1 = new JPanel();
		cards1.add(emptyPanel1, EMPTY);
		
		classPanel1 = new JPanel();
		classLabel1 = new JLabel("Class:");
		classCombo1 = new JComboBox<Object>();
		//TODO: can't change option in the combo.
		
		classCombo1.setModel(new DefaultComboBoxModel<Object>(classes.toArray()));
		addComponentsAndApplyLayout(classPanel1, classLabel1, classCombo1);
		cards1.add(classPanel1, SegmentType.CLASS.toString());
		
		associationPanel1 = new JPanel();
		associationLabel1 = new JLabel("Assoc.:");
		associationCombo1 = new JComboBox<Object>();
		addComponentsAndApplyLayout(associationPanel1, associationLabel1, associationCombo1);
		associationCombo1.setModel(new DefaultComboBoxModel<Object>(associations.toArray()));
		cards1.add(associationPanel1, SegmentType.ASSOCIATION.toString());
		
		stereotypePanel1 = new JPanel();	
		stereotypeLabel1 = new JLabel("Meta.:");
		stereotypeCombo1 = new StereotypeCombo();
		addComponentsAndApplyLayout(stereotypePanel1, stereotypeLabel1, stereotypeCombo1);
		cards1.add(stereotypePanel1, SegmentType.STEREOTYPE.toString());
		
		emptyPanel2 = new JPanel();
		cards2.add(emptyPanel2, EMPTY);
		
		classPanel2 = new JPanel();
		classLabel2 = new JLabel("Class:");
		classCombo2 = new JComboBox<Object>();
		classCombo2.setModel(new DefaultComboBoxModel<Object>(classes.toArray()));
		addComponentsAndApplyLayout(classPanel2, classLabel2, classCombo2);
		cards2.add(classPanel2, SegmentType.CLASS.toString());
		
		associationPanel2 = new JPanel();
		associationLabel2 = new JLabel("Assoc.:");
		associationCombo2 = new JComboBox<Object>();
		associationCombo2.setModel(new DefaultComboBoxModel<Object>(associations.toArray()));
		addComponentsAndApplyLayout(associationPanel2, associationLabel2, associationCombo2);
		cards2.add(associationPanel2, SegmentType.ASSOCIATION.toString());
		
		stereotypePanel2 = new JPanel();	
		stereotypeLabel2 = new JLabel("Meta.:");
		stereotypeCombo2 = new StereotypeCombo();
		addComponentsAndApplyLayout(stereotypePanel2, stereotypeLabel2, stereotypeCombo2);
		cards2.add(stereotypePanel2, SegmentType.STEREOTYPE.toString());
		
		setLayout(groupLayout);
		
		combos.add(comparisonTypeCombo);
		combos.add(operatorCombo);
		combos.add(quantificationCombo);
		spinners.add(nSpinner);
		
		combos.add(segmentCombo1);
		combos.add(associationCombo1);
		combos.add(classCombo1);
		combos.add(stereotypeCombo1);
		
		combos.add(segmentCombo2);
		combos.add(associationCombo2);
		combos.add(classCombo2);
		combos.add(stereotypeCombo2);
		
		
		
		
	}

	private void addComponentsAndApplyLayout(JPanel panel, JLabel label, JComboBox<?> combo) {
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(combo, 0, 390, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(groupLayout);
	}
	
	

	private ActionListener segment1ComboAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateCards(cards1,(SegmentType) segmentCombo1.getSelectedItem());
		}		
	};
	
	private ActionListener segment2ComboAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateCards(cards2,(SegmentType) segmentCombo2.getSelectedItem());
		}		
	};
	
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
	private JSpinner nSpinner;
	
	private void updateCards(JPanel cards, SegmentType st) {
		
		CardLayout cl = (CardLayout) cards.getLayout();
		
		if(st==null || SegmentType.POPULATION.equals(st) || SegmentType.OBJECT.equals(st) || SegmentType.PROPERTY.equals(st))
			cl.show(cards, EMPTY);
		else
			cl.show(cards, st.toString());
		
	}
	
	public ArrayList<OntoUMLElement> getClasses(){
		return convertAll(parser.getAllInstances(Class.class));
	}
	
	private ArrayList<OntoUMLElement> getAssociations() {
		return convertAll(parser.getAllInstances(Association.class));
	}
	
	public void setSizeOperators(){
		operatorCombo.setModel(new DefaultComboBoxModel<>(BinaryOperator.values()));
	}
	
	public void setExtensionOperators(){
		operatorCombo.setModel(new DefaultComboBoxModel<>(ExtensionOperator.values()));
	}
	
	@Override
	public void loadDefaultUIData() {
		segmentCombo1.setSelectedItem(null);
		((CardLayout)cards1.getLayout()).show(cards1, EMPTY);
		segmentCombo2.setSelectedItem(null);
		((CardLayout)cards2.getLayout()).show(cards2, EMPTY);
		comparisonTypeCombo.setSelectedItem(null);
		quantificationCombo.setSelectedItem(null);
		operatorCombo.setModel(new DefaultComboBoxModel<>());
		nSpinner.setValue(1);
		nSpinner.setEnabled(false);
	}

	@Override
	public void loadScenarioUIData() {
		SegmentType st = scenario.getLeftSegment().getType();
		segmentCombo1.setSelectedItem(st);
		updateCards(cards1, st);
		
		st = scenario.getRightSegment().getType();
		segmentCombo2.setSelectedItem(st);
		updateCards(cards2, st);
		
		comparisonTypeCombo.setSelectedItem(scenario.getComparisonType());
		if(scenario.isSizeComparison())
			setSizeOperators();
		else
			setExtensionOperators();
		
		WorldQuantification wq = scenario.getWorldQuantification();
		quantificationCombo.setSelectedItem(wq.getType());
		if(wq.isNumeric())
			nSpinner.setValue(wq.getValue());	
	}

	@Override
	public ComparisonScenario saveScenario() {
		
		if(scenario==null)
			scenario = new ComparisonScenario(parser);
		
		loadSegmentUIData(scenario.getLeftSegment(), segmentCombo1, stereotypeCombo1, associationCombo1, classCombo1);
		loadSegmentUIData(scenario.getRightSegment(), segmentCombo2, stereotypeCombo2, associationCombo2, classCombo2);
		
		ComparisonType ct = (ComparisonType) comparisonTypeCombo.getSelectedItem();
		if(ct==ComparisonType.EXT)
			scenario.setExtesionComparison((ExtensionOperator) operatorCombo.getSelectedItem());
		else
			scenario.setSizeComparison((BinaryOperator) operatorCombo.getSelectedItem());
		
		
		WorldQuantification wq = scenario.getWorldQuantification();
		wq.setType((WorldQuantificationType) quantificationCombo.getSelectedItem());
		if(wq.isNumeric())
			wq.setValue((int) nSpinner.getValue());	
		
		return scenario;
	}

	private void loadSegmentUIData(Segment seg, JComboBox<SegmentType> segmentCombo, StereotypeCombo stereotypeCombo, JComboBox<Object> associationCombo, JComboBox<Object> classCombo) {
		SegmentType st = (SegmentType) segmentCombo.getSelectedItem();
				
		switch (st) {
		case POPULATION:
			seg.setAsPopulation();
			break;
		case OBJECT:
			seg.setAsObject();
			break;
		case PROPERTY:
			seg.setAsProperty();
			break;
		case STEREOTYPE:
			seg.setAsStereotype(stereotypeCombo.getSelectedMetaClass()); 
			break;
		case ASSOCIATION:
			seg.setAsAssociation(getSelectedAssociation(associationCombo));
			break;
		case CLASS:
			seg.setAsClass(getSelectedClass(classCombo));
			break;
		default:
			break;
		}
	}

	public Association getSelectedAssociation(JComboBox<Object> combo){
		OntoUMLElement e = (OntoUMLElement) combo.getSelectedItem();
		return (Association) e.getClassifier();
	}
	
	public Class getSelectedClass(JComboBox<Object> combo){
		OntoUMLElement e = (OntoUMLElement) combo.getSelectedItem();
		return (Class) e.getClassifier();
	}
	
	@Override
	public boolean canSave() {
		//TODO: finish method
		return true;		
	}
}
