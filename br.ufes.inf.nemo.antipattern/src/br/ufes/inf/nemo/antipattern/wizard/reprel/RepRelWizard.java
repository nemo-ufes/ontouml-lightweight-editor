package br.ufes.inf.nemo.antipattern.wizard.reprel;

import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RepRelWizard extends AntipatternWizard {

	protected RepRelFirstPage firstPage;
	protected RepRelSecondPage secondPage;
	protected RepRelThirdPage thirdPage;
	
	public RepRelWizard(RepRelOccurrence ap) {
		super(ap, RepRelAntipattern.getAntipatternInfo().name);	    
	}
    
	@Override
	public void addPages() 
	{	
		super.addPages();
		
		finishing = new FinishingPage();
		options = new RepRelRefactoringPage((RepRelOccurrence)ap);
				
		firstPage = new RepRelFirstPage((RepRelOccurrence)ap);
		secondPage = new RepRelSecondPage((RepRelOccurrence)ap);
		thirdPage = new RepRelThirdPage((RepRelOccurrence)ap);
		
		presentation = new PresentationPage(
			RepRelAntipattern.getAntipatternInfo().name,
			RepRelAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			RepRelAntipattern.getAntipatternInfo().description,
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);
		addPage(secondPage);
		addPage(thirdPage);
		addPage(options);
		addPage(finishing);
	}

	public RepRelOccurrence getAp() {
		return (RepRelOccurrence)ap;
	}

	public RepRelFirstPage getFirstPage() {
		return firstPage;
	}
	
	public RepRelSecondPage getSecondPage()
	{
		return secondPage;
	}
		
	public RepRelThirdPage getThirdPage()
	{
		return thirdPage;
	}
	
}
