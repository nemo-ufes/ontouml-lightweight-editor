package br.ufes.inf.nemo.assistant.pattern.window.selctionbox.design;
import java.awt.Dimension;
import java.awt.Font;
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

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SortalClass;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RoleMixinPattern extends ClassSelectionPanel {

	private static final long serialVersionUID = 7669043593008401411L;

	private JTextField ssEdit1;
	private JTextField role2Edit;

	private JCheckBox ssChk1;
	private JCheckBox role2Chk;

	private JComboBox<String> ssCB1;
	private JComboBox<String> role2CB;
	private JComboBox<String> ssType1;
	private JLabel role1Lbl;
	private JTextField role1Edit;
	private JComboBox<String> role1CB;
	private JCheckBox role1Chk;
	private JLabel ss2Lbl;
	private JTextField ssEdit2;
	private JLabel ss2TypeLbl;
	private JComboBox<String> ssCB2;
	private JCheckBox ssChk2;
	private JComboBox<String> ssType2;
	private JPanel roleMixinPanel;
	private JLabel lblRoleMixin;
	private JTextField rmEdit;
	private JCheckBox rmChk;
	private JComboBox<String> rmCB;
	private JTextField relEdit;
	private JPanel relDependencyPanel;
	private JLabel ss3Lbl;
	private JTextField ssEdit3;
	private JComboBox<String> ssCB3;
	private JLabel ss3TypeLbl;
	private JCheckBox ssChk3;
	private JComboBox<String> ssType3;
	private JLabel role3Lbl;
	private JTextField role3Edit;
	private JComboBox<String> role3CB;
	private JCheckBox role3Chk;

	private JCheckBox relChk;
	private JComboBox<String> relCB;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RoleMixinPattern(OntoUMLParser parser) {
		setLayout(null);

		setSize(new Dimension(441, 527));
		JPanel roleMixinPortion1 = new JPanel();
		roleMixinPortion1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RoleMixin Portion 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		roleMixinPortion1.setBounds(0, 11, 435, 116);
		add(roleMixinPortion1);
		roleMixinPortion1.setLayout(null);

		JLabel ss1Lbl = new JLabel("SortalUniversal1:");
		ss1Lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		ss1Lbl.setBounds(15, 22, 123, 14);
		roleMixinPortion1.add(ss1Lbl);

		ssEdit1 = new JTextField();
		ssEdit1.setBounds(143, 19, 149, 20);
		roleMixinPortion1.add(ssEdit1);
		ssEdit1.setColumns(10);

		ssCB1 = new JComboBox<String>();
		ssCB1.setEnabled(false);
		ssCB1.setBounds(143, 19, 149, 20);
		roleMixinPortion1.add(ssCB1);

		JLabel ss1TypeLbl = new JLabel("OntoUML type:");
		ss1TypeLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		ss1TypeLbl.setBounds(15, 46, 97, 14);
		roleMixinPortion1.add(ss1TypeLbl);

		ssChk1 = new JCheckBox("Reuse class?");
		ssChk1.setBounds(305, 18, 97, 23);
		roleMixinPortion1.add(ssChk1);

		ssType1 = new JComboBox<String>();
		ssType1.setBounds(143, 44, 149, 20);
		roleMixinPortion1.add(ssType1);

		JPanel roleMixinPortion2 = new JPanel();
		roleMixinPortion2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RoleMixin Portion 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		roleMixinPortion2.setBounds(0, 139, 435, 121);
		add(roleMixinPortion2);
		roleMixinPortion2.setLayout(null);

		JLabel role2Lbl = new JLabel("Role2:");
		role2Lbl.setBounds(20, 89, 56, 14);
		role2Lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		roleMixinPortion2.add(role2Lbl);

		role2Edit = new JTextField();
		role2Edit.setBounds(144, 86, 149, 20);
		roleMixinPortion2.add(role2Edit);
		role2Edit.setColumns(10);

		role2CB = new JComboBox<String>();
		role2CB.setEnabled(false);
		role2CB.setBounds(144, 86, 149, 20);
		roleMixinPortion2.add(role2CB);

		role2Chk = new JCheckBox("Reuse class?");
		role2Chk.setBounds(306, 85, 97, 23);
		roleMixinPortion2.add(role2Chk);

		ss2Lbl = new JLabel("SortalUniversal2:");
		ss2Lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		ss2Lbl.setBounds(20, 27, 115, 14);
		roleMixinPortion2.add(ss2Lbl);

		ssEdit2 = new JTextField();
		ssEdit2.setBounds(145, 22, 149, 20);
		roleMixinPortion2.add(ssEdit2);
		ssEdit2.setColumns(10);

		ss2TypeLbl = new JLabel("OntoUML type:");
		ss2TypeLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		ss2TypeLbl.setBounds(20, 52, 88, 14);
		roleMixinPortion2.add(ss2TypeLbl);

		ssCB2 = new JComboBox();
		ssCB2.setEnabled(false);
		ssCB2.setBounds(145, 22, 149, 20);
		roleMixinPortion2.add(ssCB2);

		ssChk2 = new JCheckBox("Reuse class?");
		ssChk2.setBounds(307, 21, 97, 23);
		roleMixinPortion2.add(ssChk2);

		ssType2 = new JComboBox();
		ssType2.setBounds(145, 49, 149, 20);
		roleMixinPortion2.add(ssType2);

		role1Lbl = new JLabel("Role1:");
		role1Lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		role1Lbl.setBounds(18, 83, 59, 14);
		roleMixinPortion1.add(role1Lbl);

		role1Edit = new JTextField();
		role1Edit.setBounds(143, 78, 149, 20);
		roleMixinPortion1.add(role1Edit);
		role1Edit.setColumns(10);

		role1CB = new JComboBox();
		role1CB.setEnabled(false);
		role1CB.setBounds(143, 78, 149, 20);
		roleMixinPortion1.add(role1CB);

		role1Chk = new JCheckBox("Reuse class?");
		role1Chk.setBounds(305, 77, 97, 23);
		roleMixinPortion1.add(role1Chk);

		roleMixinPanel = new JPanel();
		roleMixinPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RoleMixin class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		roleMixinPanel.setBounds(0, 270, 435, 60);
		add(roleMixinPanel);
		roleMixinPanel.setLayout(null);

		lblRoleMixin = new JLabel("RoleMixin1:");
		lblRoleMixin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoleMixin.setBounds(20, 23, 79, 14);
		roleMixinPanel.add(lblRoleMixin);

		rmEdit = new JTextField();
		rmEdit.setBounds(146, 21, 149, 20);
		roleMixinPanel.add(rmEdit);
		rmEdit.setColumns(10);

		rmChk = new JCheckBox("Reuse class?");
		rmChk.setBounds(309, 20, 97, 23);
		roleMixinPanel.add(rmChk);

		rmCB = new JComboBox();
		rmCB.setEnabled(false);
		rmCB.setBounds(146, 21, 149, 20);
		roleMixinPanel.add(rmCB);

		hashChkCombo.put(rmChk, rmCB);
		hashChkEdit.put(rmChk, rmEdit);

		rmChk.addActionListener(getCheckBoxActionListener());

		JPanel relatorPanel = new JPanel();
		relatorPanel.setLayout(null);
		relatorPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Relator class", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		relatorPanel.setBounds(0, 339, 435, 60);
		add(relatorPanel);

		JLabel lblRelator = new JLabel("Relator1:");
		lblRelator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelator.setBounds(20, 23, 79, 14);
		relatorPanel.add(lblRelator);

		relEdit = new JTextField();
		relEdit.setColumns(10);
		relEdit.setBounds(146, 21, 149, 20);
		relatorPanel.add(relEdit);

		relChk = new JCheckBox("Reuse class?");
		relChk.setBounds(309, 20, 97, 23);
		relatorPanel.add(relChk);

		relCB = new JComboBox();
		relCB.setEnabled(false);
		relCB.setBounds(146, 21, 149, 20);
		relatorPanel.add(relCB);

		relDependencyPanel = new JPanel();
		relDependencyPanel.setLayout(null);
		relDependencyPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Relator Dependency", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		relDependencyPanel.setBounds(0, 406, 435, 116);
		add(relDependencyPanel);

		ss3Lbl = new JLabel("SortalUniversal3:");
		ss3Lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		ss3Lbl.setBounds(15, 22, 123, 14);
		relDependencyPanel.add(ss3Lbl);

		ssEdit3 = new JTextField();
		ssEdit3.setColumns(10);
		ssEdit3.setBounds(143, 19, 149, 20);
		relDependencyPanel.add(ssEdit3);

		ssCB3 = new JComboBox<String>();
		ssCB3.setEnabled(false);
		ssCB3.setBounds(143, 19, 149, 20);
		relDependencyPanel.add(ssCB3);

		ss3TypeLbl = new JLabel("OntoUML type:");
		ss3TypeLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		ss3TypeLbl.setBounds(15, 46, 97, 14);
		relDependencyPanel.add(ss3TypeLbl);

		ssChk3 = new JCheckBox("Reuse class?");
		ssChk3.setBounds(305, 18, 97, 23);
		relDependencyPanel.add(ssChk3);

		ssType3 = new JComboBox<String>();
		ssType3.setBounds(143, 44, 149, 20);
		relDependencyPanel.add(ssType3);

		role3Lbl = new JLabel("Role3:");
		role3Lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		role3Lbl.setBounds(18, 83, 59, 14);
		relDependencyPanel.add(role3Lbl);

		role3Edit = new JTextField();
		role3Edit.setColumns(10);
		role3Edit.setBounds(143, 78, 149, 20);
		relDependencyPanel.add(role3Edit);

		role3CB = new JComboBox();
		role3CB.setEnabled(false);
		role3CB.setBounds(143, 78, 149, 20);
		relDependencyPanel.add(role3CB);

		role3Chk = new JCheckBox("Reuse class?");
		role3Chk.setBounds(305, 77, 97, 23);
		relDependencyPanel.add(role3Chk);

		hashChkCombo.put(ssChk1, ssCB1);
		hashChkEdit.put(ssChk1, ssEdit1);
		hashEditCombo.put(ssEdit1, ssType1);

		hashChkCombo.put(ssChk2, ssCB2);
		hashChkEdit.put(ssChk2, ssEdit2);
		hashEditCombo.put(ssEdit2, ssType2);

		hashChkCombo.put(ssChk3, ssCB3);
		hashChkEdit.put(ssChk3, ssEdit3);
		hashEditCombo.put(ssEdit3, ssType3);

		hashChkCombo.put(role1Chk, role1CB);
		hashChkEdit.put(role1Chk, role1Edit);

		hashChkCombo.put(role2Chk, role2CB);
		hashChkEdit.put(role2Chk, role2Edit);

		hashChkCombo.put(role3Chk, role3CB);
		hashChkEdit.put(role3Chk, role3Edit);

		hashChkCombo.put(rmChk, rmCB);
		hashChkEdit.put(rmChk, rmEdit);

		hashChkCombo.put(relChk, relCB);
		hashChkEdit.put(relChk, relEdit);

		ssChk1.addActionListener(getCheckBoxActionListener(ssType1,ss1TypeLbl));
		ssChk2.addActionListener(getCheckBoxActionListener(ssType2,ss2TypeLbl));
		ssChk3.addActionListener(getCheckBoxActionListener(ssType3,ss3TypeLbl));

		role1Chk.addActionListener(getCheckBoxActionListener());
		role2Chk.addActionListener(getCheckBoxActionListener());
		role3Chk.addActionListener(getCheckBoxActionListener());

		rmChk.addActionListener(getCheckBoxActionListener());

		relChk.addActionListener(getCheckBoxActionListener());

		this.parser = parser;
		getModelValues(parser);
	}

	@Override
	public void getRunPattern(double x, double y) {
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		Fix _fix = new Fix();
		ArrayList<Generalization> generalizations = new ArrayList<>();

		//portion1
		Classifier ss1 = createClassifier(ssChk1, x, y);
		Classifier role1 = createClassifier(ClassStereotype.ROLE, role1Chk, x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(role1,ss1));

		//portion2
		Classifier ss2 = createClassifier(ssChk2, x+verticalDistance, y);
		Classifier role2 = createClassifier(ClassStereotype.ROLE, role2Chk, x+verticalDistance, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(role2, ss2));

		//dependency
		Classifier ss3 = createClassifier(ssChk3, x+verticalDistance, y);
		Classifier role3 = createClassifier(ClassStereotype.ROLE, role3Chk, x+(2*verticalDistance), y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(role3, ss3));

		//mixin
		Classifier roleMixin = createClassifier(ClassStereotype.ROLEMIXIN, rmChk, x+(verticalDistance/2), y+(horizontalDistance/2));

		Fix partialFix2 = outcomeFixer.createGeneralization(role1, roleMixin);
		_fix.addAll(partialFix2);
		generalizations.add((Generalization) partialFix2.getAdded().get(0));
		fix.addAll(_fix);

		Fix partialFix = outcomeFixer.createGeneralization(role2, roleMixin);
		_fix.addAll(partialFix);
		generalizations.add((Generalization) partialFix.getAdded().get(0));
		fix.addAll(_fix);

		fix.addAll(outcomeFixer.createGeneralizationSet(generalizations, true, true, "roleMixinGS"+UtilAssistant.getCont()));

		//Center side
		Classifier relator = createClassifier(ClassStereotype.RELATOR, relChk, x+(verticalDistance/2), y+(horizontalDistance/2));

		//Left Mediations
		Association leftMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, roleMixin).getAdded().get(0);

		fix.includeAdded(leftMediation);

		//Right Mediations
		Association rightMediation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MEDIATION, "", relator, role3).getAdded().get(0);

		fix.includeAdded(rightMediation);

		//Material
		Association material = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.MATERIAL, "", roleMixin, role3).getAdded().get(0);

		Association derivation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.DERIVATION, "", relator, material).getAdded().get(0);

		fix.includeAdded(material);
		fix.includeAdded(derivation);
	}

	@Override
	protected void getModelValues(OntoUMLParser parser) {
		String[] types = new String[]{"Kind", "Collective", "Quantity", "Subkind", "Phase", "Role"};
		ssType1.setModel(new DefaultComboBoxModel<String>(types));
		ssType2.setModel(new DefaultComboBoxModel<String>(types));
		ssType3.setModel(new DefaultComboBoxModel<String>(types));

		Set<SortalClass> substanceSortais = parser.getAllInstances(RefOntoUML.SortalClass.class);
		ssCB1.setModel(this.getCBModelFromSets(substanceSortais));
		ssChk1.setVisible(ssCB1.getModel().getSize() != 0);

		ssCB2.setModel(this.getCBModelFromSets(substanceSortais));
		ssChk2.setVisible(ssCB1.getModel().getSize() != 0);

		ssCB3.setModel(this.getCBModelFromSets(substanceSortais));
		ssChk3.setVisible(ssCB1.getModel().getSize() != 0);

		Set<Role> roles = parser.getAllInstances(RefOntoUML.Role.class);
		role1CB.setModel(this.getCBModelFromSets(roles));
		role1CB.setVisible(role1CB.getModel().getSize() != 0);

		role2CB.setModel(this.getCBModelFromSets(roles));
		role2CB.setVisible(role2CB.getModel().getSize() != 0);

		role3CB.setModel(this.getCBModelFromSets(roles));
		role3CB.setVisible(role3CB.getModel().getSize() != 0);

		Set<Relator> relators = parser.getAllInstances(RefOntoUML.Relator.class);
		relCB.setModel(this.getCBModelFromSets(relators));
		relChk.setVisible(relCB.getModel().getSize() != 0);

		Set<RoleMixin> rolemixins = parser.getAllInstances(RefOntoUML.RoleMixin.class);
		rmCB.setModel(this.getCBModelFromSets(rolemixins));
		rmChk.setVisible(rmCB.getModel().getSize() != 0);
	}
}
