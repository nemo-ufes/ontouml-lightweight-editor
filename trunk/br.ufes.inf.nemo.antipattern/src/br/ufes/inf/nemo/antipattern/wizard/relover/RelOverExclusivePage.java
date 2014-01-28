package br.ufes.inf.nemo.antipattern.wizard.relover;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.ExclusiveOverlappingPage;

public class RelOverExclusivePage extends ExclusiveOverlappingPage {

	public RelOverExclusivePage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(RelOverAntipattern.getAntipatternInfo().getName());
		setDescription("Analyzing the EXCLUSIVENESS of the mediated types from relator: "+occurrence.getParser().getStringRepresentation(occurrence.getMainType())+"\nGroup "+(variationIndex+1)+" of "+ occurrence.getVariations().size());
	}

	@Override
	public String getQuestion() {
		
		String question = 	"Your previous answer shows that it is correct for at least some of the mediated types to have an overlappable extension. \n\nWith that in mid, answer the following: "+
							"Can an instance of the relator '"+getOccurrence().getParser().getStringRepresentation(getOccurrence().getMainType())+"' mediated an object acting as different mediated types? "+
							"If there is any restriction of such nature, select the mediated types on the table below. (Each line on the table creates a restriction involving the selected types)";
		
		return question;
	}

}
