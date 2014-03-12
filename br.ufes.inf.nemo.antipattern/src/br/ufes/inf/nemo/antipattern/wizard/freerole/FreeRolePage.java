package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRolePage extends WizardPage {

	protected FreeRoleOccurrence freeRole;	
	
	/**
	 * Create the wizard.
	 */
	public FreeRolePage(FreeRoleOccurrence freeRole) 
	{
		super("FreeRolePage");		
		this.freeRole = freeRole;		
		setTitle(FreeRoleAntipattern.getAntipatternInfo().getName());		
	}
	
	public FreeRoleWizard geFreeRoleWizard(){
		return (FreeRoleWizard)getWizard();
	}

	@Override
	public void createControl(Composite arg0) {
		
	}
}
