package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class MultiDepWizard extends AntipatternWizard {

	public MultiDepFirstPage firstPage;
	public MultiDepSecondPage secondPage;
	
	public MultiDepWizard(MultiDepOccurrence ap) {
		super(ap,MultiDepAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		firstPage = new MultiDepFirstPage((MultiDepOccurrence)ap);
				
		finishing = new FinishingPage();
		options = new MultiDepRefactoringPage(getAp());
		
		presentation = new PresentationPage(
			MultiDepAntipattern.getAntipatternInfo().name,
			MultiDepAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			firstPage,
			options
		);
			
		addPage(presentation);	
		addPage(firstPage);
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
	
	public MultiDepSecondPage addSecondPage(ArrayList<Property> properties)
	{
		secondPage = new MultiDepSecondPage((MultiDepOccurrence)ap, properties);
		addPage(secondPage);
		return secondPage;
	}
		
	@Override
	public boolean performFinish() {
		for(AntiPatternAction<?> action: super.getAllActions())
			action.run();
		
		return true;
	}
}
