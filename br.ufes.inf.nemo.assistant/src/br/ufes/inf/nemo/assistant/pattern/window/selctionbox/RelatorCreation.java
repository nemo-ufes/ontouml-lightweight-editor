package br.ufes.inf.nemo.assistant.pattern.window.selctionbox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import javax.swing.UIManager;

public class RelatorCreation extends ClassSelectionPanel {
	private JTextField genEdit0;
	private JTextField spcEdit1;
	
	private JCheckBox genChk0;
	private JCheckBox spcChk1;
	
	private JComboBox<String> genCB0;
	private JComboBox<String> spcCB1;
	private JComboBox<String> typesCB0;
	private JLabel lblSpecific_1;
	private JTextField spcEdit0;
	private JComboBox<String> spcCB0;
	private JCheckBox spcChk0;
	private JLabel lblGeneral;
	private JTextField genEdit1;
	private JLabel lblOntoumlType1;
	private JComboBox<String> genCB1;
	private JCheckBox genChk1;
	private JComboBox<String> typesCB1;
	private JPanel relatorPanel;
	private JLabel lblRelator;
	private JTextField relEdit0;
	private JCheckBox relChk0;
	private JComboBox<String> relCB0;
	
	public RelatorCreation(OntoUMLParser parser) {
		setLayout(null);

		JPanel rigidSortalPanel = new JPanel();
		rigidSortalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Left RigidSortal classes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rigidSortalPanel.setBounds(0, 11, 435, 116);
		add(rigidSortalPanel);
		rigidSortalPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("General0:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 22, 59, 14);
		rigidSortalPanel.add(lblNewLabel);

		genEdit0 = new JTextField();
		genEdit0.setBounds(109, 19, 149, 20);
		rigidSortalPanel.add(genEdit0);
		genEdit0.setColumns(10);

		genCB0 = new JComboBox<String>();
		genCB0.setBounds(109, 19, 149, 20);
		rigidSortalPanel.add(genCB0);

		JLabel lblOntoumlType0 = new JLabel("OntoUML type:");
		lblOntoumlType0.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOntoumlType0.setBounds(20, 47, 97, 14);
		rigidSortalPanel.add(lblOntoumlType0);

		genChk0 = new JCheckBox("Reuse class?");
		genChk0.setBounds(271, 18, 97, 23);
		rigidSortalPanel.add(genChk0);

		typesCB0 = new JComboBox<String>();
		typesCB0.setBounds(109, 44, 149, 20);
		rigidSortalPanel.add(typesCB0);
		
		JPanel subkindPanel = new JPanel();
		subkindPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Right RigidSortal classes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		subkindPanel.setBounds(0, 144, 435, 121);
		add(subkindPanel);
		subkindPanel.setLayout(null);

		JLabel lblSpecific = new JLabel("Specific1:");
		lblSpecific.setBounds(20, 89, 56, 14);
		lblSpecific.setFont(new Font("Tahoma", Font.BOLD, 11));
		subkindPanel.add(lblSpecific);

		spcEdit1 = new JTextField();
		spcEdit1.setBounds(109, 86, 149, 20);
		subkindPanel.add(spcEdit1);
		spcEdit1.setColumns(10);

		spcCB1 = new JComboBox<String>();
		spcCB1.setBounds(109, 86, 149, 20);
		subkindPanel.add(spcCB1);

		spcChk1 = new JCheckBox("Reuse class?");
		spcChk1.setBounds(271, 85, 97, 23);
		subkindPanel.add(spcChk1);

		lblGeneral = new JLabel("General1:");
		lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGeneral.setBounds(20, 27, 67, 14);
		subkindPanel.add(lblGeneral);
		
		genEdit1 = new JTextField();
		genEdit1.setBounds(109, 24, 149, 20);
		subkindPanel.add(genEdit1);
		genEdit1.setColumns(10);
		
		lblOntoumlType1 = new JLabel("OntoUML type:");
		lblOntoumlType1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOntoumlType1.setBounds(20, 52, 88, 14);
		subkindPanel.add(lblOntoumlType1);
		
		genCB1 = new JComboBox();
		genCB1.setBounds(109, 24, 149, 20);
		subkindPanel.add(genCB1);
		
		genChk1 = new JCheckBox("Reuse class?");
		genChk1.setBounds(271, 23, 97, 23);
		subkindPanel.add(genChk1);
		
		typesCB1 = new JComboBox();
		typesCB1.setBounds(109, 49, 149, 20);
		subkindPanel.add(typesCB1);

		genChk0.addActionListener(getCheckBoxActionListener(typesCB0,lblOntoumlType0));
		
		lblSpecific_1 = new JLabel("Specific0:");
		lblSpecific_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecific_1.setBounds(20, 84, 59, 14);
		rigidSortalPanel.add(lblSpecific_1);
		
		spcEdit0 = new JTextField();
		spcEdit0.setBounds(109, 81, 149, 20);
		rigidSortalPanel.add(spcEdit0);
		spcEdit0.setColumns(10);
		
		spcCB0 = new JComboBox();
		spcCB0.setBounds(109, 81, 149, 20);
		rigidSortalPanel.add(spcCB0);
		
		spcChk0 = new JCheckBox("Reuse class?");
		spcChk0.setBounds(271, 80, 97, 23);
		rigidSortalPanel.add(spcChk0);
		spcChk1.addActionListener(getCheckBoxActionListener());
		
		relatorPanel = new JPanel();
		relatorPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Relator class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		relatorPanel.setBounds(0, 274, 435, 60);
		add(relatorPanel);
		relatorPanel.setLayout(null);
		
		lblRelator = new JLabel("Relator0:");
		lblRelator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelator.setBounds(20, 23, 53, 14);
		relatorPanel.add(lblRelator);
		
		relEdit0 = new JTextField();
		relEdit0.setBounds(109, 20, 149, 20);
		relatorPanel.add(relEdit0);
		relEdit0.setColumns(10);
		
		relChk0 = new JCheckBox("Reuse class?");
		relChk0.setBounds(272, 19, 97, 23);
		relatorPanel.add(relChk0);
		
		relCB0 = new JComboBox();
		relCB0.setBounds(109, 20, 149, 20);
		relatorPanel.add(relCB0);
		
		hashChkCombo.put(genChk0, genCB0);
		hashChkEdit.put(genChk0, genEdit0);
		hashEditCombo.put(genEdit0, typesCB0);

		hashChkCombo.put(spcChk0, spcCB0);
		hashChkEdit.put(spcChk0, spcEdit0);
		
		hashChkCombo.put(genChk1, genCB1);
		hashChkEdit.put(genChk1, genEdit1);
		hashEditCombo.put(genEdit1, typesCB1);

		hashChkCombo.put(spcChk1, spcCB1);
		hashChkEdit.put(spcChk1, spcEdit1);
		
		hashChkCombo.put(relChk0, relCB0);
		hashChkEdit.put(relChk0, relEdit0);
		
		genChk0.addActionListener(getCheckBoxActionListener(typesCB0,lblOntoumlType0));
		spcChk0.addActionListener(getCheckBoxActionListener());
		
		genChk1.addActionListener(getCheckBoxActionListener(typesCB1,lblOntoumlType1));
		spcChk1.addActionListener(getCheckBoxActionListener());
		
		relChk0.addActionListener(getCheckBoxActionListener());
		
		this.parser = parser;
		getModelValues(parser);
	}

	protected ActionListener getCheckBoxActionListener(final JComboBox<String> type, final JLabel lbType){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox chk = (JCheckBox)e.getSource();
				JTextField edit = hashChkEdit.get(chk);
				JComboBox<String> cb = hashChkCombo.get(chk);

				if(chk.isSelected()){
					edit.setVisible(false);
					cb.setVisible(true);
					type.setVisible(false);
					lbType.setVisible(false);
				}else{
					edit.setVisible(true);
					cb.setVisible(false);
					type.setVisible(true);
					lbType.setVisible(true);
				}
			}
		};
	}
	
	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		
		//Left side
		Classifier leftGeneral = createClassifier(genChk0, x, y);
		Classifier leftSpecific = createClassifier(ClassStereotype.ROLE, spcChk0, x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(leftSpecific,leftGeneral));

		//Right side
		
		Classifier rightGeneral = createClassifier(genChk1, x+verticalDistance, y);
		Classifier rightSpecific = createClassifier(ClassStereotype.ROLE, spcChk1, x+verticalDistance, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(rightSpecific, rightGeneral));

		//Center side
		Classifier relator = createClassifier(ClassStereotype.RELATOR, relChk0, x+(verticalDistance/2), y+(horizontalDistance/2));

		//Left Mediations
		Association leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, leftSpecific).getAdded().get(0);

		Property srcProperty = outcomeFixer.createProperty(relator, 1, 1);
		Property tgtProperty = outcomeFixer.createProperty(leftSpecific, 1, 1);

		outcomeFixer.setEnds((Association) leftMediation, srcProperty, tgtProperty);
		fix.includeAdded(leftMediation);

		//Right Mediations
		Association rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, rightSpecific).getAdded().get(0);

		tgtProperty = outcomeFixer.createProperty(rightSpecific, 1, 1);

		outcomeFixer.setEnds((Association) rightMediation, srcProperty, tgtProperty);
		fix.includeAdded(rightMediation);

		//Material
		Association material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", leftSpecific, rightSpecific).getAdded().get(0);

		srcProperty = outcomeFixer.createProperty(leftSpecific, 1, 1);
		tgtProperty = outcomeFixer.createProperty(rightSpecific, 1, 1);

		outcomeFixer.setEnds((Association) material, srcProperty, tgtProperty);
		fix.includeAdded(material);

	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		
	}
}
