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
import org.eclipse.swt.widgets.Text;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class CreateModeComposite extends Composite {
	
	public Label lblSource;		
	public Label lblAttributeType;
	public Label lblAttributeName;	
	private Label label;	
	private Text nameText;
	public ModeTable sourceTable;	
	private Button btnSourceDelete;	
	public Button btnSourceCreate;	
	
	public UndefPhaseOccurrence up;
	private Combo multCombo;
	private Label lblCardinality;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateModeComposite(Composite parent, int style, UndefPhaseOccurrence up) 
	{
		super(parent, style);
		this.up=up;
		
		btnSourceCreate = new Button(this, SWT.NONE);
		btnSourceCreate.setBounds(357, 45, 54, 25);
		btnSourceCreate.setText("Create");
		btnSourceCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {				 
				 if (!nameText.getText().isEmpty()) sourceTable.addNewMode(nameText.getText(),multCombo.getText());
			 }
		});	
		
		lblSource = new Label(this, SWT.NONE);
		lblSource.setBounds(10, 50, 341, 15);
		
		nameText = new Text(this, SWT.BORDER);
		nameText.setBounds(62, 7, 176, 23);
		
		lblAttributeName = new Label(this, SWT.NONE);
		lblAttributeName.setBounds(10, 10, 46, 20);
		lblAttributeName.setText("Mode:");
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 36, 462, 8);
		
		btnSourceDelete = new Button(this, SWT.NONE);
		btnSourceDelete.setBounds(417, 45, 54, 25);
		btnSourceDelete.setText("Delete");
		
		multCombo = new Combo(this, SWT.NONE);
		multCombo.setItems(new String[] {"1", "0..1", "1..*", "0..*"});
		multCombo.setBounds(348, 7, 123, 23);
		multCombo.select(0);
		
		lblCardinality = new Label(this, SWT.NONE);
		lblCardinality.setBounds(244, 10, 98, 20);
		lblCardinality.setText("Cardinality:");
		btnSourceDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				sourceTable.removeLine();				 
			 }
		});		
		
		sourceTable = new ModeTable(this, SWT.BORDER | SWT.V_SCROLL, up.getPhases());
		sourceTable.getTable().setBounds(10, 76, 462, 136);	
		
//		Composite composite = new Composite(this, SWT.NONE);
//		composite.setBounds(10, 76, 462, 136);		
	}

	public void enable(boolean value)
	{
		lblSource.setEnabled(value);		
		lblAttributeType.setEnabled(value);
		lblAttributeName.setEnabled(value);
		sourceTable.getTable().setEnabled(value);		
		label.setEnabled(value);	
		nameText.setEnabled(value);
		btnSourceDelete.setEnabled(value);		
		btnSourceCreate.setEnabled(value);
	}
	
	public HashMap<String,String> getValues()
	{
		return sourceTable.getValues();
	}
	
	public ArrayList<Classifier> getPhases()
	{
		return sourceTable.getPhases();
	}
}
