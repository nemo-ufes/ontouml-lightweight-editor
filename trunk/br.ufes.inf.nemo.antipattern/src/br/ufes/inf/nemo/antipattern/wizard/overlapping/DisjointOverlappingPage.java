package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;

public abstract class DisjointOverlappingPage extends OverlappingWizardPage{
	
	public DisjointOverlappingPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName, occurrence, variationIndex);
	}
		
	@Override
	public void registerActions(){
		//register one action for each valid action in the table
		getOverlappingWizard().removeAllActions(getVariationIndex());
		for (ArrayList<Property> propertyEndList : getBuilder().getSelections()) {
			OverlappingAction action = new OverlappingAction(getOccurrence(),getVariation());
			action.setDisjoint(propertyEndList);
			getOverlappingWizard().addAction(getVariationIndex(), action);
		}
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		registerActions();
		
		if (canOpenExclusivePage()){
			return getOverlappingWizard().getExclusivePage(getVariationIndex());
		}
			
		else if (getOverlappingWizard().hasNextVariation(getVariationIndex())){
			return  getOverlappingWizard().getDisjointPage(getVariationIndex()+1);
		}
		else {
			return getOverlappingWizard().getFinishing();
		}
	}
	
	private boolean canOpenExclusivePage(){
		
		for (Property partEnd : getVariation().getOverlappingProperties()) {
			boolean isPropertyContained = false;
			for (ArrayList<Property> disjointList : getBuilder().getSelections()) {
				if(disjointList.contains(partEnd)){
					isPropertyContained = true;
					break;
				}
			}
			if(!isPropertyContained)
				return true;
		}	
		return false;
	
	}
	
}
