package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.HashMap;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

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
	private Button deleteButton;	
	public Button createButton;	
	private WizardPage currentPage;
	
	public UndefPhaseOccurrence up;
	private Combo multCombo;
	private Label lblCardinality;
	private Label lblStereotype;
	private Table table;
		
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateDataTypeComposite(Composite parent, int style, UndefPhaseOccurrence up, WizardPage currentPage) 
	{
		super(parent, style);
		this.up=up;
		this.currentPage = currentPage;
		
		createButton = new Button(this, SWT.NONE);
		createButton.setText("Create");
		createButton.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getSelectionIndex()==0)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty())
						 sourceTable.addNewPrimitiveType(nameText.getText(),typeNameText.getText(),multCombo.getText());
				 if (typeList.getSelectionIndex()==1)
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) 
						 sourceTable.addNewDataType(nameText.getText(),typeNameText.getText(),multCombo.getText());
				 
				 if( CreateDataTypeComposite.this.currentPage!=null){
					 if(getValues().keySet().size()>=1)
						 CreateDataTypeComposite.this.currentPage.setPageComplete(true);
					 else
						 CreateDataTypeComposite.this.currentPage.setPageComplete(false);
				 }
					 
			 }
		});	
		
		lblSource = new Label(this, SWT.NONE);
		lblSource.setText("Atributes of "+up.getGeneral().getName()+":");
		
		typeList = new Combo(this, SWT.READ_ONLY);
		typeList.setItems(new String[] {"Primitive type", "Data type"});
		typeList.select(0);
		
		nameText = new Text(this, SWT.BORDER);
		
		typeNameText = new Text(this, SWT.BORDER);
		
		lblAttributeName = new Label(this, SWT.NONE);
		lblAttributeName.setText("Name:");
		
		lblAttributeTypeName = new Label(this, SWT.NONE);
		lblAttributeTypeName.setText("Type:");
		
		label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		deleteButton = new Button(this, SWT.NONE);
		deleteButton.setText("Delete");
		
		multCombo = new Combo(this, SWT.NONE);
		multCombo.setItems(new String[] {"1", "0..1", "1..*", "0..*"});
		multCombo.select(0);
		
		lblCardinality = new Label(this, SWT.NONE);
		lblCardinality.setText("Cardinality:");
		
		lblStereotype = new Label(this, SWT.NONE);
		lblStereotype.setText("Stereotype:");
		deleteButton.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 sourceTable.removeLine();
				 
				 if( CreateDataTypeComposite.this.currentPage!=null){
					 if(getValues().keySet().size()>=1)
						 CreateDataTypeComposite.this.currentPage.setPageComplete(true);
					 else
						 CreateDataTypeComposite.this.currentPage.setPageComplete(false);
				 }
			 }
		});		
		
		sourceTable = new AttrTable(this, SWT.BORDER | SWT.V_SCROLL , ((RefOntoUML.Class)up.getGeneral()).getOwnedAttribute());
		
//		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = sourceTable.getTable();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(label, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
							.add(lblSource, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
							.add(6)
							.add(createButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(deleteButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.add(1))
						.add(groupLayout.createSequentialGroup()
							.add(table, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
							.add(1))
						.add(groupLayout.createSequentialGroup()
							.add(groupLayout.createParallelGroup(GroupLayout.TRAILING, false)
								.add(GroupLayout.LEADING, lblAttributeTypeName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.add(GroupLayout.LEADING, lblAttributeName, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(groupLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.UNRELATED)
									.add(nameText, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
									.add(18)
									.add(lblCardinality, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.add(6)
									.add(multCombo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.add(groupLayout.createSequentialGroup()
									.add(11)
									.add(typeNameText, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
									.add(18)
									.add(lblStereotype, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.add(6)
									.add(typeList, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
							.add(1)))
					.add(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(7)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(nameText, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.add(groupLayout.createSequentialGroup()
									.add(3)
									.add(lblCardinality, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.add(multCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(lblAttributeName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.addPreferredGap(LayoutStyle.RELATED)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(typeNameText, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.add(groupLayout.createSequentialGroup()
									.add(3)
									.add(lblStereotype))
								.add(typeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.add(groupLayout.createSequentialGroup()
							.add(11)
							.add(lblAttributeTypeName)))
					.add(6)
					.add(label, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.add(2)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(5)
							.add(lblSource))
						.add(createButton)
						.add(deleteButton))
					.add(6)
					.add(table, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.add(10))
		);
		setLayout(groupLayout);
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
		deleteButton.setEnabled(value);		
		createButton.setEnabled(value);
	}
	
	public HashMap<String,String> getValues()
	{
		return sourceTable.getValues();
	}
	
	public HashMap<String,String> getStereotypes()
	{
		return sourceTable.getStereotypes();
	}
	
	public HashMap<String,String> getCardinalities()
	{
		return sourceTable.getCardinalities();
	}
}
