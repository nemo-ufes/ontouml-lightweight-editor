package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class RelSpecPage extends WizardPage {

	protected RelSpecOccurrence relSpec;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecPage(RelSpecOccurrence rs) 
	{
		super("RelRigFirstPage");				
		this.relSpec = rs;
				
		setTitle(RelSpecAntipattern.getAntipatternInfo().getName());
	
	}
	
	public RelSpecWizard getRelSpecWizard(){
		return (RelSpecWizard)getWizard();
	}

}
