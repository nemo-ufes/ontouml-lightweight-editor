package br.ufes.inf.nemo.antipattern.wizard.partover;

import br.ufes.inf.nemo.antipattern.partover.PartOverAntipattern;
import br.ufes.inf.nemo.antipattern.partover.PartOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingRefactoringPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingWizard;

public class PartOverWizard extends OverlappingWizard {

	public PartOverWizard(PartOverOccurrence ap) {
		super(ap, PartOverAntipattern.getAntipatternInfo().getName());
	}
	
	@Override
	public void addPages() 
	{	
		
		finishing = new FinishingPage();
		options = new OverlappingRefactoringPage(getAp());
		
		for (Integer variationIndex = 0; variationIndex < getAp().getVariations().size(); variationIndex++){
			disjointPages.add(new PartOverDisjointPage("DisjointPage "+variationIndex.toString(),getAp(),variationIndex));
			exclusivePages.add(new PartOverExclusivePage("ExclusivePage "+variationIndex.toString(),getAp(),variationIndex));
		}
		
		presentation = new PresentationPage(
			PartOverAntipattern.getAntipatternInfo().name,
			PartOverAntipattern.getAntipatternInfo().acronym,
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
