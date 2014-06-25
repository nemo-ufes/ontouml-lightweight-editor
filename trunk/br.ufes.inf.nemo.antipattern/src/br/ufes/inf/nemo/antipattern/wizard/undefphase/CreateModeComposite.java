package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlverificator.MultiplicityValidator;

import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.custom.CCombo;

public class CreateModeComposite extends Composite {
	
	public Label lblSource;		
	public Label lblAttributeType;
	public Label lblAttributeName;	
	private Label label;	
	private Text nameText;
	public ModeTable tableCreator;	
	private Button deleteButton;	
	public Button createButton;	
	
	public UndefPhaseOccurrence up;
	private Combo multCombo;
	private Label lblCardinality;
	private Table modeTable;
	private CCombo phaseCombo;
	private Text console;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateModeComposite(Composite parent, int style, UndefPhaseOccurrence up) 
	{
		super(parent, SWT.BORDER);
		this.up=up;
		
		createButton = new Button(this, SWT.NONE);
		createButton.setText("Create");
		
		createButton.addSelectionListener(createAction);	
		
		lblSource = new Label(this, SWT.NONE);
		lblSource.setText("Phase:");
		
		nameText = new Text(this, SWT.BORDER);
		
		lblAttributeName = new Label(this, SWT.NONE);
		lblAttributeName.setText("Mode:");
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		deleteButton = new Button(this, SWT.NONE);
		deleteButton.setText("Delete");
		
		multCombo = new Combo(this, SWT.NONE);
		multCombo.setText("");
		multCombo.setItems(new String[] {"1", "0..1", "1..*", "0..*"});
		
		lblCardinality = new Label(this, SWT.NONE);
		lblCardinality.setText("Multiplicity:");
		
		deleteButton.addSelectionListener(removeAction);		
		
		tableCreator = new ModeTable(this, SWT.BORDER | SWT.V_SCROLL, up);
		
//		modeTable = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		modeTable = tableCreator.getTable();
		
		phaseCombo = new CCombo(this, SWT.BORDER | SWT.READ_ONLY);
		setPhaseComboItens();
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("List of modes to create:");
		
		console = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		console.setText("Please, define modes to be created...");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, label, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, modeTable, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, groupLayout.createSequentialGroup()
							.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
								.add(GroupLayout.LEADING, groupLayout.createSequentialGroup()
									.add(lblAttributeName, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(nameText, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
								.add(GroupLayout.LEADING, groupLayout.createSequentialGroup()
									.add(lblSource, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(phaseCombo, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
							.add(22)
							.add(lblCardinality, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(multCombo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(console, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
							.add(18)
							.add(createButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(deleteButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.add(GroupLayout.LEADING, lblNewLabel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(7)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
							.add(multCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.add(lblCardinality, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(3)
							.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
								.add(lblAttributeName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.add(nameText, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(lblSource)
						.add(phaseCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(label, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblNewLabel)
					.add(3)
					.add(modeTable, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(deleteButton)
						.add(createButton)
						.add(console, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(8))
		);
		groupLayout.linkSize(new Control[] {nameText, phaseCombo}, GroupLayout.VERTICAL);
		groupLayout.linkSize(new Control[] {lblSource, lblAttributeName}, GroupLayout.VERTICAL);
		groupLayout.linkSize(new Control[] {lblSource, lblAttributeName}, GroupLayout.HORIZONTAL);
		setLayout(groupLayout);
			
	}

	public void setPhaseComboItens(){
		for (Classifier phase : up.getPhases()) {
			phaseCombo.add(phase.getName());
		}
	}
	
	public Classifier getSelectedPhase(){
		if(phaseCombo.getSelectionIndex()!=-1)
			return up.getPhases().get(phaseCombo.getSelectionIndex());

		return null;
	}
	
	public void enable(boolean value)
	{
		lblSource.setEnabled(value);		
		lblAttributeType.setEnabled(value);
		lblAttributeName.setEnabled(value);
		tableCreator.getTable().setEnabled(value);		
		label.setEnabled(value);	
		nameText.setEnabled(value);
		deleteButton.setEnabled(value);		
		createButton.setEnabled(value);
	}
	
	public HashMap<String,String> getValues()
	{
		return tableCreator.getValues();
	}
	
	public ArrayList<Classifier> getPhases()
	{
		return tableCreator.getPhases();
	}
	
	protected void cleanAllFields() {
		nameText.setText("");
		phaseCombo.select(-1);
		multCombo.setText("");
	}
	
	/////////////// Actions
	SelectionAdapter createAction = new SelectionAdapter() {
		 @Override
           public void widgetSelected(SelectionEvent e) {				 
			 
			 if (nameText.getText().isEmpty()){
				 console.setText("Name cannot be empty! Type in a name and try again.");
				 return;
			 }
			 
			if (phaseCombo.getSelectionIndex()==-1){
				 console.setText("No phase is selected! Select one and try again.");
				 return;
			}
			
			MultiplicityValidator validator = new MultiplicityValidator(multCombo.getText());
			if (!validator.isValid()){
				 console.setText("Invalid multiplicity! Insert a valid multiplicity value.");
				 return;
			}
			
			tableCreator.addNewMode(nameText.getText(),multCombo.getText(),phaseCombo.getSelectionIndex());
			console.setText("New mode successfuly created!");
			cleanAllFields();
		 }
	};
	
	SelectionAdapter removeAction = new SelectionAdapter() {
		 @Override
           public void widgetSelected(SelectionEvent e) {
			
			 if(modeTable.getSelectionIndex()==-1){
				 console.setText("No mode is selected! To delete, please select a row in the table.");
				 return;
			 }
				 
			 tableCreator.removeLine();				 
			 console.setText("Mode successfully removed!");
		 }
	};

	
}
