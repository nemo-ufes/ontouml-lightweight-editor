package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class HomoFuncWizard extends AntipatternWizard {

	public HomoFuncFirstPage firstPage;
	public HomoFuncSecondPage secondPage;
			
	public HomoFuncWizard(HomoFuncOccurrence ap) {
		super(ap,HomoFuncAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new HomoFuncFirstPage((HomoFuncOccurrence)ap);
		secondPage = new HomoFuncSecondPage((HomoFuncOccurrence)ap);
		
		finishing = new FinishingPage();
		options = new HomoFuncRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				HomoFuncAntipattern.getAntipatternInfo().name,
				HomoFuncAntipattern.getAntipatternInfo().acronym,
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
	
	public HomoFuncOccurrence getAp() {
		return (HomoFuncOccurrence)ap;
	}
	
	public HomoFuncFirstPage getFirstPage()
	{
		return firstPage;
	}
	public HomoFuncSecondPage getSecondPage()
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