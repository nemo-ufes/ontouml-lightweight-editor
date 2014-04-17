package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;


public abstract class AssCycPage extends AntipatternWizardPage<AssCycOccurrence, AssCycWizard> {

	protected AssCycOccurrence occurrence;	
	
	/**
	 * Create the wizard.
	 */
	public AssCycPage(AssCycOccurrence asscyc) 
	{
		super(asscyc);		
		this.occurrence = asscyc;		
		setTitle(AssCycAntipattern.getAntipatternInfo().getName());		
	}

}
