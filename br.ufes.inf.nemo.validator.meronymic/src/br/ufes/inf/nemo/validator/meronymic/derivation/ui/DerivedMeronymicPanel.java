package br.ufes.inf.nemo.validator.meronymic.derivation.ui;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.validator.meronymic.MeronymicItem;
import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic;

public class DerivedMeronymicPanel extends JPanel {

	private static final long serialVersionUID = -924382524010994410L;
	
	private JTextField patternField;
	private JTextField stereotypeField;
	private JTextPane pathText;
	private JTextField wholeTypeField;
	private JTextField wholeMultiplicityField;
	private JTextField wholeStereoField;
	private JTextField partTypeField;
	private JTextField partMultiplicityField;
	private JTextField partStereoField;
	private JCheckBox immutableWholeCheck;
	private JCheckBox immutablePartCheck;
	private JCheckBox shareableCheck;
	private JCheckBox derivedCheck;
	private JCheckBox inseparableCheck;
	private JCheckBox essentialCheck;
	private JCheckBox partOrderedCheck;
	private JCheckBox partUniqueCheck;
	private JCheckBox partDerivedCheck;
	private JCheckBox partReadOnlyCheck;
	private JCheckBox wholeOrderedCheck;
	private JCheckBox wholeDerivedCheck;
	private JCheckBox wholeUniqueCheck;
	private JCheckBox wholeReadOnlyCheck;
	
	private DerivedMeronymic derived;
	private JTextPane oclText;
	
	/**
	 * Create the panel.
	 */
	public DerivedMeronymicPanel(DerivedMeronymic derived) {
		this.derived = derived;
		
		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setBorder(new TitledBorder(null, "Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("Pattern:");
		
		JLabel label_1 = new JLabel("Stereotype:");
		
		JLabel label_2 = new JLabel("Path:");
		
		derivedCheck = new JCheckBox("Derived");
		derivedCheck.setEnabled(false);
		
		shareableCheck = new JCheckBox("Shareable");
		shareableCheck.setEnabled(false);
		
		inseparableCheck = new JCheckBox("Inseparable");
		inseparableCheck.setEnabled(false);
		
		immutablePartCheck = new JCheckBox("Immutable Part");
		immutablePartCheck.setEnabled(false);
		
		immutableWholeCheck = new JCheckBox("Immutable Whole");
		immutableWholeCheck.setEnabled(false);
		
		essentialCheck = new JCheckBox("Essential");
		essentialCheck.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		patternField = new JTextField();
		patternField.setEditable(false);
		patternField.setColumns(10);
		
		stereotypeField = new JTextField();
		stereotypeField.setEditable(false);
		stereotypeField.setColumns(10);
		GroupLayout gl_propertiesPanel = new GroupLayout(propertiesPanel);
		gl_propertiesPanel.setHorizontalGroup(
			gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 568, Short.MAX_VALUE)
				.addGroup(gl_propertiesPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_propertiesPanel.createSequentialGroup()
							.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(derivedCheck, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(shareableCheck, Alignment.LEADING))
							.addGap(18)
							.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(inseparableCheck, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addComponent(immutablePartCheck))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(immutableWholeCheck, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addComponent(essentialCheck, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
						.addComponent(patternField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
						.addComponent(stereotypeField, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_propertiesPanel.setVerticalGroup(
			gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 222, Short.MAX_VALUE)
				.addGroup(gl_propertiesPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(stereotypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(patternField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
					.addGap(9)
					.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_propertiesPanel.createSequentialGroup()
							.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(derivedCheck)
								.addComponent(essentialCheck))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_propertiesPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(shareableCheck)
								.addComponent(immutableWholeCheck)))
						.addGroup(gl_propertiesPanel.createSequentialGroup()
							.addComponent(inseparableCheck)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(immutablePartCheck))))
		);
		
		pathText = new JTextPane();
		pathText.setEditable(false);
		scrollPane.setViewportView(pathText);
		propertiesPanel.setLayout(gl_propertiesPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Whole End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_3 = new JLabel("Type:");
		
		JLabel label_4 = new JLabel("Multiplicity:");
		
		JLabel label_5 = new JLabel("Stereotype:");
		
		wholeUniqueCheck = new JCheckBox("Unique");
		wholeUniqueCheck.setEnabled(false);
		
		wholeOrderedCheck = new JCheckBox("Ordered");
		wholeOrderedCheck.setEnabled(false);
		
		wholeTypeField = new JTextField();
		wholeTypeField.setEditable(false);
		wholeTypeField.setColumns(10);
		
		wholeMultiplicityField = new JTextField();
		wholeMultiplicityField.setEditable(false);
		wholeMultiplicityField.setColumns(10);
		
		wholeStereoField = new JTextField();
		wholeStereoField.setEditable(false);
		wholeStereoField.setColumns(10);
		
		wholeDerivedCheck = new JCheckBox("Derived");
		wholeDerivedCheck.setEnabled(false);
		
		wholeReadOnlyCheck = new JCheckBox("ReadOnly");
		wholeReadOnlyCheck.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(wholeMultiplicityField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addComponent(wholeStereoField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addComponent(wholeTypeField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(wholeDerivedCheck)
								.addComponent(wholeUniqueCheck, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addGap(44)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(wholeOrderedCheck)
								.addComponent(wholeReadOnlyCheck))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(wholeTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(wholeStereoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(wholeMultiplicityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(wholeReadOnlyCheck)
						.addComponent(wholeDerivedCheck))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(wholeUniqueCheck)
						.addComponent(wholeOrderedCheck))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Whole End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_6 = new JLabel("Type:");
		
		JLabel label_7 = new JLabel("Multiplicity:");
		
		JLabel label_8 = new JLabel("Stereotype:");
		
		partUniqueCheck = new JCheckBox("Unique");
		partUniqueCheck.setEnabled(false);
		
		partOrderedCheck = new JCheckBox("Ordered");
		partOrderedCheck.setEnabled(false);
		
		partTypeField = new JTextField();
		partTypeField.setEditable(false);
		partTypeField.setColumns(10);
		
		partMultiplicityField = new JTextField();
		partMultiplicityField.setEditable(false);
		partMultiplicityField.setColumns(10);
		
		partStereoField = new JTextField();
		partStereoField.setEditable(false);
		partStereoField.setColumns(10);
		
		partDerivedCheck = new JCheckBox("Derived");
		partDerivedCheck.setEnabled(false);
		
		partReadOnlyCheck = new JCheckBox("ReadOnly");
		partReadOnlyCheck.setEnabled(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 292, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(partMultiplicityField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addComponent(partStereoField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addComponent(partTypeField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(partDerivedCheck)
								.addComponent(partUniqueCheck, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addGap(44)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(partOrderedCheck, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(partReadOnlyCheck))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 143, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(partTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(partStereoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(partMultiplicityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(partReadOnlyCheck)
						.addComponent(partDerivedCheck))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(partUniqueCheck)
						.addComponent(partOrderedCheck))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "OCL Derivation Constraint", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		oclText = new JTextPane();
		oclText.setEditable(false);
		scrollPane_1.setViewportView(oclText);
		panel_2.setLayout(gl_panel_2);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 278, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
				.addComponent(propertiesPanel, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(propertiesPanel, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

		fillData();
		
	}
	
	private void fillData(){
		stereotypeField.setText(derived.getStereotypeName());
		patternField.setText(derived.getPatternString());
		pathText.setText(MeronymicItem.getFullPathText(derived.getPath()));
		derivedCheck.setSelected(derived.isDerived());
		shareableCheck.setSelected(derived.isShareable());
		essentialCheck.setSelected(derived.isEssential());
		immutablePartCheck.setSelected(derived.isImmutablePart());
		inseparableCheck.setSelected(derived.isInseparable());
		immutableWholeCheck.setSelected(derived.isImmutableWhole());
		
		wholeTypeField.setText(derived.getWhole().getName());
		wholeStereoField.setText(OntoUMLNameHelper.getTypeName(derived.getWhole(), false)); 
		wholeMultiplicityField.setText(OntoUMLNameHelper.getMultiplicity(derived.getLowerWhole(), derived.getUpperWhole(), false, ".."));
		wholeDerivedCheck.setSelected(derived.isDerivedWhole());
		wholeOrderedCheck.setSelected(derived.isOrderedWhole());
		wholeReadOnlyCheck.setSelected(derived.isReadOnlyWhole());
		wholeUniqueCheck.setSelected(derived.isUniqueWhole());
		
		partTypeField.setText(derived.getPart().getName());
		partStereoField.setText(OntoUMLNameHelper.getTypeName(derived.getPart(), false)); 
		partMultiplicityField.setText(OntoUMLNameHelper.getMultiplicity(derived.getLowerPart(), derived.getUpperPart(), false, ".."));
		partDerivedCheck.setSelected(derived.isDerivedPart());
		partOrderedCheck.setSelected(derived.isOrderedPart());
		partReadOnlyCheck.setSelected(derived.isReadOnlyPart());
		partUniqueCheck.setSelected(derived.isUniquePart());
		
		oclText.setText(derived.getOclDerivationRule());
	}
}
