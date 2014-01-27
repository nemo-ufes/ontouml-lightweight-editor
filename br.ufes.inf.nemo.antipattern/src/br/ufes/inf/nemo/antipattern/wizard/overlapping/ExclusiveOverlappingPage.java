package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;

public abstract class ExclusiveOverlappingPage extends OverlappingWizardPage{

	
	public ExclusiveOverlappingPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);		
	}

//	@Override
//	public String getQuestion() {
//		String question = 	"From your previous answer we know that the part types do have an overlappable extension. The question now is: "+
//							"Can an instance of the whole '"+getOccurrence().getMainType().getName()+"' be composed by an object acting as the part types ";
//		
//		for (int i = 0; i < getVariation().getOverlappingProperties().size(); i++) {
//			Property partEnd = getVariation().getOverlappingProperties().get(i);
//			question += "'"+partEnd.getType().getName()+"' ";
//			
//			if(partEnd.getName()==null || partEnd.getName().trim().isEmpty())
//				question += "(unnamed)";
//			else
//				question += "("+partEnd.getName()+")";
//			
//			if (i < getVariation().getOverlappingProperties().size()-2)
//				question+= ", ";
//			else if (i == getVariation().getOverlappingProperties().size()-2)
//				question+= " and ";
//		}
//		
//		question += "? If that is not allowed, use the table below to choose which part "+
//					"types cannot be instantiated by the same object in the context of a single whole. Otherwise, just click 'Next'";
//		
//		return question;
//	}
	
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
		
		registerActions();
		
		if(getOverlappingWizard().hasNextVariation(getVariationIndex())) {
			return getOverlappingWizard().getDisjointPage(getVariationIndex()+1);
		}
		else {
			return getOverlappingWizard().getFinishing();
		}
	}
	
	
	
	
	
}
