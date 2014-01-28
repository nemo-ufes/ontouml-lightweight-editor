package br.ufes.inf.nemo.antipattern.wizard.partover;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.partover.PartOverAntipattern;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.ExclusiveOverlappingPage;

public class PartOverExclusivePage extends ExclusiveOverlappingPage {

	public PartOverExclusivePage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
		setTitle(PartOverAntipattern.getAntipatternInfo().getName());
		setDescription("Analyzing the EXCLUSIVENESS of the wholes types of part: "+occurrence.getParser().getStringRepresentation(occurrence.getMainType())+"\nGroup "+(variationIndex+1)+" of "+ occurrence.getVariations().size());
	}

	@Override
	public String getQuestion() {
		
		String question = 	"Your previous answer shows that it is correct for at least some of the whole types to have an overlappable extension. \n\nWith that in mid, answer the following: "+
							"Can an instance of the part '"+getOccurrence().getParser().getStringRepresentation(getOccurrence().getMainType())+"' compose the same whole acting as different whole types?"+
							"If there is any restriction of such nature, select the appropriate whole types on the table below. (Each line on the table creates a restriction involving the selected types)";
		
		return question;
	}

}
