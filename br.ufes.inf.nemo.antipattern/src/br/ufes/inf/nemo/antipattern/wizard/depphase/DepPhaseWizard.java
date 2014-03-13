package br.ufes.inf.nemo.antipattern.wizard.depphase;

import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class DepPhaseWizard extends AntipatternWizard {

	public DepPhaseFirstPage firstPage;
	public DepPhaseSecondPage secondPage;
	
	public DepPhaseWizard(DepPhaseOccurrence ap) {
		super(ap,DepPhaseAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new DepPhaseFirstPage(getAp());
		secondPage = new DepPhaseSecondPage(getAp());
		
		finishing = new FinishingPage();
		options = new DepPhaseRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			DepPhaseAntipattern.getAntipatternInfo().name,
			DepPhaseAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);
		addPage(secondPage);
		addPage(options);
		addPage(finishing);
	}
	
	public DepPhaseOccurrence getAp() {
		return (DepPhaseOccurrence)ap;
	}
	
	public DepPhaseFirstPage getFirstPage()
	{
		return firstPage;
	}
	
	public DepPhaseSecondPage getSecondPage()
	{
		return secondPage;
	}
	


	@Override
	public boolean performFinish() {
		for(AntiPatternAction<?> action: super.getAllActions())
			action.run();
		
		return true;
	}
}
