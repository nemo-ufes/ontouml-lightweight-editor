package br.ufes.inf.nemo.antipattern.wizard.relspec;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecWizard extends AntipatternWizard {

	protected RelSpecFirstPage firstPage;
	protected RelSpecSecondPage secondPage;
	protected RelSpecThirdPage thirdPage;
	protected RelSpecFourthPage fourthPage;
	protected RelSpecFifthPage fifthPage;
	
	public RelSpecWizard(RelSpecOccurrence ap) {
		super(ap, RelSpecAntipattern.getAntipatternInfo().name);	    
	}
    
	@Override
	public void addPages() 
	{	
		super.addPages();
		
		finishing = new FinishingPage();
		options = new RelSpecRefactoringPage((RelSpecOccurrence)ap);
				
		firstPage = new RelSpecFirstPage((RelSpecOccurrence)ap);
		secondPage = new RelSpecSecondPage((RelSpecOccurrence)ap);
		thirdPage = new RelSpecThirdPage((RelSpecOccurrence)ap);
		fourthPage = new RelSpecFourthPage((RelSpecOccurrence)ap);
		fifthPage = new RelSpecFifthPage((RelSpecOccurrence)ap);
		
		presentation = new PresentationPage(
			RelSpecAntipattern.getAntipatternInfo().name,
			RelSpecAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			RelSpecAntipattern.getAntipatternInfo().description,
			firstPage,
			options
		);
		
		addPage(presentation);		
		addPage(firstPage);
		addPage(secondPage);
		addPage(thirdPage);
		addPage(fourthPage);
		addPage(fifthPage);
		addPage(options);
		addPage(finishing);
	}

	public RelSpecOccurrence getAp() {
		return (RelSpecOccurrence)ap;
	}

	public RelSpecFirstPage getFirstPage() {
		return firstPage;
	}

	public RelSpecSecondPage getSecondPage() {
		return secondPage;
	}

	public RelSpecThirdPage getThirdPage() {
		return thirdPage;
	}

	public RelSpecFourthPage getFourthPage() {
		return fourthPage;
	}

	public RelSpecFifthPage getFifthPage() {
		return fifthPage;
	}

}
