package br.ufes.inf.nemo.antipattern.wizard.multidep;

import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class MultiDepWizard extends AntipatternWizard {

	public MultiDepFirstPage firstPage;
	public MultiDepSecondPage secondPage;
	public MultiDepThirdPage thirdPage;
	
	public MultiDepWizard(MultiDepOccurrence ap) {
		super(ap,MultiDepAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new MultiDepFirstPage((MultiDepOccurrence)ap);
		secondPage = new MultiDepSecondPage((MultiDepOccurrence)ap);
		thirdPage = new MultiDepThirdPage((MultiDepOccurrence)ap);
		
		finishing = new FinishingPage();
		options = new MultiDepRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			MultiDepAntipattern.getAntipatternInfo().name,
			MultiDepAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			MultiDepAntipattern.getAntipatternInfo().description,
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
	
	public MultiDepOccurrence getAp() {
		return (MultiDepOccurrence)ap;
	}
	
	public MultiDepFirstPage getFirstPage()
	{
		return firstPage;
	}
	
	public MultiDepSecondPage getSecondPage()
	{
		return secondPage;
	}
	
	public MultiDepThirdPage getThirdPage()
	{
		return thirdPage;
	}
	
//	public MultiDepSecondPage addSecondPage(ArrayList<Property> properties)
//	{
//		secondPage = new MultiDepSecondPage((MultiDepOccurrence)ap, properties);
//		addPage(secondPage);
//		return secondPage;
//	}
		
}
