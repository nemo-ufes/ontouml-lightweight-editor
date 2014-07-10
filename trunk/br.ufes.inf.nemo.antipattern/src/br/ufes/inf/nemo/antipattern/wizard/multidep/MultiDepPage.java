package br.ufes.inf.nemo.antipattern.wizard.multidep;

import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class MultiDepPage extends AntipatternWizardPage<MultiDepOccurrence, MultiDepWizard> {
	
	/**
	 * Create the wizard.
	 */
	public MultiDepPage(MultiDepOccurrence multiDep) 
	{
		super(multiDep);		
		setTitle(MultiDepAntipattern.getAntipatternInfo().getName());		
	}
	
}
