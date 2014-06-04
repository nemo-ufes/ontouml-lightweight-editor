package br.ufes.inf.nemo.meronymic_validation.derivation.ui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivedMeronymic;
import br.ufes.inf.nemo.meronymic_validation.ui.StereotypeCombo;

public abstract class DerivedResultPanel extends JPanel {
	
	private static final long serialVersionUID = 8228160064746074236L;
	
	OntoUMLParser parser;
	DerivationTask<?> derivationTask;
	
	private JTextField nameText;
	private JTextField pathText;
	private JTextField wholeNameText;
	private JTextField partNameText;
	
	protected DerivedTable table;
	private StereotypeCombo stereotypeCombo;
	private JComboBox wholeTypeCombo;
	private JComboBox wholeMultiplicityCombo;
	private JComboBox partMultiplicityCombo;
	private JComboBox partTypeCombo;
	private JCheckBox isDerivedCheck;
	private JCheckBox isShareableCheck;
	private JCheckBox isEssentialCheck;
	private JCheckBox isImmutablePartCheck;
	private JCheckBox isInseparableCheck;
	private JCheckBox isImmutableWholeCheck;
	private JCheckBox wholeDerivedCheck;
	private JCheckBox wholeReadOnlyCheck;
	private JCheckBox wholeOrderedCheck;
	private JCheckBox wholeUniqueCheck;
	private JCheckBox partDerivedCheck;
	private JCheckBox partReadOnlyCheck;
	private JCheckBox partOrderedCheck;
	private JCheckBox partUniqueCheck;
	private JTextArea oclText;
	
	ListSelectionListener tableSelectionListener = new ListSelectionListener() {
		
		
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if(table.getSelectedRow()>=0)
				setRowData(table.getModel().getRow(table.getSelectedRow()));
		}
	};
	
	
	/**
	 * Create the panel.
	 * @param parser 
	 */
	public DerivedResultPanel(OntoUMLParser parser) {
		
		this.parser = parser;
		this.table = new DerivedTable();
		this.table.getSelectionModel().addListSelectionListener(tableSelectionListener);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Meta-Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(UIManager.getBorder("TitledBorder.border"));
		
		JLabel lblNewLabel = new JLabel("The following "+getRelationTypeString()+" relations were infered from the model:");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Whole End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Part End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "OCL Constraint", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 172, Short.MAX_VALUE)
						.addComponent(panel_2, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		oclText = new JTextArea();
		scrollPane_1.setViewportView(oclText);
		oclText.setLineWrap(true);
		panel_3.setLayout(gl_panel_3);
		
		JLabel label_1 = new JLabel("Name:");
		
		partNameText = new JTextField();
		partNameText.setColumns(10);
		
		JLabel label_2 = new JLabel("Type:");
		
		partTypeCombo = new JComboBox();
		partTypeCombo.setEditable(true);
		
		JLabel label_3 = new JLabel("Mult.:");
		
		partMultiplicityCombo = new JComboBox();
		partMultiplicityCombo.setEditable(true);
		
		partDerivedCheck = new JCheckBox("Derived");
		
		partReadOnlyCheck = new JCheckBox("ReadOnly");
		
		partOrderedCheck = new JCheckBox("Ordered");
		
		partUniqueCheck = new JCheckBox("Unique");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(5)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(partNameText, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(partTypeCombo, 0, 142, Short.MAX_VALUE)
								.addComponent(partMultiplicityCombo, 0, 142, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(partDerivedCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(partReadOnlyCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(partOrderedCheck, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(partUniqueCheck, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))))
					.addGap(14))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(partNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(partTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(9)
							.addComponent(label_2)))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(partMultiplicityCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(9)
							.addComponent(label_3)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(partDerivedCheck)
						.addComponent(partUniqueCheck))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(partReadOnlyCheck)
						.addComponent(partOrderedCheck))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label = new JLabel("Name:");
		
		JLabel lblType = new JLabel("Type:");
		
		JLabel lblMult = new JLabel("Mult.:");
		
		wholeNameText = new JTextField();
		wholeNameText.setColumns(10);
		
		wholeTypeCombo = new JComboBox();
		wholeTypeCombo.setEditable(true);
		
		wholeMultiplicityCombo = new JComboBox();
		wholeMultiplicityCombo.setEditable(true);
		
		wholeDerivedCheck = new JCheckBox("Derived");
		
		wholeReadOnlyCheck = new JCheckBox("ReadOnly");
		
		wholeOrderedCheck = new JCheckBox("Ordered");
		
		wholeUniqueCheck = new JCheckBox("Unique");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(wholeDerivedCheck)
									.addGap(29))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(wholeReadOnlyCheck)
									.addGap(18)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(wholeOrderedCheck, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(wholeUniqueCheck, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblMult)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(label)
										.addPreferredGap(ComponentPlacement.UNRELATED))
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
										.addGap(13))))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(wholeTypeCombo, 0, 137, Short.MAX_VALUE)
									.addComponent(wholeNameText, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
								.addComponent(wholeMultiplicityCombo, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(wholeNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(wholeTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMult)
						.addComponent(wholeMultiplicityCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(wholeDerivedCheck)
						.addComponent(wholeUniqueCheck))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(wholeReadOnlyCheck)
						.addComponent(wholeOrderedCheck))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblStereotype = new JLabel("Stereotype:");
		
		JLabel lblPath = new JLabel("Path:");
		
		nameText = new JTextField();
		nameText.setColumns(10);
		
		pathText = new JTextField();
		pathText.setColumns(10);
		
		stereotypeCombo = new StereotypeCombo();
		
		isDerivedCheck = new JCheckBox("Derived");
		
		isShareableCheck = new JCheckBox("Shareable");
		
		isImmutablePartCheck = new JCheckBox("Immutable Part");
		
		isImmutableWholeCheck = new JCheckBox("Immutable Whole");
		
		isEssentialCheck = new JCheckBox("Essential");
		
		isInseparableCheck = new JCheckBox("Inseparable");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(isShareableCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPath, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblStereotype, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(isDerivedCheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(pathText, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(stereotypeCombo, 0, 223, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(isEssentialCheck, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(isInseparableCheck, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(isImmutablePartCheck)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(isImmutableWholeCheck)
							.addGap(23))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(nameText, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStereotype)
						.addComponent(stereotypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPath)
						.addComponent(pathText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(isDerivedCheck)
						.addComponent(isEssentialCheck)
						.addComponent(isInseparableCheck))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(isShareableCheck)
						.addComponent(isImmutablePartCheck)
						.addComponent(isImmutableWholeCheck))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	
	

	protected void setRowData(DerivedMeronymic derived) {
		System.out.println("setRowData!!!");
		
		nameText.setText(derived.getName());
		
		if(derived.getExistingMeronymic()==null)
			pathText.setText(OntoUMLNameHelper.getPath(derived.getWhole()));
		else
			pathText.setText(OntoUMLNameHelper.getPath(derived.getExistingMeronymic()));
		
		stereotypeCombo.setSelectedItem(derived.getStereotype());
		
		isDerivedCheck.setSelected(derived.isDerived());
		isShareableCheck.setSelected(derived.isShareable());
		isEssentialCheck.setSelected(derived.isEssential());
		isImmutablePartCheck.setSelected(derived.isImmutablePart());
		isInseparableCheck.setSelected(derived.isInseparable());
		isImmutableWholeCheck.setSelected(derived.isImmutableWhole());
		
		partReadOnlyCheck.setSelected(derived.isReadOnlyPart());
		partDerivedCheck.setSelected(derived.isDerivedPart());
		partOrderedCheck.setSelected(derived.isOrderedPart());
		partUniqueCheck.setSelected(derived.isUniquePart());
		partMultiplicityCombo.setSelectedItem(OntoUMLNameHelper.getMultiplicity(derived.getLowerPart(), derived.getUpperPart(), true, ".."));
		partNameText.setText(derived.getPartEndName());
		partTypeCombo.setSelectedItem(derived.getPart().getName());
		
		wholeReadOnlyCheck.setSelected(derived.isReadOnlyWhole());
		wholeDerivedCheck.setSelected(derived.isDerivedWhole());
		wholeOrderedCheck.setSelected(derived.isOrderedWhole());
		wholeUniqueCheck.setSelected(derived.isUniqueWhole());
		wholeMultiplicityCombo.setSelectedItem(OntoUMLNameHelper.getMultiplicity(derived.getLowerWhole(), derived.getUpperWhole(), true, ".."));
		wholeNameText.setText(derived.getWholeEndName());
		wholeTypeCombo.setSelectedItem(derived.getWhole().getName());
		
		oclText.setText(derived.getOclDerivationRule());
	}

	protected void clearRowData() {
		System.out.println("clearRowData!!!");
		
		nameText.setText("");
		pathText.setText("");		
		stereotypeCombo.setSelectedItem(null);
		
		isDerivedCheck.setSelected(false);
		isShareableCheck.setSelected(false);
		isEssentialCheck.setSelected(false);
		isImmutablePartCheck.setSelected(false);
		isInseparableCheck.setSelected(false);
		isImmutableWholeCheck.setSelected(false);
		
		partReadOnlyCheck.setSelected(false);
		partDerivedCheck.setSelected(false);
		partOrderedCheck.setSelected(false);
		partUniqueCheck.setSelected(false);
		partMultiplicityCombo.setSelectedItem(null);
		partNameText.setText("");
		partTypeCombo.setSelectedItem(null);
		
		wholeReadOnlyCheck.setSelected(false);
		wholeDerivedCheck.setSelected(false);
		wholeOrderedCheck.setSelected(false);
		wholeUniqueCheck.setSelected(false);
		wholeMultiplicityCombo.setSelectedItem(null);
		wholeNameText.setText("");
		wholeTypeCombo.setSelectedItem(null);
		
		oclText.setText("");
	}
	
	
	public void setTask(){
		derivationTask = createDerivationTask();
	}
	
	public DerivationTask<?> getTask(){
		return derivationTask;
	}
	
	public void executeTask(){
		clearRowData();
		table.getModel().clear();
		
		if(derivationTask==null || derivationTask.isCancelled() || derivationTask.isDone())
			setTask();
		
		derivationTask.execute();
	}
	
	public boolean isTableEmpty() {
		return table.getModel().getRowCount()==0;
	}
	
	public abstract DerivationTask<?> createDerivationTask();
	protected abstract String getRelationTypeString();
}
