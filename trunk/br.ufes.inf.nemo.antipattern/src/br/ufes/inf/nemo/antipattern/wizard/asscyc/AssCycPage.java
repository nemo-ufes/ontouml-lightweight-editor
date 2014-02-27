package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;


public class AssCycPage extends WizardPage {

	protected AssCycOccurrence asscyc;	
	
	/**
	 * Create the wizard.
	 */
	public AssCycPage(AssCycOccurrence asscyc) 
	{
		super("AssCycPage");		
		this.asscyc = asscyc;		
		setTitle(AssCycAntipattern.getAntipatternInfo().getName());		
	}
	
	public AssCycWizard getAssCycWizard(){
		return (AssCycWizard)getWizard();
	}

	@Override
	public void createControl(Composite arg0) {
		
	}
}
