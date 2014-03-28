package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;


public abstract class DecIntPage extends WizardPage {

	protected DecIntOccurrence decint;	
	
	/**
	 * Create the wizard.
	 */
	public DecIntPage(DecIntOccurrence decint) 
	{
		super("DecIntPage");		
		this.decint = decint;		
		setTitle(DecIntAntipattern.getAntipatternInfo().getName());		
	}
	
	public DecIntWizard getDecIntWizard(){
		return (DecIntWizard)getWizard();
	}
}
