package br.ufes.inf.nemo.antipattern.wizard.relover;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.DisjointOverlappingPage;

public class RelOverDisjointPage extends DisjointOverlappingPage {

	public RelOverDisjointPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(RelOverAntipattern.getAntipatternInfo().getName());
		setDescription("Analyzing the DISJOINTNESS of the mediated types from relator: "+occurrence.getParser().getStringRepresentation(occurrence.getMainType())+"\nGroup "+(variationIndex+1)+" of "+ occurrence.getVariations().size());
	}

	@Override
	public String getQuestion() {
		return	"Your model states that it is possible, although not required, for a single object to simultaneously act as (i.e. instantiate) "+
				"multiple mediated types of the relator "+getOccurrence().getParser().getStringRepresentation(getOccurrence().getMainType())+
				".\n\nIs there any particular case in which that multiple instantiation of the mediated types in the table below should not be allowed? " +
				"If there is such restriction in the domain, check the appropriate types. Otherwise, just go to the next page.";
	}

}
