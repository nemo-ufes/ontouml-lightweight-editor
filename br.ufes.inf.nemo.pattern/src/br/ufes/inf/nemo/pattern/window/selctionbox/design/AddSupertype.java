package br.ufes.inf.nemo.pattern.window.selctionbox.design;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

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
	private JLabel lblSpecific1;
	private JTextField spc1Edit;
	private JComboBox<String> spc1CB;
	private JLabel lblSpc1CB;
	private JComboBox<String> specific1TypesCB;
	private JCheckBox spcChk;
	private JPanel generalPanel;
	private JPanel specific1Panel;
	private JLabel lblSpecific;
	private JCheckBox spc1Chk;
	private ArrayList<String> thirdClasses = new ArrayList<String>();

	public AddSupertype(final OntoUMLParser parser, Classifier classifier) {
		setLayout(null);
		setSize(new Dimension(450, 317));

		this.selectedClassifier = classifier;
		
		if(!(selectedClassifier instanceof Mixin))
			thirdClasses.add("Mixin");

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
		genChk.setVisible(false);
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

		spcChk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(spcChk.isSelected()){
					for(String thirdClass : thirdClasses){
						if(((String)spcCB.getSelectedItem()).toLowerCase().contains(thirdClass.toLowerCase())){
							specific2Panel.setVisible(true);
							return;
						}
					}
				}else{
					for(String thirdClass : thirdClasses){
						if(((String)specificTypesCB.getSelectedItem()).equalsIgnoreCase(thirdClass)){
							specific2Panel.setVisible(true);
							return;
						}
					}					
				}
				specific2Panel.setVisible(false);
			}
		});

		lblSpecific = new JLabel("Specific:");
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
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(selectedClassifier instanceof Role){
					DefaultComboBoxModel<String> model = null;  
					String[] types = null;

					if(((String)specificTypesCB.getSelectedItem()).equalsIgnoreCase("Mixin")){
						Set<RigidSortalClass> rigidSortal = parser.getAllInstances(RefOntoUML.RigidSortalClass.class);
						model = getCBModelFromSets(rigidSortal);
						types = new String[]{"Kind","Collective","Quantity","Subkind"};
					}else if(((String)specificTypesCB.getSelectedItem()).equalsIgnoreCase("RoleMixin")){
						Set<AntiRigidSortalClass> antiRigidSortals = parser.getAllInstances(RefOntoUML.AntiRigidSortalClass.class);
						model = getCBModelFromSets(antiRigidSortals);
						types = new String[]{"Role","Phase"};
					}
					if(model != null){
						spc1CB.setModel(model);
						spc1Chk.setVisible(model.getSize() != 0);	

						model = new DefaultComboBoxModel<String>(types);  
						specific1TypesCB.setModel(model);
						specific2Panel.setVisible(true);
					}else{
						specific2Panel.setVisible(false);
					}
					return;
				}

				for(String thirdClass : thirdClasses){
					if(((String)specificTypesCB.getSelectedItem()).equalsIgnoreCase(thirdClass)){
						specific2Panel.setVisible(true);
						return;
					}
				}
				specific2Panel.setVisible(false);
			}
		});

		specific2Panel = new JPanel();
		specific2Panel.setLayout(null);
		specific2Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selected class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		specific2Panel.setBounds(0, 213, 450, 86);
		add(specific2Panel);
		

		lblSpecific1 = new JLabel("Specific:");
		lblSpecific1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecific1.setBounds(20, 22, 56, 14);
		specific2Panel.add(lblSpecific1);

		spc1Edit = new JTextField();
		spc1Edit.setColumns(10);
		spc1Edit.setBounds(109, 19, 149, 20);
		specific2Panel.add(spc1Edit);

		spc1CB = new JComboBox<String>();
		spc1CB.setEnabled(false);
		spc1CB.setBounds(109, 19, 149, 20);
		spc1CB.setVisible(false);
		specific2Panel.add(spc1CB);

		lblSpc1CB = new JLabel("OntoUML type:");
		lblSpc1CB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpc1CB.setBounds(21, 53, 97, 14);
		specific2Panel.add(lblSpc1CB);

		specific1TypesCB = new JComboBox<String>();
		specific1TypesCB.setBounds(110, 50, 149, 20);
		specific2Panel.add(specific1TypesCB);

		spc1Chk = new JCheckBox("Reuse class?");
		spc1Chk.setBounds(276, 18, 97, 23);
		specific2Panel.add(spc1Chk);

		hashChkEdit.put(genChk, genEdit);
		hashEditCombo.put(genEdit, generalTypesCB);
		hashChkCombo.put(genChk, genCB);

		hashChkEdit.put(spcChk, spcEdit);
		hashEditCombo.put(spcEdit, specificTypesCB);
		hashChkCombo.put(spcChk, spcCB);

		hashChkEdit.put(spc1Chk, spc1Edit);
		hashEditCombo.put(spc1Edit, specific1TypesCB);
		hashChkCombo.put(spc1Chk, spc1CB);

		genChk.addActionListener(getCheckBoxActionListener(generalTypesCB,lblOntoumlType));
		spcChk.addActionListener(getCheckBoxActionListener(specificTypesCB,lblSpcCB));
		spc1Chk.addActionListener(getCheckBoxActionListener(specific1TypesCB,lblSpc1CB));

		this.parser = parser;

		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();

		if(selectedClassifier instanceof RigidSortalClass || selectedClassifier instanceof Role){
			if(specific2Panel.isVisible()){
				ArrayList<Generalization> generalizationList = new ArrayList<>();
				//mixin	
				Classifier mixin = createClassifier(spcChk, x+60, y-85);
				Fix _fix = outcomeFixer.createGeneralization(selectedClassifier,mixin);

				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);

				//role	
				Classifier antiRigid = createClassifier(spc1Chk, x+120, y);
				_fix = outcomeFixer.createGeneralization(antiRigid, mixin);

				generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);

				fix.addAll(_fix);
				fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
			}else{
				Classifier specific = createClassifier(spcChk, x, y-85);
				fix.addAll(outcomeFixer.createGeneralization(selectedClassifier, specific));			
			}
		}else{
			Classifier specific = createClassifier(spcChk, x, y-85);
			fix.addAll(outcomeFixer.createGeneralization(selectedClassifier, specific));			
		}
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	protected void getModelValues(OntoUMLParser parser) {
		String[] types = new String[]{UtilAssistant.getStringRepresentationStereotype(selectedClassifier)};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(types);  
		generalTypesCB.setModel(model);
		generalTypesCB.setEnabled(false);
		genEdit.setText(UtilAssistant.getStringRepresentationClass(selectedClassifier));

		if(selectedClassifier instanceof SubKind){
			Set<Category> categories = parser.getAllInstances(RefOntoUML.Category.class);
			Set<Mixin> mixins = parser.getAllInstances(RefOntoUML.Mixin.class);
			Set<RigidSortalClass> rigids = parser.getAllInstances(RefOntoUML.RigidSortalClass.class);
			Set<AntiRigidSortalClass> antirigids = parser.getAllInstances(RefOntoUML.AntiRigidSortalClass.class);

			model = this.getCBModelFromSets(selectedClassifier,categories, mixins, rigids);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);

			types = new String[]{"Kind", "Collective", "Quantity", "Subkind", "Category", "Mixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,antirigids);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);	

			types = new String[]{"Role","Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			specific2Panel.setVisible(false);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Category or Mixin");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Anti Rigid Sortals");

		}else if(selectedClassifier instanceof RigidSortalClass){
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

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);	

			types = new String[]{"Role","Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			specific2Panel.setVisible(false);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Category or Mixin");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Anti Rigid Sortals");
		}else if(selectedClassifier instanceof Role){
			Set<Mixin> mixins = parser.getAllInstances(RefOntoUML.Mixin.class);
			Set<RoleMixin> roleMixins = parser.getAllInstances(RefOntoUML.RoleMixin.class);
			Set<SubstanceSortal> substances = parser.getAllInstances(RefOntoUML.SubstanceSortal.class);

			model = this.getCBModelFromSets(selectedClassifier,substances, mixins, roleMixins);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Kind", "Collective", "Quantity", "Subkind", "Role", "Phase", "Mixin", "RoleMixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			specific2Panel.setVisible(false);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Role Supertype");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter part");
		}else if(selectedClassifier instanceof Category){
			Set<Category> categories = parser.getAllInstances(RefOntoUML.Category.class);

			model = this.getCBModelFromSets(selectedClassifier,categories);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Category"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			specific2Panel.setVisible(false);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Category Supertype");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter part");
		}else if(selectedClassifier instanceof Mixin){
			Set<Mixin> mixins = parser.getAllInstances(RefOntoUML.Mixin.class);

			model = this.getCBModelFromSets(selectedClassifier,mixins);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Mixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			specific2Panel.setVisible(false);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Mixin Supertype");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter part");
		}else if(selectedClassifier instanceof RoleMixin){
			Set<RoleMixin> roleMixins = parser.getAllInstances(RefOntoUML.RoleMixin.class);

			model = this.getCBModelFromSets(selectedClassifier,roleMixins);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"RoleMixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			specific2Panel.setVisible(false);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("RoleMixin Supertype");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter part");
		}
	}
}
