package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigPage extends WizardPage {

	protected GSRigOccurrence gsrig;	
	
	/**
	 * Create the wizard.
	 */
	public GSRigPage(GSRigOccurrence gsrig) 
	{
		super("GSRigPage");		
		this.gsrig = gsrig;		
		setTitle(GSRigAntipattern.getAntipatternInfo().getName());		
	}
	
	public GSRigWizard getGSRigWizard(){
		return (GSRigWizard)getWizard();
	}

	@Override
	public void createControl(Composite arg0) {
		
	}
	
	@Override
	public IWizardPage getPreviousPage(){
		getGSRigWizard().setCanFinish(false);
		return super.getPreviousPage();
	}
}
