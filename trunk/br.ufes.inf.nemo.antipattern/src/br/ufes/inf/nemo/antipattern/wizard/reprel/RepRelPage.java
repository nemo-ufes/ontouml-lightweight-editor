package br.ufes.inf.nemo.antipattern.wizard.reprel;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;

public abstract class RepRelPage extends WizardPage {

	protected RepRelOccurrence repRel;
	
	/**
	 * Create the wizard.
	 */
	public RepRelPage(RepRelOccurrence rr) 
	{
		super("RepRelPage");				
		this.repRel = rr;
				
		setTitle(RepRelAntipattern.getAntipatternInfo().getName());
	}
	
	public RepRelWizard getRepRelWizard(){
		return (RepRelWizard)getWizard();
	}

}
