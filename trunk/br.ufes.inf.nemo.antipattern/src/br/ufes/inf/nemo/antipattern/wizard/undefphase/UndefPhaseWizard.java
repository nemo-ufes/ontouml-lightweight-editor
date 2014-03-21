package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class UndefPhaseWizard extends AntipatternWizard {

	protected UndefPhaseFirstPage firstPage;
	
	public UndefPhaseWizard(UndefPhaseOccurrence ap) {
		super(ap, UndefPhaseAntipattern.getAntipatternInfo().name);	    
	}
    
	@Override
	public void addPages() 
	{	
		super.addPages();
		
		finishing = new FinishingPage();
		options = new UndefPhaseRefactoringPage((UndefPhaseOccurrence)ap);
				
		firstPage = new  UndefPhaseFirstPage((UndefPhaseOccurrence)ap);
				
		presentation = new PresentationPage(
			UndefPhaseAntipattern.getAntipatternInfo().name,
			UndefPhaseAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);		
		addPage(options);
		addPage(finishing);
	}

	public UndefPhaseOccurrence getAp() {
		return ((UndefPhaseOccurrence)ap);
	}

	public UndefPhaseFirstPage getFirstPage() {
		return firstPage;
	}
	
	@Override
	public boolean performFinish() {
		runAllActions();
		return true;
	}
}
