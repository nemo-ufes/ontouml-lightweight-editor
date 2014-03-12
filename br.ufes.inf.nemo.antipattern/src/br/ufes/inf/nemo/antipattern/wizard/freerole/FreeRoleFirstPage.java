package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFirstPage extends FreeRolePage {
	
	private Label lblDoesAnInstance;
	private int index=-1;
	private Button btnYes;
	private Button btnNo;
	
	public FreeRoleFirstPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription("Role "+freeRole.getFreeRoles().get(index).getName());
	}
	
	public boolean contains(List list, String elem){
		for(String str: list.getItems()){
			if (str.equals(elem)) return true;
		}
		return false;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);			
		
		lblDoesAnInstance = new Label(container, SWT.WRAP);
		lblDoesAnInstance.setBounds(10, 10, 554, 52);
		lblDoesAnInstance.setText("Does an instance of "+freeRole.getDefinedRole().getName()+" becomes a "+freeRole.getFreeRoles().get(index).getName()+" when a pre-determined set " +
		"of conditions is valid? For example, a Student is a Freshman when he/she is in the first semester of his Enrollment (the relator which characterizes student), " +
		"whilst a senior, is one that is on his last.");		
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 69, 554, 16);
		btnYes.setText("Yes ("+freeRole.getFreeRoles().get(index).getName()+"  is a derived type)");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 94, 554, 16);
		btnNo.setText("No ("+freeRole.getFreeRoles().get(index).getName()+"  is a intentional type)\r\n");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnYes.getSelection()) {			
			FreeRoleSecondPage secondPage = ((FreeRoleWizard)getWizard()).getSecondPage(index);			
			return secondPage;
		}
		if(btnNo.getSelection()){
			FreeRoleThirdPage thirdPage = ((FreeRoleWizard)getWizard()).getThirdPage(index);			
			return thirdPage;
		}
		
		return super.getNextPage();
	}
}