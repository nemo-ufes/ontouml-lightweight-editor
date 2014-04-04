package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;

public class HetCollPage extends WizardPage {

	protected HetCollOccurrence hetColl;	
	
	/**
	 * Create the wizard.
	 */
	public HetCollPage(HetCollOccurrence hetColl) 
	{
		super("HetCollPage");		
		this.hetColl = hetColl;		
		setTitle(HetCollAntipattern.getAntipatternInfo().getName());		
	}
	
	public AntipatternWizard getHetCollWizard(){
		return (AntipatternWizard)getWizard();
	}

	@Override
	public void createControl(Composite arg0) {
		
	}
}
