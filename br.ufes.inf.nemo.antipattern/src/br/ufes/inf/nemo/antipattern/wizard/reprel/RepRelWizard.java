package br.ufes.inf.nemo.antipattern.wizard.reprel;

import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
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
//	protected RelSpecSecondPage secondPage;
//	protected RelSpecThirdPage thirdPage;
//	protected RelSpecFourthPage fourthPage;
//	protected RelSpecFifthPage fifthPage;
	
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
//		secondPage = new RelSpecSecondPage((RelSpecOccurrence)ap);
//		thirdPage = new RelSpecThirdPage((RelSpecOccurrence)ap);
//		fourthPage = new RelSpecFourthPage((RelSpecOccurrence)ap);
//		fifthPage = new RelSpecFifthPage((RelSpecOccurrence)ap);
		
		presentation = new PresentationPage(
			RepRelAntipattern.getAntipatternInfo().name,
			RepRelAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);
//		addPage(secondPage);
//		addPage(thirdPage);
//		addPage(fourthPage);
//		addPage(fifthPage);
		addPage(options);
		addPage(finishing);
	}

	public RepRelOccurrence getAp() {
		return (RepRelOccurrence)ap;
	}
//
//	public RelSpecFirstPage getFirstPage() {
//		return firstPage;
//	}
//
//	public RelSpecSecondPage getSecondPage() {
//		return secondPage;
//	}
//
//	public RelSpecThirdPage getThirdPage() {
//		return thirdPage;
//	}
//
//	public RelSpecFourthPage getFourthPage() {
//		return fourthPage;
//	}
//
//	public RelSpecFifthPage getFifthPage() {
//		return fifthPage;
//	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean performFinish() {
		AntiPatternAction action = getAction(0);			
		if(action!=null) action.run();		
		return true;
	}
}