package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncPage extends WizardPage {

	protected HomoFuncOccurrence homoFunc;	
	
	/**
	 * Create the wizard.
	 */
	public HomoFuncPage(HomoFuncOccurrence homoFunc) 
	{
		super("HomoFuncPage");		
		this.homoFunc = homoFunc;		
		setTitle(HomoFuncAntipattern.getAntipatternInfo().getName());		
	}
	
	public HomoFuncWizard getHomoFuncWizard(){
		return (HomoFuncWizard)getWizard();
	}

	@Override
	public void createControl(Composite arg0) {
		
	}
}
