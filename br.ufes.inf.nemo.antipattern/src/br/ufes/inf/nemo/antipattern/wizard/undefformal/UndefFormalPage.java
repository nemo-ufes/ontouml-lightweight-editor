package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class UndefFormalPage extends AntipatternWizardPage<UndefFormalOccurrence, UndefFormalWizard> {
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalPage(UndefFormalOccurrence uf) 
	{
		super(uf);					
		setTitle(UndefFormalAntipattern.getAntipatternInfo().getName());
	}

}