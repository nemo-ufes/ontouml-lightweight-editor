package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class DecIntRefactoringPage extends RefactoringPage {
	
	public DecIntOccurrence decint;

		
	/**
	 * Create the wizard.
	 */
	public DecIntRefactoringPage(DecIntOccurrence decint) 
	{
		super();	
		this.decint = decint;
		
		setTitle(DecIntAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public DecIntWizard getDecIntWizard(){
		return (DecIntWizard)getWizard();
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
		
		
		return ((DecIntWizard)getWizard()).getFinishing();
	}
}
