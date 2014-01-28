package br.ufes.inf.nemo.antipattern.wizard.partover;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.partover.PartOverAntipattern;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.DisjointOverlappingPage;

public class PartOverDisjointPage extends DisjointOverlappingPage {

	public PartOverDisjointPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(PartOverAntipattern.getAntipatternInfo().getName());
		setDescription("Analyzing the DISJOINTNESS of the whole types of part: "+occurrence.getParser().getStringRepresentation(occurrence.getMainType())+"\nGroup "+(variationIndex+1)+" of "+ occurrence.getVariations().size());
	}

	@Override
	public String getQuestion() {
		return	"Your model states that it is possible, although not required, for a single object to simultaneously act as (i.e. instantiate) "+
				"multiple wholes types regarding the part "+getOccurrence().getParser().getStringRepresentation(getOccurrence().getMainType())+
				".\n\nIs there any particular case in which that multiple instantiation of the whole types in the table below should not be allowed? " +
				"If there is such restriction in the domain, check the appropriate types. Otherwise, just go to the next page.";
	}

}
