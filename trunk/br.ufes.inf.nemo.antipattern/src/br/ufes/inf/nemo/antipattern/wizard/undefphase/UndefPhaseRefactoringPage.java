package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class UndefPhaseRefactoringPage extends RefactoringPage {
	
	protected UndefPhaseOccurrence up;
	/**
	 * Create the wizard.
	 */
	public UndefPhaseRefactoringPage(UndefPhaseOccurrence up) 
	{
		super();	
		this.up = up;
				
		setTitle(UndefPhaseAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
//		setDescription("The follwing options can be used to refactor the Formal relationship: "+up.getSource().getName()+" -> "+uf.getTarget().getName());
	}

	public UndefPhaseWizard getUndefFormalWizard(){
		return ( UndefPhaseWizard)getWizard();
	}	
	
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
				
		setControl(container);
	}
}
		
