package br.ufes.inf.nemo.assistant.pattern.window.selctionbox.design;
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

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import javax.swing.UIManager;

public class AddSupertype extends ClassSelectionPanel {
	private static final long serialVersionUID = 1L;
	private JTextField genEdit;
	private JTextField spcEdit;

	private JCheckBox genChk;

	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> generalTypesCB;
	private JComboBox<String> specificTypesCB;
	private JLabel lblSpcCB;

	private Classifier selectedClassifier;
	private JPanel specific2Panel;
	private JLabel lblSpecific2;
	private JTextField spc2Edit;
	private JComboBox<String> spc2CB;
	private JLabel lblSpc2CB;
	private JComboBox<String> specificTypes2CB;
	private JCheckBox spcChk;
	private JPanel generalPanel;
	private JPanel specific1Panel;
	private JLabel lblSpecific;
	private JCheckBox spc2Chk;
	private ArrayList<String> thirdClasses = new ArrayList<String>();
	
	public AddSupertype(OntoUMLParser parser, Classifier selectedClassifier) {
		setLayout(null);
		setSize(new Dimension(450, 317));

		this.selectedClassifier = selectedClassifier;

		if(selectedClassifier instanceof SubstanceSortal){
			thirdClasses.add("SubKind");
		}else if(selectedClassifier instanceof Mixin){
			thirdClasses.add("Phase");
			thirdClasses.add("Role");
			thirdClasses.add("RoleMixin");
		}else
			thirdClasses.add("Phase");

		/* RIGID SORTAL */
		generalPanel = new JPanel();
		generalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New Supertype", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		generalPanel.setBounds(0, 21, 450, 96);
		add(generalPanel);
		generalPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("General:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 22, 59, 14);
		generalPanel.add(lblNewLabel);

		genEdit = new JTextField();
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

		generalTypesCB = new JComboBox<String>();
		generalTypesCB.setBounds(109, 44, 149, 20);
		generalPanel.add(generalTypesCB);

		/* SUBKIND */
		specific1Panel = new JPanel();
		specific1Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selected class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific1Panel.setBounds(0, 124, 450, 86);
		add(specific1Panel);
		specific1Panel.setLayout(null);

		spcChk = new JCheckBox("Reuse class?");
		spcChk.setBounds(272, 17, 97, 23);
		specific1Panel.add(spcChk);

		lblSpecific = new JLabel("Specific:");
		lblSpecific.setBounds(20, 22, 56, 14);
		lblSpecific.setFont(new Font("Tahoma", Font.BOLD, 11));
		specific1Panel.add(lblSpecific);

		spcEdit = new JTextField();
		spcEdit.setEditable(false);
		spcEdit.setEnabled(false);
		spcEdit.setBounds(109, 19, 149, 20);
		specific1Panel.add(spcEdit);
		spcEdit.setColumns(10);

		spcCB = new JComboBox<String>();
		spcCB.setEnabled(false);
		spcCB.setBounds(109, 19, 149, 20);
		specific1Panel.add(spcCB);
		spcCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(String thirdClass : thirdClasses){
					if(((String)spcCB.getSelectedItem()).toLowerCase().contains(thirdClass.toLowerCase())){
						specific2Panel.setVisible(true);
						return;
					}
				}
				specific2Panel.setVisible(false);
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
		specific2Panel.setLayout(null);
		specific2Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selected class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific2Panel.setBounds(0, 213, 450, 86);
		add(specific2Panel);

		lblSpecific2 = new JLabel("Specific:");
		lblSpecific2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecific2.setBounds(20, 22, 56, 14);
		specific2Panel.add(lblSpecific2);

		spc2Edit = new JTextField();
		spc2Edit.setEnabled(false);
		spc2Edit.setEditable(false);
		spc2Edit.setColumns(10);
		spc2Edit.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc2Edit);

		spc2CB = new JComboBox<String>();
		spc2CB.setEnabled(false);
		spc2CB.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc2CB);

		lblSpc2CB = new JLabel("OntoUML type:");
		lblSpc2CB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpc2CB.setBounds(21, 53, 97, 14);
		specific2Panel.add(lblSpc2CB);

		specificTypes2CB = new JComboBox<String>();
		specificTypes2CB.setBounds(110, 50, 149, 20);
		specific2Panel.add(specificTypes2CB);

		spc2Chk = new JCheckBox("Reuse class?");
		spc2Chk.setBounds(276, 18, 97, 23);
		specific2Panel.add(spc2Chk);

		hashChkEdit.put(genChk, genEdit);
		hashEditCombo.put(genEdit, generalTypesCB);
		hashChkCombo.put(genChk, genCB);

		genChk.addActionListener(getCheckBoxActionListener(generalTypesCB,lblOntoumlType));

		this.parser = parser;

		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
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
			specific = createClassifier(null, x+(1*verticalDistance)/3, y+horizontalDistance);
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
		String[] types = new String[]{UtilAssistant.getStringRepresentationStereotype(selectedClassifier)};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(types);  
		generalTypesCB.setModel(model);
		generalTypesCB.setEnabled(false);
		genEdit.setText(UtilAssistant.getStringRepresentationClass(selectedClassifier));

		if(selectedClassifier instanceof RigidSortalClass){
			Set<Category> categories = parser.getAllInstances(RefOntoUML.Category.class);
			Set<Mixin> mixins = parser.getAllInstances(RefOntoUML.Mixin.class);
			Set<AntiRigidSortalClass> antirigids = parser.getAllInstances(RefOntoUML.AntiRigidSortalClass.class);

			model = this.getCBModelFromSets(selectedClassifier,categories, mixins);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Category", "Mixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,antirigids);

			spc2CB.setModel(model);
			spc2Chk.setVisible(model.getSize() != 0);	

			types = new String[]{"Role","Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypes2CB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Category or Mixin");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Anti Rigid Sortals");
		}
	}
}
