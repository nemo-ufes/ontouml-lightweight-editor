package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class UndefFormalRefactoringPage extends RefactoringPage {
	
	protected UndefFormalOccurrence uf;
	/**
	 * Create the wizard.
	 */
	public UndefFormalRefactoringPage(UndefFormalOccurrence uf) 
	{
		super();	
		this.uf = uf;
				
		setTitle(RepRelAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public UndefFormalWizard getUndefFormalWizard(){
		return ( UndefFormalWizard)getWizard();
	}	
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		return super.getNextPage();
	}
}
