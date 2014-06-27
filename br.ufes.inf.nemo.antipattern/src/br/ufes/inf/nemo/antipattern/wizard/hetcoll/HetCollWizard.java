package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import org.eclipse.jface.wizard.IWizardPage;

import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class HetCollWizard extends AntipatternWizard {

	public HetCollFirstPage firstPage;
	public HetCollSecondPage secondPage;
	public HetCollThirdPage thirdPage;
		
	public HetCollWizard(HetCollOccurrence ap) {
		super(ap,HetCollAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new HetCollFirstPage((HetCollOccurrence)ap);
		secondPage = new HetCollSecondPage((HetCollOccurrence)ap);
		thirdPage = new HetCollThirdPage((HetCollOccurrence)ap);
			
		finishing = new FinishingPage();
		options = new HetCollRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				HetCollAntipattern.getAntipatternInfo().name,
				HetCollAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			HetCollAntipattern.getAntipatternInfo().description,
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
	
	public HetCollOccurrence getAp() {
		return (HetCollOccurrence)ap;
	}
	
	public HetCollFirstPage getFirstPage(){
		return firstPage;
	}
	
	public IWizardPage getSecondPage() {
		return secondPage;
	}
	
	public HetCollThirdPage getThirdPage(){
		return thirdPage;
	}

	
}