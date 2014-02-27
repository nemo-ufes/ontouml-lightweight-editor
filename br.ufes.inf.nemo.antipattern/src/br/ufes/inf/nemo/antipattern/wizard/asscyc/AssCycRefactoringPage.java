package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class AssCycRefactoringPage extends RefactoringPage {
	
	public AssCycOccurrence asscyc;
		
	/**
	 * Create the wizard.
	 */
	public AssCycRefactoringPage(AssCycOccurrence asscyc) 
	{
		super();	
		this.asscyc = asscyc;
		
		setTitle(AssCycAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public AssCycWizard getAssCycWizard(){
		return (AssCycWizard)getWizard();
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
		((AssCycWizard)getWizard()).removeAllActions();
		
		return ((AssCycWizard)getWizard()).getFinishing();
	}
}
