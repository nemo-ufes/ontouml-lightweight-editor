package br.ufes.inf.nemo.antipattern.wizard.relover;

import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingRefactoringPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingWizard;

public class RelOverWizard extends OverlappingWizard {

	public RelOverWizard(RelOverOccurrence ap) {
		super(ap, RelOverAntipattern.getAntipatternInfo().getName());
	}
	
	@Override
	public void addPages() 
	{	
		
		finishing = new FinishingPage();
		options = new OverlappingRefactoringPage(getAp());
		
		for (Integer variationIndex = 0; variationIndex < getAp().getVariations().size(); variationIndex++){
			disjointPages.add(new RelOverDisjointPage("DisjointPage "+variationIndex.toString(),getAp(),variationIndex));
			exclusivePages.add(new RelOverExclusivePage("ExclusivePage "+variationIndex.toString(),getAp(),variationIndex));
		}
		
		presentation = new PresentationPage(
			RelOverAntipattern.getAntipatternInfo().name,
			RelOverAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			disjointPages.get(0),
			options
		);
				
		addPage(presentation);
		
		for (int i = 0; i < getAp().getVariations().size(); i++) {        
			addPage(disjointPages.get(i));
			addPage(exclusivePages.get(i));
		}
		
		addPage(options);
		addPage(finishing);
	}

}
