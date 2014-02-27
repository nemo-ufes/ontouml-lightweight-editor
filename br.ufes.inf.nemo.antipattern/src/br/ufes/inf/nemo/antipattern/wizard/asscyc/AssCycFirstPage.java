package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;

public class AssCycFirstPage  extends AssCycPage {
		
	public AssCycFirstPage(AssCycOccurrence asscyc) 
	{
		super(asscyc);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);			
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		return super.getNextPage();
	}
	
	
}

