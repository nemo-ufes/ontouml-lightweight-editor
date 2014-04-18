package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class HomoFuncWizard extends AntipatternWizard {

	public HomoFuncFirstPage firstPage;
	public HomoFuncSecondPage secondPage;
	public HomoFuncThirdPage thirdPage;
	public HomoFuncFifthPage fifthPage;
	public HomoFuncSixthPage sixthPage;
	public HomoFuncSeventhPage seventhPage;
	
	public HomoFuncWizard(HomoFuncOccurrence ap) {
		super(ap,HomoFuncAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new HomoFuncFirstPage((HomoFuncOccurrence)ap);
		secondPage = new HomoFuncSecondPage((HomoFuncOccurrence)ap);
		thirdPage = new HomoFuncThirdPage((HomoFuncOccurrence)ap);
		fifthPage = new HomoFuncFifthPage((HomoFuncOccurrence)ap);
		sixthPage = new HomoFuncSixthPage((HomoFuncOccurrence)ap);
		seventhPage = new HomoFuncSeventhPage((HomoFuncOccurrence)ap);
		
		finishing = new FinishingPage();
		options = new HomoFuncRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				HomoFuncAntipattern.getAntipatternInfo().name,
				HomoFuncAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			HomoFuncAntipattern.getAntipatternInfo().description,
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);		
		addPage(secondPage);
		addPage(thirdPage);
		addPage(fifthPage);
		addPage(sixthPage);
		addPage(seventhPage);
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
	public HomoFuncThirdPage getThirdPage()
	{
		return thirdPage;
	}
	public HomoFuncFifthPage getFifthPage()
	{
		return fifthPage;
	}
	public HomoFuncSixthPage getSixthPage()
	{
		return sixthPage;
	}
	public HomoFuncSeventhPage getSeventhPage()
	{
		return seventhPage;
	}
	
}