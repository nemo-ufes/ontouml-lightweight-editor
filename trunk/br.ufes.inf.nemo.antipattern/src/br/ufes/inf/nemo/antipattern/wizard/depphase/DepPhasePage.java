package br.ufes.inf.nemo.antipattern.wizard.depphase;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;

public abstract class DepPhasePage extends WizardPage {

	protected DepPhaseOccurrence depPhase;	
	
	/**
	 * Create the wizard.
	 */
	public DepPhasePage(DepPhaseOccurrence depPhase) 
	{
		super("DepPhasePage");		
		this.depPhase = depPhase;		
		setTitle(DepPhaseAntipattern.getAntipatternInfo().getName());		
	}
	
	public DepPhaseWizard getMultiDepWizard(){
		return (DepPhaseWizard)getWizard();
	}
}
