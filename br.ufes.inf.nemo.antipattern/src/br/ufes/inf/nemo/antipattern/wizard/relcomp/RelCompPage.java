package br.ufes.inf.nemo.antipattern.wizard.relcomp;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class RelCompPage extends WizardPage {

	protected RelCompOccurrence relComp;
	
	/**
	 * Create the wizard.
	 */
	public RelCompPage(RelCompOccurrence relComp) 
	{
		super("RelCompPage");				
		this.relComp = relComp;
				
		setTitle(RelCompAntipattern.getAntipatternInfo().getName());
	}
	
	public RelCompWizard getRelCompWizard(){
		return (RelCompWizard)getWizard();
	}
	
	public RelCompOccurrence getRelComp(){
		return relComp;
	}

}
