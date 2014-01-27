package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class OverlappingWizard extends AntipatternWizard {

	protected ArrayList<DisjointOverlappingPage> disjointPages;
	protected ArrayList<ExclusiveOverlappingPage> exclusivePages;
	
	public OverlappingWizard(OverlappingOccurrence ap, String wizardName) {
		super(ap, wizardName);
		disjointPages = new ArrayList<DisjointOverlappingPage>();
		exclusivePages = new ArrayList<ExclusiveOverlappingPage>();	
	}
	
	public DisjointOverlappingPage getDisjointPage(int variationIndex){
		for (DisjointOverlappingPage page : this.disjointPages) {
			if (page.getVariationIndex()== variationIndex)
				return page;
		}
		return null;
	}
	
	public ExclusiveOverlappingPage getExclusivePage(int variationIndex){
		for (ExclusiveOverlappingPage page : this.exclusivePages) {
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
	
	public OverlappingOccurrence getAp(){
		return (OverlappingOccurrence)super.getAp();
	}

}
