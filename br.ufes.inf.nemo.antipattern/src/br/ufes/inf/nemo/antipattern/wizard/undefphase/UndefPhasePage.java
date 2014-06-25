package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class UndefPhasePage extends AntipatternWizardPage<UndefPhaseOccurrence, UndefPhaseWizard> {
	
	/**
	 * Create the wizard.
	 */
	public UndefPhasePage(UndefPhaseOccurrence up) 
	{
		super(up);			
		setTitle(UndefPhaseAntipattern.getAntipatternInfo().getName());
	}
}