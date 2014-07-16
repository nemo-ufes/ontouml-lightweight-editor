package br.ufes.inf.nemo.assistant.pattern.window.selctionbox.language;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
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
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.Phase;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipleIdentiy extends ClassSelectionPanel {
	private static final long serialVersionUID = 1L;
	private JTextField genEdit;
	private JTextField spcEdit;
	private JTextField spc2Edit;
	
	private JCheckBox genChk;
	private JCheckBox spcChk;
	private JCheckBox spc2Chk;
	
	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> spc2CB;
	private JComboBox<String> generalTypesCB;
	private JComboBox<String> specificTypesCB;
	private JComboBox<String> specific2TypesCB;
	private JLabel lblSpcCB;
	private JLabel lblSpcCB2;
	
	private JPanel specific2Panel;
	
	public PrincipleIdentiy(OntoUMLParser parser) {
		setLayout(null);
		setSize(new Dimension(450, 317));
		
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
		JPanel specific1Panel = new JPanel();
		specific1Panel.setBorder(new TitledBorder(null, "Specific class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific1Panel.setBounds(0, 125, 450, 86);
		add(specific1Panel);
		specific1Panel.setLayout(null);

		JLabel lblSpecific = new JLabel("Specific:");
		lblSpecific.setBounds(20, 22, 56, 14);
		lblSpecific.setFont(new Font("Tahoma", Font.BOLD, 11));
		specific1Panel.add(lblSpecific);

		spcEdit = new JTextField();
		spcEdit.setBounds(109, 19, 149, 20);
		specific1Panel.add(spcEdit);
		spcEdit.setColumns(10);

		spcCB = new JComboBox<String>();
		spcCB.setEnabled(false);
		spcCB.setBounds(109, 19, 149, 20);
		specific1Panel.add(spcCB);
		spcCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specific2Panel.setVisible(((String)spcCB.getSelectedItem()).contains("Phase"));
			}
		});

		spcChk = new JCheckBox("Reuse class?");
		spcChk.setBounds(271, 18, 97, 23);
		specific1Panel.add(spcChk);
		spcChk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	specific2Panel.setVisible(specificTypesCB.getSelectedItem().equals("Phase"));
            }
        });

		lblSpcCB = new JLabel("OntoUML type:");
		lblSpcCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpcCB.setBounds(21, 53, 97, 14);
		specific1Panel.add(lblSpcCB);
		
		specificTypesCB = new JComboBox<String>();
		specificTypesCB.setBounds(110, 50, 149, 20);
		specific1Panel.add(specificTypesCB);
		specificTypesCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				specific2Panel.setVisible(specificTypesCB.getSelectedItem().equals("Phase"));
			}
		});
		
		
		specific2Panel = new JPanel();
		specific2Panel.setBorder(new TitledBorder(null, "Specific2 class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific2Panel.setBounds(0, 215, 450, 86);
		add(specific2Panel);
		specific2Panel.setLayout(null);
		specific2Panel.setVisible(false);

		JLabel lblSpecific2 = new JLabel("Specific:");
		lblSpecific2.setBounds(20, 22, 56, 14);
		lblSpecific2.setFont(new Font("Tahoma", Font.BOLD, 11));
		specific2Panel.add(lblSpecific2);

		spc2Edit = new JTextField();
		spc2Edit.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc2Edit);
		spc2Edit.setColumns(10);

		spc2CB = new JComboBox<String>();
		spc2CB.setEnabled(false);
		spc2CB.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc2CB);

		spc2Chk = new JCheckBox("Reuse class?");
		spc2Chk.setBounds(271, 18, 97, 23);
		specific2Panel.add(spc2Chk);

		lblSpcCB2 = new JLabel("OntoUML type:");
		lblSpcCB2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpcCB2.setBounds(21, 53, 97, 14);
		specific2Panel.add(lblSpcCB2);
		
		specific2TypesCB = new JComboBox<String>();
		specific2TypesCB.setBounds(110, 50, 149, 20);
		specific2Panel.add(specific2TypesCB);
		
		hashChkEdit.put(genChk, genEdit);
		hashEditCombo.put(genEdit, generalTypesCB);
		hashChkCombo.put(genChk, genCB);

		hashChkEdit.put(spcChk, spcEdit);
		hashEditCombo.put(spcEdit, specificTypesCB);
		hashChkCombo.put(spcChk, spcCB);
		
		hashChkEdit.put(spc2Chk, spc2Edit);
		hashEditCombo.put(spc2Edit, specific2TypesCB);
		hashChkCombo.put(spc2Chk, spc2CB);
		
		genChk.addActionListener(getCheckBoxActionListener(generalTypesCB,lblOntoumlType));
		spcChk.addActionListener(getCheckBoxActionListener(specificTypesCB,lblSpcCB));
		spc2Chk.addActionListener(getCheckBoxActionListener(specific2TypesCB,lblSpcCB2));
		
		this.parser = parser;
		
		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
//		Package root = parser.getModel();
//		outcomeFixer = new OutcomeFixer(root);
//		fix = new Fix();
//
//		Classifier general = createClassifier(genChk, x, y);
//		Classifier specific = createClassifier(spcChk, x, y+horizontalDistance);
//
//		fix.addAll(outcomeFixer.createGeneralization(specific, general));
		
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		Classifier general = createClassifier(genChk, x, y);
		
		if(specific2Panel.isVisible()){
			ArrayList<Generalization> generalizationList = new ArrayList<>();

			//Specific1
			Classifier specific = createClassifier(spcChk, x+(0*verticalDistance)/3, y+horizontalDistance);
			Fix _fix = outcomeFixer.createGeneralization(specific, general);
			
			Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
			generalizationList.add(generalization);
			
			//Specific2	
			specific = createClassifier(spc2Chk, x+(1*verticalDistance)/3, y+horizontalDistance);
			_fix = outcomeFixer.createGeneralization(specific, general);
			
			generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
			generalizationList.add(generalization);

			fix.addAll(_fix);
			fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
		}else{
			Classifier specific = createClassifier(spcChk, x, y+horizontalDistance);
			fix.addAll(outcomeFixer.createGeneralization(specific, general));	
		}
		
	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		//General
		String[] types = new String[]{"Kind", "Collective", "Quantity"};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(types);
		generalTypesCB.setModel(model);
		
		Set<SubstanceSortal> substanceSortais = parser.getAllInstances(RefOntoUML.SubstanceSortal.class);
		
		model = this.getCBModelFromSets(substanceSortais);  
		
		genCB.setModel(model);
		genChk.setVisible(model.getSize() != 0);
		
		//Specific
		types = new String[]{"Subkind", "Role", "Phase"};
		model = new DefaultComboBoxModel<String>(types);
		specificTypesCB.setModel(model);
		
		Set<SubKind> subkinds = parser.getAllInstances(RefOntoUML.SubKind.class);
		Set<AntiRigidSortalClass> antiRigids = parser.getAllInstances(RefOntoUML.AntiRigidSortalClass.class);
		
		model = this.getCBModelFromSets(subkinds,antiRigids);  
		
		spcCB.setModel(model);		
		spcChk.setVisible(model.getSize() != 0);
		
		
		Set<Phase> phases = parser.getAllInstances(RefOntoUML.Phase.class);
		
		model = this.getCBModelFromSets(phases);
		
		spc2CB.setModel(model);
		spc2Chk.setVisible(model.getSize() != 0);
		
		types = new String[]{"Phase"};
		model = new DefaultComboBoxModel<String>(types);  
		specific2TypesCB.setModel(model);
	}
}
