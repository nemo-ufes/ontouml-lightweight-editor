package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public abstract class UndefFormalPage extends WizardPage {

	protected UndefFormalOccurrence uf;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalPage(UndefFormalOccurrence uf) 
	{
		super("UndefFormalPage");				
		this.uf = uf;
				
		setTitle(UndefFormalAntipattern.getAntipatternInfo().getName());
	}
	
	public UndefFormalWizard getUndefFormalWizard(){
		return (UndefFormalWizard)getWizard();
	}

}