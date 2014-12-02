package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Segment;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentType;

public class SegmentCardPanel extends JPanel {

	private static final long serialVersionUID = -8479937594134064990L;

	private static final String EMPTY = "EMPTY";
	
	private JPanel classPanel;
	private JPanel emptyPanel;
	private JLabel classLabel;
	private OntoUMLElementCombo classCombo;

	private JPanel associationPanel;

	private JLabel associationLabel;

	private OntoUMLElementCombo associationCombo;

	private JPanel stereotypePanel;

	private JLabel stereotypeLabel;

	private StereotypeCombo stereotypeCombo;
	
	
	public SegmentCardPanel(OntoUMLParser parser) {
		this(parser,"Class:","Assoc.:","Meta.:");
	}
	
	public SegmentCardPanel(OntoUMLParser parser, String classText, String associationText, String stereotypeText) {

		setLayout(new CardLayout(0, 0));
		
		emptyPanel = new JPanel();
		add(emptyPanel, EMPTY);
		
		classPanel = new JPanel();
		classLabel = new JLabel(classText);
		classCombo = new OntoUMLElementCombo(Class.class, parser);
		
		add(classPanel, SegmentType.CLASS.toString());
		
		associationPanel = new JPanel();
		associationLabel = new JLabel(associationText);
		associationCombo = new OntoUMLElementCombo(Association.class, parser);
		
		add(associationPanel, SegmentType.ASSOCIATION.toString());
		
		stereotypePanel = new JPanel();	
		stereotypeLabel = new JLabel(stereotypeText);
		stereotypeCombo = new StereotypeCombo();
		
		add(stereotypePanel, SegmentType.STEREOTYPE.toString());
		
		GroupLayout gl_associationPanel1 = new GroupLayout(associationPanel);
		gl_associationPanel1.setHorizontalGroup(
			gl_associationPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_associationPanel1.createSequentialGroup()
					.addComponent(associationLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(associationCombo, 0, 379, Short.MAX_VALUE))
		);
		gl_associationPanel1.setVerticalGroup(
			gl_associationPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_associationPanel1.createSequentialGroup()
					.addGroup(gl_associationPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(associationCombo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(associationLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		associationPanel.setLayout(gl_associationPanel1);
		
		GroupLayout gl_stereotypePanel1 = new GroupLayout(stereotypePanel);
		gl_stereotypePanel1.setHorizontalGroup(
			gl_stereotypePanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stereotypePanel1.createSequentialGroup()
					.addComponent(stereotypeLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stereotypeCombo, 0, 379, Short.MAX_VALUE))
		);
		gl_stereotypePanel1.setVerticalGroup(
				gl_stereotypePanel1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_stereotypePanel1.createSequentialGroup()
						.addGroup(gl_stereotypePanel1.createParallelGroup(Alignment.BASELINE)
							.addComponent(stereotypeCombo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(stereotypeLabel))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		stereotypePanel.setLayout(gl_stereotypePanel1);
		
		
		
		GroupLayout gl_classPanel1 = new GroupLayout(classPanel);
		gl_classPanel1.setHorizontalGroup(
			gl_classPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel1.createSequentialGroup()
					.addComponent(classLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(classCombo, 0, 379, Short.MAX_VALUE))
		);
		gl_classPanel1.setVerticalGroup(
			gl_classPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPanel1.createSequentialGroup()
					.addGroup(gl_classPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(classCombo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(classLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		classPanel.setLayout(gl_classPanel1);
	}
	
	
	
	public void loadDefaultUIData(){
		((CardLayout)getLayout()).show(this, EMPTY);
		classCombo.setSelectedItem(null);
		associationCombo.setSelectedItem(null);
		stereotypeCombo.setSelectedItem(null);
	}
	
	public void loadSegmentData(Segment segment) {
		
		SegmentType st = segment.getType();
		
		switch (st) {
		case ASSOCIATION:
			associationCombo.assignElement(segment.getClassifier());
			break;
		case CLASS:
			classCombo.assignElement(segment.getClassifier());
			break;
		case STEREOTYPE:
			stereotypeCombo.setSelectedItem(segment.getStereotype());
			break;
		default:
			break;
		}
	}
	
	public void saveSegmentData(Segment seg, SegmentType st) {
				
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
			seg.setAsAssociation((Association) associationCombo.getElement());
			break;
		case CLASS:
			seg.setAsClass((Class) classCombo.getElement());
			break;
		default:
			break;
		}
	}
		
	public ArrayList<JComboBox<?>> getAllCombos(){
		ArrayList<JComboBox<?>> list = new ArrayList<JComboBox<?>>();
		list.add(classCombo);
		list.add(stereotypeCombo);
		list.add(associationCombo);
		return list;
	}
	
	public void updateCards(SegmentType st) {
	
		CardLayout cl = (CardLayout) getLayout();
		
		if(st==null || SegmentType.POPULATION.equals(st) || SegmentType.OBJECT.equals(st) || SegmentType.PROPERTY.equals(st))
			cl.show(this, EMPTY);
		else
			cl.show(this, st.toString());
		
	}

	public EObject getSelectedClass() {
		return classCombo.getElement();
	}

	public EObject getSelectedAssociation() {
		return associationCombo.getElement();
	}

	public java.lang.Class<?> getStereotype() {
		return stereotypeCombo.getSelectedMetaClass();
	}
}
