package br.ufes.inf.nemo.oled.antipattern.wizard.wholeover;

import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.antipattern.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.oled.antipattern.wizard.ActionWizard;
import br.ufes.inf.nemo.oled.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.oled.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.oled.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class WholeOverWizard extends AntipatternWizard {

	public boolean canFinish = true;
	
	protected WholeOverFirstPage firstPage;
//	protected WholeOverSecondPage secondPage;
//	protected WholeOverThirdPage thirdPage;
//	protected WholeOverFourthPage fourthPage;
	
	public enum WholeOverAction {MAKE_DIJSOINT, MAKE_EXCLUSIVE}
	
	private ActionWizard<WholeOverAction> action;
	
	public ActionWizard<WholeOverAction> getAction() {
		return action;
	}

	public WholeOverWizard(WholeOverOccurrence ap) {
		super(ap, WholeOverAntipattern.getAntipatternInfo().getName());
		action = new ActionWizard<WholeOverAction>();
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
		
		System.out.println("ACTION: "+action.getCode());
		System.out.println("PAR: "+action.getParameters());
		
		return true;
	}

}
