package br.ufes.inf.nemo.oled.antipattern.wizard.test;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ThirdPage extends WizardPage {

	private Composite container;
	
	/**
	 * Create the wizard.
	 */
	public ThirdPage() {
		super("wizardPage");
		setTitle("Wizard Page title");
		setDescription("Wizard Page description");		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);				
		setControl(container);		
		setPageComplete(false);
	}

	
	@Override
	public IWizardPage getNextPage() {
	  	return ((MyWizard)getWizard()).one;	  	
	}
}
