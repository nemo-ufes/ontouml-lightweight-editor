package br.ufes.inf.nemo.antipattern.wizard.wholeover;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.OverlappingRefactoringPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class WholeOverWizard extends AntipatternWizard {

	protected ArrayList<DisjointWholeOverPage> disjointPages;
	protected ArrayList<ExclusiveWholeOverPage> exclusivePages;
	
	public WholeOverWizard(WholeOverOccurrence ap) {
		super(ap, WholeOverAntipattern.getAntipatternInfo().getName());
		disjointPages = new ArrayList<DisjointWholeOverPage>();
		exclusivePages = new ArrayList<ExclusiveWholeOverPage>();	
	}
	
	@Override
	public WholeOverOccurrence getAp(){
		return (WholeOverOccurrence)super.getAp();
	}
	
	@Override
	public void addPages() 
	{	
		
		finishing = new FinishingPage();
		options = new OverlappingRefactoringPage(getAp());
		
		for (Integer variationIndex = 0; variationIndex < getAp().getVariations().size(); variationIndex++){
			disjointPages.add(new DisjointWholeOverPage("DisjointPage "+variationIndex.toString(),getAp(),variationIndex));
			exclusivePages.add(new ExclusiveWholeOverPage("ExclusivePage "+variationIndex.toString(),getAp(),variationIndex));
		}
		
		presentation = new PresentationPage(
			WholeOverAntipattern.getAntipatternInfo().name,
			WholeOverAntipattern.getAntipatternInfo().acronym,
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
	
	public DisjointWholeOverPage getDisjointPage(int variationIndex){
		for (DisjointWholeOverPage page : this.disjointPages) {
			if (page.getVariationIndex()== variationIndex)
				return page;
		}
		return null;
	}
	
	public ExclusiveWholeOverPage getExclusivePage(int variationIndex){
		for (ExclusiveWholeOverPage page : this.exclusivePages) {
			if (page.getVariationIndex()== variationIndex)
				return page;
		}
		return null;
	}
	
	@Override
	public boolean performFinish() {
		runAllActions();
		return true;
	}
	
	
	
	public boolean hasNextVariation(int variationIndex){
		return variationIndex<getAp().getVariations().size()-1;
	}

}
