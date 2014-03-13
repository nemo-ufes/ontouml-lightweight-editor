package br.ufes.inf.nemo.antipattern.wizard.depphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class DepPhaseRefactoringPage extends RefactoringPage {
	
	public DepPhaseOccurrence depPhase;
	
	/**
	 * Create the wizard.
	 */
	public DepPhaseRefactoringPage(DepPhaseOccurrence depPhase) 
	{
		super();	
		this.depPhase = depPhase;
		
		setTitle(DepPhaseAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public DepPhaseWizard getMultiDepWizard(){
		return (DepPhaseWizard)getWizard();
	}
	
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		

	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		return null;
	}
}
