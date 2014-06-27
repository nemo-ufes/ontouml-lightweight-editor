package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public abstract class HetCollPage extends AntipatternWizardPage<HetCollOccurrence, HetCollWizard> {
	
	/**
	 * Create the wizard.
	 */
	public HetCollPage(HetCollOccurrence hetColl) 
	{
		super(hetColl);		
		setTitle(HetCollAntipattern.getAntipatternInfo().getName());		
	}

}
