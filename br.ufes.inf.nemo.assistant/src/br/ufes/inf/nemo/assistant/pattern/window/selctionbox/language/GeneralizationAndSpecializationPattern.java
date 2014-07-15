package br.ufes.inf.nemo.assistant.pattern.window.selctionbox.language;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class GeneralizationAndSpecializationPattern extends ClassSelectionPanel {
	private static final long serialVersionUID = 1L;
	private JTextField genEdit;
	private JTextField spcEdit;
	
	private JCheckBox genChk;
	private JCheckBox spcChk;
	private JCheckBox spc1chk;
	
	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> spc1CB;
	private JComboBox<String> generalTypesCB;
	private JComboBox<String> specificTypesCB;
	private JComboBox<String> specific1TypesCB;
	private JLabel lblSpcCB;
	
	private Classifier selectedClassifier;
	private JTextField spc1Edit;
	
	private JPanel specific2Panel;
	
	public GeneralizationAndSpecializationPattern(OntoUMLParser parser, Classifier selectedClassifier) {
		setLayout(null);
		setSize(new Dimension(450, 299));
		this.selectedClassifier = selectedClassifier;
		
		/* RIGID SORTAL */
		JPanel rigidSortalPanel = new JPanel();
		rigidSortalPanel.setBorder(new TitledBorder(null, "General class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rigidSortalPanel.setBounds(0, 11, 450, 86);
		add(rigidSortalPanel);
		rigidSortalPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("General:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 22, 59, 14);
		rigidSortalPanel.add(lblNewLabel);

		genEdit = new JTextField();
		genEdit.setEditable(false);
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
		genChk.setVisible(false);

		generalTypesCB = new JComboBox<String>();
		generalTypesCB.setBounds(109, 44, 149, 20);
		rigidSortalPanel.add(generalTypesCB);

		/* SUBKIND */
		JPanel subkindPanel = new JPanel();
		subkindPanel.setBorder(new TitledBorder(null, "Specific class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		subkindPanel.setBounds(0, 108, 450, 86);
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
		spcCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specific2Panel.setVisible(((String)spcCB.getSelectedItem()).contains("Phase"));
			}
		});
		spcCB.setEnabled(false);
		spcCB.setBounds(109, 19, 149, 20);
		subkindPanel.add(spcCB);

		spcChk = new JCheckBox("Reuse class?");
		spcChk.setBounds(271, 18, 97, 23);
		subkindPanel.add(spcChk);
		
		spcChk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	specific2Panel.setVisible(specificTypesCB.getSelectedItem().equals("Phase"));
            }
        });

		lblSpcCB = new JLabel("OntoUML type:");
		lblSpcCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpcCB.setBounds(21, 53, 97, 14);
		subkindPanel.add(lblSpcCB);
		
		specificTypesCB = new JComboBox<String>();
		specificTypesCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				specific2Panel.setVisible(specificTypesCB.getSelectedItem().equals("Phase"));
			}
		});
		specificTypesCB.setBounds(110, 50, 149, 20);
		subkindPanel.add(specificTypesCB);
		
		specific2Panel = new JPanel();
		specific2Panel.setLayout(null);
		specific2Panel.setBorder(new TitledBorder(null, "Specific1 class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific2Panel.setBounds(0, 205, 450, 86);
		add(specific2Panel);
		
		JLabel label = new JLabel("Specific:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(20, 22, 56, 14);
		specific2Panel.add(label);
		
		spc1Edit = new JTextField();
		spc1Edit.setColumns(10);
		spc1Edit.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc1Edit);
		
		spc1CB = new JComboBox<String>();
		spc1CB.setEnabled(false);
		spc1CB.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc1CB);
		
		spc1chk = new JCheckBox("Reuse class?");
		spc1chk.setBounds(271, 18, 97, 23);
		specific2Panel.add(spc1chk);
		
		JLabel lblSpc1CB = new JLabel("OntoUML type:");
		lblSpc1CB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpc1CB.setBounds(21, 53, 97, 14);
		specific2Panel.add(lblSpc1CB);
		
		specific1TypesCB = new JComboBox<String>();
		specific1TypesCB.setBounds(110, 50, 149, 20);
		specific2Panel.add(specific1TypesCB);
		
		specific2Panel.setVisible(false);
		
		hashChkEdit.put(genChk, genEdit);
		hashEditCombo.put(genEdit, generalTypesCB);
		hashChkCombo.put(genChk, genCB);

		hashChkEdit.put(spcChk, spcEdit);
		hashEditCombo.put(spcEdit, specificTypesCB);
		hashChkCombo.put(spcChk, spcCB);
		
		hashChkEdit.put(spc1chk, spc1Edit);
		hashEditCombo.put(spc1Edit, specific1TypesCB);
		hashChkCombo.put(spc1chk, spc1CB);
		
		genChk.addActionListener(getCheckBoxActionListener(generalTypesCB,lblOntoumlType));
		spcChk.addActionListener(getCheckBoxActionListener(specificTypesCB,lblSpcCB));
		spc1chk.addActionListener(getCheckBoxActionListener(specific1TypesCB,lblSpc1CB));
		
		this.parser = parser;
		
		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		
		if(specific2Panel.isVisible()){
			ArrayList<Generalization> generalizationList = new ArrayList<>();
			//Specific1	
			Classifier specific = createClassifier(spcChk, x+(0*verticalDistance)/3, y+horizontalDistance);
			Fix _fix = outcomeFixer.createGeneralization(specific, selectedClassifier);
			
			Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
			generalizationList.add(generalization);
			
			//Specific2	
			specific = createClassifier(spc1chk, x+(1*verticalDistance)/3, y+horizontalDistance);
			_fix = outcomeFixer.createGeneralization(specific, selectedClassifier);
			
			generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
			generalizationList.add(generalization);

			fix.addAll(_fix);
			fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
		}else{
			Classifier specific = createClassifier(spcChk, x, y+horizontalDistance);
			fix.addAll(outcomeFixer.createGeneralization(specific, selectedClassifier));	
		}
	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		String[] types = new String[]{UtilAssistant.getStringRepresentationStereotype(selectedClassifier)};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(types);  
		generalTypesCB.setModel(model);
		generalTypesCB.setEnabled(false);
		genEdit.setText(UtilAssistant.getStringRepresentationClass(selectedClassifier));
		
		if(selectedClassifier instanceof SubKind){
			Set<SubKind> specific = parser.getAllInstances(RefOntoUML.SubKind.class);
			DefaultComboBoxModel<String> model1 = null;
			if(specific.size() > 0){
				String[] subkindVector = UtilAssistant.getStringRepresentationClassStereotype(specific);
				model1 = new DefaultComboBoxModel<String>(subkindVector);  
				spcCB.setModel(model1);
			}else{
				spcChk.setVisible(false);
			}
			
			Set<Role> specific2 = parser.getAllInstances(RefOntoUML.Role.class);
			if(specific2.size() > 0){
				String[] vector = UtilAssistant.getStringRepresentationClassStereotype(specific2);
				
				if(model1 == null)
					model1 = new DefaultComboBoxModel<String>(vector);
				
				for(String p:vector){
					model1.addElement(p);
				}
				spcCB.setModel(model1);
				spcChk.setVisible(true);
			}
			
			Set<Phase> specific3 = parser.getAllInstances(RefOntoUML.Phase.class);
			if(specific3.size() > 0){
				String[] vector = UtilAssistant.getStringRepresentationClassStereotype(specific3);
				
				if(model1 == null)
					model1 = new DefaultComboBoxModel<String>(vector);
				
				for(String p:vector){
					model1.addElement(p);
				}
				spcCB.setModel(model1);
				spcChk.setVisible(true);
			}
			
			
			types = new String[]{"Role", "Subkind","Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);
			
			Set<Phase> specific4 = parser.getAllInstances(RefOntoUML.Phase.class);
			if(specific4.size() > 0){
				String[] vector = UtilAssistant.getStringRepresentationClassStereotype(specific4);
				model = new DefaultComboBoxModel<String>(vector);
				
				spc1CB.setModel(model);
				spc1chk.setVisible(true);
			}else{
				spc1chk.setVisible(false);
			}
			
			types = new String[]{"Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);
		}
	}
}
