package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class GSRigWizard extends AntipatternWizard {

	public GSRigFirstPage firstPage;
	public GSRigSecondPage secondPage;
	public GSRigThirdPage thirdPage;
	public GSRigFourthPage fourthPage;
			
	public GSRigWizard(GSRigOccurrence ap) {
		super(ap,GSRigAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new GSRigFirstPage((GSRigOccurrence)ap);
		secondPage = new GSRigSecondPage((GSRigOccurrence)ap);
		thirdPage = new GSRigThirdPage((GSRigOccurrence)ap);
		fourthPage = new GSRigFourthPage((GSRigOccurrence)ap);
		
		finishing = new FinishingPage();
		options = new GSRigRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				GSRigAntipattern.getAntipatternInfo().name,
				GSRigAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);	
		addPage(secondPage);
		addPage(thirdPage);
		addPage(fourthPage);
		addPage(options);
		addPage(finishing);
	}
	
	public GSRigOccurrence getAp() {
		return (GSRigOccurrence)ap;
	}
	
	public GSRigFirstPage getFirstPage()
	{
		return firstPage;
	}
		
	public GSRigSecondPage getSecondPage()
	{
		return secondPage;
	}
	
	public GSRigThirdPage getThirdPage()
	{
		return thirdPage;
	}
	
	public GSRigFourthPage getFourthPage()
	{
		return fourthPage;	
	}
	
	@Override
	public boolean performFinish() {
		for(AntiPatternAction<?> action: super.getAllActions())
			action.run();
		
		return true;
	}
}
