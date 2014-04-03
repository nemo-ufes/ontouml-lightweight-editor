package br.ufes.inf.nemo.antipattern.wizard.freerole;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;


public abstract class FreeRolePage extends AntipatternWizardPage<FreeRoleOccurrence, FreeRoleWizard> {
	
	protected int index = -1;

	/**
	 * Create the wizard.
	 */
	public FreeRolePage(FreeRoleOccurrence freeRole) 
	{
		super(freeRole);			
		setTitle(FreeRoleAntipattern.getAntipatternInfo().getName());		
	}
}
