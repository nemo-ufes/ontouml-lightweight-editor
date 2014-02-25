package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class HomoFuncRefactoringPage extends RefactoringPage {
	
	public HomoFuncOccurrence homoFunc;
		
	/**
	 * Create the wizard.
	 */
	public HomoFuncRefactoringPage(HomoFuncOccurrence homoFunc) 
	{
		super();	
		this.homoFunc = homoFunc;
		
		setTitle( HomoFuncAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public HomoFuncWizard getHomoFuncWizard(){
		return (HomoFuncWizard)getWizard();
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
	}

	@Override
	public IWizardPage getNextPage() 
	{
		((HomoFuncWizard)getWizard()).removeAllActions();
		
		return super.getNextPage();
	}
}