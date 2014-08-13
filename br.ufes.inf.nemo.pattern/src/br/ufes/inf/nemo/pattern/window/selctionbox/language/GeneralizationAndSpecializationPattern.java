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

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
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
	private JCheckBox spc1Chk;

	private JComboBox<String> genCB;
	private JComboBox<String> spcCB;
	private JComboBox<String> spc1CB;
	private JComboBox<String> generalTypesCB;
	private JComboBox<String> specificTypesCB;
	private JComboBox<String> specific1TypesCB;
	private JLabel lblSpcCB;

	private Classifier selectedClassifier;
	private JTextField spc1Edit;

	private JPanel generalPanel;	
	private JPanel specific1Panel;
	private JPanel specific2Panel;

	private ArrayList<String> thirdClasses = new ArrayList<String>();

	public GeneralizationAndSpecializationPattern(OntoUMLParser parser, Classifier selectedClassifier) {
		setLayout(null);
		setSize(new Dimension(450, 299));
		this.selectedClassifier = selectedClassifier;

		if(selectedClassifier instanceof Category){
			thirdClasses.add("SubKind");
		}else if(selectedClassifier instanceof Mixin){
			thirdClasses.add("Phase");
			thirdClasses.add("Role");
			thirdClasses.add("RoleMixin");
		}else
			thirdClasses.add("Phase");

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
		specific1Panel.setBounds(0, 108, 450, 86);
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

		lblSpcCB = new JLabel("OntoUML type:");
		lblSpcCB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpcCB.setBounds(21, 53, 97, 14);
		specific1Panel.add(lblSpcCB);

		specificTypesCB = new JComboBox<String>();
		specificTypesCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(String thirdClass : thirdClasses){
					if(((String)specificTypesCB.getSelectedItem()).equalsIgnoreCase(thirdClass)){
						specific2Panel.setVisible(true);
						return;
					}
				}
				specific2Panel.setVisible(false);
			}
		});
		specificTypesCB.setBounds(110, 50, 149, 20);
		specific1Panel.add(specificTypesCB);

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

		spc1Chk = new JCheckBox("Reuse class?");
		spc1Chk.setBounds(271, 18, 97, 23);
		specific2Panel.add(spc1Chk);

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

		if(specific2Panel.isVisible()){
			if(selectedClassifier instanceof Category){
				Classifier specific = createClassifier(spcChk, x, y+85);
				fix.addAll(outcomeFixer.createGeneralization(specific, selectedClassifier));	

				Classifier substance = createClassifier(spc1Chk, x+80, y+85);
				fix.addAll(outcomeFixer.createGeneralization(specific, substance));
			}else if(selectedClassifier instanceof Mixin){
				Classifier specific1 = createClassifier(spcChk, x+(0*verticalDistance)/3, y+horizontalDistance);
				fix.addAll(outcomeFixer.createGeneralization(specific1, selectedClassifier));	

				Classifier specific2 = createClassifier(spc1Chk, x+(1*verticalDistance)/3, y+horizontalDistance);
				fix.addAll(outcomeFixer.createGeneralization(specific2, selectedClassifier));
			}else{
				//Phases
				ArrayList<Generalization> generalizationList = new ArrayList<>();
				//Specific1	
				Classifier specific = createClassifier(spcChk, x+60, y+85);
				Fix _fix = outcomeFixer.createGeneralization(specific, selectedClassifier);

				Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);

				//Specific2	
				specific = createClassifier(spc1Chk, x-60, y+85);
				_fix = outcomeFixer.createGeneralization(specific, selectedClassifier);

				generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
				generalizationList.add(generalization);

				fix.addAll(_fix);
				fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"+UtilAssistant.getCont()));
			}
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
			Set<SubKind> subkinds = parser.getAllInstances(RefOntoUML.SubKind.class);
			Set<Role> roles = parser.getAllInstances(RefOntoUML.Role.class);
			Set<Phase> phases = parser.getAllInstances(RefOntoUML.Phase.class);

			model = this.getCBModelFromSets(selectedClassifier,subkinds,roles,phases);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Role", "Subkind","Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,phases);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);	

			types = new String[]{"Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected Subkind class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Subkinds and AntiRigid Sortal classes");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter Part Phase");
		}else if(selectedClassifier instanceof Role){
			Set<Role> roles = parser.getAllInstances(RefOntoUML.Role.class);
			Set<Phase> phases = parser.getAllInstances(RefOntoUML.Phase.class);

			model = this.getCBModelFromSets(selectedClassifier,roles,phases);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Role", "Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,phases);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);

			types = new String[]{"Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected Role class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("AntiRigid Sortal classes");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter Part Phase");
		}else if(selectedClassifier instanceof Category){
			Set<Category> categories = parser.getAllInstances(RefOntoUML.Category.class);
			Set<SubKind> subkinds = parser.getAllInstances(RefOntoUML.SubKind.class);
			Set<SubstanceSortal> substancesortais = parser.getAllInstances(RefOntoUML.SubstanceSortal.class);

			model = this.getCBModelFromSets(selectedClassifier,categories, subkinds, substancesortais);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Category", "Subkind", "Kind","Collective","Quantity"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,substancesortais);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);

			types = new String[]{"Kind","Collective","Quantity"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected Category class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Rigid classes");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Substance Sortal classes");
		}else if(selectedClassifier instanceof Mixin){
			Set<RigidSortalClass> rigidSortalClass = parser.getAllInstances(RefOntoUML.RigidSortalClass.class);
			Set<RigidMixinClass> rigidMixinClass = parser.getAllInstances(RefOntoUML.RigidMixinClass.class);
			Set<AntiRigidSortalClass> antiRigidSortalClass = parser.getAllInstances(RefOntoUML.AntiRigidSortalClass.class);
			Set<AntiRigidMixinClass> antiRigidMixinClass = parser.getAllInstances(RefOntoUML.AntiRigidMixinClass.class);
			Set<Mixin> mixinClass = parser.getAllInstances(RefOntoUML.Mixin.class);

			model = this.getCBModelFromSets(selectedClassifier,antiRigidSortalClass, antiRigidMixinClass, mixinClass);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Mixin", "Role", "Phase", "RoleMixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,rigidSortalClass, rigidMixinClass);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);

			types = new String[]{"Kind","Collective","Quantity", "Subkind","Category"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected Mixin class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Anti Rigid classes");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Rigid classes");
		}else if(selectedClassifier instanceof RoleMixin){
			Set<RoleMixin> roleMixins = parser.getAllInstances(RefOntoUML.RoleMixin.class);
			Set<Role> roles = parser.getAllInstances(RefOntoUML.Role.class);
			Set<Phase> phases = parser.getAllInstances(RefOntoUML.Phase.class);

			model = this.getCBModelFromSets(selectedClassifier,roles,phases,roleMixins);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Role", "Phase", "RoleMixin"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,phases);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);

			types = new String[]{"Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected RoleMixin class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Anti Rigid classes");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Phases classes");
		}else if(selectedClassifier instanceof SubstanceSortal){
			Set<SubKind> subkinds = parser.getAllInstances(RefOntoUML.SubKind.class);
			Set<Role> roles = parser.getAllInstances(RefOntoUML.Role.class);
			Set<Phase> phases = parser.getAllInstances(RefOntoUML.Phase.class);

			model = this.getCBModelFromSets(selectedClassifier,subkinds,roles,phases);
			spcCB.setModel(model);
			spcChk.setVisible(model.getSize() != 0);			

			types = new String[]{"Role", "Subkind","Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specificTypesCB.setModel(model);

			model = this.getCBModelFromSets(selectedClassifier,phases);

			spc1CB.setModel(model);
			spc1Chk.setVisible(model.getSize() != 0);	

			types = new String[]{"Phase"};
			model = new DefaultComboBoxModel<String>(types);  
			specific1TypesCB.setModel(model);

			((TitledBorder)generalPanel.getBorder()).setTitle("Selected "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" class");
			((TitledBorder)specific1Panel.getBorder()).setTitle("Subkinds and AntiRigid Sortal classes");
			((TitledBorder)specific2Panel.getBorder()).setTitle("Counter Part Phase");			
		}
	}
}