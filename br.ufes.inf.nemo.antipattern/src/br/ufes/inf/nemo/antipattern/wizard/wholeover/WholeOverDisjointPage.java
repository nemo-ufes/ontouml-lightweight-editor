package br.ufes.inf.nemo.antipattern.wizard.wholeover;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.DisjointOverlappingPage;

public class WholeOverDisjointPage extends DisjointOverlappingPage {

	public WholeOverDisjointPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(WholeOverAntipattern.getAntipatternInfo().getName());
		setDescription("Analyzing the DISJOINTNESS of the part types of whole: "+occurrence.getParser().getStringRepresentation(occurrence.getMainType())+"\nGroup "+(variationIndex+1)+" of "+ occurrence.getVariations().size());
	}

	@Override
	public String getQuestion() {
		return	"Your model states that it is possible, although not required, for a single object to simultaneously act as (i.e. instantiate) "+
				"multiple part types of whole "+getOccurrence().getParser().getStringRepresentation(getOccurrence().getMainType())+
				".\n\nIs there any particular case in which that multiple instantiation of the part types in the table below should not be allowed? " +
				"If there is such restriction in the domain, check the appropriate types. Otherwise, just go to the next page.";
	}

}
