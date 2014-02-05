package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class UndefFormalWizard extends AntipatternWizard {

		protected UndefFormalFirstPage firstPage;
		protected UndefFormalSecondPage secondPage;
		protected UndefFormalThirdPage thirdPage;
		protected UndefFormalFourthPage fourthPage;
		protected UndefFormalFifthPage fifthPage;
		
		public UndefFormalWizard(UndefFormalOccurrence ap) {
			super(ap, UndefFormalAntipattern.getAntipatternInfo().name);	    
		}
	    
		@Override
		public void addPages() 
		{	
			super.addPages();
			
			finishing = new FinishingPage();
			options = new UndefFormalRefactoringPage((UndefFormalOccurrence)ap);
					
			firstPage = new  UndefFormalFirstPage(( UndefFormalOccurrence)ap);
			secondPage = new UndefFormalSecondPage((UndefFormalOccurrence)ap);
			thirdPage = new UndefFormalThirdPage((UndefFormalOccurrence)ap);
			fourthPage = new UndefFormalFourthPage((UndefFormalOccurrence)ap);
			fifthPage = new UndefFormalFifthPage((UndefFormalOccurrence)ap);
			
			presentation = new PresentationPage(
				UndefFormalAntipattern.getAntipatternInfo().name,
				UndefFormalAntipattern.getAntipatternInfo().acronym,
				ap.toString(),
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

		public UndefFormalOccurrence getAp() {
			return ((UndefFormalOccurrence)ap);
		}

		public UndefFormalFirstPage getFirstPage() {
			return firstPage;
		}
		
		public UndefFormalSecondPage getSecondPage()
		{
			return secondPage;
		}
			
		public UndefFormalThirdPage getThirdPage()
		{
			return thirdPage;
		}
		
		public UndefFormalFourthPage getFourthPage()
		{
			return fourthPage;
		}
		
		public UndefFormalFifthPage getFifthPage()
		{
			return fifthPage;
		}
		
		@Override
		public boolean performFinish() {
			runAllActions();
			return true;
		}
}