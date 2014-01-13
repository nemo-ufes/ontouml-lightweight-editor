package br.ufes.inf.nemo.oled.antipattern.wizard.wholeover;

import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.oled.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.oled.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class WholeOverWizard extends AntipatternWizard {

	protected WholeOverFirstPage firstPage;
//	protected WholeOverSecondPage secondPage;
//	protected WholeOverThirdPage thirdPage;
//	protected WholeOverFourthPage fourthPage;
	
	public enum WholeOverAction {MAKE_DIJSOINT, MAKE_EXCLUSIVE}
	
//	private WizardAction<WholeOverAction> action;
	
	public WholeOverWizard(WholeOverOccurrence ap) {
		super(ap, WholeOverAntipattern.getAntipatternInfo().getName());
	}
	
	@Override
	public WholeOverOccurrence getAp(){
		return (WholeOverOccurrence)super.getAp();
	}
	
	@Override
	public void addPages() 
	{	
//		options = new WholeOverRefactoringPage(getAp());
		finishing = new FinishingPage();
		
		firstPage = new WholeOverFirstPage(getAp());
//		secondPage = new WholeOverSecondPage(getAp());
//		thirdPage = new WholeOverThirdPage(getAp());
//		fourthPage = new WholeOverFourthPage(getAp());
		
		presentation = new PresentationPage(
			WholeOverAntipattern.getAntipatternInfo().name,
			WholeOverAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);
//		addPage(secondPage);
//		addPage(thirdPage);
//		addPage(fourthPage);
		addPage(options);
		addPage(finishing);
	}
		@Override
	public boolean performFinish() {
		return true;
	}

}
