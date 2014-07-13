package br.ufes.inf.nemo.antipattern.wizard.relspec;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class RelSpecPage extends AntipatternWizardPage<RelSpecOccurrence, RelSpecWizard> {
	/**
	 * Create the wizard.
	 */
	public RelSpecPage(RelSpecOccurrence rs) 
	{
		super(rs);				
				
		setTitle(RelSpecAntipattern.getAntipatternInfo().getName());
	
	}

}
