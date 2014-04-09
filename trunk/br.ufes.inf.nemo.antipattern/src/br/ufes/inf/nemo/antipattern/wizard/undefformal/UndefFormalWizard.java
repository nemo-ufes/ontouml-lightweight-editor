package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class UndefFormalWizard extends AntipatternWizard {

		protected ChangeFormalStereotypePage firstPage;
		protected IsMaterialPage secondPage;
		protected IsComparativeFormalPage thirdPage;
		protected CreateDataTypePage fourthPage;
		protected CreateMediatedPage fifthPage;
		
		public UndefFormalWizard(UndefFormalOccurrence ap) {
			super(ap, UndefFormalAntipattern.getAntipatternInfo().name);	    
		}
	    
		@Override
		public void addPages() 
		{	
			super.addPages();
			
			finishing = new FinishingPage();
			options = new UndefFormalRefactoringPage((UndefFormalOccurrence)ap);
					
			firstPage = new  ChangeFormalStereotypePage(( UndefFormalOccurrence)ap);
			secondPage = new IsMaterialPage((UndefFormalOccurrence)ap);
			thirdPage = new IsComparativeFormalPage((UndefFormalOccurrence)ap);
			fourthPage = new CreateDataTypePage((UndefFormalOccurrence)ap);
			fifthPage = new CreateMediatedPage((UndefFormalOccurrence)ap);
			
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

		public ChangeFormalStereotypePage getFirstPage() {
			return firstPage;
		}
		
		public IsMaterialPage getSecondPage()
		{
			return secondPage;
		}
			
		public IsComparativeFormalPage getThirdPage()
		{
			return thirdPage;
		}
		
		public CreateDataTypePage getFourthPage()
		{
			return fourthPage;
		}
		
		public CreateMediatedPage getFifthPage()
		{
			return fifthPage;
		}
		
}