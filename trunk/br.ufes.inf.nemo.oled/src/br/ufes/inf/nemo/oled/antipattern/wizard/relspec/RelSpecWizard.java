package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.oled.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecWizard extends Wizard {

	public boolean canFinish = true;
	
	protected RelSpecOccurrence ap;
	
	protected PresentationPage presentation;
	protected FinishingPage finishing;
		  
	protected RelSpecRefactoringPage options;
	
	protected RelSpecFirstPage firstPage;
	protected RelSpecSecondPage secondPage;
	protected RelSpecThirdPage thirdPage;
	protected RelSpecFourthPage fourthPage;
	protected RelSpecFifthPage fifthPage;
		
	public RelSpecWizard(RelSpecOccurrence ap) {
		this.ap = ap;
	    canFinish=false;
	    setNeedsProgressMonitor(true); 
		setWindowTitle(RelSpecAntipattern.getAntipatternInfo().name);
	}
	
    @Override
    public boolean canFinish() {	 
    	return canFinish;	  
    };
    
	@Override
	public void addPages() 
	{	
		options = new RelSpecRefactoringPage(ap);
		finishing = new FinishingPage();
		
		firstPage = new RelSpecFirstPage(ap);
		secondPage = new RelSpecSecondPage(ap);
		thirdPage = new RelSpecThirdPage(ap);
		fourthPage = new RelSpecFourthPage(ap);
		fifthPage = new RelSpecFifthPage(ap);
		
		presentation = new PresentationPage(
			RelSpecAntipattern.getAntipatternInfo().name,
			RelSpecAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);
		addPage(secondPage);
		addPage(thirdPage);
		addPage(fourthPage);
		addPage(fifthPage);
		addPage(options);
		addPage(finishing);
	}
		
	public boolean isCanFinish() {
		return canFinish;
	}

	public RelSpecOccurrence getAp() {
		return ap;
	}

	public PresentationPage getPresentation() {
		return presentation;
	}

	public FinishingPage getFinishing() {
		canFinish=true;
		return finishing;
	}

	public RelSpecRefactoringPage getOptions() {
		return options;
	}

	public RelSpecFirstPage getFirstPage() {
		return firstPage;
	}

	public RelSpecSecondPage getSecondPage() {
		return secondPage;
	}

	public RelSpecThirdPage getThirdPage() {
		return thirdPage;
	}

	public RelSpecFourthPage getFourthPage() {
		return fourthPage;
	}

	public RelSpecFifthPage getFifthPage() {
		return fifthPage;
	}

	@Override
	public boolean performFinish() {
		
		return true;
	}

}
