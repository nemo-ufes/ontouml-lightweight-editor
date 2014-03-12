package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleSecondPage extends FreeRolePage {
	
	private int index = -1;
	private Label lblEveryDerivedType;
	private StyledText styledText;
	
	public FreeRoleSecondPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription("Role "+freeRole.getFreeRoles().get(index).getName());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);
		
		lblEveryDerivedType = new Label(container, SWT.WRAP);
		lblEveryDerivedType.setBounds(10, 10, 554, 40);
		lblEveryDerivedType.setText("Every derived type must be have a derivation rule that define its instances. \r\nPlease complete the OCL template provided below: ");
		
		styledText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL);
		styledText.setText("context <FreeRole> :: allInstances() : Set(<FreeRole>)                        \r\nbody : <DefinedRole>.allInstances()->select ( x | <CONDITION>) ");
		styledText.setBounds(10, 56, 554, 92);
	}
	   
	@Override
	public IWizardPage getNextPage() 
	{			
		//Action =============================
		FreeRoleAction newAction = new FreeRoleAction(freeRole);
		newAction.setCreateOCLDerivation(freeRole.getFreeRoles().get(index),styledText.getText()); 
		geFreeRoleWizard().replaceAction(0,newAction);	
		//======================================
		
		if(index<freeRole.getFreeRoles().size()-1){
			return ((FreeRoleWizard)getWizard()).getFirstPage(index+1);
		}else{
			return ((FreeRoleWizard)getWizard()).getFinishing();
		}
	}
}
