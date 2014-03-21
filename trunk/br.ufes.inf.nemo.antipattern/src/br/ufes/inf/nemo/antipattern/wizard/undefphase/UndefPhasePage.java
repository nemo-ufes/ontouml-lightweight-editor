package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public abstract class UndefPhasePage extends WizardPage {

	protected UndefPhaseOccurrence up;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhasePage(UndefPhaseOccurrence up) 
	{
		super("UndefPhasePage");				
		this.up = up;
				
		setTitle(UndefPhaseAntipattern.getAntipatternInfo().getName());
	}
	
	public UndefPhaseWizard getUndefPhaseWizard(){
		return (UndefPhaseWizard)getWizard();
	}
}