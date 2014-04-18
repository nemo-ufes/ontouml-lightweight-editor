package br.ufes.inf.nemo.antipattern.wizard.wholeover;

import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingRefactoringPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingWizard;

public class WholeOverWizard extends OverlappingWizard {

	public WholeOverWizard(WholeOverOccurrence ap) {
		super(ap, WholeOverAntipattern.getAntipatternInfo().getName());
	}
	
	@Override
	public void addPages() 
	{	
		
		finishing = new FinishingPage();
		options = new OverlappingRefactoringPage(getAp());
		
		for (Integer variationIndex = 0; variationIndex < getAp().getVariations().size(); variationIndex++){
			disjointPages.add(new WholeOverDisjointPage("DisjointPage "+variationIndex.toString(),getAp(),variationIndex));
			exclusivePages.add(new WholeOverExclusivePage("ExclusivePage "+variationIndex.toString(),getAp(),variationIndex));
		}
		
		presentation = new PresentationPage(
			WholeOverAntipattern.getAntipatternInfo().name,
			WholeOverAntipattern.getAntipatternInfo().acronym,
			ap.toString(),
			WholeOverAntipattern.getAntipatternInfo().description,
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
