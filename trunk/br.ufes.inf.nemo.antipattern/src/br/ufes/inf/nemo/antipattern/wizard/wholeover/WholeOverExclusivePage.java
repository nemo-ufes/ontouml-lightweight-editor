package br.ufes.inf.nemo.antipattern.wizard.wholeover;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.ExclusiveOverlappingPage;

public class WholeOverExclusivePage extends ExclusiveOverlappingPage {

	public WholeOverExclusivePage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(WholeOverAntipattern.getAntipatternInfo().getName());
		setDescription("Analyzing the EXCLUSIVENESS of the part types of whole: "+occurrence.getParser().getStringRepresentation(occurrence.getMainType())+"\nGroup "+(variationIndex+1)+" of "+ occurrence.getVariations().size());
	}

	@Override
	public String getQuestion() {
		
		String question = 	"Your previous answer show that it is correct for the part types to have an overlappable extension. \n\nWith that in mid, answer the following: "+
							"Can an instance of the whole '"+getOccurrence().getParser().getStringRepresentation(getOccurrence().getMainType())+"' be composed by an object acting as different part types? "+
							"If there is any restriction of such nature, select the part types on the table below. (Each line on the table creates a restriction involving the selected types)";
		
		return question;
	}

}
