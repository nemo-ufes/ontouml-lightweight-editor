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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

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
	public AttrTable sourceTableCreator;
	private AttrTable targetTableCreator;
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
		
		sourceTableCreator = new AttrTable(this, SWT.BORDER, ((RefOntoUML.Class)uf.getSource()).getOwnedAttribute());
		Table sourceTable = sourceTableCreator.getTable();
//		Table sourceTable = new Table(this, SWT.BORDER);
		
		btnSourceCreate = new Button(this, SWT.NONE);
		btnSourceCreate.setText("Create");
		btnSourceCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getSelectionIndex()==0)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTableCreator.addNewPrimitiveType(nameText.getText(),typeNameText.getText());
				 if (typeList.getSelectionIndex()==1)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTableCreator.addNewDataType(nameText.getText(),typeNameText.getText());
//				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
//					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewEnumeration(nameText.getText(),typeNameText.getText());
			 }
		});	
		
		lblSource = new Label(this, SWT.NONE);
		lblSource.setText(""+uf.getSource().getName());
		
		targetTableCreator = new AttrTable(this, SWT.BORDER, ((RefOntoUML.Class)uf.getTarget()).getOwnedAttribute());
		Table targetTable = targetTableCreator.getTable();
//		Table targetTable = new Table(this, SWT.BORDER);
				
		
		lblTarget = new Label(this, SWT.NONE);
		lblTarget.setText(""+uf.getTarget().getName());
		
		btnTargetCreate = new Button(this, SWT.NONE);
		btnTargetCreate.setText("Create");		
		btnTargetCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getSelectionIndex()==0)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTableCreator.addNewPrimitiveType(nameText.getText(),typeNameText.getText());  
				 if (typeList.getSelectionIndex()==1)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTableCreator.addNewDataType(nameText.getText(),typeNameText.getText());
//				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
//					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewEnumeration(nameText.getText(),typeNameText.getText());				 
			 }
		});	

		oclTextComp = new StyledText(this,  SWT.V_SCROLL | SWT.BORDER);
		oclTextComp.setText(sourceTemplate);
		
		typeList = new Combo(this, SWT.READ_ONLY);
		typeList.setItems(new String[] {"Primitive type", "Data type", "Enumeration"});
		typeList.select(0);
		
		nameText = new Text(this, SWT.BORDER);
		
		typeNameText = new Text(this, SWT.BORDER);
		
		lblAttributeType = new Label(this, SWT.NONE);
		lblAttributeType.setText("Attribute Type:");
		
		lblAttributeName = new Label(this, SWT.NONE);
		lblAttributeName.setText("Attribute Name:");
		
		lblAttributeTypeName = new Label(this, SWT.NONE);
		lblAttributeTypeName.setText("Attribute Type Name:");
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		btnSourceDelete = new Button(this, SWT.NONE);
		btnSourceDelete.setText("Delete");
		btnSourceDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 sourceTableCreator.removeLine();				 
			 }
		});	
		
		btnTargetDelete = new Button(this, SWT.NONE);
		btnTargetDelete.setText("Delete");
		btnTargetDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				targetTableCreator.removeLine();				 
			 }
		});		
		
		btnTargetTemplate = new Button(this, SWT.NONE);
		btnTargetTemplate.setText("Target Template");
		btnTargetTemplate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				oclTextComp.setText(targetTemplate);
			}
		});
		
		btnSourceTemplate = new Button(this, SWT.NONE);
		btnSourceTemplate.setText("Source Template");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(lblAttributeType, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.add(3)
					.add(lblAttributeName, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblAttributeTypeName, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addContainerGap())
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(typeList, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(nameText, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
					.add(6)
					.add(typeNameText, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
					.add(10))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(label, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
					.add(10))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(oclTextComp, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
					.add(10))
				.add(groupLayout.createSequentialGroup()
					.add(335)
					.add(btnSourceTemplate)
					.add(6)
					.add(btnTargetTemplate, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.add(10))
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.addContainerGap()
							.add(lblSource, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnSourceDelete)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnSourceCreate, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(sourceTable, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(groupLayout.createSequentialGroup()
							.add(13)
							.add(targetTable, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
						.add(groupLayout.createSequentialGroup()
							.add(11)
							.add(lblTarget, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnTargetDelete)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnTargetCreate, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
					.add(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
						.add(lblAttributeType)
						.add(lblAttributeName)
						.add(lblAttributeTypeName))
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(typeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(nameText, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.add(typeNameText, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.add(17)
					.add(label, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
							.add(btnTargetCreate)
							.add(btnTargetDelete))
						.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
							.add(lblSource)
							.add(btnSourceDelete)
							.add(btnSourceCreate)
							.add(lblTarget)))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(sourceTable, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.add(targetTable, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
					.add(6)
					.add(oclTextComp, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.add(9)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(btnSourceTemplate)
						.add(btnTargetTemplate))
					.add(10))
		);
		setLayout(groupLayout);
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
		sourceTableCreator.getTable().setEnabled(value);
		targetTableCreator.getTable().setEnabled(value);
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
		return targetTableCreator.getValues();
	}
	
	public HashMap<String,String> getTargetMapStereo()
	{
		return targetTableCreator.getStereotypes();
	}
	
	public HashMap<String,String> getSourceMapType()
	{
		return sourceTableCreator.getValues();
	}
	
	public HashMap<String,String> getSourceMapStereo()
	{
		return sourceTableCreator.getStereotypes();
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
