package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.FinishingPage;
import br.ufes.inf.nemo.antipattern.wizard.PresentationPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class OverlappingWizard extends AntipatternWizard {

	protected ArrayList<DisjointOverlappingGroupPage> disjointPages;
	protected ArrayList<ExclusiveGroupPage> exclusivePages;
	private DisjointOverlappingDefinitionPage disjointOverlappingDefinitionPage;
	private ExclusiveDefinitionPage exclusiveDefinitionPage;
	protected boolean hasShownExclusiveDefinition;
	protected AntipatternInfo info;
	
	public OverlappingWizard(OverlappingOccurrence ap, String wizardName, AntipatternInfo info) {
		super(ap, wizardName);
		disjointPages = new ArrayList<DisjointOverlappingGroupPage>();
		exclusivePages = new ArrayList<ExclusiveGroupPage>();
		hasShownExclusiveDefinition = false;
		this.info = info;
	}
	
	@Override
	public void addPages(){
		
		finishing = new FinishingPage();
		options = new OverlappingRefactoringPage(getAp()); 
		exclusiveDefinitionPage = new ExclusiveDefinitionPage(getAp(),info);
		disjointOverlappingDefinitionPage = new DisjointOverlappingDefinitionPage(getAp(),info);
		
		for (Integer variationIndex = 0; variationIndex < getAp().getGroups().size(); variationIndex++){
			disjointPages.add(new DisjointOverlappingGroupPage("DisjointPage "+variationIndex.toString(),getAp(),variationIndex));
			exclusivePages.add(new ExclusiveGroupPage("ExclusivePage "+variationIndex.toString(),getAp(),variationIndex));
		}
		
		presentation = new PresentationPage(
			info.name,
			info.acronym,
			ap.toString(),
			info.description,
			disjointOverlappingDefinitionPage,
			options
		);
				
		addPage(presentation);
		addPage(disjointOverlappingDefinitionPage);
		addPage(exclusiveDefinitionPage);
		
		for (int i = 0; i < getAp().getGroups().size(); i++) {        
			addPage(disjointPages.get(i));
			addPage(exclusivePages.get(i));
		}
		
		addPage(options);
		addPage(finishing);
	}
	
	public DisjointOverlappingGroupPage getDisjointOverlappingPage(int variationIndex){
		for (DisjointOverlappingGroupPage page : this.disjointPages) {
			if (page.getVariationIndex()== variationIndex)
				return page;
		}
		return null;
	}
	
	public ExclusiveGroupPage getExclusivePage(int variationIndex){
		for (ExclusiveGroupPage page : this.exclusivePages) {
			if (page.getVariationIndex()== variationIndex)
				return page;
		}
		return null;
	}
	
	public boolean hasNextVariation(int variationIndex){
		return variationIndex<getAp().getGroups().size()-1;
	}
	
	public OverlappingOccurrence getAp(){
		return (OverlappingOccurrence)super.getAp();
	}
	
	public ExclusiveDefinitionPage getExclusiveDefinitionPage(int indexVariation){
		exclusiveDefinitionPage.setIndex(indexVariation);
		return exclusiveDefinitionPage;
	}

	public DisjointOverlappingDefinitionPage getOverlapDefinitionPage() {
		return disjointOverlappingDefinitionPage;
	}
	
	
}
