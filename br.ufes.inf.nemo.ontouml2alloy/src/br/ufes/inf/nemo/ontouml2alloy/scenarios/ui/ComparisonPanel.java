package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.BinaryOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ExtensionOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Segment;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.WorldQuantification;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.WorldQuantificationType;

public class ComparisonPanel extends ScenarioPanel<ComparisonScenario> {

	private static final long serialVersionUID = 374650019535929676L;
	private static final String EMPTY = "EMPTY";
	
	
	
	private JComboBox<SegmentType> segmentCombo1;
	private JComboBox<SegmentType> segmentCombo2;
	private JComboBox<ComparisonType> comparisonTypeCombo;
	private JComboBox<Object> operatorCombo;
	private JComboBox<WorldQuantificationType> quantificationCombo;
	private JSpinner nSpinner;
	
	private OntoUMLElementCombo classCombo1;
	private OntoUMLElementCombo associationCombo1;
	private OntoUMLElementCombo classCombo2;
	private OntoUMLElementCombo associationCombo2;
	private StereotypeCombo stereotypeCombo2;
	private StereotypeCombo stereotypeCombo1;
	
	private JLabel comparisonLabel;
	private JLabel contextLabel;
	private JLabel nLabel;
	private JLabel operatorLabel;
	private JLabel segmentLabel1;
	private JLabel segmentLabel2;
	private JLabel associationLabel1;
	private JLabel associationLabel2;
	private JLabel stereotypeLabel1;
	private JLabel stereotypeLabel2;
	private JLabel classLabel1;
	private JLabel classLabel2;
	
	private JPanel cards1;
	private JPanel associationPanel1;
	private JPanel stereotypePanel1;
	private JPanel classPanel1;
	private JPanel emptyPanel2;
	private JPanel classPanel2;
	private JPanel associationPanel2;
	private JPanel stereotypePanel2;
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
		
		segmentLabel1 = new JLabel("Segment 1:");
		segmentLabel2 = new JLabel("Segment 2:");
		comparisonLabel = new JLabel("Comparison:");
		contextLabel = new JLabel("Context:");
		
		quantificationCombo = new JComboBox<WorldQuantificationType>();
		quantificationCombo.setModel(new DefaultComboBoxModel<WorldQuantificationType>(WorldQuantificationType.values()));
		quantificationCombo.addActionListener(quantificationAction);
		
		segmentCombo1 = new JComboBox<SegmentType>();
		segmentCombo1.setModel(new DefaultComboBoxModel<SegmentType>(SegmentType.values()));
		segmentCombo1.addActionListener(segment1ComboAction);
		
		segmentCombo2 = new JComboBox<SegmentType>();
		segmentCombo2.setModel(new DefaultComboBoxModel<SegmentType>(SegmentType.values()));
		segmentCombo2.addActionListener(segment2ComboAction);
		
		comparisonTypeCombo = new JComboBox<ComparisonType>();
		comparisonTypeCombo.setModel(new DefaultComboBoxModel<ComparisonType>(ComparisonType.values()));
		comparisonTypeCombo.addActionListener(comparisonTypeComboAction);
		
		nLabel = new JLabel("n =");
		
		nSpinner = new JSpinner();

		operatorLabel = new JLabel("Op.:");
		
		operatorCombo = new JComboBox<Object>();
		
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
								.addComponent(contextLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(segmentLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(segmentLabel2, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
								.addComponent(comparisonLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
										.addComponent(operatorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(nLabel, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
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
						.addComponent(segmentLabel1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(segmentLabel2)
						.addComponent(segmentCombo2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comparisonLabel)
						.addComponent(comparisonTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(operatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(operatorLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(quantificationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(contextLabel)
						.addComponent(nLabel))
					.addContainerGap(109, Short.MAX_VALUE))
		);
			
		emptyPanel1 = new JPanel();
		cards1.add(emptyPanel1, EMPTY);
		
		classPanel1 = new JPanel();
		
		classLabel1 = new JLabel("Class:");
		classCombo1 = new OntoUMLElementCombo(Class.class, parser);
		
		
		cards1.add(classPanel1, SegmentType.CLASS.toString());
		
		associationPanel1 = new JPanel();
		associationLabel1 = new JLabel("Assoc.:");
		associationCombo1 = new OntoUMLElementCombo(Association.class, parser);
		
		cards1.add(associationPanel1, SegmentType.ASSOCIATION.toString());
		
		stereotypePanel1 = new JPanel();	
		stereotypeLabel1 = new JLabel("Meta.:");
		stereotypeCombo1 = new StereotypeCombo();
		
		GroupLayout groupLayout3 = new GroupLayout(stereotypePanel1);

		stereotypePanel1.setLayout(groupLayout3);
		
		cards1.add(stereotypePanel1, SegmentType.STEREOTYPE.toString());
		
		emptyPanel2 = new JPanel();
		cards2.add(emptyPanel2, EMPTY);
		GroupLayout gl_emptyPanel2 = new GroupLayout(emptyPanel2);
		gl_emptyPanel2.setHorizontalGroup(
			gl_emptyPanel2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 476, Short.MAX_VALUE)
		);
		gl_emptyPanel2.setVerticalGroup(
			gl_emptyPanel2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 26, Short.MAX_VALUE)
		);
		emptyPanel2.setLayout(gl_emptyPanel2);
		
		classPanel2 = new JPanel();
		classLabel2 = new JLabel("Class:");
		classCombo2 = new OntoUMLElementCombo(Class.class, parser); 

		GroupLayout groupLayout4 = new GroupLayout(classPanel2);

		classPanel2.setLayout(groupLayout4);
		
		cards2.add(classPanel2, SegmentType.CLASS.toString());
		
		associationPanel2 = new JPanel();
		associationLabel2 = new JLabel("Assoc.:");
		associationCombo2 = new OntoUMLElementCombo(Association.class, parser); 
		
		GroupLayout groupLayout5 = new GroupLayout(associationPanel2);

		associationPanel2.setLayout(groupLayout5);
		
		cards2.add(associationPanel2, SegmentType.ASSOCIATION.toString());
		
		stereotypePanel2 = new JPanel();	
		stereotypeLabel2 = new JLabel("Meta.:");
		stereotypeCombo2 = new StereotypeCombo();

		GroupLayout groupLayout6 = new GroupLayout(stereotypePanel2);

		stereotypePanel2.setLayout(groupLayout6);
		
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
		
		
		GroupLayout gl_associationPanel1 = new GroupLayout(associationPanel1);
		gl_associationPanel1.setHorizontalGroup(
			gl_associationPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_associationPanel1.createSequentialGroup()
					.addComponent(associationLabel1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(associationCombo1, 0, 379, Short.MAX_VALUE))
		);
		gl_associationPanel1.setVerticalGroup(
			gl_associationPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_associationPanel1.createSequentialGroup()
					.addGroup(gl_associationPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(associationCombo1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(associationLabel1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		associationPanel1.setLayout(gl_associationPanel1);
		
		GroupLayout gl_stereotypePanel1 = new GroupLayout(stereotypePanel1);
		gl_stereotypePanel1.setHorizontalGroup(
			gl_stereotypePanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stereotypePanel1.createSequentialGroup()
					.addComponent(stereotypeLabel1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stereotypeCombo1, 0, 379, Short.MAX_VALUE))
		);
		gl_stereotypePanel1.setVerticalGroup(
				gl_stereotypePanel1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_stereotypePanel1.createSequentialGroup()
						.addGroup(gl_stereotypePanel1.createParallelGroup(Alignment.BASELINE)
							.addComponent(stereotypeCombo1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(stereotypeLabel1))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		stereotypePanel1.setLayout(gl_stereotypePanel1);
		
		
		
		GroupLayout gl_classPanel1 = new GroupLayout(classPanel1);
		gl_classPanel1.setHorizontalGroup(
			gl_classPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel1.createSequentialGroup()
					.addComponent(classLabel1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(classCombo1, 0, 379, Short.MAX_VALUE))
		);
		gl_classPanel1.setVerticalGroup(
			gl_classPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel1.createSequentialGroup()
					.addGroup(gl_classPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(classCombo1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(classLabel1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		classPanel1.setLayout(gl_classPanel1);
		
		GroupLayout gl_associationPanel2 = new GroupLayout(associationPanel2);
		gl_associationPanel2.setHorizontalGroup(
			gl_associationPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_associationPanel2.createSequentialGroup()
					.addComponent(associationLabel2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(associationCombo2, 0, 379, Short.MAX_VALUE))
		);
		gl_associationPanel2.setVerticalGroup(
				gl_associationPanel2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_associationPanel2.createSequentialGroup()
						.addGroup(gl_associationPanel2.createParallelGroup(Alignment.BASELINE)
							.addComponent(associationCombo2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(associationLabel2))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		associationPanel2.setLayout(gl_associationPanel2);
		
		GroupLayout gl_stereotypePanel2 = new GroupLayout(stereotypePanel2);
		gl_stereotypePanel2.setHorizontalGroup(
			gl_stereotypePanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stereotypePanel2.createSequentialGroup()
					.addComponent(stereotypeLabel2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stereotypeCombo2, 0, 379, Short.MAX_VALUE))
		);
		gl_stereotypePanel2.setVerticalGroup(
				gl_stereotypePanel2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_stereotypePanel2.createSequentialGroup()
						.addGroup(gl_stereotypePanel2.createParallelGroup(Alignment.BASELINE)
							.addComponent(stereotypeCombo2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(stereotypeLabel2))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		stereotypePanel2.setLayout(gl_stereotypePanel2);
		
		
		
		GroupLayout gl_classPanel2 = new GroupLayout(classPanel2);
		gl_classPanel2.setHorizontalGroup(
			gl_classPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel2.createSequentialGroup()
					.addComponent(classLabel2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(classCombo2, 0, 379, Short.MAX_VALUE))
		);
		gl_classPanel2.setVerticalGroup(
			gl_classPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel2.createSequentialGroup()
					.addGroup(gl_classPanel2.createParallelGroup(Alignment.BASELINE)
						.addComponent(classCombo2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(classLabel2))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		classPanel2.setLayout(gl_classPanel2);
		
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
	
	private ActionListener quantificationAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			enableSpinner();
		}
	};
	
	
	private void enableSpinner() {
			boolean requiresNumber = false;
			
			WorldQuantificationType wqt = ((WorldQuantificationType)quantificationCombo.getSelectedItem());
			
			if(wqt instanceof WorldQuantificationType)
				requiresNumber = wqt.isNumeric();
			
			nSpinner.setEnabled(requiresNumber);
		}		
	
	private void updateCards(JPanel cards, SegmentType st) {
		
		CardLayout cl = (CardLayout) cards.getLayout();
		
		if(st==null || SegmentType.POPULATION.equals(st) || SegmentType.OBJECT.equals(st) || SegmentType.PROPERTY.equals(st))
			cl.show(cards, EMPTY);
		else
			cl.show(cards, st.toString());
		
	}
		
	public void setSizeOperators(){
		operatorCombo.setModel(new DefaultComboBoxModel<Object>(BinaryOperator.values()));
	}
	
	public void setExtensionOperators(){
		operatorCombo.setModel(new DefaultComboBoxModel<Object>(ExtensionOperator.values()));
	}
	
	@Override
	public void loadDefaultUIData() {
		segmentCombo1.setSelectedItem(null);
		((CardLayout)cards1.getLayout()).show(cards1, EMPTY);
		classCombo1.setSelectedItem(null);
		associationCombo1.setSelectedItem(null);
		stereotypeCombo1.setSelectedItem(null);
		
		segmentCombo2.setSelectedItem(null);
		((CardLayout)cards2.getLayout()).show(cards2, EMPTY);
		classCombo2.setSelectedItem(null);
		associationCombo2.setSelectedItem(null);
		stereotypeCombo2.setSelectedItem(null);
		
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
		enableSpinner();	
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

	private void loadSegmentUIData(Segment seg, JComboBox<SegmentType> segmentCombo, StereotypeCombo stereotypeCombo, OntoUMLElementCombo associationCombo, OntoUMLElementCombo classCombo) {
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
			seg.setAsAssociation((Association) associationCombo.getSelectedElement());
			break;
		case CLASS:
			seg.setAsClass((Class) classCombo.getSelectedElement());
			break;
		default:
			break;
		}
	}

	
	@Override
	public boolean canSave() {
		//TODO: finish method
		return true;		
	}
}
