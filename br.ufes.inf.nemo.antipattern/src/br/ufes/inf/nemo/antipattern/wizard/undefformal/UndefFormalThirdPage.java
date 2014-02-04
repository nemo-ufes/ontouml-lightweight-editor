package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import org.eclipse.swt.widgets.Text;

public class UndefFormalThirdPage extends UndefFormalPage{

	public Composite parent;
	
	public Label lblTarget;
	public Button btnTargetCreate;
	private UndefFormalAttrTable targetTable;
	public Label lblSource;
	public Button btnSourceCreate;
	public UndefFormalAttrTable sourceTable;
	
	private Combo typeList;	
	private Text nameText;
	private Text typeNameText;
	
	public StyledText oclTextComp;
	private Button btnSourceDelete;
	private Button btnTargetDelete;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalThirdPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		sc.setAlwaysShowScrollBars(true);
		sc.setBounds(10, 60, 554, 212);
		
		Composite container = new Composite(sc, SWT.NONE);		
		setControl(container);

//		Composite composite2 = new Composite(container, SWT.NONE);	
//		composite2.setBounds(10, 112, 260, 87);
		
		sourceTable = new UndefFormalAttrTable(container, SWT.BORDER, ((RefOntoUML.Class)uf.getSource()).getOwnedAttribute());
		sourceTable.getTable().setBounds(10, 112, 260, 87);
		
		btnSourceCreate = new Button(container, SWT.NONE);
		btnSourceCreate.setBounds(225, 81, 45, 25);
		btnSourceCreate.setText("Create");
		btnSourceCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Primitive type"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewPrimitiveType(nameText.getText(),typeNameText.getText());
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("DataType type"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewDataType(nameText.getText(),typeNameText.getText());
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) sourceTable.addNewEnumeration(nameText.getText(),typeNameText.getText());
			 }
		});	
		
		lblSource = new Label(container, SWT.NONE);
		lblSource.setBounds(10, 91, 158, 15);
		lblSource.setText(""+uf.getSource().getName());
		
		targetTable = new UndefFormalAttrTable(container, SWT.BORDER, ((RefOntoUML.Class)uf.getTarget()).getOwnedAttribute());
		targetTable.getTable().setBounds(283, 112, 260, 87);
				
//		Composite composite = new Composite(container, SWT.NONE);	
//		composite.setBounds(283, 112, 260, 87);
		
		lblTarget = new Label(container, SWT.NONE);
		lblTarget.setBounds(283, 91, 158, 15);
		lblTarget.setText(""+uf.getTarget().getName());
		
		btnTargetCreate = new Button(container, SWT.NONE);
		btnTargetCreate.setBounds(498, 81, 45, 25);
		btnTargetCreate.setText("Create");		
		btnTargetCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Primitive type"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewPrimitiveType(nameText.getText(),typeNameText.getText());  
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("DataType type"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewDataType(nameText.getText(),typeNameText.getText());
				 if (typeList.getItem(typeList.getSelectionIndex()).equals("Enumeration"))
					 if (!nameText.getText().isEmpty() && !typeNameText.getText().isEmpty()) targetTable.addNewEnumeration(nameText.getText(),typeNameText.getText());				 
			 }
		});	

		oclTextComp = new StyledText(container,  SWT.V_SCROLL | SWT.BORDER);
		oclTextComp.setText("\r\ncontext <Source>::<target> : <Target>\r\nderive : Target.allInstances()->select( t : Target | <CONDITION>)");
		oclTextComp.setBounds(10, 205, 533, 66);
		
		typeList = new Combo(container, SWT.READ_ONLY);
		typeList.setItems(new String[] {"Primitive type", "Data type", "Enumeration"});
		typeList.setBounds(10, 31, 195, 23);
		typeList.select(0);
		
		nameText = new Text(container, SWT.BORDER);
		nameText.setBounds(211, 31, 150, 23);
		
		typeNameText = new Text(container, SWT.BORDER);
		typeNameText.setBounds(367, 31, 176, 23);
		
		Label lblAttributeType = new Label(container, SWT.NONE);
		lblAttributeType.setBounds(10, 10, 195, 15);
		lblAttributeType.setText("Attribute Type:");
		
		Label lblAttributeName = new Label(container, SWT.NONE);
		lblAttributeName.setBounds(211, 10, 150, 15);
		lblAttributeName.setText("Attribute Name:");
		
		Label lblAttributeTypeName = new Label(container, SWT.NONE);
		lblAttributeTypeName.setBounds(367, 10, 176, 15);
		lblAttributeTypeName.setText("Attribute Type Name:");
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 71, 533, 4);
		
		btnSourceDelete = new Button(container, SWT.NONE);
		btnSourceDelete.setBounds(174, 81, 45, 25);
		btnSourceDelete.setText("Delete");
		btnSourceDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 sourceTable.removeLine();				 
			 }
		});	
		
		btnTargetDelete = new Button(container, SWT.NONE);
		btnTargetDelete.setBounds(447, 81, 45, 25);
		btnTargetDelete.setText("Delete");
		btnTargetDelete.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				targetTable.removeLine();				 
			 }
		});	
				
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setContent(container);
		sc.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		return ((UndefFormalWizard)getWizard()).getFinishing();	
	}
}
