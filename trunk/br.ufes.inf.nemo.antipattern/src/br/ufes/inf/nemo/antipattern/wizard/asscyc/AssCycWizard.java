package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class AssCycWizard extends AntipatternWizard {

	public AssCycFirstPage firstPage;
	
	public AssCycWizard(AssCycOccurrence ap) {
		super(ap,AssCycAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new AssCycFirstPage((AssCycOccurrence)ap);
				
		finishing = new FinishingPage();
		options = new AssCycRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				AssCycAntipattern.getAntipatternInfo().name,
				AssCycAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);		
		addPage(options);
		addPage(finishing);
	}
	
	public AssCycOccurrence getAp() {
		return (AssCycOccurrence)ap;
	}
	
	public AssCycFirstPage getFirstPage()
	{
		return firstPage;
	}
	@Override
	public boolean performFinish() {
		for(AntiPatternAction<?> action: super.getAllActions())
			action.run();
		
		return true;
	}
}