package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;

public class ExclusiveGroupPage extends OverlappingWizardPage{

	
	public ExclusiveGroupPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);		
	}
	
	@Override
	public void registerActions(){
		//register one action for each valid action in the table
		getOverlappingWizard().removeAllActions(getVariationIndex(),OverlappingAction.Action.EXCLUSIVE);
		
		for (ArrayList<Property> propertyEndList : getBuilder().getSelections()) {
			OverlappingAction action = new OverlappingAction(getOccurrence(),getVariation());
			action.setExclusive(propertyEndList);
			getOverlappingWizard().addAction(getVariationIndex(), action);
		}
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		if(!btnNo.getSelection() && !btnYes.getSelection())
			return null;
		
		registerActions();
		
		if(getOverlappingWizard().hasNextVariation(getVariationIndex())) {
			return getOverlappingWizard().getDisjointOverlappingPage(getVariationIndex()+1);
		}
		else {
			return getOverlappingWizard().getFinishing();
		}
	}

	@Override
	public String getQuestion() {
		return 	"Your first answer for this group confirms that at least two types in it are overlapping. " +
				"So far, the types are still non-exclusive. Is that really the case? If not, use the table below to specify which types are exclusive. " +
				"If not, just go directly to the next page.";
	}
	
	
	
	
	
}
