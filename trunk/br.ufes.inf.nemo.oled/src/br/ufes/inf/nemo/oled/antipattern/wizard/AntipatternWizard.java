package br.ufes.inf.nemo.oled.antipattern.wizard;

import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public abstract class AntipatternWizard extends Wizard {

	public boolean canFinish = true;
	
	protected AntipatternOccurrence ap;
	
	protected PresentationPage presentation;
	protected FinishingPage finishing;
	protected RefactoringPage options;
	
	
	public AntipatternWizard(AntipatternOccurrence ap, String windowTitle) {
		this.ap = ap;
	    canFinish=false;
	    setNeedsProgressMonitor(true); 
		setWindowTitle(windowTitle);
	}
	
    @Override
    public boolean canFinish() {	 
    	return canFinish;	  
    };
    
	public AntipatternOccurrence getAp() {
		return ap;
	}

	public PresentationPage getPresentation() {
		return presentation;
	}

	public FinishingPage getFinishing() {
		canFinish=true;
		return finishing;
	}

	public RefactoringPage getOptions() {
		return options;
	}

}
