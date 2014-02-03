package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class UndefFormalThirdPage extends UndefFormalPage{

	public Composite parent;
	public UndefFormalAttrTable sourceTable;
	private UndefFormalAttrTable targetTable;
	public List dataTypeList;
	public Label lblSourceAttr;
	public Label lblTargetAttr;
	public Button btnSourceNew;
	public Button btnTargetNew;
	
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
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		sourceTable = new UndefFormalAttrTable(container, SWT.BORDER, ((RefOntoUML.Class)uf.getSource()).getOwnedAttribute());
		sourceTable.getTable().setBounds(286, 45, 278, 87);
				
		btnSourceNew = new Button(container, SWT.NONE);
		btnSourceNew.setBounds(469, 138, 95, 25);
		btnSourceNew.setText("New attribute");
		
		Label lblDatatypes = new Label(container, SWT.NONE);
		lblDatatypes.setBounds(71, 252, 493, 15);
		lblDatatypes.setText("Create new data types in your model...");
		
		dataTypeList = new List(container, SWT.BORDER);
		dataTypeList.setBounds(10, 169, 554, 74);
		
		Button btnCreate = new Button(container, SWT.NONE);
		btnCreate.setBounds(10, 247, 55, 25);
		btnCreate.setText("Create");
		
		targetTable = new UndefFormalAttrTable(container, SWT.BORDER, ((RefOntoUML.Class)uf.getTarget()).getOwnedAttribute());
		targetTable.getTable().setBounds(10, 45, 270, 87);
				
		lblSourceAttr = new Label(container, SWT.NONE);
		lblSourceAttr.setBounds(10, 24, 270, 15);
		lblSourceAttr.setText("Attributes: "+uf.getSource().getName());
		
		lblTargetAttr = new Label(container, SWT.NONE);
		lblTargetAttr.setBounds(287, 24, 277, 15);
		lblTargetAttr.setText("Attributes:"+uf.getTarget().getName());
		
		btnTargetNew = new Button(container, SWT.NONE);
		btnTargetNew.setBounds(185, 138, 95, 25);
		btnTargetNew.setText("New attribute");
	}
}
