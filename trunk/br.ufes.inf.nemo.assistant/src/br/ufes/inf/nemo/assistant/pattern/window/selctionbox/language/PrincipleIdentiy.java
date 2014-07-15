package br.ufes.inf.nemo.assistant.pattern.window.selctionbox.language;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.PatternHelpPage;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipleIdentiy extends ClassSelectionPanel {
	private static final long serialVersionUID = 1L;
	private JTextField genEdit;
	private JTextField spcEdit;
	
	private JCheckBox genChk;
	private JCheckBox spcChk;
	
	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> generalTypesCB;
	private JComboBox<String> specificTypesCB;
	private JLabel lblSpcCB;
	
	public PrincipleIdentiy(OntoUMLParser parser) {
		setLayout(null);
		setSize(new Dimension(450, 235));
		
		/* RIGID SORTAL */
		JPanel rigidSortalPanel = new JPanel();
		rigidSortalPanel.setBorder(new TitledBorder(null, "Substance Sortal class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rigidSortalPanel.setBounds(0, 21, 450, 96);
		add(rigidSortalPanel);
		rigidSortalPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("General:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 22, 59, 14);
		rigidSortalPanel.add(lblNewLabel);

		genEdit = new JTextField();
		genEdit.setBounds(109, 19, 149, 20);
		rigidSortalPanel.add(genEdit);
		genEdit.setColumns(10);

		genCB = new JComboBox<String>();
		genCB.setEnabled(false);
		genCB.setBounds(109, 19, 149, 20);
		rigidSortalPanel.add(genCB);

		JLabel lblOntoumlType = new JLabel("OntoUML type:");
		lblOntoumlType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOntoumlType.setBounds(20, 47, 97, 14);
		rigidSortalPanel.add(lblOntoumlType);

		genChk = new JCheckBox("Reuse class?");
		genChk.setBounds(271, 18, 97, 23);
		rigidSortalPanel.add(genChk);

		generalTypesCB = new JComboBox<String>();
		generalTypesCB.setBounds(109, 44, 149, 20);
		rigidSortalPanel.add(generalTypesCB);

		/* SUBKIND */
		JPanel subkindPanel = new JPanel();
		subkindPanel.setBorder(new TitledBorder(null, "Specific class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		subkindPanel.setBounds(0, 128, 450, 86);
		add(subkindPanel);
		subkindPanel.setLayout(null);

		JLabel lblSpecific = new JLabel("Specific:");
		lblSpecific.setBounds(20, 22, 56, 14);
		lblSpecific.setFont(new Font("Tahoma", Font.BOLD, 11));
		subkindPanel.add(lblSpecific);

		spcEdit = new JTextField();
		spcEdit.setBounds(109, 19, 149, 20);
		subkindPanel.add(spcEdit);
		spcEdit.setColumns(10);

		spcCB = new JComboBox<String>();
		spcCB.setEnabled(false);
		spcCB.setBounds(109, 19, 149, 20);
		subkindPanel.add(spcCB);

		spcChk = new JCheckBox("Reuse class?");
		spcChk.setBounds(271, 18, 97, 23);
		subkindPanel.add(spcChk);

		lblSpcCB = new JLabel("OntoUML type:");
		lblSpcCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpcCB.setBounds(21, 53, 97, 14);
		subkindPanel.add(lblSpcCB);
		
		specificTypesCB = new JComboBox<String>();
		specificTypesCB.setBounds(110, 50, 149, 20);
		subkindPanel.add(specificTypesCB);
		
		hashChkEdit.put(genChk, genEdit);
		hashEditCombo.put(genEdit, generalTypesCB);
		hashChkCombo.put(genChk, genCB);

		hashChkEdit.put(spcChk, spcEdit);
		hashEditCombo.put(spcEdit, specificTypesCB);
		hashChkCombo.put(spcChk, spcCB);
		
		genChk.addActionListener(getCheckBoxActionListener(generalTypesCB,lblOntoumlType));
		spcChk.addActionListener(getCheckBoxActionListener(specificTypesCB,lblSpcCB));
		
		this.parser = parser;
		
		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		Classifier general = createClassifier(genChk, x, y);
		Classifier specific = createClassifier(spcChk, x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));
	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		Set<SubstanceSortal> general = parser.getAllInstances(RefOntoUML.SubstanceSortal.class);
		if(general.size() > 0){
			String[] vector = UtilAssistant.getStringRepresentationClassStereotype(general);
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(vector);  
			genCB.setModel(model);
		}else{
			genChk.setVisible(false);
		}
		
		Set<SubKind> specific = parser.getAllInstances(RefOntoUML.SubKind.class);
		DefaultComboBoxModel<String> model2 = null;
		if(specific.size() > 0){
			String[] subkindVector = UtilAssistant.getStringRepresentationClassStereotype(specific);
			model2 = new DefaultComboBoxModel<String>(subkindVector);  
			spcCB.setModel(model2);
		}else{
			spcChk.setVisible(false);
		}

		Set<AntiRigidSortalClass> specific2 = parser.getAllInstances(RefOntoUML.AntiRigidSortalClass.class);
		if(specific2.size() > 0){
			String[] vector = UtilAssistant.getStringRepresentationClassStereotype(specific2);
			
			if(model2 == null)
				model2 = new DefaultComboBoxModel<String>(vector);
			
			for(String p:vector){
				model2.addElement(p);
			}
			spcCB.setModel(model2);
			spcChk.setVisible(true);
		}
		
		
		String[] types = new String[]{"Kind", "Collective", "Quantity"};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(types);  
		generalTypesCB.setModel(model);
		
		types = new String[]{"Role", "Phase", "Subkind"};
		model = new DefaultComboBoxModel<String>(types);  
		specificTypesCB.setModel(model);
	}
}
