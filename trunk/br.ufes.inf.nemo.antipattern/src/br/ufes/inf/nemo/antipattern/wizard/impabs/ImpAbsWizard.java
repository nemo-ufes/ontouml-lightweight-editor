package br.ufes.inf.nemo.antipattern.wizard.impabs;

import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class ImpAbsWizard extends AntipatternWizard {

	protected MultiplicityPage multPage;
	protected InseparablePage inseparablePage;
	protected EssentialPage essentialPage;
	protected ShareablePage shareablePage;
	protected ReadOnlyPage readOnlyPage;
	protected DerivedPage derivedPage;
	
	public boolean isMeronymic;
	
	public ImpAbsWizard(ImpAbsOccurrence apOccur) {
		super(apOccur, ImpAbsAntipattern.getAntipatternInfo().name);	    
	
		isMeronymic = apOccur.getAssociation() instanceof Meronymic;
	}
    
	@Override
	public void addPages() 
	{	
		super.addPages();
		
		finishing = new FinishingPage();
		options = new ImpAbsRefactoringPage(getImpAbs());		
		multPage = new MultiplicityPage(getImpAbs());
		derivedPage = new DerivedPage(getImpAbs());
		
		presentation = new PresentationPage(
			ImpAbsAntipattern.getAntipatternInfo().name,
			ImpAbsAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			ImpAbsAntipattern.getAntipatternInfo().description,
			multPage,
			options
		);
		
		try {
			if(isMeronymic){
				essentialPage = new EssentialPage(getImpAbs());
				inseparablePage = new InseparablePage(getImpAbs());
				shareablePage = new ShareablePage(getImpAbs());
				readOnlyPage = null;
			}
			else {
				readOnlyPage = new ReadOnlyPage(getImpAbs());
				inseparablePage = null;
				essentialPage = null;
				shareablePage = null;
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		addPage(presentation);		
		addPage(multPage);
		addPage(derivedPage);
		if(isMeronymic){
			addPage(inseparablePage);
			addPage(essentialPage);
			addPage(shareablePage);
		}
		else{
			addPage(readOnlyPage);
		}
		
		addPage(options);
		addPage(finishing);
	}

	public ImpAbsOccurrence getImpAbs() {
		return (ImpAbsOccurrence)ap;
	}

	public MultiplicityPage getMultPage() {
		return multPage;
	}

	public InseparablePage getInseparablePage() {
		return inseparablePage;
	}

	public EssentialPage getEssentialPage() {
		return essentialPage;
	}

	public ShareablePage getShareablePage() {
		return shareablePage;
	}

	public ReadOnlyPage getReadOnlyPage() {
		return readOnlyPage;
	}

	public DerivedPage getDerivedPage() {
		return derivedPage;
	}

	public boolean isMeronymic() {
		return isMeronymic;
	}

}
