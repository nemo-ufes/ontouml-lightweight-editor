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

public class UndefFormalThirdPage extends UndefFormalPage{

	public Composite parent;
	
	public Label lblTarget;
	public Button btnTargetCreate;
	private UndefFormalAttrTable targetTable;
	private Combo sourceList;
	
	public Label lblSource;
	public Button btnSourceCreate;
	public UndefFormalAttrTable sourceTable;
	private Combo targetList;
	
	public StyledText oclTextComp;
	
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
//		composite2.setBounds(10, 45, 260, 87);
		
		sourceTable = new UndefFormalAttrTable(container, SWT.BORDER, ((RefOntoUML.Class)uf.getSource()).getOwnedAttribute());
		sourceTable.getTable().setBounds(10, 45, 260, 87);
		
		btnSourceCreate = new Button(container, SWT.NONE);
		btnSourceCreate.setBounds(211, 138, 59, 25);
		btnSourceCreate.setText("Create");
		btnSourceCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (sourceList.getItem(sourceList.getSelectionIndex()).equals("Primitive type attribute"))
					 sourceTable.addNewPrimitiveType("","");
				 if (sourceList.getItem(sourceList.getSelectionIndex()).equals("DataType type attribute"))
					 sourceTable.addNewDataType("","");
				 if (sourceList.getItem(sourceList.getSelectionIndex()).equals("Enumeration attribute"))
					 sourceTable.addNewEnumeration("","");
			 }
		});	
		
		lblSource = new Label(container, SWT.NONE);
		lblSource.setBounds(10, 24, 260, 15);
		lblSource.setText("(attributes) "+uf.getSource().getName());
		
		targetTable = new UndefFormalAttrTable(container, SWT.BORDER, ((RefOntoUML.Class)uf.getTarget()).getOwnedAttribute());
		targetTable.getTable().setBounds(283, 45, 260, 87);
				
//		Composite composite = new Composite(container, SWT.NONE);	
//		composite.setBounds(283, 45, 260, 87);
		
		lblTarget = new Label(container, SWT.NONE);
		lblTarget.setBounds(283, 24, 260, 15);
		lblTarget.setText("(attributes) "+uf.getTarget().getName());
		
		btnTargetCreate = new Button(container, SWT.NONE);
		btnTargetCreate.setBounds(484, 138, 59, 25);
		btnTargetCreate.setText("Create");		
		btnTargetCreate.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 if (targetList.getItem(targetList.getSelectionIndex()).equals("Primitive type attribute"))
					 targetTable.addNewPrimitiveType("","");
				 if (targetList.getItem(targetList.getSelectionIndex()).equals("DataType type attribute"))
					 targetTable.addNewDataType("","");
				 if (targetList.getItem(targetList.getSelectionIndex()).equals("Enumeration attribute"))
					 targetTable.addNewEnumeration("","");				 
			 }
		});	

		oclTextComp = new StyledText(container,  SWT.V_SCROLL | SWT.BORDER);
		oclTextComp.setText("\r\ncontext <Source>::<target> : <Target>\r\nderive : Target.allInstances()->select( t : Target | <CONDITION>)");
		oclTextComp.setBounds(10, 184, 533, 66);
		
		Label lblOclTemplate = new Label(container, SWT.NONE);
		lblOclTemplate.setBounds(10, 256, 533, 15);
		lblOclTemplate.setText(" OCL Template");
		
		sourceList = new Combo(container, SWT.READ_ONLY);
		sourceList.setItems(new String[] {"Primitive type attribute", "Data type attribute", "Enumeration attribute"});
		sourceList.setBounds(10, 138, 195, 23);
		sourceList.select(0);
		
		targetList = new Combo(container, SWT.READ_ONLY);
		targetList.setBounds(283, 140, 195, 23);
		targetList.setItems(new String[] {"Primitive type attribute", "Data type attribute", "Enumeration attribute"});
		targetList.select(0);
		
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setContent(container);
		sc.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		return super.getNextPage();	
	}
}
