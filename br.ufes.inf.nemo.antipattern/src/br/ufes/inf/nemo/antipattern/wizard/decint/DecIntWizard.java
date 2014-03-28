package br.ufes.inf.nemo.antipattern.wizard.decint;

import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class DecIntWizard extends AntipatternWizard {

	public IdentityProviderPage identityProviderPage;
	public GeneralizationSetPage generalizationSetPage;
	
	public DecIntWizard(DecIntOccurrence ap) {
		super(ap,DecIntAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		identityProviderPage = new IdentityProviderPage(getAp());
		generalizationSetPage = new GeneralizationSetPage(getAp());
		
		finishing = new FinishingPage();
		options = new DecIntRefactoringPage(getAp());
		
		presentation = new PresentationPage(
				DecIntAntipattern.getAntipatternInfo().name,
				DecIntAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			identityProviderPage,
			options
		);
			
		addPage(presentation);	
		addPage(identityProviderPage);	
		addPage(generalizationSetPage);
		addPage(options);
		addPage(finishing);
	}
	
	public DecIntOccurrence getAp() {
		return (DecIntOccurrence)ap;
	}
	
	public IdentityProviderPage getIdentityProviderPage()
	{
		return identityProviderPage;
	}
	public GeneralizationSetPage getSecondPage()
	{
		return generalizationSetPage;
	}
	@Override
	public boolean performFinish() {
		for(AntiPatternAction<?> action: super.getAllActions())
			action.run();
		
		return true;
	}
}