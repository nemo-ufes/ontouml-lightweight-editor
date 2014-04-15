package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class DataTypeComposite extends Composite {

	public Label lblTarget;		
	public Label lblSource;		
	public Label lblAttributeType;
	public Label lblAttributeName;
	private Label lblAttributeTypeName;
	private Label label;	
	private Combo typeList;	
	private Text nameText;
	private Text typeNameText;
	public AttrTable sourceTable;
	private AttrTable targetTable;
	private Button btnSourceDelete;
	private Button btnTargetDelete;
	public Button btnSourceCreate;
	public Button btnTargetCreate;
	public StyledText oclTextComp;
	
	public UndefFormalOccurrence uf;
	private Button btnTargetTemplate;
	private Button btnSourceTemplate;
	
	private final String sourceTemplate, targetTemplate;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DataTypeComposite(Composite parent, int style, UndefFormalOccurrence uf) 
	{
		super(parent, style);
		this.uf=uf;
		
		
		uf.getFixer().fixPropertyName(uf.getFormal().getMemberEnd().get(0));
		uf.getFixer().fixPropertyName(uf.getFormal().getMemberEnd().get(1));
		
		String sourceName = uf.addQuotes(uf.getSource().getName());
		String targetName = uf.addQuotes(uf.getTarget().getName());
		String sourceEnd = uf.addQuotes(uf.getFormal().getMemberEnd().get(0).getName());
		String targetEnd = uf.addQuotes(uf.getFormal().getMemberEnd().get(1).getName());
		
		sourceTemplate ="context "+sourceName+"::<"+targetEnd+"> : "+targetName+"\r\n" +
						"derive : <COMPLETE-HERE>";
		
		targetTemplate ="context "+targetName+"::<"+sourceEnd+"> : "+sourceName+"\r\n" +
						"derive : <COMPLETE-HERE>";
		
		sourceTable = new AttrTable(this, SWT.BORDER, ((RefOntoUML.Class)uf.getSource()).getOwnedAttribute());
		sourceTable.getTable().setBounds(10, 112, 260, 87);
		
		btnSourceCreate = new Button(this, SWT.NONE);
		btnSourceCreate.setBounds(225, 81, 45, 25);
		btnSourceCreate.setText("Create");
		btnSourceCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getSelectionIndex()==0)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewPrimitiveType(nameText.getText(),typeNameText.getText());
				 if (typeList.getSelectionIndex()==1)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewDataType(nameText.getText(),typeNameText.getText());
//				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
//					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewEnumeration(nameText.getText(),typeNameText.getText());
			 }
		});	
		
		lblSource = new Label(this, SWT.NONE);
		lblSource.setBounds(10, 91, 158, 15);
		lblSource.setText(""+uf.getSource().getName());
		
		targetTable = new AttrTable(this, SWT.BORDER, ((RefOntoUML.Class)uf.getTarget()).getOwnedAttribute());
		targetTable.getTable().setBounds(283, 112, 260, 87);
				
		
		lblTarget = new Label(this, SWT.NONE);
		lblTarget.setBounds(283, 91, 158, 15);
		lblTarget.setText(""+uf.getTarget().getName());
		
		btnTargetCreate = new Button(this, SWT.NONE);
		btnTargetCreate.setBounds(498, 81, 45, 25);
		btnTargetCreate.setText("Create");		
		btnTargetCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getSelectionIndex()==0)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewPrimitiveType(nameText.getText(),typeNameText.getText());  
				 if (typeList.getSelectionIndex()==1)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewDataType(nameText.getText(),typeNameText.getText());
//				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
//					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewEnumeration(nameText.getText(),typeNameText.getText());				 
			 }
		});	

		oclTextComp = new StyledText(this,  SWT.V_SCROLL | SWT.BORDER);
		oclTextComp.setText(sourceTemplate);
		oclTextComp.setBounds(10, 205, 533, 85);
		
		typeList = new Combo(this, SWT.READ_ONLY);
		typeList.setItems(new String[] {"Primitive type", "Data type", "Enumeration"});
		typeList.setBounds(10, 31, 195, 23);
		typeList.select(0);
		
		nameText = new Text(this, SWT.BORDER);
		nameText.setBounds(211, 31, 150, 23);
		
		typeNameText = new Text(this, SWT.BORDER);
		typeNameText.setBounds(367, 31, 176, 23);
		
		lblAttributeType = new Label(this, SWT.NONE);
		lblAttributeType.setBounds(10, 10, 195, 15);
		lblAttributeType.setText("Attribute Type:");
		
		lblAttributeName = new Label(this, SWT.NONE);
		lblAttributeName.setBounds(211, 10, 150, 15);
		lblAttributeName.setText("Attribute Name:");
		
		lblAttributeTypeName = new Label(this, SWT.NONE);
		lblAttributeTypeName.setBounds(367, 10, 176, 15);
		lblAttributeTypeName.setText("Attribute Type Name:");
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 71, 533, 4);
		
		btnSourceDelete = new Button(this, SWT.NONE);
		btnSourceDelete.setBounds(174, 81, 45, 25);
		btnSourceDelete.setText("Delete");
		btnSourceDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 sourceTable.removeLine();				 
			 }
		});	
		
		btnTargetDelete = new Button(this, SWT.NONE);
		btnTargetDelete.setBounds(447, 81, 45, 25);
		btnTargetDelete.setText("Delete");
		btnTargetDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				targetTable.removeLine();				 
			 }
		});		
		
		btnTargetTemplate = new Button(this, SWT.NONE);
		btnTargetTemplate.setBounds(442, 299, 101, 25);
		btnTargetTemplate.setText("Target Template");
		btnTargetTemplate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				oclTextComp.setText(targetTemplate);
			}
		});
		
		btnSourceTemplate = new Button(this, SWT.NONE);
		btnSourceTemplate.setText("Source Template");
		btnSourceTemplate.setBounds(335, 299, 101, 25);
		btnSourceTemplate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				oclTextComp.setText(sourceTemplate);
			}
		});
		
	}

	public void enable(boolean value)
	{
		lblTarget.setEnabled(value);		
		lblSource.setEnabled(value);		
		lblAttributeType.setEnabled(value);
		lblAttributeName.setEnabled(value);
		lblAttributeTypeName.setEnabled(value);
		sourceTable.getTable().setEnabled(value);
		targetTable.getTable().setEnabled(value);
		label.setEnabled(value);	
		typeList.setEnabled(value);	
		nameText.setEnabled(value);
		typeNameText.setEnabled(value);		
		btnSourceDelete.setEnabled(value);
		btnTargetDelete.setEnabled(value);
		btnSourceCreate.setEnabled(value);
		btnTargetCreate.setEnabled(value);
		oclTextComp.setEnabled(value);	
		oclTextComp.setEditable(value);
	}
	
	public HashMap<String,String> getTargetMapType()
	{
		return targetTable.getValues();
	}
	
	public HashMap<String,String> getTargetMapStereo()
	{
		return targetTable.getStereotypes();
	}
	
	public HashMap<String,String> getSourceMapType()
	{
		return sourceTable.getValues();
	}
	
	public HashMap<String,String> getSourceMapStereo()
	{
		return sourceTable.getStereotypes();
	}
	
	public String getConstraints()
	{
		return oclTextComp.getText();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
