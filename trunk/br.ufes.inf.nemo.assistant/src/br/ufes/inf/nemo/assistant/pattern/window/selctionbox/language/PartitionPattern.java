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
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class PartitionPattern extends ClassSelectionPanel {
	private static final long serialVersionUID = 1L;
	private JTextField genEdit;
	private JTextField spcEdit;

	private JCheckBox genChk;
	private JCheckBox spcChk;
	private JCheckBox spc1Chk;

	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> spc1CB;
	private JComboBox<String> generalTypesCB;
	private JComboBox<String> specificTypesCB;
	private JLabel lblSpcCB;

	private Classifier selectedClassifier;

	private JPanel generalPanel;	
	private JPanel specific1Panel;

	private JLabel lblSpecific;
	private JLabel lblSpecific1;

	private JTextField spc1Edit;

	public PartitionPattern(OntoUMLParser parser, Classifier selectedClassifier) {
		setLayout(null);
		setSize(new Dimension(450, 217));
		this.selectedClassifier = selectedClassifier;

		/* RIGID SORTAL */
		generalPanel = new JPanel();
		generalPanel.setBorder(new TitledBorder(null, "General class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		generalPanel.setBounds(0, 11, 450, 86);
		add(generalPanel);
		generalPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("General:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 22, 59, 14);
		generalPanel.add(lblNewLabel);

		genEdit = new JTextField();
		genEdit.setEditable(false);
		genEdit.setBounds(109, 19, 149, 20);
		generalPanel.add(genEdit);
		genEdit.setColumns(10);

		genCB = new JComboBox<String>();
		genCB.setEnabled(false);
		genCB.setBounds(109, 19, 149, 20);
		generalPanel.add(genCB);

		JLabel lblOntoumlType = new JLabel("OntoUML type:");
		lblOntoumlType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOntoumlType.setBounds(20, 47, 97, 14);
		generalPanel.add(lblOntoumlType);

		genChk = new JCheckBox("Reuse class?");
		genChk.setBounds(271, 18, 97, 23);
		generalPanel.add(genChk);
		genChk.setVisible(false);

		generalTypesCB = new JComboBox<String>();
		generalTypesCB.setBounds(109, 44, 149, 20);
		generalPanel.add(generalTypesCB);

		/* SUBKIND */
		specific1Panel = new JPanel();
		specific1Panel.setBorder(new TitledBorder(null, "Specific class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific1Panel.setBounds(0, 108, 450, 108);
		add(specific1Panel);
		specific1Panel.setLayout(null);

		lblSpecific1 = new JLabel("Specific:");
		lblSpecific1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecific1.setBounds(20, 51, 56, 14);
		specific1Panel.add(lblSpecific1);

		spc1Edit = new JTextField();
		spc1Edit.setColumns(10);
		spc1Edit.setBounds(109, 48, 149, 20);
		specific1Panel.add(spc1Edit);

		spc1CB = new JComboBox<String>();
		spc1CB.setEnabled(false);
		spc1CB.setBounds(109, 48, 149, 20);
		specific1Panel.add(spc1CB);

		spc1Chk = new JCheckBox("Reuse class?");
		spc1Chk.setBounds(271, 48, 97, 23);
		specific1Panel.add(spc1Chk);

		lblSpecific = new JLabel("Specific:");
		lblSpecific.setBounds(20, 22, 56, 14);
		lblSpecific.setFont(new Font("Tahoma", Font.BOLD, 11));
		specific1Panel.add(lblSpecific);

		spcEdit = new JTextField();
		spcEdit.setBounds(109, 19, 149, 20);
		specific1Panel.add(spcEdit);
		spcEdit.setColumns(10);

		spcCB = new JComboBox<String>();
		spcCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(spcChk.isSelected()){

					spc1CB.setModel(new DefaultComboBoxModel<String>());
					String selectedStereotype = (String) ((String)spcCB.getSelectedItem()).subSequence(0, ((String)spcCB.getSelectedItem()).indexOf("-")+1);
					for(int i = 0; i < spcCB.getModel().getSize(); i++){
						if(((String)spcCB.getItemAt(i)).contains(selectedStereotype) && !((String)spcCB.getItemAt(i)).equals(((String)spcCB.getSelectedItem())))
							spc1CB.addItem(spcCB.getModel().getElementAt(i));
					}

					if(spc1CB.getModel().getSize() == 0){
						spc1Chk.setSelected(false);
						spc1Chk.setVisible(false);
					}else{
						spc1Chk.setVisible(true);	
					}

					specificTypesCB.setModel(new DefaultComboBoxModel<String>(new String[]{selectedStereotype.substring(0, selectedStereotype.length()-2)}));					
				}
			}
		});
		spcCB.setEnabled(false);
		spcCB.setBounds(109, 19, 149, 20);
		specific1Panel.add(spcCB);

		spcChk = new JCheckBox("Reuse class?");
		spcChk.setBounds(271, 18, 97, 23);
		specific1Panel.add(spcChk);

		spcChk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(spcChk.isSelected()){
					spcEdit.setVisible(false);
					spcEdit.setEnabled(false);

					spcCB.setVisible(true);
					spcCB.setEnabled(true);

					lblSpecific.setVisible(false);
					lblSpecific.setEnabled(false);

					spc1Chk.setVisible(true);
					spcCB.setSelectedIndex(0);
				}else{
					spcEdit.setVisible(true);
					spcEdit.setEnabled(true);

					spcCB.setVisible(false);
					spcCB.setEnabled(false);

					lblSpecific.setVisible(true);
					lblSpecific.setEnabled(true);

					specificTypesCB.setVisible(true);
					lblSpcCB.setVisible(true);

					spc1Chk.setSelected(false);
					spc1Chk.setVisible(false);

					spc1Edit.setVisible(true);
					spc1Edit.setEnabled(true);

					spc1CB.setVisible(false);
					spc1CB.setEnabled(false);

					lblSpecific1.setVisible(true);
					lblSpecific1.setEnabled(true);
				}
				if(!spcChk.isSelected() && !spc1Chk.isSelected())
					specificTypesCB.setModel(new DefaultComboBoxModel<String>(types));
			}
		});

		spc1Chk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!spcChk.isSelected() && !spc1Chk.isSelected())
					specificTypesCB.setModel(new DefaultComboBoxModel<String>(types));

				if(spcChk.isSelected() && spc1Chk.isSelected()){
					specificTypesCB.setVisible(false);
					lblSpcCB.setVisible(false);
				}else{ 
					specificTypesCB.setVisible(true);
					lblSpcCB.setVisible(true);
				}

				if(spc1Chk.isSelected()){
					spc1Edit.setVisible(false);
					spc1Edit.setEnabled(false);

					spc1CB.setVisible(true);
					spc1CB.setEnabled(true);

					lblSpecific1.setVisible(false);
					lblSpecific1.setEnabled(false);
				}else{
					spc1Edit.setVisible(true);
					spc1Edit.setEnabled(true);

					spc1CB.setVisible(false);
					spc1CB.setEnabled(false);

					lblSpecific1.setVisible(true);
					lblSpecific1.setEnabled(true);
				}
			}
		});

		lblSpcCB = new JLabel("OntoUML type:");
		lblSpcCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpcCB.setBounds(21, 79, 97, 14);
		specific1Panel.add(lblSpcCB);

		specificTypesCB = new JComboBox<String>();
		specificTypesCB.setBounds(110, 76, 149, 20);
		specific1Panel.add(specificTypesCB);

		this.parser = parser;

		hashChkEdit.put(spcChk, spcEdit);
		hashEditCombo.put(spcEdit, specificTypesCB);
		hashChkCombo.put(spcChk, spcCB);

		hashChkEdit.put(spc1Chk, spc1Edit);
		hashEditCombo.put(spc1Edit, specificTypesCB);
		hashChkCombo.put(spc1Chk, spc1CB);

		getModelValues(parser);	
	}

	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		ArrayList<Generalization> generalizationList = new ArrayList<>();
		//Specific1	
		Classifier specific = createClassifier(spcChk, x+(0*verticalDistance)/3, y+horizontalDistance);
		Fix _fix = outcomeFixer.createGeneralization(specific, selectedClassifier);

		Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
		generalizationList.add(generalization);

		//Specific2	
		specific = createClassifier(spc1Chk, x+(1*verticalDistance)/3, y+horizontalDistance);
		_fix = outcomeFixer.createGeneralization(specific, selectedClassifier);

		generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
		generalizationList.add(generalization);

		fix.addAll(_fix);
		fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
	}

	private String[] types;
	@Override
	protected void getModelValues(OntoUMLParser parser) {
		types = new String[]{UtilAssistant.getStringRepresentationStereotype(selectedClassifier)};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(types);  
		generalTypesCB.setModel(model);
		generalTypesCB.setEnabled(false);
		genEdit.setText(UtilAssistant.getStringRepresentationClass(selectedClassifier));

		DefaultComboBoxModel<String> cbModel;
		Set<Role> roles = parser.getAllInstances(RefOntoUML.Role.class);
		Set<Phase> phases = parser.getAllInstances(RefOntoUML.Phase.class);

		cbModel = this.getCBModelFromSets(selectedClassifier,roles,phases);

		spcCB.setModel(cbModel);
		spcChk.setVisible(cbModel.getSize() != 0);

		spc1CB.setModel(this.getCBModelFromSets(selectedClassifier,roles,phases));

		types = new String[]{"Role", "Phase"};
		model = new DefaultComboBoxModel<String>(types);  
		specificTypesCB.setModel(model);

		((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
		((TitledBorder)specific1Panel.getBorder()).setTitle("AntiRigid Sortal classes");
		
		if(selectedClassifier instanceof RigidSortalClass){
			Set<SubKind> subkinds = parser.getAllInstances(RefOntoUML.SubKind.class);

			cbModel = this.getCBModelFromSets(selectedClassifier,roles,phases,subkinds);

			spcCB.setModel(cbModel);
			spcChk.setVisible(cbModel.getSize() != 0);

			spc1CB.setModel(this.getCBModelFromSets(selectedClassifier,roles,phases,subkinds));

			types = new String[]{"Role", "Phase", "Subkind"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("AntiRigid Sortals and Subkinds class");
		}
		spc1Chk.setVisible(false);
	}
}