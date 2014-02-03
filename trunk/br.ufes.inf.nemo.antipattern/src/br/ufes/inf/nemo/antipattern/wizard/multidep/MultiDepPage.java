package br.ufes.inf.nemo.antipattern.wizard.multidep;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;

public abstract class MultiDepPage extends WizardPage {

	protected MultiDepOccurrence multiDep;	
	
	/**
	 * Create the wizard.
	 */
	public MultiDepPage(MultiDepOccurrence multiDep) 
	{
		super("MultiDepPage");		
		this.multiDep = multiDep;		
		setTitle(MultiDepAntipattern.getAntipatternInfo().getName());		
	}
	
	public MultiDepWizard getMultiDepWizard(){
		return (MultiDepWizard)getWizard();
	}
}
