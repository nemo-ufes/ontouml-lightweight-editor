package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.WizardPage;

import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

public class DecIntWizard extends AntipatternWizard {

	private IdentityProviderPage identityProviderPage;
	private GeneralizationSetPage generalizationSetPage;
	private IntentionalDerivedPage intentionalDerivedPage;
	
	public DecIntWizard(DecIntOccurrence ap) {
		super(ap,DecIntAntipattern.getAntipatternInfo().name);		
	}

	@Override
	public void addPages() 
	{
		if(getAp().getIdentityProviders().size()>1)
			identityProviderPage = new IdentityProviderPage(getAp());
		else
			identityProviderPage = null;
		
		if(getAp().getDisjointGSList().size()>0)
			generalizationSetPage = new GeneralizationSetPage(getAp());
		else
			generalizationSetPage = null;
	
		intentionalDerivedPage = new IntentionalDerivedPage(getAp());
		finishing = new FinishingPage();
		options = new DecIntRefactoringPage(getAp());
		
		WizardPage firstPage;
		
		if(identityProviderPage!=null)
			firstPage = identityProviderPage;
		else if (generalizationSetPage!=null)
			firstPage = generalizationSetPage;
		else
			firstPage = intentionalDerivedPage;
			
		presentation = new PresentationPage(
			DecIntAntipattern.getAntipatternInfo().name,
			DecIntAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			DecIntAntipattern.getAntipatternInfo().description,
			firstPage,
			options
		);
			
		addPage(presentation);	
		
		if(identityProviderPage!=null) addPage(identityProviderPage);	
		if(generalizationSetPage!=null) addPage(generalizationSetPage);
		
		addPage(intentionalDerivedPage);
		addPage(options);
		addPage(finishing);
		
		this.canFinish = false;
	}
	
	public GeneralizationSetPage getGeneralizationSetPage() {
		return generalizationSetPage;
	}

	public IntentionalDerivedPage getIntentionalDerivedPage() {
		return intentionalDerivedPage;
	}

	public IdentityProviderPage getIdentityProviderPage()
	{
		return identityProviderPage;
	}
	
	public DecIntOccurrence getAp() {
		return (DecIntOccurrence)ap;
	}
	
}