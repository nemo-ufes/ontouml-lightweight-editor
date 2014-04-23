package br.ufes.inf.nemo.assistant.pattern.window.selctionbox;
import java.awt.Font;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class SubkindCreation extends ClassSelectionPanel {
	private static final long serialVersionUID = 1L;
	private JTextField genEdit;
	private JTextField spcEdit;
	
	private JCheckBox genChk;
	private JCheckBox spcChk;
	
	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> typesCB;
	
	public SubkindCreation(OntoUMLParser parser) {
		setLayout(null);

		/* RIGID SORTAL */
		JPanel rigidSortalPanel = new JPanel();
		rigidSortalPanel.setBorder(new TitledBorder(null, "RigidSortal class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		typesCB = new JComboBox<String>();
		typesCB.setBounds(109, 44, 149, 20);
		rigidSortalPanel.add(typesCB);

		/* SUBKIND */
		JPanel subkindPanel = new JPanel();
		subkindPanel.setBorder(new TitledBorder(null, "Subkind class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		subkindPanel.setBounds(0, 128, 450, 67);
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

		hashChkEdit.put(genChk, genEdit);
		hashEditCombo.put(genEdit, typesCB);
		hashChkCombo.put(genChk, genCB);

		hashChkEdit.put(spcChk, spcEdit);
		hashChkCombo.put(spcChk, spcCB);

		genChk.addActionListener(getCheckBoxActionListener(typesCB,lblOntoumlType));
		spcChk.addActionListener(getCheckBoxActionListener());
		
		this.parser = parser;
		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		Classifier general = createClassifier(genChk, x, y);
		Classifier specific = createClassifier(ClassStereotype.SUBKIND, spcChk, x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));
	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		Set<RigidSortalClass> rigids = parser.getAllInstances(RefOntoUML.RigidSortalClass.class);
		if(rigids.size() > 0){
			String[] rigidVector = UtilAssistant.getStringRepresentationClassStereotype(rigids);
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(rigidVector);  
			genCB.setModel(model);
		}else{
			genChk.setVisible(false);
		}
		
		Set<SubKind> subkinds = parser.getAllInstances(RefOntoUML.SubKind.class);
		if(subkinds.size() > 0){
			String[] subkindVector = UtilAssistant.getStringRepresentationClassStereotype(subkinds);
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(subkindVector);  
			spcCB.setModel(model);
		}else{
			spcChk.setVisible(false);
		}
		
		String[] rigidTypes = new String[]{"Kind", "Collective", "Quantity","Subkind"};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(rigidTypes);  
		typesCB.setModel(model);
	}
}
