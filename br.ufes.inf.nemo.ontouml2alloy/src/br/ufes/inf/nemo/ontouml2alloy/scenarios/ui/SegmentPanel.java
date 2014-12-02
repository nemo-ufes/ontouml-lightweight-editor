package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Segment;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentType;

public class SegmentPanel extends JPanel {
	
	private static final long serialVersionUID = -6998696116737351674L;
	
	private JComboBox<SegmentType> combo;
	private JLabel segmentLabel;
	private SegmentCardPanel cards;


	public SegmentPanel(OntoUMLParser parser){
		this(parser, "Segment:", "Class:", "Association:", "Stereotype:");
	}
	
	public SegmentPanel(OntoUMLParser parser, String segmentText, String classText, String associationText, String stereotypeText) {
		
		segmentLabel = new JLabel(segmentText);
		
		combo = new JComboBox<SegmentType>();
		combo.setModel(new DefaultComboBoxModel<SegmentType>(SegmentType.values()));
		combo.addActionListener(comboListener);
		
		cards = new SegmentCardPanel(parser,classText,associationText, stereotypeText);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(segmentLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(combo, 0, 359, Short.MAX_VALUE))
				.addComponent(cards, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(combo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(segmentLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(242, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {combo, cards});
		setLayout(groupLayout);

	}
	
	private ActionListener comboListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			cards.updateCards((SegmentType) combo.getSelectedItem());
		}		
	};
	
	public void loadDefaultUIData(){
		combo.setSelectedItem(null);
		cards.loadDefaultUIData();
	}
	
	public void loadSegmentData(Segment segment){
		combo.setSelectedItem(segment.getType());
		cards.loadSegmentData(segment);
		cards.updateCards(segment.getType());
	}
	
	public void saveSegmentData(Segment seg) {
		SegmentType st = (SegmentType) combo.getSelectedItem();
		cards.saveSegmentData(seg, st);
	}
	
	public ArrayList<JComboBox<?>> getAllCombos(){
		ArrayList<JComboBox<?>> list = new ArrayList<JComboBox<?>>();
		list.add(combo);
		list.addAll(cards.getAllCombos());
		return list;
	}
	
	public boolean canSave() {
		SegmentType st = (SegmentType) combo.getSelectedItem();
		
		if(st==SegmentType.CLASS && cards.getSelectedClass() instanceof RefOntoUML.Class)
			return true;
	
		if(st==SegmentType.ASSOCIATION && cards.getSelectedAssociation() instanceof Association)
			return true;
			
		if(st==SegmentType.STEREOTYPE && cards.getStereotype() instanceof Class<?>)
			return true;
		
		if(st==SegmentType.POPULATION || st==SegmentType.OBJECT || st==SegmentType.PROPERTY)
			return true;
		
		return false;
	}

}
