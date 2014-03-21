package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class CreateDataTypeComposite extends Composite {
	
	public Label lblSource;		
	public Label lblAttributeType;
	public Label lblAttributeName;	
	private Label lblAttributeTypeName;
	private Label label;	
	private Combo typeList;	
	private Text nameText;
	private Text typeNameText;
	public AttrTable sourceTable;	
	private Button btnSourceDelete;	
	public Button btnSourceCreate;	
	
	public UndefPhaseOccurrence up;
	private Combo multCombo;
	private Label lblCardinality;
	private Label lblStereotype;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateDataTypeComposite(Composite parent, int style, UndefPhaseOccurrence up) 
	{
		super(parent, style);
		this.up=up;
		
		btnSourceCreate = new Button(this, SWT.NONE);
		btnSourceCreate.setBounds(357, 75, 54, 25);
		btnSourceCreate.setText("Create");
		btnSourceCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Primitive type"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewPrimitiveType(nameText.getText(),typeNameText.getText(),multCombo.getText());
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("DataType type"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewDataType(nameText.getText(),typeNameText.getText(),multCombo.getText());
//				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
//					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewEnumeration(nameText.getText(),typeNameText.getText(),multCombo.getText());
			 }
		});	
		
		lblSource = new Label(this, SWT.NONE);
		lblSource.setBounds(10, 80, 341, 15);
		lblSource.setText(""+up.getGeneral().getName());
		
		typeList = new Combo(this, SWT.READ_ONLY);
		typeList.setItems(new String[] {"Primitive type", "Data type", "Enumeration"});
		typeList.setBounds(348, 36, 123, 23);
		typeList.select(0);
		
		nameText = new Text(this, SWT.BORDER);
		nameText.setBounds(62, 7, 176, 23);
		
		typeNameText = new Text(this, SWT.BORDER);
		typeNameText.setBounds(62, 36, 176, 23);
		
		lblAttributeName = new Label(this, SWT.NONE);
		lblAttributeName.setBounds(10, 10, 46, 20);
		lblAttributeName.setText("Name:");
		
		lblAttributeTypeName = new Label(this, SWT.NONE);
		lblAttributeTypeName.setBounds(10, 39, 46, 15);
		lblAttributeTypeName.setText("Type:");
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 65, 462, 8);
		
		btnSourceDelete = new Button(this, SWT.NONE);
		btnSourceDelete.setBounds(417, 75, 54, 25);
		btnSourceDelete.setText("Delete");
		
		multCombo = new Combo(this, SWT.NONE);
		multCombo.setItems(new String[] {"1", "0..1", "1..*", "0..*"});
		multCombo.setBounds(348, 7, 123, 23);
		multCombo.select(0);
		
		lblCardinality = new Label(this, SWT.NONE);
		lblCardinality.setBounds(244, 10, 98, 20);
		lblCardinality.setText("Cardinality:");
		
		lblStereotype = new Label(this, SWT.NONE);
		lblStereotype.setBounds(244, 39, 98, 15);
		lblStereotype.setText("Stereotype:");
		btnSourceDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				sourceTable.removeLine();				 
			 }
		});		
		
		sourceTable = new AttrTable(this, SWT.BORDER, ((RefOntoUML.Class)up.getGeneral()).getOwnedAttribute());
		sourceTable.getTable().setBounds(10, 106, 462, 97);
		
//		Composite composite = new Composite(this, SWT.NONE);
//		composite.setBounds(10, 106, 462, 97);		
	}

	public void enable(boolean value)
	{				
		lblSource.setEnabled(value);		
		lblAttributeType.setEnabled(value);
		lblAttributeName.setEnabled(value);
		lblAttributeTypeName.setEnabled(value);
		sourceTable.getTable().setEnabled(value);		
		label.setEnabled(value);	
		typeList.setEnabled(value);	
		nameText.setEnabled(value);
		typeNameText.setEnabled(value);		
		btnSourceDelete.setEnabled(value);		
		btnSourceCreate.setEnabled(value);
	}
	
	public HashMap<String,String> getSourceMapType()
	{
		return sourceTable.getValues();
	}
	
	public HashMap<String,String> getSourceMapStereo()
	{
		return sourceTable.getStereotypes();
	}
}
